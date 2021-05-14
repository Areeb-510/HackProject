package Models;

public class User {

    String profilepic, mail, password, userId,userName;

    public User(String profilepic,String userName ,String mail, String password, String userId) {
        this.profilepic = profilepic;
        this.mail = mail;
        this.password = password;
        this.userId = userId;
        this.userName = userName;
    }

    public User(){}

    //SignUp Constructor

    public User(String userName,String mail, String password) {
        this.mail = mail;
        this.userName = userName;
        this.password = password;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
