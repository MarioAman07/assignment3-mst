package com.example.assignment3.io.dto;

import com.example.assignment3.graph.Edge;
import java.util.List;

public class AnalysisResultEntry {
    public List<Edge> mst_edges;
    public long total_cost;
    public long operations_count;
    public double execution_time_ms;

    public AnalysisResultEntry(List<Edge> mstEdges, long totalCost, long operationCount, double executionTimeMs) {
        this.mst_edges = mstEdges;
        this.total_cost = totalCost;
        this.operations_count = operationCount;
        this.execution_time_ms = executionTimeMs;
    }
}