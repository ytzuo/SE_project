import org.neo4j.*;
import GUI.*;
public class Main {
    AnsweringWindow aw;
    KnowledgeCL kc;

    public Main(){
        kc = new KnowledgeCL();
        aw = new AnsweringWindow();
    }

    public static void main(String[] args) {
        Main m = new Main();
    }
}
