package net.shadowfacts.underwaterutilities.block.tank;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.shadowfacts.shadowmc.inventory.ContainerBase;
import net.shadowfacts.shadowmc.inventory.SlotOxygenHandler;

/**
 * @author shadowfacts
 */
public class ContainerTank extends ContainerBase {

	public ContainerTank(BlockPos pos, InventoryPlayer inv, TileEntityTank tank) {
		super(pos);

		IItemHandler handler = tank.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		addSlotToContainer(new SlotOxygenHandler(handler, 0, 44, 34));
		addSlotToContainer(new SlotOxygenHandler(handler, 1, 116, 34));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; k++) {
			this.addSlotToContainer(new Slot(inv, k, 8 + k * 18, 142));
		}
	}

}
