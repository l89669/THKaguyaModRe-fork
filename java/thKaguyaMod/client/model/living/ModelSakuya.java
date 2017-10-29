package thKaguyaMod.client.model.living;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelSakuya extends ModelBase {

	public ModelRenderer bipedBody;
	public ModelRenderer bipedHead;
	public ModelRenderer bipedRightArm;
	public ModelRenderer bipedLeftArm;
	public ModelRenderer bipedRightLeg;
	public ModelRenderer bipedLeftLeg;
	public ModelRenderer skirtTop;
	public ModelRenderer skirtBottom;
	public ModelRenderer longHair;
	public ModelRenderer leftRibbon;
	public ModelRenderer rightRibbon;
	public ModelRenderer leftKatyusha;
	public ModelRenderer rightKatyusha;
	public ModelRenderer rightBraid;
	public ModelRenderer leftBraid;
	
	protected float legHeight = 14F;
	protected float bodyHeight = 9F;
	
    public ModelSakuya()
    {
        this(0.0F);
    }

    public ModelSakuya(float size)
    {
        this(size, 0.0F, 64, 64);
    }

	public ModelSakuya(float size, float yOffset, int textureXSize, int textureH)
	{
		textureHeight = 64;
		textureWidth = 64;

		
	      bipedBody = new ModelRenderer(this, 32, 8);
	      bipedBody.addBox(-3F, 0F, -2F, 6, 9, 4);
	      bipedBody.setRotationPoint(0F, 1F, 0F);
	      bipedBody.setTextureSize(64, 64);
	      bipedBody.mirror = true;
	      setRotation(bipedBody, 0F, 0F, 0F);
	      bipedRightArm = new ModelRenderer(this, 48, 0);
	      bipedRightArm.addBox(-1F, -1F, -1F, 2, 8, 2);
	      bipedRightArm.setRotationPoint(-4F, 2F, 0F);
	      bipedRightArm.setTextureSize(64, 64);
	      bipedRightArm.mirror = true;
	      setRotation(bipedRightArm, -0.7679449F, 0F, -0.6457718F);
	      bipedLeftArm = new ModelRenderer(this, 56, 0);
	      bipedLeftArm.addBox(-1F, -1F, -1F, 2, 8, 2);
	      bipedLeftArm.setRotationPoint(4F, 2F, 0F);
	      bipedLeftArm.setTextureSize(64, 64);
	      bipedLeftArm.mirror = true;
	      setRotation(bipedLeftArm, -0.7679449F, 0F, 0.6457718F);
	      bipedRightLeg = new ModelRenderer(this, 50, 18);
	      bipedRightLeg.addBox(-1F, 0F, -2F, 3, 14, 4);
	      bipedRightLeg.setRotationPoint(-2F, 10F, 0F);
	      bipedRightLeg.setTextureSize(64, 64);
	      bipedRightLeg.mirror = true;
	      setRotation(bipedRightLeg, 0F, 0F, 0F);
	      bipedLeftLeg = new ModelRenderer(this, 50, 18);
	      bipedLeftLeg.addBox(-2F, 0F, -2F, 3, 14, 4);
	      bipedLeftLeg.setRotationPoint(2F, 10F, 0F);
	      bipedLeftLeg.setTextureSize(64, 64);
	      bipedLeftLeg.mirror = true;
	      setRotation(bipedLeftLeg, 0F, 0F, 0F);
	      skirtTop = new ModelRenderer(this, 0, 16);
	      skirtTop.addBox(-4F, 0F, -4F, 8, 4, 8);
	      skirtTop.setRotationPoint(0F, 6F, 0F);
	      skirtTop.setTextureSize(64, 64);
	      skirtTop.mirror = true;
	      setRotation(skirtTop, 0F, 0F, 0F);
	      skirtBottom = new ModelRenderer(this, 16, 32);
	      skirtBottom.addBox(-5F, 0F, -5F, 10, 5, 10);
	      skirtBottom.setRotationPoint(0F, 4F, 0F);
	      skirtBottom.setTextureSize(64, 64);
	      skirtBottom.mirror = true;
	      setRotation(skirtBottom, 0F, 0F, 0F);
	      leftRibbon = new ModelRenderer(this, 0, 38);
	      leftRibbon.addBox(0F, 0F, 0F, 5, 4, 1);
	      leftRibbon.setRotationPoint(0F, 5F, 3F);
	      leftRibbon.setTextureSize(64, 64);
	      leftRibbon.mirror = true;
	      setRotation(leftRibbon, 0.4886922F, -0.3F, -0.296706F);
	      rightRibbon = new ModelRenderer(this, 0, 38);
	      rightRibbon.addBox(0F, 0F, -1F, 5, 4, 1);
	      rightRibbon.setRotationPoint(0F, 5F, 3F);
	      rightRibbon.setTextureSize(64, 64);
	      rightRibbon.mirror = true;
	      setRotation(rightRibbon, -0.4886922F, -(float)Math.PI + 0.3F, 0.296706F);
	      bipedHead = new ModelRenderer(this, 0, 0);
	      bipedHead.addBox(-4F, -8F, -4F, 8, 8, 8);
	      bipedHead.setRotationPoint(0F, 1F, 0F);
	      bipedHead.setTextureSize(64, 64);
	      bipedHead.mirror = true;
	      setRotation(bipedHead, 0F, 0F, 0F);
	      longHair = new ModelRenderer(this, 24, 0);
	      longHair.addBox(-4F, -2F, -4F, 8, 5, 3);
	      longHair.setRotationPoint(0F, 0F, 4F);
	      longHair.setTextureSize(64, 64);
	      longHair.mirror = true;
	      setRotation(longHair, 0.2094395F, 0F, 0F);
	      leftBraid = new ModelRenderer(this, 0, 32);
	      leftBraid.addBox(0F, -1F, -1F, 1, 5, 1);
	      leftBraid.setRotationPoint(3F, -1F, -3F);
	      leftBraid.setTextureSize(64, 64);
	      leftBraid.mirror = true;
	      setRotation(leftBraid, -0.2443461F, 0F, 0F);
	      rightBraid = new ModelRenderer(this, 0, 32);
	      rightBraid.addBox(-1F, -1F, -1F, 1, 5, 1);
	      rightBraid.setRotationPoint(-3F, -1F, -3F);
	      rightBraid.setTextureSize(64, 64);
	      rightBraid.mirror = true;
	      setRotation(rightBraid, -0.2443461F, 0F, 0F);
	      leftKatyusha = new ModelRenderer(this, 4, 32);
	      leftKatyusha.addBox(0F, -1F, 0F, 4, 1, 1);
	      leftKatyusha.setRotationPoint(0F, -8F, -5F);
	      leftKatyusha.setTextureSize(64, 64);
	      leftKatyusha.mirror = true;
	      setRotation(leftKatyusha, 0F, -0.1745329F, 0.2268928F);
	      rightKatyusha = new ModelRenderer(this, 4, 32);
	      rightKatyusha.addBox(-4F, -1F, 0F, 4, 1, 1);
	      rightKatyusha.setRotationPoint(0F, -8F, -5F);
	      rightKatyusha.setTextureSize(64, 64);
	      rightKatyusha.mirror = true;
	      setRotation(rightKatyusha, 0F, 0.1745329F, -0.2268928F);
	      
	      bipedBody.addChild(bipedHead);
	      bipedBody.addChild(bipedRightArm);
	      bipedBody.addChild(bipedLeftArm);
	      bipedBody.addChild(bipedRightLeg);
	      bipedBody.addChild(bipedLeftLeg);
	      //bipedBody.addChild(skirtTop);
	      bipedHead.addChild(rightBraid);
	      bipedHead.addChild(leftBraid);
	      bipedBody.addChild(rightRibbon);
	      bipedBody.addChild(leftRibbon);
	      bipedHead.addChild(longHair);
	      bipedHead.addChild(rightKatyusha);
	      bipedHead.addChild(leftKatyusha);
		  bipedBody.addChild(skirtTop);
		  skirtTop.addChild(skirtBottom);
		  
		  
	}
	
    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entity, float moveCounter, float moveCycle, float ticksExisted, float headYaw, float headPitch, float scale)
    {
    	this.setRotationAngles(moveCounter, moveCycle, ticksExisted, headYaw, headPitch, scale, entity);
    	bipedBody.render(scale);
    }

	@Override
	public void setRotationAngles(float moveCounter, float moveCycle, float ticksExisted, float pheadYaw, float pheadPitch, float scale, Entity entity)
	{
		
		//f = 移動時に増加する
		//f1 = 増加したり戻ったり。スニーク、移動時は変化量が少し。
		//ticksExisted = 時間で常に増え続けている
		//pheadYaw = 向いている方角方向で変化
		//pheadPitch = 向いている上下方向で変化
		//scale = スケール値？
		setDefaultPause(moveCounter, moveCycle, ticksExisted, pheadYaw, pheadPitch, scale, entity);
		//bipedBody.rotationPointY = 5F;
		
		bipedRightLeg.rotationPointX = -2.0F;
		bipedLeftLeg.rotationPointX = 2.0F;
		//bipedBody.rotateAngleY = AS.getFloat(AS.mathHelperCos, f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		//bipedHead.rotationPointY = -1.0F;
		bipedHead.rotateAngleY = pheadYaw / 57.29578F;
		bipedHead.rotateAngleX = pheadPitch / 57.29578F;
		//bipedRightArm.rotateAngleY = 90F / 180F * 3.141593F;
		//bipedLeftArm.rotateAngleY = 90F / 180F * 3.141593F;
		
		bipedRightArm.rotateAngleX = MathHelper.cos(moveCounter * 0.6662F + 3.141593F) * 2.0F * moveCycle * 0.5F;
		bipedLeftArm.rotateAngleX = MathHelper.cos(moveCounter * 0.6662F) * 2.0F * moveCycle * 0.5F;
		bipedRightArm.rotateAngleZ = 30F / 180F * 3.141593F;
		bipedLeftArm.rotateAngleZ = -30F / 180F * 3.141593F;
		this.skirtTop.rotateAngleX = 0.0F;
		this.skirtBottom.rotationPointZ = 0.0F;
		this.skirtBottom.rotateAngleX = 0.0F;
		//this.bipedLeftArm.rotationPointY = -1F;
		//this.bipedRightArm.rotationPointY = -1F;
		
		if (entity.isSneaking()){
			bipedBody.rotateAngleY = MathHelper.cos(moveCounter * 0.6662F + (float) Math.PI) * 2.4F * moveCycle;
			skirtTop.rotateAngleY = MathHelper.cos(moveCounter * 0.6662F + (float) Math.PI) * 2.4F * moveCycle;
			bipedRightArm.rotateAngleX = MathHelper.cos(moveCounter * 0.6662F + (float) Math.PI) * 1.4F * moveCycle;
			bipedLeftArm.rotateAngleX = MathHelper.cos(moveCounter * 0.6662F) * 1.4F * moveCycle;
			//bipedLeftArm.rotateAngleZ = (MathHelper.cos(moveCounter * 0.2812F) - 1.0F) * 1.0F * moveCycle;
			//bipedRightArm.rotateAngleZ = (MathHelper.cos(moveCounter * 0.2312F) + 1.0F) * 1.0F * moveCycle;
		} else {
			
			//bipedRightArm.rotateAngleZ = 0.0F;
			//bipedLeftArm.rotateAngleZ = 0.0F;
		}
		
		bipedRightLeg.rotateAngleX = MathHelper.cos(moveCounter * 0.6662F) * 0.5F * moveCycle;//AS.getFloat(AS.mathHelperCos, f * 0.6662F) * 1.4F * f1;
		bipedLeftLeg.rotateAngleX = MathHelper.cos(moveCounter * 0.6662F + (float)Math.PI) * 0.5F * moveCycle;//AS.getFloat(AS.mathHelperCos, f * 0.6662F + 3.141593F) * 1.4F * f1;
		bipedRightLeg.rotateAngleY = bipedLeftLeg.rotateAngleY = bipedRightLeg.rotateAngleZ = bipedLeftLeg.rotateAngleZ = 0.0F;
		
		if (entity.isRiding()) {
			// 乗り物に乗っている
			this.bipedBody.rotationPointY = -1F;
			this.bipedRightLeg.rotationPointY = 5.0F;
			this.bipedLeftLeg.rotationPointY = 5.0F;
			this.bipedRightArm.rotateAngleX += -((float)Math.PI / 5F);
			this.bipedLeftArm.rotateAngleX += -((float)Math.PI / 5F);
			this.bipedRightLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
			this.bipedLeftLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
			this.bipedRightLeg.rotateAngleY = ((float)Math.PI / 14F);
			this.bipedLeftLeg.rotateAngleY = -((float)Math.PI / 14F);
			this.bipedRightLeg.rotateAngleZ = ((float)Math.PI / 14F);
			this.bipedLeftLeg.rotateAngleZ = -((float)Math.PI / 14F);
	          
	          skirtTop.rotationPointY = 5F;
	          skirtTop.rotationPointZ = 0F;
	          this.skirtTop.rotateAngleX = -((float)Math.PI / 5F);
	          skirtBottom.rotationPointZ =  0.0F;
	          skirtBottom.rotationPointY =  4F;
	          this.skirtBottom.rotateAngleX = -((float)Math.PI / 4F);
		} else {
			skirtTop.rotationPointY = 5F;
			skirtTop.rotationPointZ = 0F;
			skirtBottom.rotationPointZ = 0.0F;
	        skirtBottom.rotationPointY =  4.0F;
			this.bipedRightLeg.rotationPointY = bodyHeight;
			this.bipedLeftLeg.rotationPointY = bodyHeight;
			//setRotationAnglesGulliverBefore(f, f1, ticksExisted, pheadYaw, pheadPitch, scale, entity);
		}

		bipedRightArm.rotateAngleY = bipedLeftArm.rotateAngleY = 0.0F;

		bipedRightLeg.rotationPointZ = bipedLeftLeg.rotationPointZ =
				bipedBody.rotateAngleX = /*bipedBody.rotateAngleY =*/ bipedBody.rotateAngleZ = 0.0F;
		
		if (entity.isSneaking()) {
			// しゃがみ
			bipedHead.rotateAngleX -= 0.5F;
			//skirtTop.rotateAngleX = -upperBody.rotateAngleX;
		} else {
			// 通常立ち
		}

		//setRotationAnglesGulliverAfter(f, f1, ticksExisted, pheadYaw, pheadPitch, scale, entity);
	}
	
	public void setDefaultPause(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		if (bipedHead != null) bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		//if (bipedHeadwear != null) bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
		if (bipedBody != null) bipedBody.setRotationPoint(0.0F, 1.0F, 0.0F);
		if (bipedRightArm != null) bipedRightArm.setRotationPoint(-3.0F, 1.0F, 0.0F);
		if (bipedLeftArm != null) bipedLeftArm.setRotationPoint(3.0F, 1.0F, 0.0F);
		if (bipedRightLeg != null) bipedRightLeg.setRotationPoint(-2.0F, bodyHeight, 0.0F);
		if (bipedLeftLeg != null) bipedLeftLeg.setRotationPoint(2.0F, bodyHeight, 0.0F);
	}
	
	  private void setRotation(ModelRenderer model, float rotateX, float rotateY, float rotateZ)
	  {
		  model.rotateAngleX = rotateX;
		  model.rotateAngleY = rotateY;
		  model.rotateAngleZ = rotateZ;
	  }
}