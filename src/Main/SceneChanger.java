package Main;

// Allow For Scenes to Change
public class SceneChanger {

    private UI ui;

    public SceneChanger(GameManager gm){
        this.ui = gm.getUi();
    }

    // Display Scene 1 - Welcome Screen
    public void showScreen1(){
        ui.getBgPanel()[1].setVisible(true);
        ui.getBgPanel()[2].setVisible(false);
        ui.getBgPanel()[3].setVisible(false);
        ui.getBgPanel()[4].setVisible(false);
        ui.getBgPanel()[5].setVisible(false);
    }
    
    // Display Scene 2 - Instructions Screen
    public void showScreen2(){
        ui.getBgPanel()[1].setVisible(false);
        ui.getBgPanel()[3].setVisible(false);
        ui.getBgPanel()[2].setVisible(true);
        ui.getBgPanel()[4].setVisible(false);
        ui.getBgPanel()[5].setVisible(false);
    }
    
    // Display Scene 3 - Game Board
    public void showScreen3(){
        ui.getBgPanel()[1].setVisible(false);
        ui.getBgPanel()[2].setVisible(false);
        ui.getBgPanel()[3].setVisible(true);
        ui.getBgPanel()[4].setVisible(false);
        ui.getBgPanel()[5].setVisible(false);
    }
    
    // Display Scene 4 - Losing Screen
    public void showScreen4(){
        ui.getBgPanel()[1].setVisible(false);
        ui.getBgPanel()[2].setVisible(false);
        ui.getBgPanel()[3].setVisible(false);
        ui.getBgPanel()[4].setVisible(true);
        ui.getBgPanel()[5].setVisible(false);
    }
    
    // Display Scene 5 - Victory Screen
    public void showScreen5(){
        ui.getBgPanel()[1].setVisible(false);
        ui.getBgPanel()[2].setVisible(false);
        ui.getBgPanel()[3].setVisible(false);
        ui.getBgPanel()[4].setVisible(false);
        ui.getBgPanel()[5].setVisible(true);
    }
}
