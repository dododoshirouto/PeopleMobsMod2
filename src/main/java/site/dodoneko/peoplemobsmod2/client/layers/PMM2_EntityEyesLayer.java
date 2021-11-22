package site.dodoneko.peoplemobsmod2.client.layers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_BipedModel;
import site.dodoneko.peoplemobsmod2.client.renderer.PMM2_BipedRenderer;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;

@OnlyIn(Dist.CLIENT)
public class PMM2_EntityEyesLayer<T extends MobEntity, M extends PMM2_BipedModel<T>, R extends PMM2_BipedRenderer<T, M>> extends LayerRenderer<T, M> {
	protected R renderer;

	public PMM2_EntityEyesLayer(R renderer) {
		super(renderer);
		renderer = (R) renderer;
	}
	
	private static final ResourceLocation ENTITY_EYES_TEXTURE = new ResourceLocation("textures/entity/sample-map.png");
	protected ResourceLocation GET_EYES_TEXTURE() {
		return ENTITY_EYES_TEXTURE;
	}

	public void render(T entityIn, float limbSwing, float limbSwingAmount, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_)
	{
		this.renderer.bindTexture(GET_EYES_TEXTURE());
		GlStateManager.enableBlend();
		GlStateManager.disableAlphaTest();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
		GlStateManager.disableLighting();
		GlStateManager.depthMask(!entityIn.isInvisible());
		GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, 61680.0F, 0.0F);
		GlStateManager.enableLighting();
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		@SuppressWarnings("resource")
		GameRenderer gamerenderer = Minecraft.getInstance().gameRenderer;
		M model = renderer.getEntityModel();

		GlStateManager.pushMatrix();

        limbSwing *= model.walkSpeed + ((model.isChild || model.useChildModel)? 0.2F: 0.0F);
        if (model.doWalkBounding)
        {
            GlStateManager.translatef(0.0F, MathHelper.abs(MathHelper.cos(limbSwing * 1.3314F)) * limbSwingAmount * model.modelScale * 0.0625F * 4 - 0.0625F * 2 * limbSwingAmount * ((model.isChild || model.useChildModel)? 0.5F: 1F), 0.0F);
        }
        
		if (model.isChild || model.useChildModel)
		{
			model.bipedHeadwear.isHidden = true;
			model.bipedLowerBoob.isHidden = true;
			model.bipedUpperBoob.isHidden = true;
			model.bipedLowerBoobwear.isHidden = true;
			model.bipedUpperBoobwear.isHidden = true;

			GlStateManager.translatef(0.0F, 1.5F * (1F-model.modelScale) +3F/16F, 0.0F);
			GlStateManager.scalef(0.75F * model.modelScale, 0.75F * model.modelScale, 0.75F * model.modelScale);
			GlStateManager.translatef(0.0F, 16.0F * model.scaleFactor * model.modelScale, 0.0F);
			model.bipedHead.render(model.scaleFactor, model.entityIn.hashCode());
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.translatef(0.0F, 1.5F * (1F-model.modelScale) +3F/16F, 0.0F);
			GlStateManager.scalef(0.5F * model.modelScale, 0.5F * model.modelScale, 0.5F * model.modelScale);
			GlStateManager.translatef(0.0F, 24.0F * model.scaleFactor * model.modelScale, 0.0F);
			model.bipedBody.render(model.scaleFactor, model.entityIn.hashCode());
		}
		else
		{
			GlStateManager.translatef(0.0F, -1.5F * model.modelScale + 1.5F, 0.0F);
			GlStateManager.scalef(model.modelScale, model.modelScale, model.modelScale);

			model.bipedHeadwear.isHidden = false;
			if (model.boobHeight > 0.000012)
			{
				if (model.boobHeight < 0.999996F)
				{

					model.bipedLowerBoob.isHidden = false;
					model.bipedUpperBoob.isHidden = false;
					model.bipedLowerBoobwear.isHidden = false;
					model.bipedUpperBoobwear.isHidden = false;
				}
				else {
					model.bipedLowerBoob.isHidden = true;
					model.bipedUpperBoob.isHidden = false;
					model.bipedLowerBoobwear.isHidden = true;
					model.bipedUpperBoobwear.isHidden = false;
				}
			}
			else {
				model.bipedLowerBoob.isHidden = true;
				model.bipedUpperBoob.isHidden = true;
				model.bipedLowerBoobwear.isHidden = true;
				model.bipedUpperBoobwear.isHidden = true;
			}

			model.bipedHead.render(model.scaleFactor, model.entityId, false);
			model.bipedBody.render(model.scaleFactor, model.entityId, false);
		}
		GlStateManager.popMatrix();

		gamerenderer.setupFogColor(false);
		this.func_215334_a(entityIn);
		GlStateManager.depthMask(true);
		GlStateManager.disableBlend();
		GlStateManager.enableAlphaTest();
	}

	public boolean shouldCombineTextures() {
		return false;
	}
}
