package job4j_url_shortcut.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 06.11.2020
 */
@Entity
@Table(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "url")
    private String url;
    @Column(name = "code")
    private String code;

    public Url() {
    }

    public Url(int id, String url, String code) {
        this.id = id;
        this.url = url;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url1 = (Url) o;
        return id == url1.id &&
                Objects.equals(url, url1.url) &&
                Objects.equals(code, url1.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, code);
    }
}
