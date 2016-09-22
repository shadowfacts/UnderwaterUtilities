package net.shadowfacts.underwaterutilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.shadowfacts.underwaterutilities.api.item.Goggles;

/**
 * @author shadowfacts
 */
public class UUCapabilities {

//	ItemStack
	@CapabilityInject(Goggles.class)
	public static Capability<Goggles> GOGGLES;

}
