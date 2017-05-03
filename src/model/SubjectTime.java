package model;

/**
 * Created by Slavian on 4/14/2017.
 */
public class SubjectTime {
    private String weekDay;
    private String timePlace;
    private String numOfWeek;

    public SubjectTime(){}

    public SubjectTime(String weekDay, String timePlace, String numOfWeek) {
        this.weekDay = weekDay;
        this.timePlace = timePlace;
        this.numOfWeek = numOfWeek;
    }
    public SubjectTime(SubjectTime s){
        this.weekDay = s.weekDay;
        this.timePlace = s.timePlace;
        this.numOfWeek = s.numOfWeek;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getTimePlace() {
        return timePlace;
    }

    public void setTimePlace(String timePlace) {
        this.timePlace = timePlace;
    }

    public String getNumOfWeek() {
        return numOfWeek;
    }

    public void setNumOfWeek(String numOfWeek) {
        this.numOfWeek = numOfWeek;
    }
}