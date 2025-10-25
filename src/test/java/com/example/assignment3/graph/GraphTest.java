package com.example.assignment3.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    @Test
    void shouldAddVertexAndCountCorrectly() {
        Graph graph = new Graph();

        graph.addVertex("A");
        graph.addVertex("B");


        graph.addVertex("A");

        assertEquals(2, graph.getVertexCount());
        assertNotNull(graph.getVertex("B"));
        assertNull(graph.getVertex("C"));
    }

    @Test
    void shouldAddUndirectedEdgeAndCountCorrectly() {
        Graph graph = new Graph();

        graph.addEdge("A", "B", 10);

        graph.addEdge("B", "C", 5);

        assertEquals(3, graph.getVertexCount(), "Graph should contain A, B, and C.");
        assertEquals(2, graph.getEdgeCount(), "Graph should contain 2 total edges.");
        assertEquals(2, graph.getAllEdges().size(), "Total edges list size must be 2.");

        assertEquals(2, graph.getVertex("B").getEdges().size(), "Vertex B must have 2 adjacent edges.");

        assertEquals(1, graph.getVertex("A").getEdges().size(), "Vertex A must have 1 adjacent edge.");

        Edge edgeAB = graph.getVertex("A").getEdges().get(0);
        assertEquals(10, edgeAB.getWeight());
    }

    @Test
    void shouldCreateVerticesIfTheyDoNotExistWhenAddingEdge() {
        Graph graph = new Graph();

        graph.addEdge("X", "Y", 100);

        assertEquals(2, graph.getVertexCount(), "Vertices X and Y must be created automatically.");
        assertNotNull(graph.getVertex("X"));
        assertNotNull(graph.getVertex("Y"));
        assertEquals(1, graph.getEdgeCount());
    }
}