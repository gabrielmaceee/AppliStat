package fr.statface.guistatface;

import fr.statface.Statistiques.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class EntrerEchantillon extends Application{
    Echantillon[] tabEch = new Echantillon[12];
    int compteur = 0;
    public void start(Stage primaryStage)  {
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
        ecran.setEditable(false);
        Label t = new Label("Syntaxe : 'nombre'+';'");
        Label res = new Label("Résultats : ");
        Label t1 = new Label("Partie théorique : ");
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
        Button btnSuppr = new Button("Supprimer un échantillon");
        HBox hbBtnModif = new HBox(10);
        hbBtnModif.setAlignment(Pos.CENTER);
        hbBtnModif.getChildren().add(btnModif);
        hbBtnModif.getChildren().add(btnSuppr);
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
        Button btnTest = new Button("Les tests statistiques");
        Button btnCRL = new Button("R\u00E9gression lin\u00E9aire");
        Button btnCAnova = new Button("Test d'Anova");
        Button btnCChi2 = new Button("Test d'indépendance du Chi2");
        grid1.add(t1, 0,0);
        grid1.add(btnTest, 0, 1);
        grid1.add(btnCRL, 1, 1);
        grid1.add(btnCAnova, 0, 2);
        grid1.add(btnCChi2, 1, 2);


        GridPane grid2 = new GridPane();
        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.add(t2,0,0);
        ArrayList<CheckBox> lsCheckBoxes = new ArrayList<CheckBox>();
        for( int i = 0;i<12; i++){
            CheckBox cB = new CheckBox("Echantillon "+(i));
            lsCheckBoxes.add(cB);
            if(i<6) grid2.add(cB,0,i+1);
            else grid2.add(cB,1,(i-5));
            cB.setVisible(false);
        }
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

        ArrayList<Button> lsBtnUnEchantillon = new ArrayList<Button>();
        lsBtnUnEchantillon.add(btnMoy);
        lsBtnUnEchantillon.add(btnTaille);
        lsBtnUnEchantillon.add(btnMin);
        lsBtnUnEchantillon.add(btnMax);
        lsBtnUnEchantillon.add(btnEcarttype);
        lsBtnUnEchantillon.add(btnVariance);
        lsBtnUnEchantillon.add(btnsct);
        lsBtnUnEchantillon.add(btnMediane);
        lsBtnUnEchantillon.add(btnquartile);
        lsBtnUnEchantillon.add(btnfrequence);
        lsBtnUnEchantillon.forEach(b -> b.setVisible(false));

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
                Si vous avez sélectionné plusieurs échantillons : Seul le dernier sélectionné sera pris en compte
                Pour réaliser un test de régression linéaire veuillez sélectionner 2 de vos échantillons
                Pour un test ANOVA ou de CHI2 d'indépendance il faut en sélectionner au moins 2""");
        grid3.add(regle,0,0);


        GridPane gridRL =new GridPane();
        gridRL.setHgap(10);
        gridRL.setVgap(10);
        Button btnRL = new Button("Régression linéaire");
        HBox hbBtnRL = new HBox(10);
        btnRL.setVisible(false);
        Button btnAnova = new Button("ANOVA");
        btnAnova.setVisible(false);
        Button btnchi2 = new Button("Chi2");
        btnchi2.setVisible(false);
        hbBtnRL.getChildren().add(btnRL);
        gridRL.add(t3b,0,0);
        gridRL.add(hbBtnRL, 0, 1);
        gridRL.add(btnAnova,0,2);
        gridRL.add(btnchi2, 0, 3);


        GridPane gridGraph1 = new GridPane();
        Button btnGraph1 = new Button("Graph 1");
        gridGraph1.add(btnGraph1,0,0);

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


        btn.setOnAction(e -> {
            try {
                if(compteur == 12) {
                    actiontarget.setFill(Color.GREEN);
                    actiontarget.setText("Plus de place,pour un nouvel échantillon");
                } else {
                    tabEch[compteur] = new Echantillon(entree.getText());
                    lsCheckBoxes.get(compteur).setVisible(true);
                    compteur++;
                    lsBtnUnEchantillon.forEach(b -> b.setVisible(true));
                    if(compteur==2) {
                        btnRL.setVisible(true);
                        btnAnova.setVisible(true);
                        btnchi2.setVisible(true);
                    }
                    actiontarget.setFill(Color.BLUE);
                    actiontarget.setText("échantillon " + compteur + " créé");
                }
            }
            catch(ExceptionDonneesEntree de){
                actiontarget.setFill(Color.RED);
                actiontarget.setText("L'échantillon " + (compteur+1) + " n'a pas pu être créé :\n"+ de.getMessage());
            }
        });

        btnfile.setOnAction(    e -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if(file==null)return;
            for(CheckBox tb:lsCheckBoxes){
                tb.setVisible(false);
            }
            EchantillonReader er = new EchantillonReader(file.toPath().toString());
            compteur = er.getCompteur();
            if(compteur>=1) lsCheckBoxes.get(0).setVisible(true);
            if(compteur>=2) {
                lsCheckBoxes.get(1).setVisible(true);
                btnRL.setVisible(true);
                btnAnova.setVisible(true);
                btnchi2.setVisible(true);
            }
            for(int i=2;i<lsCheckBoxes.size();i++){
                if(compteur>i)lsCheckBoxes.get(i).setVisible(true);
            }
            int ik=0;
            try {
                String[] result= er.EchantillonToString();
                for(int i=0;i< tabEch.length;i++){
                    if(ik<compteur){
                        System.out.println(result[ik]);
                        tabEch[i]=new Echantillon(result[ik]);
                        ik++;
                    }
                }
            } catch (ExceptionDonneesEntree ex) {
                throw new RuntimeException(ex);
            }

        });

        btnModif.setOnAction(e -> {
            int c = 0;
            for (CheckBox cbx: lsCheckBoxes) {
                if (cbx.isSelected()){
                    try {
                        tabEch[c] = new Echantillon(entree.getText());
                        ecran.setText(tabEch[c].toString());
                        actiontarget.setFill(Color.BLUE);
                        actiontarget.setText("L'échantillon" + (compteur+1) + "a bien été modifié");
                    } catch(ExceptionDonneesEntree de){
                        actiontarget.setFill(Color.RED);
                        actiontarget.setText("L'échantillon " + (compteur+1) + " n'a pas pu être créé :"+ de.getMessage()+"\n Ou bien la syntaxe n'est pas respectée");
                    }
                }
            }
        });
        btnAffiche.setOnAction(e -> {
            int c = 0;
            StringBuilder bf = new StringBuilder();
            for (CheckBox cbx: lsCheckBoxes) {
                if (cbx.isSelected()){
                    try {
                        bf.append("Echantillon ").append(c+1).append(" : ").append(tabEch[c].toString()).append("\n");
                    }catch (NullPointerException n) {
                        actiontarget.setText("echantillon " + c + "null");
                    }
                }
                c++;
            }
            if (bf.isEmpty()) {
                actiontarget.setFill(Color.RED);
                actiontarget.setText("Affichage impossible :Aucun échantillon existant ou sélectionné");
            } else {
                ecran.setText(bf.toString());
            }
        });

        btnMoy.setOnAction(e -> {
            int c = 0;
            for(int i = 0;i<12;i++ ){
                if(lsCheckBoxes.get(i).isSelected()) {
                    c = i;
                    break;
                }
            }
            ecran.setText(String.valueOf(tabEch[c].getMoyenne()));
        });

        btnMediane.setOnAction(e -> {
            int c = 0;
            for (int i = 0; i < 12; i++) {
                if (lsCheckBoxes.get(i).isSelected()) {
                    c = i;
                    break;
                }
            }
            ecran.setText(String.valueOf(tabEch[c].getMediane()));
        });

        btnVariance.setOnAction(e -> {
            int c = 0;
            for(int i = 0;i<12;i++ ){
                if(lsCheckBoxes.get(i).isSelected()) {
                    c = i;
                    break;
                }
            }
            ecran.setText(String.valueOf(tabEch[c].getVariance()));
        });

        btnsct.setOnAction(e -> {
            int c = 0;
            for(int i = 0;i<12;i++ ){
                if(lsCheckBoxes.get(i).isSelected()) {
                    c = i;
                    break;
                }
            }
            ecran.setText(String.valueOf(tabEch[c].getSCT()));
        });

        btnEcarttype.setOnAction(e -> {
            int c = 0;
            for(int i = 0;i<12;i++ ){
                if(lsCheckBoxes.get(i).isSelected()) {
                    c = i;
                    break;
                }
            }
            ecran.setText(String.valueOf(tabEch[c].getEcarttype()));
        });

        btnMin.setOnAction(e -> {
            int c = 0;
            for(int i = 0;i<12;i++ ){
                if(lsCheckBoxes.get(i).isSelected()) {
                    c = i;
                    break;
                }
            }
            ecran.setText(String.valueOf(tabEch[c].getMinimum()));
        });

        btnMax.setOnAction(e -> {
            int c = 0;
            for(int i = 0;i<12;i++ ){
                if(lsCheckBoxes.get(i).isSelected()) {
                    c = i;
                    break;
                }
            }
            ecran.setText(String.valueOf(tabEch[c].getMaximum()));
        });

        btnTaille.setOnAction(e -> {
            int c = 0;
            for(int i = 0;i<12;i++ ){
                if(lsCheckBoxes.get(i).isSelected()) {
                    c = i;
                    break;
                }
            }
            ecran.setText(String.valueOf(tabEch[c].getTaille()));
        });

        btnfrequence.setOnAction(e -> {
            int c = 0;
            for(int i = 0;i<12;i++ ){
                if(lsCheckBoxes.get(i).isSelected()) {
                    c = i;
                    break;
                }
            }
            try {
                ecran.setText(String.valueOf(tabEch[c].getFrequence(Double.parseDouble(entree.getText()))));
            }
            catch(Exception def){
                actiontarget.setFill(Color.RED);
                actiontarget.setText("La fréquence doit être celle d'un nombre");
            }
        });

        btnquartile.setOnAction(e -> {
            int c = 0;
            for(int i = 0;i<12;i++ ){
                if(lsCheckBoxes.get(i).isSelected()) {
                    c = i;
                    break;
                }
            }
            try {
                ecran.setText(String.valueOf(tabEch[c].getQuartiles(Integer.parseInt(entree.getText()))));
            }catch(Exception def){
                actiontarget.setFill(Color.RED);
                actiontarget.setText("Entrer le quartile à calculer");
            }
        });

        btnAnova.setOnAction(e -> {
            ecran.setText("ANOVA:");
            int compt =0;
            for(CheckBox tb:lsCheckBoxes){
                if(tb.isSelected()) compt++;
            }
            Echantillon[] echAN = new Echantillon[compt];
            compt = 0;
            for(int i = 0;i<tabEch.length;i++ ){
                if(lsCheckBoxes.get(i).isSelected()) {
                    echAN[compt] = tabEch[i];
                    compt++;
                }
            }
            try {
                Anova an = new Anova(echAN);
                ecran.setText(an.decision());
            } catch (ExceptionNombreEchantillons | ExceptionTailleEchantillon en){
                ecran.setText(en.getMessage());
            }
        });

        btnchi2.setOnAction(e -> {
            ecran.setText("Chi2:");
            int compt =0;
            for(int i = 0;i<12;i++ ){
                if(lsCheckBoxes.get(i).isSelected()) compt++;
            }
            Echantillon[] echC2 = new Echantillon[compt];
            compt = 0;
            for(int i = 0;i<12;i++ ){
                if(lsCheckBoxes.get(i).isSelected()) {
                    echC2[compt] = tabEch[i];
                    compt++;
                }
            }
            try {
                Chi2 c2 =new Chi2(echC2);
                ecran.setText(c2.decision());
            } catch (IllegalArgumentException en){
                ecran.setText(en.getMessage());
            }
        });

        btnRL.setOnAction(e -> {
            ecran.setText("Régression linéaire:");
            int compt = 0;
            Echantillon[] echRL = new Echantillon[2];
            for(int i = 0;i<12;i++ ){
                if(lsCheckBoxes.get(i).isSelected()) {
                    echRL[compt] = tabEch[i];
                    compt++;
                    if(compt==2)break;
                }
            }

            try {
                if(compt!=2) ecran.setText("Sélectionner 2 échantillons");
                else {
                    RegressionLineaire RL = new RegressionLineaire(echRL[0], echRL[1]);
                    ecran.setText(RL.decision());
                }
            }
            catch (ExceptionTailleEchantillon et){
                ecran.setText(et.getMessage());
            }
        });

        btnTest.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Les tests statistiques");
            GridPane gpts = new GridPane();
            Label lblTest = new Label("Les tests statistiques");
            lblTest.setAlignment(Pos.CENTER);
            String s = """
                    Dans le domaine des statistiques, un test est une procédure permettant de rejeter ou non une hypothèse,
                     dite nulle, en fonction d’échantillons donnés.
                    Risque de se tromper : rejeter H0 alors qu’elle est vraie = en général 5%
                            Ici, nous verrons que les tests paramétriques : On connait la loi que suivent les données.
                    Pour cela il faut :
                    -	Formuler l’hypothèse nulle H0, et l’hypothèse alternative H1 : H0 correspond à un non-effet de l’expérience
                     (par exemple l’égalités de moyennes ou de variance), H1 est son contraire\s
                    (soit une différence-> test bilatéral, soit supérieur ou inférieur ->unilatéral.
                    -	 Récupérer des données
                    -	Choisir un type de test
                    -	Analyser les résultats : récup d’une valeur, comparaison avec les tables""";

            TextArea taTest = new TextArea(s);
            taTest.setEditable(false);
            taTest.setMinSize(700,280);
            gpts.add(lblTest,0,0);
            gpts.add(taTest,0,1);
            Scene scene12 = new Scene(gpts,750,300);
            stage.setScene(scene12);
            stage.show();
        });
        btnCRL.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Les tests statistiques");
            GridPane gpCRL = new GridPane();
            Label lblCRL = new Label("Test de régression linéaire");
            lblCRL.setAlignment(Pos.CENTER);
            String s = """
                    Ce test permet de tester un éventuel effet d’une variable explicative x sur une variable expliquée y.
                    H0 : x n’influence pas y
                    Notation des indices de degré de liberté : a=nombre d’échantillons (=2) ; n = la taille des échantillons
                    Etapes / Détail calcul :
                    Calculs des moyennes, variances des deux échantillons, covariance, sct(x)
                    SCT : ∑(Xi-Xmoy)²
                    Calcul de l’équation de la droite des moindres carrés y=B1*x+B0 (obtenue à part des données empirique de x et de y)
                    B1 : cov(x,y)/var(x)
                    B0 : moyenne(y) – B1*moyenne(x)
                    Calcul de la corrélation entre le modèle et les valeurs empiriques de y, par le coefficient de corrélation linéaire r
                    R = r² = pourcentage de variance expliquée par le modèle
                    Conditions : si R<0.8 : on ne peut pas tester ni rejeter H0.
                    r : cov/(√ (var(x)*var(y))\s
                    R=r²
                    Calcul de l’indice de Fischer F :
                    SCM : R * SCT(x) ou ∑(moyenne(i)-moyenne tt) ²
                    SCE : SCT(x)-SCM ou ∑ (Yi -Yi estimé) ²
                    F : (SCM*taille-2) /SCE
                    Puis récupération du quantile théorique en fonction des degrés de liberté d’une loi de Fischer : a-1, n -2.
                    Comparaison de F et du quantile théorique (1, n-2) : Si  F > au quantile : On rejette H0 : x a un effet sur y, sinon one ne rejette pas H0.
                    """;
            TextArea taCRL = new TextArea(s);
            taCRL.setEditable(false);
            taCRL.setMinSize(730,380);
            gpCRL.add(lblCRL, 0,0);
            gpCRL.add(taCRL, 0, 1);
            Scene scene13 = new Scene(gpCRL,750,400);
            stage.setScene(scene13);
            stage.show();
        });
        btnCAnova.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Les tests statistiques");
            GridPane gpCA = new GridPane();
            Label lblCA = new Label("Test d'anova à un facteur");
            lblCA.setAlignment(Pos.CENTER);
            String s = """
                    Test d’anova à un facteur : Quoi, pourquoi, H0, étapes, conditions
                    Ce test permet de tester l’égalité des moyennes d’un groupe d’échantillons.
                    H0 : égalité des moyennes, H1 : non. Il s’agit donc d’un test bilatéral\s
                    Etapes :
                    Calcul variance et moyenne de chaque échantillon
                    Notation des indices de degré de liberté : a=nombre d’échantillons ;
                    t = taille des échantillons ; n = nombre total de valeurs : a*t
                    Détail calcul :\s
                    Calcul de l’indice de Ficher F :
                    SCE : somme des variances
                    SCM : t * ∑(moyenne(i)-moyenne tt) ²
                    F : (SCM /(a-1))/(SCE/(n-a))
                    Comparaison de F et du quantile théorique(a-1,n-a) :\s
                    Si F est < au quantile : On ne rejette pas H0 :  égalité des moyennes
                    """;
            TextArea taTest = new TextArea(s);
            taTest.setEditable(false);
            taTest.setMinSize(700,280);
            gpCA.add(lblCA, 0,0);
            gpCA.add(taTest, 0, 1);
            Scene scene14 = new Scene(gpCA,750,300);
            stage.setScene(scene14);
            stage.show();
        });
        btnCChi2.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Les tests statistiques");
            GridPane gpCC = new GridPane();
            Label lblCC = new Label("Test du Chi2 d'indépendance");
            lblCC.setAlignment(Pos.CENTER);
            String s = """
                    Ce test permet de vérifier l’indépendance entre deux variables X et Y,
                     c’est-à-dire  de vérifier qu’il n’existe pas de lien statistique entre ces deux variables.
                    X représente une catégorie de la population, et Y un critère en particulier.
                    H0 : pas de lien, H1 : lien. Entre d’autres termes, sous H0, la catégorie n’influence pas le critère.
                    Il s’agit donc d’un test bilatéral.
                    Etapes :
                    Entrée les valeurs empiriques dans une table de contingence
                    Calcul des valeurs théoriques s’il y avait indépendance :
                    Calcul des valeurs théoriques : Pour chaque ligne de chaque colonne : total ligne * total colonne / somme total
                    Conditions : - Les valeurs théoriques doivent toutes être >=1,
                                          - 80% des valeurs théoriques doivent être >=5.

                    Puis calcul de la distance avec les valeurs empiriques :
                    Calcul de la distance d = ∑ (i = 0 à n) (val empirique(i) – val théorique(i)) ²/ val théorique(i)
                    La distance suit asymptotiquement une loi de chi2 à (i-1)*(j-1) degré de liberté,
                     où i = le nombre échantillons (2) et j  le nombre de valeurs par échantillons.

                    Comparaison de d et de la valeur théorique de la table du chi2 à (1, n-1) :\s
                    Si d <= valeur théorique chi2 : On rejette H0 : X et Y ne sont pas indépendants.
                    """;
            TextArea taCC = new TextArea(s);
            taCC.setEditable(false);
            taCC.setMinSize(700,380);
            gpCC.add(lblCC, 0,0);
            gpCC.add(taCC, 0, 1);
            Scene scene15 = new Scene(gpCC,750,400);
            stage.setScene(scene15);
            stage.show();
        });
        btn4.setOnAction(e -> {
            Stage stage = new Stage();
            Stage stage2 = new Stage();
            int compteur=0;
            Echantillon vi  = tabEch[0];
            int[] compt= new int[12];
            for(CheckBox tb:lsCheckBoxes){
                if(tb.isSelected()) compt[compteur]=1;
                compteur++;
            }
            compteur = 0;
            stage.setTitle("Bar Chart Sample");
            stage2.setTitle("Regression linéaire");
            final NumberAxis xAxis = new NumberAxis();
            final NumberAxis yAxis = new NumberAxis();
            final NumberAxis xAxis1 = new NumberAxis();
            final NumberAxis yAxis2 = new NumberAxis();
            yAxis.setLabel("Y");
            xAxis.setLabel("X");
            yAxis2.setLabel("Y");
            xAxis1.setLabel("X");
            final ScatterChart<Number,Number> bc =
                    new ScatterChart<>(xAxis,yAxis);
            bc.setTitle("Nuage de points");
            final LineChart<Number,Number> lc =
                    new LineChart<>(xAxis1,yAxis2);
            for(int i=0;i<compt.length;i++) {
                if(compt[i]!=0) {
                    compteur++;
                    Echantillon dl = tabEch[i];
                    XYChart.Series<Number, Number> series = new XYChart.Series<>();
                    for (int k = 0; k < dl.getTaille(); k++) {
                        series.getData().add(new XYChart.Data<>(k, dl.donnees.get(k)));
                    }
                    series.setName("Echantillon n°" + i);
                    if(compteur==1){
                        vi=dl;
                    }
                    if(compteur==2){
                        try {
                            RegressionLineaire RL = new RegressionLineaire(vi, dl);
                            Echantillon rl = new Echantillon(RL.getYjust());
                            LineChart.Series<Number, Number> series1 = new LineChart.Series<Number, Number>();
                            for(int k = 0; k< rl.getTaille(); k++){
                                series1.getData().add(new LineChart.Data<>(k, rl.donnees.get(k)));
                            }
                            lc.getData().add(series1);
                        } catch (ExceptionTailleEchantillon ex) {
                            ecran.setText("taille des échantillons différentes");
                        }
                    }
                    series.getData().add(new XYChart.Data<>(dl.getTaille() + 2, dl.getMoyenne()));
                    series.getData().add(new XYChart.Data<>(dl.getTaille() + 3, dl.getMediane()));
                    bc.getData().add(series);
                }
            }
            Scene scene16 = new Scene(bc,800,600);
            Scene scene1  = new Scene(lc,800,600);
            stage.setScene(scene16);
            stage2.setScene(scene1);
            stage.show();
            stage2.show();

        });


        btnSuppr.setOnAction(
                e -> {

                    ArrayList<Echantillon> lsEchantillons = new ArrayList<>(Arrays.asList(tabEch));
                    int i = 0;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Echantillon supprimer n°: {");
                    for(CheckBox tb:lsCheckBoxes){
                        if(tb.isSelected() && tb.isVisible()) { // limitation effet de bord sur cbx invisible
                            lsEchantillons.remove(i);
                            sb.append(i).append(",");
                            compteur--;
                            i++;
                        }
                    }
                    sb.replace(sb.length() - 1, sb.length(), "}");

                    lsCheckBoxes.forEach(c ->
                    {
                        c.setVisible(false);
                        c.setSelected(false);
                    });


                    for (int j = 0; j < lsEchantillons.size();j++) {
                        tabEch[j] = lsEchantillons.get(j);
                        if (tabEch[j] != null) {
                            lsCheckBoxes.get(j).setVisible(true);
                        }
                    }

                    actiontarget.setFill(Color.ORANGE);
                    actiontarget.setText(sb.toString());
                }
        );
        //primaryStage.sizeToScene();
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}