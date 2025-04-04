package studio.abos.mc.mysticalcreatures;

public interface Name {
    // misc
    String MODID = "mysticalcreatures";
    String _LONG = "_long";
    String _STRONG = "_strong";

    // entities
    String PHOENIX = "phoenix";
    String JACKALOPE = "jackalope";
    String UNICORN = "unicorn";
    String TROLL = "troll";

    // items
    String PHOENIX_FEATHER = PHOENIX + "_feather";
    String JACKALOPE_ANTLERS = JACKALOPE + "_antlers";
    String UNICORN_HORN = UNICORN + "_horn";
    String TROLL_HEART = TROLL + "_heart";

    // advancements
    String ADV_ROOT = "root";
    String ADV_BREEDER = "breeder";
    String ADV_COLLECTOR = "collector";
    String ADV_HUNTER = "hunter";
    String ADV_ALCHEMIST = "alchemist";

    // advancement criteria
    String PICKUP_ = "pickup_";
    String PICKUP_PHOENIX_FEATHER = PICKUP_ + PHOENIX_FEATHER;
    String PICKUP_JACKALOPE_ANTLERS = PICKUP_ + JACKALOPE_ANTLERS;
    String PICKUP_UNICORN_HORN = PICKUP_ + UNICORN_HORN;
    String PICKUP_TROLL_HEART = PICKUP_ + TROLL_HEART;
    String BREED_ = "breed_";
    String BREED_PHOENIX = BREED_ + PHOENIX;
    String BREED_JACKALOPE = BREED_ + JACKALOPE;
    String BREED_UNICORN = BREED_ + UNICORN;
    String BREED_TROLL = BREED_ + TROLL;
    String KILL_ = "kill_";
    String KILL_PHOENIX = KILL_ + PHOENIX;
    String KILL_JACKALOPE = KILL_ + JACKALOPE;
    String KILL_UNICORN = KILL_ + UNICORN;
    String KILL_TROLL = KILL_ + TROLL;
    String BREWED_ = "brewed_";
    String BREWED_PHOENIX = BREWED_ + PHOENIX;
    String BREWED_PHOENIX_LONG = BREWED_ + PHOENIX + _LONG;
    String BREWED_PHOENIX_STRONG = BREWED_ + PHOENIX + _STRONG;
    String BREWED_JACKALOPE = BREWED_ + JACKALOPE;
    String BREWED_JACKALOPE_LONG = BREWED_ + JACKALOPE + _LONG;
    String BREWED_JACKALOPE_STRONG = BREWED_ + JACKALOPE + _STRONG;
    String BREWED_UNICORN = BREWED_ + UNICORN;
    String BREWED_TROLL = BREWED_ + TROLL;
    String BREWED_TROLL_LONG = BREWED_ + TROLL + _LONG;
    String BREWED_TROLL_STRONG = BREWED_ + TROLL + _STRONG;
    String HURT_BY_ = "hurt_by_";
    String HURT_BY_UNICORN = HURT_BY_ + UNICORN;
    String HURT_BY_TROLL = HURT_BY_ + TROLL;

    // loot table modifiers
    String LM_SIMPLE = "simple_loot_modifier";
    String LM_WITCH = "witch_loot_modifier";
    String LM_BASTION = "bastion_loot_modifier";
    String LM_OUTPOST = "outpost_loot_modifier";
    String LM_MANSION = "mansion_loot_modifier";
    String LM_ANCIENT_CITY = "ancient_city_loot_modifier";
}
