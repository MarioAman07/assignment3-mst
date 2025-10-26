# 🏙️ Assignment 3: MST Algorithm Analysis (Prim's vs. Kruskal's)

This project provides a comprehensive implementation and performance analysis of Prim's and Kruskal's algorithms, designed to find the Minimum Spanning Tree (MST) for a simulated city transportation network. The core objective is to compare their efficiency in terms of execution time and operational complexity on a dataset of 30 graphs of varying sizes.

---

## 1. Project Objectives and Core Features

* **Algorithm Implementation**: Full Java implementation of both **Prim's Algorithm** (using a `PriorityQueue` for $\mathcal{O}(E \log V)$ complexity) and **Kruskal's Algorithm** (using an optimized **Disjoint Set Union (DSU)** structure for $\mathcal{O}(E \log E)$ complexity).
* **Performance Analysis**: A custom metrics pipeline (`Timer.java`, operation counters) measures execution time (ms) and key algorithmic operations for all 30 test cases.
* **Correctness Validation**: Automated JUnit tests verify that `Prim_Cost` is always identical to `Kruskal_Cost`, ensuring both algorithms correctly identify the MST.
* **Custom Graph Structure (Bonus)**: Implements a custom OOP-based graph model (`Graph.java`, `Vertex.java`, `Edge.java`) using an adjacency list, which serves as the input for both algorithms.

---

## 2. Technical Architecture and Usage

### 2.1 Project Structure
The project follows standard Maven conventions, separating logic into distinct packages:

| Package | Purpose |
| :--- | :--- |
| `com.example.assignment3.algo` | Contains the core algorithm logic (Prim, Kruskal, DSU). |
| `com.example.assignment3.graph` | Contains the **Bonus** custom graph data structure. |
| `com.example.assignment3.io` | Manages all I/O, including `JsonGraphReader`, `JsonResultWriter`, and `CsvSummaryWriter`. |
| `com.example.assignment3.util` | Includes the `Timer.java` utility for performance measurement. |
| `src/test/java` | Contains all JUnit 5 tests for validation and edge-case handling. |

### 2.2 How to Run
The project is built and executed using Maven.

**Prerequisites:**
* Java JDK 21+
* Apache Maven 3.8+

**Execute the full analysis pipeline:**
This command will clean, compile, and run the `Main.java` class, which processes all 30 graphs and generates the output files.

```bash
mvn clean install exec:java -Dexec.mainClass="com.example.assignment3.Main"
```

## 2. Analytical Results and Conclusion

The analysis was performed on a dataset of **sparse graphs** (low edge-to-vertex ratio), which favors Kruskal's algorithm's theoretical profile.

### 2.1 Input Data Characteristics

The 30 graphs were categorized to analyze scalability. The density ratio confirms the sparse nature of the test data.

| Category | Avg. Vertices (V) | Avg. Edges (E) | Avg. Density Ratio ($\mathbf{E / E_{max}}$) |
| :--- | :--- | :--- | :--- |
| Small (V $\le$ 50) | 19.4 | 27.4 | 17.0% |
| Medium (V $\le$ 350) | 179.3 | 356.6 | 2.4% |
| Large (V $\le$ 1100) | 746.0 | 1862.2 | 0.7% |
| XL (V > 1100) | 2454.6 | 2944.2 | 0.1% |

### 2.2 Performance Comparison


The practical results from the `performance_summary.csv` align perfectly with theoretical expectations.

**Validation:** The total MST cost was confirmed to be **identical** for both Prim's and Kruskal's algorithms across all tests.

| Category | Prim Time (Avg. ms) | Kruskal Time (Avg. ms) | Time Advantage | Prim Ops (Avg.) | Kruskal Ops (Avg.) | Ops Advantage |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| Small | 0.398 | 0.375 | Parity | 169.6 | 57.0 | Kruskal's (66% fewer) |
| Medium | 0.438 | 0.369 | Kruskal's (16% faster) | 2071.1 | 1006.6 | Kruskal's (51% fewer) |
| Large | 1.662 | 1.230 | Kruskal's (26% faster) | 10293.0 | 5390.0 | Kruskal's (48% fewer) |
| XL | 1.988 | 2.030 | Parity | 18898.0 | 9406.0 | Kruskal's (50% fewer) |

---

## 3. Final Conclusion

**Kruskal's Algorithm is the optimal solution for this sparse transportation network.**

1.  **Operational Efficiency:** Kruskal's consistently performed **~50% fewer key operations** across all categories. This is due to the extreme efficiency of the **Disjoint Set Union (DSU)** structure compared to the high overhead of `PriorityQueue` updates in Prim's.
2.  **Time Efficiency:** This operational advantage translated to **faster execution times** for Kruskal's in most cases. The time parity in the XL category suggests that at a very large scale, the $\mathcal{O}(E \log E)$ sorting cost of Kruskal's becomes a bottleneck comparable to Prim's $\mathcal{O}(E \log V)$ queue operations.
3.  **Recommendation:** For sparse graphs, Kruskal's is demonstrably superior in both speed and operational cost.

---

## 4. Bonus Section: Custom Graph Structure

This project successfully implemented a custom object-oriented graph structure (`Graph.java`, `Vertex.java`) using an adjacency list model. The correct loading and integration of this structure were validated via the debugger.
<img width="551" height="562" alt="Снимок экрана 2025-10-25 в 16 22 08 — копия" src="https://github.com/user-attachments/assets/dc987664-c6fc-4911-b2ea-05ee3ab7a18c" />
<img width="552" height="593" alt="Снимок экрана 2025-10-25 в 16 22 27 — копия" src="https://github.com/user-attachments/assets/bbc556e0-059e-4e1b-968d-86712f7f548d" />

