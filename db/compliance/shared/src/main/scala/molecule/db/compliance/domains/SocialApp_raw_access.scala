package molecule.db.compliance.domains

import molecule.DomainStructure


/**
 * Raw SQL Access Authorization
 *
 * This domain demonstrates authorization for raw SQL fallback methods,
 * which exist outside the core 4-layer authorization model.
 *
 * Raw SQL methods are escape hatches for:
 * - Complex queries Molecule doesn't support (window functions, subqueries)
 * - Database-specific features
 * - Bulk operations across multiple tables
 *
 * BUT they are dangerous because they bypass entity/attribute-level authorization!
 *
 * INSTRUCTIONS FOR SBT-MOLECULE:
 *
 * Raw SQL actions work differently than the 5 core CRUD actions:
 * - They don't operate on specific entities/attributes
 * - They check ONLY the role's action permissions
 * - No entity-level or attribute-level restrictions apply
 *
 * For rawQuery and rawTransact:
 * - Store role action bitmask (same as other actions)
 * - Access check verifies: user authenticated + role has the action
 * - No entity/attribute checking needed (raw SQL bypasses the model)
 */
trait SocialApp_raw_access extends DomainStructure {

  // Role definitions with different raw access levels
  trait Guest extends Role with query                   // Can only query (no raw access)
  trait Member extends Role with query with save        // Normal operations (no raw access)
  trait Analyst extends Role with query with rawQuery   // Can run custom analytics queries
  trait Admin extends Role with query with save with insert with update with delete with rawQuery with rawTransact  // Full access including dangerous raw SQL


  // Simple sales data for analytics demonstrations
  trait Sale {
    val saleDate   = oneLocalDate
    val customerId = oneLong
    val amount     = oneDouble
    val region     = oneString
  }

  trait StaffMember {
    val fullName   = oneString
    val email      = oneString
    val accessRole = oneString  // Shows danger: rawTransact could escalate privileges!
  }

  // Entity for demonstrating attribute-level restrictions that raw SQL bypasses
  trait Product extends Member with Admin {
    val productCode    = oneString
    val description    = oneString
    val costPrice      = oneDouble.only[Admin]  // Only Admin should see this
    val inventoryNotes = oneString.only[Admin]  // Only Admin should modify this
  }
}
