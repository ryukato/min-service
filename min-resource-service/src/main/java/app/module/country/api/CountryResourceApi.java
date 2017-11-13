package app.module.country.api;


import app.module.country.entity.CountryEntity;
import app.error.ErrorResponse;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.CompletableFuture;

@Api(value = "Country 조회를 위한 API를 제공한다.")
@RequestMapping("/api/v1/countries")
@SuppressWarnings("unused")
public interface CountryResourceApi {

    @ApiOperation(value = "find all countries", notes = "모든 Country들을 조회한다.", response = CountryEntity.class, responseContainer = "Page<CountryEntity>", produces = "application/json;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "page 번호", example = "0", dataType = "Integer" ,defaultValue = "0"),
            @ApiImplicitParam(name = "size", value = "page 게시물 개수", example = "10", dataType = "Integer" ,defaultValue = "100"),
            @ApiImplicitParam(name = "sort", value = "page 게시물 정렬", example = "id,asc", dataType = "String")
    })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = CountryEntity.class, responseContainer = "Page<CountryEntity>"),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error occurred", response = ErrorResponse.class) })

    @RequestMapping(method = RequestMethod.GET)
    CompletableFuture<Page<CountryEntity>> findAll(Pageable pageable);

    @ApiOperation(value = "find a country with id", notes = "주어진 id에 해당하는 Country를 조회.", response = CountryEntity.class, produces = "application/json;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Country ID", example = "59aea4483d4a9e4199780dc3", dataType = "String", paramType = "path", required = true)
    })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = CountryEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error occurred", response = ErrorResponse.class) })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    CompletableFuture<CountryEntity> findById(@PathVariable String id);


    @ApiOperation(value = "search country with name", notes = "주어진 국가명에 해당하는 국가를 조회", response = CountryEntity.class, produces = "application/json;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "Country name", example = "Korea", dataType = "String", paramType = "query", required = true)
    })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = CountryEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error occurred", response = ErrorResponse.class) })
    @RequestMapping(value = "/search/by-name", method = RequestMethod.GET)
    CompletableFuture<CountryEntity> findByName(@RequestParam("name") String name);

    @ApiOperation(value = "search all countries being matched part of name", notes = "주어진 국가명에 해당하는 모든 국가를 조회", response = CountryEntity.class, responseContainer = "Page<CountryEntity>", produces = "application/json;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "Country name", example = "Korea", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "page", value = "page 번호", example = "0", dataType = "Integer" ,defaultValue = "0"),
            @ApiImplicitParam(name = "size", value = "page 게시물 개수", example = "10", dataType = "Integer" ,defaultValue = "100"),
            @ApiImplicitParam(name = "sort", value = "page 게시물 정렬", example = "id,asc", dataType = "String")
    })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = CountryEntity.class, responseContainer = "Page<CountryEntity>"),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error occurred", response = ErrorResponse.class) })
    @RequestMapping(value = "/search/by-name-like", method = RequestMethod.GET)
    CompletableFuture<Page<CountryEntity>> findByNameLike(@RequestParam("name") String name, Pageable pageable);


    @ApiOperation(value = "search country with iso code", notes = "주어진 iso code에 해당하는 국가를 조회", response = CountryEntity.class, produces = "application/json;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "iso", value = "Country iso code", example = "KR", dataType = "String", paramType = "query", required = true)
    })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = CountryEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error occurred", response = ErrorResponse.class) })
    @RequestMapping(value = "/search/by-iso", method = RequestMethod.GET)
    CompletableFuture<CountryEntity> findByIso(@RequestParam("iso") String iso);
}
