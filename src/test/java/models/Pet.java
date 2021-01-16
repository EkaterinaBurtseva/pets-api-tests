package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@ToString
@Accessors(fluent = true)
public class Pet {

    @JsonProperty("id")
    public int id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("category")
    public Category category;

    @JsonProperty("photoUrls")
    public String[] photoUrls;

    @JsonProperty("tags")
    public Tag[] tags;

    @JsonProperty("status")
    public String status;

}
