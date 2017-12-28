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

//MapBlocks
public Block(String ImageFromAddress, boolean isDestroyable, boolean isCollidingtanks, boolean isCollidingBullet, boolean isWater, int LocationBlockX, int LocationBlockY)
        {
            Bloczek = new JLabel();
            Bloczek.setSize(40, 40);
            Bloczek.setLocation(LocationBlockX, LocationBlockY);
            Bloczek.setIcon(new ImageIcon(ImageFromAddress));
            //Bloczek.setBackground(BackColor);
            IsDestroyable = isDestroyable;
            IsCollidingWithTanks = isCollidingtanks;
            IsCollidingWithBullet = isCollidingBullet;
            IsWater = isWater;
        }





//AllTypeBlocks
public Block(String ImageFromAddress, boolean isDestroyable, boolean isCollidingtanks, boolean isCollidingBullet, boolean isWater)
        {
            Bloczek = new JLabel();
            Bloczek.setSize(40, 40);
            Bloczek.setIcon(new ImageIcon(ImageFromAddress));
            IsDestroyable = isDestroyable;
            IsCollidingWithTanks = isCollidingtanks;
            IsCollidingWithBullet = isCollidingBullet;
            IsWater = isWater;
                        
            
        }

    @Override
    public String toString() {
        return "Block{" + "Bloczek=" + Bloczek + ", IsDestroyable=" + IsDestroyable + ", IsCollidingWithTanks=" + IsCollidingWithTanks + ", IsWater=" + IsWater + '}';
    }

    




}
   

