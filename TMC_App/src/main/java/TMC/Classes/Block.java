package TMC.Classes;

/**
 *
 * @author diegomez
 */


import java.awt.Color;
import java.awt.Image;
import java.awt.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Block {
    
    public JLabel Bloczek;
    public boolean IsDestroyable;
    public boolean IsCollidingWithTanks;
    public boolean IsCollidingWithBullet;
    public boolean IsWater;


public Block(String ImageFromAddress, Color BackColor, boolean isDestroyable, boolean isCollidingtanks, boolean isCollidingBullet, boolean isWater, int LocationBlockX, int LocationBlockY)
        {
            Bloczek = new JLabel();
            Bloczek.setSize(40, 40);
            Bloczek.setLocation(LocationBlockX, LocationBlockY);
            Bloczek.setIcon(new ImageIcon(ImageFromAddress));
            Bloczek.setBackground(BackColor);
            IsDestroyable = isDestroyable;
            IsCollidingWithTanks = isCollidingtanks;
            IsCollidingWithBullet = isCollidingBullet;
            IsWater = isWater;
        }

    @Override
    public String toString() {
        return "Block{" + "Bloczek=" + Bloczek + ", IsDestroyable=" + IsDestroyable + ", IsCollidingWithTanks=" + IsCollidingWithTanks + ", IsCollidingWithBullet=" + IsCollidingWithBullet + ", IsWater=" + IsWater + '}';
    }




}
   

