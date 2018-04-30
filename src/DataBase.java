import model.User;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private List<User> userTable = new ArrayList();


    //DAO
    public List<User> findAllUser(){
        return this.userTable;
    }

    public User findUserByUserId(String userId){
        for(User user : this.userTable){
            if(user.getUserId().equals(userId)){
                return user;
            }
        }

        return null;
    }

    public void saveUser(User user){
        userTable.add(user);
    }
}
