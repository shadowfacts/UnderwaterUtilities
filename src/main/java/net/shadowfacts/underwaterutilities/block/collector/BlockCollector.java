package net.shadowfacts.underwaterutilities.block.collector;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.shadowfacts.shadowmc.block.BlockTE;

import javax.annotation.Nonnull;

/**
 * @author shadowfacts
 */
public class BlockCollector extends BlockTE<TileEntityCollector> {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public BlockCollector() {
		super(Material.ROCK, "oxygenCollector");

		setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.NORTH));
	}

	@Nonnull
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Nonnull
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return getDefaultState().withProperty(FACING, getDirection(pos, placer));
	}

	private EnumFacing getDirection(BlockPos pos, EntityLivingBase entity) {
		return EnumFacing.getFacingFromVector(
				(float)(entity.posX - pos.getX()),
				0,
				(float)(entity.posZ - pos.getZ()));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).ordinal();
	}

	@Nonnull
	@Override
	@Deprecated
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
	}

	@Nonnull
	@Override
	public TileEntityCollector createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
		return new TileEntityCollector();
	}

	@Override
	public Class<TileEntityCollector> getTileEntityClass() {
		return TileEntityCollector.class;
	}

}
