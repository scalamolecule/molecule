# sbt-molecule Code Generation Pipeline

## Naming Conventions (STRICT - Enforced by ParseDomainStructure)

### Entity Names: `[A-Z][a-zA-Z0-9]*`
- **Pattern**: Must start with uppercase letter, followed by letters/digits only
- **Validation**: `ParseDomainStructure.formatEntityName` enforces this
- ✅ **Valid**: `Person`, `Invoice`, `UserProfile`, `Item2`
- ❌ **Invalid**: `person`, `Person_`, `_Entity`, `Entity-Name`, `2Entity`

### Segment Names: `[a-z][a-z0-9]*`
- **Pattern**: Must start with lowercase letter, followed by lowercase letters and/or digits
- **Validation**: `ParseDomainStructure.formatSegment` enforces this
- ✅ **Valid**: `accounting`, `warehouse`, `gen2`, `lit`, `socialapp1`
- ❌ **Invalid**: `Accounting`, `Ware_house`, `Gen-Person`, `_segment`, `2gen`

**Error Messages**:
- Entity validation error: `"Entity name must start with uppercase letter and contain only letters and digits"`
- Segment validation error: `"Segment name must be all lowercase letters and/or digits"`

**Location**: `/Users/mg/molecule/sbt/sbt-molecule/src/main/scala/sbtmolecule/ParseDomainStructure.scala`

---

## Overview

The sbt-molecule plugin transforms user-defined domain structures (Scala traits) into type-safe database DSL code. The pipeline consists of:

1. **Schema Parsing** - Scala meta AST parsing of trait definitions with naming validation
2. **Domain Model Creation** - Transformation into intermediate MetaDomain representation
3. **Code Generation** - Template-based generation of type-safe DSL files
4. **Schema Export** - SQL schema generation for multiple database dialects

**High-level flow:**
```
User Domain Trait → ParseDomainStructure → MetaDomain IR → GenerateSourceFiles_db → Generated DSL + SQL Schemas
```

## 1. Plugin Entry Point

**File:** `/sbt-molecule/src/main/scala/sbtmolecule/MoleculePlugin.scala`

### SBT Integration

The plugin extends `sbt.AutoPlugin` and provides three commands:

```scala
object autoImport {
  lazy val moleculeGen             = inputKey[Unit]("Generate Molecule source files.")
  lazy val moleculePackage         = taskKey[Unit]("Generate and package jars to lib/")
  lazy val moleculeMigrationStatus = taskKey[Unit]("Show migration status.")
}
```

### Generation Flow

```scala
moleculeGen → generateSources → parseAndGenerate → ParseAndGenerate.generate
```

**Key method:** `parseAndGenerate` (lines 266-296)
- Recursively scans source directories for `.scala` files
- Creates `ParseAndGenerate` instance for each file
- Generates DSL files to `target/scala-VERSION/src_managed/main/moleculeGen/`
- Generates SQL schemas to `resources/db/schema/`

**Output structure:**
```
target/scala-3.x/src_managed/main/moleculeGen/
  └── {package}/
      └── dsl/
          └── {Domain}/
              ├── Entity1.scala           # Entry point
              ├── Entity2.scala
              ├── ops/
              │   ├── Entity1_.scala      # Attribute builders
              │   ├── Entity2_.scala
              │   └── ...
              └── metadb/
                  ├── Domain_.scala       # Meta model
                  ├── Domain_h2.scala     # H2 resolver
                  ├── Domain_mysql.scala
                  └── ...

resources/db/schema/
  └── {package}/
      └── {Domain}/
          ├── Domain_h2.sql
          ├── Domain_mysql.sql
          └── ...
```

## 2. Schema Definition Format

### User Input

Users define domain structures as Scala traits extending `DomainStructure`:

**File:** `/sbt-molecule/src/main/scala/molecule/DomainStructure.scala`

```scala
trait MyDomain extends DomainStructure {

  // Simple entity (note: uppercase first letter)
  trait Person {
    val name = oneString
    val age  = oneInt
  }

  // Entity with relationships
  trait Order {
    val total    = oneDouble
    val customer = manyToOne[Person]
  }

  // Join trait for many-to-many
  trait StudentCourse extends Join {
    val student = manyToOne[Student]
    val course  = manyToOne[Course]
    // Optional join properties
    val enrollmentDate = oneDate
  }

  // Segmented structure (note: lowercase segment name)
  object accounting {
    trait Invoice {
      val amount = oneDouble
    }
  }
}
```

**Naming Rules Enforced**:
- Entity names: `Person`, `Order`, `StudentCourse` (uppercase first, no underscores)
- Segment names: `accounting`, `warehouse` (all lowercase, no underscores)
- Invalid: `person`, `Person_`, `Accounting`, `ware_house`

### Available Types

**Cardinality prefixes:**
- `one` - Single value (mandatory)
- `set` - Set of values
- `seq` - Ordered sequence
- `map` - Key-value map (String keys)

**Base types (25+ types):** String, Int, Long, Float, Double, Boolean, BigInt, BigDecimal, Date, Duration, Instant, LocalDate, LocalTime, LocalDateTime, OffsetTime, OffsetDateTime, ZonedDateTime, UUID, URI, Byte, Short, Char

**Note**: Scala 3 tuples support unlimited arity (no 22-limit). Bindings have max 22 parameters.

**Relationships:**
- `manyToOne[Entity]` - Foreign key relationship
- Automatic `oneToMany` reverse refs generated
- `Join` trait - For many-to-many join tables

**Example:**
```scala
trait Product {
  val title    = oneString.unique.index
  val price    = oneDouble
  val tags     = setString
  val features = seqString
  val metadata = mapString
  val category = manyToOne[Category]
}

// Join trait for many-to-many
trait StudentCourse extends Join {
  val student = manyToOne[Student]
  val course  = manyToOne[Course]
  // Optional join table properties
  val grade = oneString
}
```

**Join Trait Semantics**:
- Represents a join table for many-to-many relationships
- Must extend `Join` marker trait
- Must have at least two `manyToOne` references
- Can include additional attributes (properties of the relationship)
- Automatically creates bidirectional many-to-many navigation
- See: `/Users/mg/molecule/molecule/molecule/db/compliance/shared/src/main/scala/molecule/db/compliance/domains/JoinTable.scala`

### Attribute Options

Attributes support chainable options (parsed in `ParseDomainStructure.accOptions`):

```scala
val email = oneString
  .unique                              // Uniqueness constraint
  .index                               // Database index
  .mandatory                           // NOT NULL
  .alias("email-address")              // Schema alias for special chars
  .description("User's email")         // Documentation
  .dbColumnProperties(                 // Custom column types
    Db.H2 -> "VARCHAR(100)",
    Db.MySQL -> "VARCHAR(100)"
  )
```

### Validations

Attributes can include validation logic (parsed in `ParseDomainStructure.accValidations`):

```scala
trait User {
  val age = oneInt.validate(_ >= 18, "Must be 18 or older")

  val password = oneString.validate {
    case p if p.length < 8        => "Too short"
    case p if !p.matches(".*\\d.*") => "Must contain digit"
  }

  val email = oneString.email  // Built-in email validation

  val zipCode = oneString.regex("\\d{5}", "Invalid zip code")
}
```

### Enums

Scala 3 enums are parsed and converted to string attributes:

```scala
enum Status:
  case Pending, Approved, Rejected

trait Order {
  val status = oneEnum[Status]
}
```

## 3. Schema Parsing

**File:** `/sbt-molecule/src/main/scala/sbtmolecule/ParseDomainStructure.scala`

### Parsing Strategy

Uses **Scalameta** library for AST manipulation:

```scala
case class ParseDomainStructure(
  filePath: String,
  pkg: String,
  domain: String,
  body: Seq[Stat]  // AST nodes
)
```

### Key Parsing Methods

**Entity extraction** (`getEntities`, lines 256-307):
```scala
entities.flatMap {
  case Defn.Trait.After_4_6_0(_, entityTpe, _, _, template@Template.After_4_4_0(_, _, _, stats, _)) =>
    parseEntity(entityTpe, template, stats, isJoinTable)
  case Defn.Enum.After_4_6_0(_, name, _, _, templ) =>
    parseEnum[MetaEntity](name, templ)
}
```

**Attribute extraction** (`getAttrs`, lines 378-401):
```scala
attrs.flatMap {
  case q"val $attr = $defs" =>
    val attrStr = attr.toString
    Some(acc(segmentPrefix, entity, defs, MetaAttribute(attrStr, OneValue, ""), isJoinTable))
}
```

### Chained Pattern Matching

To avoid method size limits, attribute parsing uses cascading pattern matching:

```scala
acc() → accOptions() → accMigration() → accRelationships() →
       accEnums() → accAttributesOne() → accAttributesSet() →
       accAttributesSeq() → accAttributesMap() → accValidations() →
       accAccessControl()
```

**Example - oneString attribute:**
```scala
// Input: val name = oneString.unique.index
// Pattern matching cascade:
case q"$prev.index"  => acc(..., a.copy(options = a.options :+ "index"))
case q"$prev.unique" => acc(..., a.copy(options = a.options :+ "unique"))
case q"oneString"    => a.copy(value = OneValue, baseTpe = "String")
```

### Relationship Detection

**ManyToOne parsing** (lines 516-554):
```scala
case q"manyToOne[$ref0]" =>
  val ref = formatEntityName(ref0)
  addBackRef(segmentPrefix, entity, ref)
  val reverseRef = fullEntity(segmentPrefix, English.plural(entity))
  // Creates reverse oneToMany automatically
  addReverseRef(fullRef, reverseMetaAttr)
  a.copy(value = OneValue, baseTpe = "ID", ref = Some(fullRef), ...)
```

**Reverse ref creation:**
```scala
// Person.orders = manyToOne[Order]
// Automatically creates: Order.persons = oneToMany[Person]

val reverseMetaAttr = MetaAttribute(
  reverseRef,      // "persons" (pluralized)
  SetValue,        // oneToMany returns Set
  "ID",
  ref = Some(entity),
  relationship = Some(OneToMany)
)
```

## 4. Domain Model IR (Intermediate Representation)

**File:** `/sbt-molecule/src/main/scala/molecule/base/metaModel/MetaModel.scala`

### MetaDomain Structure

```scala
case class MetaDomain(
  pkg: String,                    // Package name
  domain: String,                 // Domain name
  segments: List[MetaSegment],    // Top-level segments
  roles: List[MetaRole] = Nil,    // Access control roles
  generalDbColumnProps: Map[String, Map[String, String]] = Map.empty,
  migrationVersion: Option[Int] = None,
  migrationStatus: MigrationStatus = MigrationStatus.Clean
)

case class MetaSegment(
  segment: String,                      // "" for non-segmented
  entities: List[MetaEntity],
  migration: Option[SegmentMigration] = None
)

case class MetaEntity(
  entity: String,                       // Entity name
  attributes: List[MetaAttribute],      // All attributes including generated
  backRefs: List[String] = Nil,         // Entities referencing this one
  mandatoryAttrs: List[String] = Nil,   // Required attributes
  mandatoryRefs: List[(String, String)] = Nil,  // Required relationships
  isJoinTable: Boolean = false,
  description: Option[String] = None,
  // Access control
  entityRoles: List[String] = Nil,
  entityActions: List[String] = Nil,
  entityUpdatingGrants: List[String] = Nil,
  entityDeletingGrants: List[String] = Nil,
  migration: Option[EntityMigration] = None
)

case class MetaAttribute(
  attribute: String,                    // Attribute name
  value: Value,                         // OneValue | SetValue | SeqValue | MapValue
  baseTpe: String,                      // Base Scala type
  arguments: List[MetaArgument] = Nil,
  ref: Option[String] = None,           // Referenced entity (for relationships)
  reverseRef: Option[String] = None,    // Reverse reference name
  relationship: Option[Relationship] = None,  // ManyToOne | OneToMany | ManyToMany
  enumTpe: Option[String] = None,
  options: List[String] = Nil,          // "unique", "index", "mandatory"
  alias: Option[String] = None,
  requiredAttrs: List[String] = Nil,    // Co-dependent attributes
  valueAttrs: List[String] = Nil,       // Attributes used in validations
  validations: List[(String, String)] = Nil,  // (test, errorMsg)
  description: Option[String] = None,
  // Access control
  onlyRoles: List[String] = Nil,
  excludedRoles: List[String] = Nil,
  attrUpdatingGrants: List[String] = Nil,
  // Custom DB column properties
  dbColumnProps: Map[String, String] = Map.empty,  // db -> columnDef
  // Migration
  migration: Option[AttrMigration] = None,
  sourcePosition: Option[(Int, Int)] = None
)
```

### Value Markers

```scala
sealed trait Value {
  def _marker: String  // Used in code generation
}
case object OneValue extends Value { def _marker = "One" }
case object SetValue extends Value { def _marker = "Set" }
case object SeqValue extends Value { def _marker = "Seq" }
case object MapValue extends Value { def _marker = "Map" }
```

### Relationship Types

```scala
sealed trait Relationship
case object ManyToOne  extends Relationship
case object OneToMany  extends Relationship
case object ManyToMany extends Relationship
```

## 5. Code Generation Pipeline

**File:** `/sbt-molecule/src/main/scala/sbtmolecule/GenerateSourceFiles_db.scala`

### Main Generation Method

```scala
def generate(srcManaged: File, schemaDir: File): Unit = {
  var entityIndex = 0
  var attrIndex = 0

  // Generate enum files
  segments.filter(_.segment == "_enums").foreach { enumSegment =>
    enumSegment.entities.foreach { enumEntity =>
      val enumCode = s"""enum ${enumEntity.entity}:
                         |  case ${attributes.map(_.attribute).mkString(", ")}"""
      IO.write(domainDir / s"${enumEntity.entity}.scala", enumCode)
    }
  }

  // Generate entity files
  for {
    metaSegment <- segments
    metaEntity <- metaSegment.entities
  } yield {
    val entity  = Entity(metaDomain, metaEntity).get
    val entity_ = Entity_(metaDomain, metaEntity, entityIndex, attrIndex).get

    IO.write(domainDir / s"$ent.scala", entity)
    IO.write(domainDir / "ops" / s"${ent}_.scala", entity_)

    entityIndex += 1
    attrIndex += metaEntity.attributes.length
  }

  // Generate metadata and resolvers
  IO.write(metadb / s"${domain}_.scala", MetaDb_(metaDomain).getMeta)
  IO.write(metadb / s"${domain}_h2.scala", Db_H2(metaDomain).get)
  // ... other dialects

  // Generate SQL schemas
  IO.write(schemaDir / s"${domain}_h2.sql", Db_H2(metaDomain).getSQL)
  // ... other dialects
}
```

## 6. Entity DSL Generation

**File:** `/sbt-molecule/src/main/scala/sbtmolecule/db/dsl/Entity.scala`

### Entry Point Generation

For entity `Person`, generates:

```scala
object Person extends ops.Person_0(_DataModel(Nil, firstEntityIndex = 0)) {
  // ID-based lookup
  final def apply(id: Long, ids: Long*) = new ops.Person_0(
    _DataModel(List(_AttrOneTacID("Person", "id", _Eq, id +: ids, coord = List(0, 0))),
      firstEntityIndex = 0)
  )
  final def apply(ids: Iterable[Long]) = new ops.Person_0(
    _DataModel(List(_AttrOneTacID("Person", "id", _Eq, ids.toSeq, coord = List(0, 0))),
      firstEntityIndex = 0)
  )
}
```

**Usage:**
```scala
Person.name("John")           // Start query with name filter
Person(1L, 2L, 3L).name.query // Get names for specific IDs
```

## 7. Attribute Builder Generation

**File:** `/sbt-molecule/src/main/scala/sbtmolecule/db/dsl/ops/Entity_Builder.scala`

### Three Arities Generated

For each entity, generates three builder classes based on arity (number of attributes already selected):

1. **Arity 0** (`Person_0`) - No attributes selected yet
2. **Arity 1** (`Person_1[T]`) - One attribute selected, type T
3. **Arity N** (`Person_n[Tpl]`) - Multiple attributes, tuple type Tpl

**Example for Person entity:**

```scala
// Arity 0 - Entry point
class Person_0(override val dataModel: DataModel)
  extends Molecule_0(dataModel)
  with Person_attrs
{
  final lazy val name   = Person_1_ExprOneMan_String[String](dataModel.add(name_man))
  final lazy val name_? = Person_1_ExprOneOpt[String](dataModel.add(name_opt))
  final lazy val name_  = Person_0_ExprOneTac_String[String](dataModel.add(name_tac))

  final lazy val age   = Person_1_ExprOneMan_Integer[Int](dataModel.add(age_man))
  final lazy val age_? = Person_1_ExprOneOpt[Int](dataModel.add(age_opt))
  final lazy val age_  = Person_0_ExprOneTac_Integer[Int](dataModel.add(age_tac))

  // References
  object Orders extends Order_0(dataModel.add(_dm.Ref("Person", "orders", "Order", ...)))
}

// Arity 1 - One attribute selected
class Person_1[T](override val dataModel: DataModel)
  extends Molecule_1[T](dataModel)
  with Person_attrs
{
  final lazy val name   = Person_n_ExprOneMan_String[String, (T, String)](dataModel.add(name_man))
  final lazy val name_? = Person_n_ExprOneOpt[String, (T, Option[String])](dataModel.add(name_opt))
  final lazy val name_  = Person_1_ExprOneTac_String[T, String](dataModel.add(name_tac))

  // ... age, references ...
}

// Arity N - Multiple attributes
class Person_n[Tpl <: Tuple](override val dataModel: DataModel)
  extends Molecule_n[Tpl](dataModel)
  with Person_attrs
{
  final lazy val name   = Person_n_ExprOneMan_String[String, Tpl :* String](dataModel.add(name_man))
  final lazy val name_? = Person_n_ExprOneOpt[String, Tpl :* Option[String]](dataModel.add(name_opt))
  final lazy val name_  = Person_n_ExprOneTac_String[String, Tpl](dataModel.add(name_tac))

  // ... age, references ...
}
```

### Attribute Name Conventions

Three variants per attribute (lines 122-125):

```scala
man += s"final lazy val $cleanAttr    = ${entB}_$exprM($elemsM)"  // name   - mandatory
opt += s"final lazy val ${cleanAttr}_? = ${entB}_$exprO($elemsO)"  // name_? - optional
tac += s"final lazy val ${cleanAttr}_  = ${entA}_$exprT($elemsT)"  // name_  - tacit
```

**Usage patterns:**
```scala
Person.name("John")      // Mandatory - filter by name AND return name
Person.name_?            // Optional - return Option[String]
Person.name_("John")     // Tacit - filter by name but DON'T return it
```

### Type Safety Through Expression Classes

The `ExprXxxMan/Opt/Tac` classes provide type-specific operators:

**Generated in:** `/sbt-molecule/src/main/scala/sbtmolecule/db/dsl/ops/Entity_Exprs.scala`

**Integer attributes get:**
```scala
class Person_1_ExprOneMan_Integer[T](override val dataModel: DataModel)
  extends Person_1[T](dataModel)
    with ExprOneMan_1_Integer[T, ...]
```

From `ExprOneMan_1_Integer` (in core library):
```scala
trait ExprOneMan_1_Integer[T, Next] {
  def apply(int: Int): Next                    // Exact match
  def apply(ints: Int*): Next                  // OR multiple
  def >(int: Int): Next                        // Greater than
  def >=(int: Int): Next                       // Greater or equal
  def <(int: Int): Next                        // Less than
  def <=(int: Int): Next                       // Less or equal
  def between(lower: Int, upper: Int): Next    // Range
}
```

**String attributes get:**
```scala
class Person_1_ExprOneMan_String[T](override val dataModel: DataModel)
  extends Person_1[T](dataModel)
    with ExprOneMan_1_String[T, ...]
```

From `ExprOneMan_1_String`:
```scala
trait ExprOneMan_1_String[T, Next] {
  def apply(string: String): Next              // Exact match
  def apply(strings: String*): Next            // OR multiple
  def contains(substring: String): Next        // LIKE %sub%
  def startsWith(prefix: String): Next         // LIKE pre%
  def endsWith(suffix: String): Next           // LIKE %fix
  def matches(regex: String): Next             // Regex match
}
```

**This enables type-safe queries:**
```scala
Person.age(42)            // Valid: Int parameter
Person.age > 18           // Valid: comparison
Person.age.contains("x")  // COMPILE ERROR: contains not on Int

Person.name("John")           // Valid: String parameter
Person.name.startsWith("Jo")  // Valid: String operation
Person.name > 5               // COMPILE ERROR: comparison not on String
```

## 8. Attribute Metadata Generation

**File:** `/sbt-molecule/src/main/scala/sbtmolecule/db/dsl/ops/Entity_Attrs.scala`

Generates internal attribute definitions used by builders:

```scala
trait Person_attrs {
  // Mandatory variants
  protected def name_man = AttrOneManString("Person", "name", coord = List(0, 1))
  protected def age_man  = AttrOneManInt   ("Person", "age",  coord = List(0, 2))

  // Optional variants (skips id)
  protected def name_opt = AttrOneOptString("Person", "name", coord = List(0, 1))
  protected def age_opt  = AttrOneOptInt   ("Person", "age",  coord = List(0, 2))

  // Tacit variants
  protected def name_tac = AttrOneTacString("Person", "name", coord = List(0, 1))
  protected def age_tac  = AttrOneTacInt   ("Person", "age",  coord = List(0, 2))

  // Validations (if any)
  protected def validation_age(v: Int): Seq[String] = {
    if (v >= 18) Nil
    else Seq("Must be 18 or older")
  }
}
```

### Coordinate System

The `coord` parameter uniquely identifies attributes:

```scala
coord = List(entityIndex, attrIndex)            // For regular attributes
coord = List(entityIndex, attrIndex, refIndex)  // For relationships
```

**Used for:**
- Query optimization
- Column resolution in SQL generation
- Relationship traversal

## 9. Validation Code Generation

**File:** `/sbt-molecule/src/main/scala/sbtmolecule/db/dsl/ops/Entity_Validations.scala`

Extracts validation logic from user code and generates validation methods:

**Input:**
```scala
trait User {
  val age = oneInt.validate(_ >= 18, "Must be 18 or older")
  val password = oneString.validate {
    case p if p.length < 8 => "Too short"
    case p if !p.matches(".*\\d.*") => "Must contain digit"
  }
}
```

**Generated:**
```scala
protected def validation_age(v: Int): Seq[String] = {
  if (v >= 18) Nil
  else Seq("Must be 18 or older")
}

protected def validation_password(v: String): Seq[String] = {
  v match {
    case p if p.length < 8 => Seq("Too short")
    case p if !p.matches(".*\\d.*") => Seq("Must contain digit")
    case _ => Nil
  }
}
```

**Integrated into attribute definitions:**
```scala
protected def age_man = AttrOneManInt(
  "User", "age",
  coord = List(0, 1),
  validator = Some(validation_age)
)
```

## 10. SQL Schema Generation

**Files:** `/sbt-molecule/src/main/scala/sbtmolecule/db/resolvers/Db_*.scala`

### Multi-Dialect Support

For each domain, generates SQL schemas for 5 databases:
- H2 (in-memory/file)
- PostgreSQL
- MySQL
- MariaDB
- SQLite

**Base class:** `SqlBase` provides shared logic
**Dialect classes:** Override specific SQL syntax

**Example - H2 resolver:**
```scala
case class Db_H2(metaDomain: MetaDomain) extends SqlBase(metaDomain, H2) {

  def get: String = {
    // Generate Scala resolver code
    s"""object ${domain}_h2 extends Resolver_h2 {
       |  override def schema: String = ${domain}_h2_Schema
       |  override val metaDomain: MetaDomain = ${metaDomain.render()}
       |}"""
  }

  def getSQL: String = {
    // Generate SQL schema
    entities.map { entity =>
      val tableDef = s"""CREATE TABLE ${entity.entity} (
                        |  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        |  ${generateColumns(entity)}
                        |);"""
      tableDef
    }.mkString("\n\n")
  }
}
```

### Column Type Mapping

Each attribute type maps to database-specific column types:

**Example mappings in H2:**
```scala
oneString    -> "LONGVARCHAR"           // or custom via .dbColumnProperties
oneInt       -> "INT"
oneLong      -> "BIGINT"
oneDouble    -> "DOUBLE"
oneBoolean   -> "BOOLEAN"
oneUUID      -> "UUID"
oneDate      -> "TIMESTAMP"
setString    -> "ARRAY"                 // H2 native arrays
seqString    -> "ARRAY"
mapString    -> "OTHER" (JSON)
```

**Example mappings in PostgreSQL:**
```scala
oneString    -> "TEXT"
oneInt       -> "INTEGER"
setString    -> "TEXT[]"                // PostgreSQL arrays
mapString    -> "JSONB"                 // Native JSON support
```

### Custom Column Properties

Users can override defaults:

```scala
trait Product {
  val shortCode = oneString.dbColumnProperties(
    Db.H2 -> "VARCHAR(10)",
    Db.PostgreSQL -> "VARCHAR(10)"
  )

  val price = oneDouble.dbColumnProperties(
    Db.H2 -> "DECIMAL(10,2)",
    Db.MySQL -> "DECIMAL(10,2)"
  )
}
```

**Generated SQL (H2):**
```sql
CREATE TABLE Product (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  shortCode VARCHAR(10),
  price DECIMAL(10,2)
);
```

### Relationship Handling

**ManyToOne relationships:**
```scala
trait Order {
  val customer = manyToOne[Customer]
}
```

**Generated SQL:**
```sql
CREATE TABLE Order (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  customer BIGINT,
  FOREIGN KEY (customer) REFERENCES Customer(id)
);
```

**OneToMany (reverse refs):** No column generated - navigated via foreign key

**ManyToMany (join tables):**
```scala
trait Student extends Join {  // Join table
  val student = manyToOne[Student]
  val course  = manyToOne[Course]
}
```

**Generated SQL:**
```sql
CREATE TABLE Student_ (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  student BIGINT,
  course BIGINT,
  FOREIGN KEY (student) REFERENCES Student(id),
  FOREIGN KEY (course) REFERENCES Course(id)
);
```

## 11. Key Algorithms

### Relationship Detection and Graph Building

**Back references** (lines 209-216 in ParseDomainStructure):

When parsing `Order.customer = manyToOne[Customer]`:

1. Record back reference: `Customer -> Order`
2. Calculate reverse name: `English.plural("Order")` = "orders"
3. Create reverse attribute: `Customer.orders = oneToMany[Order]`

```scala
private def addBackRef(segmentPrefix: String, backRefEntity: String, entity: String): Unit = {
  val fullEntityName = fullEntity(segmentPrefix, entity)
  val fullBackRefEntity = fullEntity(segmentPrefix, backRefEntity)
  val curBackRefEntities = backRefs.getOrElse(fullEntityName, Nil)
  backRefs = backRefs + (fullEntityName -> (curBackRefEntities :+ fullBackRefEntity))
}

private def addReverseRef(reverseEntity: String, reverseRef: MetaAttribute): Unit = {
  val curReverseEntities = reverseRefs.getOrElse(reverseEntity, Nil)
  reverseRefs = reverseRefs + (reverseEntity -> (curReverseEntities :+ reverseRef))
}
```

**Many-to-many bridge detection** (lines 229-254):

For join table `Student_` with refs to `Student` and `Course`:

1. Detect join table: trait extends `Join` marker
2. Record bridge: `Student <-> Student_ <-> Course`
3. Generate shortcuts: `Student.courses` and `Course.students`

```scala
private def addManyToManyBridges(joinTable: String, refAttr: String, ref: String, reverseRef: String): Unit = {
  val curJoinTables = ent2joinTables.getOrElse(joinTable, Nil)
  ent2joinTables = ent2joinTables + (ref -> (curJoinTables :+ joinTable))
  val curRefs = joinTable2refs.getOrElse(joinTable, Nil)
  joinTable2refs = joinTable2refs + (joinTable -> (curRefs :+ (refAttr, ref, reverseRef)))
}
```

### Cardinality Inference

Determined by attribute definition prefix:

```scala
// Input parsing (lines 570-730)
case q"oneString"    => a.copy(value = OneValue, baseTpe = "String")
case q"setString"    => a.copy(value = SetValue, baseTpe = "String")
case q"seqString"    => a.copy(value = SeqValue, baseTpe = "String")
case q"mapString"    => a.copy(value = MapValue, baseTpe = "String")
case q"manyToOne[$ref]" => a.copy(value = OneValue, baseTpe = "ID", ref = Some(ref))
```

**Cardinality affects:**
1. **Database storage:** OneValue = single column, SetValue = array/junction table
2. **Return types:** `name: String` vs `tags: Set[String]`
3. **Expression operators:** `.apply(v)` vs `.has(v)` vs `.contains(v)`

### Type Coordination

The generated code maintains type safety through a multi-level type system:

**Level 1: Value marker** (OneValue, SetValue, etc.)
- Controls storage format
- Determines available expressions

**Level 2: Base type** (String, Int, etc.)
- Controls SQL column type
- Determines type-specific operators

**Level 3: Arity tracking** (0, 1, N)
- Controls tuple building
- Determines return types

**Level 4: Expression class mixing**
- Combines value + baseTpe to provide correct operators
- `ExprOneMan_Integer` vs `ExprSetMan_String` vs `ExprMapMan_Double`

**Example type flow:**
```scala
Person                              // Person_0 (no attrs)
  .age > 18                         // Person_0_ExprOneTac_Integer (tacit Int filter)
  .name                             // Person_1_ExprOneMan_String (return String)
  .tags.has("scala")                // Person_n_ExprSetMan_String (return (String, Set[String]))
  .query.get                        // List[(String, Set[String])]
```

### Circular Dependency Detection

**Mandatory references** create dependencies that must be acyclic:

```scala
// INVALID - circular mandatory refs
trait A {
  val b = manyToOne[B].mandatory
}
trait B {
  val a = manyToOne[A].mandatory
}
```

**Detection algorithm** (lines 169-203):

```scala
def checkCircularMandatoryRefs(segments: Seq[MetaSegment]): Unit = {
  val mappings: Map[String, List[(String, String)]] =
    // Build graph: entity -> List[(attr, refEntity)]

  def check(prevEntities: List[String], graph: List[String], entity: String): Unit = {
    mappings.get(entity).foreach { refs =>
      refs.foreach {
        case (refAttr, refEnt) if prevEntities.contains(refEnt) =>
          // Cycle detected!
          err(s"Circular mandatory references: ${graph.mkString(" --> ")}")
        case (refAttr, refEnt) =>
          // Continue traversal
          check(prevEntities :+ refEnt, graph :+ refAttr, refEnt)
      }
    }
  }

  // Check all mandatory refs
  mappings.foreach { case (entity, refs) =>
    refs.foreach { case (refAttr, refEnt) =>
      check(List(entity), List(refAttr), refEnt)
    }
  }
}
```

## 12. Migration Support

**File:** `/sbt-molecule/src/main/scala/sbtmolecule/migration/`

The plugin supports Flyway-based schema migrations with ambiguity resolution.

### Migration Markers

**Attribute-level:**
```scala
val oldName = oneString.rename("newName")  // Rename column
val deprecated = oneString.remove          // Drop column
```

**Entity-level:**
```scala
trait OldEntity extends Rename("NewEntity")  // Rename table
trait DeprecatedEntity extends Remove        // Drop table
```

### Migration Detection

When running `moleculeGen`:

1. Load previous structure from `resources/db/migration/{domain}/{domain}_previous.scala`
2. Parse current structure
3. Compare and detect changes:
   - Added/removed entities
   - Added/removed attributes
   - Renamed entities/attributes (via markers)
   - Type changes (ambiguous!)

**Ambiguous changes** (require user input):
- Attribute renamed without `.rename()` marker
- Attribute type changed
- Multiple attributes added/removed simultaneously

### Ambiguity Resolution

When ambiguity detected, generates scaffolding file:

**Example:** `MyDomain_migration.scala`
```scala
trait MyDomain_migration extends DomainStructure {
  trait Person {
    val oldName = oneString.rename("newName")  // User fills in
    val removed = oneInt.remove                // User marks for removal
  }
}
```

**Process:**
1. User fills in migration markers
2. Run `moleculeGen` again
3. Plugin generates Flyway migration SQL
4. Removes markers from source
5. Saves current as new baseline

### Generated Flyway Migrations

**Output:** `resources/db/migration/{domain}/{dialect}/V{N}__{description}.sql`

**Example:** `V2__rename_person_name.sql`
```sql
-- Auto-generated by Molecule
ALTER TABLE Person RENAME COLUMN oldName TO newName;
```

## 13. Code Flow Diagram

```
┌─────────────────────────────────────────────────────────────────────────┐
│  SBT Build Process                                                        │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
                    ┌───────────────────────────────┐
                    │   MoleculePlugin.scala        │
                    │   moleculeGen task            │
                    └───────────────────────────────┘
                                    │
                    ┌───────────────┴────────────────┐
                    │                                 │
                    ▼                                 ▼
      ┌─────────────────────────┐      ┌─────────────────────────┐
      │ Scan source directories │      │ Check for migrations    │
      │ Find *.scala files      │      │ Load previous structure │
      └─────────────────────────┘      └─────────────────────────┘
                    │                                 │
                    └───────────────┬─────────────────┘
                                    │
                                    ▼
                    ┌───────────────────────────────┐
                    │   ParseAndGenerate.scala      │
                    │   create instance per file    │
                    └───────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│  Schema Parsing Phase                                                    │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
                    ┌───────────────────────────────┐
                    │   ParseAndGenerate.extract    │
                    │   Scalameta AST parsing       │
                    └───────────────────────────────┘
                                    │
                    ┌───────────────┴────────────────┐
                    │                                 │
                    ▼                                 ▼
      ┌──────────────────────────┐      ┌──────────────────────────┐
      │ Extract package & domain │      │ Extract trait body       │
      │ q"trait D extends DS"    │      │ body: Seq[Stat]          │
      └──────────────────────────┘      └──────────────────────────┘
                                                      │
                                                      ▼
                                  ┌───────────────────────────────┐
                                  │  ParseDomainStructure.scala   │
                                  │  Traverse AST to build IR     │
                                  └───────────────────────────────┘
                                                      │
          ┌───────────────────────────────────────────┼──────────────────┐
          │                                           │                  │
          ▼                                           ▼                  ▼
┌─────────────────────┐               ┌─────────────────────┐  ┌──────────────┐
│ Parse segments      │               │ Parse entities      │  │ Parse roles  │
│ object { traits }   │               │ trait E { attrs }   │  │ trait R {...}│
└─────────────────────┘               └─────────────────────┘  └──────────────┘
                                                      │
                                                      ▼
                                      ┌───────────────────────────┐
                                      │ Parse attributes          │
                                      │ val a = oneString...      │
                                      └───────────────────────────┘
                                                      │
                      ┌───────────────────────────────┼───────────────────────┐
                      │                               │                       │
                      ▼                               ▼                       ▼
          ┌────────────────────┐        ┌──────────────────────┐  ┌─────────────────┐
          │ Parse base type    │        │ Parse options        │  │ Parse relations │
          │ oneString -> (One, │        │ .unique, .index, ... │  │ manyToOne[E]    │
          │ String)            │        └──────────────────────┘  └─────────────────┘
          └────────────────────┘                    │
                      │                             ▼
                      │               ┌──────────────────────┐
                      └──────────────>│ Parse validations    │
                                      │ .validate { ... }    │
                                      └──────────────────────┘
                                                      │
                                                      ▼
┌─────────────────────────────────────────────────────────────────────────┐
│  Domain Model IR Creation                                                │
└─────────────────────────────────────────────────────────────────────────┘
                                                      │
                                                      ▼
                                  ┌───────────────────────────────┐
                                  │ Build MetaDomain              │
                                  │ - MetaSegments                │
                                  │   - MetaEntities              │
                                  │     - MetaAttributes          │
                                  │ - MetaRoles                   │
                                  └───────────────────────────────┘
                                                      │
          ┌───────────────────────────────────────────┼──────────────────────┐
          │                                           │                      │
          ▼                                           ▼                      ▼
┌──────────────────────┐              ┌───────────────────────┐  ┌──────────────────┐
│ Add back references  │              │ Add reverse refs      │  │ Add many-to-many │
│ Order -> Customer    │              │ Customer.orders       │  │ bridge shortcuts │
└──────────────────────┘              └───────────────────────┘  └──────────────────┘
                                                      │
                                                      ▼
                                  ┌───────────────────────────────┐
                                  │ Validate structure            │
                                  │ - No circular mandatory refs  │
                                  │ - Access control rules valid  │
                                  │ - Required attrs present      │
                                  └───────────────────────────────┘
                                                      │
                                                      ▼
┌─────────────────────────────────────────────────────────────────────────┐
│  Code Generation Phase                                                   │
└─────────────────────────────────────────────────────────────────────────┘
                                                      │
                                                      ▼
                                  ┌───────────────────────────────┐
                                  │ GenerateSourceFiles_db.scala  │
                                  │ Main generation orchestrator  │
                                  └───────────────────────────────┘
                                                      │
          ┌───────────────────────┬───────────────────┴───────────────────┬──────────┐
          │                       │                                       │          │
          ▼                       ▼                                       ▼          ▼
┌──────────────────┐  ┌─────────────────────┐               ┌─────────────────────────┐
│ Generate enums   │  │ For each entity:    │               │ Generate metadata       │
│ enum Status:...  │  │                     │               │ - MetaDb_.scala         │
└──────────────────┘  │ 1. Entity.scala     │               │ - Domain_h2.scala       │
                      │    Entry point      │               │ - Domain_mysql.scala    │
                      │                     │               │ - ...                   │
                      │ 2. ops/Entity_.scala│               └─────────────────────────┘
                      │    Builders/attrs   │                           │
                      └─────────────────────┘                           ▼
                                  │                       ┌─────────────────────────┐
                                  │                       │ Generate SQL schemas    │
                                  ▼                       │ - Domain_h2.sql         │
                      ┌─────────────────────┐             │ - Domain_mysql.sql      │
                      │ Entity.scala        │             │ - ...                   │
                      │ (Entry point)       │             └─────────────────────────┘
                      └─────────────────────┘
                                  │
        ┌─────────────────────────┴─────────────────────────┐
        │                                                     │
        ▼                                                     ▼
┌──────────────────────┐                      ┌──────────────────────┐
│ Generate object E    │                      │ Optional: trait E    │
│ - Entry methods      │                      │ For right-side refs  │
│ - apply(id, ids...)  │                      │ Entity.?(opt...)     │
└──────────────────────┘                      └──────────────────────┘
                                  │
                                  ▼
                      ┌─────────────────────────────────┐
                      │ ops/Entity_.scala               │
                      │ (Builders + Attributes)         │
                      └─────────────────────────────────┘
                                  │
        ┌─────────────────────────┼─────────────────────────┬──────────────┐
        │                         │                         │              │
        ▼                         ▼                         ▼              ▼
┌──────────────┐    ┌──────────────────┐    ┌──────────────────┐  ┌────────────┐
│ Entity_0     │    │ Entity_1[T]      │    │ Entity_n[Tpl]    │  │ Entity_    │
│ Arity 0      │    │ Arity 1          │    │ Arity N          │  │ attrs      │
│ No attrs yet │    │ One attr         │    │ Multiple attrs   │  │            │
└──────────────┘    └──────────────────┘    └──────────────────┘  └────────────┘
        │                    │                         │                   │
        └────────────────────┴─────────────────────────┴───────────────────┘
                                  │
                    ┌─────────────┴──────────────┐
                    │                             │
                    ▼                             ▼
      ┌──────────────────────────┐   ┌──────────────────────────┐
      │ Generate attr accessors  │   │ Generate ref accessors   │
      │ - name   (mandatory)     │   │ - object Customer        │
      │ - name_? (optional)      │   │   extends Customer_X     │
      │ - name_  (tacit)         │   │ - object _Order          │
      └──────────────────────────┘   │   (back ref)             │
                    │                 └──────────────────────────┘
                    ▼
      ┌──────────────────────────┐
      │ Generate expression      │
      │ classes                  │
      │ - Entity_ExprOneMan      │
      │ - Entity_ExprSetMan      │
      │ - Entity_ExprOneTac_Int  │
      │ - Entity_Sort            │
      └──────────────────────────┘
                    │
                    ▼
      ┌──────────────────────────┐
      │ Generate validations     │
      │ - validation_age(v)      │
      │ - validation_email(v)    │
      └──────────────────────────┘
                    │
                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│  Output Files Written                                                    │
└─────────────────────────────────────────────────────────────────────────┘
                                  │
        ┌─────────────────────────┼─────────────────────────┐
        │                         │                         │
        ▼                         ▼                         ▼
┌──────────────────┐  ┌──────────────────────┐  ┌──────────────────────┐
│ DSL Files        │  │ Metadata Files       │  │ SQL Schema Files     │
│ Entity.scala     │  │ metadb/Domain_.scala │  │ schema/Domain_h2.sql │
│ ops/Entity_.scala│  │ metadb/Domain_h2...  │  │ schema/Domain_pg.sql │
│ Enum.scala       │  └──────────────────────┘  │ ...                  │
└──────────────────┘                            └──────────────────────┘
        │
        ▼
┌──────────────────────────────────────────────────────────────────────┐
│  Scala Compilation                                                   │
│  Generated files are compiled with project sources                   │
└──────────────────────────────────────────────────────────────────────┘
        │
        ▼
┌──────────────────────────────────────────────────────────────────────┐
│  Type-Safe API Ready                                                 │
│  Person.name("John").age > 18.query.get                              │
└──────────────────────────────────────────────────────────────────────┘
```

## 14. Extension Points

### Custom Database Dialects

To add a new database, implement:

1. **Dialect definition** extending `sbtmolecule.db.sqlDialect.Dialect`
2. **Resolver** extending `SqlBase`
3. **Type mappings** for attribute types → column types
4. **SQL syntax** for CREATE TABLE, ALTER TABLE, etc.

**Example structure:**
```scala
// New dialect
object CockroachDB extends Dialect {
  def columnType(attr: MetaAttribute): String = attr.baseTpe match {
    case "String" => "STRING"
    case "Int" => "INT4"
    // ... mappings
  }
}

// New resolver
case class Db_CockroachDB(metaDomain: MetaDomain)
  extends SqlBase(metaDomain, CockroachDB) {

  override def getSQL: String = {
    // Generate CockroachDB-specific SQL
  }
}
```

### Custom Attribute Types

The system is closed for attribute types (requires core library changes), but users can:

1. **Use enums** for predefined value sets
2. **Use mapString** with JSON for complex types
3. **Add custom validations** for any type

### Custom Code Generators

The generation is controlled by case classes that can be replaced:

- `Entity` - Entry point generation
- `Entity_Builder` - Attribute accessor generation
- `Entity_Attrs` - Metadata generation
- `Entity_Exprs` - Expression class generation
- `Entity_Validations` - Validation method generation

Users could fork and modify these generators for project-specific needs.

## 15. Performance Considerations

### Incremental Compilation

- Only regenerates changed domains
- Each domain is independent
- SBT caching applies to generated sources

### Generated Code Size

For a domain with:
- 10 entities
- 5 attributes per entity
- 3 arities (0, 1, N)

**Generates approximately:**
- 10 Entity.scala files (~50 lines each) = 500 lines
- 10 ops/Entity_.scala files (~1500 lines each) = 15,000 lines
- 1 metadb file (~1000 lines) = 1,000 lines
- 5 resolver files (~500 lines each) = 2,500 lines
- **Total: ~19,000 lines of generated code**

**But:** This generates once and is cached. Query execution has zero reflection overhead.

### Type-Safety vs Runtime Cost

The extensive type-level programming creates large method counts but:

**Benefits:**
- Zero runtime reflection
- All validation at compile time
- Optimal SQL generation (no interpretation overhead)
- IDE autocomplete fully functional

**Costs:**
- Longer initial compilation
- Larger jar size
- More methods to JIT

For typical applications, the benefits far outweigh costs.

---

## Summary

The sbt-molecule code generation pipeline:

1. **Parses** user-defined Scala traits using Scalameta
2. **Transforms** into a rich intermediate representation (MetaDomain)
3. **Generates** type-safe DSL code with arity tracking and expression typing
4. **Exports** SQL schemas for multiple database dialects
5. **Supports** migrations with ambiguity resolution

The result is a compile-time verified, type-safe database API where invalid queries cannot be written, and the compiler guides the developer toward correct usage through precise type signatures and IDE autocomplete.
