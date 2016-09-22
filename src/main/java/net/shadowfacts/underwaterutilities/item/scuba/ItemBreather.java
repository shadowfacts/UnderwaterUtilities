package net.shadowfacts.underwaterutilities.item.scuba;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.shadowfacts.underwaterutilities.UUMaterials;
import net.shadowfacts.underwaterutilities.UnderwaterUtilities;
import net.shadowfacts.underwaterutilities.item.ItemGoggles;
import net.shadowfacts.shadowmc.item.ItemModelProvider;
import net.shadowfacts.shadowmc.oxygen.OxygenCaps;
import net.shadowfacts.shadowmc.oxygen.OxygenProvider;

import javax.annotation.Nullable;

/**
 * @author shadowfacts
 */
public class ItemBreather extends ItemArmor implements ItemModelProvider {

	public ItemBreather() {
		super(UUMaterials.SCUBA, 0, EntityEquipmentSlot.HEAD);
		setRegistryName("scubaBreather");
		setUnlocalizedName("scubaBreather");
	}

	@Override
	public void initItemModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(UnderwaterUtilities.modId + ":scubaBreather", "inventory"));
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if (!player.capabilities.isCreativeMode && player.isInWater() && player.getAir() < 300) {
			ItemStack chestpiece = player.inventory.armorItemInSlot(2);
			if (chestpiece != null && chestpiece.hasCapability(OxygenCaps.PROVIDER, EnumFacing.NORTH)) {
				OxygenProvider provider = chestpiece.getCapability(OxygenCaps.PROVIDER, EnumFacing.NORTH);
				if (provider.getStored() > 0) {
					float amount = provider.extract(300 - player.getAir(), false);
					player.setAir(player.getAir() + (int)amount);
				}
			}
		}
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new ItemGoggles.GogglesCapProvider();
	}

}
