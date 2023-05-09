package view;

import javax.swing.*;
import java.awt.*;

public class DashBoardPanel extends JFrame{
    private JPanel DashBoard;
    private JLabel Hello;
    private JButton ADD;

    public DashBoardPanel(){
        setTitle("Dashboard");
        setContentPane(DashBoard);
        setMinimumSize(new Dimension(500, 429));
        setSize(1200,700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        DashBoardPanel myDashBoard = new DashBoardPanel();
    }
}
