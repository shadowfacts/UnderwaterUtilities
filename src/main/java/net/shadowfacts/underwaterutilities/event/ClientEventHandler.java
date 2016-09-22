package net.shadowfacts.underwaterutilities.event;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.shadowfacts.underwaterutilities.UUCapabilities;
import net.shadowfacts.underwaterutilities.UnderwaterUtilities;
import net.shadowfacts.shadowmc.oxygen.OxygenCaps;
import net.shadowfacts.shadowmc.oxygen.OxygenHandler;

/**
 * @author shadowfacts
 */
public class ClientEventHandler {

	private static final ResourceLocation TEXTURE = new ResourceLocation(UnderwaterUtilities.modId, "textures/gui/hud.png");

	@SubscribeEvent
	public void onRenderWaterOverlay(RenderBlockOverlayEvent event) {
		if (event.getOverlayType() == RenderBlockOverlayEvent.OverlayType.WATER) {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			ItemStack helmet = player.inventory.armorItemInSlot(3);
			if (helmet != null && helmet.hasCapability(UUCapabilities.GOGGLES, null)) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onRenderAir(RenderGameOverlayEvent.Pre event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.AIR) {

			EntityPlayer player = Minecraft.getMinecraft().thePlayer;

			if (player.isInsideOfMaterial(Material.WATER)) {

				ItemStack stack = player.inventory.armorItemInSlot(2);
				if (stack != null && stack.hasCapability(OxygenCaps.HANDLER, EnumFacing.NORTH)) {
					event.setCanceled(true);

					GlStateManager.enableBlend();
					ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
					int left = res.getScaledWidth() / 2 + 91;
					int top = res.getScaledHeight() - GuiIngameForge.right_height;

					OxygenHandler handler = stack.getCapability(OxygenCaps.HANDLER, EnumFacing.NORTH);
					int full = MathHelper.ceiling_double_int((double)(handler.getStored() - 2) * 10.0D / (double)handler.getCapacity());
					int partial = MathHelper.ceiling_double_int((double)handler.getStored() * 10.0D / (double)handler.getCapacity()) - full;

					for (int i = 0; i < full + partial; i++) {
						bindTexture(TEXTURE);
						drawTexturedModalRect(left - i * 8 - 9, top, 0, (i < full ? 0 : 9), 0, 9, 9);
					}
					GuiIngameForge.right_height += 10;

					GlStateManager.disableBlend();

				}

			}

		}
	}

	private static void bindTexture(ResourceLocation res) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(res);
	}

	private static void drawTexturedModalRect(int x, int y, double zLevel, int textureX, int textureY, int width, int height) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		vertexbuffer.pos((double)(x + 0), (double)(y + height), zLevel).tex((double)((float)(textureX + 0) * f), (double)((float)(textureY + height) * f1)).endVertex();
		vertexbuffer.pos((double)(x + width), (double)(y + height), zLevel).tex((double)((float)(textureX + width) * f), (double)((float)(textureY + height) * f1)).endVertex();
		vertexbuffer.pos((double)(x + width), (double)(y + 0), zLevel).tex((double)((float)(textureX + width) * f), (double)((float)(textureY + 0) * f1)).endVertex();
		vertexbuffer.pos((double)(x + 0), (double)(y + 0), zLevel).tex((double)((float)(textureX + 0) * f), (double)((float)(textureY + 0) * f1)).endVertex();
		tessellator.draw();
	}

}
