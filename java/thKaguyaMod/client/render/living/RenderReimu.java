/*  1:   */ package thKaguyaMod.client.render.living;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.util.ResourceLocation;
/*  7:   */ import thKaguyaMod.client.model.living.ModelReimu;
/*  8:   */ import thKaguyaMod.entity.living.EntityReimu;
/*  9:   */ 
/* 10:   */ @SideOnly(Side.CLIENT)
/* 11:   */ public class RenderReimu
/* 12:   */   extends RenderTHBoss
/* 13:   */ {
/* 14:15 */   ResourceLocation texture = new ResourceLocation("thkaguyamod", "textures/mob/ReimuTexture.png");
/* 15:   */   
/* 16:   */   public RenderReimu()
/* 17:   */   {
/* 18:19 */     super(new ModelReimu(), 0.25F);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/* 22:   */   {
/* 23:25 */     super.doRender(entity, x, y, z, yaw, pitch);
/* 24:26 */     render((EntityReimu)entity, x, y, z, yaw, pitch);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void render(EntityReimu entity, double x, double y, double z, float yaw, float pitch) {}
/* 28:   */   
/* 29:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 30:   */   {
/* 31:37 */     return getEntityTexture((EntityReimu)entity);
/* 32:   */   }
/* 33:   */   
/* 34:   */   protected ResourceLocation getEntityTexture(EntityReimu entity)
/* 35:   */   {
/* 36:42 */     return this.texture;
/* 37:   */   }
/* 38:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.living.RenderReimu
 * JD-Core Version:    0.7.0.1
 */