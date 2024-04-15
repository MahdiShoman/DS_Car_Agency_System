import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Brand {
    private Object brandName ;
    Brand(){}

    Brand(String brand){
        this.brandName=brand.trim();
    }

    public String getBrandName() {
        return (String) brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName.trim();
    }

  public static   void addBrand(String brandName){
        boolean founded =false;
        DoubleNode current =  Main.doubleLinkedList.first;
        while (current!=null) {
            if ((current.element).equals(brandName.trim()) ){
                founded=true;
                break;
            }
            current = current.next;

        }
        if(founded){
            System.out.println("the " +brandName+" already exist");
        }else{
            Main.doubleLinkedList.addLast(brandName.trim()); /// if you did the sort < change the called method
        }

    }
    void updateBrand (String brandName , String newBrandName){

        DoubleNode current =  Main.doubleLinkedList.first;
        while (current!=null) {
            if (((String)current.element).equalsIgnoreCase(brandName.trim()) ){

                Node currentCar = current.headerList;
                while (currentCar!=null){
                    ((Car)currentCar.element).setBrandName(newBrandName.trim());
                    currentCar=currentCar.next;
                }
                current.element=newBrandName.trim();
                break;
            }
            current = current.next;

        }

    }
    void deleteBrand(String brandName){
        DoubleNode previous =  Main.doubleLinkedList.first;
        DoubleNode current =  Main.doubleLinkedList.first.next;
        while (current != null && !current.element.equals(brandName.trim())) {
            previous = current;
            current = current.next;
        }
        if (current != null) {
            previous.next = current.next;
            Main.doubleLinkedList. count--;
        }
    }



    boolean search(String brandName){
        DoubleNode current = Main.doubleLinkedList.first;
        while (current!=null){
            if(((String)current.element).equalsIgnoreCase(brandName.trim())){
                return true;
            }

            current=current.next;
        }
        return false;
    }

    public void search_delete(Object x ) {

        DoubleNode previous = Main.doubleLinkedList.first;
        DoubleNode current = Main.doubleLinkedList.first.next;
        if(((String)previous.element).equalsIgnoreCase(((String)x).trim())){
            previous=current;
            current=current.next;

            Main.doubleLinkedList. count--;

            Main.doubleLinkedList.first=previous;
            Main.doubleLinkedList.first.next=current;
            return;
        }
        while (current != null && !((String)current.element).equalsIgnoreCase(((String)x).trim())) {
            previous = current;
            current = current.next;
        }
        if (current != null) {
            previous.next = current.next;
            Main.doubleLinkedList. count--;
        }
    }

    BorderPane borderBrand =new BorderPane();

    private final TableView<String> table = new TableView<>();
    TextField searchBox = new TextField();
    Button searchButton = new Button("Search");
    static Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    static Alert alertWarning = new Alert(Alert.AlertType.WARNING);

    ComboBox<Object> searchBrandField = new ComboBox<>();
    GridPane bottomGridPane = new GridPane();
    Buttons button = new Buttons();
    public BorderPane getBrands() {
        // add the field search and the button in the Top of border pane
        HBox topHbox = new HBox();

       // TextField searchBrandField = new TextField();


        //??
        searchBrandField.setPromptText("search of brand");


        topHbox.getChildren().addAll(searchBrandField, searchButton);

        topHbox.setAlignment(Pos.CENTER);

        borderBrand.setTop(topHbox);

        //add Brands of Cars from double linkedlist in the center of border pane


        // Set up the table columns
        TableColumn<String, String> brandName = new TableColumn<>("Brand Name");
        // catch column with obj
        brandName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));




        // Add the columns to the table
        table.getColumns().add(brandName);
        setData(table);

        // Set up the search box and button

        searchBox.setPromptText("Enter search term");
        searchButton.setOnAction(event -> {
            if(searchBrandField.getValue()==null){
                table.getItems().clear();
                setData(table);
            }else {
                if(search((String) searchBrandField.getValue())) {
                    table.getItems().clear();
                    System.out.println("before");
                    setAddedData(table, (String) searchBrandField.getValue());
                    System.out.println("after");
                }else {
                    alertWarning.setTitle("Warning ");
                    alertWarning.setHeaderText(" city does not exist ");
                    alertWarning.show();

                }
            }
        });
        borderBrand.setCenter(table);

        //add options in the bottom
        MainButtonsEvent mainButtonsEvent = new MainButtonsEvent();



        button.labelInsert.setText("* If you want to insert a location, click on ");
        bottomGridPane.add( button.labelInsert ,0,2);bottomGridPane.add(button.insertButton,1,2);
        button.insertButton.setOnAction(mainButtonsEvent);

        button.labelUpdate.setText(" * If you want to update the locations of the martyrs, click on  ");
        bottomGridPane.add(button.labelUpdate ,0,4);bottomGridPane.add(button.updateButton,1,4);
        button.updateButton.setOnAction(mainButtonsEvent);

        button.labelDelete.setText(" * If you want to delete a location, click on  ");
        bottomGridPane.add( button.labelDelete ,0,6);bottomGridPane.add(button.deleteButton,1,6);
        button.deleteButton.setOnAction(mainButtonsEvent);



        borderBrand.setBottom(bottomGridPane);

        return borderBrand;
    }
    private void setData(TableView<String> table ){
        DoubleNode current = Main.doubleLinkedList.first;
        // System.out.println(current);
        while(current!=null){
            table.getItems().add((String) current.element);
             searchBrandField.getItems().add( current.element);
            current=current.next;
        }
    }
    private void setAddedData(TableView<String> table , String s ){
        // s.toLowerCase();
        DoubleNode current = Main.doubleLinkedList.first;
        System.out.println(current);
        while(current!=null ){
            //  System.out.println(current.element+s);
            ///((String)current.element).toLowerCase();
            if ((((String)current.element)).equalsIgnoreCase(s)){
                table.getItems().add((String) current.element);
            }
            current=current.next;
        }
    }


    public  class MainButtonsEvent implements EventHandler<ActionEvent> { // the handler button
        @Override
        public void handle(ActionEvent event) {try {


            if (event.getSource() == button.insertButton) {
                // add method , check empty
                Button conform = new Button("conform");
                if (!bottomGridPane.getChildren().contains(button.insertField)) {
                    bottomGridPane.add(button.insertField, 0, 3);
                    bottomGridPane.add(conform, 1, 3);
                    bottomGridPane.add(button.labelInsertResult, 2, 3);
                    conform.setOnAction(e -> {
                        // Locations l=new Locations(button.insertField.getText());// to add  a location to list
                        if (button.insertField.getText().trim().isEmpty()) {
                            button.labelInsertResult.setText("the field is Empty");
                        } else if (!Main.doubleLinkedList.search(button.insertField.getText().trim())) {
                            Main.doubleLinkedList.addLast(button.insertField.getText().trim());
                            button.labelInsertResult.setText("Done !");
                            button.alertWarning.setTitle("Warning ");
                            button.alertWarning.setHeaderText("Dont forget save your work before exit ");
                            button.alertWarning.show();
                        } else {
                            button.labelInsertResult.setText("the location is already  exist !!");
                        }
                    });

                } else {
                    button.labelInsertResult.setText("enter city to insert it !");
                }
                Main.doubleLinkedList.displayList();
                System.out.println("_______");


            } else if (event.getSource() == button.updateButton) {  //

                Button conformButton = new Button("conform");
                // manageEvent.setText("add city to update it , then click Conform");
                button.beforeUpdateField.setPromptText("Old Name");
                button.afterUpdateField.setPromptText("New Name");
                bottomGridPane.add(button.beforeUpdateField, 0, 5);
                bottomGridPane.add(button.afterUpdateField, 1, 5);
                bottomGridPane.add(conformButton, 2, 5);
                bottomGridPane.add(button.labelUpdateResult, 3, 5);
                // MainScreen.doubleLinkedList. displaylist();
                conformButton.setOnAction(e -> {
                    if (button.beforeUpdateField.getText().trim().isEmpty() || button.afterUpdateField.getText().trim().isEmpty()) {
                        button.labelUpdateResult.setText("the fields are Empty");
                    } else {
                        if (search(button.beforeUpdateField.getText().trim())) {
                            updateBrand(button.beforeUpdateField.getText().trim(), button.afterUpdateField.getText().trim());
                        } else {
                            button.labelUpdateResult.setText("the location doesn't  exist !!");
                        }
                    }
                    Main.doubleLinkedList.displayList();
                });
                //methode update  , check the empty , to lower case , trim


            } else if (event.getSource() == button.deleteButton) {

                Button conformButton = new Button("conform");
                //manageEvent.setText("add city to delete it , then click Conform");
                bottomGridPane.add(button.deleteField, 0, 7);
                bottomGridPane.add(conformButton, 1, 7);
                //bottomGridPane.add(manageEvent,2, 7);

                conformButton.setOnAction(e -> {
                    search_delete(button.deleteField.getText().trim());
                    Main.doubleLinkedList.displayList();
                });
                //search_delete , check the empty  to lower case,trim

            }
            }catch (Exception e){
            System.out.println(e.getMessage());
            }
        }

    }

}
