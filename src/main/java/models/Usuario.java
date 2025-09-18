package models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    private UUID id;


    private String name;

    private String function;

}
