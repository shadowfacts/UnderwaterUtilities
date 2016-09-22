package net.shadowfacts.underwaterutilities.block.collector;

import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;
import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.IEnergyStorage;
import net.shadowfacts.shadowmc.capability.CapHolder;
import net.shadowfacts.shadowmc.oxygen.OxygenCaps;
import net.shadowfacts.shadowmc.oxygen.OxygenReceiver;
import net.shadowfacts.shadowmc.tileentity.BaseTileEntity;
import net.shadowfacts.underwaterutilities.util.energy.FUAdapter;
import net.shadowfacts.underwaterutilities.util.energy.RFAdapter;

/**
 * @author shadowfacts
 */
public class TileEntityCollector extends BaseTileEntity implements ITickable, RFAdapter {

	@CapHolder(capabilities = {ITeslaHolder.class, ITeslaConsumer.class, ITeslaProducer.class})
	private BaseTeslaContainer tesla = new BaseTeslaContainer(0, 10, 10, 10);

	@CapHolder(capabilities = IEnergyStorage.class)
	private FUAdapter fuAdapter = new FUAdapter(tesla);

	@Override
	public void update() {
		if (!worldObj.isRemote) {
			if (tesla.getStoredPower() >= 5) {
				for (EnumFacing facing : EnumFacing.VALUES) {
					if (facing != worldObj.getBlockState(pos).getValue(BlockCollector.FACING)) {
						TileEntity te = worldObj.getTileEntity(pos.offset(facing));
						if (te != null && te.hasCapability(OxygenCaps.RECEIVER, facing.getOpposite())) {
							OxygenReceiver receiver = te.getCapability(OxygenCaps.RECEIVER, facing.getOpposite());
							if (receiver.getStored() < receiver.getCapacity()) {
								receiver.receive(1, false);
								tesla.takePower(5, false);
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
		tag.setTag("Energy", tesla.serializeNBT());
		return tag;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		tesla.deserializeNBT(tag.getCompoundTag("Energy"));
	}

	//	RFAdapter
	@Override
	public BaseTeslaContainer getTeslaContainer() {
		return tesla;
	}

}
