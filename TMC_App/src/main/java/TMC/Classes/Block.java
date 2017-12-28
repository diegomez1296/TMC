package TMC.Classes;

/**
 *
 * @author diegomez
 */


import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Block {
    
    public JLabel Bloczek;
    private Enum_BlockTypes BlockType;
    

//MapBlocks
public Block(String ImageFromAddress, Enum_BlockTypes blockType, int LocationBlockX, int LocationBlockY)
        {
            Bloczek = new JLabel();
            Bloczek.setSize(40, 40);
            Bloczek.setLocation(LocationBlockX, LocationBlockY);
            Bloczek.setIcon(new ImageIcon(ImageFromAddress));
            this.BlockType = blockType;   
        }





//AllTypeBlocks
public Block(String ImageFromAddress, Enum_BlockTypes blockType)
        {
            Bloczek = new JLabel();
            Bloczek.setSize(40, 40);
            Bloczek.setIcon(new ImageIcon(ImageFromAddress));
            this.BlockType = blockType;                      
        }

    public Enum_BlockTypes getBlockType() {
        return BlockType;
    }

    public void setBlockType(Enum_BlockTypes BlockType) {
        this.BlockType = BlockType;
    }

    @Override
    public String toString() {
        return "Block{" + "Bloczek=" + Bloczek + ", BlockType=" + BlockType + '}';
    }


    

}
   

