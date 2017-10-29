package thKaguyaMod;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.entity.living.EntityFamiliar;
import thKaguyaMod.entity.living.EntityTHFairy;
import thKaguyaMod.entity.shot.EntityNuclearShot;
import thKaguyaMod.entity.shot.EntityTHLaser;
import thKaguyaMod.entity.shot.EntityTHSetLaser;
import thKaguyaMod.entity.shot.EntityTHShot;

/**
 * 弾幕関連の汎用関数群
 *
 */
public class THShotLib extends DanmakuConstants
{
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*		定義	Definition																										*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 弾形状		Shot Forms
	 */
	/** 小弾 */
	public static final int SMALL[]		= {  0,  1,  2,  3,  4,  5,  6,  7};
	/** 粒弾 */
	public static final int TINY[]		= {  8,  9, 10, 11, 12, 13, 14, 15};
	/** 中弾 */
	public static final int MEDIUM[]	= { 16, 17, 18, 19, 20, 21, 22, 23};
	/** 真珠弾 */
	public static final int PEARL[]		= { 24, 25, 26, 27, 28, 29, 30, 31};
	/** 輪弾 */
	public static final int CIRCLE[]	= { 32, 33, 34, 35, 36, 37, 38, 39};
	/** 光弾 */
	public static final int LIGHT[]		= { 40, 41, 42, 43, 44, 45, 46, 47};
	/** 鱗弾 */
	public static final int SCALE[]		= { 48, 49, 50, 51, 52, 53, 54, 55};
	/** 蝶弾 */
	public static final int BUTTERFLY[]	= { 56, 57, 58, 59, 60, 61, 62, 63};
	/** 小星弾 */
	public static final int SMALLSTAR[]	= { 64, 65, 66, 67, 68, 69, 70, 71};
	/** 星弾 */
	public static final int STAR[]		= { 72, 73, 74, 75, 76, 77, 78, 79};
	/** 米弾 */
	public static final int RICE[]		= { 80, 81, 82, 83, 84, 85, 86, 87};
	/** 結晶弾 */
	public static final int CRYSTAL[]	= { 88, 89, 90, 91, 92, 93, 94, 95};
	/** ハート弾 */
	public static final int HEART[]		= { 96, 97, 98, 99,100,101,102,103};
	/** クナイ弾 */
	public static final int KUNAI[]		= {104,105,106,107,108,109,110,111};
	/** 札弾 */
	public static final int TALISMAN[]	= {112,113,114,115,116,117,118,119};
	/** 大光弾 */
	public static final int BIGLIGHT[]	= {120,121,122,123,124,125,126,127};
	/** 楕円弾 */
	public static final int OVAL[]		= {128,129,130,131,132,133,134,135};
	/** 使い魔 */
	public static final int FAMILIAR[]	= {136,137,138,139,140,141,142,143};
	/** 矢弾 */
	public static final int ARROW[]		= {144,145,146,147,148,149,150,151};
	/** アミュレット */
	public static final int AMULET[]	= {216,217,218,219,220,221,222,223};
	/** ナイフ弾 */
	public static final int KNIFE[]		= {224,225,226,227,228,229,230,231};
	/** 風弾 */
	public static final int WIND[]		= {232,233,234,235,236,237,238,239};
	/** 大弾 */
	public static final int BIG[]		= {240,241,242,243,244,245,246,247};
	/** 気質弾 */
	public static final int KISHITU[]	= {248,249,250,251,252,253,254,255};
	/** レーザー */
	public static final int LASER[]		= {512,513,514,515,516,517,518,519};
	
	/**
	 * 弾の色の名前		Shot Color Names(JP)
	 */
	public static final String COLOR_NAME_JP[]	={
		"赤", "青", "緑", "黄", "紫", "水色", "橙", "白", "虹色", "ランダム", "暖色", "寒色", "", "", "", ""};
	
	/**
	 * 弾の色の名前		Shot Color Names(EN)
	 */
	public static final String COLOR_NAME[]	={
		"Red", "Blue", "Green", "Yellow", "Purple", "Aqua", "Orange", "White", "Rainbow", "Random", "Hot", "Cold", "", "", "", ""};
	
	/**
	 * 弾の名前		Shot Form Names
	 */
	public static final String SHOT_NAME_JP[]	={
		  "小弾",  "粒弾",  "中弾","真珠弾",  "輪弾",  "光弾",  "鱗弾",  "蝶弾",
		"小星弾",  "星弾",  "米弾","結晶弾","ハート","クナイ",  "札弾","大光弾",
		"楕円弾","使い魔","　矢弾","未定義","未定義","未定義","未定義","未定義",
		"未定義","未定義","未定義","未定義","ナイフ",  "風弾",  "大弾","気質弾"};
	
	/**
	 * 形状ごとの弾のダメージを定義		Shot Damage by Form
	 */
	public static final float DAMAGE[] = {	2.4F, 1.0F, 2.8F, 5.0F, 2.0F, 3.4F, 1.6F, 2.8F,
												1.6F, 3.0F, 1.2F, 1.8F, 3.2F, 2.6F, 1.0F, 6.8F,
												3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F,
												0.0F, 0.0F, 0.0F, 5.0F, 2.6F, 4.0F, 5.0F, 5.0F};
	
	/**
	 * 形状ごとの弾の大きさを定義		Shot Size by Form
	 */
	public static final float SIZE[] = {0.30F, 0.15F, 0.50F, 0.30F, 0.30F, 0.30F, 0.15F, 0.30F, 
											0.25F, 0.50F, 0.15F, 0.15F, 0.50F, 0.15F, 0.15F, 0.60F,
											0.50F, 1.00F, 0.15F, 0.00F, 0.00F, 0.00F, 0.00F, 0.00F,
											0.00F, 0.00F, 0.00F, 0.40F, 0.30F, 1.00F, 0.90F, 0.90F};
	
	private static int ways[][] ={	{ 0},
			
			{ 1},
			{ 1, 1},
			{ 1, 0, 2, 0},
			{ 1, 0, 3, 0},
			{ 1, 3, 1},
			{ 1, 4, 1},
			{ 1, 5, 1},
			{ 1, 3, 3, 1},
			{ 1, 5, 3, 0},
			{ 1, 4, 4, 1},
			
			{ 1, 6, 4, 0},
			{ 1, 5, 5, 1},
			{ 1, 3, 5, 3, 1},
			{ 1, 3, 6, 3, 1},
			{ 1, 4, 5, 4, 1},
			{ 1, 4, 6, 4, 1},
			{ 1, 4, 7, 4, 1},
			{ 1, 3, 5, 5, 3, 1},
			{ 1, 3, 6, 6, 3},
			{ 1, 3, 6, 6, 3, 1},
			
			{ 1, 5, 9, 5, 1},
			{ 1, 3, 7, 7, 3, 1},
			{ 1, 6, 9, 6, 1},
			{ 1, 4, 7, 7, 4, 1},
			{ 1, 3, 5, 7, 5, 3, 1},
			{ 1, 3, 5, 8, 5, 3, 1},
			{ 1, 3, 5, 9, 5, 3, 1},
			{ 1, 3, 6, 8, 6, 3, 1},
			{ 1, 3, 6, 9, 6, 3, 1},
			{ 1, 4, 6, 8, 6, 4, 1},
			
			{ 1, 3, 6, 11, 6, 3, 1},
			{ 1, 3, 6, 12, 6, 3, 1},
			{ 0},
			{ 0},
			{ 0},
			{ 0},
			{ 0},
			{ 0},
			{ 0},
			{ 0},
			
			{ 0},
			{ 0},
			{ 0},
			{ 1, 3, 6, 12, 12, 6, 3, 1}
			
};
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*		弾生成補助	Assistant Create Shots 																						*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 弾IDから形状IDを返す
	 * @param shotID	: 弾の形状と色を含めたID。THShotLib.SMALLSTAR[THShotLib.GREEN]と表される。
	 * @return 形状ID。色を含まない形状単位のID。形状によるデフォルトのサイズやダメージ量を求めるのに必要。
	 */
	public static final int getFormID(int shotID)
	{
		return (shotID - (shotID % 8)) / 8; 
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*		弾生成系	Create Shots																								*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * すべての弾の基礎となる生成処理	Create Shots Base of All
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : 弾の発射元。あまり意味は無い
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param slope		 : 弾の進行方向を軸とした傾き。度数で指定（180にすると弾が上下ひっくり返る）
	 * @param rotate        : 弾が角度を変えるときに利用する軸のベクトル（回転軸）
	 * @param rotationSpeed : 弾が角度を変える速度（毎フレーム指定した角度（度数）で向きを変える）。回転の軸はrotateVectorX,Y,Z
	 * @param rotationEnd	 : 弾の角速度を0にする時間。特に終わらせないなら9999あたりを入れる
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 弾のX軸方向の重力値。0.0Dで重力の影響を受けない
	 * @param shot			 : 弾の動作以外のデータ
	 * @return 生成したEntityTHShotを返す
	 */
	public static final EntityTHShot createShot(EntityLivingBase user, Entity source, Vec3 pos,
											Vec3 angle, float slope, 
											Vec3 rotate, float rotationSpeed, int rotationEnd,
											double firstSpeed, double limitSpeed, double acceleration,
											Vec3 gravity, ShotData shot)
	{
		World world = source.worldObj;

		EntityTHShot entityTHShot;
		ShotData shot2 = shot;
		boolean random = false;
		boolean rainbow = false;
		if(shot2 != null)
		{
			if(shot2.color == RAINBOW)
			{
				shot2.color = new Random().nextInt(7);
				rainbow = true;
			}
			else if(shot2.color == RANDOM)
			{
				shot2.color = new Random().nextInt(8);
				random = true;
			}
		}
		
		entityTHShot = new EntityTHShot(world, user, source, pos,
										angle, slope, 
										rotate, rotationSpeed, rotationEnd,
										firstSpeed, limitSpeed, acceleration,
										gravity, shot2);
		if(!world.isRemote)
		{
			world.spawnEntityInWorld(entityTHShot);
			
		}
		if(random)
		{
			shot.color = RANDOM;
		}
		else if(rainbow)
		{
			shot.color = RAINBOW;
		}
		return entityTHShot;
	}
	
	/**
	 * 軌道変化しない弾の基礎となる生成処理
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : 弾の発射元。あまり意味は無い
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param slope		 : 弾の進行方向を軸とした傾き。度数で指定（180にすると弾が上下ひっくり返る）
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 弾のX軸方向の重力値。0.0Dで重力の影響を受けない
	 * @param shot			 : 弾の動作以外のデータ
	 * @return 生成したEntityTHShotを返す
	 */
	public static final EntityTHShot createShot(EntityLivingBase user, Entity source, Vec3 pos,
											Vec3 angle, float slope,
											double firstSpeed, double limitSpeed, double acceleration,
											Vec3 gravity,
											ShotData shot)
	{
		float yaw = getYawFromVector(angle.xCoord, angle.zCoord);
		float pitch = getPitchFromVector(angle.xCoord, angle.yCoord, angle.zCoord);
		Vec3 rotate = getVecFromAngle(yaw, pitch + 90F);
		return createShot(user, source, pos, angle, slope, rotate, 0F, 9999,
										firstSpeed, limitSpeed, acceleration, gravity, shot);
	}
	
	/**
	 * 加減速を指定できる一般的な弾の生成
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param shot			 : 弾の動作以外のデータ
	 * @return 生成したEntityTHShotを返す
	 */
	public static final EntityTHShot createShot(EntityLivingBase user, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, ShotData shot)
	{	
		return createShot(user, pos, angle, firstSpeed, limitSpeed, acceleration, gravity_Zero(), shot);
	}
	
	
	/**
	 * 加減速と重力加速度を指定できる弾の生成
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 弾のX軸方向の重力値。0.0Dで重力の影響を受けない
	 * @param shot			 : 弾の動作以外のデータ
	 * @return 生成したEntityTHShotを返す
	 */
	public static final EntityTHShot createShot(EntityLivingBase user, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, ShotData shot)
	{

		return createShot(user, user, pos, angle, 0F,
						firstSpeed, limitSpeed, acceleration, gravity, shot);
	}

	
	/**
	 * 最も単純な弾の生成
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param speed    	 : 弾の速度
	 * @param shot			 : 弾の動作以外のデータ
	 * @return 生成したEntityTHShotを返す
	 */
	public static final EntityTHShot createShot(EntityLivingBase user, Vec3 pos, Vec3 angle, double speed, ShotData shot)
	{
		return createShot(user, pos, angle, speed, speed, 0.0D, shot);
	}
	
	
	/**
	 * すべての扇状弾の基礎となる処理	Create Wide Shots Base
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : 弾の発射元。あまり意味は無い
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param rotationSpeed : 弾が角度を変える速度（毎フレーム指定した角度（度数）で向きを変える）
	 * @param rotationEnd	 : 弾の角速度を0にする時間。特に終わらせないなら9999あたりを入れる
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 弾のX軸方向の重力値。0.0Dで重力の影響を受けない
	 * @param shot			 : 弾の動作以外のデータ
	 * @param way           : 弾数
	 * @param wideAngle     : 扇状の成す角度
	 * @param distance      : 発射地点からこの分だけ離して発射する
	 * @param baseAngle	 : 弾の角度のズレ
	 */
	public static final void createWideShot(EntityLivingBase user, Entity source, Vec3 pos,
											Vec3 angle, float rotationSpeed, int rotationEnd,
											double firstSpeed, double limitSpeed, double acceleration,
											Vec3 gravity,
											ShotData shot, int way, float wideAngle, double distance, float baseAngle)
	{	
		//弾の発射方向に対して上方に９０度回転させたベクトル（回転軸）を取得する
		float yaw = getYawFromVector(angle.xCoord, angle.zCoord);
		float pitch = getPitchFromVector(angle.xCoord, angle.yCoord, angle.zCoord);
		//Vec3 rotateVec = getOverVector(angle);
		Vec3 rotateVec = THShotLib.getVecFromAngle(-yaw, - pitch + 90F, 1.0F);
		//扇の成す角度の半分、取得した回転軸を回転させる
		float angle2 = -wideAngle / 2F;
		float span = wideAngle / (way - 1);
		angle2 += baseAngle;
		
		//色関連
		int color = new Random().nextInt(7);
		int rainbowPattern[] = {RED, ORANGE, YELLOW, GREEN, AQUA, BLUE, PURPLE};
		boolean random = false;
		boolean rainbow = false;
		ShotData shot2 = shot;
		if(shot2.color == RANDOM)
		{
			random = true;
		}
		else if(shot2.color == RAINBOW)
		{
			rainbow = true;
		}
		
		if(random)
		{
			shot2.color = new Random().nextInt(8);
		}
		
		//扇の角度になるまで回転軸に沿って弾を配置する
		for(int i = 0; i < way; i++)
		{

			if(rainbow)
			{
				shot2.color = rainbowPattern[color % 7];
				color++;
			}
			Vec3 angleVec = getVectorFromRotation(rotateVec.xCoord, rotateVec.yCoord, rotateVec.zCoord, angle.xCoord, angle.yCoord, angle.zCoord, angle2);
			createShot(user, source, Vec3.createVectorHelper(pos.xCoord + angleVec.xCoord * distance, pos.yCoord + angleVec.yCoord * distance, pos.zCoord + angleVec.zCoord * distance), angleVec,
					 pitch * (float)Math.sin(angle2 / 180F * 3.141593F), rotateVec, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, shot);
			angle2 += span;
		}
		if(random)
		{
			shot.color = RANDOM;
		}
		else if(rainbow)
		{
			shot.color = RAINBOW;
		}
	}
	
	/**
	 * 旧すべての扇状弾の基礎となる処理
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : 弾の発射元。あまり意味は無い
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param rotationSpeed : 弾が角度を変える速度（毎フレーム指定した角度（度数）で向きを変える）
	 * @param rotationEnd	 : 弾の角速度を0にする時間。特に終わらせないなら9999あたりを入れる
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 弾のX軸方向の重力値。0.0Dで重力の影響を受けない
	 * @param shot			 : 弾の動作以外のデータ
	 * @param way           : 弾数
	 * @param wideAngle     : 扇状の成す角度
	 * @param distance      : 発射地点からこの分だけ離して発射する
	 */
	public static final void createWideShot(EntityLivingBase user, Entity source, Vec3 pos,
			Vec3 angle, float rotationSpeed, int rotationEnd,
			double firstSpeed, double limitSpeed, double acceleration,
			Vec3 gravity,
			ShotData shot, int way, float wideAngle, double distance)
	{
		createWideShot(user, source, pos, angle, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration,
				gravity, shot, way, wideAngle, distance, 0F);
	}
	
	/**
	 * 軌道変化しない扇状弾の基礎となる処理
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : 弾の発射元。あまり意味は無い
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 弾のX軸方向の重力値。0.0Dで重力の影響を受けない
	 * @param shot			 : 弾の動作以外のデータ
	 * @param way           : 弾数
	 * @param wideAngle     : 扇状の成す角度
	 * @param distance      : 発射地点からこの分だけ離して発射する
	 * @param baseAngle	 : 弾の角度のズレ
	 */
	public static final void createWideShot(EntityLivingBase user, Entity source, Vec3 pos,
			Vec3 angle, 
			double firstSpeed, double limitSpeed, double acceleration,
			Vec3 gravity,
			ShotData shot, int way, float wideAngle, double distance, float baseAngle)
	{
		createWideShot(user, source, pos, angle, 0F, 9999, firstSpeed, limitSpeed, acceleration,
				gravity, shot, way, wideAngle, distance, baseAngle);
	}
	
	/**
	 * 旧軌道変化しない扇状弾の基礎となる処理
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : 弾の発射元。あまり意味は無い
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 弾のX軸方向の重力値。0.0Dで重力の影響を受けない
	 * @param shot			 : 弾の動作以外のデータ
	 * @param way           : 弾数
	 * @param wideAngle     : 扇状の成す角度
	 * @param distance      : 発射地点からこの分だけ離して発射する
	 */
	public static final void createWideShot(EntityLivingBase user, Entity source, Vec3 pos,
											Vec3 angle, double firstSpeed, double limitSpeed, double acceleration,
											Vec3 gravity,
											ShotData shot, int way, float wideAngle, double distance)
	{	
		createWideShot(user, source, pos, angle, 0F, 9999, firstSpeed, limitSpeed, acceleration, gravity, shot, way, wideAngle, distance);
	}
	
	/**
	 * 最も一般的な扇状弾に加減速が加わったもの
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param shot			 : 弾の動作以外のデータ
	 * @param way           : 弾数
	 * @param wideAngle     : 扇状の成す角度
	 */
	public static final void createWideShot(EntityLivingBase user, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, ShotData shot, int way, float wideAngle)
	{
		createWideShot(user, user, pos, angle, firstSpeed, limitSpeed, acceleration, gravity_Zero(),
				shot, way, wideAngle, 0.0D);
	}
	
	/**
	 * 最も一般的な扇状弾
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param speed    	 : 弾の速度
	 * @param shot			 : 弾の動作以外のデータ
	 * @param way           : 弾数
	 * @param wideAngle     : 扇状の成す角度
	 */
	public static final void createWideShot(EntityLivingBase user, Vec3 pos, Vec3 angle, double speed, ShotData shot, int way, float wideAngle)
	{
		createWideShot(user, pos, angle, speed, speed, 0.0D, shot, way, wideAngle);
	}
	
	/**
	 * すべての全方位弾の基礎となる処理	Create Circle Shots Base
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : 弾の発射元。あまり意味は無い
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param rotationSpeed : 弾が角度を変える速度（毎フレーム指定した角度（度数）で向きを変える）
	 * @param rotationEnd	 : 弾の角速度を0にする時間。特に終わらせないなら9999あたりを入れる
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 弾のX軸方向の重力値。0.0Dで重力の影響を受けない
	 * @param shot			 : 弾の動作以外のデータ
	 * @param way           : 弾数
	 * @param distance      : 発射地点からこの分だけ離して発射する
	 * @param baseAngle	 : 弾の角度のズレ
	 */
	public static final void createCircleShot(EntityLivingBase user, Entity source, Vec3 pos,
											Vec3 angle, float rotationSpeed, int rotationEnd,
											double firstSpeed, double limitSpeed, double acceleration,
											Vec3 gravity,
											ShotData shot, int way, double distance, float baseAngle)
	{
		if(way % 2 == 0)
		{
			baseAngle += 360F / (way * 2F);
		}

		createWideShot(user, source, pos,
				angle, rotationSpeed, rotationEnd,
				firstSpeed, limitSpeed, acceleration,
				gravity,
				shot,  way, 360F - (360F / way), distance, baseAngle);
	}
	
	/**
	 * 旧すべての全方位弾の基礎となる処理
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : 弾の発射元。あまり意味は無い
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param rotationSpeed : 弾が角度を変える速度（毎フレーム指定した角度（度数）で向きを変える）
	 * @param rotationEnd	 : 弾の角速度を0にする時間。特に終わらせないなら9999あたりを入れる
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 弾のX軸方向の重力値。0.0Dで重力の影響を受けない
	 * @param shot			 : 弾の動作以外のデータ
	 * @param way           : 弾数
	 * @param distance      : 発射地点からこの分だけ離して発射する
	 */
	public static final void createCircleShot(EntityLivingBase user, Entity source, Vec3 pos,
											Vec3 angle, float rotationSpeed, int rotationEnd,
											double firstSpeed, double limitSpeed, double acceleration,
											Vec3 gravity,
											ShotData shot, int way, double distance)
	{
		createCircleShot(user, source, pos,
				angle, rotationSpeed, rotationEnd,
				firstSpeed, limitSpeed, acceleration,
				gravity,
				shot,  way, distance, 0F);
	}
	
	/**
	 * 角度連れのない全方位弾の基礎となる処理
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : 弾の発射元。あまり意味は無い
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param rotationSpeed : 弾が角度を変える速度（毎フレーム指定した角度（度数）で向きを変える）
	 * @param rotationEnd	 : 弾の角速度を0にする時間。特に終わらせないなら9999あたりを入れる
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 弾のX軸方向の重力値。0.0Dで重力の影響を受けない
	 * @param shot			 : 弾の動作以外のデータ
	 * @param way           : 弾数
	 * @param distance      : 発射地点からこの分だけ離して発射する
	 * @param baseAngle	 : 弾の角度のズレ
	 */
	public static final void createCircleShot(EntityLivingBase user, Entity source, Vec3 pos,
											Vec3 angle, double firstSpeed, double limitSpeed, double acceleration,
											Vec3 gravity,
											ShotData shot, int way, double distance, float baseAngle)
	{
		createCircleShot(user, source, pos,
				angle, 0F, 9999, firstSpeed, limitSpeed, acceleration,
				gravity,
				shot, way, distance, baseAngle);
	}
	
	/**
	 * 最も基本的な全方位弾を発射する
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param speed    	 : 弾の速度
	 * @param shot			 : 弾の動作以外のデータ
	 * @param way           : 弾数
	 */
	public static final void createCircleShot(EntityLivingBase user, Vec3 pos, Vec3 angle, double speed, ShotData shot, int way)
	{
		createCircleShot(user, pos, angle, speed, speed, 0.0D, shot, way);
	}
	
	/**
	 * 最も基本的な全方位弾を発射するに加速度遅延を指定できるもの
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param shot			 : 弾の動作以外のデータ
	 * @param way           : 弾数
	 */
	public static final void createCircleShot(EntityLivingBase user, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, ShotData shot, int way)
	{	
		createCircleShot(user, user, pos, angle, firstSpeed, limitSpeed, acceleration, gravity_Zero(), shot, way, shot.size, 0F);
	}
	
	
	/**
	 * 目の前にリング状の弾幕を発射する基礎処理
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : 弾の発射元。あまり意味は無い
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param rotationSpeed : 弾が角度を変える速度（毎フレーム指定した角度（度数）で向きを変える）
	 * @param rotationEnd	 : 弾の角速度を0にする時間。特に終わらせないなら9999あたりを入れる
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 弾のX軸方向の重力値。0.0Dで重力の影響を受けない
	 * @param shot			 : 弾の動作以外のデータ
	 * @param way           : 弾数
	 * @param distance      : 発射地点からこの分だけ離して発射する
	 * @param rinSpan       : リングの広がり。大きいほどリングが大きくなり中央ががら空きになる
	 * @param ringBaseAngle : リングの起点となる角度
	 */
	public static final void createRingShot(
		EntityLivingBase user, Entity source, Vec3 pos,
		Vec3 angle, float rotationSpeed, int rotationEnd,
		double firstSpeed, double limitSpeed, double acceleration,
		Vec3 gravity,
		ShotData shot, int way, double distance, float ringSpan, float ringBaseAngle)
	{
		ringSpan /= 2F;
		float yaw = getYawFromVector(-angle.xCoord, angle.zCoord);
		float pitch = getPitchFromVector(-angle.xCoord, -angle.yCoord, angle.zCoord);
		Vec3 baseVec = getVecFromAngle(yaw, pitch + ringSpan, 1.0D);
		Vec3 baseOverVec = getVecFromAngle(yaw, pitch + ringSpan + 90F, 1.0D);
		Vec3 overVec = getVecFromAngle(yaw, pitch + 90F, 1.0D);
		Vec3 extendVec;
		float angle2 = ringBaseAngle;
		float span = 360F / way;
		
		Vec3 movingVec;
		
		int color = new Random().nextInt(7);
		int rainbowPattern[] = {RED, ORANGE, YELLOW, GREEN, AQUA, BLUE, PURPLE};
		boolean random = false;
		boolean rainbow = false;
		ShotData shot2 = shot;
		
		if(shot2 != null)
		{
			if(shot2.color == RANDOM)
			{
				random = true;
			}
			else if(shot2.color == RAINBOW)
			{
				rainbow = true;
			}
		}
		
		if(random)
		{
			shot2.color = new Random().nextInt(8);
		}
		
		//Vec3 sideVecBase = getVecFromAngle(yaw + 90F, 0F);//真横のベクトル
		//Vec3 sideVec = sideVecBase;
		
		
		
		for(int i = 0; i < way; i++)
		{
			if(rainbow)
			{
				shot2.color = rainbowPattern[color % 7];
				color++;
			}
			movingVec = getVectorFromRotation(angle.xCoord, angle.yCoord, angle.zCoord, baseVec.xCoord, baseVec.yCoord, baseVec.zCoord, angle2);
			extendVec = getVectorFromRotation(angle.xCoord, angle.yCoord, angle.zCoord, overVec.xCoord, overVec.yCoord, overVec.zCoord, angle2);
			Vec3 overVec2 = getVectorFromRotation(angle, baseOverVec, angle2);
			createShot(user, source, Vec3.createVectorHelper(pos.xCoord + extendVec.xCoord * distance, pos.yCoord + extendVec.yCoord * distance, pos.zCoord + extendVec.zCoord * distance), Vec3.createVectorHelper(movingVec.xCoord, movingVec.yCoord, movingVec.zCoord),
					angle2, overVec2, rotationSpeed, rotationEnd,
					firstSpeed, limitSpeed, acceleration, gravity,
					shot2);
			angle2 += span;
		}
		if(random)
		{
			shot.color = RANDOM;
		}
		else if(rainbow)
		{
			shot.color = RAINBOW;
		}
	}
	
	/**
	 * 最も基本的な、目の前にリング状の弾幕を発射する
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param speed		 : 弾の速度
	 * @param shot			 : 弾の動作以外のデータ
	 * @param way           : 弾数
	 * @param distance      : 発射地点からこの分だけ離して発射する
	 * @param rinSpan       : リングの広がり。大きいほどリングが大きくなり中央ががら空きになる
	 * @param ringBaseAngle : リングの起点となる角度
	 */
	public static final void createRingShot(
			EntityLivingBase user, Vec3 pos,
			Vec3 angle, double speed, 
			ShotData shot, int way, double distance, float ringSpan, float ringBaseAngle)
	{
		ShotData shot2 = shot;
		createRingShot(user, user, pos, angle, 0F, 9999,
				speed, speed, 0.0D, gravity_Zero(), 
				shot2,
				way, distance, ringSpan, ringBaseAngle);
	}
	
	/**
	 * 目の前の輪状空間にランダムに弾を発射する
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : 弾の発射元。あまり意味は無い
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param rotationSpeed : 弾が角度を変える速度（毎フレーム指定した角度（度数）で向きを変える）
	 * @param rotationEnd	 : 弾の角速度を0にする時間。特に終わらせないなら9999あたりを入れる
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 弾のX軸方向の重力値。0.0Dで重力の影響を受けない
	 * @param shot			 : 弾の動作以外のデータ
	 * @param num           : 弾数
	 * @param distance      : 発射地点からこの分だけ離して発射する
	 * @param rinSpan       : 輪状空間の広がり。大きいほど輪状空間が大きくなりばらつく
	 */
	public static final void createRandomRingShot(
		EntityLivingBase user, Entity source, Vec3 pos,
		Vec3 angle, float rotationSpeed, int rotationEnd,
		double firstSpeed, double limitSpeed, double acceleration,
		Vec3 gravity,
		ShotData shot, int num, double distance, float ringSpan)
	{
		float ringMaxSpan = ringSpan / 2F;
		float yaw = getYawFromVector(-angle.xCoord, angle.zCoord);
		float pitch = getPitchFromVector(angle.xCoord, angle.yCoord, angle.zCoord);
		Vec3 baseVec;
		Vec3 overVec = getVecFromAngle(-yaw, pitch + 90F, 1.0D);
		Vec3 extendVec;
		float angle2;
		Random rand = new Random();
		
		Vec3 movingVec;
		
		
		int color = new Random().nextInt(7);
		int rainbowPattern[] = {RED, ORANGE, YELLOW, GREEN, AQUA, BLUE, PURPLE};
		boolean random = false;
		boolean rainbow = false;
		if(shot != null)
		{
			if(shot.color == RANDOM)
			{
				random = true;
			}
			else if(shot.color == RAINBOW)
			{
				rainbow = true;
			}
		}
		
		if(random)
		{
			shot.color = new Random().nextInt(8);
		}
		
		
		for(int i = 0; i < num; i++)
		{
			if(rainbow)
			{
				shot.color = rainbowPattern[color % 7];
				color++;
			}

			angle2 = rand.nextFloat() * 360F;
			baseVec = getVecFromAngle(-yaw, pitch + rand.nextFloat() * ringMaxSpan, 1.0D);
			movingVec = getVectorFromRotation(-angle.xCoord, -angle.yCoord, angle.zCoord, baseVec.xCoord, baseVec.yCoord, baseVec.zCoord, angle2);
			extendVec = getVectorFromRotation(-angle.xCoord, -angle.yCoord, angle.zCoord, overVec.xCoord, overVec.yCoord, overVec.zCoord, angle2);
			createShot(user, source, Vec3.createVectorHelper(pos.xCoord - extendVec.xCoord * distance, pos.yCoord - extendVec.yCoord * distance, pos.zCoord + extendVec.zCoord * distance), Vec3.createVectorHelper(-movingVec.xCoord, -movingVec.yCoord, movingVec.zCoord),
					angle2, angle, rotationSpeed, rotationEnd,
					firstSpeed, limitSpeed, acceleration, gravity,
					shot);
		}
		if(random)
		{
			shot.color = RANDOM;
		}
		else if(rainbow)
		{
			shot.color = RAINBOW;
		}
	}
	
	/**
	 * 目の前の輪状空間にランダムに弾を発射する
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param speed	     : 弾の速度
	 * @param shot			 : 弾の動作以外のデータ
	 * @param num           : 弾数
	 * @param rinSpan       : 輪状空間の広がり。大きいほど輪状空間が大きくなりばらつく
	 */
	public static final void createRandomRingShot(
			EntityLivingBase user, Vec3 pos,
			Vec3 angle, double speed, ShotData shot, int num, float ringSpan)
	{
		createRandomRingShot(user, pos, angle, 
				speed, speed, 0.0D, shot, num, 0.0D, ringSpan);
	}
	
	
	/**
	 * 目の前の輪状空間にランダムに弾を発射する
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param shot			 : 弾の動作以外のデータ
	 * @param num           : 弾数
	 * @param distance      : 発射地点からこの分だけ離して発射する
	 * @param rinSpan       : 輪状空間の広がり。大きいほど輪状空間が大きくなりばらつく
	 */
	public static final void createRandomRingShot(
			EntityLivingBase user, Vec3 pos,
			Vec3 angle,
			double firstSpeed, double limitSpeed, double acceleration,
			ShotData shot, int num, double distance, float ringSpan)
	{
		createRandomRingShot(user, user, pos, angle, 0F, 9999, firstSpeed, limitSpeed, acceleration,
				gravity_Zero(), shot, num, distance, ringSpan);
	}
	
	
	/**
	 * 球状の弾幕を発射する
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : 弾の発射元。あまり意味は無い
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向（ここでは基準となる弾の方向）
	 * @param rotate		 : 弾が角速度を持つときの回転軸
	 * @param rotationSpeed : 弾が角度を変える速度（毎フレーム指定した角度（度数）で向きを変える）
	 * @param rotationEnd	 : 弾の角速度を0にする時間。特に終わらせないなら9999あたりを入れる
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 弾のX軸方向の重力値。0.0Dで重力の影響を受けない
	 * @param shot			 : 弾の動作以外のデータ
	 * @param way           : 弾数
	 * @param distance      : 発射地点からこの分だけ離して発射する
	 * @param baseAngle     : 基準となる点からの角度のずれ
	 */
	public static final void createSphereShot(
			EntityLivingBase user, Entity source, Vec3 pos,
			Vec3 angle, float angleZ, 
			Vec3 rotate, float rotationSpeed, int rotationEnd,
			double firstSpeed, double limitSpeed, double acceleration,
			Vec3 gravity,
			ShotData shot, int way, double distance, float baseAngle)
	{
		//45発以上は未実装
		if(way >= ways.length)
		{
			return;
		}
		
		Vec3 frontPos = pos(pos.xCoord + angle.xCoord * distance, pos.yCoord + angle.yCoord * distance, pos.zCoord + angle.zCoord * distance);
		Vec3 backPos = pos(pos.xCoord - angle.xCoord * distance, pos.yCoord - angle.yCoord * distance, pos.zCoord - angle.zCoord * distance);
		

		
		float angleBase = 0F;
		float angleSpan = 0F;
		if(ways[way].length >= 2)
		{
			angleSpan = 360F / (float)(ways[way].length - 1);
		}
		boolean flagFB = false;
		int oldWay = 0;
		float slope = 0F;
		
		ShotData shot2 = shot;
		if(shot2.color == RANDOM)
		{
			shot2.color = new Random().nextInt(8);
		}
		
		for(int i = 0; i < ways[way].length; i++)
		{
			//slope = 0F;
			if(ways[way][i] == 1)
			{
				if(!flagFB)
				{
					createShot(user, source, frontPos, angle, angleZ, 
						rotate, rotationSpeed, rotationEnd,
						firstSpeed, limitSpeed, acceleration, gravity,
						shot2);
					flagFB = true;
				}
				else
				{
					createShot(user, source, backPos, angle(-angle.xCoord, -angle.yCoord, -angle.zCoord), angleZ, 
							rotate, rotationSpeed, rotationEnd,
							firstSpeed, limitSpeed, acceleration, gravity,
							shot2);
				}
			}
			else
			{
				createRingShot(user, source, pos, angle, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, shot2, ways[way][i], distance, angleBase, baseAngle + slope);
				slope += 180F / ways[way].length;
			}
			
			oldWay = ways[way].length;
			angleBase += angleSpan;
		}

	}
	
	/**
	 * 球状の弾幕を発射する
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : 弾の発射元。あまり意味は無い
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向（ここでは基準となる弾の方向）
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 弾のX軸方向の重力値。0.0Dで重力の影響を受けない
	 * @param shot			 : 弾の動作以外のデータ
	 * @param way           : 弾数
	 * @param distance      : 発射地点からこの分だけ離して発射する
	 * @param baseAngle     : 基準となる点からの角度のずれ
	 */
	public static final void createSphereShot(
			EntityLivingBase user, Entity source, Vec3 pos,
			Vec3 angle, float angleZ, 
			double firstSpeed, double limitSpeed, double acceleration,
			Vec3 gravity,
			ShotData shot, int way, double distance, float baseAngle)
	{
		createSphereShot(user, source, pos, angle, angleZ, rotate_Default(), 0.0F, 9999, firstSpeed, limitSpeed, acceleration,
				gravity, shot, way, distance, baseAngle);
	}
	
	/**
	 * 球状の弾幕を発射する
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向（ここでは基準となる弾の方向）
	 * @param speed	     : 弾の速度
	 * @param gravity       : 弾のX軸方向の重力値。0.0Dで重力の影響を受けない
	 * @param shot			 : 弾の動作以外のデータ
	 * @param way           : 弾数
	 * @param baseAngle     : 基準となる点からの角度のずれ
	 */
	public static final void createSphereShot(
			EntityLivingBase user, Vec3 pos, Vec3 angle, double speed, ShotData shot, int way, float baseAngle)
	{
		createSphereShot(user, user, pos, angle, 0F, rotate_Default(), 0.0F, 9999, speed, speed, 0.0D, gravity_Zero(),
				shot, way, 0.0D, baseAngle);
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*		レーザー生成系	Create Lasers																					　　　　*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/**
	 * すべての移動レーザー発射の基礎となる処理		Create Laser Base of Can Move
	 * @param user          : レーザーの持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : レーザーの発射元。あまり意味は無い
	 * @param pos           : レーザーの発射地点
	 * @param angle         : レーザーの進行方向（ここでは基準となる弾の方向）
	 * @param firstSpeed    : レーザーの初速度
	 * @param limitSpeed    : レーザーの限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : レーザーの加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 未実装　gravity_Zero()とお願いします
	 * @param laser         : レーザーの動作以外のデータ
	 * @return
	 */
	public static final EntityTHLaser createLaserA(	EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle,
												double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity,
												LaserData laser)
	{
		if(laser.color == RANDOM )
		{
			laser.color = new Random().nextInt(8);
		}
		else if(laser.color == RAINBOW)
		{
			laser.color = new Random().nextInt(7);
		}
		EntityTHLaser entityLaser = new EntityTHLaser(source.worldObj, user, source, pos, angle, 0F, rotate_Default(), 0F, 9999,
												firstSpeed, limitSpeed, acceleration, gravity,
												laser);
		if(!source.worldObj.isRemote)
		{
			//レーザーを生成する
			source.worldObj.spawnEntityInWorld(entityLaser);
		}
		return entityLaser;
	}
	
	/*最も一般的な移動レーザーを生成
	 * user : レーザーの使用者
	 * posX : レーザーの発射地点のX座標
	 * posY :
	 * posZ :
	 * angleYaw : レーザーの水平方向の角度
	 * anglePitch  : レーザーの水平方向の角度
	 * speed   : レーザーの速度
	 * damage  : レーザーのダメージ
	 * color   : レーザーの色
	 * width   : レーザーの太さ
	 * length  : レーザーの長さ
	 */
	/**
	 * 最も単純な移動レーザー発射を出す処理
	 * @param user          : レーザーの持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param pos           : レーザーの発射地点
	 * @param angle         : レーザーの進行方向（ここでは基準となる弾の方向）
	 * @param speed         : レーザーの速度
	 * @param gravity       : 未実装　gravity_Zero()とお願いします
	 * @param laser         : レーザーの動作以外のデータ
	 * @return
	 */
	public static final EntityTHLaser createLaserA(EntityLivingBase user, Vec3 pos, Vec3 angle, double speed, LaserData laser)
	{
		return createLaserA(	user, pos, angle, speed, speed, 0.0D, laser);
	}
	
	
	/**
	 * 移動レーザーを出す処理。最も一般的なもの
	 * @param user          : レーザーの持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param pos           : レーザーの発射地点
	 * @param angle         : レーザーの進行方向（ここでは基準となる弾の方向）
	 * @param firstSpeed    : レーザーの初速度
	 * @param limitSpeed    : レーザーの限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : レーザーの加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 未実装　gravity_Zero()とお願いします
	 * @param laser         : レーザーの動作以外のデータ
	 * @return
	 */
	public static final EntityTHLaser createLaserA(EntityLivingBase user, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, LaserData laser)
	{
		return createLaserA(	user, user, pos, angle, firstSpeed, limitSpeed, acceleration, gravity_Zero(), laser);
	}

	
	/**すべてのn-wayレーザーの基礎となる処理
	 * @param user          : レーザーの持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : レーザーの発射元。あまり意味は無い
	 * @param pos           : レーザーの発射地点
	 * @param angle         : レーザーの進行方向（ここでは基準となる弾の方向）
	 * @param firstSpeed    : レーザーの初速度
	 * @param limitSpeed    : レーザーの限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : レーザーの加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 未実装　gravity_Zero()とお願いします
	 * @param laser         : レーザーの動作以外のデータ
	 * @param way           : 弾数
	 * @param wideAngle     : 扇状の成す角度
	 * @param distance      : 発射地点からこの分だけ離して発射する
	 */
	public static final void createWideLaserA(EntityLivingBase user, Entity source, Vec3 pos,
											Vec3 angle, double firstSpeed, double limitSpeed, double acceleration,
											Vec3 gravity,
											LaserData laser, int way, float wideAngle, double distance)
	{
		//弾の発射方向に対して上方に９０度回転させたベクトル（回転軸）を取得する
		float yaw = getYawFromVector(angle.xCoord, angle.zCoord);
		float pitch = getPitchFromVector(angle.xCoord, angle.yCoord, angle.zCoord);
		Vec3 rotateVec = THShotLib.getVecFromAngle(-yaw, - pitch + 90F, 1.0F);
		//扇の成す角度の半分、取得した回転軸を回転させる
		float angle2 = -wideAngle / 2F;
		float span = wideAngle / (way - 1);
		
		boolean rainbow = false;
		int color = new Random().nextInt(7);
		int rainbowPattern[] = {RED, ORANGE, YELLOW, GREEN, AQUA, BLUE, PURPLE};
		boolean random = false;
		if(laser.color == RANDOM)
		{
			random = true;
		}
		else if(laser.color == RAINBOW)
		{
			rainbow = true;
		}
		//扇の角度になるまで回転軸に沿って弾を配置する
		for(int i = 0; i < way; i++)
		{
			if(random)
			{
				laser.color = new Random().nextInt(8);
			}
			else if(rainbow)
			{
				laser.color = rainbowPattern[color % 7];
				color++;
			}
			Vec3 angleVec = getVectorFromRotation(rotateVec.xCoord, rotateVec.yCoord, rotateVec.zCoord, angle.xCoord, angle.yCoord, angle.zCoord, angle2);
			createLaserA(user, source, pos(pos.xCoord + angleVec.xCoord * distance, pos.yCoord + angleVec.yCoord * distance, pos.zCoord + angleVec.zCoord * distance), angleVec,
					firstSpeed, limitSpeed, acceleration, gravity, laser);
			angle2 += span;
		}
	}
	
	/**最も単純なn-wayレーザーを出す処理
	 * @param user          : レーザーの持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param pos           : レーザーの発射地点
	 * @param angle         : レーザーの進行方向（ここでは基準となる弾の方向）
	 * @param speed         : レーザーの初速度
	 * @param laser         : レーザーの動作以外のデータ
	 * @param way           : 弾数
	 * @param wideAngle     : 扇状の成す角度
	 */
	public static final void createWideLaserA(EntityLivingBase user, Vec3 pos, Vec3 angle, double speed,
			LaserData laser, int way, float wideAngle)
	{
		createWideLaserA(user, pos, angle, speed, speed, 0.0D, laser, way, wideAngle);
	}
	
	/**一般的なn-wayレーザーを出す処理
	 * @param user          : レーザーの持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param pos           : レーザーの発射地点
	 * @param angle         : レーザーの進行方向（ここでは基準となる弾の方向）
	 * @param firstSpeed    : レーザーの初速度
	 * @param limitSpeed    : レーザーの限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : レーザーの加速度。毎フレームこの数値分速く（遅く）なる
	 * @param laser         : レーザーの動作以外のデータ
	 * @param way           : 弾数
	 * @param wideAngle     : 扇状の成す角度
	 */
	public static final void createWideLaserA(EntityLivingBase user, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration,
			LaserData laser, int way, float wideAngle)
	{
		createWideLaserA(user, user, pos, angle, firstSpeed, limitSpeed, acceleration,
				gravity_Zero(), laser, way, wideAngle, 0.0D);
	}
	
	/**すべての全方位レーザーの基礎となる処理
	 * @param user          : レーザーの持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : レーザーの発射元。あまり意味は無い
	 * @param pos           : レーザーの発射地点
	 * @param angle         : レーザーの進行方向（ここでは基準となる弾の方向）
	 * @param firstSpeed    : レーザーの初速度
	 * @param limitSpeed    : レーザーの限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : レーザーの加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 未実装　gravity_Zero()とお願いします
	 * @param laser         : レーザーの動作以外のデータ
	 * @param way           : 弾数
	 * @param distance      : 発射地点からこの分だけ離して発射する
	 */
	public static final void createCircleLaserA(EntityLivingBase user, Entity source, Vec3 pos,
											Vec3 angle, double firstSpeed, double limitSpeed, double acceleration,
											Vec3 gravity,
											LaserData laser, int way, double distance)
	{
		createWideLaserA(user, source, pos,
				angle, firstSpeed, limitSpeed, acceleration,
				gravity,
				laser, way + 1, 360F, distance);
	}
	
	/**最も単純な全方位レーザーを出す処理
	 * @param user          : レーザーの持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param pos           : レーザーの発射地点
	 * @param angle         : レーザーの進行方向（ここでは基準となる弾の方向）
	 * @param speed         : レーザーの速度
	 * @param laser         : レーザーの動作以外のデータ
	 * @param way           : 弾数
	 */
	public static final void createCircleLaserA(EntityLivingBase user, Vec3 pos, Vec3 angle, double speed,
			LaserData laser, int way)
	{
		createCircleLaserA(user, pos, angle, speed, speed, 0.0D, laser, way);
	}
	
	/**一般的な全方位レーザーを出す処理
	 * @param user          : レーザーの持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param pos           : レーザーの発射地点
	 * @param angle         : レーザーの進行方向（ここでは基準となる弾の方向）
	 * @param firstSpeed    : レーザーの初速度
	 * @param limitSpeed    : レーザーの限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : レーザーの加速度。毎フレームこの数値分速く（遅く）なる
	 * @param laser         : レーザーの動作以外のデータ
	 * @param way           : 弾数
	 */
	public static final void createCircleLaserA(EntityLivingBase user, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration,
			LaserData laser, int way)
	{
		createCircleLaserA(user, user, pos, angle, firstSpeed, limitSpeed, acceleration,
				gravity_Zero(), laser, way, 0.0D);
	}
	
	/**
	 * 目の前にリング状のレーザーを発射する基礎処理
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : 弾の発射元。あまり意味は無い
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 弾のX軸方向の重力値。0.0Dで重力の影響を受けない
	 * @param laser		 : 弾の動作以外のデータ
	 * @param way           : 弾数
	 * @param distance      : 発射地点からこの分だけ離して発射する
	 * @param rinSpan       : リングの広がり。大きいほどリングが大きくなり中央ががら空きになる
	 * @param ringBaseAngle : リングの起点となる角度
	 */
	public static final void createRingLaserA(
		EntityLivingBase user, Entity source, Vec3 pos,
		Vec3 angle, 
		double firstSpeed, double limitSpeed, double acceleration,
		Vec3 gravity,
		LaserData laser, int way, double distance, float ringSpan, float ringBaseAngle)
	{
		ringSpan /= 2F;
		float yaw = getYawFromVector(-angle.xCoord, angle.zCoord);
		float pitch = getPitchFromVector(angle.xCoord, angle.yCoord, angle.zCoord);
		Vec3 baseVec = getVecFromAngle(-yaw, pitch + ringSpan, 1.0D);
		Vec3 overVec = getVecFromAngle(-yaw, pitch + 90F, 1.0D);
		Vec3 extendVec;
		float angle2 = ringBaseAngle;
		float span = 360F / way;
		
		Vec3 movingVec;
		
		int color = new Random().nextInt(7);
		int rainbowPattern[] = {RED, ORANGE, YELLOW, GREEN, AQUA, BLUE, PURPLE};
		boolean random = false;
		boolean rainbow = false;
		LaserData laser2 = laser;
		if(laser2.color == RANDOM)
		{
			random = true;
		}
		else if(laser2.color == RAINBOW)
		{
			rainbow = true;
		}
		
		if(random)
		{
			laser2.color = new Random().nextInt(8);
		}
		
		
		for(int i = 0; i < way; i++)
		{
			if(rainbow)
			{
				laser2.color = rainbowPattern[color % 7];
				color++;
			}
			
			movingVec = getVectorFromRotation(-angle.xCoord, -angle.yCoord, angle.zCoord, baseVec.xCoord, baseVec.yCoord, baseVec.zCoord, angle2);
			extendVec = getVectorFromRotation(-angle.xCoord, -angle.yCoord, angle.zCoord, overVec.xCoord, overVec.yCoord, overVec.zCoord, angle2);
			createLaserA(user, source, pos(pos.xCoord - extendVec.xCoord * distance, pos.yCoord - extendVec.yCoord * distance, pos.zCoord + extendVec.zCoord * distance), angle(-movingVec.xCoord, -movingVec.yCoord, movingVec.zCoord),
					firstSpeed, limitSpeed, acceleration, gravity,
					laser2);
			angle2 += span;
		}
		if(random)
		{
			laser.color = RANDOM;
		}
		else if(rainbow)
		{
			laser.color = RAINBOW;
		}
	}
	
	/**
	 * 目の前にリング状のレーザーを発射する基礎処理
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param laser		 : 弾の動作以外のデータ
	 * @param way           : 弾数
	 * @param distance      : 発射地点からこの分だけ離して発射する
	 * @param rinSpan       : リングの広がり。大きいほどリングが大きくなり中央ががら空きになる
	 * @param ringBaseAngle : リングの起点となる角度
	 */
	public static final void createRingLaserA(
		EntityLivingBase user, Vec3 pos,
		Vec3 angle, 
		double firstSpeed, double limitSpeed, double acceleration,
		LaserData laser, int way, double distance, float ringSpan, float ringBaseAngle)
	{
		createRingLaserA(user, user, pos, angle, firstSpeed, limitSpeed, acceleration, gravity_Zero(), laser,
				way, distance, ringSpan, ringBaseAngle);
	}
	
	
	/**
	 * 目の前の輪状空間にランダムにレーザーを発射する
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : 弾の発射元。あまり意味は無い
	 * @param posX          : 弾のX座標の発射地点。
	 * @param posY          : 弾のY座標の発射地点。THShotLib.getPosYFromEye(living)を使えば、プレイヤーの目より少し低い位置に生成される。
	 * @param posZ          :
	 * @param vectorX       : 弾のX方向のベクトル値
	 * @param vectorY       :
	 * @param vectorZ       :
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param vectorX_G     : 弾のX軸方向の重力値。0.0Dで重力の影響を受けない
	 * @param vectorY_G     :
	 * @param vectorZ_G     :
	 * @param damage        : 弾のダメージ
	 * @param color         : レーザーの色
	 * 							ex: THShotLib.YELLOW　で黄色のレーザーというように指定できる
	 * @param width         : レーザーの太さ。当たり判定、描画サイズに関わる
	 * @param endTime       : 弾の消滅時間。この時間になったら強制的に消滅する。懐中時計で止められている場合は時間は進まず停滞する
	 * @param delay         : 弾の遅延時間。この時間が経過後に実体化して動作を起こす。それまでは光のエフェクトだけで何もしない
	 * @param special       : 弾の特殊な処理を指定する。普通の弾は0。THShotLib.BOUND01とすれば１回跳ね返る弾になる
	 * @param length        : レーザーの長さ
	 * @param num           : 弾数
	 * @param distance      : 発射地点からこの分だけ離して発射する
	 * @param rinSpan       : 輪状空間の広がり。大きいほど輪状空間が大きくなりばらつく
	 */
	public static final void createRandomRingLaser(
		EntityLivingBase user, Entity source, Vec3 pos,
		Vec3 angle,
		double firstSpeed, double limitSpeed, double acceleration,
		Vec3 gravity,
		LaserData laser, int num, double distance, float ringSpan)
	{
		float ringMaxSpan = ringSpan / 2F;
		float yaw = getYawFromVector(-angle.xCoord, angle.zCoord);
		float pitch = getPitchFromVector(angle.xCoord, angle.yCoord, angle.zCoord);
		Vec3 baseVec;
		Vec3 overVec = getVecFromAngle(-yaw, pitch + 90F, 1.0D);
		Vec3 extendVec;
		float angle2;
		Random rand = new Random();
		
		Vec3 movingVec;
		for(int i = 0; i < num; i++)
		{
			angle2 = rand.nextFloat() * 360F;
			baseVec = getVecFromAngle(-yaw, pitch + rand.nextFloat() * ringMaxSpan, 1.0D);
			movingVec = getVectorFromRotation(-angle.xCoord, -angle.yCoord, angle.zCoord, baseVec.xCoord, baseVec.yCoord, baseVec.zCoord, angle2);
			extendVec = getVectorFromRotation(-angle.xCoord, -angle.yCoord, angle.zCoord, overVec.xCoord, overVec.yCoord, overVec.zCoord, angle2);
			
			createLaserA(user, source, pos(pos.xCoord - extendVec.xCoord * distance, pos.yCoord - extendVec.yCoord * distance, pos.zCoord + extendVec.zCoord * distance),angle( -movingVec.xCoord, -movingVec.yCoord, movingVec.zCoord), 
					firstSpeed, limitSpeed, acceleration, gravity,
					laser);
		}
	}
	
	/**
	 * 球状のレーザーを発射する
	 * @param user          : レーザーの持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : レーザーの発射元。あまり意味は無い
	 * @param pos           : レーザーの発射地点
	 * @param angle         : レーザーの進行方向（ここでは基準となる弾の方向）
	 * @param firstSpeed    : レーザーの初速度
	 * @param limitSpeed    : レーザーの限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : レーザーの加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : レーザーのX軸方向の重力値。0.0Dで重力の影響を受けない
	 * @param laser		 : レーザーの動作以外のデータ
	 * @param way           : 弾数
	 * @param distance      : 発射地点からこの分だけ離して発射する
	 * @param baseAngle     : 基準となる点からの角度のずれ
	 */
	public static final void createSphereLaserA(
			EntityLivingBase user, Entity source, Vec3 pos,
			Vec3 angle, 
			double firstSpeed, double limitSpeed, double acceleration,
			Vec3 gravity,
			LaserData laser, int way, double distance, float baseAngle)
	{
		//45発以上は未実装
		if(way >= ways.length)
		{
			return;
		}
		
		Vec3 frontPos = pos(pos.xCoord + angle.xCoord * distance, pos.yCoord + angle.yCoord * distance, pos.zCoord + angle.zCoord * distance);
		Vec3 backPos = pos(pos.xCoord - angle.xCoord * distance, pos.yCoord - angle.yCoord * distance, pos.zCoord - angle.zCoord * distance);
		

		
		float angleBase = 0F;
		float angleSpan = 0F;
		if(ways[way].length >= 2)
		{
			angleSpan = 360F / (float)(ways[way].length - 1);
		}
		boolean flagFB = false;
		int oldWay = 0;
		float slope = 0F;
		
		LaserData laser2 = laser;
		if(laser2.color == RANDOM)
		{
			laser2.color = new Random().nextInt(8);
		}
		
		for(int i = 0; i < ways[way].length; i++)
		{
			//slope = 0F;
			if(ways[way][i] == 1)
			{
				if(!flagFB)
				{
					createLaserA(user, source, frontPos, angle, 
						firstSpeed, limitSpeed, acceleration, gravity,
						laser2);
					flagFB = true;
				}
				else
				{
					createLaserA(user, source, frontPos, angle(-angle.xCoord, -angle.yCoord, -angle.zCoord), 
							firstSpeed, limitSpeed, acceleration, gravity,
							laser2);
				}
			}
			else
			{
				createRingLaserA(user, source, pos, angle, firstSpeed, limitSpeed, acceleration, gravity, laser2, ways[way][i], distance, angleBase, baseAngle + slope);
				slope += 180F / ways[way].length;
			}
			
			oldWay = ways[way].length;
			angleBase += angleSpan;
		}
	}
	
	/**
	 * すべての設置レーザーの基礎となる生成処理　　
	 * 仕様変更がある可能性が大であり、不具合があるので設置レーザーを使うのは非推奨
	 * @param user
	 * @param setting
	 * @param pos
	 * @param angle
	 * @param rotate
	 * @param rotateYawSpeed
	 * @param rotationEnd
	 * @param laser
	 * @param set
	 * @param setLength
	 * @param setYOffset
	 * @return
	 */
	public static final EntityTHSetLaser createLaserB(EntityLivingBase user, Entity setting, Vec3 pos, 
			Vec3 angle, Vec3 rotate, float rotateYawSpeed, int rotationEnd,
			LaserData laser, Entity set, double setLength, double setYOffset)
	{
		EntityTHSetLaser entityLaser = new EntityTHSetLaser(setting.worldObj, user, setting,
				pos, angle, 0F,
				rotate, rotateYawSpeed, rotationEnd, laser, set, setLength, setYOffset);
		    	
		if(!setting.worldObj.isRemote)
		{
			//レーザーを生成する
			setting.worldObj.spawnEntityInWorld(entityLaser);
		}
		return entityLaser;   	
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*		特殊弾の生成	Create Special Shot																									*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 核弾を生成する基礎処理
	 * @param user
	 * @param source
	 * @param pos
	 * @param angle
	 * @param slope
	 * @param rotate
	 * @param rotationSpeed
	 * @param rotationEnd
	 * @param firstSpeed
	 * @param limitSpeed
	 * @param acceleration
	 * @param gravity
	 * @param shot
	 * @return
	 */
	public static EntityNuclearShot createNuclearShot(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float slope,
			Vec3 rotate, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity,
			ShotData shot)
	{
		EntityNuclearShot nuclearShot = new EntityNuclearShot(source.worldObj, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd,
				firstSpeed, limitSpeed, firstSpeed, gravity, shot);
		if(!source.worldObj.isRemote)
		{
			source.worldObj.spawnEntityInWorld(nuclearShot);//核弾を出現させる
		}
		return nuclearShot;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*		エフェクト系	Effect																									*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * ショット発射音を出す
	 * @param entity : 音を出すEntity
	 */
	public static void playShotSound(Entity entity)
	{
		entity.worldObj.playSoundAtEntity(entity, "random.bow", 2.0F, 1.2F);//音を出す
	}
	
	/**
	 * 爆発の音とパーティクルを出す
	 * @param world
	 * @param posX
	 * @param posY
	 * @param posZ
	 * @param explosionSize
	 */
    public static void explosionEffect(World world, double posX, double posY, double posZ, float explosionSize)
    {
        world.playSoundEffect(posX, posY, posZ, "random.explosion", 2.0F, 3.0F);

        if (explosionSize >= 2.0F)
        {
            world.spawnParticle("hugeexplosion", posX, posY, posZ, 1.0D, 0.0D, 0.0D);
        }
        else
        {
            world.spawnParticle("largeexplode", posX, posY, posZ, 1.0D, 0.0D, 0.0D);
        }
    }
    
	/**
	 * 軽い爆発の音とパーティクルを出す
	 * @param world
	 * @param posX
	 * @param posY
	 * @param posZ
	 * @param explosionSize
	 */
    public static void explosionEffect2(World world, double posX, double posY, double posZ, float explosionSize)
    {
        world.playSoundEffect(posX, posY, posZ, "fireworks.blast", 2.0F, 3.0F);

        if (explosionSize >= 2.0F)
        {
            world.spawnParticle("hugeexplosion", posX, posY, posZ, 1.0D, 0.0D, 0.0D);
        }
        else
        {
            world.spawnParticle("largeexplode", posX, posY, posZ, 1.0D, 0.0D, 0.0D);
        }
    }
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*		弾幕関連処理系	Danmaku Handle																							*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

    
    
    
    /**
     * 周囲から指定の、もしくはすべての弾幕を取り除く
     * @param centerEntity : 弾消しの中心となるEntity
     * @param range        : 弾消しの有効距離
     * @param target       : "ALL"ですべての弾、"Enemy"で敵弾、"Player"でプレイヤーの弾、"Other"で中心となるEntityの所有する弾以外をすべて消す
     * @param isDropBounus : 弾消しボーナスを出すかどうか。出した場合弾の元に変換され、出さない場合完全に消滅だけする
     * @return       : 消した弾の数
     */
    public static int danmakuRemove(Entity centerEntity, double range, String target, boolean isDropBounus)
    {
    	int count = 0;
		List list = centerEntity.worldObj.getEntitiesWithinAABBExcludingEntity(centerEntity, centerEntity.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(range, range, range));
		for(int k = 0; k < list.size(); k++)
		{
			Entity entity = (Entity)list.get(k);
			if(entity instanceof EntityTHShot)
			{
				EntityTHShot entityTHShot = (EntityTHShot)entity;
				if(target.equals("ALL"))
				{
					if(isDropBounus)
					{
						entityTHShot.shotFinishBonus();
					}
					else
					{
						entityTHShot.setDead();
					}
					count++;
				}
				else if(target.equals("Enemy"))
				{
					if(entityTHShot.user instanceof EntityPlayer == false)
					{
						if(isDropBounus)
						{
							entityTHShot.shotFinishBonus();
						}
						else
						{
							entityTHShot.setDead();
						}
						count++;
					}
				}
				else if(target.equals("Player"))
				{
					if(entityTHShot.user instanceof EntityPlayer)
					{
						if(isDropBounus)
						{
							entityTHShot.shotFinishBonus();
						}
						else
						{
							entityTHShot.setDead();
						}
						count++;
					}
				}
				else if(target.equals("Other"))
				{
					if(entityTHShot.user != centerEntity)
					{
						if(isDropBounus)
						{
							entityTHShot.shotFinishBonus();
						}
						else
						{
							entityTHShot.setDead();
						}
						count++;
					}
				}
			}
		}
		return count;
    }
    
    /**
     * 倒れたときに周囲の妖精にダメージを与えて消える。距離があるほどダメージが少なくなる
     * @param deadEntity   : 倒れたEntity
     * @param range        : 周囲にダメージを与える有効距離
     * @param maxDamage    : 最も接近していた時のダメージ
     */
    public static void banishExplosion(Entity deadEntity, double range, float maxDamage)
    {
		List list = deadEntity.worldObj.getEntitiesWithinAABBExcludingEntity(deadEntity, deadEntity.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(range, range, range));
		for(int k = 0; k < list.size(); k++)
		{
			Entity entity = (Entity)list.get(k);
			
			if(entity instanceof EntityTHFairy && entity instanceof EntityFamiliar == false)
			{
				EntityTHFairy fairy = (EntityTHFairy)entity;
				double distance = entity.getDistanceToEntity(deadEntity);
				
				if(distance <= range)
				{
					float damage = maxDamage * (1.0F - (float)(distance / range));
					if(damage < 2.0F)
					{
						damage = 2.0F;
					}
					fairy.attackEntityFrom(DamageSource.causeIndirectMagicDamage(entity, deadEntity), damage);
				}
				
			}
		}
    }
	
    
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*		計算系	Mathematics																										*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

    /**
     * 各成分の位置から位置ベクトルを返す。
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static Vec3 pos(double x, double y, double z)
    {
    	return Vec3.createVectorHelper(x, y, z);
    }
    
    /**
     * Entityの位置ベクトルを返す。
     * この座標のY座標はEntityによっては足元になる。弾は基本的に中心になる
     * @param entity
     * @return
     */
    public static Vec3 pos_Entity(Entity entity)
    {
    	return pos(entity.posX, entity.posY, entity.posZ);
    }
    
    /**
     * Entityの位置ベクトルを返す。
     * heightでｙ座標を調整できる
     * @param entity
     * @param height ｙ座標の調整値
     * @return
     */
    public static Vec3 pos_Entity(Entity entity, double height)
    {
    	return pos(entity.posX, entity.posY + height, entity.posZ);
    }
    
    /**
     * 生物の位置ベクトルを返す。
     * この座標のY座標はその生物の目より少し下になる
     * @param living
     * @return
     */
    public static Vec3 pos_Living(EntityLivingBase living)
    {
    	return pos(living.posX, getPosYFromEye(living), living.posZ);
    }
    
    /**
     * ある地点からある方向に一定距離離れた位置ベクトルを返す
     * @param pos 起点となる位置ベクトル
     * @param angle 起点からの方向
     * @param distance 起点からの距離
     * @return
     */
    public static Vec3 pos_Distance(Vec3 pos, Vec3 angle, double distance)
    {
    	return pos(pos.xCoord + angle.xCoord * distance, pos.yCoord + angle.yCoord * distance, pos.zCoord + angle.zCoord * distance);
    }
    
    /**
     * ある位置ベクトルからランダムな方向に一定の距離離れた位置ベクトルを返す
     * @param pos
     * @param range
     * @return
     */
	public static Vec3 pos_Random(Vec3 pos, double range)
	{
		Random rand = new Random();
		Vec3 rand_pos = angle(rand.nextFloat() * 360F, rand.nextFloat() * 180F - 90F);
		return pos.addVector(rand_pos.xCoord, rand_pos.yCoord, rand_pos.zCoord);
	}
    
    /**
     * 各成分から方向ベクトルを返す
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static Vec3 angle(double x, double y, double z)
    {
    	return Vec3.createVectorHelper(x, y, z);
    }
    
    /**
     * 水平角度と垂直角度から方向ベクトルを返す
     * @param yaw 水平角度
     * @param pitch 垂直角度
     * @return
     */
    public static Vec3 angle(float yaw, float pitch)
    {
    	return getVecFromAngle(yaw, pitch);
    }
    
    /**
     * A地点からB地点への方向ベクトルを返す（単位ベクトルとして返す）
     * @param posA A地点の位置ベクトル
     * @param posB B地点の位置ベクトル
     * @return A地点からB地点の方向ベクトル
     */
    public static Vec3 angle_ToPos(Vec3 posA, Vec3 posB)
    {
    	return getVectorNomalize(pos(posB.xCoord - posA.xCoord, posB.yCoord - posA.yCoord, posB.zCoord - posA.zCoord));
    }
    
	/**
	 * 全方向からランダムな方向ベクトルを返す
	 * @return
	 */
	public static Vec3 angle_Random()
	{
		Random rand = new Random();
		return angle(rand.nextFloat() * 360F, rand.nextFloat() * 180F - 90F);
	}
	
	/**
	 * 基準の方向ベクトルから指定の角度内のランダムな方向ベクトルを返す
	 * @param angle 基準となる方向ベクトル
	 * @param limitAngle ランダムに変化する限界の角度（度数）
	 * @return
	 */
	public static Vec3 angle_LimitRandom(Vec3 angle, float limitAngle)
	{
		Vec3 rotate = rotate_Random();
		Random rand = new Random();
		return getVectorFromRotation(rotate, angle, rand.nextFloat() * limitAngle - (limitAngle / 2.0F));
		
	}
    
    /**
     * 各成分の値から軸となるベクトルを返す
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static Vec3 rotate(double x, double y, double z)
    {
    	return Vec3.createVectorHelper(x, y, z);
    }
    
    
    /**
     * 特に指定のない真上方向に伸びる回転軸のベクトルを返す
     */
    public static Vec3 rotate_Default()
    {
    	return Vec3.createVectorHelper(0.0D, 1.0D, 0.0D);
    }
    
	/**
	 * ランダムな回転軸ベクトルを返す
	 * @return
	 */
	public static Vec3 rotate_Random()
	{
		Random rand = new Random();
		return angle(rand.nextFloat() * 360F, rand.nextFloat() * 180F - 90F);
	}
    
    
    /**
     * 各成分から重力のベクトルを返す
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static Vec3 gravity(double x, double y, double z)
    {
    	return Vec3.createVectorHelper(x, y, z);
    }
    
    /**
     * y軸方向にのみ働く重力のベクトルを返す
     * @gravityY y軸方向の重力。マイナスで上、プラスで下に向かう
     * @return
     */
    public static Vec3 gravity(double gravityY)
    {
    	return gravity(0.0D, gravityY, 0.0D);
    }
    
    /**
     * 重力の影響を受けない重力のベクトルを返す
     */
    public static Vec3 gravity_Zero()
    {
    	return gravity(0.0D, 0.0D, 0.0D);
    }

    
    /**
     * 重力を受ける場合の標準的な重力のベクトルを返す
     * @return
     */
    public static Vec3 gravity_Default()
    {
    	return gravity(GRAVITY_DEFAULT);
    }
    
    /**
     * 水平方向の方向ベクトルを単位ベクトルにして返す
     * @param vec
     * @return
     */
    public static Vec3 getVecOutY(Vec3 vec)
    {
    	return getVectorNomalize(Vec3.createVectorHelper(vec.xCoord, 0.0D, vec.zCoord));
    }
    
    /**
     * 任意のベクトルの真上にあたる方向ベクトルを返す
     * @param vec
     * @return
     */
    public static Vec3 getOverVector(Vec3 vec)
    {
    	float yaw, pitch;
    	vec = getVecOutY(vec);
    	yaw = getYawFromVector(vec.xCoord, vec.zCoord);
    	pitch = getPitchFromVector(vec.xCoord, vec.yCoord, vec.zCoord);
    	return getVecFromAngle(yaw, pitch + 90F);
    }

    
	/**
	 * DegreeをRadianに変換する
	 * @param deg : 変換するdegree
	 * @return 変換されたradian
	 */
	public static final float radTrans(float deg)
	{
		return (float)((double)deg / 180.0D * Math.PI);
	}
	
	/**
	 * DegreeをRadianに変換する
	 * @param deg : 変換するdegree
	 * @return 変換されたradian
	 */
	public static final float radTrans(double deg)
	{
		return (float)((double)deg / 180.0D * Math.PI);
	}
	
	/**
	 * 角度を-180～180度に変換する
	 * @param angle 変換する角度
	 * @return -180～180度に変換した角度
	 */
	public static final float getAngleMax180(float angle)
	{
		angle %= 360F;
    	if(angle > 180F)
    	{
    		angle -= 360F;
    	}
    	else if(angle < -180)
    	{
    		angle += 360F;
    	}
    	return angle;
	}
	
	/**
	 * 単位ベクトルを返す
	 * @param vec 単位ベクトルにするベクトル
	 * @return 単位ベクトル
	 */
	public static final Vec3 getVectorNomalize(Vec3 vec)
	{
		double length = (double)MathHelper.sqrt_double(vec.xCoord * vec.xCoord + vec.yCoord * vec.yCoord + vec.zCoord * vec.zCoord);
	    length = 1.0D / length;
	    return Vec3.createVectorHelper(vec.xCoord * length, vec.yCoord * length, vec.zCoord * length);
	}
	
	/**
	 * ベクトルの各要素を加算したベクトルを返す
	 * @param originalVec  : 各要素を乗算するベクトル
	 * @param addVec       : 加算するベクトル
	 * @return
	 */
	public static final Vec3 getVectorAdd(Vec3 originalVec, Vec3 addVec)
	{
		return Vec3.createVectorHelper(originalVec.xCoord + addVec.xCoord, originalVec.yCoord + addVec.yCoord, originalVec.zCoord + addVec.zCoord);
		
	}
	
	/**
	 * ベクトルの各要素を乗算したベクトルを返す
	 * @param vec       : 各要素を乗算するベクトル
	 * @param multiply  : 乗算する値
	 * @return
	 */
	public static final Vec3 getVectorMultiply(Vec3 vec, double multiply)
	{
		return Vec3.createVectorHelper(vec.xCoord * multiply, vec.yCoord * multiply, vec.zCoord * multiply);
	}
	
	/**
	 * ２つのベクトル、ベクトルA、ベクトルBの成分から内積を求める
	 * @param vectorAX ベクトルAのX成分
	 * @param vectorAY
	 * @param vectorAZ
	 * @param vectorBX ベクトルBのX成分
	 * @param vectorBY
	 * @param vectorBZ
	 * @return ２つのベクトルの内積
	 */
	public static final double getInnerProduct(double vectorAX, double vectorAY, double vectorAZ, double vectorBX, double vectorBY, double vectorBZ)
	{
		return vectorAX * vectorBX + vectorAY * vectorBY + vectorAZ * vectorBZ;
	}
	
	/**
	 * ２つのベクトル、ベクトルA、ベクトルBの成分から内積を求める
	 * @param vectorA
	 * @param vectorB
	 * @return ２つのベクトルの内積
	 */
	public static final double getInnerProduct(Vec3 vectorA, Vec3 vectorB)
	{
		return vectorA.xCoord * vectorB.xCoord + vectorA.yCoord * vectorB.yCoord + vectorA.zCoord * vectorB.zCoord;
	}
	
	/**
	 * ２つのベクトル、ベクトルA、ベクトルBの間の角度を返す（-180～180度）
	 * @param vectorA
	 * @param vectorB
	 * @return ２つのベクトルの成す角度
	 */
	public static final float getVectorAndVectorAngle(Vec3 vectorA, Vec3 vectorB)
	{
		double inner = getInnerProduct(vectorA, vectorB);
		return getAngleMax180((float)(Math.acos(inner / (vectorA.lengthVector() * vectorB.lengthVector())) / Math.PI * 180.0D));
	}
	
	/**
	 * ２つのベクトル、ベクトルA、ベクトルBの成分から外積を求める。
	 * ２つのベクトルに対して垂直なベクトルが求められる。
	 * @param vectorAX ベクトルAのX成分
	 * @param vectorAY
	 * @param vectorAZ
	 * @param vectorBX ベクトルBのX成分
	 * @param vectorBY
	 * @param vectorBZ
	 * @return ２つのベクトルの外積
	 */
	public static final Vec3 getOuterProduct(double vectorAX, double vectorAY, double vectorAZ, double vectorBX, double vectorBY, double vectorBZ)
	{
		Vec3 vec = Vec3.createVectorHelper(	vectorAY * vectorBZ - vectorAZ * vectorBY, 
												vectorAZ * vectorBX - vectorAX * vectorBZ,
												vectorAX * vectorBY - vectorAY * vectorBX);
		return vec;
	}
	
	/**
	 * ２つのベクトル、ベクトルA、ベクトルBから外積を求める
	 * ２つのベクトルに対して垂直なベクトルが求められる。
	 * @param vectorA
	 * @param vectorB
	 * @return ２つのベクトルの外積
	 */
	public static final Vec3 getOuterProduct(Vec3 vectorA, Vec3 vectorB)
	{
		return getOuterProduct(vectorA.xCoord, vectorA.yCoord, vectorA.zCoord, vectorB.xCoord, vectorB.yCoord, vectorB.zCoord);
	}
	
	/**
	 * ２つの角度の差を求める。-180°～180°の間で返す。
	 * @param angleA : 基準となる角度。これを０°としてangleBとどれだけ差があるかを返す
	 * @param angleB : 基準の角度angleAと比較する角度。
	 * @return angleAに対するangleBの角度（-180～180度で返る）
	 */
	public static final float getAngleAndAngleSpan(float angleA, float angleB)
	{
		angleA = getAngleMax180(angleA);
		angleB = getAngleMax180(angleB);
		
		if(angleA == 0.0F)
		{
			return angleB;
		}
		//angleAが右よりなら
		else if(angleA > 0.0F)
		{
			if(angleB > angleA - 180F)
			{
				return angleB - angleA;
			}
			else
			{
				return (angleB - angleA) + 360F;
			}
		}
		//angleAが左よりなら
		else
		{
			if(angleB < angleA + 180F)
			{
				return angleB - angleA;
			}
			else
			{
				return (angleB - angleA) - 360F;
			}
		}
	}
	
	/**
	 * EntityAが向いている方向と、EntityAに対してEntityBがいる方向の差を求める。水平方向版。（度数）
	 * @param entityA 基準となるEntity
	 * @param entityB 差の対象となるEntity
	 * @return EntityAの向いている方向と、EntityBのいる方向の差（-180～180度で返る）
	 */
	public static final float getEntityAndEntityAngleSpanYaw(Entity entityA, Entity entityB)
	{
		float angleA = getAngleMax180(entityA.rotationYaw);
		float angleB = (float)Math.atan2(entityB.posX - entityA.posX, entityB.posZ - entityA.posZ) / 3.141593F * 180F;
		return getAngleAndAngleSpan(angleA, angleB);
	}
	
	/**
	 * EntityAが向いている方向と、EntityAに対してEntityBがいる方向の差を求める。垂直方向版。（度数）
	 * @param entityA 基準となるEntity
	 * @param entityB 差の対象となるEntity
	 * @return EntityAの向いている方向と、EntityBのいる方向の差（-180～180度で返る）
	 */
	public static final float getEntityAndEntityAngleSpanPitch(Entity entityA, Entity entityB)
	{
		double distanceX = entityB.posX - entityA.posX;
		double distanceY = entityB.posY - entityA.posY;
		double distanceZ = entityB.posZ - entityA.posZ;
		double distanceXZ = Math.sqrt(distanceX * distanceX + distanceZ * distanceZ);
		float angleA = getAngleMax180(entityA.rotationPitch);
		float angleB = (float)Math.atan2(distanceY, distanceXZ) / 3.141593F * 180F;
		return getAngleAndAngleSpan(angleA, angleB);
	}
	
	/**
	 * ０度で最低、１８０，－１８０度で最大値を取る特殊なsin
	 * @param angle
	 * @return
	 */
	public static final double halfAbsSin(float angle)
	{
		angle = angle * 0.5F;
		return Math.abs(Math.sin(angle));
	}

	/**
	 * 目の高さも含めたY座標を返す
	 * @param living      : 目の高さを含めたY座標を取得するEntityLivingBase
	 * @param yAdjustment : 目の高さを基準に、高さの調整をさせる
	 * @return：目の高さに調整したY座標
	 */
	public static final double getPosYFromEye(EntityLivingBase living, double yAdjustment)
	{
		return living.posY + living.getEyeHeight() + Math.sin((double)living.rotationPitch / 180.0D * Math.PI) * yAdjustment + yAdjustment;
	}
	
	/**
	 * 目の高さも含めたY座標を返す。目より少し下から出す標準的な設定
	 * @param living      : 目の高さを含めたY座標を取得するEntityLivingBase
	 * @return ：目の高さに調整したY座標
	 */
	public static final double getPosYFromEye(EntityLivingBase living)
	{
		return living.posY + living.getEyeHeight() + Math.sin((double)living.rotationPitch / 180.0D * Math.PI) * -0.5D + Math.cos((double)living.rotationPitch / 180.0D * Math.PI) * -0.5D;
	}
	
	/**
	 * 角度（度数）と強さからベクトルを取得する
	 * @param yaw   : 水平方向の角度（度数）
	 * @param pitch : 垂直方向の角度（度数）
	 * @param force : ベクトルの強さ
	 * @return ：角度と強さから求まった３次元ベクトル
	*/
	public static final Vec3 getVecFromAngle(float yaw, float pitch, double force)
	{
		double yaw_rad = (double)yaw / 180.0D * Math.PI;
		double pitch_rad = (double)pitch / 180.0D * Math.PI;
		double vectorX = -Math.sin(yaw_rad) * Math.cos(pitch_rad) * force;//X方向　水平方向
		double vectorY = -Math.sin(pitch_rad) * force;//Y方向　上下
		double vectorZ =  Math.cos(yaw_rad) * Math.cos(pitch_rad) * force;//Z方向　水平方向
		return Vec3.createVectorHelper(vectorX, vectorY, vectorZ);
	}
	
	/**
	 * 角度（度数）から単位ベクトルを取得する
	 * @param yaw   : 水平方向の角度（度数）
	 * @param pitch : 垂直方向の角度（度数）
	 * @return ：角度から求まった３次元の単位ベクトル
	*/
	public static final Vec3 getVecFromAngle(float yaw, float pitch)
	{
		return getVecFromAngle(yaw, pitch, 1.0D);
	}
	
	/**
	 * ベクトルから水平方向の角度を返す
	 * @param vectorX : 垂直方向の角度を求めるベクトルのX成分
	 * @param vectorZ : 
	 * @return ベクトルから求まった水平方向の角度
	 */
	public static final float getYawFromVector(double vectorX, double vectorZ)
	{
		return  (float)((Math.atan2(vectorX, vectorZ) * 180D) / 3.1415927410125732D);
	}
	
	/**
	 * ベクトルから垂直方向の角度を返す
	 * @param vectorX : 垂直方向の角度を求めるベクトルのX成分
	 * @param vectorY : 
	 * @param vectorZ : 
	 * @return ベクトルから求まった垂直方向の角度
	 */
	public static final float getPitchFromVector(double vectorX, double vectorY, double vectorZ)
	{
		double f = MathHelper.sqrt_double(vectorX * vectorX + vectorZ * vectorZ);
		return  (float)((Math.atan2(vectorY, f) * 180D) / 3.1415927410125732D);
	}
	
	/**
	 * 特定の角度から水平方向に特定の角度ずれたベクトルを取得する
	 * 要はどんな方向を見ていても、左に３０度ずれた弾の進行方向が変わらないようなベクトルを取得するってもの
	 * @param vectorX : 元のベクトルのX成分
	 * @param vectorY :
	 * @param vectorZ :
	 * @param angle   : 元のベクトルからどれだけ横に角度をずらすか
	 * @param force   : ベクトルの強さ
	 * @return 特定の角度から水平方向に特定の角度ずれたベクトル
	 */
	public static final Vec3 getRotationVector(double vectorX, double vectorY, double vectorZ, float angle, double force)
	{
		float disXZ = (float)Math.sqrt( vectorX * vectorX + vectorZ * vectorZ );
		float angleYaw = (float)Math.atan2(-vectorX, vectorZ);
		float anglePitch  = (float)Math.atan2(-vectorY, disXZ);
		float baseYaw = angleYaw;
		float basePitch = anglePitch;
		double X1,X2,Z1,Z2, vecX, vecY, vecZ;
		float angleYaw_rad, anglePitch_rad;
		Vec3 lookAt = Vec3.createVectorHelper(vectorX, vectorY, vectorZ);
		lookAt.rotateAroundY((float)Math.PI*2);
		angle = angle / 180.0F * (float)Math.PI;
			
		//飛ぶ方向を設定
		angleYaw = angle;//横の角度　0=正面　+1ごとに左にずれていき360で正面に戻る
		anglePitch = 0;//縦の角度　0=正面　+1ごとに上にずれていき360で正面に戻る
		anglePitch_rad = radTrans(anglePitch);
		
		X1 =  Math.sin(angleYaw) * Math.cos(baseYaw);
		Z1 =  Math.sin(angleYaw) * Math.sin(baseYaw);
		X2 =  Math.cos(angleYaw) * Math.sin(anglePitch_rad) * Math.sin(basePitch) * Math.sin(baseYaw);
		Z2 =  Math.cos(angleYaw) * Math.sin(anglePitch_rad) * Math.sin(basePitch) * Math.cos(baseYaw);
		
		vecY = -Math.cos(angleYaw) * Math.sin(basePitch - anglePitch_rad);//Y方向　上下
		vecX =  Math.cos(angleYaw) * Math.cos(anglePitch_rad) * lookAt.xCoord + X1 - X2;//X方向　水平方向
		vecZ =  Math.cos(angleYaw) * Math.cos(anglePitch_rad) * lookAt.zCoord + Z1 + Z2;//Z方向　水平方向
		
		return Vec3.createVectorHelper(vecX * force, vecY * force, vecZ * force);
	}
	
	
	/**
	 * 特定の角度から水平方向に特定の角度ずれたベクトルを取得する
	 * 要はどんな方向を見ていても、左に３０度ずれた弾の進行方向が変わらないようなベクトルを取得するってもの
	 * @param yaw   : 元のベクトルの表す水平方向の角度（度数）
	 * @param pitch : 元のベクトルが表す垂直方向の角度（度数）
	 * @param angle   : 元のベクトルからどれだけ横に角度をずらすか
	 * @param force   : ベクトルの強さ
	 * @return 特定の角度から水平方向に特定の角度ずれたベクトル
	 */
	public static final Vec3 getRotationVectorFromAngle(float yaw, float pitch, float angle, double force)
	{
		Vec3 vec3 = getVecFromAngle(yaw, pitch, 1.0D);
		return getRotationVector(vec3.xCoord, vec3.yCoord, vec3.zCoord, angle, force);
	}
	
	/**
	 * 任意の回転軸rotateVectorに対して、任意のベクトルangleVectorがangle度回転したベクトルを返す
	 * @param rotateVectorX 回転の軸にしたいベクトルのX成分
	 * @param rotateVectorY
	 * @param rotateVectorZ
	 * @param angleVectorX 回転したいベクトルのX成分
	 * @param angleVectorY
	 * @param angleVectorZ
	 * @param angle 回転させたい角度（度数）
	 * @return 任意の回転軸rotateVectorに対して、任意のベクトルangleVectorがangle度回転したベクトル。
	 */
	public static Vec3 getVectorFromRotation(double rotateVectorX, double rotateVectorY, double rotateVectorZ, 
											double angleVectorX, double angleVectorY, double angleVectorZ, float angle)
	{
		double angleRad = (double)angle / 180.0D * (double)Math.PI;
		double sinA = Math.sin(angleRad);
		double cosA = Math.cos(angleRad);
		double returnVectorX = (rotateVectorX * rotateVectorX * (1 - cosA) + cosA)              * angleVectorX + (rotateVectorX * rotateVectorY * (1 - cosA) - rotateVectorZ * sinA) * angleVectorY + (rotateVectorZ * rotateVectorX * (1 - cosA) + rotateVectorY * sinA) * angleVectorZ;
		double returnVectorY = (rotateVectorX * rotateVectorY * (1 - cosA) + rotateVectorZ * sinA) * angleVectorX + (rotateVectorY * rotateVectorY * (1 - cosA) + cosA)              * angleVectorY + (rotateVectorY * rotateVectorZ * (1 - cosA) - rotateVectorX * sinA) * angleVectorZ;
		double returnVectorZ = (rotateVectorZ * rotateVectorX * (1 - cosA) - rotateVectorY * sinA) * angleVectorX + (rotateVectorY * rotateVectorZ * (1 - cosA) + rotateVectorX * sinA) * angleVectorY + (rotateVectorZ * rotateVectorZ * (1 - cosA) + cosA)              * angleVectorZ;
		
		return Vec3.createVectorHelper(returnVectorX, returnVectorY, returnVectorZ);
	}
	
	/**
	 * 任意の回転軸rotateVectorに対して、任意のベクトルangleVectorがangle度回転したベクトルを返す
	 * @param rotateVector 回転の軸にしたいベクトル
	 * @param angleVector 回転したい方向ベクトル
	 * @param angle 回転させたい角度（度数）
	 * @return 任意の回転軸rotateVectorに対して、任意のベクトルangleVectorがangle度回転したベクトル。
	 */
	public static Vec3 getVectorFromRotation(Vec3 rotate, Vec3 angleVector, float angle)
	{
		 return getVectorFromRotation(rotate.xCoord, rotate.yCoord, rotate.zCoord, 
					angleVector.xCoord, angleVector.yCoord, angleVector.zCoord, angle);
	}
	
/*日本語版 */
	/**
	 * すべての弾の基礎となる生成処理	Create Shots Base of All
	 * @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
	 * @param source        : 弾の発射元。あまり意味は無い
	 * @param pos           : 弾の発射地点
	 * @param angle         : 弾の進行方向
	 * @param slope		 : 弾の進行方向を軸とした傾き。度数で指定（180にすると弾が上下ひっくり返る）
	 * @param rotate        : 弾が角度を変えるときに利用する軸のベクトル（回転軸）
	 * @param rotationSpeed : 弾が角度を変える速度（毎フレーム指定した角度（度数）で向きを変える）。回転の軸はrotateVectorX,Y,Z
	 * @param rotationEnd	 : 弾の角速度を0にする時間。特に終わらせないなら9999あたりを入れる
	 * @param firstSpeed    : 弾の初速度
	 * @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
	 * @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
	 * @param gravity       : 弾のX軸方向の重力値。0.0Dで重力の影響を受けない
	 * @param shot			 : 弾の動作以外のデータ
	 * @return 生成したEntityTHShotを返す
	 */
	public static final EntityTHShot 弾生成(EntityLivingBase user, Entity source, Vec3 pos,
											Vec3 angle, float slope, 
											Vec3 rotate, float rotationSpeed, int rotationEnd,
											double firstSpeed, double limitSpeed, double acceleration,
											Vec3 gravity, ShotData shot)
	{
		return createShot( user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd,
					firstSpeed, limitSpeed, acceleration, gravity, shot);
	}

}