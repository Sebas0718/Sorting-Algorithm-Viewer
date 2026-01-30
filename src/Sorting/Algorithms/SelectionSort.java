package Sorting.Algorithms;

import Block.Block;
import Block.BlockStatus;
import DrawingPanel.DrawPanel;
import Sorting.SortingAlgorithm;

public class SelectionSort extends SortingAlgorithm {


    public SelectionSort(int delay) {
        super(delay);
    }

    @Override
    public void sort(Block[] blocks, DrawPanel dw) {
        resetStats(); // Pone comparaciones y swaps a 0
        dw.updateWindowStats(0, 0); // Limpia la pantalla de la Window
        int nextIndex = 0;
        int minIndex = 0;
        for (int i = 0; i < blocks.length; i++){

            minIndex = nextIndex;
            blocks[nextIndex].setStatus(BlockStatus.COMPARING);

            for (int j = nextIndex; j < blocks.length; j++) {

                incrementComparisons(dw);
                blocks[j].setStatus(BlockStatus.COMPARING);
                dw.repaint();
                sleep();

                if (blocks[minIndex].getValue() > blocks[j].getValue()){
                    minIndex = j;
                }
                blocks[j].setStatus(BlockStatus.INACTIVE);
                dw.repaint();
                sleep();
            }
            swap(blocks, nextIndex,minIndex,dw);
            blocks[nextIndex].setStatus(BlockStatus.SORTED);
            nextIndex++;
            dw.repaint();
            sleep();
        }



    }
}
