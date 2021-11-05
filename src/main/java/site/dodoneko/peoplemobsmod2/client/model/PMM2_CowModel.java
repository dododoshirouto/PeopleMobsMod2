/**
 *
 */
package site.dodoneko.peoplemobsmod2.client.model;

import net.minecraft.entity.MobEntity;

/**
 * @author DODONEKO
 *
 */
public class PMM2_CowModel<T extends MobEntity> extends PMM2_BipedModel<T>
{
    public PMM2_CowModel(float modelSize)
    {
        super();
        this.modelScale = 0.8F;
        this.boobHeight = 1.0F;
    }
}
