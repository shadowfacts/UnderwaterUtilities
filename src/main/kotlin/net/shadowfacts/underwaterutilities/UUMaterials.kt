package net.shadowfacts.underwaterutilities

import net.minecraft.init.SoundEvents
import net.minecraft.item.ItemArmor
import net.minecraftforge.common.util.EnumHelper

/**
 * @author shadowfacts
 */
object UUMaterials {

	val GOGGLES: ItemArmor.ArmorMaterial = EnumHelper.addArmorMaterial("GOGGLES", "$MOD_ID:goggles", 2, intArrayOf(0, 0, 0, 0), 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0f)!!
	val SCUBA: ItemArmor.ArmorMaterial = EnumHelper.addArmorMaterial("SCUBA", "$MOD_ID:scuba", 2, intArrayOf(0, 0, 0, 0), 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0f)!!

}
