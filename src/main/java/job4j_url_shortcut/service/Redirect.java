package job4j_url_shortcut.service;

import java.util.Optional;

/**
 * Интерфейс реализующий способность
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.11.2020
 */
public interface Redirect<V> {
    Optional<V> getRedirect(String code);
}
