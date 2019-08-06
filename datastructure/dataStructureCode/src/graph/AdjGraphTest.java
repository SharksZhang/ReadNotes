package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdjGraphTest {

    @Test
    void traverse() {
        AdjGraph adjGraph = new AdjGraph(5);
        adjGraph.traverse();
        adjGraph.dfs();

        adjGraph.bfs();
    }
}