package com.example.assignment3.io.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EdgeDefinition {
    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private String to;

    @JsonProperty("weight")
    private int weight;

    // Пустой конструктор для Jackson
    public EdgeDefinition() {}

    public EdgeDefinition(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    // Геттеры и сеттеры
    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }
    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }
    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }
}