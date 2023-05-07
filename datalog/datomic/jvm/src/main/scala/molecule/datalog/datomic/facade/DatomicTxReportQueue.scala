package molecule.datalog.datomic.facade

import java.util.concurrent.{BlockingQueue => jBlockingQueue}
import java.util.{LinkedList => jLinkedList, Map => jMap}
import molecule.core.util.JavaConversions
import molecule.datalog.core.facade.DatomicTxReport
import molecule.datalog.datomic.util.MakeDatomicTxReport
import scala.concurrent.duration._


/** A transaction report queue associated with a connection.
 *
 * The molecule DatomicTxReportQueue is a wrapper of the
 * java.util.concurrent.BlockingQueue returned by a Datomic
 * Peer Connection.
 *
 * This queue may be safely consumed from more than one thread.
 * Note that the queue does not block producers, and will consume
 * memory until you consume the elements from it.
 *
 * @see https://docs.datomic.com/on-prem/javadoc/datomic/Connection.html#txReportQueue-- datomic.Connection.txReportQueue()
 * @see https://blog.datomic.com/2013/10/the-transaction-report-queue.html
 */
case class DatomicTxReportQueue(javaQueue: jBlockingQueue[jMap[_, _]]) extends JavaConversions {

  /** Removes all available transaction reports from
   * this queue and returns them as a list.
   *
   * This operation may be more efficient than repeatedly
   * polling this queue.
   *
   * @return a list of all available tranaction reports.
   */
  def drain: List[DatomicTxReport] = {
    val txMaps = new jLinkedList[jMap[_, _]]
    javaQueue.drainTo(txMaps)
    val list = List.newBuilder[DatomicTxReport]
    txMaps.forEach(txMap => list += MakeDatomicTxReport(txMap))
    list.result()
  }


  /** Removes at most the given number of available
   * transaction reports from this queue and returns
   * them as a list.
   *
   * This operation may be more efficient than repeatedly
   * polling this queue.
   *
   * @param maxReports the maximum number of reports to transfer.
   * @return a list of all available tranaction reports.
   */
  def drain(maxReports: Int): List[DatomicTxReport] = {
    val txMaps = new jLinkedList[jMap[_, _]]
    javaQueue.drainTo(txMaps, maxReports)
    val list = List.newBuilder[DatomicTxReport]
    txMaps.forEach(txMap => list += MakeDatomicTxReport(txMap))
    list.result()
  }


  /** Retrieves and removes the head of this queue,
   * waiting up to the specified wait time
   * if necessary for an element to become available.
   *
   * Throws [[http://docs.oracle.com/javase/7/docs/api/java/lang/InterruptedException.html InterruptedException]]  if interrupted while waiting.
   *
   * @param timeout the duration of time to wait before giving up.
   * @return the head of this queue, or `None` if the specified
   *         waiting time elapses before an element is available.
   */
  def poll(timeout: Duration): Option[DatomicTxReport] =
    Option(javaQueue.poll(timeout.toNanos, NANOSECONDS)).map(MakeDatomicTxReport(_))


  /** Retrieves and removes the head of this queue,
   * or returns `None` if this queue is empty.
   *
   * @return the head of this queue, or `None`
   *         if this queue is empty.
   */
  def poll: Option[DatomicTxReport] = Option(javaQueue.poll()).map(MakeDatomicTxReport(_))


  /** Retrieves and removes the head of this queue,
   * waiting if necessary until an element becomes available.
   *
   * Throws [[http://docs.oracle.com/javase/7/docs/api/java/lang/InterruptedException.html InterruptedException]]  if interrupted while waiting.
   *
   * @return the head of this queue.
   */
  def take: DatomicTxReport = MakeDatomicTxReport(javaQueue.take())


  /** Retrieves, but does not remove, the head of this queue,
   * or `None` if this queue is empty.
   *
   * @return the head of this queue, or `None` if this
   *         queue is empty.
   */
  def peek: Option[DatomicTxReport] = Option(javaQueue.peek()).map(MakeDatomicTxReport(_))


  /** Returns `true` if this queue contains no transaction reports.
   *
   * @return `true` if this queue contains no transaction reports.
   */
  def isEmpty: Boolean = javaQueue.isEmpty


  /** Returns an iterator over the transaction reports in
   * this queue.
   *
   * @return an `Iterator` over the transaction reports
   *         in this queue.
   */
  def iterator: Iterator[DatomicTxReport] =
    javaQueue.iterator.asScala.map(MakeDatomicTxReport(_))


  /** Returns the number of transaction reports in the queue.
   *
   * @return the number of transaction reports in the queue.
   */
  def size: Int = javaQueue.size()
}

