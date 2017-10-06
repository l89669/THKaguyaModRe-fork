package thKaguyaMod;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import thKaguyaMod.entity.EntityDivineSpirit;
import thKaguyaMod.entity.EntityShotMaterial;
import thKaguyaMod.entity.EntityTHItem;
import thKaguyaMod.entity.effect.EntityAjaRedStoneEffect;
import thKaguyaMod.entity.effect.EntityCirnoIceBox;
import thKaguyaMod.entity.effect.EntityHakurouReflecter;
import thKaguyaMod.entity.effect.EntityMiracleCircle;
import thKaguyaMod.entity.effect.EntitySpellCardCircle;
import thKaguyaMod.entity.item.EntityCursedDecoyDoll;
import thKaguyaMod.entity.item.EntityHisou;
import thKaguyaMod.entity.item.EntityKinkakuzi;
import thKaguyaMod.entity.item.EntityMarisaBroom;
import thKaguyaMod.entity.item.EntityMazinkyoukan;
import thKaguyaMod.entity.item.EntityMiniHakkero;
import thKaguyaMod.entity.item.EntityPendulum;
import thKaguyaMod.entity.item.EntitySakuyaStopWatch;
import thKaguyaMod.entity.item.EntitySakuyaWatch;
import thKaguyaMod.entity.item.EntitySilverKnife;
import thKaguyaMod.entity.item.EntitySpiritualStrikeTalisman;
import thKaguyaMod.entity.item.EntitySukima;
import thKaguyaMod.entity.item.EntityYuukaParasol;
import thKaguyaMod.entity.living.EntityCirno;
import thKaguyaMod.entity.living.EntityDanmakuCreeper;
import thKaguyaMod.entity.living.EntityFamiliar;
import thKaguyaMod.entity.living.EntityMiko;
import thKaguyaMod.entity.living.EntityReimu;
import thKaguyaMod.entity.living.EntityRinnosuke;
import thKaguyaMod.entity.living.EntityRumia;
import thKaguyaMod.entity.living.EntitySakuya;
import thKaguyaMod.entity.living.EntitySanae;
import thKaguyaMod.entity.living.EntitySunFlowerFairy;
import thKaguyaMod.entity.living.EntityTHFairy;
import thKaguyaMod.entity.living.EntityTHPhantom;
import thKaguyaMod.entity.living.EntityToziko;
import thKaguyaMod.entity.living.EntityWriggle;
import thKaguyaMod.entity.shot.EntityDragonNeckJewel;
import thKaguyaMod.entity.shot.EntityHomingAmulet;
import thKaguyaMod.entity.shot.EntityMusouFuuin;
import thKaguyaMod.entity.shot.EntityNuclearShot;
import thKaguyaMod.entity.shot.EntityOnmyoudama;
import thKaguyaMod.entity.shot.EntitySanaeWind;
import thKaguyaMod.entity.shot.EntityTHLaser;
import thKaguyaMod.entity.shot.EntityTHSetLaser;
import thKaguyaMod.entity.shot.EntityTHShot;
import thKaguyaMod.entity.spellcard.EntitySpellCard;
import thKaguyaMod.entity.spellcard.THSC_Catadioptric;
import thKaguyaMod.entity.spellcard.THSC_EternalMeek;
import thKaguyaMod.entity.spellcard.THSC_Fafurotskies_no_Kiseki;
import thKaguyaMod.entity.spellcard.THSC_FujiyamaVolcano;
import thKaguyaMod.entity.spellcard.THSC_GagoujiCyclone;
import thKaguyaMod.entity.spellcard.THSC_HikouchuuNest;
import thKaguyaMod.entity.spellcard.THSC_HoukaKenran;
import thKaguyaMod.entity.spellcard.THSC_IcicleFall;
import thKaguyaMod.entity.spellcard.THSC_Kachoufuugetu;
import thKaguyaMod.entity.spellcard.THSC_KappaPororoca;
import thKaguyaMod.entity.spellcard.THSC_Kasho_no_Eimin;
import thKaguyaMod.entity.spellcard.THSC_Kerochan_Fuuu_ni_Makezu;
import thKaguyaMod.entity.spellcard.THSC_LittleBugStorm;
import thKaguyaMod.entity.spellcard.THSC_MasterSpark;
import thKaguyaMod.entity.spellcard.THSC_MeteonicShower;
import thKaguyaMod.entity.spellcard.THSC_MeteorOnEarth;
import thKaguyaMod.entity.spellcard.THSC_MiracleFruit;
import thKaguyaMod.entity.spellcard.THSC_Mishagujisama;
import thKaguyaMod.entity.spellcard.THSC_MoonlightRay;
import thKaguyaMod.entity.spellcard.THSC_MosesMiracle;
import thKaguyaMod.entity.spellcard.THSC_MusouFuuin;
import thKaguyaMod.entity.spellcard.THSC_Nami_to_Tubu_no_Kyoukai;
import thKaguyaMod.entity.spellcard.THSC_NijuuKokushichou;
import thKaguyaMod.entity.spellcard.THSC_NonDirectionalLaser;
import thKaguyaMod.entity.spellcard.THSC_PerfectFreeze;
import thKaguyaMod.entity.spellcard.THSC_RedMagic;
import thKaguyaMod.entity.spellcard.THSC_SaikouRanbu;
import thKaguyaMod.entity.spellcard.THSC_SatuzinDoll;
import thKaguyaMod.entity.spellcard.THSC_ScarletShoot;
import thKaguyaMod.entity.spellcard.THSC_Spear_the_Gungnir;
import thKaguyaMod.entity.spellcard.THSC_StarbowBreak;
import thKaguyaMod.entity.spellcard.THSC_StardustReverie;
import thKaguyaMod.entity.spellcard.THSC_Yasaka_no_Kamikaze;
import thKaguyaMod.entity.spellcard.THSC_YouryokuSpoiler;
import thKaguyaMod.entity.spellcard.THSC_Zenzinrui_no_Hisouten;
import thKaguyaMod.init.THKaguyaBlocks;
import thKaguyaMod.init.THKaguyaConfig;
import thKaguyaMod.init.THKaguyaItems;
import thKaguyaMod.item.ItemBlockDanmakuCraftingTable;
import thKaguyaMod.item.ItemBlockDivineSpirit;
import thKaguyaMod.item.ItemTHShot;
import thKaguyaMod.registry.DanmakuPatternRegistry;
import thKaguyaMod.registry.SpellCardRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
// レンダーに関するレジストリ
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
	public void registerTextures()
	{
	}

	public void registerRenderers()
	{
		/*
		 サーバー側では何もしない
		 クライアント側でのみ必要な処理はこのように空のメソッドを用意し,
		 CommonProxyを継承したClientProxyで行う
		*/
	}
	
	/** アイテムを登録する */
	public void registerItems()
	{
		//アイテムの内部名を決める
		GameRegistry.registerItem( THKaguyaItems.hourai_Jeweled_branch		, "Jeweled Branch of Hourai"		);
		GameRegistry.registerItem( THKaguyaItems.dragon_neck_jewel			, "Jewel from the Dragon's Neck"	);
		GameRegistry.registerItem( THKaguyaItems.buddha_stone_bowl			, "Buddha's Stone Bowl"				);
		GameRegistry.registerItem( THKaguyaItems.fire_rat_robe				, "Robe of the Fire Rat"			);
		GameRegistry.registerItem( THKaguyaItems.swallow_cowrie_shell		, "Swallow's Cowrie Shell"			);
		GameRegistry.registerItem( THKaguyaItems.hourai_pearl				, "Color Pearl"						);
		
		GameRegistry.registerItem( THKaguyaItems.kinkakuji					, "Seamless Ceiling of Kinkaku-ji"	);
		GameRegistry.registerItem( THKaguyaItems.aja_red_stone				, "Red Stone of Aja"				);
		
		GameRegistry.registerItem( THKaguyaItems.hakurei_miko_stick			, "Hakurei Shrine Maiden Stick"		);
		GameRegistry.registerItem( THKaguyaItems.amulet						, "Homing Amulet"					);
		GameRegistry.registerItem( THKaguyaItems.yin_yang_orb				, "Yin-Yang Orb"					);
		GameRegistry.registerItem( THKaguyaItems.bloodthirsty_yin_yang_orb	, "Bloodthirsty Yin-Yang Orb"		);
		GameRegistry.registerItem( THKaguyaItems.magic_bloom				, "Magic Broom"						);
		GameRegistry.registerItem( THKaguyaItems.mini_hakkero				, "Mini Hakkero"					);
		GameRegistry.registerItem( THKaguyaItems.marisa_hat					, "Marisa's Hat"					);
		GameRegistry.registerItem( THKaguyaItems.icicle_sword				, "Icicle Sword"					);
		GameRegistry.registerItem( THKaguyaItems.silver_knife				, "Silver Knife"					);
		GameRegistry.registerItem( THKaguyaItems.sakuya_stopwatch			, "Sakuya's Stopwatch"				);
		GameRegistry.registerItem( THKaguyaItems.sakuya_watch				, "Sakuya's Pocket Watch"			);
		GameRegistry.registerItem( THKaguyaItems.gungnir					, "Spear the Gungnir"				);
		GameRegistry.registerItem( THKaguyaItems.laevateinn					, "Laevateinn"						);
		//GameRegistry.registerItem( THKaguyaItems.frandreRodItem			, "Frandre's Rod"					);
		
		GameRegistry.registerItem( THKaguyaItems.cursedDecoyDoll			, "Cursed Decoy Doll"				);
		GameRegistry.registerItem( THKaguyaItems.hakurouken					, "Hakurouken"						);
		GameRegistry.registerItem( THKaguyaItems.roukanken					, "Roukanken"						);
		//GameRegistry.registerItem( THKaguyaItems.roukanSenpuuzin			, "Roukan Senpuuzin"				);
		GameRegistry.registerItem( THKaguyaItems.yuyuko_fan					, "Yuyuko's Fan"					);
		GameRegistry.registerItem( THKaguyaItems.soulTorch					, "Soul Torch"						);
		GameRegistry.registerItem( THKaguyaItems.gap						, "Gap"								);
		GameRegistry.registerItem( THKaguyaItems.gapFoldingUmbrella			, "Gap Folding Umbrella"			);
		
		GameRegistry.registerItem( THKaguyaItems.ibuki_gourd				, "Ibuki Gourd"						);
		
		GameRegistry.registerItem( THKaguyaItems.yuuka_parasol				, "Yuuka's Parasol"					);
		GameRegistry.registerItem( THKaguyaItems.death_scythe				, "Scythe of Death"					);
		GameRegistry.registerItem( THKaguyaItems.remorse_rod				, "Rod of Remorse"					);
		GameRegistry.registerItem( THKaguyaItems.tengu_fan					, "Tengu's Fan"						);
		
		GameRegistry.registerItem( THKaguyaItems.kappa_water_pistol			, "Kappa's Water Pistol"			);
		GameRegistry.registerItem( THKaguyaItems.kappa_cap					, "Kappa Cap"						);
		GameRegistry.registerItem( THKaguyaItems.wind_miko_stick			, "Wind Shrine Maiden Stick"		);
		GameRegistry.registerItem( THKaguyaItems.illness_recovery_charm		, "Illness Recovery Charm"			);
		GameRegistry.registerItem( THKaguyaItems.onbashira					, "Onbashira"						);
		GameRegistry.registerItem( THKaguyaItems.suwako_hat					, "Suwako's Hat"					);
		
		GameRegistry.registerItem( THKaguyaItems.heavenly_peach				, "Heavenly Peach"					);
		GameRegistry.registerItem( THKaguyaItems.hisou_sword				, "Sword of Scarlet Perception "	);
		
		GameRegistry.registerItem( THKaguyaItems.third_eye					, "3rd Eye"							);
		GameRegistry.registerItem( THKaguyaItems.nuclear_control_rod		, "Nuclear Control Rod"				);
		GameRegistry.registerItem( THKaguyaItems.closed_third_eye			, "Closed 3rd Eye"					);
		
		GameRegistry.registerItem( THKaguyaItems.nazrin_pendulum			, "Nazrin Pendulum"					);
		GameRegistry.registerItem( THKaguyaItems.ship_ghost_dipper_empty	, "Ship Ghost's Dipper empty"		);
		GameRegistry.registerItem( THKaguyaItems.ship_ghost_dipper_fill		, "Ship Ghost's Dipper fill"		);
		//GameRegistry.registerItem( THKaguyaItems.houtouItem				, "Bishamonten's Jeweled Pagoda"	);
		GameRegistry.registerItem( THKaguyaItems.sorcerer_sutra_scroll		, "Sorcerer's Sutra Scroll"			);
		
		GameRegistry.registerItem( THKaguyaItems.wall_passing_chisel		, "Wall-Passing Chisel"				);
		GameRegistry.registerItem( THKaguyaItems.toyosatomimi_sword			, "Sacred Sword of Toyosatomimi"	);
		
		GameRegistry.registerItem( THKaguyaItems.miracle_mallet				, "Miracle Mallet"					);
		
		GameRegistry.registerItem( THKaguyaItems.spiritual_strike_talisman	, "Spiritual Strike Talisman"		);
		
		
		GameRegistry.registerItem( THKaguyaItems.shot_material				, "Shot Material"					);
		GameRegistry.registerItem( THKaguyaItems.power_item					, "Power Up Item"					);
		GameRegistry.registerItem( THKaguyaItems.point_item					, "Point Item"						);
		GameRegistry.registerItem( THKaguyaItems.bomb_item					, "Spell Card Item"					);
		GameRegistry.registerItem( THKaguyaItems.extend_item				, "Extend Item"						);
		GameRegistry.registerItem( THKaguyaItems.shot_item					, "Shot"							);
		GameRegistry.registerItem( THKaguyaItems.laser_item					, "Laser"							);
		GameRegistry.registerItem( THKaguyaItems.spell_card					, "Spell Card"						);
		//GameRegistry.registerItem( THKaguyaItems.danmakuMemoItem			, "Danmaku Memo"					);
	}
	
	/** ブロックを登録する */
	public void registerBlocks()
	{
		GameRegistry.registerBlock(THKaguyaBlocks.danmaku_crafting_table, ItemBlockDanmakuCraftingTable.class, "Danmaku Crafting Table");
		GameRegistry.registerBlock(THKaguyaBlocks.divine_spirit, ItemBlockDivineSpirit.class, "Divine Spirit");
	}
	
	/** エンティティを登録する */
	public void registerEntitys()
	{
		
		
		/*
		 サーバーとクライアントのエンティティを同期させるメソッド
		 各引数はそれぞれ以下のとおり
		 Entityのクラス, 
		 Entityの内部名, 
		 このmod内で使用する同期取り用のID,
		 @Modのクラス(ここに書くのであればthis, そうでないならinstanceを参照)
		 更新可能な距離
		 更新頻度(tickごと)
		 Entityが速度情報を持つかどうか
		*/
		EntityRegistry.registerModEntity( EntityShotMaterial.class				, "ShotMaterial"			,  0, THKaguyaCore.instance, 40, 1, false	);
		EntityRegistry.registerModEntity( EntityDragonNeckJewel.class			, "BrilliantDragonBullet"	,  1, THKaguyaCore.instance, 80, 1, true	);
		EntityRegistry.registerModEntity( EntityTHItem.class					, "THItem"					,  2, THKaguyaCore.instance, 40, 1, true	);
		EntityRegistry.registerModEntity( EntityKinkakuzi.class					, "Kinkakuzi"				,  3, THKaguyaCore.instance,250, 1, true	);
		EntityRegistry.registerModEntity( EntitySilverKnife.class				, "SilverKnife"				,  4, THKaguyaCore.instance, 40, 1, true	);
		EntityRegistry.registerModEntity( EntitySakuyaWatch.class				, "PrivateSquare"			,  5, THKaguyaCore.instance, 40, 1, true	);
		EntityRegistry.registerModEntity( EntitySukima.class					, "Sukima"					,  6, THKaguyaCore.instance, 40, 5, false	);
		EntityRegistry.registerModEntity( EntityMazinkyoukan.class				, "Mazinkyoukan"			,  7, THKaguyaCore.instance, 40, 1, true	);
		EntityRegistry.registerModEntity( EntityHisou.class						, "Hisou"					,  8, THKaguyaCore.instance, 40, 1, true	);
		//EntityRegistry.registerModEntity( EntityObjectEye.class              		, "ObjectEye"          		,  9, THKaguyaCore.instance, 40, 1, true	);
		EntityRegistry.registerModEntity( EntityPendulum.class					, "NazrinPendulum"			, 10, THKaguyaCore.instance, 40, 1, true	);
		//EntityRegistry.registerModEntity( EntityHakureiShield.class      			, "HakureiShield"      		, 11, THKaguyaCore.instance, 250, 3, true	);
		EntityRegistry.registerModEntity( EntityMiracleCircle.class				, "MiracleCircle"			, 12, THKaguyaCore.instance, 40, 1, false	);
		EntityRegistry.registerModEntity( EntitySanaeWind.class					, "SanaeWind"				, 13, THKaguyaCore.instance, 80, 1, true	);
		EntityRegistry.registerModEntity( EntityCirnoIceBox.class				, "CirnoIceBox"				, 14, THKaguyaCore.instance, 80,10, false	);
		EntityRegistry.registerModEntity( EntitySpiritualStrikeTalisman.class	, "SpiritualStrikeTalisman"	, 15, THKaguyaCore.instance, 80,10, false	);
		EntityRegistry.registerModEntity( EntityMiniHakkero.class				, "MiniHakkero"				, 16, THKaguyaCore.instance, 40, 5, false	);
		//EntityRegistry.registerModEntity( EntityMasterSpark.class        			, "MasterSpark"          	, 17, THKaguyaCore.instance,250, 5, false	);
		EntityRegistry.registerModEntity( EntityHomingAmulet.class				, "HomingAmulet"			, 18, THKaguyaCore.instance, 80, 1, true	);
		EntityRegistry.registerModEntity( EntityTHSetLaser.class				, "THSetLaser"				, 19, THKaguyaCore.instance, 40, 1, true	);
		EntityRegistry.registerModEntity( EntityYuukaParasol.class				, "YuukaParasol"			, 20, THKaguyaCore.instance, 40, 1, true	);
		EntityRegistry.registerModEntity( EntityAjaRedStoneEffect.class			, "AjaRedStoneEffect"		, 21, THKaguyaCore.instance, 40, 1, true	);
		EntityRegistry.registerModEntity( EntityMarisaBroom.class				, "MarisaBroom"				, 22, THKaguyaCore.instance, 80, 1, true	);
		EntityRegistry.registerModEntity( EntityTHShot.class					, "NormalShot"				, 23, THKaguyaCore.instance, 40, 1, false	);
		EntityRegistry.registerModEntity( EntityTHLaser.class					, "NormalLaser"				, 24, THKaguyaCore.instance, 40, 1, false	);
		EntityRegistry.registerModEntity( EntitySpellCard.class					, "SpellCard"				, 25, THKaguyaCore.instance, 40, 5, false	);
		EntityRegistry.registerModEntity( EntityMusouFuuin.class				, "MusouFuuin"				, 26, THKaguyaCore.instance, 80, 1, true	);
		EntityRegistry.registerModEntity( EntitySakuyaStopWatch.class			, "SakuyaStopWatch"			, 27, THKaguyaCore.instance, 40, 1, true	);
		EntityRegistry.registerModEntity( EntityNuclearShot.class				, "NuclearShot"				, 28, THKaguyaCore.instance, 80, 1, true	);
		EntityRegistry.registerModEntity( EntityHakurouReflecter.class			, "HakurouReflecter"		, 29, THKaguyaCore.instance, 40, 1, true	);
		EntityRegistry.registerModEntity( EntityOnmyoudama.class				, "Onmyoudama"				, 30, THKaguyaCore.instance, 80, 1, true	);
		//EntityRegistry.registerModEntity( EntityTHHenyoriLaser.class     			, "HenyoriLaser"       		, 31, THKaguyaCore.instance, 250, 1, true	);
		EntityRegistry.registerModEntity( EntityDivineSpirit.class				, "DivineSpirit"			, 32, THKaguyaCore.instance, 80, 1, true	);
		EntityRegistry.registerModEntity( EntitySpellCardCircle.class			, "SpellCardCircle"			, 33, THKaguyaCore.instance, 80, 1, true	);
		EntityRegistry.registerModEntity( EntityCursedDecoyDoll.class			, "CursedDecoyDoll"			, 34, THKaguyaCore.instance, 60, 5, false	);
	}
	
	/**
	 * エンティティのスポーン設定登録
	 */
	public void registerEntitySpawn()
	{		
		/*MOBをスポーンさせる
		entityClass    : スポーンさせるEntityのclass
		weightedProb   : スポーンする割合。大きいほどスポーンしやすい
		min            : 一度にスポーンする最低数
		max            : 一度にスポーンする最大数
		typeOfCreature : MOBのタイプ
		biomes         : スポーンするバイオーム
		*/
		if(THKaguyaConfig.spawnDanmakuMob)
		{
			if(THKaguyaConfig.spawnFairy)
			{	
				//妖精
				EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.plains);
				EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.birchForest);
				EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.birchForestHills);
				EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.desert);
				EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.extremeHills);
				EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.extremeHillsEdge);
				EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.extremeHillsPlus);
				EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.forest);
				EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.forestHills);
				EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.taiga);
				EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.swampland);
				EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.icePlains);
				EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.iceMountains);
				EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.jungle);
				EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.jungleHills);
				EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.roofedForest);
				EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.swampland);
				EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.taiga);
				EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.taigaHills);
				
				//EntityRegistry.addSpawn(EntityTHFairy.class,  3, 1, 6,EnumCreatureType.monster, GensoukyouCore.gensoukyouPlains);
				
				//妖精
				EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.plains);
				EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.birchForest);
				EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.birchForestHills);
				EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.desert);
				EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.extremeHills);
				EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.extremeHillsEdge);
				EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.extremeHillsPlus);
				EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.forest);
				EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.forestHills);
				EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.taiga);
				EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.swampland);
				EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.icePlains);
				EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.iceMountains);
				EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.jungle);
				EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.jungleHills);
				EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.roofedForest);
				EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.swampland);
				EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.taiga);
				EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.taigaHills);
		

			}
			
			if(THKaguyaConfig.spawnPhantom)
			{
				//幽霊
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.plains);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.beach);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.birchForest);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.birchForestHills);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.coldBeach);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.coldTaiga);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.coldTaigaHills);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.deepOcean);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.desert);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.desertHills);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.extremeHills);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.extremeHillsEdge);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.extremeHillsPlus);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.forest);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.forestHills);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.frozenOcean);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.frozenRiver);
				//EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.hell);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.iceMountains);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.icePlains);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.jungle);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.jungleEdge);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.jungleHills);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.megaTaiga);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.megaTaigaHills);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.mesa);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.mesaPlateau);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.mesaPlateau_F);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.ocean);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.river);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.roofedForest);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.savanna);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.savannaPlateau);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.stoneBeach);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.swampland);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.taiga);
				EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.taigaHills);
			}
			
			if(THKaguyaConfig.spawnHanabeeper)
			{
				EntityRegistry.addSpawn(EntityDanmakuCreeper.class,  2, 1, 4,EnumCreatureType.monster, BiomeGenBase.desert);
			}
			
			if(THKaguyaConfig.spawnBoss)
			{
				//チルノ
				EntityRegistry.addSpawn(EntityCirno.class,  2, 1, 1,EnumCreatureType.monster, BiomeGenBase.coldTaiga);
				EntityRegistry.addSpawn(EntityCirno.class,  2, 1, 1,EnumCreatureType.monster, BiomeGenBase.coldTaigaHills);
				EntityRegistry.addSpawn(EntityCirno.class,  2, 1, 1,EnumCreatureType.monster, BiomeGenBase.frozenRiver);
				EntityRegistry.addSpawn(EntityCirno.class,  2, 1, 1,EnumCreatureType.monster, BiomeGenBase.iceMountains);
				EntityRegistry.addSpawn(EntityCirno.class,  2, 1, 1,EnumCreatureType.monster, BiomeGenBase.icePlains);
				
				//ルーミア
				EntityRegistry.addSpawn(EntityRumia.class, 4, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest);
				EntityRegistry.addSpawn(EntityRumia.class, 4, 1, 1, EnumCreatureType.monster, BiomeGenBase.plains);
				EntityRegistry.addSpawn(EntityRumia.class, 7, 1, 1, EnumCreatureType.monster, BiomeGenBase.roofedForest);
				EntityRegistry.addSpawn(EntityRumia.class, 4, 1, 1, EnumCreatureType.monster, BiomeGenBase.taiga);
				EntityRegistry.addSpawn(EntityRumia.class, 4, 1, 1, EnumCreatureType.monster, BiomeGenBase.taigaHills);
				
				//屠自古
				EntityRegistry.addSpawn(EntityToziko.class, 9, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest);
				EntityRegistry.addSpawn(EntityToziko.class, 7, 1, 1, EnumCreatureType.monster, BiomeGenBase.plains);
				EntityRegistry.addSpawn(EntityToziko.class,20, 1, 1, EnumCreatureType.monster, BiomeGenBase.swampland);
				
				//リグル
				EntityRegistry.addSpawn(EntityWriggle.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.birchForest);
				EntityRegistry.addSpawn(EntityWriggle.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.birchForestHills);
				EntityRegistry.addSpawn(EntityWriggle.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.forest);
				EntityRegistry.addSpawn(EntityWriggle.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.forestHills);
				EntityRegistry.addSpawn(EntityWriggle.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.jungle);
				EntityRegistry.addSpawn(EntityWriggle.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.jungleEdge);
				EntityRegistry.addSpawn(EntityWriggle.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.jungleHills);
				EntityRegistry.addSpawn(EntityWriggle.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.river);
			}
			
			//早苗
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.plains);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.beach);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.birchForest);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.birchForestHills);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.coldBeach);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.coldTaiga);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.coldTaigaHills);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.deepOcean);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.desert);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.desertHills);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.extremeHills);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.extremeHillsEdge);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.extremeHillsPlus);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.forest);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.forestHills);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.frozenOcean);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.frozenRiver);
			//EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.hell);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.iceMountains);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.icePlains);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.jungle);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.jungleEdge);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.jungleHills);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.megaTaiga);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.megaTaigaHills);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.mesa);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.mesaPlateau);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.mesaPlateau_F);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.ocean);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.river);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.roofedForest);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.savanna);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.savannaPlateau);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.stoneBeach);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.swampland);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.taiga);
			EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.taigaHills);
			
			//咲夜
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.plains);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.beach);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.birchForest);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.birchForestHills);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.coldBeach);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.coldTaiga);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.coldTaigaHills);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.deepOcean);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.desert);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.desertHills);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.extremeHills);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.extremeHillsEdge);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.extremeHillsPlus);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.forest);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.forestHills);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.frozenOcean);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.frozenRiver);
			//EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.hell);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.iceMountains);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.icePlains);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.jungle);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.jungleEdge);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.jungleHills);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.megaTaiga);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.megaTaigaHills);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.mesa);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.mesaPlateau);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.mesaPlateau_F);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.ocean);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.river);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.roofedForest);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.savanna);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.savannaPlateau);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.stoneBeach);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.swampland);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.taiga);
			EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.taigaHills);
			
			//霖之助
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.plains);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.beach);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.birchForest);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.birchForestHills);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.coldBeach);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.coldTaiga);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.coldTaigaHills);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.deepOcean);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.desert);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.desertHills);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.extremeHills);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.extremeHillsEdge);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.extremeHillsPlus);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.forest);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.forestHills);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.frozenOcean);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.frozenRiver);
			//EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.hell);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.iceMountains);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.icePlains);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.jungle);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.jungleEdge);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.jungleHills);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.megaTaiga);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.megaTaigaHills);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.mesa);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.mesaPlateau);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.mesaPlateau_F);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.ocean);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.river);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.roofedForest);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.savanna);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.savannaPlateau);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.stoneBeach);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.swampland);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.taiga);
			EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.taigaHills);
		}
	}
	
	/**
	 * スペルカードの登録
	 */
	public void registerSpellCards()
	{
		/*　スペルカードの登録は、SpellCardRegistry.registerSpellCard(...)を使う
		 * 
		 */
		SpellCardRegistry.registerSpellCard( THSC_MusouFuuin.class					, "thkaguyamod"	, "MusouFuuin"					,  0	);
		SpellCardRegistry.registerSpellCard( THSC_MasterSpark.class					, "thkaguyamod"	, "MasterSpark"					,  1	);
		SpellCardRegistry.registerSpellCard( THSC_Kasho_no_Eimin.class				, "thkaguyamod"	, "Kasho_no_Eimin"				,  2	);
		SpellCardRegistry.registerSpellCard( THSC_MeteonicShower.class				, "thkaguyamod"	, "MeteonicShower"				,  3	);
		SpellCardRegistry.registerSpellCard( THSC_Nami_to_Tubu_no_Kyoukai.class		, "thkaguyamod"	, "Nami_to_Tubu_no_Kyoukai"		,  4	);
		SpellCardRegistry.registerSpellCard( THSC_NijuuKokushichou.class				, "thkaguyamod"	, "NijuuKokushichou"			,  5	);
		SpellCardRegistry.registerSpellCard( THSC_ScarletShoot.class					, "thkaguyamod"	, "ScarletShoot"				,  6	);
		SpellCardRegistry.registerSpellCard( THSC_Zenzinrui_no_Hisouten.class		, "thkaguyamod"	, "Zenzinrui_no_Hisouten"		,  7	);
		SpellCardRegistry.registerSpellCard( THSC_SatuzinDoll.class					, "thkaguyamod"	, "SatuzinDoll"					,  8	);
		SpellCardRegistry.registerSpellCard( THSC_PerfectFreeze.class				, "thkaguyamod"	, "PerfectFreeze"				,  9	);
		SpellCardRegistry.registerSpellCard( THSC_HikouchuuNest.class				, "thkaguyamod"	, "HikouchuuNest"				, 10	);
		SpellCardRegistry.registerSpellCard( THSC_KappaPororoca.class				, "thkaguyamod"	, "Kappa'sPororoca"				, 11	);
		SpellCardRegistry.registerSpellCard( THSC_StardustReverie.class				, "thkaguyamod"	, "StardustReverie"				, 12	);
		SpellCardRegistry.registerSpellCard( THSC_Kerochan_Fuuu_ni_Makezu.class		, "thkaguyamod"	, "Kerochan_Fuuu_ni_Makezu"		, 13	);
		SpellCardRegistry.registerSpellCard( THSC_MiracleFruit.class					, "thkaguyamod"	, "MiracleFruit"				, 14	);
		SpellCardRegistry.registerSpellCard( THSC_Fafurotskies_no_Kiseki.class		, "thkaguyamod"	, "Fafrotskies_no_Kiseki"		, 15	);
		SpellCardRegistry.registerSpellCard( THSC_YouryokuSpoiler.class				, "thkaguyamod"	, "YouryokuSpoiler"				, 16	);
		SpellCardRegistry.registerSpellCard( THSC_MosesMiracle.class					, "thkaguyamod"	, "Moses_no_Kiseki"				, 17	);
		SpellCardRegistry.registerSpellCard( THSC_Yasaka_no_Kamikaze.class			, "thkaguyamod"	, "Yasaka_no_Kamikaze"			, 18	);
		SpellCardRegistry.registerSpellCard( THSC_IcicleFall.class					, "thkaguyamod"	, "IcicleFall"					, 19	);
		SpellCardRegistry.registerSpellCard( THSC_StarbowBreak.class					, "thkaguyamod"	, "StarbowBreak"				, 20	);
		SpellCardRegistry.registerSpellCard( THSC_Catadioptric.class					, "thkaguyamod"	, "Catadioptric"				, 21	);
		SpellCardRegistry.registerSpellCard( THSC_Mishagujisama.class				, "thkaguyamod"	, "Mishagujisama"				, 22	);
		SpellCardRegistry.registerSpellCard( THSC_RedMagic.class						, "thkaguyamod"	, "RedMagic"					, 23	);
		SpellCardRegistry.registerSpellCard( THSC_EternalMeek.class					, "thkaguyamod"	, "EternalMeek"					, 24	);
		SpellCardRegistry.registerSpellCard( THSC_NonDirectionalLaser.class			, "thkaguyamod"	, "Non-DirectionalLaser"		, 25	);
		SpellCardRegistry.registerSpellCard( THSC_Kachoufuugetu.class				, "thkaguyamod"	, "KachoufuugetuShoufuurougetu"	, 26	);
		SpellCardRegistry.registerSpellCard( THSC_Spear_the_Gungnir.class			, "thkaguyamod"	, "Spear_the_Gungnir"			, 27	);
		SpellCardRegistry.registerSpellCard( THSC_MoonlightRay.class					, "thkaguyamod"	, "MoonlightRay"				, 28	);
		SpellCardRegistry.registerSpellCard( THSC_HoukaKenran.class					, "thkaguyamod"	, "HoukaKenran"					, 29	);
		SpellCardRegistry.registerSpellCard( THSC_SaikouRanbu.class					, "thkaguyamod"	, "SaikouRanbu"					, 30	);
		
		SpellCardRegistry.registerSpellCard( THSC_FujiyamaVolcano.class				, "thkaguyamod"	, "FujiyamaVolcano"				, 33	);
		SpellCardRegistry.registerSpellCard( THSC_LittleBugStorm.class				, "thkaguyamod"	, "LittleBugStorm"				, 34	);
		SpellCardRegistry.registerSpellCard( THSC_MeteorOnEarth.class				, "thkaguyamod"	, "Meteor_on_Earth"				, 35	);
		//SpellCardRegistry.registerSpellCard( THSC_BrilliantDragonBullet.class		, "thkaguyamod"	, "BrilliantDragonBullet"		, 34	);
		SpellCardRegistry.registerSpellCard( THSC_GagoujiCyclone.class				, "thkaguyamod"	, "GagoujiCyclone"				, 40	);
		
	}
	
	public void registerDispenser()
	{
		BlockDispenser.dispenseBehaviorRegistry.putObject(THKaguyaItems.silver_knife,new  IBehaviorDispenseItem(){
            @Override
            public ItemStack dispense(IBlockSource blockSource, ItemStack itemStack){
                   World world = blockSource.getWorld();//World
                   IPosition iposition = BlockDispenser.func_149939_a(blockSource);//IPosition
                   double x = iposition.getX();//
                   double y = iposition.getY();//ディスペンサー射出口のところにある座標を取得
                   double z = iposition.getZ();//
                   Vec3 angle = Vec3.createVectorHelper(x - blockSource.getX(), y - blockSource.getY(), z - blockSource.getZ());
                   THShotLib.getVectorNomalize(angle);
                   
                   EntitySilverKnife knife = new EntitySilverKnife(world, THShotLib.pos(x, y, z), angle, 0.58D, itemStack.getItemDamage());
                   world.spawnEntityInWorld( knife );//ナイフを出す
                   
                   return itemStack.splitStack(itemStack.stackSize - 1);//アイテムをひとつ減らす
           }
		});
		
		BlockDispenser.dispenseBehaviorRegistry.putObject(THKaguyaItems.shot_item,new  IBehaviorDispenseItem(){
            @Override
            public ItemStack dispense(IBlockSource blockSource, ItemStack itemStack){
                   World world = blockSource.getWorld();//World
                   IPosition iposition = BlockDispenser.func_149939_a(blockSource);//IPosition
                   double x = iposition.getX();//
                   double y = iposition.getY();//ディスペンサー射出口のところにある座標を取得
                   double z = iposition.getZ();//
                   Vec3 angle = Vec3.createVectorHelper(x - blockSource.getX(), y - blockSource.getY(), z - blockSource.getZ());
                   THShotLib.getVectorNomalize(angle);
                   
                   EntityFamiliar living = new EntityFamiliar(world);
                   
                   ItemTHShot.shootDanmaku(itemStack, living, false, THShotLib.pos(blockSource.getX(), blockSource.getY(), blockSource.getZ()), angle, THShotLib.SIZE[ItemTHShot.shotTypeTrans[itemStack.getItemDamage()]] + 1.0D);
                   
                   boolean isInfinity = false;
                   NBTTagCompound nbt = itemStack.getTagCompound();
                   if(nbt != null)
                   {
                	   isInfinity = nbt.getBoolean("Infinity");
                   }
                   if(!isInfinity)
                   {
                	   itemStack.stackSize--;
                   }
                   //return itemStack.splitStack(itemStack);
                   return itemStack;
           }
		});
	}
	
	public void registerChestItem()
	{
		
	}
	
	public void registerDanmakuPattern()
	{
		DanmakuPatternRegistry.setDanmakuPattern();
	}

	public int addArmor(String armor)
	{
		return 0;
	}
	
	public int getNewRenderType()
	{
		return -1;
	}
}