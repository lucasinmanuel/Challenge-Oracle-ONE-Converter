package com.converters.services;

import com.converters.enums.Temperatures;
import com.converters.models.GenericButton;
import com.converters.models.GenericForm;
import com.converters.models.GenericSpace;

import javax.swing.*;
import java.awt.*;

public class TemperatureConverterImpl extends JPanel {
    public TemperatureConverterImpl(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        Temperatures[] Temperatures = com.converters.enums.Temperatures.values();

        String[] newTemperatures = new String[Temperatures.length];
        for (int i = 0; i < Temperatures.length; i++) {
            newTemperatures[i] = Temperatures[i].name();
        }

        GenericForm currencyConverterFrom = new GenericForm("De"+"    ",newTemperatures,true);
        GenericForm currencyConverterTo = new GenericForm("Para",newTemperatures,false);

        GenericSpace genericSpace = new GenericSpace(100,30);

        add(Box.createVerticalGlue());
        add(currencyConverterFrom);
        add(genericSpace);
        add(currencyConverterTo);
        add(Box.createVerticalGlue());
    }
    public double getKelvinTo(double kelvin,String to){
        double result = 0.0;
        switch (to){
            case "CELSIUS"->result = kelvin - 273.15;
            case "FAHRENHEIT"->result = (double) 9 / 5 * (kelvin-273.15) + 32;
            case "RANKINE"->result = kelvin * 1.8;
            case "REAUMUR" ->result = 0.8 * kelvin - 218.52;
        }
        return result;
    }
    public double getCelsiusTo(double celsius,String to){
        double result = 0.0;
        switch (to){
            case "KELVIN"->result = celsius + 273.15;
            case "FAHRENHEIT"->result = (double) 9 / 5 * celsius + 32;
            case "RANKINE"->result = (celsius + 273.15) * 1.8;
            case "REAUMUR" ->result = celsius * 0.80000;
        }
        return result;
    }
    public double getFahrenheitTo(double fahrenheit,String to){
        double result = 0.0;
        switch (to){
            case "KELVIN"->result = (fahrenheit + 459.67) * 5 / 9;
            case "CELSIUS"->result = (fahrenheit - 32) * 5 / 9;
            case "RANKINE"->result = fahrenheit + 459.67;
            case "REAUMUR" ->result = (fahrenheit - 32) * 0.44444;
        }
        return result;
    }
    public double getRankineTo(double rankine,String to){
        double result = 0.0;
        switch (to){
            case "KELVIN"->result = rankine / 1.8;
            case "CELSIUS"->result = (rankine - 491.67) / 1.8;
            case "FAHRENHEIT"->result = rankine - 459.67;
            case "REAUMUR" ->result = (rankine - 491.67) * 0.44444;
        }
        return result;
    }
    public double getReaumurTo(double reaumur,String to){
        double result = 0.0;
        switch (to){
            case "KELVIN"->result = reaumur / 0.8 + 273.15;
            case "CELSIUS"->result = reaumur / 0.8;
            case "FAHRENHEIT"->result = reaumur * 2.2500 + 32;
            case "RANKINE" ->result = reaumur * 2.2500 + 491.67;
        }
        return result;
    }
}
