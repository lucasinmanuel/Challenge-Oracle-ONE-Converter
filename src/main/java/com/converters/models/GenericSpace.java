package com.converters.models;

import javax.swing.*;
import java.awt.*;

public class GenericSpace extends JPanel {
    public GenericSpace(int width,int height){
        setPreferredSize(new Dimension(width,height));
        setMaximumSize(new Dimension(width,height));
    }
}
