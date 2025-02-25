package Event;

import Main.Die;
import Main.GameManager;
import Main.UI;

import javax.swing.*;


public class Event01 {

    private GameManager gm;
    private UI ui;
    private int rollNum;
    private int heartNum;

    public Event01(GameManager gm){
        this.gm = gm;
        this.ui = gm.ui;
        this.rollNum = 0;
    }

    public void playGame(){
        ui.player.playerCash = 100;
        heartNum = 0;
        ui.playerWallet.setText("You currently have " + ui.player.playerCash + " to spend!");
        for(int i = 0; i < 10; i++){
            ui.setObjectVisibility("heart"+i, true);
        }
        ui.setImageButtonVisibility("drink", true);
        ui.setImageButtonVisibility("tea", true);
        ui.setImageButtonVisibility("pocky", true);
        ui.setImageButtonVisibility("meal", true);
        gm.sChanger.showScreen3();
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
        betInput = betInput + num;
        ui.messageText.setText(betInput);
        rollNum +=1;
        if (rollNum > 4){
            ui.player.playerLevel +=1;
        }
    }

    public void submitBet(){
        if (Integer.parseInt(ui.messageText.getText()) > ui.player.playersCash()){
            ui.playerWallet.setText("Heyy ur spending money u dont have..");
            ui.messageText.setText("");
            betInput = "";
        }
        else if (Integer.parseInt(ui.messageText.getText()) == 0){
            ui.playerWallet.setText("C'mon u can't just bet nothing!");
            ui.messageText.setText("");
            betInput = "";
        }
        else {
        ui.setObjectVisibility("popup", false);
        Die die = new Die(3,gm, ui);
        for(int i = 0; i < 10; i++){
            ui.setButtonVisibility(""+i, true);
        }
        die.rollDie();
        ui.setObjectVisibility("heart"+(9-heartNum), false);
        heartNum++;
        if (die.didPlayerWin()){
            ui.player.winMoney(Integer.parseInt(ui.messageText.getText()));
        }
        else {
            ui.player.loseMoney(Integer.parseInt(ui.messageText.getText()));
        }
        ui.playerWallet.setText("You currently have " + ui.player.playersCash() + " to spend!");
        ui.messageText.setText("");
        betInput = "";
        if (ui.player.playersCash() < 1 || heartNum == 9){
            gm.sChanger.showScreen4();
        }
        }
    }

    public void buy(int cost, String itemKey){
        if(ui.player.playersCash() > cost) {
            changeCostume();
            ui.player.loseMoney(cost);
            ui.playerWallet.setText("You currently have " + ui.player.playersCash() + " to spend!");
            ui.setImageButtonVisibility(itemKey, false);
            ui.setTextVisibility(itemKey, false);
        }
        else {
            ui.playerWallet.setText("Woah, ur broke, you can't afford that!");
        }
    }
    public void drink(){
        buy(100, "drink");
    }
    public void tea(){
        buy(200, "tea");
    }
    public void pocky(){
        buy(500, "pocky");
    }
    public void meal(){
        buy(1000, "meal");
    }

}
