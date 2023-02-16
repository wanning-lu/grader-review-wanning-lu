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

  @Test
  public void testFilter() {
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
}
