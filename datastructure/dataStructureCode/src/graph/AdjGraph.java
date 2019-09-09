package graph;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class AdjGraph {
    List<GraphNode> nodes;
    List<Integer> visit;

    public AdjGraph(int length) {
        nodes = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            nodes.add(new GraphNode(i));
        }
        nodes.get(0).neighbors.add(nodes.get(2));
        nodes.get(0).neighbors.add(nodes.get(4));
        nodes.get(1).neighbors.add(nodes.get(0));
        nodes.get(1).neighbors.add(nodes.get(2));
        nodes.get(2).neighbors.add(nodes.get(3));
        nodes.get(3).neighbors.add(nodes.get(4));
        nodes.get(4).neighbors.add(nodes.get(3));
    }

    public void traverse() {
        for (int i = 0; i < nodes.size(); i++) {
            System.out.printf("label:(%d) ", i);
            for (int j = 0; j < nodes.get(i).neighbors.size(); j++) {
                System.out.printf(" " + nodes.get(i).neighbors.get(j).label);
            }
            System.out.println();
        }
    }

    public void dfs() {
        HashSet<GraphNode> visit = new HashSet<>();
        for (GraphNode node : nodes) {
            if (!visit.contains(node)) {
                System.out.printf("start from label(%d)", node.label);
                dfsTraverse(node, visit);
                System.out.println();
            }

        }
    }

    public void dfsTraverse(GraphNode node, HashSet<GraphNode> visit) {
        visit.add(node);
        System.out.print(node.label);
        for (GraphNode neighbor : node.neighbors) {
            if (!visit.contains(neighbor)) {
                dfsTraverse(neighbor, visit);
            }
        }
    }
    public  void bfs() {
        HashSet<GraphNode> visit = new HashSet<>();
        for (GraphNode node : nodes) {
            if (!visit.contains(node)) {
                System.out.printf("start from(%d):", node.label);
                bfsTraverse(node, visit);
                System.out.println();
            }
        }

    }

    public void bfsTraverse(GraphNode node, HashSet<GraphNode> visit) {


        LinkedList<GraphNode> queue = new LinkedList<>();
        queue.offer(node);
        visit.add(node);
        while (!queue.isEmpty()){
            GraphNode poll = queue.poll();
            System.out.print(poll.label);
            for (GraphNode neighbor : poll.neighbors) {
                if (!visit.contains(neighbor)) {
                    queue.offer(neighbor);
                    visit.add(neighbor);
                }
            }
        }
    }

}

class GraphNode {
    int label;
    List<GraphNode> neighbors;

    public GraphNode(int label) {
        this.label = label;
        neighbors = new LinkedList<>();
    }
}