package net.shadowfacts.underwaterutilities.item

import net.shadowfacts.shadowmc.item.ItemBase
import net.shadowfacts.shadowmc.item.ModItems
import net.shadowfacts.underwaterutilities.item.scuba.ItemBreather
import net.shadowfacts.underwaterutilities.item.scuba.ItemTank

/**
 * @author shadowfacts
 */
object UUItems : ModItems() {

	var goggles: ItemGoggles = ItemGoggles()
	var goggleLens: ItemBase = ItemBase("goggleLens")
	var breather: ItemBreather = ItemBreather()
	var tank: ItemTank = ItemTank()
	var blade: ItemBase = ItemBase("blade")
	var fan: ItemBase = ItemBase("fan")
	var snorkel: ItemSnorkel = ItemSnorkel()

	override fun init() {
		register(goggles)
		register(goggleLens)
		register(breather)
		register(tank)
		register(blade)
		register(fan)
		register(snorkel)
	}

}
