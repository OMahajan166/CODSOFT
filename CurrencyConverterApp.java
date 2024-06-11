//CODSOFT task 4
// Currency converter using API

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyConverterApp {

    private JTextField amountField;
    private JComboBox<String> fromCurrencyCombo;
    private JComboBox<String> toCurrencyCombo;
    private JLabel outputLabel;
    private JLabel errorLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyConverterApp().initializeGUI());
    }

    private void initializeGUI() {
        JFrame frame = new JFrame("Currency Exchange");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(220, 220, 220));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new CompoundBorder(new EmptyBorder(20, 20, 20, 20), new LineBorder(Color.DARK_GRAY, 2)));
        mainPanel.setBackground(new Color(240, 240, 240));
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel header = new JLabel("Currency Exchange");
        header.setFont(new Font("Serif", Font.BOLD, 24));
        header.setForeground(Color.DARK_GRAY);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(0, 0, 20, 0);
        mainPanel.add(header, constraints);

        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setForeground(Color.DARK_GRAY);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 0, 10, 10);
        mainPanel.add(amountLabel, constraints);

        amountField = new JTextField(12);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(amountField, constraints);

        JLabel fromCurrencyLabel = new JLabel("From:");
        fromCurrencyLabel.setForeground(Color.DARK_GRAY);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(0, 0, 10, 10);
        mainPanel.add(fromCurrencyLabel, constraints);

        fromCurrencyCombo = new JComboBox<>(new String[]{
                "Indian Rupee (INR)", "US Dollar (USD)", "Euro (EUR)",
                "British Pound (GBP)", "Japanese Yen (JPY)", "Australian Dollar (AUD)",
                "Canadian Dollar (CAD)", "Swiss Franc (CHF)", "Chinese Yuan (CNY)", "Swedish Krona (SEK)"});
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(fromCurrencyCombo, constraints);

        JLabel toCurrencyLabel = new JLabel("To:");
        toCurrencyLabel.setForeground(Color.DARK_GRAY);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.insets = new Insets(0, 0, 10, 10);
        mainPanel.add(toCurrencyLabel, constraints);

        toCurrencyCombo = new JComboBox<>(new String[]{
                "Indian Rupee (INR)", "US Dollar (USD)", "Euro (EUR)",
                "British Pound (GBP)", "Japanese Yen (JPY)", "Australian Dollar (AUD)",
                "Canadian Dollar (CAD)", "Swiss Franc (CHF)", "Chinese Yuan (CNY)", "Swedish Krona (SEK)"});
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(toCurrencyCombo, constraints);

        JButton convertButton = new JButton("Exchange");
        convertButton.setBackground(new Color(70, 130, 180));
        convertButton.setForeground(Color.WHITE);
        convertButton.setFocusPainted(false);
        convertButton.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        convertButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performConversion();
            }
        });
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(convertButton, constraints);

        JPanel outputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        outputPanel.setBackground(new Color(255, 255, 255));

        outputLabel = new JLabel();
        outputLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        outputLabel.setForeground(Color.BLACK);
        outputPanel.add(outputLabel);

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        outputPanel.add(errorLabel);

        frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(outputPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void performConversion() {
        try {
            String baseCurrency = extractCurrencyCode((String) fromCurrencyCombo.getSelectedItem());
            String targetCurrency = extractCurrencyCode((String) toCurrencyCombo.getSelectedItem());
            double amount = Double.parseDouble(amountField.getText());

            String apiEndpoint = "https://api.exchangerate-api.com/v4/latest/" + baseCurrency;
            URL url = new URL(apiEndpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }

            reader.close();
            connection.disconnect();

            double exchangeRate = Double.parseDouble(responseBuilder.toString().split("\"" + targetCurrency + "\":")[1]
                    .split(",")[0].replace("}", "").trim());

            double convertedAmount = amount * exchangeRate;

            outputLabel.setText(String.format("%.2f %s", convertedAmount, targetCurrency));
            errorLabel.setText("");
        } catch (Exception ex) {
            outputLabel.setText("");
            errorLabel.setText("Error converting currency. Please check your input.");
        }
    }

    private String extractCurrencyCode(String currency) {
        return currency.substring(currency.indexOf('(') + 1, currency.indexOf(')'));
    }
}
