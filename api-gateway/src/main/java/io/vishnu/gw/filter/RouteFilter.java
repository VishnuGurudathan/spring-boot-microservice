package io.vishnu.gw.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * spring-boot-microservice : io.vishnu.gw.filter
 *
 * @author vishnu.g
 */
public class RouteFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(RouteFilter.class);
    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        logger.info("{} request to {}",request.getMethod(), request.getRequestURI().toString());
        return null;
    }
}
