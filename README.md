# Politician Voting Manager

**DSA1 – CA2** | Jamie Whelan, Daniel Wall, Alwin Sabu

A Java application for managing politicians and their voting records, built as part of our Data Structures & Algorithms 1 continuous assessment.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Data Structures & Algorithms Used](#data-structures--algorithms-used)
- [Testing](#testing)
- [Team](#team)

---

## Overview

A JavaFX application that stores, searches, sorts, and analyzes Irish political elections, candidates, and politicians using custom linear data structures, custom hash tables, and custom non-bubble sorting algorithms.

## Features

- Implements custom array-backed Hash Tables for O ( 1 ) direct lookups of politicians and elections
- Utilizes non-bubble sorting algorithms (QuickSort / MergeSort) for multi-parameter result filtering
- Supports complete CRUD operations (Create, Read, Update, Delete) for politicians, elections, and candidate records
- Features interactive "drill-down" GUI navigation across political parties, politician profiles, and election result listings
- Highlights seat winners dynamically in election listings based on total vote tallies
- Implements XML/text file persistence to save and load complete application state on startup and exit
- Includes a comprehensive suite of unit tests built using JUnit

## Project Structure

```
DSA1-CA2-JW-DW-AS/
├── main/
   ├── Java
      ├── LinkedList
         ├── DoublyLinkedList.java
         ├── Node.java
      ├──app
         ├── Main.java
      ├── hashtables
         ├── ElectionHashTable.java
         ├── PoliticianHashTable.java
      ├── models
         ├── Candidate.java
         ├── Election.java
         ├── ElectionsController.java
         ├── Politician.java
      ├── Sort
         ├── ElectionSorter.java
         ├── PoliticianSorter.java
   ├── Resources
      ├── Elections.fxml           
├── test/java/
   ├── Hashtables
      ├── ElectionHashtableTest.java
      ├── PoliticianHashtableTest.java
   ├── Models
      ├── CandidateTest.java
      ├── ElectionTest.java
      ├── PoliticianTest.java
   ├── Sort
      ├──PoliticianSorterTest.java         
├── Project-.2.iml     
└── README.md
```

## Getting Started

### Prerequisites

- Java JDK [version, e.g. 17 or later]
- IntelliJ IDEA (recommended – the project includes IntelliJ config files)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Jamie-Whelan04/DSA1-CA2-JW-DW-AS.git
   ```
2. Open the project folder in IntelliJ IDEA.
3. Make sure the project SDK is set to your installed JDK (`File > Project Structure`).
4. Locate the class containing the `main` method and run it.


## Testing

Unit tests are located in `test/java`. To run them in IntelliJ, right-click the `test/java` folder and select **Run 'All Tests'**.

## Team

| Name | GitHub |
|------|--------|
| Jamie Whelan (JW) | [@Jamie-Whelan04](https://github.com/Jamie-Whelan04) |
| DW | [@daniel-wall06](https://github.com/daniel-wall06)| 
| AS | [@alwin2705](https://github.com/Alwin2705)| 

---

*Created for DSA1 – CA2.*
