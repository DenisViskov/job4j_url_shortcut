package job4j_url_shortcut.service;

import java.util.List;
import java.util.Optional;

/**
 * Interface of repository
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.11.2020
 */
public interface Repository<V> {
    /**
     * Method should add some to repository
     *
     * @param some
     * @return V
     */
    V add(V some);

    /**
     * Method should update some to repository
     *
     * @param some
     * @return boolean
     */
    boolean update(V some);

    /**
     * Method should delete some from repository
     *
     * @param some
     * @return boolean
     */
    boolean delete(V some);

    /**
     * Method should return V by given id
     *
     * @param id
     * @return V
     */
    Optional<V> findById(int id);

    /**
     * Method should return all elements from repository
     *
     * @return List<V>
     */
    List<V> findAll();
}
