package net.shadowfacts.underwaterutilities.util.energy

import cofh.redstoneflux.api.IEnergyStorage
import net.darkhax.tesla.api.implementation.BaseTeslaContainer

/**
 * @author shadowfacts
 */
interface RFAdapter: IEnergyStorage {

	val teslaContainer: BaseTeslaContainer

	override fun receiveEnergy(maxReceive: Int, simulate: Boolean): Int {
		return teslaContainer.givePower(maxReceive.toLong(), simulate).toInt()
	}

	override fun extractEnergy(maxExtract: Int, simulate: Boolean): Int {
		return teslaContainer.takePower(maxExtract.toLong(), simulate).toInt()
	}

	override fun getEnergyStored() = teslaContainer.storedPower.toInt()

	override fun getMaxEnergyStored() = teslaContainer.capacity.toInt()

}
