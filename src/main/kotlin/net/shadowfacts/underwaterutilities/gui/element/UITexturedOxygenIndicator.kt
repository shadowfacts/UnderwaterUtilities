package net.shadowfacts.underwaterutilities.gui.element

import net.shadowfacts.shadowmc.oxygen.OxygenHandler
import net.shadowfacts.shadowmc.ui.UIDimensions
import net.shadowfacts.shadowmc.ui.element.UIOxygenIndicator
import net.shadowfacts.shadowmc.ui.style.UIAttribute

import java.awt.Color

/**
 * @author shadowfacts
 */
class UITexturedOxygenIndicator(handler: OxygenHandler, id: String, vararg classes: String) : UIOxygenIndicator(handler, id, *classes) {

	init {
		setStyle(UIAttribute.PRIMARY_COLOR, Color(0, 0, 0, 0))
		setStyle(UIAttribute.SECONDARY_COLOR, Color(0xFF07789D.toInt()))
	}

	override fun getPreferredDimensions(): UIDimensions {
		return UIDimensions(18, 66)
	}

}
