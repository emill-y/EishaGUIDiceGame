package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener {

    private GameManager gm;

    public ActionHandler(GameManager gm){
        this.gm = gm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String yourChoice = e.getActionCommand();
        String tempCommand = "";
        if (yourChoice.indexOf("number") > -1) {
            tempCommand = yourChoice;
            yourChoice = "number";
        }
        switch(yourChoice) {
            case "rollDie": gm.ev1.rollDie(); break;
            //Switch Scenes
            case "goScene1": gm.sChanger.showScreen1(); break;
            case "goScene2": gm.sChanger.showScreen2(); break;
            case "goScene3": gm.ev1.playGame(); break;
            case "goScene4": gm.sChanger.showScreen4(); break;
            case "goScene5": gm.sChanger.showScreen5(); break;
            case "changeCostume": gm.ev1.changeCostume(); break;
            case "number": gm.ev1.handleBet(tempCommand); break;
            case "enterBet": gm.ev1.submitBet(); break;
            case "drink": gm.ev1.drink(); break;
            case "tea": gm.ev1.tea(); break;
            case "pocky": gm.ev1.pocky(); break;
            case "meal": gm.ev1.meal(); break;

            default:
                throw new IllegalStateException("Unexpected value: " + yourChoice);
        }
    }
}
