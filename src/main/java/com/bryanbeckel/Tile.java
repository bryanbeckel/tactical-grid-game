package com.bryanbeckel;

public class Tile {
    // Instance Variables
    private final int row;
    private final int column;
    private TerrainType terrain;

    // Constructors
    public Tile(int row, int column, TerrainType terrain) {
        this.row = row;
        this.column = column;
        this.terrain = terrain;
    }

    public Tile(int row, int column) {
        this.row = row;
        this.column = column;
        this.terrain = TerrainType.GRASS;
    }
    
    // Getters
    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public TerrainType getTerrain() {
        return this.terrain;
    }

    // Setters
    public void setTerrain(TerrainType terrain) {
        this.terrain = terrain;
    }
}
