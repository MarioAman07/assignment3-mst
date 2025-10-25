# 🌉 Transportation Network Optimization: Prim's vs. Kruskal's MST Analysis

This repository documents the implementation and rigorous performance analysis of two fundamental Minimum Spanning Tree (MST) algorithms, Prim's and Kruskal's. The primary objective is to evaluate their efficiency when applied to optimizing a simulated city transportation network, focusing on determining the most cost-effective construction strategy.

---

## 1. Project Architecture and Implementation

The solution adheres strictly to Object-Oriented Programming (OOP) principles, utilizing a custom graph model and highly optimized algorithm implementations.

### 1.1 Core Components
| Component | Implementation Detail | Role in Pipeline |
| :--- | :--- | :--- |
| **Graph Model (Bonus)** | Custom **`Graph`**, **`Vertex`**, and **`Edge`** | Stores the network using an **Adjacency List**, optimized for neighbor traversal (Prim's) and data integrity. |
| **Prim's Algorithm** | **`PrimsAlgorithm.java`** | Optimized implementation using **Binary Heap / PriorityQueue** to achieve $\mathcal{O}(E \log V)$ complexity. |
| **Kruskal's Algorithm** | **`KruskalsAlgorithm.java`** | Implementation relies on **DSU (Disjoint Set Union)** with path compression and union by size for near-constant time performance. |
| **Testing & Metrics** | JUnit 5, **`Timer.java`** | Includes extensive correctness tests (V-1 edges, disconnected graph handling) and measures execution time using `System.nanoTime()`. |

### 1.2 Execution and Documentation
All development adhered to a proper Git workflow (`feature` branches, atomic commits). The final results are persisted in structured formats.

To execute the full analytical pipeline:

```bash
mvn exec:java -Dexec.mainClass="com.example.assignment3.Main"
```

## 2. Analytical Results and Conclusion

The analysis was performed on a dataset consisting of **sparse graphs** (low edge-to-vertex ratio), which favors Kruskal's Algorithm theoretically and practically.

### 2.1 Input Data Characteristics

The following table summarizes the sparse nature of the test dataset, confirming the low density profile used for performance benchmarking.

| Category | Avg. Vertices (V) | Avg. Edges (E) | Avg. Density Ratio ($\mathbf{E / E_{max}}$) |
| :--- | :--- | :--- | :--- |
| Small (V $\le$ 50) | 19.4 | 27.4 | 17.0% |
| Medium (V $\le$ 350) | 179.3 | 356.6 | 2.4% |
| Large (V $\le$ 1100) | 746 | 1862.2 | 0.7% |
| XL (V > 1100) | 2454.6 | 2944.2 | 0.1% |

### 2.2 Performance Comparison Summary

**Validation:** The total MST cost was confirmed to be **identical** for both Prim's and Kruskal's algorithms across all tests. The comparison below focuses on efficiency metrics for major graph categories.

| Metric | Prim's ($\mathcal{O}(E \log V)$) | Kruskal's ($\mathcal{O}(E \log E)$) | Practical Advantage |
| :--- | :--- | :--- | :--- |
| **Execution Time (Large)** | 1.66 ms | **1.23 ms** | **35% Faster (Kruskal's)** |
| **Key Operations (Large)** | 10293 ops | 5390 ops | **52% Fewer Ops (Kruskal's)** |

### 2.3 Final Conclusion

**Kruskal's Algorithm is the optimal solution** for this sparse transportation network optimization. Its superior speed and operational efficiency validate the theoretical advantage of Kruskal's in conditions where the edge list is manageable, demonstrating robust performance on real-world-like sparse data.
