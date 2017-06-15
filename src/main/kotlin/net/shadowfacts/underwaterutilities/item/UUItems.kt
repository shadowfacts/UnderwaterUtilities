package net.shadowfacts.underwaterutilities.item

import net.minecraft.creativetab.CreativeTabs
import net.shadowfacts.shadowmc.item.ItemBase
import net.shadowfacts.shadowmc.item.ModItems
import net.shadowfacts.underwaterutilities.item.scuba.ItemBreather
import net.shadowfacts.underwaterutilities.item.scuba.ItemTank

/**
 * @author shadowfacts
 */
object UUItems : ModItems() {

	val goggles = ItemGoggles()
	val goggleLens = ItemBase("goggle_lens").apply { creativeTab = CreativeTabs.MISC }
	val breather = ItemBreather()
	val tank = ItemTank()
	val blade = ItemBase("blade").apply { creativeTab = CreativeTabs.MISC }
	val fan = ItemBase("fan").apply { creativeTab = CreativeTabs.MISC }
	val snorkelTube = ItemBase("snorkel_tube").apply { creativeTab = CreativeTabs.MISC }
	val snorkel = ItemSnorkel()

	override fun init() {
		register(goggles)
		register(goggleLens)
		register(breather)
		register(tank)
		register(blade)
		register(fan)
		register(snorkelTube)
		register(snorkel)
	}

}
