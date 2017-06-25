package net.shadowfacts.underwaterutilities.event

import net.minecraft.block.material.Material
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.MathHelper
import net.minecraftforge.client.GuiIngameForge
import net.minecraftforge.client.event.EntityViewRenderEvent
import net.minecraftforge.client.event.RenderBlockOverlayEvent
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.minecraftforge.fml.relauncher.Side
import net.shadowfacts.shadowmc.oxygen.OxygenCaps
import net.shadowfacts.underwaterutilities.MOD_ID
import net.shadowfacts.underwaterutilities.UUCapabilities
import net.shadowfacts.underwaterutilities.item.UUItems
import net.shadowfacts.underwaterutilities.util.drawTexturedModalRect

/**
 * @author shadowfacts
 */
@Mod.EventBusSubscriber(Side.CLIENT, modid = MOD_ID)
object ClientEventHandler {

	private val HUD_TEXTURE = ResourceLocation(MOD_ID, "textures/gui/hud.png")
	private var gamma = 0f

	@JvmStatic
	@SubscribeEvent
	fun onRenderWaterOverlay(event: RenderBlockOverlayEvent) {
		if (event.overlayType == RenderBlockOverlayEvent.OverlayType.WATER) {
			val player = Minecraft.getMinecraft().player
			val helmet = player.inventory.armorItemInSlot(3)
			if (!helmet.isEmpty && helmet.hasCapability(UUCapabilities.GOGGLES!!, null)) {
				event.isCanceled = true
			}
		}
	}

	@JvmStatic
	@SubscribeEvent
	fun onRenderAir(event: RenderGameOverlayEvent.Pre) {
		if (event.type == RenderGameOverlayEvent.ElementType.AIR) {

			val player = Minecraft.getMinecraft().player

			if (player.isInsideOfMaterial(Material.WATER)) {

				val chestpiece = player.inventory.armorItemInSlot(2)
				val helmet = player.inventory.armorItemInSlot(3)
				if (!chestpiece.isEmpty && chestpiece.hasCapability(OxygenCaps.HANDLER, null) &&
					helmet != null && helmet.hasCapability(UUCapabilities.BREATHING_AID!!, null)) {
					event.isCanceled = true

					GlStateManager.enableBlend()
					val res = ScaledResolution(Minecraft.getMinecraft())
					val left = res.scaledWidth / 2 + 91
					val top = res.scaledHeight - GuiIngameForge.right_height

					val handler = chestpiece.getCapability(OxygenCaps.HANDLER, null)!!
					val full = MathHelper.ceil((handler.stored - 2).toDouble() * 10.0 / handler.capacity.toDouble())
					val partial = MathHelper.ceil(handler.stored.toDouble() * 10.0 / handler.capacity.toDouble()) - full

					for (i in 0..full + partial - 1) {
						Minecraft.getMinecraft().textureManager.bindTexture(HUD_TEXTURE)
						drawTexturedModalRect(left - i * 8 - 9, top, 0.0, if (i < full) 0 else 9, 0, 9, 9)
					}
					GuiIngameForge.right_height += 10

					GlStateManager.disableBlend()

				}

			}

		}
	}

	@JvmStatic
	@SubscribeEvent
	fun onRenderFog(event: EntityViewRenderEvent.FogDensity) {
		val player = event.entity
		if (player is EntityPlayer) {
			if (event.state.material == Material.WATER) {
				val helmet = player.inventory.armorItemInSlot(3)
				if (!helmet.isEmpty && helmet.item == UUItems.breather) {
					event.density = 0f
					event.isCanceled = true
				}
			}
		}
	}

	@JvmStatic
	@SubscribeEvent
	fun onRenderTick(event: TickEvent.RenderTickEvent) {
		val player = Minecraft.getMinecraft().player
		if (player != null) {
			val helmet = player.inventory.armorItemInSlot(3)
			if (Minecraft.getMinecraft().world.getBlockState(player.position.up()).material == Material.WATER &&
					!helmet.isEmpty && helmet.hasCapability(UUCapabilities.BREATHING_AID!!, null) &&
					helmet.getCapability(UUCapabilities.BREATHING_AID!!, null)!!.canBreathe(player)) {

				if (event.phase == TickEvent.Phase.START) {
					gamma = Minecraft.getMinecraft().gameSettings.gammaSetting
					Minecraft.getMinecraft().gameSettings.gammaSetting = 2.5f
				} else {
					Minecraft.getMinecraft().gameSettings.gammaSetting = gamma
				}
			}
		}
	}

}
