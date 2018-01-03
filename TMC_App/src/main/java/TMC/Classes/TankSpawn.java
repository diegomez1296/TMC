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
public class TankSpawn {
    private String id; //"P1" -> Spawn Player 1; "E2" -> Spawn Enemy 2; etc
    private JButton jbutton_TankSpawn;
    private JLabel jlabel_TankSpawn;
    
    private Point spawnOnMap;

    public TankSpawn(String id, Point buttonLocation, Point labelLocation) {
        this.id = id;
        jbutton_TankSpawn = new JButton();
        jbutton_TankSpawn.setSize(80,20);
        jbutton_TankSpawn.setLocation(buttonLocation);
        jbutton_TankSpawn.setVisible(true);
        jlabel_TankSpawn = new JLabel();
        jlabel_TankSpawn.setSize(70, 20);
        jlabel_TankSpawn.setLocation(labelLocation);
        jlabel_TankSpawn.setVisible(true);
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JButton getJbutton_TankSpawn() {
        return jbutton_TankSpawn;
    }

    public void setJbutton_TankSpawn(JButton jbutton_TankSpawn) {
        this.jbutton_TankSpawn = jbutton_TankSpawn;
    }

    public JLabel getJlabel_TankSpawn() {
        return jlabel_TankSpawn;
    }

    public void setJlabel_TankSpawn(JLabel jlabel_TankSpawn) {
        this.jlabel_TankSpawn = jlabel_TankSpawn;
    }

    public Point getSpawnOnMap() {
        return spawnOnMap;
    }

    public String getSpawnOnMapToSave() {
        return spawnOnMap.x + ";" + spawnOnMap.y;
    }
    
    public void setSpawnOnMap(Point spawnOnMap) {
        this.spawnOnMap = spawnOnMap;
    }

    

   
    
    
    
}
