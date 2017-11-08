package app.module.codes.api;

import app.codes.model.*;
import app.error.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

@Api(value = "Application Type, Receive Method, Service Method 등과 같은 코들 조회를 위한 API를 제공한다.")
@RequestMapping("/api/v1/codes")
@SuppressWarnings("unused")
public interface CodesResourceApi {
    @ApiOperation(value = "find all application types", notes = "모든 application type들을 조회한다.", response = ApplicationType.class, responseContainer = "List<ApplicationType>", produces = "application/json;charset=UTF-8")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ApplicationType.class, responseContainer = "List<ApplicationType>"),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error occurred", response = ErrorResponse.class) })
    @RequestMapping(value = "/application-types", method = RequestMethod.GET)
    ResponseEntity<List<ApplicationType>> applicationTypes();

    @ApiOperation(value = "find all receive method types", notes = "모든 receive method type들을 조회한다.", response = ReceiveMethod.class, responseContainer = "List<ReceiveMethod>", produces = "application/json;charset=UTF-8")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ReceiveMethod.class, responseContainer = "List<ReceiveMethod>"),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error occurred", response = ErrorResponse.class) })
    @RequestMapping(value = "/receive-methods", method = RequestMethod.GET)
    ResponseEntity<List<ReceiveMethod>> receiveMethods();

    @ApiOperation(value = "find all service method types", notes = "모든 service method type들을 조회한다.", response = ServiceMethod.class, responseContainer = "List<ServiceMethod>", produces = "application/json;charset=UTF-8")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ServiceMethod.class, responseContainer = "List<ServiceMethod>"),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error occurred", response = ErrorResponse.class) })
    @RequestMapping(value = "/service-methods", method = RequestMethod.GET)
    ResponseEntity<List<ServiceMethod>> serviceMethods();

    @ApiOperation(value = "find all transfer method types", notes = "모든 transfer method type들을 조회한다.", response = TransferMethod.class, responseContainer = "List<TransferMethod>", produces = "application/json;charset=UTF-8")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = TransferMethod.class, responseContainer = "List<TransferMethod>"),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error occurred", response = ErrorResponse.class) })
    @RequestMapping(value = "/transfer-methods", method = RequestMethod.GET)
    ResponseEntity<List<TransferMethod>> transferMethods();


    @ApiOperation(value = "find all transfer fee types", notes = "모든 transfer fee type들을 조회한다.", response = TransferFeeType.class, responseContainer = "List<TransferFeeType>", produces = "application/json;charset=UTF-8")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = TransferFeeType.class, responseContainer = "List<TransferFeeType>"),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error occurred", response = ErrorResponse.class) })
    @RequestMapping(value = "/transfer-fee-types", method = RequestMethod.GET)
    ResponseEntity<List<TransferFeeType>> transferFeeTypes();

    default  <T> List<T> getEnumValues(Class<T> t) {
        return Arrays.asList(t.getEnumConstants());
    }

}
