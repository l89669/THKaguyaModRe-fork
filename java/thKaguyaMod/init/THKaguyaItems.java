package thKaguyaMod.init;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import thKaguyaMod.THKaguyaCore;
import thKaguyaMod.item.ItemAjaRedStone;
import thKaguyaMod.item.ItemBloodthirstyOnmyoudama;
import thKaguyaMod.item.ItemBuddhaStoneBowl;
import thKaguyaMod.item.ItemByoukiheiyuMamori;
import thKaguyaMod.item.ItemClosedThirdEye;
import thKaguyaMod.item.ItemCursedDecoyDoll;
import thKaguyaMod.item.ItemDeathScythe;
import thKaguyaMod.item.ItemDragonNeckJewel;
import thKaguyaMod.item.ItemFireRatRobe;
import thKaguyaMod.item.ItemGapFoldingUmbrella;
import thKaguyaMod.item.ItemHakurouken;
import thKaguyaMod.item.ItemHeavenlyPeach;
import thKaguyaMod.item.ItemHisou;
import thKaguyaMod.item.ItemHisyaku;
import thKaguyaMod.item.ItemHomingAmulet;
import thKaguyaMod.item.ItemHouraiJeweledBranch;
import thKaguyaMod.item.ItemHouraiPearl;
import thKaguyaMod.item.ItemIbukihyou;
import thKaguyaMod.item.ItemIcicleSword;
import thKaguyaMod.item.ItemKabenuke;
import thKaguyaMod.item.ItemKaigo;
import thKaguyaMod.item.ItemKappaCap;
import thKaguyaMod.item.ItemKappaWaterPistol;
import thKaguyaMod.item.ItemKerobou;
import thKaguyaMod.item.ItemKinkakuzi;
import thKaguyaMod.item.ItemLaevateinn;
import thKaguyaMod.item.ItemMarisaBroom;
import thKaguyaMod.item.ItemMarisaHat;
import thKaguyaMod.item.ItemMazinKyoukan;
import thKaguyaMod.item.ItemMikoSword;
import thKaguyaMod.item.ItemMiniHakkero;
import thKaguyaMod.item.ItemNuclearControlRod;
import thKaguyaMod.item.ItemOharaibouR;
import thKaguyaMod.item.ItemOharaibouS;
import thKaguyaMod.item.ItemOnbashira;
import thKaguyaMod.item.ItemOnmyoudama;
import thKaguyaMod.item.ItemPendulum;
import thKaguyaMod.item.ItemRoukanSenpuuzin;
import thKaguyaMod.item.ItemRoukanken;
import thKaguyaMod.item.ItemSakuyaStopWatch;
import thKaguyaMod.item.ItemSakuyaWatch;
import thKaguyaMod.item.ItemShotMaterial;
import thKaguyaMod.item.ItemSilverKnife;
import thKaguyaMod.item.ItemSoulTorch;
import thKaguyaMod.item.ItemSpearTheGungnir;
import thKaguyaMod.item.ItemSpiritualStrikeTalisman;
import thKaguyaMod.item.ItemSukima;
import thKaguyaMod.item.ItemSwallowCowrieShell;
import thKaguyaMod.item.ItemTHBombItem;
import thKaguyaMod.item.ItemTHExtendItem;
import thKaguyaMod.item.ItemTHLaser;
import thKaguyaMod.item.ItemTHPointItem;
import thKaguyaMod.item.ItemTHPowerItem;
import thKaguyaMod.item.ItemTHShot;
import thKaguyaMod.item.ItemTHSpellCard;
import thKaguyaMod.item.ItemTenguFan;
import thKaguyaMod.item.ItemThirdEye;
import thKaguyaMod.item.ItemUchide;
import thKaguyaMod.item.ItemYuukaParasol;
import thKaguyaMod.item.ItemYuyukoFan;
import thKaguyaMod.registry.SpellCardRegistry;

/** アイテムの登録クラス */
public class THKaguyaItems
{ 
	/* 五つの難題 ＋ 新難題 */
	/** 蓬莱の玉の枝 */
	public static Item hourai_Jeweled_branch;
	/** 龍の頸の玉 */
	public static Item dragon_neck_jewel;
	/** 仏の御石の鉢 */
	public static Item buddha_stone_bowl;
	/** 火鼠の皮衣 */
	public static Item fire_rat_robe;
	/** 燕の子安貝 */
	public static Item swallow_cowrie_shell;
	/** 色真珠 */
	public static Item hourai_pearl;
	/** 金閣寺の一枚天井 */
	public static Item kinkakuji;
	/** エイジャの赤石 */
	public static Item aja_red_stone;
	
	
	
	/* 霊夢と魔理沙のアイテム */
	/** 博麗のお祓い棒 */
	public static Item hakurei_miko_stick;
	/** ホーミングアミュレット */
	public static Item amulet;
	/** 陰陽玉 */
	public static Item yin_yang_orb;
	/** 血に餓えた陰陽玉 */
	public static Item bloodthirsty_yin_yang_orb ;
	/** 魔法の箒 */
	public static Item magic_bloom;
	/** ミニ八卦炉 */
	public static Item mini_hakkero;
	/** 魔理沙の帽子 */
	public static Item marisa_hat;
	
	
	
	/* 東方紅魔郷 */
	/** アイシクルソード */
	public static Item icicle_sword;
	/** 銀のナイフ */
	public static Item silver_knife;
	/** 咲夜の懐中時計 */
	public static Item sakuya_watch;
	/** ストップウォッチ */
	public static Item sakuya_stopwatch;
	/** スピア・ザ・グングニル */
	public static Item gungnir;
	/** レーヴァテイン */
	public static Item laevateinn;
	
	
	
	/* 東方妖々夢 */
	/** 呪いのデコイ人形 */
	public static Item cursedDecoyDoll;
	/** 白楼剣 */
	public static Item hakurouken;
	/** 楼観剣 */
	public static Item roukanken;
	/** 楼観旋風刃 */
	public static Item roukanSenpuuzin;
	/** 桜花扇 */
	public static Item yuyuko_fan;
	/** 人魂灯 */
	public static Item soulTorch;
	/** スキマ */
	public static Item gap;
	/** 隙間の折りたたみ傘 */
	public static Item gapFoldingUmbrella;
	
	
	
	/* 東方萃夢想 */
	/** 伊吹瓢 */
	public static Item ibuki_gourd;
	
	
	
	/* 東方永夜抄 */
	
	
	
	/* 東方花映塚 */
	/** 幽香の傘 */
	public static Item yuuka_parasol;
	/** 死神の大鎌 */
	public static Item death_scythe;
	/** 悔悟の棒 */
	public static Item remorse_rod;
	
	
	
	/* 東方文花帖 */
	/** 天狗の団扇 */
	public static Item tengu_fan;
	
	
	
	/* 東方風神録 */
	/** 河童の帽子 */
	public static Item kappa_cap;
	/** 河童の水鉄砲 */
	public static Item kappa_water_pistol;
	/** 風祝のお祓い帽 */
	public static Item wind_miko_stick;
	/** 病気平癒守 */
	public static Item illness_recovery_charm;
	/** 御柱 */
	public static Item onbashira;
	/** 諏訪子の帽子 */
	public static Item suwako_hat;
	
	
	
	/* 東方緋想天 */
	/** 天界の桃 */
	public static Item heavenly_peach;
	/** 緋想の剣 */
	public static Item hisou_sword;
	
	
	
	/* 東方地霊殿 */
	/** 第三の眼 */
	public static Item third_eye;
	/** 制御棒 */
	public static Item nuclear_control_rod;
	/** 閉じた第三の眼 */
	public static Item closed_third_eye;
	
	
	
	/* 東方星蓮船 */
	/** ナズーリンペンデュラム */
	public static Item nazrin_pendulum;
	/** 舟幽霊の柄杓（空） */
	public static Item ship_ghost_dipper_empty;
	/** 舟幽霊の柄杓（水入り） */
	public static Item ship_ghost_dipper_fill;
	/** 魔人経巻 */
	public static Item sorcerer_sutra_scroll;
	
	
	
	/* 東方神霊廟 */
	/** 壁抜けの鑿 */
	public static Item wall_passing_chisel;
	/** 豊聡耳の宝剣 */
	public static Item toyosatomimi_sword;
	
	
	
	/* 東方輝針城 */
	/** 打ち出の小槌 */
	public static Item miracle_mallet;
	
	/* キャラ分類できないアイテム */
	/** 霊撃札 */
	public static Item spiritual_strike_talisman;
	
	/* 基本アイテム */
	/** 弾幕の素 */
	public static Item shot_material;
	/** 単発ショット */
	public static Item shot_item;
	/** 単発レーザーアイテム */
	public static Item laser_item;
	/** パワーアイテム */
	public static Item power_item;
	/** 点符アイテム */
	public static Item point_item;
	/** ボムアイテム */
	public static Item bomb_item;
	/** エクステンドアイテム */
	public static Item extend_item;
	/** スペルカード */
	public static Item spell_card;

	
	
	/* 未実装 */
	/** 毘沙門天の宝塔 */
	//public static Item jeweled_pagoda = new ItemHoutou(houtouItemId).setUnlocalizedName("jeweledPagoda");
	/** 弾幕記憶アイテム */
	//public static Item danmaku_memo_item;
	/** フランの持っている棒 */
	//public static Item frandre_rod = new ItemFrandreRod(frandreRodItemId, EnumToolMaterial.IRON).setUnlocalizedName("frandreRod");
	/** ベントラーアイテム */
	//public static Item bentlar_item;
	
	//==========================メタデータで異なるアイテム===============================//
	/** 赤真珠 */
	public static ItemStack red_pearl;
	/** 青真珠 */
	public static ItemStack blue_pearl;
	/** 緑真珠 */
	public static ItemStack green_pearl;
	/** 黄真珠 */
	public static ItemStack yellow_pearl;
	/** 紫真珠 */
	public static ItemStack purple_pearl;
	/** 水真珠 */
	public static ItemStack aqua_pearl;
	/** 橙真珠 */
	public static ItemStack orange_pearl;
	/** 白真珠 */
	public static ItemStack white_pearl;
	/** 銀のナイフ 青 */
	public static ItemStack silver_knife_blue;
	/** 銀のナイフ 赤 */
	public static ItemStack silver_knife_red;
	/** 銀のナイフ 緑 */
	public static ItemStack silver_knife_green;
	/** タイムパラドックスナイフ */
	public static ItemStack silver_knife_white;
	/** 小弾 */
	public static ItemStack small_shot;
	/** 粒弾 */
	public static ItemStack tiny_shot;
	/** 中弾 */
	public static ItemStack medium_shot;
	/** 大弾 */
	public static ItemStack big_shot;
	/** 星弾 */
	public static ItemStack star_shot;
	/** 小星弾 */
	public static ItemStack small_star_shot;
	/** 輪弾 */
	public static ItemStack circle_shot;
	/** 鱗弾 */
	public static ItemStack scale_shot;
	/** 蝶弾 */
	public static ItemStack butterfly_shot;
	/** 光弾 */
	public static ItemStack light_shot;
	/** ナイフ弾 */
	public static ItemStack knife_shot;
	/** ハート弾 */
	public static ItemStack heart_shot;
	/** クナイ弾 */
	public static ItemStack kunai_shot;
	/** 札弾 */
	public static ItemStack talisman_shot;
	/** 大光弾 */
	public static ItemStack bigLight_shot;
	/** 米弾 */
	public static ItemStack rice_shot;
	/** 楕円弾 */
	public static ItemStack oval_shot;
	/** 結晶弾 */
	public static ItemStack crystal_shot;
	/** 矢弾 */
	public static ItemStack arrow_shot;
	/** レーザー */
	public static ItemStack middle_laser;
	/** ショートレーザー */
	public static ItemStack short_laser;
	/** ロングレーザー */
	public static ItemStack long_laser;
	/** パワーアップアイテム 小 */
	public static ItemStack power_up_item_small;
	/** パワーアップアイテム 大 */
	public static ItemStack power_up_item_big;
	/** ホーミングアミュレット */
	public static ItemStack homing_amulet;
	/** 拡散アミュレット */
	public static ItemStack diffusion_amulet;
	/** 各種スペルカード */
	public static ItemStack[] spellcard = new ItemStack[SpellCardRegistry.getNumberOfSpellCard()];
	public static int[] spellcardNo = new int[SpellCardRegistry.getNumberOfSpellCard()];
	
	public static ItemStack danmakuCraftingTable_shot;
	public static ItemStack danmakuCraftingTable_laser;
	
	public static ItemStack divineSpirit_red;
	public static ItemStack divineSpirit_blue;
	public static ItemStack divineSpirit_green;
	public static ItemStack divineSpirit_yellow;
	public static ItemStack divineSpirit_purple;
	public static ItemStack divineSpirit_aqua;
	public static ItemStack divineSpirit_orange;
	public static ItemStack divineSpirit_white;
	
	
	//新しいツール素材の性能を設定
	/** アイシクルソードの素材性能 */
	public static ToolMaterial toolMaterial_Icicle = EnumHelper.addToolMaterial("icicle", 0, 30, 0.0F, 2.0F, 0);
	/** レーヴァテインの素材性能 */
	public static ToolMaterial toolMaterial_Laevateinn = EnumHelper.addToolMaterial("laevateinn", 0, 95, 9.0F, 3.0F, 0);
	/** 白楼剣の素材性能 */
	public static ToolMaterial toolMaterial_Hakurouken = EnumHelper.addToolMaterial("hakurou", 0, 100, 0.0F, 0.0F, 0);
	/** 楼観剣の素材性能 */
	public static ToolMaterial toolMaterial_Roukanken = EnumHelper.addToolMaterial("roukanken", 0, 251, 4.0F, 3.0F, 0);
	/** 悔悟の棒の素材性能 */
	public static ToolMaterial toolMaterial_Kaigo = EnumHelper.addToolMaterial("kaigo", 0, 1, 0.0F, -4.0F, 0);	
	/** 緋想の剣の素材性能 */
	public static ToolMaterial toolMaterial_Hisou = EnumHelper.addToolMaterial("hisou", 0, 200, 4.0F, 0.0F, 0);

	//新しいアーマー素材の性能を設定
	/** 火鼠の皮衣の素材性能 */
	public static final ArmorMaterial armorMaterial_Hinezumi = EnumHelper.addArmorMaterial("HINEZUMI",1, new int[] {1, 2, 2, 2}, 0);
	/** 魔理沙の帽子の素材性能 */
	public static final ArmorMaterial armorMaterial_Marisa   = EnumHelper.addArmorMaterial("MARISA", 1, new int[]{1,  1, 1, 1}, 0);
	/** 諏訪子の帽子の素材性能 */
	public static final ArmorMaterial armorMaterial_Suwako   = EnumHelper.addArmorMaterial("SUWAKO", 1, new int[] {1, 1, 1,1}, 0);
	
	/** アイテムを登録 */
	public static void setItems()
	{
		/* 五つの難題 ＋ 新難題 */
		//蓬莱の玉の枝
		hourai_Jeweled_branch = new ItemHouraiJeweledBranch().setUnlocalizedName("houraiJewelBranch");
		//龍の頸の玉
		dragon_neck_jewel = new ItemDragonNeckJewel().setUnlocalizedName("dragonNeckJewel");
		//仏の御石の鉢
		buddha_stone_bowl = new ItemBuddhaStoneBowl(ToolMaterial.STONE).setUnlocalizedName("buddhaStoneBowl");
		//火鼠の皮衣
		fire_rat_robe = new ItemFireRatRobe(armorMaterial_Hinezumi, THKaguyaCore.hinezumiIndex, 1).setUnlocalizedName("fireRatRobe");
		//燕の子安貝
		swallow_cowrie_shell = new ItemSwallowCowrieShell( 0, false).setUnlocalizedName("swallowCowrieShell");
		//色真珠
		hourai_pearl = new ItemHouraiPearl().setUnlocalizedName("houraiPearl");
			red_pearl    = new ItemStack(hourai_pearl,1, 0);
			blue_pearl   = new ItemStack(hourai_pearl,1, 1);
			green_pearl  = new ItemStack(hourai_pearl,1, 2);
			yellow_pearl = new ItemStack(hourai_pearl,1, 3);
			purple_pearl = new ItemStack(hourai_pearl,1, 4);
			aqua_pearl   = new ItemStack(hourai_pearl,1, 5);
			orange_pearl = new ItemStack(hourai_pearl,1, 6);
			white_pearl  = new ItemStack(hourai_pearl,1, 7);
		//金閣寺の一枚天井
		kinkakuji = new ItemKinkakuzi().setUnlocalizedName("kinkaku-ji");
		//エイジャの赤石
		aja_red_stone = new ItemAjaRedStone().setUnlocalizedName("ajaRedStone");
		
		
		
		/* 霊夢と魔理沙のアイテム */
		//博麗のお祓い棒
		hakurei_miko_stick = new ItemOharaibouR().setUnlocalizedName("hakureiMikoStick");
		//ホーミングアミュレット
		amulet = new ItemHomingAmulet().setUnlocalizedName("homingAmulet");
			homing_amulet = new ItemStack(amulet, 16, 0);
			diffusion_amulet = new ItemStack(amulet, 16, 1);
		//陰陽玉
		yin_yang_orb = new ItemOnmyoudama().setUnlocalizedName("yinYangOrb");
		//yin_yang_orb = new ItemOnmyoudama(armorMaterial_Marisa, THKaguyaCore.marisaIndex, 0).setUnlocalizedName("yinYangOrb");
		//血に餓えた陰陽玉
		bloodthirsty_yin_yang_orb = new ItemBloodthirstyOnmyoudama().setUnlocalizedName("bloodthirstyYinYangOrb");
		//魔法の箒
		magic_bloom = new ItemMarisaBroom().setUnlocalizedName("magicBroom");
		//ミニ八卦炉
		mini_hakkero = new ItemMiniHakkero().setUnlocalizedName("miniHakkero");
		//魔理沙の帽子
		marisa_hat = new ItemMarisaHat(armorMaterial_Marisa, THKaguyaCore.marisaIndex, 0).setUnlocalizedName("marisaHat");
		
		
		
		/* 東方紅魔郷 */
		// アイシクルソード
		icicle_sword = new ItemIcicleSword(toolMaterial_Icicle).setUnlocalizedName("icicleSword");
		// 銀のナイフ
		silver_knife = new ItemSilverKnife().setUnlocalizedName("silverKnife");
			silver_knife_blue  = new ItemStack(silver_knife,4, 0);
			silver_knife_red   = new ItemStack(silver_knife,2, 1);
			silver_knife_green = new ItemStack(silver_knife,2, 2);
			silver_knife_white = new ItemStack(silver_knife,1, 3);
		// 咲夜の懐中時計
		sakuya_watch = new ItemSakuyaWatch().setUnlocalizedName("sakuyaWatch");
		// ストップウォッチ
		sakuya_stopwatch = new ItemSakuyaStopWatch().setUnlocalizedName("sakuyaStopwatch");
		// スピア・ザ・グングニル
		gungnir = new ItemSpearTheGungnir().setUnlocalizedName("spearTheGungnir");
		// レーヴァテイン
		laevateinn = new ItemLaevateinn(toolMaterial_Laevateinn).setUnlocalizedName("laevateinn");
		
		
		
		/* 東方妖々夢 */
		// 呪いのデコイ人形
		cursedDecoyDoll = new ItemCursedDecoyDoll().setUnlocalizedName("cursedDecoyDoll");
		// 白楼剣
		hakurouken = new ItemHakurouken(toolMaterial_Hakurouken).setUnlocalizedName("hakurouken");
		// 楼観剣
		roukanken = new ItemRoukanken(toolMaterial_Roukanken).setUnlocalizedName("roukanken");
		// 楼観旋風刃
		roukanSenpuuzin = new ItemRoukanSenpuuzin(toolMaterial_Roukanken).setUnlocalizedName("roukankenSenpuuzin");
		// 桜花扇
		yuyuko_fan = new ItemYuyukoFan().setUnlocalizedName("yuyukoFan");
		// 人魂灯
		soulTorch = new ItemSoulTorch().setUnlocalizedName("soulTorch");
		// スキマ
		gap = new ItemSukima().setUnlocalizedName("gap");
		// 隙間の折りたたみ傘
		gapFoldingUmbrella = new ItemGapFoldingUmbrella().setUnlocalizedName("gapFoldingUmbrella");
		
		
		
		/* 東方萃夢想 */
		//伊吹瓢
		ibuki_gourd = new ItemIbukihyou(0, false).setUnlocalizedName("ibukiGourd");
		
		
		
		/* 東方永夜抄 */
		
		
		
		/* 東方花映塚 */
		//幽香の傘
		yuuka_parasol = new ItemYuukaParasol().setUnlocalizedName("yuukaParasol");
		//死神の大鎌
		death_scythe = new ItemDeathScythe(ToolMaterial.IRON).setUnlocalizedName("deathScythe");
		//悔悟の棒
		remorse_rod = new ItemKaigo(toolMaterial_Kaigo).setUnlocalizedName("remorseRod");
		
		
		
		/* 東方文花帖 */
		//天狗の団扇
		tengu_fan = new ItemTenguFan().setUnlocalizedName("tenguFan");
		
		
		
		/* 東方風神録 */
		//河童の帽子
		kappa_cap = new ItemKappaCap(armorMaterial_Hinezumi, THKaguyaCore.hinezumiIndex, 0).setUnlocalizedName("kappaCap");
		//河童の水鉄砲
		kappa_water_pistol = new ItemKappaWaterPistol().setUnlocalizedName("kappaWaterPistol");
		//風祝のお祓い帽
		wind_miko_stick = new ItemOharaibouS().setUnlocalizedName("windMikoStick");
		//病気平癒守
		illness_recovery_charm = new ItemByoukiheiyuMamori().setUnlocalizedName("byoukiheiyuMamori");
		//御柱
		onbashira = new ItemOnbashira(ToolMaterial.WOOD).setUnlocalizedName("onbashira");
		//諏訪子の帽子
		suwako_hat = new ItemKerobou(armorMaterial_Suwako, THKaguyaCore.suwakoIndex, 0).setUnlocalizedName("suwakoHat");
		
		
		
		/* 東方緋想天 */
		//天界の桃
		heavenly_peach = new ItemHeavenlyPeach(2, true).setUnlocalizedName("heavenlyPeach");
		//緋想の剣
		hisou_sword = new ItemHisou(toolMaterial_Hisou).setUnlocalizedName("scarletPerceptionSword");
		
		
		
		/* 東方地霊殿 */
		//第三の眼
		third_eye = new ItemThirdEye().setUnlocalizedName("thirdEye");
		//制御棒
		nuclear_control_rod = new ItemNuclearControlRod().setUnlocalizedName("nuclearControlRod");
		//閉じた第三の眼
		closed_third_eye = new ItemClosedThirdEye().setUnlocalizedName("closedThirdEye");
		
		
		
		/* 東方星蓮船 */
		//ナズーリンペンデュラム
		nazrin_pendulum = new ItemPendulum().setUnlocalizedName("nazrinPendulum");
		//舟幽霊の柄杓（空）
		ship_ghost_dipper_empty = new ItemHisyaku(1).setUnlocalizedName("shipGhostDipperEmpty");
		//舟幽霊の柄杓（水入り）
		ship_ghost_dipper_fill = new ItemHisyaku(0).setUnlocalizedName("shipGhostDipper");
		//魔人経巻
		sorcerer_sutra_scroll = new ItemMazinKyoukan().setUnlocalizedName("sorcererSutraScroll");
		
		
		
		/* 東方神霊廟 */
		//壁抜けの鑿
		wall_passing_chisel = new ItemKabenuke().setUnlocalizedName("wallPassingChisel");
		//豊聡耳の宝剣
		toyosatomimi_sword = new ItemMikoSword(ToolMaterial.IRON).setUnlocalizedName("toyosatomimiSword");
		
		
		
		/* 東方輝針城 */
		//打ち出の小槌
		miracle_mallet = new ItemUchide().setUnlocalizedName("miracleMallet");
		
		
		/* キャラ分類できないアイテム */
		//霊撃札
		spiritual_strike_talisman = new ItemSpiritualStrikeTalisman().setUnlocalizedName("spiritualStrikeTalisman");
		
		
		
		/* 基本アイテム */
		//弾幕の素
		shot_material = new ItemShotMaterial().setUnlocalizedName("shotMaterial");
		//単発ショット
		shot_item = new ItemTHShot().setUnlocalizedName("thShot");
			small_shot = new ItemStack(shot_item, 1, 0);
			tiny_shot  = new ItemStack(shot_item, 1, 1);
			medium_shot = new ItemStack(shot_item, 2, 2);
			big_shot    = new ItemStack(shot_item, 3, 3);
			star_shot   = new ItemStack(shot_item, 1, 4);
			small_star_shot = new ItemStack(shot_item, 5, 5);
			circle_shot = new ItemStack(shot_item, 3, 6);
			scale_shot = new ItemStack(shot_item, 4, 7);
			butterfly_shot = new ItemStack(shot_item, 2, 8);
			light_shot = new ItemStack(shot_item, 1, 9);
			knife_shot = new ItemStack(shot_item, 2, 10);
			heart_shot = new ItemStack(shot_item, 2, 11);
			kunai_shot = new ItemStack(shot_item, 3, 12);
			talisman_shot = new ItemStack(shot_item, 2, 13);
			bigLight_shot = new ItemStack(shot_item, 1, 14);
			rice_shot = new ItemStack(shot_item, 2, 15);
			oval_shot = new ItemStack(shot_item, 2, 16);
			crystal_shot = new ItemStack(shot_item, 2, 17);
			arrow_shot = new ItemStack(shot_item, 1, 18);
		//単発レーザーアイテム
		laser_item = new ItemTHLaser().setUnlocalizedName("thLaser");
			middle_laser      = new ItemStack(laser_item, 1, 0);
			short_laser      = new ItemStack(laser_item, 1, 1);
			long_laser      = new ItemStack(laser_item, 1, 2);
		//パワーアイテム
		power_item = new ItemTHPowerItem().setUnlocalizedName("thPowerItem");
			power_up_item_small = new ItemStack(power_item, 1, 0);
			power_up_item_big   = new ItemStack(power_item, 1, 1);
		//点符アイテム
		point_item = new ItemTHPointItem().setUnlocalizedName("thPointItem");
		//ボムアイテム
		bomb_item = new ItemTHBombItem().setUnlocalizedName("thBombItem");
		//エクステンドアイテム
		extend_item = new ItemTHExtendItem().setUnlocalizedName("thExtendItem");
		//スペルカード
		spell_card = new ItemTHSpellCard().setUnlocalizedName("thSpellCard");
			int i = 0;
			for(String name: SpellCardRegistry.spellcardID.keySet())
			{
				spellcard[i] = new ItemStack(spell_card, 1, SpellCardRegistry.spellcardID.get(name));
				spellcardNo[i] = SpellCardRegistry.spellcardID.get(name);
				i++;
			}
}
	
	/**
	 * スペルカードIDからそのスペルカードのItemStackを取得する
	 * @param id
	 * @return
	 */
	public static ItemStack getSpellCardForId(int id)
	{
		for(int i = 0; i < SpellCardRegistry.getNumberOfSpellCard(); i++)
		{
			if(id == spellcardNo[i])
			{
				return spellcard[i];
			}
		}
		return null;
	}
	
	
}
