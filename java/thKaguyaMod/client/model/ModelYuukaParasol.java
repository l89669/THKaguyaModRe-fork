package thKaguyaMod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelYuukaParasol extends ModelBase
{
	//幽香の日傘の取手部、骨部のモデル
    public ModelRenderer stick[];
	public ModelRenderer handle[];
	
    public ModelYuukaParasol()
    {
    	/*Minecraftのモデルの基本事項
    	基本的にテクスチャに64x32の画像を使用
    	ボックスタイプのモデルしか扱えない
    	ボックスのサイズ＝テクスチャのサイズと考えていい
    	例えば横幅が10の箱ならば、そこに設定されるテクスチャも10ドット分使用する
    	横幅6、高さ4、奥行き3の箱を考えたとき、イメージとしてはテクスチャはこのようになる
    	000000111111222222000000
    	000000111111222222000000
    	000000111111222222000000
    	333333444444555555666666
    	333333444444555555666666
    	333333444444555555666666
    	333333444444555555666666
    	0は未使用部と考えて、ボックス型は6面存在するためこのように６つの部分に分けれれることになる。
    	この配置は人間の頭で言うと、
    	1は頭の上　2は頭の下　3は頭右　4は顔　5は頭左　6は頭後ろにあたる
    	一番左上にあたる座標はModelRenderer(this,x,y)で設定可能。
    	*/
    	stick = new ModelRenderer[4];
    	for(int i = 0; i < 4; i++)
    	{
    		stick[i] = new ModelRenderer(this, 0, 0);
    		stick[i].addBox(-2, i * 28, -2, 4, 28, 4, 0.0F);
    	}
    	
        handle = new ModelRenderer[3];
    	//持つ場所
        handle[0] = new ModelRenderer(this, 32, 16);//テクスチャ座標0,0を左上にする
    	
    	//ボックスを追加する。
    	//addBox(X座標、Z座標、Y座標、X方向のサイズ、Z方向のサイズ、Y方向のサイズ、？（倍率？）)
    	//サイズはテクスチャのサイズそのもの
    	handle[0].addBox(-3, -10, -3, 6, 10 , 6, 0.0F);
    	
    	handle[1] = new ModelRenderer(this, 32, 16);
    	handle[1].addBox( 0, 0, -6, 6, 10 , 6, 0.0F);
    	handle[1].setRotationPoint( -3F, -10F, -3F);
    	handle[1].rotateAngleX = ((float)Math.PI / 3F * 2F);
    	
    	handle[2] = new ModelRenderer(this, 32, 16);
    	handle[2].addBox( 0, 0, -6,  6, 10 , 6, 0.0F);
    	handle[2].setRotationPoint( -3F, -10F + MathHelper.cos((float)Math.PI / 3F * 2F) * 10F, -3F + MathHelper.sin((float)Math.PI / 3F * 2F) * 10F);
    	handle[2].rotateAngleX = ((float)Math.PI / 3F);
    }

    @Override
    public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
    {
    	for(int i = 0; i < 4; i++)
    	{
    		stick[i].render(size);
    	}
        for (int i = 0; i < 3; i++)
    	{
    		handle[i].render(size);
    	}
    }
}
