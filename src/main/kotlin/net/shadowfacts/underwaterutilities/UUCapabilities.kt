package net.shadowfacts.underwaterutilities

import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.shadowfacts.underwaterutilities.api.item.Goggles

/**
 * @author shadowfacts
 */
object UUCapabilities {

	//	ItemStack
	@CapabilityInject(Goggles::class)
	var GOGGLES: Capability<Goggles>? = null

}
