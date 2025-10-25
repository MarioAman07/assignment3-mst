package com.example.assignment3.io;

import com.example.assignment3.io.dto.FullGraphResult;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CsvSummaryWriter {

    public void writeSummary(List<FullGraphResult> results, String filePath) throws IOException {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

            writer.println("Graph_ID,Vertices,Edges,Prim_Cost,Kruskal_Cost,Prim_Time_ms,Kruskal_Time_ms,Prim_Ops,Kruskal_Ops");

            for (FullGraphResult result : results) {

                if (result.prim == null || result.kruskal == null || result.input_stats == null) {
                    continue;
                }

                String line = String.format("%d,%d,%d,%d,%d,%.4f,%.4f,%d,%d",
                        result.graph_id,
                        result.input_stats.vertices,
                        result.input_stats.edges,
                        result.prim.total_cost,
                        result.kruskal.total_cost,
                        result.prim.execution_time_ms,
                        result.kruskal.execution_time_ms,
                        result.prim.operations_count,
                        result.kruskal.operations_count
                );
                writer.println(line);
            }
        }
    }
}