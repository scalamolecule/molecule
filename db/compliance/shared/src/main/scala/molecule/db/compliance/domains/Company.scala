package molecule.db.compliance.domains

import molecule.DomainStructure

@deprecated
object Company extends DomainStructure {

  trait Employee {
    val name     = oneString
    val projects = many[Project].employees
//    val projects = many[Project].employees.owner_
  }

  trait Project {
    val name      = oneString
    val budget    = oneInt
    val employees = many[Employee]
  }
}
