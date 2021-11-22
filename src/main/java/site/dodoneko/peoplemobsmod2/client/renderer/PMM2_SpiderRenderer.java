package site.dodoneko.peoplemobsmod2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.util.ResourceLocation;
import site.dodoneko.peoplemobsmod2.client.layers.PMM2_SpiderEyesLayer;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_SpiderModel;

public class PMM2_SpiderRenderer<T extends SpiderEntity> extends PMM2_BipedRenderer<T, PMM2_SpiderModel<T>>
{
    private static final ResourceLocation ENTITY_TEXTURES = new ResourceLocation("textures/entity/spider/spider-chan.png");

    public PMM2_SpiderRenderer(EntityRendererManager renderManager) {
        super(renderManager, new PMM2_SpiderModel<T>(1.0F, 0.5F), 0.5F, false);
        this.addLayer(new PMM2_SpiderEyesLayer<>(this));
    }


	protected ResourceLocation getEntityTexture(T entity) {
       return ENTITY_TEXTURES;
    }
}
