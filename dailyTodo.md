TODO

- post_v1 에 modified_at, updated_at 공존 comment_v2도 동일. [v]
- hidden 에 대한 비즈니스 목적 없으면 deletedAt으로 변경 (soft delete만 할거면 컬럼 지우자)
  - 고민중
- postDto를 어떻게 받아와야 이쁘게 create 할수있는지 고민
  - 현재 null 값인 like 같은것들을 int 로변환할떄 npe 터짐 [v]
  - invalid 메서드안에 예외 던지는 로직을 추가?
- memberId가 int 로 되어있는데 지금 이것을 유지할지? String 으로 할지?
  - 어떻게 unique 하게 관리할것인가?

