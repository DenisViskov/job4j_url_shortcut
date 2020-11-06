package job4j_url_shortcut.service;

import java.util.List;

/**
 * Интерфейс реализующий способность
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.11.2020
 */
public interface Statistic<V, T> {
    List<V> getStats(String site);

    void incrementTotal(T some);
}
