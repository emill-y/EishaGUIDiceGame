package Main;

public class Player {

    //instance variables
    private String playerName;
    public int playerCash;
    public int playerLevel;
    private UI ui;

    //constructor
    public Player(String playerName, int playerCash, int level, UI ui) {
        this.playerName = playerName;
        this.playerCash = playerCash;
        playerLevel = level;
        this.ui = ui;
    }

    //default constructor
    public Player(UI ui) {
        this.playerName = "Guest Player.";
        this.playerCash = 100;
        playerLevel = 1;
        this.ui = ui;
    }

    //starting game constructor
    public Player(String playerName, int level, UI ui) {
        this.ui = ui;
        if (playerName.length() < 1) {
            this.playerName = "Guest Player";
        }
        else {
            this.playerName = playerName;
        }
        this.playerCash = 100;
        playerLevel = level;
    }

    //Individual Methods

    //returns the players level
    public int playerLevel(){
        return playerLevel;
    }

    //returns the amount of cash the player has
    public int playersCash(){
        return playerCash;
    }

    //deposits money won by the player into their cash total
    public int winMoney(int moneyGained) {
        playerCash = playerCash + moneyGained;
        return playerCash;
    }

    //removes money earned by the player from their cash total
    public int loseMoney(int moneyLost) {
        playerCash = playerCash - moneyLost;
        return playerCash;
    }

    //checks if the player has won or lost the game
    public boolean stillPlaying() {

        boolean stillPlaying = false;

        if (playerCash > 0 && playerCash <= 1000) {
            stillPlaying = true;
        }

        return stillPlaying;
    }




























}
