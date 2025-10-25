package com.example.assignment3.algo;

import java.util.HashMap;
import java.util.Map;

public class DisjointSetUnion {
    private final Map<String, String> parent;
    private final Map<String, Integer> size;
    private long operationCount;

    public DisjointSetUnion(Iterable<String> elements) {
        this.parent = new HashMap<>();
        this.size = new HashMap<>();
        this.operationCount = 0;

        for (String element : elements) {
            parent.put(element, element);
            size.put(element, 1);
        }
    }

    public String find(String i) {
        String root = parent.get(i);

        if (root.equals(i)) {
            return i;
        }

        root = find(root);
        parent.put(i, root);

        operationCount++;

        return root;
    }

    public boolean union(String i, String j) {
        String rootI = find(i);
        String rootJ = find(j);

        if (rootI.equals(rootJ)) {
            return false;
        }

        int sizeI = size.get(rootI);
        int sizeJ = size.get(rootJ);

        if (sizeI < sizeJ) {
            parent.put(rootI, rootJ);
            size.put(rootJ, sizeI + sizeJ);
        } else {
            parent.put(rootJ, rootI);
            size.put(rootI, sizeI + sizeJ);
        }

        operationCount++;

        return true;
    }

    public long getOperationCount() {
        return operationCount;
    }

    public void resetOperationCount() {
        this.operationCount = 0;
    }
}