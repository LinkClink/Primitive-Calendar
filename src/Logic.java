import javax.swing.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Glowna Klasa Logiki  */
public class Logic
{
    /** Zmianna dla formatu daty SimpleDateFormat */
     String data_string_date_format = "yyyy-MM-dd";

    /** Funkcja bierzaca aktualna date */
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(data_string_date_format);

    /** Zmianna z aktualna data */
     String date_y_m_d = simpleDateFormat.format(new Date());

    /** Funkcja dla zczytywania sciezki do pliku */
     JFileChooser fileChooser_save = new JFileChooser();

    /** Zmianna dla otwierania JFileChoser */
     int result;

    /** Zmianna dla zapisu sciezki pliku */
   String file_save;

    /** Zmianna dla zapisu daty do JLabel */
     String day;

    /** Zmianna dla zapisu miesiaca do JLabel */
     String moth;

    /** Tabela dla zapisu z bazy pszez funkcje SQL_connect rekordow */
    String[] tab_1 = new String[0];

    /** Licznik dla petli N1 */
    int a;

    /** Licznik dla petli N2 */
    int i;


    /** Zmianna dla zapisywania daty z JCalendar */
     String data_colum;


     /** Tworzymi obiekt klasy */
     sql_connect sql_fun  = new sql_connect();



    /** Funkcja pobrania z GUI danych i za pomacza SQL pokazuje rekordy z pobrane daty z JCalendar */
    public  String[] event_day(int moth_s,int year_s,int day_s)
    {
        day = "";
        moth = "";

        if (moth_s <= 9)
        {
            moth = "0" + moth_s;
        } else moth = String.valueOf(moth_s);


        if (day_s <= 9)
        {
            day = "0" + day_s;
        } else day = String.valueOf(day_s);


        data_colum = year_s + "-" + moth + "-" + day;


        try {
            tab_1=sql_fun.list_1_rev();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tab_1;
    }

    /** Funkcja pobrania z GUI danych usuniecza rekordow z tabeli evnts */
    public  String[] delete_from_event(int index)

    {
        try {
            sql_fun.remove_from_event(index);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            tab_1=sql_fun.list_1_rev();
        } catch (SQLException e) {
            e.printStackTrace();
        }


     return tab_1;
    }

    /** Funkcja pobrania z GUI danych i za pomacza SQL pokazuje rekordy z pobranej dzisziejsej daty  */
    public  String[] my_event_day()
    {

        try {
            tab_1=sql_fun.list_1_rev_my_day(date_y_m_d);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tab_1;
    }

    /** Funkcja pobrania z GUI danych i za pomacza SQL dodaje nowe rekordy do tabeli events */
    public  void add_new_event(String time_colum,String name_colum,String descrip_colum,String data_colum)
    {
        sql_fun.add_new_event(data_colum,time_colum,name_colum,descrip_colum);

    }

    /** Funkcja pobrania z GUI danych i za pomacza SQL dodaje nowe rekordy do tabeli contacts */
    public  String[] add_new_contact_and_update(String first_colum,String last_colum,String phone_colum)
    {

        sql_fun.add_new_contact(first_colum,last_colum,phone_colum);

        try {
            tab_1=sql_fun.list_2_rev();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tab_1;
    }

 /** Funkcja pobrania  danych z tabeli SQL  i dodaje ich do GUI */
public  String[] all_events()
{

    try {
        tab_1=sql_fun.list_1_rev();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return tab_1;
}

/** Funkcja zapisywania wszystkich rekordow z tabeli events SQL do XTML*/
public  void save_event ()
{

    result = fileChooser_save.showSaveDialog(new JPanel());

    if (fileChooser_save.getSelectedFile() != null)
    {
        file_save= String.valueOf(fileChooser_save.getSelectedFile());
        try {
            sql_fun.sql_save_events(file_save);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}

    /** Funkcja zapisywania wszystkich rekordow z tabeli contacts SQL do XTML*/
    public void save_contacts()
    {
        result = fileChooser_save.showSaveDialog(new JPanel());

        if (fileChooser_save.getSelectedFile() != null)
        {
            file_save= String.valueOf(fileChooser_save.getSelectedFile());
            try {
                sql_fun.sql_save_contacts(file_save);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    /** Funkcja pobrania z GUI danych usuniecza rekordow z tabeli contacts */
    public  String[] delete_and_update(int index)
    {

        try {
            sql_fun.remove_from_cont(index);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            sql_fun.remove_from_cont(index);
        } catch (SQLException e) {
            e.printStackTrace();
        }

     return tab_1;
    }

}