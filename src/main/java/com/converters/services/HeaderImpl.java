package com.converters.services;

import com.converters.models.GenericButton;
import com.converters.models.GenericSpace;
import com.converters.models.GenericText;
import com.converters.models.SideBar;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;

public class HeaderImpl extends JPanel{
    public HeaderImpl(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBorder(new MatteBorder(0,0,1,0, Color.lightGray));

        //Title
        GenericText h1 = new GenericText("Escolha o conversor",Color.black,Font.BOLD,18);

        //Sidebar
        GenericButton currencyButton = new GenericButton("Moeda",Color.BLACK,Color.WHITE,Font.BOLD,16);
        GenericButton MeasurementButton = new GenericButton("Temperatura",Color.BLACK,Color.WHITE,Font.BOLD,16);
        ArrayList<GenericButton> genericButtons = new ArrayList<>();
        genericButtons.add(currencyButton);
        genericButtons.add(MeasurementButton);
        SideBar sideBar = new SideBar(genericButtons);

        GenericSpace genericSpace1 = new GenericSpace(100,5);
        GenericSpace genericSpace2 = new GenericSpace(100,5);
        GenericSpace genericSpace3 = new GenericSpace(100,5);

        //Adding in the variable pageStartPanel
        add(genericSpace1);
        add(h1);
        add(genericSpace2);
        add(sideBar);
        add(genericSpace3);
    }
}
