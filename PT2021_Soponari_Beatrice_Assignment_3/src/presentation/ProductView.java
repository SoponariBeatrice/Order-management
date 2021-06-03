package presentation;

import javax.swing.*;
import java.awt.*;

public class ProductView {
    JTable jTableProduct;
    JLabel addNewProductLabel = new JLabel("Add new product");
    JLabel nameLabel = new JLabel("Name");
    JLabel priceLabel = new JLabel("Price");
    JLabel quantityLabel = new JLabel("Quantity");
    JTextField nameTextField = new JTextField(30);
    JTextField priceTextField = new JTextField(30);
    JTextField quantityTextField = new JTextField(30);
    JLabel editProductLabel = new JLabel("Edit product");
    JLabel insertIdToUpdateLabel = new JLabel("Insert ID");
    JLabel insertNewNameLabel = new JLabel("Insert new name");
    JLabel insertNewPriceLabel = new JLabel("Insert new price");
    JLabel insertNewQuantityLabel = new JLabel("Insert new quantity");
    JTextField insertIdText = new JTextField(30);
    JTextField insertNewNameText = new JTextField(30);
    JTextField insertNewPriceText = new JTextField(30);
    JTextField insertNewQuantityText = new JTextField(30);
    JLabel deleteLabel = new JLabel("Delete product");
    JLabel insertIdToBeDeletedLabel = new JLabel("Insert id");
    JTextField insertIdToBeDeletedText = new JTextField(30);
    JLabel viewAllProductsLabel = new JLabel("View all products");
    JButton addNewProdButton = new JButton("Add product");
    JButton editProdButton = new JButton("Edit product");
    JButton deleteProdButton = new JButton("Delete product");
    JButton viewProdButton = new JButton("View products");
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    public void constructorClientTable(Object[] columns, Object[][] data ){
       jTableProduct = new JTable(data,columns);
    }
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
    public ProductView(){
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        frame.setSize(1000,700);
        JPanel aux = new JPanel();
        setPanel(aux,nameTextField,nameLabel);
        JPanel aux2 = new JPanel();
        setPanel(aux2,priceTextField,priceLabel);
        JPanel aux3 = new JPanel();
        setPanel(aux3,quantityTextField,quantityLabel);
        JPanel aux4 = new JPanel();
        setPanel(aux4,insertIdText,insertIdToUpdateLabel);
        JPanel aux5 = new JPanel();
        setPanel(aux5,insertNewNameText,insertNewNameLabel);
        JPanel aux6 = new JPanel();
        setPanel(aux6, insertNewPriceText,insertNewPriceLabel);
        JPanel aux7 = new JPanel();
        setPanel(aux7, insertNewQuantityText,insertNewQuantityLabel);
        JPanel aux8 = new JPanel();
        setPanel(aux8,insertIdToBeDeletedText,insertIdToBeDeletedLabel);
        setButton(addNewProdButton);
        setButton(editProdButton);
        setButton(deleteProdButton);
        setButton(viewProdButton);
        panel.add(addNewProductLabel);
        panel.add(aux);
        panel.add(aux2);
        panel.add(aux3);
        panel.add(addNewProdButton);
        panel.add(editProductLabel);
        panel.add(aux4);
        panel.add(aux5);
        panel.add(aux6);
        panel.add(aux7);
        panel.add(editProdButton);
        panel.add(deleteLabel);
        panel.add(aux8);
        panel.add(deleteProdButton);
        panel.add(viewAllProductsLabel);
        panel.add(viewProdButton);
        panel.setBackground(Color.WHITE);
        frame.setBackground(Color.WHITE);
        frame.add(panel);
    }
}
