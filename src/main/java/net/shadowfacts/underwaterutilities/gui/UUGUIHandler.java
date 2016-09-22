package net.shadowfacts.underwaterutilities.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.shadowfacts.underwaterutilities.block.tank.ContainerTank;
import net.shadowfacts.underwaterutilities.block.tank.GUITank;
import net.shadowfacts.underwaterutilities.block.tank.TileEntityTank;

/**
 * @author shadowfacts
 */
public class UUGUIHandler implements IGuiHandler {

	public static final int TANK = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		switch (ID) {
			case TANK:
				return new ContainerTank(pos, player.inventory, (TileEntityTank)world.getTileEntity(pos));
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		switch (ID) {
			case TANK:
				return GUITank.create(player.inventory, pos, (TileEntityTank)world.getTileEntity(pos));
			default:
				return null;
		}
	}

}
