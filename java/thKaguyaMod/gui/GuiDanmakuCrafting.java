/*  1:   */ package thKaguyaMod.gui;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.Minecraft;
/*  4:   */ import net.minecraft.client.gui.FontRenderer;
/*  5:   */ import net.minecraft.client.gui.inventory.GuiContainer;
/*  6:   */ import net.minecraft.client.renderer.texture.TextureManager;
/*  7:   */ import net.minecraft.client.resources.I18n;
/*  8:   */ import net.minecraft.entity.player.EntityPlayer;
/*  9:   */ import net.minecraft.util.ResourceLocation;
/* 10:   */ import net.minecraft.world.World;
/* 11:   */ import org.lwjgl.opengl.GL11;
/* 12:   */ 
/* 13:   */ public class GuiDanmakuCrafting
/* 14:   */   extends GuiContainer
/* 15:   */ {
/* 16:14 */   private static final ResourceLocation bg_texture = new ResourceLocation("thkaguyamod", "textures/gui/danmaku_crafting_table.png");
/* 17:   */   
/* 18:   */   public GuiDanmakuCrafting(EntityPlayer player, World world, int x, int y, int z)
/* 19:   */   {
/* 20:21 */     super(new ContainerDanmakuCrafting(player.inventory, world, x, y, z));
/* 21:22 */     this.xSize = 256;
/* 22:   */   }
/* 23:   */   
/* 24:   */   protected void drawGuiContainerForegroundLayer(int par1, int par2)
/* 25:   */   {
/* 26:35 */     this.fontRendererObj.drawString(I18n.format("danmakuCrafting.name", new Object[0]), 6, 6, 4210752);
/* 27:36 */     this.fontRendererObj.drawString(I18n.format("danmakuCrafting.form", new Object[0]), 134, 6, 4210752);
/* 28:37 */     this.fontRendererObj.drawString(I18n.format("danmakuCrafting.copy", new Object[0]), 192, 6, 4210752);
/* 29:38 */     this.fontRendererObj.drawString(I18n.format("danmakuCrafting.material", new Object[0]), 26, 22, 4210752);
/* 30:39 */     this.fontRendererObj.drawString(I18n.format("danmakuCrafting.number", new Object[0]), 26, 39, 4210752);
/* 31:40 */     this.fontRendererObj.drawString(I18n.format("danmakuCrafting.speed", new Object[0]), 26, 56, 4210752);
/* 32:41 */     this.fontRendererObj.drawString(I18n.format("danmakuCrafting.color", new Object[0]), 87, 22, 4210752);
/* 33:42 */     this.fontRendererObj.drawString(I18n.format("danmakuCrafting.gravity", new Object[0]), 87, 39, 4210752);
/* 34:43 */     this.fontRendererObj.drawString(I18n.format("danmakuCrafting.bound", new Object[0]), 87, 56, 4210752);
/* 35:44 */     this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 48, this.ySize - 96 + 2, 4210752);
/* 36:   */   }
/* 37:   */   
/* 38:   */   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
/* 39:   */   {
/* 40:52 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 41:53 */     this.mc.getTextureManager().bindTexture(bg_texture);
/* 42:54 */     int k = (this.width - this.xSize) / 2;
/* 43:55 */     int l = (this.height - this.ySize) / 2;
/* 44:56 */     drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
/* 45:   */   }
/* 46:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.gui.GuiDanmakuCrafting
 * JD-Core Version:    0.7.0.1
 */