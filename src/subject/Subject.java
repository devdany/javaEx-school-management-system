package subject;

import model.User;

import java.util.List;
import java.util.Map;


public abstract class Subject {
    abstract public void testMethod();
    abstract public String getSubjectName();
    abstract public String getTime();
    abstract public void addStudent(User user);
    abstract public void addEstimate(int score, User user);
    abstract public boolean isEstimate(User user);
    abstract public void setProfessor(User user);
    abstract public User getProfessor();
    abstract public List<User> getStudents();
    abstract public Map<User, Score> getResult();
    abstract public void setResult(Map<User, Score> result);


}
