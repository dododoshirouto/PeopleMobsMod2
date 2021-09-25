package site.dodoneko.peoplemobsmod2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.util.ResourceLocation;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_BatModel;

public class PMM2_BatRenderer<T extends BatEntity> extends PMM2_BipedRenderer<T, PMM2_BatModel<T>>
{
    private static final ResourceLocation ENTITY_TEXTURES = new ResourceLocation("textures/entity/bat-chan.png");

    public PMM2_BatRenderer(EntityRendererManager renderManager) {
        super(renderManager, new PMM2_BatModel<T>(0.0f), 0.25F, false);
    }


    protected ResourceLocation getEntityTexture(T entity) {
       return ENTITY_TEXTURES;
    }
}
