package dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbstractJTable<T> {
    private final Class<T> type;
    @SuppressWarnings("unchecked")
    public AbstractJTable()
    {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Metoda creeaza un JTable in care adauga datele din baza de date ale unui anumit tabel(Client,Product,Order)
     * @param columns coloanele tabelului
     * @param data matricea in care vor fi puse valorile
     * @param result lista de obiecte din care se vor lua valorile din tabelul din baza de date
     * @param t tipul obiectului din al carui tabel trebuie alese valorile
     */
    public void CreateJTable(Object[] columns,Object[][] data,List<T> result, T t){
        int i = 0;

        for (Field field : t.getClass().getDeclaredFields()
             ) {
                field.setAccessible(true);
                data[0][i] = field.getName();
                columns[i] = field.getName();
                System.out.println(columns[i]);
                i++;
        }
        int row = 1, col = 0;
        for (T r : result
             ) {
            for (Field f : r.getClass().getDeclaredFields()
                 ) {
                f.setAccessible(true);
                try {
                    data[row][col] = f.get(r);
                    System.out.println(data[row][col]);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                col++;
            }
            row++;
            col = 0;
        }
    }
}
