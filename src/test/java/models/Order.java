package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(fluent = true)
public class Order {

    @JsonProperty("id")
    public int id;

    @JsonProperty("petId")
    public int petId;

    @JsonProperty("quantity")
    public int quantity;

    @JsonProperty("shipDate")
    public String shipDate;

    @JsonProperty("status")
    public String status;

    @JsonProperty("complete")
    public boolean complete;

}
