package hello.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import hello.Roll;
import hello.repository.DoubleRepository;

@Service
public class DoubleService {
    
    @Autowired
    private DoubleRepository repository;

    public void save(Roll roll) {
        repository.save(roll);
    }

    public List<Roll> fetch(int qtd) {
        Page<Roll> page = repository.findAll(
  PageRequest.of(0, qtd, Sort.by(Sort.Direction.ASC, "created")));

  return page.get().collect(Collectors.toList());
    }
    
}
