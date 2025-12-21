package molecule.db.compliance.domains

import molecule.DomainStructure


/**
 * Layer 1: Roles - Basic role definitions and entity-level role access
 *
 * INSTRUCTIONS FOR SBT-MOLECULE:
 *
 * 1. ROLE DEFINITIONS & ACTION PERMISSIONS
 *    - Parse Role traits that extend `Role` with action mixins
 *    - Available action mixins: query, save, insert, update, delete
 *    - Action mappings:
 *      * query = query only (bit 0)
 *      * save = save only (bit 1)
 *      * insert = insert only (bit 2)
 *      * update = update only (bit 3)
 *      * delete = delete only (bit 4)
 *    - Store each role's action bitmask for later use
 *
 * 2. ENTITY ACCESS CONTROL
 *    - PUBLIC ENTITIES (no Role extensions):
 *      * Anyone can access with all actions (query + save + insert + update + delete = all 5 bits)
 *      * Generate meta with public flag
 *      * Example: `trait Article` (no roles) → public entity
 *
 *    - ROLE-PROTECTED ENTITIES (extends one or more Roles):
 *      * Calculate combined action bitmask from all extended roles
 *      * Combine using bitwise OR of all role action masks
 *      * Generate meta with role list and combined action mask
 *      * Example: `trait Post extends Member with Moderator with Admin`
 *        → combine Member actions | Moderator actions | Admin actions
 *
 * 3. ACTION BITMASK VALIDATION
 *    - Verify that at least one role provides all 5 actions (full bitmask)
 *    - This ensures entity can support complete CRUD operations when needed
 *    - Typically an Admin or Owner role provides this
 *
 * 4. META GENERATION
 *    For each entity, generate:
 *    - Entity name
 *    - Public flag (true if no roles, false otherwise)
 *    - List of allowed roles (empty for public entities)
 *    - Combined action bitmask for all roles
 *    - Attribute definitions (handled in later layers)
 *
 * This is the foundation - roles define what actions users can perform.
 */
trait SocialApp1_roles extends DomainStructure {

  // Role definitions with base action permissions
  trait Guest extends Role with query           // Can only query (read-only)
  trait Member extends Role with query          // Can only query
  trait Moderator extends Role with query       // Can only query
  trait Admin extends Role with query with save with insert with update with delete  // Can do all actions


  // PUBLIC ENTITY - No roles = anyone can access
  trait Article {
    val title   = oneString
    val preview = oneString
  }

  // SINGLE ROLE ENTITY - Member and Admin can access
  // Admin provides all 5 actions (requirement: all actions must be available)
  trait UserProfile extends Member with Admin {
    val displayName = oneString
    val bio         = oneString
  }

  // MULTIPLE ROLES ENTITY - Member, Moderator, and Admin can access
  // Admin provides all 5 actions (requirement: all actions must be available)
  trait Post extends Member with Moderator with Admin {
    val content = oneString
    val author  = oneString
  }
}