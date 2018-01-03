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
public class ColorSlider {
    
    private String id;
    private JLabel jlabel_Color;
    private JSlider jslider_Color;
    private JTextField jtextfield_Color;

    public ColorSlider(String id, String text,Point labelLocation,Point textFieldLocation,Point sliderLocation, JPanel jpanel) {
        this.id = id;
        jlabel_Color = new JLabel();
        jlabel_Color.setSize(15, 14);
        jlabel_Color.setVisible(true);
        jlabel_Color.setText(text);
        jlabel_Color.setLocation(labelLocation);
        jpanel.add(jlabel_Color);
        
        jtextfield_Color = new JTextField();
        jtextfield_Color.setSize(35, 25);
        jtextfield_Color.setVisible(true);  
        jtextfield_Color.setLocation(textFieldLocation);
        
        jpanel.add(jtextfield_Color);
        
        jslider_Color = new JSlider();
        jslider_Color.setSize(140, 26);
        jslider_Color.setVisible(true);
        jslider_Color.setLocation(sliderLocation);
        jslider_Color.setMaximum(255);
        jpanel.add(jslider_Color);
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

    public JSlider getJslider_Color() {
        return jslider_Color;
    }

    public void setJslider_Color(JSlider jslider_Color) {
        this.jslider_Color = jslider_Color;
    }

    public JTextField getJtextfield_Color() {
        return jtextfield_Color;
    }

    public void setJtextfield_Color(JTextField jtextfield_Color) {
        this.jtextfield_Color = jtextfield_Color;
    }
    
    
    
}
