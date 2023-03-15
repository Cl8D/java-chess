package chess.domain.camp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CampTest {

    @ParameterizedTest(name = "잘못된 길이의 위치 정보가 주어지면, 예외가 발생한다.")
    @ValueSource(strings = {"", "a", "a23", "a234"})
    void createLengthFail(final String input) {
        assertThatThrownBy(() -> Camp.create(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 입력입니다.");
    }

    @ParameterizedTest(name = "잘못된 열의 위치 정보가 주어지면, 예외가 발생한다.")
    @ValueSource(strings = {"i1", "i2", "I1", "I2", "00"})
    void createColumnRangeFail(final String input) {
        assertThatThrownBy(() -> Camp.create(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 위치 열은 a~h 사이여야 합니다.");
    }

    @ParameterizedTest(name = "잘못된 행의 위치 정보가 주어지면, 예외가 발생한다.")
    @ValueSource(strings = {"a0", "a9"})
    void createRowRangeFail(final String input) {
        assertThatThrownBy(() -> Camp.create(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 위치 행은 1~8 사이여야 합니다.");
    }
}
