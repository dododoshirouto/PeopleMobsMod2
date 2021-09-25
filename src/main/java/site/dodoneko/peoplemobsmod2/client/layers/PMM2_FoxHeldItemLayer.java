package site.dodoneko.peoplemobsmod2.client.layers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_EndermanModel;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_FoxModel;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_RendererModel;
import site.dodoneko.peoplemobsmod2.util.PMM2_Math;

import com.mojang.blaze3d.platform.GlStateManager;

@SuppressWarnings ("deprecation") @OnlyIn(Dist.CLIENT)
public class PMM2_FoxHeldItemLayer<T extends FoxEntity> extends LayerRenderer < T, PMM2_FoxModel < T >> {

    public PMM2_FoxHeldItemLayer(IEntityRenderer < T, PMM2_FoxModel < T >> p_i50949_1_) {
        super(p_i50949_1_);
    }

    public void render(T entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
        ItemStack itemstack = entityIn.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
        if (!itemstack.isEmpty()) {
            GlStateManager.enableRescaleNormal();
            GlStateManager.pushMatrix();

            PMM2_FoxModel<T> model = this.getEntityModel();
            GlStateManager.translatef(0.0F, model.modelScale + 8F/16F*model.modelScale, 0.0F);

            boolean flag1 = entityIn.isChild();
            if (flag1) {
                GlStateManager.translatef(0.0F, 1.5F * (1F-model.modelScale) +3F/16F, 0.0F);
                GlStateManager.scalef(0.5F * model.modelScale, 0.5F * model.modelScale, 0.5F * model.modelScale);
                GlStateManager.translatef(0.0F, 24.0F * model.scaleFactor * model.modelScale, 0.0F);
            }

            
            // PMM2_HeldBlockLayerからコピペ
            PMM2_RendererModel body = model.bipedBody;
            PMM2_RendererModel arm = model.bipedRightArm;
            float armLengthY = MathHelper.abs(arm.cubeList.get(0).posY1 - arm.cubeList.get(0).posY2);
            
//            GlStateManager.translatef(
//                    body.rotationPointX/24F*model.modelScale + arm.rotationPointX/24F*model.modelScale-5.0F/24F*model.modelScale + MathHelper.sin(arm.rotateAngleY-body.rotateAngleY)*MathHelper.sin(arm.rotateAngleZ-body.rotateAngleZ)*15.0F/24F,
//                    body.rotationPointY/16F*model.modelScale + arm.rotationPointY/16F*MathHelper.cos(body.rotateAngleX)*model.modelScale + MathHelper.cos(arm.rotateAngleX+body.rotateAngleX)*MathHelper.cos(arm.rotateAngleZ+body.rotateAngleZ)*12.0F/16F*model.modelScale,
//                    body.rotationPointZ/16F*model.modelScale + arm.rotationPointZ/16F*MathHelper.sin(body.rotateAngleX)*model.modelScale + MathHelper.sin(arm.rotateAngleX+body.rotateAngleX)*12.0F/16F*model.modelScale
//                    );

            GlStateManager.translatef(
            		( arm.rotationPointX * MathHelper.cos(body.rotateAngleY) * MathHelper.cos(body.rotateAngleZ) + arm.rotationPointY * MathHelper.sin(body.rotateAngleZ) + arm.rotationPointZ * MathHelper.sin(body.rotateAngleY) )/16F*model.modelScale,
            		( arm.rotationPointY * MathHelper.cos(body.rotateAngleX) * MathHelper.cos(body.rotateAngleZ) + arm.rotationPointX * MathHelper.sin(body.rotateAngleZ) + arm.rotationPointZ * MathHelper.sin(body.rotateAngleX) )/16F*model.modelScale,
            		-( arm.rotationPointZ * MathHelper.cos(body.rotateAngleX) * MathHelper.cos(body.rotateAngleY) + arm.rotationPointX * MathHelper.sin(body.rotateAngleY) + arm.rotationPointY * MathHelper.sin(body.rotateAngleX) )/16F*model.modelScale
            		);
            GlStateManager.translatef(
            		( armLengthY * MathHelper.sin(arm.rotateAngleY+body.rotateAngleY) * MathHelper.sin(arm.rotateAngleZ+body.rotateAngleZ) )/16F*model.modelScale,
            		( armLengthY * MathHelper.cos(arm.rotateAngleX+body.rotateAngleX) * MathHelper.cos(arm.rotateAngleZ+body.rotateAngleZ) )/16F*model.modelScale,
            		-( armLengthY * MathHelper.sin(arm.rotateAngleX+body.rotateAngleX) * MathHelper.sin(arm.rotateAngleY+body.rotateAngleY) )/16F*model.modelScale
            		);

            GlStateManager.rotatef( (arm.rotateAngleX+body.rotateAngleX)*PMM2_Math.Rad2deg, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotatef( (arm.rotateAngleY+body.rotateAngleY)*PMM2_Math.Rad2deg, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotatef( (arm.rotateAngleZ+body.rotateAngleZ)*PMM2_Math.Rad2deg, 0.0F, 0.0F, 1.0F);
//            GlStateManager.rotatef( (arm.rotateAngleX+body.rotateAngleX)*0.3f *PMM2_Math.Rad2deg, 1.0F, 0.0F, 0.0F);
//            GlStateManager.translatef(0.25F, 0.1875F, 0.25F);

            float f = 0.5F;
            GlStateManager.scalef(-f, -f, f);

            // 元々書いてあった
//            GlStateManager.translatef(0, 1/16, 0);
//            model.bipedRightHand.setMatrix(1/16, true);
            
//            GlStateManager.translatef(4F/16F, 11F/16F, 2F/16F);


//            GlStateManager.rotatef( (arm.rotateAngleX+body.rotateAngleX)*0.3f *PMM2_Math.Rad2deg, 1.0F, 0.0F, 0.0F);
//            GlStateManager.translatef(0.25F, 0.1875F, 0.25F);

//            GlStateManager.scalef(0.01F, 0.01F, 0.01F);
            Minecraft.getInstance().getItemRenderer().renderItem(itemstack, entityIn, ItemCameraTransforms.TransformType.GROUND, false);
            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
        }
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}
