package api.pojo;

import lombok.Getter;
import lombok.Setter;

public class PetPojo {
    @Setter
    @Getter
    private int id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String status;

public PetPojo()
{}

public PetPojo(int id,String name, String status)
{}

}
