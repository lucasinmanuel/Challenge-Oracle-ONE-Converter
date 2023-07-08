package com.converters;

import com.converters.models.GenericForm;
import com.converters.models.GenericButton;
import com.converters.models.SideBar;
import com.converters.services.FooterImpl;
import com.converters.services.CurrencyConverterImpl;
import com.converters.services.HeaderImpl;
import com.converters.services.TemperatureConverterImpl;
import com.converters.utils.ClientHttp;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            JFrame mainScreen = new JFrame();

            mainScreen.setTitle("Conversores");
            var ScreenWidth = 640;
            var ScreenHeight = 480;
            mainScreen.setMinimumSize(new Dimension(ScreenWidth,ScreenHeight));
            mainScreen.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
            mainScreen.setLayout(new BorderLayout());
            Map<String, JPanel> componentMap = new HashMap<>();

            //Beginning | Page start panel
            HeaderImpl pageStartPanel = new HeaderImpl();
            mainScreen.add(pageStartPanel,BorderLayout.PAGE_START);
            componentMap.put(BorderLayout.PAGE_START, pageStartPanel);

            //Beginning | Center panel
            CurrencyConverterImpl currencyPanel = new CurrencyConverterImpl();
            mainScreen.add(BorderLayout.CENTER,currencyPanel);
            componentMap.put(BorderLayout.CENTER,currencyPanel);

            //Begginning | Page end panel
            FooterImpl pageEndPanel = new FooterImpl();
            mainScreen.add(pageEndPanel,BorderLayout.PAGE_END);
            componentMap.put(BorderLayout.PAGE_END, pageEndPanel);

            mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainScreen.pack();
            mainScreen.setVisible(true);

            //EVENT CHANGING CONVERTERS
            TemperatureConverterImpl temperaturePanel = new TemperatureConverterImpl();

            SideBar header = (SideBar) componentMap.get(BorderLayout.PAGE_START).getComponent(3);
            for(Component component : header.getComponents()){
                GenericButton genericButton = (GenericButton) component;
                genericButton.addActionListener(e->{
                    mainScreen.getContentPane().remove(componentMap.get(BorderLayout.CENTER));
                    componentMap.remove(BorderLayout.CENTER);
                    switch (genericButton.getText()) {
                        case "Moeda" -> {
                            mainScreen.add(currencyPanel, BorderLayout.CENTER);
                            componentMap.put(BorderLayout.CENTER, currencyPanel);
                        }
                        case "Temperatura" -> {
                            mainScreen.add(temperaturePanel, BorderLayout.CENTER);
                            componentMap.put(BorderLayout.CENTER, temperaturePanel);
                        }
                    }
                    mainScreen.revalidate();
                    mainScreen.repaint();
                });
            }

            //CONVERSION EVENT
            SideBar footer = (SideBar) componentMap.get(BorderLayout.PAGE_END).getComponent(0);
            GenericButton convertBtn = (GenericButton) footer.getComponent(0);

            ClientHttp clientHttp = new ClientHttp();
            Gson gson = new Gson();

            convertBtn.addActionListener(e -> {

                JPanel centerPanel = componentMap.get(BorderLayout.CENTER);

                JPanel centerPanelFrom = (GenericForm) centerPanel.getComponent(1);
                JPanel centerPanelTo = (GenericForm) centerPanel.getComponent(3);

                JComboBox<String> jComboBoxFrom = (JComboBox<String>) centerPanelFrom.getComponent(1);
                JComboBox<String> jComboBoxTo = (JComboBox<String>) centerPanelTo.getComponent(1);

                String selectedFrom = jComboBoxFrom.getItemAt(jComboBoxFrom.getSelectedIndex());
                String selectedTo = jComboBoxTo.getItemAt(jComboBoxTo.getSelectedIndex());

                if(Objects.equals(selectedFrom, selectedTo)){
                    JOptionPane.showMessageDialog(null,"Os valores 'De' e 'Para' não podem ser iguais.","Valores iguais",JOptionPane.WARNING_MESSAGE);
                    throw new RuntimeException("Equal values");
                }

                //VERIFICANDO SE O VALOR TEM -
                JTextField jTextFieldFrom = (JTextField) centerPanelFrom.getComponent(2);
                String newText;
                if (jTextFieldFrom.getText().startsWith("-")) {
                    newText = "-" + jTextFieldFrom.getText().replaceAll("-", "");
                }else{
                    newText = jTextFieldFrom.getText().replaceAll("-", "");
                }
                jTextFieldFrom.setText(newText);

                JTextField jTextFieldTo = (JTextField) centerPanelTo.getComponent(2);

                if(Objects.equals(jTextFieldFrom.getText(), "")){
                    JOptionPane.showMessageDialog(null,"O campo de texto não pode estar vazio.","Campo de texto vazio",JOptionPane.WARNING_MESSAGE);
                    jTextFieldTo.setText("");
                    throw new RuntimeException("Empty text field");
                }

                String selectedClass = centerPanel.getClass().getName();

                double result = 0;
                if(selectedClass.contains("Currency")){
                    String data = clientHttp.getData("https://economia.awesomeapi.com.br/last/"+selectedFrom+"-"+selectedTo);
                    JsonObject jsonObject = gson.fromJson(data, JsonObject.class);
                    String value = jsonObject.get(selectedFrom+selectedTo).getAsJsonObject().get("bid").getAsString();
                    result = Double.parseDouble(jTextFieldFrom.getText()) * Double.parseDouble(value);
                }else if(selectedClass.contains("Temperature")){
                    switch (selectedFrom){
                        case "KELVIN"->result = temperaturePanel.getKelvinTo(Double.parseDouble(jTextFieldFrom.getText()),selectedTo);
                        case "CELSIUS"->result = temperaturePanel.getCelsiusTo(Double.parseDouble(jTextFieldFrom.getText()),selectedTo);
                        case "FAHRENHEIT"->result = temperaturePanel.getFahrenheitTo(Double.parseDouble(jTextFieldFrom.getText()),selectedTo);
                        case "RANKINE"->result = temperaturePanel.getRankineTo(Double.parseDouble(jTextFieldFrom.getText()),selectedTo);
                        case "REAUMUR"->result = temperaturePanel.getReaumurTo(Double.parseDouble(jTextFieldFrom.getText()),selectedTo);
                    }
                }
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String formattedResult = decimalFormat.format(result);
                jTextFieldTo.setText(formattedResult.replace(",","."));
            });

            //COPY EVENT
            GenericButton copyBtn = (GenericButton) footer.getComponent(1);
            copyBtn.addActionListener(e->{
                JPanel centerPanel = componentMap.get(BorderLayout.CENTER);
                JPanel centerPanelTo = (GenericForm) centerPanel.getComponent(3);
                JTextField jTextFieldTo = (JTextField) centerPanelTo.getComponent(2);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                StringSelection selection = new StringSelection(jTextFieldTo.getText());
                clipboard.setContents(selection, null);
                JOptionPane.showMessageDialog(null,"Texto copiado com sucesso!","Texto copiado",JOptionPane.INFORMATION_MESSAGE);
            });
        });
    }
}