/*
 * Author: Ricky
 * Editor: Jacob Senior
 */

package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

import javax.swing.border.Border;
import java.awt.*;

public class myBorder {
    public static class RoundedBorder implements Border {

        private int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(2, 0,0, 0);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(0, 0, width - 1, height - 1, radius, radius);
        }
    }

    public static class RectBorder implements Border {
//
//        private int width;
//        private int length;
//
//        public RectBorder(int width, int length) {
//            this.width = width;
//            this.length = length;
//        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRect(0, 0, width - 1, height - 1);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(0, 0, 0, 0);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }
}
