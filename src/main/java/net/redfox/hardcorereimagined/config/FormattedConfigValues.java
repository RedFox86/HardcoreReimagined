package net.redfox.hardcorereimagined.config;

import java.util.*;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
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
import oshi.util.tuples.Pair;

public class FormattedConfigValues {
  private static final Map<String, Set<String>> loaded = new HashMap<>();
  private static final Map<String, Set<String>> skipped = new HashMap<>();
  private static final Map<String, Set<String>> invalid = new HashMap<>();
  private static final List<String> duplicates = new ArrayList<>();

  public static class Temperature {
    public static final BooleanSupplier FLUCTUATE_TEMPERATURE =
        ModCommonConfigs.TEMPERATURE_FLUCTUATION::get;

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
  }

  public static class EnvironmentNerf {
    public static final Map<Difficulty, Double> CROP_GROWTH_DIFFICULTY_MULTIPLIER = new HashMap<>();
  }

  public static class FoodNerf {
    public static final List<FoodCategory> FOOD_CATEGORIES = new ArrayList<>();
    public static final Map<Item, FoodCategory> FOOD_MODIFICATIONS = new HashMap<>();
  }

  public static void populateConfigValues() {
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
      if (Arrays.stream(Difficulty.values())
          .anyMatch(element -> element.getKey().equals(split[0].toLowerCase()))) {
        EnvironmentNerf.CROP_GROWTH_DIFFICULTY_MULTIPLIER.put(
            Difficulty.valueOf(split[0]), Double.parseDouble(split[1]));
      } else {
        HardcoreReimagined.LOGGER.warn("Failed to load {}", split[0]);
      }
    }

    logLoadedSkippedInvalidDuplicates();
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
