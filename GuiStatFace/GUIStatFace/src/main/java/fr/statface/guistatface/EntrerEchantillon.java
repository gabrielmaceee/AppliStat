package fr.statface.guistatface;

import fr.statface.Statistiques.*;
import javafx.application.Application;
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
import java.util.ArrayList;


import static jdk.jfr.consumer.EventStream.openFile;

// faire en sorte que l'anova ne prenne pas de nuls
public class EntrerEchantillon extends Application{
    Echantillon[] tabEch = new Echantillon[12];
    int compteur = 0;
    public void start(Stage primaryStage) {
        primaryStage.setTitle("StatFace");

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
        Button btnEntrer = new Button("Entrer");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(btnEntrer);
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

        ArrayList<CheckBox> lsCheckBoxes = new ArrayList<CheckBox>();
        int i=0;
        int k=1;
        for (int i1 = 1; i1 <= 12; i1++) {
            CheckBox cb = new CheckBox("Echantillon "+ i1);
            grid2.add(cb, i,k);
            k++;
            if (i1 == 6) {
                i++;
                k=1;
            }
            cb.setVisible(false);
            lsCheckBoxes.add(cb);
        }

        /*
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
        */

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
        Label regle = new Label("""
                Pour créer un échantillon à l'écrit:
                La syntaxe à respectée est de séparer chaque nombre par un';'
                Vous pouvez faire des retours et des espaces à la ligne quand vous voulez\s
                Pour la description d'un échantillon :
                    Si vous avez sélectionné plusieurs échantillons :
                    Seul le dernier sélectionné sera pris en compte
                Pour réaliser un test de régression linéaire
                    veuillez sélectionner 2 de vos échantillons
                Pour un test ANOVA ou de CHI2 d'indépendance
                    Sélectionnez en au moins 2""");
        grid3.add(regle,0,0);


        GridPane gridRL =new GridPane();
        gridRL.setHgap(10);
        gridRL.setVgap(10);
        Button btnRL = new Button("Régression linéaire");
        HBox hbBtnRL = new HBox(10);
        btnRL.setVisible(false);
        Button btnAnova = new Button("ANOVA");
        btnAnova.setVisible(false);
        Button btnchi2 = new Button("CHI2");
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


        btnEntrer.setOnAction(e -> {
            try {
                if(compteur == 12) {
                    actiontarget.setFill(Color.GREEN);
                    actiontarget.setText(("Plus de place,pour un nouvel échantillon"));
                } else {
                    tabEch[compteur] = new Echantillon(entree.getText());


                    lsCheckBoxes.get(compteur).setVisible(true);
                    if (compteur==0){
                        btnRL.setVisible(true);
                        btnAnova.setVisible(true);
                        btnchi2.setVisible(true);
                    }
                    compteur++;
                    actiontarget.setFill(Color.BLUE);
                    actiontarget.setText("échantillon " + compteur + " créé");
                }
            } catch(ExceptionDonneesEntree de){
                actiontarget.setFill(Color.RED);
                actiontarget.setText("L'échantillon " + (compteur + 1) + " n'a pas pu être créé :\n" + de.getMessage());
            }
        });

        btnfile.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                try {
                    openFile(file.toPath());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnModif.setOnAction(e -> {
            int idx = 0;
            for (CheckBox cbx: lsCheckBoxes) {
                if (cbx.isSelected()){
                    try {
                        tabEch[idx] = new Echantillon(entree.getText());
                        ecran.setText(tabEch[idx].toString());
                        actiontarget.setFill(Color.BLUE);
                        actiontarget.setText("L'échantillon a bien été modifié");
                    } catch(ExceptionDonneesEntree de){
                        actiontarget.setFill(Color.RED);
                        actiontarget.setText("L'échantillon " + (compteur+1) + " n'a pas pu être créé :"+ de.getMessage()+"\n Ou bien la syntaxe n'est pas respectée");
                    }
                }
                idx++;
            }

            /*
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
            */


        });
        btnAffiche.setOnAction(e -> {
            int c = 0;
            StringBuilder bf = new StringBuilder();
            for (CheckBox cbx: lsCheckBoxes) {
                if (cbx.isSelected()){
                    bf.append(tabEch[c].toString()).append("\n");
                }
                c++;
            }
            ecran.setText(bf.toString());
        });

        btnMoy.setOnAction(e -> {
            int c = 0;
            StringBuilder sb = new StringBuilder();
            for (CheckBox cbx: lsCheckBoxes) {
                if (cbx.isSelected()){
                    sb.append("La moyenne de l'échantillon ")
                            .append(c+1)
                            .append(" : ")
                            .append(tabEch[c].getMoyenne())
                            .append("\n");
                }
                c++;
            }
            ecran.setText(sb.toString());
        });

        btnMediane.setOnAction(e -> {
            int c = 0;
            StringBuilder sb = new StringBuilder();
            for (CheckBox cbx: lsCheckBoxes) {
                if (cbx.isSelected()){
                    sb.append("La médiane de l'échantillon ")
                            .append(c+1)
                            .append(" : ")
                            .append(tabEch[c].getMediane())
                            .append("\n");
                }
                c++;
            }
            ecran.setText(sb.toString());
        });

        btnVariance.setOnAction(e -> {
            int c = 0;
            StringBuilder sb = new StringBuilder();
            for (CheckBox cbx: lsCheckBoxes) {
                if (cbx.isSelected()){
                    sb.append("Variance echantillon ")
                            .append(c+1)
                            .append(" : ")
                            .append(tabEch[c].getVariance())
                            .append("\n");
                }
                c++;
            }
            ecran.setText(sb.toString());
        });

        btnsct.setOnAction(e -> {
            int c = 0;
            StringBuilder sb = new StringBuilder();
            for (CheckBox cbx: lsCheckBoxes) {
                if (cbx.isSelected()){
                    sb.append("SCT échantillon ")
                            .append(c+1)
                            .append(" : ")
                            .append(tabEch[c].getSCT())
                            .append("\n");
                }
                c++;
            }
            ecran.setText(sb.toString());

        });

        btnEcarttype.setOnAction(e -> {
            int c = 0;
            StringBuilder sb = new StringBuilder();
            for (CheckBox cbx: lsCheckBoxes) {
                if (cbx.isSelected()){
                    sb.append("Ecart type echantillon ")
                            .append(c+1)
                            .append(" : ")
                            .append(tabEch[c].getEcarttype())
                            .append("\n");
                }
                c++;
            }
            ecran.setText(sb.toString());
        });

        btnMin.setOnAction(e -> {
            int c = 0;
            StringBuilder sb = new StringBuilder();
            for (CheckBox cbx: lsCheckBoxes) {
                if (cbx.isSelected()){
                    sb.append("Le minimum de l'échantillon ")
                            .append(c+1)
                            .append(" : ")
                            .append(tabEch[c].getMinimum())
                            .append("\n");
                }
                c++;
            }
            ecran.setText(sb.toString());
        });

        btnMax.setOnAction(e -> {
            int c = 0;
            StringBuilder sb = new StringBuilder();
            for (CheckBox cbx: lsCheckBoxes) {
                if (cbx.isSelected()){
                    sb.append("Maximum echantillon ")
                            .append(c+1)
                            .append(" : ")
                            .append(tabEch[c].getMaximum())
                            .append("\n");
                }
                c++;
            }
            ecran.setText(sb.toString());
        });

        btnTaille.setOnAction(e -> {
            int c = 0;
            StringBuilder sb = new StringBuilder();
            for (CheckBox cbx: lsCheckBoxes) {
                if (cbx.isSelected()){
                    sb.append("Taille echantillon ")
                            .append(c+1)
                            .append(" : ")
                            .append(tabEch[c].getTaille())
                            .append("\n");
                }
                c++;
            }
            ecran.setText(sb.toString());
        });

        btnfrequence.setOnAction(e -> {
            int c = 0;
            for (CheckBox cbx: lsCheckBoxes) {
                if (cbx.isSelected()){
                    try {
                        ecran.setText(String.valueOf(tabEch[c].getFrequence(Double.parseDouble(entree.getText()))));
                    } catch(Exception def) {
                        actiontarget.setFill(Color.RED);
                        actiontarget.setText("La fréquence doit être celle d'un nombre");
                    }
                }
                c++;
            }

        });

        btnquartile.setOnAction(e -> {
            int c = 0;
            for (CheckBox cbx: lsCheckBoxes) {
                if (cbx.isSelected()) {
                    try {
                        ecran.setText(String.valueOf(tabEch[c].getQuartiles(Integer.parseInt(entree.getText()))));
                    } catch (Exception def) {
                        actiontarget.setFill(Color.RED);
                        actiontarget.setText("Entrer le quartile à calculer");
                    }
                }
            }
        });

        btnAnova.setOnAction(e -> {
            ecran.setText("ANOVA:");
            ArrayList<Echantillon> echAN = new ArrayList<>();
            int cpt = 0;
            for (CheckBox cbx: lsCheckBoxes) {
                if (cbx.isSelected()) {
                    echAN.add(tabEch[cpt]);
                }
                cpt++;
            }
            try {
                Anova an = new Anova((Echantillon[]) echAN.toArray()); // BUG de conversion
                ecran.setText(an.decision());
            } catch (ExceptionNombreEchantillons | ExceptionTailleEchantillon en){
                ecran.setText(en.getMessage());
            }

        });
        btnchi2.setOnAction(e -> {
            ecran.setText("Chi2:");
            ArrayList<Echantillon> echCHI2 = new ArrayList<>();
            int cpt = 0;
            for (CheckBox cbx: lsCheckBoxes) {
                if (cbx.isSelected()) {
                    echCHI2.add(tabEch[cpt]);
                }
                cpt++;
            }
            try {
                Chi2 c2 =new Chi2((Echantillon[]) echCHI2.toArray()); // BUG de conversion
                ecran.setText(c2.decision());
            } catch (IllegalArgumentException en){
                ecran.setText(en.getMessage());

            }

        });
        btnRL.setOnAction(e -> {
            ecran.setText("Régression linéaire:");
            ArrayList<Echantillon> echRL = new ArrayList<>(2);
            int cpt = 0;
            for (CheckBox cbx: lsCheckBoxes) {
                if (cbx.isSelected()) {
                    echRL.add(tabEch[cpt]);
                }
                cpt++;
            }

            try {
                if(echRL.size()!=2) ecran.setText("Sélectionner 2 échantillons");
                else {
                    RegressionLineaire RL = new RegressionLineaire(echRL.get(0), echRL.get(1));
                    ecran.setText(RL.decision());
                }
            }
            catch (ExceptionTailleEchantillon et){
                ecran.setText(et.getMessage());
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
