/**
 *
 */
package site.dodoneko.peoplemobsmod2.client.model;

import net.minecraft.entity.MobEntity;

/**
 * @author DODONEKO
 *
 */
public class PMM2_SpiderModel<T extends MobEntity> extends PMM2_BipedModel<T>
{
    public PMM2_SpiderModel(float modelScale, float boobHeight)
    {
    	super();
    	this.modelScale = modelScale;
    	this.boobHeight= boobHeight; 
        this.useChildModel = true;
    }
}
