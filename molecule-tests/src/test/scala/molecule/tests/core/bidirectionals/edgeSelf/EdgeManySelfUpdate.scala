package molecule.tests.core.bidirectionals.edgeSelf

import molecule.tests.core.bidirectionals.dsl.Bidirectional._
import molecule.datomic.api.in1_out3._
import molecule.core.ops.exception.VerifyModelException
import molecule.setup.TestSpec

class EdgeManySelfUpdate extends TestSpec {

  class Setup extends BidirectionalSetup {
    val knownBy = m(Person.name_(?).Knows.*(Knows.weight.Person.name))
    val ann     = Person.name("Ann").save.eid

    // Separate edges
    val Seq(
    knowsBen,
    knowsDon,
    knowsGil,
    knowsHan,
    knowsJoe,
    knowsRon,
    knowsTom
    ): Seq[Long] = Knows.weight.Person.name.insert(List(
      (2, "Ben"),
      (3, "Don"),
      (4, "Gil"),
      (5, "Han"),
      (6, "Joe"),
      (7, "Ron"),
      (8, "Tom")
    )).eids.grouped(3).map(_.head).toSeq
  }


  "add edges" in new Setup {

    // vararg
    Person(ann).knows.assert(knowsBen, knowsDon).update

    // Seq
    Person(ann).knows.assert(Seq(knowsGil)).update

    // Empty list of edges has no effect
    Person(ann).knows.assert(Seq()).update

    knownBy("Ann").get === List(List((2, "Ben"), (3, "Don"), (4, "Gil")))
    knownBy("Ben").get === List(List((2, "Ann")))
    knownBy("Don").get === List(List((3, "Ann")))
    knownBy("Gil").get === List(List((4, "Ann")))
  }


  "replace edges" in new Setup {

    // current friends
    Person(ann).knows.assert(knowsBen, knowsDon, knowsGil, knowsTom).update

    knownBy("Ann").get === List(List((2, "Ben"), (3, "Don"), (4, "Gil"), (8, "Tom")))
    knownBy("Ben").get === List(List((2, "Ann")))
    knownBy("Don").get === List(List((3, "Ann")))
    knownBy("Gil").get === List(List((4, "Ann")))
    knownBy("Tom").get === List(List((8, "Ann")))

    // Replace who Ann knows
    Person(ann).knows.replace(knowsBen -> knowsHan, knowsDon -> knowsJoe).update
    Person(ann).knows.replace(Seq(knowsGil -> knowsRon)).update

    // All friends have been replaced
    knownBy("Ann").get === List(List((5, "Han"), (6, "Joe"), (7, "Ron"), (8, "Tom")))
    knownBy("Han").get === List(List((5, "Ann")))
    knownBy("Joe").get === List(List((6, "Ann")))
    knownBy("Ron").get === List(List((7, "Ann")))
    knownBy("Tom").get === List(List((8, "Ann"))) // Hasn't been replace by empty Seq

    // Replace with empty Seq has no effect
    Person(ann).knows.replace(Seq()).update
    knownBy("Ann").get === List(List((5, "Han"), (6, "Joe"), (7, "Ron"), (8, "Tom")))
  }


  "remove edges" in new Setup {

    // current friends
    Person(ann).knows.assert(knowsBen, knowsDon, knowsGil, knowsTom).update

    knownBy("Ann").get === List(List((2, "Ben"), (3, "Don"), (4, "Gil"), (8, "Tom")))
    knownBy("Ben").get === List(List((2, "Ann")))
    knownBy("Don").get === List(List((3, "Ann")))
    knownBy("Gil").get === List(List((4, "Ann")))
    knownBy("Tom").get === List(List((8, "Ann")))


    // Remove who Ann knows
    Person(ann).knows.retract(knowsBen, knowsDon).update

    // All friends have been replaced
    knownBy("Ann").get === List(List((4, "Gil"), (8, "Tom")))
    knownBy("Ben").get === List()
    knownBy("Don").get === List()
    knownBy("Gil").get === List(List((4, "Ann")))
    knownBy("Tom").get === List(List((8, "Ann")))

    // Remove Seq of edges
    Person(ann).knows.retract(Seq(knowsGil)).update

    // All friends have been replaced
    knownBy("Ann").get === List(List((8, "Tom")))
    knownBy("Ben").get === List()
    knownBy("Don").get === List()
    knownBy("Gil").get === List()
    knownBy("Tom").get === List(List((8, "Ann")))

    // Remove empty Seq of edges has no effect
    Person(ann).knows.retract(Seq()).update

    // All friends have been replaced
    knownBy("Ann").get === List(List((8, "Tom")))
  }


  "apply edges" in new Setup {

    // current friends
    Person(ann).knows.assert(knowsBen, knowsDon, knowsGil).update

    knownBy("Ann").get === List(List((2, "Ben"), (3, "Don"), (4, "Gil")))
    knownBy("Ben").get === List(List((2, "Ann")))
    knownBy("Don").get === List(List((3, "Ann")))
    knownBy("Gil").get === List(List((4, "Ann")))
    knownBy("Tom").get === List()


    // State who Ann knows now
    Person(ann).knows(knowsBen, knowsTom).update

    // Ben remains, Tom added
    knownBy("Ann").get === List(List((2, "Ben"), (8, "Tom")))
    knownBy("Ben").get === List(List((2, "Ann")))
    knownBy("Don").get === List()
    knownBy("Gil").get === List()
    knownBy("Tom").get === List(List((8, "Ann")))

    // Apply Seq of edges
    Person(ann).knows(Seq(knowsHan)).update

    knownBy("Ann").get === List(List((5, "Han")))
    knownBy("Ben").get === List()
    knownBy("Don").get === List()
    knownBy("Gil").get === List()
    knownBy("Han").get === List(List((5, "Ann")))
    knownBy("Tom").get === List()

    // Applying empty Seq retracts all edges from Ann!
    Person(ann).knows(Seq()).update

    knownBy("Ann").get === List()
    knownBy("Han").get === List()


    // Applying no values removes all!

    Person(ann).Knows.weight(7).Person.name("Bob").update
    knownBy("Ann").get === List(List((7, "Bob")))

    Person(ann).knows().update
    knownBy("Ann").get === List()
  }


  "retract edge" in new Setup {

    // current friends
    Person(ann).knows.assert(knowsBen, knowsDon).update

    knownBy("Ann").get === List(List((2, "Ben"), (3, "Don")))
    knownBy("Ben").get === List(List((2, "Ann")))
    knownBy("Don").get === List(List((3, "Ann")))

    // Retract single edge
    knowsBen.retract

    knownBy("Ann").get === List(List((3, "Don")))
    knownBy("Ben").get === List()
    knownBy("Don").get === List(List((3, "Ann")))
  }


  "retract base/target entity" in new Setup {

    // current friends
    Person(ann).knows.assert(knowsBen, knowsDon).update

    knownBy("Ann").get === List(List((2, "Ben"), (3, "Don")))
    knownBy("Ben").get === List(List((2, "Ann")))
    knownBy("Don").get === List(List((3, "Ann")))

    // Retract base entity
    ann.retract

    // All knowing to/from Ann retracted
    knownBy("Ann").get === List()
    knownBy("Ben").get === List()
    knownBy("Don").get === List()
  }


  "no nested in update molecules" in new Setup {

    // Can't update multiple values of cardinality-one attribute `name`
    (Person(ann).Knows.weight(7).Person.name("Joe", "Liz").update must throwA[VerifyModelException])
      .message === "Got the exception molecule.core.ops.exception.VerifyModelException: " +
      s"[noConflictingCardOneValues]  Can't update multiple values for cardinality-one attribute:" +
      "\n  Person ... name(Joe, Liz)"

    // As with save molecules nesting is not allowed in update molecules
//    (Person(ann).Knows.*(Knows.weight(4)).Person.name("Joe").update must throwA[VerifyModelException])
    (Person(ann).Knows.*(Knows.weight(4)).name("Joe").update must throwA[VerifyModelException])
      .message === "Got the exception molecule.core.ops.exception.VerifyModelException: " +
      s"[update_onlyOneNs]  Update molecules can't have nested data structures like `Knows`."

//    (Person(ann).Knows.*(Knows.weight(4)).person(42L).update must throwA[VerifyModelException])
    (Person(ann).Knows.*(Knows.weight(4)).e(42L).update must throwA[VerifyModelException])
      .message === "Got the exception molecule.core.ops.exception.VerifyModelException: " +
      s"[update_onlyOneNs]  Update molecules can't have nested data structures like `Knows`."

    (Person(ann).Knows.*(Knows.weight(4).Person.name("Joe")).update must throwA[VerifyModelException])
      .message === "Got the exception molecule.core.ops.exception.VerifyModelException: " +
      s"[update_onlyOneNs]  Update molecules can't have nested data structures like `Knows`."

    (Person(ann).Knows.*(Knows.weight(4).person(42L)).update must throwA[VerifyModelException])
      .message === "Got the exception molecule.core.ops.exception.VerifyModelException: " +
      s"[update_onlyOneNs]  Update molecules can't have nested data structures like `Knows`."

    // Note that an edge always have only one target entity.
    // So we can't add multiple (won't compile)
    // Person(ann).Knows.weight(6).person(42L, 43L).update

    // Each edge has only 1 target entity so we can't use nested structures on the target namespace
    // (Person.name("Ben").Knows.weight(7).Person.*(Person.name("Joe")).update
    //                                                   ^ nesting of edge target namespace not available
  }
}
