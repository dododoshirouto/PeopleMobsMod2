package site.dodoneko.peoplemobsmod2.client.layers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
//import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
//import site.dodoneko.peoplemobsmod2.client.model.PMM2_EndermanModel;
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
//                GlStateManager.translatef(0.0F, 1F * (1F-model.modelScale) +3F/16F, 0.0F);
//                GlStateManager.scalef(0.5F * model.modelScale, 0.5F * model.modelScale, 0.5F * model.modelScale);
                GlStateManager.translatef(0.0F, 12.0F * model.scaleFactor * model.modelScale, 0.0F);
            }

            
            PMM2_RendererModel body = model.bipedBody;
            PMM2_RendererModel arm = model.bipedRightArm;
            float pixelToScale = model.modelScale*(flag1?0.5f:1f)/16;

            GlStateManager.translatef((body.rotationPointX)*pixelToScale,(body.rotationPointY)*pixelToScale,(body.rotationPointZ)*pixelToScale);

            GlStateManager.rotatef( (body.rotateAngleY)*PMM2_Math.Rad2deg, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotatef( (body.rotateAngleX)*PMM2_Math.Rad2deg, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotatef( (body.rotateAngleZ)*PMM2_Math.Rad2deg, 0.0F, 0.0F, 1.0F);

            GlStateManager.translatef((arm.rotationPointX)*pixelToScale,(arm.rotationPointY)*pixelToScale,(arm.rotationPointZ)*pixelToScale);

            GlStateManager.rotatef( (arm.rotateAngleY)*PMM2_Math.Rad2deg, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotatef( (arm.rotateAngleX)*PMM2_Math.Rad2deg, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotatef( (arm.rotateAngleZ)*PMM2_Math.Rad2deg, 0.0F, 0.0F, 1.0F);
            
            GlStateManager.rotatef( 90F, 1.0F, 0.0F, 0.0F);
            
            GlStateManager.translatef(
                (0F)*pixelToScale,
                (-2F)*pixelToScale,
                (-8.5F)*pixelToScale
            );

            float f = 0.5F;
            GlStateManager.scalef(-f, -f, f);

            Minecraft.getInstance().getItemRenderer().renderItem(itemstack, entityIn, ItemCameraTransforms.TransformType.GROUND, false);
            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
        }
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}
