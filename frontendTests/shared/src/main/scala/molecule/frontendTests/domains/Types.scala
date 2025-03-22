package molecule.frontendTests.domains

import molecule.DomainStructure

object Types extends DomainStructure(5) {

  trait Entity {
    val i   = oneInt
    val s   = oneString
    val int = oneInt
  }
}