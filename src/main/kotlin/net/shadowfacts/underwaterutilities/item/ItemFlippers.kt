package net.shadowfacts.underwaterutilities.item

import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemArmor
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.shadowfacts.shadowmc.item.ItemModelProvider
import net.shadowfacts.underwaterutilities.UUCapabilities
import net.shadowfacts.underwaterutilities.UUMaterials
import net.shadowfacts.underwaterutilities.api.item.SwimmingAid

/**
 * @author shadowfacts
 */
class ItemFlippers : ItemArmor(UUMaterials.FLIPPERS, 0, EntityEquipmentSlot.FEET), ItemModelProvider {

	init {
		setRegistryName("flippers")
		unlocalizedName = registryName.toString()
		creativeTab = CreativeTabs.MISC
	}

	override fun initItemModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, ModelResourceLocation(registryName, "inventory"))
	}

	override fun initCapabilities(stack: ItemStack?, nbt: NBTTagCompound?): ICapabilityProvider {
		return FlippersCapProvider()
	}

	class FlippersCapProvider : ICapabilityProvider {

		private val swimmingAid = SwimmingAid()

		override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
			return capability == UUCapabilities.SWIMMING_AID
		}

		override fun <T : Any?> getCapability(capability: Capability<T>?, facing: EnumFacing?): T? {
			if (capability == UUCapabilities.SWIMMING_AID) {
				return swimmingAid as T
			}
			return null
		}

	}
}