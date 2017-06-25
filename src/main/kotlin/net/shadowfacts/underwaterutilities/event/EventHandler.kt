package net.shadowfacts.underwaterutilities.event

import net.minecraft.block.material.Material
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.player.EntityPlayer
import net.minecraftforge.event.entity.living.LivingEvent
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.shadowfacts.underwaterutilities.MOD_ID
import net.shadowfacts.underwaterutilities.UUCapabilities

/**
 * @author shadowfacts
 */
@Mod.EventBusSubscriber(modid = MOD_ID)
object EventHandler {

	@JvmStatic
	@SubscribeEvent
	fun onLivingUpdate(event: LivingEvent.LivingUpdateEvent) {
		if (event.entity is EntityPlayer) {
			val player = event.entity as EntityPlayer
			val helmet = player.inventory.armorInventory[3]
			if (!helmet.isEmpty && helmet.hasCapability(UUCapabilities.BREATHING_AID!!, null)) {
				val aid = helmet.getCapability(UUCapabilities.BREATHING_AID!!, null)!!
				if (aid.canBreathe(player)) {
					aid.breathe(player)
				}
			}
		}
	}

	@JvmStatic
	@SubscribeEvent
	fun onBreakSpeed(event: PlayerEvent.BreakSpeed) {
		val player = event.entityPlayer
		if (player.isInsideOfMaterial(Material.WATER) && !EnchantmentHelper.getAquaAffinityModifier(player)) {
			val helmet = player.inventory.armorInventory[3]
			if (!helmet.isEmpty && helmet.hasCapability(UUCapabilities.BREATHING_AID!!, null) &&
				helmet.getCapability(UUCapabilities.BREATHING_AID!!, null)!!.canBreathe(player)) {
				event.newSpeed = event.originalSpeed * 5
			}
		}
	}

}