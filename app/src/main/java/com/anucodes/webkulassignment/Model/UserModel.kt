package com.anucodes.webkulassignment.Model

data class UserModel(
    val id: Int,
    val username: String,
    val name: String,
    val email: String,
    val phone: String,
    val website: String,
    val address: Address,
    val company: Company
)

data class Address(
    val suite: String,
    val street: String,
    val city: String,
    val zipcode: String
)

data class Company(
    val name: String,
    val catchPhrase: String,
    val bs: String
)