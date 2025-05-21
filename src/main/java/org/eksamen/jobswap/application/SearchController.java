package org.eksamen.jobswap.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.eksamen.jobswap.domain.Criteria;
import org.eksamen.jobswap.domain.Employee;
import org.eksamen.jobswap.persistence.EmployeeDAOImpl;

import java.io.IOException;
import java.util.List;

public class SearchController {
    @FXML
    private TextField jobTitleField, transportTimeField, salaryField, minimumYearField, minimumMonthField, maxYearField, maxMonthField;

    public void generateCriterias() {



    }

    public void switchToMatchMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/eksamen/jobswap/ui/match.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/org/eksamen/jobswap/ui/match.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
