/*  1:   */ package thKaguyaMod.client.render;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.model.ModelBase;
/*  6:   */ import net.minecraft.client.renderer.entity.Render;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.util.ResourceLocation;
/*  9:   */ import org.lwjgl.opengl.GL11;
/* 10:   */ import thKaguyaMod.client.model.ModelSilverKnife;
/* 11:   */ import thKaguyaMod.entity.item.EntitySilverKnife;
/* 12:   */ 
/* 13:   */ @SideOnly(Side.CLIENT)
/* 14:   */ public class RenderSilverKnife
/* 15:   */   extends Render
/* 16:   */ {
/* 17:20 */   private static final ResourceLocation resourceLocation_Knife_Blue = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Blue.png");
/* 18:21 */   private static final ResourceLocation resourceLocation_Knife_Red = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Red.png");
/* 19:22 */   private static final ResourceLocation resourceLocation_Knife_Green = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Green.png");
/* 20:23 */   private static final ResourceLocation resourceLocation_Knife_White = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_White.png");
/* 21:   */   protected ModelBase modelSilverKnife;
/* 22:   */   
/* 23:   */   public RenderSilverKnife()
/* 24:   */   {
/* 25:29 */     this.shadowSize = 0.3F;
/* 26:30 */     this.modelSilverKnife = new ModelSilverKnife();
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/* 30:   */   {
/* 31:36 */     renderSilverKnife((EntitySilverKnife)entity, x, y, z, yaw, pitch);
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void renderSilverKnife(EntitySilverKnife silverKnife, double x, double y, double z, float yaw, float pitch)
/* 35:   */   {
/* 36:41 */     GL11.glPushMatrix();
/* 37:42 */     bindEntityTexture(silverKnife);
/* 38:43 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/* 39:   */     
/* 40:45 */     GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
/* 41:46 */     GL11.glRotatef(-silverKnife.rotationPitch, 1.0F, 0.0F, 0.0F);
/* 42:47 */     GL11.glRotatef(silverKnife.getKnifeZAngle(), 0.0F, 0.0F, 1.0F);
/* 43:   */     
/* 44:49 */     GL11.glScalef(0.5F, 0.5F, 0.5F);
/* 45:   */     
/* 46:51 */     this.modelSilverKnife.render(silverKnife, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/* 47:52 */     GL11.glPopMatrix();
/* 48:   */   }
/* 49:   */   
/* 50:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 51:   */   {
/* 52:57 */     return getEntityTexture((EntitySilverKnife)entity);
/* 53:   */   }
/* 54:   */   
/* 55:   */   protected ResourceLocation getEntityTexture(EntitySilverKnife knife)
/* 56:   */   {
/* 57:63 */     switch (knife.getKnifeColor())
/* 58:   */     {
/* 59:   */     case 0: 
/* 60:66 */       return resourceLocation_Knife_Blue;
/* 61:   */     case 1: 
/* 62:68 */       return resourceLocation_Knife_Red;
/* 63:   */     case 2: 
/* 64:70 */       return resourceLocation_Knife_Green;
/* 65:   */     }
/* 66:72 */     return resourceLocation_Knife_White;
/* 67:   */   }
/* 68:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.RenderSilverKnife
 * JD-Core Version:    0.7.0.1
 */