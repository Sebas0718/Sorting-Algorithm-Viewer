package Sorting.Algorithms;

import Block.Block;
import Block.BlockStatus;
import DrawingPanel.DrawPanel;
import Sorting.SortingAlgorithm;

public class QuickSort extends SortingAlgorithm {

    public QuickSort(int delay) {
        super(delay);
    }

    @Override
    public void sort(Block[] blocks, DrawPanel dw) {
        resetStats(); // Pone comparaciones y swaps a 0
        dw.updateWindowStats(0, 0); // Limpia la pantalla de la Window
        quicksort(blocks,dw,0, blocks.length-1);
        clear(blocks, dw);
    }


    private void quicksort(Block[] blocks, DrawPanel dw, int low, int high){
        if (low < high){
            paintPartition(blocks, dw, low, high);
            int piv = partition(blocks,low,high,dw);
            quicksort(blocks, dw, low, piv-1);
            quicksort(blocks, dw, piv+1, high);
        }
    }


    private int partition(Block[] blocks, int low, int high, DrawPanel dw){

        int i = low -1;
        int pivot = blocks[high].getValue();
        blocks[high].setStatus(BlockStatus.SET_ASIDE);

        for (int j = low; j <= high-1; j++) {
            blocks[j].setStatus(BlockStatus.COMPARING);
            incrementComparisons(dw);
            dw.repaint();

            if (blocks[j].getValue() < pivot){
                i++;
                swap(blocks,i,j, dw);
                dw.repaint();
                sleep();
            }
            if (j != i) blocks[j].setStatus(BlockStatus.INACTIVE);
            dw.repaint();
        }
        swap(blocks, i+1, high,dw);
        blocks[i + 1].setStatus(BlockStatus.SORTED);

        for(int k = low; k <= high; k++) {
            if(blocks[k].getStatus() != BlockStatus.SORTED) {
                blocks[k].setStatus(BlockStatus.INACTIVE);
            }
        }
        return i+1;
    }

    private void paintPartition(Block[] blocks, DrawPanel dw, int low, int high){
        while (low <= high){
            blocks[low++].setStatus(BlockStatus.GROUPED);
            dw.repaint();
        }
    }


}