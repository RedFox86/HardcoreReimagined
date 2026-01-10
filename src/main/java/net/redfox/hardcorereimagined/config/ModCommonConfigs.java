package net.redfox.hardcorereimagined.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraftforge.common.ForgeConfigSpec;

public class ModCommonConfigs {
  private static final ArrayList<String> DEFAULT_DIFFICULTY_MULTIPLIER =
      new ArrayList<>(Arrays.asList("EASY:2", "NORMAL:4", "HARD:8", "PEACEFUL:1"));

  public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
  public static final ForgeConfigSpec SPEC;

  public static final ForgeConfigSpec.ConfigValue<List<String>> BABY_GROWTH_MODIFIERS;
  public static final ForgeConfigSpec.ConfigValue<List<String>> BREEDING_COOLDOWN_MULTIPLIERS;
  public static final ForgeConfigSpec.ConfigValue<List<String>> EGG_TIME_MODIFIERS;
  public static final ForgeConfigSpec.ConfigValue<List<String>> CROP_GROWTH_DIFFICULTY_MULTIPLIERS;
  public static final ForgeConfigSpec.ConfigValue<List<String>> CROP_GROWTH_BIOME_MULTIPLIERS;
  public static final ForgeConfigSpec.ConfigValue<List<String>> GLOBAL_HUNGER_MULTIPLIERS;
  public static final ForgeConfigSpec.ConfigValue<Double> SPAWN_HEALTH_MULTIPLIER;
  public static final ForgeConfigSpec.ConfigValue<Double> SPAWN_HUNGER_MULTIPLIER;
  public static final ForgeConfigSpec.ConfigValue<Integer> EGG_COOLDOWN;

  public static final ForgeConfigSpec.ConfigValue<Boolean> TEMPERATURE_FLUCTUATION;

  public static final ForgeConfigSpec.ConfigValue<Boolean> BIOME_TEMPERATURE_ENABLED;
  public static final ForgeConfigSpec.ConfigValue<Boolean> BLOCK_INSULATOR_TEMPERATURE_ENABLED;
  public static final ForgeConfigSpec.ConfigValue<Boolean> ON_TOP_BLOCK_TEMPERATURE_ENABLED;
  public static final ForgeConfigSpec.ConfigValue<Boolean> INSIDE_BLOCK_TEMPERATURE_ENABLED;
  public static final ForgeConfigSpec.ConfigValue<Boolean> ARMOR_INSULATOR_TEMPERATURE_ENABLED;

  public static final ForgeConfigSpec.ConfigValue<Boolean> WEATHER_TEMPERATURE_ENABLED;
  public static final ForgeConfigSpec.ConfigValue<Integer> RAIN_TEMPERATURE;
  public static final ForgeConfigSpec.ConfigValue<Integer> SNOW_TEMPERATURE;

  public static final ForgeConfigSpec.ConfigValue<Boolean> TIME_TEMPERATURE_ENABLED;
  public static final ForgeConfigSpec.ConfigValue<Integer> DAY_TEMPERATURE;
  public static final ForgeConfigSpec.ConfigValue<Integer> NIGHT_TEMPERATURE;

  public static final ForgeConfigSpec.ConfigValue<Boolean> FIRE_TEMPERATURE_ENABLED;
  public static final ForgeConfigSpec.ConfigValue<Integer> FIRE_TEMPERATURE;

  public static final ForgeConfigSpec.ConfigValue<Boolean> ALTITUDE_TEMPERATURE_ENABLED;
  public static final ForgeConfigSpec.ConfigValue<Integer> UPPER_ALTITUDE;
  public static final ForgeConfigSpec.ConfigValue<Integer> LOWER_ALTITUDE;
  public static final ForgeConfigSpec.ConfigValue<Double> UPPER_MULTIPLIER;
  public static final ForgeConfigSpec.ConfigValue<Double> LOWER_MULTIPLIER;

  public static final ForgeConfigSpec.ConfigValue<Boolean> FOOD_MODIFICATION_ENABLED;
  public static final ForgeConfigSpec.ConfigValue<List<String>> FOOD_CATEGORIES;
  public static final ForgeConfigSpec.ConfigValue<List<String>> FOOD_MODIFICATIONS;

  public static final ForgeConfigSpec.ConfigValue<Boolean> FOOD_HISTORY_ENABLED;
  public static final ForgeConfigSpec.ConfigValue<List<Double>> NUTRITION_DECAY;
  public static final ForgeConfigSpec.ConfigValue<Integer> MAX_HISTORY;

  public static final ForgeConfigSpec.ConfigValue<Boolean> GLOBAL_TEMPERATURE_TOGGLE;
  public static final ForgeConfigSpec.ConfigValue<Boolean> GLOBAL_ENVIRONMENT_NERF_TOGGLE;
  public static final ForgeConfigSpec.ConfigValue<Boolean> GLOBAL_FOOD_NERF_TOGGLE;
  public static final ForgeConfigSpec.ConfigValue<Boolean> GLOBAL_HEALTH_TOGGLE;

  static {
    BUILDER.push("common");
    {
      BUILDER.push("modules");
      {
        GLOBAL_TEMPERATURE_TOGGLE =
            BUILDER
                .comment("Global toggle for the temperature module")
                .comment("This includes:")
                .comment("  -The temperature bubble, data, and symptoms")
                .define("global_temperature_toggle", true);
        GLOBAL_ENVIRONMENT_NERF_TOGGLE =
            BUILDER
                .comment("Global toggle for the environment nerf module")
                .comment("This includes:")
                .comment("  -Increased animal breeding cooldown")
                .comment("  -Increased chicken egg laying cooldown")
                .comment("  -Increased baby age timer")
                .comment("  -Increased crop growth time")
                .comment("  -Crop growth depending on daytime")
                .comment("  -Crop growth depending on biome")
                .define("global_environment_nerf", true);
        GLOBAL_FOOD_NERF_TOGGLE =
            BUILDER
                .comment("Global toggle for the food nerf module")
                .comment("This includes:")
                .comment(
                    "  -Greatly reduce the nutrition value and saturation of every food in minecraft")
                .comment("  -The decaying food values of each food as you eat it more")
                .define("global_food_nerf_toggle", true);
        GLOBAL_HEALTH_TOGGLE =
            BUILDER
                .comment("Global toggle for the health nerf module")
                .comment("This includes:")
                .comment("  -Symptoms when low on hunger or health")
                .comment("  -Custom health and hunger on respawn")
                .define("global_health_toggle", true);
      }
      BUILDER.pop();
      BUILDER.push("temperature");
      {
        TEMPERATURE_FLUCTUATION = BUILDER
            .comment("If true, enables global temperature fluctuation.")
            .define("temperature_fluctuation", true);

        BUILDER.push("biomes");
        {
          BIOME_TEMPERATURE_ENABLED = BUILDER
              .comment("If true, enables temperature fluctuation based on biomes.")
              .define("biome_temperature_enabled", true);

          BUILDER.comment("Biomes can be modified in the JSON file at /hardcorereimagined/temperature/biomes.json");
        }
        BUILDER.pop();

        BUILDER.push("insulators");
        {
          BLOCK_INSULATOR_TEMPERATURE_ENABLED = BUILDER
              .comment("If true, enables temperature fluctuation based on block insulators.")
              .define("block_insulator_temperature_enabled", true);

          BUILDER.comment("Block insulators can be modified in the JSON file at /hardcorereimagined/temperature/insulators.json");
        }
        BUILDER.pop();

        BUILDER.push("inside_blocks");
        {
          INSIDE_BLOCK_TEMPERATURE_ENABLED = BUILDER
              .comment("If true, enables temperature fluctuation based on inside blocks.")
              .define("inside_block_temperature_enabled", true);

          BUILDER.comment("Inside blocks can be modified in the JSON file at /hardcorereimagined/temperature/inside_blocks.json");
        }
        BUILDER.pop();

        BUILDER.push("on_top_blocks");
        {
          ON_TOP_BLOCK_TEMPERATURE_ENABLED = BUILDER
              .comment("If true, enables temperature fluctuation based on on-top blocks.")
              .define("on_top_block_temperature_enabled", true);

          BUILDER.comment("On-top blocks can be modified in the JSON file at /hardcorereimagined/temperature/on_top_blocks.json");
        }
        BUILDER.pop();

        BUILDER.push("weather");
        {
          WEATHER_TEMPERATURE_ENABLED = BUILDER
              .comment("If true, enables temperature fluctuation based on weather.")
              .define("weather_temperature_enabled", true);

          RAIN_TEMPERATURE = BUILDER
              .comment("The temperature that is applied when exposed to rain")
              .define("rain_temperature", -30);

          SNOW_TEMPERATURE = BUILDER
              .comment("The temperature that is applied when exposed to snow")
              .define("snow_temperature", -60);
        }
        BUILDER.pop();

        BUILDER.push("time");
        {
          TIME_TEMPERATURE_ENABLED = BUILDER
              .comment("If true, enables temperature fluctuation based on time.")
              .define("time_temperature_enabled", true);

          DAY_TEMPERATURE = BUILDER
              .comment("The temperature that is applied when it is daytime")
              .define("day_temperature", 10);

          NIGHT_TEMPERATURE = BUILDER
              .comment("The temperature that is applied when it is nighttime")
              .define("night_temperature", -10);
        }
        BUILDER.pop();

        BUILDER.push("fire");
        {
          FIRE_TEMPERATURE_ENABLED = BUILDER
              .comment("If true, enables temperature fluctuation based on fire.")
              .define("fire_temperature_enabled", true);

          FIRE_TEMPERATURE = BUILDER
              .comment("The temperature that is applied when the player is on fire")
              .defineInRange("fire_temperature", 50, 1, Integer.MAX_VALUE);
        }
        BUILDER.pop();

        BUILDER.push("altitude");
        {
          ALTITUDE_TEMPERATURE_ENABLED = BUILDER
              .comment("If true, enables temperature fluctuation based on altitude.")
              .define("altitude_temperature_enabled", true);

          UPPER_ALTITUDE = BUILDER
              .comment("The upwards y-value when temperature will start changing")
              .define("upper_altitude", 80);

          UPPER_MULTIPLIER = BUILDER
              .comment("The amount of blocks that result in a change of 1 temperature")
              .comment("For example, a value of 5 means that every 5 blocks traveled after the start will change your temperature by 1")
              .defineInRange("upper_multiplier", 5, 1, 128d);

          LOWER_ALTITUDE = BUILDER
              .comment("The downwards y-value when temperature will start changing")
              .define("lower_altitude", 40);

          LOWER_MULTIPLIER = BUILDER
              .comment("The amount of blocks that result in a change of 1 temperature")
              .comment("For example, a value of 5 means that every 5 blocks traveled after the start will change your temperature by 1")
              .defineInRange("lower_multiplier", 5, 1, 128d);
        }
        BUILDER.pop();

        BUILDER.push("armor_insulators");
        {
          ARMOR_INSULATOR_TEMPERATURE_ENABLED = BUILDER
              .comment("If true, enables temperature fluctuation based on armor insulators.")
              .define("armor_insulator_temperature_enabled", true);

          BUILDER.comment("Armor insulators can be modified in the JSON file at /hardcorereimagined/temperature/armor_insulators.json");
        }
        BUILDER.pop();
      }
      BUILDER.pop();
      BUILDER.push("environment_nerf");
      {
        BUILDER.push("crops");
        {
          CROP_GROWTH_DIFFICULTY_MULTIPLIERS =
              BUILDER
                  .comment("The modifier for the growth time of crops depending on difficulty.")
                  .define("cropGrowthDifficultyMultiplier", DEFAULT_DIFFICULTY_MULTIPLIER);
          CROP_GROWTH_BIOME_MULTIPLIERS =
              BUILDER
                  .comment("The modifier for the growth time of crops depending on biome.")
                  .define(
                      "cropGrowthBiomeMultiplier",
                      new ArrayList<>(
                          List.of(
                              "[minecraft:wheat,minecraft:carrots,minecraft:potatoes,minecraft:beetroots],[minecraft:meadow,minecraft:cherry_grove,minecraft:flower_forest,minecraft:forest,minecraft:birch_forest,minecraft:sparse_jungle,minecraft:plains,minecraft:sunflower_plains]",
                              "[minecraft:pumpkin_stem,minecraft:melon_stem],[minecraft:sparse_jungle,minecraft:jungle,minecraft:bamboo_jungle,minecraft:savanna,minecraft:savanna_plateau,minecraft:windswept_savanna]",
                              "[minecraft:bamboo,minecraft:cocoa],[minecraft:jungle,minecraft:sparse_jungle,minecraft:bamboo_jungle]",
                              "minecraft:sugar_cane,[minecraft:river,minecraft:swamp,minecraft:beach,minecraft:savanna,minecraft:savanna_plateau,minecraft:windswept_savanna]",
                              "minecraft:sweet_berry_bush,[minecraft:taiga,minecraft:old_growth_pine_taiga,minecraft:old_growth_spruce_taiga]",
                              "minecraft:cactus,[minecraft:desert,minecraft:badlands,minecraft:eroded_badlands]",
                              "minecraft:kelp,[minecraft:ocean,minecraft:deep_ocean,minecraft:warm_ocean,minecraft:lukewarm_ocean,minecraft:deep_lukewarm_ocean]",
                              "minecraft:cave_vines,minecraft:lush_caves",
                              "minecraft:nether_wart,#minecraft:is_nether")));
        }
        BUILDER.pop();
        BABY_GROWTH_MODIFIERS =
            BUILDER
                .comment("The modifier for the growth time of baby animals.")
                .define("babyGrowthMultiplier", DEFAULT_DIFFICULTY_MULTIPLIER);
        BREEDING_COOLDOWN_MULTIPLIERS =
            BUILDER
                .comment("The modifier for the breeding cooldown of animals.")
                .define("breedingCooldownMultiplier", DEFAULT_DIFFICULTY_MULTIPLIER);
        EGG_COOLDOWN =
            BUILDER.comment("The base cooldown for eggs being laid").define("eggCooldown", 6000);
        EGG_TIME_MODIFIERS =
            BUILDER
                .comment("The modifier for the egg laying cooldown of chickens.")
                .define("eggCooldownMultiplier", DEFAULT_DIFFICULTY_MULTIPLIER);
      }
      BUILDER.pop();
      BUILDER.push("food");
      {
        GLOBAL_HUNGER_MULTIPLIERS =
            BUILDER
                .comment(
                    "The global hunger multiplier. Every single action's hunger loss will be multiplied by this.")
                .define(
                    "globalHungerMultiplier",
                    new ArrayList<>(Arrays.asList("EASY:1.5", "NORMAL:2", "HARD:3", "PEACEFUL:1")));
        BUILDER.push("overrides");
        {
          FOOD_MODIFICATION_ENABLED =
              BUILDER
                  .comment("If true, enables the modification of specified foods.")
                  .define("foodModificationEnabled", true);

          FOOD_CATEGORIES =
              BUILDER
                  .comment(
                      "Defines the name, nutrition value, saturation multiplier, and max stack size for each type of food")
                  .define(
                      "foodTypes",
                      new ArrayList<>(
                          Arrays.asList(
                              "MORSEL,1,0.5,16",
                              "SNACK,2,0.75,8",
                              "LIGHT_MEAL,3,1,4",
                              "MEAL,4,1.25,2",
                              "HEAVY_MEAL,5,1.5,1",
                              "FEAST,6,2,1")));
          FOOD_MODIFICATIONS =
              BUILDER
                  .comment(
                      "Overrides food, saturation, and max stack size values for foods in the format: namespace:food,hunger,saturation OR namespace:food,FOOD_TYPE")
                  .comment("Possible food types are defined above")
                  .define(
                      "foodValues",
                      new ArrayList<>(
                          Arrays.asList(
                              "minecraft:apple,MORSEL",
                              "minecraft:baked_potato,SNACK",
                              "minecraft:beetroot,MORSEL",
                              "minecraft:beetroot_soup,SNACK",
                              "minecraft:bread,LIGHT_MEAL",
                              "minecraft:carrot,MORSEL",
                              "minecraft:chorus_fruit,MORSEL",
                              "minecraft:cooked_chicken,MEAL",
                              "minecraft:cooked_cod,LIGHT_MEAL",
                              "minecraft:cooked_porkchop,MEAL",
                              "minecraft:cooked_rabbit,MEAL",
                              "minecraft:cooked_salmon,LIGHT_MEAL",
                              "minecraft:cookie,MORSEL",
                              "minecraft:dried_kelp,MORSEL",
                              "minecraft:enchanted_golden_apple,SNACK",
                              "minecraft:golden_apple,SNACK",
                              "minecraft:glow_berries,MORSEL",
                              "minecraft:golden_carrot,LIGHT_MEAL",
                              "minecraft:honey_bottle,SNACK",
                              "minecraft:melon_slice,MORSEL",
                              "minecraft:mushroom_stew,LIGHT_MEAL",
                              "minecraft:poisonous_potato,MORSEL",
                              "minecraft:potato,MORSEL",
                              "minecraft:pufferfish,MORSEL",
                              "minecraft:pumpkin_pie,MEAL",
                              "minecraft:rabbit_stew,FEAST",
                              "minecraft:beef,SNACK",
                              "minecraft:chicken,SNACK",
                              "minecraft:cod,SNACK",
                              "minecraft:mutton,SNACK",
                              "minecraft:porkchop,SNACK",
                              "minecraft:salmon,SNACK",
                              "minecraft:rotten_flesh,MORSEL",
                              "minecraft:spider_eye,MORSEL",
                              "minecraft:cooked_beef,HEAVY_MEAL",
                              "minecraft:suspicious_stew,LIGHT_MEAL",
                              "minecraft:sweet_berries,MORSEL",
                              "minecraft:tropical_fish,MORSEL",
                              "farmersdelight:cabbage,MORSEL",
                              "farmersdelight:tomato,MORSEL",
                              "farmersdelight:onion,MORSEL",
                              "farmersdelight:cabbage,MORSEL",
                              "farmersdelight:fried_egg,SNACK",
                              "farmersdelight:cabbage,MORSEL",
                              "farmersdelight:tomato_sauce,SNACK",
                              "farmersdelight:wheat_dough,SNACK",
                              "farmersdelight:raw_pasta,SNACK",
                              "farmersdelight:pumpkin_slice,MORSEL",
                              "farmersdelight:cabbage_leaf,MORSEL",
                              "farmersdelight:minced_beef,MORSEL",
                              "farmersdelight:beef_patty,LIGHT_MEAL",
                              "farmersdelight:chicken_cuts,MORSEL",
                              "farmersdelight:cooked_chicken_cuts,SNACK",
                              "farmersdelight:bacon,MORSEL",
                              "farmersdelight:cooked_bacon,SNACK",
                              "farmersdelight:cod_slice,MORSEL",
                              "farmersdelight:cooked_cod_slice,SNACK",
                              "farmersdelight:salmon_slice,MORSEL",
                              "farmersdelight:cooked_salmon_slice,SNACK",
                              "farmersdelight:mutton_chops,MORSEL",
                              "farmersdelight:cooked_mutton_chops,SNACK",
                              "farmersdelight:ham,MORSEL",
                              "farmersdelight:smoked_ham,LIGHT_MEAL",
                              "farmersdelight:pie_crust,MORSEL",
                              "farmersdelight:apple_pie_slice,LIGHT_MEAL",
                              "farmersdelight:sweet_berry_cheesecake_slice,LIGHT_MEAL",
                              "farmersdelight:chocolate_pie_slice,LIGHT_MEAL",
                              "farmersdelight:cake_slice,LIGHT_MEAL",
                              "farmersdelight:sweet_berry_cookie,SNACK",
                              "farmersdelight:honey_cookie,SNACK",
                              "farmersdelight:melon_popsicle,SNACK",
                              "farmersdelight:glow_berry_custard,LIGHT_MEAL",
                              "farmersdelight:fruit_salad,LIGHT_MEAL",
                              "farmersdelight:mixed_salad,LIGHT_MEAL",
                              "farmersdelight:nether_salad,LIGHT_MEAL",
                              "farmersdelight:barbecue_stick,MEAL",
                              "farmersdelight:egg_sandwich,MEAL",
                              "farmersdelight:chicken_sandwich,MEAL",
                              "farmersdelight:hamburger,MEAL",
                              "farmersdelight:bacon_sandwich,MEAL",
                              "farmersdelight:mutton_wrap,MEAL",
                              "farmersdelight:dumplings,LIGHT_MEAL",
                              "farmersdelight:stuffed_potato,MEAL",
                              "farmersdelight:cabbage_rolls,SNACK",
                              "farmersdelight:salmon_roll,MEAL",
                              "farmersdelight:cod_roll,MEAL",
                              "farmersdelight:kelp_roll,LIGHT_MEAL",
                              "farmersdelight:kelp_roll_slice,SNACK",
                              "farmersdelight:cooked_rice,MORSEL",
                              "farmersdelight:bone_broth,SNACK",
                              "farmersdelight:beef_stew,MEAL",
                              "farmersdelight:chicken_soup,MEAL",
                              "farmersdelight:vegetable_soup,LIGHT_MEAL",
                              "farmersdelight:fish_stew,MEAL",
                              "farmersdelight:egg_sandwich,MEAL",
                              "farmersdelight:fried_rice,MEAL",
                              "farmersdelight:pumpkin_soup,MEAL",
                              "farmersdelight:baked_cod_stew,MEAL",
                              "farmersdelight:noodle_soup,HEAVY_MEAL",
                              "farmersdelight:bacon_and_eggs,HEAVY_MEAL",
                              "farmersdelight:pasta_with_meatballs,MEAL",
                              "farmersdelight:pasta_with_mutton_chop,MEAL",
                              "farmersdelight:mushroom_rice,LIGHT_MEAL",
                              "farmersdelight:roasted_mutton_chops,HEAVY_MEAL",
                              "farmersdelight:vegetable_noodles,HEAVY_MEAL",
                              "farmersdelight:steak_and_potatoes,HEAVY_MEAL",
                              "farmersdelight:ratatouille,MEAL",
                              "farmersdelight:squid_ink_pasta,HEAVY_MEAL",
                              "farmersdelight:grilled_salmon,MEAL",
                              "farmersdelight:roast_chicken,LIGHT_MEAL",
                              "farmersdelight:stuffed_pumpkin,LIGHT_MEAL",
                              "farmersdelight:honey_glazed_ham,LIGHT_MEAL",
                              "farmersdelight:shepherds_pie,LIGHT_MEAL",
                              "farmersdelight:dog_food,SNACK")));
        }
        BUILDER.pop();
        BUILDER.push("foodHistory");
        {
          FOOD_HISTORY_ENABLED =
              BUILDER
                  .comment(
                      "If true, enables progressive decay from eating the same food repeatedly.")
                  .define("foodHistoryEnabled", true);

          NUTRITION_DECAY =
              BUILDER
                  .comment(
                      "The sequence for the decay on the nutrition values of food. Note that each value is a flat value, so they are not applied on top of each other.",
                      "1.0 means 100% of the nutritional value, while 0.0 means 0%.")
                  .define(
                      "nutritionalDecay",
                      new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, 0.75, 0.5, 0.25, 0.0)));

          MAX_HISTORY =
              BUILDER
                  .comment("The maximum food history that is stored.")
                  .defineInRange("Max History", 30, 3, 100);
        }
        BUILDER.pop();
      }
      BUILDER.pop();
      BUILDER.push("health");
      {
        SPAWN_HEALTH_MULTIPLIER =
            BUILDER
                .comment("The multiplier applied to health upon respawn")
                .define("spawnHealthMultiplier", 0.5);
        SPAWN_HUNGER_MULTIPLIER =
            BUILDER
                .comment("The multiplier applied to hunger upon respawn")
                .define("spawnHungerMultiplier", 0.5);
      }
      BUILDER.pop();
      SPEC = BUILDER.build();
    }
    BUILDER.pop();
  }
}
