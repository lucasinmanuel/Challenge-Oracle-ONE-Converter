package com.converters.models;

import com.converters.enums.Currencies;
import com.converters.utils.OnlyNumbers;

import javax.swing.*;
import java.awt.*;

public class GenericForm extends JPanel{
    public GenericForm(String text, String[] list, Boolean editable) {
        setLayout(new FlowLayout());
        setMaximumSize(new Dimension(300,40));
        GenericText h2 = new GenericText(text,Color.BLACK,Font.PLAIN,16);
        h2.setAlignmentX(LEFT_ALIGNMENT);
        JComboBox<String> dropdownMenu = new JComboBox<>();
        for (String item : list){
            dropdownMenu.addItem(item);
        }
        JTextField textField = new JTextField();
        textField.setColumns(10);
        textField.setDocument(new OnlyNumbers());
        textField.setEditable(editable);
        textField.setForeground(Color.BLACK);
        Font oldFont = textField.getFont();
        Font newFont = oldFont.deriveFont(Font.PLAIN,16);
        textField.setFont(newFont);

        add(h2);
        add(dropdownMenu);
        add(textField);
    }
}
