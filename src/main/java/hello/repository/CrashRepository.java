package hello.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import hello.Crash;

@Repository
public interface CrashRepository extends PagingAndSortingRepository<Crash, Long> {

   Page<Crash> findByPlatform(String platform, Pageable pageable);

}
