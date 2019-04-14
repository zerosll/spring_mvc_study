package com.cafe24.springmvcstudy;

import com.cafe24.springmvcstudy.welcome.controller.WelcomeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssertjTotalTest {

    @Test
    public void  assertJTest (){
        assertThat("Hello, world! Nice to meet you.") // 주어진 "Hello, world! Nice to meet you."라는 문자열은
                .isNotEmpty() // 비어있지 않고
                .contains("Nice") // "Nice"를 포함하고
                .contains("world") // "world"도 포함하고
                .doesNotContain("ZZZ") // "ZZZ"는 포함하지 않으며
                .startsWith("Hell") // "Hell"로 시작하고
                .endsWith("u.") // "u."로 끝나며
                .isEqualTo("Hello, world! Nice to meet you."); // "Hello, world! Nice to meet you."과 일치합니다.
    }

    @Test
    public  void assertJTest02() {
        assertThat(3.14d) // 주어진 3.14라는 숫자는
                .isPositive() // 양수이고
                .isGreaterThan(3) // 3보다 크며
                .isLessThan(4) // 4보다 작습니다
                .isEqualTo(3, offset(1d)) // 오프셋 1 기준으로 3과 같고
                .isEqualTo(3.1, offset(0.1d)) // 오프셋 0.1 기준으로 3.1과 같으며
                .isEqualTo(3.14); // 오프셋 없이는 3.14와 같습니다
    }
}
