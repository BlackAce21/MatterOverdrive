/*
 * This file is part of Matter Overdrive
 * Copyright (c) 2015., Simeon Radivoev, All rights reserved.
 *
 * Matter Overdrive is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Matter Overdrive is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Matter Overdrive.  If not, see <http://www.gnu.org/licenses>.
 */

package matteroverdrive.init;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import matteroverdrive.data.quest.GenericQuest;
import matteroverdrive.data.quest.RandomQuestText;
import matteroverdrive.data.quest.WeightedRandomQuest;
import matteroverdrive.data.quest.logic.QuestLogicCocktailOfAscension;
import matteroverdrive.data.quest.logic.QuestLogicCollectItem;
import matteroverdrive.data.quest.logic.QuestLogicKillCreature;
import matteroverdrive.entity.monster.EntityRougeAndroidMob;
import matteroverdrive.handler.quest.Quests;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simeon on 11/19/2015.
 */
public class MatterOverdriveQuests
{
    public static List<WeightedRandom.Item> contractGeneration = new ArrayList<>();
    public static RandomQuestText killAndroids;
    public static GenericQuest cocktailOfAscension;
    public static GenericQuest sacrifice;
    public static GenericQuest departmentOfAgriculture;

    public static void init(FMLPreInitializationEvent event)
    {
        killAndroids = new RandomQuestText(new QuestLogicKillCreature(EntityRougeAndroidMob.class,3,6),"kill_androids",1);
        cocktailOfAscension = new GenericQuest(new QuestLogicCocktailOfAscension(),"cocktail_of_ascension");
        sacrifice = new GenericQuest(new QuestLogicKillCreature(new Class[]{EntityChicken.class, EntityCow.class,EntityCow.class},8,15).setOnlyChildren(true),"sacrifice");
        departmentOfAgriculture = new GenericQuest(new QuestLogicCollectItem(new ItemStack[]{new ItemStack(Items.wheat),new ItemStack(Items.carrot),new ItemStack(Items.potato)},32,64),"department_of_agriculture");
    }

    public static void register(FMLInitializationEvent event, Quests quests)
    {
        quests.registerQuest("kill_androids",killAndroids);
        quests.registerQuest("cocktail_of_ascension",cocktailOfAscension);
        quests.registerQuest("sacrifice",sacrifice);
        quests.registerQuest("department_of_agriculture",departmentOfAgriculture);

        contractGeneration.add(new WeightedRandomQuest(killAndroids,100));
    }
}
