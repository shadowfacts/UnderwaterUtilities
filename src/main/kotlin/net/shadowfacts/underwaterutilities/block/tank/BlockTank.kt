package net.shadowfacts.underwaterutilities.block.tank

import net.minecraft.block.SoundType
import net.minecraft.block.material.Material

import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.shadowfacts.shadowmc.block.BlockTE
import net.shadowfacts.shadowmc.oxygen.OxygenCaps
import net.shadowfacts.shadowmc.oxygen.impl.OxygenHandlerImpl
import net.shadowfacts.shadowmc.util.MeshWrapper
import net.shadowfacts.underwaterutilities.MOD_ID
import net.shadowfacts.underwaterutilities.UnderwaterUtilities
import net.shadowfacts.underwaterutilities.gui.UUGUIHandler
import java.util.ArrayList
/**
 * @author shadowfacts
 */
class BlockTank : BlockTE<TileEntityTank>(Material.ROCK, "oxygen_tank") {

	companion object {
		val LEVEL: PropertyInteger = PropertyInteger.create("level", 0, 10)

		private val BOX = AxisAlignedBB((4 / 16f).toDouble(), 0.0, (4 / 16f).toDouble(), (12 / 16f).toDouble(), (13 / 16f).toDouble(), (12 / 16f).toDouble())
	}

	init {
		defaultState = defaultState.withProperty(LEVEL, 0)
		unlocalizedName = registryName.toString()
		setCreativeTab(CreativeTabs.MISC)
		setHardness(2f)
		soundType = SoundType.STONE
	}

	override fun initItemModel() {
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(this)!!, MeshWrapper.of { stack ->
			val handler = stack.getCapability(OxygenCaps.HANDLER, null)!!
			val level = (handler.stored / handler.capacity.toFloat() * 10).toInt()
			ModelResourceLocation("$MOD_ID:oxygen_tank", "level=" + level)
		})
	}

	@Deprecated("")
	override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB {
		return BOX
	}

	override fun getCollisionBoundingBox(blockState: IBlockState, world: IBlockAccess, pos: BlockPos): AxisAlignedBB? {
		return BOX
	}

	@Deprecated("")
	override fun getSelectedBoundingBox(state: IBlockState, world: World, pos: BlockPos): AxisAlignedBB {
		return BOX
	}

	override fun isSideSolid(state: IBlockState, world: IBlockAccess, pos: BlockPos, side: EnumFacing): Boolean {
		return false
	}

	@Deprecated("")
	override fun isOpaqueCube(state: IBlockState): Boolean {
		return false
	}

	@Deprecated("")
	override fun isFullCube(state: IBlockState): Boolean {
		return false
	}

	override fun getStateForPlacement(world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase, hand: EnumHand): IBlockState {
		val stack = placer.getHeldItem(hand)
		val handler = stack.getCapability(OxygenCaps.HANDLER, null)!!
		val level = (handler.stored / handler.capacity * 10).toInt()
		return defaultState.withProperty(LEVEL, level)
	}

	override fun onBlockPlacedBy(world: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
		val tank = getTileEntity(world, pos)
		tank.loadOxygen(stack.getCapability(OxygenCaps.HANDLER, null)!!)
	}

	override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
		player.openGui(UnderwaterUtilities, UUGUIHandler.TANK, world, pos.x, pos.y, pos.z)
		return true
	}

	@Deprecated("")
	override fun createBlockState(): BlockStateContainer {
		return BlockStateContainer(this, LEVEL)
	}

	override fun getMetaFromState(state: IBlockState): Int {
		return state.getValue(LEVEL)
	}

	@Deprecated("")
	override fun getStateFromMeta(meta: Int): IBlockState {
		return defaultState.withProperty(LEVEL, meta)
	}

	@Deprecated("")
	override fun breakBlock(world: World, pos: BlockPos, state: IBlockState) {
		val stack = ItemStack(this)
		(stack.getCapability(OxygenCaps.HANDLER, EnumFacing.NORTH) as OxygenHandlerImpl).load(getTileEntity(world, pos).getCapability(OxygenCaps.HANDLER, EnumFacing.NORTH))

		val item = EntityItem(world, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), stack)
		world.spawnEntity(item)

		super.breakBlock(world, pos, state)
	}

	@Deprecated("")
	override fun getDrops(world: IBlockAccess, pos: BlockPos, state: IBlockState, fortune: Int): List<ItemStack> {
		return ArrayList()
	}

	override fun createTileEntity(world: World, state: IBlockState): TileEntityTank {
		return TileEntityTank()
	}

	override fun getTileEntityClass(): Class<TileEntityTank> {
		return TileEntityTank::class.java
	}

}
