/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TMC.Classes;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 *
 * @author diegomez
 */
public abstract class SliderTMC {

    private JSlider jslider_Tmc;
    private JTextField jtextfield_Tmc;

    public SliderTMC(int slider_MaxValue, Dimension sliderSize, Point sliderLocation, Dimension textFieldSize, Point textFieldLocation, JPanel panel) {

        this.jslider_Tmc = new JSlider(0, slider_MaxValue);
        TMC.TMC_Application.setComponentSettings(jslider_Tmc, "", "", 0, sliderSize, sliderLocation, panel, true);

        this.jtextfield_Tmc = new JTextField();
        TMC.TMC_Application.setComponentSettings(jtextfield_Tmc, "", "", 0, textFieldSize, textFieldLocation, panel, true);
    }

    public JSlider getJslider_Tmc() {
        return jslider_Tmc;
    }

    public void setJslider_Tmc(JSlider jslider_Tmc) {
        this.jslider_Tmc = jslider_Tmc;
    }

    public JTextField getJtextfield_Tmc() {
        return jtextfield_Tmc;
    }

    public void setJtextfield_Tmc(JTextField jtextfield_Tmc) {
        this.jtextfield_Tmc = jtextfield_Tmc;
    }

}
