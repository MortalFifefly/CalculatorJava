import java.awt.*;
import java.awt.event.*;

public class CalculatorUI extends Frame implements ActionListener {

    TextField display;

    String[] buttons = {
        "7", "8", "9", "/", 
        "4", "5", "6", "*", 
        "1", "2", "3", "-", 
        "0", ".", "=", "+",
        "C"
    };

    public CalculatorUI() {
        setTitle("Calculator");
        setSize(320, 430);
        setLayout(new BorderLayout());
        setBackground(new Color(235, 235, 235));

        // Display
        display = new TextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setBackground(Color.WHITE);
        display.setForeground(Color.BLACK);
        display.setPreferredSize(new Dimension(0, 80));
        display.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(display, BorderLayout.NORTH);

        // Buttons Panel
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(5, 4, 8, 8));
        panel.setBackground(new Color(220, 220, 220));
        panel.setFont(new Font("Arial", Font.PLAIN, 24));

        for (String txt : buttons) {
            Button b = new Button(txt);
            b.setFont(new Font("Arial", Font.BOLD, 20));
            b.setBackground(Color.WHITE);
            b.addActionListener(this);
            panel.add(b);
        }

        add(panel, BorderLayout.CENTER);

        // Close window properly
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("C")) {
            display.setText("");
            return;
        }

        if (cmd.equals("=")) {
            String expression = display.getText();
            String result = CalculatorLogic.evaluate(expression);
            display.setText(result);
            return;
        }

        // For digits and operators
        display.setText(display.getText() + cmd);
    }

    public static void main(String[] args) {
        new CalculatorUI();
    }
}
