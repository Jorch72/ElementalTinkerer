/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 10 Apr 2013
package vazkii.tinkerer.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import vazkii.tinkerer.reference.MiscReference;

/**
 * ModelElementalDesk
 *
 * The Model for the Elementium Cogwheels (Generated by techne)
 *
 * @author Vazkii
 */
public class ModelCogwheel extends ModelBase {

	public static final ModelCogwheel INSTANCE = new ModelCogwheel();
	
	ModelRenderer GearPiece1,
				  GearPiece2,
				  GearPiece3,
				  GearPiece4,
				  GearPiece5,
				  GearPiece6,
				  GearPiece7,
				  GearPiece8,
				  GearInside,
				  Dent1,
				  Dent2,
				  Dent3,
				  Dent4,
				  Dent5,
				  Dent6,
				  Dent7,
				  Dent8;

	private ModelCogwheel() {
		textureWidth = 64;
		textureHeight = 32;

		GearPiece1 = new ModelRenderer(this, 0, 0);
		GearPiece1.addBox(-0.4F, 0F, 0.4F, 2, 2, 4);
		GearPiece1.setRotationPoint(-5F, 22F, -3F);
		GearPiece1.setTextureSize(64, 32);
		GearPiece1.mirror = true;
		setRotation(GearPiece1, 0F, 0F, 0F);
		GearPiece2 = new ModelRenderer(this, 0, 0);
		GearPiece2.addBox(0.8F, 0F, 0F, 2, 2, 5);
		GearPiece2.setRotationPoint(-6F, 22F, 2F);
		GearPiece2.setTextureSize(64, 32);
		GearPiece2.mirror = true;
		setRotation(GearPiece2, 0F, 0.7853982F, 0F);
		GearPiece3 = new ModelRenderer(this, 0, 0);
		GearPiece3.addBox(0F, 0F, 0F, 2, 2, 4);
		GearPiece3.setRotationPoint(-2F, 22F, 5F);
		GearPiece3.setTextureSize(64, 32);
		GearPiece3.mirror = true;
		setRotation(GearPiece3, 0F, 1.570796F, 0F);
		GearPiece4 = new ModelRenderer(this, 0, 0);
		GearPiece4.addBox(-0.1333333F, 0F, 0F, 2, 2, 5);
		GearPiece4.setRotationPoint(2F, 22F, 5F);
		GearPiece4.setTextureSize(64, 32);
		GearPiece4.mirror = true;
		setRotation(GearPiece4, 0F, 2.356194F, 0F);
		GearPiece5 = new ModelRenderer(this, 0, 0);
		GearPiece5.addBox(-0.4F, 0F, -0.4F, 2, 2, 4);
		GearPiece5.setRotationPoint(4F, 22F, -2F);
		GearPiece5.setTextureSize(64, 32);
		GearPiece5.mirror = true;
		setRotation(GearPiece5, 0F, 0F, 0F);
		GearPiece6 = new ModelRenderer(this, 0, 0);
		GearPiece6.addBox(0.1F, 0F, 0F, 2, 2, 5);
		GearPiece6.setRotationPoint(-2F, 22F, -6F);
		GearPiece6.setTextureSize(64, 32);
		GearPiece6.mirror = true;
		setRotation(GearPiece6, 0F, -0.7853982F, 0F);
		GearPiece7 = new ModelRenderer(this, 0, 0);
		GearPiece7.addBox(0F, 0F, 0F, 2, 2, 4);
		GearPiece7.setRotationPoint(-2F, 22F, -4F);
		GearPiece7.setTextureSize(64, 32);
		GearPiece7.mirror = true;
		setRotation(GearPiece7, 0F, 1.570796F, 0F);
		GearPiece8 = new ModelRenderer(this, 0, 0);
		GearPiece8.addBox(0.7F, 0F, 0F, 2, 2, 5);
		GearPiece8.setRotationPoint(6F, 22F, -3F);
		GearPiece8.setTextureSize(64, 32);
		GearPiece8.mirror = true;
		setRotation(GearPiece8, 0F, -2.356194F, 0F);
		GearInside = new ModelRenderer(this, 14, 0);
		GearInside.addBox(0F, 0F, -0.5F, 6, 2, 6);
		GearInside.setRotationPoint(-3F, 22F, -3F);
		GearInside.setTextureSize(64, 32);
		GearInside.mirror = true;
		setRotation(GearInside, 0F, 0F, 0F);
		Dent1 = new ModelRenderer(this, 0, 0);
		Dent1.addBox(-0.5F, 0F, 0F, 3, 2, 2);
		Dent1.setRotationPoint(-1F, 22F, 5F);
		Dent1.setTextureSize(64, 32);
		Dent1.mirror = true;
		setRotation(Dent1, 0F, 0F, 0F);
		Dent2 = new ModelRenderer(this, 0, 0);
		Dent2.addBox(-0.1F, 0F, 0.9F, 3, 2, 2);
		Dent2.setRotationPoint(-5F, 22F, 6F);
		Dent2.setTextureSize(64, 32);
		Dent2.mirror = true;
		setRotation(Dent2, 0F, 2.356194F, 0F);
		Dent3 = new ModelRenderer(this, 0, 0);
		Dent3.addBox(0.5F, 0F, 0F, 3, 2, 2);
		Dent3.setRotationPoint(-2F, 22F, -8F);
		Dent3.setTextureSize(64, 32);
		Dent3.mirror = true;
		setRotation(Dent3, 0F, 0F, 0F);
		Dent4 = new ModelRenderer(this, 0, 0);
		Dent4.addBox(-1F, 0F, 5F, 3, 2, 2);
		Dent4.setRotationPoint(0F, 22F, 0F);
		Dent4.setTextureSize(64, 32);
		Dent4.mirror = true;
		setRotation(Dent4, 0F, 1.570796F, 0F);
		Dent5 = new ModelRenderer(this, 0, 0);
		Dent5.addBox(-1F, 0F, 0F, 3, 2, 2);
		Dent5.setRotationPoint(-7F, 22F, 0F);
		Dent5.setTextureSize(64, 32);
		Dent5.mirror = true;
		setRotation(Dent5, 0F, 1.570796F, 0F);
		Dent6 = new ModelRenderer(this, 0, 0);
		Dent6.addBox(-0.2F, 0F, 0F, 3, 2, 2);
		Dent6.setRotationPoint(3F, 22F, 4F);
		Dent6.setTextureSize(64, 32);
		Dent6.mirror = true;
		setRotation(Dent6, 0F, 0.7853982F, 0F);
		Dent7 = new ModelRenderer(this, 0, 0);
		Dent7.addBox(0.5333334F, 0F, 0F, 3, 2, 2);
		Dent7.setRotationPoint(5F, 22F, -3F);
		Dent7.setTextureSize(64, 32);
		Dent7.mirror = true;
		setRotation(Dent7, 0F, 2.391101F, 0F);
		Dent8 = new ModelRenderer(this, 0, 0);
		Dent8.addBox(0.1F, 0F, -0.4F, 3, 2, 2);
		Dent8.setRotationPoint(-6F, 22F, -4F);
		Dent8.setTextureSize(64, 32);
		Dent8.mirror = true;
		setRotation(Dent8, 0F, 0.7853982F, 0F);
	}

	/** Renders the model, must be translated previously **/
	public void render() {
		GearPiece1.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
		GearPiece2.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
		GearPiece3.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
		GearPiece4.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
		GearPiece5.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
		GearPiece6.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
		GearPiece7.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
		GearPiece8.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
		GearInside.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
		Dent1.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
		Dent2.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
		Dent3.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
		Dent4.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
		Dent5.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
		Dent6.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
		Dent7.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
		Dent8.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
