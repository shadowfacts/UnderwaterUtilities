package net.shadowfacts.underwaterutilities.recipe

import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.ShapedOreRecipe

import net.shadowfacts.underwaterutilities.UnderwaterUtilities.blocks
import net.shadowfacts.underwaterutilities.UnderwaterUtilities.items

/**
 * @author shadowfacts
 */
object ModRecipes {

	fun init() {
		GameRegistry.addRecipe(ShapedOreRecipe(items.goggleLens, "SIS", "IGI", "SIS", 'S', "slimeball", 'I', "nuggetIron", 'G', "paneGlassColorless"))
		GameRegistry.addRecipe(ShapedOreRecipe(items.goggles, "LLL", "O O", 'L', "leather", 'O', items.goggleLens))

		GameRegistry.addRecipe(ShapedOreRecipe(items.blade, "Ii", 'I', "ingotIron", 'i', "nuggetIron"))
		GameRegistry.addRecipe(ShapedOreRecipe(items.fan, " B ", "BIB", " B ", 'B', items.blade, 'I', "ingotIron"))
		GameRegistry.addRecipe(ShapedOreRecipe(blocks.collector, "III", "FRI", "III", 'I', "ingotIron", 'F', items.fan, 'R', "dustRedstone"))

		GameRegistry.addRecipe(ShapedOreRecipe(blocks.tank, " i ", "I I", "I I", 'i', "nuggetIron", 'I', "ingotIron"))

		GameRegistry.addRecipe(ShapedOreRecipe(items.snorkel, " ls", "lsl", " l ", 'l', "leather", 's', "sugarcane"))

		GameRegistry.addRecipe(ShapedOreRecipe(items.breather, " g ", "lll", 'g', items.goggles, 'l', "leather"))
		GameRegistry.addRecipe(ShapedOreRecipe(items.tank, "iSi", "I I", 'i', "nuggetIron", 'I', "ingotIron", 'S', "slimeball"))

	}

}
