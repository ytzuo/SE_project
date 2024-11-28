package data_manager;

import org.neo4j.graphdb.Transaction;

import java.util.ArrayList;
import java.util.List;

//++++++++++++++++++++++++++++++++++
//Transaction tx是用来和数据库交互的对象
//需要在外部由connector返回的graphDb创建
//++++++++++++++++++++++++++++++++++

public class question {
    public static List<Integer> get_question_num(String chapter, Transaction tx){
        //可以用来支持随机选题, 由于数据库中存储的题目数可能超过一章后习题的数量
        List<Integer> question_num_list = new ArrayList<>();
        //TODO 根据章节获取对应各类题目的数量, 依次存入list后返回
        return question_num_list;
    }

    public static List<String> get_select(String chapter, Transaction tx){
        List<String> select_list = new ArrayList<>();
        //TODO 根据章节获取对应的选择题, 将题干,选项和答案按顺序存入list并返回
        return select_list;
    }

    public static List<String> get_blank(String chapter, Transaction tx){
        List<String> blank_list = new ArrayList<>();
        //TODO 根据章节获取对应的填空题, 将题干和答案按顺序存入list并返回
        return blank_list;
    }

    public static List<String> get_summary(String chapter, Transaction tx){
        List<String> summary_list = new ArrayList<>();
        //TODO 根据章节获取对应的简述题, 将题干和答案按顺序存入list并返回
        return summary_list;
    }
}

//该类中包含的方法用于获取考题和答案