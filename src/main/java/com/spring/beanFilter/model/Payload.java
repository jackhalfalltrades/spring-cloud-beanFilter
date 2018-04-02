package com.spring.beanFilter.model;

import com.fasterxml.jackson.annotation.*;
import com.spring.beanFilter.validation.annotation.DropdownValid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import javax.validation.constraints.NotEmpty;

@JsonPropertyOrder({
        "dropdownName",
        "responseFilter"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RefreshScope
public class Payload {

    private final static long serialVersionUID = -9070781078140439106L;

    @NotEmpty
    @DropdownValid(acceptedValues = "accepted.dropdown.values", message = "Invalid dropdownName")
    private String dropdownName;

    @JsonIgnore
    @JsonProperty("responseFilter")
    private ResponseFilter responseFilter;

    public boolean isEmpty(Payload payload) {

        return false;
    }

}
