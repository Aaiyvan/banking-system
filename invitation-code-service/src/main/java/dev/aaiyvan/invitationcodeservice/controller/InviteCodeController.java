package dev.aaiyvan.invitationcodeservice.controller;

import dev.aaiyvan.invitationcodeservice.exception.InvalidRoleException;
import dev.aaiyvan.invitationcodeservice.model.enums.Role;
import dev.aaiyvan.invitationcodeservice.service.InviteCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/invite-code")
@RequiredArgsConstructor
public class InviteCodeController {

    public final InviteCodeService inviteCodeService;

    @GetMapping("/{role}")
    public ResponseEntity<String> generateInviteCode(
            @PathVariable final String role
    ) {
        try {
            return ResponseEntity.ok(inviteCodeService.generateInviteCode(Role.valueOf(role.toUpperCase())));
        } catch (IllegalArgumentException e) {
            throw new InvalidRoleException("Invalid user role: " + role);
        }
    }

    @GetMapping("/link/{code}")
    public ResponseEntity<Role> useInviteCode(
            @PathVariable final String code
    ) {
        return ResponseEntity.ok(inviteCodeService.getRoleByInviteCode(code));
    }

}
