package models;

import enums.Setor;
import enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;

import java.util.UUID;

@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String tag;

    @Column
    private Status status;

    @Column
    private UUID responsable;

    @Column
    private Setor setor;

    @JoinColumn(name = "user")
    private Usuario usuario;

}
