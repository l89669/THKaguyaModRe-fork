package thKaguyaMod.client.model.living;

import net.minecraft.client.model.ModelRenderer;
import thKaguyaMod.entity.living.EntityDanmakuMob;

public class ModelWriggle extends ModelTouhouDefault
{

    public ModelRenderer Manto1;
    public ModelRenderer Manto2;
    public ModelRenderer Manto3;
    public ModelRenderer rightTouch;
    public ModelRenderer leftTouch;
    public ModelRenderer rightZubon;
    public ModelRenderer leftZubon;

	public ModelWriggle()
	{
		this(0.0F);
	}

	public ModelWriggle(float size)
	{
		this(size, 0.0F);
	}

	public ModelWriggle(float size, float yOffset)
	{
		this(size, yOffset , 64, 64);
	}

	public ModelWriggle(float size, float yOffset, int textureWidth, int textureHeight) 
	{
		super(size, yOffset, textureWidth, textureHeight);
	}

	/** ここにTechneのJava出力をそのまま入れる */
	@Override
	protected void getTechneModelData()
	{
	      bipedBody = new ModelRenderer(this, 32, 8);
	      bipedBody.addBox(-3F, 0F, -2F, 6, 7, 4);
	      bipedBody.setRotationPoint(0F, 5F, 0F);
	      bipedBody.setTextureSize(64, 64);
	      bipedBody.mirror = true;
	      setRotation(bipedBody, 0F, 0F, 0F);
	      bipedRightArm = new ModelRenderer(this, 56, 0);
	      bipedRightArm.addBox(-2F, -1F, -1F, 2, 8, 2);
	      bipedRightArm.setRotationPoint(-2F, 6F, 0F);
	      bipedRightArm.setTextureSize(64, 64);
	      bipedRightArm.mirror = true;
	      setRotation(bipedRightArm, 0F, 0F, 0F);
	      bipedLeftArm = new ModelRenderer(this, 56, 0);
	      bipedLeftArm.addBox(0F, -1F, -1F, 2, 8, 2);
	      bipedLeftArm.setRotationPoint(2F, 6F, 0F);
	      bipedLeftArm.setTextureSize(64, 64);
	      bipedLeftArm.mirror = true;
	      setRotation(bipedLeftArm, 0F, 0F, 0F);
	      bipedRightLeg = new ModelRenderer(this, 32, 19);
	      bipedRightLeg.addBox(-1F, 0F, -2F, 3, 11, 4);
	      bipedRightLeg.setRotationPoint(-2F, 13F, 0F);
	      bipedRightLeg.setTextureSize(64, 64);
	      bipedRightLeg.mirror = true;
	      setRotation(bipedRightLeg, 0F, 0F, 0F);
	      bipedLeftLeg = new ModelRenderer(this, 32, 19);
	      bipedLeftLeg.addBox(-2F, 0F, -2F, 3, 11, 4);
	      bipedLeftLeg.setRotationPoint(2F, 13F, 0F);
	      bipedLeftLeg.setTextureSize(64, 64);
	      bipedLeftLeg.mirror = true;
	      setRotation(bipedLeftLeg, 0F, 0F, 0F);
	      bipedHead = new ModelRenderer(this, 0, 0);
	      bipedHead.addBox(-4F, -8F, -4F, 8, 8, 8);
	      bipedHead.setRotationPoint(0F, 5F, 0F);
	      bipedHead.setTextureSize(64, 64);
	      bipedHead.mirror = true;
	      setRotation(bipedHead, 0F, 0F, 0F);
	      longHair = new ModelRenderer(this, 24, 0);
	      longHair.addBox(-4F, 0F, -3F, 8, 2, 3);
	      longHair.setRotationPoint(0F, 0F, 4F);
	      longHair.setTextureSize(64, 64);
	      longHair.mirror = true;
	      setRotation(longHair, 0F, 0F, 0F);
	      leftBraid = new ModelRenderer(this, 0, 16);
	      leftBraid.addBox(0F, 0F, -1F, 1, 4, 1);
	      leftBraid.setRotationPoint(2.95F, -3F, -3F);
	      leftBraid.setTextureSize(64, 64);
	      leftBraid.mirror = true;
	      setRotation(leftBraid, -0.2443461F, 0F, 0F);
	      rightBraid = new ModelRenderer(this, 0, 16);
	      rightBraid.addBox(-1F, 0F, -1F, 1, 4, 1);
	      rightBraid.setRotationPoint(-2.95F, -3F, -3F);
	      rightBraid.setTextureSize(64, 64);
	      rightBraid.mirror = true;
	      setRotation(rightBraid, -0.2443461F, 0F, 0F);
	      rightTouch = new ModelRenderer(this, 4, 16);
	      rightTouch.addBox(0F, -6F, 0F, 1, 6, 1);
	      rightTouch.setRotationPoint(-3F, -8F, -4F);
	      rightTouch.setTextureSize(64, 64);
	      rightTouch.mirror = true;
	      setRotation(rightTouch, 0F, 0F, 0F);
	      leftTouch = new ModelRenderer(this, 4, 16);
	      leftTouch.addBox(-1F, -6F, 0F, 1, 6, 1);
	      leftTouch.setRotationPoint(3F, -8F, -4F);
	      leftTouch.setTextureSize(64, 64);
	      leftTouch.mirror = true;
	      setRotation(leftTouch, 0F, 0F, 0F);
	      Manto1 = new ModelRenderer(this, 38, 47);
	      Manto1.addBox(-6F, 0F, -1F, 12, 6, 1);
	      Manto1.setRotationPoint(0F, 0F, 2F);
	      Manto1.setTextureSize(64, 64);
	      Manto1.mirror = true;
	      setRotation(Manto1, 0F, 0F, 0F);
	      Manto2 = new ModelRenderer(this, 38, 54);
	      Manto2.addBox(-6F, 0F, -1F, 12, 4, 1);
	      Manto2.setRotationPoint(0F, 6F, 0F);
	      Manto2.setTextureSize(64, 64);
	      Manto2.mirror = true;
	      setRotation(Manto2, 0F, 0F, 0F);
	      Manto3 = new ModelRenderer(this, 38, 59);
	      Manto3.addBox(-6F, 0F, -1F, 12, 4, 1);
	      Manto3.setRotationPoint(0F, 4F, 0F);
	      Manto3.setTextureSize(64, 64);
	      Manto3.mirror = true;
	      setRotation(Manto3, 0F, 0F, 0F);
	      rightZubon = new ModelRenderer(this, 0, 32);
	      rightZubon.addBox(-2F, 0F, -2.5F, 4, 6, 5);
	      rightZubon.setRotationPoint(0F, -2.5F, 0F);
	      rightZubon.setTextureSize(64, 64);
	      rightZubon.mirror = true;
	      setRotation(rightZubon, 0F, 0F, 0F);
	      leftZubon = new ModelRenderer(this, 0, 32);
	      leftZubon.addBox(-2F, 0F, -2.5F, 4, 6, 5);
	      leftZubon.setRotationPoint(0F, -2.5F, 0F);
	      leftZubon.setTextureSize(64, 64);
	      leftZubon.mirror = true;
	      setRotation(leftZubon, 0F, 0F, 0F);
	}

	@Override
	public void defaultAddChildSetting() {
		super.defaultAddChildSetting();
		
		bipedHead.addChild(rightTouch);
		bipedHead.addChild(leftTouch);;
		bipedBody.addChild(Manto1);
		Manto1.addChild(Manto2);
		Manto2.addChild(Manto3);
		bipedRightLeg.addChild(rightZubon);
		bipedLeftLeg.addChild(leftZubon);
	}
	
	/** 東方キャラモデルの独自のパラメータ設定はここでする */
	@Override
	protected void setTouhouModelData()
	{
		this.setShoulderWidth(2.4F);
		this.setBodyHeight(7F);
		this.setLegHeight(12F);
	}

	@Override
	public void setRotationAnglesTHKaguya(float moveCounter, float moveCycle, float ticksExisted, float pheadYaw, float pheadPitch, float scale, EntityDanmakuMob entity)
	{
		super.setRotationAnglesTHKaguya(moveCounter, moveCycle, ticksExisted, pheadYaw, pheadPitch, scale, entity);
		
		//マント
		this.Manto1.rotateAngleX = (float)Math.sin((double)(moveCounter + ticksExisted) / 10.0D) * 0.1F + 0.3F;
		this.Manto2.rotateAngleX = (float)Math.sin((double)(moveCounter * 1.2F + ticksExisted) / 7.8D) * 0.25F + 0.25F;
		this.Manto3.rotateAngleX = (float)Math.sin((double)(moveCounter * 1.5F + ticksExisted) / 5.3D) * 0.3F + 0.20F;
		
	    if (this.isRiding)
	    {
	    }
	    else
	    {
	  	
		  	if(entity.getFlyingHeight() == 0)
		  	{
		  		bipedRightLeg.rotateAngleX = cos(moveCounter) * 0.7F * moveCycle;
		  		bipedLeftLeg.rotateAngleX = cos(moveCounter + fPI) * 0.7F * moveCycle;
			  	bipedRightLeg.rotateAngleZ = 0F; 
			  	bipedLeftLeg.rotateAngleZ = 0F;
			  	
			  	if(moveCounter > 0F)
			  	{
			        bipedRightArm.rotateAngleX = cos(moveCounter + fPI) * 2.0F * moveCycle * 0.5F;
			        bipedLeftArm.rotateAngleX = cos(moveCounter) * 2.0F * moveCycle * 0.5F;
			        bipedRightArm.rotateAngleY = rad(-10F);
			        bipedRightArm.rotateAngleZ = rad(20F);
			        bipedLeftArm.rotateAngleY = -bipedRightArm.rotateAngleY;
			        bipedLeftArm.rotateAngleZ = -bipedRightArm.rotateAngleZ;
			  	}
			  	else
			  	{
			  	  	bipedRightArm.rotateAngleX = -0.7F - sin(ticksExisted  / 10F) * 0.1F;
			  	  	bipedRightArm.rotateAngleY = 0.0F;
			  	  	bipedRightArm.rotateAngleZ = -0.6457718F;
			  	  	bipedLeftArm.rotateAngleX = bipedRightArm.rotateAngleX;
			  	  	bipedLeftArm.rotateAngleY = 0.0F;
			  	  	bipedLeftArm.rotateAngleZ = 0.6457718F;
		
			  	}
		  	}
		  	else
		  	{
		  		
		  	  	bipedRightLeg.rotateAngleZ = Math.abs(sin(ticksExisted  / 10F) * 0.1F); 
		  	  	bipedLeftLeg.rotateAngleZ = -bipedRightLeg.rotateAngleZ;
		  	  	bipedRightLeg.rotateAngleX = Math.abs(sin(ticksExisted  / 10F) * 0.2F); 
		  	  	bipedLeftLeg.rotateAngleX = bipedRightLeg.rotateAngleZ;
		  	  	
		  	  	
		  	  	/*bipedRightArm.rotateAngleZ = rad(90F);
		  	  	bipedRightArm.rotateAngleY = 0.0F;
		  	  	bipedRightArm.rotateAngleX = 0.0F;
				bipedLeftArm.rotateAngleZ = -bipedRightArm.rotateAngleZ;
				bipedLeftArm.rotateAngleY = 0.0F;
				bipedLeftArm.rotateAngleX = 0.0F;*/
		  	  	
		  	  	bipedRightArm.rotateAngleX = rad(0F);
				bipedRightArm.rotateAngleZ = rad(20F + sin(ticksExisted / 10F) * 5F);
				bipedLeftArm.rotateAngleX = rad(0F);
				bipedLeftArm.rotateAngleZ = rad(-20F - sin(ticksExisted / 10F) * 5F);
		  	}
	    }
	}
	
}