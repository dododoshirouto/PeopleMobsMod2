package site.dodoneko.peoplemobsmod2.client.layers;

import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.util.ResourceLocation;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_EndermanModel;
import site.dodoneko.peoplemobsmod2.client.renderer.PMM2_EndermanRenderer;

public class PMM2_EndermanEyesLayer<T extends EndermanEntity> extends PMM2_EntityEyesLayer<T, PMM2_EndermanModel<T>, PMM2_EndermanRenderer<T>> {

	private static final ResourceLocation ENTITY_EYES_TEXTURE = new ResourceLocation("textures/entity/enderman/enderman-chan_eyes.png");
	
	public PMM2_EndermanEyesLayer(PMM2_EndermanRenderer<T> renderer) {
		super(renderer);
		this.renderer = renderer;
	}

    @Override
	protected ResourceLocation GET_EYES_TEXTURE() {
		return ENTITY_EYES_TEXTURE;
	}

}
