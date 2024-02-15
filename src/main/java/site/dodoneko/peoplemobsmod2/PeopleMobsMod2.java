package site.dodoneko.peoplemobsmod2;

/* @debug: https://mcstacker.net/
 *
 * Model Reference
 *
 * Vector
 * X positive: Model Right
 * Y positive: Model Top
 * Z positive: Model Forward
 *
 * �bY
 * �b�^ Z
 * �[�[�[�[�[X
 *
 * Rotation
 * X positive: y to z
 * Y positive: z to x
 * Z positive: z to y
 *
 *
 */

import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import site.dodoneko.peoplemobsmod2.client.renderer.*;

import com.google.common.collect.Maps;

@Mod("peoplemobsmod2")
public class PeopleMobsMod2
{
	public static final Logger LOGGER = LogManager.getLogger();
	
    public PeopleMobsMod2() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }


    public Map<Class<? extends Entity>, EntityRenderer<? extends Entity>> myRenderers = Maps.newHashMap();
    public <T extends Entity> void register(Class<T> c, EntityRenderer<? super T> r) {
        this.myRenderers.put(c, r);
    }




    public void setEntityList(EntityRendererManager renderManager) {
        this.register(CreeperEntity.class, new PMM2_CreeperRenderer<>(renderManager));
        this.register(ZombieEntity.class, new PMM2_ZombieRenderer<>(renderManager));
        this.register(SkeletonEntity.class, new PMM2_SkeletonRenderer<>(renderManager));
        this.register(WitherSkeletonEntity.class, new PMM2_WitherSkeletonRenderer<>(renderManager));
        this.register(EndermanEntity.class, new PMM2_EndermanRenderer<>(renderManager));
        this.register(ChickenEntity.class, new PMM2_ChickenRenderer<>(renderManager));
        this.register(BatEntity.class, new PMM2_BatRenderer<>(renderManager));
        this.register(SquidEntity.class, new PMM2_SquidRenderer<>(renderManager));
        this.register(FoxEntity.class, new PMM2_FoxRenderer<>(renderManager));
        
        boolean DEBUG = true;
        if( !DEBUG ) return;
        
        this.register(SpiderEntity.class, new PMM2_SpiderRenderer<>(renderManager));
        this.register(CaveSpiderEntity.class, new PMM2_CaveSpiderRenderer<>(renderManager));
        this.register(CowEntity.class, new PMM2_CowRenderer<>(renderManager));
        this.register(PigEntity.class, new PMM2_PigRenderer<>(renderManager));
        this.register(MooshroomEntity.class, new PMM2_MooshroomRenderer<>(renderManager));
    }





    private void doClientStuff(final FMLClientSetupEvent event)
    {
        EntityRendererManager renderManager = Minecraft.getInstance().getRenderManager();

        setEntityList(renderManager);

        for(Entry<Class<? extends Entity>, EntityRenderer<? extends Entity>> key : myRenderers.entrySet())
        {
            if (!renderManager.renderers.containsKey(key.getKey())){
                continue;
            }
            else
            {
                renderManager.renderers.remove(key.getKey());
                renderManager.renderers.put(key.getKey(), key.getValue());
            }
        }
    }
}
