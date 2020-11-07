package job4j_url_shortcut.service;

import job4j_url_shortcut.domain.RegistrationData;
import job4j_url_shortcut.domain.Site;
import job4j_url_shortcut.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Class is a site service
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.11.2020
 */
@Service
public class SiteService implements Registration<Site, RegistrationData, String> {
    /**
     * Site repository
     */
    private final SiteRepository repository;
    /**
     * Password encoder
     */
    private final PasswordEncoder encoder;

    @Autowired
    public SiteService(SiteRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    /**
     * Method add new site
     *
     * @param some
     * @return Site
     */
    @Override
    public Site add(Site some) {
        return repository.save(some);
    }

    /**
     * Method execute check by contain site in repository
     * and return special object for answer
     *
     * @param some
     * @return RegistrationData
     */
    @Override
    public RegistrationData addSite(Site some) {
        Optional<Site> siteBox = repository.findBySite(some.getSite());
        RegistrationData result = new RegistrationData(getRandom(), getRandom(), true);
        siteBox.ifPresentOrElse(site -> {
            result.setLogin(site.getLogin());
            result.setPassword(site.getPassword());
            result.setRegistered(false);
        }, () -> {
            some.setLogin(result.getLogin());
            some.setPassword(encoder.encode(result.getPassword()));
            repository.save(some);
        });
        return result;
    }

    /**
     * Method execute update some in repository
     *
     * @param some
     * @return boolean
     */
    @Override
    public boolean update(Site some) {
        boolean result = false;
        if (findById(some.getId()).isPresent()) {
            repository.save(some);
            result = true;
        }
        return result;
    }

    /**
     * Method execute delete some from repository
     *
     * @param some
     * @return boolean
     */
    @Override
    public boolean delete(Site some) {
        boolean result = false;
        if (findById(some.getId()).isPresent()) {
            repository.delete(some);
            result = true;
        }
        return result;
    }

    /**
     * Method return site by given id
     *
     * @param id
     * @return Optional<Site>
     */
    @Override
    public Optional<Site> findById(int id) {
        return repository.findById(id);
    }

    /**
     * Method return all sites from repository
     *
     * @return List<Site>
     */
    @Override
    public List<Site> findAll() {
        return repository.findAll();
    }

    /**
     * Method generate random string
     *
     * @return String
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
}
