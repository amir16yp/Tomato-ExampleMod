package mymod;

import game.*;
import game.entities.enemy.Zombie;
import game.input.KeybindRegistry;
import game.ui.Button;
import game.ui.Menu;
import game.ui.UIElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.rmi.registry.Registry;
import java.security.Key;

public class TestMod implements Mod
{

    private final Logger logger = new Logger(this.getClass().getName());
    public static ResourceLoader resourceLoader = new ModResourceLoader();
    private final ModMenu modMenu = new ModMenu();
    private final Menu[] menus = new Menu[] {modMenu};

    @Override
    public void init() {
        Game.instance.setTitle("Tomato - modded");
        logger.Log("Init mod!");
        Menu mainMenu = Screen.menus[0];
        Button lastButton = mainMenu.getButtons().getLast();
        int newButtonY = lastButton.getY() + 60;
        int newButtonX = lastButton.getX();

        Button fartMenuButton=  new Button(newButtonX, newButtonY, lastButton.getWidth(), lastButton.getHeight(), "Modded Menu");
        fartMenuButton.setOnSelectedAction(() -> {
            Screen.setCurrentMenu(modMenu);
        });

        Screen.menus[0].getButtons().add(fartMenuButton);

        KeybindRegistry.registry.registerKeyPressedAction(KeyEvent.VK_DELETE, () -> {
            Scene scene = Screen.getCurrentScene();
            Tile randomTile = scene.getRandomTileInMap();
            scene.setTile(randomTile, 4, -1);
        });

        for (Scene scene : Screen.scenes)
        {
            scene.lighting.setDarkness(0.65f);
            scene.lighting.addLightSource(320, 320, 32 * 10);
            scene.lighting.setPlayerSource(32 * 6, false);
        }

    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawString("modded", 0, Game.INTERNAL_HEIGHT - 50);
    }

    @Override
    public void update() {

    }

    @Override
    public Menu[] getMenus() {
        return this.menus;
    }

}
