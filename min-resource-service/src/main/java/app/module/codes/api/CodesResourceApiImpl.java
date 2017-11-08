package app.module.codes.api;

import app.codes.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CodesResourceApiImpl implements CodesResourceApi {
    @Override
    public ResponseEntity<List<ApplicationType>> applicationTypes() {
        return ResponseEntity.ok(getEnumValues(ApplicationType.class));
    }

    @Override
    public ResponseEntity<List<ReceiveMethod>> receiveMethods() {
        return ResponseEntity.ok(getEnumValues(ReceiveMethod.class));
    }

    @Override
    public ResponseEntity<List<ServiceMethod>> serviceMethods() {
        return ResponseEntity.ok(getEnumValues(ServiceMethod.class));
    }

    @Override
    public ResponseEntity<List<TransferMethod>> transferMethods() {
        return ResponseEntity.ok(getEnumValues(TransferMethod.class));
    }

    @Override
    public ResponseEntity<List<TransferFeeType>> transferFeeTypes() {
        return ResponseEntity.ok(getEnumValues(TransferFeeType.class));
    }
}
