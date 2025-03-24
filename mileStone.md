기초적인 페이지네이션을 구현해보기?

댓글 구조

spring securitxy refer // https://github.com/teamesa/kilometer_backend/blob/dev/kilometer-backend-api/src/main/java/com/kilometer/backend/configuration/SecurityConfiguration.java#L70
스프링 시큐리티 설정 확인

thymeleaf refer //


3.23

- 대댓글 구조 단순화 + 페이지네이션 (root comment 기준 n개, 인덱싱)
- docker compose로 여러개 도커 (mysql, board-api,...) 동시 띄우는 기능 설정 (like dev zone)'
- board-admin 만들어서 간단히 thymleaf 써보기
    - 기능 :
        - post_id, post_title, visibility (?? 보이기 숨기기)
    - hard delete,
    - soft delete (실제 삭제 x 고객들에게 비노출) -> 구현 방향. (column 추가 필요)
        - 이후 배치잡으로 실제 삭제하는 기능도 구현할 수 있다.
        - 유저 탈퇴 후 재가입 패딩 (기록보관 내부 문서?) 두는 뭐 기타 등등.