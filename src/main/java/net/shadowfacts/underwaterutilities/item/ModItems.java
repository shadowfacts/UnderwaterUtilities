package net.shadowfacts.underwaterutilities.item;

import net.shadowfacts.underwaterutilities.item.scuba.ItemBreather;
import net.shadowfacts.underwaterutilities.item.scuba.ItemTank;
import net.shadowfacts.shadowmc.item.ItemBase;

/**
 * @author shadowfacts
 */
public class ModItems extends net.shadowfacts.shadowmc.item.ModItems {

	public ItemGoggles goggles;
	public ItemBase goggleLens;
	public ItemBreather breather;
	public ItemTank tank;
	public ItemBase blade;
	public ItemBase fan;
	public ItemSnorkel snorkel;

	@Override
	public void init() {
		goggles = register(new ItemGoggles());
		goggleLens = register(new ItemBase("goggleLens"));
		breather = register(new ItemBreather());
		tank = register(new ItemTank());
		blade = register(new ItemBase("blade"));
		fan = register(new ItemBase("fan"));
		snorkel = register(new ItemSnorkel());
	}

}
