package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
//jpa entity에서는 기본 생성자가 필요함
//access level은 protected까지 열어두어야함
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@ToString(): 객체를 만들어낼 때 출력
//연관관계 필드는 넣지 않는 것이 좋음
@ToString(of = {"id", "username", "age"})
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;


    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null){
            changeTeam(team);
        }
    }

    //연관관계 메서드
    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
