package net.redfox.hardcorereimagined.util.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonHelper {
  private static final Gson GSON = new Gson();
  private static final Gson DEFAULT_GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
  public static JsonConfigValue getOrCreateJsonFile(String path) {
    DEFAULT_GSON.toJson(new JsonConfigValue("blegg"));
    Path filePath = FMLPaths.CONFIGDIR.get().resolve(path);
    if (!Files.exists(filePath)) {
      try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
        GSON.toJson(DEFAULT_GSON, writer);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    String file = "";
    try {
      file = Files.readString(filePath);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return GSON.fromJson(file, JsonConfigValue.class);
  }
}