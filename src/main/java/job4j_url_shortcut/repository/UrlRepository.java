package job4j_url_shortcut.repository;

import job4j_url_shortcut.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Interface of Url repository
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.11.2020
 */
@Repository
public interface UrlRepository extends JpaRepository<Url, Integer> {
    /**
     * Method should return url by given url name
     *
     * @param url
     * @return Optional<Url>
     */
    Optional<Url> findByUrl(String url);

    /**
     * Method should return Url by given code
     *
     * @param code
     * @return Optional<Url>
     */
    Optional<Url> findByCode(String code);

    /**
     * Execute increment total by given id
     *
     * @param id
     */
    @Modifying
    @Query("update Url u set u.total = u.total + 1 where u.id= ?1")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    void incrementTotal(int id);
}
