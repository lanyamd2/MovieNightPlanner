package com.sparta.projectmovie1.movienightplanner.services;

import com.sparta.projectmovie1.movienightplanner.models.JustWatchProvider;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class HelpSectionService {

  @Value("${justwatch.api.key}")
  private String apiKey;

  @Value("https://apis.justwatch.com/contentpartner/v2/content")
  private String rootUrl;

  private final WebClient webClient;

  @Autowired
  public HelpSectionService(WebClient webClient) {
    this.webClient = webClient;
  }

  public List<JustWatchProvider> getProviders() {
    return webClient.get()
        .uri(rootUrl + "/providers/all/locale/en_GB?token=" + apiKey)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<JustWatchProvider>>() {
        }).block();
  }
}
