package com.ollieread.technomagi.item;

import java.util.Iterator;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.ollieread.ennds.research.ResearchRegistry;
import com.ollieread.technomagi.common.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMobBrain extends ItemTM
{
    @SideOnly(Side.CLIENT)
    private IIcon theIcon;
    private static final String __OBFID = "CL_00000070";

    public ItemMobBrain(String name)
    {
        super(name);

        setHasSubtypes(true);
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
    {

    }

    public void onCreated(ItemStack stack, World world, EntityPlayer player)
    {
        stack.stackTagCompound = new NBTTagCompound();
    }

    protected String getEntity(ItemStack stack)
    {
        NBTTagCompound compound = stack.stackTagCompound;

        if (compound.hasKey("Entity")) {
            return compound.getString("Entity");
        }

        return null;
    }

    public static void setEntity(ItemStack stack, Class entityClass)
    {
        NBTTagCompound compound = stack.stackTagCompound;
        compound.setString("Entity", (String) EntityList.classToStringMapping.get(entityClass));
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        String s = ("" + StatCollector.translateToLocal(this.getUnlocalizedName() + ".name")).trim();
        String entityName = getEntity(stack);

        if (entityName != null) {
            s = StatCollector.translateToLocal("entity." + entityName + ".name") + " " + s;
        }

        return s;
    }

    /**
     * Callback for item usage. If the item does something special on right
     * clicking, he will have one of those. Return True if something happen and
     * false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        return false;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is
     * pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        return par1ItemStack;
    }

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }

    /**
     * Gets an icon index based on an item's damage value and the given render
     * pass
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int par1, int par2)
    {
        return par2 > 0 ? this.theIcon : super.getIconFromDamageForRenderPass(par1, par2);
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye
     * returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
    {
        Iterator iterator = ResearchRegistry.getBrainableEntities().iterator();

        while (iterator.hasNext()) {
            EntityList.EntityEggInfo entityegginfo = (EntityList.EntityEggInfo) EntityList.entityEggs.get(iterator.next());
            p_150895_3_.add(new ItemStack(p_150895_1_, 1, entityegginfo.spawnedID));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(Reference.MODID.toLowerCase() + ":" + getIconString());
        this.theIcon = par1IconRegister.registerIcon(Reference.MODID.toLowerCase() + ":" + getIconString() + "_overlay");
    }
}