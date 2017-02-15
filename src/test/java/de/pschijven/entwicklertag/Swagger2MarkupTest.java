package de.pschijven.entwicklertag;

import io.github.robwin.markup.builder.MarkupLanguage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import springfox.documentation.staticdocs.Swagger2MarkupResultHandler;

import static de.pschijven.entwicklertag.SwaggerConfiguration.GROUP_NAME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Generates the Swagger documentation in ASCIIDOC format.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SwaggerUiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Swagger2MarkupTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void generateAsciiDoc() throws Exception {
        mockMvc.perform(get("/v2/api-docs?group=" + GROUP_NAME).accept(MediaType.APPLICATION_JSON))
                .andDo(Swagger2MarkupResultHandler
                        .outputDirectory("swaggerdoc")
                        .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                        .build()
                )
                .andExpect(status().isOk());
    }
}