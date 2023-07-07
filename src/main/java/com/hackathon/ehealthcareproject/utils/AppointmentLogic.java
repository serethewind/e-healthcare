package com.hackathon.ehealthcareproject.utils;

import com.hackathon.ehealthcareproject.dto.doctor.DoctorResponseDto;
import com.hackathon.ehealthcareproject.entity.DaysOfWeek;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class AppointmentLogic {
    /**
     * doctors get assigned days. A set
     * based on the form, get day
     * assign a doctor based on the day selected. A list converted to a set.
     */

    public static Set<DaysOfWeek> assignDays() {

        List<DaysOfWeek> weekdays = new ArrayList<>();
        weekdays.add(DaysOfWeek.MONDAY);
        weekdays.add(DaysOfWeek.TUESDAY);
        weekdays.add(DaysOfWeek.WEDNESDAY);
        weekdays.add(DaysOfWeek.THURSDAY);
        weekdays.add(DaysOfWeek.FRIDAY);
        weekdays.add(DaysOfWeek.SATURDAY);
        weekdays.add(DaysOfWeek.SUNDAY);

        Collections.shuffle(weekdays);
        return (Set<DaysOfWeek>) weekdays.subList(0, 4);
    }

    public static DaysOfWeek determineDay(@DateTimeFormat(pattern = "dd/MM/yyyy") String enteredDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(enteredDate, formatter); //formatter saves the string into a date object in the pattern stated in the line above

        Set<DaysOfWeek> matchingDays = Arrays.stream(DaysOfWeek.values()).filter( daysOfWeek -> daysOfWeek.toString().equalsIgnoreCase(date.getDayOfWeek().toString())).collect(Collectors.toSet());
        return matchingDays.iterator().next();
    }


}
