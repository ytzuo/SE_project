package data_manager;
import org.neo4j.graphdb.*;
public class GraphDatabaseHelper {
    // 插入一个节点到数据库
    //GraphDatabaseService graphDb:这是一个连接到 Neo4j 数据库的对象
    //String label:这是你要创建的节点的标签（Label）
    //String propertyKey:这是你为节点设置的属性的键（Key）
    //String propertyValue:这是节点属性的值（Value）它对应于 propertyKey 所指定的属性键的实际值。
    public static Node insertNode(Transaction tx, String label, String propertyKey, String propertyValue) {
        try{
            // 创建一个节点，并设置标签
            Node node = tx.createNode(Label.label(label));
            // 设置节点的属性
            node.setProperty(propertyKey, propertyValue);

            return node;
        } catch (Exception e) {
            e.printStackTrace();  // 捕获并打印异常
            return null;
        }
    }


    // 插入一个关系到数据库
    //GraphDatabaseService graphDb这是一个表示 Neo4j 图数据库连接的对象
    //Node startNode这是关系的起始节点
    //Node endNode这是关系的结束节点
    //String relationshipType这是关系的类型（或标签）。在 Neo4j 中，关系有类型，用于描述两个节点之间的特定关系。
    public static Relationship insertRelationship(Transaction tx, Node startNode, Node endNode, String relationshipType) {
        try {
            // 创建一个关系
            Relationship relationship = startNode.createRelationshipTo(endNode, RelationshipType.withName(relationshipType));

            return relationship;
        } catch (Exception e) {
            e.printStackTrace();  // 捕获并打印异常
            return null;
        }
    }


    // 插入多个节点并设置属性（批量插入示例）
    //GraphDatabaseService graphDb这是一个表示 Neo4j 图数据库连接的对象
    //String label这是你要创建的节点的标签（Label）
    //String[] propertyKeys这是一个字符串数组，包含节点的属性键（Key）
    //String[] propertyValues 这是一个字符串数组，包含节点的属性值（Value）
    public static void insertMultipleNodes(Transaction tx, String label, String[] propertyKeys, String[] propertyValues) {
        try  {
            for (int i = 0; i < propertyKeys.length; i++) {
                Node node = tx.createNode(Label.label(label));
                node.setProperty(propertyKeys[i], propertyValues[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();  // 捕获并打印异常
        }
    }

    // 示例：创建一个节点并添加多个属性
    public static Node insertNodeWithMultipleProperties(Transaction tx, String label, String[] propertyKeys, String[] propertyValues) {
        try{
            Node node = tx.createNode(Label.label(label));
            for (int i = 0; i < propertyKeys.length; i++) {
                node.setProperty(propertyKeys[i], propertyValues[i]);
            }

            return node;
        } catch (Exception e) {
            e.printStackTrace();  // 捕获并打印异常
            return null;
        }
    }
    // 根据属性键和值查询节点
//GraphDatabaseService graphDb: Neo4j 数据库连接对象
//String label: 节点的标签（Label）
//String propertyKey: 节点的属性键
//Object propertyValue: 节点的属性值
    public static Node findNodeByProperty(Transaction tx, String label, String propertyKey, Object propertyValue) {
        try{
            // 使用资源获取节点
            ResourceIterator<Node> nodes = tx.findNodes(Label.label(label), propertyKey, propertyValue);
            if (nodes.hasNext()) {
                Node node = nodes.next();
                nodes.close();
                return node;
            } else {
                nodes.close();
                return null;  // 没有找到匹配的节点
            }
        } catch (Exception e) {
            e.printStackTrace();  // 捕获并打印异常
            return null;
        }
    }

}
