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
 * @author Денис Висков
 * @version 1.0
 * @since 05.11.2020
 */
@Service
public class SiteService implements SiteRepositoryService<Site, RegistrationData> {
    private final SiteRepository repository;
    private final PasswordEncoder encoder;

    @Autowired
    public SiteService(SiteRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public Site add(Site some) {
        return repository.save(some);
    }

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

    @Override
    public boolean update(Site some) {
        boolean result = false;
        if (findById(some.getId()).isPresent()) {
            repository.save(some);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Site some) {
        boolean result = false;
        if (findById(some.getId()).isPresent()) {
            repository.delete(some);
            result = true;
        }
        return result;
    }

    @Override
    public Optional<Site> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Site> findAll() {
        return repository.findAll();
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
}
