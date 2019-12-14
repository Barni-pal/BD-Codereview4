package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import java.io.*;


public class Main extends Application {

    //ObservableList

        public static ObservableList<Product> items = FXCollections.observableArrayList(
            new Product("Pfeffer", "1 Stück", "Schwarzer Pfeffer verleiht Ihren Speisen \n eine pikante Schärfe,\n besonders wenn er länger mitgekocht wird. ", "3,49", "2,79", "pfeffer.jpg"),
            new Product("Schafmilchkäse", "200 Gramm Packung", "Hier gibt es keine Beschreibung, \nweil unsere Handelskette kennst sich nur bedingt damit aus,\n wie man eine Werbebeschreibung schreibt.", "2,59", "1,99" , "cheese_salakis.jpg"),
            new Product("Vöslauer", "1.5 Liter Flasche" , "Spritziges Vöslauer Mineralwasser.", "0,75", "0,49", "voslauer.jpg"),
            new Product("Zucker", "500 Gramm Paket" , "Natürliches Gelieren wird durch Apfelpektin unterstützt,\n welches im richtigen Verhältnis mit Zitronensäure\n und Kristallzucker abgemischt wurde.", "1,39", "0,89", "zucker.jpg")
    );

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Image + List

        ImageView imageView = new ImageView();

        //Imageview Style

        imageView.setFitHeight(120);
        imageView.setFitWidth(150);

        //Labels

        Label prodNameLabel = new Label("Prod. Name ");
        Label quantityLabel = new Label("Quantity ");
        Label oldPriceLabel = new Label("Old price: ");
        Label newPriceLabel = new Label("new price: ");
        Label eurLabel = new Label("EUR");
        Label eurLabel1 = new Label("EUR");
        Label descTitle = new Label("Description");
        Label description = new Label();

        //Textfields

        TextField txtField1 = new TextField();
        TextField txtField2 = new TextField();
        TextField txtField3 = new TextField();
        TextField txtField4 = new TextField();

        //List

        ListView<Product> list = new ListView<>();
        list.getItems().addAll(items);

        //Buttons
        Button update = new Button("Update");
        Button save = new Button("Save");
        update.setOnAction(actionEvent -> {
            System.out.println("Updated");
            int selectedIdx = list.getSelectionModel().getSelectedIndex();
            if (selectedIdx != -1) {
                String oldPriceString = txtField3.getText();
                String newPriceString = txtField4.getText();
                list.getItems().get(selectedIdx).setOldPrice(oldPriceString);
                list.getItems().get(selectedIdx).setNewPrice(newPriceString);
                list.refresh();
            }
        });

        save.setOnAction(ActionEvent ->{
            FileOutputStream file = null;
            ObjectOutputStream output = null;
            try {
                file = new FileOutputStream(new File("productslist.txt"));
                output = new ObjectOutputStream(file);
                for (int i=0; i <= items.size()-1; i++) {
                    output.writeObject(items.get(i));
                    System.out.println("All Changes have been saved!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    output.close();
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //Observable List -> addValues

        list.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            txtField1.setText(newValue.getProdName());
            txtField2.setText(newValue.getQuantity());
            txtField3.setText(newValue.getOldPrice());
            txtField4.setText(newValue.getNewPrice());
            description.setText(newValue.getDescription());
            imageView.setImage(new Image(this.getClass().getResourceAsStream(newValue.getImgPath())));
        });

        //Textfield Style & Setting - non editable

        txtField1.setDisable(true);
        txtField1.setStyle("-fx-opacity: 1;");
        txtField2.setDisable(true);
        txtField2.setStyle("-fx-opacity: 1;");

        //H+VBoxes

        HBox hBox1 = new HBox(txtField3, eurLabel);
        HBox hBox2 = new HBox(txtField4, eurLabel1);
        HBox hBox3 = new HBox(update, save);
        VBox vBox = new VBox(prodNameLabel, quantityLabel, oldPriceLabel, newPriceLabel);
        VBox vBox1 = new VBox(txtField1, txtField2, hBox1, hBox2);
        VBox vBox2 = new VBox(hBox3, imageView, descTitle, description);

        //Styling

        vBox.setPadding(new Insets(15,12,15,12));
        vBox.setSpacing(10);
        vBox1.setPadding(new Insets(15, 90, 10, 12));
        vBox2.setPadding(new Insets(15,10,10,10));
        descTitle.setStyle("-fx-font-size:1.5em;");

        //Boxing boxes & Initializing

        HBox hBox = new HBox(vBox, vBox1);
        VBox vBox3 = new VBox(hBox, vBox2);
        HBox main = new HBox(vBox3, list);
        primaryStage.setTitle("Set action prices");
        primaryStage.setScene(new Scene(main, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
