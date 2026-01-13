# Molecule DSL Boilerplate Generation

## Overview

Molecule uses `sbt-molecule` to automatically generate a type-safe, composable DSL from schema definitions. This generated boilerplate code transforms declarative data models into fully-functional Scala APIs with compile-time type safety, expression operations, and relationship navigation.

The code generator creates:
- **Entity entry points** (`Entity.scala`, `Ref.scala`, etc.) with ID filtering
- **Attribute accessors** in `ops/` subdirectory with three cardinality variants (mandatory, optional, tacit)
- **Expression classes** that provide filtering operators and aggregations
- **Relationship navigation** objects for traversing entity graphs
- **Metadata representations** in `metadb/` for runtime schema introspection

**Generated code location:**
`db/compliance/jvm/target/scala-3.7.4/src_managed/main/moleculeGen/molecule/db/compliance/domains/dsl/`

---

## 1. Entity DSL Structure

### 1.1 File Organization

Each entity domain generates:
```
Types/
├── Entity.scala              # Entry point object + trait
├── Ref.scala                 # Referenced entity
├── Other.scala               # Another entity
├── Color.scala               # Enum definitions
├── ops/
│   ├── Entity_.scala         # ~5500 lines of attribute accessors
│   ├── Ref_.scala            # Relationship ops
│   └── Other_.scala
└── metadb/
    ├── Types_.scala          # Base metadata
    ├── Types_h2.scala        # DB-specific metadata
    ├── Types_postgresql.scala
    └── ...
```

### 1.2 Entity Entry Point Pattern

Each entity generates an **object** and a **trait** with the same name:

```scala
// Entity.scala
object Entity extends ops.Entity_0(_DataModel(Nil, firstEntityIndex = 0)) with Entity {
  // ID-based filtering constructors
  final def apply(id: Long, ids: Long*) = new ops.Entity_0(
    _DataModel(List(_AttrOneTacID("Entity", "id", _Eq, id +: ids, coord = List(0, 0))),
               firstEntityIndex = 0)
  )

  final def apply(ids: Iterable[Long]) = new ops.Entity_0(
    _DataModel(List(_AttrOneTacID("Entity", "id", _Eq, ids.toSeq, coord = List(0, 0))),
               firstEntityIndex = 0)
  )
}

trait Entity {
  // Optional entity syntax
  final def ?              (optEntity: Molecule_0     ) = new ops.Entity_refs_0(...)
  final def ?[T           ](optEntity: Molecule_1[T  ]) = new ops.Entity_refs_1[Option[T  ]](...)
  final def ?[Tpl <: Tuple](optEntity: Molecule_n[Tpl]) = new ops.Entity_refs_1[Option[Tpl]](...)
}
```

**Key insight:** The object extends the `_0` ops class, inheriting all attribute accessors. The trait provides optional entity syntax (`Entity.?(...)`) for conditional inclusion.

---

## 2. Attribute Accessors

Attributes are generated in the `ops/EntityName_.scala` file with three primary classes based on molecule arity:

- **`Entity_0`**: Starting point (zero attributes selected)
- **`Entity_1[T]`**: Single return value of type `T`
- **`Entity_n[Tpl <: Tuple]`**: Multiple return values as tuple

### 2.1 Cardinality Variants

Each attribute generates **three accessor variants** based on cardinality modifiers:

#### Mandatory (no suffix)
```scala
// In Entity_0 class
final lazy val s = Entity_1_ExprOneMan_String[String](
  dataModel.add(s_man)
)
```
- **Returns value directly** (e.g., `String`)
- Used in queries: `Entity.s("foo")`
- Type: Transitions from `Entity_0` → `Entity_1[String]`

#### Optional (suffix `_?`)
```scala
final lazy val s_? = Entity_1_ExprOneOpt[String](
  dataModel.add(s_opt)
)
```
- **Returns `Option[T]`** (e.g., `Option[String]`)
- Used in queries: `Entity.s_?.query.get` returns `List[(Option[String])]`
- Allows null/missing values in result set
- **Note**: This is Scala 3 notation. Old Scala 2 notation was `attr$`.

#### Tacit (suffix `_`)
```scala
final lazy val s_ = Entity_0_ExprOneTac_String[String](
  dataModel.add(s_tac)
)
```
- **Does not affect return type** (stays `Entity_0`)
- Used for filtering only: `Entity.s_("foo").i.query.get` returns `List[Int]`
- Expression is evaluated but value not returned

### 2.2 Collection Cardinalities

#### Card-One (Single Value)
```scala
final lazy val i = Entity_1_ExprOneMan_Integer[Int](dataModel.add(i_man))
// Usage: Entity.i(42).query.get → List[Int]
```

#### Card-Set (Unordered Set)
```scala
final lazy val iSet = Entity_1_ExprSetMan[Int](dataModel.add(iSet_man))
// Usage: Entity.iSet.query.get → List[Set[Int]]
```

#### Card-Seq (Ordered Sequence)
```scala
final lazy val iSeq = Entity_1_ExprSeqMan[Int](dataModel.add(iSeq_man))
// Usage: Entity.iSeq.query.get → List[Seq[Int]]
```

#### Card-Map (Key-Value Map)
```scala
final lazy val iMap = Entity_1_ExprMapMan[Int](dataModel.add(iMap_man))
// Usage: Entity.iMap.query.get → List[Map[String, Int]]
// Keys are always String, values are typed
```

#### ByteArray (Special Case)
```scala
final lazy val byteArray = Entity_1_ExprBArMan[Byte](dataModel.add(byteArray_man))
// Usage: Entity.byteArray.query.get → List[Array[Byte]]
```

### 2.3 Type Progression

As attributes are added, the molecule type evolves:

```scala
Entity                         // Entity_0 (Molecule_0)
  .s("foo")                    // Entity_1[String]
  .i(42)                       // Entity_n[(String, Int)] = Entity_n[(String, Int)]
  .bool(true)                  // Entity_n[(String, Int, Boolean)]
```

Type parameters accumulate using Scala 3's tuple concatenation (`:*`):
```scala
class Entity_n[Tpl <: Tuple] {
  final lazy val i = Entity_n_ExprOneMan_Integer[Int, Tpl :* Int](...)
  // Tpl :* Int appends Int to existing tuple type
}
```

**Note**: Scala 3 tuples support **unlimited arity** (no 22-limit from Scala 2.13). Some files hard-code 1-22 cases for performance, with `n` case for unlimited. Example: `/Users/mg/molecule/molecule/molecule/db/common/shared/src/main/scala/molecule/db/common/query/casting/CastTpl_.scala`

---

## 3. Expression Operations (ops/)

Expression classes are **specialized by type** to provide type-appropriate operators.

### 3.1 Expression Class Hierarchy

```scala
class Entity_1_ExprOneMan_String[T](override val dataModel: DataModel)
  extends Entity_1[T](dataModel)
    with Entity_Sort_1[T]
    with ExprOneMan_1_String[T, [t] =>> Entity_1_Sort[t]](...)
    with ExprOneMan_1_String_Aggr[T, [t] =>> Entity_1_ExprOneMan_String_AggrOps[t]](...)
```

**Key traits:**
- **`ExprOneMan_1_String`**: String-specific operators (contains, matches, startsWith, etc.)
- **`ExprOneMan_1_String_Aggr`**: Aggregation functions (count, countDistinct, max, min, etc.)
- **`Entity_Sort_1[T]`**: Sorting capabilities

### 3.2 String Operations

From `ExprOneMan_1_String`:
```scala
// Equality
Entity.s("foo")                  // exact match
Entity.s.not("bar")              // negation
Entity.s.has("substring")        // contains

// Pattern matching
Entity.s.startsWith("pre")       // prefix matching
Entity.s.endsWith("fix")         // suffix matching
Entity.s.matches(".*regex.*")    // regex

// Comparison
Entity.s.<("m")                  // less than
Entity.s.>("m")                  // greater than
Entity.s.<=("m")                 // less than or equal
Entity.s.>=("m")                 // greater than or equal
```

### 3.3 Integer Operations

From `ExprOneMan_1_Integer`:
```scala
// Comparison
Entity.i(42)                     // equals
Entity.i.<(100)                  // less than
Entity.i.>(10)                   // greater than
Entity.i.>=(10).<=(100)          // range

// Multiple values
Entity.i(1, 2, 3)                // IN clause

// Negation
Entity.i.not(42)                 // not equals
Entity.i.not(1, 2, 3)            // NOT IN clause
```

### 3.4 Decimal Operations

From `ExprOneMan_1_Decimal` (Float, Double, BigDecimal):
```scala
Entity.float(3.14f)
Entity.float.<(10.0f)
Entity.float.>=(0.0f).<=(100.0f)
```

### 3.5 Boolean Operations

From `ExprOneMan_1_Boolean`:
```scala
Entity.boolean(true)
Entity.boolean(false)
Entity.boolean.not(true)         // Uncommon but supported
```

### 3.6 Collection Operations

#### Set Operations
From `ExprSetMan`:
```scala
// Has values
Entity.iSet(1, 2, 3)             // contains any
Entity.iSet.has(1)               // contains specific
Entity.iSet.hasNo(99)            // doesn't contain

// Comparison
Entity.stringSet.>("abc")        // all elements > "abc"
```

#### Seq Operations
From `ExprSeqMan`:
```scala
Entity.iSeq.has(1)               // contains value
Entity.iSeq.hasNo(99)            // doesn't contain value
```

#### Map Operations
From `ExprMapMan`:
```scala
Entity.iMap.has("key" -> 42)     // contains key-value pair
Entity.iMap.hasNo("key")         // doesn't contain key
Entity.iMap("key" -> 42)         // exact match
```

### 3.7 Aggregations

String aggregations (`ExprOneMan_1_String_Aggr`):
```scala
Entity.s(count)                  // COUNT(*)
Entity.s(countDistinct)          // COUNT(DISTINCT s)
Entity.s(max)                    // MAX(s)
Entity.s(min)                    // MIN(s)
Entity.s(sample)                 // arbitrary value
```

Integer aggregations (`ExprOneMan_1_Integer_Aggr`):
```scala
Entity.i(sum)                    // SUM(i)
Entity.i(avg)                    // AVG(i)
Entity.i(median)                 // MEDIAN(i)
Entity.i(variance)               // VARIANCE(i)
Entity.i(stddev)                 // STDDEV(i)
```

---

## 4. Relationship Navigation

Relationships generate **nested objects** in the `_refs_0` classes.

### 4.1 Many-to-One Relationships

```scala
// In Entity_refs_0 class
object Ref extends Ref_0(
  dataModel.add(_dm.Ref("Entity", "ref", "Ref", ManyToOne,
                        List(0, 32, 1), Some("Entities")))
) with OptRefInit

object Other extends Other_0(
  dataModel.add(_dm.Ref("Entity", "other", "Other", ManyToOne,
                        List(0, 33, 2), Some("Entities")))
) with OptRefInit
```

**Usage:**
```scala
// Forward traversal
Entity.s("foo").Ref.i(42).query.get          // Join to Ref entity

// Access Ref's ID directly
Entity.ref(123).query.get                    // Filter by Ref ID

// Optional traversal
Entity.s("foo").Ref.?(Ref.i(42)).query.get   // LEFT JOIN
```

### 4.2 One-to-Many Relationships

```scala
// In Entity_refs_0 class
object Refs extends Ref_0(
  dataModel.add(_dm.Ref("Entity", "Refs", "Ref", OneToMany,
                        List(0, 100, 1), Some("entity")))
) with NestedInit
```

**Usage with nested syntax:**
```scala
// Nested pull
Entity.s.Refs.*(Ref.i).query.get
// Returns: List[(String, Seq[Int])]

// Optional nested
Entity.s.Refs.*?(Ref.i).query.get
// Returns: List[(String, Seq[Int])] with empty Seq if no refs
```

### 4.3 Back References

```scala
// In Entity_refs_0 class
object _Ref extends Ref_0(
  dataModel.add(_dm.BackRef("Ref", "Entity", List(1, 0)))
)
```

**Usage:**
```scala
// Navigate backwards
Ref.i(42)._Entity.s("foo").query.get    // From Ref back to Entity
```

### 4.4 Nested vs Optional References

#### `NestedInit` Trait
Provides `*` and `*?` operators for one-to-many:
```scala
trait NestedInit extends OptRefInit { self: Molecule =>
  def * [NestedT](nested: Molecule_1[NestedT]) =
    new Entity_1[Seq[NestedT]](addNested(self, nested))

  def *?[NestedT](nested: Molecule_1[NestedT]) =
    new Entity_1[Seq[NestedT]](addOptNested(self, nested))
}
```

#### `OptRefInit` Trait
Provides `?` operator for optional traversal:
```scala
trait OptRefInit { self: Molecule =>
  def ?[OptRefT](optRef: Molecule_1[OptRefT]) =
    new Entity_1[Option[OptRefT]](addOptRef(self, optRef))
}
```

---

## 5. Type Safety Mechanism

### 5.1 Compile-Time Type Tracking

Type safety is achieved through **phantom types** that track molecule state at compile-time:

```scala
// Entity_0: No attributes selected
class Entity_0(override val dataModel: DataModel) extends Entity_refs_0(dataModel) {
  // Selecting first attribute transitions to Entity_1[T]
  final lazy val s = Entity_1_ExprOneMan_String[String](...)
}

// Entity_1[T]: Single attribute selected
class Entity_1[T](override val dataModel: DataModel) extends Entity_refs_1[T](dataModel) {
  // Selecting second attribute transitions to Entity_n[(T, U)]
  final lazy val i = Entity_n_ExprOneMan_Integer[Int, (T, Int)](...)
}

// Entity_n[Tpl]: Multiple attributes (tuple)
class Entity_n[Tpl <: Tuple](override val dataModel: DataModel)
    extends Entity_refs_n[Tpl](dataModel) {
  // Adding more attributes appends to tuple type
  final lazy val bool = Entity_n_ExprOneMan_Boolean[Boolean, Tpl :* Boolean](...)
}
```

### 5.2 DataModel Accumulation

Each attribute access **adds to the internal DataModel**:

```scala
final lazy val s = Entity_1_ExprOneMan_String[String](
  dataModel.add(s_man)  // ← Appends attribute element
)

// s_man is defined in Entity_attrs trait:
protected def s_man = AttrOneManString("Entity", "s", coord = List(0, 5))
```

The `dataModel.add()` call returns a **new DataModel** with the element appended. This immutable accumulation pattern enables method chaining.

### 5.3 Coordinated Attribute Definitions

Each attribute has a **coordinate** that tracks its position:

```scala
protected def s_man = AttrOneManString("Entity", "s", coord = List(0, 5))
//                                                              ↑     ↑
//                                                         entity  attr
//                                                         index   index

protected def ref_man = AttrOneManID("Entity", "ref", coord = List(0, 32, 1),
                                     ref = Some("Ref"))
//                                                      ↑     ↑   ↑
//                                                  entity attr refEntity
```

Coordinates are used for:
- SQL query generation (column ordering)
- Result set deserialization (tuple construction)
- Relationship resolution

### 5.4 Expression Class Chaining

Expression classes maintain the type chain while providing operations:

```scala
class Entity_1_ExprOneMan_String[T](override val dataModel: DataModel)
  extends Entity_1[T](dataModel)  // ← Maintains molecule type
    with ExprOneMan_1_String[T, [t] =>> Entity_1_Sort[t]](
      [t] => (dm: DataModel) => new Entity_1_Sort[t](dm)
    )
```

The type lambda `[t] =>> Entity_1_Sort[t]` enables **higher-kinded type construction** for operation results.

---

## 6. Metadata Representation (metadb/)

The `metadb/` directory contains **runtime schema metadata** for each database backend.

### 6.1 Base Metadata Structure

```scala
// Types_.scala
trait Types_ extends MetaDb {

  /** entity -> List[mandatory-attribute] */
  val mandatoryAttrs: Map[String, List[String]] = Map()

  /** entity -> List[(entity.attr, mandatory refEntity)] */
  val mandatoryRefs: Map[String, List[(String, String)]] = Map()

  /** attr -> (Value, Scala type, required attributes) */
  val attrData: Map[String, (Value, String, List[String])] = Map(
    "Entity.id"       -> (OneValue, "ID",     List()),
    "Entity.i"        -> (OneValue, "Int",    List()),
    "Entity.iSet"     -> (SetValue, "Int",    List()),
    "Entity.iSeq"     -> (SeqValue, "Int",    List()),
    "Entity.iMap"     -> (MapValue, "Int",    List()),
    "Entity.s"        -> (OneValue, "String", List()),
    // ... all attributes
  )
}
```

### 6.2 Database-Specific Metadata

Each DB backend has specialized metadata:

```scala
// Types_h2.scala
object Types_h2 extends Types_ {
  // H2-specific schema details
  override val mandatoryAttrs = Map(...)
}

// Types_postgresql.scala
object Types_postgresql extends Types_ {
  // PostgreSQL-specific schema details
}
```

### 6.3 Metadata Usage

Metadata is used for:
- **Validation**: Checking mandatory attributes at runtime
- **Schema introspection**: Understanding domain model structure
- **Query optimization**: Knowing attribute cardinalities and types
- **Error messages**: Providing helpful validation feedback

---

## 7. Code Examples with Annotations

### 7.1 Simple Query Construction

```scala
// Start with Entity object (Entity_0)
Entity
  // Access 's' attribute → Entity_1_ExprOneMan_String[String]
  .s("foo")
  // Call comparison operator → still Entity_1[String]
  .>("a")
  // Access 'i' attribute → Entity_n_ExprOneMan_Integer[Int, (String, Int)]
  .i
  // Call comparison operator → still Entity_n[(String, Int)]
  .>(10)
  // Final query method (from Molecule trait)
  .query.get

// Result type: Future[List[(String, Int)]]
```

### 7.2 Optional Attribute

```scala
Entity
  .s("foo")              // Entity_1[String]
  .i_?(42)               // Entity_n[(String, Option[Int])]
  .query.get             // Future[List[(String, Option[Int])]]
```

### 7.3 Tacit Filter

```scala
Entity
  .s_("foo")             // Entity_0 (tacit, no type change)
  .i                     // Entity_1[Int]
  .query.get             // Future[List[Int]]

// SQL: SELECT i FROM Entity WHERE s = 'foo'
// Type:  returns only Int, not (String, Int)
```

### 7.4 Relationship Traversal

```scala
Entity
  .s("foo")              // Entity_1[String]
  .Ref                   // Ref_1[String] (forward reference)
  .i(42)                 // Ref_n[(String, Int)]
  .query.get

// SQL: SELECT Entity.s, Ref.i FROM Entity JOIN Ref ON Entity.ref = Ref.id
//      WHERE Entity.s = 'foo' AND Ref.i = 42
```

### 7.5 Nested Pull

```scala
Entity
  .s                     // Entity_1[String]
  .Refs.*(                // One-to-many nested
    Ref.i                // Nested molecule: Molecule_1[Int]
  )                      // Returns: Entity_n[(String, Seq[Int])]
  .query.get

// Result: List[(String, Seq[Int])]
// Example: [("entity1", Seq(1, 2, 3)), ("entity2", Seq(4, 5))]
```

### 7.6 Aggregation

```scala
Entity
  .s                     // Entity_1[String]
  .i(sum)                // Entity_n[(String, Int)] with aggregation
  .query.get

// SQL: SELECT s, SUM(i) FROM Entity GROUP BY s
```

### 7.7 ID Filtering

```scala
// Using apply() method from Entity object
Entity(123L)             // Entity_0 with ID filter
  .s                     // Entity_1[String]
  .query.get

// SQL: SELECT s FROM Entity WHERE id = 123

// Multiple IDs
Entity(123L, 456L)       // IN clause
  .s.query.get

// SQL: SELECT s FROM Entity WHERE id IN (123, 456)
```

---

## 8. Attribute Definition Patterns

### 8.1 Entity_attrs Trait Structure

```scala
trait Entity_attrs {
  // Mandatory attributes
  protected def id_man      = AttrOneManID("Entity", "id", coord = List(0, 0))
  protected def i_man       = AttrOneManInt("Entity", "i", coord = List(0, 1))
  protected def iSet_man    = AttrSetManInt("Entity", "iSet", coord = List(0, 2))
  protected def s_man       = AttrOneManString("Entity", "s", coord = List(0, 5))

  // Optional attributes
  protected def i_opt       = AttrOneOptInt("Entity", "i", coord = List(0, 1))
  protected def iSet_opt    = AttrSetOptInt("Entity", "iSet", coord = List(0, 2))
  protected def s_opt       = AttrOneOptString("Entity", "s", coord = List(0, 5))

  // Tacit attributes
  protected def i_tac       = AttrOneTacInt("Entity", "i", coord = List(0, 1))
  protected def iSet_tac    = AttrSetTacInt("Entity", "iSet", coord = List(0, 2))
  protected def s_tac       = AttrOneTacString("Entity", "s", coord = List(0, 5))

  // Reference attributes
  protected def ref_man     = AttrOneManID("Entity", "ref",
                                           coord = List(0, 32, 1),
                                           ref = Some("Ref"))
}
```

### 8.2 Attribute Naming Convention

| Pattern | Suffix | Cardinality | Example | Return Type |
|---------|--------|-------------|---------|-------------|
| `attr_man` | none | Mandatory | `s` | `String` |
| `attr_opt` | `_?` | Optional | `s_?` | `Option[String]` |
| `attr_tac` | `_` | Tacit | `s_` | (none) |
| `attrSet_man` | none | Set | `iSet` | `Set[Int]` |
| `attrSeq_man` | none | Seq | `iSeq` | `Seq[Int]` |
| `attrMap_man` | none | Map | `iMap` | `Map[String, Int]` |

**Note**: Optional attribute notation changed from `attr$` (Scala 2) to `attr_?` (Scala 3).

---

## 9. Key Design Principles

### 9.1 Immutable DataModel Accumulation

Every method call returns a **new molecule instance** with updated DataModel:

```scala
val m1 = Entity.s("foo")              // DataModel with 1 element
val m2 = m1.i(42)                     // DataModel with 2 elements
// m1 is unchanged, m2 is new instance
```

This enables:
- **Method chaining**: Fluent API construction
- **Reusability**: Base molecules can be composed
- **Thread safety**: No mutable state

### 9.2 Type-Level Computation

Tuple types are computed at **compile-time** using Scala 3's match types and tuple concatenation:

```scala
type Concat[A, B] = (A, B) match {
  case (EmptyTuple, b) => b
  case (a, EmptyTuple) => a
  case (a *: as, b *: bs) => a *: Concat[as, b *: bs]
}

// Entity_n uses :* operator for tuple appending
class Entity_n[Tpl <: Tuple] {
  def i = Entity_n_ExprOneMan_Integer[Int, Tpl :* Int](...)
}
```

### 9.3 Separation of Concerns

- **Entry objects** (`Entity.scala`): Public API surface
- **Ops classes** (`ops/Entity_.scala`): Attribute implementation
- **Expression traits** (from `molecule.db.common.api.expression`): Operation logic
- **Metadata** (`metadb/`): Runtime schema information

This separation enables:
- Clean generated code
- Reusable expression implementations across entities
- Database-specific optimizations in metadata

### 9.4 Lazy Evaluation

All attribute accessors use `lazy val`:

```scala
final lazy val s = Entity_1_ExprOneMan_String[String](dataModel.add(s_man))
```

Benefits:
- **Deferred construction**: Only instantiate when accessed
- **Memory efficiency**: Unused attributes not allocated
- **Circular reference handling**: Entities can reference each other

---

## 10. Summary

The generated DSL provides:

1. **Type-safe molecules** that track return types through phantom type parameters
2. **Cardinality-aware accessors** (mandatory, optional, tacit) for flexible querying
3. **Type-specific operations** (String, Integer, Decimal, Boolean, etc.) via expression traits
4. **Relationship navigation** with forward refs, back refs, and nested pulls
5. **Immutable composition** through DataModel accumulation
6. **Runtime metadata** for validation and introspection

The generator transforms this schema definition:
```scala
object EntitySchema {
  trait Entity {
    val s: String
    val i: Int
    val ref: Ref
  }
}
```

Into this usable API:
```scala
Entity
  .s("foo").>("a")
  .i(42).<(100)
  .Ref.i(99)
  .query.get
```

With full compile-time type safety:
```scala
// Type: Future[List[(String, Int, Int)]]
//                    ↑       ↑    ↑
//                 Entity.s  .i  Ref.i
```

This design achieves the "programmable comma" vision: building complex queries through natural method chaining while maintaining complete type safety and composability.
