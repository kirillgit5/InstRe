package com.kramar.Inst.Auth.Controllers;

import com.kramar.Inst.core.models.user.UserEntity;
import com.kramar.Inst.network.getUser.GetUserInfoResponse;
import com.kramar.Inst.services.repositories.UserRepository;
import com.kramar.Inst.services.userDetails.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class InternalModel {
}
