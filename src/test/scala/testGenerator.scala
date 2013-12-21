import org.scalatest._
import generator._

class GeneratorTest extends FlatSpec {
    "A Generator" should "be able to produce a Counter object" in {
        object Counter extends Generator[Int] {
            def generate() = {
                var i = 1
                while(true) {
                    produce(i)
                    i += 1
                }
            }
        }

        val counts = for (i <- 0 until 10) yield Counter.next()
        assert(counts === Vector(1,2,3,4,5,6,7,8,9,10))
    }

    it should "be able to act as a base class for a Counter class" in {
        class Counter extends Generator[Int] {
            def generate() = {
                var i = 1
                while(true) {
                    produce(i)
                    i += 1
                }
            }
        }

        // Instantiate a counter
        val counter1 = new Counter()

        // Make sure the first counter works
        val counts1 = for (i <- 0 until 10) yield counter1.next()
        assert(counts1 === Vector(1,2,3,4,5,6,7,8,9,10))

        //Instantiate another counter
        val counter2 = new Counter()

        // Make sure the second one works independently of the first
        val counts2 = for (i <- 0 until 10) yield counter2.next()
        assert(counts2 === Vector(1,2,3,4,5,6,7,8,9,10))

        // And the first one has not lost count
        val counts1_1 = for (i <- 0 until 10) yield counter1.next()
        assert(counts1_1 === Vector(11,12,13,14,15,16,17,18,19,20))
    }

    "The hasNext method of a Generator" should "return false when the Generator has been exhausted" in {
        object CountToThree extends Generator[Int] {
            def generate() = {
                produce(1)
                produce(2)
                produce(3)
            }
        }
        assert(CountToThree.next() == 1)
        assert(CountToThree.next() == 2)
        assert(CountToThree.next() == 3)
        assert(!CountToThree.hasNext)
    }

    it should "return true when the Generator has not yet been exhausted" in {
        object CountToThree extends Generator[Int] {
            def generate() = {
                produce(1)
                produce(2)
                produce(3)
            }
        }
        assert(CountToThree.hasNext)
        assert(CountToThree.next() == 1)
        assert(CountToThree.hasNext)
        assert(CountToThree.next() == 2)
        assert(CountToThree.hasNext)
        assert(CountToThree.next() == 3)
    }

    "The next method of a Generator" should "throw a NoSuchElementException when the Generator has been exhausted" in {
        object CountToThree extends Generator[Int] {
            def generate() = {
                produce(1)
                produce(2)
                produce(3)
            }
        }
        assert(CountToThree.next() == 1)
        assert(CountToThree.next() == 2)
        assert(CountToThree.next() == 3)
        intercept[NoSuchElementException] {
            CountToThree.next()
        }
    }
}
