package currency;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Currency {


    public enum CurrencyType {
        DOLLAR("Dollars", 150.0),
        EURO("Euros", 160.0),
        YEN("Yen", 1.4),
        DINAR("Dinars", 450.0),
        TZSH("TZShs", 18.0),
        POUND("Pounds", 180.0),
        YUAN("Yuans", 20.0),
        RAND("Rands", 8.0),
        UGSH("UGShs", 31.0),
        FRANC("Franc", 170.0);

        private final String name;
        private final double rate;

        CurrencyType(String name, double rate) {
            this.name = name;
            this.rate = rate;
        }

        public String getName() {
            return name;
        }

        public double getRate() {
            return rate;
        }
    }

    public static void main(String[] args) {
    
        JFrame frame = new JFrame("Currency Converter");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);


        JLabel currencyLabel = new JLabel("Currency (Kshs):");
        currencyLabel.setBounds(20, 20, 150, 30); 
        frame.add(currencyLabel);

       
        final JTextField currencyField = new JTextField();
        currencyField.setBounds(150, 20, 200, 30);
        frame.add(currencyField);

      
        JLabel valueLabel = new JLabel("Value:");
        valueLabel.setBounds(20, 60, 150, 30); 
        frame.add(valueLabel);

        final JTextField valueField = new JTextField();
        valueField.setBounds(150, 60, 200, 30);
        valueField.setEditable(false); 
        frame.add(valueField);

       
        JButton[] buttons = new JButton[CurrencyType.values().length];

        
        for (int i = 0; i < CurrencyType.values().length; i++) {
            final CurrencyType currencyType = CurrencyType.values()[i]; 

            buttons[i] = new JButton(currencyType.getName());
            buttons[i].setBounds(20 + (i % 5) * 75, 110 + (i / 5) * 50, 70, 30);
            frame.add(buttons[i]);

       
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    convertCurrency(currencyField.getText(), currencyType, valueField);
                }
            });
        }

      
        frame.setVisible(true);
    }

    private static void convertCurrency(String kshValueString, CurrencyType currencyType, JTextField valueField) {
        try {
            double kshValue = Double.parseDouble(kshValueString);
            double convertedValue = kshValue / currencyType.getRate();

            valueField.setText(String.format("%.6f", convertedValue));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(valueField.getParent(), "Please enter a valid number.");
        }
    }
}
