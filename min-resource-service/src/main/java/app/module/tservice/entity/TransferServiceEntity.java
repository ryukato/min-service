package app.module.tservice.entity;

import app.entity.AuditEntity;
import app.tservice.model.TransferService;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.hateoas.Identifiable;

import java.io.Serializable;

@SuppressWarnings("unused")
@Document(collection = "transfer-services")
public class TransferServiceEntity extends AuditEntity implements Identifiable, Serializable {

    @Id
    private String id;
    private final TransferService transferService;
    private boolean isActive;

    public TransferServiceEntity(TransferService transferService) {
        this.transferService = transferService;
    }

    public TransferService getTransferService() {
        return transferService;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Serializable getId() {
        return id;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Field("active")
    public boolean isActive() {
        return this.isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferServiceEntity that = (TransferServiceEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TransferServiceEntity{" +
                "id='" + id + '\'' +
                ", transferService=" + transferService + super.toString()+
                ", isActive=" + isActive +
                '}';
    }
}
