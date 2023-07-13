package com.hackathon.ehealthcareproject.utils;


import com.hackathon.ehealthcareproject.entity.DaysOfWeek;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class AppointmentLogic {
    /**
     * doctors get assigned days. A string of days
     * based on the form, get day
     * assign a doctor based on the day selected. A list converted to a set.
     */

    public static String assignDays() {

        List<String> weekdays = new ArrayList<>();
        weekdays.add("MONDAY");
        weekdays.add("TUESDAY");
        weekdays.add("WEDNESDAY");
        weekdays.add("THURSDAY");
        weekdays.add("FRIDAY");
        weekdays.add("SATURDAY");
        weekdays.add("SUNDAY");

        Collections.shuffle(weekdays);
        List<String> assignedDays = weekdays.subList(0, 4);
        StringBuilder availableDays = new StringBuilder();

        for (String day : assignedDays) {
            availableDays.append(day).append(" ");
        }

        return availableDays.toString().trim();
    }

//    public static DaysOfWeek determineDay(@DateTimeFormat(pattern = "dd/MM/yyyy") String enteredDate) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate date = LocalDate.parse(enteredDate, formatter); //formatter saves the string into a date object in the pattern stated in the line above
//
//        Set<DaysOfWeek> matchingDays = Arrays.stream(DaysOfWeek.values()).filter(daysOfWeek -> daysOfWeek.toString().equalsIgnoreCase(date.getDayOfWeek().toString())).collect(Collectors.toSet());
//        return matchingDays.iterator().next();
//    }


}
