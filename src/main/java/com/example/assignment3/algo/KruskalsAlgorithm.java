package com.example.assignment3.algo;

import com.example.assignment3.graph.Edge;
import com.example.assignment3.graph.Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class KruskalsAlgorithm {

    private long edgeComparisonCount;
    private DisjointSetUnion dsu;

    public KruskalResult findMST(Graph graph) {
        edgeComparisonCount = 0;

        List<String> vertexNames = new ArrayList<>(graph.getVertices().keySet());
        this.dsu = new DisjointSetUnion(vertexNames);
        dsu.resetOperationCount();

        List<Edge> sortedEdges = graph.getAllEdges().stream()
                .sorted(Comparator.comparingInt(Edge::getWeight))
                .collect(Collectors.toList());

        List<Edge> mstEdges = new ArrayList<>();
        long totalCost = 0;
        int edgesToInclude = graph.getVertexCount() - 1;

        for (Edge edge : sortedEdges) {
            edgeComparisonCount++;

            String u = edge.getFrom();
            String v = edge.getTo();

            if (dsu.union(u, v)) {

                mstEdges.add(edge);
                totalCost += edge.getWeight();
            }

            if (mstEdges.size() == edgesToInclude) {
                break;
            }
        }

        boolean isConnected = (mstEdges.size() == edgesToInclude);
        long dsuOperations = dsu.getOperationCount();
        long totalOperations = edgeComparisonCount + dsuOperations;

        return new KruskalResult(mstEdges, totalCost, totalOperations, isConnected);
    }

    public static class KruskalResult {
        public final List<Edge> mstEdges;
        public final long totalCost;
        public final long operationCount;
        public final boolean isConnected;

        public KruskalResult(List<Edge> mstEdges, long totalCost, long operationCount, boolean isConnected) {
            this.mstEdges = mstEdges;
            this.totalCost = totalCost;
            this.operationCount = operationCount;
            this.isConnected = isConnected;
        }
    }
}