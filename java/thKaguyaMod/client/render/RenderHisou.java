/*  1:   */ package thKaguyaMod.client.render;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.model.ModelBase;
/*  6:   */ import net.minecraft.client.renderer.entity.Render;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.util.ResourceLocation;
/*  9:   */ import org.lwjgl.opengl.GL11;
/* 10:   */ import thKaguyaMod.client.model.ModelHisou;
/* 11:   */ import thKaguyaMod.entity.item.EntityHisou;
/* 12:   */ 
/* 13:   */ @SideOnly(Side.CLIENT)
/* 14:   */ public class RenderHisou
/* 15:   */   extends Render
/* 16:   */ {
/* 17:20 */   private static final ResourceLocation hisouTexture = new ResourceLocation("thkaguyamod", "textures/HisouTexture.png");
/* 18:   */   protected ModelBase modelHisou;
/* 19:   */   
/* 20:   */   public RenderHisou()
/* 21:   */   {
/* 22:25 */     this.shadowSize = 0.5F;
/* 23:26 */     this.modelHisou = new ModelHisou();
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/* 27:   */   {
/* 28:32 */     doRenderHisou((EntityHisou)entity, x, y, z, yaw, pitch);
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void doRenderHisou(EntityHisou hisou, double x, double y, double z, float yaw, float pitch)
/* 32:   */   {
/* 33:37 */     GL11.glPushMatrix();
/* 34:38 */     bindTexture(getEntityTexture(hisou));
/* 35:39 */     GL11.glDisable(2896);
/* 36:40 */     GL11.glEnable(3042);
/* 37:41 */     GL11.glBlendFunc(770, 1);
/* 38:42 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/* 39:43 */     GL11.glRotatef(-yaw, 0.0F, 1.0F, 0.0F);
/* 40:44 */     GL11.glRotatef(hisou.rotationPitch, 1.0F, 0.0F, 0.0F);
/* 41:45 */     GL11.glRotatef(hisou.getAngle(), 0.0F, 0.0F, 1.0F);
/* 42:   */     
/* 43:47 */     GL11.glScalef(0.3F, 0.7F, 0.3F);
/* 44:48 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, hisou.getNum() * 0.125F);
/* 45:   */     
/* 46:50 */     this.modelHisou.render(hisou, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/* 47:51 */     GL11.glDisable(3042);
/* 48:52 */     GL11.glEnable(2896);
/* 49:53 */     GL11.glPopMatrix();
/* 50:   */   }
/* 51:   */   
/* 52:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 53:   */   {
/* 54:59 */     return hisouTexture;
/* 55:   */   }
/* 56:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.RenderHisou
 * JD-Core Version:    0.7.0.1
 */