package thKaguyaMod.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class GuiDanmakuCrafting extends GuiContainer
{

	private static final ResourceLocation bg_texture = new ResourceLocation("thkaguyamod", "textures/gui/danmaku_crafting_table.png");
	//private TileEntitySample tileentity;
	//private InventorySample inventory;
 
    //public GuiDanmakuCrafting(InventoryPlayer inventoryPlayer, World world, int x, int y, int z)
    public GuiDanmakuCrafting(EntityPlayer player, World world, int x, int y, int z)
    {
        super(new ContainerDanmakuCrafting(player.inventory, world, x, y, z));
        this.xSize = 256;
    }
 
    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
    	/*
    	 * 第一引数で描画する文字列を指定
    	 * 第２、３引数は文字の描画位置。X,Yの順。この位置はGUIの左上を0，0とする
    	 * 第４引数は手を付けなくていいと思う
    	 */
    	this.fontRendererObj.drawString(I18n.format("danmakuCrafting.name"), 6, 6, 4210752);
    	this.fontRendererObj.drawString(I18n.format("danmakuCrafting.form"), 134, 6, 4210752);
    	this.fontRendererObj.drawString(I18n.format("danmakuCrafting.copy"), 192, 6, 4210752);
    	this.fontRendererObj.drawString(I18n.format("danmakuCrafting.material"), 26, 22, 4210752);
    	this.fontRendererObj.drawString(I18n.format("danmakuCrafting.number"), 26, 39, 4210752);
    	this.fontRendererObj.drawString(I18n.format("danmakuCrafting.speed"), 26, 56, 4210752);
    	this.fontRendererObj.drawString(I18n.format("danmakuCrafting.color"), 87, 22, 4210752);
    	this.fontRendererObj.drawString(I18n.format("danmakuCrafting.gravity"), 87, 39, 4210752);
    	this.fontRendererObj.drawString(I18n.format("danmakuCrafting.bound"), 87, 56, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 48, this.ySize - 96 + 2, 4210752);
    }
 
    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(bg_texture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
 
}
