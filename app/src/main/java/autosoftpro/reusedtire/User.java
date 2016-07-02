package autosoftpro.reusedtire;

/**
 * Created by Hung on 6/17/2016.
 *  id text not null,
    name text not null,
    email text not null,
    password text not null,
    phonenumber int(8),
    description text,
    profilepict text,
    dateofbirth text,
 */
public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private int phoneNumber;
    private String description;
    private String profilePict;
    private String dateOfBirth;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfilePict() {
        return profilePict;
    }

    public void setProfilePict(String profilePict) {
        this.profilePict = profilePict;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString(){
        return getName() + " at " + getEmail();
    }
}
