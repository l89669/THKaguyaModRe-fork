package thKaguyaMod;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.WeightedRandomChestContent;
import thKaguyaMod.entity.EntityShotMaterial;
import thKaguyaMod.entity.living.EntityCirno;
import thKaguyaMod.entity.living.EntityDanmakuCreeper;
import thKaguyaMod.entity.living.EntityFamiliar;
import thKaguyaMod.entity.living.EntityRinnosuke;
import thKaguyaMod.entity.living.EntityRumia;
import thKaguyaMod.entity.living.EntitySakuya;
import thKaguyaMod.entity.living.EntitySanae;
import thKaguyaMod.entity.living.EntitySunFlowerFairy;
import thKaguyaMod.entity.living.EntityTHFairy;
import thKaguyaMod.entity.living.EntityTHPhantom;
import thKaguyaMod.entity.living.EntityToziko;
import thKaguyaMod.entity.living.EntityWriggle;


public class EntityLoader
{
    private static int nextID;
    public EntityLoader()
    {
    	nextID= 20+EntityRegistry.findGlobalUniqueEntityId();
        registerEntityID(EntityTHFairy.class,"THFairy", 0xFFFF80, 0xC0C000);
        registerEntityID(EntityCirno.class,"Cirno",0x00FFE0, 0x00E0E0);
        registerEntityID(EntityRumia.class,"Rumia",0x671503, 0xC0C010);
        registerEntityID(EntityToziko.class,"Toziko",0x60D683, 0x376E91);
        registerEntityID(EntityTHPhantom.class,"THPhantom",0xFFFFF0, 0xFFFFFF);
        registerEntityID(EntitySanae.class,"Sanae",0x6BD245, 0x2937E0);
        registerEntityID(EntityWriggle.class,"Wriggle",0x15224F, 0x83C826);
        registerEntityID(EntitySakuya.class,"Sakuya",0xC1C1D6, 0x3053E7);
        registerEntityID(EntityRinnosuke.class,"Rinnosuke",0x373737, 0x2D2DE0);
        registerEntityID(EntitySunFlowerFairy.class,"SunFlowerFairy",0xDDDD60, 0xA0A000);
        registerEntityID(EntityFamiliar.class,"Familiar",0x000000,0x000000);
        registerEntityID(EntityDanmakuCreeper.class,"Hanabeeper",0x0FF080, 0xC0C000);
        
    }
    private static void registerEntityID(Class<? extends Entity> entityClass, String name,int back,int f)
    {
    	  EntityRegistry.registerGlobalEntityID(entityClass, name, nextID);
          EntityRegistry.registerModEntity(entityClass, name, nextID, THKaguyaCore.instance, 80, 1, true);
          createEgg(nextID, back, f);
          nextID++;
    }
    public static void createEgg(int randomId, int solidColor, int spotColor) {
        EntityList.entityEggs.put(Integer.valueOf(randomId), new EntityList.EntityEggInfo(randomId, solidColor, spotColor));
    }
}