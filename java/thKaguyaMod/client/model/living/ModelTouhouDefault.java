package thKaguyaMod.client.model.living;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import thKaguyaMod.entity.living.EntityDanmakuMob;

/** 東方キャラモデル作成用のベースモデル 
 *  基本的な設定関数や、簡易表記用関数も入れてる
 */
public class ModelTouhouDefault extends ModelBase
{
	/** float型のPI */
	protected final float fPI = 3.141593F;
	
	public ModelRenderer bipedHead;
	public ModelRenderer bipedBody;
	public ModelRenderer bipedRightArm;
	public ModelRenderer bipedLeftArm;
	public ModelRenderer bipedRightLeg;
	public ModelRenderer bipedLeftLeg;
	/** スカートの上 */
	public ModelRenderer skirtTop;
	/** スカートの下 */
	public ModelRenderer skirtBottom;
	/** 後ろに伸びた髪 */
	public ModelRenderer longHair;
	/** 右のもみあげ */
	public ModelRenderer rightBraid;
	/** 左のもみあげ */
	public ModelRenderer leftBraid;
	/** 右目 */
	//public ModelRenderer eyeR;
	/** 左目 */
	//public ModelRenderer eyeL;
	
	private float legHeight = 0F;
	private float bodyHeight = 0F;
	private float shoulderWidth = 0F;
	


	public ModelTouhouDefault()
	{
		this(0.0F);
	}

	public ModelTouhouDefault(float f)
	{
		this(f, 0.0F);
	}

	public ModelTouhouDefault(float f, float f1)
	{
		this(f, f1 , 64, 64);
	}

	public ModelTouhouDefault(float f, float f1, int t_height, int t_width) {
		super();
		textureHeight = t_height;
		textureWidth = t_width;
		//東方キャラモデルの独自パラメータ設定
		setTouhouModelData();
		
		initModel(f, f1, false);
	}

	public void initModel(float f, float f1, boolean isAfterInit) {
		//super.initModel(f, f1, false);
		
		textureHeight = 64;
		textureWidth = getTextureWidth();
		
		//TechneのJava出力を読み込む
		getTechneModelData();
		
		//パーツ消去
	    deleteParts();
	    
	    defaultAddChildSetting();

		// 追加パーツ
		/*eyeR = new Modchu_ModelRenderer(this, 0, 0);
		eyeR.addPlate(-4.0F, -5.0F, -4.001F, 4, 4, 0, f);
		eyeR.setRotationPoint(0.0F, 0.0F, 0.0F);
		eyeL = new Modchu_ModelRenderer(this, 4, 0);
		eyeL.addPlate(0.0F, -5.0F, -4.001F, 4, 4, 0, f);
		eyeL.setRotationPoint(0.0F, 0.0F, 0.0F);
		if (isAfterInit) afterInit(f, f1);*/
	}
	
	  @Override
	  public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
	  {
		  super.render(entity, movement, far, tick, yaw, pitch, size);
		  setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
		  bipedBody.render(size);
	  }
	
	/** ここにTechneのJava出力をそのまま入れる */
	protected void getTechneModelData()
	{
		
	}
	
	protected int getTextureWidth()
	{
		return 64;
	}
	
	/** 東方キャラモデルの独自のパラメータ設定はここでする */
	protected void setTouhouModelData()
	{
		this.setShoulderWidth(3.0F);
	}
	
	// 親クラスで使ってるけどいらないパーツを消去する
	protected void deleteParts()
	{
	}

	public void defaultAddChildSetting() 
	{
		bipedBody.addChild(bipedHead);
		bipedBody.addChild(bipedRightArm);
		bipedBody.addChild(bipedLeftArm);
		bipedBody.addChild(bipedRightLeg);
		bipedBody.addChild(bipedLeftLeg);
		if(rightBraid != null)bipedHead.addChild(rightBraid);
		if(leftBraid != null)bipedHead.addChild(leftBraid);
	    if(longHair != null)bipedHead.addChild(longHair);
	    if(skirtTop != null)bipedBody.addChild(skirtTop);
		if(skirtBottom != null)skirtTop.addChild(skirtBottom);

		//if (eyeR != null) bipedHead.addChild(eyeR);
		//if (eyeL != null) bipedHead.addChild(eyeL);
	}

	/*
	public void setLivingAnimationsLM(ModchuModel_IModelCaps entityCaps, float f, float f1, float renderPartialTicks) 
	{
		Object entityliving = getCapsValue(entityCaps, entityCaps.caps_Entity);
		if (entityliving != null) ;else return;
		int ticksExisted = Modchu_AS.getInt(Modchu_AS.entityTicksExisted, entityliving);
		float pheadYaw = (float)ticksExisted + renderPartialTicks + ModchuModel_ModelCapsHelper.getCapsValueFloat(this, caps_entityIdFactor);
		// 目パチ
		if( 0 > Modchu_AS.getFloat(Modchu_AS.mathHelperSin, pheadYaw * 0.05F) + Modchu_AS.getFloat(Modchu_AS.mathHelperSin, pheadYaw * 0.13F) + Modchu_AS.getFloat(Modchu_AS.mathHelperSin, pheadYaw * 0.7F) + 2.55F) {
			setCapsValue(caps_visible, eyeR, true);
			setCapsValue(caps_visible, eyeL, true);
		} else {
			setCapsValue(caps_visible, eyeR, false);
			setCapsValue(caps_visible, eyeL, false);
		}
	}*/
	
	/** パーツの回転を設定 デフォルトのもの（使わない）*/
	@Override
	public void setRotationAngles(float moveCounter, float moveCycle, float ticksExisted, float pheadYaw, float pheadPitch, float scale, Entity entity)
	{
		setRotationAnglesTHKaguya(moveCounter, moveCycle, ticksExisted, pheadYaw, pheadPitch, scale, (EntityDanmakuMob)entity);
		setRotationAnglesAfterTHKaguya(moveCounter, moveCycle, ticksExisted, pheadYaw, pheadPitch, scale, (EntityDanmakuMob)entity);
	}
	
	/** パーツの回転を設定 */
	public void setRotationAnglesAfterTHKaguya(float moveCounter, float moveCycle, float ticksExisted, float pheadYaw, float pheadPitch, float scale, EntityDanmakuMob entity)
	{
	  	//スペルカードの宣言をする
	  	if(entity.getSpellCardMotion() < -15)
	  	{
	  		bipedRightArm.rotateAngleX = -0.7F - sin(ticksExisted  / 10F) * 0.1F - sin(rad(30F + entity.getSpellCardMotion())) * 4F;
	  	}
	  	else if(entity.getSpellCardMotion() < 0)
	  	{
	  		bipedRightArm.rotateAngleX = -0.7F - sin(ticksExisted  / 10F) * 0.1F - sin(rad(-entity.getSpellCardMotion())) * 4F;
	  	}
	}

	/** パーツの回転を設定 */
	public void setRotationAnglesTHKaguya(float moveCounter, float moveCycle, float ticksExisted, float pheadYaw, float pheadPitch, float scale, EntityDanmakuMob entity)
	{
		//moveCounter = 移動時に増加する
		//moveCycle = 増加したり戻ったり。スニーク、移動時は変化量が少し。
		//ticksExisted = 時間で常に増え続けている
		//pheadYaw = 向いている方角方向で変化
		//pheadPitch = 向いている上下方向で変化
		//scale = スケール値？
		
		//初期ポーズに設定
		setDefaultPause(moveCounter, moveCycle, ticksExisted, pheadYaw, pheadPitch, scale, entity);
		
		this.bipedBody.rotateAngleX = 0.0F;
		this.bipedHead.rotateAngleY = pheadYaw / 57.29578F;
		this.bipedHead.rotateAngleX = pheadPitch / 57.29578F;
		
		this.bipedRightArm.rotateAngleX = cos(moveCounter * 0.6662F + fPI) * 2.0F * moveCycle * 0.5F;
		this.bipedRightArm.rotateAngleY = rad(90F);
		this.bipedLeftArm.rotateAngleX  = cos(moveCounter * 0.6662F      ) * 2.0F * moveCycle * 0.5F;
		this.bipedLeftArm.rotateAngleY  = rad(90F);
		
		
		
		
		this.bipedRightLeg.rotateAngleX = cos(moveCounter * 0.6662F) * 0.5F * moveCycle;
		this.bipedLeftLeg.rotateAngleX = cos(moveCounter * 0.6662F + fPI) * 0.5F * moveCycle;
		this.bipedRightLeg.rotateAngleY = bipedLeftLeg.rotateAngleY = bipedRightLeg.rotateAngleZ = bipedLeftLeg.rotateAngleZ = 0.0F;
		
		if(skirtTop != null)
		{
			this.skirtTop.rotateAngleX = 0.0F;
			if(skirtBottom != null)
			{
				this.skirtBottom.rotateAngleX = 0.0F;
			}
		}
		

		
		// 座っている場合（乗り物に乗っている）
		if (this.isRiding) 
		{
			// 少し身体の位置を低くする
			this.bipedBody.rotationPointY = -1.0F;
			
			// 腕を前に持っていく
			this.bipedRightArm.rotateAngleX 	+= -rad(36F);
			this.bipedLeftArm.rotateAngleX 		+= -rad(36F);
			// 足を前に持って行き、広がらせる
			this.bipedRightLeg.rotateAngleX 	= -rad(72F);
			this.bipedLeftLeg.rotateAngleX 		= -rad(72F);
			this.bipedRightLeg.rotateAngleY 	=  rad(13F);
			this.bipedLeftLeg.rotateAngleY 		= -rad(13F);
			this.bipedRightLeg.rotateAngleZ 	=  rad(13F);
			this.bipedLeftLeg.rotateAngleZ 		= -rad(13F);

			// スカートを前に回転させる
			if(skirtTop != null)
			{
				this.skirtTop.rotateAngleX = -rad(20F);
				if(skirtBottom != null)
				{
					this.skirtBottom.rotateAngleX = -rad(30F);
				}
			}
			
			InRidingMotion();
		} 
		else 
		{
			//setRotationAnglesGulliverBefore(moveCounter, moveCycle, ticksExisted, pheadYaw, pheadPitch, scale, entity);
		}

		bipedRightArm.rotateAngleY = bipedLeftArm.rotateAngleY = 0.0F;

		/*bipedRightLeg.rotationPointZ = bipedLeftLeg.rotationPointZ =
				bipedBody.rotateAngleX = bipedBody.rotateAngleZ =
				upperBody.rotateAngleX = upperBody.rotateAngleZ = 0.0F;*/
		
		// ？？？
		//setRotationAnglesGulliverAfter(moveCounter, moveCycle, ticksExisted, pheadYaw, pheadPitch, scale, entity);
	}
	
	/** 座っているときに呼ばれるモーション処理 */
	protected void InRidingMotion()
	{
		
	}
	
	/** パーツのボーンのデフォルト位置を設定 */
	public void setDefaultPause(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) 
	{
		if (bipedHead != null)		bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		if (bipedBody != null)		bipedBody.setRotationPoint(0.0F, -(getBodyHeight() + getLegHeight() - 25F), 0.0F);
		if (bipedRightArm != null) bipedRightArm.setRotationPoint(-getShoulderWidth(), 2.0F, 0.0F);
		if (bipedLeftArm != null)	bipedLeftArm.setRotationPoint(getShoulderWidth(), 2.0F, 0.0F);
		if (bipedRightLeg != null) bipedRightLeg.setRotationPoint(-2.0F, getBodyHeight(), 0.0F);
		if (bipedLeftLeg != null) 	bipedLeftLeg.setRotationPoint(2.0F, getBodyHeight(), 0.0F);
		//if (Skirt != null) Skirt.setRotationPoint(0.0F, 4.0F, 0.0F);
	}
//============================================設定系====================================================//

	/** モデルを回転させる
	 * @param model   : 回転させるモデル
	 * @param rotateX : X軸の回転量
	 * @param rotateY : Y軸の回転量
	 * @param rotateZ : Z軸の回転量
	 */
	protected void setRotation(ModelRenderer model, float rotateX, float rotateY, float rotateZ)
	{
		model.rotateAngleX = rotateX;
		model.rotateAngleY = rotateY;
		model.rotateAngleZ = rotateZ;
	}
	
	/** 足の長さを設定
	 * @param height : 足の長さ
	 */
	protected void setLegHeight(float height)
	{
		legHeight = height;
	}
	
	/** 身体の高さ（座高）を設定
	 * @param height : 足の長さ
	 */
	protected void setBodyHeight(float height)
	{
		bodyHeight = height;
	}
	
	/** 肩幅（身体の中心から腕までの長さ）を設定　
	 * 標準は2F ～ 3F
	 * @param height : 肩幅
	 */
	protected void setShoulderWidth(float width)
	{
		shoulderWidth = width;
	}
	
//============================================取得系====================================================//
	/*@Override
	public String getUsingTexture() {
		return null;
	}*/
	
	/** 度数をラジアンに変換して返す */
	protected float rad(float degree)
	{
		return degree / 180F * fPI;
	}
	
	/** sin値を返す。簡略表記のためのもの */
	protected float sin(float angle)
	{
		return MathHelper.sin(angle);
	}
	
	/** cos値を返す。簡略表記のためのもの */
	protected float cos(float angle)
	{
		return MathHelper.cos(angle);
	}
	
	/*protected float getBoxSizeY(ModelRenderer model)
	{
		
		return;
	}*/
	
	/** 足の長さを取得　
	 * 長い方の足の長さを返す（大半同じ長さのはず）。setLegHeight()を使っている場合は設定した値を返す。
	 * @return 足の長さ
	 */
	protected float getLegHeight()
	{
		/*if( legHeight <= 0.0F)
		{
			if(bipedRightLeg != null && bipedLeftLeg != null)
			{
				if(bipedRightLeg.getBoxSizeY() >= bipedLeftLeg.getBoxSizeY())
				{
					return bipedRightLeg.getBoxSizeY();
				}
				return bipedLeftLeg.getBoxSizeY();
			}
			
			if(bipedRightLeg != null)
			{
				return bipedRightLeg.getBoxSizeY();
			}
			if(bipedLeftLeg != null)
			{
				return bipedLeftLeg.getBoxSizeY();
			}
		}*/
		return legHeight;
	}
	
	/** 身体の高さ（座高）を取得　
	 * setBodyHeight()で身体の高さを設定していない場合はbipedBodyの高さをそのまま返す
	 * @return 身体の長さ
	 */
	protected float getBodyHeight()
	{
		/*if(bipedBody != null && bodyHeight <= 0.0F)
		{
			return bipedBody.getBoxSizeY();
		}*/
		return bodyHeight;
	}
	
	/** 肩幅（身体の中心から腕までの長さ）を取得
	 * @return 肩幅
	 */
	protected float getShoulderWidth()
	{
		return shoulderWidth;
	}
}
