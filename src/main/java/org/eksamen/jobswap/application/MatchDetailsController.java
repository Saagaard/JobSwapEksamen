package org.eksamen.jobswap.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.eksamen.jobswap.domain.Match;

import java.io.IOException;
import java.util.List;

import static java.lang.Math.abs;

public class MatchDetailsController {

    Match match;
    List<Match> matchList;

    @FXML
    Label job1Name, job2Name, job1SalaryDifference, job2SalaryDifference, job1Workplace, job2Workplace, job1TransportTime, job2TransportTime, job1TimeSaved, job2TimeSaved, job1EmployeeID, job2EmployeeID,
    job1Address, job2Address, job1Email, job2Email, job1Title, job2Title, job1Seniority, job2Seniority, job1Salary, job2Salary;

    @FXML
    Button backButton;

    public void setMatch(Match match, List<Match> matches) {
        this.match = match;
        this.matchList = matches;
        showMatch();
    }

    public void showMatch() {
        job1Name.setText(match.getJob1().getEmployee().getFirstName() + " " + match.getJob1().getEmployee().getLastName());
        job1SalaryDifference.setText(match.getJob1SalaryDifference() + " kr");
        if (match.getJob1SalaryDifference() >= 0 ) {
            job1SalaryDifference.getStyleClass().add("green");
        } else {
            job1SalaryDifference.getStyleClass().add("red");
        }
        job1Workplace.setText(match.getJob1().getWorkplace().getWorkAddress() + ", " + match.getJob1().getWorkplace().getWorkAddressZip().getZipCode() + " " + match.getJob1().getWorkplace().getWorkAddressZip().getCityName());
        job1TransportTime.setText(match.getJob1NewTransportDetails().getTravelTime() + " minutter");
        job1TimeSaved.setText(abs((match.getJob1OldTransportDetails().getTravelTime() - match.getJob1NewTransportDetails().getTravelTime()) * 2) + " minutter");
        job1EmployeeID.setText(match.getJob1().getEmployee().getEmployeeID() + "");
        job1Address.setText(match.getJob1().getEmployee().getHomeAddress());
        job1Email.setText(match.getJob1().getEmployee().getEmail());
        job1Title.setText(match.getJob1().getJobTitle());
        job1Seniority.setText(match.getJob1().calculateSeniority() / 12 + " år, " + match.getJob1().calculateSeniority() % 12 + " måneder");
        job1Salary.setText(match.getJob1().getMonthlySalary() + " kr");

        job2Name.setText(match.getJob2().getEmployee().getFirstName() + " " + match.getJob2().getEmployee().getLastName());
        job2SalaryDifference.setText(match.getJob2SalaryDifference() + " kr");
        if (match.getJob2SalaryDifference() >= 0 ) {
            job2SalaryDifference.getStyleClass().add("green");
        } else {
            job2SalaryDifference.getStyleClass().add("red");
        }
        job2Workplace.setText(match.getJob2().getWorkplace().getWorkAddress() + ", " + match.getJob2().getWorkplace().getWorkAddressZip().getZipCode() + " " + match.getJob2().getWorkplace().getWorkAddressZip().getCityName());
        job2TransportTime.setText(match.getJob2NewTransportDetails().getTravelTime() + " minutter");
        job2TimeSaved.setText(abs((match.getJob2OldTransportDetails().getTravelTime() - match.getJob2NewTransportDetails().getTravelTime()) * 2) + " minutter");
        job2EmployeeID.setText(match.getJob2().getEmployee().getEmployeeID() + "");
        job2Address.setText(match.getJob2().getEmployee().getHomeAddress());
        job2Email.setText(match.getJob2().getEmployee().getEmail());
        job2Title.setText(match.getJob2().getJobTitle());
        job2Seniority.setText(match.getJob2().calculateSeniority() / 12 + " år, " + match.getJob2().calculateSeniority() % 12 + " måneder");
        job2Salary.setText(match.getJob2().getMonthlySalary() + " kr");
    }

    public void switchToMatches(ActionEvent event) throws IOException {
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
