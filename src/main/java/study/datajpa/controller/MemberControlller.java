package study.datajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberControlller {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id){
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    @GetMapping("/members2/{id}")
    public String findMember(@PathVariable("id") Member member){
        return member.getUsername();
    }

    @GetMapping("/members")
    public Page<MemberDto> list(@PageableDefault(size = 5) Pageable pageable){
        //Spring Data JPA의 기능을 Spring Boot에서 세팅
        //Controller에서 파라미터가 바인딩 될 때,
        //Pageable을 갖고 있다면,
        //해당 인터페이스 구현체인 PageRequest를 injection
        return memberRepository.findAll(pageable)
                .map(MemberDto::new);
    }

    @PostConstruct //의존성 주입 후 초기화 -> 단 한번만 호출 보장
    public void init(){
        for (int i=0; i<100; i++){
            memberRepository.save(new Member("user" + i, i));
        }
    }
}
