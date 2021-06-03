package model;

public class Bill {
    private int id;
    private int clientId;
    private int productId;
    private float total;


    public Bill( int clientId, int productId, float total) {
        this.clientId = clientId;
        this.productId = productId;
        this.total = total;
    }

    /**
     * 	Returneaza un sir de string-uri care contine campurile clasei si numele tabelei
     * din care face parte in baza de date;
     */
    public String[] toArrayString() {
        String s[] = new String[5];
        s[0] = "bill";
        s[1] = String.valueOf(clientId);
        s[2] = String.valueOf(productId);
        s[3] = Float.toString(total);
        return s;
    }

    public int getClientName() {
        return clientId;
    }

    public int getProductType() {
        return productId;
    }

    public float getTotal() {
        return total;
    }
}