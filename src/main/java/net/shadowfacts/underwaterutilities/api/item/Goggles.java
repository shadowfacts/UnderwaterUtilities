package net.shadowfacts.underwaterutilities.api.item;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * @author shadowfacts
 */
public class Goggles implements INBTSerializable {

	@Override
	public NBTBase serializeNBT() {
		return new NBTTagByte((byte)0);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {

	}

}
