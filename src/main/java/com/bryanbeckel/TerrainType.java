package com.bryanbeckel;

public enum TerrainType {
    // The numbers show how much movement is needed
    GRASS(1),
    RIVER(3),
    MOUNTAIN(-1);

    private final int movementCost;

    // Constructor
    TerrainType(int movementCost) {
        this.movementCost = movementCost;
    }

    // Getters
    public int getMovementCost() {
        return this.movementCost;
    }

    // If movement cost is -1, cannot walk
    public boolean isWalkable() {
        return movementCost != -1;
    }
}
