package net.redfox.hardcorereimagined.temperature;

import static net.redfox.hardcorereimagined.config.FormattedConfigValues.Temperature.*;

import com.google.gson.JsonArray;
import java.util.*;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.redfox.hardcorereimagined.config.ModCommonConfigs;
import net.redfox.hardcorereimagined.effect.HeatStrokeEffect;
import net.redfox.hardcorereimagined.effect.HypothermiaEffect;
import net.redfox.hardcorereimagined.networking.ModPackets;
import net.redfox.hardcorereimagined.networking.packet.TemperatureDataSyncS2CPacket;
import net.redfox.hardcorereimagined.util.MathHelper;
import net.redfox.hardcorereimagined.util.config.json.JsonConfigReader;
import oshi.util.tuples.Pair;

@AutoRegisterCapability
public class PlayerTemperature {
  public static final double DEFAULT_BIOME_TEMPERATURE = 0D;
  public static final JsonArray JSON_BIOME_TEMPERATURES =
      JsonConfigReader.getOrCreateJsonFile("temperature/biomes", JsonConfigReader.BIOMES)
          .get("values")
          .getAsJsonArray();
  public static final Map<Biome, Double> CACHED_BIOME_TEMPERATURES = new HashMap<>();
  public static final JsonArray JSON_INSULATOR_TEMPERATURES =
      JsonConfigReader.getOrCreateJsonFile("temperature/insulators", JsonConfigReader.INSULATORS)
          .get("values")
          .getAsJsonArray();
  public static final Map<Block, Double> CACHED_INSULATOR_TEMPERATURES = new HashMap<>();
  public static final JsonArray JSON_INSIDE_BLOCK_TEMPERATURES =
      JsonConfigReader.getOrCreateJsonFile(
              "temperature/inside_blocks", JsonConfigReader.INSIDE_BLOCKS)
          .get("values")
          .getAsJsonArray();
  public static final Map<Block, Double> CACHED_INSIDE_BLOCK_TEMPERATURES_NO_BOOTS =
      new HashMap<>();
  public static final Map<Block, Double> CACHED_INSIDE_BLOCK_TEMPERATURES_BOOTS = new HashMap<>();
  public static final JsonArray JSON_ON_TOP_BLOCK_TEMPERATURES =
      JsonConfigReader.getOrCreateJsonFile(
              "temperature/on_top_blocks", JsonConfigReader.ON_TOP_BLOCKS)
          .get("values")
          .getAsJsonArray();
  public static final Map<Block, Double> CACHED_ON_TOP_BLOCK_TEMPERATURES_NO_BOOTS =
      new HashMap<>();
  public static final Map<Block, Double> CACHED_ON_TOP_BLOCK_TEMPERATURES_BOOTS = new HashMap<>();
  public static final JsonArray JSON_ARMOR_INSULATIONS =
      JsonConfigReader.getOrCreateJsonFile(
              "temperature/armor_insulators", JsonConfigReader.ARMOR_INSULATORS)
          .get("values")
          .getAsJsonArray();
  public static final Map<Item, Pair<Double, Double>> CACHED_ARMOR_INSULATIONS = new HashMap<>();

  private double temperature = 0;

  public double getTemperature() {
    return MathHelper.roundToOneDecimal(temperature);
  }

  public void setTemperature(double temperature) {
    this.temperature = MathHelper.roundToOneDecimal(temperature);
  }

  public void approachTemperature(double goal) {
    double difference =
        MathHelper.roundToOneDecimal(
            ((Math.max(0.1, Math.min(5, Math.abs(goal - temperature) / 10))) * 10) / 10);

    if (temperature > goal) {
      temperature -= difference;
    } else if (temperature < goal) {
      temperature += difference;
    }
  }

  public void saveNBTData(CompoundTag nbt) {
    nbt.putDouble("temperature", MathHelper.roundToOneDecimal(temperature));
  }

  public void loadNBTData(CompoundTag nbt) {
    temperature = MathHelper.roundToOneDecimal(nbt.getDouble("temperature"));
  }

  public static void periodicUpdate(ServerPlayer player) {
    player
        .getCapability(PlayerTemperatureProvider.PLAYER_TEMPERATURE)
        .ifPresent(
            playerTemperature -> {
              double approachingTemperature = PlayerTemperature.calculateTemperatureGoal(player);
              double temp = playerTemperature.getTemperature();
              if (temp >= 80) {
                HeatStrokeEffect.applyStandardEffect(player, temp);
              } else if (temp <= -80) {
                HypothermiaEffect.applyStandardEffect(player, temp);
              }
              playerTemperature.approachTemperature(approachingTemperature);
              ModPackets.sendToClient(
                  new TemperatureDataSyncS2CPacket(playerTemperature.getTemperature()), player);
            });
  }

  public static double calculateTemperatureGoal(ServerPlayer player) {
    Level level = player.level();
    BlockState insideBlock = level.getBlockState(player.blockPosition());
    BlockState blockBelow = level.getBlockState(player.blockPosition().below());
    Holder<Biome> currentBiome = level.getBiome(player.blockPosition());
    double goalTemperature = 0;
    // Biome

    if (ModCommonConfigs.BIOME_TEMPERATURE_ENABLED.get()) {
      if (!CACHED_BIOME_TEMPERATURES.containsKey(currentBiome.get())) {
        JSON_BIOME_TEMPERATURES.asList().stream()
            .filter(
                value ->
                    value
                        .getAsJsonObject()
                        .get("biome")
                        .getAsString()
                        .equals(currentBiome.unwrapKey().get().location().toString()))
            .findFirst()
            .ifPresentOrElse(
                (value ->
                    CACHED_BIOME_TEMPERATURES.put(
                        currentBiome.get(),
                        value.getAsJsonObject().get("temperature").getAsDouble())),
                () -> CACHED_BIOME_TEMPERATURES.put(currentBiome.get(), DEFAULT_BIOME_TEMPERATURE));
      }

      goalTemperature += CACHED_BIOME_TEMPERATURES.get(currentBiome.get());
    }

    if (ModCommonConfigs.BLOCK_INSULATOR_TEMPERATURE_ENABLED.get()) {
      for (BlockState state :
          level.getBlockStates(player.getBoundingBox().inflate(5)).distinct().toList()) {
        if (!CACHED_INSULATOR_TEMPERATURES.containsKey(state.getBlock())) {
          JSON_INSULATOR_TEMPERATURES.asList().stream()
              .filter(
                  value ->
                      value
                          .getAsJsonObject()
                          .get("block")
                          .getAsString()
                          .equals(state.getBlockHolder().unwrapKey().get().location().toString()))
              .findFirst()
              .ifPresent(
                  (value ->
                      CACHED_INSULATOR_TEMPERATURES.put(
                          state.getBlock(),
                          value.getAsJsonObject().get("temperature").getAsDouble())));
        }

        goalTemperature += CACHED_INSULATOR_TEMPERATURES.getOrDefault(state.getBlock(), 0d);
      }
    }

    if (player.getItemBySlot(EquipmentSlot.FEET).isEmpty()) {
      if (ModCommonConfigs.INSIDE_BLOCK_TEMPERATURE_ENABLED.get()) {
        if (!CACHED_INSIDE_BLOCK_TEMPERATURES_BOOTS.containsKey(insideBlock.getBlock())) {
          JSON_INSIDE_BLOCK_TEMPERATURES.asList().stream()
              .filter(
                  value ->
                      value
                          .getAsJsonObject()
                          .get("block")
                          .getAsString()
                          .equals(
                              insideBlock
                                  .getBlockHolder()
                                  .unwrapKey()
                                  .get()
                                  .location()
                                  .toString())
                          && value.getAsJsonObject().get("boots").getAsBoolean())
              .findFirst()
              .ifPresent(
                  value ->
                      CACHED_INSIDE_BLOCK_TEMPERATURES_BOOTS.put(
                          insideBlock.getBlock(),
                          value.getAsJsonObject().get("temperature").getAsDouble()));
        }
        goalTemperature +=
            CACHED_INSIDE_BLOCK_TEMPERATURES_BOOTS.getOrDefault(insideBlock.getBlock(), 0d);
      }
      if (ModCommonConfigs.ON_TOP_BLOCK_TEMPERATURE_ENABLED.get()) {
        if (!CACHED_ON_TOP_BLOCK_TEMPERATURES_BOOTS.containsKey(blockBelow.getBlock())) {
          JSON_ON_TOP_BLOCK_TEMPERATURES.asList().stream()
              .filter(
                  value ->
                      value
                          .getAsJsonObject()
                          .get("block")
                          .getAsString()
                          .equals(
                              blockBelow.getBlockHolder().unwrapKey().get().location().toString())
                          && value.getAsJsonObject().get("boots").getAsBoolean())
              .findFirst()
              .ifPresent(
                  value ->
                      CACHED_ON_TOP_BLOCK_TEMPERATURES_BOOTS.put(
                          blockBelow.getBlock(),
                          value.getAsJsonObject().get("temperature").getAsDouble()));
        }
        goalTemperature +=
            CACHED_ON_TOP_BLOCK_TEMPERATURES_BOOTS.getOrDefault(blockBelow.getBlock(), 0d);
      }
    }

    if (ModCommonConfigs.ON_TOP_BLOCK_TEMPERATURE_ENABLED.get()) {
      if (!CACHED_ON_TOP_BLOCK_TEMPERATURES_NO_BOOTS.containsKey(blockBelow.getBlock())) {
        JSON_ON_TOP_BLOCK_TEMPERATURES.asList().stream()
            .filter(
                value ->
                    value
                        .getAsJsonObject()
                        .get("block")
                        .getAsString()
                        .equals(
                            blockBelow.getBlockHolder().unwrapKey().get().location().toString())
                        && !value.getAsJsonObject().get("boots").getAsBoolean())
            .findFirst()
            .ifPresent(
                value ->
                    CACHED_ON_TOP_BLOCK_TEMPERATURES_NO_BOOTS.put(
                        blockBelow.getBlock(),
                        value.getAsJsonObject().get("temperature").getAsDouble()));
      }

      goalTemperature +=
          CACHED_ON_TOP_BLOCK_TEMPERATURES_NO_BOOTS.getOrDefault(blockBelow.getBlock(), 0d);
    }

    if (ModCommonConfigs.ON_TOP_BLOCK_TEMPERATURE_ENABLED.get()) {
      if (!CACHED_INSIDE_BLOCK_TEMPERATURES_NO_BOOTS.containsKey(insideBlock.getBlock())) {
        JSON_INSIDE_BLOCK_TEMPERATURES.asList().stream()
            .filter(
                value ->
                    value
                        .getAsJsonObject()
                        .get("block")
                        .getAsString()
                        .equals(
                            insideBlock.getBlockHolder().unwrapKey().get().location().toString())
                        && !value.getAsJsonObject().get("boots").getAsBoolean())
            .findFirst()
            .ifPresent(
                value ->
                    CACHED_INSIDE_BLOCK_TEMPERATURES_NO_BOOTS.put(
                        insideBlock.getBlock(),
                        value.getAsJsonObject().get("temperature").getAsDouble()));
      }

      goalTemperature +=
          CACHED_INSIDE_BLOCK_TEMPERATURES_NO_BOOTS.getOrDefault(insideBlock.getBlock(), 0d);
    }

    // Rain / snow / thunder
    if (ModCommonConfigs.WEATHER_TEMPERATURE_ENABLED.get()) {
      if (level.isRainingAt(player.blockPosition())) {
        goalTemperature += RAIN_TEMPERATURE.get();
      }
      if (level.isRaining()
          && level.getBiome(player.blockPosition()).value().coldEnoughToSnow(player.blockPosition())
          && level.canSeeSky(player.blockPosition())) {
        goalTemperature += SNOW_TEMPERATURE.get();
      }
    }

    // Day or night
    if (ModCommonConfigs.TIME_TEMPERATURE_ENABLED.get()) {
      if (level.isDay() && level.dimension() == Level.OVERWORLD) {
        goalTemperature += DAY_TEMPERATURE.get();
      } else {
        goalTemperature += NIGHT_TEMPERATURE.get();
      }
    }
    // On fire

    if (ModCommonConfigs.FIRE_TEMPERATURE_ENABLED.get() && player.getRemainingFireTicks() > 0) {
      goalTemperature += FIRE_TEMPERATURE.get();
    }

    // Altitude
    if (ModCommonConfigs.ALTITUDE_TEMPERATURE_ENABLED.get()) {
      if (player.getY() < LOWER_ALTITUDE.get()) {
        goalTemperature -=
            MathHelper.roundToOneDecimal(
                Math.abs(((player.getY() - LOWER_ALTITUDE.get()) / LOWER_MULTIPLIER.get())));
      }
      if (player.getY() > UPPER_ALTITUDE.get()) {
        goalTemperature -=
            MathHelper.roundToOneDecimal(
                Math.abs(((UPPER_ALTITUDE.get() - player.getY()) / UPPER_MULTIPLIER.get())));
      }
    }
    if (ModCommonConfigs.ARMOR_INSULATOR_TEMPERATURE_ENABLED.get()) {
      int heatResistance = 0;
      int coldResistance = 0;

      for (ItemStack i : player.getArmorSlots()) {
        if (!CACHED_ARMOR_INSULATIONS.containsKey(i.getItem())) {
          JSON_ARMOR_INSULATIONS.asList().stream()
              .filter(
                  value ->
                      value
                          .getAsJsonObject()
                          .get("item")
                          .getAsString()
                          .equals(i.getItemHolder().unwrapKey().get().location().toString()))
              .findFirst()
              .ifPresent(
                  value ->
                      CACHED_ARMOR_INSULATIONS.put(
                          i.getItem(),
                          new Pair<>(
                              value.getAsJsonObject().get("heat_resistance").getAsDouble(),
                              value.getAsJsonObject().get("cold_resistance").getAsDouble())));
        }

        Pair<Double, Double> pair =
            CACHED_ARMOR_INSULATIONS.getOrDefault(i.getItem(), new Pair<>(0D, 0D));
        heatResistance += pair.getA();
        coldResistance += pair.getB();

        if (goalTemperature < 0) {
          goalTemperature += coldResistance;
          if (goalTemperature > 0) goalTemperature = 0;
        } else if (goalTemperature > 0) {
          goalTemperature -= heatResistance;
          if (goalTemperature < 0) goalTemperature = 0;
        }
      }
    }

    return goalTemperature
        + (FLUCTUATE_TEMPERATURE.getAsBoolean()
            ? MathHelper.roundToOneDecimal(Math.random() * 2 - 0.5)
            : 0);
  }
}
