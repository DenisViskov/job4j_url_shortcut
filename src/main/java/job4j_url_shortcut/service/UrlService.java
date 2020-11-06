package job4j_url_shortcut.service;

import job4j_url_shortcut.domain.Site;
import job4j_url_shortcut.domain.Url;
import job4j_url_shortcut.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 06.11.2020
 */
@Service
public class UrlService implements RepositoryService<Url>, Randomizer<String> {
    private final UrlRepository urlRepository;
    private final SiteRepositoryService siteRepositoryService;

    @Autowired
    public UrlService(UrlRepository urlRepository, SiteRepositoryService siteRepositoryService) {
        this.urlRepository = urlRepository;
        this.siteRepositoryService = siteRepositoryService;
    }

    @Override
    public String getRandom() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder result = new StringBuilder(20);
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            result.append(c);
        }
        return result.toString();
    }

    @Override
    public Url add(Url some) {
        Optional<Url> urlBox = urlRepository.findByUrl(some.getUrl());
        urlBox.ifPresentOrElse(url -> {
            some.setId(url.getId());
            some.setUrl(url.getUrl());
            some.setCode(url.getCode());
        }, () -> {
            String siteName = nameImport(some.getUrl());
            Site site = (Site) siteRepositoryService.findBySiteName(siteName).get();
            some.setId(0);
            some.setCode(getRandom());
            urlRepository.save(some);
            site.addUrl(some);
            siteRepositoryService.update(site);
        });
        return some;
    }

    private String nameImport(String url) {
        String result = "";
        try {
            URL tmp = new URL(url);
            result = tmp.getProtocol() + "://" + tmp.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Url some) {
        return false;
    }

    @Override
    public boolean delete(Url some) {
        return false;
    }

    @Override
    public Optional<Url> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Url> findAll() {
        return null;
    }
}
