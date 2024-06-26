package hello.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import hello.Crash;
    import hello.repository.CrashRepository;

@Service
public class CrashService {
    
    @Autowired
    private CrashRepository repository;

    public void save(Crash crash) {
        repository.save(crash);
    }

    public List<Crash> fetch(int qtd, String sort, String platform) {
        // return repository.findByPlatform(platform);
        Sort.Direction sortDirection = "asc".equals(sort) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Page<Crash> page = repository.findByPlatform(platform, PageRequest.of(0, qtd, Sort.by(sortDirection, "created")));

  return page.get().collect(Collectors.toList());
    }
    
}
