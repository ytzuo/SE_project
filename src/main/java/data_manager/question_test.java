package data_manager;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

import java.util.List;

public class question_test {
    public static void main(String[] args) {
        System.out.println("test begin");
        GraphDatabaseService db = connector.connect();
        Transaction tx = db.beginTx();
       /* List<String> l = knowledge_point.get_knowledge_content("2.4 原型模型", tx);
        for(String s : l){
            System.out.println(s);
        }*/

        List<Integer> ll = question.get_question_num("2.4 原型模型",tx);
        for(int i : ll){
            System.out.println(i);
        }
        List<Integer> ll1 = question.get_question_num("2.4 原型模型",tx);
        for(int i : ll1){
            System.out.println(i);
        }

        /*List<String> lll = question.get_select("2.4 原型模型", tx);
        for(String s : lll){
            System.out.println(s);
        }

        List<String> llll = question.get_summary("2.4 原型模型", tx);
        for(String s: llll){
            System.out.println(s);
        }
        tx.commit();
        tx.close();
        System.out.println("test finish");*/
    }
}
