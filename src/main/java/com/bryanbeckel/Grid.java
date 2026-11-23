package com.bryanbeckel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Grid {
    // Instance Variables
    private int rows;
    private int columns;
    private Tile[][] map;

    // Constructor
    public Grid() {
        this.rows = 0;
        this.columns = 0;
        this.map = new Tile[rows][columns];
    }

    // Getters
    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public Tile getTile(int row, int column) {
        return this.map[row][column];
    }

    // Create tiles based on csv file info
    public void loadMap(String filePath) throws IOException {
        List<String[]> fLines = new ArrayList<>();
        String delimiter = ","; // Divider for info that is used in csv

        // csv info is added to BufferReader to iterate through
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line; // Used for holding current line in while loop through iterations
            // If there is nothing in the line, then it is the end of the csv file
            while ((line = br.readLine()) != null) {
                // .trim() is used to get rid of any extra space, tab, ect.
                if (!line.trim().isEmpty()) {
                    fLines.add(line.split(delimiter));
                }
            }
        }

        // Sets rows to correct integer
        this.rows = fLines.size();
        // If csv is empty, columns is 0 so no crash. Otherwise, sets columns to correct integer
        if (this.rows > 0) {
            this.columns = fLines.get(0).length;
        } else {
            this.columns = 0;
        }

        // Creates array of tiles for grid to use
        this.map = new Tile[this.rows][this.columns];

        // Loops through rows and columns of file to assign each tile in this.map a terrain type
        for (int fRow = 0; fRow < this.rows; fRow++) {
            String[] tokens = fLines.get(fRow);

            for (int fColumn = 0; fColumn < tokens.length; fColumn++) {
                    // Loops through line array and assigns terrain type based on designated number
                    TerrainType terrain;

                    // .trim() is used to get rid of any extra space, tab, ect.
                    switch (tokens[fColumn].trim()) {
                        case "0" ->
                            terrain = TerrainType.GRASS;
                        case "1" -> 
                            terrain = TerrainType.RIVER;
                        case "2" -> 
                            terrain = TerrainType.MOUNTAIN;
                        default -> 
                            terrain = TerrainType.GRASS;
                
                    }
                    
                    // If tile is outside of map size, it is not assigned
                    if (fRow < this.rows && fColumn < this.columns) {
                        this.map[fRow][fColumn] = new Tile (fRow, fColumn, terrain);
                    }
                }
        }
    }
    
    
}
