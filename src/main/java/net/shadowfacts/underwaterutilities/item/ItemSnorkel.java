package net.shadowfacts.underwaterutilities.item;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.shadowfacts.shadowmc.item.ItemModelProvider;
import net.shadowfacts.underwaterutilities.UUMaterials;
import net.shadowfacts.underwaterutilities.UnderwaterUtilities;

import javax.annotation.Nullable;

/**
 * @author shadowfacts
 */
public class ItemSnorkel extends ItemArmor implements ItemModelProvider {

	public ItemSnorkel() {
		super(UUMaterials.GOGGLES, 0, EntityEquipmentSlot.HEAD);
		setRegistryName("snorkel");
		setUnlocalizedName("snorkel");
	}

	@Override
	public void initItemModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(UnderwaterUtilities.modId + ":snorkel", "inventory"));
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		if (!player.capabilities.isCreativeMode &&
				checkWaterLevel(player) &&
				player.getAir() < 300) {

			player.setAir(300);
		}
	}

	private boolean checkWaterLevel(EntityPlayer player) {
		World world = player.worldObj;
		BlockPos pos = player.getPosition().up(); // player.getPosition() is the foot position, .up() is the head positionclie
		IBlockState state = world.getBlockState(pos);
		if (state.getMaterial() == Material.WATER) {
			BlockPos upPos = pos.up();
			IBlockState upState = world.getBlockState(upPos);
			if (upState.getMaterial() == Material.AIR || (upState.getMaterial() == Material.WATER && world.getBlockState(upPos.up()).getMaterial() == Material.AIR)) {
				return true;
			}
		}
		return false;
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new ItemGoggles.GogglesCapProvider();
	}
}
