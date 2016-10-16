package net.shadowfacts.underwaterutilities.item

import net.shadowfacts.shadowmc.item.ItemBase

/**
 * @author shadowfacts
 */
class ItemBase(name: String) : ItemBase(name) {

	init {
		unlocalizedName = registryName.toString()
	}

}