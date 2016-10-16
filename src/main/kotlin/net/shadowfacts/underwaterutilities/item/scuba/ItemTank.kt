package net.shadowfacts.underwaterutilities.item.scuba

import net.minecraft.client.model.ModelBiped
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemArmor
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.shadowfacts.shadowmc.item.ItemModelProvider
import net.shadowfacts.shadowmc.oxygen.OxygenCaps
import net.shadowfacts.shadowmc.oxygen.impl.OxygenTankProvider
import net.shadowfacts.shadowmc.util.MeshWrapper
import net.shadowfacts.underwaterutilities.MOD_ID
import net.shadowfacts.underwaterutilities.UUMaterials
import net.shadowfacts.underwaterutilities.model.ModelScubaTank

/**
 * @author shadowfacts
 */
class ItemTank : ItemArmor(UUMaterials.SCUBA, 0, EntityEquipmentSlot.CHEST), ItemModelProvider {

	init {
		setRegistryName("scubaTank")
		unlocalizedName = registryName.toString()
	}

	override fun getArmorModel(entityLiving: EntityLivingBase, stack: ItemStack, armorSlot: EntityEquipmentSlot, _default: ModelBiped): ModelBiped {
		return if (armorSlot == EntityEquipmentSlot.CHEST) ModelScubaTank else _default
	}

	override fun initItemModel() {
		ModelLoader.setCustomMeshDefinition(this, MeshWrapper.of { stack ->
			val handler = stack.getCapability(OxygenCaps.HANDLER, EnumFacing.NORTH)
			val level = (handler.stored / handler.capacity * 10).toInt()
			ModelResourceLocation("$MOD_ID:scubaTank", "level=" + level)
		})
	}

	override fun addInformation(stack: ItemStack, player: EntityPlayer, tooltip: MutableList<String>, advanced: Boolean) {
		val handler = stack.getCapability(OxygenCaps.HANDLER, EnumFacing.NORTH)
		tooltip.add(String.format("Oxygen: %.1f / %.0f", handler.stored, handler.capacity))
	}

	override fun initCapabilities(stack: ItemStack?, nbt: NBTTagCompound?): ICapabilityProvider? {
		return OxygenTankProvider(12000f, 20f)
	}

}
