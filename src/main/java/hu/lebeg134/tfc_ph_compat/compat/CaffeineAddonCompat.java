package hu.lebeg134.tfc_ph_compat.compat;

import com.eerussianguy.firmalife.recipe.DryingRecipe;
import com.pam.harvestcraft.blocks.CropRegistry;
import com.pam.harvestcraft.item.ItemRegistry;
import hu.lebeg134.tfc_ph_compat.types.RecipeModifier;
import hu.lebeg134.tfc_ph_compat.util.handlers.OreDictHandler;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.util.calendar.ICalendar;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;
import pieman.caffeineaddon.recipes.DryingMatRecipe;

public class CaffeineAddonCompat {
    public static void InitCaffeine(){
        //OreDict addition
        OreDictionary.registerOre("cropCoffee", new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("ca:coffee_ground"))));
        OreDictionary.registerOre("cropTea", new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("ca:tea_leaves_dried"))));
        //OreDict remove
        OreDictHandler.removeAll(CropRegistry.getFood(CropRegistry.COFFEE));
        OreDictHandler.removeAll(CropRegistry.getFood(CropRegistry.TEALEAF));

    }
    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        IForgeRegistryModifiable<IRecipe> recipeRegistry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();

        RecipeModifier.dummyOutRecipe(recipeRegistry, "harvestcraft:coffeeitem");
        //RecipeModifier.dummyOutRecipe(recipeRegistry, "harvestcraft:teaitem");
        RecipeModifier.dummyOutRecipe(recipeRegistry, "harvestcraft:driedsoupitem");
        RecipeModifier.dummyOutRecipe(recipeRegistry, "harvestcraft:raisinsitem");
    }
    @SubscribeEvent
    public static void onRegisterDryingMatRecipeEvent(RegistryEvent.Register<DryingMatRecipe> event)
    {
        int hour = ICalendar.TICKS_IN_HOUR;
        IForgeRegistry<DryingMatRecipe> r = event.getRegistry();
        r.registerAll(
                new DryingMatRecipe(IIngredient.of(ItemRegistry.stockItem), new ItemStack(ItemRegistry.driedsoupItem),24000).setRegistryName("dried_soup_drying1"),
                new DryingMatRecipe(IIngredient.of("cropGrape"),new ItemStack(ItemRegistry.raisinsItem), 24*hour).setRegistryName("raisins_drying2")
        );
    }

}



