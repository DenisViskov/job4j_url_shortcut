package job4j_url_shortcut.control;

import job4j_url_shortcut.domain.Url;
import job4j_url_shortcut.service.Repository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 06.11.2020
 */
@RestController
@RequestMapping("/url")
public class UrlControl {
    private final Repository service;

    public UrlControl(@Qualifier("urlService") Repository service) {
        this.service = service;
    }

    @PostMapping("/convert")
    public ResponseEntity<Url> convert(@RequestBody Url url) {
        return new ResponseEntity<>((Url) service.add(url),
                HttpStatus.OK
        );
    }
}
