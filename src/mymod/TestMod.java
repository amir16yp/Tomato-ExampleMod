package mymod;

import tomato.*;
import tomato.entities.player.PlayerEntity;
import tomato.registry.KeybindRegistry;
import tomato.ui.Menu;

import java.awt.*;
import java.util.ArrayList;

public class TestMod implements Mod
{

    private final Logger logger = new Logger(this.getClass().getName());
    public static ResourceLoader resourceLoader = new ModResourceLoader();
    private final Menu modMenu = new Menu("Mod");

    @Override
    public void init() {
        Game.instance.setTitle("Tomato - Modded");

        modMenu.addButton("Click to fart", () -> {
            PlayerEntity.getActionDialogue().setMessage("you farted");
        });


        ArrayList<Menu> menus = Screen.mainMenu.getChildMenus();
        for (Menu menu : menus)
        {
            logger.Log("Found menu in Main Menu: " + menu.getLabel());
            // you can add a child menu to one of the existing menus if you want.
            // menu.addChildMenu(modMenu); // example
        }
        Screen.mainMenu.addChildMenu(modMenu);

    }

    @Override
    public void draw(Graphics graphics) {
        FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.drawString("modded", Game.INTERNAL_WIDTH - fontMetrics.stringWidth("modded"), fontMetrics.getHeight());
    }

    @Override
    public void update() {

    }


    public static void main(String[] args)
    {
        System.out.println("This is a mod for the Tomato engine. put it the `mods` directory in the same directory as `Tomato.jar`.");
    }

}
