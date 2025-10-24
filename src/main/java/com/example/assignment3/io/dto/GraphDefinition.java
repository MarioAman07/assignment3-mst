package com.example.assignment3.io.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GraphDefinition {
    @JsonProperty("id")
    private int id;

    @JsonProperty("nodes")
    private List<String> nodes;

    @JsonProperty("edges")
    private List<EdgeDefinition> edges;

    // Пустой конструктор для Jackson
    public GraphDefinition() {}

    public GraphDefinition(int id, List<String> nodes, List<EdgeDefinition> edges) {
        this.id = id;
        this.nodes = nodes;
        this.edges = edges;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public List<String> getNodes() { return nodes; }
    public void setNodes(List<String> nodes) { this.nodes = nodes; }
    public List<EdgeDefinition> getEdges() { return edges; }
    public void setEdges(List<EdgeDefinition> edges) { this.edges = edges; }
}