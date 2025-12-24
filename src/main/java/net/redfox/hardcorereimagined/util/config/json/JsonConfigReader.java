package net.redfox.hardcorereimagined.util.config.json;

import com.google.gson.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import net.minecraftforge.fml.loading.FMLPaths;
import net.redfox.hardcorereimagined.HardcoreReimagined;

public class JsonConfigReader {
  public static final Runnable BIOMES =
      () ->
          writeJsonFile(
              getFilePathAsString("temperature/biomes"),
              createDefaultJsonObject(
                  createJsonArray(
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:ocean")
                          .add("temperature", -20)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:deep_ocean")
                          .add("temperature", -30)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:warm_ocean")
                          .add("temperature", 20)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:lukewarm_ocean")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:deep_lukewarm_ocean")
                          .add("temperature", -5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:cold_ocean")
                          .add("temperature", -40)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:deep_cold_ocean")
                          .add("temperature", -50)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:frozen_ocean")
                          .add("temperature", -60)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:deep_frozen_ocean")
                          .add("temperature", -70)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:mushroom_fields")
                          .add("temperature", 0)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:jagged_peaks")
                          .add("temperature", -80)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:frozen_peaks")
                          .add("temperature", -100)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:stony_peaks")
                          .add("temperature", -60)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:meadow")
                          .add("temperature", -10)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:cherry_grove")
                          .add("temperature", 10)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:grove")
                          .add("temperature", -50)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:windswept_hills")
                          .add("temperature", -30)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:windswept_gravelly_hills")
                          .add("temperature", -30)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:windswept_forest")
                          .add("temperature", -30)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:forest")
                          .add("temperature", 10)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:flower_forest")
                          .add("temperature", 10)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:taiga")
                          .add("temperature", -10)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:old_growth_pine_taiga")
                          .add("temperature", -20)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:old_growth_spruce_taiga")
                          .add("temperature", -20)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:snowy_taiga")
                          .add("temperature", -40)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:birch_forest")
                          .add("temperature", 10)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:old_growth_birch_forest")
                          .add("temperature", 10)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:dark_forest")
                          .add("temperature", 40)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:pale_garden")
                          .add("temperature", -70)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:jungle")
                          .add("temperature", 90)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:sparse_jungle")
                          .add("temperature", 70)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:bamboo_jungle")
                          .add("temperature", 80)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:river")
                          .add("temperature", -20)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:frozen_river")
                          .add("temperature", -80)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:swamp")
                          .add("temperature", 60)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:mangrove_swamp")
                          .add("temperature", 90)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:beach")
                          .add("temperature", 30)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:snowy_beach")
                          .add("temperature", -30)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:stony_shore")
                          .add("temperature", -20)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:plains")
                          .add("temperature", 0)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:sunflower_plains")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:snowy_plains")
                          .add("temperature", -40)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:ice_spikes")
                          .add("temperature", -70)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:desert")
                          .add("temperature", 120)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:savanna")
                          .add("temperature", 70)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:savanna_plateau")
                          .add("temperature", 70)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:windswept_savanna")
                          .add("temperature", 65)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:badlands")
                          .add("temperature", 140)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:wooded_badlands")
                          .add("temperature", 130)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:eroded_badlands")
                          .add("temperature", 150)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:deep_dark")
                          .add("temperature", -150)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:dripstone_caves")
                          .add("temperature", -30)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:lush_caves")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:the_void")
                          .add("temperature", 0)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:nether_wastes")
                          .add("temperature", 200)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:soul_sand_valley")
                          .add("temperature", 190)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:crimson_forest")
                          .add("temperature", 220)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:warped_forest")
                          .add("temperature", 80)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:basalt_deltas")
                          .add("temperature", 230)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:the_end")
                          .add("temperature", -400)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:small_end_islands")
                          .add("temperature", -400)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:end_midlands")
                          .add("temperature", -400)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:end_highlands")
                          .add("temperature", -400)
                          .build(),
                      new JsonObjectBuilder()
                          .add("biome", "minecraft:end_barrens")
                          .add("temperature", -400)
                          .build())));
  public static final Runnable INSULATORS =
      () ->
          writeJsonFile(
              getFilePathAsString("temperature/insulators"),
              createDefaultJsonObject(
                  createJsonArray(
                      new JsonObjectBuilder()
                          .add("block", "minecraft:torch")
                          .add("temperature", 15)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:wall_torch")
                          .add("temperature", 15)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:fire")
                          .add("temperature", 20)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:lantern")
                          .add("temperature", 25)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:campfire")
                          .add("temperature", 30)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:lava")
                          .add("temperature", 60)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:magma_block")
                          .add("temperature", 30)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:candle")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:white_candle")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:orange_candle")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:magenta_candle")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:light_blue_candle")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:yellow_candle")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:lime_candle")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:pink_candle")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:gray_candle")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:light_gray_candle")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:cyan_candle")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:purple_candle")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:blue_candle")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:brown_candle")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:green_candle")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:red_candle")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:black_candle")
                          .add("temperature", 5)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:soul_campfire")
                          .add("temperature", -30)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:soul_torch")
                          .add("temperature", -15)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:soul_wall_torch")
                          .add("temperature", -15)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:soul_lantern")
                          .add("temperature", -25)
                          .build())));
  public static final Runnable INSIDE_BLOCKS =
      () ->
          writeJsonFile(
              getFilePathAsString("temperature/inside_blocks"),
              createDefaultJsonObject(
                  createJsonArray(
                      new JsonObjectBuilder()
                          .add("block", "minecraft:water")
                          .add("temperature", -30)
                          .add("boots", false)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:powder_snow")
                          .add("temperature", -100)
                          .add("boots", false)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:lava")
                          .add("temperature", 100)
                          .add("boots", false)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:snow")
                          .add("temperature", -20)
                          .add("boots", true)
                          .build())));
  public static final Runnable ON_TOP_BLOCKS =
      () ->
          writeJsonFile(
              getFilePathAsString("temperature/on_top_blocks"),
              createDefaultJsonObject(
                  createJsonArray(
                      new JsonObjectBuilder()
                          .add("block", "minecraft:blue_ice")
                          .add("temperature", -60)
                          .add("boots", true)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:packed_ice")
                          .add("boots", true)
                          .add("temperature", -40)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:ice")
                          .add("boots", true)
                          .add("temperature", -20)
                          .build(),
                      new JsonObjectBuilder()
                          .add("block", "minecraft:snow_block")
                          .add("boots", true)
                          .add("temperature", -25)
                          .build())));
  public static final Runnable ARMOR_INSULATORS =
      () ->
          writeJsonFile(
              getFilePathAsString("temperature/armor_insulators"),
              createDefaultJsonObject(
                  createJsonArray(
                      new JsonObjectBuilder()
                          .add("item", "minecraft:leather_boots")
                          .add("heat_resistance", 2)
                          .add("cold_resistance", 10)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:leather_leggings")
                          .add("heat_resistance", 6)
                          .add("cold_resistance", 30)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:leather_chestplate")
                          .add("heat_resistance", 8)
                          .add("cold_resistance", 40)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:leather_helmet")
                          .add("heat_resistance", 4)
                          .add("cold_resistance", 20)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:chainmail_boots")
                          .add("heat_resistance", 30)
                          .add("cold_resistance", 0)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:chainmail_leggings")
                          .add("heat_resistance", 50)
                          .add("cold_resistance", 0)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:chainmail_chestplate")
                          .add("heat_resistance", 60)
                          .add("cold_resistance", 0)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:chainmail_helmet")
                          .add("heat_resistance", 40)
                          .add("cold_resistance", 0)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:iron_boots")
                          .add("heat_resistance", -10)
                          .add("cold_resistance", -10)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:iron_leggings")
                          .add("heat_resistance", -30)
                          .add("cold_resistance", -30)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:iron_chestplate")
                          .add("heat_resistance", -40)
                          .add("cold_resistance", -40)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:iron_helmet")
                          .add("heat_resistance", -20)
                          .add("cold_resistance", -20)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:gold_boots")
                          .add("heat_resistance", 10)
                          .add("cold_resistance", 10)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:gold_leggings")
                          .add("heat_resistance", 30)
                          .add("cold_resistance", 30)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:gold_chestplate")
                          .add("heat_resistance", 40)
                          .add("cold_resistance", 40)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:gold_helmet")
                          .add("heat_resistance", 20)
                          .add("cold_resistance", 20)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:diamond_boots")
                          .add("heat_resistance", -20)
                          .add("cold_resistance", -20)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:diamond_leggings")
                          .add("heat_resistance", -40)
                          .add("cold_resistance", -40)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:diamond_chestplate")
                          .add("heat_resistance", -50)
                          .add("cold_resistance", -50)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:diamond_helmet")
                          .add("heat_resistance", -30)
                          .add("cold_resistance", -30)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:netherite_boots")
                          .add("heat_resistance", 40)
                          .add("cold_resistance", -40)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:netherite_leggings")
                          .add("heat_resistance", 60)
                          .add("cold_resistance", -60)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:netherite_chestplate")
                          .add("heat_resistance", 70)
                          .add("cold_resistance", -70)
                          .build(),
                      new JsonObjectBuilder()
                          .add("item", "minecraft:netherite_helmet")
                          .add("heat_resistance", 50)
                          .add("cold_resistance", -50)
                          .build())));

  public static JsonObject getOrCreateJsonFile(String fileName, Runnable create) {
    if (!new File(fileName).exists()) create.run();
    return readJsonFile(fileName);
  }

  private static void writeJsonFile(String fileName, JsonObject jsonObject) {
    File file = new File(fileName);
    try {
      if (file.getParentFile() != null) {
        file.getParentFile().mkdirs();
      }

      file.createNewFile();

      try (FileWriter writer = new FileWriter(file)) {
        new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject, writer);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static JsonObject readJsonFile(String fileName) {
    File file = new File(getFilePathAsString(fileName));
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(file)) {
      return gson.fromJson(reader, JsonObject.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static JsonObject createDefaultJsonObject(JsonElement jsonElement) {
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("values", jsonElement);
    return jsonObject;
  }

  private static JsonArray createJsonArray(JsonObject... jsonObjects) {
    JsonArray jsonArray = new JsonArray();
    for (JsonObject jsonObject : jsonObjects) {
      jsonArray.add(jsonObject);
    }
    return jsonArray;
  }

  private static String getFilePathAsString(String filePath) {
    return FMLPaths.CONFIGDIR
        .get()
        .resolve(HardcoreReimagined.MOD_ID + "/" + filePath + ".json")
        .toString();
  }

  public static class JsonObjectBuilder {
    private final List<String> keys;
    private final List<Object> values;

    public JsonObjectBuilder() {
      this.keys = new ArrayList<>();
      this.values = new ArrayList<>();
    }

    public JsonObjectBuilder add(String key, Object value) {
      this.keys.add(key);
      this.values.add(value);
      return this;
    }

    public JsonObject build() {
      JsonObject jsonObject = new JsonObject();
      for (int i = 0; i < this.keys.size(); i++) {
        Object value = this.values.get(i);
        if (value instanceof String s) {
          jsonObject.addProperty(this.keys.get(i), s);
        } else if (value instanceof Number n) {
          jsonObject.addProperty(this.keys.get(i), n);
        } else if (value instanceof Boolean b) {
          jsonObject.addProperty(this.keys.get(i), b);
        }
      }

      return jsonObject;
    }
  }
}
