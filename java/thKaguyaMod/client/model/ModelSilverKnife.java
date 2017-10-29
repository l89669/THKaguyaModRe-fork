package thKaguyaMod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSilverKnife extends ModelBase
{
	//銀のナイフのモデル
	
    public ModelRenderer knife[];

    public ModelSilverKnife()
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
    	
        knife = new ModelRenderer[3];
    	//鍔
        knife[0] = new ModelRenderer(this, 0, 0);//テクスチャ座標0,0を左上にする
    	
    	//ボックスを追加する。
    	//addBox(X座標、Z座標、Y座標、X方向のサイズ、Z方向のサイズ、Y方向のサイズ、？（倍率？）)
    	//サイズはテクスチャのサイズそのもの
    	knife[0].addBox(-4, -5, -1, 7,1 , 3, 0.0F);
    	knife[0].rotateAngleX = ((float)Math.PI / 2F);
    	
    	//柄
    	knife[1] = new ModelRenderer(this, 0, 16);
    	knife[1].addBox(-2, -9,  0, 3,14 , 1, 0.0F);
    	knife[1].rotateAngleX = ((float)Math.PI / 2F);
    	
    	//刃部　小
    	knife[2] = new ModelRenderer(this, 32, 16);
    	knife[2].addBox(-1, 5, 0, 1, 1 , 1, 0.0F);
        knife[2].rotateAngleX = ((float)Math.PI / 2F);
        
    }

    @Override
    public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
    {
        for (int i = 0; i < 3; i++)
    	{
    		knife[i].render(size);//描画　par7は気にしなくていいと思う
    	}
    }
}
