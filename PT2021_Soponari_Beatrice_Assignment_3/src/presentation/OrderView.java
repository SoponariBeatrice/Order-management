package presentation;

import javax.swing.*;
import java.awt.*;

public class OrderView {

    JLabel orderLabel = new JLabel("Order");
    JLabel insertClientIdLabel = new JLabel("Insert client ID");
    JLabel insertProductIdLabel = new JLabel("Insert product ID");
    JLabel insertQuantityLabel = new JLabel("Insert quantity");
    JTextField inserstClientIdText = new JTextField(30);
    JTextField insertProductIdText = new JTextField(30);
    JTextField insertQuantityText = new JTextField(30);
    JButton orderButton = new JButton("Order");
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    public void setPanel(JPanel p, JTextField t,JLabel l)
    {
        p.setBackground(Color.WHITE);
        p.setLayout(new FlowLayout());
        p.add(l);
        p.add(t);
    }
    public void setButton(JButton button)
    {
        button.setBackground(Color.LIGHT_GRAY);
    }
    public OrderView(){

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000,700);
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        JPanel aux = new JPanel();
        setPanel(aux,inserstClientIdText,insertClientIdLabel);
        JPanel aux2 = new JPanel();
        setPanel(aux2,insertProductIdText,insertProductIdLabel);
        JPanel aux3 = new JPanel();
        setPanel(aux3,insertQuantityText,insertQuantityLabel);
        setButton(orderButton);
        panel.setBackground(Color.WHITE);
        panel.add(orderLabel);
        panel.add(aux);
        panel.add(aux2);
        panel.add(aux3);
        panel.add(orderButton);
        frame.add(panel);
    }
}
