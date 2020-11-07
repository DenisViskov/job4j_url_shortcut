package job4j_url_shortcut.service;

import job4j_url_shortcut.domain.Site;
import job4j_url_shortcut.domain.Url;
import job4j_url_shortcut.repository.SiteRepository;
import job4j_url_shortcut.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Class is an url service
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.11.2020
 */
@Service
public class UrlService implements Repository<Url>, Randomizer<String> {
    /**
     * Url repository
     */
    private final UrlRepository urlRepository;
    /**
     * Site repository
     */
    private final SiteRepository siteRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository, SiteRepository siteRepository) {
        this.urlRepository = urlRepository;
        this.siteRepository = siteRepository;
    }

    /**
     * @return random string
     */
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

    /**
     * Method executing check url by contain in repository and in
     * dependency of result perform associated him with site and then
     * returns Url
     *
     * @param some
     * @return URL
     */
    @Override
    public Url add(Url some) {
        Optional<Url> urlBox = urlRepository.findByUrl(some.getUrl());
        urlBox.ifPresentOrElse(url -> {
            some.setId(url.getId());
            some.setUrl(url.getUrl());
            some.setCode(url.getCode());
            some.setTotal(url.getTotal());
        }, () -> {
            String siteName = nameImport(some.getUrl());
            Optional<Site> siteBox = siteRepository.findBySite(siteName);
            siteBox.ifPresent(site -> {
                some.setId(0);
                some.setCode(getRandom());
                urlRepository.save(some);
                site.addUrl(some);
                siteRepository.save(site);
            });
        });
        return some;
    }

    /**
     * Extract from url site name
     *
     * @param url
     * @return String
     */
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

    /**
     * Method execute update some in repository
     *
     * @param some
     * @return boolean
     */
    @Override
    public boolean update(Url some) {
        boolean result = false;
        if (urlRepository.findById(some.getId()).isPresent()) {
            urlRepository.save(some);
            result = true;
        }
        return result;
    }

    /**
     * Perform delete from repository by given some
     *
     * @param some
     * @return boolean
     */
    @Override
    public boolean delete(Url some) {
        boolean result = false;
        if (urlRepository.findById(some.getId()).isPresent()) {
            urlRepository.delete(some);
            result = true;
        }
        return result;
    }

    /**
     * Return url by given id
     *
     * @param id
     * @return Url
     */
    @Override
    public Optional<Url> findById(int id) {
        return urlRepository.findById(id);
    }

    /**
     * Return all urls from repository
     *
     * @return List<Url>
     */
    @Override
    public List<Url> findAll() {
        return urlRepository.findAll();
    }
}
