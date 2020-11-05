package job4j_url_shortcut.service;

/**
 * Интерфейс реализующий способность
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.11.2020
 */
public interface SiteRepositoryService<V, K> extends RepositoryService<V> {
    K addSite(V some);
    String getRandom();
}
