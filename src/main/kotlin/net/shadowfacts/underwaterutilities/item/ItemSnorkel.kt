package net.shadowfacts.underwaterutilities.item

import net.minecraft.block.material.Material
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemArmor
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.shadowfacts.shadowmc.item.ItemModelProvider
import net.shadowfacts.underwaterutilities.MOD_ID
import net.shadowfacts.underwaterutilities.UUMaterials

/**
 * @author shadowfacts
 */
class ItemSnorkel : ItemArmor(UUMaterials.GOGGLES, 0, EntityEquipmentSlot.HEAD), ItemModelProvider {

	init {
		setRegistryName("snorkel")
		unlocalizedName = "snorkel"
	}

	override fun initItemModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, ModelResourceLocation("$MOD_ID:snorkel", "inventory"))
	}

	override fun onArmorTick(world: World, player: EntityPlayer, itemStack: ItemStack) {
		if (!player.capabilities.isCreativeMode &&
				checkWaterLevel(player) &&
				player.air < 300) {

			player.air = 300
		}
	}

	private fun checkWaterLevel(player: EntityPlayer): Boolean {
		val world = player.worldObj
		val pos = player.position.up() // player.getPosition() is the foot position, .up() is the head positionclie
		val state = world.getBlockState(pos)
		if (state.material === Material.WATER) {
			val upPos = pos.up()
			val upState = world.getBlockState(upPos)
			if (upState.material === Material.AIR || upState.material === Material.WATER && world.getBlockState(upPos.up()).material === Material.AIR) {
				return true
			}
		}
		return false
	}

	override fun initCapabilities(stack: ItemStack, nbt: NBTTagCompound?): ICapabilityProvider? {
		return ItemGoggles.GogglesCapProvider()
	}

}
