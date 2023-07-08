package com.converters.models;

import javax.swing.*;
import java.awt.*;

public class GenericButton extends JButton {
    public GenericButton(String text,Color fontColor,Color background,int fontStyle,int fontSize){
        setText(text);
        setAlignmentX(CENTER_ALIGNMENT);
        setForeground(fontColor);
        setBackground(background);
        Font oldFont = getFont();
        Font newFont = oldFont.deriveFont(fontStyle,fontSize);
        setFont(newFont);
        setFocusPainted(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
