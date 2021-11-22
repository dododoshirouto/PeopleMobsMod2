package site.dodoneko.peoplemobsmod2.client.layers;

import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.util.ResourceLocation;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_SpiderModel;
import site.dodoneko.peoplemobsmod2.client.renderer.PMM2_SpiderRenderer;

public class PMM2_SpiderEyesLayer<T extends SpiderEntity> extends PMM2_EntityEyesLayer<T, PMM2_SpiderModel<T>, PMM2_SpiderRenderer<T>> {

	private static final ResourceLocation ENTITY_EYES_TEXTURE = new ResourceLocation("textures/entity/spider/spider-chan_eyes.png");
	
	public PMM2_SpiderEyesLayer(PMM2_SpiderRenderer<T> renderer) {
		super(renderer);
		this.renderer = renderer;
	}
	
	@Override
	protected ResourceLocation GET_EYES_TEXTURE() {
		return ENTITY_EYES_TEXTURE;
	}

}
