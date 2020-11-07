package job4j_url_shortcut.service;

import java.util.List;

/**
 * Interface of Statistic
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.11.2020
 */
public interface Statistic<V, T> {
    /**
     * Returns statistic data by given site name
     *
     * @param site
     * @return List<V>
     */
    List<V> getStats(String site);

    /**
     * Method perform increment total calls on given some object
     *
     * @param some
     */
    void incrementTotal(T some);
}
