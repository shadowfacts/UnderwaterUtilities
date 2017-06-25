package net.shadowfacts.underwaterutilities.model

import net.minecraft.client.model.ModelBiped
import net.minecraft.client.model.ModelRenderer
import net.minecraft.entity.Entity

/**
 * @author shadowfacts
 */
object ModelScubaTank: ModelBiped() {

	private val shape9: ModelRenderer

	init {
		this.textureWidth = 64
		this.textureHeight = 32
		this.shape9 = ModelRenderer(this, 0, 16)
		this.shape9.setRotationPoint(-2.0f, 0.0f, 2.0f)
		this.shape9.addBox(0.0f, 0.0f, 0.0f, 4, 8, 4, 0.0f)
	}

	override fun render(entity: Entity, f: Float, f1: Float, f2: Float, f3: Float, f4: Float, f5: Float) {
		this.shape9.render(f5)
	}

}
