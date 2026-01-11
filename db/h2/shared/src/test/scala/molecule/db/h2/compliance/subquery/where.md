You're absolutely right! Let me give you examples that **truly require** a WHERE subquery and cannot be rewritten with joins:

## 1. **Comparing to aggregated value from another table**
```sql
-- Find entities whose i is greater than the MAX of all Ref.i values
SELECT Entity.s
FROM Entity
WHERE Entity.i > (
  SELECT MAX(Ref.i)
  FROM Ref
)
```
Entity.s.i_.>(Ref.i_(max))

❌ Cannot use JOIN - you need a single global max value to compare against

## 2. **Comparing to own correlated aggregate**
```sql
-- Find entities whose i is greater than the average of their own refs
SELECT Entity.s
FROM Entity
WHERE Entity.i > (
  SELECT AVG(Ref.i)
  FROM Ref
  WHERE Ref.entity_id = Entity.id
)
```
Entity.s.i_.>(Entity.Ref.i_(avg))

❌ Cannot use JOIN - you need per-row aggregate comparison

## 3. **Filtering based on count**
```sql
-- Find entities that have more than 3 refs
SELECT Entity.s
FROM Entity
WHERE (
  SELECT COUNT(*)
  FROM Ref
  WHERE Ref.entity_id = Entity.id
) > 3
```
Entity.s.sub(Entity.Ref.i_(count).>(3))
or maybe just:
Entity.s.Ref.id_(count).>(3)

❌ Cannot use JOIN without GROUP BY + HAVING (which changes query structure)

## 4. **NOT IN with filtered subquery**
```sql
-- Find entities that are NOT referenced by any high-value refs
SELECT Entity.s
FROM Entity
WHERE Entity.id NOT IN (
  SELECT Ref.entity_id
  FROM Ref
  WHERE Ref.i > 100
)
```
Entity.s.Ref.i_.<=(100)

❌ Cannot use LEFT JOIN easily - NULL handling gets complex

## 5. **Multiple independent aggregate comparisons**
```sql
-- Entities where i is between min and max of ALL refs globally
SELECT Entity.s
FROM Entity
WHERE Entity.i > (SELECT MIN(Ref.i) FROM Ref)
  AND Entity.i < (SELECT MAX(Ref.i) FROM Ref)
```
Entity.s.i_.>(Ref.i_(min)).i_.<(Ref.i_(max))

❌ Cannot join to get both min and max for comparison

## 6. **Cross-entity comparison**
```sql
-- Find refs whose i is greater than their entity's i
SELECT Ref.s
FROM Ref
WHERE Ref.i > (
  SELECT Entity.i
  FROM Entity
  WHERE Entity.id = Ref.entity_id
)
```
Entity.Ref.s.i_.>(Entity.i)

❌ Could use JOIN, but subquery is clearer for "looking up" a single value

## 7. **Comparing counts across relationships**
```sql
-- Entities with more refs than the average entity
SELECT Entity.s
FROM Entity
WHERE (
  SELECT COUNT(*) 
  FROM Ref 
  WHERE Ref.entity_id = Entity.id
) > (
  SELECT AVG(ref_count)
  FROM (
    SELECT COUNT(*) as ref_count
    FROM Ref
    GROUP BY entity_id
  ) counts
)
```
// Can't really find any good syntax for this - maybe out of boundary for Molecule implementation?
Entity.s.Ref.id_(count).>(sub(Ref.entity_.id(count))(avg))
Entity.s.Ref.id_(count).>(Ref.entity_.id(count)(avg))

❌ Requires nested subqueries - cannot flatten

## 8. **ALL operator (no JOIN equivalent)**
```sql
-- Entities whose i is greater than ALL ref values
SELECT Entity.s
FROM Entity
WHERE Entity.i > ALL (
  SELECT Ref.i
  FROM Ref
  WHERE Ref.i IS NOT NULL
)
```
Entity.s.Entity.i_.>(sub(Ref.i(max)))
// or?
Entity.s.Entity.i_.>(Ref.i(max))

❌ `> ALL` has no direct JOIN equivalent

## 9. **ANY operator (no simple JOIN equivalent)**
```sql
-- Entities whose i equals ANY ref i value
SELECT Entity.s
FROM Entity
WHERE Entity.i = ANY (
  SELECT Ref.i
  FROM Ref
)
```
Entity.s.i.in(sub(Ref.i))
Entity.s.i(sub(Ref.i))
Entity.s.i(Ref.i)

❌ Could use `IN` or JOIN, but ANY is the proper semantic

---

## **The Core Truly-Needed Patterns:**

1. **Scalar aggregate comparison** - compare to global aggregate (#1, #5)
2. **Correlated aggregate comparison** - compare to own aggregate (#2, #3)
3. **ALL/ANY operators** - set comparison operators (#8, #9)
4. **Nested aggregates** - aggregate of aggregates (#7)
5. **NOT IN with NULL safety** - exclusion with complex filtering (#4)

These **cannot be easily replaced** with JOINs while maintaining the same semantics and readability.