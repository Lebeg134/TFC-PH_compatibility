package hu.lebeg134.tfc_ph_compat.objects.items;

import com.google.common.collect.ImmutableList;
import hu.lebeg134.tfc_ph_compat.util.agriculture.TPCrop;
import hu.lebeg134.tfc_ph_compat.util.agriculture.TPFood;
import net.dries007.tfc.api.capability.food.CapabilityFood;
import net.dries007.tfc.api.capability.food.FoodHandler;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.items.ItemSeedsTFC;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import static hu.lebeg134.tfc_ph_compat.Reference.MODID;
import static net.dries007.tfc.objects.CreativeTabsTFC.CT_FOOD;

@Mod.EventBusSubscriber(modid = MODID)
@GameRegistry.ObjectHolder(MODID)
public final class ItemsTPC {
    private static ImmutableList<Item> allSimpleItems;
    public static ImmutableList<Item> getAllSimpleItems()
    {
        return allSimpleItems;
    }
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> r = event.getRegistry();
        ImmutableList.Builder<Item> simpleItems = ImmutableList.builder();
        for (TPCrop crop : TPCrop.values()) {
            simpleItems.add(register(r, "crop/seeds/" + crop.name().toLowerCase(), new ItemSeedsTFC(crop), CT_FOOD));
        }
        allSimpleItems = simpleItems.build();
        //registering PH plant data




        //registering PH food nutrition data
        for (TPFood food: TPFood.values())
        {
            CapabilityFood.CUSTOM_FOODS.put(IIngredient.of(food.getItem()),() -> new FoodHandler(null, food.getFoodData()));
            //CapabilityFood.CUSTOM_FOODS.put(IIngredient.of(new ItemStack(CropRegistry.getFood(food.name().toLowerCase()))), () -> new FoodHandler(null, food.getFoodData()));
        }


        //CapabilityFood.CUSTOM_FOODS.put(IIngredient.of(ItemFoodTFC.getByNameOrId(Food.POTATO.name())),() -> new FoodHeatHandler(null,new FoodData(),1f,200f));

    }
    private static <T extends Item> T register(IForgeRegistry<Item> r, String name, T item, CreativeTabs ct)
    {
        item.setRegistryName(MODID, name);
        item.setTranslationKey(MODID + "." + name.replace('/', '.'));
        item.setCreativeTab(ct);
        r.register(item);
        return item;
    }
}