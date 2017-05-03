package model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Slavian on 4/14/2017.
 */
public class Subject {
    private String name;
    private ArrayList<SubjectTime> subDays;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate finalDate;

    public Subject(String name, ArrayList<SubjectTime> subDays) {
        this.name = name;
        this.subDays = subDays;
    }

    public Subject(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SubjectTime> getSubDays() {
        return subDays;
    }

    public void setSubDays(ArrayList<SubjectTime> subDays) {
        this.subDays = subDays;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }
}
