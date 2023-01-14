package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Team;

//@Repository가 없어도 인식 가능
public interface TeamRepository extends JpaRepository<Team, Long> {
}
