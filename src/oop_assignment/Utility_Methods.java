/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_assignment;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author teckann
 */
public class Utility_Methods {
    public static String autoGenerateID(String fileName, String character) {
        ArrayList<String[]> dataList = File_Control.readFile(fileName, false);
        
        int count = dataList.size() + 1;
        
        String newID;
        if (count > 0 && count < 10) {
            newID = character + "00" + String.valueOf(count);
        }
        else if (count >= 10 && count < 100) {
            newID = character + "0" + String.valueOf(count);
        }
        else {
            newID = character + String.valueOf(count);
        }
        
        return newID;
    }

    public static String definePriceFormat(String input) {
        double price = Double.parseDouble(input);
        String formatedPrice = String.format("%.2f", price);  
        return formatedPrice;
    }
    
    // Method to create and return the custom scroll bar UI
    public static BasicScrollBarUI createWindowsScrollBarUI() {
        return new BasicScrollBarUI() {
            private final int THUMB_SIZE = 40; // slightly smaller for sleek look

            @Override
            protected void configureScrollBarColors() {
                thumbColor = new Color(120, 170, 230); // soft blue thumb
                trackColor = new Color(235, 240, 245); // light gray-blue track
            }

            @Override
            protected Dimension getMaximumThumbSize() {
                return new Dimension(THUMB_SIZE, THUMB_SIZE);
            }

            @Override
            protected Dimension getMinimumThumbSize() {
                return new Dimension(THUMB_SIZE, THUMB_SIZE);
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
                Graphics2D g2D = (Graphics2D) g.create();
                g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Gradient effect
                Color top = new Color(100, 150, 220);
                Color bottom = new Color(160, 200, 240);
                g2D.setPaint(new java.awt.GradientPaint(r.x, r.y, top, r.x, r.y + r.height, bottom));
                g2D.fillRoundRect(r.x, r.y, r.width, r.height, 12, 12);

                // Border line
                g2D.setColor(new Color(90, 130, 190));
                g2D.drawRoundRect(r.x, r.y, r.width - 1, r.height - 1, 12, 12);

                g2D.dispose();
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
                Graphics2D g2D = (Graphics2D) g.create();
                g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Subtle gradient for track
                Color top = new Color(245, 248, 250);
                Color bottom = new Color(225, 230, 235);
                g2D.setPaint(new java.awt.GradientPaint(r.x, r.y, top, r.x, r.y + r.height, bottom));
                g2D.fillRect(r.x, r.y, r.width, r.height);

                g2D.dispose();
            }
        };
    }
}
