# Molecule Testing Conventions

**Created**: 2026-01-04
**Purpose**: Document Molecule's Scala/uTest testing patterns for consistent test writing
**Audience**: AI assistants and contributors

---

## Core Principle

**Use Scala/uTest idiomatic assertions, not Java-style assertions.**

---

## Standard Assertion Pattern

### ✅ CORRECT: Use `==>`

```scala
// Compare whole results
_ <- Entity.i.a1.int.query.get.map(_ ==> List(
  (1, int1),
  (2, int2),
  (3, int3),
))

// Single result
_ <- Entity.int(min).query.get.map(_ ==> List(int1))

// Nested structures
_ <- Entity.i.a1.int(distinct).query.get.map(_ ==> List(
  (1, Set(int1)),
  (2, Set(int2, int3)),
))

// Empty results
_ <- Entity.i.a1.int(Seq.empty[Int]).query.get.map(_ ==> List())
```

### ❌ INCORRECT: Don't use Java-style assertions

```scala
// DON'T DO THIS
_ <- Entity.int.query.get.map { result =>
  assert(result.size == 3)
  assert(result.contains(int1))
  assert(result.head == int1)
}

// DON'T DO THIS
_ <- Entity.int.query.get.map { result =>
  assert(result == List(int1, int2, int3))
}
```

---

## SQL Inspection Pattern

### Special Case: SQL Contains Check

When verifying SQL generation (and **only** for SQL inspection), use `.contains()` with full SQL string and `==> true`:

```scala
_ <- Entity.s.sub(Ref.id(count).entity_(Entity.id_)).query.inspect.map(_.contains(
  """SELECT DISTINCT
    |  Entity.s,
    |  (
    |    SELECT
    |      COUNT(Ref.id)
    |    FROM Ref
    |    WHERE
    |      Ref.entity = Entity.id
    |  )
    |FROM Entity
    |WHERE
    |  Entity.s IS NOT NULL;""".stripMargin
) ==> true)
```

**Key points:**
- Use `.contains()` with the **full expected SQL**, not partial fragments
- Use multiline strings with `stripMargin` for readability
- End with `==> true`
- This is the **only** acceptable use case for `==> true`

### ❌ INCORRECT: Partial SQL checks with assert()

```scala
// DON'T DO THIS - partial checks
_ <- Entity.s.sub(Ref.id(count).entity_(Entity.id_)).query.inspect.map { sql =>
  assert(sql.contains("SELECT"))
  assert(sql.contains("FROM Entity"))
  assert(sql.contains("COUNT"))
}

// DON'T DO THIS - using assert() instead of ==>
_ <- Entity.s.query.inspect.map { sql =>
  assert(sql.contains("SELECT Entity.s FROM Entity"))
}
```

### Alternative: Full Inspection Output

For complete verification including DataModel, compare the full inspection output:

```scala
_ <- Person.name.tpe.query.inspect.map(_ ==> {
  s"""=== QUERY =====================================
     |DataModel(
     |  List(
     |    AttrOneManString("Person", "name", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 1)),
     |    AttrOneManString("Person", "type", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 2))
     |  ),
     |  Set(1, 2), 0, 0, Nil
     |)
     |
     |SELECT DISTINCT
     |  Person.name,
     |  Person.type
     |FROM Person
     |WHERE
     |  Person.name IS NOT NULL AND
     |  Person.type IS NOT NULL;
     |----------------------------------------
     |""".stripMargin
})
```

---

## Test Structure Pattern

### Standard Test Layout

```scala
"Test description" - types {  // or refs, joinTable, etc.
  for {
    // Setup: Insert test data
    _ <- Entity.i.int.insert(List(
      (1, int1),
      (2, int2),
      (3, int3),
    )).transact

    // Test: Multiple assertions in sequence
    _ <- Entity.i.a1.int.query.get.map(_ ==> List(
      (1, int1),
      (2, int2),
      (3, int3),
    ))

    _ <- Entity.int(min).query.get.map(_ ==> List(int1))

    _ <- Entity.int(max).query.get.map(_ ==> List(int3))
  } yield ()
}
```

### Multiple Related Tests

```scala
"Feature category" - types {
  for {
    _ <- Entity.i.int.insert(a, b, c).transact

    // Test case 1
    _ <- Entity.i.a1.int(int1).query.get.map(_ ==> List(a))

    // Test case 2
    _ <- Entity.i.a1.int(int1, int2).query.get.map(_ ==> List(a, b))

    // Test case 3
    _ <- Entity.i.a1.int.not(int1).query.get.map(_ ==> List(b, c))
  } yield ()
}
```

---

## Common Patterns

### Empty Results

```scala
_ <- Entity.i.int(int0).query.get.map(_ ==> List())
```

### Single Result

```scala
_ <- Entity.int(min).query.get.map(_ ==> List(int1))
```

### Multiple Results

```scala
_ <- Entity.i.a1.int.query.get.map(_ ==> List(
  (1, int1),
  (2, int2),
))
```

### Nested Data Structures

```scala
_ <- Entity.i.a1.int(distinct).query.get.map(_ ==> List(
  (1, Set(int1)),
  (2, Set(int2, int3)),
))
```

### Head/Single Value from Result

```scala
_ <- Entity.int(distinct).query.get.map(_.head ==> Set(
  int1, int2, int3
))
```

### Error Testing

```scala
_ <- Entity.s.sub(Ref.id(count)).query.get
  .map(_ ==> "Should fail").recover { case ModelError(err) =>
    err ==> "Expected error message here"
  }
```

---

## Why These Patterns?

### 1. Whole Result Comparison

**Benefit**: Tests verify complete behavior, not just partial conditions
- Catches unexpected extra/missing results
- Self-documenting expected output
- Easier to debug failures

### 2. Using `==>` instead of `assert()`

**Benefit**: uTest-idiomatic, better error messages
- Shows expected vs actual on failure
- Consistent with Scala testing conventions
- More readable test code

### 3. Full SQL Comparison (when needed)

**Benefit**: Verifies complete SQL generation
- Catches subtle SQL bugs
- Documents expected query structure
- Comprehensive validation

---

## Test Domain Conventions

### Domain Prefixes

- `types` - Main test domain (Entity, Ref, Other, Color)
- `refs` - Relationship testing (A-H entities)
- `joinTable` - Many-to-many (A, B, J where J extends Join)
- `uniques` - Unique constraint testing
- `validation` - Validation rules
- `social` - Authorization examples
- `segments` - Namespaced entities

### Test Data Naming

```scala
// Use descriptive variable names for tuples
val a = (1, int1)
val b = (2, int2)
val c = (3, int3)

// Or inline for clarity
_ <- Entity.i.int.insert(List(
  (1, int1),
  (2, int2),
  (3, int3),
)).transact
```

---

## Common Mistakes to Avoid

### ❌ Using assert() for Result Checking

```scala
// WRONG
_ <- Entity.int.query.get.map { result =>
  assert(result == List(int1, int2))
}

// RIGHT
_ <- Entity.int.query.get.map(_ ==> List(int1, int2))
```

### ❌ Partial SQL Checks

```scala
// WRONG
_ <- Entity.s.query.inspect.map { sql =>
  assert(sql.contains("SELECT"))
  assert(sql.contains("Entity.s"))
}

// RIGHT
_ <- Entity.s.query.inspect.map(_.contains(
  """SELECT DISTINCT
    |  Entity.s
    |FROM Entity
    |WHERE
    |  Entity.s IS NOT NULL;""".stripMargin
) ==> true)
```

### ❌ Checking Size Instead of Contents

```scala
// WRONG
_ <- Entity.int.query.get.map { result =>
  assert(result.size == 3)
}

// RIGHT
_ <- Entity.int.query.get.map(_ ==> List(int1, int2, int3))
```

---

## Examples from Codebase

### Filter Test Example

From `db/compliance/shared/src/test/scala/molecule/db/compliance/test/filter/one/types/FilterOne_Int.scala`:

```scala
"Mandatory" - types {
  val a = (1, int1)
  val b = (2, int2)
  val c = (3, int3)
  for {
    _ <- Entity.i.int.insert(List(a, b, c)).transact

    _ <- Entity.i.a1.int.query.get.map(_ ==> List(a, b, c))
    _ <- Entity.i.a1.int(int1).query.get.map(_ ==> List(a))
    _ <- Entity.i.a1.int(int1, int2).query.get.map(_ ==> List(a, b))
    _ <- Entity.i.a1.int.not(int1).query.get.map(_ ==> List(b, c))
  } yield ()
}
```

### Aggregation Test Example

From `db/compliance/shared/src/test/scala/molecule/db/compliance/test/aggregation/any/Aggr_Int.scala`:

```scala
"distinct" - types {
  for {
    _ <- Entity.i.int.insert(List(
      (1, int1),
      (2, int2),
      (2, int2),
      (2, int3),
    )).transact

    _ <- Entity.i.a1.int(distinct).query.get.map(_ ==> List(
      (1, Set(int1)),
      (2, Set(int2, int3)),
    ))

    _ <- Entity.int(distinct).query.get.map(_.head ==> Set(
      int1, int2, int3
    ))
  } yield ()
}
```

### Relationship Test Example

From `db/compliance/shared/src/test/scala/molecule/db/compliance/test/relationship/ManyToMany.scala`:

```scala
"M2M property" - joinTable {
  import molecule.db.compliance.domains.dsl.JoinTable.*
  for {
    List(a1, a2) <- A.i.insert(1, 2).transact.map(_.ids)
    List(b1, b2) <- B.i.insert(3, 4).transact.map(_.ids)

    _ <- J.a.i.b.insert(
      (a1, 10, b1),
      (a2, 20, b2),
    ).transact

    _ <- A.i.Js.i.a1.B.i.query.get.map(_ ==> List(
      (1, 10, 3),
      (2, 20, 4),
    ))
  } yield ()
}
```

---

## Summary

1. **Always use `==>` for assertions**, not `assert()`
2. **Compare whole results**, not partial checks
3. **For SQL inspection**: use `.contains(fullSQL) ==> true`
4. **Never use `assert()` with `.contains()` for partial checks**
5. **Structure tests with clear setup → test → assert flow**
6. **Use descriptive test names and clear data setup**

---

## Quick Reference

| Pattern | Use |
|---------|-----|
| `_ ==> List(...)` | Standard result comparison |
| `.head ==> value` | Single value from collection |
| `_.contains(fullSQL) ==> true` | SQL inspection (ONLY use case for `==> true`) |
| `.recover { case ModelError(err) => ... }` | Error testing |
| `==> List()` | Empty result |
| `==> Set(...)` | Set comparison |

---

## File Location

This document: `docs/claude-context/testing-conventions.md`

Related:
- `00-quick-reference.md` - Quick syntax reference
- `compliance-test-taxonomy.md` - Complete test coverage
- `01-project-structure.md` - Test file organization
