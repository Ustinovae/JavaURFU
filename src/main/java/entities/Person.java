package entities;


import javax.persistence.*;

@Entity
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false)
    private Long id;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "link_photo")
    private String linkPhoto;

    @Column(name = "vk_id")
    private int vkId;

    @Column(name = "gender")
    private String gender;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

    public Person() {
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setLinkPhoto(String link) {
        this.linkPhoto = link;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setVkId(int vkId) {
        this.vkId = vkId;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getLinkPhoto() {
        return linkPhoto;
    }

    public int getVkId() {
        return vkId;
    }

    public Student getStudent() {
        return student;
    }

    public City getCity() {
        return city;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", birthday='" + birthday + '\'' +
                ", linkPhoto='" + linkPhoto + '\'' +
                ", vkId=" + vkId +
                ", gender='" + gender + '\'' +
                ", student=" + student +
                ", city=" + city +
                '}';
    }
}
