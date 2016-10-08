package net.shadowfacts.underwaterutilities.gui

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler
import net.shadowfacts.underwaterutilities.block.tank.ContainerTank
import net.shadowfacts.underwaterutilities.block.tank.GUITank
import net.shadowfacts.underwaterutilities.block.tank.TileEntityTank

/**
 * @author shadowfacts
 */
object UUGUIHandler : IGuiHandler {

	val TANK = 0

	override fun getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
		val pos = BlockPos(x, y, z)
		when (ID) {
			TANK -> return ContainerTank(pos, player.inventory, (world.getTileEntity(pos) as TileEntityTank?)!!)
			else -> return null
		}
	}

	override fun getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
		val pos = BlockPos(x, y, z)
		when (ID) {
			TANK -> return GUITank.create(pos, player.inventory, (world.getTileEntity(pos) as TileEntityTank?)!!)
			else -> return null
		}
	}

}
