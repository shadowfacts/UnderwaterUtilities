package net.shadowfacts.underwaterutilities.gui;

import net.minecraft.client.gui.GuiScreen;
import net.shadowfacts.underwaterutilities.UUConfig;
import net.shadowfacts.underwaterutilities.UnderwaterUtilities;
import net.shadowfacts.shadowmc.config.GUIConfig;

/**
 * @author shadowfacts
 */
public class UUConfigGUI extends GUIConfig {

	public UUConfigGUI(GuiScreen parent) {
		super(parent, UnderwaterUtilities.modId, UUConfig.config);
	}

}
