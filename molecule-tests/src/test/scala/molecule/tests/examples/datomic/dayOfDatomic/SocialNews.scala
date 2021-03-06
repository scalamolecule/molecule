package molecule.tests.examples.datomic.dayOfDatomic

import molecule.datomic.api.out4._
import molecule.setup.TestSpec
import molecule.tests.examples.datomic.dayOfDatomic.dsl.SocialNews._


class SocialNews extends TestSpec {


  "Social News" in new SocialNewsSetup {

    // Add underscore to attribute name to _not_ return it's value (and keep it as a search attribute)
    // Here we get all Story ids (entities having a url value)
    val allStories = Story.url_.e.get

    // Add John and let him upvote all stories
    val john = User.email.firstName.lastName.upVotes insert List(
      ("john@example.com", "John", "Doe", allStories.toSet)
    ) eid

    // Users with upvotes
    User.email.upVotes.get === List(("john@example.com", Set(s1, s2, s3)))

    // Update John's first name
    User(john).firstName("Jonathan").update

    // John regrets upvoting Paul Graham story (`s3`)
    val paulGrahamStory = Story.e.url_("http://www.paulgraham.com/avg.html").get.head
    User(john).upVotes.retract(paulGrahamStory).update

    // John now has only 2 upvotes
    User(john).upVotes.get.head.size === 2

    // Only John's 2 upvotes exist
    User.email.upVotes.get === List(("john@example.com", Set(s1, s2)))

    // Retract all John's upvotes
    User(john).upVotes().update

    // John now has no upvotes
    User(john).upVotes.get.size === 0

    // No Users with upvotes anymore
    User.email.upVotes.get === List()

    // Let Stuart upvote a story
    User(stu).upVotes(paulGrahamStory).update

    // Current Users and upvotes
    User.email.upVotes.get.head === ("stuarthalloway@datomic.com", Set(paulGrahamStory))

    // Current upVotes
    User.email.upVotes$.get.sortBy(_._1) === List(
      ("editor@example.com", None),
      ("john@example.com", None),
      ("stuarthalloway@datomic.com", Some(Set(paulGrahamStory))),
    )
  }
}