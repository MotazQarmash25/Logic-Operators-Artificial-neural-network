package com.mycompany.ai_project2;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class GraphPanel extends JPanel {
    private final int[][] points; // Array to hold points and their colors
    private final int axisOffset = 50; // Offset for axes to be drawn
    private final int scale = 50; // Scaling factor for coordinates

    // List of line equation parameters
    private final List<double[]> lines; // Each line is defined by {A, B, C}

    public GraphPanel(int[][] points, List<double[]> lines) {
        this.points = points;
        this.lines = lines;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw x and y axes
        g.setColor(Color.BLACK);
        g.drawLine(axisOffset, getHeight() - axisOffset, getWidth(), getHeight() - axisOffset); // x-axis
        g.drawLine(axisOffset, 0, axisOffset, getHeight() - axisOffset); // y-axis

        // Draw axis labels
        g.drawString("X", getWidth() - 20, getHeight() - axisOffset + 15);
        g.drawString("Y", axisOffset - 15, 15);

        // Draw points
        for (int[] point : points) {
            int x = axisOffset + point[0] * scale;
            int y = getHeight() - axisOffset - point[1] * scale;

            // Determine color based on third input value
            Color pointColor = (point[2] == 0) ? Color.RED : Color.BLUE;
            g.setColor(pointColor);

            // Draw the point
            g.fillOval(x - 2, y - 2, 5, 5); // Adjusted to center the point
        }

        // Draw all lines
        for (double[] line : lines) {
            g.setColor(Color.GREEN);
            drawLine(g, line);
        }
    }

    private void drawLine(Graphics g, double[] line) {
        double A = line[0];
        double B = line[1];
        double C = line[2];

        // Calculate the line endpoints
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Calculate y for the left edge of the panel (x = axisOffset)
        int x1 = axisOffset;
        int y1 = (int) (getHeight() - axisOffset - (getLineY(x1, A, B, C) * scale));

        // Calculate y for the right edge of the panel (x = panelWidth)
        int x2 = panelWidth;
        int y2 = (int) (getHeight() - axisOffset - (getLineY(x2, A, B, C) * scale));

        // Draw the line
        g.drawLine(x1, y1, x2, y2);
    }

    private double getLineY(int x, double A, double B, double C) {
        // Convert panel x position to logical x
        double logicalX = (x - axisOffset) / (double) scale;
        // Calculate y based on line equation
        return (-A * logicalX - C) / B;
        
    }
}



