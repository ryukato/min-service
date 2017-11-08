package app.module.currency.api;

import app.module.currency.entity.CurrencyEntity;
import app.error.ErrorResponse;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Api(value = "Currency 조회를 위한 API를 제공한다.")
@RequestMapping("/api/v1/currencies")
public interface CurrencyResourceApi {

    @ApiOperation(value = "find all currencies", notes = "모든 Currency들을 조회한다.", response = CurrencyEntity.class, responseContainer = "Page<CurrencyEntity>", produces = "application/json;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "page 번호", example = "0", dataType = "Integer" ,defaultValue = "0"),
            @ApiImplicitParam(name = "size", value = "page 게시물 개수", example = "10", dataType = "Integer" ,defaultValue = "100"),
            @ApiImplicitParam(name = "sort", value = "page 게시물 정렬", example = "id,asc", dataType = "String")
    })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = CurrencyEntity.class, responseContainer = "Page<CurrencyEntity>"),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error occurred", response = ErrorResponse.class) })
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Page<CurrencyEntity>> findAll(Pageable pageable);

    @ApiOperation(value = "find a currency", notes = "주어진 id에 해당하는 환율 정보를 조회", response = CurrencyEntity.class, produces = "application/json;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Currency ID", example = "59aea4483d4a9e4199780dc3", dataType = "String", paramType = "path")
    })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = CurrencyEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error occurred", response = ErrorResponse.class) })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<CurrencyEntity> findById(@PathVariable("id") String id);

    @ApiOperation(value = "find a currency matched with given name", notes = "주어진 환율명에 해당하는 환율 정보를 조회", response = CurrencyEntity.class, produces = "application/json;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "Currency Name", example = "KRW", dataType = "String", paramType = "query")
    })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = CurrencyEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error occurred", response = ErrorResponse.class) })
    @RequestMapping(value = "/search/by-name", method = RequestMethod.GET)
    ResponseEntity<CurrencyEntity> findByName(@RequestParam("name") String name);


    @ApiOperation(value = "find a currency matched with given korean name", notes = "주어진 한글명에 해당하는 환율 정보를 조회", response = CurrencyEntity.class, produces = "application/json;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "Currency Korean Name", example = "원", dataType = "String", paramType = "query")
    })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = CurrencyEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error occurred", response = ErrorResponse.class) })
    @RequestMapping(value = "/search/by-korean-name", method = RequestMethod.GET)
    ResponseEntity<CurrencyEntity> findByKoreanName(@RequestParam("name") String name);
}
