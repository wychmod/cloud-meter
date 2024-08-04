package com.wychmod.controller.common;

import cn.hutool.core.util.RandomUtil;
import com.wychmod.util.JsonFormatter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class TestController {

    /**
     * form表单提交，不能json
     * @param mail
     * @param pwd
     * @return
     */
    @RequestMapping("/api/v1/test/login_form")
    @ResponseBody
    public JsonFormatter login(String mail, String pwd){

        if(mail.startsWith("a")){
            return JsonFormatter.buildError("账号错误");
        }
        return JsonFormatter.buildSuccess("mail="+mail+",pwd="+pwd);
    }

    /**
     * json提交
     * @param mail
     * @param pwd
     * @return
     */
    @PostMapping("/api/v1/test/pay_json")
    @ResponseBody
    public JsonFormatter pay(@RequestBody Map<String,String> map) {

        String id = map.get("id");
        String amount = map.get("amount");
        return JsonFormatter.buildSuccess("id="+id+",amount="+amount);
    }


    /**
     * json提交, 加上随机睡眠时间
     * @param mail
     * @param pwd
     * @return
     */
    @PostMapping("/api/v1/test/pay_json_sleep")
    @ResponseBody
    public JsonFormatter paySleep(@RequestBody Map<String,String> map) {

        try {
            int value = RandomUtil.randomInt(1000);
            TimeUnit.MICROSECONDS.sleep(value);
            String id = map.get("id");
            String amount = map.get("amount");
            return JsonFormatter.buildSuccess("id="+id+",amount="+amount+",sleep="+value);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * get方式提交
     * @param id
     * @return
     */
    @GetMapping("/api/v1/test/query")
    @ResponseBody
    public JsonFormatter queryDetail(Long id){
        return JsonFormatter.buildSuccess("id="+id);
    }


    /**
     * get方式，随机睡眠时间
     * @param id
     * @return
     */
    @GetMapping("/api/v1/test/query_sleep")
    @ResponseBody
    public JsonFormatter querySleep(Long id){
        try {
            int value = RandomUtil.randomInt(1000);
            TimeUnit.MICROSECONDS.sleep(value);
            return JsonFormatter.buildSuccess("id="+id+",sleep="+value);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * get方式，id取模3是0后则http状态码500
     * @param id
     * @return
     */
    @GetMapping("/api/v1/test/query_error_code")
    @ResponseBody
    public JsonFormatter queryError(Long id,  HttpServletResponse response){

        if(id % 3 == 0){
            response.setStatus(500);
        }
        return JsonFormatter.buildSuccess("id="+id);
    }
}
