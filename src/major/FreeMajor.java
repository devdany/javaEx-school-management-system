package major;

import subject.Subject;

public class FreeMajor extends Major{
    String name = "자율전공";
    Subject subject [] = new Subject[3];

    private static FreeMajor freeMajor = new FreeMajor();

    private FreeMajor(){

    }

    public static FreeMajor getInstance(){
        return freeMajor;
    }

    public void setSubject(Subject[] subject) {
        this.subject = subject;
    }

    @Override
    public Subject[] getSubject() {
        return subject;
    }



}
