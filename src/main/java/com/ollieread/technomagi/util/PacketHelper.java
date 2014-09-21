package com.ollieread.technomagi.util;

import net.minecraft.entity.player.EntityPlayerMP;

import com.ollieread.technomagi.network.PacketHandler;
import com.ollieread.technomagi.network.message.MessageOpenTeleporter;
import com.ollieread.technomagi.network.message.MessageSetArchive;
import com.ollieread.technomagi.network.message.MessageSetBuilding;
import com.ollieread.technomagi.network.message.MessageSetCrafting;
import com.ollieread.technomagi.network.message.MessageSetProgress;
import com.ollieread.technomagi.network.message.MessageSetTeleporterMode;
import com.ollieread.technomagi.network.message.MessageSyncTileEntityTM;
import com.ollieread.technomagi.tileentity.TileEntityArchive;
import com.ollieread.technomagi.tileentity.TileEntityConstruct;
import com.ollieread.technomagi.tileentity.TileEntityCrafting;
import com.ollieread.technomagi.tileentity.TileEntityResearch;
import com.ollieread.technomagi.tileentity.TileEntityTM;
import com.ollieread.technomagi.tileentity.TileEntityTeleporter;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class PacketHelper
{

    public static void setArchive(TileEntityArchive archive, int type, int subtype, int page)
    {
        PacketHandler.INSTANCE.sendToServer(new MessageSetArchive(archive, type, subtype, page));
    }

    public static void setTeleporterMode(TileEntityTeleporter machine, int mode)
    {
        PacketHandler.INSTANCE.sendToServer(new MessageSetTeleporterMode(machine, mode));
    }

    public static void openTeleporter(int x, int y, int z, EntityPlayerMP player)
    {
        PacketHandler.INSTANCE.sendTo(new MessageOpenTeleporter(x, y, z), player);
    }

    public static void setBuilding(TileEntityConstruct machine, boolean building)
    {
        PacketHandler.INSTANCE.sendToServer(new MessageSetBuilding(machine, building));
    }

    public static void setCrafting(TileEntityCrafting machine, boolean progress)
    {
        PacketHandler.INSTANCE.sendToServer(new MessageSetCrafting(machine, progress));
    }

    public static void setProgress(TileEntityResearch machine, boolean progress)
    {
        PacketHandler.INSTANCE.sendToServer(new MessageSetProgress(machine, progress));
    }

    public static void syncTile(IMessage message)
    {
        PacketHandler.INSTANCE.sendToAll(message);
    }

    public static void syncTile(TileEntityTM tile)
    {
        syncTile(new MessageSyncTileEntityTM(tile));
    }

}
