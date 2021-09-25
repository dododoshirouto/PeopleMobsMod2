package site.dodoneko.peoplemobsmod2.client.layers;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_EndermanModel;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_RendererModel;
import site.dodoneko.peoplemobsmod2.util.PMM2_Math;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;

@OnlyIn(Dist.CLIENT)
public class PMM2_HeldBlockLayer < T extends EndermanEntity > extends LayerRenderer < T, PMM2_EndermanModel < T >> {


    public PMM2_HeldBlockLayer(IEntityRenderer < T, PMM2_EndermanModel < T >> p_i50949_1_) {
        super(p_i50949_1_);
    }

    public void render(T entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
        BlockState blockstate = entityIn.getHeldBlockState();
        if (blockstate != null) {
            GlStateManager.enableRescaleNormal();
            GlStateManager.pushMatrix();

//            GlStateManager.translatef(0.0F, 0.6875F, -0.75F);
            PMM2_EndermanModel<T> model = this.getEntityModel();
            PMM2_RendererModel body = model.bipedBody;
            PMM2_RendererModel arm = model.bipedRightArm;


            GlStateManager.translatef(
                    0F, //body.rotationPointX/24F + arm.rotationPointX/24F-5.0F/24F + MathHelper.sin(arm.rotateAngleY-body.rotateAngleY)*MathHelper.sin(arm.rotateAngleZ-body.rotateAngleZ)*15.0F/24F,
                    body.rotationPointY/16F + arm.rotationPointY/16F*MathHelper.cos(body.rotateAngleX) + MathHelper.cos(arm.rotateAngleX+body.rotateAngleX)*MathHelper.cos(arm.rotateAngleZ+body.rotateAngleZ)*12.0F/16F,
                    body.rotationPointZ/16F + arm.rotationPointZ/16F*MathHelper.sin(body.rotateAngleX) + MathHelper.sin(arm.rotateAngleX+body.rotateAngleX)*12.0F/16F
                    );


            GlStateManager.rotatef( (arm.rotateAngleX+body.rotateAngleX)*0.3f *PMM2_Math.Rad2deg, 1.0F, 0.0F, 0.0F);
//            GlStateManager.rotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translatef(0.25F, 0.1875F, 0.25F);

            float f = 0.5F;
            GlStateManager.scalef(-f, -f, f);

            int i = entityIn.getBrightnessForRender();
            int j = i % 65536;
            int k = i / 65536;
            GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) j, (float) k);
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
            Minecraft.getInstance().getBlockRendererDispatcher().renderBlockBrightness(blockstate, 1.0F);

            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
        }
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}
