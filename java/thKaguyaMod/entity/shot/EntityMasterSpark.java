package thKaguyaMod.entity.shot;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** マスタースパーク　地形破壊、高威力のレーザーを発射する */
public class EntityMasterSpark extends EntityTHSetLaser
{

	public int count;
	public int flagtime;
	public double speed;
	public float size;
	public float oldSize;
	public int endtime;
	public int inicount;

	//ワールド読み込み時に呼び出されるコンストラクト
    public EntityMasterSpark(World world)
    {
        super(world);
    	//renderDistanceWeight = 64.0D;
    	setSize(60.0F, 5.0F);
    	count = 0;
    	//endtime = 120;
    }

    public EntityMasterSpark(World world, EntityLivingBase user, Entity source,
    	Vec3 pos, Vec3 angle, float slope, Vec3 rotate, float rotationSpeed, int rotationEnd,
    	int color, float width, float length, float damage, int delay, int end, int special, Entity set, double setLength, double setYOffset)
    {
    	//damage = 6.0F;
        super(world, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd, 0, width, length, damage, delay, end, special, set, setLength, setYOffset);
        setSize(5.0F, 5.0F);
    	//count = 0;
    	//inicount = ini;
    	//setIniTime(inicount);
    }
	
	
	@Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(21, new Integer(0));
		dataWatcher.addObject(22, new Integer(0));
	}
	
	/*
	*わからんので、翻訳機使用　描画判定のチェックか？
	*エンティティかどうかをチェックするには、遠くに過去を使用し、その平均エッジと比較することで、レンダリングする範囲内にある
	*長さ* 64 * renderDistanceWeightの引数：距離
	*/
	@SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double d)
    {
        return true;
    }

	/*@Override
	public boolean canBePushed()
    {
        return true;
    }*/

	//マスタースパークが存在する限り呼び出されるメソッド
	@Override
    public void onUpdate()
    {
        super.onUpdate();

    	if(!worldObj.isRemote && ticksExisted == 1)
    	{
    		setDead();
    	}
    }
	

	//弾が当たったときの処理
	@Override
    protected boolean onImpact(MovingObjectPosition movingobjectposition)
    {
    	//当たった時の処理　　気質弾同士のあたり判定はとらない
		return true;
    }
	
	public void setIniTime(int par1)
	{
		dataWatcher.updateObject(21, Integer.valueOf(par1));
	}
	
	public int getIniTime()
	{
		return dataWatcher.getWatchableObjectInt(21);
	}
	
	public void setMaxLength(double par1)
	{
		dataWatcher.updateObject(22, Integer.valueOf((int)(par1 * 100.0D)));
	}
	
	public double getMaxLength()
	{
		return (double)dataWatcher.getWatchableObjectInt(22) / 100.0D;
	}
	
	@Override
	public boolean canBeCollidedWith()
    {
        return false;
    }
	
}
