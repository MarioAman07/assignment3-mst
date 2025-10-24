package com.example.assignment3.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    @Test
    void shouldAddVertexAndCountCorrectly() {
        Graph graph = new Graph();

        // Добавление новых вершин
        graph.addVertex("A");
        graph.addVertex("B");

        // Попытка добавить дубликат
        graph.addVertex("A");

        assertEquals(2, graph.getVertexCount());
        assertNotNull(graph.getVertex("B"));
        assertNull(graph.getVertex("C"));
    }

    @Test
    void shouldAddUndirectedEdgeAndCountCorrectly() {
        Graph graph = new Graph();

        // Добавление ребра между A и B
        graph.addEdge("A", "B", 10);

        // Добавление ребра между B и C
        graph.addEdge("B", "C", 5);

        // Проверка общих счетчиков
        assertEquals(3, graph.getVertexCount(), "Graph should contain A, B, and C.");
        assertEquals(2, graph.getEdgeCount(), "Graph should contain 2 total edges.");
        assertEquals(2, graph.getAllEdges().size(), "Total edges list size must be 2.");

        // Проверка списка смежности (Adjacency List)
        // B должна иметь 2 ребра (B-A и B-C)
        assertEquals(2, graph.getVertex("B").getEdges().size(), "Vertex B must have 2 adjacent edges.");
        // A должна иметь 1 ребро (A-B)
        assertEquals(1, graph.getVertex("A").getEdges().size(), "Vertex A must have 1 adjacent edge.");

        // Проверка веса ребра
        Edge edgeAB = graph.getVertex("A").getEdges().get(0);
        assertEquals(10, edgeAB.getWeight());
    }

    @Test
    void shouldCreateVerticesIfTheyDoNotExistWhenAddingEdge() {
        Graph graph = new Graph();

        // Добавляем ребро без явного вызова addVertex
        graph.addEdge("X", "Y", 100);

        assertEquals(2, graph.getVertexCount(), "Vertices X and Y must be created automatically.");
        assertNotNull(graph.getVertex("X"));
        assertNotNull(graph.getVertex("Y"));
        assertEquals(1, graph.getEdgeCount());
    }
}