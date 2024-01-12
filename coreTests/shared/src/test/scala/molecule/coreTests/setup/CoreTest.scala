package molecule.coreTests.setup

trait CoreTest extends TestData {
  val isJsPlatform: Boolean
  val database    : String
  val platform    : String
}
