package com.garden;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.garden.Insect.insects;
import static com.garden.Pest.pests;
import static com.garden.Plant.plantImageViewMap;
import static com.garden.Plant.plantsList;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class ViewController {
    private static final Logger log = LogManager.getLogger(GardenController.class);
    private static final Image sunnyImage = new Image(Objects.requireNonNull(ViewController.class.getResourceAsStream("/images/sunny.png")));
    private static final Image rainyImage = new Image(Objects.requireNonNull(ViewController.class.getResourceAsStream("/images/rain.png")));
    public static final Image orange = new Image(Objects.requireNonNull(ViewController.class.getResourceAsStream("/images/orange.png")));
    public static final Image tomato = new Image(Objects.requireNonNull(ViewController.class.getResourceAsStream("/images/tomato.png")));
    public static final Map<Insect, ImageView> pestImageViewMap = new HashMap<>();
    private static final Image roseImage = new Image(Objects.requireNonNull(ViewController.class.getResourceAsStream("/images/rose.png")));
    private static final Image pestiside = new Image(Objects.requireNonNull(ViewController.class.getResourceAsStream("/images/pestiside.png")));
    private static final Image soilImage = new Image("file:cc.jfif");

    private static final Image caterpillar = new Image(Objects.requireNonNull(ViewController.class.getResourceAsStream("/images/caterpillar.png")));
    public static Set<String> occupiedCells = new HashSet<>();
    public static ArrayList<String> occupiedPestCells = new ArrayList<>();
    private HeatingController heatingController;
    private SprinklerController sprinklerController;
    private PesticideController pesticideController;
    private RainController rainController;
    private TemperatureController temperatureController;   

    public static int day = 1;
    public Button pressToPlayButton;
    public Button iterateDayButton;
    @FXML
    private ImageView weatherImageView;
    @FXML
    private Label weatherLabel;
    @FXML
    private GridPane gardenGrid;
    @FXML
    private GridPane weatherGrid;
    @FXML
    private Pane imagePane;
    @FXML
    private RadioButton roseButton;
    @FXML
    private RadioButton OrangeButton;
    @FXML
    private RadioButton TomatoButton;
    @FXML
    private HBox imageBox = new HBox();
    @FXML
    private HBox weatherBox = new HBox();
    private Timeline timeline;
    @FXML
    private Label userInfoLabel;
    @FXML
    private Label label2;
    @FXML
    private Label systemLabel;
    @FXML
    private Button heatingButton;
    @FXML
    private Button sprinklersButton;
    @FXML
    private Button pesticideButton;
    @FXML
    private TextArea logArea;
    @FXML
    private Label statusLabel;

    @FXML
    private Button rainButton;
    @FXML private Label temperatureWarningLabel;

    public ViewController() {
        heatingController = new HeatingController();
        sprinklerController = new SprinklerController();
        pesticideController = new PesticideController();
        rainController = new RainController();
        temperatureController = new TemperatureController();
    }
    @FXML
    public void initialize() {
        try {
            initializeGarden(); // Automatically initialize the garden
            weatherLabel.setText("SUNNY");
            weatherImageView.setImage(sunnyImage);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void initializeGarden() throws FileNotFoundException {
        String imageUrl = getClass().getResource("/images/garden.jpg").toExternalForm();
        gardenGrid.setStyle(
                "-fx-background-image: url('" + imageUrl + "'); " +
                        "-fx-background-repeat: stretch; " +
                        "-fx-background-size: cover; " +
                        "-fx-background-position: center center;"
        );
        int rows = gardenGrid.getRowCount() + 1;
        int cols = gardenGrid.getColumnCount() + 1;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                HBox imageBox = new HBox();
                ImageView soilView = new ImageView();
                soilView.setFitHeight(75);
                soilView.setFitWidth(75);
                soilView.setImage(soilImage);
                imageBox.getChildren().add(soilView);
                gardenGrid.add(imageBox, row, col);
                userInfoLabel.setText("Welcome to the Garden Simulator!");
                userInfoLabel.setText("   Today is Day 1");
            }
        }


    }

    //this is our method to call each plant method depending on which button is selected
    @FXML
    public void plantPlants() {
        log.info("Planting plants");
        EventHandler<MouseEvent> plantHandler = event -> {
            Node node = (Node) event.getSource();
            int row = GridPane.getRowIndex(node);
            int col = GridPane.getColumnIndex(node);
            try {
                if (roseButton.isSelected()) {
                    plantRose(row, col);
                }
                if (OrangeButton.isSelected()) {
                    plantOrange(row, col);
                }
                if (TomatoButton.isSelected()) {
                    plantTomato(row, col);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        };
        for (Node node : gardenGrid.getChildren()) {
            node.setOnMouseClicked(plantHandler);
        }
    }

    //planting a Rose ; creating rose object and adding it to the gird
    public void plantRose(int row, int col) throws FileNotFoundException {
        String roseCell = row + "," + col;
        if (occupiedCells.contains(roseCell)) {
            return;
        }
        HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
        ImageView plantView = new ImageView();
        plantView.setFitHeight(65);
        plantView.setFitWidth(65);
        plantView.setImage(roseImage);
        imageBox.getChildren().add(plantView);
        occupiedCells.add(roseCell);
        Rose rose = new Rose(gardenGrid);
        plantImageViewMap.put(rose, plantView);
        plantsList.add(rose);
    }


    //planting a Orange; create Orange object and add it to the grid
    public void plantOrange(int row, int col) throws FileNotFoundException {
        String cell = row + "," + col;
        if (occupiedCells.contains(cell)) {
            return;
        }
        HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
        ImageView plantView = new ImageView();
        plantView.setFitHeight(65);
        plantView.setFitWidth(65);
        plantView.setImage(orange);
        imageBox.getChildren().add(plantView);
        occupiedCells.add(cell);
        Orange Orange = new Orange(gardenGrid);
        plantImageViewMap.put(Orange, plantView);
        plantsList.add(Orange);
    }

    //planting a Tomato; create Tomato object and add it to the grid
    public void plantTomato(int row, int col) throws FileNotFoundException {
        String cell = row + "," + col;
        if (occupiedCells.contains(cell)) {
            return;
        }
        HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
        ImageView plantView = new ImageView();
        plantView.setFitHeight(65);
        plantView.setFitWidth(65);
        plantView.setImage(tomato);
        imageBox.getChildren().add(plantView);
        occupiedCells.add(cell);
        Tomato Tomato = new Tomato(gardenGrid);
        plantImageViewMap.put(Tomato, plantView);
        plantsList.add(Tomato);
    }
    public void activateHeating() {
        int temperature = heatingController.activateHeating();
        // Updates UI with temperature
    }

    public void activateSprinklers() {
        logMessage("Activated Sprinklers");
        sprinklerController.activateSprinklers(plantsList);

        // Updates UI to indicate sprinklers are active
    }

    @FXML
    private void applyPesticide(ActionEvent event) {
        logMessage("Activated Pesticide");
        pesticideController.applyPesticide(plantsList);
        removePestsImmediately();
    }
    private void removePestsImmediately() {
        Platform.runLater(() -> {
            List<Pest> pestsToRemove = new ArrayList<>();
            for (Pest pest : pests) {
                ImageView pestView = pestImageViewMap.get(pest);
                if (pestView != null) {
                    // Remove ImageView from grid
                    HBox imageBox = (HBox) pestView.getParent();
                    imageBox.getChildren().remove(pestView);
                    pestsToRemove.add(pest);
                    pestImageViewMap.remove(pest);
                    occupiedPestCells.remove(pest.getRow() + "," + pest.getCol());
                }
            }
            pests.removeAll(pestsToRemove);
        });
    }
    @FXML
    private void activateRain(ActionEvent event) {
        logMessage("Activated Rainfall");
        int rainfallAmount = 10; // Set the desired rainfall amount
        rainController.simulateRain(rainfallAmount, plantsList);
        adjustSprinklersBasedOnRainfall(rainfallAmount);
        Platform.runLater(() -> {
            weatherLabel.setText("RAINY");
            weatherImageView.setImage(rainyImage);


            // Schedule weather to return to sunny after 10 seconds
            Timeline weatherResetTimeline = new Timeline(new KeyFrame(
                    Duration.seconds(10),
                    e -> {
                        weatherLabel.setText("Sunny");
                        weatherImageView.setImage(sunnyImage);

                    }
            ));
            weatherResetTimeline.play();
        });

    }
    private void adjustSprinklersBasedOnRainfall(int rainfallAmount) {
        Platform.runLater(() -> {

            if (rainfallAmount <=5) {
                log.warn("Insufficient rainfall received. Activating sprinkler system.");
                logMessage("Insufficient rainfall received. Activating sprinkler system.");
                sprinklerController.activateSprinklers(plantsList);
            } else {
                log.info("Sufficient rainfall received. No need to activate sprinklers.");
                logMessage("Sufficient rainfall received. No need to activate sprinklers.");


            }
        });
    }
    @FXML
    private void adjustTemperature(ActionEvent event) {
        logMessage("Handingly Temperature");
        Platform.runLater(() -> {
            int currentTemperature = getCurrentTemperature();
            temperatureController.adjustTemperature(currentTemperature, plantsList);
            handleTemperatureWarnings(currentTemperature);

        });
    }

    private void handleTemperatureWarnings(int temperature) {
        if (temperature < TemperatureController.LOWER_TEMPERATURE_THRESHOLD) {
            int adjustedTemperature = heatingController.activateHeating();
            log.warn("Warning: Low temperature detected. Heating system activated. Current temperature: " + adjustedTemperature + "°F");
            logMessage("Warning: Low temperature detected. Heating system activated. Current temperature: " + adjustedTemperature + "°F");


        } else if (temperature > 120) {
            log.warn("Extreme high temperature detected. All plants will die.");
            logMessage("Extreme high temperature detected. All plants will die.");

        } else {
            log.info("Temperature is within the normal range.");
            logMessage("Temperature is within the normal range.");
        }
    }
    public void displayLogContents() {
        try {
            // Get the path to the log file
            Path logFilePath = Paths.get("garden-simulation.log");

            try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath.toFile()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    logArea.appendText(line + "\n");
                }
            }
        } catch (IOException e) {
            logArea.appendText("Error reading log file: " + e.getMessage() + "\n");
        }
    }


    private int getCurrentTemperature() {
        return 24;
    }

    void logMessage(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }

    //remove plant object from grid
    public void die() {
        ArrayList<Plant> plants = new ArrayList<>();

        for (Plant plant : plantsList) {
            int col = plant.getCol();
            int row = plant.getRow();
            String cell = row + "," + col;
            ImageView plantView = plantImageViewMap.get(plant);

            if (plantView != null) {
                // remove ImageView from grid
                HBox imageBox = (HBox) plantView.getParent();
                imageBox.getChildren().remove(plantView);

                // remove association between Flower object and ImageView
                plantImageViewMap.remove(plant);
                //occupiedCells.remove(cell);
                occupiedCells.remove(cell);
                plants.add(plant);
            }
        }

        plantsList.removeAll(plants);
    }


    //incrementing each day and calling appropriate methods to run each day
    public void iterateDay() throws IOException {
        userInfoLabel.setText("Today is Day " + day);
    
        // Perform pest control
        pestControl();
    
        // Wait for 3 seconds before adding new pests
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> addPestsToCells());
        delay.play();
    
        if (!occupiedCells.isEmpty()) {
            // pests();
        }
        for (Plant plant : plantsList){
            System.out.println(plant);
        }
        
        day++;
    }
    

    public void iterateDayWithTimer() throws InterruptedException, IOException {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    iterateDay();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        iterateDayButton.setDisable(true);
    }

    public void finish() {
        System.exit(0);
    }

    public void addPestsToCells() {
        // Create a random insect type outside the loop
        Insect insect = createRandomInsectForPlant();  // Get a random insect type
    
        for (String cell : occupiedCells) {
            Random random = new Random();
            int num = random.nextInt(3);  // Random number between 0 and 2
    
            if (num == 1) {
                // Split the cell string into row and column
                String[] parts = cell.split(",");
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                
                // Access the correct HBox in the grid to place the image
                HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
    
                // Create a new instance of the insect each time in the loop
                // Create a new instance of the insect
                
                // Create a new ImageView and set the image of the new insect
                ImageView pestView = new ImageView();
                pestView.setFitHeight(35);
                pestView.setFitWidth(35);
                pestView.setImage(insect.getImage());  // Set the image for the created insect
                
                // Add the ImageView to the HBox in the grid
                imageBox.getChildren().add(pestView);
                Insect newInsect = createInsectInstance(insect);  
                // Update the pestImageViewMap to associate the insect with its ImageView
                pestImageViewMap.put(newInsect, pestView);
                
                // Add the cell to occupiedPestCells to track where pests are
                occupiedPestCells.add(cell);
                
                // Add the new insect to the insects list to track the insect
                insects.add(newInsect);
    
                // Loop through the plants to check if there is a plant in the same position as the insect
                for (Plant plant : Plant.plantsList) {
                    if (plant.getRow() == row && plant.getCol() == col) {
                        // Increase the number of pests for that plant
                        plant.numPests++;
                    }
                }
            }
        }
    }
    
    private Insect createRandomInsectForPlant() {
        Insect[] possibleInsects = {
            new Aphid(),
            new Mites(),
            new Caterpillar(),
            new Beetle(),
        };
    
        return possibleInsects[new Random().nextInt(possibleInsects.length)];
    }
    
    private Insect createInsectInstance(Insect insectType) {
        try {
            return insectType.getClass().getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    

    public void pestKillPlant() {
        Platform.runLater(() -> {
            List<Plant> plantsToRemove = new ArrayList<>();
            List<Insect> pestsToRemove = new ArrayList<>();
            for (Plant plant : plantsList) {
                if (plant.numPests >= 1) {
                    int col = plant.getCol();
                    int row = plant.getRow();
                    String cell = row + "," + col;
                    ImageView plantView = plantImageViewMap.get(plant);

                    if (plantView != null) {
                        // remove ImageView from grid
                        HBox imageBox = (HBox) plantView.getParent();
                        imageBox.getChildren().remove(plantView);

                        // remove association between Plant object and ImageView
                        plantImageViewMap.remove(plant);
                        occupiedCells.remove(cell);
                        plantsToRemove.add(plant);

                        for (Insect pest : insects) {
                            if (pest.getRow() == row && pest.getCol() == col) {
                                ImageView pestView = pestImageViewMap.get(pest);
                                imageBox.getChildren().remove(pestView);
                                pestImageViewMap.remove(pest, pestView);
                                pestsToRemove.add(pest);
                            }
                        }
                    }
                }
            }
            Plant.plantsList.removeAll(plantsToRemove);
            insects.removeAll(pestsToRemove);
        });
    }

    public void pestControl() {
        List<Insect> pestsToRemove = new ArrayList<>();

        for (Insect pest : insects) {
            Random ran = new Random();
            int rando = ran.nextInt(8);

            String cell = pest.getRow() + "," + pest.getCol();
            if (rando != 1) {
                ImageView spiderView = pestImageViewMap.get(pest);

                if (spiderView != null) {
                    // Remove the insect's ImageView from the grid
                    HBox imageBox = (HBox) spiderView.getParent();
                    imageBox.getChildren().remove(spiderView);

                    // Add the pesticide ImageView
                    ImageView pesticideView = new ImageView(pestiside);
                    pesticideView.setFitHeight(35);
                    pesticideView.setFitWidth(35);
                    imageBox.getChildren().add(pesticideView);

                    // Track the pest for removal
                    pestsToRemove.add(pest);

                    pestImageViewMap.remove(pest);
                    occupiedPestCells.remove(cell);

                    // Remove the pesticide image after 2 seconds
                    PauseTransition delay = new PauseTransition(Duration.seconds(2));
                    delay.setOnFinished(event -> imageBox.getChildren().remove(pesticideView));
                    delay.play();
                }
            }
        }

        // Remove pests from the list
        insects.removeAll(pestsToRemove);
        //pestKillPlant();
    }

}
