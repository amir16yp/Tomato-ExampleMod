package mymod;

import tomato.Sprite;
import tomato.SpriteFactory;
import tomato.entities.player.PlayerEntity;
import tomato.items.PickupItem;
import tomato.registry.SoundClip;
import tomato.registry.SoundRegistry;

public class Rock extends PickupItem {

    private static final Sprite rock = SpriteFactory.getSprite("/mymod/rock.png", 16, 16, 1000, TestMod.resourceLoader);
    private static final int maxUsages = 100;
    private static final long coolDownTimeMS = 500;
    private static final int speed = 5;
    private static final int damage = 15;
    private static final SoundClip soundEffect = SoundRegistry.registry.getSound("weapons", "gun1");
    public Rock() {
        super(rock, maxUsages, coolDownTimeMS);
    }

    @Override
    public void onRunOut() {
        super.onRunOut();

    }

    @Override
    public void use() {
        super.use();
        PlayerEntity player = PlayerEntity.getPlayer();
        player.shootProjectile(rock, speed, damage);
        if (soundEffect != null)
        {
            soundEffect.play(true); // true for cancelling the previous sound when a new sound is played, makes an item with short cooldown time in ms more smooth
        }
    }
}
