/**
 *
 */
package site.dodoneko.peoplemobsmod2.client.model;

import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.util.math.MathHelper;
import site.dodoneko.peoplemobsmod2.util.PMM2_Math;

import com.mojang.blaze3d.platform.GlStateManager;

/**
 * @author DODONEKO
 *
 */
public class PMM2_SquidModel<T extends SquidEntity> extends PMM2_BipedModel<T>
{
    public float partialTicks = 0.0F;

    public PMM2_SquidModel(float modelSize)
    {
        super();
        this.modelScale = 0.8F;
        this.boobHeight = 0.2F;
        this.doWalkBounding = false;
        this.boobsSwing = false;
    }

    @Override
    public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GlStateManager.pushMatrix();
        
        GlStateManager.translatef(0f, 0.8f, -0.8f);
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        GlStateManager.popMatrix();
    }

    @Override
    protected void setSwimmingAnimations()
    {
        float f1 = Math.min(1F, (float)Math.sqrt( Math.pow(entityIn.posX-entityIn.lastTickPosX,2)+Math.pow(entityIn.posY-entityIn.lastTickPosY,2)+Math.pow(entityIn.posZ-entityIn.lastTickPosZ,2) )*100F);
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(this.ageInTicks * 0.6F) * -0.5F + 0.2F + 0.4F*(1-f1);
        this.bipedLeftLeg .rotateAngleX = MathHelper.cos(this.ageInTicks * 0.6F + PMM2_Math.PI) * -0.5F + 0.2F + 0.4F*(1-f1);
        this.bipedRightLeg.rotateAngleZ =  0.05F;
        this.bipedLeftLeg .rotateAngleZ = -0.05F;

        this.bipedRightArm.rotateAngleZ = MathHelper.cos(this.ageInTicks * 0.45F) * (0.3F + 0.8F*f1) + 1 + 0.3F*f1;
        this.bipedLeftArm .rotateAngleZ = MathHelper.cos(this.ageInTicks * 0.45F + PMM2_Math.PI) * (0.3F + 0.8F*f1) - 1 - 0.3F*f1;
        this.bipedRightArm.rotateAngleX = (MathHelper.cos(this.ageInTicks * 0.45F) * -0.8F - 1) * (1-f1);
        this.bipedLeftArm.rotateAngleX = (MathHelper.cos(this.ageInTicks * 0.45F) * -0.8F - 1) * (1-f1);

        this.bipedBody.rotateAngleX = 0.2F + 1.1F*f1;
    }

    @Override
    protected void setWalkingAnimations() {
        if (!this.entityIn.isInWater()) {

        }
    }
}
