package job4j_url_shortcut.service;

import java.util.Optional;

/**
 * Interface of Registration
 * Extends from Repository and Randomizer interfaces
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.11.2020
 */
public interface Registration<V, K, T> extends Repository<V>, Randomizer<T> {
    /**
     * Method should add site to repository
     *
     * @param some
     * @return K
     */
    K addSite(V some);
}
