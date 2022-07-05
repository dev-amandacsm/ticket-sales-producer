package model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Client {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
