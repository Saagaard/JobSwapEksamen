package org.eksamen.jobswap.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.eksamen.jobswap.domain.Employee;
import org.eksamen.jobswap.persistence.EmployeeDAOImpl;

import java.io.IOException;
import java.util.List;

public class SearchController {

    public void initialize() throws Exception {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        List<Employee> testEmployeeList = employeeDAO.readAll();

        for (Employee employee : testEmployeeList) {
            System.out.println(employee.getFirstName());
        }
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
