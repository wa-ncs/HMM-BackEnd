package com.hmm.hmm.interfaces;

public class ApiResponse {
  private boolean success;
  private String message;

  public ApiResponse(boolean success, String message) {
    this.success = success;
    this.message = message;
  }
}
