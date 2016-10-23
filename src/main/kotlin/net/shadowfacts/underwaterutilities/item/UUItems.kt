package net.shadowfacts.underwaterutilities.item

import net.shadowfacts.shadowmc.item.ModItems
import net.shadowfacts.underwaterutilities.item.scuba.ItemBreather
import net.shadowfacts.underwaterutilities.item.scuba.ItemTank

/**
 * @author shadowfacts
 */
object UUItems : ModItems() {

	val goggles = ItemGoggles()
	val goggleLens = ItemBase("goggleLens")
	val breather = ItemBreather()
	val tank = ItemTank()
	val blade = ItemBase("blade")
	val fan = ItemBase("fan")
	val snorkelTube = ItemBase("snorkelTube")
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
