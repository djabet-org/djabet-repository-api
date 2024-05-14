import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import hello.Application;
import hello.Roll;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
public class DoubleControllerIT
 {

  @LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

    @Test
    public void test() {

      Roll newRoll = Roll.builder()
      .color("red")
      .created("2023-10-04T18:23:50.851418761-03:00")
      .id(1L)
      .platform("blaze")
      .roll("5")
      .build();

       HttpEntity<Roll> request = new HttpEntity<>(newRoll, null);
      ResponseEntity<String> response = this.restTemplate.postForEntity("http://localhost:" + port + "/api/double/save", request, String.class);
      assertTrue(response.getStatusCode().is2xxSuccessful());

    }
}