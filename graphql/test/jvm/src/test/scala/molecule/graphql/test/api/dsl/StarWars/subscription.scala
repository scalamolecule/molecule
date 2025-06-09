package molecule.graphql.test.api.dsl.StarWars

import molecule.graphql.test.api.dsl.StarWars.output.Review

object subscription extends subscription

trait subscription {
  def reviewCreated(): () => Review = ??? // ?
}

