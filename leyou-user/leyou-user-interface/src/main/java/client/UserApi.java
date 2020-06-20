package client;

import com.demo.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping
public interface UserApi {
    @GetMapping("findUser")
    User findUser(@RequestParam("username") String username, @RequestParam("password") String password);
}
