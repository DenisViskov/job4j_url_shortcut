package job4j_url_shortcut.repository;

import job4j_url_shortcut.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Интерфейс реализующий способность
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.11.2020
 */
public interface UrlRepository extends JpaRepository<Url, Integer> {
    Optional<Url> findByUrl(String url);
}
