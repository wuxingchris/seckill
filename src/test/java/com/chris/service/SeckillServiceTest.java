package com.chris.service;

import com.chris.entity.Seckill;
import com.chris.exception.RepeatKillException;
import com.chris.exception.SeckillCloseException;
import com.chris.vo.Exposer;
import com.chris.vo.SeckillExecution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.rmi.runtime.Log;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() throws Exception {
        List<Seckill> seckillList = seckillService.getSeckillList();
        logger.info("list={}", seckillList);
    }

    @Test
    public void testGetSeckillById() throws Exception {
        long seckillId = 1000L;
        Seckill seckill = seckillService.getSeckillById(seckillId);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void testExportSeckillUrl() throws Exception {
        long id = 1000L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}", exposer);
    }

    @Test
    public void testExecuteSeckill() throws Exception {
        long id = 1000L;
        long phone = 13530102682L;
        String md5 = "d3446e9052e0359349f9887c1fbde4c8";
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
            logger.info("seckillExecution={}", seckillExecution);
        }catch (RepeatKillException e1){
            logger.error(e1.getMessage(), e1);
        }catch (SeckillCloseException e2){
            logger.error(e2.getMessage(), e2);
        }
    }


    /**
     * 测试代码完整逻辑, 忽略重复和关闭异常
     * @throws Exception
     */
    @Test
    public void testExecuteSeckillLogic() throws Exception {
        long id = 1001L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if(exposer.isExposed()){
            logger.info("exposer={}", exposer);
            long phone = 13530102682L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
                logger.info("seckillExecution={}", seckillExecution);
            }catch (RepeatKillException e1){
                logger.error(e1.getMessage(), e1);
            }catch (SeckillCloseException e2){
                logger.error(e2.getMessage(), e2);
            }
        }else{
            logger.warn("exposer={}", exposer);
        }

    }

}