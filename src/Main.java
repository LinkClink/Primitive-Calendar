
import javax.swing.*;
/** Glowna klasa Main dla wloczenia calego programu */
public class Main
{
    public static void main(String[] args) {


        GUI_1_1 frame = null;

        frame = new GUI_1_1();

        frame.setSize(810, 530);

        frame.setResizable(false);
        frame.setLocation(400, 150);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}





