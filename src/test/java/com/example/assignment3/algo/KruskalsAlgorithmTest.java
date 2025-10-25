package com.example.assignment3.algo;

import com.example.assignment3.graph.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KruskalsAlgorithmTest {

    private Graph smallGraph;
    private KruskalsAlgorithm algorithm;

    @BeforeEach
    void setUp() {
        smallGraph = new Graph();
        smallGraph.addEdge("A", "B", 4);
        smallGraph.addEdge("A", "C", 3);
        smallGraph.addEdge("B", "C", 2);
        smallGraph.addEdge("B", "D", 5);
        smallGraph.addEdge("C", "D", 7);
        smallGraph.addEdge("C", "E", 8);
        smallGraph.addEdge("D", "E", 6);

        algorithm = new KruskalsAlgorithm();
    }

    @Test
    void shouldFindCorrectMstCostOnSmallGraph() {
        KruskalsAlgorithm.KruskalResult result = algorithm.findMST(smallGraph);

        assertEquals(16, result.totalCost, "Total MST cost should be 16.");
    }

    @Test
    void shouldFindCorrectNumberOfEdges() {
        KruskalsAlgorithm.KruskalResult result = algorithm.findMST(smallGraph);

        assertEquals(4, result.mstEdges.size(), "MST should contain V-1 = 4 edges.");
    }

    @Test
    void shouldIdentifyConnectedGraph() {
        KruskalsAlgorithm.KruskalResult result = algorithm.findMST(smallGraph);

        assertTrue(result.isConnected, "The graph should be identified as connected.");
    }
}