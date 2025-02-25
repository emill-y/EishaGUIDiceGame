package Main;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.text.JTextComponent;


import static javax.swing.SwingConstants.HORIZONTAL;

public class UI extends JFrame{

    private JFrame window;
    private GameManager gm;
    public JTextArea messageText;
    public JLayeredPane[] bgPanel = new JLayeredPane[10];
    public JLabel[] bgLabel = new JLabel[10];
    private int costume = 0;
    public Player player;
    public JTextArea playerWallet;

    public UI(GameManager gm){
        this.gm = gm;
        this.player = new Player(this);
        createMainField();
        generateScene();
        window.setVisible(true);
    }

    public int getCostume(){
        return costume;
    }

    public void changeCostume(){
        if (costume < 4) {
            costume += 1;
        }
        if (costume > 3) {
            gm.sChanger.showScreen5();
        }
    }

    public void createMainField() {
        this.window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setTitle("Growing Up Dice!!");

    }

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

    private Map<String, JLabel> objectMap = new HashMap<>();

    public void createObject(int bgNum, int objx, int objy, int objWidth, int objHeight, String objectFileName, String command, String objectKey){

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

    public void setObjectVisibility(String objectKey, boolean visible) {
        JLabel obj = objectMap.get(objectKey);
        if (obj != null) {
            obj.setVisible(visible);
        }
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
            createButton(3,320,280, 80,20, "Enter Bet.", "enterBet");
            messageText = new JTextArea("Enter Your Bet!");
            messageText.setVisible(visible);
            messageText.setBounds(320,150,80,20);
            messageText.setBackground(Color.pink);
            messageText.setForeground(Color.black);
            messageText.setEditable(false);
            messageText.setLineWrap(true);
            messageText.setWrapStyleWord(true);
            messageText.setFont(new Font("Title Font", Font.PLAIN, 10));
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

    private Map<String, JButton> imageButtonMap = new HashMap<>();

    public void createImageButton(int bgNum, int x, int y, int width, int height, String arrowFileName, String command, String itemKey){
        ImageIcon arrowIcon = new ImageIcon("res/" + arrowFileName);
        Image img = arrowIcon.getImage();
        Image newImg = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH ) ;
        arrowIcon = new ImageIcon(newImg);
        JButton arrowButton =  new JButton();
        arrowButton.setBounds(x,y,width,height);
        arrowButton.setBackground(null);
        arrowButton.setContentAreaFilled(false);
        arrowButton.setFocusPainted(false);
        arrowButton.setIcon(arrowIcon);
        arrowButton.addActionListener(gm.aHandler);
        arrowButton.setActionCommand(command);

        bgPanel[bgNum].add(arrowButton);
        bgPanel[bgNum].moveToFront(arrowButton);

        imageButtonMap.put(itemKey, arrowButton);
    }

    public void setImageButtonVisibility(String itemKey, boolean visible) {
        JButton item = imageButtonMap.get(itemKey);
        if (item != null) {
            item.setVisible(visible);
        }
    }

    private Map<String, JButton> buttonMap = new HashMap<>();

    public void createButton(int bgNum, int x, int y, int width, int height, String buttonText, String command){
        JButton button =  new JButton();
        button.setBounds(x,y,width,height);
        button.setBackground(null);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setText(buttonText);
        button.addActionListener(gm.aHandler);
        button.setActionCommand(command);
        bgPanel[bgNum].add(button);
        bgPanel[bgNum].moveToFront(button);
        buttonMap.put(command, button);

    }

    public void setButtonVisibility(String buttonKey, boolean visible) {
        JButton button = buttonMap.get(buttonKey);
        if (button != null) {
            button.setVisible(visible);
        }
    }

    public void setTextVisibility(String textKey, boolean visible) {
        JTextArea textArea = textAreaMap.get(textKey);
        if (textArea != null) {
            textArea.setVisible(visible);
        }
    }

    Map<String, JTextArea> textAreaMap = new HashMap<>();

    public void generateScene(){
        //SCREEN 1
        createBackground(1, "welcomeScreen.png");
        createButton(1, 270, 300, 200,45, "View Instructions!", "goScene2");
        createButton(1, 270, 250, 200,45, "Play Game NOW.", "goScene3");
        bgPanel[1].add(bgLabel[1]);

        //SCREEN 2
        createBackground(2, "Instructions.png");
        createButton(2, 270, 390, 200,45, "Play Game.", "goScene3");
        bgPanel[2].add(bgLabel[2]);

        //SCREEN 3
        createBackground(3,"gameBoard.png");
        createImageButton(3,500,50,60,60,"drink.png","drink", "drink");
        JTextArea drinkLabel = new JTextArea("$100");
        drinkLabel.setBounds(510,110,40,15);
        drinkLabel.setEditable(false);
        bgPanel[3].add(drinkLabel);
        textAreaMap.put("drink", drinkLabel);
        createImageButton(3,500,120,60,60,"tea.png","tea", "tea");
        JTextArea teaLabel = new JTextArea("$200");
        teaLabel.setBounds(510,180,40,15);
        teaLabel.setEditable(false);
        bgPanel[3].add(teaLabel);
        textAreaMap.put("tea", teaLabel);
        createImageButton(3,600,50,60,60,"pocky.png","pocky", "pocky");
        JTextArea pockyLabel = new JTextArea("$500");
        pockyLabel.setBounds(610,110,40,15);
        pockyLabel.setEditable(false);
        createImageButton(3,600,120,60,60,"meal.png","meal", "meal");
        bgPanel[3].add(pockyLabel);
        textAreaMap.put("pocky", pockyLabel);
        JTextArea mealLabel = new JTextArea("$1000");
        mealLabel.setBounds(610,180,40,15);
        mealLabel.setEditable(false);
        bgPanel[3].add(mealLabel);
        textAreaMap.put("meal", mealLabel);
        createObject(3,290,150,150,150,"costume" + costume + ".png","", "creature");
        createButton(3, 100, 300, 75,45, "Roll Die.", "rollDie");
        createObject(3, 200,120, 300,200, "popupScreen.png", "popup", "popup");
        int heartX = 500;
        int heartY = 330;
        int heartNum = 0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 2; j++){
                createObject(3, heartX, heartY, 25, 25, "heart.png", "heart", "heart"+heartNum);
                heartY += 30;
                heartNum++;
            }
            heartY = 330;
            heartX += 30;
        }
        playerWallet = new JTextArea("You currently have " + player.playersCash() + " dollars right now!");
        playerWallet.setBounds(320,350,80,40);
        playerWallet.setBackground(Color.pink);
        playerWallet.setForeground(Color.black);
        playerWallet.setEditable(false);
        playerWallet.setLineWrap(true);
        playerWallet.setWrapStyleWord(true);
        playerWallet.setFont(new Font("Title Font", Font.PLAIN, 10));
        bgPanel[3].add(playerWallet);
        bgPanel[3].moveToFront(playerWallet);
        setObjectVisibility("popup", false);
        bgPanel[3].add(bgLabel[3]);

        //SCREEN 4
        createBackground(4,"losingScreen.png");
        createButton(4,270, 390, 200,45, "Play Again!!", "goScene3");
        bgPanel[4].add(bgLabel[4]);

        //SCREEN 5
        createBackground(5, "victoryScreen.png");
        createButton(5, 270, 390, 200,45, "Play Again!!", "goScene3");
        bgPanel[5].add(bgLabel[5]);
    }
}
