package com.example.assignment3;

import com.example.assignment3.algo.KruskalsAlgorithm;
import com.example.assignment3.algo.PrimsAlgorithm;
import com.example.assignment3.graph.Graph;
import com.example.assignment3.io.CsvSummaryWriter;
import com.example.assignment3.io.JsonGraphReader;
import com.example.assignment3.io.JsonResultWriter;
import com.example.assignment3.io.dto.AnalysisResultEntry;
import com.example.assignment3.io.dto.FullGraphResult;
import com.example.assignment3.io.dto.GraphInputStats;
import com.example.assignment3.io.dto.GraphDefinition; // Добавлен импорт GraphDefinition
import com.example.assignment3.util.Timer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String INPUT_FILE_PATH = "data/input/all_graphs.json";
    private static final String OUTPUT_JSON_PATH = "data/output/assignment_3_results.json";
    private static final String OUTPUT_CSV_PATH = "data/output/performance_summary.csv";

    public static void main(String[] args) {

        System.out.println("Starting MST Analysis Pipeline...");

        try {
            runAnalysisPipeline();
            System.out.println("\n--- Analysis Complete ---");
            System.out.println("Results saved to: " + OUTPUT_JSON_PATH);
            System.out.println("Summary saved to: " + OUTPUT_CSV_PATH);

        } catch (IOException e) {
            System.err.println("\nERROR: Failed to run analysis pipeline due to IO issues.");
            e.printStackTrace();
        }
    }

    private static void runAnalysisPipeline() throws IOException {

        JsonGraphReader reader = new JsonGraphReader();
        KruskalsAlgorithm kruskalAlgo = new KruskalsAlgorithm();
        PrimsAlgorithm primAlgo = new PrimsAlgorithm();
        JsonResultWriter jsonWriter = new JsonResultWriter();
        CsvSummaryWriter csvWriter = new CsvSummaryWriter();
        Timer timer = new Timer();

        List<GraphDefinition> definitions = reader.readDefinitions(INPUT_FILE_PATH);
        List<FullGraphResult> finalResults = new ArrayList<>();

        System.out.println("Read " + definitions.size() + " graphs from input file.");

        for (GraphDefinition definition : definitions) {

            int graphId = definition.getId();

            Graph graph = reader.buildGraph(definition);

            int vertices = graph.getVertexCount();
            int edges = graph.getEdgeCount();

            if (vertices == 0) continue;

            timer.start();
            PrimsAlgorithm.PrimResult primResult = primAlgo.findMST(graph);
            timer.stop();
            double primTime = timer.getElapsedTimeMs();

            AnalysisResultEntry primEntry = new AnalysisResultEntry(
                    primResult.mstEdges,
                    primResult.totalCost,
                    primResult.operationCount,
                    primTime
            );

            timer.start();
            KruskalsAlgorithm.KruskalResult kruskalResult = kruskalAlgo.findMST(graph);
            timer.stop();
            double kruskalTime = timer.getElapsedTimeMs();

            AnalysisResultEntry kruskalEntry = new AnalysisResultEntry(
                    kruskalResult.mstEdges,
                    kruskalResult.totalCost,
                    kruskalResult.operationCount,
                    kruskalTime
            );

            GraphInputStats stats = new GraphInputStats(vertices, edges);
            FullGraphResult fullResult = new FullGraphResult(graphId, stats, primEntry, kruskalEntry);

            finalResults.add(fullResult);
            System.out.printf("Processed Graph ID: %d (V=%d, E=%d). Prim Cost: %d, Kruskal Cost: %d\n",
                    fullResult.graph_id, vertices, edges, primResult.totalCost, kruskalResult.totalCost);
        }

        jsonWriter.writeResults(finalResults, OUTPUT_JSON_PATH);
        csvWriter.writeSummary(finalResults, OUTPUT_CSV_PATH);
    }
}