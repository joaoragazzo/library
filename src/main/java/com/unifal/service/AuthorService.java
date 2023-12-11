package com.unifal.service;

import com.unifal.repository.AuthorInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorInterface authorInterface;


}
