module BlueKeeper {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.xml;
    requires java.sql;

    exports lnandobr.bluekeeper.application to javafx.graphics;
    exports lnandobr.bluekeeper.controller to javafx.fxml;
    exports lnandobr.bluekeeper.util to javafx.fxml;

    opens lnandobr.bluekeeper.controller to javafx.fxml;
    opens lnandobr.bluekeeper.util to javafx.fxml;
    opens lnandobr.bluekeeper.model to javafx.base;
}