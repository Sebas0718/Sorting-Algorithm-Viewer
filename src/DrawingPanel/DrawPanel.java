package DrawingPanel;

import Block.Block;
import Block.BlockStatus;
import Sorting.Algorithms.BubbleSort;
import Sorting.SortingAlgorithm;
import Viewer.Window;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {

    private Block[] blocks;
    private Window win;
    private SortingAlgorithm sortingAlgorithm;

    public DrawPanel(Block[] b, Window win){
        this.blocks = b;
        this.win = win;
        this.setBackground(new Color(30, 30, 30));
    }
    public void setBlocks(Block[] b) {
        this.blocks = b;
    }


    public void start(){
        if (sortingAlgorithm != null) {
            sortingAlgorithm.sort(this.blocks, this);
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (blocks == null) return;

        int blockWidth = getWidth() / blocks.length;

        for (int i = 0; i < blocks.length; i++){
            switch (blocks[i].getStatus()){
                case COMPARING: g.setColor(new Color(200,70,70)); break;
                case SORTED:    g.setColor(new Color(70,200,70)); break;
                case SET_ASIDE: g.setColor(new Color(240, 150, 70)); break;
                case GROUPED:   g.setColor(new Color(100,150,200)); break;
                default:        g.setColor(blocks[i].getColor());
            }

            int height = blocks[i].getValue();
            g.fillRect(blockWidth * i, getHeight() - height, blockWidth - 2, height);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    public void setSorter(SortingAlgorithm sorter){
        this.sortingAlgorithm = sorter;
    }

    public void updateWindowStats(int c, int s) {
        win.updateStats(c, s);
    }

}
