package GUI;

import data_manager.connector;
import data_manager.knowledge_point;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.sort;

public class KnowledgeCL extends JFrame{
    /*public static void main(String[] args) {
        System.out.println("begin");
        new KnowledgeCL();
        System.out.println("end");
        //System.exit(0);
    }*/
    public KnowledgeCL() {
        super("Welcome");//标题
        this.tx = connector.connect().beginTx();

        this.setBounds(100, 100, 1400, 700);//设置页面
        this.setLayout(new BorderLayout()); // 使用 BorderLayout 布局管理器


        //addWindowListener(new MyWindowListener());

        String beginning = "软件过程模型";
        List<String> list = knowledge_point.get_knowledge(beginning, tx);
        sort(list);
        String []jg = (String[]) list.toArray(new String[0]);
        //假装是二级知识点

        this.list1 = new JList(jg);
        list1.setBorder(BorderFactory.createTitledBorder("请选择需要学习的二级知识点"));
        this.add(list1,BorderLayout.WEST);
        list1.setFont(new Font("宋体",Font.BOLD, 20));
        //二级知识点展示栏

        class myListener implements ListSelectionListener  //创建监听器，实现列表项选择监听接口
        {
            @Override
            public void valueChanged(ListSelectionEvent e)  //要重写的方法
            {
                int b = list1.getLeadSelectionIndex(); //保存选中的项的索引（下标）
                ListModel<String> model = list1.getModel(); //获取列表框的项的模型
                itemName = model.getElementAt(b);//通过索引获得选中项的名字
                //事件 1 展示二级知识点的知识
                ta.setText("");
                //
                //?
                List<String> text = knowledge_point.get_knowledge_content(itemName, tx);
                Collections.sort(text);
                ta.setText(String.join("\n\n\n", text));
                //

            }
        }
        list1.addListSelectionListener(new myListener()); //添加监听器

        ta = new JTextArea();
        ta.setFont(new Font("宋体", Font.BOLD, 15));
        ta.append(String.join("\n", list));
        //ta.setEditable(false);
        ta.disable();
        ta.setDisabledTextColor(Color.black);
        this.add(ta,BorderLayout.CENTER);

        JPanel jp = new JPanel();
        jb = new JButton("自测");
        jb.setSize(100, 50);
        jp.add(jb);
        this.add(jp,BorderLayout.SOUTH);
        help = new JButton("问AI");
        help.setSize(100, 50);
        jp.add(help);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(itemName != null)
                {
                    KnowledgeCL.this.setVisible(false);
                    AnsweringWindow aw = new AnsweringWindow(itemName, tx, KnowledgeCL.this);
                }
            }
        });
        //选中的二级知识点String itemName
        //在jbutton的事件类內开始自测
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                aw = new AIHelpWindow();
                aw.setVisible(true);
            }
        });


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //AnsweringWindow aw = new AnsweringWindow("2.4 原型模型", tx, KnowledgeCL.this);

    }


    private JList list1;
    private JButton jb;
    private JButton help;
    JTextArea ta;
    String itemName;
    private Transaction tx;
    private AIHelpWindow aw;
}
