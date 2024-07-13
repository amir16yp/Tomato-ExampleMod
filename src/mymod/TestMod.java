package mymod;

import game.*;
import game.entities.player.PlayerEntity;
import game.ui.Button;
import game.ui.Menu;

import java.awt.*;

public class TestMod implements Mod
{

    private final Logger logger = new Logger(this.getClass().getName());
    public static ResourceLoader resourceLoader = new ModResourceLoader();
    private final Menu modMenu = new Menu();
    private final Menu[] menus = new Menu[] {modMenu};

    public float minMaxDarkness(float darkness)
    {
        if (darkness > 1.0f) {darkness = 1.0f;}
        if (darkness < 0.0f) {darkness = 0.0f;}
        return darkness;
    }

    @Override
    public void init() {
        Game.instance.setTitle("Tomato - Modded");
        Screen.menus.get(0).addButton("MOD MENU", () -> {
            Screen.setCurrentMenu(modMenu);
        });
        modMenu.addButton("GIVE FLASHLIGHT", () -> {
            PlayerEntity.inventory.addItem(new Flashlight(60));
        });

        modMenu.addButton("Add darkness", () -> {
            Lighting light = Screen.getCurrentScene().lighting;
            float darkness = light.getDarkness();
            darkness+=0.1f;
            light.setDarkness(minMaxDarkness(darkness));
            logger.Log(Utils.ConsoleColors.RED + "DARKNESS SET TO " + darkness);
        });

        modMenu.addButton("Lower darkness", () -> {
            Lighting light = Screen.getCurrentScene().lighting;
            float darkness = light.getDarkness();
            darkness-=0.1f;
            light.setDarkness(minMaxDarkness(darkness));
            logger.Log(Utils.ConsoleColors.RED + "DARKNESS SET TO " + darkness);
        });

        modMenu.addButton("BACK", () -> {
            Screen.setCurrentMenu(Screen.menus.get(0));
        });

        Screen.menus.add(modMenu);

        for (Menu menu : Screen.menus)
        {
            for (Button btn : menu.getButtons())
            {
                btn.setBackgroundColor(fromRGB(230, 57, 70));
                btn.setHighlightColor(fromRGB(247, 127, 0));
                btn.setTextColor(fromRGB(255, 255, 255));
            }
        }
        Game.screen.setBackground(fromRGB(250, 243, 224));
        logger.Log(Utils.ConsoleColors.GREEN + "EXAMPLE-MOD INIT COMPLETE");
    }

    public Color fromRGB(int r, int g, int b) {
        float[] hsbVals = Color.RGBtoHSB(r, g, b, null);
        return Color.getHSBColor(hsbVals[0], hsbVals[1], hsbVals[2]);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawString("modded", Game.INTERNAL_WIDTH - graphics.getFontMetrics().stringWidth("modded"), Game.INTERNAL_HEIGHT - 50);
    }

    @Override
    public void update() {

    }

    @Override
    public Menu[] getMenus() {
        return this.menus;
    }

    public static void main(String[] args)
    {
        System.out.println("This is a mod for the Tomato engine. put it the `mods` directory in the same directory as `Tomato.jar`.");
    }

}
