package com.converters.models;

import javax.swing.*;
import java.awt.*;

public class GenericText extends JLabel{
    public GenericText(String text, Color fontColor, int fontStyle, int fontSize){
        setText(text);
        setForeground(fontColor);
        setAlignmentX(CENTER_ALIGNMENT);
        Font oldFont = getFont();
        Font newFont = oldFont.deriveFont(fontStyle,fontSize);
        setFont(newFont);
    }
}
