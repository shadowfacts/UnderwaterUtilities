package net.shadowfacts.underwaterutilities.gui

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.fml.client.IModGuiFactory

/**
 * @author shadowfacts
 */
class UUGUIFactory: IModGuiFactory {

	override fun initialize(minecraftInstance: Minecraft) {}

	override fun hasConfigGui() = false

	override fun createConfigGui(parentScreen: GuiScreen) = UUConfigGUI(parentScreen)

	override fun runtimeGuiCategories() = null

}
