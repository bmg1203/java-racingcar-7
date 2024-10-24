package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 기능_테스트_우승자_여러명() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni,jun", "1");
                    assertThat(output()).contains("pobi : -", "woni : ", "jun : -", "최종 우승자 : pobi, jun");
                },
                MOVING_FORWARD, STOP, MOVING_FORWARD
        );
    }

    @Test
    void 기능_테스트_시도_여러번_우승자_한명() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "3");
                    //첫 번째 시도 출력
                    assertThat(output()).contains("pobi : -", "woni : -");

                    //두 번째 시도 출력
                    assertThat(output()).contains("pobi : ", "woni : -");

                    //세 번째 시도 출력
                    assertThat(output()).contains("pobi : ", "woni : ");

                    //최종 우승자 출력
                    assertThat(output()).contains("최종 우승자 : woni");
                },
                MOVING_FORWARD, MOVING_FORWARD, STOP, MOVING_FORWARD, STOP, STOP
        );
    }

    @Test
    void 기능_테스트_시도_여러번_우승자_여러명() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni,jun", "3");
                    //첫 번째 시도 출력
                    assertThat(output()).contains("pobi : -", "woni : -", "jun : ");

                    //두 번째 시도 출력
                    assertThat(output()).contains("pobi : ", "woni : ", "jun : ");

                    //세 번째 시도 출력
                    assertThat(output()).contains("pobi : ", "woni : -", "jun : ");

                    //최종 우승자 출력
                    assertThat(output()).contains("최종 우승자 : pobi, woni");
                },
                MOVING_FORWARD, MOVING_FORWARD, STOP,
                MOVING_FORWARD, STOP, STOP,
                STOP, MOVING_FORWARD, STOP
        );
    }

    @Test
    void 기능_테스트_자동차_한대_1시도() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi", "1");
                    //첫 번째 시도 출력
                    assertThat(output()).contains("pobi : -");

                    //최종 우승자 출력
                    assertThat(output()).contains("최종 우승자 : pobi");
                },
                MOVING_FORWARD
        );
    }

    @Test
    void 기능_테스트_자동차_한대_1시도_2() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi", "1");
                    //첫 번째 시도 출력
                    assertThat(output()).contains("pobi : ");

                    //최종 우승자 출력
                    assertThat(output()).contains("최종 우승자 : pobi");
                },
                STOP
        );
    }

    @Test
    void 기능_테스트_자동차_한대_2시도() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi", "2");
                    //첫 번째 시도 출력
                    assertThat(output()).contains("pobi : -");

                    //두 번째 시도 출력
                    assertThat(output()).contains("pobi : ");

                    //최종 우승자 출력
                    assertThat(output()).contains("최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    void 기능_테스트_자동차_한대_2시도_2() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi", "2");
                    //첫 번째 시도 출력
                    assertThat(output()).contains("pobi : ");

                    //두 번째 시도 출력
                    assertThat(output()).contains("pobi : ");

                    //최종 우승자 출력
                    assertThat(output()).contains("최종 우승자 : pobi");
                },
                STOP, STOP
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
