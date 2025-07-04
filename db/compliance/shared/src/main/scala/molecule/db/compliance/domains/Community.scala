package molecule.db.compliance.domains

import molecule.DomainStructure

object Community extends DomainStructure {

  trait Person {
    val name = oneString
    val age  = oneInt
    val home = one[Address]
  }

  trait Address {
    val street = oneString
  }
}
