package job4j_url_shortcut.control;

import job4j_url_shortcut.service.Redirect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Class is a redirect rest controller
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.11.2020
 */
@RestController
@RequestMapping("/redirect")
public class RedirectControl {
    /**
     * Redirect service
     */
    private final Redirect service;

    @Autowired
    public RedirectControl(Redirect service) {
        this.service = service;
    }

    /**
     * Returns originally URL by given code
     *
     * @param code
     * @return String
     */
    @GetMapping("/{code}")
    public ResponseEntity<String> getUrl(@PathVariable("code") String code) {
        Optional<String> redirect = service.getRedirect(code);
        return new ResponseEntity<>(redirect
                .orElseGet(() -> ""),
                redirect.isPresent() ? HttpStatus.resolve(302) : HttpStatus.NOT_FOUND
        );
    }
}
