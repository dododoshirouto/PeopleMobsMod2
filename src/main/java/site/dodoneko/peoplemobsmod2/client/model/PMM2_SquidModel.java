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
        this.boobHeight = 0.3F;
        this.doWalkBounding = false;
        this.boobsSwing = false;
    }

    @Override
    public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.translatef(0f, 0.6f, -0.5f);
//        GlStateManager.rotatef(PMM2_Math.PI, 0, 1, 0);
        
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        
        GlStateManager.popMatrix();
    }

    @Override
    protected void setSwimmingAnimations()
    {
    	if (!this.entityIn.isInWater()) return;
    	
        float f1 = Math.min(1F, (float)Math.sqrt( Math.pow(entityIn.posX-entityIn.lastTickPosX,2)+Math.pow(entityIn.posY-entityIn.lastTickPosY,2)+Math.pow(entityIn.posZ-entityIn.lastTickPosZ,2) )*100F);
        float speed = (this.ageInTicks%10/10)*(this.ageInTicks%10/10)*10;
        
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(speed * 0.6F) * -0.5F + 0.2F + 0.4F*(1-f1);
        this.bipedLeftLeg .rotateAngleX = MathHelper.cos(speed * 0.6F + PMM2_Math.PI) * -0.5F + 0.2F + 0.4F*(1-f1);
        this.bipedRightLeg.rotateAngleZ =  0.05F;
        this.bipedLeftLeg .rotateAngleZ = -0.05F;

        this.bipedRightArm.rotateAngleZ = MathHelper.cos(speed * 0.45F) * (0.3F + 0.8F*f1) + 1 + 0.3F*f1;
        this.bipedLeftArm .rotateAngleZ = MathHelper.cos(speed * 0.45F + PMM2_Math.PI) * (0.3F + 0.8F*f1) - 1 - 0.3F*f1;
        this.bipedRightArm.rotateAngleX = (MathHelper.cos(speed * 0.45F) * -0.8F - 1) * (1-f1);
        this.bipedLeftArm.rotateAngleX = (MathHelper.cos(speed * 0.45F) * -0.8F - 1) * (1-f1);

        this.bipedBody.rotateAngleX = 0.2F + 1.1F*f1;
    }

    @Override
    protected void setWalkingAnimations() {
        if (this.entityIn.isInWater()) return;
        
        float speed = this.entityHurtTime > 1? this.ageInTicks*5F: (this.ageInTicks-(this.ageInTicks%50/50)*(this.ageInTicks%50/50)*(this.ageInTicks%50/50)*PMM2_Math.PI)*0.2F;

        this.bipedRightArm.rotateAngleZ =  15f * PMM2_Math.Deg2Rad;
        this.bipedLeftArm.rotateAngleZ  =  -15f * PMM2_Math.Deg2Rad;
        this.bipedRightArm.rotateAngleX = (-130f + MathHelper.sin(speed)*30+20) * PMM2_Math.Deg2Rad;
        this.bipedLeftArm.rotateAngleX  = (-130f + MathHelper.sin(speed)*30+20) * PMM2_Math.Deg2Rad;
        this.bipedRightArm.rotationPointZ = -1.5f;
        this.bipedLeftArm.rotationPointZ  = -1.5f;

        this.bipedRightLeg.rotateAngleZ = -2f * PMM2_Math.Deg2Rad;
        this.bipedLeftLeg.rotateAngleZ  =  2f * PMM2_Math.Deg2Rad;
        this.bipedRightLeg.rotateAngleX = (-12f - MathHelper.sin(speed)*20-15) * PMM2_Math.Deg2Rad;
        this.bipedLeftLeg.rotateAngleX  = (-12f - MathHelper.sin(speed)*20-15) * PMM2_Math.Deg2Rad;

        this.bipedBody.rotateAngleX  = 0.07F * PMM2_Math.PI;
        this.bipedHead.rotateAngleY  = (MathHelper.sin(this.ageInTicks/5) * 0.1F * PMM2_Math.PI);
        this.bipedHead.rotateAngleX  = (5f + MathHelper.sin(speed)*20+20) * PMM2_Math.Deg2Rad;

        this.bipedLeftEyelid.rotationPointZ = this.bipedRightEyelid.rotationPointZ = 0F;
        this.bipedLeftEyelid.rotationPointY = this.bipedRightEyelid.rotationPointY = 0F;
        
        this.bipedBody.rotationPointY += 8F;
        this.bipedHead.rotationPointY += 8F;

        this.bipedBody.rotateAngleX -= PMM2_Math.PI_Half;
        this.bipedHead.rotateAngleX -= PMM2_Math.PI_Half;
    }

    @Override
    protected void setDamagedAnimations() {
    }
    
    @Override
    protected void setDeadAnimations() {
    	super.setDeadAnimations();
    	
        this.bipedBody.rotationPointY += 8F;
        this.bipedHead.rotationPointY += 8F;
    	
        this.bipedBody.rotateAngleX -= PMM2_Math.PI_Half;
        this.bipedHead.rotateAngleX -= PMM2_Math.PI_Half;
    }
    
    @Override
    protected void setJumpAnimations() {
    	return;
    }
}
