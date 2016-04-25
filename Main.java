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
        private int sceneHeight = 800;

    /**
     *  The width of the inital screen size.
     */

        private int sceneWidth = 500;

    /**
     *  This is the minimum value that the x coordinates or real numbers can be when testing convergence.
     */

        private double minimumRealNumber = -2.0;

     /**
      * This is the maimum value that the x coordinate or real numbers can be when testing convergence
      */
        private double maximumRealNumber = 1.0;

    /**
     *  This is the minimum value that our imaginary or y values can contain when testing for convergence
     */
        private double minimumImaginaryNumber = -1.2;

    /**
     * The max value the imaginary value can be.
     */
        private double maximumImaginaryNumber;

    /**
     *  The value that enables the x value to fit on the screen.
     */
        private double realNumberFactor;

    /**
     *  The value that enables the y value to fit on the screen.
     */
        private double imaginaryNumberFactor;

    /**
     *  The actual imaginary number from the complex number.
     */
        private double c_imaginary;

    /**
     *  The actual real number from the complex number.
     */
        private double c_real;

    /**
     *  The total number of iterations we allow the program to run through. Increasing this allows more detail.
     */
        private int maxIterations = 100;

    /**
     *  This is the actual x starting value. Usually begins at 0 unless stated otherwise.
     */
        private double functionZRealNumber;

    /**
     *  This is the actual y starting value. usually begins at 0 unless stated otherwise.
     */
        private double functionZImaginary;

    /**
     *  Our boolean value for convergence. If we are inside we color the point black, if not we color it some other
     *  color.
     */
        private boolean isInside = false;

    /**
     *  Number for convergence. If the c value goes past this then we know it escapes the set.
     */
        private static final int FOUR = 4;

    /**
     * The WritableImage object to enable writing to an image.
     */
        WritableImage wr;

    /**
     *  The Pixelwriter onject which allows the manipulation of pixels.
     */
        PixelWriter pr;

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
        Color turquoise      = Color.TURQUOISE;


    /**
     * The start method is required in javafx. This method acts like a normal main() in Java programming.
     * This method is where are javafx features should be initialized and used accordingly.
     * @param primaryStage - The main stage that works inside start().
     * @throws Exception - Allows for any type of exception to be thrown.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

        // Initializing the variables
        //window = primaryStage;
        bLayout = new BorderPane();
        createMenu();
        calculations2();

        bLayout.setCenter(view);

        scene = new Scene(bLayout, 2000, 2000);

        // Sets the title of the program.
        primaryStage.setTitle("Mandelbrot Sets!");




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

    public void calculations() {
        addPixelsToScreen();

        // in order to display the contents of an imaginary number, we have to multiply the min number and the difference
        // of the min and max together by the screen size
        maximumImaginaryNumber = minimumImaginaryNumber + (maximumRealNumber - minimumRealNumber) *
                (sceneHeight / sceneWidth);


        realNumberFactor = (maximumRealNumber - minimumRealNumber) / (sceneWidth - 1);


        imaginaryNumberFactor = (maximumImaginaryNumber - minimumImaginaryNumber) / (sceneHeight - 1);




        System.out.println("Beginning");
        System.out.flush();

        for (int y = 0; y < sceneHeight; y++) {

            c_imaginary = maximumImaginaryNumber - (y * imaginaryNumberFactor);

            for (int x = 0; x < sceneWidth; x++) {
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


                    if ((functionZRealSquared + functionZImaginarySquared) > FOUR) {
                        isInside = false;
                        //isIterations = false;
                        //pr.setColor(functionZImaginary, functionZRealNumber, red);
                        System.out.println("Colored Node Red");


                    } else {
                        //pr.setColor(functionZImaginary, functionZRealNumber, black);
                        System.out.println("colored node black");

                    }
                    ;

                    functionZImaginary  = 2 *functionZRealNumber*functionZImaginary + c_imaginary;
                    functionZRealNumber = functionZRealSquared - functionZImaginarySquared + c_real;
                    //maxIterations++;

                    System.out.println(functionZRealSquared);
                    System.out.println(functionZImaginarySquared);
                }
            }

        }
    }

    public void addPixelsToScreen() {
        wr = new WritableImage(sceneWidth / 2, sceneHeight / 2);
        pr = wr.getPixelWriter();
        image = wr;
        view = new ImageView(image);



    }

    public void brainstorm() {
        addPixelsToScreen();


        int oldX = 0;
        int oldY = 0;

        int newX = 0;
        int newY = 0;

        int iteratedRealNumber = 1;
        int iteratedImaginaryNumber = 1;


        int iterations = 100;



        for(int i = 0; i < iterations; i++) {
            newX = (oldX*oldX) - (oldY*oldY);
            newX+= iteratedRealNumber;

            newY = (2*oldX*oldY);
            newY+= iteratedImaginaryNumber;

            if(newX*newX + oldY*oldY > FOUR) {
                pr.setColor(newX, newY, red);
                System.out.println("X = " + newX);
                System.out.println("Y = " + newY);
                System.out.println("Colored RED");


            } else {
                pr.setColor(newX, newY, black);
                System.out.println("X = " + newX);
                System.out.println("Y = " + newY);
                System.out.println("Colored BLACK");

            }

            oldX = newX;
            oldY = newY;
        }



    }

    public void calculations2() {
        addPixelsToScreen();
        int width = 1920, height = 1080, max = 1000;
        //BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //int black = 0x000000, white = 0xFFFFF1;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                double c_re = (col - width / 2) * 4.0 / width;
                double c_im = (row - height / 2) * 4.0 / width;
                double x = 0, y = 0;
                int iterations = 0;
                while (x * x + y * y < 4 && iterations < max) {
                    double x_new = x * x - y * y + c_re;
                    y = 2 * x * y + c_im;
                    x = x_new;
                    iterations++;
                }
                if (iterations < max) {

                    pr.setColor(col, row, red);

                    System.out.println("Colored RED");
                } else {
                    pr.setColor(col, row, black);
                }
            }

        }

        bLayout.setCenter(view);
        //ImageIO.write(image, "png", new File("mandelbrot.png"));
    }

}
