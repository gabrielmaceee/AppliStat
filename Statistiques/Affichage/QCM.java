package Statistiques.Affichage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * class contenant les qcm de l'interface graphique
 */
public class QCM extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * note de l'utilisateur au qcm
     */
    public int compteur = 0;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("QCM");
        BorderPane gp = new BorderPane();
        Scene scene = new Scene(gp);
        primaryStage.setScene(scene);
        gp.setMinSize(400,400);
       // gp.setMaxSize(400,300);

        Label lblDebut = new Label("    Testez-vous");
        TextArea taDebut = new TextArea("Nous vous proposons de vous tester à l'aide d'un qcm:\n" +
                "Les questions portent uniquement sur les 4 fiches présentent sur cette interface\n" +
                "Il peut il y avoir une ou plusieurs bonne réponse par question :\n" +
                "Chaque bonne réponse vaut un point, tandis qu'un oubli vaut 0\n" +
                "et qu'une mauvaise réponse vaut -1");
        taDebut.setMaxHeight(200);
        taDebut.setEditable(false);
        Button btnDebut = new Button("Commencer");
        gp.setCenter(taDebut);
        gp.setTop(lblDebut);
        gp.setBottom(btnDebut);

        TextArea ta1 = new TextArea();
        ta1.setMaxSize(170,50);
        ta1.setEditable(false);
        Button btnVerif1 = new Button("Vérifier");
        GridPane gp1 = new GridPane();
        gp1.setVgap(10);
        gp1.setHgap(10);
        Button btnS1 = new Button("Suivant");
        btnS1.setVisible(false);
        Label lblq1 = new Label("Question 1 : Le risque de se tromper est :");
        CheckBox q1a = new CheckBox("a) Le fait de rejeter H0 alors qu’elle est vraie ");
        CheckBox q1b = new CheckBox("b) Le fait de ne pas rejeter H0 alors qu’elle est fausse");
        CheckBox q1c = new CheckBox("c) En général : 0,05 %");
        CheckBox q1d = new CheckBox("d) En général : 5 %");
        CheckBox q1e = new CheckBox("e) En général : 95 %");
        gp1.add(lblq1,0,0);
        gp1.add(q1a,0,1);
        gp1.add(q1b,0,2);
        gp1.add(q1c,0,3);
        gp1.add(q1d,0,4);
        gp1.add(q1e,0,5);
        BorderPane bpBtn = new BorderPane();
        bpBtn.setRight(btnS1);
        bpBtn.setCenter(ta1);
        bpBtn.setLeft(btnVerif1);
        gp1.setAlignment(Pos.CENTER);

        Button btnVerif2 = new Button("Vérifier");
        GridPane gp2 = new GridPane();
        gp2.setVgap(10);
        gp2.setHgap(10);
        Button btnS2 = new Button("Suivant");
        btnS2.setVisible(false);
        Label lblq2 = new Label("Question 2 : Une hypothèse est :");
        CheckBox q2a = new CheckBox("a) Bilatérale si H1 : X1<X2");
        CheckBox q2b = new CheckBox("b) Bilatérale si H1 : X1 différent de X2 ");
        CheckBox q2c = new CheckBox("c) Unilatérale si H1 : X1>=X2 ");
        CheckBox q2d = new CheckBox("d) Unilatérale si H1 : X1<X2");
        CheckBox q2e = new CheckBox("e) Unilatérale si H1 : X1 différent X2 ");
        gp2.add(lblq2,0,0);
        gp2.add(q2a,0,1);
        gp2.add(q2b,0,2);
        gp2.add(q2c,0,3);
        gp2.add(q2d,0,4);
        gp2.add(q2e,0,5);
        gp2.setAlignment(Pos.CENTER);

        Button btnVerif3 = new Button("Vérifier");
        GridPane gp3 = new GridPane();
        gp3.setVgap(10);
        gp3.setHgap(10);
        Button btnS3 = new Button("Suivant");
        btnS3.setVisible(false);
        Label lblq3 = new Label("Question 3 : Dans un test de régression linéaire, une raison de rejeter H0 est :");
        CheckBox q3a = new CheckBox("a) R<0.8");
        CheckBox q3b = new CheckBox("b) R>0.8");
        CheckBox q3c = new CheckBox("c) F<quantile théorique ");
        CheckBox q3d = new CheckBox("d) F>quantile théorique ");
        gp3.add(lblq3,0,0);
        gp3.add(q3a,0,1);
        gp3.add(q3b,0,2);
        gp3.add(q3c,0,3);
        gp3.add(q3d,0,4);
        gp3.setAlignment(Pos.CENTER);

        Button btnVerif4 = new Button("Vérifier");
        GridPane gp4 = new GridPane();
        gp4.setVgap(10);
        gp4.setHgap(10);
        Button btnS4 = new Button("Suivant");
        btnS4.setVisible(false);
        Label lblq4 = new Label("Question 4 : Pour trouver le quantile théorique d'un test d'anova :");
        CheckBox q4a = new CheckBox("a) Le premier degré de liberté est toujours a-1");
        CheckBox q4b = new CheckBox("b) Le premier degré de liberté est toujours 1");
        CheckBox q4c = new CheckBox("c) Le premier degré de liberté est toujours 2");
        CheckBox q4d = new CheckBox("d) Le deuxième degré de liberté est toujours n-1");
        CheckBox q4e = new CheckBox("e) Le deuxième degré de liberté est toujours n-a");
        gp4.add(lblq4,0,0);
        gp4.add(q4a,0,1);
        gp4.add(q4b,0,2);
        gp4.add(q4c,0,3);
        gp4.add(q4d,0,4);
        gp4.add(q4e,0,5);
        gp4.setAlignment(Pos.CENTER);

        Button btnVerif5 = new Button("Vérifier");
        GridPane gp5 = new GridPane();
        gp5.setVgap(10);
        gp5.setHgap(10);
        Button btnS5 = new Button("Home");
        btnS5.setVisible(false);
        Label lblq5 = new Label("Question 5 : Dans un test du Chi2 d'indépendance,\n"
        +"les valeurs théoriques ne sont pas acceptées si:");
        CheckBox q5a = new CheckBox("a) 80% d'entre elles sont => 5");
        CheckBox q5b = new CheckBox("b) 80% d'entre elles sont < 5");
        CheckBox q5c = new CheckBox("c) Elles sont toutes entre 1 et 5");
        CheckBox q5d = new CheckBox("d) Elles sont toutes toutes >=1");
        CheckBox q5e = new CheckBox("e) Elles sont toutes < 1");
        gp5.add(lblq5,0,0);
        gp5.add(q5a,0,1);
        gp5.add(q5b,0,2);
        gp5.add(q5c,0,3);
        gp5.add(q5d,0,4);
        gp5.add(q5e,0,5);
        gp5.setAlignment(Pos.CENTER);

        btnDebut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                bpBtn.setRight(btnS1);
                bpBtn.setCenter(ta1);
                bpBtn.setLeft(btnVerif1);
                gp.setCenter(gp1);
                btnVerif1.setVisible(true);
                gp.setBottom(bpBtn);
            }});
        btnVerif1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                btnS1.setVisible(true);
                btnVerif1.setVisible(false);
                if(q1a.isSelected()==true) compteur++;
                if(q1b.isSelected()==true)compteur--;
                if(q1c.isSelected()==true)compteur--;
                if(q1d.isSelected()==true)compteur++;
                if(q1e.isSelected()==true)compteur--;
                ta1.setText("Réponse : a,d"+"\nScore depuis le début : "+compteur);
            }});
        btnS1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
               ta1.setText("");
               gp.setCenter(gp2);
               bpBtn.setLeft(btnVerif2);
               btnVerif2.setVisible(true);
               bpBtn.setRight(btnS2);
               btnS1.setVisible(false);
            }});
        btnVerif2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                btnS2.setVisible(true);
                btnVerif2.setVisible(false);
                if(q2a.isSelected()==true) compteur--;
                if(q2b.isSelected()==true)compteur++;
                if(q2c.isSelected()==true)compteur++;
                if(q2d.isSelected()==true)compteur++;
                if(q2e.isSelected()==true)compteur--;
                ta1.setText("Réponse : b,c,d"+"\nScore depuis le début : "+compteur);
            }});
        btnS2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ta1.setText("");
                gp.setCenter(gp3);
                bpBtn.setLeft(btnVerif3);
                bpBtn.setRight(btnS3);
                btnVerif3.setVisible(true);
                btnS2.setVisible(false);
            }});
        btnVerif3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                btnS3.setVisible(true);
                btnVerif3.setVisible(false);
                if(q3a.isSelected()==true) compteur--;
                if(q3b.isSelected()==true)compteur--;
                if(q3c.isSelected()==true)compteur--;
                if(q3d.isSelected()==true)compteur++;
                ta1.setText("Réponse : d"+"\nScore depuis le début : "+compteur);
            }});
        btnS3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ta1.setText("");
                gp.setCenter(gp4);
                bpBtn.setLeft(btnVerif4);
                bpBtn.setRight(btnS4);
                btnVerif4.setVisible(true);
                btnS3.setVisible(false);
            }});

        btnVerif4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                btnS4.setVisible(true);
                btnVerif4.setVisible(false);
                if(q4a.isSelected()==true) compteur++;
                if(q4b.isSelected()==true)compteur--;
                if(q4c.isSelected()==true)compteur--;
                if(q4d.isSelected()==true)compteur--;
                if(q4e.isSelected()==true)compteur++;
                ta1.setText("Réponse : a,e"+"\nScore depuis le début : "+compteur);
            }});
        btnS4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ta1.setText("");
                gp.setCenter(gp5);
                bpBtn.setLeft(btnVerif5);
                bpBtn.setRight(btnS5);
                btnVerif5.setVisible(true);
                btnS4.setVisible(false);
            }});
        btnVerif5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                btnS5.setVisible(true);
                btnVerif5.setVisible(false);
                if(q5a.isSelected()==true) compteur--;
                if(q5b.isSelected()==true)compteur++;
                if(q5c.isSelected()==true)compteur--;
                if(q5d.isSelected()==true)compteur--;
                if(q5e.isSelected()==true)compteur++;
                ta1.setText("Réponse : b,e"+"\nScore depuis le début : "+compteur);
            }});
        btnS5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ta1.setText("");
                gp.setCenter(taDebut);
                gp.setBottom(btnDebut);
                btnS5.setVisible(false);
                compteur=0;
            }});

        primaryStage.show();
    }
}
