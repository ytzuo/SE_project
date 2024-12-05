package GUI;

import javax.swing.*;

public class AnsweringWindow {

    private JPanel Aw;

    public static void main(String[] args) {
        JFrame frame = new JFrame("AnsweringWindow");
        frame.setContentPane(new AnsweringWindow().Aw);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setVisible(true);
    }
}
