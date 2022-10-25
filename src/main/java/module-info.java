module com.tag_excel_database.tag_excel_database {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.tag_excel_database to javafx.fxml;
    exports com.tag_excel_database;
}