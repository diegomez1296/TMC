package TMC.Classes;

/**
 *
 * @author diegomez
 */


import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Block {
    
    private JLabel jLabel_Block;
    private BlockTypes blockType;
    

//MapBlocks
public Block(String ImageFromAddress, BlockTypes blockType, int LocationBlockX, int LocationBlockY)
        {
            jLabel_Block = new JLabel();
            jLabel_Block.setSize(40, 40);
            jLabel_Block.setLocation(LocationBlockX, LocationBlockY);
            jLabel_Block.setIcon(new ImageIcon(ImageFromAddress));
            this.blockType = blockType;   
        }





//AllTypeBlocks
public Block(String ImageFromAddress, BlockTypes blockType)
        {
            jLabel_Block = new JLabel();
            jLabel_Block.setSize(40, 40);
            jLabel_Block.setIcon(new ImageIcon(ImageFromAddress));
            this.blockType = blockType;                      
        }

    public void setjLabel_Block(JLabel jLabel_Block) {
        this.jLabel_Block = jLabel_Block;
    }

    public void setBlockType(BlockTypes blockType) {
        this.blockType = blockType;
    }

    public JLabel getjLabel_Block() {
        return jLabel_Block;
    }

    public BlockTypes getBlockType() {
        return blockType;
    }

    
    

    @Override
    public String toString() {
        return this.blockType + ";" + this.jLabel_Block.getIcon() + ";" + this.jLabel_Block.getX() + ";" + this.jLabel_Block.getY();
    }
}
   

