package Main;

public class Player {

    public void setPlayerCash(int playerCash) {
        this.playerCash = playerCash;
    }

    //instance variables
    private int playerCash;

    //default constructor
    public Player() {
        this.playerCash = 100;
    }

    //Individual Methods
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

}
