package net.shadowfacts.underwaterutilities.block;

import net.shadowfacts.underwaterutilities.block.collector.BlockCollector;
import net.shadowfacts.underwaterutilities.block.tank.BlockTank;
import net.shadowfacts.underwaterutilities.block.tank.ItemBlockTank;

/**
 * @author shadowfacts
 */
public class ModBlocks extends net.shadowfacts.shadowmc.block.ModBlocks {

	public BlockCollector collector;
	public BlockTank tank;

	@Override
	public void init() {
		collector = register(new BlockCollector());
		tank = new BlockTank();
		register(tank, new ItemBlockTank(tank));
	}

}
