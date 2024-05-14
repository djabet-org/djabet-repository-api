package hello;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import hello.service.DoubleService;

@Controller
public class DoubleController {

@Autowired
private DoubleService service;

@PostMapping(path = "/api/double/save",
   consumes = MediaType.APPLICATION_JSON_VALUE,
   produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity saveRoll(@RequestBody Roll newRoll, HttpServletRequest request) {
   System.out.println("Roll received: "+newRoll);

   return service.save(newRoll).map(r -> ResponseEntity.ok().build()).orElse(ResponseEntity.status(500).build());

}}
