package net.shadowfacts.underwaterutilities.item.scuba

import net.minecraft.client.model.ModelBiped
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.ItemArmor
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.shadowfacts.shadowmc.ShadowMC
import net.shadowfacts.shadowmc.item.ItemModelProvider
import net.shadowfacts.shadowmc.oxygen.OxygenCaps
import net.shadowfacts.shadowmc.oxygen.impl.OxygenHandlerImpl
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
		setRegistryName("scuba_tank")
		unlocalizedName = registryName.toString()
		creativeTab = CreativeTabs.MISC
		hasSubtypes = true
	}

	override fun getSubItems(tab: CreativeTabs, items: NonNullList<ItemStack>) {
		items.add(ItemStack(this))
		val stack2 = ItemStack(this)
		(stack2.getCapability(OxygenCaps.HANDLER, null) as OxygenHandlerImpl).stored = 12000f
		items.add(stack2)
	}

	override fun getArmorModel(entityLiving: EntityLivingBase, stack: ItemStack, armorSlot: EntityEquipmentSlot, _default: ModelBiped): ModelBiped {
		return if (armorSlot == EntityEquipmentSlot.CHEST) ModelScubaTank else _default
	}

	override fun initItemModel() {
		ShadowMC.proxy.registerItemModel(this, 0, registryName)
	}

	override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
		val handler = stack.getCapability(OxygenCaps.HANDLER, EnumFacing.NORTH)!!
		tooltip.add(String.format("Oxygen: %.1f / %.0f", handler.stored, handler.capacity))
	}

	override fun initCapabilities(stack: ItemStack?, nbt: NBTTagCompound?): ICapabilityProvider? {
		return OxygenTankProvider(12000f, 20f)
	}

}
