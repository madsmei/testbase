package com.zg.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/******
 * Spring AOP代码
 * 这里面使用了 动态代理技术，我们可以根据代理对象 JoinPoint 来获取 被代理类的信息
 */
@Component
@Aspect
public class SpringAopDemo {
    /*****
     * 声明 切入点
     * execution 声明连接点
     */
    @Pointcut("execution(* com.abc.service..*.*(..))")
    public void poinCut(){
        System.out.println("=======实际业务代码========="+Thread.currentThread().getName());

    }

    /******
     * 前置通知
     * 切入点的通知。这里直接写是切入点的方法名
     * 比如说 开启事务
     */
    @Before("poinCut()")
    public void before(JoinPoint joinPoint){
        System.out.println("===========方法前调用======="+Thread.currentThread().getName());
    }

    /******
     * 后置通知
     * 可以额外配置一个returning属性，来指定一个参数名接收目标方法执行后的返回值
     */
    @AfterReturning(value = "poinCut()",returning = "msg")
    public void afterReturning(String msg) {
        System.out.println("============后置通知============返回结果 : "+msg);
    }

    /******
     * 异常通知
     */
    @AfterThrowing(value = "poinCut()" ,throwing="e")
    public void afterThrowing(Throwable e) {
        System.out.println("=======异常通知==========错误信息: "+e.getMessage());
    }

    /******
     * 最终通知
     * 切入点的通知。这里直接写是切入点的方法名
     * 比如说 提交事务
     */
    @After(value = "poinCut()")
    public void after(JoinPoint joinPoint){
        System.out.println("=============方法后调用==============="+Thread.currentThread().getName());
    }


    /*****
     * 环绕通知
     */
    @Around(value = "poinCut()")
    public void around(ProceedingJoinPoint joinPoint) {
        System.out.println("==========环绕通知==================");

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();

        System.out.println("代理类名 "+className+" 的方法名-->"+methodName);

        //调用实际的业务代码
//        joinPoint.proceed();
    }
}


