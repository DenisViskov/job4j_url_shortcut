package job4j_url_shortcut.repository;

import job4j_url_shortcut.domain.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface of Site repository
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.11.2020
 */
public interface SiteRepository extends JpaRepository<Site, Integer> {
    /**
     * Method should return site by given name
     *
     * @param name
     * @return Optional<Site>
     */
    Optional<Site> findBySite(String name);

    /**
     * Method should return Site by given login
     *
     * @param login
     * @return Site
     */
    Site findByLogin(String login);
}
