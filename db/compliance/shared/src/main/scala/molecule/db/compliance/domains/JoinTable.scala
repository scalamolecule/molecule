package molecule.db.compliance.domains

import molecule.DomainStructure

trait JoinTable extends DomainStructure {

  trait A {
    val i = oneInt
    val s = oneString
  }

  trait J extends Join {
    val a = manyToOne[A]
    val b = manyToOne[B]

    // Optional join properties
    val i = oneInt
    val s = oneString
  }

  trait B {
    val i = oneInt
    val s = oneString
  }
}
