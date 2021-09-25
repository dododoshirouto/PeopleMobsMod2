/**
 *
 */
package site.dodoneko.peoplemobsmod2.client.model;

import net.minecraft.entity.passive.BatEntity;
import net.minecraft.util.math.MathHelper;
import site.dodoneko.peoplemobsmod2.util.PMM2_Math;

import com.mojang.blaze3d.platform.GlStateManager;

/**
 * @author DODONEKO
 *
 */
public class PMM2_BatModel<T extends BatEntity> extends PMM2_BipedModel<T>
{
    public PMM2_BatModel(float modelSize)
    {
        super();
        this.modelScale = 0.4F;
        this.boobHeight = 1.0F;
        this.doWalkBounding = false;
        this.isChild = true;
        this.childBoob = true;
        this.childHeadwear = true;

        this.bipedLeftArm.easeMotion = this.bipedRightArm.easeMotion = false;
    }

    @Override
    public void setJumpAnimations() {
        return;
    }

    @Override
    public void setWalkingAnimations() {
        return;
    }

    @Override
    public void setStayAnimations() {

        if (this.entityIn.getIsBatHanging()) {
            this.bipedBody.rotateAngleX = (float) Math.PI - 0.5F;
            this.setSneakAnimations();
            this.bipedHead.rotateAngleX = this.bipedBody.rotateAngleX + 0.3F;
            this.bipedHead.rotationPointZ = 1.5F;
            this.bipedBody.rotationPointZ = 1.5F;
            this.bipedRightArm.rotateAngleZ =  15f * PMM2_Math.Deg2Rad;
            this.bipedLeftArm.rotateAngleZ  =  -15f * PMM2_Math.Deg2Rad;
            this.bipedRightArm.rotateAngleX = -130f * PMM2_Math.Deg2Rad;
            this.bipedLeftArm.rotateAngleX  = -130f * PMM2_Math.Deg2Rad;
            this.bipedRightArm.rotationPointZ = -1.5f;
            this.bipedLeftArm.rotationPointZ  = -1.5f;
        } else {

            float f1 = MathHelper.sin( this.ageInTicks * (this.limbSwingAmount<0.5F? 2.5F: 3.5F) );
            float f2 = MathHelper.cos( this.ageInTicks * (this.limbSwingAmount<0.5F? 2.5F: 3.5F) + 0.18F);

            if (this.entityIn.getMotion().y < 0.0F) {
                f1 = 0.02F;
            }

            this.bipedBody.rotateAngleX += this.limbSwingAmount*1.5F - 0.2F;
            this.bipedHead.rotateAngleX += this.limbSwingAmount*0.5F;
            this.bipedRightLeg.rotateAngleZ =  0.15F;
            this.bipedLeftLeg .rotateAngleZ = -0.15F;
            this.bipedRightLeg.rotateAngleX = this.bipedLeftLeg.rotateAngleX = PMM2_Math.lerp(-0.15F, 0.3F, this.limbSwingAmount) - f2*0.1F;
            this.bipedRightArm.rotateAngleZ =  1.57F;
            this.bipedLeftArm .rotateAngleZ = -1.57F;
            this.bipedRightArm.rotateAngleX =  (f1 * 1.0F);
            this.bipedLeftArm .rotateAngleX =  (f1 * 1.0F);
            this.bipedRightArm.rotateAngleY = -1.57F + this.limbSwingAmount * 1.2F;
            this.bipedLeftArm .rotateAngleY =  1.57F - this.limbSwingAmount * 1.2F;
        }
    }

    @Override
    public void setAddAnimations() {
    }

    @Override
    public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (entityIn.getIsBatHanging()) {
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0.0F, 1.5F * scale, 0.0F);
            super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.popMatrix();
        } else {
            super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }
}
