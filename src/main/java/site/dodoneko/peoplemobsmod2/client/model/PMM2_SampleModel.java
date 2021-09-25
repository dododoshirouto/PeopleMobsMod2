/**
 *
 */
package site.dodoneko.peoplemobsmod2.client.model;

import net.minecraft.entity.MobEntity;

/**
 * @author DODONEKO
 *
 */
public class PMM2_SampleModel<T extends MobEntity> extends PMM2_BipedModel<T>
{
    public PMM2_SampleModel(float modelSize)
    {
        super();
        this.modelScale = 0.8F;
        this.boobHeight = 0.5F;
    }
}
