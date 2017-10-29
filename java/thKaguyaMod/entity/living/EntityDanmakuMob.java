package thKaguyaMod.entity.living;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import thKaguyaMod.THKaguyaLib;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.EntityTHItem;
import thKaguyaMod.entity.effect.EntitySpellCardCircle;
import thKaguyaMod.init.THKaguyaConfig;
import thKaguyaMod.init.THKaguyaItems;

/** 弾幕を使うMOBの定義クラス */
public abstract class EntityDanmakuMob extends EntityCreature// implements IRangedAttackMob
{	
    protected ChunkCoordinates currentFlightTarget;
    /** 最後に動いた時間 */
	protected int lastTime;
	//public EntityLivingBase entityToAttack;
	
	//public EntityLivingBase entityToAttack = null;
	public int attackCounter = 0;
	public int attackInterval;
	public int courseChangeCooldown = 0;
	public double waypointX;
    public double waypointY;
    public double waypointZ;
	
	public int lostTarget;
	
	/** 指定移動するときの方向 */
	protected Vec3 moveVec;
	
	/** 指定移動する時間 */
	protected int moveTimer;
	
	/** 指定移動する最大時間 */
	protected int moveTimerMax;
	
	/** 停止中かどうか？ */
	protected boolean stop;


	
	/** ターゲットとの間に取る間合い */
	protected double attackDistance;
	
	/** 近づくと敵対化する距離 */
	protected double detectionDistance;
	
	/** 飛ぶ地面からの高さ */
	protected int flyingHeight;
	
	/** 飛行モードであるか？
	 * @true  飛行モード
	 * @false 着地モード
	 */
	public boolean isFlyingMode;
	
	
	
	/** 妖精などの出す弾幕のパターンの設定 */
	public int danmakuPattern;
	/** 妖精などの出す弾の発射頻度の設定 */
	public int danmakuSpan;
	/** 妖精などの出す弾の形状設定 */
	public byte shotForm;
	/** 妖精などの出す弾の色設定 */
	public byte shotColor;
	/** 妖精などの出す弾の速度設定 */
	public float speedRate;
	/** 妖精などの出す弾の特殊弾設定 */
	public short special;
	

	

	/** スペルカードモードか？ 
	 * @true  スペルカードを使用中
	 * @false スペルカードを使用していない
	 */
	public boolean isSpellCardMode;
	
	public final int EASY = 1;
	public final int NORMAL = 2;
	public final int HARD = 3;
	public final int LUNATIC = 4;
	
	//体力ゲージに表示される、名前の英語名
	private String danmakuMobName = "";
	/** 種族ID 1 */
	protected int species_1;
	/** 種族ID 2 */
	protected int species_2;
	
	//魔法陣の色
	/** 魔法陣の色　赤 */
	public static final int CIRCLE_COLOR_RED = 0;
	/** 魔法陣の色　青 */
	public static final int CIRCLE_COLOR_BLUE = 1;
	/** 魔法陣の色　緑 */
	public static final int CIRCLE_COLOR_GREEN = 2;
	/** 魔法陣の色　黄 */
	public static final int CIRCLE_COLOR_YELLOW = 3;
	/** 魔法陣の色　紫 */
	public static final int CIRCLE_COLOR_PURPLE = 4;
	/** 魔法陣の色　水色 */
	public static final int CIRCLE_COLOR_AQUA = 5;
	/** 魔法陣の色　青 */
	public static final int CIRCLE_COLOR_ORANGE = 6;
	/** 魔法陣の色　白 */
	public static final int CIRCLE_COLOR_WHITE = 7;
	/** 魔法陣の色　レインボー */
	public static final int CIRCLE_COLOR_RAINBOW = 8;
	
	/** 魔法陣 */
	public EntitySpellCardCircle circle;
	
	//弾幕MOBの種族
	
	//人間に属す種族
	/** 種族：人間 */
	public static final int SPECIES_HUMAN = 0;
	/** 種族：半人 */
	public static final int SPECIES_HUMAN_HALF = 1;
	/** 種族：月人 */
	public static final int SPECIES_HUMAN_LUNARIAN = 2;
	/** 種族：仙人 */
	public static final int SPECIES_HUMAN_HERMIT = 3;
	/** 種族：尸解仙*/
	public static final int SPECIES_HUMAN_SHIKAISEN = 4;
	/** 種族：天人 */
	public static final int SPECIES_HUMAN_CELESTIALS = 5;
	/** 種族：聖人 */
	public static final int SPECIES_HUMAN_SAINT = 6;
	/** 種族：小人 */
	public static final int SPECIES_HUMAN_INCHLINGS = 7;
	/** 種族：蓬莱人 */
	public static final int SPECIES_HUMAN_HOURAI = 8;
	
	//神に属す種族
	/** 種族：神 */
	public static final int SPECIES_GOD = 32;
	/** 種族：現人神 */
	public static final int SPECIES_GOD_ARAHITOGAMI = 33;
	/** 種族：八咫烏 */
	public static final int SPECIES_GOD_YATAGARASU = 34;
	
	//閻魔
	/** 種族：閻魔 */
	public static final int SPECIES_ENMA = 48;
	
	//妖精に属す種族
	/** 種族：妖精 */
	public static final int SPECIES_FAIRY = 64;
	/** 種族：氷精 */
	public static final int SPECIES_FAIRY_ICE = 65;
	
	//幽霊に属す種族
	/** 種族：幽霊 */
	public static final int SPECIES_PHANTOM = 92;
	/** 種族：半霊 */
	public static final int SPECIES_PHANTOM_HALF = 93;
	/** 種族：亡霊 */
	public static final int SPECIES_PHANTOM_GHOST = 94;
	/** 種族：神霊 */
	public static final int SPECIES_PHANTOM_DIVINESPIRIT = 95;
	/** 種族：船幽霊 */
	public static final int SPECIES_PHANTOM_SHIP = 96;
	/** 種族：キョンシー */
	public static final int SPECIES_PHANTOM_JIANGSHI = 97;
	
	//式神に属す種族
	/** 種族：式神 */
	public static final int SPECIES_SHIKIGAMI = 108;
	/** 種族：人形 */
	public static final int SPECIES_SHIKIGAMI_DOLL = 109;
	/** 種族：使い魔 */
	public static final int SPECIES_SHIKIGAMI_FAMILIAR = 110;
	
	//妖怪に属す種族
	/** 種族：妖怪 */
	public static final int SPECIES_YOUKAI = 128;//妖怪
	/** 種族：魔法使い */
	public static final int SPECIES_YOUKAI_MAGICIAN = 129;//魔法使い
	/** 種族：妖獣 */
	public static final int SPECIES_YOUKAI_BEAST = 130;//妖獣
	/** 種族：吸血鬼 */
	public static final int SPECIES_YOUKAI_VAMPIRE = 131;//吸血鬼
	/** 種族：鬼 */
	public static final int SPECIES_YOUKAI_ONI = 132;//鬼
	/** 種族：河童 */
	public static final int SPECIES_YOUKAI_KAPPA = 133;//河童
	/** 種族：烏天狗 */
	public static final int SPECIES_YOUKAI_TENGU_CROW = 134;//烏天狗
	/** 種族：白狼天狗 */
	public static final int SPECIES_YOUKAI_TENGU_WHITEWOLF = 135;//白狼天狗
	/** 種族：死神 */
	public static final int SPECIES_YOUKAI_SHINIGAMI = 136;//死神
	/** 種族：雪女 */
	public static final int SPECIES_YOUKAI_YUKIONNA = 137;//雪女
	/** 種族：妖蟲 */
	public static final int SPECIES_YOUKAI_BUG = 139;//妖蟲
	/** 種族：夜雀 */
	public static final int SPECIES_YOUKAI_YOSUZUME = 140;//夜雀
	/** 種族：妖怪兎 */
	public static final int SPECIES_YOUKAI_RABBIT = 142;//妖怪兎
	/** 種族：付喪神 */
	public static final int SPECIES_YOUKAI_TUKUMOGAMI = 143;//付喪神
	/** 種族：釣瓶落とし */
	public static final int SPECIES_YOUKAI_TURUBEOTOSHI = 144;//釣瓶落とし
	/** 種族：橋姫 */
	public static final int SPECIES_YOUKAI_HASHIHIME = 145;//橋姫
	/** 種族：覚 */
	public static final int SPECIES_YOUKAI_SATORI = 146;//覚
	/** 種族：入道 */
	public static final int SPECIES_YOUKAI_NYUUDOU = 147;//入道
	/** 種族：鵺 */
	public static final int SPECIES_YOUKAI_NUE = 148;//鵺
	/** 種族：人魚 */
	public static final int SPECIES_YOUKAI_MERMAID = 149;//人魚
	/** 種族：天邪鬼 */
	public static final int SPECIES_YOUKAI_AMANOJAKU = 150;//天邪鬼
	
	//龍神
	/** 種族：龍神 */
	public static final int SPECIES_DRAGON = 192;
	
	//その他の種族
	/** 種族：その他種族 */
	public static final int SPECIES_OTHERS = 255;
	
	//攻撃の段階
	/** 攻撃の意思がない状態を表す */
	public static final int NOT_ATTACK 	  = 0;
	/** 通常攻撃１ */
	public static final int NORMAL_ATTACK01 = 1;
	/** 通常攻撃２ */
	public static final int NORMAL_ATTACK02 = 2;
	/** 通常攻撃３ */
	public static final int NORMAL_ATTACK03 = 3;
	public static final int NORMAL_ATTACK04 = 4;
	public static final int NORMAL_ATTACK05 = 5;
	public static final int NORMAL_ATTACK06 = 6;
	public static final int NORMAL_ATTACK07 = 7;
	public static final int NORMAL_ATTACK08 = 8;
	public static final int NORMAL_ATTACK09 = 9;
	public static final int NORMAL_ATTACK10 =10;
	public static final int NORMAL_ATTACK11 =11;
	public static final int NORMAL_ATTACK12 =12;
	public static final int NORMAL_ATTACK13 =13;
	public static final int NORMAL_ATTACK14 =14;
	public static final int NORMAL_ATTACK15 =15;
	public static final int NORMAL_ATTACK16 =16;
	public static final int NORMAL_ATTACK17 =17;
	public static final int NORMAL_ATTACK18 =18;
	public static final int NORMAL_ATTACK19 =19;
	public static final int NORMAL_ATTACK20 =20;
	/** スペルカード１ */
	public static final int SPELLCARD_ATTACK01 = 101;
	/** スペルカード２ */
	public static final int SPELLCARD_ATTACK02 = 102;
	/** スペルカード３ */
	public static final int SPELLCARD_ATTACK03 = 103;
	public static final int SPELLCARD_ATTACK04 = 104;
	public static final int SPELLCARD_ATTACK05 = 105;
	public static final int SPELLCARD_ATTACK06 = 106;
	public static final int SPELLCARD_ATTACK07 = 107;
	public static final int SPELLCARD_ATTACK08 = 108;
	public static final int SPELLCARD_ATTACK09 = 109;
	public static final int SPELLCARD_ATTACK10 = 110;
	public static final int SPELLCARD_ATTACK11 = 111;
	public static final int SPELLCARD_ATTACK12 = 112;
	public static final int SPELLCARD_ATTACK13 = 113;
	public static final int SPELLCARD_ATTACK14 = 114;
	public static final int SPELLCARD_ATTACK15 = 115;
	public static final int SPELLCARD_ATTACK16 = 116;
	public static final int SPELLCARD_ATTACK17 = 117;
	public static final int SPELLCARD_ATTACK18 = 118;
	public static final int SPELLCARD_ATTACK19 = 119;
	public static final int SPELLCARD_ATTACK20 = 120;
	public static final int SPELLCARD_ATTACK21 = 121;
	public static final int SPELLCARD_ATTACK22 = 122;
	public static final int SPELLCARD_ATTACK23 = 123;
	public static final int SPELLCARD_ATTACK24 = 124;
	public static final int SPELLCARD_ATTACK25 = 125;
	public static final int SPELLCARD_ATTACK26 = 126;
	/** 攻撃の終了を表す(倒れた) */
	public static final int ATTACK_END = 127;
	
	public EntityDanmakuMob(World world)
	{
		super(world);
		isFlyingMode = true;
		this.setFlyingHeight(3);
		this.stop = false;
		this.setSpecies(this.SPECIES_OTHERS);
	}
	
	public EntityDanmakuMob(World world, double setX, double setY, double setZ)
	{
		this(world);
		this.setPosition(setX, setY, setZ);
	}
	
	/**
	 * 最初に一度だけ呼ばれる処理
	 */
	@Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)0));//見た目
    	this.dataWatcher.addObject(17, new Integer((byte)0));//使用中のスペルカード
        this.dataWatcher.addObject(18, new Byte((byte)0));//スペルカードモーション
        this.dataWatcher.addObject(19, new Byte((byte)0));//飛ぶ高さ
    }
	
    @Override
    public boolean isAIEnabled()
    {
    	return false;
    }
	
    /**
     * 最大HPを設定
     * @param hp 最大HP
     */
    public void setMaxHP(float hp)
    {
    	this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(hp);
    }
    
    /**
     * 移動速度を設定
     * @param speed 移動速度
     */
    public void setSpeed(double speed)
    {
    	this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speed);
    }
    
    /**
     * 移動速度を返す
     * @return 移動速度
     */
    public double getSpeed()
    {
    	return this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue();
    }
    
    /**
     * ターゲットとの間に取る間合いを設定する
     * @param distance ターゲットとの間に取る間合い
     */
    public void setAttackDistance(double distance)
    {
    	attackDistance = distance;
    }
    
    /**
     * ターゲットとの間に取る間合いを返す
     * @return ターゲットとの間合いの距離
     */
    public double getAttackDistance()
    {
    	return attackDistance;
    }
    
    /**
     * 近づいたら敵対化する距離を設定する
     * @param distance 近づいたら敵対化する距離
     */
    public void setDetectionDistance(double distance)
    {
    	detectionDistance = distance;
    }
    
    /**
     * 近づいたら敵対化する距離を返す
     * @return 近づいたら敵対化する距離
     */
    protected double getDetectionDistance()
    {
    	return detectionDistance;
    }
    
    /**
     * 周りの妖精を敵対化させるかどうか
     * @return 敵対化させられるならtrue
     */
    protected boolean canFairyCall()
    {
    	return false;
    }
    
    /**
     * 妖精を敵対化させられる最大の距離を返す
     * @return 妖精を敵対化させられる最大の距離
     */
    protected double getFairyCallDistance()
    {
    	return 10.0D;
    }
    
    
    
    /**
     * スペルカードを宣言するモーションを始める
     */
    protected void setSpellCardAttack()
    {
    	setSpellCardMotion((byte)-30);
    }
    
    public EntityLivingBase getTarget()
    {
    	if(THKaguyaConfig.danmakuLevel <= 0)
    	{
    		return null;
    	}
    	if(entityToAttack instanceof EntityLivingBase)
    	{
    		return (EntityLivingBase)entityToAttack;
    	}
    	return null;
    }
    
    /** 座標を直接指定して、その位置ベクトルを返す
     * @param x : X座標
     * @param y : Y座標
     * @param z : Z座標
     * @return
     */
    public Vec3 pos(double x, double y, double z)
    {
    	return THShotLib.pos(x, y, z);
    }
    
    /**
     * 目の位置より少し下の位置ベクトルを返す
     * @return
     */
    public Vec3 pos()
    {
    	return THShotLib.pos_Living(this);
    }
    
    /**
     * EntityLivingBaseの目の位置より少し下の位置ベクトルを返す
     * @param living : 位置ベクトルを知りたいEntityLivingBase
     * @return
     */
    public Vec3 pos(EntityLivingBase living)
    {
    	return THShotLib.pos_Living(living);
    }
    
    /**
     * 水平角度と垂直角度からベクトルを返す
     * @param yaw
     * @param pitch
     * @return
     */
    public Vec3 angle(float yaw, float pitch)
    {
    	return THShotLib.angle(yaw, pitch);
    }
    
    /**
     * 重力の影響を受けないベクトルを返す
     * @return
     */
    public Vec3 gravity_Zero()
    {
    	return THShotLib.gravity_Zero();
    }
    
    /**
     * 弾幕のパターンを返す
     * @return 弾幕パターン
     */
    public int getDanmakuPattern()
    {
    	return dataWatcher.getWatchableObjectInt(17);
    }
    
    /**
     * 弾幕パターンを設定
     * @param pattern 弾幕パターン
     */
    public void setDanmakuPattern(int pattern)
    {
    	dataWatcher.updateObject(17, Integer.valueOf(pattern));
    }
    
    /**
     * スペルカード宣言モーションを返す
     * @return スペルカード宣言時のモーションカウント
     */
    public byte getSpellCardMotion()
    {
    	return dataWatcher.getWatchableObjectByte(18);
    }
    
    /**
     * スペルカード宣言モーションを設定
     * @param motion モーションカウント
     */
    public void setSpellCardMotion(byte motion)
    {
    	dataWatcher.updateObject(18, Byte.valueOf(motion));
    }
    
    /**
     * 使用しているスペルカードNoを返す
     * @return スペルカードNo
     */
    public int getUsingSpellCardNo()
    {
    	return -1;
    }
    
    /**
     * スペルカードを使用中かどうかを返す
     * @return スペルカード使用中ならtrue
     */
    public boolean isSpellCardAttack()
    {
    	return getUsingSpellCardNo() >= 0;
    }
    
    /**
     * スペルカード宣言モーションを返す
     * @return スペルカード宣言時のモーションカウント
     */
    public byte getFlyingHeight()
    {
    	return dataWatcher.getWatchableObjectByte(19);
    }
    
    /**
     * スペルカード宣言モーションを設定
     * @param motion モーションカウント
     */
    public void setFlyingHeight(int height)
    {
    	dataWatcher.updateObject(19, Byte.valueOf((byte)height));
    }
    
    /**
     * 名前を返す。返す名前は英語
     * @return 英名を返す
     */
    public String getDanmakuMobName()
    {
    	return danmakuMobName;
    }
    
    /**
     * 名前を決める。英名
     * @param name 英名
     */
    public void setDanmakuMobName(String name)
    {
    	danmakuMobName = name;
    }
    
    /**
     * 種族を設定する
     * @param species1 種族１
     * @param species2 種族２
     */
    protected void setSpecies(int species1, int species2)
    {
    	species_1 = species1;
    	species_2 = species2;
    }
    
    /**
     * 種族を設定する
     * @param species 種族
     */
    protected void setSpecies(int species)
    {
    	setSpecies(species, -1);
    }
	
    /**
     * 種族１を返す
     * @return 種族ID
     */
    public int getSpecies_1()
    {
    	return this.SPECIES_OTHERS;
    }
    
    /**
     * 種族２を返す
     * @return 種族ID
     */
    public int getSpecies_2()
    {
    	return this.SPECIES_OTHERS;
    }
    
    /**
     * 人間ならtrueを返す
     * @return 人間ならtrue
     */
    public boolean isHuman()
    {
    	return getSpecies_1() < this.SPECIES_GOD || getSpecies_2() < this.SPECIES_GOD; 
    }
    
    /**
     * 幽霊ならtrueを返す
     * @return 幽霊ならtrue
     */
    public boolean isPhantom()
    {
    	return (getSpecies_1() >= this.SPECIES_PHANTOM && getSpecies_1() < this.SPECIES_SHIKIGAMI) ||
    			(getSpecies_2() >= this.SPECIES_PHANTOM && getSpecies_2() < this.SPECIES_SHIKIGAMI);
    }
    
    /**
     * 妖精ならtrueを返す
     * @return 妖精ならtrue
     */
    public boolean isFairy()
    {
    	return getSpecies_1() == this.SPECIES_FAIRY || getSpecies_2() == this.SPECIES_FAIRY;
    }
    
    /**
     * 妖怪ならtrueを返す
     * @return 妖怪ならtrue
     */
    public boolean isYoukai()
    {
    	return (getSpecies_1() >= this.SPECIES_YOUKAI && getSpecies_1() < this.SPECIES_DRAGON) ||
    			(getSpecies_2() >= this.SPECIES_YOUKAI && getSpecies_2() < this.SPECIES_DRAGON);
    }
    
    /**
     * ある種族であるか返す
     * @param species 種族
     * @return ある種族であるならtrue
     */
    public boolean isInSpecies(int species)
    {
    	return getSpecies_1() == species || getSpecies_2() == species;
    }
    
    /**
     * 移動をやめ、見ている方向も一定にする
     */
    protected void setStopStart()
    {
    	stop = true;
    	moveTimer = 0;
    	moveTimerMax = 0;
    }
    
    /**
     * 移動停止を終了させる
     */
    protected void setStopEnd()
    {
    	stop = false;
    }
    
    /**
     * 停止中かどうかを返す
     * @return 停止中ならtrue
     */
    public boolean isStop()
    {
    	return stop;
    }
    

    
    /**
     * ベクトルで指定した方向に指定の速度で、指定の時間の間動く
     * @param move 移動する方向を表すベクトル
     * @param speed 移動速度
     * @param time 移動時間
     */
    protected void move(Vec3 move, double speed, int time)
    {
    	moveVec = move;
    	moveVec.xCoord *= speed;
    	moveVec.yCoord *= speed;
    	moveVec.zCoord *= speed;
    	moveTimer = time;
    	moveTimerMax = time;
    }
    
    /**
     * 右方向に指定の速度で、指定の時間の間動く
     * @param speed 移動速度
     * @param time 移動時間
     */
    protected void moveRight(double speed, int time)
    {
    	moveVec = THShotLib.getVecFromAngle(rotationYaw + 90F, rotationPitch);
    	move(moveVec, speed, time);
    }
    
    /**
     * 左方向に指定の速度で、指定の時間の間動く
     * @param speed 移動速度
     * @param time 移動時間
     */
    protected void moveLeft(double speed, int time)
    {
    	moveVec = THShotLib.getVecFromAngle(rotationYaw - 90F, rotationPitch);
    	move(moveVec, speed, time);
    }
    
    /**
     * 前方に指定の速度で、指定の時間の間動く
     * @param speed 移動速度
     * @param time 移動時間
     */
    protected void moveForward(double speed, int time)
    {
    	moveVec = THShotLib.getVecFromAngle(rotationYaw, rotationPitch);
    	move(moveVec, speed, time);
    }
    
    /**
     * 後方に指定の速度で、指定の時間の間動く
     * @param speed 移動速度
     * @param time 移動時間
     */
    protected void moveBack(double speed, int time)
    {
    	moveVec = THShotLib.getVecFromAngle(rotationYaw + 180, rotationPitch);
    	move(moveVec, speed, time);
    }
    
    /**
     * 攻撃を次の段階に移す処理
     * @param nextPattern 次の攻撃パターンID
     * @param interval 次の攻撃を開始するまでの時間
     * @param maxHP 次の攻撃パターン時の最大HP
     * @param invincibleTime 無敵時間
     */
    protected void moveDanmakuAttack(int nextPattern, int interval, float maxHP, int invincibleTime)
    {
		setDanmakuPattern((byte)nextPattern);
		attackCounter = -interval;
		deathTime = 0;
		if(!worldObj.isRemote)
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHP);
			setHealth(maxHP);
		}
		attackInterval = invincibleTime;
		THShotLib.danmakuRemove(this, 40.0D, "Enemy", true);
		isSpellCardMode = false;
		if(circle != null)
		{
			circle.setDead();
		}
    }
    
    /**
     * 向いている方向ベクトルを返す。
     * @return
     */
    public Vec3 look()
    {
    	return THShotLib.angle(this.rotationYaw, this.rotationPitch);
    }
    
    public int getSpellCardCircleColor()
    {
    	return CIRCLE_COLOR_RED;
    }
    
    /**
     * スペルカードを宣言する
     * @param spellCardNo
     */
    protected void useSpellCard(int spellCardNo)
    {
		if(entityToAttack instanceof EntityLivingBase)
		{
			THKaguyaLib.spellCardDeclaration(this.worldObj, this, (EntityLivingBase)this.entityToAttack, spellCardNo, 0, THKaguyaConfig.danmakuLevel, !isSpellCardMode);
		}
		if(!isSpellCardMode)
		{
			isSpellCardMode = true;
	    	//スペルカードの魔法陣を出現させる
	    	circle = new EntitySpellCardCircle(worldObj, this, 16 + getSpellCardCircleColor(), -100);
	    	if(!worldObj.isRemote)
	    	{
	    		worldObj.spawnEntityInWorld(circle);
	    	}
			this.setSpellCardAttack();
		}
    }
    
    //Entityの属性の設定
    @Override
    protected void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	//最大HP
    	this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(2.0D);
    	//動く速さ
    	this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    	//ノックバック耐性。0.0でノックバック無効
    	this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0D);
    	//索敵距離
    	this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(30.0D);
    }
    
    //毎tick呼ばれる
    @Override
    public void onEntityUpdate()
    {
    	if(ticksExisted > lastTime)
    	{
    		super.onEntityUpdate();
    	}
    }
    
	/** 毎tick呼ばれる処理 */
    @Override
    public void onUpdate()
    {	
        //ピースフルなら消滅させる
    	if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
        {
            this.setDead();
            return;
        }
    	
    	//弾幕レベルが０なら、攻撃の対象を持たない
    	if(THKaguyaConfig.danmakuLevel <= 0)
    	{
    		entityToAttack = null;
    	}
    	
    	//時が止まっているなら処理を行わない
    	if(ticksExisted <= lastTime)
    	{
    		motionX = 0.0D;
    		motionY = 0.0D;
    		motionZ = 0.0D;
    		setPosition(prevPosX, prevPosY, prevPosZ);
    		return;
    	}
    	//時が止まっていないなら通常の処理を行う
    	else
    	{
    		super.onUpdate();
    	}
    	
    	if(getHealth() <= 0.0F)
    	{
    		return;
    	}
    	
    	if(attackInterval > 0)
    	{
    		attackInterval--;
    	}
    	if(isSpellCardAttack())
    	{
    		if(attackCounter == -20)setSpellCardAttack();
    	}
    	
    	//スペルカードモーション中ならモーションを進める
    	if(getSpellCardMotion() < 0)
    	{
    		if(!worldObj.isRemote)
    		{
    			setSpellCardMotion((byte)(getSpellCardMotion() + 2));
    		}
    	}
        
        if(moveTimer > 0)
        {
        	double moveRate = Math.cos((double)moveTimer / (double)moveTimerMax * Math.PI / 2.0D);
        	if(!worldObj.isRemote)
        	{
        		this.moveEntity(moveVec.xCoord * moveRate, moveVec.yCoord * moveRate, moveVec.zCoord * moveRate);
        	}
        	moveTimer--;
        }

        if(getFlyingHeight() > 0)
        {
	    	//高さを調整する
	    	int heightCount = 0;
	    	//地面から何ｍ上空にいるか調べる
	    	while(worldObj.isAirBlock((int)posX, (int)posY - heightCount, (int)posZ) && heightCount < 8)
	    	{
	    		heightCount++;
	    	}
	    	
	    	//ターゲットがいて地面から離れる高さ以上にいるなら
	    	if(entityToAttack != null && heightCount > getFlyingHeight())
	    	{
	    		//ターゲットの高さと今の高さを比較
	    		double distance = entityToAttack.posY - this.posY;
	    		
	    		motionY += distance * 0.0006D;
	    	}
	    	//ターゲットがいないななら
	    	else if(heightCount < getFlyingHeight())
	    	{
		    	if(heightCount >= getFlyingHeight())
		    	{
		    		motionY -= 0.0006D;
		    	}
		    	else if(heightCount < getFlyingHeight())
		    	{
		    		motionY += 0.006D;
		    	}
	    	}
	    	if(!stop)
	    	{
	    		this.moveEntity(0D, motionY, 0D);
	    	}
        }

    	
    	//ターゲットがいるが、死んでいるなら
    	if(entityToAttack != null && entityToAttack.isDead)
    	{
    		entityToAttack = null;//ターゲットから外す
    	}
    	
    	//ターゲットがいないなら
    	if (this.entityToAttack == null)
        {
    		//敵対範囲内にいるプレイヤーを探す
            this.entityToAttack = this.worldObj.getClosestVulnerablePlayerToEntity(this, getDetectionDistance());

            //プレイヤーがいるなら
            if (this.entityToAttack != null)
            {
            	//妖精を敵対化させられるなら、周りの妖精を敵対化させる
            	if(canFairyCall())
            	{
            		//範囲内のEntityをリストで取得
	            	List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(getFairyCallDistance(), getFairyCallDistance(), getFairyCallDistance()));//指定範囲内のEntityをリストに登録
	            	for(int i = 0; i < list.size(); i++)
	            	{
	            		Entity entity1 = (Entity)list.get(i);
	            		//弾幕MOBなら
	            		if(entity1 instanceof EntityTHFairy)
	            		{
	            			EntityTHFairy danmakuMob = (EntityTHFairy)entity1;
	            			//妖精のターゲットがいないなら
	            			if(danmakuMob.entityToAttack == null)
	            			{
	            				//妖精のターゲットをこの弾幕MOBと同じターゲットにする
	            				danmakuMob.entityToAttack = entityToAttack;
	            			}
	            		}
	            	}
            	}
                //this.aggroCooldown = 20;
            }
        }
    	
    	double entityToAttackableLength = this.getAttackDistance();//攻撃する射程
    	
    	//射程圏内にターゲットがいるなら
    	if(entityToAttack != null && !isDead /*&& entityToAttack.getDistanceSqToEntity(this) < entityToAttackableLength * entityToAttackableLength*/)
    	{

    			
    		//ターゲットの方を向かせる
    		double xDistance = entityToAttack.posX - posX;
    		double yDistance = (entityToAttack.posY + entityToAttack.getEyeHeight()) - (posY + this.getEyeHeight());
    		double zDistance = entityToAttack.posZ - posZ;
    		float angleXZ = - ((float)Math.atan2(xDistance, zDistance)) / 3.141593F * 180F;
			float angleY  = -(float)Math.atan2( yDistance, Math.sqrt(xDistance * xDistance + zDistance * zDistance)) / 3.141593F * 180F;
    			
			if(!stop)
			{
				setRotation(angleXZ, angleY);
			}
        	
    		//ターゲットを目視できるなら
        	if(canEntityBeSeen(entityToAttack))
        	{
        			//ターゲットの見失いカウントを０にする
        			lostTarget = 0;
    			//弾幕難易度が０でなければ弾幕攻撃をする
    			int level = THKaguyaConfig.danmakuLevel;
    			if(level != 0 && attackCounter >= 0)
    			{
    				danmakuPattern(level);

    			}
    			
    			//攻撃カウントを増やす
    			attackCounter++;
    		}
    		//ターゲットを目視できないなら
    		else
    		{
    			//move(THShotLib.getVecFromAngle(rand.nextFloat() + 360F, rand.nextFloat() * 360F), getSpeed() * 10, 30);
    			//ターゲットの見失いカウントを増やす
    			lostTarget++;
    			//ターゲットを１０秒目視できないなら
    			if(lostTarget > 200)
    			{
    				//ターゲットをなしにする
    				entityToAttack = null;
    			}
    		}
    		
        	if(moveTimer <= 0 && lostTarget > 0)
        	{
        		//moveRight(getSpeed(), 10);
        		move(THShotLib.getVecFromAngle(rand.nextFloat() + 360F, rand.nextFloat() * 360F), getSpeed(), 30);
        	}
        	
    		if(moveTimer <= 0 && entityToAttack != null && getAttackDistance() > 0)
    		{
	    		double toTargetDistance = this.getDistanceToEntity(entityToAttack);
	    		double rate;
	    		
	    		if(toTargetDistance < getAttackDistance())
	    		{
	    			rate = 1.0D -  (toTargetDistance / getAttackDistance());
	    			moveBack(getSpeed() * 0.3D * rate, 15);
	    		}
	    		else
	        	{
	    			rate = (toTargetDistance / getAttackDistance());
	        		moveForward(getSpeed() * 0.5D * rate, 15);
	        	}
    		}
    	}
    	
    	
    	//最後に動いた時間として、その時間を保存
    	if(ticksExisted > lastTime)
    	{
    		lastTime = ticksExisted;
    	}
    	

    }
    
    /**
     * 無敵状態を取得する
     * 攻撃移行時の時間の間は無敵
     * @return [ true : 無敵状態 ] [ false : 無敵ではない]
     */
    @Override
    public boolean isEntityInvulnerable()
    {
    	if(attackInterval > 0)
    	{
    		return true;
    	}
    	else
    	{
    		return super.isEntityInvulnerable();
    	}
    }
    
    //ダメージを受けたときの処理
    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage)
    {
        if (super.attackEntityFrom(damageSource, applyPotionDamageCalculations(damageSource, damage)) && damageSource.getEntity() instanceof EntityLivingBase)
        {
            EntityLivingBase entity = (EntityLivingBase)damageSource.getEntity();
        	//ノックバック耐性が高い
        	motionX *= 0.0001D;
        	motionY *= 0.0001D;
        	motionZ *= 0.0001D;
            if (this.riddenByEntity != entity && this.ridingEntity != entity)
            {
                if (entity instanceof EntityPlayer)//entity != this)
                {
                	this.entityToAttack = entity;
                }

                return true;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }
    
	//衝突処理
	public void hitCheck(float damage)
	{
	    //始点（現在地）
    	Vec3 vec3d = Vec3.createVectorHelper(posX, posY, posZ);
    	//終点（現在地に移動量を足した点）
    	Vec3 vec3d1 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
        //始点と終点からブロックとの当たりを取得
    	//MovingObjectPosition movingObjectPosition = worldObj.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);
    	MovingObjectPosition movingObjectPosition = worldObj.func_147447_a(vec3d, vec3d1, false, true, true);
    	vec3d = Vec3.createVectorHelper(posX, posY, posZ);
    	vec3d1 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
    	//何らかのブロックに当たっているなら
        if (movingObjectPosition != null)
        {
        	THShotLib.playShotSound(this);
        	//終点を当たった点に変更
        	vec3d1 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
        }
        
        movingObjectPosition = hitEntityCheck(movingObjectPosition, vec3d, vec3d1);
        
        if (movingObjectPosition != null)
        {
        	//当たった場合の処理をする
            onImpact(movingObjectPosition, damage);
        }

	}
	
	//Entityとの当たり判定をとる
	public MovingObjectPosition hitEntityCheck(MovingObjectPosition movingObjectPosition, Vec3 vec3d, Vec3 vec3d1)
	{
        Entity entity = null;//実際に当たったことにするEntity
    	double d = 0.0D;//そのEntityまでの仮の距離
		float hitSize = 0.9F;//getSize() * 0.5F;
    	//ここから移動量分の線分を作り、それに弾の大きさの２倍の肉付けをし直方体を作る。それに当たったEntityをリスト化する\\
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(hitSize, hitSize, hitSize));//指定範囲内のEntityをリストに登録
		
    	for (int j = 0; j < list.size(); j++)
        {
            Entity entity1 = (Entity)list.get(j);//entity1にリストの先端のentityを保存
        	//entity1が、当たり判定を取らない　または　entity1が使用者　または　飛んで25カウント以下？　または　EntityTHShotならパス
            if ( entity1.canBeCollidedWith())
        	{
            	{
	        		//判定を弾の大きさに変更
	            	AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(hitSize, hitSize, hitSize);
	            	MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
	        		//この判定で当たっているなら
	            	if (movingObjectPosition1 != null)
	            	{
	        			//当たっているならここからその点までの距離を取得
	            		double d1 = vec3d.distanceTo(movingObjectPosition1.hitVec);
	        			//今までの一番近くにいるなら、一番近いEntityを更新する
	            		if (d1 < d || d == 0.0D)
	            		{
	                		entity = entity1;
	                		d = d1;
	            		}
	        		}
            	}
        	}
        }

    	//当たったEntityがいるなら、当たったEntityをMovingObjectPositionで登録
        if (entity != null)
        {
        	
            movingObjectPosition = new MovingObjectPosition(entity);
        }
		
        return movingObjectPosition;
	}
	
	//ブロックやEntityに当たった時の処理
    protected void onImpact(MovingObjectPosition movingObjectPosition, float damage)
    {
    	//当たった時の処理
    	if (!worldObj.isRemote)
    	{
    		Entity hitEntity = movingObjectPosition.entityHit;
        
    		//当たったEntityがいるなら
    		if ( hitEntity != null )
        	{
        		{
        			float damageRate = 1.0F;
        			{
        				if(!hitEntity.attackEntityFrom(DamageSource.causeMobDamage(this), damage));
        			}
        		}
			}
    	}
    }
    
    //EntityCreatureでの邪魔な処理を無効化
    @Override
    protected void updateEntityActionState()
    {
    	if(entityToAttack == null)
    	{
    		super.updateEntityActionState();
    	}
    }
    
    /*@Override
    protected void updateLeashedState()
    {
    	
    }
    
    @Override
    public void setPathToEntity(PathEntity par1PathEntity)
    {
    }*/
    
    /*@Override
    public void onLivingUpdate()
    {
    	if(ticksExisted > lastTime)
    	{
    		super.onLivingUpdate();
    	}
    }*/
    
    //AIが有効ならtrueを返す
    /*@Override
    protected boolean isAIEnabled()
    {
    	return ticksExisted > lastTime;
    }*/
    
    /*@Override
    protected void updateEntityActionState()
    {
    	if(ticksExisted > lastTime)
    	{
    		super.updateEntityActionState();
    	}
    }*/
    
    /*@Override
    public void faceEntity(Entity par1Entity, float par2, float par3)
    {
    	if(ticksExisted > lastTime)
    	{
    		super.faceEntity(par1Entity, par2, par3);
    	}
    }*/
    
    /**
     * Sets the position of the entity, keeps yaw/pitch,  and updates the 'last' variables
     */
    /*public void setPositionAndUpdate(double par1, double par3, double par5)
    {
        if(ticksExisted > lastTime)
        {
        	super.setPositionAndUpdate(par1, par3, par5);
        }
    }*/
    
    /*@Override
    public void moveEntityWithHeading(float par1, float par2)
    {
    
    }*/
    
    /**
     * Moves the entity based on the specified heading.  Args: strafe, forward
     */
    @Override
    public void moveEntityWithHeading(float par1, float par2)
    {
    	if(getFlyingHeight() <= 0)
    	{
    		super.moveEntityWithHeading(par1, par2);
    		return;
    	}
    	
    	this.moveFlying(par1, par2, 0.02F);
    	
    	if(!worldObj.isRemote)
    	{
    		//this.moveEntity(Math.cos(rotationYaw / 180F * 3.141593F) * speed, motionY, Math.sin(rotationYaw / 180F * 3.141593F) * speed);
    	}
    	
    	//motionX *= 0.9F;
    	//motionZ *= 0.9F;
        if (this.isInWater())
        {
        	this.moveFlying(par1, par2, 0.02F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.800000011920929D;
            this.motionY *= 0.800000011920929D;
            this.motionZ *= 0.800000011920929D;
        }
        else
        {
            float f2 = 0.91F;

            if (this.onGround)
            {
                f2 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ)).slipperiness * 0.91F;
            }

            float f3 = 0.16277136F / (f2 * f2 * f2);
            this.moveFlying(par1, par2, this.onGround ? 0.1F * f3 : 0.02F);
            f2 = 0.91F;

            if (this.onGround)
            {
                f2 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ)).slipperiness * 0.91F;
            }

            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)f2;
            this.motionY *= (double)f2;
            this.motionZ *= (double)f2;
        }

        this.prevLimbSwingAmount = this.limbSwingAmount;
        double d1 = this.posX - this.prevPosX;
        double d0 = this.posZ - this.prevPosZ;
        float f4 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;

        if (f4 > 1.0F)
        {
            f4 = 1.0F;
        }

        this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }
    
    /*public void moveEntity(double par1, double par3, double par5)
    {
    	if(ticksExisted > lastTime)
    	{
    		super.moveEntity(par1, par3, par5);
    	}
    }*/
    
    //弾幕パターンの記述
    protected void danmakuPattern(int level)
    {

    }
    
    //死んでいるときに呼ばれる
    @Override
    protected void onDeathUpdate()
    {
    	super.onDeathUpdate();
    	
    	//周囲の妖精を巻き込む
    	if(this.deathTime == 7)
    	{
    		THShotLib.explosionEffect2(worldObj, posX, posY, posZ, 1.0F + deathTime * 0.1F);
    		THShotLib.banishExplosion(this, 5.0F, 5.0F);
    	}
    	
    }
    
    //倒れたときに落とすアイテム
	@Override
	protected void dropFewItems(boolean hasBeenAttackedByPlayer, int lootingLevel)
    {
		//スペルカードを使用していたなら、そのスペルカードを落とす
		if(getUsingSpellCardNo() >= 0 && hasBeenAttackedByPlayer)
		{
			this.entityDropItem(new ItemStack(THKaguyaItems.spell_card, 1, getUsingSpellCardNo()), 0.0F);
		}
    }
	
	public void dropItem(int type, Vec3 pos, Vec3 angle)
	{
		EntityTHItem item = new EntityTHItem(worldObj, pos.xCoord, pos.yCoord, pos.zCoord, angle, (byte)type);
		
		if(!worldObj.isRemote)
		{
			worldObj.spawnEntityInWorld(item);
		}
	}
	
	/**
	 * パワーアップアイテム 小を落とす
	 * @param pos   : 落とす場所
	 * @param angle : 落とす方向 
	 */
	public void dropPowerUpItem(Vec3 pos, Vec3 angle)
	{
		this.dropItem(1, pos, angle);
	}
	
	/**
	 * パワーアップアイテム 小を落とす
	 * @param num 落とす数
	 */
	public void dropPowerUpItem(int num)
	{
		for(int i = 1; i <= num; i++)
		{
			dropPowerUpItem(this.pos(), THShotLib.angle_LimitRandom(THShotLib.angle(0.0D, 1.0D, 0.0D), 90F));
		}
	}
	
	/**
	 * 得点アイテムを落とす
	 * @param pos   : 落とす場所
	 * @param angle : 落とす方向 
	 */
	public void dropPointItem(Vec3 pos, Vec3 angle)
	{
		this.dropItem(4, pos, angle);
	}
	
	/**
	 * 得点アイテムを落とす
	 * @param num 落とす数
	 */
	public void dropPointItem(int num)
	{
		for(int i = 1; i <= num; i++)
		{
			dropPointItem(this.pos(), THShotLib.angle_LimitRandom(THShotLib.angle(0.0D, 1.0D, 0.0D), 90F));
		}
	}
	
	/**
	 * スペルカードアイテムを落とす
	 * @param pos   : 落とす場所
	 * @param angle : 落とす方向 
	 */
	public void dropSpellCardItem(Vec3 pos, Vec3 angle)
	{
		this.dropItem(10, pos, angle);
	}
	
	/**
	 * スペルカードアイテムを落とす
	 * @param num 落とす数
	 */
	public void dropSpellCardItem(int num)
	{
		for(int i = 1; i <= num; i++)
		{
			dropSpellCardItem(this.pos(), THShotLib.angle_LimitRandom(THShotLib.angle(0.0D, 1.0D, 0.0D), 90F));
		}
	}
	
	/**
	 * エクステンドアイテムを落とす
	 * @param pos   : 落とす場所
	 * @param angle : 落とす方向 
	 */
	public void dropExtendItem(Vec3 pos, Vec3 angle)
	{
		this.dropItem(11, pos, angle);
	}
	
	/**
	 * エクステンドアイテムを落とす
	 * @param num 落とす数
	 */
	public void dropExtendItem(int num)
	{
		for(int i = 1; i <= num; i++)
		{
			dropExtendItem(this.pos(), THShotLib.angle_LimitRandom(THShotLib.angle(0.0D, 1.0D, 0.0D), 90F));
		}
	}
	
	/**
	 * 弾アイテムを落とす。弾幕形状の設定もできる
	 * @param shotID : 弾ID。ItemTHShot.BUTTERFLY という感じで指定
	 * @param stackNumber : スタック数
	 * @param number : 弾幕の弾数
	 * @param speed : 形状ごとの速度に、形状速度 * speed * 0.03　を加算する
	 * @param color : THShotLib.REDなどで指定。全８色 + RANDOM + RAINBOW
	 * @param gravity : 重力加速度。gravity * 0.003の速度を毎tick下方向に加速させる
	 * @param special : 特殊な弾。THShotLib.BOUND01などで指定
	 * @param form : 弾幕系状。0で一点、1で前方ランダム、2で扇状、3で全方位、4で球状、5でリング
	 * @return 作成されたEntityItem
	 */
	public EntityItem dropShotItem(int shotID, int stackNumber, int number, int speed, int color, int gravity, int special, int form)
	{
        ItemStack shot = new ItemStack(THKaguyaItems.shot_item, stackNumber, shotID);
        
	    NBTTagCompound nbt = shot.getTagCompound();
		if(nbt == null)
		{
			nbt = new NBTTagCompound();
			nbt.setShort("Number", (short)number);
			nbt.setByte("Speed", (byte)(speed % 64));
			nbt.setByte("Color", (byte)(color % 16));
			nbt.setByte("Gravity", (byte)(gravity % 16));
			nbt.setInteger("Special", special);
		    nbt.setByte("DanmakuForm", (byte)(form % 8));
		   	shot.setTagCompound(nbt);
        }

	    return this.entityDropItem(shot, 0.0F);
	}
    
	//落下させる
    @Override
    protected void fall(float par1) 
    {
    	if(getFlyingHeight() > 0)
    	{
    		super.fall(par1);
    	}
    }
    
    //落下しているときの処理
    @Override
    protected void updateFallState(double par1, boolean par3) 
    {
    }
    
    //Entityの発する音の大きさ
    @Override
    protected float getSoundVolume()
    {
        return 0.3F;
    }
    
	//発する音のピッチ
    @Override
    protected float getSoundPitch()
    {
        return super.getSoundPitch() * 1.95F;
    }

    //生きてるときに出す音
    @Override
    protected String getLivingSound()
    {
        return this.rand.nextInt(4) != 0 ? null : "mob.bat.idle";
    }

    //攻撃を受けたときの音
    @Override
    protected String getHurtSound()
    {
        return "mob.bat.hurt";
    }

	//倒れたときの音
    @Override
    protected String getDeathSound()
    {
        return "mob.bat.death";
    }
    
    //一つのチャンクに湧く最大数
    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }
    
    /**
     * 見た目を返す
     * @return
     */
    public byte getForm()
    {
    	return dataWatcher.getWatchableObjectByte(16);
    }
    
    /**
     * 見た目を設定する
     * @param type
     */
    public void setForm(byte type)
    {
    	dataWatcher.updateObject(16, Byte.valueOf(type));
    }
    
    /*エンダーマンのテレポート処理*/
    
    /**
     * エンダーマンのテレポート。ランダムに近くへテレポートする
     */
    protected boolean teleportRandomly(double range)
    {
        double warpX;
        double warpY;
        double warpZ;
        
        int count = 0;
        do
        {
            warpX = this.posX + (this.rand.nextDouble() - 0.5D) * range;
            warpY = this.posY + (double)(this.rand.nextInt((int)range) - (int)(range / 2.0D));
            warpZ = this.posZ + (this.rand.nextDouble() - 0.5D) * range;
            count++;
        }while(!this.teleportTo(warpX, warpY, warpZ) && count < 10);
        return true;
    }

    /**
     * エンダーマンのテレポート。他のエンティティのところへテレポートする
     */
    protected boolean teleportToEntity(Entity par1Entity)
    {
        Vec3 vec3 = Vec3.createVectorHelper(this.posX - par1Entity.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - par1Entity.posY + (double)par1Entity.getEyeHeight(), this.posZ - par1Entity.posZ);
        vec3 = vec3.normalize();
        double d0 = 16.0D;
        double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.xCoord * d0;
        double d2 = this.posY + (double)(this.rand.nextInt(16) - 8) - vec3.yCoord * d0;
        double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.zCoord * d0;
        return this.teleportTo(d1, d2, d3);
    }

    /**
     * エンダーマンのテレポート
     */
    protected boolean teleportTo(double par1, double par3, double par5)
    {
        EnderTeleportEvent event = new EnderTeleportEvent(this, par1, par3, par5, 0);
        if (MinecraftForge.EVENT_BUS.post(event)){
            return false;
        }
        double d3 = this.posX;
        double d4 = this.posY;
        double d5 = this.posZ;
        this.posX = event.targetX;
        this.posY = event.targetY;
        this.posZ = event.targetZ;
        boolean flag = false;
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.posY);
        int k = MathHelper.floor_double(this.posZ);

        if (this.worldObj.blockExists(i, j, k))
        {
            boolean flag1 = false;

            while (!flag1 && j > 0)
            {
                Block block = this.worldObj.getBlock(i, j - 1, k);

                if (block.getMaterial().blocksMovement())
                {
                    flag1 = true;
                }
                else
                {
                    --this.posY;
                    --j;
                }
            }

            if (flag1)
            {
                this.setPosition(this.posX, this.posY, this.posZ);

                if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox))
                {
                    flag = true;
                }
            }
        }

        if (!flag)
        {
            this.setPosition(d3, d4, d5);
            return false;
        }
        else
        {
            short short1 = 128;

            for (int l = 0; l < short1; ++l)
            {
                double d6 = (double)l / ((double)short1 - 1.0D);
                float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
                double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * (double)this.height;
                double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
                this.worldObj.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
            }

            this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
            this.playSound("mob.endermen.portal", 1.0F, 1.0F);
            return true;
        }
    }
    
    
    
    //保存したデータを読み込む
    @Override
    public void readEntityFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readEntityFromNBT(nbtTagCompound);
        if(nbtTagCompound != null)
        {
	        //弾幕MOBの攻撃パターンを読み込み
        	setForm(nbtTagCompound.getByte("Form"));
	        setDanmakuPattern(nbtTagCompound.getInteger("Danmaku"));
	        danmakuSpan = nbtTagCompound.getShort("Span");
	        shotForm = nbtTagCompound.getByte("ShotForm");
	        shotColor = nbtTagCompound.getByte("ShotColor");
	        speedRate = nbtTagCompound.getFloat("Speed");
	        setAttackDistance(nbtTagCompound.getByte("AttackRange"));
	        setDetectionDistance(nbtTagCompound.getByte("DetectRange"));
	        setFlyingHeight((int)nbtTagCompound.getByte("Flying"));
	        special = nbtTagCompound.getShort("Special");
	        if(getFlyingHeight() <= 0)
	        {
	        	isFlyingMode = false;
	        }
	        else
	        {
	        	isFlyingMode = true;
	        }
	        if(shotForm < 0 || shotForm >= 32)
	        {
	        	shotForm = 0;
	        }
	        if(shotColor < 0 || shotColor >= 10)
	        {
	        	shotColor = 8;
	        }
        }
        else
        {
        	setDanmakuPattern((byte)0);
        	setAttackDistance(14.0D);
        	setDetectionDistance(30.0D);
        	setFlyingHeight(3);
        }
        
    	
    }

    //保存するデータを書き込む
    @Override
    public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeEntityToNBT(nbtTagCompound);
        //弾幕MOBの攻撃パターンを書き込む
        nbtTagCompound.setByte("Form", getForm());
        nbtTagCompound.setInteger("Danmaku", getDanmakuPattern());
        nbtTagCompound.setShort("Span", (short) danmakuSpan);
        nbtTagCompound.setByte("ShotForm", shotForm);
        nbtTagCompound.setByte("ShotColor", shotColor);
        nbtTagCompound.setFloat("Speed", speedRate);
        nbtTagCompound.setByte("AttackRange", (byte)getAttackDistance());
        nbtTagCompound.setByte("DetectRange", (byte)getDetectionDistance());
        nbtTagCompound.setByte("Flying", (byte)getFlyingHeight());
        nbtTagCompound.setShort("Special", (short) special);
    	
    }
}
