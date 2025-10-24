package com.example.assignment3.io;

// Import the classes from our dto package
import com.example.assignment3.io.dto.EdgeDefinition;
import com.example.assignment3.io.dto.GraphDefinition;
import com.example.assignment3.io.dto.GraphInputFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GraphGenerator {

    private final Random random = new Random();
    private final ObjectMapper mapper;

    public GraphGenerator() {
        this.mapper = new ObjectMapper();
        // Enable pretty-printing (indented output)
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * [OLD METHOD]
     * Generates and saves a set of graphs into a specified directory,
     * with each graph in its own file.
     */
    public void generateAndSaveSet(String directoryPath, String filePrefix, int count, int minV, int maxV, double densityFactor) {
        try {
            // Create the directory if it doesn't exist
            Files.createDirectories(Paths.get(directoryPath));
        } catch (IOException e) {
            System.err.println("Failed to create directory: " + directoryPath);
            e.printStackTrace();
            return;
        }

        for (int i = 1; i <= count; i++) {
            int numVertices = minV + random.nextInt(maxV - minV + 1);

            int minEdges = numVertices - 1;
            long maxPossibleEdges = (long) numVertices * (numVertices - 1) / 2;
            int numEdges = (int) (minEdges * densityFactor);

            if (numEdges > maxPossibleEdges) {
                numEdges = (int) maxPossibleEdges;
            }
            numEdges = Math.max(minEdges, numEdges);

            GraphDefinition graph = generateConnectedGraph(i, numVertices, numEdges);

            GraphInputFile inputFile = new GraphInputFile(Collections.singletonList(graph));

            String filePath = String.format("%s/%s_%d.json", directoryPath, filePrefix, i);
            try {
                mapper.writeValue(new File(filePath), inputFile);
                System.out.println("Generated file: " + filePath + " (V=" + numVertices + ", E=" + graph.getEdges().size() + ")");
            } catch (IOException e) {
                System.err.println("Error writing file: " + filePath);
                e.printStackTrace();
            }
        }
    }

    /**
     * [NEW METHOD]
     * Generates a set of graphs and RETURNS them as a List.
     *
     * @param idPrefix      An identifier for logging (e.g., "small")
     * @param count         Number of graphs to generate
     * @param minV          Min vertices
     * @param maxV          Max vertices
     * @param densityFactor Graph density
     * @return A list of generated graphs
     */
    public List<GraphDefinition> generateSet(String idPrefix, int count, int minV, int maxV, double densityFactor) {
        List<GraphDefinition> generatedGraphs = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            int numVertices = minV + random.nextInt(maxV - minV + 1);

            int minEdges = numVertices - 1;
            long maxPossibleEdges = (long) numVertices * (numVertices - 1) / 2;
            int numEdges = (int) (minEdges * densityFactor);

            if (numEdges > maxPossibleEdges) {
                numEdges = (int) maxPossibleEdges;
            }
            numEdges = Math.max(minEdges, numEdges);

            // We pass 'i' as a temporary ID.
            // Main() will update it later to be unique (1, 2, ... 30).
            GraphDefinition graph = generateConnectedGraph(i, numVertices, numEdges);

            System.out.println("Generated graph: " + idPrefix + "_" + i + " (V=" + numVertices + ", E=" + graph.getEdges().size() + ")");
            generatedGraphs.add(graph);
        }

        return generatedGraphs;
    }


    /**
     * [PRIVATE METHOD - UNCHANGED LOGIC]
     * Generates a single connected graph.
     */
    private GraphDefinition generateConnectedGraph(int id, int numVertices, int numEdges) {
        List<String> nodes = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            nodes.add("V" + i);
        }

        List<EdgeDefinition> edges = new ArrayList<>();
        Set<String> existingEdges = new HashSet<>();

        // 1. Ensure connectivity: create a random spanning tree (guarantees V-1 edges)
        List<Integer> vertexIndices = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            vertexIndices.add(i);
        }
        Collections.shuffle(vertexIndices, random); // Shuffle to make the tree random, not just a line

        for (int i = 0; i < numVertices - 1; i++) {
            String from = "V" + vertexIndices.get(i);
            String to = "V" + vertexIndices.get(i+1);
            int weight = 1 + random.nextInt(100);
            edges.add(new EdgeDefinition(from, to, weight));
            existingEdges.add(getEdgeKey(from, to));
        }


        // 2. Add remaining edges to reach numEdges
        int edgesToAdd = numEdges - (numVertices - 1);
        int safetyBreak = 0; // Fuse to prevent infinite loops
        int maxTries = numEdges * 10; // Max attempts, esp. for dense graphs

        while (edgesToAdd > 0 && safetyBreak < maxTries) {
            String from = "V" + random.nextInt(numVertices);
            String to = "V" + random.nextInt(numVertices);

            if (from.equals(to)) {
                safetyBreak++;
                continue; // No self-loops
            }

            String edgeKey = getEdgeKey(from, to);
            if (!existingEdges.contains(edgeKey)) {
                int weight = 1 + random.nextInt(100);
                edges.add(new EdgeDefinition(from, to, weight));
                existingEdges.add(edgeKey);
                edgesToAdd--;
            }
            safetyBreak++;
        }

        if (edgesToAdd > 0) {
            System.out.println(" (Warning: failed to add " + edgesToAdd + " edges, graph is likely too dense) ");
        }

        return new GraphDefinition(id, nodes, edges);
    }

    /**
     * [PRIVATE METHOD - UNCHANGED LOGIC]
     * Creates a unique key for a vertex pair to avoid duplicates
     * (e.g., V1-V2 and V2-V1 are the same key).
     */
    private String getEdgeKey(String from, String to) {
        // Sort to ensure V1-V2 and V2-V1 are the same key
        if (from.compareTo(to) > 0) {
            String temp = from;
            from = to;
            to = temp;
        }
        return from + "-" + to;
    }
}