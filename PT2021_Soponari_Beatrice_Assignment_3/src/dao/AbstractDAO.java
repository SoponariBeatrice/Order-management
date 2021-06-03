package dao;

import connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T>{
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
       }

    /**
     *
     * @param field parametrul field da numele coloanei care trebuie introdusa in queryul care se realizeaza
     * @return metoda returneaza un String, un query care realizeaza operatia de SELECT in MySQL
     *
     */
    String createSelectQuery(String field){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Metoda findALL selecteaza toate obiectele de un anumit tip(Client,Product,Order) stocate in tabelele din baza de date
     * @return returneaza o lista de obiecte preluate din baza de date
     */
    public List<T> findAll() {

        StringBuilder findAll = new StringBuilder();
        findAll.append("SELECT * FROM ");
        findAll.append(type.getSimpleName() + ";");
        ResultSet result = null;
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(findAll))){
            result = preparedStatement.executeQuery();
            return createObjects(result);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(result != null){
                ConnectionFactory.close(result);
            }
        }
        return Collections.emptyList();
    }

    /**
     * Metoda gaseste un anumit obiect dupa id-ul sau
     * @param id - id-ul la care se afla obiectul stocar in baza de date
     * @return - returneaza obiectul gasit
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Preia rezultatul unui query si il transforma intr-o lista de obiecte din rezultat
     * @param resultSet resultetul unui query
     * @return returneaza lista de obiecte creata
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Metoda insereaza un obiect de un anumit tip in baza de date
     * @param t
     * @return
     */
    public T insert(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ");
        sb.append(" into `");
        sb.append(t.getClass().getSimpleName());
        sb.append("` (");
        int nrOfFields = 0;
        for (Field field : t.getClass().getDeclaredFields()
        ) {
            sb.append(field.getName());
            sb.append(",");
            nrOfFields++;
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        for (int i = 0; i < nrOfFields; i++){
            if(i == nrOfFields - 1)
                sb.append("?)");
            else
                sb.append("?,");
        }
        PreparedStatement insertStatement = null;
        int rs ;
        Connection dbConnection = ConnectionFactory.getConnection();
        try {
            int index = 1;
            System.out.println("SB este = " + sb);
            insertStatement = dbConnection.prepareStatement(sb.toString());
            for (Field field : t.getClass().getDeclaredFields()
                 ) {
                field.setAccessible(true);
               if(field.getType().toString().equals("int"))
               {
                   try {
                       insertStatement.setInt(index, (int)field.get(t));
                       System.out.println("insert statement = " + insertStatement);
                   } catch (IllegalAccessException e) {
                       e.printStackTrace();
                   }
               }
               else {
                   try {
                       insertStatement.setString(index, (String)(field.get(t)));
                       System.out.println(field.get(t));
                   } catch (IllegalAccessException e) {
                       e.printStackTrace();
                   }
               }
               index++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            rs = insertStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return t;
    }

    /**
     * Metoda updateaza randul din baza de date la care se afla obiectul dat ca parametru t
     * @param t obiectul care trebuie editat
     * @return returneaza noul obiect
     */
    public T update(T t)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(t.getClass().getSimpleName());
        sb.append(" SET ");
        for (Field field : t.getClass().getDeclaredFields()
             ) {
            field.setAccessible(true);
            sb.append(field.getName());
            sb.append(" = '");
            try {
                sb.append(field.get(t));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            sb.append("', ");
        }
        sb.delete(sb.length() - 2,sb.length() - 1);

        sb.append("\nWHERE ");
        for (Field field : t.getClass().getDeclaredFields()
        ) {
            field.setAccessible(true);
            if(field.getName().equals("id"))
            {
                sb.append("id = ");
                try {
                    sb.append(field.get(t));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(sb);
        PreparedStatement updateStatement = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        try {
            updateStatement = dbConnection.prepareStatement(sb.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            updateStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return t;
    }

    /**
     * Metoda sterge din baza de date obiectul dat ca parametru
     * @param t - obiectul care trebuie sters
     * @return returneaza obiectul care a fost sters
     */
    public T delete(T t)
    {   StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(t.getClass().getSimpleName());
        sb.append(" WHERE id = ");
        for (Field field : t.getClass().getDeclaredFields()
             ) {
            if(field.getName().equals("id"))
            {
                field.setAccessible(true);
                try {
                    sb.append(field.get(t));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(sb.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            deleteStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return t;
    }
}
