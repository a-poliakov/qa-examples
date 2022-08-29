package org.example;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User {
  @Id
  private Long id;
  private String name;
}
