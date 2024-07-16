package gift.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import gift.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    private Member member;
    private Member savedMember;

    @BeforeEach
    public void setUp() {
        member = new Member(1L, "kbm", "kbm@kbm.com", "mbk", "user");
        savedMember = memberRepository.save(member);
    }


    @Test
    void testSave() {
        assertAll(
            () -> assertThat(savedMember.getId()).isNotNull(),
            () -> assertThat(savedMember.getName()).isEqualTo(member.getName()),
            () -> assertThat(savedMember.getEmail()).isEqualTo(member.getEmail()),
            () -> assertThat(savedMember.getPassword()).isEqualTo(member.getPassword()),
            () -> assertThat(savedMember.getRole()).isEqualTo(member.getRole())
        );
    }

    @Test
    void testFindByEmail() {
        Member foundMember = memberRepository.findByEmail(member.getEmail());
        assertAll(
            () -> assertThat(foundMember).isNotNull(),
            () -> assertThat(foundMember.getId()).isEqualTo(savedMember.getId())
        );
    }

    @Test
    void testSaveWithNullName() {
        try {
            Member nullNameMember = new Member(1L, null, "kbm@kbm.com", "mbk", "user");
            memberRepository.save(nullNameMember);
        } catch (IllegalArgumentException e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void testSaveWithEmptyName() {
        try {
            Member emptyNameMember = new Member(1L, "", "kbm@kbm.com", "mbk", "user");
            memberRepository.save(emptyNameMember);
        } catch (IllegalArgumentException e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void testSaveWithNullEmail() {
        try {
            Member nullEmailMember = new Member(1L, "kbm", null, "mbk", "user");
            memberRepository.save(nullEmailMember);
        } catch (IllegalArgumentException e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void testSaveWithEmptyEmail() {
        try {
            Member emptyEmailMember = new Member(1L, "kbm", "", "mbk", "user");
            memberRepository.save(emptyEmailMember);
        } catch (IllegalArgumentException e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void testSaveWithInvalidName() {
        try {
            Member invalidEmailMember = new Member(1L, "kbm", "kbm", "mbk", "user");
            memberRepository.save(invalidEmailMember);
        } catch (IllegalArgumentException e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void testSaveWithNullPassword() {
        try {
            Member nullPasswordMember = new Member(1L, "kbm", "kbm@kbm.com", null, "user");
            memberRepository.save(nullPasswordMember);
        } catch (IllegalArgumentException e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void testSaveWithEmptyPassword() {
        try {
            Member emptyPasswordMember = new Member(1L, "kbm", "kbm@kbm.com", "", "user");
            memberRepository.save(emptyPasswordMember);
        } catch (IllegalArgumentException e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }
}