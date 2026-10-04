package com.akshayashokcode.androiddesignpatterns.structural.facade

class Compressor { fun compress(file: String) = "$file.zip" }
class AuthService { fun token() = "token-123" }
class ApiClient { fun upload(file: String, token: String) = "uploaded $file with $token" }

/** Facade: one simple call hides three subsystems and the order they must be used in. */
class UploadFacade(
    private val compressor: Compressor = Compressor(),
    private val auth: AuthService = AuthService(),
    private val api: ApiClient = ApiClient()
) {
    fun upload(file: String) = api.upload(compressor.compress(file), auth.token())
}
