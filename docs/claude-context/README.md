# Molecule Context Index

**Created**: 2026-01-04
**Updated**: 2026-01-04
**Purpose**: Comprehensive documentation of the Molecule project for AI assistant context
**Audience**: Claude Code AI assistant (optimized for fast retrieval and understanding)

---

## Important: Naming Conventions

**Entity Names**: `[A-Z][a-zA-Z0-9]*` - Must start with uppercase letter
**Segment Names**: `[a-z][a-z0-9]*` - Must start with lowercase letter

Enforced by `ParseDomainStructure` in sbt-molecule plugin.

See detailed naming rules and Join trait semantics in:
- `00-quick-reference.md` - Quick syntax reference
- `compliance-test-taxonomy.md` - Relationship patterns including many-to-many
- `sbt-molecule-codegen.md` - Validation details

---

## Why This Exists

These documents enable the AI assistant to:
1. **Understand the complete Molecule feature set** without re-learning basics
2. **Answer questions efficiently** by reading concise summaries vs. scanning entire codebase
3. **Maintain context across conversations** via persistent documentation
4. **Reduce token usage** - 5KB of curated docs vs. 500KB of source files
5. **Provide accurate answers** based on comprehensive project knowledge

---

## Document Index

### 0. Quick Reference
**File**: `00-quick-reference.md` (6KB)
**Use when**: Need quick facts, syntax reminders, or navigation pointers

### 0.1 Testing Conventions
**File**: `testing-conventions.md` (8KB)
**Use when**: Writing or reviewing tests
**IMPORTANT**: Always consult this before writing tests!

**Contents**:
- Standard assertion patterns (use `==>`, not `assert()`)
- SQL inspection patterns (full SQL with `.contains()`)
- Test structure conventions
- Common mistakes to avoid
- Examples from codebase

---

### 1. Project Structure
**File**: `01-project-structure.md` (8KB)
**Use when**: Need to understand code organization or find specific files

**Contents**:
- Repository layout
- Module breakdown (core, boilerplate, db/common, db/xxx, db/compliance)
- Build system (SBT configuration)
- Code generation flow
- Testing structure
- Platform-specific code (JVM vs. JS)
- External dependencies
- Development workflow
- Navigation tips

---

### 2. Compliance Test Taxonomy
**File**: `compliance-test-taxonomy.md` (~15KB)
**Use when**: Questions about Molecule features, capabilities, or semantics

**Contents**:
- Complete feature matrix (346 test files)
- 14 test categories with examples:
  1. Aggregation (count, sum, avg, median, variance, stddev, min, max, sample)
  2. API (Async, IO, ZIO)
  3. Authorization (role-based, attribute-level)
  4. Bind (runtime parameterization)
  5. CRUD (insert, save, update, delete)
  6. Filter (equals, comparison, string, collection)
  7. FilterAttr (cross-attribute comparisons)
  8. Pagination (offset, cursor strategies)
  9. Relationship (flat refs, nested, bidirectional, many-to-many)
  10. Segments (multi-tenant namespaces)
  11. Sorting (compile-time, dynamic, with aggregates)
  12. Subscription (live queries)
  13. Types (25+ types with cardinalities)
  14. Validation (mandatory, enums, format, allowed values)
- DSL syntax patterns
- Operator reference
- Special features and limitations

---

### 3. Boilerplate DSL Generation
**File**: `boilerplate-dsl-generation.md` (~12KB)
**Use when**: Questions about how DSL code works or is structured

**Contents**:
- Entity DSL structure (entry points, file organization)
- Attribute accessor patterns (mandatory, optional, tacit)
- Expression operations (type-specific operators)
- Relationship navigation (forward refs, back refs, nested)
- Type safety mechanism (phantom types, DataModel accumulation)
- Metadata representation (metadb/)
- Annotated code examples showing type progression
- Attribute definition patterns
- Key design principles

**Key Insight**: Shows how `Entity.s("foo").i(42)` chains through `Entity_0` → `Entity_1[String]` → `Entity_2[String, Int]` with tuple accumulation.

---

### 4. sbt-molecule Code Generation
**File**: `sbt-molecule-codegen.md` (~10KB)
**Use when**: Questions about code generation, schema parsing, or extending Molecule

**Contents**:
- Plugin entry point (SBT AutoPlugin)
- Schema definition format (trait-based DSL)
- Parsing pipeline (Scalameta AST → IR)
- Domain model IR (MetaDomain → MetaSegment → MetaEntity → MetaAttribute)
- Code generation strategy:
  - Entity.scala (entry points)
  - ops/Entity_.scala (arity variants)
  - ops/Entity_attrs.scala (metadata)
  - Expression classes (type-specific operators)
  - metadb/ (database resolvers)
- Type safety mechanism
- Relationship detection (manyToOne, oneToMany, many-to-many)
- SQL type mapping
- Migration support (Flyway)

**Key Insight**: Shows transformation from `val name = oneString.unique` → generated DSL classes with type-safe chaining.

---

### 5. Compilation Pipeline
**File**: `compilation-pipeline.md` (~16KB)
**Use when**: Questions about how queries compile or how features are implemented

**Contents**:
- DataModel Element hierarchy (Attr, Ref, BackRef, Nested, SubQuery, etc.)
- Element anatomy (coordinates, cardinality, mode, operation, value)
- Molecule building phase (DSL → Elements)
- Query resolution (Model2Query recursive pattern)
- SQL generation (Model2SqlQuery):
  - Filter compilation (equals, comparison, string, collection)
  - Aggregation compilation (groupBy, HAVING)
  - Join compilation (inner, left, back-refs)
  - Sorting and pagination
- Transaction resolution (Insert, Save, Update, Delete)
- Model transformations (optimizations)
- Execution & marshalling:
  - Query execution
  - ResultSet → Tuple casting
  - Type-specific casters
  - JS/JVM marshalling (PickleTpls/UnpickleTpls)
- Database-specific implementations
- Flow diagrams
- 5 detailed examples (DSL → Elements → SQL → Results)

**Key Insight**: The heart of Molecule - shows complete transformation from type-safe DSL to SQL and back to typed tuples.

---

### 6. Database Backend Implementations
**File**: `database-backends.md` (~12KB)
**Use when**: Questions about database-specific behavior or differences

**Contents**:
- All 5 backends: H2, PostgreSQL, MySQL, MariaDB, SQLite
- For each database:
  - SPI implementation (sync, async, IO, ZIO)
  - Query implementation (SQL dialect)
  - JDBC/Connection layer
  - Special features (arrays, JSON, full-text, UPSERT)
  - Limitations and workarounds
- Feature comparison matrix
- Type mapping comparison (Scala → SQL per database)
- Connection patterns
- Transaction strategies (UPSERT, collection manipulation)
- Migration considerations
- Testing coverage

**Key Insight**: H2/PostgreSQL have native arrays; MySQL/MariaDB/SQLite use JSON. Different UPSERT syntax per database.

---

## How to Use This Index

### For the AI Assistant

**Start here for every conversation**:
1. Read `00-quick-reference.md` (6KB) - Get oriented
2. Based on question type, read relevant detailed doc
3. Use file paths in docs to read specific source code if needed

**Question Type → Document Mapping**:
- "How should I write tests?" → `testing-conventions.md` (READ THIS FIRST!)
- "What features does Molecule support?" → `compliance-test-taxonomy.md`
- "How do I write a query that..." → `compliance-test-taxonomy.md` + Quick Reference
- "How does X work internally?" → `compilation-pipeline.md`
- "Where is X implemented?" → `01-project-structure.md`
- "Why doesn't X work on Y database?" → `database-backends.md`
- "How is the DSL generated?" → `boilerplate-dsl-generation.md` + `sbt-molecule-codegen.md`

**Token Optimization Strategy**:
- Always read Quick Reference first (6KB = ~2K tokens)
- Read only relevant detailed docs (10-16KB = ~3-5K tokens each)
- Use file paths in docs to read specific source files (targeted vs. exploratory)
- Total index: ~80KB ≈ 25K tokens (read once vs. 200K+ tokens exploring codebase)

---

## For Human Developers

While these docs are optimized for AI consumption, they also serve as:
- **Project overview** for new contributors
- **Reference documentation** for advanced features
- **Design documentation** showing architecture decisions
- **Test coverage map** showing what's tested where

Feel free to read them, but note they're intentionally dense and reference-heavy for AI efficiency.

---

## Maintenance

**When to update**:
- New features added → Update `compliance-test-taxonomy.md`
- Schema syntax changes → Update `sbt-molecule-codegen.md`
- Query compilation changes → Update `compilation-pipeline.md`
- New database support → Update `database-backends.md`
- Project reorganization → Update `01-project-structure.md`

**Update Quick Reference** whenever any other doc changes significantly.

---

## Document Format

**Design Principles**:
- **Dense** - Maximize information per token
- **Hierarchical** - Easy to scan and jump to sections
- **Reference-heavy** - File paths, line numbers when relevant
- **Example-rich** - Show actual code patterns
- **Concise prose** - Bullet points over paragraphs
- **Tables** - For comparisons and feature matrices

**Not optimized for**:
- Prose reading by humans
- Pretty formatting
- Exhaustive detail (source code is source of truth)

---

## Coverage Statistics

**Total Molecule Project**:
- ~200K lines of code
- 346 test files
- 14 feature categories
- 5 database backends
- 2 platforms (JVM + JS)
- 25+ data types (Scala 3 tuples have unlimited arity)
- 50+ operators

**Index Coverage**:
- ✅ All 14 feature categories documented
- ✅ All 5 database backends compared
- ✅ Complete compilation pipeline mapped
- ✅ DSL generation fully explained
- ✅ Project structure documented
- ✅ All 25+ types covered
- ✅ All operator types documented
- ✅ Binding limitations documented (max 22 parameters)

---

## Version

**Molecule Version**: v0.29.0 (Flyway migration support)
**Index Created**: 2026-01-04
**Last Updated**: 2026-01-04

Update this section when major Molecule versions are released.

---

## Example AI Usage

**User asks**: "How do I filter by a String that starts with 'foo'?"

**AI reads**: `00-quick-reference.md` (finds String operators section)
**AI answers**: `Entity.stringAttr.startsWith("foo")`

---

**User asks**: "Why doesn't median() work on SQLite?"

**AI reads**: `database-backends.md` (finds SQLite aggregation section)
**AI answers**: SQLite requires client-side median calculation (see db/sqlite/.../AggrNum.scala:123)

---

**User asks**: "Explain how nested pulls compile to SQL"

**AI reads**: `compilation-pipeline.md` (finds Nested element + join compilation sections)
**AI answers**: [Detailed explanation with code references]

---

## Future Enhancements

Potential additions as Molecule evolves:
- Performance optimization guide
- Migration guide between databases
- Custom validation function guide
- Authorization patterns catalog
- Subquery implementation details (when stable)
- Time/temporal query support (if added)

---

## Contact

These docs were generated by analyzing the Molecule codebase.
For questions about Molecule itself: [Molecule GitHub](https://github.com/scalamolecule/molecule)
