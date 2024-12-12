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
                    "1. 软件生命周期的阶段: 需求分析, 系统设计, 实现编码, 测试, 部署, 维护, 退役",
                    "2. 软件质量与软件过程的关系: 过程定义质量标准, 过程保证质量, 过程能适应变化, 过程改进提高质量, 过程包括风险管理, 过程提高用户满意度",
                    "3. 常见的软件过程模型: 瀑布模型、增量模型、原型模型、螺旋模型、喷泉模型、统一软件过程 (RUP)、敏捷开发"
            }, new String[]{
                            "选择1: 软件生命周期包含的阶段有: (  ), 系统设计, 实现编码, 测试, 部署, 维护, 退役\nA. 过程规划  B. 需求分析  C. 工具选择  D. 编程实现",
                            "选择2: 一个软件过程模型适用于所有的软件开发(  )\nA. 正确  B. 错误"

            }, new String[]{
                    "简答1: 请写出几个常见的软件过程模型"
            }, new String[]{"B","B",
            "参考答案: 瀑布模型、增量模型、原型模型、螺旋模型、喷泉模型、统一软件过程 (RUP)、敏捷开发"});

            insertKnowledgeWithPoints(tx, chapterNode, "2.2 瀑布模型", new String[]{
                    "1. 瀑布模型（Waterfall Model）是一种经典的软件开发过程模型，它将软件开发过程划分为一系列阶段性的任务，每个阶段完成后才能进入下一个阶段。\n" +
                            "这个模型因其过程像瀑布一样逐级下降而得名。",
                    "2. 阶段划分: 需求分析、系统设计、编码、测试、部署、维护",
                    "3. 特点:  线性顺序：瀑布模型强调按顺序执行各个阶段，每个阶段都有明确的开始和结束。\n" +
                            "   阶段性：通常包括需求分析、设计、编码、测试和维护等阶段。\n" +
                            "   文档驱动：在瀑布模型中，文档扮演着非常重要的角色，每个阶段结束时都需要产出相应的文档。",
                    "4. 优点: 清晰的开发流程，易于管理和监控。\n" +
                            "   每个阶段都有明确的产出物，便于质量控制。\n" +
                            "   适合于需求稳定且变化不大的项目。",
                    "5. 缺点： 对需求变更的适应性差，需求一旦确定，后期修改成本高。\n" +
                            "   客户参与度低，通常只能在项目后期看到成品。\n" +
                            "   风险发现晚，缺陷可能在项目后期才被发现，导致修复成本增加。",
                    "6. 适用场景：适用于需求明确的情况：瀑布模型适用于需求明确且变化不大的项目，因为一旦需求确定，后续的开发工作就相对固定。"
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
                    "1. 增量模型（Incremental Model）是一种软件开发过程模型，它将软件项目分解成多个小的、可管理的部分，这些部分被称为增量。\n" +
                            "   每个增量都是软件的一个独立且完整的版本，包含了一组特定的功能。\n" +
                            "   这种模型允许软件开发在多个阶段进行，每个阶段完成后，客户可以评估并提供反馈，从而逐步完善产品。",
                    "2. 阶段划分: 分为多个阶段, 每个阶段都是一个增量, 每个增量都需要进行开发、测试与交付, 不断迭代",
                    "3. 特点: 迭代开发：增量模型采用迭代式的开发方式，每个迭代都会增加新的功能。每个迭代都需要进行开发、测试和交付，并且需要及时集成到系统中。\n" +
                            "   分阶段开发：增量模型将整个软件系统分成若干个小的部分，每个部分都是一个增量。每个增量都需要进行开发、测试和交付，直到整个系统完成。\n" +
                            "   需求变更适应性：增量模型可以适应需求变化和不确定性。每个迭代都会增加新的功能，同时可以根据用户的反馈和需求进行调整，从而满足用户的需求。\n" +
                            "   反馈机制：增量模型需要不断地集成和测试，需要不断地沟通和协作。这可以提高团队的协作和沟通能力，从而及时发现和解决问题。\n" +
                            "   风险管理：增量模型需要不断地进行测试和集成，可以及时发现和解决问题，从而降低项目的风险。\n" +
                            "   模块化：将待开发的软件系统模块化，可以分批次地提交软件产品，使用户可以及时了解软件项目的进展。\n" +
                            "   开发顺序灵活性：开发人员可以对组件的实现顺序进行优先级排序，先完成需求稳定的核心组件。当组件的优先级发生变化时，还能及时地对实现顺序进行调整。",
                    "4. 优点:  风险分散：通过分阶段开发，可以降低项目失败的风险。\n" +
                            "   需求适应性：能够更好地适应需求变化，提高项目的灵活性。\n" +
                            "   客户参与：客户可以在整个开发过程中提供反馈，提高产品的满意度。\n" +
                            "   快速交付：可以更快地向市场交付产品，满足市场的需求。",
                    "5. 缺点:  成本增加：由于需要多次迭代和集成，可能会导致项目成本的增加。\n" +
                            "   管理复杂性：增量模型需要更复杂的项目管理，以确保各个增量的协调和一致性。\n" +
                            "   技术挑战：增量集成可能会带来技术挑战，如兼容性问题和集成测试等。",
                    "6. 适用场景：增量模型适用于那些需求不完全明确、需求动态变化或者需要逐步交付的项目。\n" +
                            "   通过增量模型，可以逐步构建和完善软件系统，同时允许客户在开发过程中提供反馈，以确保最终产品更符合用户的实际需求。"
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
                    "1. 原型模型是一种软件开发模型，其核心思想是在初步需求分析之后，快速向客户展示一个软件产品原型，\n" +
                            "   通过客户的试用和反馈，收集意见，修改原型，再让客户试用，如此反复循环，直到客户确认为止。",
                    "2. 阶段划分: 需求收集, 快速原型构架, 用户反馈, 原型迭代, 实现和测试, 交付和维护",
                    "3. 特点: 快速迭代和反馈：原型模型允许团队快速理解和验证需求，减少后期修改成本。\n" +
                            "   用户参与：用户在开发过程中起到了关键作用，他们的反馈直接影响了产品的设计和开发。\n" +
                            "   适应需求变化：原型模型可以快速响应需求变化，适用于需求不稳定、开发周期短的项目。\n" +
                            "   降低风险：通过早期的原型展示和测试，可以及时发现和修正错误，避免了在开发后期进行大规模修改的风险。",
                    "4. 优点: 提高开发效率和用户满意度，减少风险和成本。\n" +
                            "   用户能够很早就感觉到实际系统的“模式”，开发者可以很快地建造出一些供以后实际开发的“模型”。\n" +
                            "   增加用户与开发人员的交流，用户在项目开发中占主导作用。",
                    "5. 缺点:  原型设计是一个缓慢且耗时的过程，可能会增加开发成本。\n" +
                            "   客户可能会对原型的完成度有过高的期待，误以为原型的功能和性能就是最终产品的标准。\n" +
                            "   维护原型可能消耗资源，对于技术层面远大于其分析层面的问题不宜使用原型法。",
                    "6. 适用场景：原型模型适用于需求不明确或者预计会有较多变化的项目，可以帮助用户和开发者较快速地获取双方理解一致的需求，但不是最终交付的软件产品。\n" +
                            "   根据运用原型的目的和方式不同，原型模型可以分为快速原型模型（抛弃型）和原型进化模型（渐进型）。"
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
                    "1. 螺旋模型是一种迭代式的软件开发过程模型，它结合了瀑布模型的系统化和快速原型模型的迭代特性。",
                    "2. 阶段划分: 客户沟通, 快速计划, 快速建模, 构建原型, 部署反馈五个阶段不断循环",
                    "3. 特点: 风险驱动：螺旋模型将风险管理置于软件开发过程的核心，在每个迭代周期中都进行风险分析和评估，并采取必要的措施以减轻或消除风险。\n" +
                            "   迭代式开发：模型采用迭代式开发，即在每个阶段中都有反馈和迭代，有助于快速发现和纠正问题。\n" +
                            "   持续评估：在每个阶段中进行评估，以确保项目进展符合预期，如果需要，可以进行调整和变更。\n" +
                            "   灵活性：可以根据项目需求进行定制和调整，更好地满足客户需求。\n" +
                            "   沟通和协作：要求开发团队与客户之间进行持续的沟通和协商，确保项目的透明度和可见性。",
                    "4. 优点:  设计上的灵活性：可以在项目的各个阶段进行变更。\n" +
                            "   成本计算简单：以小的分段来构建大型系统，使成本计算变得简单容易。\n" +
                            "   客户参与：客户始终参与每个阶段的开发，保证了项目不偏离正确方向以及项目的可控性。\n" +
                            "   透明度：随着项目推进，客户始终掌握项目的最新信息，从而能够和管理层有效地交互。\n" +
                            "   质量保证：强调各个开发阶段的质量。",
                    "5. 缺点: 成本和时间：螺旋模型的迭代过程可能导致项目成本和时间的增加。\n" +
                            "   管理复杂性：需要进行详细的风险管理和评估，增加了项目管理的复杂性。\n" +
                            "   对团队要求高：要求团队成员具备较高的技能和经验，以应对复杂的开发过程。",
                    "6. 适用场景：螺旋模型特别适用于需求不明确或大型软件系统的开发，尤其是那些复杂度高、风险大的项目。它适合于那些需要严格风险管理和客户持续参与的项目。"
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
                    "1. 喷泉模型是一种面向对象的软件开发迭代模型，它强调开发过程中的迭代性和无间隙性。",
                    "2. 阶段划分: 需求收集, 概念化, 规格说明, 实现, 测试, 交付部署, 维护, 由于非线性和并行的特点, 可以进行多重需求的处理",
                    "3. 特点: 迭代开发：喷泉模型采用迭代的方式进行软件开发，每个迭代周期内完成一部分功能的开发和测试。\n" +
                            "   需求变更：在喷泉模型中，需求变更是被允许的，甚至在某些情况下是被鼓励的，使得项目能够更好地适应市场和技术的变化。\n" +
                            "   增量交付：喷泉模型支持增量交付，即在每个迭代周期结束时，客户可以接收到部分完成的产品，从而更早地获得产品价值。\n" +
                            "   风险管理：通过迭代开发，喷泉模型能够有效地识别和降低项目风险，提高项目的成功率。\n" +
                            "   客户参与：喷泉模型强调客户参与，客户可以在开发过程中提供反馈，帮助团队更好地理解需求和优化产品。\n" +
                            "   无间隙性：喷泉模型中各阶段没有明显的界限，开发人员可以同步进行开发，提高了软件项目开发效率，节省开发时间。",
                    "4. 优点: 提高效率：由于各个阶段没有明显的界限，开发人员可以同时进行多步骤，故软件项目开发效率高，节省开发时间。\n" +
                            "   适应变化：软件的某个部分通常被重复多次，相关对象在每次迭代中随之加入渐进的软件成分，使得模型可以适应需求的变化。",
                    "5. 缺点: 项目管理难度：由于喷泉模型的各个阶段没有明显的界限，开发人员可以同步进行开发，则开发过程中需要大量开发人员，增加了项目管理的难度。\n" +
                            "   文档管理：需严格管理文档，从而又使得审核的难度加大，因为随时面对加入的各种信息、需求与资料等。",
                    "6. 适用场景：需求不明确或经常变化的项目：在需求不明确的情况下，喷泉模型允许项目团队通过迭代开发逐步明确需求。\n" +
                            "   需要快速响应市场变化的项目：喷泉模型的迭代性使得项目可以快速适应市场变化，及时调整开发方向。\n" +
                            "   需要高度用户参与的项目：喷泉模型强调用户反馈，适合那些需要用户深度参与和反馈的项目。\n" +
                            "   风险较高的项目：通过多次迭代，喷泉模型可以及时发现并修正问题，降低项目失败的风险。"
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
                    "1. 统一过程模型（RUP，Rational Unified Process）是一种软件工程方法，由IBM公司的Rational Software部门开发。",
                    "2. 阶段划分: 启动阶段、细化阶段、构建阶段和交付阶段",
                    "3. 特点: 迭代和增量开发：RUP强调软件开发是一个迭代过程，通过多次迭代逐步构建和完善软件产品。\n" +
                            "   用例驱动：RUP以用例为驱动，用例和场景是捕获和管理功能需求的重要工具。\n" +
                            "   架构中心：RUP以架构设计为中心，强调在软件开发过程中架构的重要性。\n" +
                            "   风险管理：RUP通过迭代开发和管理需求来降低项目风险。\n" +
                            "   多角色参与：RUP强调团队合作，涉及开发人员、测试人员、业务分析师等多个角色的参与。",
                    "4. 适用场景: 适用于大型复杂项目，但要求高的过程管理和高成本的过程定义"
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
                    "1. 敏捷开发（Agile Development）是一种以人为核心、迭代、循序渐进的软件开发方法。\n" +
                            "敏捷开发强调在快速变化的需求下交付软件产品，它鼓励灵活的应对变化，而不是遵循一个过于严格的、预先设定的计划。",
                    "2. 阶段划分: 规划、设计、编码、测试、交付和维护，在每个阶段中进行迭代",
                    "3. 特点: 人本关注：敏捷开发强调团队成员之间的密切协作、沟通和自我组织。\n" +
                            "   迭代开发：项目被分解为多个小的、可管理的迭代周期，通常称为冲刺（Sprint），在每个冲刺结束时都能交付一个可工作的软件增量。\n" +
                            "   透明沟通：项目进度和问题对所有团队成员、客户和利益相关者都是透明的。\n" +
                            "   适应变化：敏捷开发拥抱变化，允许在开发过程中根据客户需求的变化调整项目方向。\n" +
                            "   客户合作：敏捷开发强调与客户或用户紧密合作，确保开发的产品能够满足他们的需求。\n" +
                            "   持续交付：敏捷团队致力于持续地提供可用的软件，从而可以快速地获得反馈并进行改进。\n" +
                            "   简洁性：敏捷开发倡导工作简洁性，即最大化未完成的工作量，这有助于减少浪费和提高效率。\n" +
                            "   技术卓越和良好设计：敏捷开发鼓励通过持续的改进、技术卓越和良好的设计来增强敏捷性。\n" +
                            "   快速反馈：通过定期的演示和回顾会议，团队可以快速获得反馈并据此进行调整。\n" +
                            "   跨功能团队：敏捷团队通常是由具有多种技能的成员组成，他们可以独立完成冲刺目标。",
                    "4. 优缺点: 灵活性强，能够快速响应客户需求的变化，但可能在过程管理上有较高的难度"
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
