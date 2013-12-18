import generator.Generator.{generator, suspendable}
import org.scalatest._

class GeneratorTest extends FlatSpec {
    "A generator" should "work like so" in {
        def example = generator[String] { 
            yld => yld( "first" )
 
            for( i <- suspendable(List(1,2,3)); j <- suspendable(List(4,5,6))) { 
                yld((i*j).toString) 
            }
        
            yld("last")
        }
        assert(example.toArray === Array("first","4","5","6","8","10","12","12","15","18","last"))
    }
}