package thKaguyaMod;

/** 弾幕に関する定数定義 */
public class DanmakuConstants {

	/**
	 * 難易度（弾幕難易度）
	 */
	/**	弾幕難易度　EASY	*/
	public static final int EASY		= 1;
	/**	弾幕難易度　NORMAL	*/
	public static final int NORMAL		= 2;
	/**	弾幕難易度　HARD	*/
	public static final int HARD		= 3;
	/**	弾幕難易度　LUNATIC	*/
	public static final int LUNATIC		= 4;
	
	/**
	 * 弾の色		Shot Colors
	 * 弾形状データ使用ビット	oooo xxxx xxxx xxxx
	 */
	/*public static final int RED			= 0x0000;
	public static final int BLUE		= 0x1000;
	public static final int GREEN		= 0x2000;
	public static final int YELLOW		= 0x3000;
	public static final int PURPLE		= 0x4000;
	public static final int AQUA		= 0x5000;
	public static final int ORANGE		= 0x6000;
	public static final int WHITE		= 0x7000;
	
	public static final int RANDOM		= 0x8000;
	public static final int RAINBOW		= 0x9000;*/
	
	public static final int RED			= 0x0000;
	public static final int BLUE		= 0x0001;
	public static final int GREEN		= 0x0002;
	public static final int YELLOW		= 0x0003;
	public static final int PURPLE		= 0x0004;
	public static final int AQUA		= 0x0005;
	public static final int ORANGE		= 0x0006;
	public static final int WHITE		= 0x0007;
	
	public static final int RANDOM		= 0x0008;
	public static final int RAINBOW		= 0x0009;
	
	public static final int MASK_COLOR	= 0xF000;
	
	public static final int 赤弾			= RED;
	public static final int 青弾			= BLUE;
	public static final int 緑弾			= GREEN;
	public static final int 黄弾			= YELLOW;
	public static final int 紫弾			= PURPLE;
	public static final int 水弾			= AQUA;
	public static final int 橙弾			= ORANGE;
	public static final int 白弾			= WHITE;
	
	public static final int 乱弾			= RANDOM;
	public static final int 虹弾			= RAINBOW;
	
	/**
	 * 弾の形状		Shot Forms
	 * 弾形状データ使用ビット	xxxx oooo ooxx xxxx
	 */
	/*public static final int FORM_SMALL		= 0x0000;
	public static final int FORM_TINY		= 0x0040;
	public static final int FORM_MEDIUM	= 0x0080;
	public static final int FORM_PEARL		= 0x00C0;
	public static final int FORM_CIRCLE	= 0x0100;
	public static final int FORM_LIGHT		= 0x0140;
	public static final int FORM_SCALE		= 0x0180;
	public static final int FORM_BUTTERFLY	= 0x01C0;
	public static final int FORM_SMALLSTAR	= 0x0200;
	public static final int FORM_STAR		= 0x0240;
	public static final int FORM_RICE		= 0x0280;
	public static final int FORM_CRYSTAL	= 0x02C0;
	public static final int FORM_HEART		= 0x0300;
	public static final int FORM_KUNAI		= 0x0340;
	public static final int FORM_TALISMAN	= 0x0380;
	public static final int FORM_BIGLIGHT	= 0x03C0;
	public static final int FORM_OVAL		= 0x0400;
	public static final int FORM_FAMILIAR	= 0x0440;
	public static final int FORM_ARROW		= 0x0480;
	public static final int FORM_PHANTOM	= 0x0680;
	public static final int FORM_AMULET	= 0x06C0;
	public static final int FORM_KNIFE		= 0x0700;
	public static final int FORM_WIND		= 0x0740;
	public static final int FORM_BIG		= 0x0780;
	public static final int FORM_KISHITU	= 0x07C0;*/
	
	public static final int MASK_FORM		= 0x0FC0;
	
	public static final int FORM_SMALL		=  0;
	public static final int FORM_TINY		=  1;
	public static final int FORM_MEDIUM	=  2;
	public static final int FORM_PEARL		=  3;
	public static final int FORM_CIRCLE	=  4;
	public static final int FORM_LIGHT		=  5;
	public static final int FORM_SCALE		=  6;
	public static final int FORM_BUTTERFLY	=  7;
	public static final int FORM_SMALLSTAR	=  8;
	public static final int FORM_STAR		=  9;
	public static final int FORM_RICE		= 10;
	public static final int FORM_CRYSTAL	= 11;
	public static final int FORM_HEART		= 12;
	public static final int FORM_KUNAI		= 13;
	public static final int FORM_TALISMAN	= 14;
	public static final int FORM_BIGLIGHT	= 15;
	public static final int FORM_OVAL		= 16;
	public static final int FORM_FAMILIAR	= 17;
	public static final int FORM_ARROW		= 18;
	public static final int FORM_PHANTOM	= 26;
	public static final int FORM_AMULET	= 27;
	public static final int FORM_KNIFE		= 28;
	public static final int FORM_WIND		= 29;
	public static final int FORM_BIG		= 30;
	public static final int FORM_KISHITU	= 31;

	
	public static final int 小弾				= FORM_SMALL;
	public static final int 粒弾				= FORM_TINY;
	public static final int 中弾				= FORM_MEDIUM;
	public static final int 真珠弾			= FORM_PEARL;
	public static final int 輪弾				= FORM_CIRCLE;
	public static final int 光弾				= FORM_LIGHT;
	public static final int 鱗弾				= FORM_SCALE;
	public static final int 蝶弾				= FORM_BUTTERFLY;
	public static final int 小星弾			= FORM_SMALLSTAR;
	public static final int 星弾				= FORM_STAR;
	public static final int 米弾				= FORM_RICE;
	public static final int 結晶弾			= FORM_CRYSTAL;
	public static final int ハート弾			= FORM_HEART;
	public static final int クナイ弾			= FORM_KUNAI;
	public static final int 札弾				= FORM_TALISMAN;
	public static final int 大光弾			= FORM_BIGLIGHT;
	public static final int 楕円弾			= FORM_OVAL;
	public static final int 使い魔			= FORM_FAMILIAR;
	public static final int 矢弾				= FORM_ARROW;
	public static final int 幽霊弾			= FORM_PHANTOM;
	public static final int アミュレット弾	= FORM_AMULET;
	public static final int ナイフ弾			= FORM_KNIFE;
	public static final int 風弾				= FORM_WIND;
	public static final int 大弾				= FORM_BIG;
	public static final int 気質弾			= FORM_KISHITU;
	
	/**
	 * レーザーの形状
	 */
	public static final int LASER_NORMAL = 0;
	public static final int LASER_GUNGNIR = 1;
	public static final int LASER_LAEVATAIN = 2;
	
	/**
	 * 特殊弾の定義		Special Shot Definition
	 */
	public static final int HOMING01		=  10;//ホーミング弾。ホーミングの数値が上がるほど追尾性能が高い
	public static final int HOMING02		=  11;
	public static final int DIFFUSION01	=  20;//拡散アミュレット
	public static final int FALL01			=  30;
	public static final int BOUND01			=  40;//一回だけ跳ね返る
	public static final int BOUND02			=  41;//二回だけ跳ね返る
	public static final int BOUND03			=  42;//三回だけ跳ね返る
	public static final int BOUND04			=  43;//無限に跳ね返る
	public static final int BOUNDFALL01	=  50;
	public static final int FIRE			=  60;//着火効果付与
	public static final int EXPLOSION01	=  70;//地形を破壊する爆発
	public static final int EXPLOSION02	=  71;//地形を破壊しない爆発
	public static final int BRAKE01			= 150;

	public static final int WIND01			= 180;

	public static final int GUNGNIR			= 285;
	
	/**
	 * 標準の重力値		Default Gravity
	 */
	public static final double GRAVITY_DEFAULT = -0.03D;
}
