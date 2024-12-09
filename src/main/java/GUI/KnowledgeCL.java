package GUI;
import data_manager.connector;
import data_manager.knowledge_point;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Vector;




public class KnowledgeCL extends JFrame{

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new KnowledgeCL();
    }
    public KnowledgeCL() {
        super("Welcome");//标题
        this.setBounds(100, 100, 600, 300);//设置页面
        this.setLayout(new BorderLayout()); // 使用 BorderLayout 布局管理器


        //List<String> list = new ArrayList<>();
        //list.add("beijing");
        //list.add("shanghai");
        String beginning = "2.4 原型模型";
        GraphDatabaseService db = connector.connect();
        Transaction tx = db.beginTx();
        List<String> list = knowledge_point.get_knowledge(beginning, tx);
        String []jg = (String[]) list.toArray(new String[list.size()]);
        //假装是二级知识点

        this.list1 = new JList(jg);
        list1.setBorder(BorderFactory.createTitledBorder("请选择需要学习的二级知识点"));
        this.add(list1,BorderLayout.WEST);
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
                GraphDatabaseService db = connector.connect();
                Transaction tx = db.beginTx();
                ta.setText(String.join("\n", knowledge_point.get_knowledge_content(itemName, tx)));
                //

            }
        }
        list1.addListSelectionListener(new myListener()); //添加监听器

        ta = new JTextArea();
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


        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                KnowledgeCL.this.dispose();

            }
        });
        //选中的二级知识点String itemName
        //在jbutton的事件类內开始自测

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private JList list1;
    private JButton jb;
    JTextArea ta;
    String itemName;
}
