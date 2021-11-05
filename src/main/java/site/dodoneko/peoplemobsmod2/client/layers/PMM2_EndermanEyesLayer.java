package site.dodoneko.peoplemobsmod2.client.layers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_EndermanModel;
import site.dodoneko.peoplemobsmod2.client.renderer.PMM2_EndermanRenderer;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;

@OnlyIn(Dist.CLIENT)
public class PMM2_EndermanEyesLayer<T extends EndermanEntity> extends LayerRenderer<T, PMM2_EndermanModel<T>> {
   private static final ResourceLocation RES_ENDERMAN_EYES = new ResourceLocation("textures/entity/enderman/enderman-chan_eyes.png");
   private PMM2_EndermanRenderer<T> renderer;

   public PMM2_EndermanEyesLayer(IEntityRenderer<T, PMM2_EndermanModel<T>> p_i50939_1_) {
      super(p_i50939_1_);
      renderer = (PMM2_EndermanRenderer<T>) p_i50939_1_;
   }

   public void render(T entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_)
   {
       this.renderer.bindTexture(RES_ENDERMAN_EYES);
       GlStateManager.enableBlend();
       GlStateManager.disableAlphaTest();
       GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
       GlStateManager.disableLighting();
       GlStateManager.depthMask(!entityIn.isInvisible());
//       int i = 61680;
//       int j = 61680;
//       int k = 0;
       GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, 61680.0F, 0.0F);
       GlStateManager.enableLighting();
       GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
       @SuppressWarnings("resource")
	GameRenderer gamerenderer = Minecraft.getInstance().gameRenderer;
       PMM2_EndermanModel<T> model = renderer.getEntityModel();
//       renderer.getEntityModel().CopyAll(model);
//       renderer.getEntityModel().render(entityIn, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);

       GlStateManager.pushMatrix();
       if (model.isChild)
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
