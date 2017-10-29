package thKaguyaMod.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class GuiMiracleMallet extends GuiContainer
{

	private static final ResourceLocation bg_texture = new ResourceLocation("thkaguyamod", "textures/gui/danmaku_crafting_table.png");
	//private TileEntitySample tileentity;
	//private InventorySample inventory;
	
    /** "Done" button for the GUI. */
    private GuiButton doneBtn1;
    private GuiButton doneBtn2;
    private GuiButton doneBtn3;
    private GuiListExtended optionsRowList;
    private static final GameSettings.Options[] videoOptions = new GameSettings.Options[] {GameSettings.Options.GRAPHICS, GameSettings.Options.RENDER_DISTANCE, GameSettings.Options.AMBIENT_OCCLUSION, GameSettings.Options.FRAMERATE_LIMIT, GameSettings.Options.ANAGLYPH, GameSettings.Options.VIEW_BOBBING, GameSettings.Options.GUI_SCALE, GameSettings.Options.ADVANCED_OPENGL, GameSettings.Options.GAMMA, GameSettings.Options.RENDER_CLOUDS, GameSettings.Options.PARTICLES, GameSettings.Options.USE_FULLSCREEN, GameSettings.Options.ENABLE_VSYNC, GameSettings.Options.MIPMAP_LEVELS, GameSettings.Options.ANISOTROPIC_FILTERING};
 
    //public GuiDanmakuCrafting(InventoryPlayer inventoryPlayer, World world, int x, int y, int z)
    public GuiMiracleMallet(EntityPlayer player, World world, int x, int y, int z)
    {
        super(new ContainerMiracleMallet(player.inventory, world, x, y, z));
        this.xSize = 256;
    }
    
    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
    	super.initGui();
        //this.buttonList.clear();
        //this.optionsRowList = new GuiOptionsRowList(this.mc, this.width, this.height, 32, this.height - 32, 25, videoOptions);
        //Keyboard.enableRepeatEvents(true);
        this.buttonList.add(this.doneBtn1 = new GuiButton(0, (this.width - this.xSize) / 2 + 100, (this.height - this.ySize) / 2 +  3, 64, 20, "1 SWING" ) );
        this.buttonList.add(this.doneBtn2 = new GuiButton(1, (this.width - this.xSize) / 2 + 100, (this.height - this.ySize) / 2 + 26, 64, 20, "2 SWING" ) );
        this.buttonList.add(this.doneBtn3 = new GuiButton(2, (this.width - this.xSize) / 2 + 100, (this.height - this.ySize) / 2 + 49, 64, 20, "3 SWING" ) );
        
    }
 
    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
    	/*
    	 * 第１引数で描画する文字列を指定
    	 * 第２、３引数は文字の描画位置。X,Yの順。この位置はGUIの左上を0，0とする
    	 * 第４引数は手を付けなくていいと思う
    	 */
    	this.fontRendererObj.drawString(I18n.format("item.miracleMallet.name"), 6, 6, 4210752);
    	this.fontRendererObj.drawString(I18n.format("miracleMallet.material"), 134, 6, 4210752);
    	this.fontRendererObj.drawString(I18n.format("miracleMallet.result"), 192, 6, 4210752);
    	this.fontRendererObj.drawString(I18n.format("danmakuCrafting.material"), 26, 22, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 48, this.ySize - 96 + 2, 4210752);
        
        doneBtn1.enabled = false;
    }
 
    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(bg_texture);
        int xPosition = (this.width - this.xSize) / 2;
        int yPosition = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect( xPosition, yPosition, 0, 0, this.xSize, this.ySize );
        
        
    }
 
}
