package bll;

import dao.OrderDAO2;
import model.*;

public class OrderBLL {
    OrderDAO2 o = new OrderDAO2();
    public void generateMessage(Bill bill, Order order, int quantity, int price){
        o.generateMessage(bill,order,quantity,price);
    }
}
