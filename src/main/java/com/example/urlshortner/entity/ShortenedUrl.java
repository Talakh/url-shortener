package com.example.urlshortner.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "shortened_urls")
public class ShortenedUrl {
    @Id
    private String id;
    @Column(nullable = false)
    private String originalUrl;
    private String title;
    private LocalDateTime createdAt;
}
