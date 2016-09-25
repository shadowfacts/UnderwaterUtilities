package net.shadowfacts.underwaterutilities.item.scuba;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.shadowfacts.underwaterutilities.UUMaterials;
import net.shadowfacts.underwaterutilities.UnderwaterUtilities;
import net.shadowfacts.underwaterutilities.model.ModelScubaTank;
import net.shadowfacts.shadowmc.item.ItemModelProvider;
import net.shadowfacts.shadowmc.oxygen.OxygenCaps;
import net.shadowfacts.shadowmc.oxygen.OxygenHandler;
import net.shadowfacts.shadowmc.oxygen.impl.OxygenTankProvider;
import net.shadowfacts.shadowmc.util.MeshWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * @author shadowfacts
 */
public class ItemTank extends ItemArmor implements ItemModelProvider {

	public ItemTank() {
		super(UUMaterials.SCUBA, 0, EntityEquipmentSlot.CHEST);
		setRegistryName("scubaTank");
		setUnlocalizedName("scubaTank");
	}

	@Nonnull
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		return armorSlot == EntityEquipmentSlot.CHEST ? ModelScubaTank.instance : _default;
	}

	@Override
	public void initItemModel() {
		ModelLoader.setCustomMeshDefinition(this, MeshWrapper.of(stack -> {
			OxygenHandler handler = stack.getCapability(OxygenCaps.HANDLER, EnumFacing.NORTH);
			int level = (int)((handler.getStored() / handler.getCapacity()) * 10);
			return new ModelResourceLocation(UnderwaterUtilities.modId + ":scubaTank", "level=" + level);
		}));
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		OxygenHandler handler = stack.getCapability(OxygenCaps.HANDLER, EnumFacing.NORTH);
		tooltip.add(String.format("Oxygen: %.1f / %.0f", handler.getStored(), handler.getCapacity()));
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new OxygenTankProvider(12000, 20);
	}

}
