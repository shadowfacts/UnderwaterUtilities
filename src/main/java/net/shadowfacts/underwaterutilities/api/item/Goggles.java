package net.shadowfacts.underwaterutilities.api.item;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * @author shadowfacts
 */
public class Goggles implements INBTSerializable<NBTTagCompound> {

	@Override
	public NBTTagCompound serializeNBT() {
		return new NBTTagCompound();
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {

	}

}
