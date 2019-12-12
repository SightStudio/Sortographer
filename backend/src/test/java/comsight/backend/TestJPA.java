package comsight.backend;

import com.BackendApplication;
import com.app.dto.photo.PhotoInfoDTO;
import com.app.repository.photo.PhotoRepository;
import com.app.repository.photoLabel.PhotoLabelRepository;
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
    PhotoRepository      photoRepository;

    @Autowired
    PhotoLabelRepository photoLabelRepository;

    @Test
    public void test_1() {
        List<PhotoInfoDTO> pList1 = photoRepository.findWithPhotoLabel(1, 10);
        System.out.println(pList1);


        List<PhotoInfoDTO> pList2 = photoRepository.findWithPhotoLabel(1, 10, "Person");
        System.out.println(pList2);
    }

    @Test
    public void test_2() {
        List<String> pList = photoLabelRepository.getDistinctLabelList();
        System.out.println(pList);
    }
}
