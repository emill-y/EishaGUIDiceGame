package Event;

import Main.Die;
import Main.GameManager;
import Main.UI;

import javax.swing.*;


public class Event01 {

    private GameManager gm;
    private UI ui;
    private int rollNum;

    public Event01(GameManager gm){
        this.gm = gm;
        this.ui = gm.ui;
        this.rollNum = 0;
    }
    public void rollDie() {
        ui.setObjectVisibility("popup", true);
    }
    public void changeCostume(){
        ui.setObjectVisibility("creature", false);
        ui.changeCostume();
        ui.createObject(3,290,150,150,150,"costume" + ui.getCostume() + ".png","", "creature");
    }

    String betInput = "";
    public void handleBet(String yourChoice){
        String num = yourChoice.substring(6);
//        if(num > ui.player.playerCash){
//            ui.messageText.s
//        }
        betInput = betInput + num;
        ui.messageText.setText(betInput.toString());
        rollNum +=1;
        if (rollNum > 4){
            ui.player.playerLevel +=1;
        }
    }

    public void submitBet(){
        ui.setObjectVisibility("popup", false);
        Die die = new Die(3,gm, ui);
        for(int i = 0; i < 10; i++){
            ui.setButtonVisibility(""+i, true);
        }
        die.rollDie();
        if (die.didPlayerWin()){
            ui.player.winMoney(Integer.parseInt(ui.messageText.getText()));
        }
        else {
            ui.player.loseMoney(Integer.parseInt(ui.messageText.getText()));
        }
        ui.playerWallet.setText("You currently have " + ui.player.playersCash() + " to spend!");
        ui.messageText.setText("");
        betInput = "";
    }
}
