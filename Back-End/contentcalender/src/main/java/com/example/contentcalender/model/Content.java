package com.example.contentcalender.model;

import java.time.LocalDateTime;

public record Content(
    Integer id,
    String name,
    String desc,
    Status status,
    Type contenttype,
    LocalDateTime dateCreated,
    LocalDateTime dateUpdated,
    String url

) {
    
}
