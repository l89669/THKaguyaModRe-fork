package thKaguyaMod.entity.shot;

import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public abstract interface ISpecialShot
{
  public abstract void specialShot_move(World paramWorld, int paramInt, EntityTHShot paramEntityTHShot);
  
  public abstract boolean specialShot_hitBlock(World paramWorld, int paramInt, EntityTHShot paramEntityTHShot, MovingObjectPosition paramMovingObjectPosition);
  
  public abstract boolean specialShot_hitEntity(World paramWorld, int paramInt, EntityTHShot paramEntityTHShot, Entity paramEntity);
}


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.shot.ISpecialShot
 * JD-Core Version:    0.7.0.1
 */