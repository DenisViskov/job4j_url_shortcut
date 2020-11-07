package job4j_url_shortcut.service;

import java.util.Optional;

/**
 * Interface of redirect
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.11.2020
 */
public interface Redirect<V> {
    /**
     * Method should return any by given code
     *
     * @param code
     * @return V
     */
    Optional<V> getRedirect(String code);
}
