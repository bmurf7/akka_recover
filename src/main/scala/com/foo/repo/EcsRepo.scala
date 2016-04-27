package com.foo.repo

class EcsRepo {
  def performUpdate() = {
    throw new RuntimeException("Database connection failed")
  }
}
