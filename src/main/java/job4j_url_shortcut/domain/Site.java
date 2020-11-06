package job4j_url_shortcut.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 05.11.2020
 */
@Entity
@Table(name = "sites")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "site")
    private String site;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Url> urls;

    public Site() {
        urls = new HashSet<>();
    }

    public Site(int id, String site, String login, String password) {
        this.id = id;
        this.site = site;
        this.login = login;
        this.password = password;
        urls = new HashSet<>();
    }

    public void addUrl(Url url) {
        urls.add(url);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Url> getUrls() {
        return urls;
    }

    public void setUrls(Set<Url> urls) {
        this.urls = urls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Site site1 = (Site) o;
        return id == site1.id &&
                Objects.equals(site, site1.site) &&
                Objects.equals(login, site1.login) &&
                Objects.equals(password, site1.password) &&
                Objects.equals(urls, site1.urls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, site, login, password, urls);
    }
}
