package com.dingding.backendblogsystem.aop;


    import com.alibaba.fastjson.JSONObject;
    import com.dingding.backendblogsystem.pojo.Log;
    import com.dingding.backendblogsystem.service.LogService;
    import lombok.extern.slf4j.Slf4j;
    import org.aspectj.lang.ProceedingJoinPoint;
    import org.aspectj.lang.annotation.Around;
    import org.aspectj.lang.annotation.Aspect;
    import org.aspectj.lang.annotation.Pointcut;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.web.context.request.RequestAttributes;
    import org.springframework.web.context.request.RequestContextHolder;
    import org.springframework.web.context.request.ServletRequestAttributes;

    import javax.servlet.http.HttpServletRequest;
    import java.net.URLDecoder;
    import java.util.*;

    /**
     * 定义日志切面
     */
    @Aspect // 声明一个切面
    @Configuration // 声明为配置类
    @Slf4j // 注入log对象
    public class LogAop {

        // 定义切点
        @Pointcut(value = "execution(public * com.dingding.backendblogsystem.controller..*(..))")
        public void execute() {
        }


        // 环绕通知
        @Around("execute()")
        public Object doAround(ProceedingJoinPoint pjp) throws Throwable {


            // 记录请求信息
            long beginTime = System.currentTimeMillis();

            // 获取request对象
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();


            // 获取请求协议  服务器地址  端口 访问ip  请求参数
            String scheme = httpServletRequest.getScheme();
            String serverName = httpServletRequest.getServerName();
            int serverPort = httpServletRequest.getServerPort();
            String ip = httpServletRequest.getRemoteAddr();
            String queryString = httpServletRequest.getQueryString() == null ? "?query=null" : URLDecoder.decode(httpServletRequest.getQueryString(), "UTF-8"); // 获取请求参数

            // 拼接请求地址  获取请求方法   获取query以及body参数
            String uri = scheme + "://" + serverName + ":" + serverPort + httpServletRequest.getRequestURI() + queryString; // 获取请求URI
            String method = httpServletRequest.getMethod();
            Object[] args = pjp.getArgs();

            // 调用目标方法，并获取返回结果
            Object result = pjp.proceed();
            long endTime = System.currentTimeMillis();
            // 如果是日志请求，不保存日志信息
            if (httpServletRequest.getRequestURI().contains("/api/log/page")||httpServletRequest.getRequestURI().contains("/api/qr-login/get-login-status")) {
                return result;
            }
            // 保存日志信息
            saveLog(new Date(), method.toUpperCase() + "::" + uri, ip, method, args, result, endTime - beginTime);
            return result;
        }

        @Autowired
        private LogService logService;

        // 保存日志信息
        private void saveLog(Date date, String s, String ip, String method, Object[] args, Object result, long time) {
            Log logInfo = new Log();
            logInfo.setAccessTime(date);
            logInfo.setAccessApi(s);
            log.info("请求地址：" + s);
            if (ip.equals("0:0:0:0:0:0:0:1")) {
                ip = "localhost";
            }
            logInfo.setAccessIp(ip);
            logInfo.setTimeConsume(time);
            logInfo.setRespData(JSONObject.toJSONString(result));
            logInfo.setAccessParams(Arrays.toString(args));
            logService.save(logInfo);
        }


    }


