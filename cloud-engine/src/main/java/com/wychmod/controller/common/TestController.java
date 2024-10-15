package com.wychmod.controller.common;

import cn.hutool.core.util.RandomUtil;
import com.wychmod.util.JsonData;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class TestController {


    /**
     * 处理登录请求
     * 该方法接收邮件和密码参数，并返回一个JsonFormatter对象
     * 如果邮件地址以"a"开头，返回错误信息；否则，返回成功信息以及邮件和密码
     *
     * @param mail 用户的邮件地址
     * @param pwd 用户的密码
     * @return JsonFormatter对象，包含错误或成功信息
     */
    @RequestMapping("/api/v1/test/login_form")
    @ResponseBody
    public JsonData login(String mail, String pwd){
        // 检查邮件地址是否以"a"开头，如果是，返回账号错误的信息
        if(mail.startsWith("a")){
            return JsonData.buildError("账号错误");
        }
        // 如果邮件地址不以"a"开头，返回包含邮件和密码的成功信息
        return JsonData.buildSuccess("mail="+mail+",pwd="+pwd);
    }


    /**
     * 处理支付请求的控制器方法
     * 该方法接收一个包含支付信息的JSON对象，并返回一个格式化的支付确认信息
     *
     * @param map 包含支付信息的键值对，其中键包括"id"和"amount"
     * @return 返回一个JsonFormatter对象，包含支付确认信息
     */
    @PostMapping("/api/v1/test/pay_json")
    @ResponseBody
    public JsonData pay(@RequestBody Map<String,String> map) {

        // 从请求体中提取支付id
        String id = map.get("id");
        // 从请求体中提取支付金额
        String amount = map.get("amount");
        // 构建并返回成功支付的确认信息
        return JsonData.buildSuccess("id="+id+",amount="+amount);
    }


    /**
     * json提交, 加上随机睡眠时间
     * 通过POST请求接收JSON格式的数据，并模拟处理时间的随机延迟
     *
     * @param map 请求体，包含id和amount等信息
     * @return 返回处理结果的JSON格式字符串
     */
    @PostMapping("/api/v1/test/pay_json_sleep")
    @ResponseBody
    public JsonData paySleep(@RequestBody Map<String,String> map) {

        try {
            int value = RandomUtil.randomInt(1000);
            TimeUnit.MICROSECONDS.sleep(value);
            String id = map.get("id");
            String amount = map.get("amount");
            return JsonData.buildSuccess("id="+id+",amount="+amount+",sleep="+value);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * 使用GET方法访问指定的API路径，处理请求并返回测试数据的详细信息
     * 该方法主要用于查询测试数据的详细信息，通过URL中的id参数来唯一标识需要查询的数据
     *
     * @param id 查询的数据的唯一标识符，通过URL传入
     * @return 返回一个格式化的JSON对象，包含成功状态和消息，消息中包含了传入的id信息
     */
    @GetMapping("/api/v1/test/query")
    @ResponseBody
    public JsonData queryDetail(Long id){
        return JsonData.buildSuccess("id="+id);
    }


    /**
     * 查询并模拟睡眠
     * 该接口用于查询指定ID的数据，并模拟一个随机时长的睡眠过程
     * 主要用于测试异步请求处理和性能影响
     *
     * @param id 查询的数据ID
     * @return 包含成功信息和睡眠时长的JsonFormatter对象
     * @throws RuntimeException 当睡眠过程中被中断时，抛出运行时异常
     */
    @GetMapping("/api/v1/test/query_sleep")
    @ResponseBody
    public JsonData querySleep(Long id){
        try {
            int value = RandomUtil.randomInt(1000);
            TimeUnit.MICROSECONDS.sleep(value);
            return JsonData.buildSuccess("id="+id+",sleep="+value);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 查询错误码接口
     * 该接口用于模拟根据ID查询错误码的场景，并在特定条件下返回错误状态码
     *
     * @param id 查询的ID，用于判断是否触发错误状态码
     * @param response 用于设置HTTP响应状态码
     * @return 返回一个JsonFormatter对象，包含成功或错误的信息
     */
    @GetMapping("/api/v1/test/query_error_code")
    @ResponseBody
    public JsonData queryError(Long id, HttpServletResponse response){

        if(id % 3 == 0){
            response.setStatus(500);
        }
        return JsonData.buildSuccess("id="+id);
    }
}
