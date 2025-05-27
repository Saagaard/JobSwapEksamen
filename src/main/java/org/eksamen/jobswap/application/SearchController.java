package org.eksamen.jobswap.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.eksamen.jobswap.domain.Criteria;
import org.eksamen.jobswap.domain.Match;
import org.eksamen.jobswap.domain.MatchSearch;

import java.util.List;

import static org.eksamen.jobswap.businessServices.IsDigits.isDigits;

public class SearchController {
    @FXML
    private TextField jobTitleField, transportTimeField, salaryField, minimumYearField, minimumMonthField, maxYearField, maxMonthField;

    @FXML
    private Label transportError, salaryError, seniorityError1, seniorityError2;

    Criteria criteria;

    public void initialize() {
        transportError.setText("");
        salaryError.setText("");
        seniorityError1.setText("");
        seniorityError2.setText("");

    }

    public void validateFields(ActionEvent event) throws Exception {
        boolean validFields = true;

        // Transporttid
        if (transportTimeField.getText().isEmpty()) {
            transportError.setText("Transporttid skal udfyldes");
            validFields = false;
        } else if (!isDigits(transportTimeField.getText())) {
            transportError.setText("Transporttid skal være et tal");
            transportTimeField.setText("");
            validFields = false;
        } else {
            int transportInput = Integer.parseInt(transportTimeField.getText());
            if (transportInput < 0) {
                transportError.setText("Transporttid skal være mindst 0 minutter");
                validFields = false;
            } else {
                transportError.setText("");
            }
        }

        // Lønafvigelse
        if (salaryField.getText().isEmpty()) {
            salaryError.setText("Lønafvigelse skal udfyldes");
            validFields = false;
        } else if (!isDigits(salaryField.getText())) {
            salaryError.setText("Lønafvigelse skal være et tal");
            salaryField.setText("");
            validFields = false;
        } else {
            int salaryInput = Integer.parseInt(salaryField.getText());
            if (salaryInput < 0 || salaryInput > 15) {
                salaryError.setText("Lønafvigelse skal være mellem 0-15%");
                validFields = false;
            } else {
                salaryError.setText("");
            }
        }

        // Minimum anciennitet
        int minimumSeniorityInput = 0;

        if (minimumYearField.getText().isEmpty() || minimumMonthField.getText().isEmpty()) {
            seniorityError1.setText("Anciennitet skal udfyldes");
            validFields = false;
        } else if (!isDigits(minimumYearField.getText()) || !isDigits(minimumMonthField.getText())) {
            minimumYearField.setText("");
            minimumMonthField.setText("");
            seniorityError1.setText("Anciennitet skal være et tal");
            validFields = false;
        } else  {
            minimumSeniorityInput = (Integer.parseInt(minimumYearField.getText()) * 12 + Integer.parseInt(minimumMonthField.getText()));
            if (minimumSeniorityInput < 6) {
                seniorityError1.setText("Anciennitet skal være mindst 6 måneder");
                validFields = false;
            } else {
                seniorityError1.setText("");
            }
        }


        // Max anciennitet
        int maxSeniorityInput = 0;

        if (maxYearField.getText().isEmpty() || maxMonthField.getText().isEmpty()) {
            seniorityError2.setText("Anciennitet skal udfyldes");
            validFields = false;
        } else if (!isDigits(maxYearField.getText()) || !isDigits(maxMonthField.getText())) {
            maxYearField.setText("");
            maxMonthField.setText("");
            seniorityError2.setText("Anciennitet skal være et tal");
            validFields = false;
        } else  {
            maxSeniorityInput = (Integer.parseInt(maxYearField.getText()) * 12 + Integer.parseInt(maxMonthField.getText()));
            if (maxSeniorityInput < minimumSeniorityInput) {
                seniorityError2.setText("Max anciennitet skal være over minimum");
                validFields = false;
            } else {
                seniorityError2.setText("");
            }
        }


        if (validFields) {
            generateCriteria(event);
        }

    }

    public void generateCriteria(ActionEvent event) throws Exception {

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
        searchMatches(this.criteria, event);

    }

    public void searchMatches(Criteria criteria, ActionEvent event) throws Exception {
        System.out.println("Jobtitel: " + criteria.getJobTitle());
        System.out.println("Minimum anciennitet: " + criteria.getMinimumSeniority());
        System.out.println("Max anciennitet: " + criteria.getMaxSeniority());
        System.out.println("Lønafvigelse: " + criteria.getSalaryDifference());
        System.out.println("Ny transporttid: " + criteria.getTransportTime());
        MatchSearch matchSearch = new MatchSearch();
        List <Match> matchList = matchSearch.createMatches(criteria);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/eksamen/jobswap/ui/match.fxml"));
        Parent root = loader.load();

        MatchController matchController = loader.getController();
        matchController.setMatches(matchList);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/org/eksamen/jobswap/ui/match.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }


}
