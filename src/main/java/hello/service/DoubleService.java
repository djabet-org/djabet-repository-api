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

    public List<Roll> fetch(int qtd, String sort, String platform) {
        System.out.println("creu "+platform);
        // return repository.findByPlatform(platform);
        Sort.Direction sortDirection = "asc".equals(sort) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Page<Roll> page = repository.findByPlatform(platform, PageRequest.of(0, qtd, Sort.by(sortDirection, "created")));

  return page.get().collect(Collectors.toList());
    }
    
}
