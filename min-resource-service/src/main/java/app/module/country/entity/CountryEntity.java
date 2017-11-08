package app.module.country.entity;

import app.entity.AuditEntity;
import app.country.model.Country;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.hateoas.Identifiable;

import java.io.Serializable;
import java.util.List;

@Document(collection="countries")
public class CountryEntity extends AuditEntity implements Identifiable, Serializable {

    @Id
    private String id;

    private final Country country;

    private boolean isActive;

    public CountryEntity(Country country) {
        this.country = country;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public String getId() {
        return id;
    }

    @Field("name")
    public String name() {
        return this.country.getName();
    }

    @Field("iso")
    public String iso() {
        return this.country.getIsoCode();
    }

    @Field("currencies")
    public List<String> currencies() {
        return country.getCurrencies();
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

        CountryEntity that = (CountryEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CountryEntity{" +
                "id='" + id + '\'' +
                ", country=" + country + super.toString()+
                '}';
    }
}
