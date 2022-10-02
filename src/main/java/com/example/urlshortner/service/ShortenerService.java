package com.example.urlshortner.service;

import com.example.urlshortner.entity.ShortenedUrl;
import com.example.urlshortner.repository.ShortenedUrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;

@Service
public class ShortenerService {
    private final ShortenedUrlRepository shortenedUrlRepository;

    public ShortenerService(final ShortenedUrlRepository shortenedUrlRepository) {
        this.shortenedUrlRepository = shortenedUrlRepository;
    }

    public String shortUrl(String url, String name) {
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        ShortenedUrl shortenedUrl = new ShortenedUrl()
                .setId(generatedString)
                .setOriginalUrl(url)
                .setTitle(name)
                .setCreatedAt(LocalDateTime.now());
        shortenedUrlRepository.save(shortenedUrl);
        return generatedString;
    }

    @Cacheable("shorts")
    public URI getOriginalUrl(String urlId) {
        return shortenedUrlRepository.findById(urlId)
                .map(shortUrl -> URI.create(shortUrl.getOriginalUrl()))
                .orElseThrow(() -> new IllegalArgumentException("Url with id " + urlId + "not found!"));
    }
}
