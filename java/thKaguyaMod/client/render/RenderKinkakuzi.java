/*  1:   */ package thKaguyaMod.client.render;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.model.ModelBase;
/*  6:   */ import net.minecraft.client.renderer.entity.Render;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.util.MathHelper;
/*  9:   */ import net.minecraft.util.ResourceLocation;
/* 10:   */ import org.lwjgl.opengl.GL11;
/* 11:   */ import thKaguyaMod.client.model.ModelKinkakuzi;
/* 12:   */ import thKaguyaMod.entity.item.EntityKinkakuzi;
/* 13:   */ 
/* 14:   */ @SideOnly(Side.CLIENT)
/* 15:   */ public class RenderKinkakuzi
/* 16:   */   extends Render
/* 17:   */ {
/* 18:21 */   private static final ResourceLocation kinkakuziTexture = new ResourceLocation("thkaguyamod", "textures/Kinkakuzi.png");
/* 19:   */   protected ModelBase modelKinkakuzi;
/* 20:   */   
/* 21:   */   public RenderKinkakuzi()
/* 22:   */   {
/* 23:26 */     this.shadowSize = 3.0F;
/* 24:27 */     this.modelKinkakuzi = new ModelKinkakuzi();
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/* 28:   */   {
/* 29:33 */     doRenderKinkakuzi((EntityKinkakuzi)entity, x, y, z, yaw, pitch);
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void doRenderKinkakuzi(EntityKinkakuzi kinkakuzi, double x, double y, double z, float yaw, float pitch)
/* 33:   */   {
/* 34:38 */     GL11.glPushMatrix();
/* 35:39 */     bindTexture(getEntityTexture(kinkakuzi));
/* 36:40 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/* 37:41 */     GL11.glScalef(3.0F, 0.75F, 3.0F);
/* 38:42 */     GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
/* 39:43 */     float f = kinkakuzi.getTimeSinceHit() - pitch;
/* 40:44 */     float f1 = kinkakuzi.getDamageTaken() - pitch;
/* 41:46 */     if (f1 < 0.0F) {
/* 42:48 */       f1 = 0.0F;
/* 43:   */     }
/* 44:51 */     if (f > 0.0F) {
/* 45:53 */       GL11.glRotatef(MathHelper.sin(f) * f * f1 / 10.0F * kinkakuzi.getForwardDirection(), 1.0F, 0.0F, 0.0F);
/* 46:   */     }
/* 47:57 */     this.modelKinkakuzi.render(kinkakuzi, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/* 48:58 */     GL11.glPopMatrix();
/* 49:   */   }
/* 50:   */   
/* 51:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 52:   */   {
/* 53:63 */     return kinkakuziTexture;
/* 54:   */   }
/* 55:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.RenderKinkakuzi
 * JD-Core Version:    0.7.0.1
 */