package net.shadowfacts.underwaterutilities.block.tank

import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Slot
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler
import net.shadowfacts.shadowmc.inventory.ContainerBase
import net.shadowfacts.shadowmc.inventory.SlotOxygenHandler

/**
 * @author shadowfacts
 */
class ContainerTank(pos: BlockPos, inv: InventoryPlayer, tank: TileEntityTank): ContainerBase(pos) {

	init {
		val handler = tank.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)
		addSlotToContainer(SlotOxygenHandler(handler, 0, 44, 34))
		addSlotToContainer(SlotOxygenHandler(handler, 1, 116, 34))

		for (i in 0..2) {
			for (j in 0..8) {
				this.addSlotToContainer(Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18))
			}
		}

		for (k in 0..8) {
			this.addSlotToContainer(Slot(inv, k, 8 + k * 18, 142))
		}
	}

}

