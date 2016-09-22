package net.shadowfacts.underwaterutilities;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

/**
 * @author shadowfacts
 */
public class UUMaterials {

	public static final ItemArmor.ArmorMaterial GOGGLES = EnumHelper.addArmorMaterial("GOGGLES", UnderwaterUtilities.modId + ":goggles", 2, new int[]{0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0);

	public static final ItemArmor.ArmorMaterial SCUBA = EnumHelper.addArmorMaterial("SCUBA", UnderwaterUtilities.modId + ":scuba", 2, new int[]{0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0);
}
