package net.shadowfacts.underwaterutilities.gui

import net.minecraft.client.gui.GuiScreen
import net.shadowfacts.shadowmc.config.GUIConfig
import net.shadowfacts.underwaterutilities.MOD_ID
import net.shadowfacts.underwaterutilities.UUConfig

/**
 * @author shadowfacts
 */
class UUConfigGUI(parent: GuiScreen): GUIConfig(parent, MOD_ID, UUConfig.config)
