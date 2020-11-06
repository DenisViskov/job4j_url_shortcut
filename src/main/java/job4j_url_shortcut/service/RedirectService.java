package job4j_url_shortcut.service;

import job4j_url_shortcut.domain.Url;
import job4j_url_shortcut.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 06.11.2020
 */
@Service
public class RedirectService implements Redirect<String> {
    private final UrlRepository repository;
    private final Statistic statisticService;

    @Autowired
    public RedirectService(UrlRepository repository, Statistic statisticService) {
        this.repository = repository;
        this.statisticService = statisticService;
    }

    @Override
    public Optional<String> getRedirect(String code) {
        Optional<String> result = Optional.empty();
        Optional<Url> urlBox = repository.findByCode(code);
        if (urlBox.isPresent()) {
            result = Optional.of(urlBox
                    .get()
                    .getUrl());
            statisticService.incrementTotal(urlBox.get());
        }
        return result;
    }
}
