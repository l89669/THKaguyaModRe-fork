/*  1:   */ package thKaguyaMod.client.render.living;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.renderer.entity.RenderLiving;
/*  6:   */ import net.minecraft.entity.Entity;
/*  7:   */ import net.minecraft.util.ResourceLocation;
/*  8:   */ import thKaguyaMod.client.model.living.ModelCursedDecoyDoll;
/*  9:   */ import thKaguyaMod.entity.item.EntityCursedDecoyDoll;
/* 10:   */ 
/* 11:   */ @SideOnly(Side.CLIENT)
/* 12:   */ public class RenderCursedDecoyDoll
/* 13:   */   extends RenderLiving
/* 14:   */ {
/* 15:16 */   ResourceLocation texture = new ResourceLocation("thkaguyamod", "textures/mob/DecoyTexture.png");
/* 16:   */   
/* 17:   */   public RenderCursedDecoyDoll()
/* 18:   */   {
/* 19:20 */     super(new ModelCursedDecoyDoll(), 0.25F);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/* 23:   */   {
/* 24:26 */     super.doRender(entity, x, y, z, yaw, pitch);
/* 25:27 */     render((EntityCursedDecoyDoll)entity, x, y, z, yaw, pitch);
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void render(EntityCursedDecoyDoll entity, double x, double y, double z, float yaw, float pitch) {}
/* 29:   */   
/* 30:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 31:   */   {
/* 32:38 */     return getEntityTexture((EntityCursedDecoyDoll)entity);
/* 33:   */   }
/* 34:   */   
/* 35:   */   protected ResourceLocation getEntityTexture(EntityCursedDecoyDoll entity)
/* 36:   */   {
/* 37:43 */     return this.texture;
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.living.RenderCursedDecoyDoll
 * JD-Core Version:    0.7.0.1
 */