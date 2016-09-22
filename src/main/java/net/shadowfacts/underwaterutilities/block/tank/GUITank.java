package net.shadowfacts.underwaterutilities.block.tank;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.shadowfacts.underwaterutilities.UnderwaterUtilities;
import net.shadowfacts.underwaterutilities.gui.component.TexturedOxygenIndicator;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.gui.component.GUIComponentText;
import net.shadowfacts.shadowmc.gui.component.GUIComponentTexture;
import net.shadowfacts.shadowmc.gui.mcwrapper.GuiContainerWrapper;
import net.shadowfacts.shadowmc.gui.mcwrapper.MCBaseGUIContainer;
import net.shadowfacts.shadowmc.network.PacketRequestTEUpdate;
import net.shadowfacts.shadowmc.oxygen.OxygenCaps;

/**
 * @author shadowfacts
 */
public class GUITank extends MCBaseGUIContainer {

	private static final ResourceLocation BG = new ResourceLocation(UnderwaterUtilities.modId, "textures/gui/tank.png");

	private TileEntityTank tank;

	public GUITank(GuiContainerWrapper wrapper, TileEntityTank tank) {
		super(wrapper);
		this.tank = tank;

		addChild(new GUIComponentTexture(0, -13, xSize, ySize + 13, BG));
		addChild(new GUIComponentText(5, -10, I18n.format("tile.oxygenTank.name")));
		addChild(new TexturedOxygenIndicator(79, 12, 18, 66, tank.getCapability(OxygenCaps.HANDLER, EnumFacing.NORTH)));
	}

	@Override
	public void update() {
		super.update();

		ShadowMC.network.sendToServer(new PacketRequestTEUpdate(tank));
	}

	public static GuiScreen create(InventoryPlayer inv, BlockPos pos, TileEntityTank tank) {
		GuiContainerWrapper wrapper = new GuiContainerWrapper(new ContainerTank(pos, inv, tank));
		GUITank gui = new GUITank(wrapper, tank);
		gui.setZLevel(0);
		wrapper.gui = gui;
		return wrapper;
	}

}
