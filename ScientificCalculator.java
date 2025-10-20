import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScientificCalculator extends JFrame implements ActionListener{
    private JTextField display;
    private String operator = "";
    private double num1=0,num2=0,result=0;
    private boolean startNew = true;

    public ScientificCalculator(){
        setTitle("Scientific Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField("0");
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD,24));
        add(display,BorderLayout.NORTH);

        String[] buttons = {
            "7","8","9","/","sin",
            "4","5","6","*","cos",
            "1","2","3","-","tan",
            "0",".","=","+","log",
            "sqrt","x^2","x^3","1/x","C"
        };

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,5,5,5));

        for(String text:buttons){
            JButton button = new JButton(text);
            button.setFont(new Font("Arial",Font.BOLD,18));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();

        try{
            if("0123456789.".contains(command)){
                if(startNew){
                    display.setText(command.equals(".")?"0.":command);
                    startNew = false;
                }
                else{
                    display.setText(display.getText() + command);
                }
            }
            else if("+-*/".contains(command)){
                num1 = Double.parseDouble(display.getText());
                operator = command;
                startNew = true;
            }
            else if(command.equals("=")){
                num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1*num2;
                        break;
                    case "/":
                        result = num2!=0 ? num1/num2 : 0;
                        break;                
                }
                display.setText(String.valueOf(result));
                startNew = true;
            }
            else if(command.equals("C")){
                display.setText("0");
                operator = "";
                num1 = num2 = result = 0;
                startNew = true;
            }
            else{
                double value = Double.parseDouble(display.getText());
                switch(command){
                    case "sin":
                        result = Math.sin(Math.toRadians(value));
                        break;
                    case "cos":
                        result = Math.cos(Math.toRadians(value));
                        break;
                    case "tan":
                        result = Math.tan(Math.toRadians(value));
                        break;
                    case "log":
                        result = Math.log10(value);
                        break;
                    case "sqrt":
                        result = Math.sqrt(value);
                        break;
                    case "x^2":
                        result = Math.pow(value,2);
                        break;
                    case "x^3":
                        result = Math.pow(value,3);
                        break;
                    case "1/x":
                        result = 1/value;
                        break;
                }
                display.setText(String.valueOf(result));
                startNew = true;
            }
        }
        catch(Exception ex){
            display.setText("Error");
            startNew = true;
        }
    }

    public static void main(String args[]){
        new ScientificCalculator();
    }
}