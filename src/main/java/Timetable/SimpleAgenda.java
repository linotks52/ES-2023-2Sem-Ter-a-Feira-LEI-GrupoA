package Timetable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.HBox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;
import jfxtras.scene.control.agenda.Agenda.LocalDateTimeRange;
import javafx.scene.control.Button;

public class SimpleAgenda extends Application {

    @Override
    public void start(Stage primaryStage) {
        // create agenda
        Agenda agenda = new Agenda();
        LocalDateTime currentdate = LocalDateTime.now();
        
        Callback<Agenda.LocalDateTimeRange, Void> callback = new Callback<Agenda.LocalDateTimeRange, Void>() {
            @Override
            public Void call(Agenda.LocalDateTimeRange range) {
                LocalDateTime start = range.getStartLocalDateTime();
                LocalDateTime end = range.getEndLocalDateTime();
                // Perform some action with the start and end times...
                return null;
            }
        };

        // set start and end times for the agenda
        LocalDateTimeRange range = new LocalDateTimeRange(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(7));
        callback.call(range);
        agenda.setDisplayedLocalDateTime(LocalDateTime.now());
        agenda.setLocalDateTimeRangeCallback(callback);
       

        // create appointments for the agenda
        List<Appointment> appointments = new ArrayList<>();

        Appointment appointment1 = new Agenda.AppointmentImpl();
        appointment1.setStartLocalDateTime(LocalDateTime.now().minusHours(2));
        appointment1.setEndLocalDateTime(LocalDateTime.now().plusMinutes(10));
        appointments.add(appointment1);


        // add appointments to agenda
        agenda.appointments().addAll(appointments);

        // create label to display agenda date
        Label dateLabel = new Label();
        dateLabel.textProperty().bind(agenda.displayedLocalDateTime().asString(""));
        
        Button prevWeekButton = new Button("Previous Week");
        prevWeekButton.setOnAction(event -> {
        	agenda.setDisplayedLocalDateTime(LocalDateTime.now().minusWeeks(1));
            primaryStage.show();
            // Update your UI with the new date...
        });
        
        Button nextWeekButton = new Button("Next Week");
        nextWeekButton.setOnAction(event -> {
        	agenda.setDisplayedLocalDateTime(LocalDateTime.now().plusWeeks(1));
            primaryStage.show();
            // Update your UI with the new date...
        });
        
        HBox buttonBox = new HBox(10, prevWeekButton, nextWeekButton);
        
        

        // create vbox to hold agenda and label
        VBox vbox = new VBox(dateLabel, agenda);

        // create borderpane and add vbox to center
        BorderPane root = new BorderPane();
        root.setCenter(vbox);
        root.setBottom(buttonBox);

        // set scene and show stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    

    public static void main(String[] args) {
        launch(args);
    }

}
