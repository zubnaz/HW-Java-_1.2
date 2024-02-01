package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Comparable {
    private String firstName;
    private String lastName;
    private int age;


    @Override
    public int compareTo(Object o) {
        User u =(User)o;
        int result = this.firstName.compareTo(u.firstName);
        if(result==0){
            result = this.lastName.compareTo(u.lastName);
        }
        return 0;
    }
}
