package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", nullable = false)
    private Long id;

    @Column(name = "city_name")
    private String cityName;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Person> people = new ArrayList<>();

    public City() {
    }

    public void addPerson(Person person) {
        person.setCity(this);
        people.add(person);
    }

    public void setId(Long id){
        this.id = id;
    }

    public void removePerson(Person person) {
        people.remove(person);
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    public Long getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public List<Person> getPeople() {
        return people;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", people=" + people +
                '}';
    }
}
