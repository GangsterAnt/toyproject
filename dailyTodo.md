TODO

- post_v1 에 modified_at, updated_at 공존 comment_v2도 동일. [v]
- hidden 에 대한 비즈니스 목적 없으면 deletedAt으로 변경 (soft delete만 할거면 컬럼 지우자)
  - 고민중
- postDto를 어떻게 받아와야 이쁘게 create 할수있는지 고민
  - 현재 null 값인 like 같은것들을 int 로변환할떄 npe 터짐 [v]
  - invalid 메서드안에 예외 던지는 로직을 추가?
- memberId가 int 로 되어있는데 지금 이것을 유지할지? String 으로 할지?
  - 어떻게 unique 하게 관리할것인가?


4.8
restful 하게 개선..
 - [x] controller 동작 중심에서 리소스 중심으로 변경.
 - [x] service 동작 중심에서 리소스 중심으로 변경.
 - [x] post/comment -> postEntity/commentEntity, postBo/commentBo -> post/comment 로 리네임
 - [ ] repositoryWrapper 없애기.? 고민해보기
   - 현재는 로직 (converting logic)의 역할만 하고있음. 이럴거면 서비스에서 처리하는게 나을수도?
   - 그렇지만 entity를 서비스에 직접 노출하고 싶지않음... 어떻게 해야할까??
     - 왜 wrapper 를 만들었는지?
       - entity를 서비스에 노출하고 싶지 않았음. side effect 방지. 추후 추가될수 있는 비즈니스 로직 및 필드까지 원활하게 추가 할 수 있음.
       - 예를 들면 post 안에 첨부파일 라는 필드가 추가된다면? postEntity에는 없어야하고 도메인 모델에서는 존재해야 다루기 편함.
       - 그치만 이거 그냥 서비스에서 처리해도 되는데? 그리고 엔티티가 서비스에서 쓰이는걸 방지할수도 없는건디?
 - [x] newPost 클래스 생성. 새로운 post는 이객체로 받아서 생성하자.
 - [ ] post,comment 테이블에 ownerMemberId를 String 으로 변환할지 고민. (P2)
 - [ ] post,comment 테이블에 ownerMemberId를 인덱싱하기
 