package de.pschijven.entwicklertag;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Greeting", description = "Represents a greeting")
public class Greeting {


    private Long id;
    private String content;

    @ApiModelProperty(value = "The ID of the Greeting",
            dataType = "Long",
            example = "1",
            position = 1,
            required = true
    )
    public Long getId() {
        return id;
    }

    @ApiModelProperty(value = "The content of the Greeting",
            dataType = "String",
            example = "Hello world",
            position = 2,
            required = true
    )
    public String getContent() {
        return content;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setContent(final String content) {
        this.content = content;
    }
}
