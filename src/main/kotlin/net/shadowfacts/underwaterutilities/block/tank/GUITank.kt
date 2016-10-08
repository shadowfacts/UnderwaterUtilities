package net.shadowfacts.underwaterutilities.block.tank

import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.shadowfacts.shadowmc.ShadowMC
import net.shadowfacts.shadowmc.network.PacketRequestTEUpdate
import net.shadowfacts.shadowmc.oxygen.OxygenCaps
import net.shadowfacts.shadowmc.ui.element.button.UIImage
import net.shadowfacts.shadowmc.ui.element.view.UIFixedView
import net.shadowfacts.shadowmc.ui.util.UIBuilder
import net.shadowfacts.underwaterutilities.MOD_ID
import net.shadowfacts.underwaterutilities.gui.element.UITexturedOxygenIndicator

/**
 * @author shadowfacts
 */
object GUITank {

	private val BG = ResourceLocation(MOD_ID, "textures/gui/tank.png")

	fun create(pos: BlockPos, playerInv: InventoryPlayer, tank: TileEntityTank): GuiContainer {
		val root = UIFixedView(176, 166, "root")

		val bg = UIImage(BG, 176, 166, "bg")
		root.add(bg)

		val top = UIFixedView(176, 166 / 2, "top")

		val oxygenIndicator = UITexturedOxygenIndicator(tank.getCapability(OxygenCaps.HANDLER, EnumFacing.NORTH), "oxygen")
		top.add(oxygenIndicator)

		root.add(top)

		val updateHandler = object : Runnable {
			private var ticks = 0
			override fun run() {
				ticks++
				if (ticks % 20 == 0) {
					ticks = 0
					ShadowMC.network.sendToServer(PacketRequestTEUpdate(tank))
				}
			}
		}

		return UIBuilder().add(root).setUpdateHandler(updateHandler).style("$MOD_ID:tank").createContainer(ContainerTank(pos, playerInv, tank))
	}

}
