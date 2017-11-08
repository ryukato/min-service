package app.module.tservice.api;

import app.module.currency.entity.CurrencyEntity;
import app.module.tservice.entity.TransferServiceEntity;
import app.error.ErrorResponse;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api("Transfer Service 조회를 위한 API를 제공한다.")
@RequestMapping("/api/v1/transfer-services")
public interface TransferServiceResourceApi {

    @ApiOperation(value = "find all transfer services", notes = "모든 TransferService들을 조회한다.", response = TransferServiceEntity.class, responseContainer = "Page<TransferServiceEntity>", produces = "application/json;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "page 번호", example = "0", dataType = "Integer" ,defaultValue = "0"),
            @ApiImplicitParam(name = "size", value = "page 게시물 개수", example = "10", dataType = "Integer" ,defaultValue = "100"),
            @ApiImplicitParam(name = "sort", value = "page 게시물 정렬", example = "id,asc", dataType = "String")
    })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = CurrencyEntity.class, responseContainer = "Page<TransferServiceEntity>"),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error occurred", response = ErrorResponse.class) })

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Page<TransferServiceEntity>> findAll(Pageable pageable);
}
