package net.shadowfacts.underwaterutilities.gui

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.fml.client.IModGuiFactory

/**
 * @author shadowfacts
 */
class UUGUIFactory : IModGuiFactory {

	override fun initialize(minecraftInstance: Minecraft) {

	}

	override fun mainConfigGuiClass(): Class<out GuiScreen> {
		return UUConfigGUI::class.java
	}

	override fun runtimeGuiCategories(): Set<IModGuiFactory.RuntimeOptionCategoryElement>? {
		return null
	}

	override fun getHandlerFor(element: IModGuiFactory.RuntimeOptionCategoryElement): IModGuiFactory.RuntimeOptionGuiHandler? {
		return null
	}

}
