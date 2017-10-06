/*  1:   */ package thKaguyaMod.client.render.living;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.util.ResourceLocation;
/*  7:   */ import thKaguyaMod.client.model.living.ModelCirno;
/*  8:   */ import thKaguyaMod.entity.living.EntityCirno;
/*  9:   */ 
/* 10:   */ @SideOnly(Side.CLIENT)
/* 11:   */ public class RenderCirno
/* 12:   */   extends RenderTHBoss
/* 13:   */ {
/* 14:15 */   ResourceLocation cirnoTexture = new ResourceLocation("thkaguyamod", "textures/mob/CirnoTexture.png");
/* 15:   */   
/* 16:   */   public RenderCirno()
/* 17:   */   {
/* 18:19 */     super(new ModelCirno(), 0.25F);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/* 22:   */   {
/* 23:25 */     super.doRender(entity, x, y, z, yaw, pitch);
/* 24:26 */     renderTHFairyCirno((EntityCirno)entity, x, y, z, yaw, pitch);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void renderTHFairyCirno(EntityCirno thFairyCirno, double x, double y, double z, float yaw, float pitch) {}
/* 28:   */   
/* 29:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 30:   */   {
/* 31:36 */     return getEntityTexture((EntityCirno)entity);
/* 32:   */   }
/* 33:   */   
/* 34:   */   protected ResourceLocation getEntityTexture(EntityCirno cirno)
/* 35:   */   {
/* 36:41 */     return this.cirnoTexture;
/* 37:   */   }
/* 38:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.living.RenderCirno
 * JD-Core Version:    0.7.0.1
 */