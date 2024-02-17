package site.dodoneko.peoplemobsmod2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.RabbitRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_BipedModel;

public class PMM2_RabbitRenderer<T extends MobEntity> extends PMM2_BipedRenderer<T, PMM2_BipedModel<T>> {
    private static final ResourceLocation BROWN = new ResourceLocation("textures/entity/rabbit/brown-chan.png");
    private static final ResourceLocation BLACK = new ResourceLocation("textures/entity/rabbit/black-chan.png");

    private static final ResourceLocation WHITE = new ResourceLocation("textures/entity/rabbit/black-chan.png");
    private static final ResourceLocation GOLD = new ResourceLocation("textures/entity/rabbit/black-chan.png");
    private static final ResourceLocation SALT = new ResourceLocation("textures/entity/rabbit/black-chan.png");
    private static final ResourceLocation WHITE_SPLOTCHED = new ResourceLocation(
            "textures/entity/rabbit/black-chan.png");
    private static final ResourceLocation TOAST = new ResourceLocation("textures/entity/rabbit/black-chan.png");
    private static final ResourceLocation CAERBANNOG = new ResourceLocation("textures/entity/rabbit/black-chan.png");

    public PMM2_RabbitRenderer(EntityRendererManager renderManager) {
        super(renderManager, new PMM2_BipedModel<T>(0.0f), 0.5F, false);
        PMM2_BipedModel<T> model = this.getEntityModel();
        model.boobHeight = 0.8F;
        model.modelScale = 0.5F;
    }

    protected ResourceLocation getEntityTexture(T entity) {
        String s = TextFormatting.getTextWithoutFormattingCodes(entity.getName().getString());
        if (s != null && "Toast".equals(s)) {
            return TOAST;
        } else {
            switch (((RabbitEntity)entity).getRabbitType()) {
                case 0:
                default:
                    return BROWN;
                case 1:
                    return WHITE;
                case 2:
                    return BLACK;
                case 3:
                    return WHITE_SPLOTCHED;
                case 4:
                    return GOLD;
                case 5:
                    return SALT;
                case 99:
                    return CAERBANNOG;
            }
        }
    }
}
