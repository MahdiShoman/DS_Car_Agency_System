import javafx.beans.property.SimpleStringProperty;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



public class Car {
    private  String  brandName ;
    private String Model ;
    private String year ;
    private String color ;
    private String price;

    Car(){

    }
    public Car(String  brandName, String model, String year, String color, String price) {
        this.brandName = brandName.trim();
        this.Model = model.trim();
        this.year = year.trim();
        this.color = color.trim();
        this.price = price.trim();
    }
    public Car(Brand  brandName, String model, String year, String color, String price) {
        this.brandName = brandName.getBrandName().trim();
        this.Model = model.trim();
        this.year = year.trim();
        this.color = color.trim();
        this.price = price.trim();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName=(brandName).trim() ;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price.trim();
    }


    public static Object search_return_headerListOfDoubleNode(Object x ) {//take the name of city and return the headerList of it's Node
        // i can check the return uses the .getsours() == String
        // or if it equals x
        DoubleNode current = Main.doubleLinkedList.first;
        while(current!=null) {
            if (((String)current.element).equalsIgnoreCase((String) x)) {
                //System.out.println("yes");
                return (current.headerList);
            }
            current = current.next;

        }

        return x;
    }
    public static DoubleNode search_return_headerList(Object x ) {//take the name of city and return the headerList of it's Node
        // i can check the return uses the .getsours() == String
        // or if it equals x
        DoubleNode current = Main.doubleLinkedList.first;
        while(current!=null) {
            if (((String)current.element).equalsIgnoreCase((String) x)) {

                return (current);
            }
            current = current.next;

        }

        return null;
    }

    static Node search ( String brandName,String model, String year, String color, String price,TableView<Car> table){

        if (!(brandName.trim().equals("") && model.trim().equals("") && year.trim().equals("") && color.trim().equals("") && price.trim().equals(""))){
          //  Object headerListOfDoubleNode = search_return_headerListOfDoubleNode(brandName);
            DoubleNode doubleNode = search_return_headerList(brandName);
            assert doubleNode != null;
            Node current = doubleNode.headerList;
            if(!brandName.trim().equals("")){
                while (current!=null){
                    if (((Car)current.element).getBrandName().equalsIgnoreCase(brandName)) {
                        if (((Car)current.element).getModel().equalsIgnoreCase(model)){
                            if(((Car)current.element).getYear().equalsIgnoreCase(year)){
                                if (((Car)current.element).getColor().equalsIgnoreCase(color)){
                                    if (((Car)current.element).getPrice().equalsIgnoreCase(price)){
                                        table.getItems().add((Car) current.element);
                                      //  return current;//Node
                                    } else if (price.equals("")) {
                                        table.getItems().add((Car) current.element);
                                       // return current;//Node
                                    }
                                } else if (color.equals("")) {
                                    if (((Car)current.element).getPrice().equalsIgnoreCase(price)){
                                        table.getItems().add((Car) current.element);
                                       // return current;//Node
                                    } else if (price.equals("")) {
                                        table.getItems().add((Car) current.element);
                                       // return current;//Node
                                    }
                                }
                            }else if(year.equals("")) {
                                if (((Car)current.element).getColor().equalsIgnoreCase(color)){
                                    if (((Car)current.element).getPrice().equalsIgnoreCase(price)){
                                        table.getItems().add((Car) current.element);
                                      //  return current;//Node
                                    } else if (price.equals("")) {
                                        table.getItems().add((Car) current.element);
                                      //  return current;//Node
                                    }
                                } else if (color.equals("")) {
                                    if (((Car)current.element).getPrice().equalsIgnoreCase(price)){
                                        table.getItems().add((Car) current.element);
                                    } else if (price.equals("")) {
                                        table.getItems().add((Car) current.element);
                                        /*return null;//*/
                                    }
                                }
                            }
                        } else if (model.equals("")) {
                            if(((Car)current.element).getYear().equalsIgnoreCase(year)){
                                if (((Car)current.element).getColor().equalsIgnoreCase(color)){
                                    if (((Car)current.element).getPrice().equalsIgnoreCase(price)){
                                        table.getItems().add((Car) current.element);
                                    } else if (price.equals("")) {
                                        table.getItems().add((Car) current.element);
                                    }
                                } else if (color.equals("")) {
                                    if (((Car)current.element).getPrice().equalsIgnoreCase(price)){
                                        table.getItems().add((Car) current.element);//Node
                                    } else if (price.equals("")) {
                                        table.getItems().add((Car) current.element);//Node
                                    }
                                }
                            }else if(year.equals("")) {
                                if (((Car)current.element).getColor().equalsIgnoreCase(color)){
                                    if (((Car)current.element).getPrice().equalsIgnoreCase(price)){
                                        table.getItems().add((Car) current.element);//Node
                                    } else if (price.equals("")) {
                                        table.getItems().add((Car) current.element);//Node
                                    }
                                } else if (color.equals("")) {
                                    if (((Car)current.element).getPrice().equalsIgnoreCase(price)){
                                        table.getItems().add((Car) current.element);//Node
                                    } else if (price.equals("")) {
                                        return null;//
                                    }
                                }
                            }
                        }

                    }else{
                        System.out.println(current.element.toString());


                    }
                    current=current.next;
                }
            }else {
                while(current!=null){
                    if(((Car)current.element).getYear().equalsIgnoreCase(year)){
                        if (((Car)current.element).getColor().equalsIgnoreCase(color)){
                            if (((Car)current.element).getPrice().equalsIgnoreCase(price)){
                                table.getItems().add((Car) current.element);
                            } else if (price.equals("")) {
                                table.getItems().add((Car) current.element);
                            }
                        } else if (color.equals("")) {
                            if (((Car)current.element).getPrice().equalsIgnoreCase(price)){
                                table.getItems().add((Car) current.element);
                            } else if (price.equals("")) {
                                table.getItems().add((Car) current.element);
                            }
                        }
                    }else if(year.equals("")) {
                        if (((Car)current.element).getColor().equalsIgnoreCase(color)){
                            if (((Car)current.element).getPrice().equalsIgnoreCase(price)){
                                table.getItems().add((Car) current.element);
                            } else if (price.equals("")) {
                                table.getItems().add((Car) current.element);
                            }
                        } else if (color.equals("")) {
                            if (((Car)current.element).getPrice().equalsIgnoreCase(price)){
                                table.getItems().add((Car) current.element);
                            } else if (price.equals("")) {
                                return null;//
                            }
                        }
                    }
                    current=current.next;
                }

            }

        }else {
            System.out.println("SET SOME details TO SHOW THE DATA");
            return null;//
        }
        return null;
    }
    void insert(Object car){
        Object resultOfSearch = search_return_headerListOfDoubleNode(((Car)car).getBrandName());
        if(!resultOfSearch.equals(((Car)car).getBrandName())){
            // now we need to find if the martyr is in or not ,(we need to loob the list),
            boolean founded = false;
            Node current =(Node) resultOfSearch;
            while (current.next!=null){
                if (current.element.equals(car)) {
                    founded = true;
                   alertWarning.setTitle("Warning ");
                    alertWarning.setHeaderText(" the car is on the list actually >: ");
                    alertWarning.showAndWait();
                    break;
                }
                current = current.next;
            }
            if(!founded){
                Node temp = new Node(car);
                Main.linkedList.addLast(car);
                current.next = temp;
                //from me
                temp.next=null;

             /*   button.alertInformation.setTitle("Warning ");
                button.alertInformation.setContentText("the insert is done !!");
                button.alertInformation.setHeaderText("Don't forget save your work before exit ");
                button.alertInformation.showAndWait();*/
            }

        }else {
            Brand.addBrand(((Car)car).getBrandName());
            insert(car);
        }//else print in the lable that is the city name is not exist


    } //take the header of the city name  and insert

    void update(String  brandName, String model, String year, String color, String price ){//in update take the header of list  and walk to reach the obj you wont and update
        Object resultOfSearch = search_return_headerListOfDoubleNode(brandName);
        Car car = new Car(brandName, model, year,  color,  price);
        if(!(resultOfSearch).equals(brandName)){ // if  resultOfSearch != city Name  that's mean that is founded the city
            // now we need to find if the car is in or not ,(we need to loob the list),
            Node current =(Node) resultOfSearch;
            while (current!=null){
                if (((Car)current.element).getBrandName().equalsIgnoreCase(brandName)&&((Car)current.element).getModel().equalsIgnoreCase(model)) {
                     ((Car)current.element).setBrandName(brandName);
                    ((Car)current.element).setColor(color);
                    ((Car)current.element).setModel(model);
                    ((Car)current.element).setPrice(price);
                    ((Car)current.element).setYear(year);
                    Main.linkedList.addLast(current);
                    break;
                }
                current = current.next;
            }

        }else{
            Main.doubleLinkedList.addLast(brandName);
            Main.linkedList.addLast(car);
            Main.doubleLinkedList.addList(brandName,car);
        }

    }

    static void delete ( Car car){
        DoubleNode resultOfSearch = search_return_headerList((car).getBrandName());
        if(resultOfSearch != null){
            // now we need to find if the martyr is in or not ,(we need to loob the list),

            Node previous = resultOfSearch.headerList;
            Node current =previous.next;
            if (compare((Car)previous.element,car)==5 ){
               resultOfSearch.setHeaderList(current);
                Main.linkedList.removeValue(car);
                Main.linkedList.count--;
                return;
            }
            while (current != null && compare((Car)current.element,car)!=5) {
                previous = current;
                current = current.next;
            }
            if (current != null) {
                previous.next = current.next;
                Main.linkedList.removeValue(car);
                Main.linkedList.count--;
            }

        }

    }

    static int compare (Car c1,Car c2){
        int res =0;
        if (c1.getBrandName().equalsIgnoreCase(c2.getBrandName())) {
            res++;
        }
        if (c1.getModel().equalsIgnoreCase(c2.getModel())) {
            res++;
        }
        if (c1.getYear().equalsIgnoreCase(c2.getYear())) {
            res++;
        }
        if (c1.getColor().equalsIgnoreCase(c2.getColor())) {
            res++;
        }
        if (c1.getPrice().equalsIgnoreCase(c2.getPrice())) {
            res++;
        }
        return res;
    }



    @Override
    public String toString() {
        return "Car{" +
                "brandName=" + brandName +
                ", Model='" + Model + '\'' +
                ", year='" + year + '\'' +
                ", color='" + color + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    /* for the radio button */
    Label askLabel = new Label("you know the brand and model of car ");
    ToggleGroup tg = new ToggleGroup();

    // create radiobuttons
    RadioButton yesRadioButton = new RadioButton("yes");
    RadioButton noRadioButton = new RadioButton("no");

    /* for the radio button */

    Button conformButton =  new Button("Conform");
    ComboBox<Object> carBrandsComboBox = new ComboBox<>();
    ComboBox<Object> carModelComboBox = new ComboBox<>();
    ComboBox<Object> carYearComboBox = new ComboBox<>();
    ComboBox<Object> carColorComboBox = new ComboBox<>();
    ComboBox<Object> carPriceComboBox = new ComboBox<>();

    private final TableView<Car> table = new TableView<>();
    Button searchButton =  new Button("Search");
    Button insertButton =  new Button("Insert");
    Button updateButton =  new Button("Update");
    Button deleteButton =  new Button("Delete");
    Button saveButton =  new Button("Save");

    GridPane topGridPane = new GridPane();
    HBox bottomHBOX = new HBox();

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

    BorderPane borderCars = new BorderPane();
BorderPane getCars (){
    insetBrand.setPromptText("Brand name");
    insetModel.setPromptText("Model name");
    insetColor.setPromptText("Color name");
    insetYear.setPromptText("Year name");
    insetPrice.setPromptText("Price name");

    updateBrand.setPromptText("Brand name");
    updateModel.setPromptText("Model name");
    updateColor.setPromptText("Color name");
    updateYear.setPromptText("Year name");
    updatePrice.setPromptText("Price name");

    deleteBrand.setPromptText("Brand name");
    deleteModel.setPromptText("Model name");
    deleteColor.setPromptText("Color name");
    deleteYear.setPromptText("Year name");
    deletePrice.setPromptText("Price name");

    TableColumn<Car, String> brandName = new TableColumn<>("Brand Name");
    // catch column with obj
    brandName.setCellValueFactory(data -> new SimpleStringProperty((data.getValue()).brandName));


    TableColumn<Car, String> carModel = new TableColumn<>("Car Model");
    // catch column with obj
    carModel.setCellValueFactory(data -> new SimpleStringProperty((data.getValue()).getModel()));


    TableColumn<Car, String> carYear = new TableColumn<>("Car Year");
    // catch column with obj
    carYear.setCellValueFactory(data -> new SimpleStringProperty((data.getValue()).getYear()));




    TableColumn<Car , String> carColor = new TableColumn<>("Car color");
    // catch column with obj
    carColor.setCellValueFactory(data -> new SimpleStringProperty((data.getValue()).getColor()));


    TableColumn<Car , String> carPrice = new TableColumn<>("Car Price");
    // catch column with obj
    carPrice.setCellValueFactory(data -> new SimpleStringProperty((data.getValue()).getPrice()));

    // Add the columns to the table
    //martyrNameColumn,carYear,martyrDeathDateColumn,martyrGenderColumn
    table.getColumns().addAll(brandName,carModel, carYear,carColor,carPrice);



    bottomHBOX.getChildren().addAll(insertButton,updateButton,deleteButton,saveButton);
    bottomHBOX.setAlignment(Pos.CENTER);

    insertHbox.getChildren().addAll(insetBrand,insetModel,insetYear,insetColor,insetPrice,conformInsertButton);
    insertHbox.setAlignment(Pos.CENTER);
    borderInsert.setCenter(insertHbox);

    updateHbox.getChildren().addAll(updateBrand,updateModel,updateYear,updateColor,updatePrice,conformUpdateButton);
    updateHbox.setAlignment(Pos.CENTER);
    borderUpdate.setCenter(updateHbox);

    deleteHbox.getChildren().addAll(deleteBrand,deleteModel,deleteYear,deleteColor,deletePrice,conformDeleteButton);
    deleteHbox.setAlignment(Pos.CENTER);
    borderDelete.setCenter(deleteHbox);


    // add radiobuttons to toggle group
    yesRadioButton.setToggleGroup(tg);
    noRadioButton.setToggleGroup(tg);



    topGridPane.add(askLabel,0,0); topGridPane.add(yesRadioButton,1,0); topGridPane.add(noRadioButton,2,0);
    topGridPane.add(searchButton,5,2);





    MainButtonsEvent mainButtonsEvent = new MainButtonsEvent();
    yesRadioButton.setOnAction(mainButtonsEvent);
    noRadioButton.setOnAction(mainButtonsEvent);
    searchButton.setOnAction(mainButtonsEvent);
    insertButton.setOnAction(mainButtonsEvent);
    updateButton.setOnAction(mainButtonsEvent);
    deleteButton.setOnAction(mainButtonsEvent);
    conformInsertButton.setOnAction(mainButtonsEvent);
    conformUpdateButton.setOnAction(mainButtonsEvent);
    conformDeleteButton.setOnAction(mainButtonsEvent);

    borderCars.setTop(topGridPane);
    borderCars.setCenter(table);
    borderCars.setBottom(bottomHBOX);
    return borderCars;
}
    Scene sceneInsert = new Scene(borderInsert, 900,500);
    Stage stageInsert = new Stage();
    Scene sceneUpdate= new Scene(borderUpdate, 900,500);
    Stage stageUpdate = new Stage();
    Scene sceneDelete = new Scene(borderDelete, 900,500);
    Stage stageDelete = new Stage();


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
                             carBrandsComboBox.getItems().add( current.element);
                             current=current.next;
                         }
                            if(topGridPane.getChildren().contains(carBrandsComboBox)){
                                alertWarning.setTitle("Warning ");
                                alertWarning.setHeaderText(" choose a Brand then click conform");
                                alertWarning.show();

                            }else {
                                topGridPane.add(carBrandsComboBox,1,1);topGridPane.add(conformButton,2,1);
                                conformButton.setOnAction(e ->{
                                    String brandField = carBrandsComboBox.getValue().toString();
                                    if (!brandField.isEmpty()) {
                                        Object resultOfSearch = search_return_headerListOfDoubleNode(brandField);
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
                                 Node currentCar =  currentBrand.headerList;
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
                         if(( carBrandsComboBox.getValue())==null){
                              carBrandsComboBox.setValue("");
                         }
                         if(( carModelComboBox.getValue())==null){
                             carModelComboBox.setValue("");
                         }
                         if((carYearComboBox.getValue())==null){
                             carYearComboBox.setValue("");
                         }
                         if(( carColorComboBox.getValue())==null){
                             carColorComboBox.setValue("");
                         }
                         if((carPriceComboBox.getValue())==null){
                             carPriceComboBox.setValue("");
                         }
                         search((String) carBrandsComboBox.getValue(),(String) carModelComboBox.getValue(),(String) carYearComboBox.getValue(),(String) carColorComboBox.getValue(),(String) carPriceComboBox.getValue(),table);
                     }else if (event.getSource()== insertButton) {
                         stageInsert.setScene(sceneInsert);
                         stageInsert.show();
                     }else if (event.getSource()== conformInsertButton) {
                         Car n = new Car(insetBrand.getText(),insetModel.getText(),insetYear.getText(),insetColor.getText(),insetPrice.getText());
                         insert(n);
                     }else if (event.getSource()== updateButton) {
                         stageUpdate.setScene(sceneUpdate);
                         stageUpdate.show();
                     }else if (event.getSource()== conformUpdateButton) {
                        // Car n = new Car(updateBrand.getText(),updateModel.getText(),updateYear.getText(),updateColor.getText(),updatePrice.getText());
                         update(updateBrand.getText(),updateModel.getText(),updateYear.getText(),updateColor.getText(),updatePrice.getText());
                     }else if (event.getSource()== deleteButton) {
                         stageDelete.setScene(sceneDelete);
                         stageDelete.show();
                     }else if (event.getSource()== conformDeleteButton) {
                         Car n = new Car(deleteBrand.getText(),deleteModel.getText(),deleteYear.getText(),deleteColor.getText(),deletePrice.getText());
                         delete(n);
                     }


                 }catch (Exception e){
                     System.out.println(e.getMessage());
                 }
            }
    }





}
