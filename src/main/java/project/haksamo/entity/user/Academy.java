package project.haksamo.entity.user;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import project.haksamo.entity.Address;
import project.haksamo.entity.user.User;

@Entity
@Getter
public class Academy extends User {

    private String name;
    private String shortIntroduction;
    private String target;

    @Embedded
    private Address address;

    private String introduction;
    private int count;

    public void createDetails(String shortIntroduction, String target, Address address, String introduction) {
        this.shortIntroduction = shortIntroduction;
        this.target = target;
        this.address = address;
        this.introduction = introduction;
    }
}
