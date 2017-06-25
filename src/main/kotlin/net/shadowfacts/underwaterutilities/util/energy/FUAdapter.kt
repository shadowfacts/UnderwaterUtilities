package net.shadowfacts.underwaterutilities.util.energy

import net.darkhax.tesla.api.implementation.BaseTeslaContainer
import net.minecraftforge.energy.IEnergyStorage

/**
 * @author shadowfacts
 */
class FUAdapter(private val tesla: BaseTeslaContainer): IEnergyStorage {

	override fun receiveEnergy(maxReceive: Int, simulate: Boolean): Int {
		return tesla.givePower(maxReceive.toLong(), simulate).toInt()
	}

	override fun extractEnergy(maxExtract: Int, simulate: Boolean): Int {
		return tesla.takePower(maxExtract.toLong(), simulate).toInt()
	}

	override fun getEnergyStored() = tesla.storedPower.toInt()

	override fun getMaxEnergyStored() = tesla.capacity.toInt()

	override fun canExtract() = tesla.outputRate > 0

	override fun canReceive() = tesla.inputRate > 0

}
