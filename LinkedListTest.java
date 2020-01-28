import static org.hamcrest.CoreMatchers.*;
import org.junit.*;
import org.junit.rules.*;

import java.util.Iterator;

import static org.junit.Assert.*;

public class LinkedListTest {
    private static final int INVALID_CAP = -1;
    private static final String STRING_VAL1 = "Gondor";
    private static final String STRING_VAL2 = "Mordor";
    private static final String STRING_VAL3 = "Trees";
    private static final String STRING_VAL4 = "Saturn";
    private static final String STRING_VAL5 = "Mercury";
    private static final String STRING_VAL6 = "Spider";
    private static final String STRING_VAL7 = "Phone";
    private static final String STRING_VAL8 = "White_Rabbit";
    private static final String STRING_VAL9 = null;
    private static final String STRING_VAL10 = "Filet";
    private static final String STRING_VAL11 = "Smith_Bible";
    private static final String STRING_VAL12 = "Melon_Armor";
    private static final String STRING_VAL13 = "Gandalf";
    private static final String STRING_VAL14 = "pen";
    private static final String STRING_VAL15 = "salmon";
    private static final String STRING_VAL16 = "Seattle";
    private static final String STRING_VAL17 = "Moscow";
    private static final String STRING_VAL18 = "Rome";
    private static final String STRING_VAL19 = "Lisbon";
    private static final String STRING_VAL20 = "Bryansk";
    private static final String STRING_VAL21 = "Vancouver";

    private static LinkedList<String> _list1;
    private static LinkedList<String> _list2;
    private static LinkedList<String> _list3;
    private static Iterator _iterator1;
    private static Iterator _iterator2;
    private static Iterator _iterator3;
    private static Iterator _reverseIterator1;
    private static Iterator _reverseIterator2;
    private static Iterator _reverseIterator3;

    @BeforeClass
    public static void setup() {
        _list1 = new LinkedList<>();
        _list2 = new LinkedList<>();
        _list3 = new LinkedList<>();
        _iterator1 = _list1.iterator();
        _iterator2 = _list2.iterator();
        _iterator3 = _list3.iterator();
        _reverseIterator1 = _list1.reverseIterator();
        _reverseIterator2 = _list2.reverseIterator();
        _reverseIterator3 = _list3.reverseIterator();
        listTwoSetUp();
        listThreeSetUp();
    }

    private static void listTwoSetUp() {
        _list2.add(STRING_VAL1);
        _list2.add(STRING_VAL2);
        _list2.add(STRING_VAL3);
        _list2.add(STRING_VAL4);
        _list2.add(STRING_VAL5);
        _list2.add(STRING_VAL6);
    }

    private static void listThreeSetUp() {
        _list3.add(STRING_VAL1);
    }

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void test_LinkedList_ctor() {
        assertThat(_list1, instanceOf(LinkedList.class));
    }

    @Test
    public void test_LinkedList_size_empty() {
        assertThat(_list1.size(), equalTo(0));
    }

    @Test
    public void test_LinkedList_list1_get_empty() {
        thrown.expect(IndexOutOfBoundsException.class);
        _list1.get(0);
    }

    @Test
    public void test_LinkedList_list1_remove_empty() {
        thrown.expect(IndexOutOfBoundsException.class);
        _list1.remove(0);
    }

    @Test
    public void test_LinkedList_list1_remove_negative() {
        thrown.expect(IndexOutOfBoundsException.class);
        _list1.remove(-1);
    }

    @Test
    public void test_LinkedList_list3_get_head() {
        assertThat(_list3.get(0), equalTo(STRING_VAL1));
    }

    @Test
    public void test_LinkedList_list3_get_tail() {
        thrown.expect(IndexOutOfBoundsException.class);
        _list3.get(1);
    }

    @Test
    public void test_LinkedList_list2_get_head() {
        assertThat(_list2.get(0), equalTo(STRING_VAL1));
    }

    @Test
    public void test_LinkedList_list2_get_tail() {
        assertThat(_list2.get(5), equalTo(STRING_VAL6));
    }

    @Test
    public void test_LinkedList_list2_get_index() {
        assertThat(_list2.get(1), equalTo(STRING_VAL2));
    }

    @Test
    public void test_LinkedList_list2_get_indexOutOfBounds() {
        thrown.expect(IndexOutOfBoundsException.class);
        _list2.get(6);
    }

    @Test
    public void test_LinkedList_list2_remove_negative() {
        thrown.expect(IndexOutOfBoundsException.class);
        _list2.remove(-1);
    }

    @Test
    public void test_LinkedList_list2_remove_indexOutOfBounds() {
        thrown.expect(IndexOutOfBoundsException.class);
        _list2.remove(6);
    }

    @Test
    public void test_LinkedList_list2_size() {
        assertThat(_list2.size(), equalTo(6));
    }

    @Test
    public void test_LinkedList_list3_size() {
        assertThat(_list3.size(), equalTo(1));
    }

    @Test
    public void test_LinkedList_list1_size() {
        assertThat(_list1.size(), equalTo(0));
    }

    @Test
    public void test_LinkedList_list1_isEmpty() {
        assertThat(_list1.isEmpty(), equalTo(true));
    }

    @Test
    public void test_LinkedList_list2_isEmpty() {
        assertThat(_list2.isEmpty(), equalTo(false));
    }

    @Test
    public void test_LinkedList_list3_isEmpty() {
        assertThat(_list3.isEmpty(), equalTo(false));
    }

    @Test
    public void test_iterator() {
        System.out.println("***");
        while (_iterator2.hasNext()) {
            System.out.println(_iterator2.next());
        }
        System.out.println("***");

        System.out.println("\n\nREVERSE_ITERATOR\n\n");

        System.out.println("***");
        while (_reverseIterator2.hasNext()) {
            System.out.println(_reverseIterator2.next());
        }
        System.out.println("***");

    }
}
