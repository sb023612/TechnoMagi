package com.ollieread.technomagi.research.event;

import com.ollieread.ennds.extended.ExtendedPlayerKnowledge;
import com.ollieread.ennds.research.IResearchEvent;
import com.ollieread.ennds.research.Research;
import com.ollieread.technomagi.common.Reference;

public class ResearchEventKnowledgeEnderman extends Research implements IResearchEvent
{

    public ResearchEventKnowledgeEnderman(String name, String knowledge, int progress)
    {
        super(name, knowledge, progress, Reference.MODID.toLowerCase());
    }

    @Override
    public boolean isRepeating()
    {
        return true;
    }

    @Override
    public boolean canPerform(ExtendedPlayerKnowledge charon)
    {
        return true;
    }

    @Override
    public String getEvent()
    {
        return "knowledgeEntityEnderman";
    }

    @Override
    public int getChance()
    {
        return 1;
    }

}
