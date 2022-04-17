package egovframework.msa.sample.controller;

import egovframework.msa.sample.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ElkStackExampleController {

    Logger logger = LoggerFactory.getLogger(ElkStackExampleController.class);

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable int id) {
        List<User> users = getUsers();
        User user = users.stream().filter(u -> u.getId() == id).findAny().orElse(null);

        if (user != null) {
            logger.info("user found : {}", user);
            return user;
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("User Not Found with ID : {}", id);
            }
            return new User();
        }
    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        User u1 = new User();
        u1.setId(1);
        users.add(u1);

        User u2 = new User();
        u2.setId(2);
        users.add(u2);

        User u3 = new User();
        u3.setId(3);
        users.add(u3);

        return users;
    }
}
