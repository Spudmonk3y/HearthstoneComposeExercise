package com.monkeybased.hearthtechnicalexercise.data.model

import androidx.compose.ui.graphics.Color
import com.monkeybased.hearthtechnicalexercise.R
import com.monkeybased.hearthtechnicalexercise.ui.theme.DeathKnightClassColor
import com.monkeybased.hearthtechnicalexercise.ui.theme.DemonHunterClassColor
import com.monkeybased.hearthtechnicalexercise.ui.theme.DruidClassColor
import com.monkeybased.hearthtechnicalexercise.ui.theme.HunterClassColor
import com.monkeybased.hearthtechnicalexercise.ui.theme.MageClassColor
import com.monkeybased.hearthtechnicalexercise.ui.theme.PaladinClassColor
import com.monkeybased.hearthtechnicalexercise.ui.theme.PriestClassColor
import com.monkeybased.hearthtechnicalexercise.ui.theme.RogueClassColor
import com.monkeybased.hearthtechnicalexercise.ui.theme.ShamanClassColor
import com.monkeybased.hearthtechnicalexercise.ui.theme.WarlockClassColor
import com.monkeybased.hearthtechnicalexercise.ui.theme.WarriorClassColor

enum class CharacterClass(val label: String, val iconResId: Int, val classColor: Color) {
    DEATH_KNIGHT("Death Knight", R.drawable.class_deathknight, DeathKnightClassColor),
    DEMON_HUNTER("Demon Hunter", R.drawable.class_demonhunter, DemonHunterClassColor),
    DRUID("Druid", R.drawable.class_druid, DruidClassColor),
    HUNTER("Hunter", R.drawable.class_hunter, HunterClassColor),
    MAGE("Mage", R.drawable.class_mage, MageClassColor),
    PALADIN("Paladin", R.drawable.class_paladin, PaladinClassColor),
    PRIEST("Priest", R.drawable.class_priest, PriestClassColor),
    ROGUE("Rogue", R.drawable.class_rogue, RogueClassColor),
    SHAMAN("Shaman", R.drawable.class_shaman, ShamanClassColor),
    WARLOCK("Warlock", R.drawable.class_warlock, WarlockClassColor),
    WARRIOR("Warrior", R.drawable.class_warrior, WarriorClassColor)
}