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
public class Tag {
    @JsonProperty("id")
    public int idTag;

    @JsonProperty("name")
    public String nameTag;

}
