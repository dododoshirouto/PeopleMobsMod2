/**
 *
 */
package site.dodoneko.peoplemobsmod2.client.model;

import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.util.math.MathHelper;

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
        
        this.bipedBody.showModel = false;
        this.bipedBodywear.showModel = false;
    }

    @Override
    public void setAddAnimations() {

        if (this.entityIn.isCrouching() && this.entityIn.onGround) {
            // ������Œn�ʂ̂ɂ�����ǐ�
            this.setSneakAnimations();
//            this.bipedHead.rotateAngleX += -0.28F;
            this.bipedHead.rotateAngleY = MathHelper.sin(this.ageInTicks*0.28F) * 0.6F;
            this.bipedShippo.rotateAngleY = MathHelper.sin(this.ageInTicks*0.8F) * 0.32F;
            this.bipedLeftEyelid.rotationPointZ = this.bipedRightEyelid.rotationPointZ = 0F;
            this.bipedLeftEyelid.rotationPointY = this.bipedRightEyelid.rotationPointY = 0F;
        } else if (this.entityIn.isSleeping()) {

        } else if (this.entityIn.isSitting()) {

        } else if (this.entityIn.func_213472_dX()) {
            // ��W�����v���H�i�����؁j

        }
    }

    @Override
    public void setPostAnimations() {
        if (this.entityIn.isCrouching() && this.entityIn.onGround) {
            // ������Œn�ʂ̂ɂ�����ǐ�
            this.bipedLeftEyelid.rotationPointZ = this.bipedRightEyelid.rotationPointZ = 0F;
            this.bipedLeftEyelid.rotationPointY = this.bipedRightEyelid.rotationPointY = 0F;
        }
    }
    
    // TODO: �������ĂĂ��r�̐U����͕��i�ʂ��
}
