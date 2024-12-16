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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import org.neo4j.graphdb.Transaction;
import data_manager.*;
import picocli.CommandLine;

public class AIHelpWindow extends JFrame{
    private JTextField questionField; // 用户输入问题的文本框
     JTextArea answerArea;     // 展示回答的文本框
    public AIHelpWindow() {
        // 设置窗口标题
        setTitle("问AI");
        setBounds(100,100,800, 600);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 创建提问部分的面板
        JPanel questionPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("您有什么疑问？");
        questionPanel.add(titleLabel, BorderLayout.NORTH);

        questionField = new JTextField();
        questionPanel.add(questionField, BorderLayout.CENTER);

        JButton submitButton = new JButton("提问");
        questionPanel.add(submitButton, BorderLayout.EAST);

        add(questionPanel, BorderLayout.NORTH);

        // 创建展示回答的文本框
        answerArea = new JTextArea();
        answerArea.setEditable(false); // 设置为不可编辑
        answerArea.setFont(new Font("微软雅黑",Font.BOLD, 15));
        answerArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(answerArea);
        add(scrollPane, BorderLayout.CENTER);


        // 为按钮添加事件监听器
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String question = questionField.getText(); // 获取用户输入的问题
                try {
                    f(AIHelpWindow.this, question);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    public static void f(AIHelpWindow ste, String question) throws Exception {
        ste.answerArea.setText("");
        BigModelNew bg = new BigModelNew(0 + "",
                false, ste);
        BigModelNew.NewQuestion=question;
        // 构建鉴权url
        String authUrl = BigModelNew.getAuthUrl(BigModelNew.hostUrl, BigModelNew.apiKey, BigModelNew.apiSecret);
        OkHttpClient client = new OkHttpClient.Builder().build();
        String url = authUrl.toString().replace("http://", "ws://").replace("https://", "wss://");
        Request request = new Request.Builder().url(url).build();


        WebSocket webSocket = client.newWebSocket(request, bg);

        client.dispatcher().executorService().shutdown();

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
