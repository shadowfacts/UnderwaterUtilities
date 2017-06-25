package net.shadowfacts.underwaterutilities.block.collector

import net.darkhax.tesla.api.ITeslaConsumer
import net.darkhax.tesla.api.ITeslaHolder
import net.darkhax.tesla.api.ITeslaProducer
import net.darkhax.tesla.api.implementation.BaseTeslaContainer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.ITickable
import net.minecraftforge.energy.IEnergyStorage
import net.shadowfacts.shadowmc.capability.CapHolder
import net.shadowfacts.shadowmc.oxygen.OxygenCaps
import net.shadowfacts.shadowmc.tileentity.BaseTileEntity
import net.shadowfacts.underwaterutilities.util.energy.FUAdapter
import net.shadowfacts.underwaterutilities.util.energy.RFAdapter

/**
 * @author shadowfacts
 */
class TileEntityCollector: BaseTileEntity(), ITickable, RFAdapter {

	@CapHolder(capabilities = arrayOf(ITeslaHolder::class, ITeslaConsumer::class, ITeslaProducer::class))
	private val tesla = BaseTeslaContainer(0, 10, 10, 10)

	@CapHolder(capabilities = arrayOf(IEnergyStorage::class))
	private val fuAdapter = FUAdapter(tesla)

	override fun update() {
		if (!world.isRemote) {
			if (tesla.storedPower >= 5) {
				for (facing in EnumFacing.VALUES) {
					if (facing != world.getBlockState(pos).getValue(BlockCollector.FACING)) {
						val te = world.getTileEntity(pos.offset(facing))
						if (te != null && te.hasCapability(OxygenCaps.RECEIVER, facing.opposite)) {
							val receiver = te.getCapability(OxygenCaps.RECEIVER, facing.opposite)!!
							if (receiver.stored < receiver.capacity) {
								receiver.receive(1f, false)
								tesla.takePower(5, false)
								break
							}
						}
					}
				}
			}
		}
	}

	override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
		super.writeToNBT(tag)
		tag.setTag("Energy", tesla.serializeNBT())
		return tag
	}

	override fun readFromNBT(tag: NBTTagCompound) {
		super.readFromNBT(tag)
		tesla.deserializeNBT(tag.getCompoundTag("Energy"))
	}

//	FUAdapter
	override val teslaContainer: BaseTeslaContainer
		get() = tesla

}
