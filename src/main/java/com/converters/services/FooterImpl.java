package com.converters.services;

import com.converters.models.GenericButton;
import com.converters.models.SideBar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FooterImpl extends JPanel {
    public FooterImpl(){
        GenericButton convertBtn = new GenericButton("Converter", Color.BLACK,Color.WHITE,Font.BOLD,16);
        GenericButton copyBtn = new GenericButton("Copiar",Color.BLACK,Color.WHITE,Font.BOLD,16);
        ArrayList<GenericButton> genericButtons = new ArrayList<>();
        genericButtons.add(convertBtn);
        genericButtons.add(copyBtn);
        SideBar sideBar = new SideBar(genericButtons);
        add(sideBar);
    }
}
