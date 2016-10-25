package net.shadowfacts.underwaterutilities.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.shadowfacts.underwaterutilities.UUCapabilities;

/**
 * @author shadowfacts
 */
public class ASMHooks {

	public static boolean isPushedByWater(EntityPlayer player) {
		if (player.capabilities.isFlying) {
			return false;
		} else {
			ItemStack boots = player.inventory.armorItemInSlot(0);
			return boots == null || !boots.hasCapability(UUCapabilities.INSTANCE.getSWIMMING_AID(), null);
		}
	}

}
