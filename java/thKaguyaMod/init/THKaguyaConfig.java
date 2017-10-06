package thKaguyaMod.init;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/** コンフィグ設定クラス */
public class THKaguyaConfig
{
	/** スキマの効果音の設定 */
	public static String sukimaWarpSE = "mob.endermen.portal";
	//public static String sukimaWarpSE = "random.sukima";
	
	//コンフィグで変更できる変数
	
	/*===================================EntityID関連========================================*/
	/** 雑魚妖精のEntityID */
	public static int entityIdTHFairy;
	/** ひまわり妖精のEntityID */
	public static int entityIdSunflowerFairy;
	/** 使い魔のEntityID */
	public static int entityIdFamiliar;
	/** 幽霊のEntityID */
	public static int entityIdTHPhantom;
	/** ハナビーパーのEntityID */
	public static int entityIdHanabeeper;
	/** チルノのEntityID */
	public static int entityIdCirno;
	/** ルーミアのEntityID */
	public static int entityIdRumia;
	/** 早苗のEntityID */
	public static int entityIdSanae;
	/** 屠自古のEntityID */
	public static int entityIdToziko;
	/** 霖之助のEntityID */
	public static int entityIdRinnosuke;
	/** リグルのEntityID */
	public static int entityIdWriggle;
	/** 咲夜のEntityID */
	public static int entityIdSakuya;
	
	public static int entityIdReimu;
	public static int entityIdMeirin;
	public static int entityIdPatchouli;
	public static int entityIdRemilia;
	public static int entityIdAlice;
	public static int entityIdKaguya;
	public static int entityIdMomizi;
	public static int entityIdByakuren;
	public static int entityIdMiko;
	public static int entityIdSeija;
	public static int entityIdYukari;
	public static int entityIdKasen;
	public static int entityIdSeiga;
	
	/*===================================エフェクト関連========================================*/
	/** スペルカードの宣言音の音量 */
	public static float SpellCardVol;
	/** マスタースパークの効果音の音量 */
	public static float MasterSparkVol;
	/** 全人類の緋想天の効果音の音量 */
	public static float HisoutenVol;
	/** 倒れたときのいわゆるピチューン音の音量 */
	public static float DownVol;
	/** マスタースパークを使用できるか？　true:使用できる　false:使用できない */
	public static boolean UsingMasterSpark;
	/** マスタースパークでブロックを破壊できるか？　true:破壊できる　false:破壊できない */
	public static boolean MasterSparkDestroysBlocks;
	/** デフォルトのスキマの効果音を使うか？　true:使う　false:使わない */
	public static boolean useDefaultGapSE;
	/** 時間停止のエフェクトを出現させるか？　true:出現する　false:出現しない */
	public static boolean useTimeStopEffect;
	
	/*===================================弾幕関連========================================*/
	/** このMODで追加されるMOBが自然スポーンするか？　true:する　false:しない */
	public static boolean spawnDanmakuMob;
	/** ハナビーパーが自然スポーンするか？　true:する　false:しない */
	public static boolean spawnHanabeeper;
	/** 妖精が自然スポーンするか？　true:する　false:しない */
	public static boolean spawnFairy;
	/** 幽霊が自然スポーンするか？　true:する　false:しない */
	public static boolean spawnPhantom;
	/** ボスが自然スポーンするか？　true:する　false:しない */
	public static boolean spawnBoss;
	
	/** 妖精のスポーン率 */
	public static int fairySpawnRate;
	/** 弾幕のレベル 1～4を指定して、1がEasy、4がLunatic。 0は弾を出さない */
	public static int danmakuLevel;
	/** 妖精などが弾幕を打つ方向の適格性（100でずれなし、0でずれまくり） */
	public static float danmakuAccuracy;
	
	/** 弾幕攻撃に当たると即死するモードにするか？　true:即死するモード　false:通常モード */
	public static boolean danmakuOneKillMode;
	/** 壁抜けの鑿が使用できるかどうか？　true:使用できる　false:使用できない */
	public static boolean canUseWallPassingChisel;
	/** 弾アイテムで出せる弾幕の限界量 */
	public static int shotMaxNumber;
	/** レーザーアイテムで出せる弾幕の限界量 */
	public static int laserMaxNumber;
	
	/** レシピのないアイテムのレシピを追加するか？　true:使用する(非推奨)　false:使用しない */
	public static boolean recipelessItemsRecipe;
	
	
	/** コンフィグを設定する */
	public static void setConfig(FMLPreInitializationEvent event)
	{
		//コンフィグの設定
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		int setSpellCardVol;
		int setMasterSparkVol;
		int setHisoutenVol;
		int setDownVol;
		
		try
		{
			/*
			* ファイルをロードする.
			*/
			cfg.load();
			
			/*===================================EntityID関連========================================*/
			entityIdTHFairy   	= cfg.get("EntityID", "Fairy"   		, 530).getInt();
			entityIdTHPhantom   	= cfg.get("EntityID", "Phantom"   		, 531).getInt();
			entityIdFamiliar   	= cfg.get("EntityID", "Familiar"   		, 532).getInt();
			entityIdSunflowerFairy  = cfg.get("EntityID", "SunFlowerFairy"	, 533).getInt();
			
			entityIdReimu			= cfg.get("EntityID", "Reimu"   		, 540).getInt();
			entityIdMeirin		= cfg.get("EntityID", "Meirin"   		, 541).getInt();
			entityIdPatchouli		= cfg.get("EntityID", "Patchouli"   	, 542).getInt();
			entityIdSakuya		= cfg.get("EntityID", "Sakuya"   		, 543).getInt();
			entityIdRemilia		= cfg.get("EntityID", "Remilia"   		, 544).getInt();
			entityIdAlice			= cfg.get("EntityID", "Alice"   		, 545).getInt();
			entityIdKaguya		= cfg.get("EntityID", "Kaguya"   		, 546).getInt();
			entityIdMomizi		= cfg.get("EntityID", "Momizi"   		, 547).getInt();
			entityIdByakuren		= cfg.get("EntityID", "Byakuren"   		, 548).getInt();
			entityIdMiko			= cfg.get("EntityID", "Miko"   			, 549).getInt();
			entityIdSeija			= cfg.get("EntityID", "Seija"   		, 550).getInt();
			entityIdYukari   		= cfg.get("EntityID", "Yukari"   		, 551).getInt();
			entityIdKasen   		= cfg.get("EntityID", "Kasen"   		, 552).getInt();
			entityIdSeiga   		= cfg.get("EntityID", "Seiga"   		, 553).getInt();
			
			entityIdWriggle  		= cfg.get("EntityID", "Wriggle"   		, 556).getInt();
			entityIdHanabeeper   = cfg.get("EntityID", "Hanabeeper"  	, 557).getInt();
			entityIdRinnosuke		= cfg.get("EntityID", "Rinnosuke"   	, 558).getInt();
			
			entityIdRumia   		= cfg.get("EntityID", "Rumia"   		, 559).getInt();
			entityIdToziko   		= cfg.get("EntityID", "Toziko"   		, 560).getInt();
			entityIdCirno   		= cfg.get("EntityID", "Cirno"   		, 561).getInt();
			entityIdSanae   		= cfg.get("EntityID", "Sanae"   		, 562).getInt();
			
			

			/*===================================制限関連========================================*/
			UsingMasterSpark = true;
			//マスタースパークの使用ができるか？
			UsingMasterSpark = cfg.get("Limit", "Use Master Spark", true).getBoolean(true);
			//マスタースパークが地形を破壊するかどうか
			MasterSparkDestroysBlocks = cfg.get("Limit", "Master Spark Destroys Blocks", true).getBoolean(true);
			//壁抜けの鑿の仕様を許可するか
			canUseWallPassingChisel = cfg.get("Limit", "Can Use Wall-Passing Chisel", true).getBoolean(true);
			
			//弾アイテムで出せる弾幕の限界量
			shotMaxNumber    = cfg.get("Limit", "Shot Max Number"   , 32).getInt();
			//レーザーアイテムで出せる弾幕の限界量
			laserMaxNumber   = cfg.get("Limit", "Laser Max Number"   , 8).getInt();

			
			/*===================================エフェクト関連========================================*/
			//時間停止のエフェクトを有効にするかどうか
			useTimeStopEffect = cfg.get("Effect", "Appear Time stop effect", true).getBoolean(true);
			
			//スキマのSEをデフォルトのものを使うか
			useDefaultGapSE = cfg.get("Effect", "Use default Gap(Sukima)SE", true).getBoolean(true);
			if(!useDefaultGapSE)
			{
				sukimaWarpSE = "thkaguyamod:sukima";
			}
			
			File sukimaFile = new File("bin/assets/thkaguyamod/sounds/sukima.ogg");
			File sukimaFile2 = new File("mods/modid-1.0.jar/assets/thkaguyamod/sounds/sukima.ogg");
			if(sukimaFile.exists() || sukimaFile2.exists())
			{
				sukimaWarpSE = "thkaguyamod:sukima";
			}

			//SEのボリュームのコンフィグ
			setSpellCardVol		= cfg.get("SE vol", "Spell Card Vol"   , 50).getInt();
			setMasterSparkVol	= cfg.get("SE vol", "Master Spark Vol", 50).getInt();
			setHisoutenVol		= cfg.get("SE vol", "Hisouten Vol"   , 20).getInt();
			setDownVol    		= cfg.get("SE vol", "Down Vol"   , 50).getInt();
			
			if(setSpellCardVol > 500)setSpellCardVol = 500;
			if(setMasterSparkVol > 500)setMasterSparkVol = 500;
			if(setHisoutenVol > 500)setHisoutenVol = 500;
			if(setDownVol > 500)setDownVol = 500;
			SpellCardVol = (float)setSpellCardVol / 100F;
			MasterSparkVol = (float)setMasterSparkVol / 100F;
			HisoutenVol = (float)setHisoutenVol / 100F;
			DownVol = (float)setDownVol / 100F;
			



			
			
			/*===================================弾幕関連========================================*/
			//弾幕攻撃で即死するモードにするか
			danmakuOneKillMode = cfg.get("Danmaku", "Danmaku 1 Kill Mode", false).getBoolean(false);
			
			//弾幕を扱うMOBが自然スポーンするか
			spawnDanmakuMob = cfg.get("Danmaku", "Spawn Danmaku Mob", true).getBoolean(false);
			
			//妖精が出現するか
			spawnFairy = cfg.get("Danmaku", "Spawn Fairy", true).getBoolean(false);
			
			//幽霊が出現するか
			spawnPhantom = cfg.get("Danmaku", "Spawn Phantom", true).getBoolean(false);
			
			//ボスMOBが出現するか
			spawnBoss = cfg.get("Danmaku", "Spawn Danmaku Mob Boss", true).getBoolean(false);
			
			//妖精の出現確率
			fairySpawnRate    = 100 - cfg.get("Danmaku", "Fairy Spawn Rate(%)"   , 50).getInt();
			
			//ハナビーパーが出現するか
			spawnHanabeeper = cfg.get("Other", "Spawn Hanabeeper", false).getBoolean(false);

			//妖精の出す弾幕のレベル デフォルトはノーマル
			danmakuLevel    = cfg.get("Danmaku", "Danmaku Level"   , 2).getInt();
			if(danmakuLevel > 4)
			{
				danmakuLevel = 4;
			}
			else if(danmakuLevel < 0)
			{
				danmakuLevel = 0;
			}
			//敵弾の命中の適格性
			danmakuAccuracy = (float)cfg.get("Danmaku", "Hit Rate of Enemy Danmaku(row=0,high=100)", 80).getInt();
			danmakuAccuracy = 360F * (1F - (danmakuAccuracy / 100F));
			
			/*===================================ゲームセッティング========================================*/

			
			//レシピなしのアイテムのレシピを追加するか
			recipelessItemsRecipe = cfg.get("Other", "Add Recipeless Items Recipe", false).getBoolean(false);
			
		}
		catch (Exception e)
		{
			/*
			* ファイルの読み込み/書き込み時に例外が発生した場合, ログに残す.
			*/
			FMLLog.severe("Error Message");
		}
		finally
		{
			/*
			* 最後に必ずセーブする.
			*/
			cfg.save();
		}
	}
}
