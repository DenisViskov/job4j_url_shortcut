package job4j_url_shortcut.service;

import java.util.Optional;

/**
 * Интерфейс реализующий способность
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.11.2020
 */
public interface SiteRepositoryService<V, K, T> extends RepositoryService<V>, Randomizer<T> {
    K addSite(V some);
    Optional<V> findBySiteName(String name);
}
