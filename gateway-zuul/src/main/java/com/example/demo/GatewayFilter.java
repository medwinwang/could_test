package com.example.demo;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by medwin on 2018/10/31.
 */
@Slf4j
public class GatewayFilter extends ZuulFilter {

    /**
     * pre    ：可以在请求被路由之前调用
     * routing：在路由请求时候被调用
     * post   ：在routing和error过滤器之后被调用
     * error  ：处理请求时发生错误时被调用
     */

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        //通过int值来定义过滤器的执行顺序
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //设置该过滤器总是生效，即总是执行拦截请求
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("收到 %s 请求 %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if(accessToken == null) {

            log.warn("accesstoken为空");
            //令zuul过滤该请求，不对其进行路由
            ctx.setSendZuulResponse(false);
            //设置其返回的错误码和报文体
            //这里没有设置应答码为401，是因为401会导致客户端走到它的断路器里面（HystrixCalculatorService）
            //所有设置为200，让应答报文体跳过客户端的断路器，返回给前台
            ctx.getResponse().setContentType("text/html;charset=UTF-8");
            ctx.setResponseStatusCode(200);
            ctx.setResponseBody("权限不足");
            return null;
        }
        log.info("accesstoken验证通过");
        return null;
    }
}
