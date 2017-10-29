package thKaguyaMod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelHisou extends ModelBase
{	
    public ModelRenderer hisou[];

    public ModelHisou()
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
    	
        hisou = new ModelRenderer[3];
    	//持つ場所
        hisou[0] = new ModelRenderer(this, 32, 0);//テクスチャ座標0,0を左上にする
    	
    	//ボックスを追加する。
    	//addBox(X座標、Z座標、Y座標、X方向のサイズ、Z方向のサイズ、Y方向のサイズ、？（倍率？）)
    	//サイズはテクスチャのサイズそのもの
    	hisou[0].addBox(-2, -12, -2, 4, 8 , 4, 0.0F);
    	
    	//刃の部分
    	hisou[1] = new ModelRenderer(this, 0, 0);
    	hisou[1].addBox(-2, -4,  -2, 4, 28 , 4, 0.0F);
    	//把手
    	hisou[2] = new ModelRenderer(this, 32, 16);
    	hisou[2].addBox(-2, -20, 0,  12, 12 , 0, 0.0F);
    }

    @Override
    public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
    {
        for (int i = 0; i < 3; i++)
    	{
    		hisou[i].render(size);
    	}
    }
}
