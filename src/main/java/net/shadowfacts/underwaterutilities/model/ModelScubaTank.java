package net.shadowfacts.underwaterutilities.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nonnull;

/**
 * @author shadowfacts
 */
public class ModelScubaTank extends ModelBiped {

	public static ModelScubaTank instance = new ModelScubaTank();

	private ModelRenderer shape9;

	private ModelScubaTank() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.shape9 = new ModelRenderer(this, 0, 16);
		this.shape9.setRotationPoint(-2.0F, 0.0F, 2.0F);
		this.shape9.addBox(0.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
	}

	@Override
	public void render(@Nonnull Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.shape9.render(f5);
	}

}
