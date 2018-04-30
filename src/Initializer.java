import major.ComputerScience;
import major.English;
import major.Law;
import major.Major;
import subject.*;

public class Initializer {

    public static void init(){
        //학과, 수업 초기화

        Major computerScience = ComputerScience.getInstance();
        Major english = English.getInstance();
        Major law = Law.getInstance();

        Subject [] computers = new Subject[3];

        Subject algorithm = Algorithm.getInstance();
        Subject java = Java.getInstance();
        Subject network = Network.getInstance();
        computers[0] = algorithm;
        computers[1] = java;
        computers[2] = network;

        computerScience.setSubject(computers);

        //////////////////////////////////////

        Subject [] laws = new Subject[3];

        Subject hunlaw = Hunlaw.getInstance();
        Subject hyunglaw = Hyunglaw.getInstance();
        Subject minlaw = Minlaw.getInstance();
        laws[0] = hunlaw;
        laws[1] = hyunglaw;
        laws[2] = minlaw;

        law.setSubject(laws);

        //////////////////////////////////////

        Subject [] englishs = new Subject[3];

        Subject speaking = Speaking.getInstance();
        Subject writing = Writing.getInstance();
        Subject listening = Listening.getInstance();
        englishs[0] = speaking;
        englishs[1] = writing;
        englishs[2] = listening;

        english.setSubject(englishs);

    }
}
