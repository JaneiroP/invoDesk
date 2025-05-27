package models;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String productName;
    private Integer referenceCode;
    private String description;
    private BigDecimal priceUnit;


}
