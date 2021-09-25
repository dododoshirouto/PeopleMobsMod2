/**
 *
 */
package site.dodoneko.peoplemobsmod2.client.model;

import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.math.MathHelper;

/**
 * @author DODONEKO
 *
 */
public class PMM2_ChickenModel<T extends ChickenEntity> extends PMM2_BipedModel<T>
{
    public PMM2_ChickenModel(float modelSize)
    {
        super();
        this.boobHeight = 0.1F;
        this.modelScale = 0.45F;
        this.isChild = true;
        this.childHeadwear = true;
    }

    @Override
    protected void setJumpAnimations()
    {
//        Logger.getGlobal().info(""+this.entityMotion.y);
        this.bipedRightArm.rotateAngleZ =  MathHelper.cos(this.entityIn.wingRotation) * 1F + 1.57F;
        this.bipedLeftArm .rotateAngleZ = -MathHelper.cos(this.entityIn.wingRotation) * 1F - 1.57F;
        this.bipedRightLeg.rotateAngleX = (float) (-this.entityMotion.y*5F) * 0.785F;
        this.bipedLeftLeg .rotateAngleX = (float) (-this.entityMotion.y*5F) * 0.785F;
        this.bipedRightLeg.rotateAngleY = -(float) (-this.entityMotion.y*5F) * 0.392F;
        this.bipedLeftLeg .rotateAngleY =  (float) (-this.entityMotion.y*5F) * 0.392F;
    }
}
