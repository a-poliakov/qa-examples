package org.example;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
  private final UserDao userDao;

  public List<User> findAll() {
    return userDao.findAll();
  }
}
