/**
 *
 */
package site.dodoneko.peoplemobsmod2.client.model;

import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.util.math.MathHelper;

/**
 * @author DODONEKO
 *
 */
public class PMM2_EndermanModel<T extends EndermanEntity> extends PMM2_BipedModel<T>
{
    public boolean isCarrying;
    public boolean isAttacking;

    public PMM2_EndermanModel(float modelSize)
    {
        super();
        this.modelScale = 1F;
        this.boobHeight = 0.2F;
        this.doWalkBounding = false;
        this.boobsSwing = false;
    }



    @Override
    public void setStayAnimations() {
        float f1 = MathHelper.sin( this.ageInTicks * 0.4f );
        float f2 = MathHelper.sin( this.ageInTicks * 0.4f - 1.2F );

        this.bipedHead.rotationPointY = -13F + f1 * 1.5F;
        this.bipedBody.rotationPointY = -13F + f1 * 1.5F;
        this.bipedBody.rotateAngleX = -0.1F + f1 * 0.03F;

        this.bipedRightArm.rotateAngleZ =  0.5F - f2 * 0.15F;
        this.bipedLeftArm .rotateAngleZ = -0.5F + f2 * 0.15F;
        this.bipedRightArm.rotateAngleX = 0.25F;
        this.bipedLeftArm .rotateAngleX = 0.25F;

        this.bipedRightLeg.rotateAngleZ =  0.15F;
        this.bipedLeftLeg .rotateAngleZ = -0.15F;
        this.bipedRightLeg.rotateAngleX = 0.3F - f2 * 0.1F;
        this.bipedLeftLeg .rotateAngleX = 0.3F - f2 * 0.1F;
    }

    @Override
    public void setWalkingAnimations() {
        this.setStayAnimations();
        this.bipedBody.rotateAngleX += this.limbSwingAmount;
        this.bipedRightArm.rotateAngleX -= this.limbSwingAmount * 1.0F;
        this.bipedLeftArm .rotateAngleX -= this.limbSwingAmount * 1.0F;
        this.bipedRightLeg.rotateAngleX -= this.limbSwingAmount * 0.2F;
        this.bipedLeftLeg .rotateAngleX -= this.limbSwingAmount * 0.2F;
    }

    @Override
    public void setAggressiveAnimations() {
        this.bipedRightArm.rotateAngleX = MathHelper.cos(this.limbSwing * 2.6648F - 1.4F) * 0.15F - 2.0F;
        this.bipedLeftArm.rotateAngleX  = MathHelper.cos(this.limbSwing * 2.6648F - 1.4F) * 0.15F - 2.0F;
        this.bipedRightLeg.rotateAngleZ =  0.3F;
        this.bipedLeftLeg .rotateAngleZ = -0.3F;
    }

    @Override
    public void setHasAnythingAnimations(ArmPose leftArmPose, ArmPose rightArmPose) {
        super.setHasAnythingAnimations(leftArmPose, rightArmPose);
        if (this.isCarrying) {
            this.bipedLeftArm .rotateAngleX = -1.0F - this.bipedBody .rotateAngleX * 0.5F;
            this.bipedRightArm.rotateAngleX = -1.0F - this.bipedBody .rotateAngleX * 0.5F;
            this.bipedLeftArm .rotateAngleY =  0.0F;
            this.bipedRightArm.rotateAngleY =  0.0F;
            this.bipedLeftArm .rotateAngleZ =  0.0F;
            this.bipedRightArm.rotateAngleZ =  0.0F;
        }
    }

    @Override
    public void setAddAnimations() {
        if (this.isAttacking) {
            this.setAggressiveAnimations();
        }
    }
}
