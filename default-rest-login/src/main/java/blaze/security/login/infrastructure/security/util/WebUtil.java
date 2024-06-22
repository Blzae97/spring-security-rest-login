package blaze.security.login.infrastructure.security.util;

import jakarta.servlet.http.HttpServletRequest;

public class WebUtil {
    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    private static final String X_REQUESTED_WITH = "X-Requested-With";
    private static final String CONTENT_TYPE = "Content-type";
    private static final String CONTENT_TYPE_JSON = "application/json";

    /**
     * 요청이 Ajax 비동기 요청인지 확인
     *
     * @param request 요청
     * @return true: 비동기 요청, false: 비동기 요청아님
     */
    public static boolean isAjax(HttpServletRequest request) {
        return XML_HTTP_REQUEST.equals(request.getHeader(X_REQUESTED_WITH));
    }

    /**
     * Content-type이 json인지 확인
     *
     * @param request 요청
     * @return true: json 타입, false: json 타입이 아님
     */
    public static boolean isContentTypeJson(HttpServletRequest request) {
        return request.getHeader(CONTENT_TYPE).contains(CONTENT_TYPE_JSON);
    }

}
