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
            
            float temp1 = MathHelper.abs(MathHelper.sin(this.ageInTicks*0.28F)*MathHelper.sin(this.ageInTicks*0.28F*4));
            this.bipedHead.rotateAngleX += (temp1>0.5f? temp1*2-1: 0)*30F*PMM2_Math.Deg2Rad;
            
        }
        else if (this.entityIn.isSleeping()) {
        	System.out.println("fox is sleeping");
        	// 仮りのモーション
            this.setSittingAnimations();

            this.bipedHead.rotateAngleX += (MathHelper.sin(this.ageInTicks)*15F + MathHelper.sin(this.ageInTicks*0.12F)*7F + 8F)*PMM2_Math.Deg2Rad;
        }
        else if (this.entityIn.isSitting()) {
//        	System.out.println("fox is sitting");
            this.setSittingAnimations();
        }
        else if (this.entityIn.func_213472_dX()) {
        	System.out.println("fox is Fitting on snow");

            float fallingSpeed = 1F;
            this.bipedLeftArm.rotateAngleZ  = -(80F - 30F*fallingSpeed) * PMM2_Math.Deg2Rad;
            this.bipedRightArm.rotateAngleZ =  (80F - 30F*fallingSpeed) * PMM2_Math.Deg2Rad;
            this.bipedLeftLeg.rotateAngleY = -fallingSpeed / 1.5F;
            this.bipedLeftLeg.rotateAngleY =  fallingSpeed / 1.5F;
            this.bipedLeftLeg.rotateAngleX  = fallingSpeed * 1.3F;
            this.bipedRightLeg.rotateAngleX = fallingSpeed * 1.3F;
            
            float moveYRate = PMM2_Math.clamp( 1F - ((float)this.entityMotion.y + 0.5F) / (0.5F + 0.5F) , 0, 1);
            
            this.bipedBody.rotateAngleX += PMM2_Math.lerp(0, 180F * PMM2_Math.Deg2Rad, moveYRate );
            this.bipedHead.rotateAngleX += PMM2_Math.lerp(0, 180F * PMM2_Math.Deg2Rad, moveYRate );
        }
        else if (this.entityIn.func_213480_dY()) {
        	System.out.println("fox is jumping");
//        	System.out.println("motionY: " + this.entityMotion.y); // 6.4 .. -5.6

            float fallingSpeed = MathHelper.clamp((float)this.entityMotion.y, -1.0F, 1.0F);
            this.bipedLeftArm.rotateAngleZ  = -(80F - 30F*fallingSpeed) * PMM2_Math.Deg2Rad;
            this.bipedRightArm.rotateAngleZ =  (80F - 30F*fallingSpeed) * PMM2_Math.Deg2Rad;
            this.bipedLeftLeg.rotateAngleY = -fallingSpeed / 1.5F;
            this.bipedLeftLeg.rotateAngleY =  fallingSpeed / 1.5F;
            this.bipedLeftLeg.rotateAngleX  = fallingSpeed * 1.3F;
            this.bipedRightLeg.rotateAngleX = fallingSpeed * 1.3F;
            
            float moveYRate = PMM2_Math.clamp( 1F - ((float)this.entityMotion.y + 0.5F) / (0.5F + 0.5F) , 0, 1);
            
            this.bipedBody.rotateAngleX += PMM2_Math.lerp(0, 180F * PMM2_Math.Deg2Rad, moveYRate );
            this.bipedHead.rotateAngleX += PMM2_Math.lerp(0, 180F * PMM2_Math.Deg2Rad, moveYRate );
        }
        else if (this.entityIn.func_213490_ee()) {
//        	System.out.println("fox is jumping ?");
        }
        else if (this.entityIn.func_213467_eg()) {
        	System.out.println("fox is ready to jump");
        	// 仮りのモーション
            this.bipedBody.rotateAngleY = MathHelper.sin(this.ageInTicks*1F) * 0.42F;
            this.setSneakAnimations();
        }
    }

    @Override
    public boolean setPreAnimations() {
        if (this.entityIn.isCrouching() && this.entityIn.onGround) {
            // かがんで地面のにおいを追跡
            this.bipedLeftEyelid.rotationPointZ = this.bipedRightEyelid.rotationPointZ = 0F;
            this.bipedLeftEyelid.rotationPointY = this.bipedRightEyelid.rotationPointY = 0F;
        }
        else if (this.entityIn.isSleeping()) {
            this.bipedLeftEyelid.rotationPointZ = this.bipedRightEyelid.rotationPointZ = 0F;
            this.bipedLeftEyelid.rotationPointY = this.bipedRightEyelid.rotationPointY = 0F;
        }
        else if (this.entityIn.isSitting()) {
        }
        else if (this.entityIn.func_213472_dX()) {
        }
        else if (this.entityIn.func_213480_dY()) {
        }
        else if (this.entityIn.func_213490_ee()) {
        }
        else if (this.entityIn.func_213467_eg()) {
        	// 仮りのモーション
            this.bipedBody.rotateAngleY = MathHelper.sin(this.ageInTicks*1F) * 0.42F;
        }
        
        return false;
    }
}
