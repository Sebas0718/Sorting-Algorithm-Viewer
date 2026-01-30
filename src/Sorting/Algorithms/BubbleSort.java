package Sorting.Algorithms;

import Block.Block;
import Block.BlockStatus;
import DrawingPanel.DrawPanel;
import Sorting.SortingAlgorithm;

public class BubbleSort extends SortingAlgorithm {


    public BubbleSort(int delay){
        super(delay);
    }


    @Override
    public void sort(Block[] blocks, DrawPanel dw) {
        resetStats(); // Pone comparaciones y swaps a 0
        dw.updateWindowStats(0, 0); // Limpia la pantalla de la Window

        int n = blocks.length;
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++){

                incrementComparisons(dw);
                blocks[j].setStatus(BlockStatus.COMPARING);
                blocks[j+1].setStatus(BlockStatus.COMPARING);
                dw.repaint();
                sleep();

                if (blocks[j].getValue() > blocks[j+1].getValue()){
                    swap(blocks,j,j+1,dw);
                }

                blocks[j].setStatus(BlockStatus.INACTIVE);
                blocks[j+1].setStatus(BlockStatus.INACTIVE);


            }
            blocks[n-i-1].setStatus(BlockStatus.SORTED);
            dw.repaint();
        }
        blocks[0].setStatus(BlockStatus.SORTED);
        dw.repaint();
    }


}
