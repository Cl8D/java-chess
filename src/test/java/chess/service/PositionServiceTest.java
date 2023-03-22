package chess.service;

import chess.controller.dto.PositionDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PositionServiceTest {

    @Test
    @DisplayName("rank, file이 주어지면 위치에 대한 dto를 생성한다.")
    void createPositionDto() {
        // given
        final PositionService positionService = new PositionService();
        final int rank = 3;
        final int file = 3;

        // when, then
        final PositionDto positionDto = assertDoesNotThrow(() -> positionService.createPositionDto(rank, file));
        assertThat(positionDto)
                .isInstanceOf(PositionDto.class);
    }
}
