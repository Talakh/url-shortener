package com.example.urlshortner.controller;

import com.example.urlshortner.dto.ShortenerRequestDto;
import com.example.urlshortner.service.ShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequestUri;

@RestController
public class ShortenerUrlController {
    private final ShortenerService shortenerService;

    public ShortenerUrlController(final ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @PostMapping("/shorts/urls")
    public ResponseEntity<Object> shortUrl(@RequestBody ShortenerRequestDto request) {
        var shortUrl = shortenerService.shortUrl(request.getUrl(), request.getTitle());
        return ResponseEntity
                .created(fromCurrentRequestUri().pathSegment(shortUrl).build().toUri())
                .build();
    }

    @GetMapping("/shorts/urls/{shortenUrlId}")
    public ResponseEntity<Object> redirectShortenedUrl(@PathVariable String shortenUrlId) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(shortenerService.getOriginalUrl(shortenUrlId))
                .build();
    }
}
