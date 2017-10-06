/*  1:   */ package thKaguyaMod.client.render;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*  4:   */ import cpw.mods.fml.relauncher.Side;
/*  5:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  6:   */ import net.minecraft.block.Block;
/*  7:   */ import net.minecraft.client.renderer.RenderBlocks;
/*  8:   */ import net.minecraft.world.IBlockAccess;
/*  9:   */ import thKaguyaMod.THKaguyaCore;
/* 10:   */ 
/* 11:   */ @SideOnly(Side.CLIENT)
/* 12:   */ public class RenderDivineSpiritBlock
/* 13:   */   implements ISimpleBlockRenderingHandler
/* 14:   */ {
/* 15:16 */   protected float[] colorR = { 0.8784314F, 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 1.0F, 1.0F };
/* 16:17 */   protected float[] colorG = { 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 0.8784314F, 0.5019608F, 1.0F };
/* 17:18 */   protected float[] colorB = { 0.0F, 0.8784314F, 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 1.0F };
/* 18:   */   
/* 19:   */   public void renderInventoryBlock(Block block, int metadata, int renderType, RenderBlocks renderer) {}
/* 20:   */   
/* 21:   */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/* 22:   */   {
/* 23:27 */     return false;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public boolean shouldRender3DInInventory()
/* 27:   */   {
/* 28:36 */     return false;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public int getRenderId()
/* 32:   */   {
/* 33:42 */     return THKaguyaCore.blockRenderId;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public boolean shouldRender3DInInventory(int modelId)
/* 37:   */   {
/* 38:48 */     return false;
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.RenderDivineSpiritBlock
 * JD-Core Version:    0.7.0.1
 */