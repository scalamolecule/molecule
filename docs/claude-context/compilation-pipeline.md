# Molecule Compilation Pipeline

## Naming Conventions

**Entity Names**: `[A-Z][a-zA-Z0-9]*` (uppercase first, letters/digits only)
**Segment Names**: `[a-z][a-z0-9]*` (lowercase first, then letters/digits only)

**Validated by**: `ParseDomainStructure` in sbt-molecule

---

## Overview

Molecule transforms type-safe DSL expressions into executable database queries through a sophisticated multi-stage compilation pipeline:

```
DSL Method Calls
    ↓
DataModel (Elements)
    ↓
Model Validation & Transformations
    ↓
Query Resolution (Model2Query)
    ↓
SQL Generation (Model2SqlQuery)
    ↓
PreparedStatement Binding
    ↓
ResultSet Execution
    ↓
Type-Safe Tuple Casting
    ↓
Typed Results
```

## 1. DataModel Elements

### Element Hierarchy

The core data structure that represents a molecule query is a `DataModel` containing a list of `Element` objects:

**File**: `/core/shared/src/main/scala/molecule/core/dataModel/Element.scala`

```scala
sealed trait Element

// Attributes (value holders)
sealed trait Attr extends Element {
  val ent: String         // Entity/table name
  val attr: String        // Attribute/column name
  val op: Op              // Operation (V, Eq, Lt, Gt, etc.)
  val filterAttr: Option[(Int, List[String], Attr)]  // Filter by other attr
  val validator: Option[Validator]
  val valueAttrs: List[String]
  val errors: Seq[String]
  val ref: Option[String]
  val sort: Option[String]
  val coord: List[Int]    // Coordinates for tracking
}

// Relationships
case class Ref(
  ent: String,
  refAttr: String,
  ref: String,
  relationship: Relationship,  // OneToMany | ManyToOne
  coord: List[Int],
  reverseRef: Option[String],
  owner: Boolean
) extends Element

case class BackRef(prev: String, cur: String, coord: List[Int]) extends Element

// Optional structures
case class OptEntity(attrs: List[Attr]) extends Element
case class OptRef(ref: Ref, elements: List[Element]) extends Element

// Nested structures
case class Nested(ref: Ref, elements: List[Element]) extends Element
case class OptNested(ref: Ref, elements: List[Element]) extends Element

// Subqueries
case class SubQuery(elements: List[Element]) extends Element
```

### Attribute Cardinality & Mode

**Cardinality**:
- `AttrOne` - Single value (card-one)
- `AttrSet` - Set of values (card-many, unordered)
- `AttrSeq` - Sequence of values (card-many, ordered)
- `AttrMap` - Map of key-value pairs (card-map)

**Mode** (trait mixins):
- `Mandatory` (`Man`) - Required, returns value
- `Optional` (`Opt`) - Optional, returns `Option[T]`
- `Tacit` (`Tac`) - Filter-only, not returned

Examples:
- `AttrOneManString` - Mandatory single String
- `AttrSetOptInt` - Optional Set of Ints
- `AttrOneTacBoolean` - Tacit Boolean (filter only)

**All 25+ Scala types supported**: ID (Long), String, Int, Long, Float, Double, Boolean, BigInt, BigDecimal, Date, Duration, Instant, LocalDate, LocalTime, LocalDateTime, OffsetTime, OffsetDateTime, ZonedDateTime, UUID, URI, Byte, Short, Char

**Note**: Scala 3 tuples support **unlimited arity** (no 22-limit from Scala 2.13). Bindings have a max of 22 parameters.

### Operations (Op)

**File**: `/core/shared/src/main/scala/molecule/core/dataModel/Op.scala`

```scala
sealed trait Op
case object V           // Plain value (no operation)
case object Eq          // Equal
case object Neq         // Not equal
case object Lt          // Less than
case object Le          // Less than or equal
case object Gt          // Greater than
case object Ge          // Greater than or equal
case object NoValue     // Null check

// String operations
case object StartsWith
case object EndsWith
case object Contains
case object Matches     // Regex

// Numeric operations
case object Remainder
case object Even
case object Odd

// Set operations
case object Has         // Set contains
case object HasNo       // Set doesn't contain

// Aggregation function
case class Fn(
  baseType: String,
  fn: String,           // count, sum, avg, min, max, etc.
  n: Option[Int],       // sample/median n
  op: Option[Op],       // optional comparison in HAVING
  v: Option[Value]      // optional value for HAVING
) extends Op

// Attribute operations (transformations)
sealed trait AttrOp extends Op
object AttrOp {
  // String: Append, Prepend, SubString, ReplaceAll, ToLower, ToUpper
  // Numeric: Plus, Minus, Times, Divide, Negate, Abs, AbsNeg, Ceil, Floor
  // Boolean: And, Or, Not
}
```

### DataModel Structure

**File**: `/core/shared/src/main/scala/molecule/core/dataModel/DataModel.scala`

```scala
case class DataModel(
  elements: List[Element],          // The molecule structure
  attrIndexes: Set[Int] = Set.empty[Int],
  firstEntityIndex: Int = -1,
  binds: Int = 0,                   // Number of bind variables
  inputElements: List[Element] = Nil
)
```

## 2. Molecule Building Phase

### How DSL Chaining Accumulates Elements

Each DSL method call on a molecule returns a new molecule with an updated `DataModel`:

**File**: `/db/common/shared/src/main/scala/molecule/db/common/api/Molecule.scala`

```scala
trait Molecule {
  val dataModel: DataModel
}

// Empty molecule (no attributes yet)
trait Molecule_0 extends Molecule

// Single attribute molecule
trait Molecule_1[T] extends NonEmptyMolecule

// Multiple attributes (tuple)
trait Molecule_n[Tpl <: Tuple] extends NonEmptyMolecule
```

**Example DSL to Elements**:

```scala
Person.name.age
```
Generates:
```scala
DataModel(
  List(
    AttrOneManString("Person", "name", V, Seq()),
    AttrOneManInt("Person", "age", V, Seq())
  )
)
```

With filter:
```scala
Person.name.age(42)
```
Generates:
```scala
AttrOneManInt("Person", "age", Eq, Seq(42))
```

With aggregation:
```scala
Person.name.age(avg)
```
Generates:
```scala
AttrOneManInt("Person", "age", Fn("Int", "avg", None, None, None))
```

## 3. Query Resolution

### Model2Query - Core Resolution Logic

**File**: `/db/common/shared/src/main/scala/molecule/db/common/query/Model2Query.scala`

The `Model2Query` trait recursively resolves `Element` objects into query components:

```scala
trait Model2Query extends QueryExpr {

  final def resolve(elements: List[Element]): Unit = elements match {
    case element :: tail => element match {
      case a: AttrOne => a match {
        case a: AttrOneMan => queryAttrOneMan(a); resolve(tail)
        case a: AttrOneTac => queryAttrOneTac(a); resolve(tail)
        case a: AttrOneOpt => queryAttrOneOpt(a); resolve(tail)
      }

      case a: AttrSet => a match {
        case a: AttrSetMan => queryAttrSetMan(a); resolve(tail)
        case a: AttrSetTac => queryAttrSetTac(a); resolve(tail)
        case a: AttrSetOpt => queryAttrSetOpt(a); resolve(tail)
      }

      case a: AttrSeq => /* ... */
      case a: AttrMap => /* ... */

      case ref: Ref                 => queryRef(ref, tail); resolve(tail)
      case backRef: BackRef         => queryBackRef(backRef, tail); resolve(tail)
      case OptRef(ref, refElements) => queryOptRef(ref, refElements); resolve(tail)
      case OptEntity(refElements)   => queryOptEntity(refElements); resolve(tail)
      case SubQuery(subElements)    => querySubQuery(subElements); resolve(tail)
      case Nested(ref, nestedElements)    => queryNested(ref, nestedElements); resolve(tail)
      case OptNested(ref, nestedElements) => queryOptNested(ref, nestedElements); resolve(tail)
    }
    case Nil => ()
  }
}
```

### QueryExpr - Database-Specific Implementations

**File**: `/db/common/shared/src/main/scala/molecule/db/common/query/QueryExpr.scala`

This trait defines the interface that each database must implement:

```scala
trait QueryExpr {
  protected def queryIdMan(attr: AttrOneMan): Unit
  protected def queryIdTac(attr: AttrOneTac): Unit

  protected def queryAttrOneMan(attr: AttrOneMan): Unit
  protected def queryAttrOneTac(attr: AttrOneTac): Unit
  protected def queryAttrOneOpt(attr: AttrOneOpt): Unit

  protected def queryAttrSetMan(attr: AttrSetMan): Unit
  // ... etc for all attribute types

  protected def queryRef(ref: Ref, tail: List[Element]): Unit
  protected def queryBackRef(backRef: BackRef, tail: List[Element]): Unit
  // ... etc for all element types
}
```

### QueryExprOne - Card-One Attribute Resolution

**File**: `/db/common/shared/src/main/scala/molecule/db/common/query/QueryExprOne.scala`

Example of how a mandatory card-one attribute is resolved:

```scala
trait QueryExprOne extends QueryExpr {

  override protected def queryAttrOneMan(attr: AttrOneMan): Unit = {
    attr match {
      case at: AttrOneManID      => man(attr, at.vs, resId1)
      case at: AttrOneManString  => man(attr, at.vs, resString1)
      case at: AttrOneManInt     => man(attr, at.vs, resInt1)
      // ... for all 25+ types
    }
  }

  protected def man[T: ClassTag](
    attr: AttrOne,
    args: Seq[T],
    res: ResOne[T]
  ): Unit = {
    val col = getCol(attr)
    select += col                    // Add to SELECT
    groupByCols += col               // Add to GROUP BY (if needed)
    castStrategy.add(res.sql2one)    // Add result caster
    addSort(attr, col)               // Add ORDER BY (if specified)

    // Add WHERE clause based on operation
    attr.filterAttr.fold {
      expr(attr.ent, attr.attr, col, attr.op, args,
           attr.sort.isDefined, attr.binding, res)
    } { case (dir, filterPath, filterAttr) =>
      expr2(col, attr.op, filterAttr.name)  // Filter by another attr
    }
  }

  // Generate WHERE predicate based on operation
  protected def expr[T](
    ent: String, attr: String, col: String,
    op: Op, args: Seq[T], hasSort: Boolean,
    binding: Boolean, res: ResOne[T]
  ): Unit = op match {
    case V           => attrV(col)           // No filter
    case Eq          => equal(col, args, ...)
    case Neq         => neq(col, args, ...)
    case Lt          => compare(col, args, "<", ...)
    case Gt          => compare(col, args, ">", ...)
    case Le          => compare(col, args, "<=", ...)
    case Ge          => compare(col, args, ">=", ...)
    case NoValue     => noValue(col)         // IS NULL
    case StartsWith  => startsWith(col, args, ...)
    case EndsWith    => endsWith(col, args, ...)
    case Contains    => contains(col, args, ...)
    case Matches     => matches(col, args, ...)
    case Remainder   => remainder(col, args)
    case Even        => even(col)
    case Odd         => odd(col)
    case Fn(...)     => aggr(...)            // Aggregation
    case other       => unexpectedOp(other)
  }
}
```

## 4. SQL Generation

### Model2SqlQuery - SQL Query Builder

**File**: `/db/common/shared/src/main/scala/molecule/db/common/query/Model2SqlQuery.scala`

```scala
abstract class Model2SqlQuery(elements0: List[Element])
  extends Model2Query
    with QueryExprRef
    with SqlQueryBase {

  final def getSqlQuery(
    altElements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int],
    optProxy: Option[ConnProxy]
  ): String = {
    val elements1 = if (altElements.isEmpty) elements0 else altElements
    resolveElements(elements1)
    renderSqlQuery(optLimit, optOffset)
  }

  final def resolveElements(elements1: List[Element]): Unit = {
    from = getInitialEntity(elements1)
    prevRefs = Set(from)
    path = List(from)
    preExts += path -> None

    val (elements2, _, _) = validateQueryModel(
      elements1, None, Some(handleRef), Some(handleBackRef)
    )

    path = List(from)
    exts ++= preExts

    // Recursively resolve molecule elements
    resolve(elements2)
  }

  final def renderSqlQuery(
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): String = {
    val distinct_   = if (distinct) " DISTINCT" else ""
    val select_     = mkSelect
    val joins_      = mkJoins(1)
    val tempTables_ = mkTempTables
    val where_      = mkWhere
    val groupBy_    = mkGroupBy
    val having_     = mkHaving
    val orderBy_    = mkOrderBy(isBackwards)
    val pagination_ = pagination(optLimit, optOffset, isBackwards)

    s"""SELECT$distinct_
       |  $select_
       |FROM $from$joins_$tempTables_$where_$groupBy_$having_$orderBy_$pagination_;""".stripMargin
  }
}
```

### SqlQueryBase - Query Component Builders

**File**: `/db/common/shared/src/main/scala/molecule/db/common/query/SqlQueryBase.scala`

This trait holds mutable state that accumulates query components:

```scala
trait SqlQueryBase {
  type RS = ResultSetInterface
  type ParamIndex = Int
  type Cast = (RS, ParamIndex) => Any

  // Query components
  final val select      = new ListBuffer[String]
  final var distinct    = true
  final var from        = ""
  final val joins       = new ListBuffer[(String, String, String, List[String])]
  final val tempTables  = ListBuffer.empty[String]
  final val where       = new ListBuffer[(String, String)]
  final val groupBy     = new mutable.LinkedHashSet[String]
  final val having      = new mutable.LinkedHashSet[String]
  final var orderBy     = new ListBuffer[(Int, Int, String, String)]
  final var aggregate   = false
  final val groupByCols = new mutable.LinkedHashSet[String]

  // Prepared statement bindings
  final val binders    = new ListBuffer[PrepStmt => Unit]
  final var bindIndex  = -1
  final val bindValues = new ListBuffer[Value]

  // Result casting strategy
  final var castStrategy: CastStrategy = CastTuple()
}
```

### Database-Specific SQL Generation

**File**: `/db/h2/shared/src/main/scala/molecule/db/h2/query/Model2SqlQuery_h2.scala`

Each database extends the base with database-specific SQL dialects:

```scala
class Model2SqlQuery_h2(elements0: List[Element])
  extends Model2SqlQuery(elements0)
    with QueryExprOne_h2
    with QueryExprSet_h2
    with QueryExprSeq_h2
    with QueryExprMap_h2
    with QueryExprSetRefAttr_h2
    with SqlQueryBase {

  // H2-specific implementations override base methods
  // e.g., array handling, JSON functions, etc.
}
```

### Filter Compilation Examples

**Equal**:
```scala
Person.age(42)
```
→ WHERE clause: `Person.age = ?` with binding `42`

**Greater than**:
```scala
Person.age.>(18)
```
→ WHERE clause: `Person.age > ?` with binding `18`

**Multiple values (OR)**:
```scala
Person.age(25, 30, 35)
```
→ WHERE clause: `Person.age IN (?, ?, ?)` with bindings `[25, 30, 35]`

**String operations**:
```scala
Person.name.startsWith("John")
```
→ WHERE clause: `Person.name LIKE ?` with binding `"John%"`

**Set operations**:
```scala
Person.hobbies.has("Tennis")
```
→ WHERE clause: `? = ANY(Person.hobbies)` with binding `"Tennis"`

### Aggregation Compilation

**Count**:
```scala
Person.name.age(count)
```
→ SELECT: `Person.name, COUNT(Person.age)`
→ GROUP BY: `Person.name`

**Average**:
```scala
Person.department.salary(avg)
```
→ SELECT: `Person.department, AVG(Person.salary)`
→ GROUP BY: `Person.department`

**With HAVING**:
```scala
Person.department.salary(avg).>(50000.0)
```
→ SELECT: `Person.department, AVG(Person.salary)`
→ GROUP BY: `Person.department`
→ HAVING: `AVG(Person.salary) > ?` with binding `50000.0`

### Join Compilation

**Many-to-one relationship**:
```scala
Person.name.Address.street
```
→ FROM: `Person`
→ JOIN: `INNER JOIN Address ON Address.id = Person.address`
→ SELECT: `Person.name, Address.street`

**One-to-many relationship**:
```scala
Person.name.Order.amount
```
→ FROM: `Person`
→ JOIN: `INNER JOIN Order ON Order.person = Person.id`
→ SELECT: `Person.name, Order.amount`

**Optional relationship**:
```scala
Person.name.Address.?(_.street)
```
→ FROM: `Person`
→ JOIN: `LEFT JOIN Address ON Address.id = Person.address`
→ SELECT: `Person.name, Address.street`

**Back reference**:
```scala
Person.name.Address.street._Person.age
```
→ Navigates back to Person after joining Address

### Sorting and Pagination

**Sort ascending**:
```scala
Person.name.a1.age
```
→ ORDER BY: `Person.name ASC`

**Sort descending**:
```scala
Person.name.age.d1
```
→ ORDER BY: `Person.age DESC`

**Multiple sorts**:
```scala
Person.name.a1.age.d2
```
→ ORDER BY: `Person.name ASC, Person.age DESC`

**Limit**:
```scala
Person.name.age.query.limit(10)
```
→ LIMIT: `10`

**Offset**:
```scala
Person.name.age.query.offset(20)
```
→ OFFSET: `20`

## 5. Transaction Resolution

### Insert Compilation

**File**: `/db/common/jvm/src/main/scala/molecule/db/common/transaction/ResolveInsert.scala`

```scala
trait ResolveInsert {
  final def resolve(
    elements: List[Element],
    paramIndex: Int,
    tplIndex: Int,
    partitions: List[Partition],
    tableInsert: TableInsert,
  ): List[Partition] = {
    elements match {
      case element :: tail => element match {
        case a: AttrOneMan =>
          val tableInsert1 = tableInsert.add(
            a, attrOneManSetter(tableInsert, a, paramIndex, tplIndex), cast
          )
          resolve(tail, paramIndex + 1, tplIndex + 1, partitions, tableInsert1)

        case a: AttrSetMan =>
          val tableInsert1 = tableInsert.add(
            a, attrSetManSetter(a, paramIndex, tplIndex)
          )
          resolve(tail, paramIndex + 1, tplIndex + 1, partitions, tableInsert1)

        case Ref(_, refAttr, ref, OneToMany, _, reverseRefAttr, _) =>
          resolveOneToManyRef(partitions, tableInsert, refAttr, ref,
                              reverseRefAttr, tplIndex, tail)

        case Ref(_, refAttr, ref, ManyToOne, _, reverseRefAttr, _) =>
          resolveManyToOneRef(partitions, tableInsert, refAttr, ref,
                              reverseRefAttr, tplIndex, tail)

        case Nested(Ref(_, refAttr, ref, _, _, reverseRefAttr, _), nestedElements) =>
          resolveNested(partitions, nestedElements, refAttr, ref,
                       reverseRefAttr, tableInsert, tplIndex)

        // ...
      }
      case Nil => /* return accumulated partitions */
    }
  }
}
```

**Insert example**:
```scala
Person.name.age.insert(("John", 42), ("Jane", 35))
```

Generates:
```sql
INSERT INTO Person (name, age) VALUES (?, ?), (?, ?)
```
with bindings: `["John", 42, "Jane", 35]`

### Save Compilation

**File**: `/db/common/jvm/src/main/scala/molecule/db/common/transaction/ResolveSave.scala`

Save = Upsert (insert if not exists, update if exists)

```scala
trait ResolveSave {
  final def resolve(
    elements: List[Element],
    paramIndex: Int,
    tableInserts: List[TableInsert],
    tableInsert: TableInsert,
  ): List[TableInsert] = {
    elements match {
      case element :: tail => element match {
        case a: AttrOneMan =>
          val tableInsert1 = tableInsert.add(
            a, resolveAttrOneMan(a, paramIndex), cast
          )
          resolve(tail, paramIndex + 1, tableInserts, tableInsert1)
        // ...
      }
      case Nil => tableInserts :+ tableInsert
    }
  }
}
```

**Save example**:
```scala
Person.name("John").age(42).save
```

Generates (H2/PostgreSQL):
```sql
MERGE INTO Person (name, age) KEY(name) VALUES (?, ?)
```
or (MySQL):
```sql
INSERT INTO Person (name, age) VALUES (?, ?)
ON DUPLICATE KEY UPDATE age = VALUES(age)
```

### Update Compilation

**File**: `/db/common/jvm/src/main/scala/molecule/db/common/transaction/ResolveUpdate.scala`

Similar pattern - resolves elements to UPDATE statements with WHERE clause.

### Delete Compilation

**File**: `/db/common/jvm/src/main/scala/molecule/db/common/transaction/ResolveDelete.scala`

```scala
trait ResolveDelete {
  final def resolve(
    elements: List[Element],
    topLevel: Boolean,
    tableDelete: TableDelete
  ): TableDelete = {
    elements match {
      case element :: tail => element match {
        case AttrOneTacID(_, "id", Eq, ids, _, _, _, _, _, _, _, _) =>
          resolve(tail, topLevel, tableDelete.add(a))

        case _: AttrOneTac =>
          resolve(tail, topLevel, tableDelete.add(a))

        case r@Ref(ent, refAttr, ref, relationship, _, reverseRefAttr, owner) =>
          // Add join for filtering by related entity
          resolve(tail, false, tableDelete.copy(
            joinTable = Some(ref),
            joinClause = Some(...)
          ))
        // ...
      }
      case Nil => tableDelete
    }
  }
}
```

**Delete example**:
```scala
Person.name_("John").age_(42).delete
```

Generates:
```sql
DELETE FROM Person WHERE Person.name = ? AND Person.age = ?
```
with bindings: `["John", 42]`

## 6. Model Transformations

**File**: `/db/common/shared/src/main/scala/molecule/db/common/ops/ModelTransformations_.scala`

### Aggregation Transformations

When you call `.count`, `.sum`, `.avg`, etc., Molecule transforms the DataModel:

```scala
trait ModelTransformations_ {

  // Transform to aggregation function
  def asIs(dataModel: DataModel, kw: Kw, n: Option[Int] = None): DataModel = {
    val es = dataModel.elements
    val last = es.last match {
      case a: AttrOneMan => a match {
        case a: AttrOneManInt => a.copy(op = getFn(a, kw, n))
        // ... for all types
      }
    }
    dataModel.copy(elements = es.init :+ last)
  }

  // Add aggregation operation (for HAVING clause)
  def addAggrOp[T](
    dataModel: DataModel,
    aggrOp: Op,
    aggrOpV: Option[T]
  ): DataModel = {
    val es = dataModel.elements
    val last = es.last match {
      case a: AttrOneMan =>
        val fn = a.op.asInstanceOf[Fn]
        a match {
          case a: AttrOneManInt =>
            aggrOpV.fold(
              a.copy(op = fn.copy(op = Some(aggrOp)), binding = true)
            )(v =>
              a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneInt(v.asInstanceOf[Int]))))
            )
          // ... for all types
        }
    }
    dataModel.copy(elements = es.init :+ last)
  }
}
```

### Nested/Optional Transformations

```scala
// Add nested data structure
def addNested(self: Molecule, nestedMolecule: Molecule): DataModel = {
  val dataModel = self.dataModel
  val nestedDataModel = nestedMolecule.dataModel
  val ns = getInitialEntity(dataModel.elements.reverse)
  val initialElements = dataModel.elements.init :+ getSortedTacitId(ns)
  DataModel(
    initialElements :+ Nested(dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
    dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
    binds = dataModel.binds + nestedDataModel.binds
  )
}

// Add optional ref
def addOptRef(self: Molecule, optRef: Molecule): DataModel = {
  val dataModel = self.dataModel
  val optRefDataModel = optRef.dataModel
  DataModel(
    dataModel.elements.init :+ OptRef(dataModel.elements.last.asInstanceOf[Ref], optRefDataModel.elements),
    dataModel.attrIndexes ++ optRefDataModel.attrIndexes,
    binds = dataModel.binds + optRefDataModel.binds
  )
}

// Add subquery
def addSubQuery(self: Molecule, subQuery: Molecule): DataModel = {
  val dataModel = self.dataModel
  val subQueryDataModel = subQuery.dataModel
  DataModel(
    dataModel.elements :+ SubQuery(subQueryDataModel.elements),
    dataModel.attrIndexes ++ subQueryDataModel.attrIndexes,
    binds = dataModel.binds + subQueryDataModel.binds
  )
}
```

### Query Planning Optimizations

1. **Tacit ID sorting**: Automatically adds tacit entity IDs for proper nested sorting
2. **Path tracking**: Maintains reference paths to generate proper table aliases
3. **Extension resolution**: Generates `_refName` extensions for re-used entities
4. **Predicate optimization**: Moves predicates to JOIN ON clauses for LEFT JOINs

## 7. Execution & Marshalling

### Query Execution Flow

**File**: `/db/common/jvm/src/main/scala/molecule/db/common/spi/SpiBaseJVM_sync.scala`

```scala
trait SpiBaseJVM_sync extends Spi_sync {

  override def query_get[Tpl](query: Query[Tpl])(using conn0: Conn): List[Tpl] = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]

    // 1. Check access control
    checkQueryAccess(query.dataModel.elements, conn)

    // 2. Clean element names (keyword collision prevention)
    val cleanElements = keywordsSuffixed(query.dataModel.elements, conn.proxy)
    val cleanDataModel = query.dataModel.copy(elements = cleanElements)

    // 3. Build Model2SqlQuery
    val m2q = getModel2SqlQuery(cleanElements)
    m2q.bindValues.addAll(query.bindValues)

    // 4. Generate SQL and execute
    SqlQueryResolveOffset[Tpl](cleanDataModel, query.optLimit, None, m2q)
      .getListFromOffset_sync(using conn)._1
  }
}
```

### ResultSet to Typed Tuples

**Casting Strategy**:

**File**: `/db/common/shared/src/main/scala/molecule/db/common/query/casting/strategy/CastTuple.scala`

```scala
case class CastTuple(
  casts0: List[(ResultSetInterface, Int) => Any] = Nil,
  firstIndex: Int = 1
) extends CastStrategy {

  private var casts = casts0

  // Build row-to-tuple caster function
  override def rs2row: RS => Any =
    CastTpl_.cast(casts, firstIndex)

  override def add(cast: Cast): Unit = {
    casts = casts :+ cast
  }
}
```

**File**: `/db/common/shared/src/main/scala/molecule/db/common/query/casting/CastTpl_.scala`

Generated code for tuple casting (up to arity 22):

```scala
object CastTpl_ {

  final def cast(
    casts: List[Cast],
    firstIndex: ParamIndex
  ): RS => Any = {
    casts.length match {
      case 1  => cast1(casts, firstIndex)
      case 2  => cast2(casts, firstIndex)
      case 3  => cast3(casts, firstIndex)
      // ... up to 22
      case n  => castN(casts, firstIndex)  // Dynamic for > 22
    }
  }

  final private def cast2(
    casts: List[(RS, ParamIndex) => Any],
    firstIndex: ParamIndex
  ): RS => Any = {
    val List(c1, c2) = casts
    val List(i1, i2) = (firstIndex until firstIndex + 2).toList
    (row: RS) =>
      (
        c1(row, i1),
        c2(row, i2)
      )
  }

  // ... cast3, cast4, etc.
}
```

### Type-Specific Casters

Each database type has a corresponding caster that extracts and converts ResultSet values:

**Example casters** (in `LambdasOne`, `LambdasSet`, etc.):

```scala
// Card-one String
val resString1 = ResOne(
  tpe = "String",
  sql2one = (row: RS, paramIndex: Int) => row.getString(paramIndex),
  sql2oneOrNull = (row: RS, paramIndex: Int) => {
    val v = row.getString(paramIndex)
    if (row.wasNull()) null else v
  },
  one2sql = (v: String) => v,
  bind = (ps: PrepStmt, paramIndex: Int, v: String) => ps.setString(paramIndex, v)
)

// Card-one Int
val resInt1 = ResOne(
  tpe = "Int",
  sql2one = (row: RS, paramIndex: Int) => row.getInt(paramIndex),
  sql2oneOrNull = (row: RS, paramIndex: Int) => {
    val v = row.getInt(paramIndex)
    if (row.wasNull()) null else v
  },
  one2sql = (v: Int) => v,
  bind = (ps: PrepStmt, paramIndex: Int, v: Int) => ps.setInt(paramIndex, v)
)

// Card-many Set[String]
val resSetString = ResSet(
  tpe = "String",
  sql2set = (row: RS, paramIndex: Int) => {
    val array = row.getArray(paramIndex).getArray.asInstanceOf[Array[String]]
    array.toSet
  },
  set2sqlArray = (set: Set[String]) => set.toArray,
  // ...
)
```

### Marshalling for Client/Server (JS/JVM)

**Serialization (JVM → JS)**:

**File**: `/db/common/shared/src/main/scala/molecule/db/common/marshalling/serialize/PickleTpls.scala`

```scala
case class PickleTpls(
  dataModel: DataModel,
  allTuples: Boolean
) extends PickleTpl_ {

  def getPickledTpls(tpls: Seq[Any]): ByteBuffer = {
    pickleTpls(tpls)
    state.toByteBuffer
  }

  private def pickleTpls(tpls: Seq[Any]): Unit = {
    enc.writeInt(tpls.size)  // encode length
    if (tpls.nonEmpty) {
      val pickleTpl = getPickler(dataModel.elements)
      tpls.asInstanceOf[Seq[Product]].foreach(pickleTpl)
    }
  }

  @tailrec
  final protected def resolvePicklers(
    elements: List[Element],
    picklers: List[Product => Unit],
    tplIndex: Int
  ): List[Product => Unit] = {
    elements match {
      case element :: tail => element match {
        case a: AttrOneMan =>
          resolvePicklers(tail, picklers :+ pickleAttrOneMan(a, tplIndex), tplIndex + 1)
        case a: AttrSetMan =>
          resolvePicklers(tail, picklers :+ pickleAttrSetMan(a, tplIndex), tplIndex + 1)
        // ...
      }
      case Nil => picklers
    }
  }
}
```

**Deserialization (JS → JVM)**:

**File**: `/db/common/shared/src/main/scala/molecule/db/common/marshalling/deserialize/UnpickleTpls.scala`

```scala
case class UnpickleTpls[Tpl](
  dataModel: DataModel,
  eitherSerialized: ByteBuffer
) extends UnpickleTpl_[Tpl] {

  def unpickleTpls: List[Tpl] = {
    dec.readInt match {  // decode List size
      case 0   => List.empty[Tpl]
      case len =>
        val tpls = ListBuffer.empty[Tpl]
        tpls.sizeHint(len)
        val unpickleTpl = getUnpickler(dataModel.elements)
        var i = 0
        while (i < len) {
          tpls += unpickleTpl().asInstanceOf[Tpl]
          i += 1
        }
        tpls.result()
    }
  }

  @tailrec
  final protected def resolveUnpicklers(
    elements: List[Element],
    unpicklers: List[() => Any]
  ): List[() => Any] = {
    elements match {
      case element :: tail => element match {
        case a: AttrOneMan =>
          resolveUnpicklers(tail, unpicklers :+ unpickleAttrOneMan(a))
        case a: AttrSetMan =>
          resolveUnpicklers(tail, unpicklers :+ unpickleAttrSetMan(a))
        // ...
      }
      case Nil => unpicklers
    }
  }
}
```

## 8. Database-Specific Implementations

### Architecture

Each database has its own implementation package:
- `/db/h2/` - H2 (embedded/in-memory)
- `/db/postgresql/` - PostgreSQL
- `/db/mysql/` - MySQL
- `/db/mariadb/` - MariaDB
- `/db/sqlite/` - SQLite

### Database-Specific Traits

Each database extends base traits and overrides for SQL dialect differences:

**H2 Example**:

```scala
// Query
class Model2SqlQuery_h2(elements0: List[Element])
  extends Model2SqlQuery(elements0)
    with QueryExprOne_h2
    with QueryExprSet_h2
    with QueryExprSeq_h2
    with QueryExprMap_h2
    with SqlQueryBase

// SPI
trait Spi_h2_sync extends SpiBaseJVM_sync {
  override def getModel2SqlQuery(elements: List[Element]): Model2SqlQuery & SqlQueryBase =
    new Model2SqlQuery_h2_JVM(elements)
}
```

### Key Database Differences

**Array/Set handling**:
- **H2/PostgreSQL**: Native array support (`INT ARRAY`, `= ANY(array)`)
- **MySQL/MariaDB**: JSON arrays (`JSON_CONTAINS()`)
- **SQLite**: No native arrays, converted to JSON

**Upsert syntax**:
- **H2/PostgreSQL**: `MERGE INTO` / `INSERT ... ON CONFLICT`
- **MySQL/MariaDB**: `INSERT ... ON DUPLICATE KEY UPDATE`
- **SQLite**: `INSERT OR REPLACE INTO`

**JSON functions**:
- **H2**: `JSON_VALUE()`, `JSON_ARRAY()`
- **PostgreSQL**: `->`, `->>`, `jsonb` operators
- **MySQL/MariaDB**: `JSON_EXTRACT()`, `JSON_CONTAINS()`
- **SQLite**: `json_extract()`, `json_array()`

**String matching**:
- **All**: `LIKE` for prefix/suffix/contains
- **PostgreSQL**: Also supports `SIMILAR TO` for regex
- **MySQL/MariaDB**: `REGEXP`

## 9. Flow Diagrams

### Complete Compilation Flow

```
┌─────────────────────────────────────────────────────────────┐
│ DSL Layer                                                   │
│ Person.name.age(42)                                         │
└────────────────────────────┬────────────────────────────────┘
                             │ Method chaining
                             ↓
┌─────────────────────────────────────────────────────────────┐
│ DataModel Construction                                      │
│ List(                                                       │
│   AttrOneManString("Person", "name", V, Seq()),             │
│   AttrOneManInt("Person", "age", Eq, Seq(42))               │
│ )                                                           │
└────────────────────────────┬────────────────────────────────┘
                             │ .query.get
                             ↓
┌─────────────────────────────────────────────────────────────┐
│ Model Validation & Transformations                          │
│ - Validate structure                                        │
│ - Clean keywords                                            │
│ - Apply optimizations                                       │
└────────────────────────────┬────────────────────────────────┘
                             │ resolve()
                             ↓
┌─────────────────────────────────────────────────────────────┐
│ Query Resolution (Model2Query)                              │
│ - Iterate elements recursively                              │
│ - Dispatch to type-specific handlers                        │
│ - Build query components                                    │
└────────────────────────────┬────────────────────────────────┘
                             │ renderSqlQuery()
                             ↓
┌─────────────────────────────────────────────────────────────┐
│ SQL Generation                                              │
│ SELECT Person.name, Person.age                              │
│ FROM Person                                                 │
│ WHERE Person.age = ?                                        │
│                                                             │
│ Bindings: [42]                                              │
│ Casts: [resString1, resInt1]                                │
└────────────────────────────┬────────────────────────────────┘
                             │ executeQuery()
                             ↓
┌─────────────────────────────────────────────────────────────┐
│ JDBC Execution                                              │
│ - Create PreparedStatement                                  │
│ - Bind parameters                                           │
│ - Execute query                                             │
│ - Get ResultSet                                             │
└────────────────────────────┬────────────────────────────────┘
                             │ while (rs.next())
                             ↓
┌─────────────────────────────────────────────────────────────┐
│ Result Casting                                              │
│ rs2row(resultSet):                                          │
│   (                                                         │
│     resString1(rs, 1),  // "John"                           │
│     resInt1(rs, 2)      // 42                               │
│   )                                                         │
└────────────────────────────┬────────────────────────────────┘
                             │ collect()
                             ↓
┌─────────────────────────────────────────────────────────────┐
│ Typed Result                                                │
│ List(("John", 42))                                          │
└─────────────────────────────────────────────────────────────┘
```

### Transaction Flow (Insert)

```
┌─────────────────────────────────────────────────────────────┐
│ DSL Insert                                                  │
│ Person.name.age.insert(("John", 42), ("Jane", 35))         │
└────────────────────────────┬────────────────────────────────┘
                             │
                             ↓
┌─────────────────────────────────────────────────────────────┐
│ ResolveInsert                                               │
│ - Build TableInsert structures                              │
│ - Create parameter setters                                  │
│ - Handle foreign keys                                       │
│ - Handle nested data                                        │
└────────────────────────────┬────────────────────────────────┘
                             │
                             ↓
┌─────────────────────────────────────────────────────────────┐
│ SQL Generation                                              │
│ INSERT INTO Person (name, age) VALUES (?, ?), (?, ?)        │
│                                                             │
│ Setters:                                                    │
│ - ps.setString(1, tuple._1)                                 │
│ - ps.setInt(2, tuple._2)                                    │
└────────────────────────────┬────────────────────────────────┘
                             │
                             ↓
┌─────────────────────────────────────────────────────────────┐
│ Batch Execution                                             │
│ - Create PreparedStatement                                  │
│ - For each tuple:                                           │
│   - Apply setters                                           │
│   - addBatch()                                              │
│ - executeBatch()                                            │
│ - getGeneratedKeys()                                        │
└────────────────────────────┬────────────────────────────────┘
                             │
                             ↓
┌─────────────────────────────────────────────────────────────┐
│ TxReport                                                    │
│ TxReport(eids = List(101, 102))                             │
└─────────────────────────────────────────────────────────────┘
```

## 10. Code Examples

### Example 1: Simple Filter Query

**DSL**:
```scala
Person.name.age(42).query.get
```

**Elements**:
```scala
List(
  AttrOneManString("Person", "name", V, Seq()),
  AttrOneManInt("Person", "age", Eq, Seq(42))
)
```

**SQL**:
```sql
SELECT DISTINCT
  Person.name,
  Person.age
FROM Person
WHERE
  Person.age = ?;
```

**Bindings**: `[42]`

**Result Type**: `List[(String, Int)]`

### Example 2: Aggregation with Filter

**DSL**:
```scala
Person.department.salary(avg).>(50000.0).query.get
```

**Elements**:
```scala
List(
  AttrOneManString("Person", "department", V, Seq()),
  AttrOneManDouble("Person", "salary",
    Fn("Double", "avg", None, Some(Gt), Some(OneDouble(50000.0))),
    Seq())
)
```

**SQL**:
```sql
SELECT DISTINCT
  Person.department,
  AVG(Person.salary)
FROM Person
GROUP BY Person.department
HAVING AVG(Person.salary) > ?;
```

**Bindings**: `[50000.0]`

**Result Type**: `List[(String, Double)]`

### Example 3: Join with Optional

**DSL**:
```scala
Person.name.Address.?(_.street.city).query.get
```

**Elements**:
```scala
List(
  AttrOneManString("Person", "name", V, Seq()),
  OptRef(
    Ref("Person", "address", "Address", ManyToOne, ...),
    List(
      AttrOneManString("Address", "street", V, Seq()),
      AttrOneManString("Address", "city", V, Seq())
    )
  )
)
```

**SQL**:
```sql
SELECT DISTINCT
  Person.name,
  Address.street,
  Address.city
FROM Person
LEFT JOIN Address ON Address.id = Person.address;
```

**Result Type**: `List[(String, Option[(String, String)])]`

### Example 4: Nested Data Insert

**DSL**:
```scala
Person.name.age.Order.*(Order.item.amount).insert(
  ("John", 42, List(("Book", 29.99), ("Pen", 2.50))),
  ("Jane", 35, List(("Laptop", 999.99)))
)
```

**Elements**:
```scala
List(
  AttrOneManString("Person", "name", V, Seq()),
  AttrOneManInt("Person", "age", V, Seq()),
  Nested(
    Ref("Person", "orders", "Order", OneToMany, ...),
    List(
      AttrOneManString("Order", "item", V, Seq()),
      AttrOneManDouble("Order", "amount", V, Seq())
    )
  )
)
```

**SQL** (multiple statements):
```sql
-- Insert parent
INSERT INTO Person (name, age) VALUES (?, ?);  -- Returns id 101

-- Insert nested (one-to-many)
INSERT INTO Order (person, item, amount) VALUES (?, ?, ?), (?, ?, ?);
-- Bindings: [101, "Book", 29.99, 101, "Pen", 2.50]

-- Insert parent
INSERT INTO Person (name, age) VALUES (?, ?);  -- Returns id 102

-- Insert nested
INSERT INTO Order (person, item, amount) VALUES (?, ?, ?);
-- Bindings: [102, "Laptop", 999.99]
```

### Example 5: Subquery

**DSL**:
```scala
Person.name.age._(?[Person.age]{ age =>
  Person.age_(age).department("Sales")
}).query.get
```

**Elements**:
```scala
List(
  AttrOneManString("Person", "name", V, Seq()),
  AttrOneManInt("Person", "age", V, Seq()),
  SubQuery(
    List(
      AttrOneTacInt("Person", "age", V, Seq(),
        filterAttr = Some((0, List("Person"), AttrOneManInt(...)))),
      AttrOneTacString("Person", "department", Eq, Seq("Sales"))
    )
  )
)
```

**SQL**:
```sql
SELECT DISTINCT
  Person.name,
  Person.age
FROM Person
WHERE
  Person.age IN (
    SELECT Person.age
    FROM Person
    WHERE Person.department = ?
  );
```

**Bindings**: `["Sales"]`

**Result Type**: `List[(String, Int)]`

## Summary

The Molecule compilation pipeline is a sophisticated multi-stage transformation process:

1. **DSL → DataModel**: Method chaining accumulates `Element` objects into a `DataModel`
2. **Validation**: Structural validation and optimizations
3. **Resolution**: Recursive traversal resolves elements to query components
4. **SQL Generation**: Components assembled into database-specific SQL
5. **Execution**: PreparedStatement execution with parameter binding
6. **Casting**: ResultSet rows cast to type-safe tuples
7. **Marshalling**: Cross-platform serialization (for JS/JVM)

This architecture provides:
- **Type Safety**: Compile-time type checking throughout
- **Database Agnosticism**: Abstract traits with database-specific implementations
- **Performance**: Prepared statements, batch operations, efficient casting
- **Flexibility**: Supports complex queries (joins, nested, optional, subqueries, aggregations)
- **Composability**: Molecules can be composed and transformed before execution
