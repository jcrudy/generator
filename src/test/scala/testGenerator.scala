import generator.Generator.{generator, suspendable}
import org.scalatest._

class GeneratorTest extends FlatSpec {
    "A generator" should "work like so" in {
        def example = generator[Int] { yld => 
            for( i <- suspendable(List(1,2,3))) { 
                yld(i) 
            }
        }
        assert(example.toArray === Array(1,2,3))
    }
}