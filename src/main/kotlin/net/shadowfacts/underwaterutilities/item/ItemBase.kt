package net.shadowfacts.underwaterutilities.item

import net.minecraft.creativetab.CreativeTabs
import net.shadowfacts.shadowmc.item.ItemBase

/**
 * @author shadowfacts
 */
class ItemBase(name: String) : ItemBase(name) {

	init {
		unlocalizedName = registryName.toString()
		creativeTab = CreativeTabs.MISC
	}

}