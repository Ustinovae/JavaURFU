package dataStructure;

import java.net.URL;
import java.util.Date;

public class Person implements PersonInterface{
    private final String username;
    private int userVkId;
    private String firstname;
    private String lastname;
    private String birthDay;
    private String city;
    private URL linkPhoto;
    private Gender gender;

    public Person(String username){
        this.username = username;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender(){
        return gender;
    }

    public String getFirstname(){
        return firstname;
    }

    public int getUserVkId(){
        return userVkId;
    }

    public void setUserVkId(int userVkId){
        this.userVkId = userVkId;
    }

    public void setFirstname(String name){
        this.firstname = name;
    }

    public String getLastname(){
        return lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getBirthDay(){ return birthDay; }

    public void setBirthDay(String birthDay){ this.birthDay = birthDay; }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setLinkPhoto(URL linkPhoto){
        this.linkPhoto = linkPhoto;
    }

    public URL getLinkPhoto() {
        return linkPhoto;
    }
}
