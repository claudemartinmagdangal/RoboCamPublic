package Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ColorChooserButton extends javax.swing.JPanel {
    public static interface ColorChangedListener {
        public void colorChanged(Color newColor);
    }

    private Color mColor = Color.BLACK;
    private final List<ColorChangedListener> mListeners = new ArrayList<>();

    public ColorChooserButton() {
        initComponents();
        setColor(mColor, false);
    }

    /**
     * @return the current color
     */
    public Color getColor() {
        return mColor;
    }

    /**
     * Set the color, without notifying the listeners.
     *
     * @param color
     */
    public void setColor(Color color) {
        setColor(color, false);
    }

    /***
     * Set the color and optionally notify the listeners.
     *
     * @param newColor - The news color to be set
     * @param notify - if true then all the color-change listeners will be fired.
     */
    private void setColor(Color newColor, boolean notify) {
        if (newColor == null) return;

        mColor = newColor;
        mColorButton.setIcon(createIcon(mColor, 16, 16));
        mColorButton.updateUI();

        if (notify) {
            // Notify everybody that may be interested.
            for (ColorChangedListener l : mListeners) {
                l.colorChanged(newColor);
            }
        }
    }

    /**
     * Add the given listener to be fired when different
     * color is selected by user.
     *
     * @param listener
     */
    public void addColorChangeListener(ColorChangedListener listener) {
        mListeners.add(listener);
    }

    /**
     * Creates and returns an image icon of given dimension and color.
     *
     * @param color  Fill color
     * @param width
     * @param height
     * @return image icon.
     */
    public static ImageIcon createIcon(Color color, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(color);
        graphics.fillRect(0, 0, width, height);
        graphics.setXORMode(Color.DARK_GRAY);
        graphics.drawRect(0, 0, width - 1, height - 1);
        image.flush();
        ImageIcon icon = new ImageIcon(image);
        return icon;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mColorButton = new javax.swing.JButton();

        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[]{1};
        layout.rowHeights = new int[]{1};
        layout.columnWeights = new double[]{1.0};
        layout.rowWeights = new double[]{1.0};
        setLayout(layout);

        mColorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mColorButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(mColorButton, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void mColorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mColorButtonActionPerformed
        Color newColor = JColorChooser.showDialog(null, "Choose a color", mColor);
        setColor(newColor, true);
    }//GEN-LAST:event_mColorButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton mColorButton;
    // End of variables declaration//GEN-END:variables
}
