package data_manager;

import org.neo4j.graphdb.*;

import java.util.ArrayList;
import java.util.List;


//+++++++++++++++++++++++++++++++++++++++
//Transaction tx是用来和数据库交互的对象
//需要在外部由connector返回的graphDb创建
//注意:调用commit方法后,和tx有关的节点等均会失效
//+++++++++++++++++++++++++++++++++++++++

public class knowledge_point {
    //这是一级知识点和二级知识点之间的关系
    private static final RelationshipType rel = RelationshipType.withName("knowledge");
    //这是二级知识点和具体知识之间的关系
    private static final RelationshipType rel1 = RelationshipType.withName("knowledge_point");

    private static final RelationshipType content = RelationshipType.withName("content");

    private static final RelationshipType test = RelationshipType.withName("new_relationship");

    //根据一级知识点: 软件过程模型 获取二级知识点(2.x)   注: 可能用不上
    public static List<String> get_knowledge(String chapter, Transaction tx){ //用来返回知识点
        //根据传入的, 查找其包含的具体知识点, 组成list返回
        Label label = Label.label("chapter");
        List<String> knowledge_list = new ArrayList<>();
        Node parentNode = tx.findNode(label, "chapter_name", chapter); //三个参数为: 节点的标签, 节点的属性名, 节点的属性值

        if(parentNode != null){
            for(Relationship r : parentNode.getRelationships(Direction.OUTGOING, rel)){
                Node childNode = r.getOtherNode(parentNode);
                knowledge_list.add(childNode.getProperty("knowledge").toString()); //将二级知识点加入List中
            }
        }

        return knowledge_list;
    }
    //++++++++++++
    // 这个, 不需要了
    // 神经
    //++++++++++++
    /*
    //根据二级(2.x)获取对应的具体知识点
    public static List<String> get_knowledge_point(String knowledge_name, Transaction tx){ //用来返回知识点
        //根据传入的二级知识点, 查找其包含的具体知识点, 组成list返回
        Label label = Label.label("knowledge");
        List<String> knowledge_list = new ArrayList<>();
        Node parentNode = tx.findNode(label, "knowledge_name", knowledge_name); //三个参数为: 节点的标签, 节点的属性名, 节点的属性值

        if(parentNode != null){
            for(Relationship r : parentNode.getRelationships(Direction.OUTGOING, content)){
                Node childNode = r.getOtherNode(parentNode);
                knowledge_list.add(childNode.getProperty("content").toString()); //将二级知识点加入List中
            }
        }

        return knowledge_list;
    }*/

    //根据传入的知识点, 获取知识点的内容(二级知识点)
    public static List<String> get_knowledge_content(String knowledge_name, Transaction tx){
        //根据传入的二级知识点, 查找具体的知识点内容, 组成list返回
        Label label = Label.label("knowledge");
        List<String> knowledge_point_list = new ArrayList<>();
        Node parentNode = tx.findNode(label, "knowledge_name", knowledge_name); //三个参数为: 节点的标签, 节点的属性名, 节点的属性值

        if(parentNode != null){
            for(Relationship r : parentNode.getRelationships(Direction.OUTGOING, content)){
                Node childNode = r.getOtherNode(parentNode);
                knowledge_point_list.add(childNode.getProperty("content").toString()); //将知识点的具体内容加入List中
            }
        }

        return knowledge_point_list;
    }

    public static List<String> test_method(String node_name, Transaction tx){
        Label label = Label.label("test_node");
        List<String> knowledge_point_list = new ArrayList<>();
        Node parentNode = tx.findNode(label, "name", node_name); //三个参数为: 节点的标签, 节点的属性名, 节点的属性值

        if(parentNode != null){
            for(Relationship r : parentNode.getRelationships(Direction.OUTGOING, test)){
                Node childNode = r.getOtherNode(parentNode);
                knowledge_point_list.add(childNode.getProperty("name").toString()); //将知识点的具体内容加入List中
            }
        }

        return knowledge_point_list;
    }
}


//该类中的方法用于获取每一章对应的知识点
