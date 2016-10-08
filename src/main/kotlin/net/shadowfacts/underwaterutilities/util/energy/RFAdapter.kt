package net.shadowfacts.underwaterutilities.util.energy

import cofh.api.energy.IEnergyStorage
import net.darkhax.tesla.api.implementation.BaseTeslaContainer

/**
 * @author shadowfacts
 */
interface RFAdapter : IEnergyStorage {

	val teslaContainer: BaseTeslaContainer

	override fun receiveEnergy(maxReceive: Int, simulate: Boolean): Int {
		return teslaContainer.givePower(maxReceive.toLong(), simulate).toInt()
	}

	override fun extractEnergy(maxExtract: Int, simulate: Boolean): Int {
		return teslaContainer.takePower(maxExtract.toLong(), simulate).toInt()
	}

	override fun getEnergyStored(): Int {
		return teslaContainer.storedPower.toInt()
	}

	override fun getMaxEnergyStored(): Int {
		return teslaContainer.capacity.toInt()
	}

}
