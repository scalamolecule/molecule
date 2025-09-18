package molecule.db.compliance.domains

import molecule.DomainStructure

object Refs extends DomainStructure {

  trait A {
    val i    = oneInt
    val iSet = setInt
    val iSeq = seqInt
    val iMap = mapInt
    val s    = oneString
    val bool = oneBoolean

    val a    = manyToOne[A].oneToMany("Aa")
    val b    = manyToOne[B].oneToMany("Aa")
    val b1   = manyToOne[B].oneToMany("Aa1")
    val b2   = manyToOne[B].oneToMany("Aa2")
    val c    = manyToOne[C].oneToMany("Aa")
    val d    = manyToOne[D].oneToMany("Aa")
  }

  trait B {
    val i    = oneInt
    val iSet = setInt
    val iSeq = seqInt
    val iMap = mapInt
    val s    = oneString

    val a    = manyToOne[A].oneToMany("Bb")
    val b    = manyToOne[B].oneToMany("Bb")
    val c    = manyToOne[C].oneToMany("Bb")
    val c1   = manyToOne[C].oneToMany("Bb1")
    val d    = manyToOne[D].oneToMany("Bb")
  }

  trait C {
    val i    = oneInt
    val s    = oneString
    val iSet = setInt
    val iSeq = seqInt
    val iMap = mapInt
    val a    = manyToOne[A].oneToMany("Cc")
    val b    = manyToOne[B].oneToMany("Cc")
    val d    = manyToOne[D].oneToMany("Cc")
  }


  trait D {
    val i  = oneInt
    val s  = oneString
    val a  = manyToOne[A].oneToMany("Dd")
    val b  = manyToOne[B].oneToMany("Dd")
    val c  = manyToOne[C].oneToMany("Dd")
    val e  = manyToOne[E].oneToMany("Dd")
    val e1 = manyToOne[E].oneToMany("Dd1")
  }

  trait E {
    val i = oneInt
    val s = oneString
    val d = manyToOne[D].oneToMany("Ee")
    val f = manyToOne[F]
  }

  trait F {
    val i = oneInt
    val s = oneString
    val e = manyToOne[E].oneToMany("Ff")
    val g = manyToOne[G]
  }

  trait G {
    val i = oneInt
    val s = oneString
    val f = manyToOne[F].oneToMany("Gg")
    val h = manyToOne[H]
  }

  trait H {
    val i = oneInt
    val s = oneString
    val g = manyToOne[G].oneToMany("Hh").owner
  }
}
