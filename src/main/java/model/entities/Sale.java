package model.entities;

import lombok.*;
import model.enums.Status;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Sale {
    private Long id;
    private Client client;
    private Integer amount;
    private BigDecimal totalValue;
    private Status status;
}
