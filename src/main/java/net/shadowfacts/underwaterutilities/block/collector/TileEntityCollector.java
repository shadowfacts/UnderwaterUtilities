package net.shadowfacts.underwaterutilities.block.collector;

import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.shadowfacts.underwaterutilities.api.energy.EnergyContainer;
import net.shadowfacts.shadowmc.oxygen.OxygenCaps;
import net.shadowfacts.shadowmc.oxygen.OxygenReceiver;
import net.shadowfacts.shadowmc.tileentity.BaseTileEntity;

/**
 * @author shadowfacts
 */
public class TileEntityCollector extends BaseTileEntity implements ITickable {

	private EnergyContainer energy = new EnergyContainer(0, 10, 10, 10);

	@Override
	public void update() {
		if (!worldObj.isRemote) {
			if (energy.tesla.getStoredPower() >= 5) {
				for (EnumFacing facing : EnumFacing.VALUES) {
					if (facing != worldObj.getBlockState(pos).getValue(BlockCollector.FACING)) {
						TileEntity te = worldObj.getTileEntity(pos.offset(facing));
						if (te != null && te.hasCapability(OxygenCaps.RECEIVER, facing.getOpposite())) {
							OxygenReceiver receiver = te.getCapability(OxygenCaps.RECEIVER, facing.getOpposite());
							if (receiver.getStored() < receiver.getCapacity()) {
								receiver.receive(1, false);
								energy.tesla.takePower(5, false);
								break;
							}
						}
					}
				}
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setTag("Energy", energy.serializeNBT());
		return tag;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		energy.deserializeNBT(tag.getCompoundTag("Energy"));
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == TeslaCapabilities.CAPABILITY_HOLDER || capability == TeslaCapabilities.CAPABILITY_CONSUMER || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == TeslaCapabilities.CAPABILITY_HOLDER || capability == TeslaCapabilities.CAPABILITY_CONSUMER) {
			return (T)energy.tesla;
		} else {
			return super.getCapability(capability, facing);
		}
	}

}
