package data_manager;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Transaction;

import java.util.ArrayList;
import java.util.List;


//++++++++++++++++++++++++++++++++++
//Transaction tx是用来和数据库交互的对象
//需要在外部由connector返回的graphDb创建
//++++++++++++++++++++++++++++++++++

public class knowledge_point {
    public static List<String> get_knowledge(String chapter, Transaction tx){
        //根据传入的章节, 查找其包含的知识点, 组成list返回
        Label label = Label.label("chapter");
        List<String> knowledge_list = new ArrayList<>();
        //TODO 获取并返回指定章节的知识点

        return knowledge_list;
    }

    public static List<String> get_knowledge_point(String knowledge_point, Transaction tx){
        //根据传入的知识点, 查找具体的知识点内容, 组成list返回
        Label label = Label.label("knowledge");
        List<String> knowledge_point_list = new ArrayList<>();
        //TODO 获取并返回指定的知识点的内容
        return knowledge_point_list;
    }
}

//该类中的方法用于获取每一章对应的知识点
