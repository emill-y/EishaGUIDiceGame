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

        switch(yourChoice) {
//            case "lookHut": gm.ev1.lookHut(); break;
//            case "talkHut": gm.ev1.lookHut(); break;
//            case "restHut": gm.ev1.lookHut(); break;
            case "rollDie": gm.ev1.rollDie(); break;
            //Switch Scenes
            case "goScene1": gm.sChanger.showScreen1(); break;
            case "goScene2": gm.sChanger.showScreen2(); break;
            case "goScene3": gm.sChanger.showScreen3(); break;
            case "goScene4": gm.sChanger.showScreen4(); break;
            case "goScene5": gm.sChanger.showScreen5(); break;
            case "changeCostume": gm.ev1.changeCostume(); break;
        }
    }
}
