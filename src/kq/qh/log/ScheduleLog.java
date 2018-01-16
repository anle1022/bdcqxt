package kq.qh.log;

import kq.qh.util.JsonUtil;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @Title: 定时程序日志
 * @Package kq.dh.common
 * Copyright: Copyright (c) 2016 
 * Company:苍穹广州技术中心
 * 
 * @author ylf
 * @date 2016年8月15日 上午10:20:45
 * @version V1.0
 */
@Aspect
public class ScheduleLog {
	//切入点
	public static final String EDP = "execution (* kq.qh.schedule.service.*.*(..))"  ;
	//获取日志配置
	Logger log = Logger.getLogger("scheduleLogger");

	@Before(value=EDP,argNames="jp")
    public void doBefore(JoinPoint jp) {
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n    Schedule[INFO]: ").append(jp.getTarget().getClass().getName()).append(".").append(jp.getSignature().getName())
		.append("\r\n    ==>方法参数：");
		for(Object obj : jp.getArgs()){
			sb.append(JsonUtil.toJson(obj)+",");
		}
		log.info(sb.toString());
    }
	
	/*@AfterReturning(value=EDP,returning="result")
	public void getValue(Object result){
		 System.out.println("log Ending result: "+JsonUtil.toJson(result));
	}*/
	//@Pointcut("execution(* ylf.test.service.*.*(..))")
	/*@Around(value = EDP)
    public Object doAround(ProceedingJoinPoint pjp)throws Throwable {
        long time = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        time = System.currentTimeMillis() - time;
        System.out.println("process time: " + time + " ms");
        return retVal;
    }

	@Before(value = EDP)
    public void doBefore(JoinPoint jp) {
		System.out.println("log Begining method: "
                + jp.getTarget().getClass().getName() + "."
                + jp.getSignature().getName());
    }*/

	@AfterThrowing(value = EDP,throwing="ex")
    public void doThrowing(Exception ex) {
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n    Schedule[ERROR]: ").append(ex.getClass().getName()).append("：").append(ex.getMessage()).append("\r\n");
		for(StackTraceElement obj : ex.getStackTrace()){
			sb.append("        at ").append(obj.getClassName()).append(".").append(obj.getMethodName()).append(" (")
			.append(obj.getClassName()).append(":").append(obj.getLineNumber()).append(") \r\n");
		}
		log.info(sb.toString());
    }
}
