package data_manager;

import org.neo4j.graphdb.*;

import java.util.ArrayList;
import java.util.List;

//+++++++++++++++++++++++++++++++++++
//Transaction tx是用来和数据库交互的对象
//需要在外部由connector返回的graphDb创建
//+++++++++++++++++++++++++++++++++++

//+++++++++++++++++++++++++++++++++++
//每个题目的节点的父节点均为chapter节点
//且每个题目节点都有属性question和answer
//+++++++++++++++++++++++++++++++++++

public class question {


    public static List<Integer> get_question_num(String knowledge, Transaction tx){
        //可以用来支持随机选题, 由于数据库中存储的题目数可能超过一章后习题的数量
        Label label = Label.label("knowledge");
        Node parentNode = tx.findNode(label, "knowledge_name", knowledge); //三个参数为: 节点的标签, 节点的属性名, 节点的属性值
        List<Integer> question_num_list = new ArrayList<>();
        int select_num = 0;
        //int blank_num = 0;
        int summary_num = 0;
        if(parentNode != null){ //计算各个类型题目的数量
            for(Relationship r : parentNode.getRelationships(Direction.OUTGOING,RelationshipType.withName("select"))) select_num++;
            for(Relationship r : parentNode.getRelationships(Direction.OUTGOING,RelationshipType.withName("summary"))) summary_num++;
        }
        question_num_list.add(select_num);
        //question_num_list.add(blank_num);
        question_num_list.add(summary_num);

        return question_num_list;
    }

    public static List<String> get_select(String knowledge, Transaction tx){
        Label label = Label.label("knowledge");
        Node parentNode = tx.findNode(label, "knowledge_name", knowledge); //参数含义同上
        List<String> select_list = new ArrayList<>();
        if(parentNode != null){
            for(Relationship r : parentNode.getRelationships(Direction.OUTGOING,RelationshipType.withName("select"))) {
                Node childNode = r.getOtherNode(parentNode);
                String question = childNode.getProperty("content").toString();
                String answer = childNode.getProperty("answer").toString();
                select_list.add(question);
                select_list.add(answer);
            }
        }

        return select_list;
    }



   /* public static List<String> get_blank(String knowledge, Transaction tx){
        Label label = Label.label("chapter");
        Node parentNode = tx.findNode(label, "knowledge_name", knowledge); //参数含义同上
        List<String> blank_list = new ArrayList<>();
        if(parentNode != null){
            for(Relationship r : parentNode.getRelationships(Direction.OUTGOING,rel.blank)) {
                Node childNode = r.getOtherNode(parentNode);
                String question = childNode.getProperty("question").toString();
                String answer = childNode.getProperty("answer").toString();
                blank_list.add(question);
                blank_list.add(answer);
            }
        }

        return blank_list;
    }
*/
    public static List<String> get_summary(String knowledge, Transaction tx){
        Label label = Label.label("knowledge");
        Node parentNode = tx.findNode(label, "knowledge_name", knowledge); //参数含义同上
        List<String> summary_list = new ArrayList<>();
        if(parentNode != null){
            for(Relationship r : parentNode.getRelationships(Direction.OUTGOING, RelationshipType.withName("summary"))) {
                Node childNode = r.getOtherNode(parentNode);
                String question = childNode.getProperty("content").toString();
                String answer = childNode.getProperty("answer").toString();
                summary_list.add(question);
                summary_list.add(answer);
            }
        }

        return summary_list;
    }
}

//该类中包含的方法用于获取考题和答案