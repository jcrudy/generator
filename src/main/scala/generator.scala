package generator
import scala.util.continuations.{shift, reset, cpsParam}

/** Abstract base class for Python-like generators.
  *
  * Classes and objects extending Generator should implement
  * the generate method, which should call produce.  For example,
  * the following would create a counter that increments each time the 
  * next method is called:
  * 
  *     object Counter extends Generator[Int] {
  *         def generate() = {
  *             var i = 1
  *             while(true) {
  *                 produce(i)
  *                 i += 1
  *             }
  *         }
  *     }
  */
abstract class Generator[T] extends Iterator[T] {

	private var result: T = _
	private var empty = false
	private var callback: Unit=>Unit = null

	reset {
		generate()
		empty = true
	}

	/** Implementation of this method defines the behavior of the generator.
	  *
	  */
	protected def generate(): Unit @cpsParam[Unit,Unit]

	/** The produce method should be called only within the implementation
	  * of generate.  The resulting Generator returns the arguments of 
	  * produce as they are called in generate each time the next method is 
	  * called.
	  *
	  */
	protected def produce(item: T) = {
		shift {f: (Unit=>Unit) =>
			callback = f
			result = item
		}
	}

	def hasNext: Boolean = !empty

	def next(): T = {
		if (empty) {
			throw new NoSuchElementException("reached generator end")
		}
		val thisResult = result
		callback()
		thisResult
	}
}
