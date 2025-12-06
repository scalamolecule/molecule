# Security Documentation

## Overview

Molecule provides a **robust authorization framework** (role-based access control) but intentionally does **NOT** include authentication (credential validation). This separation follows security best practices and allows you to integrate with any authentication system of your choice.

## Clear Security Boundaries

### ✅ What Molecule Provides (Authorization)

- **Role-based access control** at entity and attribute level
- **Action-level permissions** (query, save, update, delete)
- **Runtime authorization checks** on all database operations
- **Tested and production-ready** authorization framework

### ⚠️ What You Must Provide (Authentication)

- **Credential validation** (username/password, tokens, etc.)
- **User identity verification** before creating `AuthContext`
- **Session management** and token generation
- **Production-ready authentication** implementation

## Testing vs Production

### Testing: `withAuth()` Method

For **testing only**, Molecule provides a convenient `withAuth()` method:

```scala
// JVM Testing
val adminConn = baseConn.withAuth("user1", "Admin")
Person.name.query.get(using adminConn)

// JS Testing (with test server)
for {
  adminConn <- baseConn.withAuth("user1", "Admin")
  names <- Person.name.query.get(using adminConn)
} yield names
```

**⚠️ SECURITY WARNING:**
- This method is `private[molecule]` - **not accessible in your application code**
- It **bypasses all credential validation**
- It is **ONLY for testing** authorization rules
- The `/auth` endpoint **must be removed** from production servers

### Production: Proper Authentication

For **production**, you must implement secure authentication:

```scala
// 1. Validate credentials
def login(username: String, password: String): Future[Option[User]] = {
  // Validate against your user database
  // Hash passwords with bcrypt/argon2
  // Return user with roles if valid
}

// 2. Generate secure token (JWT recommended)
def generateToken(user: User): String = {
  // Create signed JWT with user claims
  // Set appropriate expiration
}

// 3. Validate token on each request
def validateAndCreateAuthContext(token: String): Option[AuthContext] = {
  verifyJWT(token) match {
    case Valid(claims) =>
      Some(AuthContext(claims.userId, claims.role, claims.data))
    case Invalid(_) =>
      None
  }
}
```

See [`ProductionAuthExample.scala`](db/common/shared/src/main/scala/molecule/db/common/authentication/ProductionAuthExample.scala) for complete implementation examples.

## Security Architecture

### Two-Layer Security Model

```
┌─────────────────────────────────────────────────────┐
│ Authentication (YOUR RESPONSIBILITY)                │
│ - Validate credentials                              │
│ - Generate secure tokens                            │
│ - Verify token signatures                           │
│ - Create AuthContext with validated claims          │
└────────────────┬────────────────────────────────────┘
                 │ AuthContext(userId, role, data)
                 ▼
┌─────────────────────────────────────────────────────┐
│ Authorization (MOLECULE HANDLES)                    │
│ - Check role has permission for entity              │
│ - Validate action allowed (query/save/update/delete)│
│ - Enforce attribute-level access control            │
│ - Return data or throw access denied error          │
└─────────────────────────────────────────────────────┘
```

### Platform-Specific Implementations

#### JVM (Direct Database Access)

```scala
// Create connection with authentication
val authCtx = validateUserCredentials(token) // Your implementation
val conn = JdbcConn_JVM(proxy, sqlConn, Some(authCtx))

// Authorization happens automatically
Person.name.age.query.get(using conn) // ✓ Authorized
Admin.secretData.query.get(using conn) // ✗ Access denied
```

#### JavaScript (Server-Backed)

```scala
// Client sends token, server validates and creates AuthContext
val conn = JdbcConn_JS(proxy, "api.example.com", 443, "https")

// Server-side: Validate token and populate authCache
authCache.put(conn.uuid, validatedAuthContext)

// Authorization happens server-side
Person.name.age.query.get(using conn) // ✓ Authorized
```

## Production Deployment Checklist

### Before Going to Production

#### Remove Test Authentication

The `/auth` endpoint is **automatically disabled in production** by default. It's only enabled when you explicitly set an environment variable or system property.

**✅ Safe by Default:** Just don't enable test endpoints!

- [ ] **DO NOT set** `MOLECULE_ENABLE_TEST_ENDPOINTS=true` in production
- [ ] **DO NOT set** `-Dmolecule.enable.test.endpoints=true` in production JVM args
- [ ] Verify `/auth` returns 404 in production by testing: `curl http://your-server/molecule/auth`
- [ ] Verify `withAuth()` is not accessible (it's `private[molecule]`)

**For Testing/Development:**
```bash
# Enable test endpoints in development
export MOLECULE_ENABLE_TEST_ENDPOINTS=true

# Or via JVM system property
sbt -Dmolecule.enable.test.endpoints=true run
```

**In Production:** Simply don't set these flags. The endpoint won't be registered.

#### Implement Authentication

- [ ] Choose authentication method (JWT, OAuth2, sessions)
- [ ] Implement credential validation
- [ ] Generate secure tokens with appropriate expiration
- [ ] Validate tokens on every request
- [ ] Create `AuthContext` only from validated credentials

#### Secure Communication

- [ ] Use HTTPS only (TLS 1.2+)
- [ ] Set secure cookie flags (HttpOnly, Secure, SameSite)
- [ ] Implement CORS with strict origin whitelist
- [ ] Add CSRF protection
- [ ] Use secure headers (CSP, HSTS, X-Frame-Options)

#### Session Security

- [ ] Use strong secret keys (256+ bits random)
- [ ] Rotate keys periodically
- [ ] Store sessions in secure backend (Redis, database)
- [ ] Implement session timeout and renewal
- [ ] Clear sessions on logout

#### Password Security

- [ ] Hash passwords with bcrypt or argon2
- [ ] Never store passwords in plaintext
- [ ] Implement password complexity requirements
- [ ] Add rate limiting on login attempts
- [ ] Lock accounts after failed attempts

#### Monitoring

- [ ] Log authentication failures
- [ ] Monitor for brute force attempts
- [ ] Set up intrusion detection
- [ ] Alert on suspicious patterns

## Common Authentication Patterns

### 1. JWT (JSON Web Tokens) - Recommended

**Pros:** Stateless, scalable, industry standard
**Cons:** Cannot revoke before expiry

```scala
import pdi.jwt.{Jwt, JwtAlgorithm, JwtClaim}

// Generate token on login
val claim = JwtClaim(
  content = s"""{"userId":"$userId","role":"$role"}""",
  expiration = Some(System.currentTimeMillis() / 1000 + 3600)
)
val token = Jwt.encode(claim, secretKey, JwtAlgorithm.HS256)

// Validate token on each request
Jwt.decode(token, secretKey, Seq(JwtAlgorithm.HS256)) match {
  case Success(claims) =>
    val authCtx = AuthContext(claims.userId, claims.role)
  case Failure(_) =>
    // Invalid token, deny access
}
```

### 2. Session Cookies

**Pros:** Easy to implement, can revoke sessions
**Cons:** Requires session storage, less scalable

```scala
// Generate secure session ID
val sessionId = java.util.UUID.randomUUID().toString

// Store in Redis/database
sessionStore.put(sessionId, UserSession(userId, role, expiresAt))

// Set secure cookie
setCookie("sessionId", sessionId, HttpOnly = true, Secure = true)

// Validate on each request
sessionStore.get(sessionId) match {
  case Some(session) if !session.isExpired =>
    val authCtx = AuthContext(session.userId, session.role)
  case _ =>
    // Invalid or expired session
}
```

### 3. OAuth2 / OpenID Connect

**Pros:** Delegates auth, SSO support
**Cons:** Complex setup, external dependency

```scala
// Use libraries like pac4j or scala-oauth2-provider
// Validate OAuth token with identity provider
// Map OAuth claims to Molecule roles
```

### 4. API Keys

**Pros:** Simple for machine-to-machine
**Cons:** Less secure if keys leak

```scala
// Generate cryptographically random key
val apiKey = SecureRandom.getInstanceStrong()
  .ints(32, 33, 127)
  .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
  .toString()

// Hash before storing (like passwords)
val hashedKey = BCrypt.hashpw(apiKey, BCrypt.gensalt())

// Validate on each request
if (BCrypt.checkpw(providedKey, storedHashedKey)) {
  val authCtx = AuthContext(userId, role)
}
```

## FAQ

### Q: Why doesn't Molecule include authentication?

**A:** Authentication is highly application-specific. By separating concerns:
- You can use **any authentication system** (JWT, OAuth, LDAP, custom)
- Molecule focuses on what it does best: **authorization**
- You maintain **full control** over security policies
- No vendor lock-in to a specific auth system

### Q: Is the authorization framework secure?

**A:** Yes! The authorization framework is:
- ✅ Tested across all database platforms
- ✅ Enforced at runtime on all operations
- ✅ Cannot be bypassed by client code
- ✅ Uses bitmask-based permissions for efficiency
- ✅ Provides both strict and permissive modes

### Q: Can I use `withAuth()` in production?

**A:** **NO!** The `withAuth()` method is:
- ❌ Package-private (you can't access it anyway)
- ❌ Bypasses credential validation
- ❌ Only for testing authorization rules
- ❌ Would allow privilege escalation

### Q: What if I forget to authenticate?

**A:** Without authentication, all operations run as **unauthenticated**:
- Only **public entities** are accessible
- Entities requiring roles will return "Access denied"
- This is **safe by default** (principle of least privilege)

### Q: How do I test authorization?

**A:** Use the `withAuth()` method in your test suites:

```scala
"Admin can access sensitive data" - testDb {
  for {
    adminConn <- baseConn.withAuth("user1", "Admin")
    _ <- SensitiveData.info.save.transact(using adminConn)
    data <- SensitiveData.info.query.get(using adminConn)
  } yield {
    data ==> List("sensitive info")
  }
}

"Guest cannot access sensitive data" - testDb {
  for {
    adminConn <- baseConn.withAuth("user1", "Admin")
    _ <- SensitiveData.info.save.transact(using adminConn)

    guestConn <- baseConn.withAuth("user2", "Guest")
    result <- SensitiveData.info.query.get(using guestConn)
      .recover { case ModelError(err) => err }
  } yield {
    result ==> "Access denied: Role 'Guest' cannot query entity 'SensitiveData'"
  }
}
```

## Resources

- **Production Auth Example:** [`ProductionAuthExample.scala`](db/common/shared/src/main/scala/molecule/db/common/authentication/ProductionAuthExample.scala)
- **Authorization Setup:** See Molecule documentation on defining roles and permissions
- **JWT Libraries:** jwt-scala, jose4j, java-jwt
- **OAuth Libraries:** pac4j, scala-oauth2-provider
- **Password Hashing:** bcrypt, argon2

## Reporting Security Issues

If you discover a security vulnerability in Molecule's authorization framework, please email security@molecule.com or create a private security advisory on GitHub.

**DO NOT** create public issues for security vulnerabilities.

## License

This security documentation is part of the Molecule project and is provided as-is for informational purposes.
