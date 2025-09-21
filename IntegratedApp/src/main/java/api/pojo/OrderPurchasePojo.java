package api.pojo;

import lombok.Getter;
import lombok.Setter;

public class OrderPurchasePojo {
    // Getters and Setters

    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private int petId;
    @Setter
    @Getter
    private int quantity;

    @Getter
    @Setter
    private String shipDate;
    @Getter
    @Setter
    private String status;
    @Setter
    @Getter
    private boolean complete;

    // Default constructor
    public OrderPurchasePojo()
    {}


}
