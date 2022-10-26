module com.tag_excel_database.tag_excel_database {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens com.tag_excel_database to javafx.fxml;
    exports com.tag_excel_database;
    exports com.tag_excel_database.controller;
    opens com.tag_excel_database.controller to javafx.fxml;
}