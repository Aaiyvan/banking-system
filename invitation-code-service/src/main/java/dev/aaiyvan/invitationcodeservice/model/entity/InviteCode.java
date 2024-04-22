package dev.aaiyvan.invitationcodeservice.model.entity;

import dev.aaiyvan.invitationcodeservice.model.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Entity representing an invite code.
 * It has a code and a role associated with it.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InviteCode {

    /**
     * The invite code.
     */
    String code;

    /**
     * The role associated with the invite code.
     */
    Role role;

}
