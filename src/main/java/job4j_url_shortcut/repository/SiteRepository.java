package job4j_url_shortcut.repository;

import job4j_url_shortcut.domain.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Интерфейс реализующий способность
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.11.2020
 */
public interface SiteRepository extends JpaRepository<Site, Integer> {
    Optional<Site> findBySite(String name);
}
