package net.shadowfacts.underwaterutilities.util.energy;

import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.minecraftforge.energy.IEnergyStorage;

/**
 * @author shadowfacts
 */
public class FUAdapter implements IEnergyStorage {

	private final BaseTeslaContainer tesla;

	public FUAdapter(BaseTeslaContainer tesla) {
		this.tesla = tesla;
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		return (int)tesla.givePower(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		return (int)tesla.takePower(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored() {
		return (int)tesla.getStoredPower();
	}

	@Override
	public int getMaxEnergyStored() {
		return (int)tesla.getCapacity();
	}

	@Override
	public boolean canExtract() {
		return tesla.getOutputRate() > 0;
	}

	@Override
	public boolean canReceive() {
		return tesla.getInputRate() > 0;
	}
	
}
