package model;

public class Order {
    private int id;
    private int idClient;
    private int idProdus;
    public Order(){

    }
    public Order(int id, int idClient, int idProdus)
    {
        super();
        this.id = id;
        this.idClient = idClient;
        this.idProdus = idProdus;
    }
    public Order(int idClient,int idProdus)
    {
        super();
        this.idClient = idClient;
        this.idProdus = idProdus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }
}
