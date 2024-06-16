package mymod;

import game.*;
import game.entities.NPC;
import game.entities.enemy.Zombie;
import game.input.KeybindRegistry;
import game.items.Gun;
import game.ui.Button;
import game.ui.Menu;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TestMod implements Mod
{

    private final Logger logger = new Logger(this.getClass().getName());
    public static ResourceLoader resourceLoader = new ModResourceLoader();
    private final FartMenu fartMenu = new FartMenu();
    private final Menu[] menus = new Menu[] {fartMenu};

    @Override
    public void init() {
        Game.instance.setTitle("Tomato - modded");
        logger.Log("Init mod!");

        Menu mainMenu = Screen.menus[0];

        Button lastButton = mainMenu.getButtons().getLast();
        int newButtonY = lastButton.getY() + 60;
        int newButtonX = lastButton.getX();

        Button fartMenuButton=  new Button(newButtonX, newButtonY, lastButton.getWidth(), lastButton.getHeight(), "FART MENU");
        fartMenuButton.setOnSelectedAction(() -> {
            Screen.setCurrentMenu(fartMenu);
        });

        Screen.menus[0].getButtons().add(fartMenuButton);

        KeybindRegistry.registry.registerKeyPressedAction(KeyEvent.VK_Z, () -> {
            Scene scene = Screen.getCurrentScene();
            int[] coords = scene.getRandomCoordsInMap();
            scene.spawnEntity(new Zombie(), coords[0], coords[1]);
        });

    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawString("modded", 0, 500);
    }

    @Override
    public void update() {

    }

    @Override
    public Menu[] getMenus() {
        return this.menus;
    }

}
