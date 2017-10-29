package thKaguyaMod.client.model.living;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelMiko extends ModelBase
{

	public ModelRenderer bipedHead;
	public ModelRenderer bipedBody;
	public ModelRenderer bipedRightArm;
	public ModelRenderer bipedLeftArm;
	public ModelRenderer bipedRightLeg;
	public ModelRenderer bipedLeftLeg;
	
    public ModelRenderer skirtTop;
    public ModelRenderer skirtBottom;
    public ModelRenderer cape;
    public ModelRenderer Manto1;
    public ModelRenderer Manto2;
    public ModelRenderer Manto3;
    public ModelRenderer Manto4;
    public ModelRenderer longHair;
    public ModelRenderer leftBraid;
    public ModelRenderer rightBraid;
    public ModelRenderer MimiateRight;
    public ModelRenderer MimiateLeft;
    public ModelRenderer HearLeft1;
    public ModelRenderer HearLeft2;
    public ModelRenderer HearRight1;
    public ModelRenderer HearRight2;
    
	public ModelRenderer eyeR;
	public ModelRenderer eyeL;
	
	protected float legHeight = 12F;
	protected float bodyHeight = 9F;

	public ModelMiko()
	{
		this(0.0F);
	}

	public ModelMiko(float size)
	{
		this(size, 0.0F, 64, 64);
	}

	public ModelMiko(float size, float yOffset)
	{
		this(size, yOffset , 64, 64);
	}

	public ModelMiko(float size, float yOffset, int textureWidth, int textureHeight) 
	{
		
		this.textureHeight = 64;
		this.textureWidth = 64;

		
	      bipedBody = new ModelRenderer(this, 32, 8);
	      bipedBody.addBox(-3F, 0F, -2F, 6, 9, 4);
	      bipedBody.setRotationPoint(0F, 3F, 0F);
	      bipedBody.setTextureSize(64, 64);
	      bipedBody.mirror = true;
	      setRotation(bipedBody, 0F, 0F, 0F);
	      bipedRightArm = new ModelRenderer(this, 56, 0);
	      bipedRightArm.addBox(-0.8F, -1F, -1F, 2, 8, 2);
	      bipedRightArm.setRotationPoint(-4F, 4F, 0F);
	      bipedRightArm.setTextureSize(64, 64);
	      bipedRightArm.mirror = true;
	      setRotation(bipedRightArm, 0F, 0F, 0F);
	      bipedLeftArm = new ModelRenderer(this, 56, 0);
	      bipedLeftArm.addBox(-1.2F, -1F, -1F, 2, 8, 2);
	      bipedLeftArm.setRotationPoint(4F, 4F, 0F);
	      bipedLeftArm.setTextureSize(64, 64);
	      bipedLeftArm.mirror = true;
	      setRotation(bipedLeftArm, 0F, 0F, 0F);
	      bipedRightLeg = new ModelRenderer(this, 50, 18);
	      bipedRightLeg.addBox(-1F, 0F, -2F, 3, 12, 4);
	      bipedRightLeg.setRotationPoint(-2F, 9F, 0F);
	      bipedRightLeg.setTextureSize(64, 64);
	      bipedRightLeg.mirror = true;
	      setRotation(bipedRightLeg, 0F, 0F, 0F);
	      bipedLeftLeg = new ModelRenderer(this, 50, 18);
	      bipedLeftLeg.addBox(-2F, 0F, -2F, 3, 12, 4);
	      bipedLeftLeg.setRotationPoint(2F, 0F, 0F);
	      bipedLeftLeg.setTextureSize(64, 64);
	      bipedLeftLeg.mirror = true;
	      setRotation(bipedLeftLeg, 0F, 0F, 0F);
	      skirtTop = new ModelRenderer(this, 0, 16);
	      skirtTop.addBox(-4F, 0F, -6F, 8, 4, 8);
	      skirtTop.setRotationPoint(0F, 6F, 2F);
	      skirtTop.setTextureSize(64, 64);
	      skirtTop.mirror = true;
	      setRotation(skirtTop, 0F, 0F, 0F);
	      skirtBottom = new ModelRenderer(this, 16, 31);
	      skirtBottom.addBox(-5F, 0F, -10F, 10, 8, 10);
	      skirtBottom.setRotationPoint(0F, 4F, 3F);
	      skirtBottom.setTextureSize(64, 64);
	      skirtBottom.mirror = true;
	      setRotation(skirtBottom, 0F, 0F, 0F);
	      cape = new ModelRenderer(this, 30, 54);
	      cape.addBox(-5F, -4F, -2.5F, 10, 3, 7);
	      cape.setRotationPoint(0F, 2F, 0F);
	      cape.setTextureSize(64, 64);
	      cape.mirror = true;
	      setRotation(cape, 0F, 0F, 0F);
	      Manto1 = new ModelRenderer(this, 0, 49);
	      Manto1.addBox(-5.5F, 0F, -1F, 11, 3, 1);
	      Manto1.setRotationPoint(0F, 0F, 3F);
	      Manto1.setTextureSize(64, 64);
	      Manto1.mirror = true;
	      setRotation(Manto1, 0F, 0F, 0F);
	      Manto2 = new ModelRenderer(this, 0, 53);
	      Manto2.addBox(-6.0F, 0F, -1F, 12, 4, 1);
	      Manto2.setRotationPoint(0F, 3F, 0F);
	      Manto2.setTextureSize(64, 64);
	      Manto2.mirror = true;
	      setRotation(Manto2, 0F, 0F, 0F);
	      Manto3 = new ModelRenderer(this, 0, 58);
	      Manto3.addBox(-6.5F, 0F, -1F, 13, 5, 1);
	      Manto3.setRotationPoint(0F, 4F, 0F);
	      Manto3.setTextureSize(64, 64);
	      Manto3.mirror = true;
	      setRotation(Manto3, 0F, 0F, 0F);
	      Manto4 = new ModelRenderer(this, 0, 58);
	      Manto4.addBox(-6.5F, 0F, -1F, 13, 5, 1);
	      Manto4.setRotationPoint(0F, 5F, 0F);
	      Manto4.setTextureSize(64, 64);
	      Manto4.mirror = true;
	      setRotation(Manto4, 0F, 0F, 0F);
	      bipedHead = new ModelRenderer(this, 0, 0);
	      bipedHead.addBox(-4F, -8F, -4F, 8, 8, 8);
	      bipedHead.setRotationPoint(0F, 3F, 0F);
	      bipedHead.setTextureSize(64, 64);
	      bipedHead.mirror = true;
	      setRotation(bipedHead, 0F, 0F, 0F);
	      longHair = new ModelRenderer(this, 0, 28);
	      longHair.addBox(-4F, -2F, -3F, 8, 3, 3);
	      longHair.setRotationPoint(0F, 0F, 4F);
	      longHair.setTextureSize(64, 64);
	      longHair.mirror = true;
	      setRotation(longHair, 0.2094395F, 0F, 0F);
	      leftBraid = new ModelRenderer(this, 0, 16);
	      leftBraid.addBox(0F, -3F, -1F, 1, 4, 1);
	      leftBraid.setRotationPoint(3F, 0F, -3F);
	      leftBraid.setTextureSize(64, 64);
	      leftBraid.mirror = true;
	      setRotation(leftBraid, -0.2443461F, 0F, 0F);
	      rightBraid = new ModelRenderer(this, 0, 16);
	      rightBraid.addBox(-1F, -3F, -1F, 1, 4, 1);
	      rightBraid.setRotationPoint(-3F, 0F, -3F);
	      rightBraid.setTextureSize(64, 64);
	      rightBraid.mirror = true;
	      setRotation(rightBraid, -0.2443461F, 0F, 0F);
	      MimiateRight = new ModelRenderer(this, 0, 41);
	      MimiateRight.addBox(-2F, -5F, -1F, 4, 4, 1);
	      MimiateRight.setRotationPoint(-4F, -1F, 0F);
	      MimiateRight.setTextureSize(64, 64);
	      MimiateRight.mirror = true;
	      setRotation(MimiateRight, 0F, 1.570796F, 0F);
	      MimiateLeft = new ModelRenderer(this, 0, 41);
	      MimiateLeft.addBox(-2F, -5F, -1F, 4, 4, 1);
	      MimiateLeft.setRotationPoint(4F, -1F, 0F);
	      MimiateLeft.setTextureSize(64, 64);
	      MimiateLeft.mirror = true;
	      setRotation(MimiateLeft, 0F, -1.570796F, 0F);
	      HearLeft1 = new ModelRenderer(this, 24, 0);
	      HearLeft1.addBox(0F, -5F, -2F, 4, 2, 4);
	      HearLeft1.setRotationPoint(0F, -5F, 0F);
	      HearLeft1.setTextureSize(64, 64);
	      HearLeft1.mirror = true;
	      setRotation(HearLeft1, 0F, 0F, 0.1745329F);
	      HearLeft2 = new ModelRenderer(this, 38, 1);
	      HearLeft2.addBox(0F, -1F, -1.5F, 3, 3, 3);
	      HearLeft2.setRotationPoint(1F, -7F, 0F);
	      HearLeft2.setTextureSize(64, 64);
	      HearLeft2.mirror = true;
	      setRotation(HearLeft2, 0F, 0F, 0.1745329F);
	      HearRight1 = new ModelRenderer(this, 24, 0);
	      HearRight1.addBox(-4F, -5F, -2F, 4, 2, 4);
	      HearRight1.setRotationPoint(0F, -5F, 0F);
	      HearRight1.setTextureSize(64, 64);
	      HearRight1.mirror = true;
	      setRotation(HearRight1, 0F, 0F, -0.1745329F);
	      HearRight2 = new ModelRenderer(this, 38, 1);
	      HearRight2.addBox(-3F, -1F, -1.5F, 3, 3, 3);
	      HearRight2.setRotationPoint(-1F, -7F, 0F);
	      HearRight2.setTextureSize(64, 64);
	      HearRight2.mirror = true;
	      setRotation(HearRight2, 0F, 0F, -0.1745329F);
	      
			bipedBody.addChild(bipedHead);
			bipedBody.addChild(bipedRightArm);
			bipedBody.addChild(bipedLeftArm);
			bipedBody.addChild(bipedRightLeg);
			bipedBody.addChild(bipedLeftLeg);
			
			bipedHead.addChild(rightBraid);
			bipedHead.addChild(leftBraid);
			bipedHead.addChild(MimiateRight);
			bipedHead.addChild(MimiateLeft);
			bipedHead.addChild(HearLeft1);
			HearLeft1.addChild(HearLeft2);
			bipedHead.addChild(HearRight1);
			HearRight1.addChild(HearRight2);
			bipedBody.addChild(Manto1);
			Manto1.addChild(Manto2);
			Manto2.addChild(Manto3);
			Manto3.addChild(Manto4);
			bipedBody.addChild(cape);
		    bipedHead.addChild(longHair);
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
	public void setRotationAngles(float moveCounter, float moveCycle, float ticksExisted, float pheadYaw, float pheadPitch, float scale, Entity entity) {

		
		//moveCounter = 移動時に増加する
		//moveCycle = 増加したり戻ったり。スニーク、移動時は変化量が少し。
		//ticksExisted = 時間で常に増え続けている
		//pheadYaw = 向いている方角方向で変化
		//pheadPitch = 向いている上下方向で変化
		//scale = スケール値？
		setDefaultPause(moveCounter, moveCycle, ticksExisted, pheadYaw, pheadPitch, scale, entity);
		this.bipedBody.rotateAngleX = 0.0F;
		this.bipedHead.rotateAngleY = pheadYaw / 57.29578F;
		this.bipedHead.rotateAngleX = pheadPitch / 57.29578F;
		
		this.bipedRightArm.rotateAngleX = MathHelper.cos( moveCounter * 0.6662F + 3.141593F) * 2.0F * moveCycle * 0.5F;
		this.bipedRightArm.rotateAngleY = 90F / 180F * 3.141593F;
		this.bipedLeftArm.rotateAngleX  = MathHelper.cos( moveCounter * 0.6662F) * 2.0F * moveCycle * 0.5F;
		this.bipedLeftArm.rotateAngleY  = 90F / 180F * 3.141593F;
		this.bipedRightArm.rotateAngleZ  = 20F / 180F * 3.141593F;
		this.bipedLeftArm.rotateAngleZ  = -20F / 180F * 3.141593F;
		
		
		
		
		this.bipedRightLeg.rotateAngleX = MathHelper.cos(moveCounter * 0.6662F) * 0.5F * moveCycle;//AS.getFloat(AS.mathHelperCos, f * 0.6662F) * 1.4F * moveCycle;
		this.bipedLeftLeg.rotateAngleX = MathHelper.cos(moveCounter * 0.6662F + (float)Math.PI) * 0.5F * moveCycle;//AS.getFloat(AS.mathHelperCos, f * 0.6662F + 3.141593F) * 1.4F * moveCycle;
		this.bipedRightLeg.rotateAngleY = bipedLeftLeg.rotateAngleY = bipedRightLeg.rotateAngleZ = bipedLeftLeg.rotateAngleZ = 0.0F;
		
		this.skirtTop.rotateAngleX = 0.0F;
		this.skirtBottom.rotateAngleX = 0.0F;
		
		//マント
		this.Manto1.rotateAngleX = (float)Math.sin((double)(moveCounter + ticksExisted) / 10.0D) * 0.1F + 0.3F;
		this.Manto2.rotateAngleX = (float)Math.sin((double)(moveCounter * 1.2F + ticksExisted) / 7.8D) * 0.25F + 0.25F;
		this.Manto3.rotateAngleX = (float)Math.sin((double)(moveCounter * 1.5F + ticksExisted) / 5.3D) * 0.3F + 0.20F;
		this.Manto4.rotateAngleX = (float)Math.sin((double)(moveCounter * 2.0F + ticksExisted) / 4.1D) * 0.34F + 0.0F;
		
		/*if (ModchuModel_ModelCapsHelper.getCapsValueBoolean(this, entityCaps, caps_getIsSneak)
				&& !ModchuModel_ModelCapsHelper.getCapsValueBoolean(this, entityCaps, caps_getIsRiding)
				&& ModchuModel_ModelCapsHelper.getCapsValueBoolean(this, entityCaps, caps_oldwalking)) {
			bipedBody.rotateAngleY = MathHelper.cos( moveCounter * 0.6662F + (float) Math.PI) * 2.4F * moveCycle;
			//upperBody.rotateAngleY = bipedBody.rotateAngleY;
			skirtTop.rotateAngleY = MathHelper.cos( moveCounter * 0.6662F + (float) Math.PI) * 2.4F * moveCycle;
			bipedRightArm.rotateAngleX = MathHelper.cos( moveCounter * 0.6662F + (float) Math.PI) * 1.4F * moveCycle;
			bipedLeftArm.rotateAngleX = MathHelper.cos( moveCounter * 0.6662F) * 1.4F * moveCycle;
			//bipedLeftArm.rotateAngleZ = (AS.getFloat(AS.mathHelperCos, moveCounter * 0.2812F) - 1.0F) * 1.0F * moveCycle;
			bipedLeftArm.rotateAngleZ = (MathHelper.cos( moveCounter * 0.1812F) - 1.0F) * 1.0F * moveCycle;
			bipedRightArm.rotateAngleZ = (MathHelper.cos( moveCounter * 0.2312F) + 1.0F) * 1.0F * moveCycle;
		} else {
			
			bipedRightArm.rotateAngleZ = 0.0F;
			bipedLeftArm.rotateAngleZ = 0.0F;
		}*/
		

		if (entity.isRiding()) {
		//if (ModchuModel_ModelCapsHelper.getCapsValueBoolean(this, entityCaps, caps_getIsRiding)) {
			// 乗り物に乗っている
			bipedBody.rotationPointY = -1.0F;
			this.bipedRightArm.rotateAngleX += -((float)Math.PI / 5F);
			this.bipedLeftArm.rotateAngleX += -((float)Math.PI / 5F);
			this.bipedRightLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
			this.bipedLeftLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
			this.bipedRightLeg.rotateAngleY = ((float)Math.PI / 14F);
			this.bipedLeftLeg.rotateAngleY = -((float)Math.PI / 14F);
			this.bipedRightLeg.rotateAngleZ = ((float)Math.PI / 14F);
			this.bipedLeftLeg.rotateAngleZ = -((float)Math.PI / 14F);

			this.skirtTop.rotateAngleX = -(20F / 180F * (float)Math.PI);
			this.skirtBottom.rotateAngleX = -(30F / 180F * (float)Math.PI);
		} else {
			//setRotationAnglesGulliverBefore(moveCounter, moveCycle, ticksExisted, pheadYaw, pheadPitch, scale, entityCaps);
		}
		
		bipedRightArm.rotateAngleY = bipedLeftArm.rotateAngleY = 0.0F;

		
	}
	
	
	//@Override
	public void setDefaultPause(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		if (bipedHead != null) bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		//if (bipedHeadwear != null) bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
		if (bipedBody != null) bipedBody.setRotationPoint(0.0F, 3.0F, 0.0F);
		if (bipedRightArm != null) bipedRightArm.setRotationPoint(-3.0F, 1.0F, 0.0F);
		if (bipedLeftArm != null) bipedLeftArm.setRotationPoint(3.0F, 1.0F, 0.0F);
		if (bipedRightLeg != null) bipedRightLeg.setRotationPoint(-2.0F, bodyHeight, 0.0F);
		if (bipedLeftLeg != null) bipedLeftLeg.setRotationPoint(2.0F, bodyHeight, 0.0F);
		//if (Skirt != null) Skirt.setRotationPoint(0.0F, 4.0F, 0.0F);
	}
	
	  private void setRotation(ModelRenderer model, float rotateX, float rotateY, float rotateZ)
	  {
		  model.rotateAngleX = rotateX;
		  model.rotateAngleY = rotateY;
		  model.rotateAngleZ = rotateZ;
	  }
}
