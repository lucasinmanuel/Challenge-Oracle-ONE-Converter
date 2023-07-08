package com.converters.models;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SideBar extends JPanel{
    public SideBar(ArrayList<GenericButton> genericButtons){
        setLayout(new FlowLayout());
        for (GenericButton genericButton : genericButtons) {
            add(genericButton);
        }
    }
}
