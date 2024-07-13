package mymod;

import game.Screen;
import game.Sprite;
import game.entities.player.PlayerEntity;
import game.items.PickupItem;

public class Flashlight extends PickupItem {
    private long startTimeMillis; // Start time in milliseconds
    private static final Sprite sprite = new Sprite("/mymod/fart.png", 16, 16, 1000, TestMod.resourceLoader);
    public Flashlight(int durationSeconds) {
        super(sprite, 4, durationSeconds * 1000);
    }

    @Override
    public void use() {
        super.use();
        this.startTimeMillis = System.currentTimeMillis();
        Screen.getCurrentScene().lighting.setPlayerSource(32 * 7, true, 1.0f);
    }

    @Override
    public void update() {
        super.update();
        long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
        float remainingTime = (this.cooldownTime - elapsedTimeMillis);
        if (remainingTime <= 0) {
            Screen.getCurrentScene().lighting.setPlayerSource(32 * 10, false, 1.0f);
        }
    }

    @Override
    public void onRunOut() {
        super.onRunOut();
        PlayerEntity.inventory.removeItem(this);
    }
}
