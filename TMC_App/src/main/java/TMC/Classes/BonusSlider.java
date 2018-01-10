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
 * @author diegomez
 */
public class BonusSlider extends SliderTMC {

    public BonusSlider(Dimension sliderSize, Point sliderLocation, Dimension textFieldSize, Point textFieldLocation, JPanel panel) {

        super(100, sliderSize, sliderLocation, textFieldSize, textFieldLocation, panel);
        this.getJtextfield_Tmc().setText(getJslider_Tmc().getValue() + "");
    }

}
