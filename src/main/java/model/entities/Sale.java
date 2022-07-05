package model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Sale {
    private Long id;
    private Client client;
    private Integer amount;
    private BigDecimal price;
}
