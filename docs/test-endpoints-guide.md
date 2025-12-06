# Test Endpoints Configuration Guide

## What Are Test Endpoints?

Test endpoints are **insecure** endpoints that allow testing authorization without proper authentication. They should **NEVER** be exposed in production.

Currently, the only test endpoint is:
- `/molecule/auth` - Allows setting any userId/role without credential validation

## Default Behavior: SAFE ✅

**By default, test endpoints are DISABLED.** They are only enabled when you explicitly opt-in.

## How to Enable (Testing/Development Only)

### Option 1: Environment Variable (Recommended for local testing)

```bash
# In your terminal
export MOLECULE_ENABLE_TEST_ENDPOINTS=true

# Run your server
sbt run

# Or for a specific run
MOLECULE_ENABLE_TEST_ENDPOINTS=true sbt run
```

### Option 2: System Property (Recommended for test configurations)

```bash
# Via SBT
sbt -Dmolecule.enable.test.endpoints=true run

# Or in your sbt settings
javaOptions += "-Dmolecule.enable.test.endpoints=true"
```

### Option 3: Programmatically (Advanced)

```scala
// Before starting your server
System.setProperty("molecule.enable.test.endpoints", "true")
```

## Verification

### Check if Test Endpoints Are Enabled

When your server starts, look for these log messages:

**✅ Production (Safe):**
```
[INFO] Test endpoints disabled (production mode)
```

**⚠️ Testing (Insecure):**
```
[WARN] ⚠️  TEST ENDPOINTS ENABLED - DO NOT USE IN PRODUCTION!
```

### Test the Endpoint

```bash
# Test if /auth endpoint is accessible
curl -X POST http://localhost:8080/molecule/auth

# Expected results:
# - Enabled (testing):  Returns success (200 OK)
# - Disabled (production): Returns 404 Not Found
```

## Production Deployment

### ✅ Do This:

1. **Don't set the flags** - That's it! Test endpoints are disabled by default.
2. Verify with curl that `/auth` returns 404
3. Check server logs show "Test endpoints disabled"

### ❌ Never Do This:

```bash
# NEVER in production!
export MOLECULE_ENABLE_TEST_ENDPOINTS=true

# NEVER in production!
java -Dmolecule.enable.test.endpoints=true -jar myapp.jar
```

## Docker/Kubernetes Example

### Development (Enable test endpoints)

```dockerfile
# Dockerfile.dev
FROM scala:2.13
WORKDIR /app
COPY . .
ENV MOLECULE_ENABLE_TEST_ENDPOINTS=true
CMD ["sbt", "run"]
```

### Production (Disabled by default)

```dockerfile
# Dockerfile.prod
FROM scala:2.13
WORKDIR /app
COPY . .
# NO MOLECULE_ENABLE_TEST_ENDPOINTS - disabled by default!
CMD ["java", "-jar", "myapp.jar"]
```

### Kubernetes ConfigMap

```yaml
# config-dev.yaml (Development)
apiVersion: v1
kind: ConfigMap
metadata:
  name: molecule-config-dev
data:
  MOLECULE_ENABLE_TEST_ENDPOINTS: "true"

---
# config-prod.yaml (Production)
apiVersion: v1
kind: ConfigMap
metadata:
  name: molecule-config-prod
data:
  # No MOLECULE_ENABLE_TEST_ENDPOINTS key - disabled by default!
  DATABASE_URL: "jdbc:postgresql://..."
```

## CI/CD Configuration

### GitHub Actions Example

```yaml
# .github/workflows/test.yml
name: Tests
on: [push]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Run tests with test endpoints
        env:
          MOLECULE_ENABLE_TEST_ENDPOINTS: true
        run: sbt test

  deploy-production:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Build production image
        run: |
          # NO test endpoint flags!
          docker build -t myapp:prod -f Dockerfile.prod .
      - name: Verify test endpoints disabled
        run: |
          docker run myapp:prod &
          sleep 5
          # Should return 404
          ! curl -f http://localhost:8080/molecule/auth
```

## Testing Authorization in Your Test Suites

The `withAuth()` method is `private[molecule]`, so you can only use it in test code within the molecule package. For your application tests:

### JVM Tests

```scala
import molecule.db.common.facade.JdbcConn_JVM

class MyAuthorizationTest extends munit.FunSuite {
  test("Admin can access admin data") {
    // This works because withAuth is private[molecule]
    // and test code can access it
    val adminConn = baseConn.withAuth("user1", "Admin")
    // ... test with adminConn
  }
}
```

### JS Tests (Require Test Server)

```scala
// 1. Start your test server with test endpoints enabled:
// MOLECULE_ENABLE_TEST_ENDPOINTS=true sbt "dbServer/Test/run"

// 2. Run your JS tests:
// sbt "dbSqlH2JS/test"

class MyAuthorizationTest extends munit.FunSuite {
  test("Admin can access admin data") {
    for {
      adminConn <- baseConn.withAuth("user1", "Admin")
      data <- AdminData.info.query.get(using adminConn)
    } yield {
      assert(data.nonEmpty)
    }
  }
}
```

## Troubleshooting

### Problem: Test endpoints not working in tests

**Solution:** Enable them explicitly for tests:

```bash
# In terminal where you run tests
export MOLECULE_ENABLE_TEST_ENDPOINTS=true
sbt test
```

### Problem: Worried test endpoints might be enabled in production

**Verification:**
1. Check your deployment scripts - do they set `MOLECULE_ENABLE_TEST_ENDPOINTS`?
2. Check your Dockerfile/K8s configs - is the flag present?
3. Start your production server and check logs for "Test endpoints disabled"
4. Try: `curl http://your-production-server/molecule/auth` (should be 404)

### Problem: Need to run some services with test endpoints, others without

**Solution:** Use different configurations:

```bash
# Test server (for JS frontend testing)
MOLECULE_ENABLE_TEST_ENDPOINTS=true java -jar server.jar --port 8080

# Production server
java -jar server.jar --port 9090
```

## Summary

| Environment | Setting | Safety |
|------------|---------|--------|
| **Production** | Don't set any flags | ✅ Safe (disabled) |
| **Local Testing** | `export MOLECULE_ENABLE_TEST_ENDPOINTS=true` | ⚠️ Testing only |
| **CI/CD Tests** | Set in test job environment | ⚠️ Testing only |
| **JS Frontend Tests** | Enable on test server | ⚠️ Testing only |

**Remember:** The safest thing is to do nothing. Test endpoints are disabled by default! 🔒
