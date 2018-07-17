package com.im.net.client.view;

import com.im.net.client.util.ClientUtil;
import com.im.net.server.pojo.User;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatView {

    private User user;

    public ChatView(User user) {
        this.user = user;
    }

    public void show(){
        Stage loginstage = new Stage();
        Image image02 = new Image("com/im/net/client/img/image_02.png");
        ImageView view02 = new ImageView(image02);
        view02.setFitHeight(50);
        view02.setFitWidth(50);
        MenuBar mb = new MenuBar();
        Menu menu = new Menu("菜单");
        MenuItem exit = new MenuItem("退出");
        MenuItem add = new MenuItem("添加");
        MenuItem change = new MenuItem("切换账号");
        menu.getItems().addAll(add, change, exit);
        mb.getMenus().addAll(menu);

        BorderPane wholepane = new BorderPane();

        AnchorPane left = new AnchorPane();
        left.getChildren().addAll(view02,mb);
        AnchorPane.setTopAnchor(view02,0.0);
        AnchorPane.setBottomAnchor(mb,0.0);
        wholepane.setLeft(left);

        ListView fiendslist = new ListView();
        ClientUtil.list = fiendslist;
        HBox listpane = new HBox();
        listpane.getChildren().add(fiendslist);
        wholepane.setCenter(listpane);

        ScrollPane sp = new ScrollPane();
        AnchorPane talk = new AnchorPane();
        talk.setPadding(new Insets(20));
        talk.setStyle("-fx-background-color: white");
        sp.setContent(talk);
        sp.setMaxHeight(400);
        sp.setMinHeight(400);
        sp.setFitToHeight(true);
        sp.setFitToWidth(true);
        sp.getStyleClass().add("edge-to-edge");
        sp.setHvalue(0);
        Image im = new Image("com/im/net/client/img/image_03.png");
        ImageView fileim = new ImageView(im);
        fileim.setFitHeight(30);
        fileim.setFitWidth(30);
        HBox hb = new HBox();
        hb.getChildren().addAll(fileim);
        TextArea send = new TextArea();
        Button sendbtn = new Button("发送");
        VBox vb = new VBox();
        vb.getChildren().addAll(sp, hb, send, sendbtn);
        wholepane.setRight(vb);

        Scene sc = new Scene(wholepane, 1000, 700);
        sc.getStylesheets().add(ChatView.class.getResource("chat.css").toExternalForm());
        loginstage.setScene(sc);
        loginstage.show();
    }
}
