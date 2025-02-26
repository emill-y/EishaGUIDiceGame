package Main;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import Event.Event01;


public class UI extends JFrame{

    // Declare Instance Variables
    private JFrame window;
    private GameManager gm;
    private JLayeredPane[] bgPanel = new JLayeredPane[10];
    private JLabel[] bgLabel = new JLabel[10];
    private int costume = 0;
    private Player player;
    private ActionHandler aHandler;
    private SceneChanger sChanger;
    private Event01 ev1;
    // Hash-Maps to keep track and access elements created.
    private Map<String, JLabel> objectMap;
    private Map<String, JButton> imageButtonMap;
    private Map<String, JTextArea> textAreaMap;
    private Map<String, JButton> buttonMap;
    // Text Boxes That Appear on Screen.
    private JTextArea resultsText;
    private JTextArea messageText;
    private JTextArea playerWallet;

    // UI Constructor
    public UI(GameManager gm){
        this.gm = gm;
    }

    public void init(){
        this.sChanger = new SceneChanger(gm);
        this.ev1 = new Event01(gm);
        this.aHandler = new ActionHandler(gm);
        gm.setsChanger(sChanger);
        gm.setaHandler(aHandler);
        gm.setEv1(ev1);
        this.player = new Player();
        this.objectMap = new HashMap<>();
        this.imageButtonMap = new HashMap<>();
        this.textAreaMap = new HashMap<>();
        this.buttonMap = new HashMap<>();
        createMainField();
        generateScene();
        window.setVisible(true);
    }

    // Getters and Setters to Access Instance Variables.
    public JTextArea getResultsText() {
        return resultsText;
    }

    public void setResultsText(String resultsText) {
        this.resultsText.setText(resultsText);
    }

    public JTextArea getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText.setText(messageText);
    }

    public JLayeredPane[] getBgPanel() {
        return bgPanel;
    }

    public void setCostume(int costume) {
        this.costume = costume;
    }

    public Player getPlayer() {
        return player;
    }

    public JTextArea getPlayerWallet() {
        return playerWallet;
    }

    public void setPlayerWallet(String playerWallet) {
        this.playerWallet.setText(playerWallet);
    }

    public int getCostume(){
        return costume;
    }

    // Change the Appearance of the Creature.
    public void changeCostume(){
        if (costume < 4) {
            costume += 1;
        }
        if (costume > 3) {
            // If reached maximum costume, take player to victory screen.
            gm.getsChanger().showScreen5();
        }
    }

    // Initialize and Create the Window.
    public void createMainField() {
        this.window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setTitle("Growing Up Dice!!");

    }

    // Create An individual Background Screen.
    public void createBackground(int bgNum, String bgFileName) {

        bgPanel[bgNum] = new JLayeredPane();
        bgPanel[bgNum].setBounds(50,50,700,450);
        bgPanel[bgNum].setBackground(Color.blue);
        bgPanel[bgNum].setLayout(null);
        window.add(bgPanel[bgNum]);

        bgLabel[bgNum] = new JLabel();
        bgLabel[bgNum].setBounds(0,0,700,450);

        ImageIcon bgIcon = new ImageIcon("res/" + bgFileName);
        bgLabel[bgNum].setIcon(bgIcon);
    }

    // Create Image Elements that Appear on Window.
    public void createObject(int bgNum, int objx, int objy, int objWidth, int objHeight, String objectFileName, String objectKey){
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(objx, objy, objWidth, objHeight);
        ImageIcon objectIcon = new ImageIcon("res/" + objectFileName);
        Image img = objectIcon.getImage();
        Image newImg = img.getScaledInstance( objWidth, objHeight,  Image.SCALE_SMOOTH ) ;
        objectIcon = new ImageIcon(newImg);
        objectLabel.setIcon(objectIcon);
        bgPanel[bgNum].add(objectLabel);
        bgPanel[bgNum].moveToFront(objectLabel);
        objectMap.put(objectKey, objectLabel);
    }

    // Allow Specific Objects to Hide and Reappear.
    public void setObjectVisibility(String objectKey, boolean visible) {
        JLabel obj = objectMap.get(objectKey);
        if (obj != null) {
            obj.setVisible(visible);
        }
        // Allow for Popup "Enter Bet" Functionality.
        if (objectKey.equals("popup") && visible) {
            bgPanel[3].moveToFront(obj);
            int keypadNumX = 260;
            int keypadNumY = 210;
            boolean isFirstTime = true;
            for (int i = 0; i < 10; i++)
            {
                if(i > 4 && isFirstTime) {
                    keypadNumY = 240;
                    keypadNumX = 260;
                    isFirstTime = false;
                }
                createButton(3,keypadNumX, keypadNumY , 20, 20, i+"", "number"+i);
                setButtonVisibility("number"+i, visible);
                keypadNumX +=40;
            }
            createButton(3,320,280, 80,20, "Now Roll The Die!", "enterBet");
            messageText = newJTextArea(320,150,80,20,Color.pink,"Enter Your Bet!");
            messageText.setVisible(visible);
            bgPanel[3].add(messageText);
            bgPanel[3].moveToFront(messageText);
            setButtonVisibility("enterBet", true);

        }
        else {
            if (messageText != null){
                messageText.setVisible(false);
            }
            for(int i = 0; i < 10; i++){
                setButtonVisibility("number"+i, false);
            }
            setButtonVisibility("enterBet", false);
        }
    }

    // Create A Button with an Image Icon.
    public void createImageButton(int bgNum, int x, int y, int width, int height, String arrowFileName, String command, String itemKey){
        ImageIcon arrowIcon = new ImageIcon("res/" + arrowFileName);
        Image img = arrowIcon.getImage();
        Image newImg = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH ) ;
        arrowIcon = new ImageIcon(newImg);
        JButton imgButton =  new JButton();
        imgButton.setBounds(x,y,width,height);
        imgButton.setBackground(null);
        imgButton.setContentAreaFilled(false);
        imgButton.setFocusPainted(false);
        imgButton.setIcon(arrowIcon);
        imgButton.addActionListener(aHandler);
        imgButton.setActionCommand(command);
        bgPanel[bgNum].add(imgButton);
        bgPanel[bgNum].moveToFront(imgButton);
        imageButtonMap.put(itemKey, imgButton);
    }

    // Allow Image Buttons to appear or disappear.
    public void setImageButtonVisibility(String itemKey, boolean visible) {
        JButton item = imageButtonMap.get(itemKey);
        if (item != null) {
            item.setVisible(visible);
        }
    }

    // Create a New Button with Text.
    public void createButton(int bgNum, int x, int y, int width, int height, String buttonText, String command){
        JButton button =  new JButton();
        button.setBounds(x,y,width,height);
        button.setBackground(null);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setText(buttonText);
        button.addActionListener(aHandler);
        button.setActionCommand(command);
        bgPanel[bgNum].add(button);
        bgPanel[bgNum].moveToFront(button);
        buttonMap.put(command, button);
    }

    // Allow Button to Appear or Disappear.
    public void setButtonVisibility(String buttonKey, boolean visible) {
        JButton button = buttonMap.get(buttonKey);
        if (button != null) {
            button.setVisible(visible);
        }
    }

    // Allow Text to Appear or Disappear.
    public void setTextVisibility(String textKey, boolean visible) {
        JTextArea textArea = textAreaMap.get(textKey);
        if (textArea != null) {
            textArea.setVisible(visible);
        }
    }

    // Create New Text Area on Screen.
    public JTextArea newJTextArea(int x, int y, int width, int height, Color background, String text){
        JTextArea textArea = new JTextArea(text);
        textArea.setBounds(x,y,width,height);
        textArea.setBackground(background);
        textArea.setForeground(Color.black);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Title Font", Font.PLAIN, 10));
        return textArea;
    }

    // Generate individual Scenes to Appear.
    public void generateScene(){

        // SCREEN 1 - Welcome Screen
        createBackground(1, "welcomeScreen.png");
        createButton(1, 270, 300, 200,45, "View Instructions!", "goScene2");
        createButton(1, 270, 250, 200,45, "Play Game NOW.", "goScene3");
        bgPanel[1].add(bgLabel[1]);

        // SCREEN 2 - Game Instructions
        createBackground(2, "Instructions.png");
        createButton(2, 270, 390, 200,45, "Play Game.", "goScene3");
        bgPanel[2].add(bgLabel[2]);

        // SCREEN 3 - Game Board
        createBackground(3,"gameBoard.png");
        createImageButton(3,500,50,60,60,"drink.png","drink", "drink");
        JTextArea drinkLabel = new JTextArea("$50");
        drinkLabel.setBounds(510,110,40,15);
        drinkLabel.setEditable(false);
        bgPanel[3].add(drinkLabel);
        textAreaMap.put("drink", drinkLabel);
        createImageButton(3,500,120,60,60,"tea.png","tea", "tea");
        JTextArea teaLabel = new JTextArea("$150");
        teaLabel.setBounds(510,180,40,15);
        teaLabel.setEditable(false);
        bgPanel[3].add(teaLabel);
        textAreaMap.put("tea", teaLabel);
        createImageButton(3,600,50,60,60,"pocky.png","pocky", "pocky");
        JTextArea pockyLabel = new JTextArea("$200");
        pockyLabel.setBounds(610,110,40,15);
        pockyLabel.setEditable(false);
        createImageButton(3,600,120,60,60,"meal.png","meal", "meal");
        bgPanel[3].add(pockyLabel);
        textAreaMap.put("pocky", pockyLabel);
        JTextArea mealLabel = new JTextArea("$300");
        mealLabel.setBounds(610,180,40,15);
        mealLabel.setEditable(false);
        bgPanel[3].add(mealLabel);
        textAreaMap.put("meal", mealLabel);
        createObject(3,290,150,150,150,"costume" + costume + ".png","creature");
        createButton(3, 100, 300, 75,45, "Place Bet. ", "rollDie");
        createObject(3, 200,120, 300,200, "popupScreen.png",  "popup");
        int heartX = 500;
        int heartY = 330;
        int heartNum = 0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 2; j++){
                createObject(3, heartX, heartY, 25, 25, "heart.png",  "heart"+heartNum);
                heartY += 30;
                heartNum++;
            }
            heartY = 330;
            heartX += 30;
        }
        playerWallet = newJTextArea(300,350,120,30,Color.lightGray, "You currently have \" + player.playersCash() + \" dollars right now");
        bgPanel[3].add(playerWallet);
        bgPanel[3].moveToFront(playerWallet);
        resultsText = newJTextArea(300,400,120,30, Color.lightGray, "Let's see if you won or lost!");
        bgPanel[3].add(resultsText);
        bgPanel[3].moveToFront(resultsText);
        setObjectVisibility("popup", false);
        bgPanel[3].add(bgLabel[3]);

        // SCREEN 4 - Losing Screen
        createBackground(4,"losingScreen.png");
        createButton(4,270, 390, 200,45, "Play Again!!", "goScene3");
        bgPanel[4].add(bgLabel[4]);

        // SCREEN 5 - Victory Screen
        createBackground(5, "victoryScreen.png");
        createButton(5, 270, 390, 200,45, "Play Again!!", "goScene3");
        bgPanel[5].add(bgLabel[5]);
    }
}
