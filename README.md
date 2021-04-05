# SpringRestServer
Spring REST API Server

JSON 객체를 반환하는 REST API Server로 Spring MVC 패턴을 사용하였습니다.
회원관련으로는 회원가입, 로그인, 이메일 중복체크, 닉네임 중복체크가 있으며, 암호화는 sha-256을 사용하였고 이미지 저장은 UUID를 사용하여 중복 방지를 하였습니다.
아이템관련으로는 아이템 목록보기, 상세보기, 추가, 삭제, 수정이 있으며 데이터 최신화를 위한 데이터 업데이트 날짜를 반환하는 API도 추가하였습니다.
