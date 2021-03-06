package molecule.tests.core.bidirectionals.edgeOther

import molecule.tests.core.bidirectionals.dsl.Bidirectional._
import molecule.datomic.api.in1_out4._
import molecule.core.ops.exception.VerifyModelException
import molecule.setup.TestSpec

class EdgeOneOtherInsert extends TestSpec {

  class Setup extends BidirectionalSetup {
    val favoriteAnimalOf = m(Person.name_(?).Favorite.weight.Animal.name)
    val favoritePersonOf = m(Animal.name_(?).Favorite.weight.Person.name)
  }

  "base/edge/target" >> {

    "new target" in new Setup {

      Person.name.Favorite.weight.Animal.name.insert("Ann", 7, "Rex")

      // Bidirectional property edge has been inserted
      // Note how we query differently from each end
      favoriteAnimalOf("Ann").get === List((7, "Rex"))
      favoritePersonOf("Rex").get === List((7, "Ann"))
    }

    "existing target" in new Setup {

      val ann = Person.name.insert("Ann").eid

      // We can insert from the other end as well
      Animal.name.Favorite.weight.person.insert("Rex", 7, ann)

      // Bidirectional property edge has been inserted
      favoriteAnimalOf("Ann").get === List((7, "Rex"))
      favoritePersonOf("Rex").get === List((7, "Ann"))
    }
  }


  "base + edge/target" >> {

    "new target" in new Setup {

      // Create edges to/from Rex
      val favoriteRex = Favorite.weight.Animal.name.insert(7, "Rex").eid

      // Base entity Ann points to one of the edges (doesn't matter which of them - Molecule connects Ann to both)
      Person.name.favorite.insert("Ann", favoriteRex)

      // Ann loves Rex and Rex loves Ann - that is 70% love
      favoriteAnimalOf("Ann").get === List((7, "Rex"))
      favoritePersonOf("Rex").get === List((7, "Ann"))
    }

    "existing target" in new Setup {

      val rex = Animal.name.insert("Rex").eid

      // Create edges to existing Rex
      val List(favoriteRex, rexFavorite) = Favorite.weight.animal.insert(7, rex).eids

      // Base entity Ann points to one of the edges (doesn't matter which of them - Molecule connects Ann to both)
      Person.name.favorite.insert("Ann", rexFavorite)

      // Ann loves Rex and Rex loves Ann - that is 70% love
      favoriteAnimalOf("Ann").get === List((7, "Rex"))
      favoritePersonOf("Rex").get === List((7, "Ann"))
    }
  }

  "base/edge - <missing target>" in new Setup {
    // Can't allow edge without ref to target entity
    (Person.name.Favorite.weight.insert must throwA[VerifyModelException])
      .message === "Got the exception molecule.core.ops.exception.VerifyModelException: " +
      s"[edgeComplete]  Missing target namespace after edge namespace `Favorite`."
  }

  "<missing base> - edge - <missing target>" in new Setup {
    // Edge always have to have a ref to a target entity
    (Favorite.weight.insert must throwA[VerifyModelException])
      .message === "Got the exception molecule.core.ops.exception.VerifyModelException: " +
      s"[edgeComplete]  Missing target namespace somewhere after edge property `Favorite/weight`."
  }
}
