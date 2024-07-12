package com.lec.spring.repository;

import com.lec.spring.domain.Gender;
import com.lec.spring.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest // test 할때 springboot 컨테이너를 전부 생성
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

//        @Test
    void crud() {
        System.out.println("\n-- TEST#crud() ---------------------------------------------");
//        userRepository.findAll().forEach(System.out::println); // SELECT 전체 조회 -> SELECT * FROM T_USER;
//        User user = new User(); // Java 객체
//        System.out.println("user:" + user);
//
//        userRepository.save(user); // INSERT, 저장하기 -> 영속화된 객체! (user 에 id 값이 적용됨)
//        userRepository.findAll().forEach(System.out::println);
//        System.out.println("user:" + user);


        // 테스트에 사용할 변수들
        List<User> users = null;
        User user1 = null, user2 = null, user3 = null, user4 = null, user5 = null;
        List<Long> ids = null;

        // #002 findAll()
//        users = userRepository.findAll();
//        users.forEach(System.out::println);
//
//        System.out.println();


        // #003 findXX() + Sort.by() 사용
//        users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
//        users.forEach(System.out::println);


        // #004 findXXByXX(Iterable)
//        ids = List.of(1L, 3L, 5L);
//        users = userRepository.findAllById(ids);
//        users.forEach(System.out::println);

        // #005 sava(entity) 저장하기
//        user1 = new User("jack", "jack@redknight.com");
//        userRepository.save(user1); // INSERT
//        userRepository.findAll().forEach(System.out::println);

        // #006 saveAll(Iterable)
//        user1 = new User("jack", "jack@redknight.com");
//        user2 = new User("steve", "steve@redknight.com");
//        userRepository.saveAll(List.of(user1, user2));
//        userRepository.findAll().forEach(System.out::println);

        // #008 findById(id)
        // 리턴타입은 Optional<Entity>
//        Optional<User> user;
//        user = userRepository.findById(1L);
//        System.out.println(user); // Optional
//        System.out.println(user.get());
//
//        user = userRepository.findById(10L); // 없는 id 라면?
//        System.out.println(user); // Optional.empty
//
//        user1 = userRepository.findById(10L).orElse(null);
//        System.out.println(user1); // null


       // #009 flush()
       // flush() 는 SQL쿼리의 변화를 주는게 아니라 DB 반영 시점을 조정한다. 로그 출력으로는 변화를 확인하기 힘들다
//        userRepository.save(new User("new martin", "newmartin@redknight.com"));
//        userRepository.flush();

       // saveAndFlush() = save() + flush()
//        userRepository.saveAndFlush(new User("베리베리", "베리@berry.com"));
//        userRepository.findAll().forEach(System.out::println);

        // #010 Count()
//        Long count = userRepository.count();
//        System.out.println(count);

        // #011 existsById() -> count() 함수 사용, where 절 붙음, true 리턴
//        boolean exists = userRepository.existsById(1L);
//        System.out.println(exists);

        // #012 delete(entity)
        // 삭제하기 -> 영속성 객체 가져와서 삭제해야함
//        userRepository.delete(userRepository.findById(1L).orElse(null));
        // delete 하기전에 먼저 select 를 수행함.
        // delete 는 select + delete
//        userRepository.findAll().forEach(System.out::println);

        // delete 는 null 값 허용안함.
//         userRepository.delete(userRepository.findById(1L).orElse(null));

        // 차라리 예외 발생하고 처리하도록 하는게 더 좋은 방법.
//         userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));

        // #013 deleteById(id)
//        userRepository.deleteById(1L); // id가 없는 경우는 select 만 수행하고 끝남.
//        userRepository.deleteById(2L); // id가 있는 경우는 select 를 수행하고 delete 수행하고 끝남.

        // #014 deleteAll()
//        System.out.println(new String("베리").repeat(10));
//        userRepository.deleteAll(); // select 1회 + delete x n회 수행

        // #015 deleteAll(Iterable)
//        userRepository.deleteAll(userRepository.findAllById(List.of(1L, 3L)));
        // select n회 + delete n회 수행.

        // deleteAll() 은 성능이슈 발생!
        // #016 deleteInBatch()
//        userRepository.deleteInBatch(userRepository.findAllById(List.of(3L, 4L, 5L)));

        // #017
//        userRepository.deleteAllInBatch(); // 전부 삭제 (delete from t_user)

        // Batch 가 없는 delete -> delete x n회
        // Batch 가 있는 delete -> 한번에 delete

        // #018 findXXX(Pageable)  페이징
        // PageRequest 는 Pageable 의 구현체  org.springframework.data.domain.PageRequest
        // 리턴값은 Page<T>  org.springframework.data.domain.Page
        // 주의: page 값은 0-base 다
        Page<User> page = userRepository.findAll(PageRequest.of(3, 3)); // page1 (두번째 페이지), size 3

        // page: Page 2 of 4 containing com.lec.spring.domain.User instances
        // ⬆️ page 객체의 toString 값
        // Page<T> 의 메소드들
        System.out.println("page: " + page);
        System.out.println("totalElements: " + page.getTotalPages());
        System.out.println("numberOfElements: " + page.getNumberOfElements());
        System.out.println("sort: " + page.getSort());
        System.out.println("size: " + page.getSize());  // 페이징 할때 나누는 size

        page.getContent().forEach(System.out::println); // 해당 페이지 내부의 데이터 (들)


        // #019 QueryByExample 사용
        ExampleMatcher matcher = ExampleMatcher.matching()  // 검색조건을 담는 객체
                .withIgnorePaths("name")    // name 컬럼은 매칭하지 않음.
                .withMatcher("email", endsWith());  // email 컬럼은 뒷 부분으로 매칭하여 검색

//         Example.of(probe, ExampleMatcher)  <-- 여기서 probe 란 실제 Entity 는 아니란 말입니다.  match 를 위해서 쓰인 객체
        // email 컬럼의 뒤가 "knight.com" 으로 끝나는 것만 검색하는 select 문을 수행.
        Example<User> example = Example.of(new User("ma", "knight.com"), matcher);  // probe

        userRepository.findAll(example).forEach(System.out::println);


        // #020
        user1 = new User();
        user1.setEmail("blue");
//
        // email 필드에서 주어진 문자열을 담고 있는 것을 검색
        matcher = ExampleMatcher.matching().withMatcher("email", contains());
        example = Example.of(user1, matcher);
        userRepository.findAll(example).forEach(System.out::println);


        // UPDATE !!
        // save() 는 INSERT 뿐 아니라 UPDATE 도 수행.
        userRepository.save(new User("한정우", "han@jungwoo.com")); // INSERT ( id 값이 null 일때 insert )

        user1 = userRepository.findById(1L).orElseThrow(RuntimeException::new); // 영속성 객체
        user1.setEmail("마우스가@움직여요.com");
        userRepository.save(user1); // SELECT + UPDATE, id=1L 인 User 를 수정

        userRepository.findAll().forEach(System.out::println);

        System.out.println("------------------------------------------------------------\n");
    }


    // 테스트에 사용할 변수들
    List<User> users;
    User user, user1, user2, user3, user4, user5;
    List<Long> ids;
    Optional<User> optUser;


    // @BeforeEach : 매 테스트 메소드가 실행되기 직전에 실행
    //  @BeforeEach 메소드의 매개변수에 TestInfo 객체를 지정하면
    // JUnit Jupiter 에선 '현재 실행할 test' 의 정보가 담긴 TestInfo 객체를 주입해준다
    @BeforeEach
    void beforeEach(TestInfo testInfo) {
        System.out.println("─".repeat(40));

        users = null;
        user = user1 = user2 = user3 = user4 = user5 = null;
        ids = null;
        optUser = null;
    }

    // @AfterEach : 매 테스트 메소드가 종료된 직후에 실행
    @AfterEach
    void afterEach() {
        System.out.println("-".repeat(40) + "\n");
    }


    /***********************************************************************
     * Query Method
     */

    // 다양한 Query Return Types
    //   https://docs.spring.io/spring-data/jpa/reference/repositories/query-return-types-reference.html
    //   => void, Primitive, Wrapper, T, Iterator<T>, Collection<T>, List<T>, Optional<T>, Option<T>, Stream<T> ...
    // 쿼리 메소드의 리턴타입은 SELECT 결과가  '1개인지' 혹은 '복수개인지' 에 따라, 위에서 가용한 범위내에서 설정해서 선언
    @Test
    void qryMethod01() {
        System.out.println(userRepository.findByName("dennis"));

        // 리턴타입이 User 이면 에러다. <- 여러개를 리턴하는 경우
//         System.out.println(userRepository.findByName("martin"));

//        userRepository.findByName("martin").forEach(System.out::println);
    }


    // 쿼리 메소드의 naming
    //  https://docs.spring.io/spring-data/jpa/reference/repositories/query-keywords-reference.html
    //     find…By, read…By, get…By, query…By, search…By, stream…By
    @Test
    void qryMethod002() {
        String email = "martin@redknight.com";
        System.out.println("findByEmail : " + userRepository.findByEmail(email));
        System.out.println("getByEmail : " + userRepository.getByEmail(email));
        System.out.println("readByEmail : " + userRepository.readByEmail(email));
        System.out.println("queryByEmail : " + userRepository.queryByEmail(email));
        System.out.println("searchByEmail : " + userRepository.searchByEmail(email));
        System.out.println("streamByEmail : " + userRepository.streamByEmail(email));
        System.out.println("findUserByEmail : " + userRepository.findUsersByEmail(email));
    }

    @Test
    void qryMethod003() {
        System.out.println(userRepository.findHanByEmail("martin@redknight.com"));
    }


    // First, Top
    // First 와 Top 은 둘다 동일 (가독성 차원에서 제공되는 것임)
    @Test
    void qryMethod005() {
        String name = "martin";
        // ⬇ 'martin' 이라는 이름 중에 첫번째만 select 함.
        System.out.println("findTop1ByName : " + userRepository.findFirst1ByName(name));
        System.out.println();
        System.out.println("findTop2ByName : " + userRepository.findFirst1ByName(name));
        System.out.println();
        System.out.println("findFirst1ByName : " + userRepository.findTop1ByName(name));
        System.out.println();
        System.out.println("findFirst2ByName : " + userRepository.findTop2ByName(name));
    }


    // And, Or
    @Test
    void qryMethod007() {
        String email = "martin@redknight.com";
        System.out.println("findByEmailAndName : " + userRepository.findByEmailAndName("martin@redknight.com", "martin"));
        System.out.println();
        System.out.println("findByEmailAndName : " + userRepository.findByEmailAndName("martin@redknight.com", "dennis"));
        System.out.println();
        System.out.println("findByEmailOrName : " + userRepository.findByEmailOrName("martin@redknight.com", "dennis"));
    }


    // After, Before
    // After, Before 는 주로 '시간'에 대해서 쓰이는 조건절에 쓰인다.  (가독성 측면)
    // 그러나, 꼭 '시간'만을 위해서 쓰이지는 않는다 .   '숫자', '문자열' 등에서도 쓰일수 있다.
    @Test
    void qryMethod008() {
        System.out.println("findByCreatedAtAfter : " + userRepository.findByCreatedAtAfter(
                LocalDateTime.now().minusDays(1L)
        ));

        // id 가 4보다 큰거
        System.out.println("findByIdAfter : " + userRepository.findByIdAfter(4L));

        // 'martin' 이라는 이름보다 사전 순으로 빠른거
        System.out.println("findByNameBefore : " + userRepository.findByNameBefore("martin"));
    }


    // GreaterThan, GreaterThanEqual, LessThan, LessThanEqual
    @Test
    void qryMethod009() {
        // u1_0.created_at > ?
        System.out.println("findByCreatedAtGreaterThan : " + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));

        // u1_0.name >= ?
        System.out.println("findByNameGreaterThanEqual : " + userRepository.findByNameGreaterThanEqual("martin"));
    }


    // Between
    @Test
    void qryMethod010() {

        // u1_0.created_at between ? and ?
        System.out.println("findByCreatedAtBetween : "
                + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));

        // u1_0.id between ? and ?
        System.out.println("findByIdBetween : " + userRepository.findByIdBetween(1L, 3L));

        //  u1_0.id >= ?
        //        and u1_0.id <= ?
        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual : "
                + userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L, 3L));
    }


    // Empty 와 Null
    //   - IsEmpty, Empty
    //   - IsNotEmpty, NotEmpty,
    //   - NotNull, IsNotNull
    //   - Null, IsNull
    @Test
    void qryMethod011() {
        System.out.println("findByIdIsNotNull : " + userRepository.findByIdIsNotNull());


//        System.out.println("findByIdIsNotEmpty : " + userRepository);


//      System.out.println("findByAddressIsNotEmpty : " + userRepository.findByAddressIsNotEmpty());
    }


    // In, NotIn
    @Test
    void qryMethod012() {
        System.out.println("findByNameIn : " +
                userRepository.findByNameIn(List.of("martin", "dennis")));
        // WHERE name IN (?, ?)
    }


    // StartingWith, EndingWith, Contains
    // 문자열에 대한 검색쿼리, LIKE 사용
    @Test
    void qryMethod013() {
        System.out.println("findByNameStartingWith : " + userRepository.findByNameStartingWith("mar"));
        System.out.println("findByNameEndingWith : " + userRepository.findByNameEndingWith("s"));
        System.out.println("findByEmailContains : " + userRepository.findByEmailContains("red"));
    }


    // Like
    // 사실 위의 키워드는 Like 를 한번 더 wrapping 한거다.
    // Like 검색 시 %, _ 와 같은 와일드 카드 사용.
    @Test
    void qryMethod014() {
        System.out.println("findByEmailLike : " + userRepository.findByEmailLike("%" + "dragon" + "%"));
    }


    // Is, Equals
    // 특별한 역할은 하지 않는다. 생략가능. 가독성 차원에서 남겨진 키워드입니다.
    @Test
    void qryMethod015() {
        System.out.println(userRepository);
    }


    /***********************************************************************
     * Query Method - Sorting & Paging
     */


    // Naming 기반 정렬
    // Query method 에 OrderBy 를 붙임
    @Test
    void qryMethod016() {
        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("martin"));
        System.out.println("findLast1ByName : " + userRepository.findLast1ByName("martin"));


        System.out.println("findTopByNameOrderByIdDesc : " + userRepository.findTopByNameOrderByIdDesc("martin"));
    }


    // 정렬기준 추가
    @Test
    void qryMethod017() {
        System.out.println("findFirstByNameOrderByIdDesc : "
                + userRepository.findFirstByNameOrderByIdDesc("martin"));
        System.out.println("findFirstByNameOrderByIdDescEmailDesc : "
                + userRepository.findFirstByNameOrderByIdDescEmailDesc("martin"));
    }


    // 매개변수(Sort) 기반 정렬
    // Query method 에 Sort 매개변수로 정렬옵션 제공
    @Test
    void qryMethod018() {
        System.out.println("findFirstByName + Sort : "
                + userRepository.findFirstByName("martin", Sort.by(Sort.Order.desc("id"))));
        System.out.println("findFirstByName + Sort : "
                + userRepository.findFirstByName("martin", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))));
        System.out.println("findFirstByName + Sort : "
                + userRepository);
    }


    @Test
    void qryMethod018_2() {
        System.out.println("findFirstByName + Sort : "
                + userRepository);
        System.out.println("findFirstByName + Sort : "
                + userRepository);
        System.out.println("findFirstByName + Sort : "
                + userRepository);
        System.out.println("findFirstByName + Sort : "
                + userRepository);
    }

    private Sort getSort() {
        return Sort.by(
                Sort.Order.asc("id"),
                Sort.Order.desc("email").ignoreCase(),
                Sort.Order.desc("createdAt"),
                Sort.Order.asc("updateAt")
        );
    }

    @Test
    void qryMethod018_3() {
        System.out.println("findFirstByName + Sort : "
                + userRepository.findFirstByName("martin", getSort()));
    }


    // TODO


    // 19 Paging + Sort
    // PageRequest.of(page, size, Sort) page는 0-base
    // PageRequest 는 Pageable의 구현체
    @Test
    void qryMethod019() {
        Page<User> userPage = userRepository.findByName("martin", PageRequest.of(1, 1, Sort.by(Sort.Order.desc("id"))));

        System.out.println("page: " + userPage); // Page 를 함 찍어보자
        System.out.println("totalElements: " + userPage.getTotalElements()); // 2
        System.out.println("totalPages: " + userPage.getTotalPages()); // 2
        System.out.println("numberOfElements: " + userPage.getNumberOfElements()); // 1
        System.out.println("sort: " + userPage.getSort()); // 1
        System.out.println("size: " + userPage.getSize()); // 페이징 할때 나누는 size // 1
        userPage.getContent().forEach(System.out::println);  // 페이지 내의 데이터 List<> // 1

    }


    @Test
    void insertAndUpdateTest() {
        System.out.println("\n-- TEST#insertAndUpdateTest() ---------------------------------------------");

        user = User.builder()
                .name("martin")
                .email("martin2@blueknight.com")
                .build();

        userRepository.save(user);  // INSERT

        user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("H J WOO");
        userRepository.save(user2); // UPDATE

        System.out.println("\n------------------------------------------------------------\n");
    }


    @Test
    void enumTest() {
        System.out.println("\n-- TEST#enumTest() ---------------------------------------------");
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);
        userRepository.save(user);  // UPDATE
        userRepository.findAll().forEach(System.out::println);

        // enum 타입이 실제 어떤 값으로 DB 에 저장되었는지 확인
        System.out.println(userRepository.findRowRecord().get("gender"));

        System.out.println("\n------------------------------------------------------------\n");
    }


    @Test
    void listenerTest() {

        user = new User();
        user.setEmail("베리베리@mail.com");
        user.setName("후홍히");

        userRepository.save(user);  // INSERT

        // SELECT
        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        user2.setName("marrrrrtin");
        userRepository.save(user2);  // SELECT, UPDATE

        userRepository.deleteById(4L);  // SELECT, DELETE

        System.out.println("\n------------------------------------------------------------\n");
    }


    @Test
    void prePersistTest() {
        System.out.println("\n-- TEST#prePersistTest() ---------------------------------------------");
        User user = new User();
        user.setEmail("martin2@redknight.com");
        user.setName("martin2");

//        user.setCreatedAt(LocalDateTime.now());
//        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user); // INSERT

        System.out.println(userRepository.findByEmail("martin2@redknight.com"));

        System.out.println("\n------------------------------------------------------------\n");
    }


    @Test
    void preUpdateTest() throws InterruptedException {
        Thread.sleep(1000); // 1초 뒤에 UPDATE 시도
        System.out.println("\n-- TEST#preUpdateTest() ---------------------------------------------");

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println("as-is : " + user);   // 수정전
        user.setName("martin2");

        userRepository.save(user); // UPDATE

        System.out.println("to-be : " + userRepository.findAll().get(0));

        System.out.println("\n------------------------------------------------------------\n");
    }


    @Test
    void userHistoryTest() {
        System.out.println("\n-- TEST#userHistoryTest() ---------------------------------------------");

        User user = new User();
        user.setEmail("martin-new@greendragon.com");
        user.setName("martin-new");

        userRepository.save(user); // INSERT

        user.setName("H J WOO");
        userRepository.save(user); // UPDATE.   User Update 전에 UserHistory 에 INSERT 발생

        userHistoryRepository.findAll().forEach(System.out::println);


        System.out.println("\n------------------------------------------------------------\n");
    }







}