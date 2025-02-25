package Event;

import Main.Die;
import Main.GameManager;
import Main.UI;

import javax.swing.*;


public class Event01 {

    private GameManager gm;
    private UI ui;

    public Event01(GameManager gm){
        this.gm = gm;
        this.ui = gm.ui;
    }
    public void rollDie() {
        Die die = new Die(3,gm, ui);
        for(int i = 0; i < 10; i++){
            ui.setButtonVisibility("keypadNum", true);
        }
        ui.setObjectVisibility("popup", true);
        die.rollDie();
    }
    public void changeCostume(){
        ui.setObjectVisibility("creature", false);
        ui.changeCostume();
        ui.createObject(3,290,150,150,150,"costume" + ui.getCostume() + ".png","", "creature");
    }

}
