import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {

    Save(){

    }
    Stage stage = new Stage();
    TextField fileChooserField = new TextField();
    Button fileChooserButton = new Button("Choose File");
    Button saveButton = new Button("Save");
    String fileName;

    static Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    static Alert alertWarning = new Alert(Alert.AlertType.WARNING);

    BorderPane p = new BorderPane();
    BorderPane save(){
        HBox h = new HBox();
        fileChooserField.setPromptText("Set Resource File /  Choose File");
        h.getChildren().addAll(fileChooserField,fileChooserButton,saveButton);
        h.setAlignment(Pos.CENTER);
        h.setMinHeight(450);
        p.setTop(h);
        //p.setMinWidth(200);



        fileChooserButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File selected = fileChooser.showOpenDialog(stage);
            if (selected != null) {
                fileChooserField.setText(selected.getAbsolutePath());
            //    fileName=selected.getAbsolutePath();

            }
        });

        saveButton.setOnAction(actionEvent -> {
            try {
                FileWriter f = new FileWriter(Main.fileOrderName);

                if (!Main.queue.isEmpty()&& !Main.stack.isEmpty()) { // O(n)
                    int sSize =Main.queue.size;
                    while (sSize!=0){//  f.write
                        Object s= Main.queue. dequeue();
                       f.write ( "\n"+((Orders)s).getCustomer().getCustomerName()+","+((Orders)s).getCustomer().getCustomerNumber()+","+((Orders)s).getCar().getBrandName()
                                +","+((Orders)s).getCar().getModel()+","+((Orders)s).getCar().getColor()+","+((Orders)s).getCar().getYear()+","+((Orders)s).getCar().getPrice()
                                +","+((Orders)s).getOrderDate()+","+((Orders)s).getOrderStatus());
                        Main.queue. enqueue(s);
                        sSize--;
                    }
                    int ssSize =Main.stack.size;
                    Stack temp = new Stack();
                    while (ssSize!=0){
                        Object s= Main.stack.pop();
                        temp.push(s);
                        f.write( "\n"+((Orders)s).getCustomer().getCustomerName()+","+((Orders)s).getCustomer().getCustomerNumber()+","+((Orders)s).getCar().getBrandName()
                                +","+((Orders)s).getCar().getModel()+","+((Orders)s).getCar().getColor()+","+((Orders)s).getCar().getYear()+","+((Orders)s).getCar().getPrice()
                                +","+((Orders)s).getOrderDate()+","+((Orders)s).getOrderStatus());
                        ssSize--;
                    }
                    ssSize=Main.stack.size;
                    while (ssSize!=0){
                        Main.stack.push(temp.pop());
                        ssSize--;
                    }

                }
                alertInformation.setContentText("Save is Done");
                alertInformation.showAndWait();

            } catch (IOException e) {
                System.out.println( e.getMessage());
            }});

        return p;
    }
}
