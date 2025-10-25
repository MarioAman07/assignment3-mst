package com.example.assignment3.io.dto;

public class FullGraphResult {
    public int graph_id;
    public GraphInputStats input_stats;
    public AnalysisResultEntry prim;
    public AnalysisResultEntry kruskal;

    public FullGraphResult(int graphId, GraphInputStats stats, AnalysisResultEntry primResult, AnalysisResultEntry kruskalResult) {
        this.graph_id = graphId;
        this.input_stats = stats;
        this.prim = primResult;
        this.kruskal = kruskalResult;
    }
}