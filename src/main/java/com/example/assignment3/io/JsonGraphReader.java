package com.example.assignment3.io;

import com.example.assignment3.graph.Graph;
import com.example.assignment3.io.dto.GraphDefinition;
import com.example.assignment3.io.dto.GraphInputFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonGraphReader {

    private final ObjectMapper mapper = new ObjectMapper();

    public List<GraphDefinition> readDefinitions(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("Input file not found at path: " + filePath);
        }

        GraphInputFile inputFile = mapper.readValue(file, GraphInputFile.class);
        return inputFile.getGraphs();
    }

    public List<Graph> readAll(String filePath) throws IOException {
        List<GraphDefinition> definitions = readDefinitions(filePath);
        List<Graph> graphs = new ArrayList<>();

        for (GraphDefinition definition : definitions) {
            graphs.add(buildGraph(definition));
        }
        return graphs;
    }

    public Graph buildGraph(GraphDefinition definition) {
        Graph graph = new Graph();

        for (String nodeName : definition.getNodes()) {
            graph.addVertex(nodeName);
        }

        definition.getEdges().forEach(edge ->
                graph.addEdge(edge.getFrom(), edge.getTo(), edge.getWeight())
        );

        return graph;
    }
}