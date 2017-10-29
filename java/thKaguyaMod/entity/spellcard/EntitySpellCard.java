package thKaguyaMod.entity.spellcard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.effect.EntitySpellCardCircle;
import thKaguyaMod.entity.living.EntityDanmakuMob;
import thKaguyaMod.registry.SpellCardRegistry;

/** スペルカードのEntity */
public class EntitySpellCard extends Entity
{
	/** スペルカードを宣言したEntityLiving */
	public EntityLivingBase user;
	/** 攻撃対象のEntityLiving */
	public EntityLivingBase tgEntity;
	/** スペルカードを宣言してからの時間 */
	public int count;
	private int spellCardNumber;//スペルカード番号
	/** ターゲットへのベクトル */
	protected Vec3 tgVec;
	private int spellCardUsedTime;
	private int lastTime;
	public int level;//スペルカードのレベル。プレイヤー使用時は基本ノーマルであるレベル２固定
	
	public EntitySpellCardCircle circle;
	
	public boolean canMoveInTimeStop;
	
	public static final int SC_REIMU_MusouFuuin = 0;
	public static final int SC_MARISA_MasterSpark = 1;
	public static final int SC_YUYUKO_Kasho_no_Eimin = 2;
	public static final int SC_MARISA_MeteonicShower = 3;
	public static final int SC_YUKARI_Nami_to_Tubu_no_Kyoukai = 4;
	public static final int SC_YUKARI_NijuuKokushichou = 5;
	public static final int SC_REMILIA_ScarletShoot = 6;
	public static final int SC_TENSHI_Zenzinrui_no_Hisouten = 7;
	public static final int SC_SAKUYA_SatuzinDoll = 8;
	public static final int SC_CIRNO_PerfectFreeze = 9;
	public static final int SC_YUKARI_HikouchuuNest = 10;
	public static final int SC_NITORI_Kappa_no_Pororoca = 11;
	public static final int SC_MARISA_StardustReverie = 12;
	public static final int SC_SUWAKO_Kerochan_Fuu_ni_Makezu = 13;
	public static final int SC_SANAE_MiracleFruit = 14;
	public static final int SC_SANAE_Fafrotskies_no_Kiseki = 15;
	public static final int SC_SANAE_YouryokuSpoiler = 16;
	public static final int SC_SANAE_Moses_no_Kiseki = 17;
	public static final int SC_SANAE_Yasaka_no_Kamikaze = 18;
	public static final int SC_CIRNO_IcicleFall = 19;
	public static final int SC_FLANDRE_StarbowBreak = 20;
	public static final int SC_FLANDRE_Catadioptric = 21;
	public static final int SC_SUWAKO_MishagujiSama = 22;
	public static final int SC_REMILIA_RedMagic = 23;
	public static final int SC_SAKUYA_EternalMeek = 24;
	public static final int SC_MARISA_NonDirectionalLaser = 25;
	public static final int SC_YUUKA_Kachoufuugetu_Shoufuurougetu = 26;
	public static final int SC_REMILIA_Spear_the_Gungnir = 27;
	public static final int SC_RUMIA_MoonlightRay = 28;
	public static final int SC_MEIRIN_Houkakenran = 29;
	public static final int SC_MEIRIN_Saikouranbu = 30;
	public static final int SC_PATCHOULI_PrincessUndine = 31;
	
	Class<?> spellcard_0;// = THSpellCard_0.class;
	Class<?> spellcard;// = THSpellCard_0.class;
	THSpellCard useSpellCard;// = spellcard.newInstance();
	
	
	/** スペルカードのコンストラクタ */
    public EntitySpellCard(World world)
    {
        super(world);
        preventEntitySpawning = true;
        setSize(0.4F, 0.4F);//サイズを設定　平面上の横と奥行きサイズ、高さ
        yOffset = 0.0F;//高さを設定
    }
	
    /** スペルカードのコンストラクタ */
    public EntitySpellCard(World world, EntityLivingBase spUser, EntityLivingBase target, int num, int spLevel)
    {
        this(world);

        setPosition(spUser.posX - (double)MathHelper.sin((spUser.rotationYaw + 30F) / 180F * 3.141593F) * (double)MathHelper.cos(spUser.rotationPitch / 180F * 3.141593F),
        			spUser.posY - (double)MathHelper.sin(spUser.rotationPitch / 180F * 3.141593F) + (double)spUser.getEyeHeight() - 0.10000000149011612D ,
        			spUser.posZ + (double)MathHelper.cos((spUser.rotationYaw + 30F) / 180F * 3.141593F) * (double)MathHelper.cos(spUser.rotationPitch / 180F * 3.141593F));//初期位置を設定(x,y,z)
    	rotationYaw = spUser.rotationYaw;
    	rotationPitch = spUser.rotationPitch;
    	setRotation(rotationYaw, rotationPitch);
    	tgVec = spUser.getLookVec();
    	user = spUser;//使用者をuserに保存
    	tgEntity = target;//ターゲットした相手
    	spellCardNumber = num;
    	setSpellCardNumber(num);
    	spellCardUsedTime = 0;
    	count = 0;
    	lastTime = 0;
    	//worldObj.playSoundAtEntity(this, "thkaguyamod:spellcard", THKaguyaConfig.SpellCardVol, 1.0F);
    	

    	
    	level = spLevel;

		if((spellcard = SpellCardRegistry.getSpellCardClass(num)) != null)
		{
			try {
				useSpellCard = (THSpellCard)spellcard.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
			useSpellCard.init(world, this, user, tgEntity, level);
			canMoveInTimeStop = useSpellCard.canMoveInTimeStop();
			if(spUser instanceof EntityPlayer)
			{
				
		    	//スペルカードの魔法陣を出現させる
		    	circle = new EntitySpellCardCircle(world, spUser, useSpellCard.getSpellCardCircleColor(), useSpellCard.getEndTime());
		    	if(!world.isRemote)
		    	{
		    		world.spawnEntityInWorld(circle);
		    	}
			}
		}
		else
		{
			if(!worldObj.isRemote)
			{
				setDead();
			}
		}
    }
    
    /*public EntitySpellCard(World world,EntityLivingBase spUser, EntityLivingBase target, int num, int spLevel)
    {
        this(world, spUser, target, num, level);
    	
    	level = spLevel;
    }*/

    /** 生成時に一度だけ呼ばれるメソッド */
	@Override
    protected void entityInit()
    {
    	dataWatcher.addObject(18, new Integer(0));
    }

    /**
     * 押すことができるかを返す
     */
    @Override
    public boolean canBePushed()
    {
        return false;
    }

    /**
     * 他のEntityと接触できるかを返す
     */
    @Override
    public boolean canBeCollidedWith()
    {
    	return false;
    }

	/**
	 * Entityの消滅処理
	 */
	private void finish()
	{
		if(!this.worldObj.isRemote)
		{
			this.setDead();
			if(circle != null)
			{
				circle.setDead();
			}
		}
	}
	
	/**
	 * 指定した時間になったらスペルカードを終了する
	 */
	private void spellCardEnd(int time)
	{
		if(this.ticksExisted >= time)
		{
			finish();
		}
	}
	
	private void playerSpellCardDanmakuRemove(int time)
	{
		if(user instanceof EntityPlayer && ticksExisted < time)
		{
			THShotLib.danmakuRemove(user, 40.0F, "Other", true);
		}
	}
	
	private void playerSpellCardDanmakuRemove(int start, int end)
	{
		if(user instanceof EntityPlayer && ticksExisted >= start && ticksExisted < end)
		{
			THShotLib.danmakuRemove(user, 40.0F, "Other", true);
		}
	}
	
	public void specialProcessInTimeStop()
	{
		useSpellCard.specialProcessInTimeStop();
	}
	
	/**
	 * Entityが存在する限り毎フレーム呼び出されるメソッド
	 */
	@Override
    public void onUpdate()
    {
    	//時間が進んでいないなら
    	if(ticksExisted <= lastTime)
    	{
    		return;//処理を終了させる
    	}
		
        super.onUpdate();
        
        //使用者がいないか、死んでいるなら消す
    	if(!worldObj.isRemote && (user == null || user.isDead))
    	{
    		finish();
    		return;
    	}
    	
    	//使用者がいるなら
    	if(user != null)
    	{
    		//使用者が弾幕MOBなら
    		if(user instanceof EntityDanmakuMob)
    		{
    			EntityDanmakuMob danmakuMob = (EntityDanmakuMob)user;
    			//弾幕MOBがスペルカードモードでないなら消す
    			if(!danmakuMob.isSpellCardMode)
    			{
    				finish();
    				return;
    			}
    		}
    	}
    	

    	if(ticksExisted > spellCardUsedTime/* || spellCardNumber == 8*/)
    	{
    		if(spellcard == null)
    		{
    			return;
    		}
    		if(useSpellCard == null)
    		{
    			return;
    		}
    		
    		useSpellCard.onUpdate();
    		
    		playerSpellCardDanmakuRemove(useSpellCard.getRemoveTime());
    		
    		if(useSpellCard.time >= useSpellCard.getEndTime())
    		{
    			finish();
    			return;
    		}
    	}
    	
    	if(spellCardUsedTime < ticksExisted)
    	{
    		spellCardUsedTime = ticksExisted;
    	}

		//時間で消滅
		if(ticksExisted >= 9999)
		{
			if(!worldObj.isRemote)
			{
				finish();
			}
		}
		
    	//通常通り時間が進んでいるなら
    	if(ticksExisted > lastTime)
    	{
    		//最後に時間が動いていたときの時間と移動量を保存する
    		lastTime = ticksExisted;
    	}
	}
	
	
	/**
	 * 保存するデータの書き込み
	 * @param nbtTagCompound : NTBタグ
	 */
	@Override
    protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
    {
    }

	/**
	 * 保存したデータの読み込み
	 * @param nbtTagCompound : NBTタグ
	 */
    @Override
    protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
    {
    }
	
	/**
	 * スペルカードNoを設定する
	 */
	public void setSpellCardNumber(int number)
	{
		dataWatcher.updateObject(18, Integer.valueOf(number));
	}
	
	/**
	 * スペルカードNoを返す
	 */
	public int getSpellCardNumber()
	{
		return dataWatcher.getWatchableObjectInt(18);
	}

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float par1)
    {
        return 0.5F;
    }

}
