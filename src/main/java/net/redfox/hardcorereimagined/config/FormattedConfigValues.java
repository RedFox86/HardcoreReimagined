package net.redfox.hardcorereimagined.config;

import java.util.*;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Difficulty;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import net.redfox.hardcorereimagined.HardcoreReimagined;
import net.redfox.hardcorereimagined.food.FoodCategory;
import net.redfox.hardcorereimagined.util.config.ConfigValue;
import net.redfox.hardcorereimagined.util.config.ListConfigValue;
import net.redfox.hardcorereimagined.util.config.SingleConfigValue;
import net.redfox.hardcorereimagined.util.config.TagConfigValue;
import oshi.util.tuples.Pair;

public class FormattedConfigValues {
  private static final Map<String, Set<String>> loaded = new HashMap<>();
  private static final Map<String, Set<String>> skipped = new HashMap<>();
  private static final Map<String, Set<String>> invalid = new HashMap<>();
  private static final List<String> duplicates = new ArrayList<>();

  public static class Temperature {
    public static final BooleanSupplier FLUCTUATE_TEMPERATURE =
        ModCommonConfigs.TEMPERATURE_FLUCTUATION::get;

    public static final BooleanSupplier BIOME_TEMPERATURE_ENABLED =
        ModCommonConfigs.BIOME_TEMPERATURES_ENABLED::get;
    public static final Map<ConfigValue<Biome>, Integer> BIOME_TEMPERATURES = new HashMap<>();

    public static final BooleanSupplier INSULATORS_ENABLED =
        ModCommonConfigs.INSULATORS_ENABLED::get;
    public static final Map<ConfigValue<Block>, Integer> INSULATOR_TEMPERATURES = new HashMap<>();
    public static final Supplier<Integer> INSULATORS_RANGE = ModCommonConfigs.INSULATORS_RANGE;
    public static final BooleanSupplier INSULATORS_DISTINCT =
        ModCommonConfigs.INSULATORS_DISTINCT::get;

    public static final BooleanSupplier FLUID_TEMPERATURE_ENABLED =
        ModCommonConfigs.FLUID_TEMPERATURES_ENABLED::get;
    public static final Map<ConfigValue<Block>, Integer> FLUID_TEMPERATURES = new HashMap<>();

    public static final BooleanSupplier BLOCK_TEMPERATURES_ENABLED =
        ModCommonConfigs.BLOCK_TEMPERATURES_ENABLED::get;
    public static final BooleanSupplier BLOCK_TEMPERATURES_NEED_BOOTS =
        ModCommonConfigs.BLOCK_TEMPERATURES_NEED_BOOTS::get;
    public static final Map<ConfigValue<Block>, Integer> BLOCK_TEMPERATURES = new HashMap<>();

    public static final BooleanSupplier WEATHER_TEMPERATURES_ENABLED =
        ModCommonConfigs.WEATHER_TEMPERATURE_ENABLED::get;
    public static final Supplier<Integer> RAIN_TEMPERATURE = ModCommonConfigs.RAIN_TEMPERATURE;
    public static final Supplier<Integer> SNOW_TEMPERATURE = ModCommonConfigs.SNOW_TEMPERATURE;

    public static final BooleanSupplier TIME_TEMPERATURE_ENABLED =
        ModCommonConfigs.TIME_TEMPERATURE_ENABLED::get;
    public static final Supplier<Integer> DAY_TEMPERATURE = ModCommonConfigs.DAY_TEMPERATURE;
    public static final Supplier<Integer> NIGHT_TEMPERATURE = ModCommonConfigs.NIGHT_TEMPERATURE;

    public static final BooleanSupplier FIRE_TEMPERATURE_ENABLED =
        ModCommonConfigs.FIRE_TEMPERATURES_ENABLED::get;
    public static final Supplier<Integer> FIRE_TEMPERATURE = ModCommonConfigs.FIRE_TEMPERATURE;

    public static final BooleanSupplier ALTITUDE_TEMPERATURE_ENABLED =
        ModCommonConfigs.ALTITUDE_TEMPERATURES_ENABLED::get;
    public static final Supplier<Integer> UPPER_ALTITUDE = ModCommonConfigs.UPPER_ALTITUDE;
    public static final Supplier<Double> UPPER_MULTIPLIER = ModCommonConfigs.UPPER_MULTIPLIER;
    public static final Supplier<Integer> LOWER_ALTITUDE = ModCommonConfigs.LOWER_ALTITUDE;
    public static final Supplier<Double> LOWER_MULTIPLIER = ModCommonConfigs.LOWER_MULTIPLIER;

    public static final BooleanSupplier ARMOR_INSULATIONS_ENABLED =
        ModCommonConfigs.ARMOR_INSULATORS_ENABLED::get;
    public static final Map<ConfigValue<Item>, Pair<Integer, Integer>> ARMOR_INSULATIONS =
        new HashMap<>();
  }

  public static class EnvironmentNerf {
    public static final Map<Difficulty, Double> CROP_GROWTH_DIFFICULTY_MULTIPLIER = new HashMap<>();
    public static final Map<ConfigValue<Block>, List<ConfigValue<Biome>>> CROP_GROWTH_BIOME_MULTIPLIER = new HashMap<>();
  }

  public static class FoodNerf {
    public static final List<FoodCategory> FOOD_CATEGORIES = new ArrayList<>();
    public static final Map<Item, FoodCategory> FOOD_MODIFICATIONS = new HashMap<>();
  }

  public static void populateConfigValues() {
    createSingleIntegerConfigValues(
        ModCommonConfigs.INSULATORS_VALUES.get(), Temperature.INSULATOR_TEMPERATURES, Block.class);
    createSingleIntegerConfigValues(
        ModCommonConfigs.FLUID_TEMPERATURES.get(), Temperature.FLUID_TEMPERATURES, Block.class);
    createSingleIntegerConfigValues(
        ModCommonConfigs.BLOCK_TEMPERATURES.get(), Temperature.BLOCK_TEMPERATURES, Block.class);
    createPairIntegerConfigValues(
        ModCommonConfigs.ARMOR_INSULATORS.get(), Temperature.ARMOR_INSULATIONS, Item.class);
    for (String entry : ModCommonConfigs.FOOD_CATEGORIES.get()) {
      String[] split = entry.split(",");
      FoodNerf.FOOD_CATEGORIES.add(
          new FoodCategory(
              Integer.parseInt(split[1]),
              Double.parseDouble(split[2]),
              Integer.parseInt(split[3]),
              split[0]));
    }
    for (String entry : ModCommonConfigs.FOOD_MODIFICATIONS.get()) {
      String[] split = entry.split(",");
      String itemName = split[0];
      String namespace = itemName.split(":")[0];
      Item item = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(itemName));
      if (!ModList.get().isLoaded(namespace)) {
        if (!getOrCreate(namespace, skipped).add(itemName)) duplicates.add(itemName);
      } else if (item == null || item.equals(Items.AIR)) {
        if (!getOrCreate(namespace, invalid).add(itemName)) {
          duplicates.add(itemName);
          continue;
        }
        HardcoreReimagined.LOGGER.warn("Failed to load {}", itemName);
      } else {
        if (!getOrCreate(namespace, loaded).add(itemName)) {
          duplicates.add(itemName);
          continue;
        }
        FoodNerf.FOOD_MODIFICATIONS.put(item, FoodCategory.getFromString(split[1]));
      }
    }
    for (String entry : ModCommonConfigs.CROP_GROWTH_DIFFICULTY_MULTIPLIERS.get()) {
      String[] split = entry.split(":");
      if (Arrays.stream(Difficulty.values()).anyMatch(element -> element.getKey().equals(split[0].toLowerCase()))) {
        EnvironmentNerf.CROP_GROWTH_DIFFICULTY_MULTIPLIER.put(Difficulty.valueOf(split[0]), Double.parseDouble(split[1]));
      } else {
        HardcoreReimagined.LOGGER.warn("Failed to load {}", split[0]);
      }
    }

    logLoadedSkippedInvalidDuplicates();
  }

  public static <E> void createSingleIntegerConfigValues(
      List<String> forgeConfigValue, Map<ConfigValue<E>, Integer> storage, Class<E> clazz) {
    for (String entry : forgeConfigValue) {
      if (entry.startsWith("[")) {
        ListConfigValue<E> configValue = new ListConfigValue<>(getStringArray(entry), clazz);
        if (configValue.isInvalid(clazz)) {
          continue;
        }
        storage.put(configValue, Integer.parseInt(entry.substring(entry.indexOf("]") + 2)));
      } else if (entry.startsWith("#")) {
        TagConfigValue<E> configValue = new TagConfigValue<>(getString(entry.substring(1)), clazz);
        if (configValue.isInvalid(clazz)) {
          continue;
        }
        storage.put(configValue, Integer.parseInt(entry.substring(entry.indexOf(",") + 1)));
      } else {
        SingleConfigValue<E> configValue = new SingleConfigValue<>(getString(entry), clazz);
        if (configValue.isInvalid(clazz)) {
          continue;
        }
        storage.put(configValue, Integer.parseInt(entry.substring(entry.indexOf(",") + 1)));
      }
    }
  }

  public static <E> void createPairIntegerConfigValues(
      List<String> forgeConfigValue,
      Map<ConfigValue<E>, Pair<Integer, Integer>> storage,
      Class<E> clazz) {
    for (String entry : forgeConfigValue) {
      if (entry.startsWith("[")) {
        String[] ints = entry.substring(entry.indexOf("]") + 2).split(",");
        ListConfigValue<E> configValue = new ListConfigValue<>(getStringArray(entry), clazz);
        if (configValue.isInvalid(clazz)) continue;
        storage.put(configValue, new Pair<>(Integer.parseInt(ints[0]), Integer.parseInt(ints[1])));
      } else if (entry.startsWith("#")) {
        String[] ints = entry.substring(entry.indexOf(",") + 1).split(",");
        TagConfigValue<E> configValue = new TagConfigValue<>(getString(entry.substring(1)), clazz);
        if (configValue.isInvalid(clazz)) continue;
        storage.put(configValue, new Pair<>(Integer.parseInt(ints[0]), Integer.parseInt(ints[1])));
      } else {
        String[] ints = entry.substring(entry.indexOf(",") + 1).split(",");
        SingleConfigValue<E> configValue = new SingleConfigValue<>(getString(entry), clazz);
        if (configValue.isInvalid(clazz)) continue;
        storage.put(configValue, new Pair<>(Integer.parseInt(ints[0]), Integer.parseInt(ints[1])));
      }
    }
  }

  public static <E> int checkMapForValue(Map<ConfigValue<E>, Integer> storage, E key) {
    for (ConfigValue<E> configValue : storage.keySet()) {
      if (configValue.is(key)) return storage.get(configValue);
    }
    return 0;
  }

  public static <E> Pair<Integer, Integer> checkMapForPair(
      Map<ConfigValue<E>, Pair<Integer, Integer>> storage, E key) {
    for (ConfigValue<E> configValue : storage.keySet()) {
      if (configValue.is(key)) return storage.get(configValue);
    }
    return new Pair<>(0, 0);
  }

  private static String[] getStringArray(String string) {
    return string.substring(1, string.indexOf("]")).split(",");
  }

  private static String getString(String string) {
    return string.substring(0, string.indexOf(","));
  }

  private static Set<String> getOrCreate(String key, Map<String, Set<String>> map) {
    if (map.containsKey(key)) return map.get(key);
    map.put(key, new HashSet<>());
    return map.get(key);
  }

  private static void logLoadedSkippedInvalidDuplicates() {
    for (String namespace : skipped.keySet()) {
      HardcoreReimagined.LOGGER.info(
          "{} "
              + ((skipped.get(namespace).size() == 1) ? "item from {} was" : "items from {} were")
              + " skipped.",
          skipped.get(namespace).size(),
          namespace);
    }
    for (String namespace : invalid.keySet()) {
      HardcoreReimagined.LOGGER.info(
          "{} invalid "
              + ((invalid.get(namespace).size() == 1) ? "item from {} was" : "items from {} were")
              + " skipped.",
          invalid.get(namespace).size(),
          namespace);
    }
    HardcoreReimagined.LOGGER.info(
        "{} duplicate " + (((duplicates.size() == 1)) ? "entry was" : "entries were") + " skipped.",
        duplicates.size());
    for (String namespace : loaded.keySet()) {
      HardcoreReimagined.LOGGER.info(
          "{} "
              + ((loaded.get(namespace).size() == 1) ? "item from {} was" : "items from {} were")
              + " successfully loaded.",
          loaded.get(namespace).size(),
          namespace);
    }
  }
}
