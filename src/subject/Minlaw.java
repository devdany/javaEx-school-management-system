package subject;

import major.ComputerScience;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Minlaw extends Subject  {
    private final String subjectName = "민법";
    private boolean testClear = false;
    private final String testMethod = "필기시험";
    private Map<User, Score> result = new HashMap<>();
    private int levtureEvalTotal;
    private List<User> isEvalStudents = new ArrayList<>();
    private List<User> students = new ArrayList<>();
    private User professor;
    private String time = "mon 6";

    private static Minlaw minlaw = new Minlaw();

    private Minlaw(){

    }

    public static Minlaw getInstance(){
        return minlaw;
    }
    @Override
    public String getTime() {
        return time;
    }
    @Override
    public void addStudent(User user) {
        this.students.add(user);
    }
    @Override
    public void addEstimate(int score, User user) {
        this.levtureEvalTotal += score;
        this.isEvalStudents.add(user);
    }
    @Override
    public boolean isEstimate(User user) {
        return this.isEvalStudents.contains(user);
    }
    @Override
    public List<User> getStudents() {
        return students;
    }
    @Override
    public void testMethod() {
        System.out.println(subjectName+"의 시험은 "+testMethod+"입니다. 시험 완료 처리를 정상적으로 수행합니다. 수고하셨습니다.");
        this.testClear = true;
    }
    @Override
    public void setResult(Map<User, Score> result) {
        this.result = result;
    }
    @Override
    public Map<User, Score> getResult() {
        return result;
    }

    @Override
    public String getSubjectName() {
        return subjectName;
    }

    public void setProfessor(User professor) {
        this.professor = professor;
    }

    public User getProfessor() {
        return professor;
    }
}
