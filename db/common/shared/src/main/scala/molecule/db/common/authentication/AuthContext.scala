package molecule.db.common.authentication

import molecule.db.common.api.*

/** Authentication context created by backend after validating credentials
 *
 * SECURITY: This context should ONLY be created on the backend after validating
 * authentication tokens (JWT, OAuth, etc.). Frontend code must NEVER create
 * AuthContext instances directly or send them in requests.
 *
 * The constructor is package-private to prevent direct instantiation outside
 * the authentication package. Use AuthProvider implementations to create instances.
 *
 * @param userId Unique user identifier
 * @param role   Role name (e.g., "Admin", "StandardUser")
 * @param claims Additional authentication claims/metadata
 */
case class AuthContext private[authentication](
  userId: String,
  role: String,
  claims: Map[String, Any] = Map.empty
)

object AuthContext {
  /** Create AuthContext - should ONLY be called by backend code
   *
   * This method is available for backend implementations and testing.
   * Frontend code cannot easily access this due to package visibility.
   */
  private[molecule] def apply(
    userId: String,
    role: String,
    claims: Map[String, Any] = Map.empty
  ): AuthContext = new AuthContext(userId, role, claims)
}


/** Errors that can occur during authentication */
sealed trait AuthError
case class InvalidToken(message: String) extends AuthError
case class ExpiredToken(message: String) extends AuthError
case class UnknownRole(role: String) extends AuthError
case class AuthProviderError(message: String) extends AuthError


/** Authentication provider trait
 *
 * Implement this trait for different authentication mechanisms:
 * - JWT tokens
 * - OAuth2 (Google, Facebook, GitHub, etc.)
 * - SAML
 * - BankID (Swedish authentication)
 * - Custom authentication systems
 */
trait AuthProvider {

  /** Validate authentication token and return AuthContext
   *
   * @param token Authentication token (format depends on provider)
   * @return Right(AuthContext) if valid, Left(AuthError) if invalid
   */
  def authenticate(token: String): Either[AuthError, AuthContext]

  /** Optional: Get provider name for logging/debugging */
  def providerName: String = getClass.getSimpleName
}


/** Example JWT authentication provider */
object JWTAuthProvider {
  def apply(
    secretKey: String,
    issuer: String = "",
    audience: String = ""
  ): AuthProvider = new AuthProvider {

    override def authenticate(token: String): Either[AuthError, AuthContext] = {
      // TODO: Implement JWT validation using a library like jwt-scala
      // 1. Parse JWT token
      // 2. Verify signature with secretKey
      // 3. Check expiration
      // 4. Validate issuer and audience
      // 5. Extract claims (userId, role, etc.)
      ???
    }

    override def providerName: String = "JWT"
  }
}


/** Example OAuth2 authentication provider */
object OAuth2AuthProvider {
  def apply(
    clientId: String,
    clientSecret: String,
    tokenEndpoint: String,
    userInfoEndpoint: String
  ): AuthProvider = new AuthProvider {

    override def authenticate(token: String): Either[AuthError, AuthContext] = {
      // TODO: Implement OAuth2 validation
      // 1. Validate access token with provider
      // 2. Fetch user info from userInfoEndpoint
      // 3. Map provider's user info to AuthContext
      ???
    }

    override def providerName: String = "OAuth2"
  }
}


/** Example Google authentication provider */
object GoogleAuthProvider {
  def apply(clientId: String): AuthProvider = new AuthProvider {

    override def authenticate(idToken: String): Either[AuthError, AuthContext] = {
      // TODO: Implement Google ID token validation
      // 1. Verify token signature using Google's public keys
      // 2. Check audience matches clientId
      // 3. Check expiration
      // 4. Extract email, name, sub (user ID)
      // 5. Map to internal role (e.g., based on email domain)
      ???
    }

    override def providerName: String = "Google"
  }
}


/** Example BankID authentication provider (Swedish authentication) */
object BankIDAuthProvider {
  def apply(
    apiUrl: String,
    certificate: String
  ): AuthProvider = new AuthProvider {

    override def authenticate(token: String): Either[AuthError, AuthContext] = {
      // TODO: Implement BankID validation
      // 1. Parse BankID completion token
      // 2. Verify signature
      // 3. Extract personal number (personnummer)
      // 4. Map to internal user/role
      ???
    }

    override def providerName: String = "BankID"
  }
}


/** Composite provider that tries multiple providers in order */
case class CompositeAuthProvider(providers: List[AuthProvider]) extends AuthProvider {

  override def authenticate(token: String): Either[AuthError, AuthContext] = {
    providers.foldLeft[Either[AuthError, AuthContext]](
      Left(AuthProviderError("No authentication providers configured"))
    ) { (result, provider) =>
      result match {
        case Right(_) => result // Already authenticated
        case Left(_)  =>
          provider.authenticate(token) match {
            case success@Right(_) => success
            case Left(_)          => result // Keep trying other providers
          }
      }
    }
  }

  override def providerName: String =
    s"Composite(${providers.map(_.providerName).mkString(", ")})"
}


/** Provider that extracts role from token prefix for testing
 *
 * Example tokens:
 * - "dev:Admin" -> AuthContext(userId = "dev-user", role = "Admin")
 * - "dev:StandardUser" -> AuthContext(userId = "dev-user", role = "StandardUser")
 *
 * This provider is useful for development and testing but should NEVER
 * be used in production.
 */
object DevAuthProvider extends AuthProvider {

  override def authenticate(token: String): Either[AuthError, AuthContext] = {
    if (!token.startsWith("dev:")) {
      Left(InvalidToken("Dev tokens must start with 'dev:'"))
    } else {
      val role = token.stripPrefix("dev:")
      if (role.isEmpty) {
        Left(InvalidToken("Role cannot be empty"))
      } else {
        Right(AuthContext(
          userId = "dev-user",
          role = role,
          claims = Map("provider" -> "dev")
        ))
      }
    }
  }

  override def providerName: String = "Dev"
}
