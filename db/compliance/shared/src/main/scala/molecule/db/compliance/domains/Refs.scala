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

    val a    = one[A]("Aa")
    val b    = one[B]("Aa")
    val b1   = one[B]("Aa1")
    val b2   = one[B]("Aa2")
    val c    = one[C]("Aa")
    val d    = one[D]("Aa")
    val ownB = one[B]("OwnAa").owner
  }

  trait B {
    val i    = oneInt
    val iSet = setInt
    val iSeq = seqInt
    val iMap = mapInt
    val s    = oneString

    val a    = one[A]("Bb")
    val b    = one[B]("Bb")
    val c    = one[C]("Bb")
    val c1   = one[C]("Bb1")
    val d    = one[D]("Bb")
    val ownA = one[A]("OwnBb").owner
    val ownC = one[C]("OwnBb").owner
  }

  trait C {
    val i    = oneInt
    val s    = oneString
    val iSet = setInt
    val iSeq = seqInt
    val iMap = mapInt
    val a    = one[A]("Cc")
    val b    = one[B]("Cc")
    val d    = one[D]("Cc")
    val ownB = one[B]("OwnCc").owner
  }


  trait D {
    val i  = oneInt
    val s  = oneString
    val a  = one[A]("Dd")
    val b  = one[B]("Dd")
    val c  = one[C]("Dd")
    val e  = one[E]("Dd")
    val e1 = one[E]("Dd1")
  }

  trait E {
    val i = oneInt
    val s = oneString
    val d = one[D]("Ee")
    val f = one[F]
  }

  trait F {
    val i = oneInt
    val s = oneString
    val e = one[E]("Ff")
    val g = one[G]
  }

  trait G {
    val i = oneInt
    val s = oneString
    val f = one[F]("Gg")
    val h = one[H]
  }

  trait H {
    val i = oneInt
    val s = oneString
    val g = one[G]("Hh")
  }
}
