package com.getir.readingisgood.constants;

import org.springframework.http.MediaType;

public class ApiEndPoints {

    public static final String RESPONSE_CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8";

    public static final String API_BASE_URL = "/api";
    public static final String BOOK_API = API_BASE_URL + "/book";
    public static final String ORDER_API = API_BASE_URL + "/order";
    public static final String CUSTOMER_API = API_BASE_URL + "/customer";
    public static final String STATISTICS_API = API_BASE_URL + "/statistics";

    private ApiEndPoints() {
    }
}
