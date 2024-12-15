package Graphs;

import java.util.*;

public class Ford_Fulkersons_List {

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        // Add edges with from, to, and capacity
        // edges.add(new Edge(0, 1, 16));
        edges.add(new Edge(0,1,16));
        edges.add(new Edge(0,2,13));
        edges.add(new Edge(1,2,10));
        edges.add(new Edge(1,3,12));
        edges.add(new Edge(2,1,4));
        edges.add(new Edge(2,4,14));
        edges.add(new Edge(3,2,9));
        edges.add(new Edge(3,5,20));
        edges.add(new Edge(4,3,7));
        edges.add(new Edge(4,5,4));

        // Remember to update these values
        int vertices = 6;
        int source = 0;
        int sink = 5;

        System.out.println("The maximum possible flow is " +
                fordFulkerson(edges, source, sink, vertices));
    }

    static class Edge {
        int from, to, capacity;

        Edge(int from, int to, int capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return from == edge.from && to == edge.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }

    static final int INF = Integer.MAX_VALUE;

    // A method to find if there is a path from source to sink using BFS.
    static boolean bfs(int source, int sink, Map<Integer, List<Edge>> graph, Map<Edge, Integer> residualCapacity, int[] parent) {
        Arrays.fill(parent, -1); // Reset parent array
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Edge edge : graph.get(current)) {
                int capacityLeft = residualCapacity.getOrDefault(edge, 0);
                if (!visited[edge.to] && capacityLeft > 0) {
                    parent[edge.to] = current;
                    queue.add(edge.to);
                    visited[edge.to] = true;

                    if (edge.to == sink) return true; // Reached sink
                }
            }
        }

        return false; // No path to sink
    }

    static int fordFulkerson(List<Edge> edges, int source, int sink, int vertices) {
        // Initialize the graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < vertices; i++) graph.put(i, new ArrayList<>());

        // Add edges to the graph
        Map<Edge, Integer> residualCapacity = new HashMap<>();
        for (Edge edge : edges) {
            graph.get(edge.from).add(edge);
            Edge reverseEdge = new Edge(edge.to, edge.from, 0);
            graph.get(edge.to).add(reverseEdge);

            residualCapacity.put(edge, edge.capacity);
            residualCapacity.put(reverseEdge, 0);
        }

        int maxFlow = 0;
        int[] parent = new int[vertices];

        while (bfs(source, sink, graph, residualCapacity, parent)) {
            // Find minimum capacity in the augmenting path
            int pathFlow = INF;
            for (int v = sink; v != source; ) {
                int u = parent[v];
                int finalV = v;
                Edge edge = graph.get(u).stream().filter(e -> e.to == finalV).findFirst().orElse(null);
                pathFlow = Math.min(pathFlow, residualCapacity.get(edge));
                v = u;
            }

            // Update residual capacities
            for (int v = sink; v != source; ) {
                int u = parent[v];
                int finalV = v;
                Edge edge = graph.get(u).stream().filter(e -> e.to == finalV).findFirst().orElse(null);
                Edge reverseEdge = graph.get(v).stream().filter(e -> e.to == u).findFirst().orElse(null);

                residualCapacity.put(edge, residualCapacity.get(edge) - pathFlow);
                residualCapacity.put(reverseEdge, residualCapacity.get(reverseEdge) + pathFlow);
                v = u;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }
}
