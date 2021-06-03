package presentation;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class View {
    JButton clientButton = new JButton("Client");
    JButton productButton = new JButton("Product");
    JButton orderButton = new JButton("Order");
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    public View(){
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        clientButton.setSize(50,20);
        productButton.setSize(50,20);
        orderButton.setSize(50,20);
        clientButton.setBounds(100,300,50,20);
        productButton.setBounds(100,200,50,20);
        orderButton.setBounds(100,100,50,20);
        clientButton.setBackground(Color.LIGHT_GRAY);
        productButton.setBackground(Color.LIGHT_GRAY);
        orderButton.setBackground(Color.LIGHT_GRAY);
        panel.setBackground(Color.WHITE);
        panel.add(clientButton);
        panel.add(productButton);
        panel.add(orderButton);
        frame.add(panel);
        frame.setVisible(true);
    }
}
