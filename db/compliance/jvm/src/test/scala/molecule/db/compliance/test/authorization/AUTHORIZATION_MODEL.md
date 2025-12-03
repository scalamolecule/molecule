# Molecule Authorization Model

## Overview

Molecule provides a declarative, compile-time validated authorization system integrated directly into the domain model. Authorization rules are defined once in the domain and automatically enforced at runtime with zero boilerplate.

---

## The 4 Authorization Layers

Authorization in Molecule is built on 4 distinct concepts that work together:

1. **Layer 1: Roles** - Which roles have access to entities
2. **Layer 2: Role Actions** - Action grants at entity level
3. **Layer 3: Attribute Roles** - Attribute role restrictions
4. **Layer 4: Attribute Update** - Attribute update grants

These layers build on each other, providing progressively finer control.

---

## Core Principles

1. **Public entities (no roles) are unrestricted** - Entities with no roles can be accessed by anyone with all 6 actions (query, subscribe, save, insert, update, delete) without authentication
2. **Roles define baseline capabilities** via action traits (Layer 1)
3. **Action grants ADD capabilities** to specific roles (Layer 2 - additive, per-role)
4. **Attribute restrictions NARROW access** from entity baseline (Layer 3 - restrictive)
5. **Attribute update grants ADD capabilities** to specific roles for specific fields (Layer 4 - additive, per-role)
6. **All 6 actions must be available** - For role-restricted entities, combined roles and grants must provide access to all 6 actions
7. **User authenticates as ONE role** at a time (for role-restricted entities)
8. **No magic roles** - all roles follow same rules
9. **One entity = one database table** (no entity variations)

---

## Action Traits

### Atomic Actions

```scala
trait query extends Action      // Read data via queries
trait subscribe extends Action  // Reactive queries
trait save extends Action       // Create single entity
trait insert extends Action     // Batch insert multiple entities
trait update extends Action     // Modify existing data
trait delete extends Action     // Remove data
```

### Convenience Collections

```scala
trait read extends query with subscribe
trait write extends save with insert with update with delete
trait all extends read with write  // All 6 actions
```

---

## Role Definition

Roles are defined by combining action traits:

```scala
trait RoleName extends Role with action1 with action2 ...
```

**Examples:**

```scala
trait Guest extends Role with query
trait Member extends Role with read with save  // query + subscribe + save
trait Moderator extends Role with read with write  // All 6 actions
trait Admin extends Role with all  // All 6 actions
```

**Limit:** Up to 32 different roles per domain.

---

## Entity-Level Authorization

### Syntax

```scala
// Single role
trait Entity extends Role1

// Multiple roles
trait Entity extends Role1 with Role2 with Role3

// With action grants
trait Entity extends Role1 with Role2
  with updating[Role]   // ADD update capability to this role
  with deleting[Role]   // ADD delete capability to this role
```

### Semantics

**Public entities** (no roles):
- Entities that don't extend any roles are public
- Anyone can access without authentication
- All 6 actions available (query, subscribe, save, insert, update, delete)
- Cannot use authorization features (grants, restrictions) on public entities

**Entity baseline** (`extends Role1 with Role2 ...`):
- Lists all roles that can access this entity
- Each role uses their own action access

**Action grants** (`with updating[Role]`, `with deleting[Role]`):
- ADD action capabilities to specific roles that lack update/delete access
- **Action grants apply to both the entity AND all its attributes** automatically
- Other roles unaffected (purely additive)
- Can grant to multiple roles: `with updating[(Role1, Role2)]`
- Granting to roles that already have the access is redundant but allowed

### Natural Reading

```scala
trait Post extends Member with Admin with updating[Member]
```

Reads as: "Post extends Member with Admin with updating Member"

---

## Attribute-Level Authorization

### Syntax

```scala
// Restrictions (affect query, save, update together)
val attr = oneType                  // All entity roles (baseline)
val attr = oneType.only[Role]    // ONLY these roles (replacement)
val attr = oneType.exclude[Role]    // All EXCEPT these roles (subtraction)

// Update grants (add update capability to specific roles)
val attr = oneType.updating[Role]   // ADD update capability to this role
```

### Semantics

**`.only[Role]`**:
- Restrictive - only these roles can query/save/update this attribute
- Replaces entity baseline for this attribute
- Must reference roles in entity baseline

**`.exclude[Role]`**:
- Restrictive - these roles cannot query/save/update this attribute
- Subtracts from entity baseline for this attribute
- Must reference roles in entity baseline

**`.updating[Role]`**:
- Additive - add update capability to this role for this field
- Does NOT affect other roles - purely additive
- Must reference roles in entity baseline
- Only grant to roles that lack update access (otherwise redundant)

---

## Interaction Rules

### 1. Action Grants Apply to Entity AND All Attributes

Entity-level action grants (`with updating[Role]` and `with deleting[Role]`) automatically apply to both the entity itself and all of its attributes. This ensures consistent permission checking during operations like delete-by-ID, which affects all attribute values.

```scala
trait Post extends Member with updating[Member] {
  val title = oneString  // Member can update (action grant cascades)
  val bio = oneString    // Member can update (action grant cascades)
}

// When deleting by ID, the action grant allows deletion of the entity
// AND all its attributes:
Post(id).delete.transact  // ✓ Checks entity + all attribute permissions
```

**Rationale:** Deleting an entity by ID removes all attribute values. If action grants didn't cascade to attributes, users could be granted delete permission on the entity but be blocked from deleting individual attribute values—creating an inconsistent security model.

### 2. Attribute Update Grants Are Purely Additive

```scala
trait Post extends Guest with Member with updating[Member] {
  val bio = oneString                   // Member can update (action grant)
  val notes = oneString.updating[Guest] // Guest ALSO can update (attribute update grant)
}
```

**Result:**
- BOTH Member and Guest can update notes (additive)
- Member can update bio (action grant)
- Guest cannot update bio (no action grant)

### 3. Attribute Restrictions Apply First

```scala
trait Post extends Guest with Member with Admin
  with updating[Member]
{
  val secret = oneString.only[Admin]  // Only Admin can access
}
```

**Result:** Member cannot query/save/update secret (restriction blocks everything), even though Member has action grant for update.

### 4. Multiple Grants Accumulate

```scala
trait Post extends Guest with Member
  with updating[Member]  // Member can update all
{
  val tags = oneString.updating[Guest]  // Guest can ALSO update tags
}
```

**Result:**
- Member can update all attributes (action grant)
- Guest can update tags (attribute update grant)
- Both have update capability on tags

### 5. Roles with Action Access Always Use It (Unless Restricted)

```scala
trait Post extends Member with Admin {
  val title = oneString
}
```

**Result:**
- Member: query, subscribe, save (from role definition)
- Admin: all actions (from `all` action)
- No action grants needed for Admin (already has capabilities)

---

## Permission Resolution Algorithm

For each operation (query, save, update, delete) on an attribute:

1. **Check attribute restrictions first:**
   - If `.only[Roles]`: Only those roles can proceed
   - If `.exclude[Roles]`: Those roles are blocked
   - If blocked: Access denied ✗

2. **Check if role has required action access:**
   - Query: needs `query` or `read` access
   - Save: needs `save` access
   - Update: needs `update` access
   - Delete: needs `delete` access (entity-level only)

3. **Check action grants (if role lacks action access):**
   - For update: check `with updating[Roles]`
   - For delete: check `with deleting[Roles]`
   - **Action grants apply to both entity AND all attributes**
   - If role in grant: capability added ✓

4. **Check attribute update grants (if role lacks action access):**
   - For update: check `.updating[Roles]`
   - Attribute update grants only apply to specific attributes
   - If role in grant: capability added ✓

5. **Final check:**
   - Has capability (action access or grant): Access allowed ✓
   - Lacks capability: Access denied ✗

---

## Compile-Time Validation

sbt-molecule enforces at compile time:

1. ✓ **All 6 actions must be available** - For role-restricted entities, the combined roles (and their action grants) must provide access to all 6 actions: query, subscribe, save, insert, update, delete
   - Public entities (no roles) automatically satisfy this requirement
   - This prevents deadlock scenarios where data can be created but not read, or read but not created
   - Example of invalid entity:
     ```scala
     trait Member extends Role with read  // Only query + subscribe
     trait Post extends Member { ... }    // ERROR: save, insert, update, delete not available
     ```
   - Valid alternatives:
     ```scala
     // Option 1: Add role with missing actions
     trait Admin extends Role with all
     trait Post extends Member with Admin { ... }  // OK: all actions covered

     // Option 2: Make entity public (no roles)
     trait Post { ... }  // OK: public entities have all actions

     // Option 3: Action grants don't help (they don't provide save/insert)
     trait Post extends Member
       with updating[Member]
       with deleting[Admin] { ... }  // Still ERROR: save, insert not available
     ```
2. ✓ At least one role can query (has `query` or `read` access) - Not required for public entities
3. ✓ At least one role can save (has `save` access) - Not required for public entities
4. ✓ Action grants reference roles in entity baseline
5. ✓ Action grants only grant to roles lacking the action access (warning if redundant)
6. ✓ Attribute `.only/.exclude` reference roles in entity baseline
7. ✓ Attribute `.updating` references roles in entity baseline
8. ✓ Attribute `.updating` only grants to roles lacking update access (warning if redundant)
9. ✓ **Action grants must be compatible with attribute restrictions** - Roles granted `updating[R]` or `deleting[R]` must have access to all entity attributes
   - Validation ensures roles can't update/delete entities with attributes they can't access
   - Example of invalid entity:
     ```scala
     trait Document extends Member with Admin
       with deleting[(Member, Admin)] {  // ERROR: Member can't access secretNotes
       val title = oneString
       val secretNotes = oneString.only[Admin]
     }
     ```
   - Error message guides fix:
     ```
     Entity grants deleting to role 'Member' but attribute 'secretNotes' is restricted with .only[Admin]
     A role cannot delete an entity if it cannot access all attributes
     ```
   - Valid alternatives:
     ```scala
     // Option 1: Only grant delete to Admin
     with deleting[Admin]
     
     // Option 2: Allow Member to access secretNotes
     val secretNotes = oneString.only[(Member, Admin)]
     ```
10. ✓ **Action grants respect `.exclude[R]` restrictions** - Similar validation for `.exclude[R]`
   - Ensures roles granted updating/deleting aren't excluded from any attributes

---

## Layer-by-Layer Examples

### Layer 1: Roles - Entity-Level Role Access

**Example 1a: Public Entity (anyone can access)**

```scala
trait Article {
  val title = oneString
}
```

**Permissions:**
- Public entity (no roles)
- **Unauthenticated users**: All 6 actions available (query, subscribe, save, insert, update, delete)
- **Authenticated users**: Must follow their role's action permissions
  - Rationale: Aligns with industry standards (AWS, GCP, PostgreSQL, GitHub)
  - Security: Prevents privilege escalation attacks
  - Principle: Authentication adds identity/accountability, not extra permissions
  - Example: Guest (query-only) can query public Article but CANNOT save to it

**Example 1b: Single Role Entity**

```scala
trait Member extends Role with read

trait Post extends Member {
  val content = oneString
}
```

**Permissions:**
- Only Member can access
- Member: query, subscribe (from `read`)

**Example 1c: Multiple Role Entity**

```scala
trait Guest extends Role with query
trait Member extends Role with read
trait Admin extends Role with all

trait Post extends Guest with Member with Admin {
  val title = oneString
}
```

**Permissions:**
- Guest: query
- Member: query, subscribe
- Admin: all actions

---

### Layer 2: Role Actions - Action Grants at Entity Level

**Example 2a: Entity-Level Update Grant**

```scala
trait Member extends Role with read

trait Comment extends Member
  with updating[Member] {
  val text = oneString
}
```

**Permissions:**
- Member: query, subscribe (from `read`)
- Member: **update** (from action grant)

**Example 2b: Entity-Level Delete Grant**

```scala
trait Moderator extends Role with read
trait Admin extends Role with all

trait Comment extends Moderator with Admin
  with deleting[Moderator] {
  val text = oneString
}
```

**Permissions:**
- Moderator: query, subscribe (from `read`)
- Moderator: **delete** on entity AND all attributes (from action grant)
- Admin: all actions

**Key behavior:** The `with deleting[Moderator]` action grant applies to both:
1. The Comment entity itself
2. All attributes (text) within Comment

This ensures that `Comment(id).delete.transact` succeeds, as the system checks both entity-level and attribute-level delete permissions.

**Example 2c: Combining Both Grants**

```scala
trait Member extends Role with read

trait BlogPost extends Member
  with updating[Member]
  with deleting[Admin] {
  val content = oneString
}
```

**Permissions:**
- Member: query, subscribe, **update** (from action grant)
- Admin: all actions, including **delete** (from action grant)

---

### Layer 3: Attribute Roles - Attribute Role Restrictions

**Example 3a: Using .only[R]**

```scala
trait Member extends Role with read
trait Admin extends Role with all

trait Settings extends Member with Admin {
  val theme = oneString              // Both roles
  val apiKey = oneString.only[Admin] // Only Admin
}
```

**Permissions:**
- Member: can access theme, blocked from apiKey
- Admin: can access both attributes

**Example 3b: Using .exclude[R]**

```scala
trait Guest extends Role with query
trait Member extends Role with read

trait Profile extends Guest with Member {
  val username = oneString              // Both roles
  val email = oneString.exclude[Guest]  // Only Member
}
```

**Permissions:**
- Guest: can access username, blocked from email
- Member: can access both attributes

---

### Layer 4: Attribute Update - Attribute Update Grants

**Example 4a: Single Role Update Grant**

```scala
trait Member extends Role with read

trait Draft extends Member {
  val content = oneString                // Member can read
  val title = oneString.updating[Member] // Member can also update
}
```

**Permissions:**
- Member on content: query, subscribe
- Member on title: query, subscribe, **update** (from attribute update grant)

**Example 4b: Multiple Role Update Grant**

```scala
trait Member extends Role with read
trait Moderator extends Role with read

trait Page extends Member with Moderator {
  val body = oneString
  val tags = oneString.updating[(Member, Moderator)] // Both can update
}
```

**Permissions:**
- Member: query, subscribe on all, **update** on tags
- Moderator: query, subscribe on all, **update** on tags

---

### Composed Example - All Layers Together

This example demonstrates how all 4 layers work together in a single entity:

```scala
trait Guest extends Role with query
trait Member extends Role with read
trait Admin extends Role with all

trait BlogPost extends Guest with Member with Admin    // Layer 1: Multiple roles
  with updating[Member]                                // Layer 2: Action grant
  with deleting[Admin] {                               // Layer 2: Action grant

  val title = oneString                                // All roles

  val content = oneString                              // Layer 2: Member gets action grant
    .exclude[Guest]                                    // Layer 3: No Guest

  val viewCount = oneLong                              // All roles
    .updating[Guest]                                   // Layer 4: Guest can update

  val internal = oneString                             // Only Admin, gets action grant
    .only[Admin]                                       // Layer 3
}
```

**Permissions:**

**Guest:**
- title: query ✓
- content: blocked (exclude) ✗
- viewCount: query, **update** (attribute update grant) ✓
- internal: blocked (only Admin) ✗

**Member:**
- title: query, subscribe, **update** (action grant) ✓
- content: query, subscribe, **update** (action grant) ✓
- viewCount: query, subscribe ✓
- internal: blocked (only Admin) ✗

**Admin:**
- All attributes: all actions ✓
- Can **delete** BlogPost (action grant) ✓

---

### Complex Example: Blog Post

```scala
trait Guest extends Role with query
trait Member extends Role with read with save
trait Moderator extends Role with read with write  // All actions
trait Admin extends Role with all

trait BlogPost extends Guest with Member with Moderator with Admin
  with updating[Member]    // Members can edit
  with deleting[Moderator] // Moderators can delete
{
  // Public
  val title = oneString
  val views = oneLong

  // Members+ only
  val content = oneString.exclude[Guest]

  // Members, Moderators, and Guest can all update
  val draft = oneBoolean.updating[Guest]

  // Only Moderators+ can access
  val flagged = oneBoolean.only[(Moderator, Admin)]

  // Only Admin can access
  val featured = oneBoolean.only[Admin]
}
```

**Permissions:**

**Guest:**
- title, views: query ✓
- content: blocked (exclude) ✗
- draft: query, **update** (attribute update grant) ✓
- flagged, featured: blocked (require) ✗

**Member:**
- title, views, content: query, subscribe, save ✓
- title, views, content: **update** (action grant) ✓
- draft: query, subscribe, save, **update** (action grant) ✓
- flagged, featured: blocked (.only restriction) ✗
- Cannot delete (no delete access, no action grant) ✗

**Moderator:**
- title, views, content, draft, flagged: all actions (has `write` access) ✓
- featured: blocked (.only[Admin]) ✗
- Can delete posts (action grant - redundant since has delete access) ✓

**Admin:**
- All attributes: all actions (has `all` access) ✓

---

### Complex Example: Collaborative Document

```scala
trait Viewer extends Role with query
trait Contributor extends Role with read with save
trait Collaborator extends Role with read with save with update
trait Owner extends Role with read with write

trait SharedDocument extends Viewer with Contributor with Collaborator with Owner
  with updating[Contributor]  // Contributors can update
  with deleting[Owner]         // Only Owner can delete
{
  // Everyone can see
  val title = oneString

  // Viewer can also update subtitle
  val subtitle = oneString.updating[Viewer]

  // Contributors+ can see/edit content
  val content = oneString.exclude[Viewer]

  // Collaborators and Viewers can both update tags
  val tags = oneString.updating[(Viewer, Collaborator)]

  // Only Owner can see/edit metadata
  val metadata = oneString.only[Owner]
}
```

**Permissions:**

**Viewer:**
- title: query ✓
- subtitle: query, **update** (attribute update grant) ✓
- content: blocked (exclude) ✗
- tags: query, **update** (attribute update grant) ✓
- metadata: blocked (require) ✗

**Contributor:**
- title: query, subscribe, save, **update** (action grant) ✓
- subtitle: query, subscribe, save, **update** (action grant) ✓
- content: query, subscribe, save, **update** (action grant) ✓
- tags: query, subscribe, save, **update** (action grant) ✓
- metadata: blocked (require) ✗

**Collaborator:**
- All accessible fields: query, subscribe, save, **update** (has trait) ✓
- tags: **update** (attribute update grant - redundant) ✓
- metadata: blocked (require) ✗

**Owner:**
- All attributes: all actions (has `write` access) ✓

---

## Runtime Usage

### Authentication

```scala
// User authenticates as ONE role
implicit val conn = baseConn.withAuth(userId, "Member")
```

### Automatic Authorization

```scala
// All operations check authorization automatically in .transact
Post.title.query.get                      // ✓ or ✗ based on role
Post.title("Hi").save.transact            // ✓ or ✗ based on role
Post(id).title("New").update.transact     // ✓ or ✗ based on role
Post(id).delete.transact                  // ✓ or ✗ based on role
```

### Error Messages

When authorization fails:

```scala
Post(id).title("New").update.transact

// If Member lacks update capability:
// ModelError: Access denied: Role 'Member' cannot update attribute 'Post.title'

Post(id).delete.transact

// If Member lacks delete capability:
// ModelError: Access denied: Role 'Member' cannot delete entity 'Post'
```

---

## Key Design Decisions

### Why Additive Grants?

**Flexibility:** Same role can have different capabilities on different entities:
```scala
trait Profile extends Member with updating[Member]  // Can update
trait Post extends Member                           // Cannot update
```

**Accumulation:** Multiple grants can apply:
```scala
with updating[Member]                 // Action grant
val tags = oneString.updating[Guest]  // Attribute update grant
// Both Member and Guest can update tags
```

### Why Separate Entity and Attribute Grants?

**Action grants:** Coarse-grained control that applies to entity AND all attributes
```scala
with updating[Member]  // Member can update entity + all attributes
with deleting[Moderator]  // Moderator can delete entity + all attributes
```

**Attribute update grants:** Fine-grained control per specific field
```scala
val email = oneString.updating[Admin]  // Only Admin can update this specific field
```

**Key difference:**
- Action grants cascade to ALL attributes automatically
- Attribute update grants apply only to the specific attribute they're attached to

### Why Not Override Semantics?

**Override would be confusing:**
```scala
with updating[Member]              // Member can update all
val tags = oneString.updating[Guest]  // Does this mean ONLY Guest? Or Guest also?
```

**Additive is clear:**
```scala
with updating[Member]                 // Member can update all
val tags = oneString.updating[Guest]  // Guest can ALSO update tags
```

### Why Restrictions Are Not Grants?

**Different semantics:**
- **Action grants:** Add action capabilities (expanding)
- **Restrictions:** Remove access (narrowing)

**Example:**
```scala
val secret = oneString.only[Admin]     // ONLY Admin (restrictive)
val tags = oneString.updating[Guest]   // Guest can ALSO update (additive)
```

---

## Summary

### The 4 Authorization Layers

1. **Layer 1: Roles** - Define which roles have access to entities and what baseline actions they can perform
2. **Layer 2: Role Actions** - Action grants (updating/deleting) at entity level
3. **Layer 3: Attribute Roles** - Attribute role restrictions (only/exclude)
4. **Layer 4: Attribute Update** - Attribute update grants at attribute level

### The Model in Four Concepts

1. **Roles have action access** (what they CAN do - Layer 1)
2. **Entities extend roles** (who CAN access - Layer 1)
3. **Action grants add capabilities** (additive, per-role - Layers 2 & 4)
4. **Restrictions remove access** (subtractive - Layer 3)

### Key Behaviors

- **All grants are additive** - they accumulate, don't override
- **Restrictions are subtractive** - they remove access
- **Roles with action access can always use it** (unless restricted)
- **Only grant to roles that lack the action access** (otherwise redundant)
- **Restrictions checked first, then grants applied**

### Advantages

- ✓ **Compile-time safety** - catch errors at build time
- ✓ **Zero boilerplate** - no middleware, decorators, or resolver code
- ✓ **Centralized** - all authorization in domain definition
- ✓ **Flexible** - additive grants allow fine-grained control
- ✓ **Natural syntax** - reads like English
- ✓ **Type-safe** - full Scala type system backing
