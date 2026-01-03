package com.example.localBud.integration;

import com.example.localBud.client.AiClient;
import com.example.localBud.dto.BusinessContextResponse;
import com.example.localBud.dto.CreateBusinessContextRequest;
import com.example.localBud.entity.Business;
import com.example.localBud.entity.BusinessContext;
import com.example.localBud.repository.BusinessContextRepository;
import com.example.localBud.repository.BusinessRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "ai.enabled=false"
)
@AutoConfigureWebTestClient
@Import(BusinessContextServiceIntegrationTest.AiTestConfig.class)
public class BusinessContextServiceIntegrationTest {

    private static final Long BUSINESS_ID = 1L;
    private static final String TEXT = "Some random text";

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private BusinessContextRepository businessContextRepository;
    @Autowired
    private BusinessRepository businessRepository;

    @BeforeEach
    public void cleanDb() {
        businessContextRepository.deleteAll();
    }

    @TestConfiguration
    static class AiTestConfig {

        @Bean
        @Primary
        public AiClient aiClient() {
            return prompt -> "TEST_AI_RESPONSE";
        }
    }

    @Test
    void shouldCreateContextForBusiness() {
        var request = new CreateBusinessContextRequest("Crypto Exchange FAQ", "Services", "eng", "Some random text", "manual"
        );

        var response = webTestClient.post()
                .uri("/api/business/1/context")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(BusinessContextResponse.class)
                .returnResult()
                .getResponseBody();

        if (response != null) {
            assertThat(response.title()).isEqualTo(request.title());
            assertThat(response.category()).isEqualTo(request.category());
        }

        var repositoryAll = businessContextRepository.findAll();
        assertThat(repositoryAll).hasSize(1);

    }

    @Test
    void shouldGetBusinessContextById() {
        var business = Business.builder()
                .name("Test Business").build();

        businessRepository.save(business);

        var context = BusinessContext.builder()
                .business(business)
                .text(TEXT)
                .build();

        businessContextRepository.save(context);

        var response = webTestClient.get()
                .uri("/api/business/{businessId}/context", BUSINESS_ID)
                .exchange()
                .expectBody(BusinessContextResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(response).isNotNull();
        assertThat(response.text()).isEqualTo(TEXT);

    }
}
