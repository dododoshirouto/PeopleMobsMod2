package site.dodoneko.peoplemobsmod2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

public class PMM2_CaveSpiderRenderer<T extends MobEntity> extends PMM2_SpiderRenderer<T>
{
    private static final ResourceLocation ENTITY_TEXTURES = new ResourceLocation("textures/entity/spider/cave_spider-chan.png");

    public PMM2_CaveSpiderRenderer(EntityRendererManager renderManager) {
        super(renderManager);
        this.getEntityModel().boobHeight = 0.4F;
        this.getEntityModel().modelScale = 0.9F;
    }


    protected ResourceLocation getEntityTexture(T entity) {
       return ENTITY_TEXTURES;
    }
}
