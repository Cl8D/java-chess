# 🪜 체스 게임 구현 미션

## Pair: 져니 [⛄️](http://github.com/cl8d), 제나 [❤️](https://github.com/yenawee)

## ✔ 기능 요구사항

## ✨ Controller

### Controller

- [x] 사용자가 입력한 명령어에 따라 게임의 상태를 확인한다.
- [x] 현재 게임이 실행 중인지 판단한다.

### EndController

- [x] 사용자가 입력한 명령어가 End가 아니라면 예외를 발생시킨다.
- [x] 현재 게임이 실행 중인지 판단한다.

### MoveController

- [x] 사용자가 입력한 명령어에 따라서 상태를 변경한다.
    - [x] 사용자가 입력한 명령어가 Start라면 예외를 발생시키고, End라면 종료한다.
    - [x] 사용자가 입력한 status거나 모든 킹이 살아있는 게 아니라면 상태를 status로 변경한다.
- [x] 사용자가 입력한 체스말을 시작 지점에서 끝 지점으로 이동시키고, 차례를 변경한다.
- [x] 현재 게임이 실행 중인지 판단한다.

### StartController

- [x] 사용자가 입력한 명령어가 move/status라면 예외를 발생시키고, End라면 종료한다.
- [x] 현재 게임이 실행 중인지 판단한다.

### StatusController

- [x] 사용자가 입력한 명령어가 start라면 예외를 발생시키고, move면 상태를 move로 변경하고, end라면 종료한다.
- [x] status일 경우 각 진영의 체스판 점수를 계산한다.
- [x] 현재 게임이 실행 중인지 판단한다.
- [x] 현재 살아있는 킹이 없다면 종료 메시지를 출력하고, end로 변경한다.

### ChessHandler

- [x] 체스 게임에 대한 전반적인 실행을 담당한다.

### Command

- [x] 사용자가 입력한 명령어에 대해서 관리한다.
- [x] 사용자가 입력한 명령어가 start, move, end인지 검증한다.
- [x] 사용자가 입력한 명령어가 start인지 확인한다.
- [x] 사용자가 입력한 명령어가 end인지 확인한다.
- [x] 사용자가 입력한 명령어가 status인지 확인한다.
- [x] 사용자가 입력한 명령어가 move일 때 올바른 명령어 길이로 들어오는지 확인한다.

### CommandType

- [x] 명령어의 종류에 대해서 관리한다. (start, move, end, status)

---

## ✨dao

### RowMapper

- [x] DB에서 조회한 결과에 대해 각 테이블에 맞는 엔티티로 매핑시킨다.

### UserDaoImpl

- [x] 사용자의 이름을 기준으로 사용자 엔티티를 조회한다.
- [x] 사용자의 정보를 삽입한다.

### ChessGameDaoImpl

- [x] 사용자의 아이디를 기준으로 체스 게임을 조회한다.
- [x] 사용자가 현재 진행 중인 체스 게임을 저장한다.
- [x] 체스 게임 아이디에 해당하는 게임의 현재 진영을 업데이트한다.
- [x] 사용자의 아이디에 해당하는 체스 게임을 제거한다.

### PieceDaoImpl

- [x] 체스 게임 아이디를 기준으로 체스말 리스트의 정보를 조회한다.
- [x] 입력으로 들어온 체스 게임에 대한 체스말의 정보를 저장한다.
- [x] 입력으로 들어온 체스 게임에 대해 특정 위치에 존재하는 체스말의 정보를 제거한다.
- [x] 체스 게임 아이디에 해당하는 체스말 정보를 제거한다

---

## ✨database

### ChessProperties

- [x] DB 연결에 필요한 프로퍼티 정보를 관리한다.

### JdbcTemplate

- [x] 쿼리에 따라 테이블에서 일치하는 결과를 1개만 조회하여 매핑된 결과를 반환한다.
- [x] 쿼리에 따라 테이블에서 일치하는 결과 전부를 조회하여 매핑된 결과를 반환한다.
- [x] 쿼리에 따라 테이블에 업데이트 연산 (삽입, 수정, 삭제)을 진행한다.

---

## ✨domain - board

### ChessBoard

- [x] 체스판을 초기화한다.
    - [x] 체스 말의 위치에 대한 정보를 관리한다.
- [x] 해당 위치에 체스말이 존재하는지 확인한다.
- [x] 특정 위치에 존재하는 체스말을 반환한다.
- [x] 특정 위치에 존재하는 체스말을 제거한다.
- [x] 특정 위치에 체스말을 둔다.
- [x] 특정 말의 종류에 따라서 시작 위치에서 종료 위치로 이동 가능한지 판단한다.
- [x] 현재 체스판에서 살아있는 킹들을 조회한다.
- [x] 입력받은 진영의 체스판을 반환한다.

### ChessBoardFactory

- [x] 체스판의 초기 상태에 대해서 초기화한다.

---

## ✨domain - chess

### ScoreVO

- [x] 각 진영의 체스 결과에 대한 점수를 담는다.

### ChessGame

- [x] 체스 게임을 진행한다.
- [x] 현재 체스 게임에서 킹이 살아있는지 판단한다.
- [x] WHITE 진영의 체스판을 반환한다.
- [x] BLACK 진영의 체스판을 반환한다.

### CampType

- [x] 사용자의 진영을 관리한다.
    - [x] 입력받은 위치의 열이 소문자면 WHITE, 대문자면 BLACK 진영으로 나눈다.
- [x] 플레이할 진영을 번갈아가며 반환한다.

### ChessScoreCalculator

- [x] 현재 체스판의 각 진영에 대한 점수를 계산한다.

---

## ✨domain - piece

### Direction

- [x] 체스말이 이동하는 방향으로 관리한다.
- [x] 모든 방향에 대해서 반환한다.
- [x] 상하좌우에 대해서 반환한다.
- [x] 상하좌우의 대각선에 대해서 반환한다.

### Location

- [x] 이동 가능한 모든 위치에 대해 관리한다.
- [x] 이동 가능한 위치에 새로운 정보를 추가한다.
- [x] 이동 가능한 위치에 입력받은 위치가 포함되어 있는지 확인한다.

### Position

- [x] 체스판 위의 위치 정보를 저장한다.
- [x] 목표 위치를 반환한다.
- [x] 위치가 입력받은 제한값을 넘어가는지 판단한다.
- [x] 목표 위치로 이동하기 위한 단위 벡터를 계산한다.
- [x] 입력받은 위치를 복사한 새로운 위치를 반환한다.
- [x] 현재 위치와 입력받은 위치의 rank의 차이를 계산한다.
- [x] 현재 위치와 입력받은 위치의 file의 차이를 계산한다.
- [x] 현재 위치의 rank가 입력받은 위치의 rank보다 큰지 판단한다.
- [x] 현재 위치의 rank과 입력받은 위치의 rank가 동일한지 판단한다.
- [x] 현재 위치와 입력받은 위치가 동일한지 판단한다.

### PositionConverter

- [x] 입력받은 source, target 위치를 가로, 세로 값으로 분리한다.
    - [x] 가로값은 왼쪽부터 0~7 세로값은 아래부터 0~7으로 변환한다.
- [x] 사용자가 입력한 위치를 검증한다.
    - [x] 입력받은 위치 명령어의 길이가 2인지 확인한다.
    - [x] 첫 번째 글자가 a~h, 두 번째 글자가 1~8인지 확인한다.

### Score

- [x] 점수에 대해서 관리한다.
- [x] 인자로 들어온 점수를 합한 값을 반환한다.
- [x] 인자로 들어온 점수를 뺀 값을 반환한다.
- [x] 인자로 들어온 횟수만큼 곱한 값을 반환한다.

### Piece

- [x] 체스말이 어떤 진영에 속하는지 관리한다.
- [x] 두 개의 체스말이 동일한 진영에 속하는지 판단한다.
- [x] 체스말이 입력받은 진영에 속하는지 판단한다.
- [x] 체스말이 시작 위치에서 도착 위치로 이동 가능한지 판단한다.
- [x] 체스말이 공격 가능한지 판단한다.
- [x] 체스말이 킹인지 판단한다.
- [x] 체스말이 폰인지 판단한다.

### PieceType

- [x] 체스말의 종류를 관리한다.
- [x] 특정 체스말이 시작 위치에서 종료 위치까지 이동 가능한지 판단한다.
- [x] 각 체스말의 점수에 대해 관리한다.

### Movable

- [x] 체스말의 움직임을 관리한다.
- [x] 체스말이 공격 가능한지 판단한다.

### Move

- [x] 입력받은 위치로 이동시킨다.
- [x] 체스말이 이동 가능한 위치를 전부 반환한다.

### Queen

- [x] 입력받은 위치에 대해서 퀸이 이동 가능한지 판단한다.

### Rook

- [x] 입력받은 위치에 대해서 룩이 이동 가능한지 판단한다.

### Bishop

- [x] 입력받은 위치에 대해서 비숍이 이동 가능한지 판단한다.

### King

- [x] 입력받은 위치에 대해서 킹이 이동 가능한지 판단한다.

### Knight

- [x] 입력받은 위치에 대해서 나이트가 이동 가능한지 판단한다.

### Pawn

- [x] 입력받은 출발 위치의 행보다 도착 위치의 행이 더 크면 UP 방향, 아니면 DOWN 방향으로 이동할 수 있는지 판단한다.

---

## ✨domain - user

### User

- [x] 사용자의 이름이 20자 초과라면 예외를 발생시킨다.

---

## ✨domain - entity

### UserEntity

- [x] user (사용자) 테이블에서 조회한 결과에 대해 담는다.

### ChessGameEntity

- [x] chess_game (체스 게임) 테이블에서 조회한 결과에 대해 담는다.

---

## ✨domain - service

### UserService

- [x] 사용자가 입력한 이름이 이미 존재한다면 해당 사용자의 아이디를, 아니라면 새롭게 삽입 후 생성된 아이디를 반환한다.
- [x] 사용자의 이름을 바탕으로 사용자의 정보를 삽입한다.

### ChessGameService

- [x] 사용자의 아이디에 해당하는 체스 게임이 존재하면 해당 게임을 반환하고, 아니라면 새 게임을 반환한다.
- [x] 사용자의 아이디를 기준으로 체스 게임을 조회한다.

### ChessBoardService

- [x] 체스 게임 아이디를 기준으로 체스판 정보를 조회한다.

---

## ✨domain - view

### InputView

- [x] 명령어를 입력받는다.

### OutputView

- [x] 게임 안내 메시지를 출력한다.
- [x] 체스판에 있는 체스말을 출력하고, 비어있는 곳은 .으로 출력한다.
- [x] 각 진영의 점수와 이긴 진영에 대해서 출력한다.

### PieceName

- [x] 체스말의 종류에 따라 체스말의 이름으로 변환한다.

---

## ✔ 프로그래밍 요구사항

- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
    - 기본적으로 Java Style Guide을 원칙으로 한다.
- indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
    - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
    - 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
- 3항 연산자를 쓰지 않는다.
- else 예약어를 쓰지 않는다.
    - else 예약어를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
    - 힌트: if문에서 값을 반환하는 방식으로 구현하면 else 예약어를 사용하지 않아도 된다.
- 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
    - 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분한다.
    - UI 로직을 InputView, ResultView와 같은 클래스를 추가해 분리한다.
- 함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현한다.
    - 함수(또는 메소드)가 한 가지 일만 하도록 최대한 작게 만들어라.
- 배열 대신 컬렉션을 사용한다.
- 모든 원시 값과 문자열을 포장한다
- 줄여 쓰지 않는다(축약 금지).
- 일급 컬렉션을 쓴다.
- 모든 엔티티를 작게 유지한다.
- 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- 도메인의 의존성을 최소한으로 구현한다.
- 한 줄에 점을 하나만 찍는다.
- 게터/세터/프로퍼티를 쓰지 않는다.
- 모든 객체지향 생활 체조 원칙을 잘 지키며 구현한다.
- 프로그래밍 체크리스트의 원칙을 지키면서 프로그래밍 한다.
