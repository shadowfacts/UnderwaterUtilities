package net.shadowfacts.underwaterutilities.block.tank

import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.shadowfacts.shadowmc.oxygen.OxygenCaps
import net.shadowfacts.shadowmc.oxygen.impl.OxygenHandlerImpl
import net.shadowfacts.shadowmc.oxygen.impl.OxygenTankProvider

/**
 * @author shadowfacts
 */
class ItemBlockTank(block: Block) : ItemBlock(block) {

	init {
		registryName = block.registryName
		setHasSubtypes(true)
	}

	override fun getSubItems(item: Item, tab: CreativeTabs?, subItems: MutableList<ItemStack>) {
		subItems.add(ItemStack(this))
		val stack2 = ItemStack(this)
		(stack2.getCapability(OxygenCaps.HANDLER, null) as OxygenHandlerImpl).stored = 20000f
		subItems.add(stack2)
	}

	override fun addInformation(stack: ItemStack, player: EntityPlayer, tooltip: MutableList<String>, advanced: Boolean) {
		val oxygen = stack.getCapability(OxygenCaps.HANDLER, EnumFacing.NORTH)
		tooltip.add(String.format("Oxygen: %.1f / %.0f", oxygen.stored, oxygen.capacity))
	}

	override fun initCapabilities(stack: ItemStack?, nbt: NBTTagCompound?): ICapabilityProvider? {
		return OxygenTankProvider(20000f, 20f)
	}

}
