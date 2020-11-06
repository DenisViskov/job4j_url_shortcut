package job4j_url_shortcut.control;

import job4j_url_shortcut.Job4jUrlShortcutApplication;
import job4j_url_shortcut.domain.Site;
import job4j_url_shortcut.repository.SiteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Job4jUrlShortcutApplication.class)
@AutoConfigureMockMvc
class RegistrationControlTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    @Autowired
    private SiteRepository siteRepository;

    @Test
    @WithMockUser
    void create() throws Exception {
        when(siteRepository.findBySite("job4j.ru"))
                .thenReturn(Optional.of(new Site(0,
                        "job4j.ru",
                        null,
                        null)));
        mockMvc.perform(post("/registration/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"site\":\"job4j.ru\"}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"login\":null,\"password\":null,\"registered\":false}"))
                .andExpect(status().isCreated());
    }
}