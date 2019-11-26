package comsight.backend;

import com.BackendApplication;
import com.app.repository.photo.PhotoRepository;
import com.app.repository.photo.PhotoRepositorySupport;
import com.app.service.PhotoServiceIF;
import com.querydsl.core.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {BackendApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class TestJPA {

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    PhotoRepositorySupport photoRepositorySupport;

    @Autowired
    PhotoServiceIF serviceIF;

    @Test
    public void test() {
        List<Tuple> pList = photoRepositorySupport.findWithPhotoLabel(0, 10);
        System.out.println(pList);
    }

}
