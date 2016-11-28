package net.shadowfacts.underwaterutilities.item

import net.minecraft.block.material.Material
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemArmor
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.shadowfacts.shadowmc.item.ItemModelProvider
import net.shadowfacts.underwaterutilities.MOD_ID
import net.shadowfacts.underwaterutilities.UUCapabilities
import net.shadowfacts.underwaterutilities.UUMaterials
import net.shadowfacts.underwaterutilities.api.item.BreathingAid
import net.shadowfacts.underwaterutilities.api.item.Goggles

/**
 * @author shadowfacts
 */
class ItemSnorkel : ItemArmor(UUMaterials.GOGGLES, 0, EntityEquipmentSlot.HEAD), ItemModelProvider {

	init {
		setRegistryName("snorkel")
		unlocalizedName = registryName.toString()
		creativeTab = CreativeTabs.MISC
	}

	override fun initItemModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, ModelResourceLocation("$MOD_ID:snorkel", "inventory"))
	}

	override fun initCapabilities(stack: ItemStack, nbt: NBTTagCompound?): ICapabilityProvider? {
		return SnorkelCapProvider()
	}

	class SnorkelCapProvider() : ICapabilityProvider {

		private val goggles = Goggles()
		private val breathingAid = object : BreathingAid() {

			override fun canBreathe(player: EntityPlayer): Boolean {
				if (!player.capabilities.isCreativeMode) {
					val world = player.world
					val pos = player.position.up() // player.getPosition() is the foot position, .up() is the head position
					val state = world.getBlockState(pos)
					if (state.material === Material.WATER) {
						val upPos = pos.up()
						val upState = world.getBlockState(upPos)
						if (upState.material === Material.AIR || upState.material === Material.WATER && world.getBlockState(upPos.up()).material === Material.AIR) {
							return true
						}
					}
				}
				return false
			}

			override fun breathe(player: EntityPlayer) {
				player.air = 300
			}

		}

		override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
			return capability == UUCapabilities.GOGGLES || capability == UUCapabilities.BREATHING_AID
		}

		override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
			if (capability == UUCapabilities.GOGGLES) {
				return goggles as T
			} else if (capability == UUCapabilities.BREATHING_AID) {
				return breathingAid as T
			}
			return null
		}

	}

}
