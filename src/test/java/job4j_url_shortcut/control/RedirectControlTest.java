package job4j_url_shortcut.control;

import job4j_url_shortcut.Job4jUrlShortcutApplication;
import job4j_url_shortcut.domain.Url;
import job4j_url_shortcut.repository.UrlRepository;
import job4j_url_shortcut.service.Redirect;
import job4j_url_shortcut.service.Statistic;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Job4jUrlShortcutApplication.class)
@AutoConfigureMockMvc
class RedirectControlTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UrlRepository repository;

    @Test
    void getUrl() throws Exception {
        when(repository.findByCode(anyString())).thenReturn(Optional.of(new Url(0, "url", "asd", 1)));
        mockMvc.perform(get("/redirect/asdasfgh"))
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("url"))
                .andExpect(status().is3xxRedirection());
    }
}