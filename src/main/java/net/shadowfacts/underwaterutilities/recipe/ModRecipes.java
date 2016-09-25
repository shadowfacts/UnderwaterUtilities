package net.shadowfacts.underwaterutilities.recipe;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

import static net.shadowfacts.underwaterutilities.UnderwaterUtilities.*;

/**
 * @author shadowfacts
 */
public class ModRecipes {

	public static void init() {
		GameRegistry.addRecipe(new ShapedOreRecipe(items.goggleLens, "SIS", "IGI", "SIS", 'S', "slimeball", 'I', "nuggetIron", 'G', "paneGlassColorless"));
		GameRegistry.addRecipe(new ShapedOreRecipe(items.goggles, "LLL", "O O", 'L', "leather", 'O', items.goggleLens));

		GameRegistry.addRecipe(new ShapedOreRecipe(items.blade, "Ii", 'I', "ingotIron", 'i', "nuggetIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(items.fan, " B ", "BIB", " B ", 'B', items.blade, 'I', "ingotIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(blocks.collector, "III", "FRI", "III", 'I', "ingotIron", 'F', items.fan, 'R', "dustRedstone"));

		GameRegistry.addRecipe(new ShapedOreRecipe(blocks.tank, " i ", "I I", "I I", 'i', "nuggetIron", 'I', "ingotIron"));

		GameRegistry.addRecipe(new ShapedOreRecipe(items.snorkel, " ls", "lsl", " l ", 'l', "leather", 's', "sugarcane"));

	}

}
