package thKaguyaMod.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

/** レシピの登録クラス */
public class THKaguyaRecipe {

	/** レシピの登録 */
	public static void setAllRecipes()
	{
		//赤真珠
		GameRegistry.addRecipe(THKaguyaItems.red_pearl,
							new Object[]{	"XXX",
											"XYX",
											"XXX",
							Character.valueOf('X'),Items.redstone,
							Character.valueOf('Y'),Items.ender_pearl });
		//青真珠
		GameRegistry.addRecipe(THKaguyaItems.blue_pearl,
							new Object[]{	"XXX",
											"XYX",
											"XXX",
							Character.valueOf('X'),new ItemStack(Items.dye, 1, 4),
							Character.valueOf('Y'),Items.ender_pearl });
		//緑真珠
		GameRegistry.addRecipe(THKaguyaItems.green_pearl,
							new Object[]{	"XXX",
											"XYX",
											"XXX",
							Character.valueOf('X'),Items.emerald,
							Character.valueOf('Y'),Items.ender_pearl });
		//黄真珠
		GameRegistry.addRecipe(THKaguyaItems.yellow_pearl,
							new Object[]{	"XXX",
											"XYX",
											"XXX",
							Character.valueOf('X'),Items.glowstone_dust,
							Character.valueOf('Y'),Items.ender_pearl });
		//紫真珠
		GameRegistry.addRecipe(THKaguyaItems.purple_pearl,
							new Object[]{	"XXX",
											"XYX",
											"XXX",
							Character.valueOf('X'),Blocks.obsidian,
							Character.valueOf('Y'),Items.ender_pearl });
		
		//水真珠
		GameRegistry.addRecipe(THKaguyaItems.aqua_pearl,
							new Object[]{	"XXX",
											"XYX",
											"XXX",
							Character.valueOf('X'),Blocks.ice,
							Character.valueOf('Y'),Items.ender_pearl });
		//橙真珠
		GameRegistry.addRecipe(THKaguyaItems.orange_pearl,
							new Object[]{	"XXX",
											"XYX",
											"XXX",
							Character.valueOf('X'),Items.blaze_powder,
							Character.valueOf('Y'),Items.ender_pearl });
		
		//蓬莱の玉の枝
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.hourai_Jeweled_branch),
							new Object[]{	"YRB",
											"GEP",
											" E ",
							Character.valueOf('Y'),THKaguyaItems.yellow_pearl,
							Character.valueOf('R'),THKaguyaItems.red_pearl,
							Character.valueOf('B'),THKaguyaItems.blue_pearl,
							Character.valueOf('G'),THKaguyaItems.green_pearl,
							Character.valueOf('P'),THKaguyaItems.purple_pearl,
							Character.valueOf('E'),Items.gold_ingot});
		//仏の御石の鉢
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.buddha_stone_bowl),
							new Object[]{	"DOD",
											"O O",
											"DOD",
							Character.valueOf('D'),Items.diamond,
							Character.valueOf('O'),Blocks.obsidian });
		//火鼠の皮衣
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.fire_rat_robe),
							new Object[]{	"BDB",
											"BPB",
											"B B",
							Character.valueOf('B'),Items.blaze_powder,
							Character.valueOf('D'),Items.diamond,
							Character.valueOf('P'),Items.leather_chestplate });
		//燕の子安貝
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.swallow_cowrie_shell),
							new Object[]{	"GGG",
											"FEF",
											"WWW",
							Character.valueOf('E'),Items.egg,
							Character.valueOf('W'),Items.wheat,
							Character.valueOf('F'),Items.feather,
							Character.valueOf('G'),Items.ghast_tear });
		//龍の頸の玉
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.dragon_neck_jewel),
							new Object[]{ 	"GWG",
											"LDL",
											"GWG",
							Character.valueOf('D'),THKaguyaItems.aqua_pearl,
							Character.valueOf('L'),Items.lava_bucket,
							Character.valueOf('W'),Items.water_bucket,
							Character.valueOf('G'),Blocks.glowstone});
		//金閣寺の一枚天井
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.kinkakuji),
							new Object[]{	"WWW",
											"GGG",
							Character.valueOf('W'),Blocks.planks,
							Character.valueOf('G'),Blocks.gold_block});
		//金閣寺の一枚天井を金ブロック3つに還元するレシピ
		GameRegistry.addRecipe(new ItemStack(Blocks.gold_block, 3),
							new Object[]{	"K",
							Character.valueOf('K'),THKaguyaItems.kinkakuji});
		
		//エイジャの赤石
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.aja_red_stone),
							new Object[]{	"RRR",
											"RRR",
											"RRR",
							Character.valueOf('R'),THKaguyaItems.red_pearl});
		
		//舟幽霊の柄杓
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.ship_ghost_dipper_fill),
							new Object[]{	" SB",
											" WS",
											"W  ",
							Character.valueOf('B'),Items.bowl,
							Character.valueOf('W'),Items.stick,
							Character.valueOf('S'),Blocks.soul_sand});
		//死神の大鎌
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.death_scythe),
							new Object[]{	"III",
											" S ",
											"S  ",
							Character.valueOf('I'),Items.iron_ingot,
							Character.valueOf('S'),Blocks.soul_sand});
		
		//悔悟の棒
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.remorse_rod),
							new Object[]{	"WPB",
											"WPB",
											" S ",
							Character.valueOf('W'),new ItemStack(Items.dye, 1, 0),
							Character.valueOf('B'),new ItemStack(Items.dye, 1, 15),
							Character.valueOf('S'),Items.stick,
							Character.valueOf('P'),Blocks.planks});
		
		//ナズーリンペンデュラム
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.nazrin_pendulum),
							new Object[]{	" I ",
											"I I",
											" D ",
							Character.valueOf('I'),Items.iron_ingot,
							Character.valueOf('D'),Items.diamond});
		
		//楼観剣
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.roukanken),
							new Object[]{	"  I",
											"FI ",
											"S  ",
							Character.valueOf('I'),Items.iron_ingot,
							Character.valueOf('F'),THKaguyaItems.soulTorch,
							Character.valueOf('S'),Items.iron_sword});
		
		//白楼剣
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.hakurouken),
								new Object[]{	"  I",
												"FI ",
												"S  ",
								Character.valueOf('I'),Items.iron_ingot,
								Character.valueOf('F'),THKaguyaItems.soulTorch,
								Character.valueOf('S'),Items.stick});
			
		//咲夜の懐中時計
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.sakuya_watch),
							new Object[]{	" Y ",
											"RCR",
											" R ",
							Character.valueOf('Y'), THKaguyaItems.yellow_pearl,
							Character.valueOf('R'), Items.redstone ,
							Character.valueOf('C'), Items.clock});
		//銀のナイフ 青
		GameRegistry.addRecipe(THKaguyaItems.silver_knife_blue,
							new Object[]{	"I ",
											" R",
							Character.valueOf('R'),new ItemStack(Items.dye, 1, 4) ,
							Character.valueOf('I'),Items.iron_ingot });
		
		//銀のナイフ 赤
		GameRegistry.addRecipe(THKaguyaItems.silver_knife_red,
							new Object[]{	"I ",
											" R",
							Character.valueOf('R'),Items.redstone ,
							Character.valueOf('I'),Items.iron_ingot });
		//銀のナイフ 緑
		GameRegistry.addRecipe(THKaguyaItems.silver_knife_green,
							new Object[]{	"I ",
											" R",
							Character.valueOf('R'),Items.emerald ,
							Character.valueOf('I'),Items.iron_ingot });
		
		//スキマ
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.gap, 2),
							new Object[]{	" KR",
											"KEK",
											"RK ",
							Character.valueOf('K'),Blocks.obsidian,
							Character.valueOf('R'),new ItemStack(Items.dye, 1, 1),
							Character.valueOf('E'),Items.ender_eye });
		//第三の眼
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.third_eye),
							new Object[]{	" X ",
											"XYX",
											" X ",
							Character.valueOf('X'),new ItemStack(Items.dye, 1, 1),
							Character.valueOf('Y'),Items.ender_eye });
		//魔人経巻
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.sorcerer_sutra_scroll),
							new Object[]{	"BRB",
											"BRB",
											"BRB",
							Character.valueOf('B'),Blocks.lapis_block,
							Character.valueOf('R'),Blocks.redstone_block});
		//緋想の剣
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.hisou_sword),
							new Object[]{	" PG",
											"PGP",
											"RP ",
							Character.valueOf('P'),Items.blaze_powder,
							Character.valueOf('G'),Blocks.glowstone,
							Character.valueOf('R'),Items.blaze_rod});
		//幽々子の扇
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.yuyuko_fan),
							new Object[]{	"RDL",
											"SPS",
											"TST",
							Character.valueOf('D'),Items.diamond,
							Character.valueOf('P'),Items.paper,
							Character.valueOf('S'),Items.stick,
							Character.valueOf('T'),THKaguyaItems.soulTorch,
							Character.valueOf('R'),Items.redstone,
							Character.valueOf('L'),new ItemStack(Items.dye, 1, 4)});
		//風祝のお祓い棒
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.wind_miko_stick),
							new Object[]{	" FP",
											" S ",
											"S  ",
							Character.valueOf('S'),Items.stick,
							Character.valueOf('P'),Items.paper,
							Character.valueOf('F'),Items.feather});
		//御柱
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.onbashira),
							new Object[]{	"PWP",
											" W ",
											" W ",
							Character.valueOf('W'),Blocks.log,
							Character.valueOf('P'),Items.paper});
		
		//ミニ八卦炉
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.mini_hakkero),
							new Object[]{	"IBI",
											"BFB",
											"IBI",
							Character.valueOf('I'),Items.iron_ingot,
							Character.valueOf('F'),Items.flint_and_steel,
							Character.valueOf('B'),Items.blaze_rod});
		//壁抜けの鑿
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.wall_passing_chisel),
							new Object[]{	"GSS",
											"E  ",
							Character.valueOf('G'),Items.gold_ingot,
							Character.valueOf('S'),Items.stick,
							Character.valueOf('E'),Items.ender_pearl});
		//豊聡耳の宝剣
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.toyosatomimi_sword),
							new Object[]{	"DID",
											"DID",
											"DGD",
							Character.valueOf('D'), Items.glowstone_dust,
							Character.valueOf('I'), Items.iron_ingot,
							Character.valueOf('G'), Items.gold_ingot});
		
		//閉ざした第三の眼
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.closed_third_eye),
							new Object[]{	"OOO",
											"OTO",
											"OOO",
							Character.valueOf('O'),Blocks.obsidian,
							Character.valueOf('T'),THKaguyaItems.third_eye});
		
		//ホーミングアミュレット
		GameRegistry.addRecipe(THKaguyaItems.homing_amulet,
							new Object[]{	"PPP",
											"PRP",
											"PPP",
							Character.valueOf('P'),Items.paper,
							Character.valueOf('R'),new ItemStack(Items.dye, 1, 1)});
		
		//拡散アミュレット
			GameRegistry.addRecipe(THKaguyaItems.diffusion_amulet,
								new Object[]{	"PPP",
												"PBP",
												"PPP",
								Character.valueOf('P'),Items.paper,
								Character.valueOf('B'),new ItemStack(Items.dye, 1, 4)});
		
		//幽香の日傘
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.yuuka_parasol),
							new Object[]{	"YRO",
											" IR",
											"I Y",
							Character.valueOf('O'),THKaguyaItems.orange_pearl,
							Character.valueOf('I'),Items.iron_ingot,
							Character.valueOf('Y'),Blocks.yellow_flower,
							Character.valueOf('R'),Blocks.red_flower});
		
		//河童の帽子
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.kappa_cap),
							new Object[]{	"LWL",
											"SCS",
							Character.valueOf('L'),Blocks.waterlily,
							Character.valueOf('W'),Items.water_bucket,
							Character.valueOf('S'),Items.slime_ball,
							Character.valueOf('C'),Items.leather_helmet });
		
		//伊吹瓢
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.ibuki_gourd),
							new Object[]{	"PLL",
											"LWL",
											"LLP",
							Character.valueOf('P'),Items.paper,
							Character.valueOf('L'),Blocks.lapis_block,
							Character.valueOf('W'),Items.nether_wart
							});
		
		//天狗の団扇
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.tengu_fan),
							new Object[]{	"ORO",
											"LRR",
											"SLO",
							Character.valueOf('O'),new ItemStack(Items.dye, 1, 14),
							Character.valueOf('R'),new ItemStack(Items.dye, 1, 1),
							Character.valueOf('L'),Blocks.leaves,
							Character.valueOf('S'),Items.stick
							});
		
		//河童の水鉄砲
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.kappa_water_pistol),
							new Object[]{	"LLL",
											"GGP",
											" TB",
							Character.valueOf('L'),new ItemStack(Items.dye, 1, 4),
							Character.valueOf('G'),Blocks.glass,
							Character.valueOf('P'),Blocks.piston,
							Character.valueOf('T'),Blocks.tripwire_hook,
							Character.valueOf('B'),Items.glass_bottle,
							});
		
		//制御棒
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.nuclear_control_rod),
							new Object[]{	"BOB",
											"BOB",
											"BOB",
							Character.valueOf('B'),Items.blaze_rod,
							Character.valueOf('O'),Blocks.obsidian});
		
		//陰陽玉
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.yin_yang_orb),
							new Object[]{	"RRQ",
											"REQ",
											"RQQ",
							Character.valueOf('E'),Items.ender_pearl,
							Character.valueOf('R'),Items.redstone,
							Character.valueOf('Q'),Items.quartz});
		
		//博麗のお祓い棒
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.hakurei_miko_stick),
							new Object[]{	"PPO",
											" SP",
											"S P",
							Character.valueOf('P'),Items.paper,
							Character.valueOf('S'),Items.stick,
							Character.valueOf('O'),THKaguyaItems.yin_yang_orb});
		
		//魔理沙の帽子
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.marisa_hat),
							new Object[]{	" B ",
											"WWW",
											"BBB",
							Character.valueOf('B'),new ItemStack(Blocks.wool, 1 ,15),
							Character.valueOf('W'),new ItemStack(Blocks.wool, 1, 0)});
		
		//諏訪子の帽子
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.suwako_hat),
							new Object[]{	"E E",
											" C ",
							Character.valueOf('E'),Items.ender_eye,
							Character.valueOf('C'),Items.leather_helmet });
		
		//スピア・ザ・グングニル
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.gungnir),
							new Object[]{	"LLR",
											" RL",
											"R L",
							Character.valueOf('L'),THKaguyaItems.middle_laser,
							Character.valueOf('R'),Blocks.redstone_block});
		
		//レーヴァテイン
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.laevateinn),
							new Object[]{	" PB",
											"POP",
											"GP ",
							Character.valueOf('P'),Items.blaze_powder,
							Character.valueOf('G'),Items.golden_sword,
							Character.valueOf('O'),THKaguyaItems.orange_pearl,
							Character.valueOf('B'),Items.blaze_rod});

		//パワーアップアイテム（大）
		GameRegistry.addRecipe(THKaguyaItems.power_up_item_big, 
							new Object[]{	"PPP",
											"PPP",
											"PPP",
							Character.valueOf('P'), THKaguyaItems.power_up_item_small});
		
		//パワーアップアイテム（大）を小にするレシピ
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.power_item, 9), 
							new Object[]{	"P",
							Character.valueOf('P'), THKaguyaItems.power_up_item_big});
		
		//弾の素
		GameRegistry.addShapelessRecipe(new ItemStack(THKaguyaItems.shot_material, 64),
							new Object[]{
							new ItemStack(Items.glowstone_dust, 1), new ItemStack(Items.gunpowder, 1)});
		
		//粒弾
		GameRegistry.addRecipe(THKaguyaItems.tiny_shot,
							new Object[]{	"B",
							Character.valueOf('B'),THKaguyaItems.shot_material });
		//小弾
		GameRegistry.addRecipe(THKaguyaItems.small_shot,
							new Object[]{	"BB",
							Character.valueOf('B'),THKaguyaItems.shot_material });
		//中弾
		GameRegistry.addRecipe(THKaguyaItems.medium_shot,
							new Object[]{	"BB",
											"BB",
							Character.valueOf('B'),THKaguyaItems.shot_material });
		//大弾
		GameRegistry.addRecipe(THKaguyaItems.big_shot,
							new Object[]{	"BBB",
											"BBB",
											"BBB",
							Character.valueOf('B'),THKaguyaItems.shot_material });
		//輪弾
		GameRegistry.addRecipe(THKaguyaItems.circle_shot,
							new Object[]{	" B ",
											"BBB",
											" B " ,
							Character.valueOf('B'),THKaguyaItems.shot_material });
		//星
		GameRegistry.addRecipe(THKaguyaItems.small_star_shot,
							new Object[]{	" B ",
											"BBB",
											"B B",
							Character.valueOf('B'),THKaguyaItems.shot_material });
		//星
		GameRegistry.addRecipe(THKaguyaItems.star_shot,
							new Object[]{	"BB",
							Character.valueOf('B'),THKaguyaItems.small_star_shot });
		//鱗弾
		GameRegistry.addRecipe(THKaguyaItems.scale_shot,
							new Object[]{	" B ",
											"B B",
											"B B",
							Character.valueOf('B'),THKaguyaItems.shot_material });
		//蝶弾
		GameRegistry.addRecipe(THKaguyaItems.butterfly_shot,
							new Object[]{	"B B",
											" B ",
											"B B",
							Character.valueOf('B'),THKaguyaItems.shot_material });
		//光弾
		GameRegistry.addRecipe(THKaguyaItems.light_shot,
							new Object[]{	"B B",
											"   ",
											"B B",
							Character.valueOf('B'),THKaguyaItems.shot_material });
		
		//ナイフ弾
		GameRegistry.addRecipe(THKaguyaItems.knife_shot,
							new Object[]{	"  B",
											"BB ",
											"BB ",
							Character.valueOf('B'),THKaguyaItems.shot_material });
		
		//ハート弾
		GameRegistry.addRecipe(THKaguyaItems.heart_shot,
							new Object[]{	"B B",
											"BBB",
											" B ",
							Character.valueOf('B'),THKaguyaItems.shot_material });
				
		//クナイ弾
		GameRegistry.addRecipe(THKaguyaItems.kunai_shot,
						new Object[]{	" BB",
										" BB",
										"B  ",
						Character.valueOf('B'),THKaguyaItems.shot_material });
		
		//札弾
		GameRegistry.addRecipe(THKaguyaItems.talisman_shot,
						new Object[]{	"B",
										"B",
						Character.valueOf('B'),THKaguyaItems.shot_material });
		
		//大光弾
		GameRegistry.addRecipe(THKaguyaItems.bigLight_shot,
						new Object[]{	"BB",
						Character.valueOf('B'),THKaguyaItems.light_shot });
		
		//米弾
		GameRegistry.addRecipe(THKaguyaItems.rice_shot,
				new Object[]{	" B",
								"B ",
				Character.valueOf('B'),THKaguyaItems.shot_material });
		
		//楕円弾
		GameRegistry.addRecipe(THKaguyaItems.oval_shot,
				new Object[]{	" BB",
								"BBB",
								"BB ",
				Character.valueOf('B'),THKaguyaItems.shot_material });
		
		//結晶弾
		GameRegistry.addRecipe(THKaguyaItems.crystal_shot,
				new Object[]{	"  B",
								" B ",
								"B  ",
				Character.valueOf('B'),THKaguyaItems.shot_material });
		
		//矢弾
		GameRegistry.addRecipe(THKaguyaItems.arrow_shot,
				new Object[]{	" B ",
								" B ",
								" B ",
				Character.valueOf('B'),THKaguyaItems.shot_material });
		
		//レーザー
		GameRegistry.addRecipe(THKaguyaItems.middle_laser,
							new Object[]{	"L",
											"L",
											"L",
							Character.valueOf('L'),THKaguyaItems.light_shot });
		//ショートレーザー
		GameRegistry.addRecipe(THKaguyaItems.short_laser,
							new Object[]{	"L",
											"L",
							Character.valueOf('L'), THKaguyaItems.light_shot });
		
		//ロングレーザー
		GameRegistry.addRecipe(THKaguyaItems.long_laser,
							new Object[]{	"L",
											"L",
							Character.valueOf('L'),THKaguyaItems.short_laser });

		//霊符「夢想封印」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(0),
							new Object[]{	"GOG",
											"OPO",
											"GOG",
							Character.valueOf('P'),Items.paper,
							Character.valueOf('O'),THKaguyaItems.big_shot,
							Character.valueOf('G'),Items.glowstone_dust});
		//恋符「マスタースパーク」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(1),
							new Object[]{	"SRS",
											"SPS",
											"SHS",
							Character.valueOf('P'),Items.paper,
							Character.valueOf('H'),THKaguyaItems.mini_hakkero,
							Character.valueOf('S'),THKaguyaItems.star_shot,
							Character.valueOf('R'),Blocks.redstone_block});
		//死蝶「華胥の永眠」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(2),
							new Object[]{	"BBB",
											"BPB",
											"BBB",
							Character.valueOf('P'),Items.paper,
							Character.valueOf('B'),THKaguyaItems.butterfly_shot});
		//星符「メテオニックシャワー」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(3),
							new Object[]{	"BBB",
											"BPB",
							Character.valueOf('P'),Items.paper,
							Character.valueOf('B'),THKaguyaItems.star_shot});
		//境符「粒と波の境界」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(4),
							new Object[]{	"LWL",
											"WPW",
											"LWL",
							Character.valueOf('P'),Items.paper,
							Character.valueOf('W'),Items.water_bucket,
							Character.valueOf('L'), THKaguyaItems.light_shot});
		//魍魎「二重黒死蝶」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(5),
							new Object[]{	"BZB",
											"ZPZ",
											"BZB",
							Character.valueOf('P'), Items.paper,
							Character.valueOf('Z'), Items.rotten_flesh,
							Character.valueOf('B'), THKaguyaItems.butterfly_shot});
		//紅符「スカーレットシュート」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(6),
							new Object[]{	"BBB",
											"MPM",
											"SSS",
							Character.valueOf('P'), Items.paper,
							Character.valueOf('S'), THKaguyaItems.small_shot,
							Character.valueOf('M'), THKaguyaItems.medium_shot,
							Character.valueOf('B'), THKaguyaItems.big_shot});
		
		//メイド秘技「殺人ドール」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(8),
							new Object[]{	" K ",
											"KPK",
											" C ",
							Character.valueOf('P'), Items.paper,
							Character.valueOf('K'), THKaguyaItems.knife_shot,
							Character.valueOf('C'), THKaguyaItems.sakuya_watch});
		
		//幻巣「飛行虫ネスト」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(10),
							new Object[]{	"L",
											"P",
											"S",
							Character.valueOf('P'), Items.paper,
							Character.valueOf('L'), THKaguyaItems.middle_laser,
							Character.valueOf('S'), THKaguyaItems.gap});
		
		//水符「河童のポロロッカ」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(11),
							new Object[]{	"WWW",
											"WPW",
											"LLL",
							Character.valueOf('P'), Items.paper,
							Character.valueOf('L'), THKaguyaItems.light_shot,
							Character.valueOf('W'), Items.water_bucket});
		
		//魔符「スターダストレヴァリエ」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(12),
							new Object[]{	"MSM",
											"SPS",
											"MSM",
							Character.valueOf('P'), Items.paper,
							Character.valueOf('M'), THKaguyaItems.small_star_shot,
							Character.valueOf('S'), THKaguyaItems.star_shot});
		
		//土着神「ケロちゃん風雨に負けず」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(13),
							new Object[]{	" L ",
											"LPL",
											"L L",
							Character.valueOf('P'), Items.paper,
							Character.valueOf('L'), THKaguyaItems.light_shot});
		
		//奇跡「ミラクルフルーツ」
			GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(14),
								new Object[]{	" S ",
												" P ",
												"   ",
								Character.valueOf('P'), Items.paper,
								Character.valueOf('S'), THKaguyaItems.star_shot});
			
		//奇跡「ファフロッキーズの奇跡」
			GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(15),
								new Object[]{	"   ",
												"SPS",
												"   ",
								Character.valueOf('P'), Items.paper,
								Character.valueOf('S'), THKaguyaItems.star_shot});
		
		//妖怪退治「妖力スポイラー」
			GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(16),
								new Object[]{	" S ",
												" P ",
												"S S",
								Character.valueOf('P'), Items.paper,
								Character.valueOf('S'), THKaguyaItems.star_shot});
			
		//開海「モーゼの奇跡」
			GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(17),
								new Object[]{	"S S",
												" P ",
												"S S",
								Character.valueOf('P'), Items.paper,
								Character.valueOf('S'), THKaguyaItems.star_shot});
		
		//大奇跡「八坂の神風」
			GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(18),
								new Object[]{	" S ",
												"SPS",
												"S S",
								Character.valueOf('P'), Items.paper,
								Character.valueOf('S'), THKaguyaItems.star_shot});
			
		//禁弾「スターボウブレイク」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(20),
							new Object[]{	" S ",
											"LPL",
											"L L",
							Character.valueOf('P'), Items.paper,
							Character.valueOf('S'), THKaguyaItems.star_shot,
							Character.valueOf('L'), THKaguyaItems.light_shot});
			
		//禁弾「カタディオプトリック」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(21),
							new Object[]{	"SSS",
											"MPM",
											"BBB",
							Character.valueOf('P'), Items.paper,
							Character.valueOf('S'), THKaguyaItems.small_shot,
							Character.valueOf('M'), THKaguyaItems.medium_shot,
							Character.valueOf('B'), THKaguyaItems.big_shot});
		
		//祟符「ミシャグジさま」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(22),
				new Object[]{	"RRR",
								"RPR",
								"RRR",
				Character.valueOf('P'), Items.paper,
				Character.valueOf('R'), THKaguyaItems.rice_shot});
		
		//「レッドマジック」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(23),
							new Object[]{	"SSB",
											"SPS",
											"BSS",
							Character.valueOf('P'), Items.paper,
							Character.valueOf('S'), THKaguyaItems.small_shot,
							Character.valueOf('B'), THKaguyaItems.big_shot});
		
		//奇術「エターナルミーク」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(24),
							new Object[]{	"KKK",
											" P ",
							Character.valueOf('P'), Items.paper,
							Character.valueOf('K'), THKaguyaItems.knife_shot});
		
		//恋符「ノンディレクショナルレーザー」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(25),
							new Object[]{	" L ",
											"LPL",
											"L L",
							Character.valueOf('P'), Items.paper,
							Character.valueOf('L'), THKaguyaItems.middle_laser});
		
		//幻想「花鳥風月、嘯風弄月」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(26),
							new Object[]{	"RYR",
											"YPY",
											"RYR",
							Character.valueOf('P'), Items.paper,
							Character.valueOf('Y'),Blocks.yellow_flower,
							Character.valueOf('R'),Blocks.red_flower});
		
		//蓬莱「フジヤマヴォルケイノ」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(33),
							new Object[]{	"LLL",
											"LPL",
											"L L",
							Character.valueOf('P'), Items.paper,
							Character.valueOf('L'), Items.lava_bucket});
		
		//弾幕作業台（弾）
		GameRegistry.addRecipe(new ItemStack(THKaguyaBlocks.danmaku_crafting_table, 1, 0), //THKaguyaBlocks.danmaku_crafting_table_shot,
							new Object[]{	"DDD",
											"DCD",
											"DDD",
							Character.valueOf('D'), THKaguyaItems.shot_material,
							Character.valueOf('C'), Blocks.crafting_table});
		
		//弾幕作業台（レーザー）
		GameRegistry.addRecipe(new ItemStack(THKaguyaBlocks.danmaku_crafting_table, 1, 1), //THKaguyaBlocks.danmaku_crafting_table_laser,
							new Object[]{	"DDD",
											"DCD",
											"DDD",
							Character.valueOf('D'), THKaguyaItems.light_shot,
							Character.valueOf('C'), Blocks.crafting_table});
	}
	
	/** レシピのないアイテムのレシピ追加する場合の登録 */
	public static void setRecipelessItemsRecipe()
	{
		//魔法の箒
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.magic_bloom),
							new Object[]{	" RS",
											"WSR",
											"MW ",
							Character.valueOf('R'),Blocks.redstone_block,
							Character.valueOf('S'),Items.stick,
							Character.valueOf('W'),Items.wheat,
							Character.valueOf('M'),THKaguyaItems.mini_hakkero });
		
		//アイシクルソード
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.icicle_sword),
							new Object[]{	"  I",
											"II ",
											"II ",
							Character.valueOf('I'),Blocks.packed_ice });
		
		//打ち出の小槌
		GameRegistry.addRecipe(new ItemStack(THKaguyaItems.miracle_mallet),
							new Object[]{	"GGG",
											"RSR",
											" S ",
							Character.valueOf('R'),Blocks.redstone_block,
							Character.valueOf('S'),Items.stick,
							Character.valueOf('G'),Blocks.gold_block});
		
		//凍符「パーフェクトフリーズ」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(9),
				new Object[]{	"III",
								"IPI",
								"III",
							Character.valueOf('P'), Items.paper,
							Character.valueOf('I'), Blocks.ice});
		
		//氷符「アイシクルフォール」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(19),
				new Object[]{	"I I",
								"CPC",
								"C C",
							Character.valueOf('P'), Items.paper,
							Character.valueOf('C'), THKaguyaItems.crystal_shot,
							Character.valueOf('I'), Blocks.ice});
		
		//月符「ムーンライトレイ」
		GameRegistry.addRecipe(THKaguyaItems.getSpellCardForId(28),
				new Object[]{	" B ",
								"LPL",
							Character.valueOf('P'), Items.paper,
							Character.valueOf('B'), THKaguyaItems.bigLight_shot,
							Character.valueOf('L'), THKaguyaItems.long_laser});
		
		//水真珠
		GameRegistry.addRecipe(THKaguyaItems.aqua_pearl,
							new Object[]{	"XXX",
											"XYX",
											"XXX",
							Character.valueOf('X'),Blocks.ice,
							Character.valueOf('Y'),Items.ender_pearl });
		
		//白真珠
		GameRegistry.addRecipe(THKaguyaItems.white_pearl,
							new Object[]{	"XXX",
											"XYX",
											"XXX",
							Character.valueOf('X'),Items.quartz,
							Character.valueOf('Y'),Items.ender_pearl });
	}

}
