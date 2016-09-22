package net.shadowfacts.underwaterutilities.block.tank;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.shadowfacts.underwaterutilities.UnderwaterUtilities;
import net.shadowfacts.underwaterutilities.gui.UUGUIHandler;
import net.shadowfacts.shadowmc.block.BlockTE;
import net.shadowfacts.shadowmc.oxygen.OxygenCaps;
import net.shadowfacts.shadowmc.oxygen.OxygenHandler;
import net.shadowfacts.shadowmc.oxygen.impl.OxygenHandlerImpl;
import net.shadowfacts.shadowmc.util.MeshWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowfacts
 */
public class BlockTank extends BlockTE<TileEntityTank> {

	public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 10);

	private static final AxisAlignedBB BOX = new AxisAlignedBB(4/16f, 0, 4/16f, 12/16f, 13/16f, 12/16f);

	public BlockTank() {
		super(Material.ROCK, "oxygenTank");
		setDefaultState(getDefaultState().withProperty(LEVEL, 0));
	}

	@Override
	public void initItemModel() {
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(this), MeshWrapper.of(stack -> {
			OxygenHandler handler = stack.getCapability(OxygenCaps.HANDLER, null);
			int level = (int)((handler.getStored() / (float)handler.getCapacity()) * 10);
			return new ModelResourceLocation(UnderwaterUtilities.modId + ":oxygenTank", "level=" + level);
		}));
	}

	@Nonnull
	@Override
	@Deprecated
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOX;
	}

	@Override
	@Deprecated
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, @Nonnull World world, @Nonnull BlockPos pos) {
		return BOX;
	}

	@Nonnull
	@Override
	@Deprecated
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, @Nonnull World world, @Nonnull BlockPos pos) {
		return BOX;
	}

	@Override
	public boolean isSideSolid(IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntityTank tank = getTileEntity(world, pos);
		tank.loadOxygen(stack.getCapability(OxygenCaps.HANDLER, null));
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		player.openGui(UnderwaterUtilities.instance, UUGUIHandler.TANK, world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	@Nonnull
	@Override
	@Deprecated
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, LEVEL);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(LEVEL);
	}

	@Nonnull
	@Override
	@Deprecated
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(LEVEL, meta);
	}

	@Nonnull
	@Override
	@Deprecated
	public IBlockState getActualState(@Nonnull IBlockState state, IBlockAccess world, BlockPos pos) {
		return state;
	}

	@Override
	@Deprecated
	public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
		ItemStack stack = new ItemStack(this);
		((OxygenHandlerImpl)stack.getCapability(OxygenCaps.HANDLER, EnumFacing.NORTH)).load(getTileEntity(world, pos).getCapability(OxygenCaps.HANDLER, EnumFacing.NORTH));

		EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
		world.spawnEntityInWorld(item);

		super.breakBlock(world, pos, state);
	}

	@Nonnull
	@Override
	@Deprecated
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, @Nonnull IBlockState state, int fortune) {
		return new ArrayList<>();
	}

	@Nonnull
	@Override
	public TileEntityTank createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
		return new TileEntityTank();
	}

	@Override
	public Class<TileEntityTank> getTileEntityClass() {
		return TileEntityTank.class;
	}

}
