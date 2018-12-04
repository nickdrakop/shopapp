/**
 @author nick.drakopoulos
 */

package com.market.shopapp.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.Instant;

@MappedSuperclass
public abstract class AbstractEntity<T> {

    @Column(name = "created_at", updatable = false, nullable = false)
    protected Instant createdAt;

    public abstract T getId();

    public abstract void setId(T id);

    @PrePersist
    public void onPrePersist() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    protected void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
