package LeetCode.MerryChristmas;

import javax.swing.*;

public class MyFrame extends JFrame {

    MyPanel p ;
    MyFrame() {
        p = new MyPanel() ;
        add(p) ;
        setBounds(400, 200, 800, 800) ;
        setVisible(true) ;
        validate() ;
        setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE) ;
    }

}
