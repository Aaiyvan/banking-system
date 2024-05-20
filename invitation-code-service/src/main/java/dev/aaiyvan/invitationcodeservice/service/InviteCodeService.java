package dev.aaiyvan.invitationcodeservice.service;

import dev.aaiyvan.invitationcodeservice.model.enums.Role;

public interface InviteCodeService {

    String generateInviteCode(Role role);

    Role getRoleByInviteCode(String code);

}
