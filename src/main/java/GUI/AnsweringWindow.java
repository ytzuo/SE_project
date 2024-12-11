package GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

//import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import data_manager.*;

public class AnsweringWindow extends JFrame {
    KnowledgeCL parent;
    private boolean hasSubmitted = false;
    private JPanel Aw;
    private JList QuestionList;
    private JPanel Inter;
    private SelectionPanel selectionPanel;
    private ShortAnswerPanel shortAnswerPanel;
    private JButton submitButton;
    private List<String> SelectQuestions = new ArrayList<>();
    private List<String> SummaryQuestions = new ArrayList<>();
    private List<String> SelectAnswers = new ArrayList<>();
    private List<String> UserSelectAnswers = new ArrayList<>();
    private List<String> SummaryAnswers = new ArrayList<>();
    private List<String> UserSummaryAnswers = new ArrayList<>();
    private Transaction tx;
    static String knowledgeTitle = "2.4 原型模型";
    //这里需要从上级窗口获得知识点的标题
    //private int currentQuestionIndex = 0;
    private int currentSelectQuestionIndex = 0;
    private int currentSummaryQuestionIndex = 0;
    //当前问题索引

    //默认构造函数为展示2.4的内容

    public AnsweringWindow(String _knowledgeTitle, Transaction tx, KnowledgeCL parent){
        super("AnsweringWindow");
        this.parent = parent;
        this.tx = tx;
        knowledgeTitle = _knowledgeTitle;
        // 初始化组件
        Aw = new JPanel(new BorderLayout());
        QuestionList = new JList<>();
        Inter = new JPanel(new CardLayout());
        selectionPanel = new SelectionPanel();
        shortAnswerPanel = new ShortAnswerPanel();
        this.setContentPane(Aw);
        this.setBounds(100,100,900, 600);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AnsweringWindow.this.parent.setVisible(true);
                AnsweringWindow.this.dispose();
            }
        });
        //连接数据库并初始化题目
        dataPrepare();

        //决定用户答案大小
        for(int i = 0;i < SelectAnswers.size();i++){
            UserSelectAnswers.add(" ");
        }

        // 创建选择题和简答题面板
        selectionPanel = new SelectionPanel();
        shortAnswerPanel = new ShortAnswerPanel();

        // 将面板添加到 Inter 中
        Inter.add(selectionPanel, "选择题");
        Inter.add(shortAnswerPanel, "简答题");

        // 添加交卷按钮
        submitButton = new JButton("交卷");
        submitButton.addActionListener(e -> submitAnswers());

        // 将按钮添加到主面板中
        Aw.add(submitButton, BorderLayout.SOUTH);

        // 添加问题列表和事件监听
        QuestionList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedValue = (String) QuestionList.getSelectedValue();
                    CardLayout layout = (CardLayout) Inter.getLayout();
                    if (selectedValue != null) {
                        if (selectedValue.startsWith("选择")) {
                            currentSelectQuestionIndex = Integer.parseInt(selectedValue.substring(2)) - 1;
                            loadQuestion(currentSelectQuestionIndex,true);
                            layout.show(Inter, "选择题");
                        } else if (selectedValue.startsWith("简答")) {
                            currentSummaryQuestionIndex = Integer.parseInt(selectedValue.substring(2)) - 1;
                            loadQuestion(currentSummaryQuestionIndex,false);
                            layout.show(Inter, "简答题");
                        }
                    }
                }
            }
        });

        Aw.add(new JScrollPane(QuestionList), BorderLayout.WEST);
        Aw.add(Inter, BorderLayout.CENTER);
        QuestionList.setSelectedIndex(0);

        this.setVisible(true);
    }

    private void dataPrepare(){
        //GraphDatabaseService db = connector.connect();
        //Transaction tx = db.beginTx();
        Vector<String> QusIndex = new Vector<>();
        List<Integer> question_nums = question.get_question_num(knowledgeTitle, tx);
        for (int i = 0; i < question_nums.get(0); i++) {
            QusIndex.add("选择" + (i + 1));
        }
        for (int i = 0; i < question_nums.get(1); i++) {
            QusIndex.add("简答" + (i + 1));
        }
        QuestionList.setListData(QusIndex);

        List<String> selects = question.get_select(knowledgeTitle, tx);
        List<String> summaries = question.get_summary(knowledgeTitle, tx);

        for(int i = 0;i < selects.size(); i++) {
            if(i % 2 == 0){
                SelectQuestions.add(selects.get(i));
            } else {
                SelectAnswers.add(selects.get(i));
            }
        }
        for(int i = 0;i < summaries.size(); i++) {
            if (i % 2 == 0) {
                SummaryQuestions.add(summaries.get(i));
            } else {
                SummaryAnswers.add(summaries.get(i));
            }
        }

    }

    private void loadQuestion(int index,boolean isSelect) {
        // 加载选择题或简答题
        if(isSelect){
            if (index < SelectQuestions.size()) {
                selectionPanel.setQuestion(SelectQuestions.get(index));
                selectionPanel.setIndex(index);
            }
        }else {
            if (index < SummaryQuestions.size()) {
                shortAnswerPanel.setQuestion(SummaryQuestions.get(index));
                shortAnswerPanel.setIndex(index);
            }
        }

    }

    class SelectionPanel extends JPanel {
        private JLabel questionLabel;
        private JRadioButton optionA, optionB, optionC, optionD;
        private ButtonGroup optionsGroup;
        private JButton previousButton, nextButton;

        public SelectionPanel() {
            setLayout(new BorderLayout());

            // 问题 label
            questionLabel = new JLabel("问题");
            questionLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
            add(questionLabel, BorderLayout.NORTH);

            // 创建选项
            JPanel optionsPanel = new JPanel(new FlowLayout());
            optionA = new JRadioButton("A");
            optionA.setFont(new Font("微软雅黑", Font.BOLD, 14));
            optionB = new JRadioButton("B");
            optionB.setFont(new Font("微软雅黑", Font.BOLD, 14));
            optionC = new JRadioButton("C");
            optionC.setFont(new Font("微软雅黑", Font.BOLD, 14));
            optionD = new JRadioButton("D");
            optionD.setFont(new Font("微软雅黑", Font.BOLD, 14));
            optionsGroup = new ButtonGroup();
            optionsGroup.add(optionA);
            optionsGroup.add(optionB);
            optionsGroup.add(optionC);
            optionsGroup.add(optionD);

            optionsPanel.add(optionA);
            optionsPanel.add(optionB);
            optionsPanel.add(optionC);
            optionsPanel.add(optionD);

            add(optionsPanel, BorderLayout.CENTER);

            // 按钮Panel
            JPanel buttonPanel = new JPanel();
            previousButton = new JButton("上一题");
            nextButton = new JButton("下一题");

            // 绑定选项选择事件
            optionA.addActionListener(e -> updateUserSelectAnswers(0));
            optionB.addActionListener(e -> updateUserSelectAnswers(1));
            optionC.addActionListener(e -> updateUserSelectAnswers(2));
            optionD.addActionListener(e -> updateUserSelectAnswers(3));


            // 上一题按钮事件
            previousButton.addActionListener(e -> {
                if (currentSelectQuestionIndex > 0) {
                    currentSelectQuestionIndex--;
                    QuestionList.setSelectedIndex(currentSelectQuestionIndex);
                    loadQuestion(currentSelectQuestionIndex, true); // 加载上一题
                }
            });

            /*// 下一题按钮事件
            nextButton.addActionListener(e -> {
                if (currentSelectQuestionIndex < SelectQuestions.size() - 1) {
                    currentSelectQuestionIndex++;
                    QuestionList.setSelectedIndex(currentSelectQuestionIndex);
                    loadQuestion(currentSelectQuestionIndex, true); // 加载下一题
                }
            });*/

            // 下一题按钮事件
            nextButton.addActionListener(e -> {
                if (currentSelectQuestionIndex < SelectQuestions.size() - 1) {
                    currentSelectQuestionIndex++;
                    QuestionList.setSelectedIndex(currentSelectQuestionIndex);
                    loadQuestion(currentSelectQuestionIndex, true); // 加载下一题
                } else {
                    // 如果已经是最后一题，跳转至简答题第一题
                    if (currentSelectQuestionIndex == SelectQuestions.size() - 1) {
                        currentSummaryQuestionIndex = 0; // 跳转到简答题第一题
                        QuestionList.setSelectedIndex(currentSummaryQuestionIndex + SelectQuestions.size()); // 确保切换到简答列表的对应索引
                        loadQuestion(currentSummaryQuestionIndex, false); // 加载简答题
                    }

                }
            });
            buttonPanel.add(previousButton);
            buttonPanel.add(nextButton);
            add(buttonPanel, BorderLayout.SOUTH);
        }

        public void setQuestion(String question) {
            questionLabel.setText(question);
            syncUserSelectAnswers(); // 同步答案到按钮
        }

        public void setIndex(int index) {
            currentSelectQuestionIndex = index;
        }

        private void updateUserSelectAnswers(int optionIndex) {
            // 更新选择的答案
            if (currentSelectQuestionIndex < UserSelectAnswers.size()) {
                UserSelectAnswers.set(currentSelectQuestionIndex, String.valueOf((char) ('A' + optionIndex))); // A,B,C,D
            }
        }

        private void syncUserSelectAnswers() {
            // 同步 UserSelectAnswers 和 JRadioButton 状态
            String userAnswer = UserSelectAnswers.size() > currentSelectQuestionIndex ? UserSelectAnswers.get(currentSelectQuestionIndex) : " ";
            optionsGroup.clearSelection(); // 首先清除当前选择
            if (!userAnswer.equals(" ")) { // 只在非空时进行选择
                switch (userAnswer) {
                    case "A":
                        optionA.setSelected(true);
                        break;
                    case "B":
                        optionB.setSelected(true);
                        break;
                    case "C":
                        optionC.setSelected(true);
                        break;
                    case "D":
                        optionD.setSelected(true);
                        break;
                }
            }
        }
    }

    private void submitAnswers() {
        if(this.hasSubmitted){
            // 提示用户已经交卷
            JOptionPane.showMessageDialog(this, "您已交卷, 无法再次提交!");
            return ;
        }
        // 计算得分
        int score = 0;
        StringBuilder incorrectAnswers = new StringBuilder();

        for (int i = 0; i < SelectQuestions.size(); i++) {
            if (UserSelectAnswers.size() > i && UserSelectAnswers.get(i).equals(SelectAnswers.get(i))) {
                score++;
            } else {
                incorrectAnswers.append("题目: ").append(SelectQuestions.get(i))
                        .append(" 正确答案: ").append(SelectAnswers.get(i)).append("\n");
            }
        }

        // 弹出得分窗口
        showScoreWindow(score, incorrectAnswers.toString());
    }

    private void showScoreWindow(int score, String incorrectAnswers) {
        this.hasSubmitted = true;
        JFrame scoreFrame = new JFrame("交卷结果");
        scoreFrame.setBounds(100,100,800, 600);
        scoreFrame.setLayout(new BorderLayout());

        // 显示得分
        JLabel scoreLabel = new JLabel("你的得分: " + score + "/" + SelectQuestions.size());
        scoreFrame.add(scoreLabel, BorderLayout.NORTH);
        scoreLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
        // 显示错误的选择题及答案
        JTextArea incorrectAnswersArea = new JTextArea(10, 30);
        incorrectAnswersArea.setFont(new Font("微软雅黑", Font.BOLD, 14));
        incorrectAnswersArea.setText(incorrectAnswers);
        incorrectAnswersArea.setEditable(false);
        scoreFrame.add(new JScrollPane(incorrectAnswersArea), BorderLayout.CENTER);

        // 显示简答题的题目和答案
        StringBuilder summaryAnswersBuilder = new StringBuilder();
        for (int i = 0; i < SummaryQuestions.size(); i++) {
            summaryAnswersBuilder.append("简答题: ").append(SummaryQuestions.get(i))
                    .append(" 正确答案: ").append(SummaryAnswers.get(i)).append("\n");
        }
        JTextArea summaryArea = new JTextArea(10, 30);
        summaryArea.setFont(new Font("微软雅黑", Font.BOLD, 14));
        summaryArea.setText(summaryAnswersBuilder.toString());
        summaryArea.setEditable(false);
        scoreFrame.add(new JScrollPane(summaryArea), BorderLayout.SOUTH); // 在下方添加简答题的内容

        scoreFrame.setVisible(true);
        scoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    class ShortAnswerPanel extends JPanel {
        private JLabel questionLabel;
        private JTextArea answerArea;
        private JButton previousButton, nextButton;

        public ShortAnswerPanel() {
            setLayout(new BorderLayout());

            //决定用户答案大小
            for(int i = 0;i < SummaryAnswers.size();i++){
                UserSummaryAnswers.add(" ");
            };

            // 问题 label
            questionLabel = new JLabel("问题");
            questionLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16)); // 设置字体
            add(questionLabel, BorderLayout.NORTH);

            // 答案区域
            answerArea = new JTextArea(10, 30);
            answerArea.setFont(new Font("微软雅黑", Font.PLAIN, 16)); // 设置字体
            add(new JScrollPane(answerArea), BorderLayout.CENTER);

            // 按钮Panel
            JPanel buttonPanel = new JPanel();
            previousButton = new JButton("上一题");
            nextButton = new JButton("下一题");

            // 上一题按钮事件
            previousButton.addActionListener(e -> {
                if (currentSummaryQuestionIndex > 0) {
                    saveUserAnswer(); // 保存当前答案
                    currentSummaryQuestionIndex--;
                    QuestionList.setSelectedIndex(currentSummaryQuestionIndex + SelectQuestions.size());
                    loadQuestion(currentSummaryQuestionIndex, false); // 加载上一题
                }
                else if(currentSelectQuestionIndex == SelectQuestions.size()-1){
                    QuestionList.setSelectedIndex(SelectQuestions.size() -1 );
                    loadQuestion(currentSelectQuestionIndex, false); // 加载上一题
                }
            });

            // 下一题按钮事件
            nextButton.addActionListener(e -> {
                if (currentSummaryQuestionIndex < SummaryQuestions.size() - 1) {
                    saveUserAnswer(); // 保存当前答案
                    currentSummaryQuestionIndex++;
                    QuestionList.setSelectedIndex(currentSummaryQuestionIndex + SelectQuestions.size());
                    loadQuestion(currentSummaryQuestionIndex, false); // 加载下一题
                }
                if (currentSummaryQuestionIndex == SummaryQuestions.size() - 1) {
                    JOptionPane.showMessageDialog(this, "已经是最后一题!");
                }
            });

            buttonPanel.add(previousButton);
            buttonPanel.add(nextButton);
            add(buttonPanel, BorderLayout.SOUTH);
        }

        public void setQuestion(String question) {
            questionLabel.setText(question);
            syncUserSummaryAnswer(); // 同步答案到文本区域
        }

        public void setIndex(int index) {
            currentSummaryQuestionIndex = index; // 设置当前题目的索引
        }

        private void saveUserAnswer() {
            // 保存文本区域的内容到 UserSummaryAnswers
            if (currentSummaryQuestionIndex < UserSummaryAnswers.size()) {
                UserSummaryAnswers.set(currentSummaryQuestionIndex, answerArea.getText());
            }
        }

        private void syncUserSummaryAnswer() {
            // 从 UserSummaryAnswers 中加载当前问题的答案到文本区域
            if (currentSummaryQuestionIndex < UserSummaryAnswers.size()) {
                String userAnswer = UserSummaryAnswers.get(currentSummaryQuestionIndex);
                answerArea.setText(userAnswer); // 设置文本区域内容
            } else {
                answerArea.setText(""); // 如果没有答案，清空文本区域
            }
        }
    }

    public static void main(String[] args) {
        //AnsweringWindow aw = new AnsweringWindow();
    }
}
