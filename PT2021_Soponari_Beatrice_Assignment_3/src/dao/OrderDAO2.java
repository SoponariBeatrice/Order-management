package dao;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import dao.AbstractDAO;
import model.Bill;
import model.Order;

import com.itextpdf.text.Paragraph;

import java.io.FileOutputStream;

public class OrderDAO2 extends AbstractDAO<Order> {
    public OrderDAO2(){
        super();
    }
    private int generateOrder = 0;

    /**
     * Metoda genereaza o factura pentru momentul in care se incheie o comanda
     * @param bill - factura care trebuie creata
     * @param order - comanda trecuta pe factura
     * @param quantity cantitatea de produs comandata
     * @param price pretul produsului
     */
    public void generateMessage(Bill bill, Order order, int quantity, int price) {
        Document document = new Document();
        if(bill != null) {
            try {
                String s = order.getId() + "Bill_for" + order.getIdClient() + ".pdf";
                PdfWriter.getInstance(document, new FileOutputStream(s));
                document.open();
                document.add(new Paragraph("model.Bill number: " + order.getId()));
                document.add(Chunk.NEWLINE);
                document.add(new Paragraph("Client name: " + bill.getClientName() ));
                document.add(Chunk.NEWLINE);
                document.add( new Paragraph("Quantity: " + bill.getTotal()));
                document.add(Chunk.NEWLINE);
                document.add( new Paragraph("Product type: " + bill.getProductType()));
                document.add(Chunk.NEWLINE);
                document.add( new Paragraph("Total price: " + price*quantity));
                document.add(Chunk.NEWLINE);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                String s = ++generateOrder + "ERROR_for" + order.getIdClient() + ".pdf";
                PdfWriter.getInstance(document, new FileOutputStream(s));
                document.open();
                Chunk chunk = new Chunk(s);
                document.add(chunk);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        document.close();
    }
}
