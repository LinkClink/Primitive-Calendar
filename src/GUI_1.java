import com.toedter.calendar.JCalendar;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Klasa GUI JFrame to Main */
class GUI_1_1 extends JFrame{

    GUI_1_1()
    {
        /** Klasa jpanel dodanie do Jframe */
        GUI_1 panel = new GUI_1();

        add(panel);
    }
}

/** Klasa GUI JPanel to GUI JFrame */
public class GUI_1 extends JPanel {

    /** Okienko zarzadzaniem kontaktami */
    JFrame contact_jframe = new JFrame();

    /** Jcalendar dla tworzenia nowych zdarzen */
    JCalendar calendar_j = new JCalendar();  // calendar

    /// data_year_mouth_day ///
    /** String dla SimpleDate dla wyswietlania daty */
    String data_string_date_format = "yyyy-MM-dd";


    sql_connect sql = new sql_connect();
    /** Dzisiejsza data rok-mies-dzien- dla Jlabel */
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(data_string_date_format);

    /** String data dla Jlabel */
    String date_y_m_d = simpleDateFormat.format(new Date());

    /** Jlabel z dziesziejsza data */
    JLabel today_date = new JLabel(date_y_m_d);

    /** Zmianna dla zapisywania danych (FirstName) z tabeli contacts */
    String first_colum;

    /** Zmianna dla zapisywania danych (LastName) z tabeli contacts */
    String last_colum;

    /** Zmianna dla zapisywania danych (Phone) z tabeli contacts */
    String phone_colum;

    /** Zmianna dla zapisywania danych (time) z tabeli events */
    String time_colum;

    /** Zmianna dla zapisywania danych (name event) z tabeli events */
    String name_colum;

    /** Zmianna dla zapisywania danych (description) z tabeli events */
    String descrip_colum;

    /** Zmianna dla zapisywania danych (date event) z tabeli events */
    String data_colum;

    /** Licznik dla petli N1 */
    int a;

    /** Licznik dla petli N2 */
    int i;

    /////
    /** Zmianna GUI dla widoku */
    String sel_data = "[Please Selected DATE]";

    /////
    /** Zmianna dla pobierania ustawionego dnia z JCalendar*/
    int day_s;

    /** Zmianna dla pobierania ustawionego miesiaca z JCalendar*/
    int moth_s;

    /** Zmianna dla pobierania ustawionego roku z JCalendar*/
    int year_s;


    int time_ev, hour_ev;


    //////

    /** List model dla listy zdarzen */
    DefaultListModel listModel = new DefaultListModel();

    /** Lista zdarzen dla wyswietlenia zdarzen */
    JList event_list = new JList(listModel);
    //////
    // calendar //

    /// event ///

    /** Ustawia czas zdarzenia w minutach */
    JScrollBar time_sc_min = new JScrollBar();

    /** Ustawia czas zdarzenia w godzinach */
    JScrollBar time_sc_hour = new JScrollBar();

    JScrollBar date_event_sc = new JScrollBar();
    /////

    /** Styl calego programu */
    Font event = new Font("Onyx", Font.PLAIN, 15);

    //////

    /**  JLabel czensc widoku GUI dla dodawania nowych zdarzen */
    JLabel text_event_1 = new JLabel("New Event");

    /**  JLabel czensc widoku GUI dla dodawania nowych zdarzen */
    JLabel text_event_2 = new JLabel("Name:");

    /**  JLabel czensc widoku GUI dla dodawania nowych zdarzen pokazuje date */
    JLabel text_event_3 = new JLabel(sel_data);

    /**  JLabel czensc widoku GUI dla dodawania nowych zdarzen */
    JLabel text_event_4 = new JLabel("Time");

    /**  JLabel czensc widoku GUI dodawania nowych zdarzen */
    JLabel reminder_l = new JLabel("<-Reminder->");

    /**  JLabel czensc widoku GUI pokazuje czas zdarzenia */
    JLabel time_event = new JLabel("00/00");
    ///////

    /** JButton dla zapisywania daty do JLabel z JCalendar  */
    JButton setdata_but = new JButton("New event");

    /** JButton pokazuje  w liscie zdarzen tylko te zderzenia z dzisziejszego dni */
    JButton my_evnt = new JButton("My event day");

    /** JButton dla dodawania nowych zdarzen */
    JButton add_new_event_but = new JButton("Add");

    /** JButton dla usuniecia nowych zdarzen z tabeli events */
    JButton delete_ev = new JButton("Delete");

    /** JButton pokazuje wzystkie zdarzenia w liscie events */
    JButton my_evnt_all = new JButton(">>");

    /** JButton pokazuje  w liscie zdarzen tylko te zderzenia z ktorego dnia wybrane w JCalendar */
    JButton this_day = new JButton("Day");
    /////

    /** Glowny JMenuBar dla wszystkich JMenu*/
    JMenuBar menu_bar_1 = new JMenuBar();

    /** JMenu z jednym MenuItem "About" */
    JMenu menu_1 = new JMenu("Menu");

    /** JMenu z jednym MenuItem "Show Contacts" pokazuja okienko JFrame */
    JMenu menu_2 = new JMenu("Contacts");

    /** JMenu dla zapisywania zdarzen i comtactow do XML */
    JMenu menu_3 = new JMenu("Save");

    /** Item menu pokazuje Okienko o pogramie */
    JMenuItem menu_item_1 = new JMenuItem("About");

    /** Item menu pokazuje Okienko z lista kontactow */
    JMenuItem menu_item_2 = new JMenuItem("Show List");

    /** Item menu zapisuje dane rekordow z tabeli contacts do XML*/
    JMenuItem menu_item_3 = new JMenuItem("Save Contacts to XML");

    /** Item menu zapisuje dane rekordow z tabeli events do XML*/
    JMenuItem menu_item_4 = new JMenuItem("Save Events to XML");
    /////

    /** TextField dla wprowadzenia tekstu do tabeli event/description */
    JTextField field_event_1 = new JTextField(20);
    /////

    /** TextField dla wprowadzenia tekstu do tabeli event/name */
    JTextArea field_event_2 = new JTextArea(15, 15);


    JScrollPane scroll_textArea_redactor_1 = new JScrollPane(field_event_2);


    /** Tworzumi object klasy dla odwolania do funkcji */
    Logic logic_fun = new Logic();
    sql_connect sql_fun = new sql_connect();

    /////

    /** Ustawia pszypominania za 1 godzine */
    JCheckBox rem_com_1 = new JCheckBox("1-hour");

    /** Ustawia pszypominania za 5 godzine */
    JCheckBox rem_com_2 = new JCheckBox("5-hour");

    /** Ustawia pszypominania za 1 dzien */
    JCheckBox rem_com_3 = new JCheckBox("1-day");

    /** Ustawia pszypominania za 1 tydzien */
    JCheckBox rem_com_4 = new JCheckBox("1-week");
    /////

    /** Okienko jakie pokazuje pszez JMenuIem "About"  */
    JDialog about_dialog = new JDialog();

    // event //
    ///////
    //// Contacts
    /**  JLabel czensc widoku GUI dla dodawania nowych contactow okienko czensc okienka JFrame */
    JLabel label_1_cont = new JLabel("Contacts:");

    /**  JLabel czensc widoku GUI dla dodawania nowych contactow okienko czensc okienka JFrame */
    JLabel label_2_cont = new JLabel("(Add)");

    /**  JLabel czensc widoku GUI dla dodawania nowych contactow okienko czensc okienka JFrame */
    JLabel label_3_cont = new JLabel("FirstName:");

    /**  JLabel czensc widoku GUI dla dodawania nowych contactow okienko czensc okienka JFrame */
    JLabel label_4_cont = new JLabel("LastName:");

    /**  JLabel czensc widoku GUI dla dodawania nowych contactow okienko czensc okienka JFrame */
    JLabel label_5_cont = new JLabel("Phone:");

    /////

    /** List model dla listy kontaktow */
    DefaultListModel listModel_1_con = new DefaultListModel();

    /** Lista zdarzen dla wyswietlenia konaktow */
    JList list_1_con = new JList(listModel_1_con);

    JScrollPane list_1_con_scrol = new JScrollPane(list_1_con);
    /////

    /** TextField dla wprowadzenia tekstu do tabeli contacts/FirstName */
    JTextField text_1_con = new JTextField();

    /** TextField dla wprowadzenia tekstu do tabeli contacts/LastName */
    JTextField text_2_con = new JTextField();

    /** TextField dla wprowadzenia tekstu do tabeli contacts/Phone */
    JTextField text_3_con = new JTextField();
    ////

    /** JButton dla dodawania nowych nowych contactow */
    JButton add_new_cont = new JButton("add");

    /** JButton dla usuniecza rekordow z tabeli contacts */
    JButton delete_cont = new JButton("delete");
    ///

    //// Contacts

    GUI_1()   {

        setLayout(new BorderLayout());
        setBorder(new TitledBorder(new EtchedBorder(), "Organizer_v.0.1"));

        // calendar //
        calendar_j.setSize(330, 200);
        calendar_j.setLocation(10, 18);
        add(calendar_j);
        // calendar //
        /////
        // event //

////////////////////// dodawanie do listy/////////////////////////////////////
         a = 0;
        String[] tab_1 = new String[0];
        try {
            tab_1=sql_fun.list_1_rev();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(a=0;a<tab_1.length;i++)
        {
            if (tab_1[a]==null)
            {break;}
            listModel.addElement(tab_1[a]);
            a++;
        }
///////////////////////////////////////////////////////////////////////////////
        a = 0;
        String[] tab_2 = new String[0];

        try {
            tab_1=sql_fun.list_2_rev();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(a=0;a<tab_2.length;i++)
        {
            if (tab_2[a]==null)
            {break;}
            listModel_1_con.addElement(tab_2[a]);
            a++;
        }
///////////////////////////////////////////////////////////////////////////////

        ////CONTACT JF
        contact_jframe.setSize(280, 530);
        contact_jframe.setLocation(127, 150);
        contact_jframe.setVisible(true);

        label_1_cont.setFont(event);
        label_1_cont.setSize(100, 50);
        label_1_cont.setLocation(17, 1);

        label_2_cont.setSize(100, 50);
        label_2_cont.setLocation(5, 347);

        label_3_cont.setSize(100, 50);
        label_3_cont.setLocation(110, 285);

        text_1_con.setSize(200, 20);
        text_1_con.setLocation(43, 320);

        label_4_cont.setSize(100, 50);
        label_4_cont.setLocation(110, 330);

        text_2_con.setSize(200, 20);
        text_2_con.setLocation(43, 365);

        label_5_cont.setSize(100, 50);
        label_5_cont.setLocation(120, 372);

        text_3_con.setSize(200, 20);
        text_3_con.setLocation(43, 407);

        add_new_cont.setSize(80, 30);
        add_new_cont.setLocation(150, 435);

        delete_cont.setSize(80, 30);
        delete_cont.setLocation(55, 435);

        list_1_con.setSize(240, 250);
        list_1_con.setLocation(10, 40);

        contact_jframe.add(list_1_con);
        contact_jframe.add(label_2_cont);
        contact_jframe.add(label_3_cont);
        contact_jframe.add(text_2_con);
        contact_jframe.add(text_1_con);
        contact_jframe.add(label_4_cont);
        contact_jframe.add(text_3_con);
        contact_jframe.add(label_5_cont);
        contact_jframe.add(label_1_cont);
        contact_jframe.add(add_new_cont);
        contact_jframe.add(delete_cont);

        contact_jframe.setLayout(new BorderLayout());
        //////////

        text_event_4.setSize(50, 20);
        text_event_4.setLocation(475, 60);
        add(text_event_4);

        event_list.setSize(330, 150);
        event_list.setLocation(11, 265);
        event_list.setSelectionBackground(Color.gray);
        event_list.setDragEnabled(true);
        add(event_list);

        delete_ev.setSize(100, 30);
        delete_ev.setLocation(240, 425);
        add(delete_ev);

        date_event_sc.setSize(20, 150);
        date_event_sc.setLocation(348, 266);
        add(date_event_sc);

        text_event_1.setFont(event);
        text_event_2.setFont(event);
        text_event_3.setFont(event);

        field_event_1.setFont(event);
        field_event_2.setFont(event);

        reminder_l.setFont(event);

        time_event.setFont(event);

        text_event_1.setSize(200, 20);
        text_event_1.setLocation(678, 25);
        add(text_event_1);

        text_event_2.setSize(50, 20);
        text_event_2.setLocation(500, 138);
        add(text_event_2);

        add(scroll_textArea_redactor_1);

        scroll_textArea_redactor_1.setLocation(470,170);
        scroll_textArea_redactor_1.setSize(280,150);
        text_event_3.setSize(200, 20);
        text_event_3.setLocation(480, 25);
        add(text_event_3);

        field_event_1.setSize(200, 20);
        field_event_1.setLocation(550, 140);
        field_event_1.setEditable(false);
        add(field_event_1);

        field_event_2.setEditable(false);
        field_event_2.setLineWrap(true);

        setdata_but.setSize(100, 30);
        setdata_but.setLocation(11, 223);
        add(setdata_but);

        my_evnt.setSize(110, 30);
        my_evnt.setLocation(200, 223);
        add(my_evnt);

        this_day.setSize(60, 30);
        this_day.setLocation(123, 223);
        add(this_day);


        my_evnt_all.setSize(50, 30);
        my_evnt_all.setLocation(310, 223);
        add(my_evnt_all);

        add_new_event_but.setSize(60, 30);
        add_new_event_but.setLocation(650, 330);
        add_new_event_but.setEnabled(false);
        add(add_new_event_but);

        time_sc_hour.setSize(20, 60);
        time_sc_hour.setLocation(520, 60);
        time_sc_hour.setMinimum(0);
        time_sc_hour.setMaximum(33);
        time_sc_hour.setEnabled(false);
        add(time_sc_hour);

        time_sc_min.setSize(20, 60);
        time_sc_min.setLocation(545, 60);
        time_sc_min.setMinimum(0);
        time_sc_min.setMaximum(69);
        time_sc_min.setEnabled(false);
        add(time_sc_min);

        time_event.setSize(60, 60);
        time_event.setLocation(470, 60);
        add(time_event);

        reminder_l.setSize(100, 20);
        reminder_l.setLocation(610, 50);
        add(reminder_l);

        rem_com_1.setSize(70, 20);
        rem_com_1.setLocation(590, 70);
        add(rem_com_1);

        rem_com_2.setSize(70, 20);
        rem_com_2.setLocation(660, 70);
        add(rem_com_2);

        rem_com_3.setSize(70, 20);
        rem_com_3.setLocation(590, 90);
        add(rem_com_3);

        rem_com_4.setSize(70, 20);
        rem_com_4.setLocation(660, 90);
        add(rem_com_4);

        today_date.setSize(100, 20);
        today_date.setLocation(710, 445);
        add(today_date);

        menu_1.add(menu_item_1);
        menu_2.add(menu_item_2);
        menu_3.add(menu_item_3);
        menu_3.add(menu_item_4);
        menu_bar_1.add(menu_1);
        menu_bar_1.add(menu_2);
        menu_bar_1.add(menu_3);
        add(menu_bar_1);
        menu_bar_1.setSize(100, 100);
        add(menu_bar_1, BorderLayout.SOUTH);

        my_evnt.addActionListener(new actions());
        add_new_event_but.addActionListener(new actions());
        setdata_but.addActionListener(new actions());
        my_evnt_all.addActionListener(new actions());
        menu_item_2.addActionListener(new actions());
        time_sc_min.addAdjustmentListener(new actions());
        time_sc_hour.addAdjustmentListener(new actions());
        date_event_sc.addAdjustmentListener(new actions());
        menu_item_1.addActionListener(new actions());
        add_new_cont.addActionListener(new actions());
        menu_item_3.addActionListener(new actions());
        menu_item_4.addActionListener(new actions());
        this_day.addActionListener(new actions());
        delete_ev.addActionListener(new actions());
        delete_cont.addActionListener(new actions());


    }
    public class actions implements ActionListener,AdjustmentListener {

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

        public void actionPerformed(ActionEvent ae) {

            if (ae.getActionCommand().equals("Day")) {

                moth_s = calendar_j.getMonthChooser().getMonth() + 1;
                day_s = calendar_j.getDayChooser().getDay();
                year_s = calendar_j.getYearChooser().getYear();

                delete_ev.setEnabled(false);
                listModel.clear();

                // 1
                tab_1=logic_fun.event_day(moth_s,year_s,day_s);

                for(a=0;a<=tab_1.length;i++)
                {
                    if (tab_1[a]==null)
                    {break;}
                    listModel.addElement(tab_1[a]);
                    a++;
                }
            }
            if (ae.getActionCommand().equals("Delete")) {

                int index = event_list.getSelectedIndex();

                // 2
                tab_1=logic_fun.delete_from_event(index);

                listModel.clear();

                for(a=0;a<tab_1.length;i++)
                {
                    if (tab_1[a]==null)
                    {break;}
                    listModel.addElement(tab_1[a]);
                    a++;
                }

            }
            if (ae.getActionCommand().equals("My event day")) {

                delete_ev.setEnabled(false);

                listModel.clear();

                // 3
                tab_1=logic_fun.my_event_day();

                for(a=0;a<tab_1.length;i++)
                {
                    if (tab_1[a]==null)
                    {break;}
                    listModel.addElement(tab_1[a]);
                    a++;
                }

            }
            if (ae.getActionCommand().equals("Add"))
            {
                time_colum = time_event.getText();
                name_colum = field_event_1.getText();
                descrip_colum = field_event_2.getText();

                //4
                logic_fun.add_new_event(time_colum,name_colum,descrip_colum,data_colum);

                field_event_1.setText("");
                field_event_2.setText("");



            }
            if (ae.getActionCommand().equals("New event"))
            {
                moth_s = calendar_j.getMonthChooser().getMonth() + 1;
                day_s = calendar_j.getDayChooser().getDay();
                year_s = calendar_j.getYearChooser().getYear();

                sel_data = year_s + "|" + moth_s + "|" + day_s;
                data_colum = year_s + "-" + moth_s + "-" + day_s;
                text_event_3.setText(sel_data);

                field_event_2.setEditable(true);
                field_event_1.setEditable(true);

                time_sc_min.setEnabled(true);
                time_sc_hour.setEnabled(true);

                add_new_event_but.setEnabled(true);
            }
            if (ae.getActionCommand().equals(">>")) {

                delete_ev.setEnabled(true);
                listModel.clear();

                //5
                tab_1=logic_fun.all_events();

                for(a=0;a<tab_1.length;i++)
                {
                    if (tab_1[a]==null)
                    {break;}
                    listModel.addElement(tab_1[a]);
                    a++;
                }

            }
            if (ae.getActionCommand().equals("Show List")) {
                contact_jframe.setVisible(true);
            }

            if (ae.getActionCommand().equals("About")) {
                JOptionPane.showMessageDialog(about_dialog, "(Organazier_ver_0.1)\n" +
                        "Has the function of creating events\n" +
                        "Reminder of them and associate them with contacts.\n email:shekaqwe@gmail.com \n by LinkClink", "About program", JOptionPane.INFORMATION_MESSAGE);
            }

            if (ae.getActionCommand().equals("Save Events to XML"))
            {

               logic_fun.save_event();
            }

            if (ae.getActionCommand().equals("Save Contacts to XML"))
            {
                logic_fun.save_contacts();

            }
            if (ae.getActionCommand().equals("delete"))
            {
                int index = list_1_con.getSelectedIndex();

                listModel_1_con.clear();

                tab_1=logic_fun.delete_and_update(index);

                for(a=0;a<tab_1.length;i++)
                {
                    if (tab_1[a]==null)
                    {break;}
                    listModel_1_con.addElement(tab_1[a]);
                    a++;
                }

            }
            if (ae.getActionCommand().equals("add")) {

                first_colum = text_1_con.getText();
                last_colum = text_2_con.getText();
                phone_colum = text_3_con.getText();

                text_1_con.setText("");
                text_2_con.setText("");
                text_3_con.setText("");

                listModel_1_con.clear();
                // 6
                tab_1=logic_fun.add_new_contact_and_update(first_colum,last_colum,phone_colum);

                for(a=0;a<tab_1.length;i++)
                {
                    if (tab_1[a]==null)
                    {break;}
                    listModel_1_con.addElement(tab_1[a]);
                    a++;
                }
            }
        }

        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            Object action_ob = e.getSource();
            if (action_ob == time_sc_min) {
                time_ev = time_sc_min.getValue();
                if (time_ev < 10) {
                    time_event.setText(hour_ev + ":0" + String.valueOf(time_ev));
                } else time_event.setText(hour_ev + ":" + String.valueOf(time_ev));

            }
            if (action_ob == time_sc_hour) {
                hour_ev = time_sc_hour.getValue();
                time_event.setText(hour_ev + ":" + String.valueOf(time_ev));
            }
            if (action_ob == date_event_sc) {
                int gg;
                gg = date_event_sc.getValue();
                event_list.setSelectedIndex(gg);
                event_list.ensureIndexIsVisible(20);
            }
        }
    }

}









