package com.example.statistiques;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.CheckBox;

import java.io.File;
import java.io.IOException;

import static jdk.jfr.consumer.EventStream.openFile;

// faire en sorte que l'anova ne prenne pas de nuls
public class EntrerEchantillon extends Application{
    Echantillon[] tabEch = new Echantillon[10];
    int compteur = 0;
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Coucou");

        GridPane gp =new GridPane();
        gp.setHgap(30);
        gp.setVgap(30);
        for(int i = 0; i<3; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(33);
            gp.getColumnConstraints().addAll(cc);
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(33);
            gp.getRowConstraints().addAll(rc);
        }
        gp.setMinSize(700,700);
        Scene scene = new Scene(gp);
        primaryStage.setScene(scene);
        TextField entree = new TextField();
        TextField ecran = new TextField();
        ecran.setMinSize(300, 50);
        GridPane grid = new GridPane();
        Label t = new Label("Syntaxe : 'nombre'+';'");
        Label t1 = new Label("Partie theorique");
        Label t2 = new Label("Echantillons ");
        Label t3 = new Label("Un seul echantillon :");
        Label t3b = new Label("Plusieurs echantillons :");
        Label t4 = new Label("Graphique");
        grid.setHgap(10);
        grid.setVgap(10);
        Button btn = new Button("Entrer");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(btn);
        Button btnModif = new Button("Modifier un échantillon");
        HBox hbBtnModif = new HBox(10);
        hbBtnModif.setAlignment(Pos.CENTER);
        hbBtnModif.getChildren().add(btnModif);
        grid.add(t, 0,0);
        grid.add(entree,0,1);
        grid.add(hbBtn, 0, 2);
        grid.add(ecran, 0, 4);
        grid.add(hbBtnModif, 0, 5);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importer un fichier");

        Button btnfile = new Button("Entrer un fichier");
        HBox hbBtnfile = new HBox(10);
        hbBtnfile.setAlignment(Pos.CENTER);
        hbBtnfile.getChildren().add(btnfile);
        grid.add(hbBtnfile, 0,3);

        GridPane grid1 = new GridPane();
        grid1.setHgap(10);
        grid1.setVgap(10);
        Button btn1 = new Button("Lien");
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn1.getChildren().add(btn1);
        grid1.add(hbBtn1, 1, 0);
        grid1.add(t1, 0,0);

        GridPane grid2 = new GridPane();
        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.add(t2,0,0);
        CheckBox cb = new CheckBox("E1");
        grid2.add(cb, 0,1);
        CheckBox cb1 = new CheckBox("E2");
        grid2.add(cb1, 0,2);

        CheckBox cb2 = new CheckBox("E3");
        grid2.add(cb2, 0,3);
        CheckBox cb3 = new CheckBox("E4");
        grid2.add(cb3, 0,4);
        CheckBox cb4 = new CheckBox("E5");
        grid2.add(cb4, 0,5);
        CheckBox cb5 = new CheckBox("E6");
        grid2.add(cb5, 0,6);






        GridPane grid3 = new GridPane();
        grid3.setHgap(10);
        grid3.setVgap(10);
        Button btn3 = new Button("Moyenne");
        HBox hbBtn3 = new HBox(10);
        hbBtn3.getChildren().add(btn3);
        Button btn2 = new Button("Anova");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.getChildren().add(btn2);
        Button btnRL = new Button("Régression linéaire");
        HBox hbBtnRL = new HBox(10);
        hbBtn2.getChildren().add(btnRL);
        grid3.add(t3,0,0);
        grid3.add(hbBtn3, 0, 1);
        grid3.add(t3b,0,2);
        grid3.add(hbBtn2, 0, 3);
        grid3.add(hbBtnRL, 0, 4);

        GridPane grid4 = new GridPane();
        grid4.setHgap(10);
        grid4.setVgap(10);
        Button btn4 = new Button("Graphique");
        HBox hbBtn4 = new HBox(10);
        hbBtn4.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn4.getChildren().add(btn4);
        grid4.add(hbBtn4, 1, 0);
        grid4.add(t4,0,0);

        grid1.setAlignment(Pos.TOP_CENTER);
        grid.setAlignment(Pos.CENTER);
        grid2.setAlignment(Pos.CENTER);
        grid3.setAlignment(Pos.CENTER);
        grid4.setAlignment(Pos.BOTTOM_CENTER);

        gp.add(grid1,1,0);
        gp.addRow(1,grid2,grid, grid3);
        gp.add(grid4,1,2);
        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 6);

        final Text actiontarget2 = new Text();
        grid3.add(actiontarget2, 0, 1);


        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                try {
                    tabEch[compteur] = new Echantillon(entree.getText());
                    compteur++;
                    actiontarget.setFill(Color.BLUE);
                    actiontarget.setText("echantillon " + compteur + " créé");
                }
                catch(ExceptionDonneesEntree de){
                    actiontarget.setFill(Color.RED);
                    actiontarget.setText(new String("L'échantillon " + (compteur +1) + " n'a pas pu être créé :\n"+ de.getMessage()));
                }
            }
        });
        btnfile.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle ( final ActionEvent e){
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    try {
                        openFile(file.toPath());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        btnModif.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                int c = 0;
                if(cb1.isSelected()) c=1;
                if(cb2.isSelected()) c=2;
                if(cb3.isSelected()) c=3;
                if(cb3.isSelected()) c=4;
                if(cb3.isSelected()) c=5;
                if(cb3.isSelected()) c=6;
                try {
                    tabEch[c] = new Echantillon(entree.getText());
                    ecran.setText(tabEch[c].toString());
                    actiontarget.setFill(Color.BLUE);
                    actiontarget.setText("L'échantillon a bien été modifié");
                }catch(ExceptionDonneesEntree de){
                    actiontarget.setFill(Color.RED);
                    actiontarget.setText("L'échantillon " + compteur+1 + " n'a pas pu être créé :"+ de.getMessage()+"\n Ou bien la synthaxe n'est pas respéctée");
                }
            }
        });
        btn3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                int c = 0;
                if(cb1.isSelected()) c=1;
                if(cb2.isSelected()) c=2;
                if(cb3.isSelected()) c=3;
                if(cb3.isSelected()) c=4;
                if(cb3.isSelected()) c=5;
                if(cb3.isSelected()) c=6;


                ecran.setText(String.valueOf(tabEch[c].getMoyenne()));

            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent e) {
                ecran.setText("ANOVA:");
                int c =0;
                if(cb.isSelected()) c++;
                if(cb1.isSelected()) c++;
                if(cb2.isSelected()) c++;
                if(cb3.isSelected()) c++;
                if(cb4.isSelected()) c++;
                if(cb5.isSelected()) c++;
                Echantillon[] echAN = new Echantillon[c];
                    c = 0;

                    if (cb.isSelected() ) {
                        echAN[c] = tabEch[c];
                        c++;
                    }
                    if (cb1.isSelected() ) {
                        echAN[c] = tabEch[c];
                        c++;
                    }
                    if (cb2.isSelected() ) {
                        echAN[c] = tabEch[c];
                        c++;
                    }
                    if (cb3.isSelected() ) {
                        echAN[c] = tabEch[c];
                        c++;
                    }
                    if (cb4.isSelected() ) {
                        echAN[c] = tabEch[c];
                        c++;
                    }
                    if (cb5.isSelected() ) {
                        echAN[c] = tabEch[c];
                        c++;
                    }
                    try {
                        Anova an = new Anova(echAN);
                        ecran.setText(an.decision());
                    } catch (ExceptionNombreEchantillons | ExceptionTailleEchantillon en){
                        ecran.setText(en.getMessage());

                    }

            }

        });
        btnRL.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent e) {
                ecran.setText("Régression linéaire:");
                int c = 0;
                Echantillon[] echRL = new Echantillon[2];
                if (cb.isSelected() ) {
                    echRL[c] = tabEch[c];
                    c++;
                }
                if (cb1.isSelected() ) {
                    echRL[c] = tabEch[c];
                    c++;
                }
                if (cb2.isSelected() ) {
                    if (c == 2) c++;
                    else {
                        echRL[c] = tabEch[c];
                        c++;
                    }
                }
                if (cb3.isSelected() ) {
                    if (c == 2) c++;
                    else {
                        echRL[c] = tabEch[c];
                        c++;
                    }
                }
                if (cb4.isSelected() ) {
                    if (c == 2) c++;
                    else {
                        echRL[c] = tabEch[c];
                        c++;
                    }
                }
                if (cb5.isSelected() ) {
                    if (c == 2) c++;
                    else {
                        echRL[c] = tabEch[c];
                        c++;
                    }
                }

                try {
                    if(c>2) ecran.setText("Séléctionner 2 échantillons");
                    else {
                        RegressionLineaire RL = new RegressionLineaire(echRL[0], echRL[1]);
                        ecran.setText(RL.decision());
                    }
                }
                catch (ExceptionTailleEchantillon et){
                    ecran.setText(et.getMessage());
                }
            }
        });
        //primaryStage.sizeToScene();
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
     
