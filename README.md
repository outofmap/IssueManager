# IssueManager        
project별 이슈생성 및 관리를 도와주는 서비스   
##### Project duration : 1개월     
##### Language : Java, JavaScript, HTML, CSS    
##### Database : Mysql    
##### Framework and Library : Spring, Maven, Lucy-xss-servlet-filter, BCrypt
##### Unit test : JUnit 
----------------------
#### Description
Java web programming수업에서 진행한 1인 프로젝트로 spring 프레임워크를 활용해 이슈관리 시스템의 백엔드를 완성 후 Linux서버에 쉘 스크립트를 통한 배포자동화를 구현. 

#### 서비스 시연 동영상(이미지를 클릭하면 동영상이 실행됩니다.)
[![시연동영상](https://cloud.githubusercontent.com/assets/11879870/16192345/b27b6b2e-3723-11e6-9af6-e20f8960348b.png)](https://youtu.be/VaSbgZ38H14)

#### 프로젝트에서 맡은 일
ERD 및 DB table설계,Spring과 Maven 초기 세팅, REST API에 따른 백엔드 API구현(회원가입, 로그인, 프로젝트 CRUD, 이슈 보기/생성, 리플 보기 기능), Lucy 활용 xss공격 방어, Transaction적용, 데이터 유효성 체크, 비밀번호 암호화, nginx사용, linux 서버 세팅 및 쉘 스크립트로 배포 자동화.

#### Lessons 
##### 배우고 성장한 점
* Spring 프레임워크 실컷 사용하기       
지난 팀 프로젝트에서도 스프링 프레임워크를 사용해 백엔드 개발을 했지만, 그때는 웹 프레임워크의 사용도 처음인데다가 spring사용방법을 배우자마자 구현하는 것이라서 힘들었다. 이번에는 web.xml없이 Annotation으로만 스프링 설정을 해결하고 spirng form tag를 사용하는 등 spring이 웹서버 개발을 더 편리하게 해준다는 것을 느끼면서 즐겁게 개발할 수 있었다.  

* Database의 M:N관계를 구현하기     
DB 테이블 구조가 M:N관계까지 등장하고, 테이블 개수도 많은 프로젝트라서 좋은 경험이 되었다. DB를 설계하면서도 이전보다 많이 배웠고, 관계형 데이터베이스 구조를 자바의 객체로 옮기면서 발생하는 문제도 경험할 수 있었다. 두 객체가 Many to Many관계일 때, Unidirectional/Bidirectional relationship을 가질 수밖에 없다는 것도 깨달았다.  

* Validation과 BCrypt 라이브러리 사용      
잘 만들어진 라이브러리를 활용해 서비스의 작지만 중요한 부분인 데이터 유효성 체크와 비밀번호 암호화를 해결했다. 이전 서비스에서는 데이터 유효성체크를 내가 구현한 클래스로 해결했는데, 그보다는 라이브러리를 활용하는 것이 더 간편하면서도 많은 기능을 활용할 수 있어서 훨씬 좋다는 생각이 들었다. 

* Custom Annotation 구현 
회원가입 및 로그인 기능이 있는 서비스는 사용자가 로그인한 유저인지 아닌지를 거의 모든 API에서 체크해야한다. @LoginUser Custom Annotation를 구현하니 로그인 여부를 판단하는 로직이 간단하고 명시적으로 바뀌었다. 라이브러리와 테스트에서 제공하던 Annotation을 쓰기만 하다가 이번에 처음 직접 구현해보면서 어떤 원리로 annotation의 사용이 가능한 것인지 학습했다.  

* Nginx와 함께한 우여곡절       
하나의 서버에서 port를 다르게 설정해 3명이 각자의 서비스를 배포할 수 있도록 Nginx설정을 마치고 배포했다. 그런데, 내 서비스를 배포하고 일정시간이 지나면 서버가 종료되어버리는 문제가 발생했다. 처음에는 Nginx설정을 잘못한 줄 알고, 설정파일을 다시 살펴보았지만 문제가 없었다. 한참을 디버깅하고나서야 서버를 공유하는 친구와 나사이의 문제라는 것을 발견했다. 우리는 tomcat의 server.xml에서 Connector port는 각자 다르게 설정했지만, Shutdown port는 동일하게 설정해두고 있었다. 따라서 스크립트로 자동배포를 하면 서버가 종료되었다가 재시작하는데, 종료될 때마다 자신의 웹서비스말고 친구의 서비스도 함께 종료시키는 에러가 발생했다. 트러블슈팅 과정에서 Nginx와 port설정에 대해서도 정확히 학습할 수 있었고, 서버에서 CLI로 작업하는 것도 익숙해 졌다. 

##### 아쉬운 점
* 준비했지만 구현하지 못한 기능들        
이슈관리 프로젝트는 동시에 작업했던 Node.js와 UI 프로젝트에 밀려서 필요한 기술 학습,프로젝트 설계 기간을 제외한 본격적인 개발기간이 2주밖에 안됐다. 따라서, DB로는 설계해 두었던 Due date, Label관리 기능을 구현하지 못했다. UI에서 Calender를 활용해 due date를 설정하려고 미리 찾아두었던 jQuery 라이브러리를 사용하지 못해서 아쉬웠다. 프로젝트 기간의 마지막에는 GitHub의 이슈 관리 기능과 거의 동일하게 구현하는 초기 목표에서 프로젝트와 이슈를 생성하는 주요기능을 개발하고, 배포자동화 스크립트를 활용해 배포하는 것을 최소한의 목표로 수정했다. 다음 번에는 여러개의 프로젝트를 진행하더라도 특정 프로젝트만 많이 지연되는 것을 막기위해서 시간배분을 하는 것에 특별히 신경써야겠다. 

* Nodejs보다는 거대한 Spring, 기대되는 Spring-Boot의 세계     
Nodejs 기반의 프로젝트로 동시에 진행하면서 차이점을 실감나게 느낄 수 있었다. 특히, 프로젝트 초기에 Spring Configuration 클래스와 Maven을 위한 pom.xml 작성에도 많은 시간이 걸렸다. 다행히도 Spring-Boot를 사용하면 어렵고 시간도 오래걸리는 spring 초기 세팅과정을 직접하지 않아도 되서 더 편하게 Spring의 장점을 누릴 수 있다고 한다. 다음 번 프로젝트에서는 Spring-Boot를 사용해 더 편하게 개발할 수 있다고 생각하니 Spring-Boot를 학습해 개발하는 것도 기대된다.  

