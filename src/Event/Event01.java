package Event;

import Main.Die;
import Main.GameManager;
import Main.Player;
import Main.UI;

import java.awt.*;

// Manages Different Events triggered from Action Handler.
public class Event01 {

    // Initialize Instance Variables.
    private GameManager gm;
    private UI ui;
    private int heartNum;
    private Player player;
    private String betInput;
    private static final int DRINK_COST = 50,
                            TEA_COST = 150,
                            POCKY_COST = 200,
                            MEAL_COST = 500;

    public Event01(GameManager gm){
        this.gm = gm;
    }

    public void init(){
        this.ui = gm.getUi();
        this.player = ui.getPlayer();
        this.betInput = "";
    }

    // Re-initializes game when started.
    public void playGame(){
        // Sets Game Variables to Default Values.
        player.setPlayerCash(100);
        heartNum = 0;
        ui.setCostume(0);
        ui.getPlayerWallet().setText("You currently have " + player.playersCash() + " to spend!");
        ui.setTextVisibility("drink", true);
        ui.setTextVisibility("tea", true);
        ui.setTextVisibility("pocky", true);
        ui.setTextVisibility("meal", true);
        for(int i = 0; i < 10; i++){
            ui.setObjectVisibility("heart"+i, true);
        }
        ui.setImageButtonVisibility("drink", true);
        ui.setImageButtonVisibility("tea", true);
        ui.setImageButtonVisibility("pocky", true);
        ui.setImageButtonVisibility("meal", true);
        // Go to Game Screen.
        gm.getsChanger().showScreen3();
    }

    public void rollDie() {
        // Display Popup Window to Enter Bet.
        ui.setObjectVisibility("popup", true);
    }

    public void changeCostume(){
        // Advance the Creatures Appearance.
        ui.setObjectVisibility("creature", false);
        ui.changeCostume();
        ui.createObject(3,290,150,150,150,"costume" + ui.getCostume() + ".png", "creature");
    }

    public void handleBet(String yourChoice){
        // Display Bet inputted by number keypad press.
        String num = yourChoice.substring(6);
        betInput = betInput + num;
        ui.setMessageText(betInput);
    }

    public void submitBet(){
        // Validate Bet.
        if (Integer.parseInt(ui.getMessageText().getText()) > player.playersCash()){
            ui.setPlayerWallet("Heyy ur spending money u dont have..");
            ui.setMessageText("");
            betInput = "";
        }
        else if (Integer.parseInt(ui.getMessageText().getText()) == 0){
            ui.setPlayerWallet("C'mon u can't just bet nothing!");
            ui.setMessageText("");
            betInput = "";
        }
        else {
            // Remove Popup And Roll Die
            ui.setObjectVisibility("popup", false);
            Die die = new Die(3, ui);
            for(int i = 0; i < 10; i++){
                ui.setButtonVisibility(""+i, true);
            }
            die.rollDie();
            // Remove a Heart Because of Turn Taken
            ui.setObjectVisibility("heart"+(9-heartNum), false);
            heartNum++;
            // Determine if player won or lost turn, and iterate wallet.
            if (die.didPlayerWin()){
                player.winMoney(Integer.parseInt(ui.getMessageText().getText()));
                ui.setResultsText("You just won " + Integer.parseInt(ui.getMessageText().getText()) + " dollars!!");
                ui.getResultsText().setBackground(Color.green);
            }
            else {
                player.loseMoney(Integer.parseInt(ui.getMessageText().getText()));
                ui.setResultsText("You just LOST " + Integer.parseInt(ui.getMessageText().getText()) + " dollars:((");
                ui.getResultsText().setBackground(Color.pink);
            }
            // Display and clear results for next turn.
            ui.setPlayerWallet("You currently have " + player.playersCash() + " to spend!");
            ui.setMessageText("");
            betInput = "";
            // If player is broke or out of lives, show loss screen.
            if (player.playersCash() < 1 || heartNum == 9){
                gm.getsChanger().showScreen4();
            }
        }
    }

    // Allow Player to buy items.
    public void buy(int cost, String itemKey){
        // Only Allow User To buy item if they can afford it.
        if(player.playersCash() > cost) {
            changeCostume();
            player.loseMoney(cost);
            ui.setPlayerWallet("You currently have " + player.playersCash() + " to spend!");
            ui.setImageButtonVisibility(itemKey, false);
            ui.setTextVisibility(itemKey, false);
        }
        else {
            ui.setPlayerWallet("Woah, ur broke, you can't afford that!");
        }
    }

    // Specific Buy Functions Based On Item
    public void drink(){
        buy(DRINK_COST, "drink");
    }
    public void tea(){
        buy(TEA_COST, "tea");
    }
    public void pocky(){
        buy(POCKY_COST, "pocky");
    }
    public void meal(){
        buy(MEAL_COST, "meal");
    }

}
