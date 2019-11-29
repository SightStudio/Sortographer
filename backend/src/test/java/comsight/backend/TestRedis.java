package comsight.backend;

import com.BackendApplication;
import com.app.model.redisHash.ApiCall;
import com.app.repository.api.ApiRedisRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {BackendApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class TestRedis {

    @Autowired
    ApiRedisRepository apiRedisRepository;

    @Test
    public void test() {

        String name = "sdm3285@gmail.com";

        ApiCall apiCall = new ApiCall(name);
        apiRedisRepository.save(apiCall);

        ApiCall get = apiRedisRepository.findById(name).get();
        get.refresh();

        apiRedisRepository.save(apiCall);
        System.out.println(get);
    }
}
