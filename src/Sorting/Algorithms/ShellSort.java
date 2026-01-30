package Sorting.Algorithms;

import Block.Block;
import Block.BlockStatus;
import DrawingPanel.DrawPanel;
import Sorting.SortingAlgorithm;

public class ShellSort extends SortingAlgorithm

{
    public ShellSort(int delay) {
        super(delay);
    }

    @Override
    public void sort(Block[] blocks, DrawPanel dw) {
        resetStats();
        dw.updateWindowStats(0, 0);

        for (int gap = blocks.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < blocks.length; i++) {

                Block temp = blocks[i];
                temp.setStatus(BlockStatus.SET_ASIDE);
                dw.repaint();
                sleep();
                int j = i;

                // --- LA COMPARACIÓN OCURRE AQUÍ ---
                // Ponemos el contador antes de entrar y también dentro del bucle
                // para contar la comparación que "rompe" el while.

                while (j >= gap) {
                    incrementComparisons(dw); // Registramos que vamos a comparar

                    if (temp.getValue() < blocks[j - gap].getValue()) {
                        blocks[j - gap].setStatus(BlockStatus.COMPARING);
                        dw.repaint();
                        sleep();

                        swap(blocks, j, j - gap, dw); // Esto ya suma 1 a 'swaps' automáticamente

                        blocks[j].setStatus(BlockStatus.INACTIVE);
                        dw.repaint();
                        j -= gap;
                    } else {
                        // Si no entra al if, la comparación se hizo igual,
                        // pero rompemos el bucle.
                        break;
                    }
                }
                blocks[j].setStatus(BlockStatus.INACTIVE);
                dw.repaint();
                sleep();
            }
        }
        clear(blocks, dw);
    }
}
