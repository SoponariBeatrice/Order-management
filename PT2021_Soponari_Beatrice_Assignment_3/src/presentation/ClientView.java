package presentation;

import javax.swing.*;
import java.awt.*;

public class ClientView {
    JLabel addNewClientLabel = new JLabel("Add new client: ");
    JLabel nameLabel = new JLabel("Name");
    JLabel addressLabel = new JLabel("Address");
    JLabel emailLabel = new JLabel("Email");
    JButton addClientButton = new JButton("Add client");
    JTextField nameTextField = new JTextField(30);
    JTextField addressTextField = new JTextField(30);
    JTextField emailTextField = new JTextField(30);
    JLabel editClientLabel = new JLabel("Edit client");
    JLabel insertIdLabel = new JLabel("insert ID");
    JLabel insertNewNameLabel = new JLabel("insert new name");
    JTextField insertNewNameTextField = new JTextField(30);
    JLabel insertAddressLabel = new JLabel("insert new address");
    JLabel insertEmailLabel = new JLabel("insert new email");
    JTextField insertIdTextField = new JTextField(30);
    JTextField insertNewAddressTextField = new JTextField(30);
    JTextField insertNewEmailTextField = new JTextField(30);
    JButton editButton = new JButton("Edit");
    JLabel deleteLabel = new JLabel("Delete");
    JLabel insertIdToBeDeletedLabel = new JLabel("insert id");
    JTextField insertIdToBeDeletedTextField = new JTextField(30);
    JButton deleteButton = new JButton("Delete");
    JLabel viewAllClientsLabel = new JLabel("presentation.View all clients");
    JButton viewButton = new JButton("view");
    JTable clientTable;
    JFrame frame = new JFrame();
    JPanel finalPanel = new JPanel();

    public void constructorClientTable(Object[] columns, Object[][] data ){
        clientTable = new JTable(data,columns);

    }

    /**
     * Seteaza un layout pentru panoul introdus si adauga un JTextField si un JLable
     * @param p panoul pe care se adauga
     * @param t TexField-ul care va fi pus pe panou
     * @param l Label-ul care va fi pus pe panou
     */
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

    /**
     * Constructorul adauga Label-urile si TextField-urile necesare creari interfetei pentru adaugarea/editarea/stergerea sau vizualizarea tabelului cu clienti
     */
    public ClientView()
    {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        finalPanel.setLayout(new BoxLayout(finalPanel,BoxLayout.PAGE_AXIS));
        JPanel aux = new JPanel();
        setPanel(aux, nameTextField,nameLabel);
        JPanel aux2 = new JPanel();
        setPanel(aux2,addressTextField,addressLabel);
        JPanel aux3 = new JPanel();
        setPanel(aux3,emailTextField,emailLabel);
        JPanel aux3_2 = new JPanel();
        setPanel(aux3_2,insertNewNameTextField,insertNewNameLabel);
        JPanel aux4 = new JPanel();
        setPanel(aux4,insertIdTextField,insertIdLabel);
        JPanel aux5 = new JPanel();
        setPanel(aux5,insertIdTextField,insertIdLabel);
        JPanel aux6 = new JPanel();
        setPanel(aux6,insertNewAddressTextField,insertAddressLabel);
        JPanel aux7 = new JPanel();
        setPanel(aux7,insertNewEmailTextField,insertEmailLabel);
        JPanel aux8 = new JPanel();
        setPanel(aux8,insertIdToBeDeletedTextField,insertIdToBeDeletedLabel);
        frame.setSize(1000,700);
        setButton(addClientButton);
        setButton(editButton);
        setButton(deleteButton);
        setButton(viewButton);
        finalPanel.setBackground(Color.WHITE);
        finalPanel.add(addNewClientLabel);
        finalPanel.add(aux);
        finalPanel.add(aux2);
        finalPanel.add(aux3);
        finalPanel.add(addClientButton);
        finalPanel.add(editClientLabel);
        finalPanel.add(aux3_2);
        finalPanel.add(aux4);
        finalPanel.add(aux5);
        finalPanel.add(aux6);
        finalPanel.add(aux7);
        finalPanel.add(editButton);
        finalPanel.add(deleteLabel);
        finalPanel.add(aux8);
        finalPanel.add(deleteButton);
        finalPanel.add(viewAllClientsLabel);
        finalPanel.add(Box.createRigidArea(new Dimension(1000,10)));
        finalPanel.add(viewButton);
        frame.add(finalPanel);
       //frame.setVisible(true);
    }

}
