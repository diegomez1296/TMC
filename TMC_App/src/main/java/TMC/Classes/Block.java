package TMC.Classes;

/**
 *
 * @author diegomez
 */
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.*;

public class Block {

    private JLabel jLabel_Block;
    private BlockTypes blockType;

//MapBlocks
    public Block(String imageFromAddress, BlockTypes blockType, Point location, JPanel panel) {
        jLabel_Block = new JLabel();
        TMC.TMC_Application.setComponentSettings(jLabel_Block, "", imageFromAddress, 0, new Dimension(40, 40), location, panel, true);
        this.blockType = blockType;
    }

//AllTypeBlocks
    public Block(String imageFromAddress, BlockTypes blockType) {
        jLabel_Block = new JLabel();
        TMC.TMC_Application.setComponentSettings(jLabel_Block, "", imageFromAddress, 0, new Dimension(40, 40), null, null, false);
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
