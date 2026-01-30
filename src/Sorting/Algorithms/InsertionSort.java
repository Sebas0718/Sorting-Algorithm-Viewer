package Sorting.Algorithms;

import Block.Block;
import Block.BlockStatus;
import DrawingPanel.DrawPanel;
import Sorting.SortingAlgorithm;

import javax.swing.text.BadLocationException;

public class InsertionSort extends SortingAlgorithm {

    public InsertionSort(int delay) {
        super(delay);
    }

    @Override
    public void sort(Block[] blocks, DrawPanel dw) {
        resetStats(); // Pone comparaciones y swaps a 0
        dw.updateWindowStats(0, 0); // Limpia la pantalla de la Window
        for (int i = 0; i < blocks.length; i++) {

            Block temp = blocks[i];
            temp.setStatus(BlockStatus.SET_ASIDE);
            dw.repaint();
            sleep();
            int j = i-1;

            while (j>=0 && temp.getValue() < blocks[j].getValue()){
                blocks[j].setStatus(BlockStatus.COMPARING);
                incrementComparisons(dw);
                dw.repaint();
                sleep();
                swap(blocks,j,j+1,dw);
                blocks[j+1].setStatus(BlockStatus.INACTIVE);
                blocks[j].setStatus(BlockStatus.SET_ASIDE);
                dw.repaint();
                j--;
            }
            blocks[j+1].setStatus(BlockStatus.INACTIVE);
            blocks[j+1] = temp;
            dw.repaint();
            sleep();
        }
        clear(blocks, dw);
    }

}
