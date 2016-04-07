package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.lang.Math;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.scene.image.WritableImage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class Main extends Application {

    Stage window;




    //Equation draw = new Equation();
    //draw.createMandelbrot();
    //draw.

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

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

        // Creating the borderlayout to add the menu to the top of the screen
        BorderPane bLayout = new BorderPane();
        bLayout.setTop(menubar);

        // Creating the scene and adding it to the show
        Scene scene = new Scene(bLayout, 400, 300);
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window.setTitle("Mandelbrot Sets!");
        window.setScene(scene);
        window.show();
    }


    public static void main(String[] args) {

        // The height of the initial screen size
        int canvasHeight = 300;

        // The width of the inital screen size
        int canvasWidth = 500;

        // this is the minimum value that the x coordinates or real numbers can be when testing convergence
        double minimumRealNumber = -2.0;

        // this is the maimum value that the x coordinate or real numbers can be when testing convergence
        double maximumRealNumber = 1.0;

        // This is the minimum value that our imaginary or y values can contain when testing for convergence
        double minimumImaginaryNumber = -1.2;

        // in order to display the contents of an imaginary number, we have to multiply the min number and the difference
        // of the min and max together by the screen size
        double maximumImaginaryNumber = minimumImaginaryNumber + (maximumRealNumber - minimumRealNumber) *
                (canvasHeight / canvasWidth);

        // the value that enables the x value to fit on the screen
        double realNumberFactor = (maximumRealNumber - minimumRealNumber) / (canvasWidth - 1);

        // the value that enables the y value to fit on the screen
        double imaginaryNumberFactor = (maximumImaginaryNumber - minimumImaginaryNumber) / (canvasHeight - 1);

        // The actual imaginary number from the complex number
        double c_imaginary;

        // The actual real number from the complex number
        double c_real;

        // The total number of iterations we allow the program to run through. Increasing this allows more detail.
        int maxIterations = 6;

        // This is the actual x starting value. Usually begins at 0 unless stated otherwise
        double functionZRealNumber;

        // This is the actual y starting value. usually begins at 0 unless stated otherwise
        double functionZImaginary;

        // Our boolean value for convergence. If we are inside we color the point black, if not we color it some other
        // color.
        boolean isInside = false;

        boolean isIterations = true;

        // The canvas to draw and add to the center borderpane.
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);

        // The Graphics context object used in order to edit the canvas
        GraphicsContext gc = new GraphicsContext();


        System.out.println("Beginning");
        System.out.flush();

        for (int y = 0; y < canvasHeight; y++) {

            c_imaginary = maximumImaginaryNumber - (y * imaginaryNumberFactor);

            for (int x = 0; x < canvasWidth; x++) {
                c_real = minimumRealNumber + (x * realNumberFactor);
                functionZRealNumber = c_real;
                functionZImaginary  = c_imaginary;
                isInside = true;
                for(int i = 0; i < maxIterations; i++) {
                    System.out.println("I made it! 2");
                    System.out.flush();
                    double functionZRealSquared      = (functionZRealNumber * functionZRealNumber);
                    double functionZImaginarySquared = (functionZImaginary * functionZImaginary);

                    System.out.println(functionZRealSquared);
                    System.out.flush();

                    System.out.println(functionZImaginarySquared);
                    System.out.flush();

                    //while(isIterations) {
                        if ((functionZRealSquared + functionZImaginarySquared) > 4) {
                            isInside = false;
                            isIterations = false;
                     //   }
                    }
                    functionZImaginary  = 2 *functionZRealNumber*functionZImaginary + c_imaginary;
                    functionZRealNumber = functionZRealSquared - functionZImaginarySquared + c_real;

                    System.out.println(functionZRealNumber);
                    System.out.println(functionZImaginary);
                }
            }
            if(isInside) {
                // this is where we place a pixel
                // this is also where we would COLOR the pixel
            }
        }
        launch(args);

    }


}
