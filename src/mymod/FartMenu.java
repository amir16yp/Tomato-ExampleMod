package mymod;

import game.Game;
import game.Screen;
import game.entities.player.PlayerEntity;
import game.ui.Button;
import game.ui.Menu;

import java.util.List;

public class FartMenu extends Menu {
    public FartMenu() {
        super(0, 0, Game.WIDTH, Game.HEIGHT);
    }

    @Override
    public void initializeButtons() {
        super.initializeButtons();

        List<Button> buttonList = getButtons();
        int startX = this.startX();
        int startY = 32;

        Button backButton = new Button(startX, startY, this.buttonWidth, this.buttonHeight, "Back");
        backButton.setOnSelectedAction(() -> {
            Screen.setCurrentMenu(Screen.menus[0]);
        });

        Button clearInventoryBtn = new Button(startX, startY + spacing, this.buttonWidth, this.buttonHeight, "Clear inventory");
        clearInventoryBtn.setOnSelectedAction(() -> {
            for (int i = 0; PlayerEntity.inventory.items.length > i; i++)
            {
                PlayerEntity.inventory.clearSlots();
            }
        });

        buttonList.add(backButton);
        buttonList.add(clearInventoryBtn);

    }
}
