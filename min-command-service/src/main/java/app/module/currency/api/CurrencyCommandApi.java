package app.module.currency.api;

import app.module.error.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
@Api(value = "Currency 생성, 수정 및 삭제를 위한 HTTP API 를 제공한다.")
@RequestMapping("/api/v1/command/currency")
public interface CurrencyCommandApi {

    @ApiOperation(value = "Send request to create a currency", notes = "Currency 생성을 요청한다.", response = String.class, produces = "application/json;charset=UTF-8")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error occurred", response = ErrorResponse.class) })
    @PostMapping
    CompletableFuture<String> createCurrency(CreateCurrencyRequest request);


    @ApiOperation(value = "Send request to update a currency", notes = "Currency 수정을 요청한다.", response = String.class, produces = "application/json;charset=UTF-8")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error occurred", response = ErrorResponse.class) })
    @PutMapping(value = "/{id}")
    CompletableFuture<String> updateCurrency(@PathVariable String id, @RequestBody  UpdateCurrencyRequest request);


    @ApiOperation(value = "Send request to delete a currency", notes = "Currency 삭제를 요청한다.", produces = "application/json;charset=UTF-8")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error occurred", response = ErrorResponse.class) })
    @DeleteMapping(value = "/{id}")
    CompletableFuture<Void> deleteCurrency(@PathVariable String id);
}
