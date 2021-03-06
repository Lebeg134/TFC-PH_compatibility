package hu.lebeg134.tfc_ph_compat.util.handlers;

import hu.lebeg134.tfc_ph_compat.TFC_PH_Compat;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static hu.lebeg134.tfc_ph_compat.Reference.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class TPLootTableHandler {
    public static ResourceLocation ANIMALS_DUCK;
    public static ResourceLocation ANIMALS_TURKEY;

    public static void Init()
    {
        ANIMALS_DUCK = register("animals/duck");
        ANIMALS_TURKEY = register("animals/turkey");
    }
    @SubscribeEvent
    public static void onLootTablesLoaded(LootTableLoadEvent event)
    {
        if(TFC_PH_Compat.config.enablePHMeats) {
            if (event.getName().toString().equals("tfc:animals/duck")) {
                event.setTable(event.getLootTableManager().getLootTableFromLocation(ANIMALS_DUCK));
            }
            if (event.getName().toString().equals("tfc:animals/turkey")) {
                event.setTable(event.getLootTableManager().getLootTableFromLocation(ANIMALS_TURKEY));
            }
        }
    }
    private static ResourceLocation register(String id)
    {
        return LootTableList.register(new ResourceLocation(MODID, id));
    }
}
