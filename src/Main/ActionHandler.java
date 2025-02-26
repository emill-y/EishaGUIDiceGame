package Main;

import Event.Event01;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener {

    private GameManager gm;
    private SceneChanger sChanger;
    private Event01 ev1;

    public ActionHandler(GameManager gm){
        this.gm = gm;
    }

    public void init(){
        this.sChanger = gm.getsChanger();
        this.ev1 = gm.getEv1();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String yourChoice = e.getActionCommand();
        // Stores Command For The Number Keyboard Input.
        String tempCommand = "";
        if (yourChoice.contains("number")) {
            tempCommand = yourChoice;
            yourChoice = "number";
        }
        switch(yourChoice) {

            // Switch Scenes.
            case "goScene1": sChanger.showScreen1(); break;
            case "goScene2": sChanger.showScreen2(); break;
            case "goScene3": ev1.playGame(); break;
            case "goScene4": sChanger.showScreen4(); break;
            case "goScene5": sChanger.showScreen5(); break;
            // Perform Button Actions for Game.
            case "number": ev1.handleBet(tempCommand); break;
            case "enterBet": ev1.submitBet(); break;
            case "rollDie": ev1.rollDie(); break;
            // Perform Item Actions.
            case "drink": ev1.drink(); break;
            case "tea": ev1.tea(); break;
            case "pocky": ev1.pocky(); break;
            case "meal": ev1.meal(); break;
            // Default Value.
            default:
                throw new IllegalStateException("Unexpected value: " + yourChoice);
        }
    }
}
