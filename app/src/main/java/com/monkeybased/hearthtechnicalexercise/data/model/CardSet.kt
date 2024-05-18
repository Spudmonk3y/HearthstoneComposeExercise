package com.monkeybased.hearthtechnicalexercise.data.model

import com.monkeybased.hearthtechnicalexercise.R

enum class CardSet(val label: String, val serverId: String, val iconResId: Int) {
    BASIC("Basic", "Basic", R.drawable.set_basic),
    CLASSIC("Classic", "Classic", R.drawable.set_classic),
    CURSE_OF_NAXXRAMAS("Curse of Naxxramas", "Naxxramas", R.drawable.set_naxx),
    GOBLINS_VS_GNOMES("Goblins vs Gnomes", "Goblins vs Gnomes", R.drawable.set_gvg),
    BLACKROCK_MOUNTAIN("Blackrock Mountain", "Blackrock Mountain", R.drawable.set_brm),
    GRAND_TOURNAMENT("The Grand Tournament", "The Grand Tournament", R.drawable.set_tgt),
    LEAGUE_OF_EXPLORERS("The League of Explorers", "The League of Explorers", R.drawable.set_loe),
    WHISPERS_OF_THE_OLD_GODS("Whispers of the Old Gods", "Whispers of the Old Gods", R.drawable.set_og),
    ONE_NIGHT_IN_KARAZHAN("One Night in Karazhan", "One Night in Karazhan", R.drawable.set_kara),
    MEAN_STREETS_OF_GADGETZAN("Mean Streets of Gadgetzan", "Mean Streets of Gadgetzan", R.drawable.set_gadgetzan),
    JOURNEY_TO_UNGORO("Journey to Un\'Goro", "Journey to Un\'Goro", R.drawable.set_ungoro),
    HALL_OF_FAME("Hall of Fame", "Hall of Fame", R.drawable.set_hof),
    KNIGHTS_OF_THE_FROZEN_THRONE("Knights of the Frozen Throne", "Knights of the Frozen Throne", R.drawable.set_icc),
    KOBOLDS_AND_CATACOMBS("Kobolds and Catacombs", "Kobolds & Catacombs", R.drawable.set_loot),
    THE_WITCHWOOD("The Witchwood", "The Witchwood", R.drawable.set_wood),
    THE_BOOMSDAY_PROJECT("The Boomsday Project", "The Boomsday Project", R.drawable.set_boom),
    RASTAKANS_RUMBLE("Rastakhan\'s Rumble", "Rastakhan\'s Rumble", R.drawable.set_troll),
    RISE_OF_SHADOWS("Rise of Shadows", "Rise of Shadows", R.drawable.set_shadows),
    SAVIORS_OF_ULDUM("Saviors of Uldum", "Saviors of Uldum", R.drawable.set_saviors),
    DESCENT_OF_DRAGONS("Descent of Dragons", "Descent of Dragons", R.drawable.set_dragons),
    GALAKRONDS_AWAKENING("Galakrond\'s Awakening", "Galakrond\'s Awakening", R.drawable.set_galakrond),
    ASHES_OF_OUTLAND("Ashes of Outland", "Ashes of Outland", R.drawable.set_ashes),
    DEMON_HUNTER("Demon Hunter", "Demon Hunter initiate", R.drawable.set_demonhunter),
    SCHOLOMANCE_ACADEMY("Scholomance Academy", "Scholomance Academy", R.drawable.set_scholomancy),
    MADNESS_AT_THE_DARKMOON_FAIRE("Madness at the Darkmoon Faire", "Madness at the Darkmoon Faire", R.drawable.set_darkmoon),
    FORGED_IN_THE_BARRENS("Forged in the Barrens", "Forged in the Barrens", R.drawable.set_barrens),
    LEGACY("Legacy", "Legacy", R.drawable.set_classic),
    UNITED_IN_STORMWIND("United in Stormwind", "United in Stormwind", R.drawable.set_classic),
    FRACTURED_IN_ALTERAC_VALLEY("Fractured in Alterac Valley", "Fractured in Alterac Valley", R.drawable.set_classic),
    CAVERNS_OF_TIME("Caverns of Time", "Caverns of Time", R.drawable.set_classic),
    CORE("Core", "Core", R.drawable.set_classic),
    VOYAGE_TO_THE_SUNKEN_CITY("Voyage to the Sunken City", "Voyage to the Sunken City", R.drawable.set_classic),
    MURDER_AT_CASTLE_NATHRIA("Murder at Castle Nathria", "Murder at Castle Nathria", R.drawable.set_classic),
    PATH_OF_ARTHAS("Path of Arthas", "Path of Arthas", R.drawable.set_classic),
    MARCH_OF_THE_LICH_KING("March of the Lich King", "March of the Lich King", R.drawable.set_classic),
    FESTIVAL_OF_LEGENDS("Festival of Legends", "Festival of Legends", R.drawable.set_classic),
    TITANS("TITANS", "TITANS", R.drawable.set_classic);

    companion object {
        fun getSetFromCard(card: Card): CardSet {
            entries.forEach {
                if (it.serverId == card.cardSet) {
                    return it
                }
            }
            return LEGACY
        }
    }
}