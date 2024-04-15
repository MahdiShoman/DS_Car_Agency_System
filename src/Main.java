import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Main extends Application {
    Button conformButton = new Button("Conform");
    ComboBox<Object> usersComboBox = new ComboBox<>();

    static final DoubleLinkedList doubleLinkedList=new DoubleLinkedList();
    static final LinkedList linkedList =new LinkedList();
    static final Stack stack = new Stack();
    static final Queue queue = new Queue();
    public static void main(String[] args) {

       // read_File("C:\\Users\\ATIYA AMG\\Desktop\\Mahdi\\DS\\proj2\\cars.txt");


       // Car.search("KIA","","2022","","");

        String s = "  mahdi shoman  ";
        System.out.println(s);
        System.out.println(s.trim());
        launch(args);
    /*    Stack s = new Stack();

        s.push(4);
        s.push(5);
        s.printStack();
        System.out.println(s.pop());
        System.out.println(s.top());
      //  s.push("f");*/
       // s.push("a");
        //s.pop();
        //System.out.println(s.top());
       // s.printStack();

      //  read_orders("C:\\Users\\ATIYA AMG\\Desktop\\Mahdi\\DS\\proj2\\orders.txt");

    }


    Button readCarsBT = new Button("read");
    Button readOrdersBT = new Button("read");
    TextField fileChooserCarsField = new TextField();
    TextField fileChooserOrdersField = new TextField();

    Button cars = new Button("Cars");
    Button brands = new Button("Brands");
    Button orders = new Button("Orders");



    Stage adminStage = new Stage();
    Scene adminScene = new Scene(adminPage(),600,400);

    Stage customerStage = new Stage();
    Customer c = new Customer();
    Scene customerScene = new Scene(c.getCustomer(),700,500);

    Brand brand = new Brand();
    Scene sceneBrand = new Scene(brand.getBrands() , 800,600);
    Stage stageBrand = new Stage();

    Car car = new Car();
    Scene sceneCar = new Scene(car.getCars() , 800,600);
    Stage stageCar = new Stage();

    Orders order = new Orders();
    Scene sceneOrders = new Scene(order.getOrders(),900,600);
    Stage stageOrders =new Stage();
    String fileName ;
    static String fileOrderName ;


    static Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    static Alert alertWarning = new Alert(Alert.AlertType.WARNING);
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane mainBorderPane = new BorderPane();
        HBox hBoxTop = new HBox();
        hBoxTop.getChildren().addAll(usersComboBox,conformButton);

        VBox vBox = new VBox();

        usersComboBox.getItems().add("Admin");
        usersComboBox.getItems().add("Customer");
        Label label = new Label("choose your account :");
        vBox.getChildren().add(label);
        vBox.getChildren().add(hBoxTop);
        vBox.setAlignment(Pos.CENTER);
        hBoxTop.setAlignment(Pos.CENTER);
        mainBorderPane.setCenter(vBox);
        vBox.setSpacing(20);


        Scene scene = new Scene(mainBorderPane,500,400);
        stage.setScene(scene);
        stage.show();

        MainButtonsEvent mainButtonsEvent = new MainButtonsEvent();
        conformButton.setOnAction(mainButtonsEvent);
        readCarsBT.setOnAction(mainButtonsEvent);
        cars.setOnAction(mainButtonsEvent);
        orders.setOnAction(mainButtonsEvent);

    }



    BorderPane adminPage (){


        BorderPane borderPane = new BorderPane();
        HBox hBoxTop = new HBox();
        HBox hBoxTop2 = new HBox();

        // Label labelChooseFile = new Label();
        try {
            Button fileChooserButton = new Button("Choose File");
            Label readCarsLabel  = new Label("Choose File to Enter the Car's Data : ");
            hBoxTop.getChildren().addAll(readCarsLabel, fileChooserCarsField,fileChooserButton, readCarsBT);
            hBoxTop.setAlignment(Pos.CENTER);
            hBoxTop.setMinWidth(400);
            BorderPane topBorderPane = new BorderPane();


            Button fileChooserOrderButton = new Button("Choose File");
            Label readOrdersLabel  = new Label("Choose File to Enter the Order's Data :  ");
            hBoxTop2.getChildren().addAll(readOrdersLabel, fileChooserOrdersField,fileChooserOrderButton, readOrdersBT);
            hBoxTop2.setAlignment(Pos.CENTER);
            hBoxTop2.setMinWidth(400);

            topBorderPane.setTop(hBoxTop);
            topBorderPane.setCenter(hBoxTop2);

            borderPane.setTop(topBorderPane);

            fileChooserCarsField.setPromptText("Open cars File");

            HBox hBoxCenter = new HBox();
            hBoxCenter.getChildren().addAll(brands,cars,orders);
            hBoxCenter.setAlignment(Pos.CENTER);

            borderPane.setCenter(hBoxCenter);

         fileChooserButton.setOnAction(event -> {
             FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open cars File");
            File selected = fileChooser.showOpenDialog(adminStage);
        if (selected != null) {
            fileChooserCarsField.setText(selected.getAbsolutePath());
                fileName=selected.getAbsolutePath();
        }
    });
            fileChooserOrderButton.setOnAction(event -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open cars File");
                File selected = fileChooser.showOpenDialog(adminStage);
                if (selected != null) {
                    fileChooserOrdersField.setText(selected.getAbsolutePath());
                    fileOrderName=selected.getAbsolutePath();
                }
            });

         MainButtonsEvent mainButtonsEvent = new MainButtonsEvent();
         brands.setOnAction(mainButtonsEvent);
         cars.setOnAction(mainButtonsEvent);
         orders.setOnAction(mainButtonsEvent);
         readCarsBT.setOnAction(mainButtonsEvent);
         readOrdersBT.setOnAction(mainButtonsEvent);

        }catch (Exception e){
            e.getMessage();
        }


        return borderPane;
    }







    public static void read_File(String file) {
        int count = 0;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    // Use StringTokenizer to split the martyr by comma delimiter
                    StringTokenizer st = new StringTokenizer(line, ",");
                    String brandName = st.nextToken().trim();
                    String model = st.nextToken().trim();
                    String year = (st.nextToken()).trim();
                    String color = st.nextToken().trim();
                    String price = st.nextToken().trim();
                    count++;

                    Brand brand = new Brand(brandName);
                    // Create a new Statement object and add it to the linked list
                    Car car = new Car(brand,model,year,color,price);

                    linkedList.addLast(car); // to use it in the Statistic class\

                    if (doubleLinkedList.search(car.getBrandName())) {
                        doubleLinkedList.addList(car.getBrandName().trim(), car);
                        //martyrs.add(martyr,MainScreen.doubleLinkedList.first.headerList);
                    } else {
                        doubleLinkedList.addBrand(car.getBrandName());
                        // add2(locations.getCity());
                        //  sort(locations.getCity());
                        doubleLinkedList.addList(car.getBrandName(), car);
                        //martyrs.add(martyr,MainScreen.doubleLinkedList.first.headerList);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        doubleLinkedList.displaylistFull();

    }

    static void read_orders (String file){
        int count = 0;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    // Use StringTokenizer to split the martyr by comma delimiter
                    StringTokenizer st = new StringTokenizer(line, ",");

                    String customerName = st.nextToken();
                    String customerMobile = st.nextToken();
                    String brand = (st.nextToken());
                    String model = st.nextToken();
                    String year = st.nextToken();
                    String color = st.nextToken();
                    String price = (st.nextToken());
                    String orderDate = st.nextToken();
                    String orderStatus = st.nextToken();
                    count++;


                    // Create a new Statement object and add it to the linked list
                    Car car = new Car(brand,model,year,color,price);
                    Customer customer = new Customer(customerName,customerMobile);
                    Orders orders = new Orders(customer,car,orderDate,orderStatus);

                    if (orderStatus.trim().equals("Finished")){
                        stack.push(orders);
                    }else {
                        queue.enqueue(orders);
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            stack.printStack();
            System.out.println("dd");
            queue.printQueue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

static int x =0;
    public  class MainButtonsEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() ==  conformButton) {
                if (!(usersComboBox.getValue() == null)) {
                    if (usersComboBox.getValue().equals("Admin")) {
                        adminStage.setScene(adminScene);
                        adminStage.show();

                    } else {
                        if (x!=1) {
                            read_File("C:\\Users\\ATIYA AMG\\Desktop\\cars.txt");
                            x++;
                        }
                        customerStage.setScene(customerScene);
                        customerStage.show();
                    }
                }else{//C:\Users\ATIYA AMG\Desktop\cars.txt
                    alertWarning.setTitle("Warning ");
                    alertWarning.setHeaderText("chose your account  !!");
                    alertWarning.showAndWait();
                }
            }else if (event.getSource() == readCarsBT) {
                if(fileName!=null){

                    read_File(fileName);
                    alertInformation.setContentText("For information .,, we avoid the cars doesn't has full information ");
                    alertInformation.setHeaderText(" End Reading ");
                    alertInformation.showAndWait();
                }else {
                    alertWarning.setTitle("Warning ");
                    alertWarning.setHeaderText("the file is not found !!");
                    alertWarning.showAndWait();
                }
            }else if (event.getSource() == readOrdersBT) {
                if(fileOrderName!=null){

                    read_orders(fileOrderName);
                    alertInformation.setContentText("For information .,, we avoid the cars doesn't has full information ");
                    alertInformation.setHeaderText(" End Reading ");
                    alertInformation.showAndWait();
                }else {
                    alertWarning.setTitle("Warning ");
                    alertWarning.setHeaderText("the file is not found !!");
                    alertWarning.showAndWait();
                }
            }else if (event.getSource() == brands) {
                stageBrand.setScene(sceneBrand);
                stageBrand.show();

            } else if (event.getSource()==cars) {
                stageCar.setScene(sceneCar);
                stageCar.show();
            }else if (event.getSource()==orders) {

                stageOrders.setScene(sceneOrders);
                stageOrders.show();
            }
        }
    }// the handler button
}