package com.chris.web;

import com.chris.SeckillStatus;
import com.chris.entity.Seckill;
import com.chris.exception.RepeatKillException;
import com.chris.exception.SeckillCloseException;
import com.chris.exception.SeckillException;
import com.chris.service.SeckillService;
import com.chris.vo.Exposer;
import com.chris.vo.SeckillExecution;
import com.chris.vo.SeckillResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by wuxing on 2016/8/20.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Seckill> seckillList = seckillService.getSeckillList();
        model.addAttribute("list", seckillList);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getSeckillById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute(seckill);
        return "detail";
    }

    @ResponseBody
    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/{seckillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId, @PathVariable("md5") String md5, @CookieValue(value = "killPhone", required = false) Long killPhone) {
        if (killPhone == null) {
            return new SeckillResult<SeckillExecution>(false, "未注册");
        }
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, killPhone, md5);
            return new SeckillResult<SeckillExecution>(true, seckillExecution);
        } catch (RepeatKillException e1) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatus.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(false, seckillExecution);
        } catch (SeckillCloseException e2) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatus.END);
            return new SeckillResult<SeckillExecution>(false, seckillExecution);
        } catch (Exception e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatus.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(false, seckillExecution);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    public SeckillResult<Long> time() {
        Date now = new Date();
        return new SeckillResult<Long>(true, now.getTime());
    }
}