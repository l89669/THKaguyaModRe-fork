package thKaguyaMod;

//FMLにロードさせるためのアノテーション
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import thKaguyaMod.entity.living.VillagerRinnosuke;
import thKaguyaMod.event.THKaguyaPlayerDeathEventHandler;
import thKaguyaMod.gui.GuiHandler;
import thKaguyaMod.init.THKaguyaBlocks;
import thKaguyaMod.init.THKaguyaConfig;
import thKaguyaMod.init.THKaguyaItems;
import thKaguyaMod.init.THKaguyaRecipe;
import thKaguyaMod.tileentity.TileEntityDivineSpirit;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
//Entityに関するレジストリ
//前初期化, 初期化のイベント
//プロキシシステムのためのアノテーション
//言語に関するレジストリ
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;


/** 五つの難題MOD+のコアクラス */
@Mod(modid = "THKaguyaMod", name = "Itutu no Nandai MOD+", version = "2.90-1.7.10")
public class THKaguyaCore
{
	
	// クライアント側とサーバー側で異なるインスタンスを生成
	@SidedProxy(clientSide = "thKaguyaMod.client.ClientProxy", serverSide = "thKaguyaMod.CommonProxy")
	public static CommonProxy proxy;
	
	// 自身のインスタンス
	@Mod.Instance("THKaguyaMod")//数行上の@Mod(modid = "......"で指定したmodid
	public static THKaguyaCore instance;
	
	/*==================ブロックレンダーID====================*/
	public static int blockRenderId;
	
	/*========================= GUI===========================*/
	/** 弾幕作業台GUI */
	public static final int guiDanmakuCraftingID			=  0;
	/** レーザー弾幕作業台GUI */
	public static final int guiDanmakuCraftingLaserID	=  1;
	//public static int guiSpellCardID = 2;
	/** 守矢神社出張早苗さんGUI */
	public static final int guiMerchantSanaeID			=  2;
	/** 打ち出の小槌GUI */
	public static final int guiMiracleMalletID			=  3;
	
	/*====================防具インデックス====================*/
	public static int hinezumiIndex; 
	public static int marisaIndex;
	public static int suwakoIndex;
	
	/** 村人ID 霖之助 */
	public static int villagerRinnosukeId = 932;
	public static VillagerRinnosuke rinnosuke;
	
	/*=================クリエイティブタブ登録==================*/
	/** スペルカードタブ */
	public static final CreativeTabs tabSpellCard = new CreativeTabSpellCard("spellcard");
	
	
	/** 前処理 */
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		THKaguyaConfig.setConfig(event);
		
		blockRenderId = proxy.getNewRenderType();
		
		proxy.registerSpellCards();
		
		
		//アイテムの初期設定
		THKaguyaItems.setItems();
		//アイテムを登録
		proxy.registerItems();
		//ブロックの初期設定
		THKaguyaBlocks.setBlocks();
		//ブロックの登録
		proxy.registerBlocks();
		
		//TileEntityの登録
		GameRegistry.registerTileEntity(TileEntityDivineSpirit.class, "TileEntityDivineSpirit");
		
		//GUIの登録
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		//エンティティの登録
		new EntityLoader();
		
		proxy.registerEntitys();
		
		
		//妖精などの弾幕パターンを登録
		proxy.registerDanmakuPattern();
		
	}
	
	/** 初期化処理 */
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{	
		rinnosuke = new VillagerRinnosuke();
		//VillagerRegistry.instance().registerVillagerId(villagerRinnosukeId);
		VillagerRegistry.instance().registerVillageTradeHandler(villagerRinnosukeId,rinnosuke);
	
		//スポーン設定
		proxy.registerEntitySpawn();
		
		//ディスペンサーの発射アイテムの登録
		proxy.registerDispenser();
		
		proxy.registerChestItem();
		
		// サーバー側は何もしない, クライアント側ではレンダーの登録が行われる
		proxy.registerRenderers();
		
		
		//レシピの登録
		THKaguyaRecipe.setAllRecipes();
		if(THKaguyaConfig.recipelessItemsRecipe)
		{
			THKaguyaRecipe.setRecipelessItemsRecipe();
		}
		
		MinecraftForge.EVENT_BUS.register(new THKaguyaPlayerDeathEventHandler());
		//MinecraftForge.EVENT_BUS.register(new THKaguyaTimeStopEventHandler());
		
	}
}