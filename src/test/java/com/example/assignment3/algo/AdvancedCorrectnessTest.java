package com.example.assignment3.algo;

import com.example.assignment3.graph.Graph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdvancedCorrectnessTest {

    private final PrimsAlgorithm prim = new PrimsAlgorithm();
    private final KruskalsAlgorithm kruskal = new KruskalsAlgorithm();

    @Test
    void shouldHandleEmptyGraph() {
        Graph emptyGraph = new Graph();

        PrimsAlgorithm.PrimResult primResult = prim.findMST(emptyGraph);
        assertEquals(0, primResult.totalCost);
        assertEquals(0, primResult.mstEdges.size());
        assertTrue(primResult.isConnected);

        KruskalsAlgorithm.KruskalResult kruskalResult = kruskal.findMST(emptyGraph);
        assertEquals(0, kruskalResult.totalCost);
        assertEquals(0, kruskalResult.mstEdges.size());
        assertTrue(kruskalResult.isConnected);
    }

    @Test
    void shouldHandleSingleVertexGraph() {
        Graph singleVertexGraph = new Graph();
        singleVertexGraph.addVertex("Z");

        PrimsAlgorithm.PrimResult primResult = prim.findMST(singleVertexGraph);
        assertEquals(0, primResult.totalCost);
        assertEquals(0, primResult.mstEdges.size());
        assertTrue(primResult.isConnected);

        KruskalsAlgorithm.KruskalResult kruskalResult = kruskal.findMST(singleVertexGraph);
        assertEquals(0, kruskalResult.totalCost);
        assertEquals(0, kruskalResult.mstEdges.size());
        assertTrue(kruskalResult.isConnected);
    }

    @Test
    void shouldHandleDisconnectedGraph() {
        Graph disconnectedGraph = new Graph();
        // Component 1 (A-B)
        disconnectedGraph.addEdge("A", "B", 10);
        // Component 2 (C-D)
        disconnectedGraph.addEdge("C", "D", 20);
        // V=4, E=2. Disconnected.

        // Prim's check
        PrimsAlgorithm.PrimResult primResult = prim.findMST(disconnectedGraph);
        assertFalse(primResult.isConnected, "Prim should detect disconnected graph.");
        // Ожидаем 2 ребра (V-1 для каждого из 2 компонентов)
        assertEquals(2, primResult.mstEdges.size(), "Prim should find V-1=1 edge for each component (total size 2).");

        // Kruskal's check
        KruskalsAlgorithm.KruskalResult kruskalResult = kruskal.findMST(disconnectedGraph);
        assertFalse(kruskalResult.isConnected, "Kruskal should detect disconnected graph.");
        // Ожидаем 2 ребра
        assertEquals(2, kruskalResult.mstEdges.size(), "Kruskal should find V-1=1 edge for each component (total size 2).");
    }
}