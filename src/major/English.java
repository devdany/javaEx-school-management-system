package major;

import subject.Subject;

public class English extends Major {
    String name = "영어영문학과";
    Subject subject [] = new Subject[3];

    private static English english = new English();

    private English(){

    }

    public static English getInstance(){
        return english;
    }

    public void setSubject(Subject[] subject) {
        this.subject = subject;
    }

    @Override
    public Subject[] getSubject() {
        return subject;
    }
}
