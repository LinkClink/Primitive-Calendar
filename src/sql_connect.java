import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

/** Klasa base */
public class sql_connect extends SQLException {


    /** Link laczenia bazy dannych */
     String url = "jdbc:mysql://localhost:3306/program_1";
    /** UserName laczenia bazy dannych */
    String user = "root";
    /** password laczenia bazy dannych */
     String password = "";

    /** Funkcja laczenia bazy dannych za pomocza link-username-password */
    Connection conect_base;

    /** Funkcja tworzaca Statement dla bazy */
    Statement stmt_base;

    /** Funkcja za pomoca jaka zapisujemy odczytujemy w/z baze*/
    ResultSet res_base;

    /** Licznyk */
     int a;

    /** Sql pytania do bazy */
     String query;

    /** Tabela String  N1 jaka sluzy jak schowek dla usuniecza rekordow z tabeli */
     String[] delete_list = new String[1000];

    /** Tabela String  N2 jaka sluzy jak schowek dla usuniecza rekordow z tabeli */
     String[] delete_list_1 = new String[1000];

    /** Tabela String  N3 jaka sluzy jak schowek dla usuniecza rekordow z tabeli */
     String[] delete_list_2 = new String[1000];

    /** Zmianna dla lacenija i dodawanija nowych elementow z tabeli do listy */
     String add_new;

    /** Funkcja testowa nie uzywana */

    /** Funkcja dodawania do listy Events z tabeli events */

    public String[] list_1_rev() throws SQLException {
         query = "Select date,time,name  from event";
         a = 0;
        String[] tab_1 = new String[1000];
        conect_base = DriverManager.getConnection(url, user, password);
        stmt_base = conect_base.createStatement();
        res_base = stmt_base.executeQuery(query);
        while (res_base.next()) {
            String date = res_base.getString(1);
            String time = res_base.getString(2);
            String name = res_base.getString(3);
            tab_1[a] = date + "-" + time + "-" + name;
            a++;
        }
        return tab_1;
    }

    /** Funkcja dodawania do listy Contacts z tabeli contacts */
    public  String[] list_2_rev() throws SQLException {
        query = "Select FirstName,LastName,Phone  from contacts";
         a = 0;
        String[] tab_1 = new String[1000];
        conect_base = DriverManager.getConnection(url, user, password);
        stmt_base = conect_base.createStatement();
        res_base = stmt_base.executeQuery(query);
        while (res_base.next()) {
            String FirstName = res_base.getString(1);
            String LastName = res_base.getString(2);
            String Phone = res_base.getString(3);
            tab_1[a] = FirstName + " " + LastName + " " + Phone;
            a++;
        }
        return tab_1;
    }

    /** Funkcja dodawania do tabeli contacts (MySQL) nowego Kontaktu*/
    public  void add_new_contact(String first_colum, String last_colum, String phone_colum) {
        query = "INSERT INTO program_1.contacts (FirstName,LastName,Phone) \n" +
                " VALUES ('" + first_colum + "','" + last_colum + "','" + phone_colum + "')";
        try {
            stmt_base.executeUpdate(query);
        } catch (SQLException e1) {
            System.out.print("Error");

        }
    }

    /** Funkcja dodawania do tabeli events (MySQL) nowego Kontaktu*/
    public  void add_new_event(String data_colum, String time_colum, String name_colum,String descrip_colum) {
        query = "INSERT INTO program_1.event (date,time,name,descrip) \n" +
                " VALUES ('" + data_colum + "','" + time_colum + "','" + name_colum + "','" + descrip_colum + "')";

        try {
            stmt_base.executeUpdate(query);
        } catch (SQLException e1) {
            System.out.print("Error");

        }
    }

    /** Funkcja zapisywania listy kontaktow z tabeli contacts do txt */
    public void sql_save_contacts(String file_save) throws SQLException {


        query = "Select FirstName,LastName,Phone  from contacts";

        conect_base = DriverManager.getConnection(url, user, password);
        stmt_base = conect_base.createStatement();
        res_base = stmt_base.executeQuery(query);

        while (res_base.next()) {
            String FirstName = res_base.getString(1);
            String LastName = res_base.getString(2);
            String Phone = res_base.getString(3);
            add_new = FirstName + " " + LastName + " " + Phone;
            try (FileWriter writer = new FileWriter("C:\\Users\\LinkClink\\Desktop\\Contacts.txt", true)) {
                writer.write(add_new);
                writer.write("\r\n");
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }
        }
    }

    /** Funkcja zapisywania listy zdarzen z tabeli events do txt file na pulpit */
    public void sql_save_events(String file_save) throws SQLException {


        query = "Select date,time,name,descrip  from event";

        res_base = stmt_base.executeQuery(query);


        while (res_base.next()) {
            String date = res_base.getString(1);
            String time = res_base.getString(2);
            String name = res_base.getString(3);
            String descrip = res_base.getString(4);
            add_new = date + "-" + time + "-" + name + "(" + descrip + ")";

            try (FileWriter writer = new FileWriter(file_save, true)) {
                writer.write(add_new);
                writer.write("\r\n");
            } catch (IOException e) {

            }

        }
    }

    /** Funkcja dodawania do listy Events z tabeli events (tylko tych rekordow zgodnie z dzieseijsza data) */
    public  String[] list_1_rev_my_day(String date_y_m_d) throws SQLException {


        query = "Select date,time,name  from event";
        a = 0;

        String[] tab_1 = new String[1000];

        conect_base = DriverManager.getConnection(url, user, password);
        stmt_base = conect_base.createStatement();
        res_base = stmt_base.executeQuery(query);
        while (res_base.next()) {
            String date = res_base.getString(1);
            String time = res_base.getString(2);
            String name = res_base.getString(3);
            if (date.equals(date_y_m_d)) {
                tab_1[a] = date + "-" + time + "-" + name;
            }
            a++;

        }
        return tab_1;

    }

    /** Funkcja dodawania do listy Events z tabeli events (tylko tych rekordow zgodnie z ustawia data z JCalendar) */
    public  String[] list_1_rev_day(String data_colum) throws SQLException
    {

        query = "Select date,time,name  from event";

        String[] tab_1 = new String[1000];

         a=0;

        try {
            res_base = stmt_base.executeQuery(query);
        } catch (SQLException e1) {

        }
        try {
            while (res_base.next()) {
                String date = res_base.getString(1);
                String time = res_base.getString(2);
                String name = res_base.getString(3);
                add_new = date + "-" + time + "-" + name;
                if (date.equals(data_colum)) {
                    tab_1[a]=add_new;
                    a++;
                }

            }
        } catch (SQLException e1) {

        }

    return tab_1;
    }
/** Funkcja uzuniecza rekordow z tabeli events zgodnie z wybranym indeksem w liscie Events */
       public  void remove_from_event(int select_in) throws SQLException
        {
            a = 0;
            query = "Select date,time,name  from event";

            delete_list = new String[1000];
            delete_list_1 = new String[1000];
            delete_list_2 = new String[1000];

            res_base = stmt_base.executeQuery(query);


                while (res_base.next()) {
                    String date = res_base.getString(1);
                    String time = res_base.getString(2);
                    String name = res_base.getString(3);

                    delete_list[a] = time;
                    delete_list_1[a] = name;
                    delete_list_2[a] = date;
                    a++;

                }

            query = "DELETE FROM `event` WHERE time = '" + delete_list[select_in] + "' and name = '" + delete_list_1[select_in] + "'and date ='" + delete_list_2[select_in] + "'";

                stmt_base.executeUpdate(query);

            }

    /** Funkcja uzuniecza rekordow z tabeli contacts zgodnie z wybranym indeksem w liscie Contacts */
    public void remove_from_cont(int select_in) throws SQLException
    {
        a = 0;
        query = "Select FirstName,LastName,Phone  from contacts";

        delete_list = new String[1000];
        delete_list_1 = new String[1000];
        delete_list_2 = new String[1000];

        res_base = stmt_base.executeQuery(query);

        while (res_base.next()) {
            String FirstName = res_base.getString(1);
            String LastName = res_base.getString(2);
            String Phone = res_base.getString(3);

            delete_list[a] = FirstName;
            delete_list_1[a] = LastName;
            delete_list_2[a] = Phone;
            a++;

        }

        query = "DELETE FROM `contacts` WHERE FirstName = '" + delete_list[select_in] + "' and LastName = '" + delete_list_1[select_in] + "'and Phone ='" + delete_list_2[select_in] + "'";

        stmt_base.executeUpdate(query);

    }

}





