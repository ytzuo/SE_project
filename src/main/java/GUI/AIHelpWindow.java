package GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

//import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import data_manager.*;
import picocli.CommandLine;

public class AIHelpWindow extends JFrame{
    private JTextField questionField; // 用户输入问题的文本框
    private JTextArea answerArea;     // 展示回答的文本框
    public AIHelpWindow() {
        // 设置窗口标题
        setTitle("有什么疑问？");
        setSize(800, 480);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 创建提问部分的面板
        JPanel questionPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("有什么疑问？");
        questionPanel.add(titleLabel, BorderLayout.NORTH);

        questionField = new JTextField();
        questionPanel.add(questionField, BorderLayout.CENTER);

        JButton submitButton = new JButton("提交");
        questionPanel.add(submitButton, BorderLayout.EAST);

        add(questionPanel, BorderLayout.NORTH);

        // 创建展示回答的文本框
        answerArea = new JTextArea();
        answerArea.setEditable(false); // 设置为不可编辑
        JScrollPane scrollPane = new JScrollPane(answerArea);
        add(scrollPane, BorderLayout.CENTER);


        // 为按钮添加事件监听器
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String question = questionField.getText(); // 获取用户输入的问题
                String answer = getAnswer(question); // 获取回答
                answerArea.setText(answer); // 显示回答
            }
        });

    }

    // 模拟获取回答的方法
    private String getAnswer(String question) {
        // 这里可以根据实际需求实现回答逻辑
        return "你问的问题是: " + question + "\n回答: 这是一个示例回答。";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AIHelpWindow window = new AIHelpWindow();
                window.setVisible(true);
            }
        });
    }
}
