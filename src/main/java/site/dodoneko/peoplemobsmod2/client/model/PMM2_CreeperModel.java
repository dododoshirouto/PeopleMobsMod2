package site.dodoneko.peoplemobsmod2.client.model;

import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.util.math.MathHelper;
import site.dodoneko.peoplemobsmod2.util.PMM2_Math;

public class PMM2_CreeperModel<T extends CreeperEntity> extends PMM2_BipedModel<T>
{

    public PMM2_CreeperModel(float modelSize)
    {
        super(modelSize, 0.0F, 64, 64);
        this.modelScale = 0.8F;
        this.boobHeight = 0.35F;
    }

    @Override
    protected void setPostAnimations() {

        if ( entityIn.hasIgnited() || entityIn.getCreeperState()>0 ) {
            this.bipedRightArm.rotateAngleZ = -15f * PMM2_Math.Deg2Rad;
            this.bipedLeftArm.rotateAngleZ  =  15f * PMM2_Math.Deg2Rad;
            this.bipedRightArm.rotateAngleX = -20f * PMM2_Math.Deg2Rad;
            this.bipedLeftArm.rotateAngleX  = -20f * PMM2_Math.Deg2Rad;
            this.bipedRightArm.rotationPointZ = -1.5f;
            this.bipedLeftArm.rotationPointZ  = -1.5f;

            this.bipedRightLeg.rotateAngleZ = -2f * PMM2_Math.Deg2Rad;
            this.bipedLeftLeg.rotateAngleZ  =  2f * PMM2_Math.Deg2Rad;
            this.bipedRightLeg.rotateAngleX = -0.2F * PMM2_Math.PI;
            this.bipedLeftLeg.rotateAngleX  = -0.2F * PMM2_Math.PI;

            this.bipedBody.rotateAngleX  = 0.07F * PMM2_Math.PI;
            this.bipedHead.rotateAngleY  = (MathHelper.sin(ageInTicks/5) * 0.1F * PMM2_Math.PI);
            this.bipedHead.rotateAngleX  = 0.09F * PMM2_Math.PI;

            this.bipedLeftEyelid.rotationPointZ = this.bipedRightEyelid.rotationPointZ = 0F;
            this.bipedLeftEyelid.rotationPointY = this.bipedRightEyelid.rotationPointY = 0F;

//            this.setBoobsAnimations();
//            this.setAhogeAnimations();
//            return true;
        }

//        Logger.getGlobal().info((creeper.getAttackTarget()!=null?creeper.getAttackTarget().toString(): "Null."));


        // not work...
        if ( this.isAggressive ) {
            this.bipedRightArm.rotateAngleX = MathHelper.cos(this.limbSwing * 2.6648F - 1.4F) * 0.15F - 1.5F;
            this.bipedLeftArm.rotateAngleX  = MathHelper.cos(this.limbSwing * 2.6648F - 1.4F) * 0.15F - 1.5F;
            this.bipedRightArm.rotationPointY = 2.5F + MathHelper.cos(this.limbSwing * 2.6648F - 0.7F) * 0.5F + 0.5F;
            this.bipedLeftArm.rotationPointY  = 2.5F + MathHelper.cos(this.limbSwing * 2.6648F - 0.7F) * 0.5F + 0.5F;
        }

//        return false;
    }
}
