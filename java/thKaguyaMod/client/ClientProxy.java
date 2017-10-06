/*   1:    */ package thKaguyaMod.client;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.client.registry.ClientRegistry;
/*   4:    */ import cpw.mods.fml.client.registry.RenderingRegistry;
/*   5:    */ import cpw.mods.fml.relauncher.Side;
/*   6:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   7:    */ import net.minecraft.client.renderer.entity.RenderCreeper;
/*   8:    */ import thKaguyaMod.CommonProxy;
/*   9:    */ import thKaguyaMod.client.render.RenderAjaRedStoneEffect;
/*  10:    */ import thKaguyaMod.client.render.RenderCirnoIceBox;
/*  11:    */ import thKaguyaMod.client.render.RenderDivineSpirit;
/*  12:    */ import thKaguyaMod.client.render.RenderDivineSpiritBlock;
/*  13:    */ import thKaguyaMod.client.render.RenderHisou;
/*  14:    */ import thKaguyaMod.client.render.RenderKinkakuzi;
/*  15:    */ import thKaguyaMod.client.render.RenderMarisaBroom;
/*  16:    */ import thKaguyaMod.client.render.RenderMazinkyoukan;
/*  17:    */ import thKaguyaMod.client.render.RenderMiniHakkero;
/*  18:    */ import thKaguyaMod.client.render.RenderPendulum;
/*  19:    */ import thKaguyaMod.client.render.RenderPrivateSquare;
/*  20:    */ import thKaguyaMod.client.render.RenderSakuyaStopWatch;
/*  21:    */ import thKaguyaMod.client.render.RenderShotMaterial;
/*  22:    */ import thKaguyaMod.client.render.RenderSilverKnife;
/*  23:    */ import thKaguyaMod.client.render.RenderSpellCard;
/*  24:    */ import thKaguyaMod.client.render.RenderSpiritualStrikeTalisman;
/*  25:    */ import thKaguyaMod.client.render.RenderSukima;
/*  26:    */ import thKaguyaMod.client.render.RenderTHItem;
/*  27:    */ import thKaguyaMod.client.render.RenderYuukaParasol;
/*  28:    */ import thKaguyaMod.client.render.effect.RenderHakurouReflecter;
/*  29:    */ import thKaguyaMod.client.render.effect.RenderMiracleCircle;
/*  30:    */ import thKaguyaMod.client.render.effect.RenderSpellCardCircle;
/*  31:    */ import thKaguyaMod.client.render.living.RenderCirno;
/*  32:    */ import thKaguyaMod.client.render.living.RenderCursedDecoyDoll;
/*  33:    */ import thKaguyaMod.client.render.living.RenderFamiliar;
/*  34:    */ import thKaguyaMod.client.render.living.RenderMiko;
/*  35:    */ import thKaguyaMod.client.render.living.RenderReimu;
/*  36:    */ import thKaguyaMod.client.render.living.RenderRinnosuke;
/*  37:    */ import thKaguyaMod.client.render.living.RenderRumia;
/*  38:    */ import thKaguyaMod.client.render.living.RenderSakuya;
/*  39:    */ import thKaguyaMod.client.render.living.RenderSanae;
/*  40:    */ import thKaguyaMod.client.render.living.RenderSunFlowerFairy;
/*  41:    */ import thKaguyaMod.client.render.living.RenderTHFairy;
/*  42:    */ import thKaguyaMod.client.render.living.RenderTHPhantom;
/*  43:    */ import thKaguyaMod.client.render.living.RenderToziko;
/*  44:    */ import thKaguyaMod.client.render.living.RenderWriggle;
/*  45:    */ import thKaguyaMod.client.render.shot.RenderHomingAmulet;
/*  46:    */ import thKaguyaMod.client.render.shot.RenderMusouFuuin;
/*  47:    */ import thKaguyaMod.client.render.shot.RenderNuclearShot;
/*  48:    */ import thKaguyaMod.client.render.shot.RenderOnmyoudama;
/*  49:    */ import thKaguyaMod.client.render.shot.RenderRyuuLightningBolt;
/*  50:    */ import thKaguyaMod.client.render.shot.RenderSanaeWind;
/*  51:    */ import thKaguyaMod.client.render.shot.RenderTHLaser;
/*  52:    */ import thKaguyaMod.client.render.shot.RenderTHShot;
/*  53:    */ import thKaguyaMod.entity.EntityDivineSpirit;
/*  54:    */ import thKaguyaMod.entity.EntityShotMaterial;
/*  55:    */ import thKaguyaMod.entity.EntityTHItem;
/*  56:    */ import thKaguyaMod.entity.effect.EntityAjaRedStoneEffect;
/*  57:    */ import thKaguyaMod.entity.effect.EntityCirnoIceBox;
/*  58:    */ import thKaguyaMod.entity.effect.EntityHakurouReflecter;
/*  59:    */ import thKaguyaMod.entity.effect.EntityMiracleCircle;
/*  60:    */ import thKaguyaMod.entity.effect.EntitySpellCardCircle;
/*  61:    */ import thKaguyaMod.entity.item.EntityCursedDecoyDoll;
/*  62:    */ import thKaguyaMod.entity.item.EntityHisou;
/*  63:    */ import thKaguyaMod.entity.item.EntityKinkakuzi;
/*  64:    */ import thKaguyaMod.entity.item.EntityMarisaBroom;
/*  65:    */ import thKaguyaMod.entity.item.EntityMazinkyoukan;
/*  66:    */ import thKaguyaMod.entity.item.EntityMiniHakkero;
/*  67:    */ import thKaguyaMod.entity.item.EntityPendulum;
/*  68:    */ import thKaguyaMod.entity.item.EntitySakuyaStopWatch;
/*  69:    */ import thKaguyaMod.entity.item.EntitySakuyaWatch;
/*  70:    */ import thKaguyaMod.entity.item.EntitySilverKnife;
/*  71:    */ import thKaguyaMod.entity.item.EntitySpiritualStrikeTalisman;
/*  72:    */ import thKaguyaMod.entity.item.EntitySukima;
/*  73:    */ import thKaguyaMod.entity.item.EntityYuukaParasol;
/*  74:    */ import thKaguyaMod.entity.living.EntityCirno;
/*  75:    */ import thKaguyaMod.entity.living.EntityDanmakuCreeper;
/*  76:    */ import thKaguyaMod.entity.living.EntityFamiliar;
/*  77:    */ import thKaguyaMod.entity.living.EntityMiko;
/*  78:    */ import thKaguyaMod.entity.living.EntityReimu;
/*  79:    */ import thKaguyaMod.entity.living.EntityRinnosuke;
/*  80:    */ import thKaguyaMod.entity.living.EntityRumia;
/*  81:    */ import thKaguyaMod.entity.living.EntitySakuya;
/*  82:    */ import thKaguyaMod.entity.living.EntitySanae;
/*  83:    */ import thKaguyaMod.entity.living.EntitySunFlowerFairy;
/*  84:    */ import thKaguyaMod.entity.living.EntityTHFairy;
/*  85:    */ import thKaguyaMod.entity.living.EntityTHPhantom;
/*  86:    */ import thKaguyaMod.entity.living.EntityToziko;
/*  87:    */ import thKaguyaMod.entity.living.EntityWriggle;
/*  88:    */ import thKaguyaMod.entity.shot.EntityDragonNeckJewel;
/*  89:    */ import thKaguyaMod.entity.shot.EntityHomingAmulet;
/*  90:    */ import thKaguyaMod.entity.shot.EntityMusouFuuin;
/*  91:    */ import thKaguyaMod.entity.shot.EntityNuclearShot;
/*  92:    */ import thKaguyaMod.entity.shot.EntityOnmyoudama;
/*  93:    */ import thKaguyaMod.entity.shot.EntitySanaeWind;
/*  94:    */ import thKaguyaMod.entity.shot.EntityTHLaser;
/*  95:    */ import thKaguyaMod.entity.shot.EntityTHSetLaser;
/*  96:    */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  97:    */ import thKaguyaMod.entity.spellcard.EntitySpellCard;
/*  98:    */ import thKaguyaMod.tileentity.TileEntityDivineSpirit;
/*  99:    */ 
/* 100:    */ @SideOnly(Side.CLIENT)
/* 101:    */ public class ClientProxy
/* 102:    */   extends CommonProxy
/* 103:    */ {
/* 104:    */   public static int hinezumiID;
/* 105:    */   
/* 106:    */   public void registerTextures() {}
/* 107:    */   
/* 108:    */   public void registerRenderers()
/* 109:    */   {
/* 110:118 */     RenderingRegistry.registerEntityRenderingHandler(EntityTHFairy.class, new RenderTHFairy());
/* 111:119 */     RenderingRegistry.registerEntityRenderingHandler(EntitySunFlowerFairy.class, new RenderSunFlowerFairy());
/* 112:120 */     RenderingRegistry.registerEntityRenderingHandler(EntityTHPhantom.class, new RenderTHPhantom());
/* 113:121 */     RenderingRegistry.registerEntityRenderingHandler(EntityFamiliar.class, new RenderFamiliar());
/* 114:122 */     RenderingRegistry.registerEntityRenderingHandler(EntityCirno.class, new RenderCirno());
/* 115:123 */     RenderingRegistry.registerEntityRenderingHandler(EntityRumia.class, new RenderRumia());
/* 116:124 */     RenderingRegistry.registerEntityRenderingHandler(EntityToziko.class, new RenderToziko());
/* 117:125 */     RenderingRegistry.registerEntityRenderingHandler(EntitySanae.class, new RenderSanae());
/* 118:126 */     RenderingRegistry.registerEntityRenderingHandler(EntityRinnosuke.class, new RenderRinnosuke());
/* 119:127 */     RenderingRegistry.registerEntityRenderingHandler(EntityWriggle.class, new RenderWriggle());
/* 120:128 */     RenderingRegistry.registerEntityRenderingHandler(EntityCursedDecoyDoll.class, new RenderCursedDecoyDoll());
/* 121:129 */     RenderingRegistry.registerEntityRenderingHandler(EntitySakuya.class, new RenderSakuya());
/* 122:    */     
/* 123:131 */     RenderingRegistry.registerEntityRenderingHandler(EntityReimu.class, new RenderReimu());
/* 124:132 */     RenderingRegistry.registerEntityRenderingHandler(EntityMiko.class, new RenderMiko());
/* 125:    */     
/* 126:134 */     RenderingRegistry.registerEntityRenderingHandler(EntityShotMaterial.class, new RenderShotMaterial());
/* 127:135 */     RenderingRegistry.registerEntityRenderingHandler(EntityTHItem.class, new RenderTHItem());
/* 128:136 */     RenderingRegistry.registerEntityRenderingHandler(EntityDragonNeckJewel.class, new RenderRyuuLightningBolt());
/* 129:    */     
/* 130:138 */     RenderingRegistry.registerEntityRenderingHandler(EntityKinkakuzi.class, new RenderKinkakuzi());
/* 131:139 */     RenderingRegistry.registerEntityRenderingHandler(EntityPendulum.class, new RenderPendulum());
/* 132:140 */     RenderingRegistry.registerEntityRenderingHandler(EntitySakuyaWatch.class, new RenderPrivateSquare());
/* 133:141 */     RenderingRegistry.registerEntityRenderingHandler(EntitySakuyaStopWatch.class, new RenderSakuyaStopWatch());
/* 134:142 */     RenderingRegistry.registerEntityRenderingHandler(EntitySilverKnife.class, new RenderSilverKnife());
/* 135:143 */     RenderingRegistry.registerEntityRenderingHandler(EntitySukima.class, new RenderSukima());
/* 136:144 */     RenderingRegistry.registerEntityRenderingHandler(EntityMazinkyoukan.class, new RenderMazinkyoukan());
/* 137:145 */     RenderingRegistry.registerEntityRenderingHandler(EntityHisou.class, new RenderHisou());
/* 138:146 */     RenderingRegistry.registerEntityRenderingHandler(EntityMiniHakkero.class, new RenderMiniHakkero());
/* 139:147 */     RenderingRegistry.registerEntityRenderingHandler(EntityMiracleCircle.class, new RenderMiracleCircle());
/* 140:148 */     RenderingRegistry.registerEntityRenderingHandler(EntitySanaeWind.class, new RenderSanaeWind());
/* 141:149 */     RenderingRegistry.registerEntityRenderingHandler(EntityCirnoIceBox.class, new RenderCirnoIceBox());
/* 142:    */     
/* 143:151 */     RenderingRegistry.registerEntityRenderingHandler(EntityHomingAmulet.class, new RenderHomingAmulet());
/* 144:152 */     RenderingRegistry.registerEntityRenderingHandler(EntityYuukaParasol.class, new RenderYuukaParasol());
/* 145:153 */     RenderingRegistry.registerEntityRenderingHandler(EntityAjaRedStoneEffect.class, new RenderAjaRedStoneEffect());
/* 146:154 */     RenderingRegistry.registerEntityRenderingHandler(EntityTHShot.class, new RenderTHShot());
/* 147:155 */     RenderingRegistry.registerEntityRenderingHandler(EntityTHLaser.class, new RenderTHLaser());
/* 148:156 */     RenderingRegistry.registerEntityRenderingHandler(EntitySpellCard.class, new RenderSpellCard());
/* 149:157 */     RenderingRegistry.registerEntityRenderingHandler(EntityMusouFuuin.class, new RenderMusouFuuin());
/* 150:    */     
/* 151:    */ 
/* 152:160 */     RenderingRegistry.registerEntityRenderingHandler(EntityDanmakuCreeper.class, new RenderCreeper());
/* 153:    */     
/* 154:162 */     RenderingRegistry.registerEntityRenderingHandler(EntityMarisaBroom.class, new RenderMarisaBroom());
/* 155:163 */     RenderingRegistry.registerEntityRenderingHandler(EntityNuclearShot.class, new RenderNuclearShot());
/* 156:164 */     RenderingRegistry.registerEntityRenderingHandler(EntityHakurouReflecter.class, new RenderHakurouReflecter());
/* 157:165 */     RenderingRegistry.registerEntityRenderingHandler(EntityOnmyoudama.class, new RenderOnmyoudama());
/* 158:166 */     RenderingRegistry.registerEntityRenderingHandler(EntityTHSetLaser.class, new RenderTHLaser());
/* 159:167 */     RenderingRegistry.registerEntityRenderingHandler(EntityDivineSpirit.class, new RenderDivineSpirit());
/* 160:168 */     RenderingRegistry.registerEntityRenderingHandler(EntitySpellCardCircle.class, new RenderSpellCardCircle());
/* 161:169 */     RenderingRegistry.registerEntityRenderingHandler(EntitySpiritualStrikeTalisman.class, new RenderSpiritualStrikeTalisman());
/* 162:    */     
/* 163:    */ 
/* 164:    */ 
/* 165:    */ 
/* 166:174 */     RenderingRegistry.getNextAvailableRenderId();
/* 167:175 */     RenderingRegistry.registerBlockHandler(new RenderDivineSpiritBlock());
/* 168:    */     
/* 169:    */ 
/* 170:178 */     ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDivineSpirit.class, new TileEntityDivineSpiritRenderer());
/* 171:    */   }
/* 172:    */   
/* 173:    */   public int addArmor(String armor)
/* 174:    */   {
/* 175:183 */     return RenderingRegistry.addNewArmourRendererPrefix(armor);
/* 176:    */   }
/* 177:    */   
/* 178:    */   public int getNewRenderType()
/* 179:    */   {
/* 180:189 */     return RenderingRegistry.getNextAvailableRenderId();
/* 181:    */   }
/* 182:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.ClientProxy
 * JD-Core Version:    0.7.0.1
 */