package com.compay.exception;

public class WrongDataExc extends  Exception {
    public WrongDataExc() { super(); }
    public WrongDataExc(String message) { super(message); }
    public WrongDataExc(String message, Throwable cause) { super(message, cause); }
    public WrongDataExc(Throwable cause) { super(cause); }
}
