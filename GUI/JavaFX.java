/**
 * GUI: Graphic User Interfaces, interfaces that users interact with asynchronously
 *
 * Steps for JavaFX Application
 *   1. class JavaFXApp extends javafx application
 *   2. main() method calls launch()
 *   3. launch() initializes JavaFX, then calls start(stage)
 *   4. start(Stage mainStage) sets up GUI on stage
 *         -- create controls and place them into layout managers
 *         -- place layout managers into scene
 *         -- place scene into stage
 *         -- mainStage.show()
 *
 * Interface Controls (GUI elements, import javafx.scene.controls.*)
 *     Label label = new Label("user name: ");
 *     Button button = new Button("submit");
 *     TextField -> get user input as a String
 *     TextArea -> get paragraph of text from user
 *     ImageView -> display images for users
 *     ComboBox -> show multiple options in a drop-down list
 *
 * Event (a Java object that represents user interaction with the GUI and contains data about it, javafx.event.Event)
 * !!! Events are asynchronous (happen at any time)
 * 
 * Steps to add to GUI controls:
 *   1. define class that implements interface
 *   2. create instance of that class
 *   3. register instance with GUI controls (setOnAction)
 *
 * Window Layout Managers -- can be combined
 *    help to place / organize things on the screen
 *    adapt to different screen sizes and resolutions, such that GUI remain usable across screens and devices
 *    Types:
 *        BorderPane -- Top, Left, Center, Right, Bottom
 *        HBox: everything in a horizontal row
 *        VBox: everything in a vertical row
 *        GridPane: set number of rows and columns
 */
