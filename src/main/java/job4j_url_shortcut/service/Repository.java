package job4j_url_shortcut.service;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс реализующий способность
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.11.2020
 */
public interface Repository<V> {
    V add(V some);

    boolean update(V some);

    boolean delete(V some);

    Optional<V> findById(int id);

    List<V> findAll();
}
