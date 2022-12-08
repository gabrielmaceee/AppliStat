module fr.statface.guistatface {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jfr;


    opens fr.statface.guistatface to javafx.fxml;
    exports fr.statface.guistatface;
}