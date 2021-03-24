package br.com.api.curso.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class AbstractEntity {

    @Id
    private String id;

    @PrePersist
    private void genericPrePersist(){
        this.id = UUID.randomUUID().toString().replace("-","");
    }

}
