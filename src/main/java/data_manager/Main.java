package data_manager;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
public class Main {
    public static void main(String[] args) {
        System.out.println("test begin");
        GraphDatabaseService db = connector.connect();
        Transaction tx = db.beginTx();
        Node test_parent = GraphDatabaseHelper.insertNode(tx, "test_node", "name", "test_parent");
        Node test_child = GraphDatabaseHelper.insertNode(tx, "test_node", "name", "test_child");
        GraphDatabaseHelper.insertRelationship(tx, test_parent, test_child, "test_relationship");
        Node n = GraphDatabaseHelper.findNodeByProperty(tx, "test_node", "name", "test_child");
        Node new_child = GraphDatabaseHelper.insertNode(tx, "test_node", "name", "new_child");
        GraphDatabaseHelper.insertRelationship(tx, n, new_child, "new_relationship");
        tx.commit();
        tx.close();
        System.out.println("test finish");
    }
}