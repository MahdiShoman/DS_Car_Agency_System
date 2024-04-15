import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Customer {
    private String customerName;
    private String customerNumber;
    Customer(){}

    Customer(String name , String num){
        this.customerName = name.trim();
        this.customerNumber=num.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName.trim();
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber.trim();
    }


    Label askLabel = new Label("you know the brand and model of car ");
    ToggleGroup tg = new ToggleGroup();

    // create radiobuttons
    RadioButton yesRadioButton = new RadioButton("yes");
    RadioButton noRadioButton = new RadioButton("no");

    /* for the radio button */

    Button conformButton =  new Button("Conform");
    Button searchButton =  new Button("Search");
    ComboBox<Object> carBrandsComboBox = new ComboBox<>();
    ComboBox<Object> carModelComboBox = new ComboBox<>();
    ComboBox<Object> carYearComboBox = new ComboBox<>();
    ComboBox<Object> carColorComboBox = new ComboBox<>();
    ComboBox<Object> carPriceComboBox = new ComboBox<>();

    private final TableView<Car> table = new TableView<>();
    Button orderNowButton =  new Button("Order Now");

    GridPane topGridPane = new GridPane();
    HBox bottomHBOX = new HBox();
    VBox bottomVBox = new VBox();
    TextField customerNameField = new TextField();
    TextField customerNumField  = new TextField();
    Button orderItButton =  new Button("Order It");
    BorderPane borderInsert = new BorderPane();
    HBox insertHbox = new HBox();
    TextField insetBrand = new TextField();
    TextField insetYear = new TextField();
    TextField insetColor = new TextField();
    TextField insetModel = new TextField();
    TextField insetPrice = new TextField();
    Button conformInsertButton = new Button("Conform");
    Button insertSaveButton =  new Button("Save");

    BorderPane borderUpdate = new BorderPane();
    HBox updateHbox = new HBox();
    TextField updateBrand = new TextField();
    TextField updateYear = new TextField();
    TextField updateColor = new TextField();
    TextField updateModel = new TextField();
    TextField updatePrice = new TextField();
    Button conformUpdateButton = new Button("Conform");
    Button updateSaveButton =  new Button("Save");

    BorderPane borderDelete= new BorderPane();
    HBox deleteHbox = new HBox();
    TextField deleteBrand = new TextField();
    TextField deleteYear = new TextField();
    TextField deleteColor = new TextField();
    TextField deleteModel = new TextField();
    TextField deletePrice = new TextField();
    Button conformDeleteButton = new Button("Conform");
    Button deleteSaveButton =  new Button("Save");

    BorderPane customerBorderPane = new BorderPane();
    BorderPane orderNowBorderPane = new BorderPane();

    BorderPane getCustomer (){

        TableColumn<Car, String> brandName = new TableColumn<>("Brand Name");
        // catch column with obj
        brandName.setCellValueFactory(data -> new SimpleStringProperty(((Car)data.getValue()).getBrandName()));


        TableColumn<Car, String> carModel = new TableColumn<>("Car Model");
        // catch column with obj
        carModel.setCellValueFactory(data -> new SimpleStringProperty(((Car)data.getValue()).getModel()));


        TableColumn<Car, String> carYear = new TableColumn<>("Car Year");
        // catch column with obj
        carYear.setCellValueFactory(data -> new SimpleStringProperty(((Car)data.getValue()).getYear()));




        TableColumn<Car , String> carColor = new TableColumn<>("Car color");
        // catch column with obj
        carColor.setCellValueFactory(data -> new SimpleStringProperty(((Car)data.getValue()).getColor()));


        TableColumn<Car , String> carPrice = new TableColumn<>("Car Price");
        // catch column with obj
        carPrice.setCellValueFactory(data -> new SimpleStringProperty(((Car)data.getValue()).getPrice()));

        // Add the columns to the table
        //martyrNameColumn,carYear,martyrDeathDateColumn,martyrGenderColumn
        table.getColumns().addAll(brandName,carModel, carYear,carColor,carPrice);

   /* searchButton.setOnAction(actionEvent -> {

    });*/

        bottomHBOX.getChildren().addAll(customerNameField,customerNumField,orderItButton);
        bottomHBOX.setAlignment(Pos.CENTER);
        bottomHBOX.setSpacing(10);
        customerNameField.setPromptText("Your Name");
        customerNumField.setPromptText("Your Number");


        // add radiobuttons to toggle group
        yesRadioButton.setToggleGroup(tg);
        noRadioButton.setToggleGroup(tg);



        topGridPane.add(askLabel,0,0); topGridPane.add(yesRadioButton,1,0); topGridPane.add(noRadioButton,2,0);
        topGridPane.add(searchButton,5,2);





        MainButtonsEvent mainButtonsEvent = new MainButtonsEvent();
        yesRadioButton.setOnAction(mainButtonsEvent);
        noRadioButton.setOnAction(mainButtonsEvent);

        searchButton.setOnAction(mainButtonsEvent);
        orderNowButton.setOnAction(mainButtonsEvent);
        orderItButton.setOnAction(mainButtonsEvent);

        customerBorderPane.setTop(topGridPane);
        customerBorderPane.setCenter(table);
        bottomVBox.getChildren().add(orderNowButton);
        bottomVBox.setAlignment(Pos.CENTER);
        bottomVBox.setSpacing(20);
        customerBorderPane.setBottom(bottomVBox);


        return customerBorderPane;
    }

/*
    Scene scene = new Scene(orderNowBorderPane,600,500);
    Stage stage = new Stage();*/
    static Alert alertWarning = new Alert(Alert.AlertType.WARNING);
    public  class MainButtonsEvent implements EventHandler<ActionEvent> { // the handler button
        @Override
        public void handle(ActionEvent event) {
            try {

                if (event.getSource() == yesRadioButton) {
                    alertWarning.setTitle("Warning ");
                    alertWarning.setHeaderText(" choose a Brand then click conform");
                    alertWarning.show();
                    DoubleNode current = Main.doubleLinkedList.first;
                    // System.out.println(current);
                    while(current!=null){
                        carBrandsComboBox.getItems().add((String) current.element);
                        current=current.next;
                    }
                    if(topGridPane.getChildren().contains(carBrandsComboBox)){
                        alertWarning.setTitle("Warning ");
                        alertWarning.setHeaderText(" choose a Brand then click conform");
                        alertWarning.show();

                    }else {
                        topGridPane.add(carBrandsComboBox,1,1);topGridPane.add(conformButton,2,1);
                        conformButton.setOnAction(e ->{
                            Object brandField = carBrandsComboBox.getValue();
                            if (brandField !=null) {
                                Object resultOfSearch = Car.search_return_headerListOfDoubleNode(brandField);
                                if (!resultOfSearch.equals(brandField)) {
                                    carModelComboBox.getItems().clear();
                                    carPriceComboBox.getItems().clear();
                                    carColorComboBox.getItems().clear();
                                    carYearComboBox.getItems().clear();
                                    Node currentCar = (Node) resultOfSearch;
                                    while (currentCar != null) {

                                        carModelComboBox.getItems().add(((Car) currentCar.element).getModel());

                                        carYearComboBox.getItems().add(((Car) currentCar.element).getYear());

                                        carColorComboBox.getItems().add(((Car) currentCar.element).getColor());

                                        carPriceComboBox.getItems().add(((Car) currentCar.element).getPrice());

                                        currentCar = currentCar.next;
                                    }
                                }
                            }

                        });
                        topGridPane.add(carModelComboBox,1,2); topGridPane.add(carYearComboBox,2,2); topGridPane.add(carColorComboBox,3,2); topGridPane.add(carPriceComboBox,4,2);
                    }

                } else if (event.getSource()== noRadioButton) {

                    if(topGridPane.getChildren().contains(carBrandsComboBox)){
                        topGridPane.getChildren().removeAll(carBrandsComboBox, conformButton, carModelComboBox);
                        carBrandsComboBox.getItems().clear();
                        carModelComboBox.getItems().clear();
                        carPriceComboBox.getItems().clear();
                        carColorComboBox.getItems().clear();
                        carYearComboBox.getItems().clear();
                        DoubleNode currentBrand = Main.doubleLinkedList.first;
                        while (currentBrand!=null) {
                            Node currentCar = (Node) currentBrand.headerList;
                            while (currentCar != null) {
                                carYearComboBox.getItems().add(((Car) currentCar.element).getYear());

                                carColorComboBox.getItems().add(((Car) currentCar.element).getColor());

                                carPriceComboBox.getItems().add(((Car) currentCar.element).getPrice());

                                currentCar = currentCar.next;
                            }
                            currentBrand=currentBrand.next;
                        }
                        //   topGridPane.add(carYear,2,2); topGridPane.add(carColor,3,2); topGridPane.add(carPrice,4,2);

                    }
                }else if (event.getSource()== searchButton) {
                    table.getItems().clear();
                    if(((String) carBrandsComboBox.getValue())==null){
                        carBrandsComboBox.setValue("");
                    }
                    if(((String) carModelComboBox.getValue())==null){
                        carModelComboBox.setValue("");
                    }
                    if(((String) carYearComboBox.getValue())==null){
                        carYearComboBox.setValue("");
                    }
                    if(((String) carColorComboBox.getValue())==null){
                        carColorComboBox.setValue("");
                    }
                    if(((String) carPriceComboBox.getValue())==null){
                        carPriceComboBox.setValue("");
                    }
                   Car. search((String) carBrandsComboBox.getValue(),(String) carModelComboBox.getValue(),(String) carYearComboBox.getValue(),(String) carColorComboBox.getValue(),(String) carPriceComboBox.getValue(),table);
                }else if (event.getSource()== orderNowButton) {
                    if (!bottomVBox.getChildren().contains(customerNameField) ) {
                        bottomVBox.getChildren().add(bottomHBOX);
                    }
                }else if (event.getSource()== orderItButton) {
                    Car selectedItems =  table.getSelectionModel().getSelectedItem();

                    Customer c= new Customer(customerNameField.getText(),customerNumField.getText());
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDateTime now = LocalDateTime.now();
                    System.out.println();
                    Orders o = new Orders(c,  selectedItems, dtf.format(now),"InProcess");
                    Main.queue.enqueue(o);

                    Main.queue.printQueue();
                }


            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

}
