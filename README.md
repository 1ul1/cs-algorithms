# CS-Algorithms

## Java and Assembly Implementations of Classic Computational Problems.

This repository contains a collection of algorithmic problems solved in Java and x86 Assembly (Intel syntax), covering topics from Dynamic Programming and Graph Theory to Divide et Impera and low-level data manipulation. It showcases both high-level algorithmic design and low-level system understanding, bridging theoretical foundations with practical implementations.

## Java

Developed as part of academic algorithmic coursework, these problems are structured by sets and documented with their respective complexity analyses.

**Highlights:**

- **Feribot** – Binary Search for minimal feasible weight distribution
- **Stocks** – Dynamic Programming (Knapsack-like optimization)
- **Prinel** – Combined BFS and DP to minimize transformation steps
- **Crypto** – Dynamic subsequence counting with constraints
- **P1** – Graph reconstruction from BFS distance vectors

**Each solution includes:**
- Clear algorithmic reasoning and complexity analysis
- Modular and documented structure for readability

## Assembly

The assembly programs focus on divide-and-conquer and logic-based algorithmic tasks.

### Divide et Impera

- **binary_search.asm** – Recursive binary search implemented at register level using `cmp`, `jmp`, and stack operations
- **quick_sort.asm** – Full recursive QuickSort implementation using stack frames and manual memory management

### Paranthesinator

- **paranthesinator.asm** – Validates balanced parentheses using register-based stack logic

Each program directly manipulates CPU registers and the stack, showcasing low-level algorithmic control flow and efficient computation.