/*  1:   */ package thKaguyaMod.client.render.living;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.renderer.entity.RenderLiving;
/*  6:   */ import net.minecraft.entity.Entity;
/*  7:   */ import net.minecraft.util.ResourceLocation;
/*  8:   */ import thKaguyaMod.client.model.living.ModelSunFlowerFairy;
/*  9:   */ import thKaguyaMod.entity.living.EntityTHFairy;
/* 10:   */ 
/* 11:   */ @SideOnly(Side.CLIENT)
/* 12:   */ public class RenderSunFlowerFairy
/* 13:   */   extends RenderLiving
/* 14:   */ {
/* 15:   */   public RenderSunFlowerFairy()
/* 16:   */   {
/* 17:18 */     super(new ModelSunFlowerFairy(), 0.25F);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/* 21:   */   {
/* 22:25 */     super.doRender(entity, x, y, z, yaw, pitch);
/* 23:26 */     renderTHFairy((EntityTHFairy)entity, x, y, z, yaw, pitch);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void renderTHFairy(EntityTHFairy thFairy, double x, double y, double z, float yaw, float pitch) {}
/* 27:   */   
/* 28:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 29:   */   {
/* 30:38 */     return getEntityTexture((EntityTHFairy)entity);
/* 31:   */   }
/* 32:   */   
/* 33:   */   protected ResourceLocation getEntityTexture(EntityTHFairy thFairy)
/* 34:   */   {
/* 35:43 */     return new ResourceLocation("thkaguyamod", "textures/mob/FairyTexture_10.png");
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.living.RenderSunFlowerFairy
 * JD-Core Version:    0.7.0.1
 */