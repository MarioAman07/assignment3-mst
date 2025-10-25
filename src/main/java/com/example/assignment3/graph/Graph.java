package com.example.assignment3.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private final Map<String, Vertex> vertices;
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
        addVertex(from);
        addVertex(to);

        Vertex vertexFrom = getVertex(from);
        Vertex vertexTo = getVertex(to);

        Edge edge = new Edge(from, to, weight);

        vertexFrom.addEdge(edge);
        vertexTo.addEdge(edge);

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