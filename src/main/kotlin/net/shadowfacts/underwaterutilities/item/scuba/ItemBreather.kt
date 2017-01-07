package net.shadowfacts.underwaterutilities.item.scuba

import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemArmor
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.shadowfacts.shadowmc.item.ItemModelProvider
import net.shadowfacts.shadowmc.oxygen.OxygenCaps
import net.shadowfacts.underwaterutilities.MOD_ID
import net.shadowfacts.underwaterutilities.UUCapabilities
import net.shadowfacts.underwaterutilities.UUMaterials
import net.shadowfacts.underwaterutilities.api.item.BreathingAid
import net.shadowfacts.underwaterutilities.api.item.Goggles
import net.shadowfacts.underwaterutilities.item.ItemGoggles

/**
 * @author shadowfacts
 */
class ItemBreather : ItemArmor(UUMaterials.SCUBA, 0, EntityEquipmentSlot.HEAD), ItemModelProvider {

	init {
		setRegistryName("scuba_breather")
		unlocalizedName = registryName.toString()
		creativeTab = CreativeTabs.MISC
	}

	override fun initItemModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, ModelResourceLocation("$MOD_ID:scuba_breather", "inventory"))
	}

	override fun initCapabilities(stack: ItemStack, nbt: NBTTagCompound?): ICapabilityProvider? {
		return BreatherCapProvider()
	}

	class BreatherCapProvider() : ICapabilityProvider {

		private val goggles = Goggles()
		private val breathingAid = object : BreathingAid() {
			override fun canBreathe(player: EntityPlayer): Boolean {
				if (!player.capabilities.isCreativeMode && player.isInWater && player.air < 300) {
					val chestpiece = player.inventory.armorInventory[2]
					if (!chestpiece.isEmpty && chestpiece.hasCapability(OxygenCaps.PROVIDER, null)) {
						val provider = chestpiece.getCapability(OxygenCaps.PROVIDER, null)!!
						return provider.stored > 0
					}
				}
				return false
			}

			override fun breathe(player: EntityPlayer) {
				val chestpiece = player.inventory.armorItemInSlot(2)
				val provider = chestpiece.getCapability(OxygenCaps.PROVIDER, null)!!
				val amount = provider.extract((300 - player.air).toFloat(), false)
				player.air += amount.toInt()
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
