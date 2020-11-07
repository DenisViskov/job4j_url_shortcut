package job4j_url_shortcut.config;

import job4j_url_shortcut.domain.Site;
import job4j_url_shortcut.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

/**
 * Class is an user detail service
 *
 * @author Денис Висков
 * @version 1.0
 * @since 07.11.2020
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    /**
     * Repository
     */
    private final SiteRepository repository;

    @Autowired
    public UserDetailsServiceImpl(SiteRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Site site = repository.findByLogin(s);
        if (site == null) {
            throw new UsernameNotFoundException(s);
        }
        return new User(site.getLogin(), site.getPassword(), emptyList());
    }
}
