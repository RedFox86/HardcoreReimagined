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

  public static final ForgeConfigSpec.ConfigValue<Boolean> BIOME_TEMPERATURES_ENABLED;
  public static final ForgeConfigSpec.ConfigValue<List<String>> BIOME_TEMPERATURES_VALUES;

  public static final ForgeConfigSpec.ConfigValue<Boolean> INSULATORS_ENABLED;
  public static final ForgeConfigSpec.ConfigValue<List<String>> INSULATORS_VALUES;
  public static final ForgeConfigSpec.ConfigValue<Integer> INSULATORS_RANGE;
  public static final ForgeConfigSpec.ConfigValue<Boolean> INSULATORS_DISTINCT;

  public static final ForgeConfigSpec.ConfigValue<Boolean> FLUID_TEMPERATURES_ENABLED;
  public static final ForgeConfigSpec.ConfigValue<List<String>> FLUID_TEMPERATURES;

  public static final ForgeConfigSpec.ConfigValue<Boolean> BLOCK_TEMPERATURES_ENABLED;
  public static final ForgeConfigSpec.ConfigValue<Boolean> BLOCK_TEMPERATURES_NEED_BOOTS;
  public static final ForgeConfigSpec.ConfigValue<List<String>> BLOCK_TEMPERATURES;

  public static final ForgeConfigSpec.ConfigValue<Boolean> WEATHER_TEMPERATURE_ENABLED;
  public static final ForgeConfigSpec.ConfigValue<Integer> RAIN_TEMPERATURE;
  public static final ForgeConfigSpec.ConfigValue<Integer> SNOW_TEMPERATURE;

  public static final ForgeConfigSpec.ConfigValue<Boolean> TIME_TEMPERATURE_ENABLED;
  public static final ForgeConfigSpec.ConfigValue<Integer> DAY_TEMPERATURE;
  public static final ForgeConfigSpec.ConfigValue<Integer> NIGHT_TEMPERATURE;

  public static final ForgeConfigSpec.ConfigValue<Boolean> FIRE_TEMPERATURES_ENABLED;
  public static final ForgeConfigSpec.ConfigValue<Integer> FIRE_TEMPERATURE;

  public static final ForgeConfigSpec.ConfigValue<Boolean> ALTITUDE_TEMPERATURES_ENABLED;
  public static final ForgeConfigSpec.ConfigValue<Integer> UPPER_ALTITUDE;
  public static final ForgeConfigSpec.ConfigValue<Integer> LOWER_ALTITUDE;
  public static final ForgeConfigSpec.ConfigValue<Double> UPPER_MULTIPLIER;
  public static final ForgeConfigSpec.ConfigValue<Double> LOWER_MULTIPLIER;

  public static final ForgeConfigSpec.ConfigValue<Boolean> ARMOR_INSULATORS_ENABLED;
  public static final ForgeConfigSpec.ConfigValue<List<String>> ARMOR_INSULATORS;

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
        TEMPERATURE_FLUCTUATION =
            BUILDER
                .comment(
                    "If true, the temperature will fluctuate around its goal by a margin of one.")
                .define("temperatureFluctuation", true);
        BUILDER.push("biome");
        {
          BIOME_TEMPERATURES_ENABLED =
              BUILDER
                  .comment("If true, enables temperature fluctuation based on biome.")
                  .define("biome_temperature_enabled", true);

          BIOME_TEMPERATURES_VALUES =
              BUILDER
                  .comment("A Map of all biomes and their temperature values.")
                  .define(
                      "biomeTemperature",
                      new ArrayList<>(
                          Arrays.asList(
                              "minecraft:ocean,-20",
                              "minecraft:deep_ocean,-30",
                              "minecraft:warm_ocean,20",
                              "minecraft:lukewarm_ocean,5",
                              "minecraft:deep_lukewarm_ocean,-5",
                              "minecraft:cold_ocean,-40",
                              "minecraft:deep_cold_ocean,-50",
                              "minecraft:frozen_ocean,-60",
                              "minecraft:deep_frozen_ocean,-70",
                              "minecraft:mushroom_fields,0",
                              "minecraft:jagged_peaks,-80",
                              "minecraft:frozen_peaks,-100",
                              "minecraft:stony_peaks,-60",
                              "minecraft:meadow,-10",
                              "minecraft:cherry_grove,10",
                              "minecraft:grove,-50",
                              "minecraft:windswept_hills,-30",
                              "minecraft:windswept_gravelly_hills,-30",
                              "minecraft:windswept_forest,-30",
                              "minecraft:forest,10",
                              "minecraft:flower_forest,10",
                              "minecraft:taiga,-10",
                              "minecraft:old_growth_pine_taiga,-20",
                              "minecraft:old_growth_spruce_taiga,-20",
                              "minecraft:snowy_taiga,-40",
                              "minecraft:birch_forest,10",
                              "minecraft:old_growth_birch_forest,10",
                              "minecraft:dark_forest,40",
                              "minecraft:pale_garden,-70",
                              "minecraft:jungle,90",
                              "minecraft:sparse_jungle,70",
                              "minecraft:bamboo_jungle,80",
                              "minecraft:river,-20",
                              "minecraft:frozen_river,-80",
                              "minecraft:swamp,60",
                              "minecraft:mangrove_swamp,90",
                              "minecraft:beach,30",
                              "minecraft:snowy_beach,-30",
                              "minecraft:stony_shore,-20",
                              "minecraft:plains,0",
                              "minecraft:sunflower_plains,5",
                              "minecraft:snowy_plains,-40",
                              "minecraft:ice_spikes,-70",
                              "minecraft:desert,120",
                              "minecraft:savanna,70",
                              "minecraft:savanna_plateau,70",
                              "minecraft:windswept_savanna,65",
                              "minecraft:badlands,140",
                              "minecraft:wooded_badlands,130",
                              "minecraft:eroded_badlands,150",
                              "minecraft:deep_dark,-150",
                              "minecraft:dripstone_caves,-30",
                              "minecraft:lush_caves,5",
                              "minecraft:the_void,0",
                              "minecraft:nether_wastes,200",
                              "minecraft:soul_sand_valley,190",
                              "minecraft:crimson_forest,220",
                              "minecraft:warped_forest,80",
                              "minecraft:basalt_deltas,230",
                              "minecraft:the_end,-400",
                              "minecraft:small_end_islands,-400",
                              "minecraft:end_midlands,-400",
                              "minecraft:end_highlands,-400",
                              "minecraft:end_barrens,-400")));
        }
        BUILDER.pop();
        BUILDER.push("block_insulators");
        {
          INSULATORS_ENABLED =
              BUILDER
                  .comment(
                      "If true, enables temperature fluctuation based on the surrounding blocks")
                  .define("insulators_enabled", true);
          INSULATORS_VALUES =
              BUILDER
                  .comment("A Map of all the blocks that will provide a change in temperature")
                  .define(
                      "insulators",
                      new ArrayList<>(
                          Arrays.asList(
                              "[minecraft:torch,minecraft:wall_torch],15",
                              "minecraft:fire,20",
                              "minecraft:lantern,25",
                              "minecraft:campfire,30",
                              "minecraft:lava,60",
                              "minecraft:magma_block,30",
                              "#minecraft:candles,5",
                              "minecraft:soul_campfire,-30",
                              "[minecraft:soul_torch,minecraft:soul_wall_torch],-15",
                              "minecraft:soul_lantern,-25")));
          INSULATORS_RANGE =
              BUILDER
                  .comment("The range that the game will check for insulating blocks")
                  .defineInRange("insulators_range", 5, 1, Integer.MAX_VALUE);
          INSULATORS_DISTINCT =
              BUILDER
                  .comment("If true, the game will only count each block once in the check.")
                  .comment(
                      "If false, the game will sum the values of all of the insulators inside the range.")
                  .comment(
                      "Note that enabling this may result in a small performance decrease and can also cause some HUGE temperature spikes (for example if you're standing next to a lava pool, each one of those lava blocks will be counted individually.")
                  .define("insulators_distinct", true);
        }
        BUILDER.pop();
        BUILDER.push("fluids");
        {
          FLUID_TEMPERATURES_ENABLED =
              BUILDER
                  .comment(
                      "If true, enables temperature fluctuation based on the fluid you are standing in")
                  .define("fluid_temperatures_enabled", true);
          FLUID_TEMPERATURES =
              BUILDER
                  .comment(
                      "A Map of all the blocks that will change your temperature by standing in them")
                  .define(
                      "fluidTemperature",
                      new ArrayList<>(
                          Arrays.asList(
                              "minecraft:water,-30",
                              "minecraft:powder_snow,-100",
                              "minecraft:lava,100")));
        }
        BUILDER.pop();
        BUILDER.push("block_temperatures");
        {
          BLOCK_TEMPERATURES_ENABLED =
              BUILDER
                  .comment(
                      "If true, enables temperature fluctuation based on the block you're standing on.")
                  .define("block_temperatures_enabled", true);
          BLOCK_TEMPERATURES_NEED_BOOTS =
              BUILDER
                  .comment(
                      "If true, block temperatures will only affect you when you are not wearing boots.")
                  .define("block_temperatures_need_boots", true);
          BLOCK_TEMPERATURES =
              BUILDER
                  .comment(
                      "A Map of all the blocks that will change your temperature by standing on top of them while not wearing boots")
                  .define(
                      "walkingOnTop",
                      new ArrayList<>(
                          Arrays.asList(
                              "minecraft:blue_ice,-60",
                              "minecraft:packed_ice,-40",
                              "minecraft:ice,-20",
                              "minecraft:snow_block,-15")));
        }
        BUILDER.pop();
        BUILDER.push("weather");
        {
          WEATHER_TEMPERATURE_ENABLED =
              BUILDER
                  .comment("If true, enables temperature fluctuation based on biome.")
                  .define("weather_temperature_enabled", true);

          RAIN_TEMPERATURE =
              BUILDER
                  .comment("The amount that your temperature will change while exposed to rain")
                  .define("rain_temperature", -30);

          SNOW_TEMPERATURE =
              BUILDER
                  .comment("The amount that your temperature will change while exposed to snow")
                  .define("snow_temperature", -60);
        }
        BUILDER.pop();
        BUILDER.push("time");
        {
          TIME_TEMPERATURE_ENABLED =
              BUILDER
                  .comment("If true, enables temperature fluctuation based on time of day.")
                  .define("time_temperature_enabled", true);

          DAY_TEMPERATURE =
              BUILDER
                  .comment("The temperature that is applied when it is daytime")
                  .define("day_temperature", 10);

          NIGHT_TEMPERATURE =
              BUILDER
                  .comment("The temperature that is applied when it is nighttime")
                  .define("night_temperature", -10);
        }
        BUILDER.pop();
        BUILDER.push("fire");
        {
          FIRE_TEMPERATURES_ENABLED =
              BUILDER
                  .comment(
                      "If true, enables temperature fluctuation based on whether the player is on fire.")
                  .define("fire_temperatures_enabled", true);

          FIRE_TEMPERATURE =
              BUILDER
                  .comment("The temperature of being on fire")
                  .defineInRange("fire_temperature", 50, 1, Integer.MAX_VALUE);
        }
        BUILDER.pop();
        BUILDER.push("altitude");
        {
          ALTITUDE_TEMPERATURES_ENABLED =
              BUILDER
                  .comment("If true, enables temperature fluctuation based on player altitude.")
                  .define("altitude_temperatures_enabled", true);

          UPPER_ALTITUDE =
              BUILDER
                  .comment("The upwards y-value when temperature will start changing")
                  .define("upper_altitude", 80);

          UPPER_MULTIPLIER =
              BUILDER
                  .comment("The amount of blocks that result in a change of 1 temperature")
                  .comment(
                      "For example, a value of 5 means that every 5 blocks traveled after the start will change your temperature by 1")
                  .defineInRange("upper_multiplier", 5, 1, Double.MAX_VALUE);

          LOWER_ALTITUDE =
              BUILDER
                  .comment("The downwards y-value when temperature will start changing")
                  .define("lower_altitude", 40);

          LOWER_MULTIPLIER =
              BUILDER
                  .comment("The amount of blocks that result in a change of 1 temperature")
                  .comment(
                      "For example, a value of 5 means that every 5 blocks traveled after the start will change your temperature by 1")
                  .defineInRange("lower_multiplier", 5, 1, Double.MAX_VALUE);
        }
        BUILDER.pop();
        BUILDER.push("armor");
        {
          ARMOR_INSULATORS_ENABLED =
              BUILDER
                  .comment(
                      "If true, gives the player an insulation value based on the armor they're wearing.")
                  .comment("Insulation will keep your temperatuer closer to zero no matter what.")
                  .define("armor_insulations_enabled", true);
          ARMOR_INSULATORS =
              BUILDER
                  .comment("A Map of all the armor pieces that give insulation while wearing them")
                  .define(
                      "armor",
                      new ArrayList<>(
                          Arrays.asList(
                              "minecraft:leather_boots,2,10",
                              "minecraft:leather_leggings,6,30",
                              "minecraft:leather_chestplate,8,40",
                              "minecraft:leather_helmet,4,20",
                              "minecraft:chainmail_boots,30,0",
                              "minecraft:chainmail_leggings,50,0",
                              "minecraft:chainmail_chestplate,60,0",
                              "minecraft:chainmail_helmet,40,0",
                              "minecraft:iron_boots,-10,-10",
                              "minecraft:iron_leggings,-30,-30",
                              "minecraft:iron_chestplate,-40,-40",
                              "minecraft:iron_helmet,-20,-20",
                              "minecraft:gold_boots,10,10",
                              "minecraft:gold_leggings,30,30",
                              "minecraft:gold_chestplate,40,40",
                              "minecraft:gold_helmet,20,20",
                              "minecraft:diamond_boots,-20,-20",
                              "minecraft:diamond_leggings,-40,-40",
                              "minecraft:diamond_chestplate,-50,-50",
                              "minecraft:diamond_helmet,-30,-30",
                              "minecraft:netherite_boots,40,-40",
                              "minecraft:netherite_leggings,60,-60",
                              "minecraft:netherite_chestplate,70,-70",
                              "minecraft:netherite_helmet,50,-50")));
        }
        BUILDER.pop();
      }
      BUILDER.pop();
      BUILDER.push("environment_nerf");
      {
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
        CROP_GROWTH_DIFFICULTY_MULTIPLIERS =
            BUILDER
                .comment("The modifier for the growth time of crops depending on difficulty.")
                .define("cropGrowthDifficultyMultiplier", DEFAULT_DIFFICULTY_MULTIPLIER);
        CROP_GROWTH_BIOME_MULTIPLIERS = BUILDER.comment("The modifier for the growth time of crops depending on biome.")
            .define("cropGrowthBiomeMultiplier", new ArrayList<>(List.of("minecraft:wheat,[minecraft:plains,minecraft:forest]")));
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
        BUILDER.push("food_history");
        {
          FOOD_HISTORY_ENABLED =
              BUILDER
                  .comment(
                      "If true, enables progresive decay from eating the same food repeatedly.")
                  .define("food_history_enabled", true);

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
