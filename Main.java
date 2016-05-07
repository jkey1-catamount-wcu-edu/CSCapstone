package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.MenuBar;

public class Main extends Application {

    /**
     *  The Stage is created. The Stage allows us to add javafx features to the program.
     */
        //Stage window;

    /**
     *  Creating the BorderPane Layout for the javafx program.
     */
        BorderPane bLayout;

    /**
     * The Scene node allows us to add things to the stage.
     */
        Scene scene;

    /**
     *  The Height of the initial screen size.
     */
        private int sceneHeight = 700;

    /**
     *  The width of the inital screen size.
     */
        private int sceneWidth = 1000;

    /**
     *  Number for convergence. If the c value goes past this then we know it escapes the set.
     */
        private static final int FOUR = 4;

    /**
     * The WritableImage object to enable writing to an image.
     */
        WritableImage wImage;

    /**
     *  The Pixelwriter onject which allows the manipulation of pixels.
     */
        PixelWriter pixelWriter;

    /**
     * The Image object. We will put the WritableImage onject into the Image object so we can use ImageView to display
     * the custom image.
     */
        Image image;

    /**
     *  The ImageView object. This object is what will ACTUALLY allow us to see the Mandelbrot set image.
     */
        ImageView view;

    /**
     *  The Color object. This allows us to have color for the Mandelbrot set of black.
     */
        Color black      = Color.BLACK;

    /**
     *  The Color object. This allows us to have color for the Mandelbrot set of black.
     */
        Color red      = Color.RED;

    /**
     *  The Color object. This allows us to have color for the Mandelbrot set of green.
     */
        Color green      = Color.GREEN;

    /**
     *  The Color object. This allows us to have color for the Mandelbrot set of teal.
     */
        Color teal      = Color.TEAL;

    /**
     *  The Color object. This allows us to have color for the Mandelbrot set of turqoise.
     */
        Color goldenrod      = Color.GOLDENROD;

    /**
     *  This is the Writable Image height and width. This should not be greater than the scene
     *  height and width.
     */
        int writableImageWidth  = sceneWidth -  100;
        int writableImageHeight = sceneHeight - 100;


    /**
     * The start method is required in javafx. This method acts like a normal main() in Java programming.
     * This method is where are javafx features should be initialized and used accordingly.
     * @param primaryStage - The main stage that works inside start().
     * @throws Exception - Allows for any type of exception to be thrown.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

        // Initializing the layout for the scene.
        bLayout = new BorderPane();

        // Calling the method that creates the menu for the mandelbrot set!
        createMenu();

        // Calling the method that actually will calculate the mandelbrot set.
        calculateMandelbrotSet();

        // Setting the ImageView object to the center or the borderlayout so the user can view the Mandelbrot set.
        bLayout.setCenter(view);

        // Creating the Scene of the application. A scene needs a layout, a screen widith, and a screen height.
        scene = new Scene(bLayout, sceneWidth, sceneHeight);

        // Sets the title of the program.
        primaryStage.setTitle("Mandelbrot Sets: A future distributed Application.");




        // Sets the scene to the Stage. Everything attached the the scene goes onto the Stage.
        primaryStage.setScene(scene);



        // This is how Javafx 'shows' everything attached to the program. This is what actually puts every
        // feature onto the program.
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }



    public void createMenu() {



        // Making the equation, recent, zoom, and picture menus
        Menu equationMenu = new Menu("Equation");
        Menu recentMenu = new Menu("Recent");
        Menu zoomMenu = new Menu("Zoom");
        Menu pictureMenu = new Menu("Picture");

        //Add items to the equation menu
        equationMenu.getItems().add(new MenuItem("Enter Equation"));
        equationMenu.getItems().add(new SeparatorMenuItem());
        equationMenu.getItems().add(new MenuItem("Exit"));

        // Add items to the recent menu
        recentMenu.getItems().add(new MenuItem("last equation"));

        // Add items to the zoom menu
        zoomMenu.getItems().add(new MenuItem("zoom in..."));
        zoomMenu.getItems().add(new MenuItem("zoom out..."));

        // Add items to the picture menu
        pictureMenu.getItems().add(new MenuItem("Save Picture"));
        pictureMenu.getItems().add(new MenuItem("Open PictureBook"));

        // Adding menus to the menu bar
        MenuBar menubar = new MenuBar();
        menubar.getMenus().addAll(equationMenu);
        menubar.getMenus().addAll(recentMenu);
        menubar.getMenus().addAll(zoomMenu);
        menubar.getMenus().addAll(pictureMenu);

        // Setting the MenuBar to the Top portion of the BorderPane.
        bLayout.setTop(menubar);

    }


    public void addPixelsToScreen() {
        // initializing the
        wImage = new WritableImage(writableImageWidth, writableImageHeight);
        pixelWriter = wImage.getPixelWriter();
        image = wImage;
        view = new ImageView(image);



    }

    public void calculateMandelbrotSet() {
        // Calling this method sets up the objects necessary to view a mandelbrot set.
        addPixelsToScreen();

        // The maximum value the algorithm will iterate before deciding the value of x and y have left the mandelbrot
        // set.
        final int MAX = 100;



        // The Complex number values. The 'Real' (x) number and the 'Imaginary' (y) number.
        double cReal;
        double cImaginary;



        // These two for loops iterate through each pixel in the screen and run the algorithm to see if that pixel is in
        // fact part of the Mandelbrot set.
        for (int i = 0; i < writableImageHeight; i++) {
            for (int j = 0; j < writableImageWidth; j++) {
                cReal = (j - writableImageWidth / 2) * (4.0 / writableImageWidth);
                cImaginary = (i - writableImageHeight / 2) * (4.0 / writableImageWidth);


                int iterations = 0;
                double newX    = -1.75;
                double newY    = 0.0;
                double oldX    = 0.0;
                double oldY    = 0.0;

                while ((newX * newX + newY * newY) < (1 << 16) && (iterations < MAX)) {
                    newX = (oldX * oldX) - (oldY * oldY) + cReal;

                    newY = (2 * oldX * oldY) + cImaginary;


                    oldX = newX;
                    oldY = newY;
                    iterations++;
                }

                if (iterations < 20 && iterations < MAX) {
                    pixelWriter.setColor(j, i, red);
                    System.out.println("X Coordinate = " + j);
                    System.out.println("Y Coordinate = " + i);
                    System.out.println("Colored RED");
                    System.out.println("----------------");
                } else if(iterations > 20 && iterations < 60 && iterations < MAX) {

                    pixelWriter.setColor(j, i, goldenrod);
                    System.out.println("X Coordinate = " + j);
                    System.out.println("Y Coordinate = " + i);
                    System.out.println("Colored GoldenRod");
                    System.out.println("-----------------");
                } else if(iterations > 60 && iterations < 90 && iterations < MAX) {
                    pixelWriter.setColor(j, i, green);
                    System.out.println("X Coordinate = " + j);
                    System.out.println("Y Coordinate = " + i);
                    System.out.println("Colored GREEN");
                    System.out.println("-----------------");
                } else {
                    pixelWriter.setColor(j, i, black);
                    System.out.println("X Coordinate = " + j);
                    System.out.println("Y Coordinate = " + i);
                    System.out.println("Colored BLACK");
                    System.out.println("-----------------");
                }
            }
        }
    }


}
