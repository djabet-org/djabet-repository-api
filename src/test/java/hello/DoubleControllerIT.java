package hello;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

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
    public void shouldSaveRoll() {

      Roll newRoll = Roll.builder()
      .color("red")
      .id(1L)
      .platform("blaze")
      .roll(5)
      .build();

      ResponseEntity<String> response = saveRoll(newRoll);
      assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());

    }
@Test
public void throw400BadRequest_WhenNull() {
      Roll newRoll = Roll.builder()
      .color("red")
      .build();

      ResponseEntity<String> response = saveRoll(newRoll);

      assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
}

@Test
public void shouldSendServerEventAfterSave() {

      ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:" + port + "/api/double/sse", String.class);
}

private ResponseEntity<String> saveRoll(Roll roll) {
       HttpEntity<Roll> request = new HttpEntity<>(roll, null);
      return this.restTemplate.postForEntity("http://localhost:" + port + "/api/double/save", request, String.class);

}
}