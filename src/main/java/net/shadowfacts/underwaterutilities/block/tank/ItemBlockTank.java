package net.shadowfacts.underwaterutilities.block.tank;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.shadowfacts.shadowmc.oxygen.OxygenCaps;
import net.shadowfacts.shadowmc.oxygen.OxygenHandler;
import net.shadowfacts.shadowmc.oxygen.impl.OxygenHandlerImpl;
import net.shadowfacts.shadowmc.oxygen.impl.OxygenTankProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * @author shadowfacts
 */
public class ItemBlockTank extends ItemBlock {

	public ItemBlockTank(Block block) {
		super(block);
		setRegistryName(block.getRegistryName());
		setHasSubtypes(true);
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
		subItems.add(new ItemStack(this));
		ItemStack stack2 = new ItemStack(this);
		((OxygenHandlerImpl)stack2.getCapability(OxygenCaps.HANDLER, null)).setStored(20000);
		subItems.add(stack2);
	}

	@Override
	public void addInformation(@Nonnull ItemStack stack, @Nonnull EntityPlayer player, @Nonnull List<String> tooltip, boolean advanced) {
		OxygenHandler oxygen = stack.getCapability(OxygenCaps.HANDLER, EnumFacing.NORTH);
		tooltip.add(String.format("Oxygen: %.1f / %.0f", oxygen.getStored(), oxygen.getCapacity()));
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new OxygenTankProvider(20000, 20);
	}

}
