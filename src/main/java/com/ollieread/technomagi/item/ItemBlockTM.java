package com.ollieread.technomagi.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockTM extends ItemBlock
{
    private Block field_150950_b;

    public ItemBlockTM(Block block)
    {
        super(block);
        this.field_150950_b = block;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1)
    {
        return this.field_150950_b.getIcon(2, par1);
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int par1)
    {
        return par1;
    }

    public String getUnlocalizedName(ItemStack stack)
    {
        return getUnlocalizedName() + "." + stack.getItemDamage();
    }

}
