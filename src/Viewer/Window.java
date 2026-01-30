package Viewer;

import Block.BlockGenerator;
import DrawingPanel.DrawPanel;
import Sorting.Algorithms.*;
import Sorting.SortingAlgorithm;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Window extends JFrame {


    private JLabel lblComparisons;
    private JLabel lblSwaps;
    private DrawPanel drawPanel;
    private JComboBox<String> algorithmSelector;
    private BlockGenerator generator = new BlockGenerator();
    private int delay = 50;
    private JSlider speedSlider;


    public Window(int width, int height){

        setTitle("Sorting Visualizer");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 1. Panel de Configuración (Norte)
        JPanel configPanel = new JPanel();
        configPanel.setBackground(new Color(60, 63, 65));
        configPanel.setLayout(new FlowLayout());

        lblComparisons = new JLabel("Comparaciones: 0");
        lblSwaps = new JLabel("Intercambios: 0");
        lblComparisons.setForeground(Color.WHITE);
        lblSwaps.setForeground(Color.WHITE);

        configPanel.add(lblComparisons);
        configPanel.add(lblSwaps);

        JLabel lblSpeed = new JLabel("Delay (ms): ");
        lblSpeed.setForeground(Color.WHITE); //
        speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 500, 50);
        speedSlider.setPreferredSize(new Dimension(150, 20));
        speedSlider.setBackground(new Color(180, 180, 180));

        configPanel.add(lblSpeed);
        configPanel.add(speedSlider);

        algorithmSelector = new JComboBox<>(new String[]{
                "Bubble Sort", "Insertion Sort", "Selection Sort", "Shell Sort", "Quick Sort"
        });

        JButton btnReset = new JButton("RESET");
        JButton btnSort = new JButton("SORT!");

        JLabel label = new JLabel("Algorithm: ");
        label.setForeground(Color.WHITE);

        configPanel.add(label);
        configPanel.add(algorithmSelector);
        configPanel.add(btnReset);
        configPanel.add(btnSort);

        add(configPanel, BorderLayout.NORTH);

        // 2. Inicializar DrawPanel con datos iniciales (Centro)
        drawPanel = new DrawPanel(generator.generateBlocks(50), this);
        add(drawPanel, BorderLayout.CENTER);

        // --- EVENTOS ---

        // Botón RESET: Genera nuevos bloques
        btnReset.addActionListener(e -> {
            drawPanel.setBlocks(generator.generateBlocks(50));
            SwingUtilities.invokeLater(() -> {
                lblComparisons.setText("Comparaciones: " + 0);
                lblSwaps.setText("Intercambios: " + 0);
            });
            drawPanel.repaint();

        });

        // Botón SORT: Lanza el algoritmo en un HILO APARTE
        btnSort.addActionListener(e -> {
            new Thread(() -> {
                btnSort.setEnabled(false);
                btnReset.setEnabled(false);
                speedSlider.setEnabled(false);

                int chosenDelay = speedSlider.getValue();

                SortingAlgorithm selected = getSelectedAlgorithm();
                selected.setDelay(chosenDelay);

                drawPanel.setSorter(selected);
                drawPanel.start();

                btnSort.setEnabled(true);
                btnReset.setEnabled(true);
                speedSlider.setEnabled(true);
            }).start();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
    private SortingAlgorithm getSelectedAlgorithm() {
        String name = (String) algorithmSelector.getSelectedItem();
        switch (name) {
            case "Quick Sort": return new QuickSort(delay);
            case "Shell Sort": return new ShellSort(delay);
            case "Insertion Sort": return new InsertionSort(delay);
            case "Selection Sort": return new SelectionSort(delay);
            default: return new BubbleSort(delay);
        }
    }
    public int getDelay() {
        return speedSlider.getValue();
    }

    public void updateStats(int comparisons, int swaps) {
        SwingUtilities.invokeLater(() -> {
            lblComparisons.setText("Comparaciones: " + comparisons);
            lblSwaps.setText("Intercambios: " + swaps);
        });
    }

}
