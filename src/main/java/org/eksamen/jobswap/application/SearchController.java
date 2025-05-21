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
import org.eksamen.jobswap.domain.Match;

import java.io.IOException;
import java.util.List;

public class SearchController {
    @FXML
    private TextField jobTitleField, transportTimeField, salaryField, minimumYearField, minimumMonthField, maxYearField, maxMonthField;

    Criteria criteria;

    public void generateCriteria() {

        String jobTitle =  jobTitleField.getText();
        int transportTime = Integer.parseInt(transportTimeField.getText());
        float salary = Float.parseFloat(salaryField.getText());

        int minimumYear = Integer.parseInt(minimumYearField.getText());
        int minimumMonth = Integer.parseInt(minimumMonthField.getText());
        int minimumSeniority = minimumYear * 12 + minimumMonth;

        int maxYear = Integer.parseInt(maxYearField.getText());
        int maxMonth = Integer.parseInt(maxMonthField.getText());
        int maxSeniority = maxYear * 12 + maxMonth;

        this.criteria = new Criteria(jobTitle, transportTime, salary, minimumSeniority, maxSeniority);
        searchMatches(this.criteria);

    }

    public List<Match> searchMatches(Criteria criteria) {
        System.out.println("Jobtitel: " + criteria.getJobTitle());
        System.out.println("Minimum anciennitet: " + criteria.getMinimumSeniority());
        System.out.println("Max anciennitet: " + criteria.getMaxSeniority());
        System.out.println("Lønafvigelse: " + criteria.getSalaryDifference());
        System.out.println("Ny transporttid: " + criteria.getTransportTime());

        //        MatchController matchController = loader.getController();
        //menuController.setMatches(List<Match>);

        return null;
    }


    public void switchToMatchMenu(ActionEvent event) throws IOException {



//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/eksamen/jobswap/ui/match.fxml"));
//        Parent root = loader.load();
//
//
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("/org/eksamen/jobswap/ui/match.css").toExternalForm());
//        stage.setScene(scene);
//        stage.show();
    }
}
