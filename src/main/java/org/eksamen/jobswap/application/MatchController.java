package org.eksamen.jobswap.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.eksamen.jobswap.domain.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class MatchController {
    List<Match> matches;

    @FXML
    private GridPane matchGrid;

    public void setMatches(List<Match> matches) {
        this.matches = matches;
        showMatches();
    }

    public void showMatches() {
        if (matches != null) {

            int column = 0;
            int row = 0;

            for (int i = 0; i < matches.size(); i++) {
                Match match = matches.get(i);

                GridPane matchContainer = new GridPane();
                matchContainer.getStyleClass().add("matchWrapper");
                matchContainer.setGridLinesVisible(true);
                matchContainer.setPrefWidth(200);

                matchContainer.setOnMouseClicked(event -> {
                    try {
                        switchToMatchDetailsMenu(event, match, matches);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                for (int j = 0; j < 2; j++) {
                    ColumnConstraints cc = new ColumnConstraints();
                    cc.setPercentWidth(50);  // each column takes 50% width
                    cc.setHgrow(Priority.ALWAYS);
                    matchContainer.getColumnConstraints().add(cc);
                }

                for (int j = 0; j < 2; j++) {
                    RowConstraints rc = new RowConstraints();
                    if (j == 0) {
                        rc.setPercentHeight(25);  // each column takes 50% width
                    } else {
                        rc.setPercentHeight(75);  // each column takes 50% width
                    }

                    rc.setVgrow(Priority.ALWAYS);
                    matchContainer.getRowConstraints().add(rc);
                }

                matchGrid.add(matchContainer, column, row);

                StackPane employee1Name = new StackPane();
                StackPane employee2Name = new StackPane();
                matchContainer.add(employee1Name, 0, 0);
                matchContainer.add(employee2Name, 1, 0);

                Label employee1NameLabel = new Label(match.getJob1().getEmployee().getFirstName() + " " + match.getJob1().getEmployee().getLastName());
                employee1NameLabel.getStyleClass().add("employeeName");
                employee1Name.getChildren().add(employee1NameLabel);
                Label employee2NameLabel = new Label(match.getJob2().getEmployee().getFirstName() + " " + match.getJob2().getEmployee().getLastName());
                employee2NameLabel.getStyleClass().add("employeeName");
                employee2Name.getChildren().add(employee2NameLabel);

                VBox employee1Details = new VBox();
                employee1Details.getStyleClass().add("employeeDetails");
                VBox employee2Details = new VBox();
                employee2Details.getStyleClass().add("employeeDetails");
                matchContainer.add(employee1Details, 0, 1);
                matchContainer.add(employee2Details, 1, 1);

                Label employee1WorkplaceLabel = new Label("Ny arbejdsplads: " + match.getJob2().getWorkplace().getWorkAddress() + ", " + match.getJob2().getWorkplace().getWorkAddressZip().getZipCode());
                Label employee1DetailsLabel = new Label("Ny transporttid: " + match.getJob1NewTransportDetails().getTravelTime() + " minutter");
                Label employee1TimeSavedLabel = new Label("Tid sparet om dagen: " + abs((match.getJob1OldTransportDetails().getTravelTime() - match.getJob1NewTransportDetails().getTravelTime()) * 2) + " minutter");
                Label employee1SalaryLabel = new Label("Lønændring: " + match.getJob1SalaryDifference() + " kr");
                employee1Details.getChildren().add(employee1WorkplaceLabel);
                employee1Details.getChildren().add(employee1DetailsLabel);
                employee1Details.getChildren().add(employee1TimeSavedLabel);
                employee1Details.getChildren().add(employee1SalaryLabel);

                Label employee2WorkplaceLabel = new Label("Ny arbejdsplads: " + match.getJob1().getWorkplace().getWorkAddress() + ", " + match.getJob1().getWorkplace().getWorkAddressZip().getZipCode());
                Label employee2DetailsLabel = new Label("Ny transporttid: " + match.getJob2NewTransportDetails().getTravelTime() + " minutter");
                Label employee2TimeSavedLabel = new Label("Tid sparet om dagen: " + abs((match.getJob2OldTransportDetails().getTravelTime() - match.getJob2NewTransportDetails().getTravelTime()) * 2) + " minutter");
                Label employee2SalaryLabel = new Label("Lønændring: " + match.getJob2SalaryDifference() + " kr");
                employee2Details.getChildren().add(employee2WorkplaceLabel);
                employee2Details.getChildren().add(employee2DetailsLabel);
                employee2Details.getChildren().add(employee2TimeSavedLabel);
                employee2Details.getChildren().add(employee2SalaryLabel);

                column++;
                if (column >= 2) { // GridPane er 2x3
                    column = 0;
                    row++;
                }
            }
        } else {
            System.out.println("Ingen matches fundet");
        }
    }

    public void switchToMatchDetailsMenu(MouseEvent event, Match match, List<Match> matches) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/eksamen/jobswap/ui/matchdetails.fxml"));
        Parent root = loader.load();

        MatchDetailsController matchDetailsController = loader.getController();
        matchDetailsController.setMatch(match, matches);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/org/eksamen/jobswap/ui/match.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }


}
