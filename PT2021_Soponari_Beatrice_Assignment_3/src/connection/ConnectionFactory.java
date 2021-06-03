package connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static final Logger LOGGER =Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/schooldb";
    private static final String USER = "root";
    private static final String PASS = "Diutagrasuta1#";

    private static ConnectionFactory singleInstance = new ConnectionFactory();
    private ConnectionFactory()
    {
        try {
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Metoda creeaza o conexiune cu baza de date specificata
     * @return
     */
    private Connection createConnection()
    {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL,USER,PASS);
        }catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }
    public static Connection getConnection(){
        return singleInstance.createConnection();
    }

    /**
     * Metoda permite inchiderea conexiunii cu baza de date dupa ce nu mai este nevoie de ea
     * @param connection
     */
    public static void close(Connection connection)
    {
        if(connection != null)
        {
            try {
                connection.close();
            }catch (SQLException e)
            {
                LOGGER.log(Level.WARNING,"An error occured while trying to close the connection");
            }
        }
    }

    /**
     * Metoda permite inchiderea unui statement
     * @param statement - statement-ul care trebuie inchis
     */
    public static void close(Statement statement)
    {
        if(statement != null)
        {
            try{
                statement.close();
            }catch (SQLException e)
            {
                LOGGER.log(Level.WARNING,"An error occured while trying to close the statement");
            }
        }
    }

    /**
     * Metoda permite inchiderea unui resultSet o data ce nu mai este nevoie de el
     * @param resultSet resultSet-ul care trebuie inchis
     */
    public static void close(ResultSet resultSet)
    {
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e){
                LOGGER.log(Level.WARNING,"An error occured while trying to close the ResultSet");
            }
        }
    }
}
