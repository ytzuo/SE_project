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
            }, new String[]{
                            "选择1: 软件生命周期包含的阶段有: (  ), 系统设计, 实现编码, 测试, 部署, 维护, 退役\nA. 过程规划  B. 需求分析  C. 工具选择  D. 编程实现",
                            "选择2: 一个软件过程模型适用于所有的软件开发(  )\nA. 正确  B. 错误"

            }, new String[]{
                    "简答1: 请写出几个常见的软件过程模型"
            }, new String[]{"B","B",
            "参考答案: 瀑布模型、增量模型、原型模型、螺旋模型、喷泉模型、统一软件过程 (RUP)、敏捷开发"});

            insertKnowledgeWithPoints(tx, chapterNode, "2.2 瀑布模型", new String[]{
                    "阶段划分: 需求分析、系统设计、编码、测试、部署、维护",
                    "特点: 阶段间具有顺序和不可逆性, 强调文档驱动, 每个阶段的阶段性成果是进入下一个阶段的前提条件",
                    "优缺点: 开发流程具有顺序性, 简单, 但是客户参与有限, 对需求变更的适应性差"
            }, new String[]{
                            "选择1: 在瀑布模型中，哪一个特性决定了它的开发流程是“顺序进行”的？\nA. 文档驱动   B. 客户参与有限   C. 阶段间的不可逆性",
                            "选择2: 瀑布模型的阶段有: 需求分析、系统设计、(  ) 、测试、部署、维护\nA. 交付,客户    B. 论证可行性   C.需求修改    D.编码实现",
                            "选择3: 瀑布模型能否适应客户频繁变更的需求?\nA. 可以     B. 不可以 "
            }, new String[]{
                    "简答1: 描述瀑布模型的基本流程",
                    "简答2: 瀑布模型的主要缺点是什么？。"
            }, new String[]{"C","D","B",
            "参考答案：瀑布模型的流程是顺序的，分为需求分析、系统设计、实现、测试、部署和维护六个阶段。每个阶段必须完成并通过审核后才能进入下一阶段，且阶段之间没有回溯。",
            "参考答案: 主要缺点是对需求变更的适应性差。一旦某阶段完成，若要回溯修改，成本高昂。因此，瀑布模型不适合需求不稳定的项目"});

            insertKnowledgeWithPoints(tx, chapterNode, "2.3 增量模型", new String[]{
                    "阶段划分: 分为多个阶段, 每个阶段都是一个增量, 每个增量都需要进行开发、测试与交付, 不断迭代",
                    "特点: 支持迭代开发, 分阶段开发, 逐步交付，有对需求变更的适应性",
                    "优缺点: 发布快速, 可以适应需求的变更, 风险较低. 但是可能暴露出开发的不足"
            }, new String[]{
                            "选择1: 增量模型的开发分为(  )迭代阶段\nA. 单个     B. 多个 ",
                            "选择2: 增量模型开发中, 每次迭代的内容是否相同?\nA. 是       B. 否 "
            }, new String[]{
                    "简答1: 与瀑布模型相比，增量模型的优势是什么？",
                    "简答2: 增量模型与瀑布模型的主要区别是什么？"
            }, new String[]{"B","A",
            "参考答案：增量模型在开发早期就能交付部分功能，让用户可以提前使用和反馈。与瀑布模型相比，它能更快地响应需求变更，减少风险和成本，适合需求可能随时间调整的项目。",
            "参考答案: 增量模型将项目划分为多个小的可交付部分，每个部分在独立的增量中实现，而瀑布模型则是将项目分阶段进行，阶段间不可逆。增量模型更具灵活性，能够较早交付部分功能供用户使用。"});

            insertKnowledgeWithPoints(tx, chapterNode, "2.4 原型模型", new String[]{
                    "阶段划分: 需求收集, 快速原型构架, 用户反馈, 原型迭代, 实现和测试, 交付和维护",
                    "特点: 以客户为中心, 快速向客户反馈, 多个迭代周期迭代开发, 灵活适应需求调整",
                    "优缺点: 可以帮助用户明确需求, 可能把开发中的不成熟暴露给客户, 多次迭代可能延长开发时间"
            }, new String[]{
                            "选择1: 原型模型以什么为核心(  )?\nA. 客户    B. 用例    C. 文档    D.产出",
                            "选择2: 原型模型能否用于客户需求不明确的场景? (   )\nA. 不能    B. 能  ",
                            "选择3: 原型的特点有(   )\nA. 不可交互   B.较为简单   C. 成本高 "
            },new String[]{
                    "简答1: 原型模型如何帮助减少开发过程中的需求不明确问题？",
                    "简答2: 原型模型有哪些局限性？"
            }, new String[]{"A","B","B",
            "参考答案: 通过快速制作一个可交互的原型，用户可以直观地了解产品，从而更明确地提出需求调整，帮助开发团队在正式开发前更好地理解需求。",
            "参考答案：原型模型可能导致过多的反复修改，延长开发时间；另外，用户可能会将原型误认为最终系统，导致对交付时间的误解。"});

            insertKnowledgeWithPoints(tx, chapterNode, "2.5 螺旋模型", new String[]{
                    "阶段划分: 客户沟通, 快速计划, 快速建模, 构建原型, 部署反馈五个阶段不断循环",
                    "特点: 结合了瀑布模型和增量模型, 以风险为中心, 每个迭代中都有风险控制 ,用户参与风险分析和决策,成本较高",
                    "优缺点: 适用于复杂高风险的项目, 但是开发周期可能过长, 成本较高"
            }, new String[]{
                            "选择1: 螺旋模型的(  )迭代中都有风险控制\nA. 每个     B. 并非每个 ",
                            "选择2: 螺旋模型以(  )为中心\nA. 用户   B. 文档    C. 价值    D. 风险"
            }, new String[]{
                    "简答1: 螺旋模型与增量模型的不同之处是什么？",
                    "简答2: 螺旋模型的适用场景是什么？"
            }, new String[]{"A", "D",
                    "参考答案: 增量模型注重逐步实现功能模块，适合需求逐步明朗的项目，而螺旋模型则侧重于风险控制和复杂性管理，并通过每次迭代的风险评估来确保项目的安全性，适合风险高的项目。",
                    "参考答案：螺旋模型适用于大型、复杂、需求可能会变动的项目。它结合了增量和风险分析的优点，适合那些需要定期调整和风险控制的开发环境"}
            );

            insertKnowledgeWithPoints(tx, chapterNode, "2.6 喷泉模型", new String[]{
                    "阶段划分: 需求收集, 概念化, 规格说明, 实现, 测试, 交付部署, 维护, 由于非线性和并行的特点, 可以进行多重需求的处理",
                    "特点: 适应性强, 各阶段可以并行进行, 强调早期阶段的原型建设和评审",
                    "优缺点: 灵活适应需求变化, 可以处理多重需求, 但是可能导致开发资源的不合理分配"
            }, new String[]{
                            "选择1: 喷泉模型是(  )模型\nA. 非线性      B. 线性    C. 高效   D. 易实现 ",
                            "选择2: 喷泉模型中哪个阶段最容易在后续进行修改?\nA. 测试    B. 概念化    C. 需求收集    D. 实现  "
            }, new String[]{
                    "简答1: 喷泉模型与瀑布模型的差异在哪里？",
                    "简答2: 喷泉模型的缺点是什么？"
            }, new String[]{"A", "B",
                    "参考答案: 喷泉模型强调各阶段的反馈机制，阶段间可以并行和重叠，而瀑布模型则是严格的线性顺序过程，阶段间不可逆。",
                    "参考答案: 喷泉模型可能会导致开发资源分配不均，尤其是当需求变更频繁时，会影响项目的进度和资源的合理利用。"});

            insertKnowledgeWithPoints(tx, chapterNode, "2.7 统一过程模型 (RUP)", new String[]{
                    "阶段划分: 需求获取、分析、设计、实现、测试、发布与维护",
                    "特点: 强调迭代开发，文档和评审过程, 强调过程管理和需求管理",
                    "优缺点: 适用于大型复杂项目，但要求高的过程管理和高成本的过程定义"
            }, new String[]{
                            "选择1: 统一过程模型 (RUP) 是一种(  )开发过程\nA. 迭代式   B. 瀑布式    C. 原型式 ",
                            "选择2: 统一过程模型的核心原则是什么？\nA. 计划详细   B. 文档详尽    C. 迭代开发  "
            }, new String[]{
                       "简答1: 统一过程模型 (RUP) 和传统瀑布模型相比，有何不同？",
                       "简答2: 统一过程模型的主要特点是什么？。"
            }, new String[]{"A", "C",
                    "参考答案: 统一过程模型强调多次迭代和评审，通过反复的需求、设计、实现和测试等阶段的循环来逐步完善项目。",
                    "参考答案: 统一过程模型的主要特点是迭代开发、强调需求和过程管理，以及通过定期的评审来保证项目的进展"});

            insertKnowledgeWithPoints(tx, chapterNode, "2.8 敏捷开发模型", new String[]{
                    "阶段划分: 规划、设计、编码、测试、交付和维护，在每个阶段中进行迭代",
                    "特点: 强调快速反馈和适应性变化，用户参与，频繁交付工作产品",
                    "优缺点: 灵活性强，能够快速响应客户需求的变化，但可能在过程管理上有较高的难度"
            }, new String[]{
                            "选择1: 敏捷开发的核心原则是(  )\nA. 延迟交付    B. 灵活应变   C. 文档驱动",
                            "选择2: 敏捷开发强调(  )的参与\nA. 客户   B. 开发团队  C. 规划人员  "
            }, new String[]{
                    "简答1: 敏捷开发的优势是什么？",
                    "简答2: 敏捷开发的主要缺点是什么？"
            }, new String[]{"B","A",
                    "参考答案: 敏捷开发强调快速的反馈周期，能迅速应对变化的需求，通常能提高客户满意度并加速产品上市。",
                    "参考答案: 敏捷开发可能导致过程的管理较松散，且对团队的自律性要求较高，尤其是在较大的团队中，容易出现协调上的困难。"});
            // 提交事务
            tx.commit();
            System.out.println("知识点数据和习题插入成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 辅助方法，用于插入二级知识点及具体知识点
    private void insertKnowledgeWithPoints(Transaction tx, Node chapterNode, String knowledgeName, String[] knowledgePoints, String[] selects, String[] summaries, String[] answers) {
        // 插入二级知识点节点
        Node knowledgeNode = GraphDatabaseHelper.insertNode(tx, "knowledge", "knowledge_name", knowledgeName);
        // 建立与一级知识点的关系
        GraphDatabaseHelper.insertRelationship(tx, chapterNode, knowledgeNode, "knowledge");
        // 插入具体知识点
        for (String content : knowledgePoints) {
            Node pointNode = GraphDatabaseHelper.insertNode(tx, "knowledge_point", "content", content);
            // 建立与二级知识点的关系
            GraphDatabaseHelper.insertRelationship(tx, knowledgeNode, pointNode, "content");
        }
        // 插入习题
        int i = 0;
        //Node exercisesNode = GraphDatabaseHelper.insertNode(tx, "exercises", "exercise_name", knowledgeName + " 习题");
        //GraphDatabaseHelper.insertRelationship(tx, knowledgeNode, exercisesNode, "HAS_EXERCISES");
        for (String select : selects) {
            Node exerciseNode = GraphDatabaseHelper.insertNodeWithMultipleProperties(tx, "exercise",
                    new String[]{"content","answer"}, new String[]{select, answers[i]});
            GraphDatabaseHelper.insertRelationship(tx, knowledgeNode, exerciseNode, "select");
            i++;
        }

        for (String summary : summaries) {
            Node exerciseNode = GraphDatabaseHelper.insertNodeWithMultipleProperties(tx, "exercise",
                    new String[]{"content","answer"}, new String[]{summary, answers[i]});
            GraphDatabaseHelper.insertRelationship(tx, knowledgeNode, exerciseNode, "summary");
            i++;
        }
        //插入习题答案
    }
    public static void main(String[] args) {
        System.out.println("test begin");
        GraphDatabaseService db = connector.connect();
        GraphDatabaseExample ex = new GraphDatabaseExample(db);
        ex.insertKnowledgeData();
        System.out.println("test finish");
    }
}
