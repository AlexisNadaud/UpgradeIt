package fr.alexisnadaud.upgradeit.Classes;

/**
 * Created by Alexis on 27/03/2018.

 private static final String TABLE_COURSE = "table_course";
 private static final String COL_ID_COURSE = "id_course";
 private static final String COL_CLICS_COURSE = "course_clics";
 private static final String COL_NB_PARTICIPANTS = "course_participants";

 */

public class Course {
    private int id_course;
    private int course_clics;
    private int course_participants;

    public Course(int id_course, int course_clics, int course_participants) {
        this.id_course = id_course;
        this.course_clics = course_clics;
        this.course_participants = course_participants;
    }

    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "id_course=" + id_course +
                ", course_clics=" + course_clics +
                ", course_participants=" + course_participants +
                '}';
    }

    public int getId_course() {
        return id_course;
    }

    public void setId_course(int id_course) {
        this.id_course = id_course;
    }

    public int getCourse_clics() {
        return course_clics;
    }

    public void setCourse_clics(int course_clics) {
        this.course_clics = course_clics;
    }

    public int getCourse_participants() {
        return course_participants;
    }

    public void setCourse_participants(int course_participants) {
        this.course_participants = course_participants;
    }
}
