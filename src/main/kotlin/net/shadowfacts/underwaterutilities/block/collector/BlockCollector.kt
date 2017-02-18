package net.shadowfacts.underwaterutilities.block.collector

import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyDirection
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.Item
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.shadowfacts.shadowmc.ShadowMC
import net.shadowfacts.shadowmc.block.BlockTE

/**
 * @author shadowfacts
 */
class BlockCollector : BlockTE<TileEntityCollector>(Material.ROCK, "oxygen_collector") {

	companion object {
		val FACING: PropertyDirection = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL)
	}

	init {
		defaultState = defaultState.withProperty(FACING, EnumFacing.NORTH)
		unlocalizedName = registryName.toString()
		setCreativeTab(CreativeTabs.MISC)
		setHardness(2f)
		soundType = SoundType.STONE
	}

	override fun createBlockState(): BlockStateContainer {
		return BlockStateContainer(this, FACING)
	}

	override fun getStateForPlacement(world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase, hand: EnumHand): IBlockState {
		return defaultState.withProperty(FACING, getDirection(pos, placer))
	}

	private fun getDirection(pos: BlockPos, entity: EntityLivingBase): EnumFacing {
		return EnumFacing.getFacingFromVector(
				(entity.posX - pos.x).toFloat(),
				0f,
				(entity.posZ - pos.z).toFloat())
	}

	override fun getMetaFromState(state: IBlockState?): Int {
		return state!!.getValue(FACING).ordinal
	}

	@Deprecated("")
	override fun getStateFromMeta(meta: Int): IBlockState {
		return defaultState.withProperty(FACING, EnumFacing.getFront(meta))
	}

	override fun initItemModel() {
		ShadowMC.proxy.registerItemModel(Item.getItemFromBlock(this), getMetaFromState(defaultState), registryName)
	}

	override fun createTileEntity(world: World, state: IBlockState): TileEntityCollector {
		return TileEntityCollector()
	}

	override fun getTileEntityClass(): Class<TileEntityCollector> {
		return TileEntityCollector::class.java
	}

}
