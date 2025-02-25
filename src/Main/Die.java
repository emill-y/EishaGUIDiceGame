package Main;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Die
{
    private int bgnum;
    private UI ui;
    private GameManager gm;
    private int value;
    private String[] dieImages;
    private String[] dieGameImages;
    private int gameValue;
    private int playerValue;

    public Die(int bgnum, GameManager gm, UI ui){
        this.bgnum = bgnum;
        this.gm = gm;
        this.ui = ui;
        this.value = 0;
        this.dieImages = new String[6];
        for(int i = 0; i < 6; i++){
            dieImages[i] = (i+1) + ".png";
        }
        this.dieGameImages = new String[6];
        for(int i = 0; i < 6; i++){
            dieGameImages[i] = (i+1) + "Game.png";
        }
    }

    public int roll() {
        int roll = (int) (Math.random() * 6 + 1);
        this.value = roll;
        return roll;
    }

    public void rollDie(){
        ui.setObjectVisibility("die0", false);
        ui.setObjectVisibility("die1", false);


        int die_cornerx = (int) (Math.random() * 100) + 50;
        int die_cornery = (int) (Math.random() * 300) + 50;
        int Gamedie_cornerx = (int) (Math.random() * 100) + 50;
        int Gamedie_cornery = (int) (Math.random() * 300) + 50;
        ui.createObject(bgnum, die_cornerx, die_cornery, 25, 25, dieImages[roll()-1], "die", "die0");
        playerValue = value;
        ui.createObject(bgnum, Gamedie_cornerx, Gamedie_cornery, 25, 25, dieGameImages[roll()-1], "die", "die1");
        gameValue = value;
    }

    public boolean didPlayerWin(){
        return playerValue > gameValue;
    }
}