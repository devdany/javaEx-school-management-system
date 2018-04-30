import major.*;
import model.Job;
import model.User;
import subject.Score;
import subject.Subject;

import java.util.*;

public class Views {
    Scanner scanner = new Scanner(System.in);

    public String index(User loginUser) {
        if (loginUser == null) {
            return notLoginindex();
        } else {
            if (loginUser.isStudent()) {
                return loginStudent(loginUser);
            } else {
                return loginProfessor(loginUser);
            }
        }
    }

    public Map<String, String> login() {
        System.out.println("아이디를 입력하세요.");
        String inputUserId = scanner.next();
        System.out.println("비밀번호를 입력하세요.");
        String inputPasswd = scanner.next();
        Map<String, String> param = new HashMap();
        param.put("userId", inputUserId);
        param.put("passwd", inputPasswd);

        return param;
    }

    public Map<String, Object> signUp() {
        System.out.println("사용하실 아이디를 입력하세요.");
        String userId = scanner.next();

        System.out.println("사용하실 비밀번호를 입력하세요.");
        String password = scanner.next();

        System.out.println("학과를 입력하세요");
        System.out.println("1.컴퓨터 공학과");
        System.out.println("2.법학과");
        System.out.println("3.영어영문학과");

        int majorCode = scanner.nextInt();
        Major major;

        switch (majorCode) {
            case 1:
                major = ComputerScience.getInstance();
                break;
            case 2:
                major = Law.getInstance();
                break;
            case 3:
                major = English.getInstance();
                break;
            default:
                major = FreeMajor.getInstance();
        }

        System.out.println("직책을 입력하세요");
        System.out.println("1.학생");
        System.out.println("2.교수");

        Job job;
        int jobCode = scanner.nextInt();
        switch (jobCode) {
            case 1:
                job = Job.STUDENT;
                break;
            default:
                job = Job.PROFESSOR;
                break;

        }


        Map<String, Object> params = new HashMap();

        params.put("userId", userId);
        params.put("password", password);
        params.put("major", major);
        params.put("job", job);


        return params;
    }

    public Subject enterMajor(Subject[] subjects) {
        System.out.println("수강신청하실 수업을 선택하세요.");
        for (int i = 0; i < subjects.length; i++) {
            System.out.println((i + 1) + "." + subjects[i].getSubjectName());
        }
        int input = scanner.nextInt();
        Subject selected = subjects[input - 1];

        return selected;
    }

    public Subject takeMajor(Subject[] subjects) {
        System.out.println("담당하실 수업을 선택하세요.");
        for (int i = 0; i < subjects.length; i++) {
            System.out.println((i + 1) + "." + subjects[i].getSubjectName());
        }
        int input = scanner.nextInt();
        Subject selected = subjects[input - 1];

        return selected;
    }

    public Subject cancelSubject(List<Subject> subjects) {
        System.out.println("수강취소할 수업을 선택하세요");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + "." + subjects.get(i).getSubjectName());
        }
        int input = scanner.nextInt();
        Subject selected = subjects.get(input - 1);
        return selected;
    }

    public void myScheduler(List<Subject> subjects) {
        String schedule[][] = new String[5][7];

        for (Subject subject : subjects) {
            String time = subject.getTime();
            String temp[] = time.split(" ");
            String week = temp[0];
            String day = temp[1];

            int i = 0;
            switch (week) {
                case "mon":
                    i = 0; break;
                case "tue":
                    i = 1;break;
                case "wed":
                    i = 2;break;
                case "thu":
                    i = 3;break;
                case "fri":
                    i = 4;break;
            }

            int j = Integer.parseInt(day) - 1;

            schedule[i][j] = "  "+subject.getSubjectName().substring(0, 2);

        }

        System.out.print("     mon     ");
        System.out.print("tue     ");
        System.out.print("wed     ");
        System.out.print("thu     ");
        System.out.println("fri");
        System.out.println("-----------------------------------------");

        for(int a = 0; a<7; a++){
            for(int b = 0; b<5; b++){
                if(b == 0){
                    System.out.print(a+1+"|");

                    if(schedule[b][a] != null){
                        System.out.print(schedule[b][a] + "  |") ;
                    } else {
                        System.out.print("       |");
                    }
                }else{
                    if(schedule[b][a] != null){
                        System.out.print(schedule[b][a] + "  |") ;
                    } else {
                        System.out.print("       |");
                    }
                }
            }

            System.out.println();
            System.out.print("-----------------------------------------");
            System.out.println();
        }


    }

    public Subject estimate(List<Subject> subjects){
        System.out.println("평가할 수업을 선택하세요.");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + "." + subjects.get(i).getSubjectName());
        }
        int input = scanner.nextInt();
        Subject estimated = subjects.get(input - 1);

        return estimated;
    }

    public int howMany(){
        System.out.println("1~5 중 점수를 주세요.");
        return scanner.nextInt();
    }

    public Subject selectCheckStudent(List<Subject> subjects){
        System.out.println("출석부를 확인할 과목을 선택하세요");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + "." + subjects.get(i).getSubjectName());
        }
        int input = scanner.nextInt();
        Subject checked = subjects.get(input - 1);

        return checked;
    }

    public Subject test(List<Subject> subjects){
        System.out.println("시험 완료 처리할 과목을 선택하세요");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + "." + subjects.get(i).getSubjectName());
        }
        int input = scanner.nextInt();
        Subject tested = subjects.get(input - 1);

        return tested;
    }

    public void checkStudentList(Subject subject){
        List<User> students = subject.getStudents();
        for(int i = 0; i<students.size(); i++){
            System.out.println((i+1)+". "+students.get(i).getUserId());
        }
    }

    public Subject resultSubjects(List<Subject> subjects){
        System.out.println("성적 입력할 과목을 선택하세요");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + "." + subjects.get(i).getSubjectName());
        }
        int input = scanner.nextInt();
        Subject resulted = subjects.get(input - 1);

        return resulted;
    }

    public User resultUser(List<User> students){
        System.out.println("성적을 입력할 학생을 선택하세요");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + "." + students.get(i).getUserId());
        }
        int input = scanner.nextInt();
        User resulted = students.get(input - 1);

        return resulted;
    }

    public Score resultScore(){
        System.out.println("성적을 입력하세요");
        int i = 1;
        for(Score score : Score.values()){
            System.out.println(i+"."+score);
            i++;
        }

        int input = scanner.nextInt();
        Score result;

        switch (input){
            case 1 : result = Score.Ap; break;
            case 2 : result = Score.A; break;
            case 3 : result = Score.Bp; break;
            case 4 : result = Score.B; break;
            case 5 : result = Score.Cp; break;
            case 6 : result = Score.C; break;
            case 7 : result = Score.Dp; break;
            case 8 : result = Score.D; break;
            default: result = Score.F;

        }

        return result;
    }

    public void resultCheck(List<Subject> subjects, User user){
        System.out.println("성적 확인할 과목을 선택하세요");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + "." + subjects.get(i).getSubjectName());
        }
        int input = scanner.nextInt();
        Subject target = subjects.get(input - 1);
        Map<User, Score> targetResult = target.getResult();
        System.out.println(user.getUserId()+" 님의 점수는 "+targetResult.get(user)+" 입니다.");
    }

    public String notLoginindex() {
        System.out.println("학사관리 시스템에 오신걸 환영합니다.");
        System.out.println("1.로그인");
        System.out.println("2.회원가입");

        int input = scanner.nextInt();

        switch (input) {
            case 1:
                return "login";
            case 2:
                return "signUp";
            default:
                return "index";
        }

    }

    public String loginStudent(User user) {
        System.out.println(user.getUserId() + "님, 학사관리 시스템에 오신걸 환영합니다.");
        System.out.println("1.수강 신청");
        System.out.println("2.수강 취소");
        System.out.println("3.나의 시간표");
        System.out.println("4.수강 평가");
        System.out.println("5.성적 확인");
        System.out.println("6.로그 아웃");

        int input = scanner.nextInt();

        switch (input) {
            case 1:
                return "enterClass";
            case 2:
                return "cancelClass";
            case 3:
                return "myClass";
            case 4:
                return "estimateClass";
            case 5:
                return "checkResult";
            case 6:
                return "logout";
            default:
                return "index";

        }
    }

    public String loginProfessor(User user) {
        System.out.println(user.getUserId() + "님, 학사관리 시스템에 오신걸 환영합니다.");
        System.out.println("1.출석부 확인");
        System.out.println("2.담당 교수 되기");
        System.out.println("3.성적 입력");
        System.out.println("4.시험 완료 처리");
        System.out.println("5.로그 아웃");

        int input = scanner.nextInt();
        switch (input) {
            case 1:
                return "checkStudentList";
            case 2:
                return "takeClass";
            case 3:
                return "inputResult";
            case 4:
                return "test";
            case 5:
                return "logout";
            default:
                return "index";
        }
    }
}
