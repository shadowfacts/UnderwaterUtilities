package net.shadowfacts.underwaterutilities.item.scuba

import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemArmor
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.shadowfacts.shadowmc.item.ItemModelProvider
import net.shadowfacts.shadowmc.oxygen.OxygenCaps
import net.shadowfacts.underwaterutilities.MOD_ID
import net.shadowfacts.underwaterutilities.UUMaterials
import net.shadowfacts.underwaterutilities.item.ItemGoggles

/**
 * @author shadowfacts
 */
class ItemBreather : ItemArmor(UUMaterials.SCUBA, 0, EntityEquipmentSlot.HEAD), ItemModelProvider {

	init {
		setRegistryName("scubaBreather")
		unlocalizedName = registryName.toString()
	}

	override fun initItemModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, ModelResourceLocation("$MOD_ID:scubaBreather", "inventory"))
	}

	override fun onArmorTick(world: World, player: EntityPlayer, stack: ItemStack?) {
		if (!player.capabilities.isCreativeMode && player.isInWater && player.air < 300) {
			val chestpiece = player.inventory.armorItemInSlot(2)
			if (chestpiece != null && chestpiece.hasCapability(OxygenCaps.PROVIDER, EnumFacing.NORTH)) {
				val provider = chestpiece.getCapability(OxygenCaps.PROVIDER, EnumFacing.NORTH)
				if (provider.stored > 0) {
					val amount = provider.extract((300 - player.air).toFloat(), false)
					player.air = player.air + amount.toInt()
				}
			}
		}
	}

	override fun initCapabilities(stack: ItemStack?, nbt: NBTTagCompound?): ICapabilityProvider? {
		return ItemGoggles.GogglesCapProvider()
	}

}
