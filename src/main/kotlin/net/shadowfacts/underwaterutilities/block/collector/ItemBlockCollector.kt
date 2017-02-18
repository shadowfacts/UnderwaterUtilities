package net.shadowfacts.underwaterutilities.block.collector

import net.minecraft.block.Block
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.shadowfacts.underwaterutilities.block.UUBlocks

/**
 * @author shadowfacts
 */
class ItemBlockCollector(block: Block): ItemBlock(block) {

	init {
		registryName = block.registryName
	}

	override fun getMetadata(damage: Int): Int {
		return UUBlocks.collector.getMetaFromState(UUBlocks.collector.defaultState)
	}

	override fun getMetadata(stack: ItemStack): Int {
		return UUBlocks.collector.getMetaFromState(UUBlocks.collector.defaultState)
	}

}