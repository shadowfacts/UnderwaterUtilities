package net.shadowfacts.underwaterutilities.block

import net.shadowfacts.shadowmc.block.ModBlocks
import net.shadowfacts.underwaterutilities.block.collector.BlockCollector
import net.shadowfacts.underwaterutilities.block.tank.BlockTank
import net.shadowfacts.underwaterutilities.block.tank.ItemBlockTank

/**
 * @author shadowfacts
 */
object UUBlocks : ModBlocks() {

	var collector: BlockCollector = BlockCollector()
	var tank: BlockTank = BlockTank()

	override fun init() {
		register(collector)
		register(tank, ItemBlockTank(tank))
	}

}
