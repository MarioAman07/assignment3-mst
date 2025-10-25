package com.example.assignment3.io;

import com.example.assignment3.io.dto.FinalOutput;
import com.example.assignment3.io.dto.FullGraphResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonResultWriter {

    private final ObjectMapper mapper;

    public JsonResultWriter() {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void writeResults(List<FullGraphResult> results, String filePath) throws IOException {
        FinalOutput finalOutput = new FinalOutput(results);
        File file = new File(filePath);

        mapper.writeValue(file, finalOutput);
    }
}