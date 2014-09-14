package com.ollieread.technomagi.research.crafting;

import net.minecraft.item.ItemStack;

import com.ollieread.ennds.extended.ExtendedPlayerKnowledge;
import com.ollieread.ennds.research.IResearchCrafting;
import com.ollieread.ennds.research.Research;
import com.ollieread.technomagi.common.Reference;

public class ResearchCraftingFlintAndSteel extends Research implements IResearchCrafting
{

    public ResearchCraftingFlintAndSteel(String name, String knowledge, int progress)
    {
        super(name, knowledge, progress, Reference.MODID.toLowerCase());
    }

    @Override
    public boolean isRepeating()
    {
        return false;
    }

    @Override
    public boolean canPerform(ExtendedPlayerKnowledge charon)
    {
        return true;
    }

    @Override
    public ItemStack getCrafting()
    {
        return new ItemStack(net.minecraft.init.Items.flint_and_steel);
    }

    @Override
    public int getChance()
    {
        return 1;
    }

}
