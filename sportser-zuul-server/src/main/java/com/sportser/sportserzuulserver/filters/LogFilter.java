package com.sportser.sportserzuulserver.filters;

import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

@Component
public class LogFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        return null;
    }
}
