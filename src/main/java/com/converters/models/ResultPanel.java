package com.converters.models;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {
    private final JTextArea jTextArea = new JTextArea();
    public ResultPanel(String text, Color fontColor, Color background,int fontStyle, int fontSize, Boolean editable){
        jTextArea.setText(text);
        jTextArea.setForeground(fontColor);
        jTextArea.setBackground(background);
        jTextArea.setEditable(editable);
        jTextArea.setMargin(new java.awt.Insets(10,10,10,10));
        Font oldFont = getFont();
        Font newFont = oldFont.deriveFont(fontStyle,fontSize);
        jTextArea.setFont(newFont);
        add(jTextArea);
    }
    public void setText(String text){
        jTextArea.setText(text);
    }
}
