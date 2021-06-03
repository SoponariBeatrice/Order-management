package presentation;

import bll.OrderBLL;
import connection.ConnectionFactory;
import dao.AbstractDAO;
import dao.ClientDAO2;
import dao.OrderDAO2;
import dao.ProductDAO2;
import model.*;
import presentation.ClientView;
import presentation.OrderView;
import presentation.ProductView;
import presentation.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class Controller {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    /**
     * Constructorul presentation.Controller adauga ActionListeners la toate butoanele la care e nevoie si apeleaza operatiile de insert/update/delete
     */
    public Controller(){

        ClientDAO2 cl = new ClientDAO2();
        OrderDAO2 orderDAO2 = new OrderDAO2();
        ClientView view = new ClientView();
        View view1 = new View();
        ProductView prodView = new ProductView();
        OrderView orderView = new OrderView();
        /**
         * Afiseaza dupa frame-ul initial frame-ul selectat de utilizator(client)
         */
        view1.clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view1.frame.setVisible(false);
                view.frame.setVisible(true);
            }
        });
        /**
         * Afiseaza dupa frame-ul initial frame-ul selectat de utilizator(product)
         */
        view1.productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view1.frame.setVisible(false);
                prodView.frame.setVisible(true);
            }
        });
        /**
         * Afiseaza dupa frame-ul initial frame-ul selectat de utilizator(order)
         */
        view1.orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view1.frame.setVisible(false);
                orderView.frame.setVisible(true);
            }
        });
        /**
         * Insereaza un client cu datele introduse de cate utilizator in tabelul Client
         */
        view.addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Client client = new Client(view.nameTextField.getText(),view.addressTextField.getText(),view.emailTextField.getText());
            cl.insert(client);
            }
        });
        /**
         * Editeaza datele unui client cu noile date introduse de utilizator
         */
        view.editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(view.insertIdTextField.getText());
                Client client = new Client(id,view.insertNewNameTextField.getText(),view.insertNewAddressTextField.getText(),view.insertNewEmailTextField.getText());
                cl.update(client);
            }
        });
        /**
         * Sterge clientul selectat de utilizator prin inserarea ID-ului
         */
        view.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(view.insertIdToBeDeletedTextField.getText());
                Client client = new Client();
                client.setId(id);
                cl.delete(client);
            }
        });
        /**
         * Adauga tabelul in frame pentru vizualizarea tabelului cu clienti la apasarea butonului view
         */
        view.viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTableClient j = new JTableClient();
                Client client = new Client();
                ClientDAO2 c = new ClientDAO2();
                List<Client> res = c.findAll();
                System.out.println(res.size());
                Object[] columns = new Object[4];
                Object[][] data = new Object[res.size()+1][4];
                j.CreateJTable(columns,data,res,client);
                System.out.println(columns[0]);
                view.constructorClientTable(columns,data);
                view.finalPanel.add(view.clientTable);
                view.frame.add(view.finalPanel);
            }
        });
        /**
         * Adauga un produs nou in tabelul Product, cu informatii primite de la utilizator
         */
        prodView.addNewProdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product p = new Product(prodView.nameTextField.getText(),Integer.parseInt(prodView.priceTextField.getText()),Integer.parseInt(prodView.quantityTextField.getText()));
                ProductDAO2 pr = new ProductDAO2();
                pr.insert(p);
            }
        });
        /**
         * Adauga tabelul cu porodusele pr frame la apasarea butonului view
         */
        prodView.viewProdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTableProduct jt = new JTableProduct();
                Product product = new Product();
                ProductDAO2 p = new ProductDAO2();
                List<Product> result = p.findAll();
                Object[] columns = new Object[4];
                Object[][] data = new Object[result.size() + 1][4];
                jt.CreateJTable(columns,data,result,product);
                prodView.constructorClientTable(columns,data);
                prodView.panel.add(prodView.jTableProduct);
                prodView.frame.add(prodView.panel);
            }
        });
        prodView.deleteProdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(prodView.insertIdToBeDeletedText.getText());
                Product product = new Product();
                product.setId(id);
                ProductDAO2 p = new ProductDAO2();
                p.delete(product);
            }
        });
        /**
         * La apasarea butonului order se adauga o comanda in baza de date cu id-ul clientului si id-ul produsului inserat de catre utilizator si se creeaza de asemenea o factura care arata pretul ce trebuie platit si se scade din cantitatea totala cantitatea comandata de cumparator
         */
        orderView.orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Order order = new Order(Integer.parseInt(orderView.inserstClientIdText.getText()),Integer.parseInt(orderView.insertProductIdText.getText()));
                int quantity = Integer.parseInt(orderView.insertQuantityText.getText());
                StringBuilder sb = new StringBuilder();
                orderDAO2.insert(order);
                sb.append("SELECT quantity FROM product WHERE id = ");
                sb.append(orderView.insertProductIdText.getText());
                sb.append(";");
                System.out.println(sb);
                PreparedStatement findStatement = null;
                ResultSet rs = null;
                int actualQuantity = 0;
                Connection dbConnection = ConnectionFactory.getConnection();
                try {
                    findStatement = dbConnection.prepareStatement(sb.toString());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                  ///  findStatement.setInt(quantity, 4);
                    rs = findStatement.executeQuery();
                    rs.next();
                    System.out.println(rs.getInt("quantity"));

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    actualQuantity = rs.getInt("quantity");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                System.out.println(actualQuantity);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("UPDATE product SET quantity = ");
                sb2.append(actualQuantity - quantity);
                sb2.append(" WHERE id = ");
                sb2.append(orderView.insertProductIdText.getText());
                sb2.append(";");
                System.out.println(sb2);
                PreparedStatement updateStatement = null;
                try {
                    updateStatement = dbConnection.prepareStatement(sb2.toString());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    updateStatement.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

               StringBuilder sb3 = new StringBuilder();
                sb3.append("SELECT price FROM product WHERE id = ");
                sb3.append(orderView.insertProductIdText.getText());
                sb3.append(";");
                System.out.println(sb);
                PreparedStatement findPriceStatement = null;
                ResultSet rsPrice = null;
                int price = 0;
                try {
                    findPriceStatement = dbConnection.prepareStatement(sb3.toString());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    rs = findPriceStatement.executeQuery();
                    rs.next();
                    System.out.println(rs.getInt("price"));

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    price = rs.getInt("price");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                Bill bill = new Bill(Integer.parseInt(orderView.inserstClientIdText.getText()),Integer.parseInt(orderView.insertProductIdText.getText()),quantity);
                OrderBLL orderBLL = new OrderBLL();
                orderBLL.generateMessage(bill,order,quantity,price);
            }

        });

    }


}
