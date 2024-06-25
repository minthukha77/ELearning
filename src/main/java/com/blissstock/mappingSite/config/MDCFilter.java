package com.blissstock.mappingSite.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.blissstock.mappingSite.service.UserSessionService;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MDCFilter implements Filter {

  @Autowired
  UserSessionService userSessionService;
   
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(
    ServletRequest req,
    ServletResponse resp,
    FilterChain chain
  )
    throws IOException, ServletException {
    //add access url to log
    if (req instanceof HttpServletRequest) {
    
      String url = ((HttpServletRequest) req).getRequestURL().toString();
      //String queryString = ((HttpServletRequest) req).getQueryString();
      MDC.put("accessURL", url);
    }
    
    //add login user email to the log
    Long id = userSessionService.getId();

    if (id != 0L) {
      MDC.put("userId", id+"");
    }
    try {
      chain.doFilter(req, resp);
    } finally {
      if (id!=0L) {
        MDC.remove("userId");
      }
    }
  }

  @Override
  public void destroy() {}
}
