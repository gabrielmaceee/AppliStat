package com.example.statistiques;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import static jdk.jfr.consumer.EventStream.openFile;

// faire en sorte que l'anova ne prenne pas de nuls
public class EntrerEchantillon extends Application{
    Echantillon[] tabEch = new Echantillon[12];
    int compteur = 0;
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Coucou");

        GridPane gp =new GridPane();
        gp.setHgap(0);
        gp.setVgap(0);
        //gp.setMinSize(500,500);
        for(int i = 0; i<3; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(33);
            gp.getColumnConstraints().add(i,cc);
        }
        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(15);
        gp.getRowConstraints().add(0,rc);
        for(int i = 1; i<3; i++) {
            rc = new RowConstraints();
            rc.setPercentHeight(35);
            gp.getRowConstraints().add(i,rc);
        }
        rc = new RowConstraints();
        rc.setPercentHeight(15);
        gp.getRowConstraints().add(3,rc);

        gp.setGridLinesVisible(true);
        Scene scene = new Scene(gp);
        primaryStage.setScene(scene);


        GridPane grid = new GridPane();
        TextArea entree = new TextArea();
        entree.setMaxSize(400,150);
        TextArea ecran = new TextArea();
        ecran.setMaxSize(400, 150);
        Label t = new Label("Syntaxe : 'nombre'+';'");
        Label res = new Label("Résultats : ");
        Label t1 = new Label("Partie théorique");
        Label t2 = new Label("Vos échantillons : ");
        Label t3 = new Label("Un seul échantillon :");
        t3.setTextFill(Color.BLUE);
        Label t3b = new Label("Plusieurs échantillons :");
        t3b.setTextFill(Color.BLUE);
        Label t4 = new Label("Graphiques :");
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

        GridPane gridecran = new GridPane();
        gridecran.setHgap(10);
        gridecran.setVgap(10);
        Button btnAffiche = new Button("Afficher");
        HBox hbbtnAffiche = new HBox(10);
        hbbtnAffiche.setAlignment(Pos.BOTTOM_CENTER);
        hbbtnAffiche.getChildren().add(btnAffiche);
        gridecran.add(res,0,0);
        gridecran.add(ecran, 0, 1);
        gridecran.add(hbBtnModif, 0, 3);
        gridecran.add(hbbtnAffiche,0,2);

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
        CheckBox cb = new CheckBox("Echantillon 1");
        grid2.add(cb, 0,1);
        cb.setVisible(false);
        CheckBox cb1 = new CheckBox("Echantillon 2");
        grid2.add(cb1, 0,2);
        cb1.setVisible(false);
        CheckBox cb2 = new CheckBox("Echantillon 3");
        grid2.add(cb2, 0,3);
        cb2.setVisible(false);
        CheckBox cb3 = new CheckBox("Echantillon 4");
        grid2.add(cb3, 0,4);
        cb3.setVisible(false);
        CheckBox cb4 = new CheckBox("Echantillon 5");
        grid2.add(cb4, 0,5);
        cb4.setVisible(false);
        CheckBox cb5 = new CheckBox("Echantillon 6");
        grid2.add(cb5, 0,6);
        cb5.setVisible(false);
        CheckBox cb6 = new CheckBox("Echantillon 7");
        grid2.add(cb6, 1,1);
        cb6.setVisible(false);
        CheckBox cb7 = new CheckBox("Echantillon 8");
        grid2.add(cb7, 1,2);
        cb7.setVisible(false);
        CheckBox cb8 = new CheckBox("Echantillon 9");
        grid2.add(cb8, 1,3);
        cb8.setVisible(false);
        CheckBox cb9 = new CheckBox("Echantillon 10");
        grid2.add(cb9, 1,4);
        cb9.setVisible(false);
        CheckBox cb10 = new CheckBox("Echantillon 11");
        grid2.add(cb10, 1,5);
        cb10.setVisible(false);
        CheckBox cb11 = new CheckBox("Echantillon 12");
        grid2.add(cb11, 1,6);
        cb11.setVisible(false);

        GridPane gpech = new GridPane();
        gpech.setHgap(10);
        gpech.setVgap(10);
        Button btnMoy = new Button("Moyenne");
        HBox hbBtnMoy = new HBox(10);
        hbBtnMoy.getChildren().add(btnMoy);
        Button btnTaille = new Button("Taille");
        HBox hbBtnTaille = new HBox(10);
        hbBtnTaille.getChildren().add(btnTaille);
        Button btnMin = new Button("Min");
        HBox hbBtnMin = new HBox(10);
        hbBtnMin.getChildren().add(btnMin);
        Button btnMax = new Button("Max");
        Button btnEcarttype = new Button("Ecart type");
        Button btnVariance = new Button("Variance");
        Button btnMediane =new Button("Médiane");
        Button btnsct = new Button("SCT");
        Button btnquartile = new Button("Quartile");
        Button btnfrequence = new Button("Fréquence");
        HBox hbBtnVariance = new HBox(10);
        hbBtnVariance.getChildren().add(btnVariance);
        gpech.add(t3,0,0);
        gpech.add(hbBtnMoy, 0, 1);
        gpech.add(btnMediane, 1,1);
        gpech.add(hbBtnVariance,0,2);
        gpech.add(btnEcarttype,1,2);
        gpech.add(btnsct, 2,2);
        gpech.add(hbBtnTaille, 0, 3);
        gpech.add(hbBtnMin, 1, 3);
        gpech.add(btnMax, 2, 3);
        gpech.add(btnquartile, 0, 4);
        gpech.add(btnfrequence, 1, 4);


        GridPane grid3 = new GridPane();
        grid3.setHgap(10);
        grid3.setVgap(10);
        Label regle = new Label("Pour créer un échantillon à l'écrit:\n La syntaxe à respectée est de séparer chaque nombre par un';'\n" +
                "Vous pouvez faire des retours et des espaces à la ligne quand vous voulez \n" +
                "Pour la description d'un échantillon :\n" +
                "Si vous avez séléctionné plusieurs échantillons : Seul le dernier séléctionné sera pris en compte\n"+
                "Pour réaliser un test de régression linéaire veuillez séléctionner 2 de vos échantillons\n" +
                "Pour un test d'anova ou de chi2 d'indépendance il faut en sélectionner au moins 2");
        grid3.add(regle,0,0);


        GridPane gridRL =new GridPane();
        gridRL.setHgap(10);
        gridRL.setVgap(10);
        Button btnRL = new Button("Régression linéaire");
        HBox hbBtnRL = new HBox(10);
        btnRL.setVisible(false);
        Button btnAnova = new Button("Anova");
        btnAnova.setVisible(false);
        Button btnchi2 = new Button("Chi2");
        btnchi2.setVisible(false);
        hbBtnRL.getChildren().add(btnRL);
        gridRL.add(t3b,0,0);
        gridRL.add(hbBtnRL, 0, 1);
        gridRL.add(btnAnova,0,2);
        gridRL.add(btnchi2, 0, 3);


        GridPane grid4 = new GridPane();
        grid4.setHgap(10);
        grid4.setVgap(10);
        Button btn4 = new Button("Graphique");
        HBox hbBtn4 = new HBox(10);
        //hbBtn4.setAlignment(Pos.TOP_RIGHT);
        hbBtn4.getChildren().add(btn4);
        grid4.add(hbBtn4, 0, 1);
        grid4.add(t4,0,0);

        grid1.setAlignment(Pos.TOP_CENTER);
        grid.setAlignment(Pos.CENTER);
        gridecran.setAlignment(Pos.TOP_CENTER);
        grid2.setAlignment(Pos.CENTER);
        gpech.setAlignment(Pos.CENTER);
        grid3.setAlignment(Pos.CENTER);
        gridRL.setAlignment(Pos.CENTER);
        grid4.setAlignment(Pos.TOP_CENTER);
        gridecran.setAlignment(Pos.CENTER);

        gp.add(grid1,1,0);
        gp.addRow(1,grid2,grid, grid3);
        gp.addRow(2, gpech, gridecran,gridRL);
        gp.add(grid4,1,3);
        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 6);

        final Text actiontarget2 = new Text();
        grid3.add(actiontarget2, 0, 1);


        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                try {
                    if(compteur == 12) {
                        actiontarget.setFill(Color.GREEN);
                        actiontarget.setText(new String(("Plus de place,pour un nouvel échantillon")));
                    }else {
                        tabEch[compteur] = new Echantillon(entree.getText());
                        compteur++;
                        if(compteur==1) cb.setVisible(true);
                        if(compteur==2) {
                            cb1.setVisible(true);
                            btnRL.setVisible(true);
                            btnAnova.setVisible(true);
                            btnchi2.setVisible(true);
                        }
                        if(compteur==3) cb2.setVisible(true);
                        if(compteur==4) cb3.setVisible(true);
                        if(compteur==5) cb4.setVisible(true);
                        if(compteur==6) cb5.setVisible(true);
                        if(compteur==7) cb6.setVisible(true);
                        if(compteur==8) cb7.setVisible(true);
                        if(compteur==9) cb8.setVisible(true);
                        if(compteur==10) cb9.setVisible(true);
                        if(compteur==11) cb10.setVisible(true);
                        if(compteur==12) cb11.setVisible(true);
                        actiontarget.setFill(Color.BLUE);
                        actiontarget.setText("échantillon " + compteur + " créé");
                    }
                }
                catch(ExceptionDonneesEntree de){
                    actiontarget.setFill(Color.RED);
                    actiontarget.setText(new String("L'échantillon " + (compteur+1) + " n'a pas pu être créé :\n"+ de.getMessage()));
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
                if(cb4.isSelected()) c=4;
                if(cb5.isSelected()) c=5;
                if(cb6.isSelected()) c=6;
                if(cb7.isSelected()) c=7;
                if(cb8.isSelected()) c=8;
                if(cb9.isSelected()) c=9;
                if(cb10.isSelected()) c=10;
                if(cb11.isSelected()) c=11;
                try {
                    tabEch[c] = new Echantillon(entree.getText());
                    ecran.setText(tabEch[c].toString());
                    actiontarget.setFill(Color.BLUE);
                    actiontarget.setText("L'échantillon a bien été modifié");
                }catch(ExceptionDonneesEntree de){
                    actiontarget.setFill(Color.RED);
                    actiontarget.setText("L'échantillon " + (compteur+1) + " n'a pas pu être créé :"+ de.getMessage()+"\n Ou bien la synthaxe n'est pas respéctée");
                }
            }
        });
        btnAffiche.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int c = 0;
                if(cb1.isSelected()) c=1;
                if(cb2.isSelected()) c=2;
                if(cb3.isSelected()) c=3;
                if(cb4.isSelected()) c=4;
                if(cb5.isSelected()) c=5;
                if(cb6.isSelected()) c=6;
                if(cb7.isSelected()) c=7;
                if(cb8.isSelected()) c=8;
                if(cb9.isSelected()) c=9;
                if(cb10.isSelected()) c=10;
                if(cb11.isSelected()) c=11;
                ecran.setText(tabEch[c].toString());
            }
        });
        btnMoy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int c = 0;
                if(cb1.isSelected()) c=1;
                if(cb2.isSelected()) c=2;
                if(cb3.isSelected()) c=3;
                if(cb4.isSelected()) c=4;
                if(cb5.isSelected()) c=5;
                if(cb6.isSelected()) c=6;
                if(cb7.isSelected()) c=7;
                if(cb8.isSelected()) c=8;
                if(cb9.isSelected()) c=9;
                if(cb10.isSelected()) c=10;
                if(cb11.isSelected()) c=11;
                ecran.setText(String.valueOf(tabEch[c].getMoyenne()));
            }
        }); btnMediane.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int c = 0;
                if(cb1.isSelected()) c=1;
                if(cb2.isSelected()) c=2;
                if(cb3.isSelected()) c=3;
                if(cb4.isSelected()) c=4;
                if(cb5.isSelected()) c=5;
                if(cb6.isSelected()) c=6;
                if(cb7.isSelected()) c=7;
                if(cb8.isSelected()) c=8;
                if(cb9.isSelected()) c=9;
                if(cb10.isSelected()) c=10;
                if(cb11.isSelected()) c=11;
                ecran.setText(String.valueOf(tabEch[c].getMediane()));
            }
        }); btnVariance.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int c = 0;
                if(cb1.isSelected()) c=1;
                if(cb2.isSelected()) c=2;
                if(cb3.isSelected()) c=3;
                if(cb4.isSelected()) c=4;
                if(cb5.isSelected()) c=5;
                if(cb6.isSelected()) c=6;
                if(cb7.isSelected()) c=7;
                if(cb8.isSelected()) c=8;
                if(cb9.isSelected()) c=9;
                if(cb10.isSelected()) c=10;
                if(cb11.isSelected()) c=11;
                ecran.setText(String.valueOf(tabEch[c].getVariance()));
            }
        }); btnsct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int c = 0;
                if(cb1.isSelected()) c=1;
                if(cb2.isSelected()) c=2;
                if(cb3.isSelected()) c=3;
                if(cb4.isSelected()) c=4;
                if(cb5.isSelected()) c=5;
                if(cb6.isSelected()) c=6;
                if(cb7.isSelected()) c=7;
                if(cb8.isSelected()) c=8;
                if(cb9.isSelected()) c=9;
                if(cb10.isSelected()) c=10;
                if(cb11.isSelected()) c=11;
                ecran.setText(String.valueOf(tabEch[c].getSCT()));
            }
        });btnEcarttype.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int c = 0;
                if(cb1.isSelected()) c=1;
                if(cb2.isSelected()) c=2;
                if(cb3.isSelected()) c=3;
                if(cb4.isSelected()) c=4;
                if(cb5.isSelected()) c=5;
                if(cb6.isSelected()) c=6;
                if(cb7.isSelected()) c=7;
                if(cb8.isSelected()) c=8;
                if(cb9.isSelected()) c=9;
                if(cb10.isSelected()) c=10;
                if(cb11.isSelected()) c=11;
                ecran.setText(String.valueOf(tabEch[c].getEcarttype()));
            }
        });btnMin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int c = 0;
                if(cb1.isSelected()) c=1;
                if(cb2.isSelected()) c=2;
                if(cb3.isSelected()) c=3;
                if(cb4.isSelected()) c=4;
                if(cb5.isSelected()) c=5;
                if(cb6.isSelected()) c=6;
                if(cb7.isSelected()) c=7;
                if(cb8.isSelected()) c=8;
                if(cb9.isSelected()) c=9;
                if(cb10.isSelected()) c=10;
                if(cb11.isSelected()) c=11;
                ecran.setText(String.valueOf(tabEch[c].getMinimum()));
            }
        });btnMax.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int c = 0;
                if(cb1.isSelected()) c=1;
                if(cb2.isSelected()) c=2;
                if(cb3.isSelected()) c=3;
                if(cb4.isSelected()) c=4;
                if(cb5.isSelected()) c=5;
                if(cb6.isSelected()) c=6;
                if(cb7.isSelected()) c=7;
                if(cb8.isSelected()) c=8;
                if(cb9.isSelected()) c=9;
                if(cb10.isSelected()) c=10;
                if(cb11.isSelected()) c=11;
                ecran.setText(String.valueOf(tabEch[c].getMaximum()));
            }
        });btnTaille.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int c = 0;
                if(cb1.isSelected()) c=1;
                if(cb2.isSelected()) c=2;
                if(cb3.isSelected()) c=3;
                if(cb4.isSelected()) c=4;
                if(cb5.isSelected()) c=5;
                if(cb6.isSelected()) c=6;
                if(cb7.isSelected()) c=7;
                if(cb8.isSelected()) c=8;
                if(cb9.isSelected()) c=9;
                if(cb10.isSelected()) c=10;
                if(cb11.isSelected()) c=11;
                ecran.setText(String.valueOf(tabEch[c].getTaille()));
            }
        });btnfrequence.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int c = 0;
                if(cb1.isSelected()) c=1;
                if(cb2.isSelected()) c=2;
                if(cb3.isSelected()) c=3;
                if(cb4.isSelected()) c=4;
                if(cb5.isSelected()) c=5;
                if(cb6.isSelected()) c=6;
                if(cb7.isSelected()) c=7;
                if(cb8.isSelected()) c=8;
                if(cb9.isSelected()) c=9;
                if(cb10.isSelected()) c=10;
                if(cb11.isSelected()) c=11;
                try {
                    ecran.setText(String.valueOf(tabEch[c].getFrequence(Double.parseDouble(entree.getText()))));
                }
                catch(Exception def){
                    actiontarget.setFill(Color.RED);
                    actiontarget.setText(new String("La fréquence doit être celle d'un nombre"));
                }
            }
        });btnquartile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int c = 0;
                if(cb1.isSelected()) c=1;
                if(cb2.isSelected()) c=2;
                if(cb3.isSelected()) c=3;
                if(cb4.isSelected()) c=4;
                if(cb5.isSelected()) c=5;
                if(cb6.isSelected()) c=6;
                if(cb7.isSelected()) c=7;
                if(cb8.isSelected()) c=8;
                if(cb9.isSelected()) c=9;
                if(cb10.isSelected()) c=10;
                if(cb11.isSelected()) c=11;
                try {
                    ecran.setText(String.valueOf(tabEch[c].getQuartiles(Integer.parseInt(entree.getText()))));
                }catch(Exception def){
                    actiontarget.setFill(Color.RED);
                    actiontarget.setText(new String("Entrer le quartile à calculer"));
                }
            }
        });
        btnAnova.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ecran.setText("ANOVA:");
                int compt =0;
                if(cb.isSelected()) compt++;
                if(cb1.isSelected()) compt++;
                if(cb2.isSelected()) compt++;
                if(cb3.isSelected()) compt++;
                if(cb4.isSelected()) compt++;
                if(cb5.isSelected()) compt++;
                if(cb6.isSelected()) compt++;
                if(cb7.isSelected()) compt++;
                if(cb8.isSelected()) compt++;
                if(cb9.isSelected()) compt++;
                if(cb10.isSelected()) compt++;
                if(cb11.isSelected()) compt++;
                Echantillon[] echAN = new Echantillon[compt];
                    compt = 0;

                    if (cb.isSelected() ) {
                        echAN[compt] = tabEch[compt];
                        compt++;
                    }
                    if (cb1.isSelected() ) {
                        echAN[compt] = tabEch[compt];
                        compt++;
                    }
                    if (cb2.isSelected() ) {
                        echAN[compt] = tabEch[compt];
                        compt++;
                    }
                    if (cb3.isSelected() ) {
                        echAN[compt] = tabEch[compt];
                        compt++;
                    }
                    if (cb4.isSelected() ) {
                        echAN[compt] = tabEch[compt];
                        compt++;
                    }
                    if (cb5.isSelected() ) {
                        echAN[compt] = tabEch[compt];
                        compt++;
                    }
                if (cb6.isSelected() ) {
                    echAN[compt] = tabEch[compt];
                    compt++;
                }
                if (cb7.isSelected() ) {
                    echAN[compt] = tabEch[compt];
                    compt++;
                }
                if (cb8.isSelected() ) {
                    echAN[compt] = tabEch[compt];
                    compt++;
                }
                if (cb9.isSelected() ) {
                    echAN[compt] = tabEch[compt];
                    compt++;
                }
                if (cb10.isSelected() ) {
                    echAN[compt] = tabEch[compt];
                    compt++;
                }
                if (cb11.isSelected() ) {
                    echAN[compt] = tabEch[compt];
                    compt++;
                }
                    try {
                        Anova an = new Anova(echAN);
                        ecran.setText(an.decision());
                    } catch (ExceptionNombreEchantillons | ExceptionTailleEchantillon en){
                        ecran.setText(en.getMessage());

                    }

            }

        });
        btnchi2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ecran.setText("Chi2:");
                int compt =0;
                if(cb.isSelected()) compt++;
                if(cb1.isSelected()) compt++;
                if(cb2.isSelected()) compt++;
                if(cb3.isSelected()) compt++;
                if(cb4.isSelected()) compt++;
                if(cb5.isSelected()) compt++;
                if(cb6.isSelected()) compt++;
                if(cb7.isSelected()) compt++;
                if(cb8.isSelected()) compt++;
                if(cb9.isSelected()) compt++;
                if(cb10.isSelected()) compt++;
                if(cb11.isSelected()) compt++;
                Echantillon[] echC2 = new Echantillon[compt];
                compt = 0;

                if (cb.isSelected() ) {
                    echC2[compt] = tabEch[compt];
                    compt++;
                }
                if (cb1.isSelected() ) {
                    echC2[compt] = tabEch[compt];
                    compt++;
                }
                if (cb2.isSelected() ) {
                    echC2[compt] = tabEch[compt];
                    compt++;
                }
                if (cb3.isSelected() ) {
                    echC2[compt] = tabEch[compt];
                    compt++;
                }
                if (cb4.isSelected() ) {
                    echC2[compt] = tabEch[compt];
                    compt++;
                }
                if (cb5.isSelected() ) {
                    echC2[compt] = tabEch[compt];
                    compt++;
                }
                if (cb6.isSelected() ) {
                    echC2[compt] = tabEch[compt];
                    compt++;
                }
                if (cb7.isSelected() ) {
                    echC2[compt] = tabEch[compt];
                    compt++;
                }
                if (cb8.isSelected() ) {
                    echC2[compt] = tabEch[compt];
                    compt++;
                }
                if (cb9.isSelected() ) {
                    echC2[compt] = tabEch[compt];
                    compt++;
                }
                if (cb10.isSelected() ) {
                    echC2[compt] = tabEch[compt];
                    compt++;
                }
                if (cb11.isSelected() ) {
                    echC2[compt] = tabEch[compt];
                    compt++;
                }
                try {
                    Chi2 c2 =new Chi2(echC2);
                    ecran.setText(c2.decision());
                } catch (IllegalArgumentException en){
                    ecran.setText(en.getMessage());

                }

            }

        });
        btnRL.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent e) {
                ecran.setText("Régression linéaire:");
                int compt = 0;
                Echantillon[] echRL = new Echantillon[2];
                if (cb.isSelected() ) {
                    echRL[compt] = tabEch[compt];
                    compt++;
                }
                if (cb1.isSelected() ) {
                    echRL[compt] = tabEch[compt];
                    compt++;
                }
                if (cb2.isSelected() ) {
                    if (compt < 2) echRL[compt] = tabEch[compt];
                        compt++;

                }
                if (cb3.isSelected() ) {
                    if (compt < 2) echRL[compt] = tabEch[compt];
                    compt++;
                    }
                if (cb4.isSelected() ) {
                    if (compt < 2) echRL[compt] = tabEch[compt];
                    compt++;
                }
                if (cb5.isSelected() ) {
                    if (compt < 2) echRL[compt] = tabEch[compt];
                    compt++;
                }
                if (cb6.isSelected() ) {
                    if (compt < 2) echRL[compt] = tabEch[compt];
                    compt++;
                }
                if (cb7.isSelected() ) {
                    if (compt < 2) echRL[compt] = tabEch[compt];
                    compt++;
                }
                if (cb8.isSelected() ) {
                    if (compt < 2) echRL[compt] = tabEch[compt];
                    compt++;
                }
                if (cb9.isSelected() ) {
                    if (compt < 2) echRL[compt] = tabEch[compt];
                    compt++;
                }
                if (cb10.isSelected() ) {
                    if (compt < 2) echRL[compt] = tabEch[compt];
                    compt++;
                }
                if (cb11.isSelected() ) {
                    if (compt < 2) echRL[compt] = tabEch[compt];
                    compt++;
                }

                try {
                    if(compt!=2) ecran.setText("Séléctionner 2 échantillons");
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
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
