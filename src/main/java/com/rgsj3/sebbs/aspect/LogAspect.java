package com.rgsj3.sebbs.aspect;

import com.rgsj3.sebbs.domain.SystemLog;
import com.rgsj3.sebbs.domain.User;
import com.rgsj3.sebbs.repository.SystemLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;


@Aspect
@Component
public class LogAspect {

    @Resource
    SystemLogRepository systemLogRepository;

	public final static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.rgsj3.sebbs.aspect.LogAnnotation)")
    public void logPointcut() {
    }
    
    /**
     * 后置功能增强 用于处理用户操作log
     * @param joinPoint 切点
     * @param rvt 方法返回值
     */
    @AfterReturning(pointcut="logPointcut()",returning="rvt")
    public void log(JoinPoint joinPoint, Object rvt) {
        String description=getServiceMthodDescription(joinPoint);
        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        String ip=request.getRemoteAddr();   //Ip地址
        Object[] arguments = joinPoint.getArgs(); //获取相关参数
        StringBuilder  stringBuilder=new StringBuilder();
        for(Object param:arguments){
            stringBuilder.append(param+":");
        }
        String params=stringBuilder.toString();
        String method=(joinPoint.getTarget().getClass().getName() + "."
                + joinPoint.getSignature().getName() + "()");    //方法名
        var user = (User) request.getSession().getAttribute("user");
        /*System.out.println("=====后置通知开始=====");
        System.out.println("请求方法:" + method);
        System.out.println("方法描述:" + description);
        System.out.println("方法参数:" + params);
        System.out.println("请求人:" + userName);
        System.out.println("请求IP:" + ip);
        System.out.println("方法返回值:"+rvt);
        System.out.println("操作时间:"+new Date());*/
        // *========数据库日志=========*//
        var systemLog = new SystemLog();
        systemLog.setMethod(method);
        systemLog.setDescription(description);
        systemLog.setParams(params);
        systemLog.setUserName(user == null ? "null" : user.getName());
        systemLog.setIp(ip);
        systemLog.setTime(new Date());
        systemLogRepository.save(systemLog);
    }
    
    
    /**
     * 获取注解中对方法的描述信息 用于service层注解
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    private  String getServiceMthodDescription(JoinPoint joinPoint){
    	String description = "";
    	try{
        //获取目标类名
        String targetName = joinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取相关参数
        Object[] arguments = joinPoint.getArgs();
        //生成类对象
        Class targetClass = Class.forName(targetName);
        //获取该类中的方法
        Method[] methods = targetClass.getMethods();
        for(Method method : methods) {
            if(!method.getName().equals(methodName)) {
                continue;
            }
            Class[] clazzs = method.getParameterTypes();
            if(clazzs.length != arguments.length) {
                continue;
            }
            description = method.getAnnotation(LogAnnotation.class).description();
        }
    	} catch (Exception e) {
			e.printStackTrace();
		}
        return description;
    }
    

}
