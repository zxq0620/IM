package com.im.net.client.view;

import com.im.net.client.Client;
import com.im.net.client.util.ClientUtil;
import com.im.net.server.protobuf.MessageOuterClass;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginView extends Application {

    public static void main(String[] args) {
        //new Thread(new Client("127.0.0.1",12345)).start();
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        Image image01 = new Image("com/im/net/client/img/image_01.jpg");
        ImageView view01 = new ImageView(image01);
        view01.setFitHeight(200);
        view01.setFitWidth(200);

        Label usernamela = new Label("用户名");
        final TextField usernamete = new TextField();

        Label passwordla = new Label("密码");
        final PasswordField password = new PasswordField();

        Button regisbtn = new Button("注册");
        Button loginbtn = new Button("登陆");

        final Label tips = new Label();
        ClientUtil.primarystage = primaryStage;
        ClientUtil.tips = tips;
        loginbtn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                if (!usernamete.getText().equals("")&&!password.getText().equals("")) {
                    ClientUtil.login(Integer.parseInt(usernamete.getText()), password.getText());
                }else {
                    tips.setText("用户名或密码不能为空");
                }
            }
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(12);
        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.getChildren().addAll(loginbtn, regisbtn);
        grid.add(view01, 1, 0);
        grid.add(usernamela, 0, 1);
        grid.add(usernamete, 1, 1);
        grid.add(passwordla, 0, 2);
        grid.add(password, 1, 2);
        grid.add(hbButtons, 0, 3, 3, 1);
        grid.setAlignment(Pos.CENTER);
        VBox hb = new VBox();
        hb.getChildren().addAll(grid, tips);
        hb.setAlignment(Pos.CENTER);
        Scene scene = new Scene(hb, 500, 400);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setTitle("即时通讯程序");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                System.exit(1);
            }
        });
        primaryStage.show();
    }
}
