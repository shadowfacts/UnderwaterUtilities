package net.shadowfacts.underwaterutilities.api.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;

/**
 * @author shadowfacts
 */
public class BreathingAid implements INBTSerializable<NBTTagCompound> {

	public boolean canBreathe(@Nonnull EntityPlayer player) {
		return false;
	}

	public void breathe(@Nonnull EntityPlayer player) {

	}

	@Override
	public NBTTagCompound serializeNBT() {
		return new NBTTagCompound();
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {

	}

}
