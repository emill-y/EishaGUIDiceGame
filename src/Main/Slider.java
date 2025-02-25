package Main;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Slider implements ChangeListener{

    JLabel label;
    JSlider slider;
    private UI ui;

    Slider(UI ui){
        this.ui = ui;

        slider = new JSlider(0,100,50);
        label = new JLabel();

        slider.setPreferredSize(new Dimension(400,200));

        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(10);

        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(25);

        slider.setPaintLabels(true);
        slider.setForeground(Color.black);
        slider.setFont(new Font("MV Boli",Font.PLAIN,15));
        label.setFont(new Font("MV Boli",Font.PLAIN,25));

        slider.setOrientation(SwingConstants.HORIZONTAL);

        label.setText("Your Bet: "+ slider.getValue());

        slider.addChangeListener(this);

        ui.bgPanel[3].add(slider);
        ui.bgPanel[3].moveToFront(slider);
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        label.setText("Your Bet: "+ slider.getValue());

    }

}

