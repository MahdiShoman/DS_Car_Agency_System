import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Orders {
    private Customer customer;
    private Car car;
    private  String  OrderDate;
    private  String OrderStatus = "InProcess";

    Orders(){}
    public Orders(Customer customer, Car car, String orderDate, String orderStatus) {
        this.customer = customer;
        this.car = car;
        OrderDate = orderDate.trim();
        OrderStatus = orderStatus.trim();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate.trim();
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus.trim();
    }

    @Override
    public String toString() {
        return "Orders{" +
                "customer=" + customer.getCustomerName() + ", customer mobile= " + customer.getCustomerNumber() +
                ", car=" + car +
                ", OrderDate='" + OrderDate + '\'' +
                ", OrderStatus='" + OrderStatus + '\'' +
                '}';
    }
    static Alert alertWarning = new Alert(Alert.AlertType.WARNING);
    private final TableView<Object> table = new TableView<>();
    VBox queueVBox = new VBox();
    Object currentCar;
    void queueDisplay (){
        Object s = Main.queue.objectOut();
        if (!Main.queue.isEmpty() && !s.equals("null")){
            queueVBox.getChildren().clear();
            queueVBox.getChildren().add(new Label( s+""));
        }else {
            queueVBox.getChildren().clear();
            queueVBox.getChildren().add(new Label( "Orders{customer=null, customer mobile= null, car=Car{brandName=null, Model='null', year='null', color='null', price='null'}, OrderDate='null', OrderStatus='Nos Status'}"));
            alertWarning.setTitle("Warning ");
            alertWarning.setHeaderText("there is no Orders for yet");
            alertWarning.show();
        }
        currentCar=s;
    }

    void search (String orderName){
        int size = Main.queue.size;
        if ((orderName.trim().equals(""))){

            while (!Main.queue.isEmpty() && size!=0){
                Object s = Main.queue.dequeue();
                table.getItems().add(s);
                orderNameComboBox.getItems().add(((Orders)s).customer.getCustomerName());
                size--;
                Main.queue.enqueue(s);
            }
        }else {
            while (!Main.queue.isEmpty()&& size!=0){
                Object s = Main.queue.dequeue();
                if (orderName.equalsIgnoreCase(((Customer)s).getCustomerName())) {
                    table.getItems().add(s);
                }

                size--;
                Main.queue.enqueue(s);
            }
        }
    }

    VBox stackVBox = new VBox();
    void stackDisplay (){
        stackVBox.getChildren().clear();
        Stack tmp = new Stack();
        for (int i = 1; i < 11; i++) {
            if(Main.stack.isEmpty())
                break;
          //  System.out.println("not empty");
            Orders o = (Orders) Main.stack.pop();
            if(o.getOrderStatus().equalsIgnoreCase("Finished")){
                tmp.push(o);
            }
            stackVBox.setSpacing(12);
            stackVBox.getChildren().add(new Label( o+""));
        }
        for (int i = 1; i < 11; i++) {
            if(tmp.isEmpty())
                break;
            Main.stack.push(tmp.pop());
        }

    }



    void insert(String customerName, String customerMobile,Car car,String orderDate){
        Node firstQueue =Main.queue.l.first;
        while (((Orders)firstQueue.element).customer.getCustomerName().equals(customerName.trim())){
            if (!((Orders)firstQueue.element).car.toString().equals(car.toString())){
                if(!((Orders)firstQueue.element).OrderDate.trim().equals(orderDate.trim())){
                    Customer customer1 = new Customer(customerName,customerMobile);
                    Orders orders = new Orders(customer1,car,orderDate,"InProcess");
                    Main.queue.enqueue(orders);
                }
            }else {
                System.out.println("we have same order");
            }
            firstQueue=firstQueue.next;
        }

    }
    void update(int orderNum,String customerName, String customerMobile,Car car,String orderDate,String orderStatus){
        Node firstQueue =Main.queue.l.first;
        int counter = 0;
        while (firstQueue.next!=null ){
            if(counter==orderNum){
                if((!(customerName.trim().isEmpty()))){
                    ((Orders)firstQueue.element).customer.setCustomerName(customerName);
                }
                if((!(customerMobile.trim().isEmpty()))){
                    ((Orders)firstQueue.element).customer.setCustomerNumber(customerMobile);
                }
                if(car!=null){ // before call the method i update the car Obj
                    ((Orders)firstQueue.element).car.setBrandName(car.getBrandName());
                    ((Orders)firstQueue.element).car.setModel(car.getModel());
                    ((Orders)firstQueue.element).car.setYear(car.getYear());
                    ((Orders)firstQueue.element).car.setColor(car.getColor());
                    ((Orders)firstQueue.element).car.setPrice(car.getPrice());
                }
                if(!(orderDate.trim().isEmpty())){
                    ((Orders)firstQueue.element).setOrderDate(orderDate);
                }
                if (!(orderStatus.trim().isEmpty())){
                    ((Orders)firstQueue.element).setOrderStatus(orderStatus);
                }
            }
            counter++;
            firstQueue=firstQueue.next;
        }
    }
    void delete(int orderNum){
    /*    Node firstQueue =Main.queue.l.first;
        int counter = 0;
        while (firstQueue.next!=null ){
            if(counter==orderNum){
               // Main.queue.dequeue();
            }
            counter++;
            firstQueue=firstQueue.next;

        }*/
        Main.queue.l.remove(orderNum);
    }
    Button conformButton =  new Button("Conform");
    Button acceptButton =  new Button("Accept");

    Button deleteButton =  new Button("Delete");
    Button doItLaterButton =  new Button("deferment it");

    Button saveButton =  new Button("Save");
    ComboBox<Object> orderNameComboBox = new ComboBox<>();
    ComboBox<Object> orderNumberComboBox = new ComboBox<>();



    BorderPane ordersPane = new BorderPane();
    BorderPane centerBorderPane = new BorderPane();
    BorderPane getOrders (){
        HBox topHBox = new HBox();
        Label hint = new Label("hit load to load the Data : ");
        topHBox.getChildren().addAll(hint,conformButton);
        topHBox.setAlignment(Pos.CENTER);
        VBox topVBox = new VBox();
        //topVBox.getChildren().addAll(topHBox);
        ordersPane.setTop(topHBox);


        Label centerLabel = new Label("Last Finished Orders");
        centerLabel.setStyle("-fx-text-fill:red; ");
        centerBorderPane.setTop(centerLabel);

/*

        TableColumn<Object, String> customerName = new TableColumn<>("Customer Name");
        // catch column with obj
        customerName.setCellValueFactory(data -> new SimpleStringProperty(((Orders) data.getValue()).customer.getCustomerName()));
        TableColumn<Object, String> customerNumber = new TableColumn<>("Customer Name");
        // catch column with obj
        customerNumber.setCellValueFactory(data -> new SimpleStringProperty(((Orders) data.getValue()).customer.getCustomerNumber()));

        TableColumn<Object, String> brandName = new TableColumn<>("Brand Name");
        // catch column with obj
        brandName.setCellValueFactory(data -> new SimpleStringProperty(((Orders) data.getValue()).car.getBrandName()));


        TableColumn<Object, String> carModel = new TableColumn<>("Car Model");
        // catch column with obj
        carModel.setCellValueFactory(data -> new SimpleStringProperty(((Orders) data.getValue()).car.getModel()));


        TableColumn<Object, String> carYear = new TableColumn<>("Car Year");
        // catch column with obj
        carYear.setCellValueFactory(data -> new SimpleStringProperty(((Orders) data.getValue()).car.getYear()));




        TableColumn<Object , String> carColor = new TableColumn<>("Car color");
        // catch column with obj
        carColor.setCellValueFactory(data -> new SimpleStringProperty(((Orders) data.getValue()).car.getColor()));


        TableColumn<Object , String> carPrice = new TableColumn<>("Car Price");
        // catch column with obj
        carPrice.setCellValueFactory(data -> new SimpleStringProperty(((Orders) data.getValue()).car.getPrice()));

        TableColumn<Object , String> orderDate  = new TableColumn<>(" Order Date ");
        // catch column with obj
        orderDate.setCellValueFactory(data -> new SimpleStringProperty(((Orders)data.getValue()).getOrderDate()));


        TableColumn<Object , String> orderStatus = new TableColumn<>("Order Status");
        // catch column with obj
        orderStatus.setCellValueFactory(data -> new SimpleStringProperty(((Orders)data.getValue()).getOrderStatus()));

        // Add the columns to the table
        //martyrNameColumn,carYear,martyrDeathDateColumn,martyrGenderColumn
        table.getColumns().addAll(customerName,customerNumber,brandName,carYear,carColor,carPrice,carModel,orderDate,orderStatus);

*/










        MainButtonsEvent mainButtonsEvent = new MainButtonsEvent();
        conformButton.setOnAction(mainButtonsEvent);
        acceptButton.setOnAction(mainButtonsEvent);
        deleteButton.setOnAction(mainButtonsEvent);
        doItLaterButton.setOnAction(mainButtonsEvent);
        saveButton.setOnAction(mainButtonsEvent);

        return ordersPane;
    }

    private void setData(TableView<Object> table ){
        DoubleNode current = Main.doubleLinkedList.first;
        while (current!= null) { // O(n^2)
            Node currentCheck = current.headerList;
            while (currentCheck!=null){
                table.getItems().add((Car) currentCheck.element);
                currentCheck=currentCheck.next;
            }
            current = current.next;
        }
    }

    public  boolean search(Car x ) {

        DoubleNode current =(Main.doubleLinkedList.first);
        while (current!=null) {
            if (((Car)current.headerList.element).getBrandName().equalsIgnoreCase(x.getBrandName()) ) {
                Node curent = current.headerList;
                while (curent!=null) {
                    int s = compare(((Car) curent.element), x);
                    if (s == 5) {
                        return true;
                    }
                    curent=curent.next;
                }
                return false;
            }
            current = current.next;

        }
        return false;
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
    Save save = new Save();


Scene scene = new Scene(  save.save(),400,400);
    Stage stage = new Stage();
    public  class MainButtonsEvent implements EventHandler<ActionEvent> { // the handler button
        @Override
        public void handle(ActionEvent event) {
            try {
                if (event.getSource() == conformButton) {
                    stackDisplay();
                    centerBorderPane.setCenter(stackVBox);
                    ordersPane.setCenter(centerBorderPane);
                    queueDisplay();
                    HBox h = new HBox(acceptButton,deleteButton,doItLaterButton,saveButton);
                    h.setAlignment(Pos.CENTER);
                    queueVBox.getChildren().add(h);
                    ordersPane.setBottom(queueVBox);
                    queueVBox.setSpacing(10);
                  /*  if(((String) orderNameComboBox.getValue())==null){
                        orderNameComboBox.setValue("");
                    }
                    search((String) orderNameComboBox.getValue());*/
                }else if (event.getSource() == acceptButton) {

                    if (((Orders) currentCar).car==null) {
                        alertWarning.setTitle("Warning ");
                        alertWarning.setHeaderText("there is no cars in order");
                        alertWarning.show();

                    } else if (!search(((Orders) currentCar).car)){
                        alertWarning.setTitle("Warning ");
                        alertWarning.setHeaderText("car does not exist");
                        alertWarning.show();
                    }
                    else {

                        ((Orders) currentCar).setOrderStatus("Finished");
                        Main.stack.push(currentCar);
                        Car.delete(((Orders) currentCar).car);
                        System.out.println("Accepted");

                        Main.queue.dequeue();
                        stackDisplay();
                        queueDisplay();
                        ordersPane.setBottom(queueVBox);
                        if (!queueVBox.getChildren().contains(acceptButton)) {
                            HBox h = new HBox(acceptButton, deleteButton, doItLaterButton,saveButton);
                            h.setAlignment(Pos.CENTER);
                            queueVBox.getChildren().add(h);
                        }
                        System.out.println();
                        Main.queue.printQueue();

                    }
                }else if (event.getSource() == deleteButton) {
                    Main.queue.dequeue();
                    stackDisplay();
                    queueDisplay();

                    if (!queueVBox.getChildren().contains(acceptButton) ) {
                        HBox h = new HBox(acceptButton,deleteButton,doItLaterButton,saveButton);
                        h.setAlignment(Pos.CENTER);
                        queueVBox.getChildren().add(h);
                    }
                    System.out.println();
                    Main.queue.printQueue();
                }else if (event.getSource() == doItLaterButton) {
                    if (((Orders) currentCar).car == null) {
                        alertWarning.setTitle("Warning ");
                        alertWarning.setHeaderText("there is no cars in order");
                        alertWarning.show();

                    } else {


                            Main.queue.enqueue(currentCar);
                            Main.queue.dequeue();
                            queueDisplay();
                            stackDisplay();

                        if (!queueVBox.getChildren().contains(acceptButton)) {
                            HBox h = new HBox(acceptButton, deleteButton, doItLaterButton,saveButton);
                            h.setAlignment(Pos.CENTER);
                            queueVBox.getChildren().add(h);
                        }
                        System.out.println();
                        Main.queue.printQueue();
                    }
                }else if (event.getSource() == saveButton) {
                  stage.setScene(scene);
                  stage.show();
                }

            }catch (Exception e){
                e.getMessage();
            }
        }
    }

}
