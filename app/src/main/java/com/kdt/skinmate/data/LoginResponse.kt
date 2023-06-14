package com.kdt.skinmate.data

data class LoginResponse(
	val data: Data,
	val success: Boolean,
	val message: String
)

data class Data(
	val uid: String,
	val token: String
)

