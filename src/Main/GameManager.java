// Eisha Yadav
// GUI Game Code
// CS2 Mr. Blick's B-Block
// Submitted 2/26/25

package Main;

import Event.Event01;

public class GameManager {

    // Declare Instance Variables.
    private SceneChanger sChanger;
    private ActionHandler aHandler;
    private UI ui;
    private Event01 ev1;

    public GameManager() {

    }

    public Event01 getEv1() {
        return ev1;
    }

    public void setEv1(Event01 ev1) {
        this.ev1 = ev1;
    }

    public SceneChanger getsChanger() {
        return sChanger;
    }

    public void setsChanger(SceneChanger sChanger) {
        this.sChanger = sChanger;
    }

    public UI getUi() {
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public ActionHandler getaHandler() {
        return aHandler;
    }

    public void setaHandler(ActionHandler aHandler) {
        this.aHandler = aHandler;
    }

    public void init(){
        this.ui = new UI(this);
    }

    // Main
    public static void main(String[] args){
        GameManager gm  = new GameManager();
        gm.init();
        gm.ui.init();
        gm.ev1.init();
        gm.aHandler.init();
    }

}
