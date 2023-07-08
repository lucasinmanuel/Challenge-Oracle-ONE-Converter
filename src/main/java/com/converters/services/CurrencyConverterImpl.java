package com.converters.services;

import com.converters.enums.Currencies;
import com.converters.models.GenericForm;
import com.converters.models.GenericButton;
import com.converters.models.GenericSpace;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class CurrencyConverterImpl extends JPanel {
    public CurrencyConverterImpl(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        Currencies[] currencies = Currencies.values();

        String[] newCurrencies = new String[currencies.length];
        for (int i = 0; i < currencies.length; i++) {
            newCurrencies[i] = currencies[i].name();
        }

        GenericForm genericFormFrom = new GenericForm("De"+"    ", newCurrencies,true);
        GenericForm genericFormTo = new GenericForm("Para",newCurrencies,false);

        GenericSpace genericSpace = new GenericSpace(100,20);

        add(Box.createVerticalGlue());
        add(genericFormFrom);
        add(genericSpace);
        add(genericFormTo);
        add(Box.createVerticalGlue());
    }
}
