package com.spring.beanFilter.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@JsonPropertyOrder({
        "requestType",
        "application",
        "environment",
        "type",
        "technology",
        "mode",
        "domain",
        "cluster",
        "host",
        "mapping",
        "action",
        "artifact"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseFilter {

    private final static long serialVersionUID = 3045894888170401059L;

    @JsonProperty("requestType")
    private String requestType;
    @JsonProperty("application")
    private List<String> application = new ArrayList<>();
    @JsonProperty("environment")
    private List<String> environment = new ArrayList<>();
    @JsonProperty("type")
    private List<String> type = new ArrayList<>();
    @JsonIgnore
    @JsonProperty("technology")
    private List<String> technology = new ArrayList<>();
    @JsonIgnore
    @JsonProperty("mode")
    private List<String> mode = new ArrayList<>();
    @JsonProperty("domain")
    private List<String> domain = new ArrayList<>();
    @JsonProperty("cluster")
    private List<String> cluster = new ArrayList<>();
    @JsonProperty("host")
    private List<String> host = new ArrayList<>();
    @JsonProperty("mapping")
    private List<String> mapping = new ArrayList<>();
    @JsonProperty("action")
    private List<String> action = new ArrayList<>();
    @JsonProperty("artifact")
    private List<String> artifact = new ArrayList<>();
}
