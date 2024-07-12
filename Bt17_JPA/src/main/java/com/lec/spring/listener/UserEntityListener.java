package com.lec.spring.listener;

import com.lec.spring.domain.User;
import com.lec.spring.domain.UserHistory;
import com.lec.spring.repository.UserHistoryRepository;
import com.lec.spring.support.BeanUtils;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

// ⭐️ Entity Listener 는 Spring Bean 을 주입 받지 못한다!
//@Component
public class UserEntityListener {


// ⭐️ Entity Listener 는 Spring Bean 을 주입 받지 못한다!
//    @Autowired
//    private UserHistoryRepository userHistoryRepository;


//    private final UserHistoryRepository userHistoryRepository;
// 생성자가 하나만 있는 경우 @Autowired 생략 가능함.
//    public UserEntityListener(UserHistoryRepository userHistoryRepository) {
//        this.userHistoryRepository = userHistoryRepository;
//    }


    @PreUpdate
    @PrePersist
    public void prePersistAndpreUpdate(Object o) {
        System.out.println(">> UserEntityListener#preUpdate()");

        // 스프링 bean 객체 주입 받기m (스프링 컨테이너에서 직접 꺼내옴)
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User) o;
        // UserHistory 에 UPDATE 될 User 정보를 담아서 저장 (INSERT)
        UserHistory userHistory = new UserHistory();
        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());

        userHistoryRepository.save(userHistory); // INSERT
    }
}
