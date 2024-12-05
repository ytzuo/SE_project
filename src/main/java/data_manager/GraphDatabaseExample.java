package data_manager;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
public class GraphDatabaseExample {
    private GraphDatabaseService graphDb; // 数据库服务
    // 构造函数，接受 GraphDatabaseService
    public GraphDatabaseExample(GraphDatabaseService graphDb) {
        this.graphDb = graphDb;
    }
    // 插入知识点数据的方法
    public void insertKnowledgeData() {
        try (Transaction tx = graphDb.beginTx()) {
            // 插入一级知识点节点
            Node chapterNode = GraphDatabaseHelper.insertNode(tx, "chapter", "chapter_name", "软件过程模型");
            // 插入二级知识点及其具体知识点和关系
            insertKnowledgeWithPoints(tx, chapterNode, "2.1 概述", new String[]{
                    "软件生命周期的阶段: 需求分析, 系统设计, 实现编码, 测试, 部署, 维护, 退役",
                    "软件质量与软件过程的关系: 过程定义质量标准, 过程保证质量, 过程能适应变化, 过程改进提高质量, 过程包括风险管理, 过程提高用户满意度",
                    "常见的软件过程模型: 瀑布模型、增量模型、原型模型、螺旋模型、喷泉模型、统一软件过程 (RUP)、敏捷开发"
            });
            insertKnowledgeWithPoints(tx, chapterNode, "2.2 瀑布模型", new String[]{
                    "阶段划分: 需求分析、系统设计、编码、测试、部署、维护",
                    "特点: 阶段间具有顺序和不可逆性, 强调文档驱动, 每个阶段的阶段性成果是进入下一个阶段的前提条件",
                    "优缺点: 开发流程具有顺序性, 简单, 但是客户参与有限, 对需求变更的适应性差"
            });
            insertKnowledgeWithPoints(tx, chapterNode, "2.3 增量模型", new String[]{
                    "阶段划分: 分为多个阶段, 每个阶段都是一个增量, 每个增量都需要进行开发、测试与交付, 不断迭代",
                    "特点: 支持迭代开发, 分阶段开发, 逐步交付，有对需求变更的适应性",
                    "优缺点: 发布快速, 可以适应需求的变更, 风险较低. 但是可能暴露出开发的不足"
            });
            insertKnowledgeWithPoints(tx, chapterNode, "2.4 原型模型", new String[]{
                    "阶段划分: 需求收集, 快速原型构架, 用户反馈, 原型迭代, 实现和测试, 交付和维护",
                    "特点: 以客户为中心, 快速向客户反馈, 多个迭代周期迭代开发, 灵活适应需求调整",
                    "优缺点: 可以帮助用户明确需求, 可能把开发中的不成熟暴露给客户, 多次迭代可能延长开发时间"
            });
            insertKnowledgeWithPoints(tx, chapterNode, "2.5 螺旋模型", new String[]{
                    "阶段划分: 客户沟通, 快速计划, 快速建模, 构建原型, 部署反馈五个阶段不断循环",
                    "特点: 结合了瀑布模型和增量模型, 以风险为中心, 每个迭代中都有风险控制 ,用户参与风险分析和决策,成本较高",
                    "优缺点: 适用于复杂高风险的项目, 但是开发周期可能过长, 成本较高"
            });
            insertKnowledgeWithPoints(tx, chapterNode, "2.6 喷泉模型", new String[]{
                    "阶段划分: 需求收集, 概念化, 规格说明, 实现, 测试, 交付部署, 维护, 由于非线性和并行的特点, 可以在各个阶段间反馈迭代",
                    "特点: 非线性, 迭代和并行开发, 允许甚至鼓励需求变更, 支持增量交付, 客户参与",
                    "优缺点: 灵活可适应需求变更, 开发过程分为多次迭代, 风险更低, 但是前期需求可能会不明确, 项目版本和开发周期可能难以预测"
            });
            insertKnowledgeWithPoints(tx, chapterNode, "2.7 统一软件过程 (RUP)", new String[]{
                    "阶段划分: 启动, 精化, 构造, 过渡",
                    "特点: 风险驱动, 支持迭代和增量, 用例驱动, 作为框架具有高可配置度",
                    "优缺点: 框架灵活可自由定制, 迭代开发易于应对需求修改, 有风险管理和需求管理支持, 但是迭代可能导致项目周期过长, 统一软件过程较为复杂, 成本较高"
            });
            insertKnowledgeWithPoints(tx, chapterNode, "2.8 敏捷开发", new String[]{
                    "核心价值观: 个体和互动高于流程和工具, 可工作的软件高于详尽的文档, 客户合作高于合同谈判, 响应变化高于遵循计划",
                    "具体实现方法: Scrum, Kanban, 极限编程, 精益软件开发, 特性驱动开发",
                    "优缺点: 快速响应变化, 增强团队合作, 透明度高, 但是难以量化管理, 需要自我管理, 需要更多前期投入"
            });
            // 提交事务
            tx.commit();
            System.out.println("知识点数据插入成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 辅助方法，用于插入二级知识点及具体知识点
    private void insertKnowledgeWithPoints(Transaction tx, Node chapterNode, String knowledgeName, String[] knowledgePoints) {
        // 插入二级知识点节点
        Node knowledgeNode = GraphDatabaseHelper.insertNode(tx, "knowledge", "knowledge_name", knowledgeName);
        // 建立与一级知识点的关系
        GraphDatabaseHelper.insertRelationship(tx, chapterNode, knowledgeNode, "HAS_KNOWLEDGE");
        // 插入具体知识点
        for (String content : knowledgePoints) {
            Node pointNode = GraphDatabaseHelper.insertNode(tx, "knowledge_point", "content", content);
            // 建立与二级知识点的关系
            GraphDatabaseHelper.insertRelationship(tx, knowledgeNode, pointNode, "HAS_POINT");
        }
    }
    public static void main(String[] args) {
        System.out.println("test begin");
        GraphDatabaseService db = connector.connect();
        GraphDatabaseExample ex = new GraphDatabaseExample(db);
        ex.insertKnowledgeData();
        System.out.println("test finish");
    }
}
