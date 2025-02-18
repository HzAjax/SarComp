package ru.volodin.SarComp.entity.authorization;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role /*implements GrantedAuthority*/ {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;
    protected String name;

    /*@JsonIgnore
    @Override
    public String getAuthority() {
        return name;
    }*/
}
