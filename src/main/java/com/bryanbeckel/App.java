package com.bryanbeckel;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    private static final int TILE_SIZE = 64; // Size of tiles in map

    private Grid grid = new Grid(); // Keeps track of grid

    @Override
    public void start(Stage stage) {
        // Assigns a default Tile to every row and column of grid
        try {
            grid.loadMap("./src/main/resources/map1.csv");
        } catch (IOException e) {
            // If file is not found in path given
            System.err.println("Error reading file path: " + e.getMessage());
            Platform.exit();
            return;
        }
        

        // Assigns amount of rows and columns to variables
        int rows = grid.getRows();
        int columns = grid.getColumns();

        // Assigns width and height of canvas in variables
        int width = columns * TILE_SIZE;
        int height = rows * TILE_SIZE;


        // Sets blank area in window to add to scene, memory buffer
        Canvas canvas = new Canvas(width, height);

        // Creates what is basically a paintbrush to add shapes to canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Creates layout of elements to represent world
        Group world = new Group(canvas);


        // Turn Order Panel
        HBox turnOrder = new HBox();
        turnOrder.setStyle("-fx-background-color: #222; -fx-padding: 10px;");

        Label orderHeader = new Label("TURN ORDER: ");
        orderHeader.setStyle("-fx-text-fill: yellow; -fx-font-weight: bold;");

        Label orderTxt = new Label("Player 1 -> Enemy A -> Player 2");
        orderTxt.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        turnOrder.getChildren().addAll(orderHeader, orderTxt);


        // Character Stat Sidebar Panel
        VBox sidebar = new VBox();
        sidebar.setPrefWidth(200);
        sidebar.setStyle("-fx-background-color: #333; -fx-padding: 15px; -fx-spacing: 10px;");

        Label statsHeader = new Label("UNIT INFO");
        statsHeader.setStyle("-fx-text-fill: yellow; -fx-font-weight: bold;");

        Label unitNameTxt = new Label("Name: ---");
        unitNameTxt.setStyle("-fx-text-fill: white;");

        Label unitHpTxt = new Label("HP: -- / --");
        unitHpTxt.setStyle("-fx-text-fill: white;");

        Label unitAtkTxt = new Label("Atk: --");
        unitAtkTxt.setStyle("-fx-text-fill: white;");

        Label unitDefTxt = new Label("Def: --");
        unitDefTxt.setStyle("-fx-text-fill: white;");

        Label unitMovementTxt = new Label("Movement: -- / --");
        unitMovementTxt.setStyle("-fx-text-fill: white;");

        sidebar.getChildren().addAll(statsHeader, unitNameTxt, unitHpTxt, unitAtkTxt, unitDefTxt, unitMovementTxt);

        // Gets all the pieces of the game UI and puts them together
        BorderPane ui = new BorderPane();
        ui.setTop(turnOrder);
        ui.setCenter(world);
        ui.setRight(sidebar);


        // 60 FPS update and render loop for game
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // Wipes slate clean so old frames don't mix with new ones
                gc.clearRect(0, 0, width, height);

                // Render the images into the canvas with the graphicscontext
                render(gc, rows, columns);
            }
        };

        // Background AnimationTimer starts
        gameLoop.start();


        // Puts world into scene and presents window with information from scene
        Scene scene = new Scene(ui);
        stage.setTitle("Tactical Grid Game Prototype");
        stage.setScene(scene);
        stage.show();
    }

    private void render(GraphicsContext gc, int rows, int columns) {
        // Render the map
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // Current tile that is being created
                Tile curTile = grid.getTile(i, j);

                // Checks terrain type to decide what tile looks like
                switch (curTile.getTerrain()) {
                    case TerrainType.GRASS ->
                        gc.setFill(Color.DARKGREEN);
                    case TerrainType.RIVER ->
                        gc.setFill(Color.BLUE);
                    case TerrainType.MOUNTAIN ->
                        gc.setFill(Color.GRAY);
                }

                // Draws tile
                gc.fillRect(TILE_SIZE * j, TILE_SIZE * i, TILE_SIZE, TILE_SIZE);
            
                // Create grid lines
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(1.0);
                gc.strokeRect(TILE_SIZE * j, TILE_SIZE * i, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }

}