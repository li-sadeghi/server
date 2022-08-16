package servermodels.users;

import servermodels.department.Department;
import sharedmodels.users.SharedUser;

import javax.persistence.*;

@Entity
public class User {
    @Id
    private String username;
    @Column
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column
    private String fullName;
    @Column
    private String nationalCode;
    @Column
    private String emailAddress;
    @Column
    private String phoneNumber;
    @Column
    private String lastLogin;
    @Column
    private String userImageBytes;
    // dont forget: private ArrayList<Request> requests = new ArrayList<Request>();
    public User(){

    }
    public User(String username, String password)  {
        this.setUsername(username);
        this.setPassword(password);
        this.setLastLogin(null);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getUserImageBytes() {
        return userImageBytes;
    }

    public void setUserImageBytes(String userImageBytes) {
        this.userImageBytes = userImageBytes;
    }

    public SharedUser toShared() {
        SharedUser sharedUser = new SharedUser();
        sharedUser.setEmailAddress(emailAddress);
        sharedUser.setRole(role.toShared());
        sharedUser.setUsername(username);
        sharedUser.setUserImageBytes(userImageBytes);
        sharedUser.setPhoneNumber(phoneNumber);
        sharedUser.setNationalCode(nationalCode);
        sharedUser.setLastLogin(lastLogin);
        sharedUser.setFullName(fullName);
        return sharedUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", fullName='" + fullName + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", userImageBytes='" + userImageBytes + '\'' +
                '}';
    }
}
