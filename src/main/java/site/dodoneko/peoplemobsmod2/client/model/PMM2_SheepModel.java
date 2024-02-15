/**
 *
 */
package site.dodoneko.peoplemobsmod2.client.model;

import net.minecraft.entity.passive.SheepEntity;

/**
 * @author DODONEKO
 *
 */
public class PMM2_SheepModel<T extends SheepEntity> extends PMM2_BipedModel<T>
{
    public boolean isFar = false;
    public PMM2_BipedModel<T> bodyModel = null;

    public PMM2_SheepModel()
    {
        super();
        this.modelScale = 0.8F;
        this.boobHeight = 0.63F;
    }

    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
    {
        if (entityIn.getHeadRotationPointY(partialTick) > 0) {
            this.isSneak = true;
        }
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
    }
}
