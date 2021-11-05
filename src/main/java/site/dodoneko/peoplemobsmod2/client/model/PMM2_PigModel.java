/**
 *
 */
package site.dodoneko.peoplemobsmod2.client.model;

import net.minecraft.entity.MobEntity;

/**
 * @author DODONEKO
 *
 */
public class PMM2_PigModel<T extends MobEntity> extends PMM2_BipedModel<T>
{
    public PMM2_PigModel(float modelSize)
    {
        super(modelSize);
        this.boobHeight = 0.7F;
        this.modelScale = 0.6F;
    }
}
