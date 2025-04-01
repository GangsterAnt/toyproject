기초적인 페이지네이션을 구현해보기?

댓글 구조

spring securitxy refer // https://github.com/teamesa/kilometer_backend/blob/dev/kilometer-backend-api/src/main/java/com/kilometer/backend/configuration/SecurityConfiguration.java#L70
스프링 시큐리티 설정 확인

thymeleaf refer //


3.23

- 대댓글 구조 단순화 + 페이지네이션 (root comment 기준 n개, 인덱싱) [v]
- docker compose로 여러개 도커 (mysql, board-api,...) 동시 띄우는 기능 설정 (like dev zone)'
  - board-api [v]
  - board-admin -> 뭔가 꼬여서 mysql Dialect?? 에러 발생해서 compose 로 띄우는게 안됨
    - application 은 돌아가는데 docker 기반으로는 안돌아감. (mysql dialect 에러)
- board-admin 만들어서 간단히 thymleaf 써보기
    - 기능 :
        - post_id, post_title, visibility (?? 보이기 숨기기)
          - hidden field 생성해서 쿼리에서 제거하는 where문을 만듬.
          - select문에서 빼는 where로직 추가. (몇몇군데는 그냥 로직상에서 필터링 -> todo 쿼리레벨로 내리기)
    - soft delete (실제 삭제 x 고객들에게 비노출) -> 구현 방향. (column 추가 필요)
        - 이후 배치잡으로 실제 삭제하는 기능도 구현할 수 있다. [3.30 투두 리스트]
        - 유저 탈퇴 후 재가입 패딩 (기록보관 내부 문서?) 두는 뭐 기타 등등. [넘기자]
          - deletedAt필드 추가, api콜하면 deletedAt 필드를 업데이트


3.30th 
## P0
- 페이지네이션
  - 댓글 페이지네이션
  - 포스트 getAll 페이지네이션
  - 공통
    - order by created_at desc, Asc
    - limit 20/50/100
    - page number


- 하드 딜리트는 배치로만 동작하게 구현 (1년? )
  - 배치잡 을 위해서 인덱싱이 필요함 (deletedAt 필드 index 추가)
- board-admin : finish docker-compose issue
- 댓글 페이지네이션 
  - 댓글 api를 따로 뺴서 페이지네이션 하는것도 좋아보인다.
    - n+1 problem
      - n개의 닷글을 가져오고, 각 n개에 대한 대댓글을 가져오는 방식 (마찬가지로 n개, 페이지네이션)
      - 쿼리dsl??
- board-api 전체 포스트목록 불러오기 구현.
- board-api 포스트 없을떄 핸들링 로직 처리
- exception handler 추가 (글로벌 익셉션 핸들러)
- 현재 db 다 인덱싱 해놓기