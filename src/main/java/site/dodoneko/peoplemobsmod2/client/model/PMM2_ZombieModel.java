package site.dodoneko.peoplemobsmod2.client.model;

import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.math.MathHelper;
import site.dodoneko.peoplemobsmod2.util.PMM2_Math;

public class PMM2_ZombieModel<T extends ZombieEntity> extends PMM2_BipedModel<T>
{
    public PMM2_ZombieModel(float modelSize)
    {
        super(modelSize, 0.0F, 64, 64);
        boobHeight = 0.2F;
        modelScale = 0.9F;
    }
    public PMM2_ZombieModel(float modelSize, int textureWidth, int textureHeight)
    {
        super(modelSize, 0.0F, textureWidth, textureHeight);
        boobHeight = 0.2F;
        modelScale = 0.9F;
    }


    @Override
    protected void setWalkingAnimations()
    {
        super.setWalkingAnimations();
        this.bipedRightArm.rotateAngleX = MathHelper.cos(this.limbSwing * 2.6648F - 1.4F) * 0.15F - 1.5F;
        this.bipedLeftArm.rotateAngleX  = MathHelper.cos(this.limbSwing * 2.6648F - 1.4F) * 0.15F - 1.5F;
        this.bipedRightArm.rotationPointY = 2.5F + MathHelper.cos(this.limbSwing * 2.6648F - 0.7F) * 0.25F + -0.25F;
        this.bipedLeftArm.rotationPointY  = 2.5F + MathHelper.cos(this.limbSwing * 2.6648F - 0.7F) * 0.25F + -0.25F;
    }

    @Override
    protected void setJumpAnimations()
    {
        super.setJumpAnimations();
        float fallingSpeed = MathHelper.clamp((float)entityMotion.y, -1.0F, 1.0F);
        this.bipedRightArm.rotateAngleX = -(80F - 30F*fallingSpeed) * PMM2_Math.Deg2Rad;
        this.bipedLeftArm.rotateAngleX  = -(80F - 30F*fallingSpeed) * PMM2_Math.Deg2Rad;
        this.bipedRightArm.rotateAngleZ = -MathHelper.cos(this.ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ  =  MathHelper.cos(this.ageInTicks * 0.09F) * 0.05F - 0.05F;
    }

    @Override
    protected void setStayAnimations()
    {
        this.bipedRightArm.rotateAngleY =  MathHelper.cos(this.ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleY  = -MathHelper.cos(this.ageInTicks * 0.09F) * 0.05F - 0.05F;
        this.bipedRightArm.rotateAngleX =  MathHelper.sin(this.ageInTicks * 0.067F) * 0.05F - 1.5F;
        this.bipedLeftArm.rotateAngleX  = -MathHelper.sin(this.ageInTicks * 0.067F) * 0.05F - 1.5F;
    }
}
