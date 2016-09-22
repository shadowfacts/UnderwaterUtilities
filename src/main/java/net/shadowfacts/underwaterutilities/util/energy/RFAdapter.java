package net.shadowfacts.underwaterutilities.util.energy;

import cofh.api.energy.IEnergyStorage;
import net.darkhax.tesla.api.implementation.BaseTeslaContainer;

/**
 * @author shadowfacts
 */
public interface RFAdapter extends IEnergyStorage {

	BaseTeslaContainer getTeslaContainer();

	@Override
	default int receiveEnergy(int maxReceive, boolean simulate) {
		return (int)getTeslaContainer().givePower(maxReceive, simulate);
	}

	@Override
	default int extractEnergy(int maxExtract, boolean simulate) {
		return (int)getTeslaContainer().takePower(maxExtract, simulate);
	}

	@Override
	default int getEnergyStored() {
		return (int)getTeslaContainer().getStoredPower();
	}

	@Override
	default int getMaxEnergyStored() {
		return (int)getTeslaContainer().getCapacity();
	}

}
