package molecule.tests.core.bidirectionals.edgeSelf

import molecule.tests.core.bidirectionals.dsl.Bidirectional._
import molecule.datomic.api.in1_out3._
import molecule.setup.TestSpec

class EdgeOneSelfUpdate extends TestSpec {

  class Setup extends BidirectionalSetup {
    val loveOf = m(Person.name_(?).Loves.weight.Person.name)
    val ann    = Person.name("Ann").save.eid
  }


  "apply edge to new target" in new Setup {

    // New edge and new target entity
    Person(ann).Loves.weight(5).Person.name("Ben").update

    // Ann and Ben loves each other
    loveOf("Ann").get === List((5, "Ben"))
    loveOf("Ben").get === List((5, "Ann"))
    loveOf("Joe").get === List() // Joe doesn't exist yet

    // Ann now loves Joe
    Person(ann).Loves.weight(8).Person.name("Joe").update

    // Both bidirectional edges have been added from/to Ann
    loveOf("Ann").get === List((8, "Joe"))
    loveOf("Ben").get === List()
    loveOf("Joe").get === List((8, "Ann"))

    // Even though Ann now loves Joe, Ben still exists
    Person.name.get.sorted === List("Ann", "Ben", "Joe")
  }


  "apply edge to existing target" in new Setup {

    val List(ben, joe) = Person.name.insert("Ben", "Joe").eids

    Person(ann).Loves.weight(5).person(ben).update

    loveOf("Ann").get === List((5, "Ben"))
    loveOf("Ben").get === List((5, "Ann"))
    loveOf("Joe").get === List()

    // Ann now loves Joe
    Person(ann).Loves.weight(8).person(joe).update

    loveOf("Ann").get === List((8, "Joe"))
    loveOf("Ben").get === List()
    loveOf("Joe").get === List((8, "Ann"))
  }


  "retract edge" in new Setup {

    val List(_, annBen, _, _) = Person.name("Ann").Loves.weight(5).Person.name("Ben").save.eids

    loveOf("Ann").get === List((5, "Ben"))
    loveOf("Ben").get === List((5, "Ann"))

    // Retract edge
    annBen.retract

    // Divorce complete
    loveOf("Ann").get === List()
    loveOf("Ben").get === List()
  }


  "retract base/target entity" in new Setup {

    val List(_, _, _, ben) = Person.name("Ann").Loves.weight(5).Person.name("Ben").save.eids

    loveOf("Ann").get === List((5, "Ben"))
    loveOf("Ben").get === List((5, "Ann"))

    // Retract base entity with single edge
    ben.retract

    // Ann becomes widow
    Person.name("Ann").get === List("Ann")
    Person.name("Ben").get === List()

    loveOf("Ann").get === List()
    loveOf("Ben").get === List()
  }
}
