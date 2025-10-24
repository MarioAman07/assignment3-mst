package com.example.assignment3.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    // Map to quickly access vertices by their name
    private final Map<String, Vertex> vertices;
    // List to store all edges, crucial for Kruskal's algorithm
    private final List<Edge> allEdges;

    public Graph() {
        this.vertices = new HashMap<>();
        this.allEdges = new ArrayList<>();
    }

    public void addVertex(String name) {
        if (!vertices.containsKey(name)) {
            vertices.put(name, new Vertex(name));
        }
    }

    public Vertex getVertex(String name) {
        return vertices.get(name);
    }

    public void addEdge(String from, String to, int weight) {
        // Ensure vertices exist (needed when loading from JSON)
        addVertex(from);
        addVertex(to);

        Vertex vertexFrom = getVertex(from);
        Vertex vertexTo = getVertex(to);

        Edge edge = new Edge(from, to, weight);

        // For Kruskal's, we only need 'allEdges'.
        // For Prim's, we need the adjacency list. We implement both here.

        // Add edge to adjacency list for both vertices (undirected)
        vertexFrom.addEdge(edge);
        vertexTo.addEdge(edge);

        // Add to the master list of edges
        allEdges.add(edge);
    }

    public List<Edge> getAllEdges() {
        return allEdges;
    }

    public Map<String, Vertex> getVertices() {
        return vertices;
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public int getEdgeCount() {
        return allEdges.size();
    }
}