package net.shadowfacts.underwaterutilities.gui.component;

import net.shadowfacts.shadowmc.gui.component.GUIOxygenIndicator;
import net.shadowfacts.shadowmc.oxygen.OxygenHandler;
import net.shadowfacts.shadowmc.util.Color;

/**
 * @author shadowfacts
 */
public class TexturedOxygenIndicator extends GUIOxygenIndicator {

	public TexturedOxygenIndicator(int x, int y, int width, int height, OxygenHandler handler) {
		super(x, y, width, height, handler);
		primaryColor = Color.TRANSPARENT;
		secondaryColor = new Color(0xFF07789D);
	}

}
