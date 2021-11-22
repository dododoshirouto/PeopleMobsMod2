/**
 *
 */
package site.dodoneko.peoplemobsmod2.client.model;

import net.minecraft.entity.monster.SpiderEntity;

/**
 * @author DODONEKO
 *
 */
public class PMM2_SpiderModel<T extends SpiderEntity> extends PMM2_BipedModel<T>
{
	boolean isOnLadder;
	boolean isBesideClimbableBlock;
	
    public PMM2_SpiderModel(float modelScale, float boobHeight)
    {
    	super();
    	this.modelScale = modelScale;
    	this.boobHeight= boobHeight; 
        this.useChildModel = true;
    }

    protected void setPostAnimations()
    {
        this.isOnLadder = this.entityIn.isOnLadder();
        this.isBesideClimbableBlock = this.entityIn.isBesideClimbableBlock();
        
    	if (this.isOnLadder) {
    		this.setOnLadderAnimations();
    	}
    	
    	this.bipedHead.rotationPointY -= 4f;
    	this.bipedBody.rotationPointY -= 4f;
    }
}
