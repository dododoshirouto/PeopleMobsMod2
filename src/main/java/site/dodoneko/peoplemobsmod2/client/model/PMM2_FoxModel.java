/**
 *
 */
package site.dodoneko.peoplemobsmod2.client.model;

import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.util.math.MathHelper;
import site.dodoneko.peoplemobsmod2.util.PMM2_Math;

/**
 * @author DODONEKO
 *
 */
public class PMM2_FoxModel<T extends FoxEntity> extends PMM2_BipedModel<T>
{
    public PMM2_FoxModel(float modelSize)
    {
        super();
        this.modelScale = 0.5F;
        this.boobHeight = 0.55F;
        this.walkSpeed = 0.5F;
        
    }

    @Override
    public void setAddAnimations() {

        if (this.entityIn.isCrouching() && this.entityIn.onGround) {
            // かがんで地面のにおいを追跡
            this.setSneakAnimations();
//            this.bipedHead.rotateAngleX += -0.28F;
            this.bipedHead.rotateAngleY = MathHelper.sin(this.ageInTicks*0.28F) * 0.6F;
            this.bipedShippo.rotateAngleY = MathHelper.sin(this.ageInTicks*0.8F) * 0.32F;
            this.bipedLeftEyelid.rotationPointZ = this.bipedRightEyelid.rotationPointZ = 0F;
            this.bipedLeftEyelid.rotationPointY = this.bipedRightEyelid.rotationPointY = 0F;
            
            float temp1 = MathHelper.abs(MathHelper.sin(this.ageInTicks*0.28F)*MathHelper.sin(this.ageInTicks*0.28F*4));
            this.bipedHead.rotateAngleX += (temp1>0.5f? temp1*2-1: 0)*10*PMM2_Math.Deg2Rad;
            
        }
        if (this.entityIn.isSleeping()) {
        	System.out.println("fox is sleeping");
        }
        if (this.entityIn.isSitting()) {
        	System.out.println("fox is sitting");
        }
        if (this.entityIn.func_213472_dX()) {
        	System.out.println("fox is Fitting on snow");
        }
        if (this.entityIn.func_213480_dY()) {
        	System.out.println("fox is jumping");
        }
        if (this.entityIn.func_213490_ee()) {
        	System.out.println("fox is jumping ?");
        }
        if (this.entityIn.func_213467_eg()) {
        	System.out.println("fox is ready to jump");
        }
    }

    @Override
    public void setPostAnimations() {
        if (this.entityIn.isCrouching() && this.entityIn.onGround) {
            // かがんで地面のにおいを追跡
            this.bipedLeftEyelid.rotationPointZ = this.bipedRightEyelid.rotationPointZ = 0F;
            this.bipedLeftEyelid.rotationPointY = this.bipedRightEyelid.rotationPointY = 0F;
        }
    }
}
