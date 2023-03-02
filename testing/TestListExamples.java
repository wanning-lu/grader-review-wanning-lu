import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class IsMoon implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("moon");
  }
}

public class TestListExamples {
  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testMergeAlreadySorted() {
    List<String> left = Arrays.asList("Awesome", "Awesomer", "Awesomest");
    List<String> right = Arrays.asList("Best", "Bester", "Bestest");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("Awesome", "Awesomer", "Awesomest", "Best", "Bester", "Bestest");
    assertEquals(expected, merged);
  }

  @Test
  public void testFilterInvalidInput() {
    StringChecker sc = new IsMoon();
    assertThrows(NullPointerException.class, () -> {
      ListExamples.filter(null, sc);
    });
    List<String> list = Arrays.asList();
    assertThrows(NullPointerException.class, () -> {
      ListExamples.filter(list, null);
    });
  }

  @Test
  public void testMergeInvalidInput() {
    List<String> list = Arrays.asList();
    assertThrows(NullPointerException.class, () -> {
      ListExamples.merge(null, list);
    });
    assertThrows(NullPointerException.class, () -> {
      ListExamples.merge(list, null);
    });
  }

  @Test
  public void testFilterWithMatch() {
    ArrayList<String> input = new ArrayList<>();
    input.add("Moon");
    input.add("cherry");
    input.add("moon");
    input.add("d");
    input.add("moot");
    input.add("bc");

    List<String> expectedResult = new ArrayList<>();
    StringChecker sc = new IsMoon();
    expectedResult.add("Moon");
    expectedResult.add("moon");
    assertEquals(expectedResult, ListExamples.filter(input, sc));
  }

  @Test
  public void testFilterWithoutMatch() {
    List<String> input = Arrays.asList("moo", "hello", "mOOOn", "moomoo");
    List<String> expected = Arrays.asList();
    StringChecker sc = new IsMoon();

    assertEquals(expected, ListExamples.filter(input, sc));
  }
}
