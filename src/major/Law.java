package major;

import subject.Subject;

public class Law extends Major {
    String name = "법학과";
    Subject subject [] = new Subject[3];

    private static Law law = new Law();

    public void setSubject(Subject[] subject) {
        this.subject = subject;
    }

    private Law(){

    }

    public static Law getInstance(){
        return law;
    }

    @Override
    public Subject[] getSubject() {
        return subject;
    }

}
