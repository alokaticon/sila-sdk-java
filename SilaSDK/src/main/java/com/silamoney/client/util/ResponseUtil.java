package com.silamoney.client.util;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import java.net.http.HttpResponse;

/**
 * Class to manage the different kinds of responses.
 *
 * @author Karlo Lorenzana
 */
public class ResponseUtil {

    /**
     * Creates an ApiResponse based on the sent HttpResponse.
     *
     * @param response
     * @return ApiResponse
     * @throws com.silamoney.client.exceptions.BadRequestException
     * @throws com.silamoney.client.exceptions.InvalidSignatureException
     * @throws com.silamoney.client.exceptions.ServerSideException
     */
    public static ApiResponse prepareResponse(HttpResponse response) 
            throws BadRequestException, 
            InvalidSignatureException, 
            ServerSideException {
        int statusCode = response.statusCode();

        BaseResponse baseResponse = (BaseResponse) Serialization
                .deserialize(response.body().toString(), BaseResponse.class);

        switch (statusCode) {
            case 400:
                throw new BadRequestException(baseResponse.message);
            case 401:
                throw new InvalidSignatureException(baseResponse.message);
            case 500:
                throw new ServerSideException(response.body().toString());
            default:
                break;
        }

        return new ApiResponse(statusCode,
                response.headers().map(),
                baseResponse);
    }
}
