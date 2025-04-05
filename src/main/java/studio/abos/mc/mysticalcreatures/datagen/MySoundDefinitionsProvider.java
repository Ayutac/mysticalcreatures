package studio.abos.mc.mysticalcreatures.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;
import studio.abos.mc.mysticalcreatures.Name;

public class MySoundDefinitionsProvider extends SoundDefinitionsProvider {
    public MySoundDefinitionsProvider(PackOutput output) {
        super(output, Name.MODID);
    }

    @Override
    public void registerSounds() {
        // jackalope
        add(MysticalCreatures.JACKALOPE_AMBIENT_SOUND.value(), SoundDefinition.definition().with(
                sound("mob/rabbit/idle1", 0.25f, 1.2f),
                sound("mob/rabbit/idle2", 0.25f, 1.2f),
                sound("mob/rabbit/idle3", 0.25f, 1.2f),
                sound("mob/rabbit/idle4", 0.25f, 1.2f)).subtitle(Name.ST_JACKALOPE_AMBIENT));
        add(MysticalCreatures.JACKALOPE_ATTACK_SOUND.value(), SoundDefinition.definition().with(
                sound("entity/rabbit/attack1", 1f, 1.2f),
                sound("entity/rabbit/attack2", 1f, 1.2f),
                sound("entity/rabbit/attack3", 1f, 1.2f),
                sound("entity/rabbit/attack4", 1f, 1.2f)).subtitle(Name.ST_JACKALOPE_ATTACK));
        add(MysticalCreatures.JACKALOPE_DEATH_SOUND.value(), SoundDefinition.definition().with(
                sound("mob/rabbit/bunnymurder", 0.5f, 1.2f)));
        add(MysticalCreatures.JACKALOPE_HURT_SOUND.value(), SoundDefinition.definition().with(
                sound("mob/rabbit/hurt1", 0.5f, 1.2f),
                sound("mob/rabbit/hurt2", 0.5f, 1.2f),
                sound("mob/rabbit/hurt3", 0.5f, 1.2f),
                sound("mob/rabbit/hurt4", 0.5f, 1.2f)).subtitle(Name.ST_JACKALOPE_HURT));
        add(MysticalCreatures.JACKALOPE_JUMP_SOUND.value(), SoundDefinition.definition().with(
                sound("mob/rabbit/hop1", 0.1f, 0.96f),
                sound("mob/rabbit/hop2", 0.1f, 0.96f),
                sound("mob/rabbit/hop3", 0.1f, 0.96f),
                sound("mob/rabbit/hop4", 0.1f, 0.96f)).subtitle(Name.ST_JACKALOPE_JUMP));
    }

    private SoundDefinition.Sound sound(String name, float volume, float pitch) {
        return sound(name).volume(volume).pitch(pitch).stream().preload();
    }
}
