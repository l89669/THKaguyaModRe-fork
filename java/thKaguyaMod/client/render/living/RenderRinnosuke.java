/*  1:   */ package thKaguyaMod.client.render.living;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.renderer.entity.RenderLiving;
/*  6:   */ import net.minecraft.entity.Entity;
/*  7:   */ import net.minecraft.entity.EntityLivingBase;
/*  8:   */ import net.minecraft.util.ResourceLocation;
/*  9:   */ import thKaguyaMod.client.model.living.ModelRinnosuke;
/* 10:   */ 
/* 11:   */ @SideOnly(Side.CLIENT)
/* 12:   */ public class RenderRinnosuke
/* 13:   */   extends RenderLiving
/* 14:   */ {
/* 15:16 */   ResourceLocation texture = new ResourceLocation("thkaguyamod", "textures/mob/RinnosukeTexture.png");
/* 16:   */   
/* 17:   */   public RenderRinnosuke()
/* 18:   */   {
/* 19:20 */     super(new ModelRinnosuke(), 0.25F);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/* 23:   */   {
/* 24:26 */     super.doRender(entity, x, y, z, yaw, pitch);
/* 25:27 */     renderRinnosuke((EntityLivingBase)entity, x, y, z, yaw, pitch);
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void renderRinnosuke(EntityLivingBase living, double x, double y, double z, float yaw, float pitch) {}
/* 29:   */   
/* 30:   */   protected ResourceLocation getEntityTexture(Entity living)
/* 31:   */   {
/* 32:38 */     return getEntityTexture((EntityLivingBase)living);
/* 33:   */   }
/* 34:   */   
/* 35:   */   protected ResourceLocation getEntityTexture(EntityLivingBase living)
/* 36:   */   {
/* 37:43 */     return this.texture;
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.living.RenderRinnosuke
 * JD-Core Version:    0.7.0.1
 */