package sample;

import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class FoodController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton squidRB;

    @FXML
    private ToggleGroup PastaTg;

    @FXML
    private RadioButton borshRB;

    @FXML
    private ToggleGroup SeaTg;

    @FXML
    private RadioButton ramenRB;

    @FXML
    private ChoiceBox<String> cbSizeSea;
    private final String[] sizeSeaItems = {"Small", "Medium", "Large"};
    private final ObservableList<String> sizeSeaList = FXCollections.observableArrayList(sizeSeaItems);


    @FXML
    private Button minusSoupQnt;

    @FXML
    private Label seaQnt;

    @FXML
    private Button addPastaQnt;

    @FXML
    private Button addDrinkQnt;

    @FXML
    private Label drinkQnt;

    @FXML
    private Button addSeaQnt;

    @FXML
    private Button minusPastaQnt;

    @FXML
    private Button orderBttn;

    @FXML
    private RadioButton waterRB;

    @FXML
    private RadioButton balogRB;


    @FXML
    private ToggleGroup SoupTg;

    @FXML
    private ToggleGroup DrinkTg;

    @FXML
    private RadioButton juiceRB;

    @FXML
    private Button resetBttn;

    @FXML
    private Button minusSeaQnt;

    @FXML
    private Label pastaQnt;

    @FXML
    private RadioButton alfredoRB;

    @FXML
    private RadioButton ShrimpsRB;

    @FXML
    private RadioButton alchRB;

    @FXML
    private Button addSoupQnt;

    @FXML
    private ChoiceBox<String> cbSizeDrink;
    private final String[] sizeDrinkItems = {"300 ml ", "500 ml", "700 ml"};
    private final ObservableList<String> sizeDrinkList = FXCollections.observableArrayList(sizeDrinkItems);

    @FXML
    private ChoiceBox<String> cbSizePasta;
    private final String[] sizePastaItems = {"Small", "Medium", "Large"};
    private final ObservableList<String> sizePastaList = FXCollections.observableArrayList(sizePastaItems);

    @FXML
    private RadioButton tunaRB;


    @FXML
    private ChoiceBox<String> cbSizeSoup;
    private final String[] sizeSoupItems = {"Small", "Large"};
    private final ObservableList<String> sizeSoupList = FXCollections.observableArrayList(sizeSoupItems);

    @FXML
    private Label soupQnt;

    @FXML
    private RadioButton fettRB;

    @FXML
    private RadioButton onionRB;

    @FXML
    private TextArea textArea;

    @FXML
    private CheckBox chkTwo;

    @FXML
    private CheckBox chkNone;

    @FXML
    private CheckBox chkOne;

    @FXML
    private TextArea totalTextArea;

    @FXML
    private Button resetEveryth;

    @FXML
    private Button calculateEvery;

    @FXML
    private Button backBttn;


    Soup soup = new Soup();
    Seafood seafood = new Seafood();
    Pasta pasta = new Pasta();
    Drink drink = new Drink();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //soup
        cbSizeSoup.setItems(sizeSoupList);
        soup.setQuantity(0);
        soup.quantityProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                soupQnt.setText(String.valueOf(soup.getQuantity()));
            }
        });
        //pasta
        cbSizePasta.setItems(sizePastaList);
        pasta.setQuantity(0);
        pasta.quantityProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                pastaQnt.setText(String.valueOf(pasta.getQuantity()));
            }
        });
        //sea
        cbSizeSea.setItems(sizeSeaList);
        seafood.setQuantity(0);
        seafood.quantityProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                seaQnt.setText(String.valueOf(seafood.getQuantity()));
            }
        });
        //drink
        cbSizeDrink.setItems(sizeDrinkList);
        drink.setQuantity(0);
        drink.quantityProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                drinkQnt.setText(String.valueOf(drink.getQuantity()));
            }
        });

        backBttn.setOnAction(actionEvent -> {
            backBttn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/HomePage.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }


    @FXML
    void handleAddSoupQnt(ActionEvent event) {
        soup.setQuantity(soup.getQuantity() + 1);
    }

    @FXML
    void handleMinusSoupQnt(ActionEvent event) {
        soup.setQuantity(soup.getQuantity() - 1);
    }

    @FXML
    void handleOrder(ActionEvent event) {
        Coupon coupon = new Coupon();
        double couponAmount = 0.0;
        DecimalFormat currency = new DecimalFormat("$,###.00");

        // coupon
        if (chkNone.isSelected()) {
            coupon.setPrice(0);
            couponAmount += 0.0;
        }
        if (chkOne.isSelected()) {
            coupon.setPrice(1);
            couponAmount += 1.0;
        }
        if (chkTwo.isSelected()) {
            coupon.setPrice(2);
            couponAmount += 2.0;
        }

        if (ramenRB.isSelected()) {
            soup.setType(1);
            soup.setSize(cbSizeSoup.getSelectionModel().getSelectedIndex());
            soup.setPrice(9.59);
            soup.setQuantity(Integer.parseInt(soupQnt.getText()));
            textArea.appendText(ramenRB.getText() + " "
                    + currency.format(soup.getPrice()) + " "
                    + soup.getQuantity() + " " + soup.getSize()
                    + " " +  currency.format(couponAmount) + " " + "\n");


        } else if (onionRB.isSelected()) {
            soup.setType(2);
            soup.setSize(cbSizeSoup.getSelectionModel().getSelectedIndex());
            soup.setPrice(9.99);
            soup.setQuantity(Integer.parseInt(soupQnt.getText()));
            textArea.appendText(onionRB.getText() + " "
                    + currency.format(soup.getPrice()) + " "
                    + soup.getQuantity() + " " + soup.getSize()
                    + " " + currency.format(couponAmount) + "\n");

        } else if (borshRB.isSelected()) {
            soup.setType(3); // fix this!
            soup.setSize(cbSizeSoup.getSelectionModel().getSelectedIndex());
            soup.setPrice(8.99);
            soup.setQuantity(Integer.parseInt(soupQnt.getText()));
            textArea.appendText(borshRB.getText() + " "
                    + currency.format(soup.getPrice()) + " "
                    + soup.getQuantity() + " " + soup.getSize()
                    + " " + currency.format(couponAmount) + "\n");

        } else {
            textArea.appendText("Please select a Soup type!\n");
        }

        ///////
        if (alchRB.isSelected()) {
            drink.setType(1); // 1 for Coke, 2 for Sprite
            drink.setSize(cbSizeDrink.getSelectionModel().getSelectedIndex());
            drink.setPrice(4.99);
            drink.setQuantity(Integer.parseInt(drinkQnt.getText()));
            textArea.appendText(alchRB.getText() + " "
                    + currency.format(drink.getPrice()) + " "
                    + drink.getQuantity() + " " + drink.getSize()
                    + " " + currency.format(couponAmount) + "\n");

        } else if (waterRB.isSelected()) {
            drink.setType(2);
            drink.setSize(cbSizeDrink.getSelectionModel().getSelectedIndex());
            drink.setPrice(0.59);
            drink.setQuantity(Integer.parseInt(drinkQnt.getText()));
            textArea.appendText(waterRB.getText() + " "
                    + currency.format(drink.getPrice()) + " "
                    + drink.getQuantity() + " " + drink.getSize()
                    + " " + currency.format(couponAmount) + "\n");
        } else if (juiceRB.isSelected()) {
            drink.setType(2);
            drink.setSize(cbSizeDrink.getSelectionModel().getSelectedIndex());
            drink.setPrice(1.59);
            drink.setQuantity(Integer.parseInt(drinkQnt.getText()));
            textArea.appendText(juiceRB.getText() + " "
                    + currency.format(drink.getPrice()) + " "
                    + drink.getQuantity() + " " + drink.getSize()
                    + " " + currency.format(couponAmount) + "\n");
        } else {
            textArea.appendText("Please select a Drink type!\n");
        }

        /////
        if (alfredoRB.isSelected()) {
            pasta.setType(1);
            pasta.setSize(cbSizePasta.getSelectionModel().getSelectedIndex());
            pasta.setPrice(5.59);
            pasta.setQuantity(Integer.parseInt(pastaQnt.getText()));
            textArea.appendText(alfredoRB.getText() + " "
                    + currency.format(pasta.getPrice()) + " "
                    + pasta.getQuantity() + " " + pasta.getSize()
                    + " " + currency.format(couponAmount) + "\n");


        } else if (fettRB.isSelected()) {
            pasta.setType(2);
            pasta.setSize(cbSizePasta.getSelectionModel().getSelectedIndex());
            pasta.setPrice(2.59);
            pasta.setQuantity(Integer.parseInt(pastaQnt.getText()));
            textArea.appendText(fettRB.getText() + " "
                    + currency.format(pasta.getPrice()) + " "
                    + pasta.getQuantity() + " " + pasta.getSize()
                    + " " + currency.format(couponAmount) + "\n");

        } else if (balogRB.isSelected()) {
            pasta.setType(3); // fix this!
            pasta.setSize(cbSizePasta.getSelectionModel().getSelectedIndex());
            pasta.setPrice(1.59);
            pasta.setQuantity(Integer.parseInt(pastaQnt.getText()));
            textArea.appendText(balogRB.getText() + " "
                    + currency.format(pasta.getPrice()) + " "
                    + pasta.getQuantity() + " " + pasta.getSize()
                    + " " + currency.format(couponAmount) + "\n");

        } else {
            textArea.appendText("Please select a Pasta type!\n");
        }
////////
        if (ShrimpsRB.isSelected()) {
            seafood.setType(1); // 1 for Hawaiian, 2 for Seafood, 3 for Vege
            seafood.setSize(cbSizeSea.getSelectionModel().getSelectedIndex()); // 0 for Small, 1 for Medium, 2 for Large
            seafood.setPrice(7.59);
            seafood.setQuantity(Integer.parseInt(seaQnt.getText()));
            textArea.appendText(ShrimpsRB.getText() + " "
                    + currency.format(seafood.getPrice()) + " "
                    + seafood.getQuantity() + " " + seafood.getSize()
                    + " " + currency.format(couponAmount) + "\n");

        } else if (tunaRB.isSelected()) {
            seafood.setType(2);
            seafood.setSize(cbSizeSea.getSelectionModel().getSelectedIndex());
            seafood.setPrice(9.59);
            seafood.setQuantity(Integer.parseInt(seaQnt.getText()));
            textArea.appendText(tunaRB.getText() + " "
                    + currency.format(seafood.getPrice()) + " "
                    + seafood.getQuantity() + " " + seafood.getSize()
                    + " " + currency.format(couponAmount) + "\n");

        } else if (squidRB.isSelected()) {
            seafood.setType(3); // fix this!
            seafood.setSize(cbSizeSea.getSelectionModel().getSelectedIndex());
            seafood.setPrice(12.59);
            seafood.setQuantity(Integer.parseInt(seaQnt.getText()));
            textArea.appendText(squidRB.getText() + " "
                    + currency.format(seafood.getPrice()) + " "
                    + seafood.getQuantity() + " " + seafood.getSize()
                    + " " + currency.format(couponAmount) + "\n");

        } else {
            textArea.appendText("Please select a Seafood type!\n");

        }


        textArea.appendText("Bon Appetite!");
        textArea.setStyle("-fx-text-fill: aqua; -fx-font-size: 16px;");

        writeRecord(soup, drink, seafood, pasta, couponAmount);
    }

    @FXML
    void handleReset(ActionEvent event) {
        //soup
        ramenRB.setSelected(false);
        onionRB.setSelected(false);
        borshRB.setSelected(false);
        soupQnt.setText(null);
        cbSizeSoup.setValue(null);
        // drink
        alchRB.setSelected(false);
        waterRB.setSelected(false);
        juiceRB.setSelected(false);
        drinkQnt.setText(null);
        cbSizeDrink.setValue(null);
        //sea
        squidRB.setSelected(false);
        ShrimpsRB.setSelected(false);
        tunaRB.setSelected(false);
        seaQnt.setText(null);
        cbSizeSea.setValue(null);
        //pasta
        alfredoRB.setSelected(false);
        fettRB.setSelected(false);
        balogRB.setSelected(false);
        pastaQnt.setText(null);
        cbSizePasta.setValue(null);
        textArea.setText(null);
    }

    @FXML
    void handleAddPastaQnt(ActionEvent event) {
        pasta.setQuantity(pasta.getQuantity() + 1);
    }

    @FXML
    void handleMinusPastaQnt(ActionEvent event) {
        pasta.setQuantity(pasta.getQuantity() - 1);
    }

    @FXML
    void handleAddSeaQnt(ActionEvent event) {
        seafood.setQuantity(seafood.getQuantity() + 1);
    }

    @FXML
    void handleMinusSeaQnt(ActionEvent event) {
        seafood.setQuantity(seafood.getQuantity() - 1);
    }


    @FXML
    void handleAddDrinkQnt(ActionEvent event) {
        drink.setQuantity(drink.getQuantity() + 1);
    }

    @FXML
    void handleMinusDrinkQnt(ActionEvent event) {
        drink.setQuantity(drink.getQuantity() - 1);
    }

    @FXML
    private void handleCalculateSales(ActionEvent event) {
        readRecord();
    }


    public void readRecord() {
        FileReader file = null;
        Scanner input = null;

        try {
            file = new FileReader("src\\sample\\sales.txt");

            input = new Scanner(file);

            double soupSubtotal = 0.0;
            double pastaSubtotal = 0.0;
            double seaSubtotal = 0.0;
            double drinkSubtotal = 0.0;
            double total = 0.0;
            double couponAmount = 0.0;
            double couponTotal = 0.0;
            DecimalFormat currency = new DecimalFormat("$,###.00");


            totalTextArea.appendText("Type \t" + "Price \t" + "Quantity \t\t" + "Size \t" + "Subtotal \t\t"
                    + "Type \t" + "Price \t" + "Quantity \t\t" + "Size \t" + "Subtotal \t\t"
                    + "Type \t" + "Price \t" + "Quantity \t\t" + "Size \t" + "Subtotal \t\t"
                    + "Type \t" + "Price \t" + "Quantity \t\t" + "Size \t" + "Subtotal \t\t"
                    + "Coupon Amount" + "\n");

            while (input.hasNext()) {
                // soup
                int soupType = input.nextInt();
                double soupPrice = input.nextDouble();
                int soupQuantity = input.nextInt();
                int soupSize = input.nextInt();
                soupSubtotal = soupPrice * soupQuantity;

                int pastaType = input.nextInt();
                double pastaPrice = input.nextDouble();
                int pastaQuantity = input.nextInt();
                int pastaSize = input.nextInt();
                pastaSubtotal = pastaPrice * pastaQuantity;

                int seaType = input.nextInt();
                double seaPrice = input.nextDouble();
                int seaQuantity = input.nextInt();
                int seaSize = input.nextInt();
                seaSubtotal = seaPrice * seaQuantity;

                int drinkType = input.nextInt();
                double drinkPrice = input.nextDouble();
                int drinkQuantity = input.nextInt();
                int drinkSize = input.nextInt();
                drinkSubtotal = drinkPrice * drinkQuantity;

                couponAmount = input.nextDouble();
                totalTextArea.appendText(soupType + " \t\t" + currency.format(soupPrice)
                        + "\t\t" + soupQuantity + "\t\t" + soupSize + "\t\t"
                        + currency.format(soupSubtotal) + "\t\t"
                        + pastaType + " \t\t" + currency.format(pastaPrice)
                        + "\t\t" + pastaQuantity + "\t\t" + pastaSize + "\t\t"
                        + currency.format(pastaSubtotal) + "\t\t"
                        + seaType + " \t\t" + currency.format(seaPrice)
                        + "\t\t" + seaQuantity + "\t\t" + seaSize + "\t\t"
                        + currency.format(seaSubtotal) + "\t\t"
                        + drinkType + " \t\t" + currency.format(drinkPrice)
                        + "\t\t" + drinkQuantity + "\t\t" + drinkSize + "\t\t"
                        + currency.format(drinkSubtotal) + "\t\t"
                        + currency.format(couponAmount) + "\n");
                total += (soupSubtotal + pastaSubtotal + seaSubtotal + drinkSubtotal);
                couponTotal += couponAmount;
            }
            totalTextArea.appendText("Total Sales: " + currency.format(total) + "\n");
            totalTextArea.appendText("Total Coupon Amount: " + currency.format(couponTotal) + "\n");
            totalTextArea.appendText("Total: " + currency.format(total - couponTotal) + "\n");

            input.close();
        } catch (FileNotFoundException ex1) {
            ex1.toString();
        } catch (IOException ex2) {
            ex2.toString();
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    public void writeRecord(Soup soup, Drink drink, Seafood seafood, Pasta pasta, double couponAmount) {
        FileWriter file = null;
        PrintWriter output = null;

        try {

            file = new FileWriter("src\\sample\\sales.txt", true);

            // Create an output file
            output = new PrintWriter(file);

            output.println(
                    soup.getType() + " " + soup.getPrice() + " "
                            + soup.getQuantity() + " " + soup.getSize() + " "
                            + pasta.getType() + " " + pasta.getPrice() + " "
                            + pasta.getQuantity() + " " + pasta.getSize() + " "
                            + seafood.getType() + " " + seafood.getPrice() + " "
                            + seafood.getQuantity() + " " + seafood.getSize() + " "
                            + drink.getType() + " " + drink.getPrice() + " "
                            + drink.getQuantity() + " " + drink.getSize()
                            + " " + couponAmount);

            output.close();
        } catch (FileNotFoundException ex1) {
            ex1.toString();
        } catch (IOException ex2) {
            ex2.toString();
        } finally {
            if(output != null){
                output.close();
            }
        }
    }
}


