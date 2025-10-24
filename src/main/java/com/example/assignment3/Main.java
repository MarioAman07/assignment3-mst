package com.example.assignment3;

import com.example.assignment3.io.GraphGenerator;
import com.example.assignment3.io.dto.GraphDefinition;
import com.example.assignment3.io.dto.GraphInputFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Starting combined graph generation...");

        // runCombinedGraphGeneration();

        System.out.println("Graph generation complete.");

        System.out.println("Starting MST analysis...");
    }


    private static void runCombinedGraphGeneration() {
        GraphGenerator generator = new GraphGenerator();
        List<GraphDefinition> allGraphs = new ArrayList<>();

        System.out.println("Generating 5 small graphs (10-30 vertices)...");
        allGraphs.addAll(generator.generateSet("small", 5, 10, 30, 1.5));

        System.out.println("Generating 10 medium graphs (100-300 vertices)...");
        allGraphs.addAll(generator.generateSet("medium", 10, 100, 300, 2.0));

        System.out.println("Generating 10 large graphs (500-1000 vertices)...");
        allGraphs.addAll(generator.generateSet("large", 10, 500, 1000, 2.5));

        System.out.println("Generating 5 extra_large graphs (2000-3000 vertices)...");
        allGraphs.addAll(generator.generateSet("xl", 5, 2000, 3000, 1.2));

        for (int i = 0; i < allGraphs.size(); i++) {
            allGraphs.get(i).setId(i + 1);
        }

        GraphInputFile finalInputFile = new GraphInputFile(allGraphs);

        String outputDir = "data/input";
        String filePath = outputDir + "/all_graphs.json";

        try {
            Files.createDirectories(Paths.get(outputDir));

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            mapper.writeValue(new File(filePath), finalInputFile);

            System.out.println("---");
            System.out.println("Successfully generated all " + allGraphs.size() + " graphs into one file:");
            System.out.println(filePath);
            System.out.println("---");

        } catch (IOException e) {
            System.err.println("Failed to write combined JSON file!");
            e.printStackTrace();
        }
    }
}