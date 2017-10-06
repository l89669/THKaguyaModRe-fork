package thKaguyaMod.tileentity;

//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.network.packet.Packet;
//import net.minecraft.network.packet.Packet130UpdateSign;
//import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDivineSpirit extends TileEntity
{

	//public int time;

	public TileEntityDivineSpirit() {
	}
	
    /*public void updateEntity()
    {
    	//if(this.blockMetadata >= 8)
    	{
    		time ++;
    		if(time >= 100)
    		{
    			this.getWorldObj().setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
    		}
    	}
    }*/
    
    /*@Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        this.time = par1NBTTagCompound.getInteger("count");
        
    }*/

    /*@Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("count", this.time);
    }*/
    
    /**
     * Overriden in a sign to provide the text.
     */
    /*public Packet getDescriptionPacket()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 4, nbttagcompound);
    }*/
    
    /*public Packet getDescriptionPacket()
    {
        return ;
    }*/
    
    /*public Packet getDescriptionPacket()
    {
        //String[] astring = new String[4];
        //System.arraycopy(this.signText, 0, astring, 0, 4);
    	return new Packet130UpdateSign
        return new Packet130UpdateSign(this.xCoord, this.yCoord, this.zCoord, time);
    }*/
    
}
