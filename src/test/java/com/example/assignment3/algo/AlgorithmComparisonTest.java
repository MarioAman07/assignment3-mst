package com.example.assignment3.algo;

import com.example.assignment3.graph.Graph;
import com.example.assignment3.io.JsonGraphReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlgorithmComparisonTest {

    private static final String ALL_GRAPHS_FILE_PATH = "data/input/all_graphs.json";
    private static List<Graph> allGraphs;

    @BeforeAll
    static void setupAll() throws IOException {
        JsonGraphReader reader = new JsonGraphReader();
        allGraphs = reader.readAll(ALL_GRAPHS_FILE_PATH);
    }

    @Test
    void shouldReadThirtyGraphs() {
        assertEquals(30, allGraphs.size(), "Should successfully read all 30 generated graphs.");
    }

    @Test
    void mstCostMustBeIdenticalForAllGraphs() {
        PrimsAlgorithm prim = new PrimsAlgorithm();
        KruskalsAlgorithm kruskal = new KruskalsAlgorithm();

        for (Graph graph : allGraphs) {

            if (graph.getVertexCount() == 0) continue;

            PrimsAlgorithm.PrimResult primResult = prim.findMST(graph);
            KruskalsAlgorithm.KruskalResult kruskalResult = kruskal.findMST(graph);

            String graphId = "Graph V=" + graph.getVertexCount() + ", E=" + graph.getEdgeCount();

            assertEquals(primResult.totalCost, kruskalResult.totalCost,
                    () -> "MST cost mismatch for " + graphId +
                            ". Prim: " + primResult.totalCost +
                            ", Kruskal: " + kruskalResult.totalCost);

            if (graph.getVertexCount() > 1) {
                assertEquals(primResult.isConnected, kruskalResult.isConnected,
                        () -> "Connectivity mismatch for " + graphId);
            }

            if (primResult.isConnected) {
                assertEquals(graph.getVertexCount() - 1, primResult.mstEdges.size(),
                        () -> "Prim: MST size should be V-1 for " + graphId);
                assertEquals(graph.getVertexCount() - 1, kruskalResult.mstEdges.size(),
                        () -> "Kruskal: MST size should be V-1 for " + graphId);
            }
        }
    }
}