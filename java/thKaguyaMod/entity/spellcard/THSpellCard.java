package thKaguyaMod.entity.spellcard;

import static thKaguyaMod.DanmakuConstants.*;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;

/** スペルカード */
public abstract class THSpellCard {
	
	public Random rand = new Random();
	protected World world;
	private int end;
	private int remove;
	private int number;
	/** スペルカードが発動してからの時間 （1/20秒）*/
	public int time;
	/** スペルカードのレベル EASY:1, NORMAL:2, HARD:3, LUNATIC:4
	 *  プレイヤーが使うスペルカードはNORMAL固定
	 */
	public int level;
	/** Entityとしてのスペルカード（フィールドに出現するスペルカード） */
	public EntitySpellCard card;
	/** スペルカードを宣言したEntityLiving */
	public EntityLivingBase user;
	/** スペルカードを宣言されたEntityLiving */
	public EntityLivingBase target;
	
	/** スペルカードのX座標 */
	protected double cardPosX;
	/** スペルカードのY座標 */
	protected double cardPosY;
	/** スペルカードのZ座標 */
	protected double cardPosZ;
	/** スペルカード宣言者のX座標 */
	protected double userPosX;
	/** スペルカード宣言者のY座標 */
	protected double userPosY;
	/** スペルカード宣言者のZ座標 */
	protected double userPosZ;
	/** 宣言対象のX座標 */
	protected double targetPosX;
	/** 宣言対象のY座標 */
	protected double targetPosY;
	/** 宣言対象のZ座標 */
	protected double targetPosZ;
	
	private int needLevel;
	private int originalUserName;
	protected String spellcardName;
	protected String iconName;
	
	public static THSpellCard instance;
	
	/**
	 * 原作所持者（想定所持者）
	 */
	public static final int OLD = -1;
	
	public static final int REIMU = 0;
	public static final int MARISA = 1;
	public static final int RUMIA = 2;
	public static final int DAIYOUSEI = 3;
	public static final int CIRNO = 4;
	public static final int MEIRIN = 5;
	public static final int KOAKUMA = 6;
	public static final int PATCHOULI = 7;
	public static final int SAKUYA = 8;
	public static final int REMILIA = 9;
	public static final int FLANDORE = 10;
	
	public static final int LETTY = 11;
	public static final int CHEN = 12;
	public static final int ALICE = 13;
	public static final int LILY = 14;
	public static final int LUNASA = 15;
	public static final int MERLIN = 16;
	public static final int LYRICA = 17;
	public static final int YOUMU = 18;
	public static final int YUYUKO = 19;
	public static final int RAN = 20;
	public static final int YUKARI = 21;
	
	public static final int SUIKA = 22;
	
	public static final int WRIGGLE = 23;
	public static final int MYSTIA = 24;
	public static final int KEINE = 25;
	public static final int TEI = 26;
	public static final int UDONGEIN = 27;
	public static final int EIRIN = 28;
	public static final int KAGUYA = 29;
	public static final int MOKOU = 30;
	
	public static final int AYA = 31;
	public static final int MEDICINE = 32;
	public static final int YUUKA = 33;
	public static final int KOMACHI = 34;
	public static final int SHIKIEIKI = 35;
	
	public static final int SHIZUHA = 36;
	public static final int MINORIKO = 37;
	public static final int HINA = 38;
	public static final int NITORI = 39;
	public static final int MOMIZI = 40;
	public static final int SANAE = 41;
	public static final int KANAKO = 42;
	public static final int SUWAKO = 43;
	
	public static final int IKU = 44;
	public static final int TENSHI = 45;
	
	public static final int KISUME = 46;
	public static final int YAMAME = 47;
	public static final int PARSEE = 48;
	public static final int YUUGI = 49;
	public static final int SATORI = 50;
	public static final int RIN = 51;
	public static final int UTUHO = 52;
	public static final int KOISHI = 53;
	
	public static final int NAZRIN = 54;
	public static final int KOGASA = 55;
	public static final int ICHIRIN = 56;
	public static final int MINAMITU = 57;
	public static final int SHOU = 58;
	public static final int BYAKUREN = 59;
	public static final int NUE = 60;
	
	public static final int HATATE = 61;
	
	public static final int KYOUKO = 62;
	public static final int YOSHIKA = 63;
	public static final int SEIGA = 64;
	public static final int TOZIKO = 65;
	public static final int FUTO = 66;
	public static final int MIKO = 67;
	public static final int MAMIZOU = 68;
	
	public static final int KOKORO = 69;
	
	public static final int WAKASAGIHIME = 70;
	public static final int SEKIBANKI = 71;
	public static final int KAGEROU = 72;
	public static final int BENBEN = 73;
	public static final int YATUHASHI = 74;
	public static final int SEIJA = 75;
	public static final int SHINMYOUMARU = 76;
	public static final int RAIKO = 77;
	
	public static final int RINNOSUKE = 128;
	public static final int TOKIKO = 129;
	
	public static final int SUNNY = 132;
	public static final int LUNAR = 133;
	public static final int STAR = 134;
	
	public static final int AKYUU = 138;
	
	public static final int TOYOHIME = 142;
	public static final int YORIHIME = 143;
	public static final int REISEN = 144;
	
	public static final int KASEN = 146;
	
	public static final int KOSUZU = 150;
	
	public static final int RENKO = 192;
	public static final int MAERIBERRY = 193;
	
	public static final int OTHER = 254;
	
	/** スペルカードのコンストラクタ */
	public THSpellCard()
	{
		setNeedLevel(1);//宣言に必要なレベルを１に設定する
	}
	
	/**
	 * スペルカードの初期設定を行う
	 * @param worldObj
	 * @param entitySpellCard
	 * @param living_user
	 * @param living_target
	 */
	public void init(World worldObj, EntitySpellCard entitySpellCard, EntityLivingBase living_user, EntityLivingBase living_target, int spLevel)
	{
		world = worldObj;
		card = entitySpellCard;
		user = living_user;
		target = living_target;
		time = 0;
		level = spLevel;
	}
	
	/**
	 * スペルカード共通の毎tickの処理
	 * @return
	 */
	public boolean onUpdate()
	{
		
		cardPosX = card.posX;
		cardPosY = card.posY;
		cardPosZ = card.posZ;

		userPosX = user.posX;
		userPosY = THShotLib.getPosYFromEye(user);
		userPosZ = user.posZ;

		targetPosX = target.posX;
		targetPosY = target.posY + target.height / 2.0D;
		targetPosZ = target.posZ;
		
		spellcard_main();
		
		time++;
		return true;
	}
	
	/**
	 * スペルカードの個別の処理を行う
	 */
	public void spellcard_main()
	{
		
	}
	
	/**
	 * 時間停止中にのみ実行される
	 */
	public void specialProcessInTimeStop()
	{
		if(canMoveInTimeStop())
		{
			setEndTime(getEndTime() + 1);
		}
	}

	/***********************************************************************************************
			弾幕ライブラリにもある関数で頻出のものは直接呼び出せる
	 ***********************************************************************************************/
	
	/**
	 * 任意の位置ベクトルを返す
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Vec3 pos(double x, double y, double z)
	{
		return Vec3.createVectorHelper(x, y, z);
	}
	
	/**
	 * 使用者の位置ベクトルを返す。Y座標は目の高さより少し下
	 * @return
	 */
	public Vec3 pos_User()
	{
		return pos(userPosX, userPosY, userPosZ);
	}
	
	/**
	 * 使用者の位置ベクトルから任意の角度に任意の距離離れた位置ベクトルを返す。Y座標は目の高さより少し下
	 * @return
	 */
	public Vec3 pos_User(Vec3 angle, double distance)
	{
		angle = THShotLib.getVectorNomalize(angle);
		return pos(userPosX + angle.xCoord * distance, userPosY + angle.yCoord, userPosZ + angle.zCoord * distance);
	}
	
	/**
	 * スペルカードの位置ベクトルを返す
	 * @return
	 */
	public Vec3 pos_Card()
	{
		return Vec3.createVectorHelper(cardPosX, cardPosY, cardPosZ);
	}
	
	/**
	 * ターゲットの位置ベクトルを返す
	 * @return
	 */
	public Vec3 pos_Target()
	{
		return THShotLib.pos_Living(target);
	}
	
	/**
	 * 各成分から方向ベクトルを返す
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Vec3 angle(double x, double y, double z)
	{
		return THShotLib.angle(x, y, z);
	}
	
	/**
	 * 水平角度と垂直角度から方向ベクトルを返す
	 * @param yaw
	 * @param pitch
	 * @return
	 */
	public Vec3 angle(float yaw, float pitch)
	{
		return THShotLib.angle(yaw, pitch);
	}
	
	/**
	 * 使用者からターゲットへの方向ベクトルを返す
	 * @return
	 */
	public Vec3 angle_UserToTarget()
	{
		return THShotLib.angle_ToPos(pos_User(), pos_Target());
	}
	
    /**
     * 特に指定のない真上方向に伸びる回転軸のベクトルを返す
     */
    public static Vec3 rotate_Default()
    {
    	return THShotLib.rotate_Default();
    }
	
	/**
	 * 重力の影響を受けない重力のベクトルを返す
	 * @return
	 */
	public Vec3 gravity_Zero()
	{
		return THShotLib.gravity_Zero();
	}
	
	/**
	 * 重力の影響を受ける標準的なベクトルを返す
	 * @return
	 */
	public Vec3 gravity_Default()
	{
		return THShotLib.gravity_Default();
	}
	
	/**
	 * 水平角度と垂直角度からその向きのベクトルを返す
	 * @param yaw
	 * @param pitch
	 * @return
	 */
	public Vec3 getVecFromAngle(float yaw, float pitch)
	{
		return THShotLib.getVecFromAngle(yaw, pitch);
	}
	
	/**
	 * ShotDataを返す
	 * @param form 弾の形状ID
	 * @param color 弾の色ID
	 * @param size 弾のサイズ
	 * @param damage 弾のダメージ
	 * @param delay 弾の遅延時間
	 * @param end 弾の消滅時間
	 * @param special 特殊弾の設定。0で普通。
	 * @return ShotDataを返す
	 */
	public static ShotData shot(int form, int color, float size, float damage, int delay, int end, int special)
	{
		return ShotData.shot(form, color, size, damage, delay, end, special);
	}
	
	public static ShotData shot(int form, int color, float size, float damage, int delay, int end)
	{
		return shot(form, color, size, damage, delay, end, 0);
	}
	
	public static ShotData shot(int form, int color, float size, float damage)
	{
		return shot(form, color, size, damage, 0, 80);
	}
	
	public static ShotData shot(int form, int color)
	{
		return shot(form, color, 0, 80, 0);
	}
	
	public static ShotData shot(int form, int color, int delay)
	{
		return shot(form, color, delay);
	}
	
	public static ShotData shot(int form, int color, int delay, int end)
	{
		return shot(form, color, delay, end, 0);
	}
	
	public static ShotData shot(int form, int color, int delay, int end, int special)
	{
		float size = THShotLib.SIZE[form];
		float damage = THShotLib.DAMAGE[form];
		return shot(form, color, size, damage, delay, end, special);
	}
	
	/***********************************************************************************************
	 ***********************************************************************************************/
	
	/**
	 * スペルカードのナンバーを取得する
	 * スペルカードレジストリで登録した独自の数値
	 * @return スペルカードのナンバーを返す
	 */
	public int getSpellCardNumber()
	{
		return -1;
	}
	
	public String getSpellCardName()
	{
		return spellcardName;
	}
	
	/**
	 * スペルカードを宣言するときに必要なレベル(=消費するレベル)を取得する
	 * @return 必要レベルを返す
	 */
	public int getNeedLevel()
	{
		return needLevel;
	}
	
	/**
	 * 弾消し時間を返す
	 * @return 弾消しする時間（1/20秒）
	 */
	public int getRemoveTime()
	{
		return remove;
	}
	
	/**
	 * スペルカードが終わるtickを返す（20tick = 1秒)
	 * @return
	 */
	public int getEndTime()
	{
		return end;
	}
	
	/**
	 * 原作のスペルカードの使用者のIDを返す。
	 * クリエイティブタブ内のスペルカードを使用者ごとに並べる整理のみに使われる数字で特に設定する必要はない
	 * @return
	 */
	public int getOriginalUserName()
	{
		return originalUserName;
	}
	
	/**
	 * 魔法陣の色を返す
	 * @return
	 */
	public int getSpellCardCircleColor()
	{
		return RED;
	}
	
	
	
	
	public void setSpellCardName(String name)
	{
		spellcardName = name;
	}
	
	/**
	 * スペルカードを宣言するのに必要なレベルを設定する（基本的に１～５の範囲）
	 * @param level
	 */
	public void setNeedLevel(int level)
	{
		needLevel = level;
	}

	/**
	 * 弾消し時間を設定する
	 * @param setTime 弾消しする時間（1/20秒）
	 */
	public void setRemoveTime(int setTime)
	{
		remove = setTime;
	}
	
	
	/**
	 * スペルカードの終了時間を返す
	 * @param setTime
	 */
	public void setEndTime(int setTime)
	{
		end = setTime;
	}
	
	/**
	 * 原作のスペルカードの使用者を設定する。
	 * クリエイティブタブ内のスペルカードを使用者ごとに順番に並べるために使われる。
	 * 使用者IDとなる定数はTHSpellCard内にある。
	 * @param originalUserName
	 */
	public void setOriginalUserName(int originalUserNameID)
	{
		originalUserName = originalUserNameID;
	}
	
	
	
	/**
	 * アイコンの名前を返す（MasterSpark.pngのMasterSparkの部分）
	 * @param name
	 */
	public void setIconName(String name)
	{
		iconName = name;
	}
	
	public String getIconName()
	{
		return iconName;
	}
	
	/**
	 * 時間の停止した状態でも処理を続行できるか（使用者自信が時間停止していないと意味は無い）
	 * 原作範囲なら基本的に咲夜のスペルカード以外使う機会はないかと
	 * @return 処理を続けられるならtrue
	 */
	public boolean canMoveInTimeStop()
	{
		return false;
	}

}
