package data_manager;

import org.neo4j.dbms.DatabaseStateService;
import org.neo4j.dbms.api.DatabaseManagementService;
import org.neo4j.dbms.api.DatabaseManagementServiceBuilder;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import static org.neo4j.configuration.GraphDatabaseSettings.DEFAULT_DATABASE_NAME;

public class connector {
    // 注册一个关闭钩子，确保当应用程序关闭时数据库能够正常关闭
    private static void registerShutdownHook( final DatabaseManagementService managementService )
    {
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running application).
        Runtime.getRuntime().addShutdownHook( new Thread()
        {
            @Override
            public void run()
            {
                managementService.shutdown();
            }
        });
    }
    // 连接到数据库并返回 GraphDatabaseService 对象
    public static GraphDatabaseService connect(){
        //连接并返回graphDb对象, 用于操作数据库
        Path databaseDirectory = FileSystems.getDefault()
                .getPath("D:\\neo4j\\neo4j-community-5.25.1");
        DatabaseManagementService managementService =
                new DatabaseManagementServiceBuilder( databaseDirectory ).build();
        GraphDatabaseService graphDb = managementService.database( DEFAULT_DATABASE_NAME );
        return graphDb;
    }

}
