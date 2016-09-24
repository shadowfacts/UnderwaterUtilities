package net.shadowfacts.underwaterutilities.gui.element;

import net.shadowfacts.shadowmc.oxygen.OxygenHandler;
import net.shadowfacts.shadowmc.ui.UIDimensions;
import net.shadowfacts.shadowmc.ui.element.UIOxygenIndicator;
import net.shadowfacts.shadowmc.ui.style.UIAttribute;

import java.awt.*;

/**
 * @author shadowfacts
 */
public class UITexturedOxygenIndicator extends UIOxygenIndicator {

	public UITexturedOxygenIndicator(OxygenHandler handler, String id, String... classes) {
		super(handler, id, classes);
		setStyle(UIAttribute.PRIMARY_COLOR, new Color(0, 0, 0, 0));
		setStyle(UIAttribute.SECONDARY_COLOR, new Color(0xFF07789D));
	}

	@Override
	public UIDimensions getPreferredDimensions() {
		return new UIDimensions(18, 66);
	}

}
