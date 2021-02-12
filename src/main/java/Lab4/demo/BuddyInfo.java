package Lab4.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BuddyInfo {


    private Long id = null;

    private String name = null;

    private Integer phoneNumber;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int number) {
        this.phoneNumber = number;
    }

    public BuddyInfo(String name, int phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public BuddyInfo() {
        this.name = "Bob";
        this.phoneNumber = 0;
    }

    public void addOrChangeName(String name) {
        this.name = name;
    }

    public void addOrChangeNumber(int number) {
        this.phoneNumber = number;
    }

    @Override
    public String toString() {
        return String.format(
                "Buddy[id=%d, name='%s', number='%d']",
                id, name, phoneNumber);
    }
}





