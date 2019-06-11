package com.kb.application.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class IpExtractor {

    private IpExtractor(){}

    private static final List<String> IP_HEADER_CANDIDATES = Arrays.asList(
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR");

    public static String getClientIpAddress(HttpServletRequest request) {
       return IP_HEADER_CANDIDATES.stream()
         .map(request::getHeader)
         .filter(headerValue -> !"unknown".equalsIgnoreCase(headerValue))
         .findFirst()
         .orElse(request.getRemoteAddr());
    }
}
