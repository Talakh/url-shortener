package com.example.urlshortner.dto;

import lombok.Data;

@Data
public class ShortenerRequestDto {
    private String url;
    private String title;
}
