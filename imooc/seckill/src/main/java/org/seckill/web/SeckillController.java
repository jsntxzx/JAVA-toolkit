package org.seckill.web;

import org.seckill.dto.AjaxResult;
import org.seckill.dto.Excuter;
import org.seckill.dto.Exposer;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillState;
import org.seckill.exception.ClosedKillException;
import org.seckill.exception.RepeatKillException;
import org.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by 中希 on 2016/8/15.
 * 该Controller 需要实现以下的接口
 * GET /seckill/list
 * GET /seckill/{id}/detail
 * GET /seckill/time/now
 * POST /seckill/{id}/exposer
 * POST /seckill/{id}/{md5}/excution
 * uri  : 模块/资源/{id}/细分
 */
@Controller
@RequestMapping("seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        // list.jsp + model(data) = ModelAndView
        List<Seckill> l = seckillService.getSeckillList();
        model.addAttribute("list", l);
        return "list"; // /WEB-INF/jsp/list.jsp
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(Model model, @PathVariable(value = "seckillId") Long seckillId) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill s = seckillService.getbyId(seckillId);
        if (s == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", s);
        return "detail"; // /WEB-INF/jsp/detail.jsp
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public AjaxResult<Long> now() {
        Date now = new Date();
        return new AjaxResult<Long>(true, now.getTime());
    }

    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public AjaxResult<Exposer> exposer(@PathVariable(value = "seckillId") Long seckillId) {
        AjaxResult<Exposer> ret;
        try {
            Exposer exposer = seckillService.seckillExpose(seckillId);
            ret = new AjaxResult<Exposer>(true, exposer);
        } catch (Exception e) {
            ret = new AjaxResult<Exposer>(false, e.getMessage());
        }
        return ret;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/excution", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public AjaxResult<Excuter> excuter(@PathVariable(value = "seckillId") Long seckillId,
                                       @PathVariable(value = "md5") String md5,
                                       @CookieValue(value = "killPhone", required = false) Long phone) {
        if (phone == null) {
            return new AjaxResult<Excuter>(false, "未注册");
        }
        try {
            // 使用存储过程调用
            Excuter excuter = seckillService.seckillExcuteProcedure(seckillId, phone, md5);
            return new AjaxResult<Excuter>(true, excuter);
        } catch (RepeatKillException e) {
            Excuter excuter = new Excuter(seckillId, SeckillState.REPEAT_KILL);
            return new AjaxResult<Excuter>(true, excuter);
        } catch (ClosedKillException e) {
            Excuter excuter = new Excuter(seckillId, SeckillState.END);
            return new AjaxResult<Excuter>(true, excuter);
        } catch (Exception e) {
            Excuter excuter = new Excuter(seckillId, SeckillState.INNER_ERROR);
            return new AjaxResult<Excuter>(true, excuter);
        }
    }


}
