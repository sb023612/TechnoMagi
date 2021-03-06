package com.ollieread.technomagi.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

import com.ollieread.technomagi.tileentity.IDisguisableTile;
import com.ollieread.technomagi.tileentity.TileEntityTM;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageSyncTileEntityTM implements IMessage, IMessageHandler<MessageSyncTileEntityTM, IMessage>
{
    public NBTTagCompound data;
    public int x;
    public int y;
    public int z;

    public MessageSyncTileEntityTM()
    {
    }

    public MessageSyncTileEntityTM(TileEntityTM tile)
    {
        data = new NBTTagCompound();
        tile.writeToNBT(data);

        x = tile.xCoord;
        y = tile.yCoord;
        z = tile.zCoord;
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        data = ByteBufUtils.readTag(buffer);
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        ByteBufUtils.writeTag(buffer, data);
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
    }

    @Override
    public IMessage onMessage(MessageSyncTileEntityTM message, MessageContext ctx)
    {
        TileEntityTM tile = (TileEntityTM) FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if (tile != null) {
            tile.readFromNBT(message.data);

            if (tile instanceof IDisguisableTile) {
                tile.getWorldObj().markBlockRangeForRenderUpdate(message.x, message.y, message.z, message.x, message.y, message.z);
            }
        }

        return null;
    }
}
