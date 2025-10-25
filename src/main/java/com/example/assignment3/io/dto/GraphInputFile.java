package com.example.assignment3.io.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GraphInputFile {
    @JsonProperty("graphs")
    private List<GraphDefinition> graphs;

    public GraphInputFile() {}

    public GraphInputFile(List<GraphDefinition> graphs) {
        this.graphs = graphs;
    }

    public List<GraphDefinition> getGraphs() {
        return graphs;
    }

    public void setGraphs(List<GraphDefinition> graphs) {
        this.graphs = graphs;
    }
}