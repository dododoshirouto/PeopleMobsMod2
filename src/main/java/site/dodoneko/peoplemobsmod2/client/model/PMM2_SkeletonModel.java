package site.dodoneko.peoplemobsmod2.client.model;

import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.monster.AbstractSkeletonEntity;

public class PMM2_SkeletonModel<T extends AbstractSkeletonEntity & IRangedAttackMob> extends PMM2_BipedModel<T>
{
    public PMM2_SkeletonModel(float modelSize)
    {
        super(modelSize, 0.0F, 64, 64);
        boobHeight = 0.45F;
        modelScale = 0.9F;
    }

    public PMM2_SkeletonModel(float modelSize, int textureWidth, int textureHeight)
    {
        super(modelSize, 0.0F, textureWidth, textureHeight);
        boobHeight = 0.45F;
        modelScale = 0.9F;
    }
}
