package net.shadowfacts.underwaterutilities.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.shadowfacts.underwaterutilities.UUCapabilities;
import net.shadowfacts.underwaterutilities.UUMaterials;
import net.shadowfacts.underwaterutilities.api.item.Goggles;
import net.shadowfacts.shadowmc.item.ItemModelProvider;

import javax.annotation.Nullable;

/**
 * @author shadowfacts
 */
public class ItemGoggles extends ItemArmor implements ItemModelProvider {

	public ItemGoggles() {
		super(UUMaterials.GOGGLES, 0, EntityEquipmentSlot.HEAD);
		setRegistryName("goggles");
		setUnlocalizedName("goggles");
	}

	@Override
	public void initItemModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new GogglesCapProvider();
	}

	public static class GogglesCapProvider implements ICapabilityProvider {

		private Goggles goggles = new Goggles();

		@Override
		public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
			return capability == UUCapabilities.GOGGLES;
		}

		@Override
		public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
			return capability == UUCapabilities.GOGGLES ? (T)goggles : null;
		}

	}

}
