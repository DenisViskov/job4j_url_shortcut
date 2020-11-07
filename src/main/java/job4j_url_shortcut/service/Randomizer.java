package job4j_url_shortcut.service;

/**
 * Interface of randomizer
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.11.2020
 */
public interface Randomizer<T> {
    /**
     * Method should return random object
     *
     * @return T
     */
    T getRandom();
}
