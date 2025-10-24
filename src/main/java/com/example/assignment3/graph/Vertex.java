package com.example.assignment3.graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private final String name;
    private final List<Edge> edges; // Adjacency list

    public Vertex(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    public String getName() {
        return name;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        return "Vertex(" + name + ")";
    }
}