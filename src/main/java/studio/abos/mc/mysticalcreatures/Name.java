package studio.abos.mc.mysticalcreatures;

public interface Name {
    String MODID = "mysticalcreatures";

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
}
