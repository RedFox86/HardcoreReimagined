package net.redfox.hardcorereimagined;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.redfox.hardcorereimagined.client.TemperatureHudOverlay;
import net.redfox.hardcorereimagined.config.FormattedConfigValues;
import net.redfox.hardcorereimagined.config.ModClientConfigs;
import net.redfox.hardcorereimagined.config.ModCommonConfigs;
import net.redfox.hardcorereimagined.effect.ModEffects;
import net.redfox.hardcorereimagined.event.AppleSkinEvents;
import net.redfox.hardcorereimagined.food.FoodNerf;
import net.redfox.hardcorereimagined.networking.ModPackets;
import org.slf4j.Logger;

@Mod(HardcoreReimagined.MOD_ID)
public class HardcoreReimagined {
  public static final String MOD_ID = "hardcorereimagined";
  public static final Logger LOGGER = LogUtils.getLogger();

  public HardcoreReimagined(FMLJavaModLoadingContext context) {
    IEventBus modEventBus = context.getModEventBus();

    context.registerConfig(
        ModConfig.Type.COMMON, ModCommonConfigs.SPEC, HardcoreReimagined.MOD_ID + "/common.toml");
    context.registerConfig(
        ModConfig.Type.CLIENT, ModClientConfigs.SPEC, HardcoreReimagined.MOD_ID + "/client.toml");

    ModEffects.register(modEventBus);

    modEventBus.addListener(this::commonSetup);
    MinecraftForge.EVENT_BUS.register(this);

    if (ModList.get().isLoaded("appleskin")) {
      MinecraftForge.EVENT_BUS.addListener(AppleSkinEvents::onAppleSkinFoodEvent);
    }
  }

  private void commonSetup(final FMLCommonSetupEvent event) {
    FormattedConfigValues.populateConfigValues();
    ModPackets.register();
    TemperatureHudOverlay.initialize();
    FoodNerf.nerfFoods();

    if (ModClientConfigs.FOOD_TYPE_TOOLTIP_DISPLAY.get())
      MinecraftForge.EVENT_BUS.addListener(FoodNerf::addTooltip);
  }
}
