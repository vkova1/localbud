package com.example.localBud.integration;

import com.example.localBud.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class BusinessServiceIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private BusinessRepository businessRepository;

//    @Test
//    void shouldRegisterBusiness() {
//        var request = new RegisterBusinessRequest(
//                "Local business",
//                "Currency exchange services",
//                "random uri"
//        );
//
//        var response = webTestClient.post()
//                .uri("/api/business/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(request)
//                .exchange()
//                .expectStatus()
//                .isOk()
//                .expectBody(RegisterBusinessResponse.class)
//                .returnResult()
//                .getResponseBody();
//
//        assertThat(Objects.requireNonNull(response).name()).isEqualTo("Local business");
//
//        var repositoryAll = businessRepository.findAll();
//        assertThat(repositoryAll).hasSize(1);
//
//    }
}
