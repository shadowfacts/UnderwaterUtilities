package net.shadowfacts.underwaterutilities.event

import net.minecraft.block.material.Material
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.MathHelper
import net.minecraftforge.client.GuiIngameForge
import net.minecraftforge.client.event.RenderBlockOverlayEvent
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.shadowfacts.shadowmc.oxygen.OxygenCaps
import net.shadowfacts.underwaterutilities.MOD_ID
import net.shadowfacts.underwaterutilities.UUCapabilities

/**
 * @author shadowfacts
 */
object ClientEventHandler {

	@SubscribeEvent
	fun onRenderWaterOverlay(event: RenderBlockOverlayEvent) {
		if (event.overlayType == RenderBlockOverlayEvent.OverlayType.WATER) {
			val player = Minecraft.getMinecraft().thePlayer
			val helmet = player.inventory.armorItemInSlot(3)
			if (helmet != null && helmet.hasCapability(UUCapabilities.GOGGLES!!, null)) {
				event.isCanceled = true
			}
		}
	}

	@SubscribeEvent
	fun onRenderAir(event: RenderGameOverlayEvent.Pre) {
		if (event.type == RenderGameOverlayEvent.ElementType.AIR) {

			val player = Minecraft.getMinecraft().thePlayer

			if (player.isInsideOfMaterial(Material.WATER)) {

				val stack = player.inventory.armorItemInSlot(2)
				if (stack != null && stack.hasCapability(OxygenCaps.HANDLER, EnumFacing.NORTH)) {
					event.isCanceled = true

					GlStateManager.enableBlend()
					val res = ScaledResolution(Minecraft.getMinecraft())
					val left = res.scaledWidth / 2 + 91
					val top = res.scaledHeight - GuiIngameForge.right_height

					val handler = stack.getCapability(OxygenCaps.HANDLER, EnumFacing.NORTH)
					val full = MathHelper.ceiling_double_int((handler.stored - 2).toDouble() * 10.0 / handler.capacity.toDouble())
					val partial = MathHelper.ceiling_double_int(handler.stored.toDouble() * 10.0 / handler.capacity.toDouble()) - full

					for (i in 0..full + partial - 1) {
						bindTexture(TEXTURE)
						drawTexturedModalRect(left - i * 8 - 9, top, 0.0, if (i < full) 0 else 9, 0, 9, 9)
					}
					GuiIngameForge.right_height += 10

					GlStateManager.disableBlend()

				}

			}

		}
	}

	private val TEXTURE = ResourceLocation(MOD_ID, "textures/gui/hud.png")

	private fun bindTexture(res: ResourceLocation) {
		Minecraft.getMinecraft().textureManager.bindTexture(res)
	}

	private fun drawTexturedModalRect(x: Int, y: Int, zLevel: Double, textureX: Int, textureY: Int, width: Int, height: Int) {
		val f = 0.00390625f
		val f1 = 0.00390625f
		val tessellator = Tessellator.getInstance()
		val vertexbuffer = tessellator.buffer
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX)
		vertexbuffer.pos((x + 0).toDouble(), (y + height).toDouble(), zLevel).tex(((textureX + 0).toFloat() * f).toDouble(), ((textureY + height).toFloat() * f1).toDouble()).endVertex()
		vertexbuffer.pos((x + width).toDouble(), (y + height).toDouble(), zLevel).tex(((textureX + width).toFloat() * f).toDouble(), ((textureY + height).toFloat() * f1).toDouble()).endVertex()
		vertexbuffer.pos((x + width).toDouble(), (y + 0).toDouble(), zLevel).tex(((textureX + width).toFloat() * f).toDouble(), ((textureY + 0).toFloat() * f1).toDouble()).endVertex()
		vertexbuffer.pos((x + 0).toDouble(), (y + 0).toDouble(), zLevel).tex(((textureX + 0).toFloat() * f).toDouble(), ((textureY + 0).toFloat() * f1).toDouble()).endVertex()
		tessellator.draw()
	}

}
