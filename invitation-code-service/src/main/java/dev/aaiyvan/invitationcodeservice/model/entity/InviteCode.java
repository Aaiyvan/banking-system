package dev.aaiyvan.invitationcodeservice.model.entity;

import dev.aaiyvan.invitationcodeservice.model.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InviteCode {

    String code;

    Role role;

}
