import com.chris.dao.SeckillDao;
import com.chris.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wuxing on 2016/8/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao;

    @Test
    public void testQueryById() throws Exception {
        Seckill seckill = seckillDao.queryById(1000);
        System.out.println(seckill);
    }

    @Test
    public void  testQueryAll() throws Exception{
        List<Seckill> seckillList = seckillDao.queryAll(0, 100);
        for (Seckill seckill : seckillList){
            System.out.println(seckill);
        }
    }

    @Test
    public void testReduceNumber() throws Exception {

    }
}
