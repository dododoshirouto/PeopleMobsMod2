package site.dodoneko.peoplemobsmod2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.ResourceLocation;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_ZombieModel;

public class PMM2_ZombieRenderer<T extends ZombieEntity> extends PMM2_BipedRenderer<T, PMM2_ZombieModel<T>>
{
    private static final ResourceLocation ENTITY_TEXTURES = new ResourceLocation("textures/entity/zombie/zombie-chan.png");

    public PMM2_ZombieRenderer(EntityRendererManager renderManager) {
        super(renderManager, new PMM2_ZombieModel<T>(0.0f), new PMM2_ZombieModel<T>(0.5f, 64, 32), new PMM2_ZombieModel<T>(1.0f, 64, 32), 0.5F);
    }


    protected ResourceLocation getEntityTexture(T entity) {
       return ENTITY_TEXTURES;
    }
}
