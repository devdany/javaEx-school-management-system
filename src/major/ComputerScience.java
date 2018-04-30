package major;

import subject.Subject;

public class ComputerScience extends Major {
    String name = "컴퓨터공학과";
    Subject subject [] = new Subject[3];

    private static ComputerScience computerScience = new ComputerScience();

    private ComputerScience(){

    }

    public static ComputerScience getInstance(){
        return computerScience;
    }

    public void setSubject(Subject[] subject) {
        this.subject = subject;
    }

    @Override
    public Subject[] getSubject() {
        return subject;
    }
}
