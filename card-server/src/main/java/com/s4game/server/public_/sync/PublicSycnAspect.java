package com.s4game.server.public_.sync;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.s4game.core.message.Message;
import com.s4game.core.sync.Lock;
import com.s4game.core.sync.LockManager;
import com.s4game.server.public_.nodecontrol.command.NodeControlCommands;
import com.s4game.server.public_.share.constants.PublicNodeConstants;
import com.s4game.server.public_.share.service.IPublicRoleStateService;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年9月11日 下午6:52:12
 * 
 */
@Component
@Aspect
public class PublicSycnAspect {

    private Logger LOG = LogManager.getLogger(getClass());

    @Resource
    private LockManager lockManager;

    @Resource
    private IPublicRoleStateService publicRoleStateService;

    @Pointcut(value = "execution(* com.s4game.server.public_..*Action.*(..) )")
    public void pointcut() {

    }

    @Around(value = "pointcut()")
    public void run(ProceedingJoinPoint joinpoint) throws Throwable {
        LOG.info(joinpoint.toShortString());

        Message message = (Message) joinpoint.getArgs()[0];
        String command = message.getCommand();
        String roleId = message.getRoleId();

        Lock lock = this.lockManager.getLock(PublicNodeConstants.COMPONENT_NAME, roleId);

        synchronized (lock) {
            if ((this.publicRoleStateService.isPublicOnline(roleId)) || (NodeControlCommands.ROLE_IN.equals(command))) {
                joinpoint.proceed();
                return;
            }

            LOG.error("role: {} public not online. message: {}", roleId, message.toString());
        }
    }

    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void exception(Exception e) {
        LOG.error("execute public sync aspect error: {}", e.getMessage(), e);
    }
}
