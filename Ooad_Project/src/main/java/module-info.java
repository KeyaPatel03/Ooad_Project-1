module com.garden {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires org.apache.logging.log4j;
    requires org.json;
    requires javafx.graphics;


    opens com.garden to javafx.fxml;
    exports com.garden;
}