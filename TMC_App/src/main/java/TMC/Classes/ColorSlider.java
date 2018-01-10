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
 * @author FoxFromDarkness
 */
public class ColorSlider extends SliderTMC {

    private String id;
    private JLabel jlabel_Color;

    public ColorSlider(String id, String text, Point labelLocation, Point textFieldLocation, Point sliderLocation, JPanel jpanel) {

        super(255, new Dimension(140, 26), sliderLocation, new Dimension(35, 25), textFieldLocation, jpanel);

        this.id = id;
        jlabel_Color = new JLabel();
        TMC.TMC_Application.setComponentSettings(jlabel_Color, text, "", 0, new Dimension(15, 14), labelLocation, jpanel, true);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JLabel getJlabel_Color() {
        return jlabel_Color;
    }

    public void setJlabel_Color(JLabel jlabel_Color) {
        this.jlabel_Color = jlabel_Color;
    }
}
