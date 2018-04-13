package fr.alexisnadaud.upgradeit.Classes;

/**
 * Created by Alexis on 27/03/2018.

 private static final String TABLE_PARTICIPE = "table_participe";
 private static final String COL_ID_JOUEUR = "id_joueur";
 private static final String COL_ID_COURSE = "id_course";
 private static final String COL_PLACEMENT = "participant_place";
 private static final String COL_TEMPS = "temps_course";


 */

public class Participe {

    private Joueur joueur;
    private Course course;
    private int idJoueur;
    private int idCourse;
    private int participant_place;
    private String temps_course;

    public Participe(Joueur joueur, Course course, int participant_place, String temps_course) {
        this.joueur = joueur;
        this.course = course;
        this.participant_place = participant_place;
        this.temps_course = temps_course;
    }

    public Participe(){}

    @Override
    public String toString() {
        return "Participe{" +
                "joueur=" + joueur +
                ", course=" + course +
                ", participant_place=" + participant_place +
                ", temps_course=" + temps_course +
                '}';
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.idJoueur = joueur.getIdJoueur();
        this.joueur = joueur;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.idCourse = course.getId_course();
        this.course = course;
    }

    public int getParticipant_place() {
        return participant_place;
    }

    public void setParticipant_place(int participant_place) {
        this.participant_place = participant_place;
    }

    public String getTemps_course() {
        return temps_course;
    }

    public void setTemps_course(String temps_course) {
        this.temps_course = temps_course;
    }
}
