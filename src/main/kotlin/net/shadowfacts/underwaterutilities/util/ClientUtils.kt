package net.shadowfacts.underwaterutilities.util

import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats

/**
 * @author shadowfacts
 */
fun drawTexturedModalRect(x: Int, y: Int, zLevel: Double, textureX: Int, textureY: Int, width: Int, height: Int) {
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