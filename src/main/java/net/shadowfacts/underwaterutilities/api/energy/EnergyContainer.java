package net.shadowfacts.underwaterutilities.api.energy;

import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * @author shadowfacts
 */
public class EnergyContainer implements INBTSerializable<NBTTagCompound> {

	public final BaseTeslaContainer tesla;
	public final RFContainer rf;

	public EnergyContainer(long power, long capacity, long input, long output) {
		tesla = new BaseTeslaContainer(power, capacity, input, output);
		rf = new RFContainer(tesla);
	}

	@Override
	public NBTTagCompound serializeNBT() {
		return tesla.serializeNBT();
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		tesla.deserializeNBT(nbt);
	}

}
