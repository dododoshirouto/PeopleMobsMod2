package site.dodoneko.peoplemobsmod2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.ResourceLocation;
import site.dodoneko.peoplemobsmod2.client.layers.PMM2_SheepWoolLayer;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_SheepModel;

public class PMM2_SheepRenderer<T extends SheepEntity> extends PMM2_BipedRenderer<T, PMM2_SheepModel<T>> {
    private static final ResourceLocation ENTITY_TEXTURES = new ResourceLocation("textures/entity/sheep/sheep-chan.png");

    public PMM2_SheepRenderer(EntityRendererManager renderManager) {
        super(renderManager, new PMM2_SheepModel<T>(), 0.5F, false);
        this.addLayer(new PMM2_SheepWoolLayer<>(this));
    }

    protected ResourceLocation getEntityTexture(T entity) {
        return ENTITY_TEXTURES;
    }
}
