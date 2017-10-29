package thKaguyaMod.client;

import net.minecraft.client.renderer.entity.RenderCreeper;
import thKaguyaMod.CommonProxy;
import thKaguyaMod.client.render.RenderAjaRedStoneEffect;
import thKaguyaMod.client.render.RenderCirnoIceBox;
import thKaguyaMod.client.render.RenderDivineSpirit;
import thKaguyaMod.client.render.RenderDivineSpiritBlock;
import thKaguyaMod.client.render.RenderHisou;
import thKaguyaMod.client.render.RenderKinkakuzi;
import thKaguyaMod.client.render.RenderMarisaBroom;
import thKaguyaMod.client.render.RenderMazinkyoukan;
import thKaguyaMod.client.render.RenderMiniHakkero;
import thKaguyaMod.client.render.RenderPendulum;
import thKaguyaMod.client.render.RenderPrivateSquare;
import thKaguyaMod.client.render.RenderSakuyaStopWatch;
import thKaguyaMod.client.render.RenderShotMaterial;
import thKaguyaMod.client.render.RenderSilverKnife;
import thKaguyaMod.client.render.RenderSpellCard;
import thKaguyaMod.client.render.RenderSpiritualStrikeTalisman;
import thKaguyaMod.client.render.RenderSukima;
import thKaguyaMod.client.render.RenderTHItem;
import thKaguyaMod.client.render.RenderYuukaParasol;
import thKaguyaMod.client.render.effect.RenderHakurouReflecter;
import thKaguyaMod.client.render.effect.RenderMiracleCircle;
import thKaguyaMod.client.render.effect.RenderSpellCardCircle;
import thKaguyaMod.client.render.living.RenderCirno;
import thKaguyaMod.client.render.living.RenderCursedDecoyDoll;
import thKaguyaMod.client.render.living.RenderFamiliar;
import thKaguyaMod.client.render.living.RenderMiko;
import thKaguyaMod.client.render.living.RenderReimu;
import thKaguyaMod.client.render.living.RenderRinnosuke;
import thKaguyaMod.client.render.living.RenderRumia;
import thKaguyaMod.client.render.living.RenderSakuya;
import thKaguyaMod.client.render.living.RenderSanae;
import thKaguyaMod.client.render.living.RenderSunFlowerFairy;
import thKaguyaMod.client.render.living.RenderTHFairy;
import thKaguyaMod.client.render.living.RenderTHPhantom;
import thKaguyaMod.client.render.living.RenderToziko;
import thKaguyaMod.client.render.living.RenderWriggle;
import thKaguyaMod.client.render.shot.RenderHomingAmulet;
import thKaguyaMod.client.render.shot.RenderMusouFuuin;
import thKaguyaMod.client.render.shot.RenderNuclearShot;
import thKaguyaMod.client.render.shot.RenderOnmyoudama;
import thKaguyaMod.client.render.shot.RenderRyuuLightningBolt;
import thKaguyaMod.client.render.shot.RenderSanaeWind;
import thKaguyaMod.client.render.shot.RenderTHLaser;
import thKaguyaMod.client.render.shot.RenderTHShot;
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
import thKaguyaMod.tileentity.TileEntityDivineSpirit;
// レンダーに関するレジストリ
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

//import thkaguyamod.*;

// クライアント側のみのクラスはこのアノテーションをつける
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	public static int hinezumiID;
	
	public void registerTextures()
	{
		//MinecraftForgeClient.preloadTexture("/gui/thKaguyaTerrain.png");
		//MinecraftForgeClient.preloadTexture("/armor/hinezumi_1.png");
	}
	
	public void registerRenderers()
	{	
		// Entityのクラスと描画, モデルを結びつける
		RenderingRegistry.registerEntityRenderingHandler( EntityTHFairy.class						, new RenderTHFairy()					);
		RenderingRegistry.registerEntityRenderingHandler( EntitySunFlowerFairy.class				, new RenderSunFlowerFairy()			);
		RenderingRegistry.registerEntityRenderingHandler( EntityTHPhantom.class					, new RenderTHPhantom()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityFamiliar.class						, new RenderFamiliar()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityCirno.class						, new RenderCirno()						);
		RenderingRegistry.registerEntityRenderingHandler( EntityRumia.class						, new RenderRumia()						);
		RenderingRegistry.registerEntityRenderingHandler( EntityToziko.class						, new RenderToziko()					);
		RenderingRegistry.registerEntityRenderingHandler( EntitySanae.class						, new RenderSanae()						);
		RenderingRegistry.registerEntityRenderingHandler( EntityRinnosuke.class					, new RenderRinnosuke()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityWriggle.class						, new RenderWriggle()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityCursedDecoyDoll.class				, new RenderCursedDecoyDoll()			);
		RenderingRegistry.registerEntityRenderingHandler( EntitySakuya.class						, new RenderSakuya()						);
		
		RenderingRegistry.registerEntityRenderingHandler( EntityReimu.class						, new RenderReimu()						);
		RenderingRegistry.registerEntityRenderingHandler( EntityMiko.class							, new RenderMiko()						);
		
		RenderingRegistry.registerEntityRenderingHandler( EntityShotMaterial.class					, new RenderShotMaterial()				);
		RenderingRegistry.registerEntityRenderingHandler(EntityTHItem.class						, new RenderTHItem()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityDragonNeckJewel.class				, new RenderRyuuLightningBolt()			);
		//RenderingRegistry.registerEntityRenderingHandler( EntityColorLightningBolt.class			, new RenderColorLightningBolt()		);
		RenderingRegistry.registerEntityRenderingHandler( EntityKinkakuzi.class					, new RenderKinkakuzi()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityPendulum.class						, new RenderPendulum()					);
		RenderingRegistry.registerEntityRenderingHandler( EntitySakuyaWatch.class					, new RenderPrivateSquare()				);
		RenderingRegistry.registerEntityRenderingHandler( EntitySakuyaStopWatch.class				, new RenderSakuyaStopWatch()			);
		RenderingRegistry.registerEntityRenderingHandler( EntitySilverKnife.class					, new RenderSilverKnife()				);
		RenderingRegistry.registerEntityRenderingHandler( EntitySukima.class						, new RenderSukima()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityMazinkyoukan.class					, new RenderMazinkyoukan()				);
		RenderingRegistry.registerEntityRenderingHandler( EntityHisou.class						, new RenderHisou()						);
		RenderingRegistry.registerEntityRenderingHandler( EntityMiniHakkero.class					, new RenderMiniHakkero()				);
		RenderingRegistry.registerEntityRenderingHandler( EntityMiracleCircle.class				, new RenderMiracleCircle()				);
		RenderingRegistry.registerEntityRenderingHandler( EntitySanaeWind.class					, new RenderSanaeWind()					);	
		RenderingRegistry.registerEntityRenderingHandler( EntityCirnoIceBox.class					, new RenderCirnoIceBox()				);
		//RenderingRegistry.registerEntityRenderingHandler( EntityMasterSpark.class					, new RenderMasterSpark()				);
		RenderingRegistry.registerEntityRenderingHandler( EntityHomingAmulet.class					, new RenderHomingAmulet()				);
		RenderingRegistry.registerEntityRenderingHandler( EntityYuukaParasol.class					, new RenderYuukaParasol()				);
		RenderingRegistry.registerEntityRenderingHandler( EntityAjaRedStoneEffect.class			, new RenderAjaRedStoneEffect()			);
		RenderingRegistry.registerEntityRenderingHandler( EntityTHShot.class						, new RenderTHShot()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityTHLaser.class						, new RenderTHLaser()					);
		RenderingRegistry.registerEntityRenderingHandler( EntitySpellCard.class					, new RenderSpellCard()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityMusouFuuin.class					, new RenderMusouFuuin()				);
		//RenderingRegistry.registerEntityRenderingHandler( EntityHakureiShield.class				, new RenderHakureiShield()				);
		//RenderingRegistry.registerEntityRenderingHandler( EntityTHHenyoriLaser.class				, new RenderTHHenyoriLaser()			);
		RenderingRegistry.registerEntityRenderingHandler( EntityDanmakuCreeper.class				, new RenderCreeper()					);
		
		RenderingRegistry.registerEntityRenderingHandler( EntityMarisaBroom.class					, new RenderMarisaBroom()				);
		RenderingRegistry.registerEntityRenderingHandler( EntityNuclearShot.class					, new RenderNuclearShot()				);
		RenderingRegistry.registerEntityRenderingHandler( EntityHakurouReflecter.class				, new RenderHakurouReflecter()			);
		RenderingRegistry.registerEntityRenderingHandler( EntityOnmyoudama.class					, new RenderOnmyoudama()				);
		RenderingRegistry.registerEntityRenderingHandler( EntityTHSetLaser.class					, new RenderTHLaser()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityDivineSpirit.class					, new RenderDivineSpirit()				);
		RenderingRegistry.registerEntityRenderingHandler( EntitySpellCardCircle.class				, new RenderSpellCardCircle()			);
		RenderingRegistry.registerEntityRenderingHandler( EntitySpiritualStrikeTalisman.class		, new RenderSpiritualStrikeTalisman()	);
		//RenderingRegistry.registerEntityRenderingHandler( EntitySpiritualStrikeTalisman.class		, new RenderSpiritualStrikeTalisman()	);
		
		//RenderingRegistry.addNewArmourRendererPrefix("hinezumiw");
		
		RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderDivineSpiritBlock());
		//RenderingRegistry.registerBlockHandler(new RenderDivineSpiritBlock());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDivineSpirit.class, new TileEntityDivineSpiritRenderer());
	}
	
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
	
	@Override
	public int getNewRenderType()
	{
		return RenderingRegistry.getNextAvailableRenderId();
	}

}