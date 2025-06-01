기초적인 페이지네이션을 구현해보기?

댓글 구조

spring securitxy refer // https://github.com/teamesa/kilometer_backend/blob/dev/kilometer-backend-api/src/main/java/com/kilometer/backend/configuration/SecurityConfiguration.java#L70
스프링 시큐리티 설정 확인

thymeleaf refer //


3.23

- 대댓글 구조 단순화 + 페이지네이션 (root commentEntity 기준 n개, 인덱싱) [v]
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
  - [x] 배치잡 을 위해서 인덱싱이 필요함 (deletedAt 필드 index 추가)
- board-admin : finish docker-compose issue 
- 댓글 페이지네이션
  - 댓글 api를 따로 뺴서 페이지네이션 하는것도 좋아보인다. [v]
    - n+1 problem
      - n개의 닷글을 가져오고, 각 n개에 대한 대댓글을 가져오는 방식 (마찬가지로 n개, 페이지네이션) [v]
      - 쿼리dsl??
- board-api 전체 포스트목록 불러오기 구현. [v]
- board-api 포스트 없을떄 핸들링 로직 처리
  - 404 엔티티로 감싸기 [v]
- exception handler 추가 (글로벌 익셉션 핸들러) [x]
- 현재 db 다 인덱싱 해놓기
  - created_at [v]
  - deleted_at [v]


4/12
- 의미있게 고민하기 (P0)
  - restful API 부터 쪼개기 (역할 중심)
- batch-hardDelete 구현 (p2)
- URL 설계를 좀더 RestAPI ful하게 만들어보기.
  - 이게 가장 표준이 된다. - 역할을 유추할 수 있게 하는 표준.
    - 예시 ) graphql을 네이티브가 선호하는 이유 -> 뭐가 올지 예상이 된다.
  - CRUD 짜기. 현재 도메인식으로 짜고있지않음 ->
    - 현재 -> CRUD 처럼 컨트롤러가 묶여있지만.
    - 이후에는 도메인 롤에 맞게. postEntity, commentEntity, etc...
      - CRUD는 기능확장에 유연성이 떨어진다. 너무 큰 4개로만 계속 묶여있을테니 분리가 안될것.
      - 기능의 토픽에 대해서 묶어 분리하자. i.e) Bo -> post,comment... 를 postEntity->Bo,dto, 
- getAllPost 안에 Comment number update.
- docker-compose 는 대면으로 마무리 짓기.
- QueryDsl 대면수업 ㅋㅋㅋ
  - 예습해가기 (myBastis)
  - UPDATE -> SAVE 로 바꾸기 
    - 이후 Wrpper가 필요없어질수도 있다.
- Server error : Cannot invoke "com.example.board.service.post.Post.getPostId()" because "postEntity" is null
  - 시스템 정보 (i.e 구조 노출 ) 노출 방지.
- catch(Exception e) 쓰지말기. 특정 예외만 잡도록.
  - 나중에 만드는 커스텀 예외가 중간에 잡혀버리는 문제가 발생할 수 있다.

-현재 Bo는 존재 의미가 없다
  search itemWrapper -> productItem -> displayItem 은 쿠가 도메인들을 가로롤 잘랐기 때문에 이런 갭이 발생한다.
  만약 entity 가 직접 수정되므로써 db side effect를 방지 (실수로 값이 수정됨)하기 위해서 Post ->postEntity 객체 를 만든다는것은이해가 된다.
 


4/13
 - DDD 처럼 만들어보자 / 혹은 테이블 처럼 만들어보자
   - Bean은 생성만, 로직은 object가 / Entity post 
 - 대댓글 안보여주고, 존재여부에 따라서 api 호출 가능정도만 (유튜브
   - 대댓글 갯수 를 보여주자.
 - 왜 Post와 PostEntity를 나눴는가?
   - PostEntity는 db에 직접적으로 쓰일수 있는 가능성이 있는 객체,
   - 도메인은 누군가 익숙치 않은 사람들이 들어올수 있는 가능성이 있다.
   - 이때 Entity는 도메인에서 접근하는걸 방지하는게 좋아보인다.
 - Jpa -> QueryDsl?
   - Entity를 직접쓰는게 부담스러운이유
     - 의도치않게 Update가 될떄.
     - 이때 queryDsl로 업데이트를 한다면 dsl객체로 업데이트가 되기때문에 이 문제를 해결할 수 있다.