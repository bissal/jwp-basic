#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* 서블릿컨텍스트가 초기화될때 ContextLoaderListener.contextInitialized()가 호출.
* jwp.sql의 초기화 sql문과 ConnectionManager의 h2 DB 정보로 DB 초기화.
* 어노테이션의 loadOnStartup 옵션으로 톰캣 구동 시 실행되는 DispatcherServlet.init()에서 매핑 초기화.
* RequestMapper.initMapping()에서 해시맵에 url:controller 형태로 매핑.

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* 

#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 
