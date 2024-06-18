package mymod;

import game.Screen;
import game.Sprite;
import game.entities.player.PlayerEntity;
import game.items.PickupItem;

public class Flashlight extends PickupItem {
    private long startTimeMillis; // Start time in milliseconds
    public Flashlight(int durationSeconds) {
        super(new Sprite("/mymod/fart.png", 16, 16, 1000, TestMod.resourceLoader), 4, durationSeconds * 1000);
    }

    @Override
    public void use() {
        super.use();
        this.startTimeMillis = System.currentTimeMillis();
        Screen.getCurrentScene().lighting.setPlayerSource(32 * 7, true);
    }

    @Override
    public void update() {
        super.update();
        long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
        float remainingTime = (this.cooldownTime - elapsedTimeMillis);
        if (remainingTime <= 0) {
            Screen.getCurrentScene().lighting.setPlayerSource(32 * 10, false);
        }
    }

    @Override
    public void onRunOut() {
        super.onRunOut();
        Screen.getCurrentScene().lighting.setDarkness(0.9f);
        PlayerEntity.inventory.removeItem(this);
    }
}
