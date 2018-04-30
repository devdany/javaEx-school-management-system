import major.Major;
import model.Job;
import model.User;
import subject.Score;
import subject.Subject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[]args) {
        Initializer.init();
        Views views = new Views();
        Map<String, User> session = new HashMap<String, User>();
        DataBase dataBase = new DataBase();

        while(true){
            User sessionUser = session.get("loginUser");
            String requestUrl = views.index(sessionUser);

            //controller
            switch (requestUrl){
                case "index" : views.index(sessionUser);
                case "signUp" :

                    Map<String, Object> signUpParams = views.signUp();
                    String signUpId = (String)signUpParams.get("userId");
                    String signUppasswd = (String)signUpParams.get("password");
                    Major major = (Major)signUpParams.get("major");
                    Job job = (Job)signUpParams.get("job");

                    User signUpUser = new User(job, major, signUpId, signUppasswd);
                    dataBase.saveUser(signUpUser);
                    session.put("loginUser", signUpUser);
                    System.out.println("성공적으로 회원가입 되었습니다.");
                    break;
                case "login" :

                    Map<String, String> loginParams = views.login();
                    User loginUser = dataBase.findUserByUserId(loginParams.get("userId"));
                    if(loginUser == null){
                        System.out.println("해당 유저가 없습니다.");
                        break;
                    }

                    if(!loginUser.isMatchPassword(loginParams.get("passwd"))){
                        System.out.println("비밀번호가 틀립니다.");
                        break;
                    }

                    session.put("loginUser", loginUser);
                    System.out.println("성공적으로 로그인 되었습니다.");
                    break;
                case "logout" :
                    session.remove("loginUser");
                    break;
                case "enterClass" :
                    User enterUser = session.get("loginUser");
                    Major myMajor = enterUser.getMajor();
                    Subject[] subjects = myMajor.getSubject();
                    Subject enterSelected = views.enterMajor(subjects);
                    if(enterUser.isAlreadyEnterSubject(enterSelected)){
                        System.out.println("이미 신청한 수업입니다.");
                        break;
                    }

                    enterUser.addSubject(enterSelected);
                    enterSelected.addStudent(enterUser);

                    System.out.println("성공적으로 수강신청 되었습니다.");
                    break;
                case "cancelClass" :
                    User cancelUser = session.get("loginUser");
                    List<Subject> myClass = cancelUser.getSubjects();
                    Subject cancelSelected = views.cancelSubject(myClass);
                    if(!myClass.contains(cancelSelected)){
                        System.out.println("수강신청 리스트에 없습니다.");
                    }
                    myClass.remove(cancelSelected);
                    System.out.println("성공적으로 수강취소 되었습니다.");
                    break;
                case "myClass" :
                    User myClasslUser = session.get("loginUser");
                    List<Subject> myClassList = myClasslUser.getSubjects();
                    views.myScheduler(myClassList);
                    break;
                case "estimateClass" :
                    User estimateUser = session.get("loginUser");
                    List<Subject> myEstimateList = estimateUser.getSubjects();

                    Subject estimated = views.estimate(myEstimateList);

                    if(!estimateUser.isAlreadyEnterSubject(estimated)){
                        System.out.println("수강하지 않으신 수업입니다.");
                        break;
                    }

                    if(estimated.isEstimate(estimateUser)){
                        System.out.println("이미 평가하신 수업입니다.");
                        break;
                    }

                    int score = views.howMany();
                    estimated.addEstimate(score, estimateUser);
                    System.out.println("정상적으로 강의평가가 처리되었습니다.");
                    break;
                case "takeClass" :
                    User takeUser = session.get("loginUser");
                    Major takeMajor = takeUser.getMajor();
                    Subject[] takeSubjects = takeMajor.getSubject();
                    Subject takeSelected = views.takeMajor(takeSubjects);

                    if(takeSelected.getProfessor() != null){
                        System.out.println("이미 담당 교수가 있습니다.");
                        break;
                    }

                    takeUser.addSubject(takeSelected);
                    takeSelected.setProfessor(takeUser);

                    System.out.println("성공적으로 담당 교수가 되었습니다.");
                    break;
                case "checkStudentList" :
                    User checkProfessor = session.get("loginUser");
                    List<Subject> checkSubjects = checkProfessor.getSubjects();
                    Subject checkSubject = views.selectCheckStudent(checkSubjects);
                    views.checkStudentList(checkSubject);
                    break;
                case "test" :
                    User testProfessor = session.get("loginUser");
                    List<Subject> testSubjects = testProfessor.getSubjects();
                    Subject testSubject = views.test(testSubjects);
                    testSubject.testMethod();

                    break;

                case "inputResult" :
                    User resultProfessor = session.get("loginUser");
                    List<Subject> resultSubjects = resultProfessor.getSubjects();

                    Subject resultSubject = views.resultSubjects(resultSubjects);
                    List<User> resultUsers = resultSubject.getStudents();

                    User resultUser = views.resultUser(resultUsers);
                    Score resultScore = views.resultScore();

                    Map <User, Score> result = new HashMap();
                    result.put(resultUser, resultScore);
                    resultSubject.setResult(result);
                    System.out.println("정상적으로 성적이 입력되었습니다.");

                    break;

                case "checkResult" :
                    User checkResultUser = session.get("loginUser");
                    List<Subject> checkResultSubjects = checkResultUser.getSubjects();
                    views.resultCheck(checkResultSubjects, checkResultUser);
                    break;

            }

        }
    }
}


