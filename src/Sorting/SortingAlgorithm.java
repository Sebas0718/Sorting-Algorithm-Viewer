package Sorting;

import Block.Block;
import Block.BlockStatus;
import DrawingPanel.DrawPanel;
import Sound.SoundManager;

import java.awt.*;;
public abstract class SortingAlgorithm {

    protected int comparisons = 0;
    protected int swaps = 0;
    SoundManager sm = new SoundManager();
    int delay;

    public SortingAlgorithm(int delay){
        this.delay = delay;
    }

    public void swap(Block[] blocks,int indexA, int indexB, DrawPanel dw){

        Block temp = blocks[indexA];
        blocks[indexA] = blocks[indexB];
        blocks[indexB] = temp;

        swaps++;
        dw.updateWindowStats(comparisons, swaps);

        sm.playTone(blocks[indexA].getValue(), blocks.length);
        blocks[indexA].setStatus(BlockStatus.INACTIVE);
        blocks[indexB].setStatus(BlockStatus.INACTIVE);
        dw.repaint();
    }

    protected void incrementComparisons(DrawPanel dw) {
        comparisons++;
        dw.updateWindowStats(comparisons, swaps);
    }

    public void resetStats() {
        comparisons = 0;
        swaps = 0;
    }

    protected void sleep(){
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected void clear(Block[] blocks, DrawPanel dw){
        for (Block b: blocks){
            b.setStatus(BlockStatus.SORTED);
            dw.repaint();
            sm.playTone(b.getValue(), blocks.length);
        }
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public abstract void sort(Block[] blocks, DrawPanel dw);

}
