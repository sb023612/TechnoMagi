package com.ollieread.technomagi.tileentity;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;

import com.ollieread.technomagi.network.message.MessageSyncTileEntityTM;
import com.ollieread.technomagi.util.PacketHelper;

public class TileEntityEmptyFiller extends TileEntityTM
{

    public int parentX;
    public int parentY;
    public int parentZ;

    public void setParent(int x, int y, int z)
    {
        if (!worldObj.isRemote) {
            System.out.println(x + ":" + y + ":" + z);
            parentX = x;
            parentY = y;
            parentZ = z;

            PacketHelper.syncTile(new MessageSyncTileEntityTM(this));
        }
    }

    public Block getParent()
    {
        return worldObj.getBlock(parentX, parentY, parentZ);
    }

    @Override
    public boolean canUpdate()
    {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        parentX = compound.getInteger("ParentX");
        parentY = compound.getInteger("ParentY");
        parentZ = compound.getInteger("ParentZ");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        compound.setInteger("ParentX", parentX);
        compound.setInteger("ParentY", parentY);
        compound.setInteger("ParentZ", parentZ);
    }

}