package com.example.assignment3.algo;

import com.example.assignment3.graph.Edge;
import com.example.assignment3.graph.Graph;
import com.example.assignment3.graph.Vertex;

import java.util.*;

public class PrimsAlgorithm {

    private long operationCount;

    public PrimResult findMST(Graph graph) {
        operationCount = 0;
        int V = graph.getVertexCount();

        if (V <= 1) {
            return new PrimResult(new ArrayList<>(), 0, operationCount, true);
        }

        Map<String, Long> key = new HashMap<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> mstSet = new HashSet<>();

        PriorityQueue<VertexKey> pq = new PriorityQueue<>(Comparator.comparingLong(vk -> vk.weight));

        for (String name : graph.getVertices().keySet()) {
            key.put(name, Long.MAX_VALUE);
            parent.put(name, null);
            operationCount++;
        }

        List<Edge> mstEdges = new ArrayList<>();
        long totalCost = 0;

        // Внешний цикл для обработки всех компонентов (MSF)
        while (mstSet.size() < V) {

            // Находим необработанную вершину для старта нового компонента
            if (pq.isEmpty()) {
                String startVertexName = null;
                for (String name : graph.getVertices().keySet()) {
                    if (!mstSet.contains(name)) {
                        startVertexName = name;
                        break;
                    }
                }

                if (startVertexName == null) break;

                key.put(startVertexName, 0L);
                pq.add(new VertexKey(startVertexName, 0L));
                operationCount++;
            }

            // Внутренний цикл Прима
            while (!pq.isEmpty()) {

                VertexKey uKey = pq.poll();
                String uName = uKey.name;
                operationCount++;

                if (mstSet.contains(uName)) {
                    operationCount++;
                    continue;
                }

                mstSet.add(uName);

                if (parent.get(uName) != null) {
                    String pName = parent.get(uName);
                    for (Edge edge : graph.getVertex(pName).getEdges()) {
                        operationCount++;
                        if ((edge.getFrom().equals(pName) && edge.getTo().equals(uName)) ||
                                (edge.getFrom().equals(uName) && edge.getTo().equals(pName))) {

                            mstEdges.add(edge);
                            totalCost += edge.getWeight();
                            break;
                        }
                    }
                }

                Vertex u = graph.getVertex(uName);
                for (Edge edge : u.getEdges()) {
                    operationCount++;

                    String vName = edge.getFrom().equals(uName) ? edge.getTo() : edge.getFrom();
                    int weight = edge.getWeight();

                    if (!mstSet.contains(vName) && (long) weight < key.get(vName)) {

                        key.put(vName, (long) weight);
                        parent.put(vName, uName);

                        pq.add(new VertexKey(vName, (long) weight));
                        operationCount++;
                    }
                }
            }
        }

        boolean isConnected = (mstEdges.size() == V - 1);

        return new PrimResult(mstEdges, totalCost, operationCount, isConnected);
    }

    private static class VertexKey {
        String name;
        long weight;

        public VertexKey(String name, long weight) {
            this.name = name;
            this.weight = weight;
        }
    }

    public static class PrimResult {
        public final List<Edge> mstEdges;
        public final long totalCost;
        public final long operationCount;
        public final boolean isConnected;

        public PrimResult(List<Edge> mstEdges, long totalCost, long operationCount, boolean isConnected) {
            this.mstEdges = mstEdges;
            this.totalCost = totalCost;
            this.operationCount = operationCount;
            this.isConnected = isConnected;
        }
    }
}