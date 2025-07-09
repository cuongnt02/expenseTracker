import com.github.ajalt.clikt.testing.test
import com.ntc.expenseTracker.Add
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class AddCommandTest {
    @Test
    fun `should add expense successfully`() {
        val addCommand = Add()
        val result = addCommand.test("--amount 500000.99 --description Buffet --category Food")
        assertEquals(0, result.statusCode)
        assertEquals("Spent 500000.99 on Buffet, this would be categorized as Food\n", result.stdout)
        assertEquals(500000.99, addCommand.amount)
        assertEquals("Buffet", addCommand.description)
        assertEquals("Food", addCommand.category)
    }

    @Test
    fun `should not add expense with no amount and desc`() {
        val addCommand = Add()
        val result = addCommand.test("--category='Nothing'")
        assertNotEquals("", result.stderr)
        assertNotEquals(0, result.statusCode)
    }

    @Test
    fun `should not add expense with negative amount`() {
        val addCommand = Add()
        val result = addCommand.test("--amount -100000.55 --description 'Got some money' --category='Nothing'")
        assertNotEquals(0, result.statusCode)
    }

    @Test
    fun `should not add empty or blank desc`() {
        val addCommand = Add()
        var result = addCommand.test("--amount -100000.55 --description '  ' --category='Nothing'")
        assertNotEquals(0, result.statusCode)
        assertNotEquals("", result.stderr)
        assertEquals("", result.stdout)
        result = addCommand.test("--amount -100000.55 --description '  ' --category='Nothing'")
        assertEquals("", result.stdout)
        assertNotEquals(0, result.statusCode)
        assertNotEquals("", result.stderr)
    }

    @Test
    fun `should add expense without category`() {
        val addCommand = Add()
        val result = addCommand.test("--amount 500000 --description 'Buffet'")
        assertEquals("", result.stderr)
        assertEquals(0, result.statusCode)
        assertEquals("Spent 500000 on Buffet\n", result.stdout)
    }
}
