package br.com.simplepass.simplepassnew.domain

/**
 * Created by leandro on 12/25/16.
 */
data class User(val id: Int,
                val phoneNumber: String,
                val name: String,
                val password: String?,
                val email: String) {

}