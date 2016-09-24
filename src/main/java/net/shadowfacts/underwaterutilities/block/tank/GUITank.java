package net.shadowfacts.underwaterutilities.block.tank;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.network.PacketRequestTEUpdate;
import net.shadowfacts.shadowmc.oxygen.OxygenCaps;
import net.shadowfacts.shadowmc.ui.element.button.UIImage;
import net.shadowfacts.shadowmc.ui.element.view.UIFixedView;
import net.shadowfacts.shadowmc.ui.util.UIBuilder;
import net.shadowfacts.underwaterutilities.UnderwaterUtilities;
import net.shadowfacts.underwaterutilities.gui.element.UITexturedOxygenIndicator;

/**
 * @author shadowfacts
 */
public class GUITank {

	private static final ResourceLocation BG = new ResourceLocation(UnderwaterUtilities.modId, "textures/gui/tank.png");

	public static GuiContainer create(BlockPos pos, InventoryPlayer playerInv, TileEntityTank tank) {
		UIFixedView root = new UIFixedView(176, 166, "root");

		UIImage bg = new UIImage(BG, 176, 166, "bg");
		root.add(bg);

		UIFixedView top = new UIFixedView(176, 166 / 2, "top");

		UITexturedOxygenIndicator oxygenIndicator = new UITexturedOxygenIndicator(tank.getCapability(OxygenCaps.HANDLER, EnumFacing.NORTH), "oxygen");
		top.add(oxygenIndicator);

		root.add(top);

		Runnable updateHandler = new Runnable() {
			private int ticks = 0;
			@Override
			public void run() {
				ticks++;
				if (ticks % 20 == 0) {
					ticks = 0;
					ShadowMC.network.sendToServer(new PacketRequestTEUpdate(tank));
				}
			}
		};

		return new UIBuilder()
				.add(root)
				.setUpdateHandler(updateHandler)
				.style(UnderwaterUtilities.modId + ":tank")
				.createContainer(new ContainerTank(pos, playerInv, tank));
	}

}
