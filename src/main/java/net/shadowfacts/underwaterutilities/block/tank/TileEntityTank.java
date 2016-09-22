package net.shadowfacts.underwaterutilities.block.tank;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.shadowfacts.underwaterutilities.UnderwaterUtilities;
import net.shadowfacts.shadowmc.capability.CapHolder;
import net.shadowfacts.shadowmc.oxygen.OxygenCaps;
import net.shadowfacts.shadowmc.oxygen.OxygenHandler;
import net.shadowfacts.shadowmc.oxygen.OxygenProvider;
import net.shadowfacts.shadowmc.oxygen.OxygenReceiver;
import net.shadowfacts.shadowmc.oxygen.impl.OxygenTank;
import net.shadowfacts.shadowmc.tileentity.BaseTileEntity;

import javax.annotation.Nonnull;

/**
 * @author shadowfacts
 */
public class TileEntityTank extends BaseTileEntity implements ITickable {

	@CapHolder(capabilities = {OxygenHandler.class, OxygenProvider.class, OxygenReceiver.class})
	private OxygenTank oxygen = new OxygenTank(20000, 20, this::oxygenChanged);
	@CapHolder(capabilities = IItemHandler.class)
	private ItemStackHandler inventory = new ItemStackHandler(2);

	private int level;

	void loadOxygen(OxygenHandler oxygen) {
		this.oxygen.load(oxygen);
	}

	private int calcLevel() {
		return (int)((oxygen.getStored() / (float)oxygen.getCapacity()) * 10);
	}

	private void oxygenChanged(OxygenTank tank) {
		markDirty();
		int newLevel = calcLevel();
		if (level != newLevel) {
			level = newLevel;
			worldObj.setBlockState(pos, UnderwaterUtilities.blocks.tank.getDefaultState().withProperty(BlockTank.LEVEL, level));
		}
	}

	@Override
	public void update() {
		if (!worldObj.isRemote) {
//			receive
			if (canReceiveOxygenFromItem()) {
				receiveOxygenFromItem();
			}

//			send
			if (canSendOxygenToItem()) {
				sendOxygenToItem();
			}
		}
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, @Nonnull IBlockState oldState, @Nonnull IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	private boolean canReceiveOxygenFromItem() {
		ItemStack stack = inventory.getStackInSlot(0);
		return stack != null && stack.hasCapability(OxygenCaps.PROVIDER, EnumFacing.NORTH);
	}

	private void receiveOxygenFromItem() {
		ItemStack stack = inventory.getStackInSlot(0);
		OxygenProvider provider = stack.getCapability(OxygenCaps.PROVIDER, EnumFacing.NORTH);
		if (provider.getStored() > 0) {
			oxygen.receive(provider.extract(oxygen.receive(provider.getStored(), true), false), false);
			oxygenChanged(oxygen);
		}
	}

	private boolean canSendOxygenToItem() {
		ItemStack stack = inventory.getStackInSlot(1);
		return stack != null && stack.hasCapability(OxygenCaps.RECEIVER, EnumFacing.NORTH);
	}

	private void sendOxygenToItem() {
		ItemStack stack = inventory.getStackInSlot(1);
		OxygenReceiver receiver = stack.getCapability(OxygenCaps.RECEIVER, EnumFacing.NORTH);
		if (receiver.getStored() < receiver.getCapacity()) {
			oxygen.extract(receiver.receive(oxygen.extract(oxygen.getStored(), true), false), false);
			oxygenChanged(oxygen);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setTag("Oxygen", oxygen.serializeNBT());
		tag.setTag("Inventory", inventory.serializeNBT());
		return tag;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		oxygen.deserializeNBT(tag.getCompoundTag("Oxygen"));
		inventory.deserializeNBT(tag.getCompoundTag("Inventory"));

		level = calcLevel();
	}

}
