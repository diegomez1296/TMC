/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TMC.Classes;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Windows 8
 */
public class BonusSlider {

    private JSlider jSlider_Bonus;
    private JTextField jTextField_BonusValue;

    public BonusSlider(Dimension sliderSize, Point sliderLocation, Dimension textFieldSize, Point textFieldLocation, JPanel panel) {
        this.jSlider_Bonus = new JSlider(0, 100);
        jSlider_Bonus.setSize(sliderSize);
        jSlider_Bonus.setLocation(sliderLocation);
        jSlider_Bonus.setVisible(true);
        panel.add(jSlider_Bonus);

        this.jTextField_BonusValue = new JTextField(jSlider_Bonus.getValue() + "");
        jTextField_BonusValue.setSize(textFieldSize);
        jTextField_BonusValue.setLocation(textFieldLocation);
        jTextField_BonusValue.setVisible(true);
        panel.add(jTextField_BonusValue);
    }

    public JSlider getjSlider_Bonus() {
        return jSlider_Bonus;
    }

    public void setjSlider_Bonus(JSlider jSlider_Bonus) {
        this.jSlider_Bonus = jSlider_Bonus;
    }

    public JTextField getjTextField_BonusValue() {
        return jTextField_BonusValue;
    }

    public void setjTextField_BonusValue(JTextField jTextField_BonusValue) {
        this.jTextField_BonusValue = jTextField_BonusValue;
    }
}
