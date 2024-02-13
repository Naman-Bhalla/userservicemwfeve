package com.scaler.userservicemwfeve.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonDeserialize
public class Role extends BaseModel {
    private String name;
}
