package ru.vyukov.bakapa.controller.controller.priv;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.hypermedia.LinksSnippet;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import ru.vyukov.bakapa.controller.domain.DetectionConfig;
import ru.vyukov.bakapa.controller.service.detection.DetectionService;

import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = DetectionPrivateApiController.class)
@Import(value = WebMvcTestConfig.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class DetectionPrivateApiControllerTest {


	protected final LinksSnippet pagingLinks = links(
			linkWithRel("next").optional().description("The next page of results"),
			linkWithRel("prev").optional().description("The previous page of results"));

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DetectionService detectionService;

	@Test
	public void testGetDetectionConfig() throws Exception {
		DetectionConfig config = new DetectionConfig();
		when(detectionService.getDetectionConfig()).thenReturn(config);

		mockMvc.perform(get("/private/detection/config"))//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.mongoPorts").isArray())
				//
				.andDo(document("detectionConfig",

						responseFields(
								fieldWithPath("mongoPorts").description("MongoDb ports"),
								fieldWithPath("mysqlPorts").description("MySql ports"),
								fieldWithPath("postgresqlPorts").description("Postgres ports")
								)
				)
		);
	}

}
