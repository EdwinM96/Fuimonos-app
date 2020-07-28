package com.fuimonos.app.models

data class ChangePasswordRequest(val currentPassword: String,
                                 val newPassowrd: String,
                                 val confirmPassword: String)
