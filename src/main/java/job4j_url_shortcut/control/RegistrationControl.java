package job4j_url_shortcut.control;

import job4j_url_shortcut.domain.RegistrationData;
import job4j_url_shortcut.domain.Site;
import job4j_url_shortcut.service.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 05.11.2020
 */
@RestController
@RequestMapping("/registration")
public class RegistrationControl {
    private final Registration service;

    @Autowired
    public RegistrationControl(Registration service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<RegistrationData> create(@RequestBody Site site) {
        RegistrationData result = (RegistrationData) service.addSite(site);
        return new ResponseEntity<>(result,
                result.isRegistered() ? HttpStatus.CREATED : HttpStatus.FOUND
        );
    }
}
