package app.module;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.io.Serializable;

public abstract class IdentifiableCommand<U, ID extends Serializable> extends AbstractAuditable<U, ID> {
    @TargetAggregateIdentifier
    private final ID id;

    public IdentifiableCommand(ID id) {
        this.id = id;
        super.setId(id);
    }

    public ID identifier(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        IdentifiableCommand<?, ?> that = (IdentifiableCommand<?, ?>) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
}
