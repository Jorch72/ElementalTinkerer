/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 25 Feb 2012
package vazkii.tinkerer.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.client.handler.ClientTickHandler;
import vazkii.tinkerer.helper.MathHelper;
import vazkii.tinkerer.reference.MiscReference;

/**
 * ModelRotating
 *
 * The Model for the Scavenger and Dislocator Blocks (Generated by techne)
 *
 * @author Vazkii
 */
public class ModelRotating extends ModelBase {

	public static final ModelRotating INSTANCE = new ModelRotating();

    ModelRenderer Base1,
   				  Base2,
    		      Pillar,
    		      SpinningCube;

  private ModelRotating() {
    textureWidth = 64;
    textureHeight = 64;

      Base1 = new ModelRenderer(this, 0, 0);
      Base1.addBox(0F, 0F, 0F, 14, 2, 14);
      Base1.setRotationPoint(-7F, 22F, -7F);
      Base1.setTextureSize(64, 64);
      Base1.mirror = true;
      Base2 = new ModelRenderer(this, 0, 16);
      Base2.addBox(0F, 0F, 0F, 12, 1, 12);
      Base2.setRotationPoint(-6F, 21F, -6F);
      Base2.setTextureSize(64, 64);
      Base2.mirror = true;
      Pillar = new ModelRenderer(this, 0, 29);
      Pillar.addBox(0F, 0F, 0F, 4, 12, 4);
      Pillar.setRotationPoint(-2F, 9F, -2F);
      Pillar.setTextureSize(64, 64);
      Pillar.mirror = true;

       // This one is weird, it's rendered multiple times with translations
      SpinningCube = new ModelRenderer(this, 42, 0);
      SpinningCube.addBox(0F, 0F, 0F, 1, 1, 1);
      SpinningCube.setRotationPoint(0F, 0F, 0F);
      SpinningCube.setTextureSize(64, 64);
      SpinningCube.mirror = true;
  }

  /** Renders the model, must be translated previously **/
  public void render() {
    Base1.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
    Base2.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
    Pillar.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);
  }

  /** Renders the animated cubes around the model, called after
   * the translations for render() **/
  public void renderSpinningCubes(int cubes) {
	  final float modifier = 6F;
	  final float rotationModifier = 0.25F;
	  final float radiusBase = 0.4F;
	  final float radiusMod = 0.1F;

	  long ticks = ClientTickHandler.elapsedClientTicks;
	  float offsetPerCube = 360 / cubes;
	  GL11.glPushMatrix();
	  GL11.glTranslatef(-0.025F, 0.85F, -0.025F);
	  for(int i = 0; i < cubes; i++) {
		  float offset = offsetPerCube * i;
		  float deg = (int) (ticks / rotationModifier % 360F + offset);
		  float rad = MathHelper.degreeToRadian(deg);
		  float radiusX = (float) (radiusBase + radiusMod * Math.sin(ticks / modifier));
		  float radiusZ = (float) (radiusBase + radiusMod * Math.cos(ticks / modifier));
		  float x =  (float) (radiusX * Math.cos(rad));
		  float z = (float) (radiusZ * Math.sin(rad));
		  float y = (float) Math.cos((ticks + (100 + i)) / 5F) / 10F;

		  GL11.glPushMatrix();
		  GL11.glTranslatef(x, y, z);
		  float xRotate = (float) Math.sin(ticks * rotationModifier) / 2F;
		  float zRotate = (float) Math.cos(ticks * rotationModifier) / 2F;

		  GL11.glRotatef(deg, xRotate, 1F, zRotate);
		  SpinningCube.render(MiscReference.MODEL_DEFAULT_RENDER_SCALE);

		  GL11.glPopMatrix();
	  }
	  GL11.glPopMatrix();
  }

}
