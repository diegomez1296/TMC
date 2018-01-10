/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TMC.Classes;

import static TMC.TMC_Application.*;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author diegomez
 */
public class TankSpawn {

    private String id; //"P1" -> Spawn Player 1; "E2" -> Spawn Enemy 2; etc
    private JButton jButton_TankSpawn;
    private JLabel jLabel_TankSpawn;

    private Point spawnOnMap;

    public TankSpawn(String id, Point buttonLocation, Point labelLocation, JPanel jPanel) {
        this.id = id;
        jButton_TankSpawn = new JButton();
        setComponentSettings(jButton_TankSpawn, "", "", 0, new Dimension(80, 20), buttonLocation, jPanel, Boolean.TRUE);

        jLabel_TankSpawn = new JLabel();
        setComponentSettings(jLabel_TankSpawn, "", "", 0, new Dimension(70, 20), labelLocation, jPanel, Boolean.TRUE);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JButton getJbutton_TankSpawn() {
        return jButton_TankSpawn;
    }

    public void setJbutton_TankSpawn(JButton jbutton_TankSpawn) {
        this.jButton_TankSpawn = jbutton_TankSpawn;
    }

    public JLabel getJlabel_TankSpawn() {
        return jLabel_TankSpawn;
    }

    public void setJlabel_TankSpawn(JLabel jlabel_TankSpawn) {
        this.jLabel_TankSpawn = jlabel_TankSpawn;
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
