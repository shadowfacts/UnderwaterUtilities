package net.shadowfacts.underwaterutilities.block.tank

import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.shadowfacts.shadowmc.ShadowMC
import net.shadowfacts.shadowmc.network.PacketRequestTEUpdate
import net.shadowfacts.shadowmc.oxygen.OxygenCaps
import net.shadowfacts.shadowmc.ui.dsl.*
import net.shadowfacts.underwaterutilities.MOD_ID
import net.shadowfacts.underwaterutilities.gui.element.UITexturedOxygenIndicator

/**
 * @author shadowfacts
 */
object GUITank {

	private val BG = ResourceLocation(MOD_ID, "textures/gui/tank.png")

	fun create(pos: BlockPos, playerInv: InventoryPlayer, tank: TileEntityTank): GuiContainer {
		return container(ContainerTank(pos, playerInv, tank)) {
			fixed {
				id = "root"
				width = 176
				height = 166

				image {
					id = "bg"
					width = 176
					height = 166
					texture = BG
				}

				fixed {
					id = "top"
					width = 176
					height = 166 / 2

					add(UITexturedOxygenIndicator(tank.getCapability(OxygenCaps.HANDLER, EnumFacing.NORTH)!!, "oxygen"))
				}
			}

			var ticks = 0
			updateHandler {
				ticks++
				if (ticks % 20 == 0) {
					ticks = 0
					ShadowMC.network.sendToServer(PacketRequestTEUpdate(tank))
				}
			}

			style("$MOD_ID:tank")
		}
	}

}
