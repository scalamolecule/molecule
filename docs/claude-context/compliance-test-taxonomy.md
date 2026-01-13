# Molecule Compliance Test Taxonomy

## Naming Conventions (STRICT - Validated by sbt-molecule)

### Entity Names: `[A-Z][a-zA-Z0-9]*`
- MUST start with uppercase letter, followed by letters/digits only
- ✅ Valid: `Person`, `Invoice`, `UserProfile`, `Item2`
- ❌ Invalid: `person`, `Person_`, `_Entity`

### Segment Names: `[a-z][a-z0-9]*`
- MUST start with lowercase letter
- Followed by lowercase letters and/or digits only
- ✅ Valid: `accounting`, `warehouse`, `gen2`, `lit`
- ❌ Invalid: `Accounting`, `Ware_house`, `_segment`, `2gen`

---

## Overview
Comprehensive mapping of Molecule's complete feature set as tested in the compliance test suite.

- **Total test files:** 346 scala files
- **Test categories:** 14 major categories covering query, mutation, validation, relationships, and advanced features
- **Test location:** `db/compliance/shared/src/test/scala/molecule/db/compliance/test/`
- **Database coverage:** Tests run against h2, mariadb, mysql, postgresql, sqlite

---

## 1. Aggregation (`aggregation/`, 43 files)

Tests for aggregate functions across all data types and relationship contexts.

### 1.1 Any Type Aggregates (`any/`, 21 files)
**Purpose:** Generic aggregates that work on any type
**DSL patterns:**
- `Entity.attr(distinct)` - distinct values returned as Set
- `Entity.attr(min)` - minimum value
- `Entity.attr(max)` - maximum value
- `Entity.attr(min(n))` - n smallest values as Set
- `Entity.attr(max(n))` - n largest values as Set
- `Entity.attr(sample)` - random sample value
- `Entity.attr(sample(n))` - n random sample values as Set
- `Entity.attr(count)` - count all values
- `Entity.attr(countDistinct)` - count unique values

**Filter operations on aggregates:**
```scala
Entity.attr(count)(4)          // equals
Entity.attr(count).not(3)      // not equals
Entity.attr(count).<(5)        // less than
Entity.attr(count).<=(4)       // less than or equal
Entity.attr(count).>(3)        // greater than
Entity.attr(count).>=(4)       // greater than or equal
```

**Types covered:** Boolean, Int, String, Double, Byte, Short, Long, Float, Char, BigInt, BigDecimal, Date, Duration, Instant, LocalDate, LocalTime, LocalDateTime, OffsetTime, OffsetDateTime, ZonedDateTime, UUID, URI, ref

**Key tests:**
- `Aggr_Int.scala` - comprehensive aggregate tests for Int
- `Aggr_String_.scala` - aggregates for String types
- `Aggr_ref.scala` - aggregates on reference attributes

### 1.2 Number Aggregates (`number/`, 7 files)
**Purpose:** Statistical aggregates for numeric types only
**DSL patterns:**
- `Entity.attr(sum)` - sum of all values
- `Entity.attr(avg)` - average (returns Double)
- `Entity.attr(median)` - median value (returns Double)
- `Entity.attr(variance)` - variance (returns Double)
- `Entity.attr(stddev)` - standard deviation (returns Double)

**Database support notes:**
- median: not available for mariadb, mysql, sqlite
- variance/stddev ops: not available for mariadb, mysql, sqlite

**Types covered:** Int, Long, Float, Double, Byte, Short, BigInt, BigDecimal

**Note**: Scala 3 tuples support unlimited arity (no 22-limit). However, bindings are limited to max 22 parameters.

**Key tests:**
- `AggrNum_Int.scala` - comprehensive numeric aggregates with filter operations
- `AggrNum_Double_.scala` - decimal number aggregates

### 1.3 Ref Aggregates (`ref/`, 6 files)
**Purpose:** Aggregates across entity relationships
**DSL patterns:**
```scala
A.B.i(count)                    // count across ref
A.i.a1.B.i(count)              // grouped count
A.B.C.i(countDistinct)         // distinct count across multiple refs
A.i.a1.B.i(count).C.i(count)   // multiple aggregates
```

**Key tests:**
- `AggrRef_count.scala` - count/countDistinct across refs
- `AggrRef_min_max.scala` - min/max across refs
- `AggrRef_sample.scala` - random sampling across refs
- `AggrRef_distinct.scala` - distinct values across refs

### 1.4 RefNum Aggregates (`refNum/`, 4 files)
**Purpose:** Numeric aggregates across relationships
**Key tests:**
- `AggrRefNum_sum.scala` - sum across refs
- `AggrRefNum_avg.scala` - average across refs
- `AggrRefNum_median.scala` - median across refs
- `AggrRefNum_stddev.scala`, `AggrRefNum_variance.scala` - statistical functions

### 1.5 Relational Aggregates (`AggrRelations.scala`)
**Purpose:** Complex aggregation across multi-level relationships
**DSL patterns:**
```scala
A.i.B.i.C.s.i(count)                    // count across 3 entities
A.i.a1.B.i.a2.C.s.a3.i(count)          // grouped multi-level count
A.i(count).B.i.C.i.a1                   // aggregate positioning
A.i_.B.i(countDistinct).C.i_.query      // tacit attrs with aggregates
```

---

## 2. API (`api/`, 3 files)

Tests for different API execution models.

### 2.1 AsyncApi (`AsyncApi.scala`)
**Purpose:** Future-based asynchronous API
**DSL patterns:**
```scala
// CRUD
Entity.int.insert(1, 2).transact.map(_.ids)
Entity.int(3).save.transact
Entity(id).int(10).update.transact
Entity(id).delete.transact
Entity.int.a1.query.get

// Optional refs (left join)
A.i.B.?(B.i.s).query.get            // Returns (Int, Option[(Int, String)])
A.i.a1.B.?(B.i.s).query.get         // Sorted optional refs

// Streaming (JVM only)
Entity.i.query.stream
  .compile
  .toList
  .unsafeToFuture()

// Validation
Tpe.string("a").save.transact
  .recover { case ValidationErrors(errorMap) => ... }
```

### 2.2 IOApi (`IOApi.scala`)
**Purpose:** Cats Effect IO-based API for functional programming
**Similar to AsyncApi but returns `IO[...]` instead of `Future[...]`**

### 2.3 ZioApi (`ZioApi.scala`)
**Purpose:** ZIO effect system integration
**Returns `ZIO[...]` types for ZIO-based applications**

---

## 3. Authorization (`authorization/`, 6 files)

Fine-grained access control system with role-based permissions at entity and attribute level.

### 3.1 Role-Based Access (`Authorization1_roles.scala`)
**Purpose:** Entity-level role permissions
**DSL patterns:**
```scala
// Connection with authentication
val conn = summon[Conn]
guestConn <- baseConn.withAuth("userId", "Guest")

// Public entities - no roles required
Article.title("Public").save.transact(using conn)

// Single role entities
UserProfile.displayName("Alice").save.transact(using adminConn)

// Multiple role entities (Member OR Moderator)
Post.content("Text").save.transact(using memberConn)

// Access denied
UserProfile.displayName.query.get(using guestConn)
  .recover { case ModelError(err) =>
    err ==> "Access denied: Role 'Guest' cannot query entity 'UserProfile'"
  }
```

### 3.2 Role Actions (`Authorization2_roleActions.scala`)
**Purpose:** Action-level permissions per role (query, save, insert, update, delete)
**Roles can have different permissions for different actions**

### 3.3 Attribute Roles (`Authorization3_attrRoles.scala`)
**Purpose:** Attribute-level access control
**DSL patterns:**
```scala
// Admin can see all fields
UserProfile.displayName.email.query.get(using adminConn)

// Member can only see public fields
UserProfile.displayName.query.get(using memberConn)

// Private field access denied
UserProfile.email.query.get(using memberConn)
  .recover { case ModelError(err) =>
    err ==> "Access denied: Role 'Member' cannot query attribute 'email'"
  }
```

### 3.4 Attribute Update (`Authorization4_attrUpdate.scala`)
**Purpose:** Attribute-level update permissions
**Different roles can update different attributes**

### 3.5 Overview & Raw Access
**Purpose:** Authorization system overview and raw access bypass

---

## 4. Bind (`bind/`, 27 files)

Query parameterization for prepared statement-like queries with runtime binding.

### 4.1 Core Binding (`Semantics.scala`, `Refs.scala`, `Nested.scala`)
**Purpose:** Input molecules with bind variables
**DSL patterns:**
```scala
// Create input molecule with placeholder
biggerThan = Entity.int.>(?).query

// Bind values at runtime
biggerThan(1).get                  // List(2, 3)
biggerThan(2).get                  // List(3)

// Multiple placeholders
range = Entity.int.>=(?).<=(?).query
range(2, 4).get                    // Values 2,3,4

// With pagination
offset1gt = Entity.int.>(?).query.offset(1)
offset1gt(0).get                   // Skip first result

// Cursor-based pagination
queryGt = Entity.int.>(?).a1.query
queryGt(1).from(cursor).limit(2).get
```

**Runtime type checking:**
```scala
eq = Entity.int(?).query
eq("1").get  // ModelError: "First bind value `1` is of type String but should be of type Int."
```

**Restrictions:**
- Save, insert, update, upsert do NOT support bind parameters
- Only queries support bind variables

### 4.2 String Operations (`StringOps.scala`)
**Purpose:** String manipulation operations with binding
**DSL patterns:**
```scala
Entity.string.startsWith(?).query
Entity.string.endsWith(?).query
Entity.string.contains(?).query
Entity.string.matches(?).query      // regex
```

### 4.3 Refs Binding (`Refs.scala`)
**Purpose:** Bind variables across entity references

### 4.4 Widening (`Widening.scala`)
**Purpose:** Type widening in bind operations

### 4.5 Type Bindings (`types/`, 23 files)
**All 23 Molecule types support binding:** Boolean, Int, Long, Float, Double, String, BigInt, BigDecimal, Date, Duration, Instant, LocalDate, LocalTime, LocalDateTime, OffsetTime, OffsetDateTime, ZonedDateTime, UUID, URI, Byte, Short, Char, Ref

**Key files:**
- `Bind_Int.scala` - integer binding
- `Bind_String_.scala` - string binding
- `Bind_Ref.scala` - reference binding

---

## 5. CRUD (`crud/`, 51 files)

Create, Read, Update, Delete operations.

### 5.1 Insert (`insert/`, 5 files)
**Purpose:** Bulk insert operations
**DSL patterns:**
```scala
// Card-one
Entity.int.insert(1, 2, 3).transact
Entity.int.string.insert((1, "a"), (2, "b")).transact
Entity.int.string.insert(List((1, "a"), (2, "b"))).transact

// Card-set
Entity.intSet.insert(Set(1, 2, 3)).transact

// Card-seq
Entity.intSeq.insert(Seq(1, 2, 3)).transact

// Card-map
Entity.intMap.insert(Map("a" -> 1, "b" -> 2)).transact

// With refs
Entity.i.Ref.i.insert((1, 11), (2, 22)).transact

// Get inserted ids
List(id1, id2) <- Entity.int.insert(1, 2).transact.map(_.ids)
```

**Key tests:**
- `InsertCardOne.scala` - single value attributes
- `InsertCardSet.scala` - set attributes
- `InsertCardSeq.scala` - sequence attributes
- `InsertCardMap.scala` - map attributes
- `InsertRefs.scala` - inserting with relationships
- `InsertSemantics.scala` - insert behavior and edge cases

### 5.2 Save (`save/`, 5 files)
**Purpose:** Save single entity (upsert by id if provided)
**DSL patterns:**
```scala
// New entity (no id)
id <- Entity.int(1).string("a").save.transact.map(_.id)

// Update existing (with id)
Entity(id).int(2).save.transact

// With refs
Entity.i(1).Ref.i(2).save.transact

// Card-set
Entity.intSet(Set(1, 2, 3)).save.transact

// Card-seq
Entity.intSeq(Seq(1, 2, 3)).save.transact

// Card-map
Entity.intMap(Map("a" -> 1)).save.transact
```

**Key tests:**
- `SaveCardOne.scala` - single value save
- `SaveCardSet.scala`, `SaveCardSeq.scala`, `SaveCardMap.scala` - collections
- `SaveRefs.scala` - save with relationships
- `SaveSemantics.scala` - save behavior

### 5.3 Update (`update/`, 40 files)
**Purpose:** Update existing entities by id or filter
**DSL patterns:**
```scala
// By id
Entity(id).int(2).update.transact

// By filter
Entity.int(1).query.update.transact

// Card-one operations
Entity(id).int(10).update.transact

// Card-set operations
Entity(id).intSet.add(1).update.transact
Entity(id).intSet.remove(1).update.transact
Entity(id).intSet.replace(Set(1, 2)).update.transact

// Card-seq operations
Entity(id).intSeq.add(1).update.transact
Entity(id).intSeq.remove(1).update.transact
Entity(id).intSeq.replace(Seq(1, 2)).update.transact

// Card-map operations
Entity(id).intMap.add("a" -> 1).update.transact
Entity(id).intMap.remove("a").update.transact
Entity(id).intMap.replace(Map("a" -> 1)).update.transact

// AttrOp - attribute operations
Entity(id).int(add(5)).update.transact        // increment
Entity(id).int(subtract(3)).update.transact   // decrement
Entity(id).int(multiply(2)).update.transact   // multiply
Entity(id).int(divide(2)).update.transact     // divide

// With filter
Entity.i(1).int(10).update.transact           // updates all entities where i == 1
```

**Restrictions:**
- Cannot update optional values: `Entity(id).int_?(Some(1)).update` - error
- Cannot upsert related data: `Entity(id).Ref.i(2).upsert` - error (use update instead)
- Backref not supported in update

**Update structure:**
- `Basics.scala` - fundamental update operations
- `ops/` - OpsOne, OpsSet, OpsSeq, OpsMap - collection operations
- `attrOp/` - AttrOp_Boolean, AttrOp_String, number/AttrOpInteger_*, decimal/AttrOpDecimal_* - attribute operations
- `filter/` - FilterOne, FilterSet, FilterSeq, FilterMap - filtered updates
- `relation/` - one/One_*, many/Many_* - relationship updates

### 5.4 Delete (`delete/`, 2 files)
**Purpose:** Delete entities
**DSL patterns:**
```scala
// By id
Entity(id).delete.transact

// By filter
Entity.int(1).query.delete.transact
```

**Key tests:**
- `Delete_id.scala` - delete by id
- `Delete_filter.scala` - delete by filter query

---

## 6. Filter (`filter/`, 100+ files)

Query filtering for all cardinality and type combinations.

### 6.1 Card-One Filters (`one/`, 30+ files)

#### 6.1.1 Basic Filtering (`types/FilterOne_*.scala`)
**DSL patterns:**
```scala
// Equality
Entity.int(1).query.get
Entity.int(1, 2).query.get              // OR semantics
Entity.int(Seq(1, 2)).query.get

// Negation
Entity.int.not(1).query.get
Entity.int.not(1, 2).query.get

// Comparison
Entity.int.<(10).query.get
Entity.int.<=(10).query.get
Entity.int.>(5).query.get
Entity.int.>=(5).query.get

// Empty check (only for optional attrs)
Entity.int_?.apply(None).query.get
```

#### 6.1.2 Special Filters

**String filters** (`FilterOneSpecial_String.scala`, `FilterOne_String_.scala`):
```scala
Entity.string.startsWith("a").query.get
Entity.string.endsWith("z").query.get
Entity.string.contains("mid").query.get
Entity.string.matches("a.*").query.get      // regex
```

**Enum filters** (`FilterOne_Enum.scala`):
```scala
Entity.enum("Red").query.get
Entity.enum("Red", "Blue").query.get
```

**ID filters** (`FilterOne_id.scala`):
```scala
Entity.id(someId).query.get
Entity.id(id1, id2).query.get
Entity.id.not(id1).query.get
Entity.id.<(id2).query.get
```

**Ref filters** (`types/FilterOne_ref.scala`):
```scala
Entity.ref(refId).query.get
```

**Decimal-specific** (`decimal/FilterOneDecimal_*.scala`):
- Additional precision handling for Float, Double, BigDecimal

#### 6.1.3 Types Covered
All 23 core types: Boolean, Int, Long, Float, Double, String, BigInt, BigDecimal, Date, Duration, Instant, LocalDate, LocalTime, LocalDateTime, OffsetTime, OffsetDateTime, ZonedDateTime, UUID, URI, Byte, Short, Char, ref

### 6.2 Card-Set Filters (`set/`, 30+ files)

**DSL patterns:**
```scala
// Has (contains)
Entity.intSet.has(1).query.get
Entity.intSet.has(1, 2).query.get           // has 1 OR 2

// Has no
Entity.intSet.hasNo(1).query.get
Entity.intSet.hasNo(1, 2).query.get         // has neither 1 nor 2

// Enums
Entity.enumSet.has("Red").query.get

// References
Entity.refs.has(refId).query.get
```

**Semantics** (`SetSemantics.scala`):
- Set equality and containment semantics
- Empty set handling

**Types covered:** All applicable types for sets
**Ref filters** (`ref/FilterRefSet_Card2Ref.scala`)

### 6.3 Card-Seq Filters (`seq/`, 30+ files)

**DSL patterns:**
```scala
// Has (contains)
Entity.intSeq.has(1).query.get
Entity.intSeq.has(1, 2).query.get           // has 1 OR 2

// Has no
Entity.intSeq.hasNo(1).query.get

// Enums
Entity.enumSeq.has("Red").query.get

// ByteArray special
Entity.byteArray.query.get
```

**Types covered:** All applicable types for sequences
**Special:** `FilterSeq_ByteArray.scala` - byte array handling
**Ref filters** (`ref/FilterRefSeq_Card1Ref.scala`, `ref/FilterRefSeq_Card2Ref.scala`)

### 6.4 Card-Map Filters (`map/`, 14 files)

**DSL patterns:**
```scala
// Has key
Entity.intMap.has("a").query.get
Entity.intMap.has("a", "b").query.get       // has key "a" OR "b"

// Has no key
Entity.intMap.hasNo("a").query.get

// No direct value filtering - use key filtering
```

**Semantics** (`MapSemantics.scala`):
- Map key equality and containment
- Empty map handling

**Types covered:** All map-compatible types (Int, Long, Float, Double, String, BigInt, BigDecimal, Date, Duration, Instant, LocalDate, LocalTime, LocalDateTime, OffsetTime, OffsetDateTime, ZonedDateTime, UUID, URI, Byte, Short, Char, Boolean)

---

## 7. FilterAttr (`filterAttr/`, 14 files)

Self-joins and attribute comparisons within and across entities.

### 7.1 Card-One FilterAttr (`one/`, 7 files)

**Purpose:** Compare attribute values within queries
**DSL patterns:**
```scala
// Self-comparison (same attribute used for data and filter)
Entity.int.int_(10).query.get              // int == 10

// Cross-attribute comparison
Entity.int(Entity.long_).query.get         // int == long

// Across refs
A.i(B.i_).B.i.query.get                    // A.i == B.i
A.i.B.i(A.i_).query.get                    // B.i == A.i (backref)

// Ref-Ref
A.i(B.i_).B.i(C.i_).C.i.query.get        // A.i == B.i == C.i

// Optional qualifying
A.s.i_(A.B.i_).B.i_.query.get             // explicit namespace path
A.s.i_(B.i_).B.i_.query.get               // omit qualifier if unambiguous
```

**Key tests:**
- `Types.scala` - type-based filterAttr
- `FilterAttrRef.scala` - filterAttr across refs
- `FilterAttrNested.scala` - filterAttr in nested data
- `FilterAttr_id.scala` - id-based filterAttr
- `Adjacent.scala` - adjacent attribute comparison
- `CrossNs.scala` - cross-namespace comparison
- `Sorting.scala` - sorting with filterAttr
- `Semantics.scala` - filterAttr semantics and edge cases

### 7.2 Card-Set FilterAttr (`set/`, 3 files)

**DSL patterns:**
```scala
Entity.intSet(Entity.int_).query.get       // intSet contains int
Entity.intSet.has(Entity.int_).query.get   // explicit has
```

**Key tests:**
- `Types.scala` - set filterAttr
- `Adjacent.scala` - adjacent set comparison
- `CrossNs.scala` - cross-namespace set comparison

### 7.3 Card-Seq FilterAttr (`seq/`, 3 files)

Similar to set filterAttr but for sequences.

**Key tests:**
- `Types.scala` - seq filterAttr
- `Adjacent.scala` - adjacent seq comparison
- `CrossNs.scala` - cross-namespace seq comparison

---

## 8. Pagination (`pagination/`, 26 files)

Offset and cursor-based pagination strategies.

### 8.1 Offset Pagination (`offset/`, 3 files)

**Purpose:** Traditional offset/limit pagination
**DSL patterns:**
```scala
// Forward pagination
Entity.int.a1.query.offset(0).limit(10).get.map { case (data, totalCount, hasMore) => ... }
Entity.int.a1.query.offset(10).limit(10).get

// Backwards pagination
Entity.int.d1.query.offset(0).limit(10).get
```

**Returns:** `(List[A], Int, Boolean)` - (data, totalCount, hasMore)

**Key tests:**
- `OffsetForward.scala` - forward pagination
- `OffsetBackwards.scala` - reverse pagination
- `OffsetSemantics.scala` - offset behavior and edge cases

### 8.2 Cursor Pagination (`cursor/`)

#### 8.2.1 Primary Unique (`primaryUnique/`, 5 files)

**Purpose:** Cursor pagination on unique attributes
**DSL patterns:**
```scala
val query = Entity.unique.a1.query

// Forward from start
c1 <- query.from("").limit(20).get.map { case (data, cursor, hasMore) => cursor }
c2 <- query.from(c1).limit(20).get.map { case (data, cursor, hasMore) => cursor }

// Backward from end
c1 <- query.from("").limit(-20).get
c2 <- query.from(c1).limit(-20).get

// Navigate both directions
c2 <- query.from(c3).limit(-20).get
```

**Returns:** `(List[A], String, Boolean)` - (data, cursor, hasMore)

**Key tests:**
- `Directions.scala` - forward/backward navigation
- `Nested.scala` - cursor with nested data
- `MutationAdd.scala`, `MutationDelete.scala` - cursor stability with mutations
- `TypesFilterAttr.scala` - cursor with filterAttr

#### 8.2.2 No Unique (`noUnique/`, 10 files)

**Purpose:** Cursor pagination without unique attributes (uses row position)
**Supports:** Mandatory and optional attributes

**Key tests:**
- `DirectionsMandatory.scala`, `DirectionsOptional.scala` - navigation
- `AttrOrderMandatory.scala`, `AttrOrderOptional.scala` - attribute ordering
- `TypesOptional.scala` - optional type handling
- `MutationAdd.scala`, `MutationDelete.scala` - mutation handling
- `Nested.scala`, `OptNested.scala` - nested data

#### 8.2.3 Sub-Unique (`subUnique/`, 7 files)

**Purpose:** Cursor pagination with combination of unique + standard attributes
**DSL patterns:**
```scala
// Unique attribute first, then standard
query = Entity.unique.a1.standard.a2.query
```

**Key tests:**
- `DirectionsUniqueStandard.scala` - unique attr first
- `DirectionsStandardUnique.scala` - standard attr first
- `AttrOrder.scala` - attribute order combinations
- `TypesUniqueValue.scala` - type handling
- `MutationAdd.scala`, `MutationDelete.scala` - mutation stability
- `Nested.scala`, `OptNested.scala` - nested handling

#### 8.2.4 Shared (`SharedSemantics.scala`)
**Common cursor pagination semantics**

---

## 9. Relationship (`relationship/`, 15 files)

Entity relationships: flat refs, nested data, bidirectional, many-to-many.

### 9.1 Flat References (`flat/`, 7 files)

**Purpose:** Traditional foreign key style relationships
**DSL patterns:**
```scala
// Card-one ref
A.i.B.i.query.get                          // (Int, Int)
A.i.B.i.C.i.query.get                      // (Int, Int, Int)

// Optional ref (left join)
A.i.B.?(B.i).query.get                     // (Int, Option[Int])
A.i.B.?(B.i.s).query.get                   // (Int, Option[(Int, String)])

// Optional refs - adjacent
A.i.B.?(B.i).C.?(C.i).query.get

// Optional refs - nested
A.i.B.?(B.i.C.?(C.i)).query.get

// Entity reference
A.s.B(b).i.query.get                       // filter by entity id
```

**Key tests:**
- `FlatRef.scala` - basic ref traversal
- `FlatEntity.scala` - entity references
- `FlatOptRef.scala` - optional refs
- `FlatOptRefAdjacent.scala` - adjacent optional refs
- `FlatOptRefNested.scala` - nested optional refs

### 9.2 Nested Data (`nested/`, 7 files)

**Purpose:** Nested data structures (like JSON)
**DSL patterns:**
```scala
// Mandatory nested
A.s.Bb.*(B.i).insert(
  ("a", List(1, 2)),
  ("b", List(3))
).transact
A.s.Bb.*(B.i.a1).query.get                 // ("a", List(1, 2)), ("b", List(3))

// Optional nested
A.s.a1.Bb.*?(B.i.a1).query.get             // ("a", List(1, 2)), ("b", Nil)

// Nested expressions
A.i_.Bb.*(B.i.<(5).a1).query.get          // filter within nested

// Multi-level nested
A.s.Bb.*(B.i.Cc.*(C.i)).query.get         // 2 levels
```

**Key tests:**
- `NestedBasic.scala` - basic nested operations
- `NestedTypes.scala` - nested with all types
- `NestedLevels.scala` - multi-level nesting
- `NestedOptional.scala` - optional nested data
- `NestedRef.scala` - nested with refs
- `NestedSemantics.scala` - nested behavior

### 9.3 Special Relationships

**Unlimited Arity**:
- Scala 3 tuples support unlimited arity (no 22-limit from Scala 2.13)
- Some files hard-code 1-22 cases for performance with `n` case for unlimited
- Bindings have max 22 parameter limitation

**Bidirectional** (`Bidirectional.scala`):
- Two-way relationships
- Backref syntax: `A.i.B.i._A.C.i`

**ManyToMany** (`ManyToMany.scala`):
- Many-to-many relationship patterns
- Uses Join trait for join tables

**Join Trait** (for many-to-many):
```scala
trait J extends Join {
  val a = manyToOne[A]
  val b = manyToOne[B]
  // Optional join table properties
  val i = oneInt
}
```

**Explicit navigation (via join table)**:
```scala
A.i.Js.i.a1.B.i.query                      // Access join property i
A.i.Js.B.i.a1.query                        // Skip join property
A.i.a1.Js.*(J.i.B.i.a1).query              // Nested via join
```

**Implicit bridging (skip join table)**:
```scala
A.i.Bs.i.a1.query                          // Direct to B (flat)
A.i.Bs.**(B.i.a1).query                    // Direct to B (nested)
```

- Each Join entity must have at least two `manyToOne` references
- Can include additional attributes
- `Js.*` - Explicit navigation through join table (can access join properties)
- `Bs.**` - Implicit bridge directly to target (cleaner for simple many-to-many)
- See: `/Users/mg/molecule/molecule/molecule/db/compliance/shared/src/test/scala/molecule/db/compliance/test/relationship/ManyToMany.scala`

---

## 10. Segments (`segments/`, 1 file)

### Prefixed Segments (`Prefixed.scala`)

**Purpose:** Namespace prefixes for multi-tenant/partitioned data
**DSL patterns:**
```scala
// Define segment
Entity("segment1").attr.query.get

// Multiple segments
Entity("segment1").attr.query.get
Entity("segment2").attr.query.get
```

---

## 11. Sorting (`sorting/`, 4 files)

### 11.1 Basic Sorting (`SortBasics.scala`)

**DSL patterns:**
```scala
// Ascending (a1, a2, a3...)
Entity.int.a1.query.get                    // sort by int ascending
Entity.int.a1.string.a2.query.get          // primary sort by int, secondary by string

// Descending (d1, d2, d3...)
Entity.int.d1.query.get                    // sort by int descending
Entity.int.d1.string.d2.query.get          // primary desc, secondary desc

// Mixed
Entity.int.a1.string.d2.query.get          // int asc, string desc

// Optional attr sorting
Entity.int_?.a1.query.get                  // None values first/last (db-dependent)
```

**Supports:** All 25+ types
**Boolean sort:** false before true (ascending)

### 11.2 Aggregate Sorting (`SortAggr.scala`)

**DSL patterns:**
```scala
Entity.i.int(sum).a1.query.get             // sort by sum
Entity.i.int(avg).d1.query.get             // sort by average desc
Entity.i.int(count).a1.int(sum).a2.query   // multi-aggregate sort
```

### 11.3 Nested Sorting (`SortNested.scala`)

**DSL patterns:**
```scala
A.i.a1.Bb.*(B.i.a1).query.get             // sort outer by A.i, inner by B.i
A.i.d1.Bb.*(B.i.d1).query.get             // descending on both
```

### 11.4 Dynamic Sorting (`SortDynamic.scala`)

**Purpose:** Runtime sort specification
**DSL patterns:**
```scala
Entity.int.string.query.sort("int").get
Entity.int.string.query.sort("-int").get   // descending
Entity.int.string.query.sort("int", "-string").get
```

---

## 12. Subscription (`subscription/`, 1 file)

### Real-time Subscriptions (`Subscription.scala`)

**Purpose:** Live query subscriptions (JS platform)
**DSL patterns:**
```scala
// Subscribe to query results
val unsubscribe = Entity.int.query.subscribe { result =>
  // Callback on data changes
}

// Unsubscribe
unsubscribe()
```

**Note:** Streaming preferred on JVM, subscription on JS

---

## 13. Types (`types/`, 9 files)

Type system and cardinality tests.

### 13.1 Card-One Types (`One_*.scala`)

**Categories:**
- **One_Boolean.scala** - Boolean type
- **One_Integer.scala** - Int, Long, Byte, Short, BigInt
- **One_Decimal.scala** - Float, Double, BigDecimal
- **One_String.scala** - String type
- **One_Enum.scala** - Enumeration types
- **One_.scala** - Date, Duration, Instant, LocalDate, LocalTime, LocalDateTime, OffsetTime, OffsetDateTime, ZonedDateTime, UUID, URI, Char

**Type return patterns:**
```scala
// Single attribute
val x: Int = Entity.int.query.get.head
val x: String = Entity.string.query.get.head

// With aggregates
val x: Int = Entity.int(count).query.get.head
val x: Double = Entity.int(avg).query.get.head
val x: Set[Int] = Entity.int(distinct).query.get.head

// Optional
val x: Option[Int] = Entity.int_?.query.get.head

// Multiple attributes
val x: (Int, String) = Entity.int.string.query.get.head

// Tacit
val x: String = Entity.string.int_.query.get.head
```

### 13.2 Card-Set Types (`Set_.scala`)

**DSL patterns:**
```scala
// Set attributes
val x: Set[Int] = Entity.intSet.query.get.head
val x: Set[String] = Entity.stringSet.query.get.head

// With other attrs
val x: (Int, Set[String]) = Entity.int.stringSet.query.get.head
```

### 13.3 Card-Seq Types (`Seq_.scala`)

**DSL patterns:**
```scala
// Seq attributes
val x: Seq[Int] = Entity.intSeq.query.get.head
val x: Seq[String] = Entity.stringSeq.query.get.head

// Special: ByteArray
val x: Array[Byte] = Entity.byteArray.query.get.head
```

### 13.4 Card-Map Types (`Map_.scala`)

**DSL patterns:**
```scala
// Map attributes
val x: Map[String, Int] = Entity.intMap.query.get.head
val x: Map[String, String] = Entity.stringMap.query.get.head
```

---

## 14. Validation (`validation/`, 33 files)

Data validation at transaction time.

### 14.1 Mandatory Attributes (`MandatoryAttrs.scala`, `MandatoryRefs.scala`)

**Purpose:** Ensure required attributes are present
**DSL patterns:**
```scala
// Error on missing mandatory attr
MandatoryAttr.age(42).save.transact
  .recover { case ModelError(error) =>
    error ==> "Missing/empty mandatory attributes: MandatoryAttr.name, MandatoryAttr.hobbies"
  }

// Empty Set/Seq counts as missing
MandatoryAttr.name("Bob").hobbies(Set.empty).save.transact
  .recover { case ModelError(error) => ... }

// Success with all mandatory attrs
MandatoryAttr.name("Bob").age(42).hobbies(Set("golf")).save.transact
```

### 14.2 Required Attributes (`RequiredAttrs.scala`)

**Purpose:** Require specific attributes in queries
**Similar to mandatory but for query validation**

### 14.3 Enums (`Enums.scala`)

**Purpose:** Validate enum values
**DSL patterns:**
```scala
// Valid enum
Entity.color("Red").save.transact           // OK if "Red" in enum

// Invalid enum
Entity.color("Purple").save.transact
  .recover { case ValidationErrors(errorMap) =>
    errorMap.head._2.head.contains("not a valid enum value")
  }
```

### 14.4 Format Validation (`FormatConstants.scala`, `FormatVariables.scala`)

**Purpose:** String format validation (regex, email, etc.)
**DSL patterns:**
```scala
// Validation rule in domain model
@Field(validate = "(_ matches \"[a-z]+\") or error(\"Only lowercase\")")
val string: String = ???

// Validation error
Tpe.string("ABC").save.transact
  .recover { case ValidationErrors(errorMap) =>
    errorMap.head._2.head.contains("Only lowercase")
  }

// Constants - validation rules hardcoded
// Variables - validation rules with parameters
```

### 14.5 String Validation Functions (`StringValidationFns.scala`)

**Purpose:** Built-in string validators
**Functions:** email, url, regex patterns, length checks

### 14.6 Keyword Substitution (`KeywordSubstitution.scala`)

**Purpose:** Domain-specific validation rule keywords

### 14.7 Allowed Values (`AllowedValues.scala`)

**Purpose:** Restrict values to allowed set
**DSL patterns:**
```scala
@Field(allowedValues = Set("A", "B", "C"))
val code: String = ???

Entity.code("D").save.transact
  .recover { case ValidationErrors(errorMap) => ... }
```

### 14.8 Aliased Validation (`Aliased.scala`)

**Purpose:** Validation with aliased attributes

### 14.9 Type-Specific Validation

**Insert:** `insert/Types*.scala` (9 files)
- TypesOne, TypesOneOpt, TypesSeq, TypesSeqOpt, TypesSet, TypesSetOpt
- FormatConstants, FormatVariables, Semantics, Nested

**Save:** `save/Types*.scala` (9 files)
- Same structure as insert

**Update:** `update/Types*.scala` (6 files)
- TypesOne, TypesOneOpt, TypesSeq, TypesSeqOpt, TypesSet, TypesSetOpt

---

## Feature Matrix

| Category | Subcategories | Files | Key DSL Patterns | Notes |
|----------|---------------|-------|------------------|-------|
| **Aggregation** | any, number, ref, refNum, relations | 43 | `attr(count)`, `attr(sum)`, `attr(avg)`, `attr(median)`, `attr(variance)`, `attr(stddev)`, `attr(distinct)`, `attr(min)`, `attr(max)`, `attr(sample)` | Statistical functions, some db-specific |
| **API** | async, io, zio | 3 | `.transact`, `.query.get`, `.stream`, `B.?(...)` | Multiple execution models |
| **Authorization** | roles, actions, attributes | 6 | `withAuth(userId, role)`, entity/attr-level ACL | Fine-grained access control |
| **Bind** | semantics, refs, nested, stringOps, types | 27 | `attr(?)`, `query(value)`, runtime param binding | Prepared statement-like queries |
| **CRUD** | insert, save, update, delete | 51 | `.insert`, `.save`, `.update`, `.delete`, `.transact` | Full CRUD with collections |
| **Filter** | one, set, seq, map, decimal, ref | 100+ | `attr(value)`, `.not()`, `.<()`, `.>()`, `has()`, `startsWith()` | All types & cardinalities |
| **FilterAttr** | one, set, seq | 14 | `attr(OtherAttr.attr_)`, self-joins | Cross-attribute comparison |
| **Pagination** | offset, cursor (primary/no/sub-unique) | 26 | `.offset()`, `.limit()`, `.from(cursor)` | Offset & cursor strategies |
| **Relationship** | flat, nested, bidirectional, many-to-many | 15 | `A.B.C`, `A.Bb.*(B.i)`, `B.?(...)`, `._A` | Multiple relationship patterns |
| **Segments** | prefixed | 1 | `Entity("segment")` | Multi-tenant partitioning |
| **Sorting** | basics, aggr, nested, dynamic | 4 | `.a1`, `.d1`, `.sort("attr")` | Compile-time & runtime sort |
| **Subscription** | real-time | 1 | `.subscribe(callback)` | Live queries (JS platform) |
| **Types** | Boolean, Integer, Decimal, String, Enum, temporal, UUID, URI, etc. | 9 | All 23 Molecule types | Type system tests |
| **Validation** | mandatory, required, enums, format, allowed values | 33 | `@Field(validate = ...)`, error handling | Transaction-time validation |

---

## Special Operators Summary

### Comparison Operators
- `attr(value)` or `attr(v1, v2)` - equals (OR semantics for multiple)
- `attr(Seq(values))` - equals any in sequence
- `attr.not(value)` - not equals
- `attr.<(value)` - less than
- `attr.<=(value)` - less than or equal
- `attr.>(value)` - greater than
- `attr.>=(value)` - greater than or equal

### String Operators
- `attr.startsWith(prefix)`
- `attr.endsWith(suffix)`
- `attr.contains(substring)`
- `attr.matches(regex)`

### Collection Operators (Set/Seq)
- `attr.has(value)` - contains
- `attr.hasNo(value)` - does not contain

### Map Operators
- `attr.has(key)` - has key
- `attr.hasNo(key)` - does not have key

### Update Operators
- `attr(add(n))` - increment
- `attr(subtract(n))` - decrement
- `attr(multiply(n))` - multiply
- `attr(divide(n))` - divide
- `attr.add(value)` - add to collection
- `attr.remove(value)` - remove from collection
- `attr.replace(values)` - replace collection

### Aggregate Operators
- `count`, `countDistinct` - counting
- `sum`, `avg`, `median` - central tendency
- `variance`, `stddev` - dispersion
- `min`, `max`, `min(n)`, `max(n)` - extremes
- `distinct` - unique values
- `sample`, `sample(n)` - random sampling

### Sort Markers
- `.a1`, `.a2`, `.a3` - ascending sort priority
- `.d1`, `.d2`, `.d3` - descending sort priority

### Optional Markers
- `attr` - mandatory attribute (returned in results)
- `attr_?` - optional attribute (returns `Option[T]`)
- `attr_` - tacit attribute (filter only, not returned in results)

---

## Data Types (25+ total)

Scala 3 tuples support **unlimited arity** (no 22-limit from Scala 2.13).
**Note**: Bindings have a max of 22 parameters. See `/Users/mg/molecule/molecule/molecule/db/compliance/shared/src/test/scala/molecule/db/compliance/test/bind`

### Numeric
- **Integer:** Int, Long, Byte, Short, BigInt
- **Decimal:** Float, Double, BigDecimal

### Text
- **String:** String, Char
- **Enum:** Enumeration types

### Temporal
- **Legacy:** Date
- **Duration:** Duration
- **Instant:** Instant
- **Local:** LocalDate, LocalTime, LocalDateTime
- **Offset:** OffsetTime, OffsetDateTime, ZonedDateTime

### Other
- **Identifiers:** UUID, URI
- **Reference:** ref (entity reference)
- **Binary:** ByteArray (Seq only)
- **Logical:** Boolean

---

## Cardinalities

- **Card-one:** Single value - `attr: T`
- **Card-set:** Set of values - `attrSet: Set[T]`
- **Card-seq:** Sequence of values - `attrSeq: Seq[T]`
- **Card-map:** Key-value pairs - `attrMap: Map[String, T]`

---

## Test Infrastructure

### Domain Models
- **Types** - Entity with all 25+ types
- **Refs** - A, B, C entities for relationship testing
- **JoinTable** - A, B, J (where J extends Join) for many-to-many testing
- **Uniques** - Unique value testing
- **Validation** - Domain model with validation rules
- **SocialApp** - Authorization testing domain

### Databases Tested
- H2 (in-memory)
- MariaDB
- MySQL
- PostgreSQL
- SQLite

### API Styles
- Async (Future-based)
- IO (Cats Effect)
- ZIO
- Sync (blocking, mainly for type tests)

---

## Key Files for Understanding Features

### Aggregation
- `aggregation/any/Aggr_Int.scala` - comprehensive aggregation examples
- `aggregation/number/AggrNum_Int.scala` - numeric aggregates with ops
- `aggregation/AggrRelations.scala` - aggregation across relationships

### CRUD
- `crud/update/Basics.scala` - update fundamentals
- `crud/insert/InsertCardOne.scala` - insert basics
- `crud/update/ops/OpsSet.scala` - collection operations

### Filter
- `filter/one/FilterOne_Int.scala` - basic filtering patterns
- `filter/one/FilterOneSpecial_String.scala` - string operations
- `filter/set/SetSemantics.scala` - set filtering semantics

### Relationships
- `relationship/flat/FlatRef.scala` - basic ref traversal
- `relationship/nested/NestedBasic.scala` - nested data structures
- `relationship/Bidirectional.scala` - bidirectional refs

### Validation
- `validation/MandatoryAttrs.scala` - required field validation
- `validation/insert/FormatConstants.scala` - format validation
- `validation/Enums.scala` - enum validation

### Authorization
- `authorization/Authorization1_roles.scala` - role-based access
- `authorization/Authorization3_attrRoles.scala` - attribute-level ACL

### Pagination
- `pagination/cursor/primaryUnique/Directions.scala` - cursor pagination
- `pagination/offset/OffsetForward.scala` - offset pagination

---

## Notes

- **Platform differences:** Streaming (JVM), Subscription (JS)
- **Database limitations:** Some aggregates not available on all databases (median, variance, stddev on mariadb/mysql/sqlite)
- **Arity:** Scala 3 tuples have unlimited arity (no 22-limit). Some files hard-code 1-22 cases for performance with `n` case for unlimited.
- **Binding limitation:** Bindings DO have max 22 limitation. See `/Users/mg/molecule/molecule/molecule/db/compliance/shared/src/test/scala/molecule/db/compliance/test/bind`
- **Optional refs:** Use `B.?(...)` for left join semantics
- **Optional attributes:** Use `attr_?` (Scala 3 notation, not `attr$` from Scala 2)
- **Tacit attributes:** Use `attr_` to filter without returning value
- **Bind restrictions:** Only queries support bind parameters, not mutations
- **Update restrictions:** Cannot update optional values, cannot upsert related data

---

## Document Version
Generated: 2026-01-04
Test suite location: `/molecule/db/compliance/shared/src/test/scala/molecule/db/compliance/test/`
Total files analyzed: 346
